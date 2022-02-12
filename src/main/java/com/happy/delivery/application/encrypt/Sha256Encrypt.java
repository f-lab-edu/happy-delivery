package com.happy.delivery.application.encrypt;

import org.slf4j.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Sha256Encrypt {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public String encrypt(String plainText) throws NoSuchAlgorithmException {
        MessageDigest instance = MessageDigest.getInstance("SHA-256");
        instance.update(plainText.getBytes(StandardCharsets.UTF_8));
        logger.info("encrypt = {}", instance.digest());
        return byteToHex(instance.digest());
    }

    private String byteToHex(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }
}
