package org.mjsip.server.sbc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import java.util.Vector;
import org.junit.jupiter.api.Test;

class GwProfileDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    GwProfile actualGwProfile = new GwProfile();

    // Assert
    assertEquals(0, actualGwProfile.sink_port);
    assertEquals("127.0.0.1", actualGwProfile.sink_addr);
    assertEquals(60000L, actualGwProfile.relay_timeout);
    Vector vector = actualGwProfile.media_ports;
    assertEquals(200, vector.size());
    assertEquals(37000, vector.get(0));
    assertEquals(37002, vector.get(1));
    assertEquals(37004, vector.get(2));
    assertEquals(37394, vector.get(197));
    assertEquals(37396, vector.get(198));
    assertEquals(37398, vector.get(199));
    assertEquals("0.0.0.0", actualGwProfile.media_addr);
    assertEquals(0L, actualGwProfile.keepalive_time);
    assertFalse(actualGwProfile.keepalive_aggressive);
    assertEquals(0, actualGwProfile.handover_time);
    assertFalse(actualGwProfile.do_interception);
    assertFalse(actualGwProfile.do_active_interception);
    assertEquals(3600000L, actualGwProfile.binding_timeout);
  }

  @Test
  void testConstructor2() {
    // Arrange and Act
    GwProfile actualGwProfile = new GwProfile("File");

    // Assert
    assertEquals(0, actualGwProfile.sink_port);
    assertEquals("127.0.0.1", actualGwProfile.sink_addr);
    assertEquals(60000L, actualGwProfile.relay_timeout);
    Vector vector = actualGwProfile.media_ports;
    assertEquals(200, vector.size());
    assertEquals(37000, vector.get(0));
    assertEquals(37002, vector.get(1));
    assertEquals(37004, vector.get(2));
    assertEquals(37394, vector.get(197));
    assertEquals(37396, vector.get(198));
    assertEquals(37398, vector.get(199));
    assertEquals("0.0.0.0", actualGwProfile.media_addr);
    assertEquals(0L, actualGwProfile.keepalive_time);
    assertFalse(actualGwProfile.keepalive_aggressive);
    assertEquals(0, actualGwProfile.handover_time);
    assertFalse(actualGwProfile.do_interception);
    assertFalse(actualGwProfile.do_active_interception);
    assertEquals(3600000L, actualGwProfile.binding_timeout);
  }

  @Test
  void testParseLine() {
    // Arrange
    GwProfile gwProfile = new GwProfile("File");

    // Act
    gwProfile.parseLine("do_active_interception");

    // Assert
    assertFalse(gwProfile.do_active_interception);
  }

  @Test
  void testParseLine2() {
    // Arrange
    GwProfile gwProfile = new GwProfile();

    // Act
    gwProfile.parseLine("keepalive_aggressive");

    // Assert
    assertFalse(gwProfile.keepalive_aggressive);
  }

  @Test
  void testParseLine3() {
    // Arrange
    GwProfile gwProfile = new GwProfile("File");

    // Act
    gwProfile.parseLine("do_interception");

    // Assert
    assertFalse(gwProfile.do_interception);
  }

  @Test
  void testParseLine4() {
    // Arrange
    GwProfile gwProfile = new GwProfile();

    // Act
    gwProfile.parseLine("sink_addr");

    // Assert
    assertEquals("", gwProfile.sink_addr);
  }
}

