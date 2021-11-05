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

class OptionsServerDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    OptionsServer actualOptionsServer = new OptionsServer(new SipProvider("File"), "Allow", "Accept");

    // Assert
    assertEquals("Accept", actualOptionsServer.accept);
    assertNull(actualOptionsServer.supported_option_tags);
    assertNull(actualOptionsServer.logger);
    assertNull(actualOptionsServer.accept_encoding);
    assertEquals("Allow", actualOptionsServer.allow);
    assertNull(actualOptionsServer.accept_language);
    assertEquals(1, actualOptionsServer.sip_provider.getListeners().size());
  }

  @Test
  void testConstructor2() {
    // Arrange
    SipProvider sipProvider = new SipProvider("42 Main St", 8080, new String[]{"foo", "foo", "foo"}, "");
    sipProvider.addPromiscuousListener(mock(SipProviderListener.class));

    // Act
    OptionsServer actualOptionsServer = new OptionsServer(sipProvider, "Allow", "Accept");

    // Assert
    assertEquals("Accept", actualOptionsServer.accept);
    assertNull(actualOptionsServer.supported_option_tags);
    assertNull(actualOptionsServer.logger);
    assertNull(actualOptionsServer.accept_encoding);
    assertEquals("Allow", actualOptionsServer.allow);
    assertNull(actualOptionsServer.accept_language);
    assertEquals(1, actualOptionsServer.sip_provider.getListeners().size());
  }

  @Test
  void testConstructor3() {
    // Arrange and Act
    OptionsServer actualOptionsServer = new OptionsServer(new SipProvider("File"), "Allow", "Accept", "UTF-8", "en",
        new String[]{"Supported option tags"});

    // Assert
    assertEquals("Accept", actualOptionsServer.accept);
    assertEquals(1, actualOptionsServer.supported_option_tags.length);
    assertNull(actualOptionsServer.logger);
    assertEquals("UTF-8", actualOptionsServer.accept_encoding);
    assertEquals("Allow", actualOptionsServer.allow);
    assertEquals("en", actualOptionsServer.accept_language);
    assertEquals(1, actualOptionsServer.sip_provider.getListeners().size());
  }

  @Test
  void testConstructor4() {
    // Arrange
    SipProvider sipProvider = new SipProvider("42 Main St", 8080, new String[]{"foo", "foo", "foo"}, "");
    sipProvider.addPromiscuousListener(mock(SipProviderListener.class));

    // Act
    OptionsServer actualOptionsServer = new OptionsServer(sipProvider, "Allow", "Accept", "UTF-8", "en",
        new String[]{"Supported option tags"});

    // Assert
    assertEquals("Accept", actualOptionsServer.accept);
    assertEquals(1, actualOptionsServer.supported_option_tags.length);
    assertNull(actualOptionsServer.logger);
    assertEquals("UTF-8", actualOptionsServer.accept_encoding);
    assertEquals("Allow", actualOptionsServer.allow);
    assertEquals("en", actualOptionsServer.accept_language);
    assertEquals(1, actualOptionsServer.sip_provider.getListeners().size());
  }

  @Test
  void testHalt() {
    // Arrange
    OptionsServer optionsServer = new OptionsServer(new SipProvider("File"), "Allow", "Accept");

    // Act
    optionsServer.halt();

    // Assert
    assertNull(optionsServer.sip_provider);
    assertNull(optionsServer.logger);
  }

  @Test
  void testOnReceivedMessage() {
    // Arrange
    OptionsServer optionsServer = new OptionsServer(new SipProvider("File"), "Allow", "Accept");
    SipProvider sipProvider = new SipProvider("File");

    // Act
    optionsServer.onReceivedMessage(sipProvider, new SipMessage("Str"));

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
    assertEquals("Accept", optionsServer.accept);
    assertEquals("Allow", optionsServer.allow);
  }

  @Test
  void testOnReceivedMessage2() {
    // Arrange
    OptionsServer optionsServer = new OptionsServer(new SipProvider("File"), "Allow", "Accept");
    SipProvider sipProvider = new SipProvider("File");

    // Act
    optionsServer.onReceivedMessage(sipProvider, new SipMessage("Accept"));

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
    assertEquals("Accept", optionsServer.accept);
    assertEquals("Allow", optionsServer.allow);
  }

  @Test
  void testLog() {
    // Arrange
    OptionsServer optionsServer = new OptionsServer(new SipProvider("File"), "Allow", "Accept");

    // Act
    optionsServer.log("Str");

    // Assert that nothing has changed
    assertEquals("Accept", optionsServer.accept);
    assertEquals("Allow", optionsServer.allow);
  }
}

