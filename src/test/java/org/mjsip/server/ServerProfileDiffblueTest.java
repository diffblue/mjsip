package org.mjsip.server;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.zoolu.util.Configure;

class ServerProfileDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    ServerProfile actualServerProfile = new ServerProfile("File");

    // Assert
    assertTrue(actualServerProfile.register_new_users);
    assertEquals(180000, ServerProfile.proxy_transaction_timeout);
    assertEquals(0, actualServerProfile.phone_proxying_rules.length);
    assertFalse(actualServerProfile.on_route);
    assertFalse(actualServerProfile.memory_log);
    assertTrue(actualServerProfile.loose_route);
    assertTrue(actualServerProfile.loop_detection);
    assertEquals("local", actualServerProfile.location_service);
    assertEquals("users.db", actualServerProfile.location_db);
    assertTrue(actualServerProfile.is_registrar);
    assertTrue(actualServerProfile.is_open_proxy);
    assertEquals(3600, actualServerProfile.expires);
    assertEquals(0, actualServerProfile.domain_proxying_rules.length);
    assertFalse(actualServerProfile.domain_port_any);
    String[] stringArray = actualServerProfile.domain_names;
    assertEquals(0, stringArray.length);
    assertArrayEquals(new String[]{}, stringArray);
    assertFalse(actualServerProfile.do_proxy_authentication);
    assertFalse(actualServerProfile.do_authentication);
    assertFalse(actualServerProfile.clean_location_db);
    assertFalse(actualServerProfile.call_log);
    assertEquals("local", actualServerProfile.authentication_service);
    assertEquals("Digest", actualServerProfile.authentication_scheme);
    assertNull(actualServerProfile.authentication_realm);
    assertEquals("aaa.db", actualServerProfile.authentication_db);
    assertEquals(0, actualServerProfile.authenticated_phone_proxying_rules.length);
    assertEquals(0, actualServerProfile.authenticated_domain_proxying_rules.length);
    assertEquals("NONE", Configure.NONE);
  }

  @Test
  void testConstructor2() {
    // Arrange and Act
    ServerProfile actualServerProfile = new ServerProfile("File");

    // Assert
    assertTrue(actualServerProfile.register_new_users);
    assertEquals(0, actualServerProfile.phone_proxying_rules.length);
    assertFalse(actualServerProfile.on_route);
    assertFalse(actualServerProfile.memory_log);
    assertTrue(actualServerProfile.loose_route);
    assertTrue(actualServerProfile.loop_detection);
    assertEquals("local", actualServerProfile.location_service);
    assertEquals("users.db", actualServerProfile.location_db);
    assertTrue(actualServerProfile.is_registrar);
    assertTrue(actualServerProfile.is_open_proxy);
    assertEquals(3600, actualServerProfile.expires);
    assertEquals(0, actualServerProfile.domain_proxying_rules.length);
    assertFalse(actualServerProfile.domain_port_any);
    assertEquals(0, actualServerProfile.domain_names.length);
    assertFalse(actualServerProfile.do_proxy_authentication);
    assertFalse(actualServerProfile.do_authentication);
    assertFalse(actualServerProfile.clean_location_db);
    assertFalse(actualServerProfile.call_log);
    assertEquals("local", actualServerProfile.authentication_service);
    assertEquals("Digest", actualServerProfile.authentication_scheme);
    assertNull(actualServerProfile.authentication_realm);
    assertEquals("aaa.db", actualServerProfile.authentication_db);
    assertEquals(0, actualServerProfile.authenticated_phone_proxying_rules.length);
    assertEquals(0, actualServerProfile.authenticated_domain_proxying_rules.length);
  }

  @Test
  void testConstructor3() {
    // Arrange and Act
    ServerProfile actualServerProfile = new ServerProfile(null);

    // Assert
    assertTrue(actualServerProfile.register_new_users);
    assertEquals(0, actualServerProfile.phone_proxying_rules.length);
    assertFalse(actualServerProfile.on_route);
    assertFalse(actualServerProfile.memory_log);
    assertTrue(actualServerProfile.loose_route);
    assertTrue(actualServerProfile.loop_detection);
    assertEquals("local", actualServerProfile.location_service);
    assertEquals("users.db", actualServerProfile.location_db);
    assertTrue(actualServerProfile.is_registrar);
    assertTrue(actualServerProfile.is_open_proxy);
    assertEquals(3600, actualServerProfile.expires);
    assertEquals(0, actualServerProfile.domain_proxying_rules.length);
    assertFalse(actualServerProfile.domain_port_any);
    assertEquals(0, actualServerProfile.domain_names.length);
    assertFalse(actualServerProfile.do_proxy_authentication);
    assertFalse(actualServerProfile.do_authentication);
    assertFalse(actualServerProfile.clean_location_db);
    assertFalse(actualServerProfile.call_log);
    assertEquals("local", actualServerProfile.authentication_service);
    assertEquals("Digest", actualServerProfile.authentication_scheme);
    assertNull(actualServerProfile.authentication_realm);
    assertEquals("aaa.db", actualServerProfile.authentication_db);
    assertEquals(0, actualServerProfile.authenticated_phone_proxying_rules.length);
    assertEquals(0, actualServerProfile.authenticated_domain_proxying_rules.length);
  }

  @Test
  void testParseLine() {
    // Arrange
    ServerProfile serverProfile = new ServerProfile("File");

    // Act
    serverProfile.parseLine("is_registrar");

    // Assert
    assertFalse(serverProfile.is_registrar);
  }

  @Test
  void testParseLine2() {
    // Arrange
    ServerProfile serverProfile = new ServerProfile("File");

    // Act
    serverProfile.parseLine("register_new_users");

    // Assert
    assertFalse(serverProfile.register_new_users);
  }

  @Test
  void testParseLine3() {
    // Arrange
    ServerProfile serverProfile = new ServerProfile("File");

    // Act
    serverProfile.parseLine("is_open_proxy");

    // Assert
    assertFalse(serverProfile.is_open_proxy);
  }

  @Test
  void testParseLine4() {
    // Arrange
    ServerProfile serverProfile = new ServerProfile("=");

    // Act
    serverProfile.parseLine("authenticated_domain_proxying_rules");

    // Assert
    assertEquals(0, serverProfile.authenticated_domain_proxying_rules.length);
  }

  @Test
  void testParseLine5() {
    // Arrange
    ServerProfile serverProfile = new ServerProfile("File");

    // Act
    serverProfile.parseLine("location_service");

    // Assert
    assertEquals("", serverProfile.location_service);
  }

  @Test
  void testParseLine6() {
    // Arrange
    ServerProfile serverProfile = new ServerProfile("=");

    // Act
    serverProfile.parseLine("authenticated_phone_proxying_rules");

    // Assert
    assertEquals(0, serverProfile.authenticated_phone_proxying_rules.length);
  }

  @Test
  void testParseLine7() {
    // Arrange
    ServerProfile serverProfile = new ServerProfile("File");

    // Act
    serverProfile.parseLine("location_db");

    // Assert
    assertEquals("", serverProfile.location_db);
  }

  @Test
  void testParseLine8() {
    // Arrange
    ServerProfile serverProfile = new ServerProfile("=");

    // Act
    serverProfile.parseLine("authentication_db");

    // Assert
    assertEquals("", serverProfile.authentication_db);
  }

  @Test
  void testParseLine9() {
    // Arrange
    ServerProfile serverProfile = new ServerProfile("File");

    // Act
    serverProfile.parseLine("clean_location_db");

    // Assert
    assertFalse(serverProfile.clean_location_db);
  }

  @Test
  void testParseLine10() {
    // Arrange
    ServerProfile serverProfile = new ServerProfile("=");

    // Act
    serverProfile.parseLine("authentication_realm");

    // Assert
    assertEquals("", serverProfile.authentication_realm);
  }

  @Test
  void testParseLine11() {
    // Arrange
    ServerProfile serverProfile = new ServerProfile("File");

    // Act
    serverProfile.parseLine("do_authentication");

    // Assert
    assertFalse(serverProfile.do_authentication);
  }

  @Test
  void testParseLine12() {
    // Arrange
    ServerProfile serverProfile = new ServerProfile("=");

    // Act
    serverProfile.parseLine("authentication_scheme");

    // Assert
    assertEquals("", serverProfile.authentication_scheme);
  }

  @Test
  void testParseLine13() {
    // Arrange
    ServerProfile serverProfile = new ServerProfile("File");

    // Act
    serverProfile.parseLine("do_proxy_authentication");

    // Assert
    assertFalse(serverProfile.do_proxy_authentication);
  }

  @Test
  void testParseLine14() {
    // Arrange
    ServerProfile serverProfile = new ServerProfile("=");

    // Act
    serverProfile.parseLine("authentication_service");

    // Assert
    assertEquals("", serverProfile.authentication_service);
  }

  @Test
  void testParseLine15() {
    // Arrange
    ServerProfile serverProfile = new ServerProfile("=");

    // Act
    serverProfile.parseLine("call_log");

    // Assert
    assertFalse(serverProfile.call_log);
  }
}

