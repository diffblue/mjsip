package org.zoolu.net;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.Test;

class UdpProviderDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    UdpProvider actualUdpProvider = new UdpProvider(new UdpSocket(), mock(UdpProviderListener.class));

    // Assert
    assertEquals(0L, actualUdpProvider.getAliveTime());
    assertEquals(0, actualUdpProvider.getMinimumReceivedDataLength());
    assertEquals(UdpProvider.DEFAULT_SOCKET_TIMEOUT, actualUdpProvider.getSoTimeout());
    assertNull(actualUdpProvider.getUdpSocket().socket);
  }
}

