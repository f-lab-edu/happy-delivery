package com.happy.delivery.infra.util.encoder;

public interface EncryptMapper {
    String encoder(String password);
    boolean isMatch(String password, String hashed);
}
