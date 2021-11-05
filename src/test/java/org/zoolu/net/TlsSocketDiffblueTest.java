package org.zoolu.net;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

class TlsSocketDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    TlsSocket actualTlsSocket = new TlsSocket();

    // Assert
    assertFalse(actualTlsSocket.isConnected());
    assertNull(actualTlsSocket.socket);
  }

  @Test
  void testConstructor2() {
    // Arrange and Act
    TlsSocket actualTlsSocket = new TlsSocket(null);

    // Assert
    assertFalse(actualTlsSocket.isConnected());
    assertNull(actualTlsSocket.socket);
    assertNull(null);
  }
}

