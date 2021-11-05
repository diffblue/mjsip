package org.mjsip.server;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import java.io.UnsupportedEncodingException;
import org.junit.jupiter.api.Test;

class UserAuthInfoDiffblueTest {
  @Test
  void testConstructor() throws UnsupportedEncodingException {
    // Arrange and Act
    UserAuthInfo actualUserAuthInfo = new UserAuthInfo("Name", "AAAAAAAA".getBytes("UTF-8"));
    actualUserAuthInfo.setKey("AAAAAAAA".getBytes("UTF-8"));
    actualUserAuthInfo.setName("Name");

    // Assert
    byte[] expectedKey = actualUserAuthInfo.key;
    assertSame(expectedKey, actualUserAuthInfo.getKey());
    assertEquals("Name", actualUserAuthInfo.getName());
    assertEquals("user= Name\r\nkey= QUFBQUFBQUE=\r\n", actualUserAuthInfo.toString());
  }
}

