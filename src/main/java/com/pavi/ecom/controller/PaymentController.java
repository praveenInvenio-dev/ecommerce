package com.pavi.ecom.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pavi.ecom.Exception.OrderException;
import com.pavi.ecom.model.Orders;
import com.pavi.ecom.model.PaymentDetails;
import com.pavi.ecom.model.PaymentLinkResponse;
import com.pavi.ecom.repository.OrderRepository;
import com.razorpay.Order;
import com.razorpay.Payment;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/payment")
public class PaymentController {

	@Value("${razorpay.api.key}")
	private String key;

	@Value("${razorpay.api.secret}")
	private String secret;

	@Autowired
	private OrderRepository orderRepository;

	@GetMapping
	public String getpayment() {
		return key + " " + secret;
	}

	@PostMapping("/place/{orderid}")
	public ResponseEntity<PaymentLinkResponse> createPaymentLink(@PathVariable Long orderid)
			throws OrderException, RazorpayException {
		Orders order = orderRepository.findById(orderid).get();

		try {
			RazorpayClient razorpayClient = new RazorpayClient(key, secret);

			JSONObject paymentLinkRequest = new JSONObject();
			paymentLinkRequest.put("amount", 100);
			paymentLinkRequest.put("currency", "INR");

			JSONObject customer = new JSONObject();
			customer.put("name", order.getUser().getFirstName());
			customer.put("email", order.getUser().getEmail());
			paymentLinkRequest.put("customer", customer);

			JSONObject notify = new JSONObject();
			notify.put("sms", true);
			notify.put("email", true);
			paymentLinkRequest.put("notify", notify);
			paymentLinkRequest.put("callback_url", "http://localhost:4200/#/payment");

			PaymentLink payment = razorpayClient.paymentLink.create(paymentLinkRequest);
			String paymentLinkId = payment.get("id");
			String paymentLinkurl = payment.get("short_url");

			PaymentLinkResponse res = new PaymentLinkResponse();
			res.setPaymentLinkId(paymentLinkId);
			res.setPaymentLinkResponse(paymentLinkurl);

			return new ResponseEntity<PaymentLinkResponse>(res, HttpStatus.CREATED);
		} catch (Exception e) {
			throw new RazorpayException(e.getMessage());
		}

	}

	@GetMapping("/details")
	public ResponseEntity<PaymentLinkResponse> redirect(@RequestParam(name = "payment_id") String payment_id,
			@RequestParam(name = "order_id") Long orderId) throws OrderException, RazorpayException {
		Orders order = orderRepository.findById(orderId).get();
		RazorpayClient razorpayClient = new RazorpayClient(key, secret);
		try {
			Payment payment = razorpayClient.payments.fetch(payment_id);

			if (payment.get("status").equals("captured")) {
				PaymentDetails p = new PaymentDetails();
				p.setPaymentId(payment.get("id"));
				p.setPaymentMethod(payment.get("method"));
				p.setPaymentStatus(payment.get("status"));
				// p.setRazorPaymentLinkReferenceId(payment.get("acquirer_data").get("upi_transaction_id"));
				// p.set(payment.get("status"));

				order.setPaymentDetails(p);
				order.setOrderStatus("Processed");
				orderRepository.save(order);
			}
			PaymentLinkResponse res = new PaymentLinkResponse();
			res.setPaymentLinkId(order.getOrderId());
			res.setPaymentLinkResponse("Your order " + order.getOrderId() + " is placed");
			return new ResponseEntity<PaymentLinkResponse>(res, HttpStatus.CREATED);
		} catch (Exception e) {
			throw new RazorpayException(e.getMessage());
		}

	}

}
