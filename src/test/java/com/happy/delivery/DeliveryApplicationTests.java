package com.happy.delivery;

import com.happy.delivery.domain.User;
import com.happy.delivery.infra.repository.UserHashMapRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DeliveryApplicationTests {

	@Test
	void saveTest() {
		//given
		User user = new User("id","password","phoneNumber","email","address");
		UserHashMapRepository repository = new UserHashMapRepository();
		//when
		repository.save(user);
		//then
	}

}
