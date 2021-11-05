package org.mjsip.server;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.mjsip.sip.address.GenericURI;
import org.zoolu.net.SocketAddress;

class DomainProxyingRuleDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    DomainProxyingRule actualDomainProxyingRule = new DomainProxyingRule("Domain", new SocketAddress("42 Main St"));

    // Assert
    assertEquals("{domain=Domain,nexthop=42 Main St:-1}", actualDomainProxyingRule.toString());
    SocketAddress socketAddress = actualDomainProxyingRule.nexthop;
    assertEquals("42 Main St:-1", socketAddress.toString());
    assertEquals(-1, socketAddress.getPort());
  }

  @Test
  void testGetNexthop() {
    // Arrange
    DomainProxyingRule domainProxyingRule = new DomainProxyingRule("Domain", new SocketAddress("42 Main St"));
    GenericURI genericURI = mock(GenericURI.class);
    when(genericURI.isSipURI()).thenReturn(false);

    // Act and Assert
    assertNull(domainProxyingRule.getNexthop(genericURI));
    verify(genericURI).isSipURI();
  }
}

