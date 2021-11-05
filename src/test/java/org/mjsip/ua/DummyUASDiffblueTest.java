package org.mjsip.ua;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.UnsupportedEncodingException;
import java.util.Vector;
import org.junit.jupiter.api.Test;
import org.mjsip.sip.header.StatusLine;
import org.mjsip.sip.message.SipMessage;
import org.mjsip.sip.provider.SipProvider;

class DummyUASDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    DummyUAS actualDummyUAS = new DummyUAS(8080, 1, "Just cause");
    SipProvider sipProvider = new SipProvider("File");
    actualDummyUAS.onReceivedMessage(sipProvider, new SipMessage("Str"));

    // Assert that nothing has changed
    assertEquals(1, actualDummyUAS.code);
    assertEquals("Just cause", actualDummyUAS.reason);
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
  void testConstructor2() {
    // Arrange and Act
    DummyUAS actualDummyUAS = new DummyUAS(0, 1, "Just cause");
    SipProvider sipProvider = new SipProvider("File");
    actualDummyUAS.onReceivedMessage(sipProvider, new SipMessage("Str"));

    // Assert that nothing has changed
    assertEquals(1, actualDummyUAS.code);
    assertEquals("Just cause", actualDummyUAS.reason);
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
  void testConstructor3() throws UnsupportedEncodingException {
    // Arrange and Act
    DummyUAS actualDummyUAS = new DummyUAS(8080, 1, "Just cause");
    SipProvider sipProvider = new SipProvider("File");
    StatusLine status_line = new StatusLine(1, "foo");

    Vector headers = new Vector(1);
    actualDummyUAS.onReceivedMessage(sipProvider,
        new SipMessage(status_line, headers, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8")));

    // Assert that nothing has changed
    assertEquals(1, actualDummyUAS.code);
    assertEquals("Just cause", actualDummyUAS.reason);
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
  void testOnReceivedMessage() {
    // Arrange
    DummyUAS dummyUAS = new DummyUAS(8080, 1, "Just cause");
    SipProvider sipProvider = new SipProvider("File");

    // Act
    dummyUAS.onReceivedMessage(sipProvider, new SipMessage("Str"));

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
    assertEquals(1, dummyUAS.code);
    assertEquals("Just cause", dummyUAS.reason);
  }

  @Test
  void testOnReceivedMessage2() throws UnsupportedEncodingException {
    // Arrange
    DummyUAS dummyUAS = new DummyUAS(8080, 1, "Just cause");
    SipProvider sipProvider = new SipProvider("File");
    StatusLine status_line = new StatusLine(1, "foo");

    Vector headers = new Vector(1);

    // Act
    dummyUAS.onReceivedMessage(sipProvider,
        new SipMessage(status_line, headers, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8")));

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
    assertEquals(1, dummyUAS.code);
    assertEquals("Just cause", dummyUAS.reason);
  }
}

