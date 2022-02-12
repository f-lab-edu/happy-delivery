package com.happy.delivery.application.encrypt;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Sha256Encrypt {

    public String encrypt(String plainText) throws NoSuchAlgorithmException {
        MessageDigest instance = MessageDigest.getInstance("SHA-256");
        instance.update(plainText.getBytes(StandardCharsets.UTF_8));
        System.out.println(byteToHex(instance.digest()));
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
