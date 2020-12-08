package com.org.trustservice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TrustServiceApplicationTests {

  @Test
  void contextLoads() {
    Assertions.assertDoesNotThrow(() -> TrustServiceApplication.main(new String[]{}));
  }
}
