package org.mjsip.server.sbc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class InputRegulatedUdpProviderDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    InputRegulatedUdpProvider actualInputRegulatedUdpProvider = new InputRegulatedUdpProvider(null, 10L, 10L,
        new SymmetricUdpRelay());

    // Assert
    assertEquals(10L, actualInputRegulatedUdpProvider.getAliveTime());
    assertTrue(actualInputRegulatedUdpProvider.isRunning());
    assertNull(actualInputRegulatedUdpProvider.getUdpSocket());
    assertEquals(2000, actualInputRegulatedUdpProvider.getSoTimeout());
    assertEquals(0, actualInputRegulatedUdpProvider.getMinimumReceivedDataLength());
    assertEquals(10L, actualInputRegulatedUdpProvider.getMinimumInterPacketTime());
  }

  @Test
  void testConstructor2() {
    // Arrange and Act
    InputRegulatedUdpProvider actualInputRegulatedUdpProvider = new InputRegulatedUdpProvider(null, 10L,
        new SymmetricUdpRelay());

    // Assert
    assertEquals(0L, actualInputRegulatedUdpProvider.getAliveTime());
    assertTrue(actualInputRegulatedUdpProvider.isRunning());
    assertNull(actualInputRegulatedUdpProvider.getUdpSocket());
    assertEquals(2000, actualInputRegulatedUdpProvider.getSoTimeout());
    assertEquals(0, actualInputRegulatedUdpProvider.getMinimumReceivedDataLength());
    assertEquals(10L, actualInputRegulatedUdpProvider.getMinimumInterPacketTime());
  }
}

