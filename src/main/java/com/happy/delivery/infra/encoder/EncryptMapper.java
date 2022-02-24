package com.happy.delivery.infra.encoder;

/**
 * EncryptMapper.
 */
public interface EncryptMapper {

  String encoder(String password);

  boolean isMatch(String password, String hashed);
}
