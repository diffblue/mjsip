package org.mjsip.server;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.io.UnsupportedEncodingException;
import java.util.Vector;
import org.junit.jupiter.api.Test;
import org.mjsip.sip.address.GenericURI;
import org.mjsip.sip.header.StatusLine;
import org.mjsip.sip.message.SipMessage;
import org.mjsip.sip.provider.MethodId;
import org.mjsip.sip.provider.SipProvider;
import org.mjsip.sip.provider.SipProviderListener;

class RegistrarDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    Registrar actualRegistrar = new Registrar();

    // Assert
    assertNull(actualRegistrar.as);
    assertNull(actualRegistrar.sip_provider);
    assertNull(actualRegistrar.server_profile);
    assertNull(actualRegistrar.logger);
    assertNull(actualRegistrar.location_service);
    assertNull(actualRegistrar.authentication_service);
  }

  @Test
  void testConstructor2() {
    // Arrange
    SipProvider provider = new SipProvider("File");

    // Act
    Registrar actualRegistrar = new Registrar(provider, new ServerProfile("File"));

    // Assert
    assertEquals("", actualRegistrar.getLocalDomains());
    assertNull(actualRegistrar.as);
    LocationService locationService = actualRegistrar.location_service;
    assertTrue(locationService instanceof LocationServiceImpl);
    assertNull(actualRegistrar.logger);
    assertTrue(((LocationServiceImpl) locationService).users.isEmpty());
    assertEquals("users.db", ((LocationServiceImpl) locationService).file_name);
    assertFalse(((LocationServiceImpl) locationService).changed);
    assertEquals(1, actualRegistrar.sip_provider.getListeners().size());
  }

  @Test
  void testConstructor3() {
    // Arrange
    SipProvider sipProvider = new SipProvider("File");
    sipProvider.addSelectiveListener(new MethodId("ANY"), mock(SipProviderListener.class));

    // Act
    Registrar actualRegistrar = new Registrar(sipProvider, new ServerProfile("File"));

    // Assert
    assertEquals("", actualRegistrar.getLocalDomains());
    assertNull(actualRegistrar.as);
    LocationService locationService = actualRegistrar.location_service;
    assertTrue(locationService instanceof LocationServiceImpl);
    assertNull(actualRegistrar.logger);
    assertTrue(((LocationServiceImpl) locationService).users.isEmpty());
    assertEquals("users.db", ((LocationServiceImpl) locationService).file_name);
    assertFalse(((LocationServiceImpl) locationService).changed);
    assertEquals(1, actualRegistrar.sip_provider.getListeners().size());
  }

  @Test
  void testProcessRequestToLocalUser() {
    // Arrange
    Registrar registrar = new Registrar();
    SipMessage sipMessage = mock(SipMessage.class);
    when(sipMessage.isAck()).thenReturn(true);

    // Act
    registrar.processRequestToLocalUser(sipMessage);

    // Assert
    verify(sipMessage).isAck();
  }

  @Test
  void testProcessRequestToRemoteUA() {
    // Arrange
    Registrar registrar = new Registrar();
    SipMessage sipMessage = mock(SipMessage.class);
    when(sipMessage.isAck()).thenReturn(true);

    // Act
    registrar.processRequestToRemoteUA(sipMessage);

    // Assert
    verify(sipMessage).isAck();
  }

  @Test
  void testProcessResponse() {
    // Arrange
    Registrar registrar = new Registrar();
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    registrar.processResponse(sipMessage);

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
  }

  @Test
  void testGetTargets() {
    // Arrange
    Registrar registrar = new Registrar();

    // Act and Assert
    assertTrue(registrar.getTargets(new SipMessage("Str")).isEmpty());
  }

  @Test
  void testGetLocalDomains() {
    // Arrange
    SipProvider provider = new SipProvider("File");

    // Act and Assert
    assertEquals("", (new Registrar(provider, new ServerProfile("File"))).getLocalDomains());
  }

  @Test
  void testIsResponsibleFor() {
    // Arrange
    SipProvider provider = new SipProvider("File");

    // Act and Assert
    assertFalse((new Registrar(provider, new ServerProfile("File"))).isResponsibleFor("Domain", 8080));
  }

  @Test
  void testIsResponsibleFor2() {
    // Arrange
    SipProvider provider = new SipProvider("42 Main St", 8080);

    // Act and Assert
    assertFalse((new Registrar(provider, new ServerProfile("File"))).isResponsibleFor("Domain", 8080));
  }

  @Test
  void testIsResponsibleFor3() {
    // Arrange
    SipProvider provider = new SipProvider("File");

    // Act and Assert
    assertFalse((new Registrar(provider, new ServerProfile("File"))).isResponsibleFor("Domain", 0));
  }

  @Test
  void testIsResponsibleFor4() {
    // Arrange
    SipProvider provider = new SipProvider("Domain", 8080);

    // Act and Assert
    assertTrue((new Registrar(provider, new ServerProfile("File"))).isResponsibleFor("Domain", 8080));
  }

  @Test
  void testIsResponsibleFor5() {
    // Arrange
    Registrar registrar = new Registrar();
    GenericURI genericURI = mock(GenericURI.class);
    when(genericURI.isSipURI()).thenReturn(false);

    // Act and Assert
    assertFalse(registrar.isResponsibleFor(genericURI));
    verify(genericURI).isSipURI();
  }

  @Test
  void testOnReceivedMessage() {
    // Arrange
    Registrar registrar = new Registrar();
    SipProvider sipProvider = new SipProvider("File");

    // Act
    registrar.onReceivedMessage(sipProvider, new SipMessage("Str"));

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
    Registrar registrar = new Registrar();
    SipProvider sipProvider = new SipProvider("File");
    StatusLine status_line = new StatusLine(1, "foo");

    Vector headers = new Vector(1);

    // Act
    registrar.onReceivedMessage(sipProvider,
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
}

