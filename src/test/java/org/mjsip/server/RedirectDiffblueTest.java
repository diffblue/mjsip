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

class RedirectDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange
    SipProvider provider = new SipProvider("File");

    // Act
    Redirect actualRedirect = new Redirect(provider, new ServerProfile("File"));

    // Assert
    assertEquals("", actualRedirect.getLocalDomains());
    assertNull(actualRedirect.as);
    LocationService locationService = actualRedirect.location_service;
    assertTrue(locationService instanceof LocationServiceImpl);
    assertNull(actualRedirect.logger);
    assertTrue(((LocationServiceImpl) locationService).users.isEmpty());
    assertEquals("users.db", ((LocationServiceImpl) locationService).file_name);
    assertFalse(((LocationServiceImpl) locationService).changed);
    assertEquals(1, actualRedirect.sip_provider.getListeners().size());
  }

  @Test
  void testConstructor2() {
    // Arrange
    SipProvider sipProvider = new SipProvider("File");
    sipProvider.addSelectiveListener(new MethodId("ANY"), mock(SipProviderListener.class));

    // Act
    Redirect actualRedirect = new Redirect(sipProvider, new ServerProfile("File"));

    // Assert
    assertEquals("", actualRedirect.getLocalDomains());
    assertNull(actualRedirect.as);
    LocationService locationService = actualRedirect.location_service;
    assertTrue(locationService instanceof LocationServiceImpl);
    assertNull(actualRedirect.logger);
    assertTrue(((LocationServiceImpl) locationService).users.isEmpty());
    assertEquals("users.db", ((LocationServiceImpl) locationService).file_name);
    assertFalse(((LocationServiceImpl) locationService).changed);
    assertEquals(1, actualRedirect.sip_provider.getListeners().size());
  }

  @Test
  void testProcessRequestToRemoteUA() {
    // Arrange
    SipProvider provider = new SipProvider("File");
    Redirect redirect = new Redirect(provider, new ServerProfile("File"));
    SipMessage sipMessage = mock(SipMessage.class);
    when(sipMessage.isAck()).thenReturn(true);

    // Act
    redirect.processRequestToRemoteUA(sipMessage);

    // Assert
    verify(sipMessage).isAck();
  }

  @Test
  void testProcessResponse() {
    // Arrange
    SipProvider provider = new SipProvider("File");
    Redirect redirect = new Redirect(provider, new ServerProfile("File"));
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    redirect.processResponse(sipMessage);

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
    assertTrue(redirect.location_service instanceof LocationServiceImpl);
    assertFalse(redirect.server_profile.do_authentication);
  }

  @Test
  void testGetLocalDomains() {
    // Arrange
    SipProvider provider = new SipProvider("File");

    // Act and Assert
    assertEquals("", (new Redirect(provider, new ServerProfile("File"))).getLocalDomains());
  }

  @Test
  void testIsResponsibleFor() {
    // Arrange
    SipProvider provider = new SipProvider("File");

    // Act and Assert
    assertFalse((new Redirect(provider, new ServerProfile("File"))).isResponsibleFor("Domain", 8080));
  }

  @Test
  void testIsResponsibleFor2() {
    // Arrange
    SipProvider provider = new SipProvider("42 Main St", 8080);

    // Act and Assert
    assertFalse((new Redirect(provider, new ServerProfile("File"))).isResponsibleFor("Domain", 8080));
  }

  @Test
  void testIsResponsibleFor3() {
    // Arrange
    SipProvider provider = new SipProvider("File");

    // Act and Assert
    assertFalse((new Redirect(provider, new ServerProfile("File"))).isResponsibleFor("Domain", 0));
  }

  @Test
  void testIsResponsibleFor4() {
    // Arrange
    SipProvider provider = new SipProvider("Domain", 8080);

    // Act and Assert
    assertTrue((new Redirect(provider, new ServerProfile("File"))).isResponsibleFor("Domain", 8080));
  }

  @Test
  void testIsResponsibleFor5() {
    // Arrange
    SipProvider provider = new SipProvider("File");
    Redirect redirect = new Redirect(provider, new ServerProfile("File"));
    GenericURI genericURI = mock(GenericURI.class);
    when(genericURI.isSipURI()).thenReturn(false);

    // Act and Assert
    assertFalse(redirect.isResponsibleFor(genericURI));
    verify(genericURI).isSipURI();
  }

  @Test
  void testOnReceivedMessage() {
    // Arrange
    SipProvider provider = new SipProvider("File");
    Redirect redirect = new Redirect(provider, new ServerProfile("File"));
    SipProvider sipProvider = new SipProvider("File");

    // Act
    redirect.onReceivedMessage(sipProvider, new SipMessage("Str"));

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
    assertTrue(redirect.location_service instanceof LocationServiceImpl);
    assertFalse(redirect.server_profile.do_authentication);
  }

  @Test
  void testOnReceivedMessage2() throws UnsupportedEncodingException {
    // Arrange
    SipProvider provider = new SipProvider("File");
    Redirect redirect = new Redirect(provider, new ServerProfile("File"));
    SipProvider sipProvider = new SipProvider("File");
    StatusLine status_line = new StatusLine(1, "foo");

    Vector headers = new Vector(1);

    // Act
    redirect.onReceivedMessage(sipProvider,
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
    assertTrue(redirect.location_service instanceof LocationServiceImpl);
    assertFalse(redirect.server_profile.do_authentication);
  }
}

