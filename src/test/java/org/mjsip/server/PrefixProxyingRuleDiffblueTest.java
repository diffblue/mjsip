package org.mjsip.server;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.mjsip.sip.address.GenericURI;
import org.zoolu.net.SocketAddress;

class PrefixProxyingRuleDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    PrefixProxyingRule actualPrefixProxyingRule = new PrefixProxyingRule("Prefix", new SocketAddress("42 Main St"));

    // Assert
    assertEquals("{prefix=Prefix,nexthop=42 Main St:-1}", actualPrefixProxyingRule.toString());
    SocketAddress socketAddress = actualPrefixProxyingRule.nexthop;
    assertEquals("42 Main St:-1", socketAddress.toString());
    assertEquals(-1, socketAddress.getPort());
  }

  @Test
  void testGetNexthop() {
    // Arrange
    PrefixProxyingRule prefixProxyingRule = new PrefixProxyingRule("Prefix", new SocketAddress("42 Main St"));
    GenericURI genericURI = mock(GenericURI.class);
    when(genericURI.isSipURI()).thenReturn(false);

    // Act and Assert
    assertNull(prefixProxyingRule.getNexthop(genericURI));
    verify(genericURI).isSipURI();
  }
}

