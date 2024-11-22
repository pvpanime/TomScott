package dev.nemi.tomscott;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestExample {

  @Test
  public void test() {
    int v1 = 100;
    int v2 = 100;
    Assertions.assertEquals(v1, v2);
  }
}
