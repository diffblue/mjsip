package org.mjsip.server.sbc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import java.util.Vector;
import org.junit.jupiter.api.Test;

class SessionBorderControllerProfileDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    SessionBorderControllerProfile actualSessionBorderControllerProfile = new SessionBorderControllerProfile();

    // Assert
    assertEquals(0, actualSessionBorderControllerProfile.sink_port);
    assertEquals("127.0.0.1", actualSessionBorderControllerProfile.sink_addr);
    assertEquals(60000L, actualSessionBorderControllerProfile.relay_timeout);
    Vector vector = actualSessionBorderControllerProfile.media_ports;
    assertEquals(100, vector.size());
    assertEquals(41000, vector.get(0));
    assertEquals(41002, vector.get(1));
    assertEquals(41004, vector.get(2));
    assertEquals(41194, vector.get(97));
    assertEquals(41196, vector.get(98));
    assertEquals(41198, vector.get(99));
    assertEquals("0.0.0.0", actualSessionBorderControllerProfile.media_addr);
    assertEquals(0L, actualSessionBorderControllerProfile.keepalive_time);
    assertFalse(actualSessionBorderControllerProfile.keepalive_aggressive);
    assertEquals(0L, actualSessionBorderControllerProfile.interpacket_time);
    assertEquals(0, actualSessionBorderControllerProfile.handover_time);
    assertFalse(actualSessionBorderControllerProfile.do_interception);
    assertFalse(actualSessionBorderControllerProfile.do_active_interception);
    assertEquals(3600000L, actualSessionBorderControllerProfile.binding_timeout);
    assertNull(actualSessionBorderControllerProfile.backend_proxy);
  }

  @Test
  void testConstructor2() {
    // Arrange and Act
    SessionBorderControllerProfile actualSessionBorderControllerProfile = new SessionBorderControllerProfile("File");

    // Assert
    assertEquals(0, actualSessionBorderControllerProfile.sink_port);
    assertEquals("127.0.0.1", actualSessionBorderControllerProfile.sink_addr);
    assertEquals(60000L, actualSessionBorderControllerProfile.relay_timeout);
    Vector vector = actualSessionBorderControllerProfile.media_ports;
    assertEquals(100, vector.size());
    assertEquals(41000, vector.get(0));
    assertEquals(41002, vector.get(1));
    assertEquals(41004, vector.get(2));
    assertEquals(41194, vector.get(97));
    assertEquals(41196, vector.get(98));
    assertEquals(41198, vector.get(99));
    assertEquals("0.0.0.0", actualSessionBorderControllerProfile.media_addr);
    assertEquals(0L, actualSessionBorderControllerProfile.keepalive_time);
    assertFalse(actualSessionBorderControllerProfile.keepalive_aggressive);
    assertEquals(0L, actualSessionBorderControllerProfile.interpacket_time);
    assertEquals(0, actualSessionBorderControllerProfile.handover_time);
    assertFalse(actualSessionBorderControllerProfile.do_interception);
    assertFalse(actualSessionBorderControllerProfile.do_active_interception);
    assertEquals(3600000L, actualSessionBorderControllerProfile.binding_timeout);
    assertNull(actualSessionBorderControllerProfile.backend_proxy);
  }

  @Test
  void testParseLine() {
    // Arrange
    SessionBorderControllerProfile sessionBorderControllerProfile = new SessionBorderControllerProfile("File");

    // Act
    sessionBorderControllerProfile.parseLine("backend_proxy");

    // Assert
    assertNull(sessionBorderControllerProfile.backend_proxy);
  }

  @Test
  void testParseLine2() {
    // Arrange
    SessionBorderControllerProfile sessionBorderControllerProfile = new SessionBorderControllerProfile();

    // Act
    sessionBorderControllerProfile.parseLine("keepalive_aggressive");

    // Assert
    assertFalse(sessionBorderControllerProfile.keepalive_aggressive);
  }

  @Test
  void testParseLine3() {
    // Arrange
    SessionBorderControllerProfile sessionBorderControllerProfile = new SessionBorderControllerProfile("File");

    // Act
    sessionBorderControllerProfile.parseLine("do_active_interception");

    // Assert
    assertFalse(sessionBorderControllerProfile.do_active_interception);
  }

  @Test
  void testParseLine4() {
    // Arrange
    SessionBorderControllerProfile sessionBorderControllerProfile = new SessionBorderControllerProfile("File");

    // Act
    sessionBorderControllerProfile.parseLine("keepalive_aggressive");

    // Assert
    assertFalse(sessionBorderControllerProfile.keepalive_aggressive);
  }

  @Test
  void testParseLine5() {
    // Arrange
    SessionBorderControllerProfile sessionBorderControllerProfile = new SessionBorderControllerProfile("File");

    // Act
    sessionBorderControllerProfile.parseLine("do_interception");

    // Assert
    assertFalse(sessionBorderControllerProfile.do_interception);
  }
}

