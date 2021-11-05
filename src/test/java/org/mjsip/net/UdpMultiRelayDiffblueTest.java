package org.mjsip.net;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import java.util.Vector;
import org.junit.jupiter.api.Test;

class UdpMultiRelayDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    UdpMultiRelay actualUdpMultiRelay = new UdpMultiRelay(null, new Vector(1), true, true,
        mock(UdpMultiRelayListener.class));

    // Assert
    assertFalse(actualUdpMultiRelay.stop);
    assertTrue(actualUdpMultiRelay.getDestSockets().isEmpty());
    assertNull(actualUdpMultiRelay.src_soaddr);
    assertTrue(actualUdpMultiRelay.isOneToOne());
    assertTrue(actualUdpMultiRelay.isFiltered());
    assertEquals(3000, actualUdpMultiRelay.getSoTimeout());
    assertNull(actualUdpMultiRelay.getSocket());
  }

  @Test
  void testRun() {
    // Arrange
    UdpMultiRelay udpMultiRelay = new UdpMultiRelay(null, new Vector(1), true, true, null);

    // Act
    udpMultiRelay.run();

    // Assert that nothing has changed
    assertFalse(udpMultiRelay.stop);
    assertTrue(udpMultiRelay.getDestSockets().isEmpty());
    assertTrue(udpMultiRelay.isOneToOne());
    assertTrue(udpMultiRelay.isFiltered());
    assertEquals(3000, udpMultiRelay.getSoTimeout());
  }
}

