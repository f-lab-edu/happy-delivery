package com.happy.delivery;

import com.happy.delivery.infra.encoder.EncryptMapper;
import com.happy.delivery.infra.encoder.JbCrypt;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DeliveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeliveryApplication.class, args);
	}

}
