package com.example.verifit;

import com.example.verifit.controller.MemberController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class VerifitApplicationTests {

	@Autowired
	private MemberController memberController;

	@Test
	void contextLoads() throws Exception {
		assertThat(memberController).isNotNull();
	}

}
