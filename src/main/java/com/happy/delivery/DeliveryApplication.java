package com.happy.delivery;

import com.happy.delivery.infra.util.encoder.EncryptMapper;
import com.happy.delivery.infra.util.encoder.JbCrypt;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DeliveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeliveryApplication.class, args);
	}

}
