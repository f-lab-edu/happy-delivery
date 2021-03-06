package com.happy.delivery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * DeliveryApplication.
 */
@SpringBootApplication(scanBasePackages = "com.happy.delivery")
public class DeliveryApplication {

  public static void main(String[] args) {
    SpringApplication.run(DeliveryApplication.class, args);
  }
}
