package org.mjsip.sip.call;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.Test;
import org.mjsip.sip.message.SipMessage;
import org.mjsip.sip.provider.SipProvider;
import org.mjsip.sip.provider.SipProviderListener;

class CancelServerDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange, Act and Assert
    assertEquals(1, (new CancelServer(new SipProvider("File"))).sip_provider.getListeners().size());
  }

  @Test
  void testConstructor2() {
    // Arrange
    SipProvider sipProvider = new SipProvider("42 Main St", 8080, new String[]{"foo", "foo", "foo"},
        new int[]{1, 1, 1, 1, 1, 1, 1, 1});
    sipProvider.addPromiscuousListener(mock(SipProviderListener.class));

    // Act and Assert
    assertEquals(1, (new CancelServer(sipProvider)).sip_provider.getListeners().size());
  }

  @Test
  void testHalt() {
    // Arrange
    CancelServer cancelServer = new CancelServer(new SipProvider("File"));

    // Act
    cancelServer.halt();

    // Assert
    assertNull(cancelServer.sip_provider);
  }

  @Test
  void testHalt2() {
    // Arrange
    CancelServer cancelServer = new CancelServer(
        new SipProvider("42 Main St", 8080, new String[]{"foo", "foo", "foo"}, ""));

    // Act
    cancelServer.halt();

    // Assert
    assertNull(cancelServer.sip_provider);
  }

  @Test
  void testOnReceivedMessage() {
    // Arrange
    CancelServer cancelServer = new CancelServer(new SipProvider("File"));
    SipProvider sipProvider = new SipProvider("File");

    // Act
    cancelServer.onReceivedMessage(sipProvider, new SipMessage("Str"));

    // Assert that nothing has changed
    assertTrue(sipProvider.isRportSet());
    assertFalse(sipProvider.isForceRportSet());
    assertFalse(sipProvider.hasTelGateway());
    assertFalse(sipProvider.hasSecureTransport());
    assertFalse(sipProvider.hasOutboundProxy());

    assertEquals(5060, sipProvider.getPort());
    assertEquals(Integer.SIZE, sipProvider.getNMaxConnections());
    assertTrue(sipProvider.getListeners().isEmpty());
    assertEquals("udp", sipProvider.getDefaultTransport());
  }

  @Test
  void testOnReceivedMessage2() {
    // Arrange
    CancelServer cancelServer = new CancelServer(new SipProvider("File"));
    SipProvider sipProvider = new SipProvider("File");

    // Act
    cancelServer.onReceivedMessage(sipProvider, new SipMessage("Accept"));

    // Assert that nothing has changed
    assertTrue(sipProvider.isRportSet());
    assertFalse(sipProvider.isForceRportSet());
    assertFalse(sipProvider.hasTelGateway());
    assertFalse(sipProvider.hasSecureTransport());
    assertFalse(sipProvider.hasOutboundProxy());

    assertEquals(5060, sipProvider.getPort());
    assertEquals(Integer.SIZE, sipProvider.getNMaxConnections());
    assertTrue(sipProvider.getListeners().isEmpty());
    assertEquals("udp", sipProvider.getDefaultTransport());
  }
}

