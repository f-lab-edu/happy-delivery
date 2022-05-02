package com.happy.delivery;

import static com.happy.delivery.infra.enumeration.Status.USER;

import com.happy.delivery.domain.common.AuthorizationToken;
import com.happy.delivery.infra.enumeration.Status;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@SpringBootTest
class DeliveryApplicationTests {

  private static final String TOKEN = "aqswdefr1234";
  private static final Long ID = 1L;

  @Autowired
  RedisTemplate<String, Object> redisTemplate;

  @Test
  public void testStringObject() {

    // given
    @SuppressWarnings("unchecked")
    ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
    valueOperations.set(TOKEN, new AuthorizationToken(TOKEN, ID, Status.USER));

    // when
    Object tokenObj = valueOperations.get(TOKEN);
    AuthorizationToken token = (AuthorizationToken) tokenObj;

    // then
    Assertions.assertThat(token.getToken()).isEqualTo(TOKEN);
    Assertions.assertThat(token.getId()).isEqualTo(ID);
    Assertions.assertThat(token.getAuthority()).isEqualTo(USER);
  }
}
