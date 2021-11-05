package org.mjsip.server.sbc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import org.junit.jupiter.api.Test;
import org.zoolu.net.SocketAddress;

class MasqueradeDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange
    SocketAddress socketAddress = new SocketAddress("42 Main St");
    SocketAddress socketAddress1 = new SocketAddress("42 Main St");

    // Act
    Masquerade actualMasquerade = new Masquerade(socketAddress, socketAddress1);

    // Assert
    SocketAddress masqSoaddr = actualMasquerade.getMasqSoaddr();
    assertSame(socketAddress1, masqSoaddr);
    assertEquals(socketAddress, masqSoaddr);
    SocketAddress peerSoaddr = actualMasquerade.getPeerSoaddr();
    assertSame(socketAddress, peerSoaddr);
    assertEquals(masqSoaddr, peerSoaddr);
  }
}

