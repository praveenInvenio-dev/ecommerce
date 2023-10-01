package com.pavi.ecom.custom;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.hibernate.tuple.ValueGenerator;
import org.springframework.stereotype.Component;


@Component
public class CustomIdGenerator {

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	private static final Random random = new Random();

	public String generateValue() {
		String timestamp = dateFormat.format(new Date());
		String randomNumbers = String.format("%04d", random.nextInt(10000));
		return timestamp + randomNumbers;
	}

}