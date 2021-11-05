package org.mjsip.sip.call;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import java.io.UnsupportedEncodingException;
import java.util.Vector;
import org.junit.jupiter.api.Test;
import org.mjsip.sip.header.StatusLine;
import org.mjsip.sip.message.SipMessage;
import org.mjsip.sip.provider.MethodId;
import org.mjsip.sip.provider.SipProvider;
import org.mjsip.sip.provider.SipProviderListener;

class NotImplementedServerDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    NotImplementedServer actualNotImplementedServer = new NotImplementedServer(new SipProvider("File"));

    // Assert
    assertNull(actualNotImplementedServer.implemented_methods);
    assertNull(actualNotImplementedServer.logger);
    assertEquals(1, actualNotImplementedServer.sip_provider.getListeners().size());
  }

  @Test
  void testConstructor2() {
    // Arrange and Act
    NotImplementedServer actualNotImplementedServer = new NotImplementedServer(new SipProvider(""));

    // Assert
    assertNull(actualNotImplementedServer.implemented_methods);
    assertNull(actualNotImplementedServer.logger);
    assertEquals(1, actualNotImplementedServer.sip_provider.getListeners().size());
  }

  @Test
  void testConstructor3() {
    // Arrange
    SipProvider sipProvider = new SipProvider("File");
    sipProvider.addSelectiveListener(new MethodId("ANY"), mock(SipProviderListener.class));

    // Act
    NotImplementedServer actualNotImplementedServer = new NotImplementedServer(sipProvider);

    // Assert
    assertNull(actualNotImplementedServer.implemented_methods);
    assertNull(actualNotImplementedServer.logger);
    assertEquals(1, actualNotImplementedServer.sip_provider.getListeners().size());
  }

  @Test
  void testConstructor4() {
    // Arrange
    SipProvider sipProvider = new SipProvider("_events.log");
    sipProvider.addSelectiveListener(new MethodId("ANY"), mock(SipProviderListener.class));

    // Act
    NotImplementedServer actualNotImplementedServer = new NotImplementedServer(sipProvider);

    // Assert
    assertNull(actualNotImplementedServer.implemented_methods);
    assertNull(actualNotImplementedServer.logger);
    assertEquals(1, actualNotImplementedServer.sip_provider.getListeners().size());
  }

  @Test
  void testConstructor5() {
    // Arrange and Act
    NotImplementedServer actualNotImplementedServer = new NotImplementedServer(new String[]{"Implemented methods"},
        new SipProvider("File"));

    // Assert
    assertEquals(1, actualNotImplementedServer.implemented_methods.length);
    assertNull(actualNotImplementedServer.logger);
    assertEquals(1, actualNotImplementedServer.sip_provider.getListeners().size());
  }

  @Test
  void testConstructor6() {
    // Arrange
    SipProvider sipProvider = new SipProvider("File");
    sipProvider.addSelectiveListener(new MethodId("ANY"), mock(SipProviderListener.class));

    // Act
    NotImplementedServer actualNotImplementedServer = new NotImplementedServer(new String[]{"Implemented methods"},
        sipProvider);

    // Assert
    assertEquals(1, actualNotImplementedServer.implemented_methods.length);
    assertNull(actualNotImplementedServer.logger);
    assertEquals(1, actualNotImplementedServer.sip_provider.getListeners().size());
  }

  @Test
  void testConstructor7() {
    // Arrange
    SipProvider sipProvider = new SipProvider("_events.log");
    sipProvider.addSelectiveListener(new MethodId("ANY"), mock(SipProviderListener.class));

    // Act
    NotImplementedServer actualNotImplementedServer = new NotImplementedServer(new String[]{"Implemented methods"},
        sipProvider);

    // Assert
    assertEquals(1, actualNotImplementedServer.implemented_methods.length);
    assertNull(actualNotImplementedServer.logger);
    assertEquals(1, actualNotImplementedServer.sip_provider.getListeners().size());
  }

  @Test
  void testHalt() {
    // Arrange
    NotImplementedServer notImplementedServer = new NotImplementedServer(new SipProvider("File"));

    // Act
    notImplementedServer.halt();

    // Assert
    assertNull(notImplementedServer.sip_provider);
    assertNull(notImplementedServer.logger);
  }

  @Test
  void testOnReceivedMessage() {
    // Arrange
    NotImplementedServer notImplementedServer = new NotImplementedServer(new SipProvider("File"));
    SipProvider sipProvider = new SipProvider("File");

    // Act
    notImplementedServer.onReceivedMessage(sipProvider, new SipMessage("Str"));

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
  void testOnReceivedMessage2() throws UnsupportedEncodingException {
    // Arrange
    NotImplementedServer notImplementedServer = new NotImplementedServer(new SipProvider("File"));
    SipProvider sipProvider = new SipProvider("File");
    StatusLine status_line = new StatusLine(1, "foo");

    Vector headers = new Vector(1);

    // Act
    notImplementedServer.onReceivedMessage(sipProvider,
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
  }

  @Test
  void testLog() {
    // Arrange
    NotImplementedServer notImplementedServer = new NotImplementedServer(new SipProvider("File"));

    // Act
    notImplementedServer.log("Str");

    // Assert that nothing has changed
    assertNull(notImplementedServer.implemented_methods);
    assertNull(notImplementedServer.logger);
  }
}

