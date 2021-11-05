package org.mjsip.server;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.UnsupportedEncodingException;
import org.junit.jupiter.api.Test;

class AuthenticationServiceImplDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    AuthenticationServiceImpl actualAuthenticationServiceImpl = new AuthenticationServiceImpl("File name");

    // Assert
    assertEquals("File name", actualAuthenticationServiceImpl.getName());
    assertFalse(actualAuthenticationServiceImpl.isChanged());
    assertTrue(actualAuthenticationServiceImpl.users.isEmpty());
  }

  @Test
  void testConstructor2() {
    // Arrange and Act
    AuthenticationServiceImpl actualAuthenticationServiceImpl = new AuthenticationServiceImpl("File name");

    // Assert
    assertEquals("File name", actualAuthenticationServiceImpl.getName());
    assertTrue(actualAuthenticationServiceImpl.users.isEmpty());
    assertFalse(actualAuthenticationServiceImpl.isChanged());
  }

  @Test
  void testSync() {
    // Arrange
    AuthenticationServiceImpl authenticationServiceImpl = new AuthenticationServiceImpl("File name");

    // Act
    authenticationServiceImpl.sync();

    // Assert that nothing has changed
    assertEquals("File name", authenticationServiceImpl.getName());
    assertTrue(authenticationServiceImpl.users.isEmpty());
    assertFalse(authenticationServiceImpl.isChanged());
  }

  @Test
  void testSize() {
    // Arrange, Act and Assert
    assertEquals(0, (new AuthenticationServiceImpl("File name")).size());
  }

  @Test
  void testHasUser() {
    // Arrange, Act and Assert
    assertFalse((new AuthenticationServiceImpl("File name")).hasUser("User"));
  }

  @Test
  void testHasUser2() {
    // Arrange
    AuthenticationServiceImpl authenticationServiceImpl = new AuthenticationServiceImpl("File name");
    authenticationServiceImpl.addUser("User");

    // Act and Assert
    assertTrue(authenticationServiceImpl.hasUser("User"));
  }

  @Test
  void testAddUser() {
    // Arrange
    AuthenticationServiceImpl authenticationServiceImpl = new AuthenticationServiceImpl("File name");

    // Act
    Repository actualAddUserResult = authenticationServiceImpl.addUser("User");

    // Assert
    assertSame(authenticationServiceImpl, actualAddUserResult);
    assertEquals(1, ((AuthenticationServiceImpl) actualAddUserResult).users.size());
    assertEquals("user= User\r\nkey= \r\n", actualAddUserResult.toString());
    assertTrue(((AuthenticationServiceImpl) actualAddUserResult).isChanged());
  }

  @Test
  void testAddUser2() {
    // Arrange
    AuthenticationServiceImpl authenticationServiceImpl = new AuthenticationServiceImpl("File name");
    authenticationServiceImpl.addUser("User");

    // Act and Assert
    assertSame(authenticationServiceImpl, authenticationServiceImpl.addUser("User"));
  }

  @Test
  void testAddUser3() throws UnsupportedEncodingException {
    // Arrange
    AuthenticationServiceImpl authenticationServiceImpl = new AuthenticationServiceImpl("File name");

    // Act
    AuthenticationService actualAddUserResult = authenticationServiceImpl.addUser("User", "AAAAAAAA".getBytes("UTF-8"));

    // Assert
    assertSame(authenticationServiceImpl, actualAddUserResult);
    assertEquals(1, ((AuthenticationServiceImpl) actualAddUserResult).users.size());
    assertEquals("user= User\r\nkey= QUFBQUFBQUE=\r\n", actualAddUserResult.toString());
    assertTrue(((AuthenticationServiceImpl) actualAddUserResult).isChanged());
  }

  @Test
  void testAddUser4() throws UnsupportedEncodingException {
    // Arrange
    AuthenticationServiceImpl authenticationServiceImpl = new AuthenticationServiceImpl("File name");
    authenticationServiceImpl.addUser("User");

    // Act and Assert
    assertSame(authenticationServiceImpl, authenticationServiceImpl.addUser("User", "AAAAAAAA".getBytes("UTF-8")));
  }

  @Test
  void testSetUserKey() throws UnsupportedEncodingException {
    // Arrange
    AuthenticationServiceImpl authenticationServiceImpl = new AuthenticationServiceImpl("File name");

    // Act and Assert
    assertSame(authenticationServiceImpl, authenticationServiceImpl.setUserKey("User", "AAAAAAAA".getBytes("UTF-8")));
  }

  @Test
  void testSetUserKey2() throws UnsupportedEncodingException {
    // Arrange
    AuthenticationServiceImpl authenticationServiceImpl = new AuthenticationServiceImpl("File name");
    authenticationServiceImpl.addUser("User");

    // Act
    AuthenticationService actualSetUserKeyResult = authenticationServiceImpl.setUserKey("User",
        "AAAAAAAA".getBytes("UTF-8"));

    // Assert
    assertSame(authenticationServiceImpl, actualSetUserKeyResult);
    assertEquals("user= User\r\nkey= QUFBQUFBQUE=\r\n", actualSetUserKeyResult.toString());
    assertTrue(((AuthenticationServiceImpl) actualSetUserKeyResult).isChanged());
  }

  @Test
  void testRemoveUser() {
    // Arrange
    AuthenticationServiceImpl authenticationServiceImpl = new AuthenticationServiceImpl("File name");

    // Act and Assert
    assertSame(authenticationServiceImpl, authenticationServiceImpl.removeUser("User"));
    assertTrue(((AuthenticationServiceImpl) authenticationServiceImpl.removeUser("User")).users.isEmpty());
    assertTrue(((AuthenticationServiceImpl) authenticationServiceImpl.removeUser("User")).isChanged());
  }

  @Test
  void testRemoveAllUsers() {
    // Arrange
    AuthenticationServiceImpl authenticationServiceImpl = new AuthenticationServiceImpl("File name");

    // Act and Assert
    assertSame(authenticationServiceImpl, authenticationServiceImpl.removeAllUsers());
    assertTrue(((AuthenticationServiceImpl) authenticationServiceImpl.removeAllUsers()).users.isEmpty());
    assertTrue(((AuthenticationServiceImpl) authenticationServiceImpl.removeAllUsers()).isChanged());
  }

  @Test
  void testGetUserKey() {
    // Arrange, Act and Assert
    assertNull((new AuthenticationServiceImpl("File name")).getUserKey("User"));
  }

  @Test
  void testGetUserKey2() {
    // Arrange
    AuthenticationServiceImpl authenticationServiceImpl = new AuthenticationServiceImpl("File name");
    authenticationServiceImpl.addUser("User");

    // Act and Assert
    assertEquals(0, authenticationServiceImpl.getUserKey("User").length);
  }

  @Test
  void testLoad() {
    // Arrange
    AuthenticationServiceImpl authenticationServiceImpl = new AuthenticationServiceImpl("File name");

    // Act
    authenticationServiceImpl.load();

    // Assert
    assertFalse(authenticationServiceImpl.isChanged());
  }

  @Test
  void testToString() {
    // Arrange, Act and Assert
    assertEquals("", (new AuthenticationServiceImpl("File name")).toString());
  }

  @Test
  void testToString2() {
    // Arrange
    AuthenticationServiceImpl authenticationServiceImpl = new AuthenticationServiceImpl("File name");
    authenticationServiceImpl.addUser("");

    // Act and Assert
    assertEquals("user= \r\nkey= \r\n", authenticationServiceImpl.toString());
  }

  @Test
  void testToString3() throws UnsupportedEncodingException {
    // Arrange
    AuthenticationServiceImpl authenticationServiceImpl = new AuthenticationServiceImpl("File name");
    authenticationServiceImpl.addUser("", "AAAAAAAA".getBytes("UTF-8"));

    // Act and Assert
    assertEquals("user= \r\nkey= QUFBQUFBQUE=\r\n", authenticationServiceImpl.toString());
  }

  @Test
  void testToString4() throws UnsupportedEncodingException {
    // Arrange
    AuthenticationServiceImpl authenticationServiceImpl = new AuthenticationServiceImpl("File name");
    authenticationServiceImpl.addUser("", "AAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    // Act and Assert
    assertEquals("user= \r\nkey= QUFBQUFBQUFBQUFBQUFBQQ==\r\n", authenticationServiceImpl.toString());
  }
}

