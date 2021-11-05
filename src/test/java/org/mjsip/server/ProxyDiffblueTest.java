package org.mjsip.server;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.io.UnsupportedEncodingException;
import java.util.Vector;
import org.junit.jupiter.api.Test;
import org.mjsip.sip.address.GenericURI;
import org.mjsip.sip.address.NameAddress;
import org.mjsip.sip.header.Header;
import org.mjsip.sip.header.MultipleHeader;
import org.mjsip.sip.header.RecordRouteHeader;
import org.mjsip.sip.header.StatusLine;
import org.mjsip.sip.header.ViaHeader;
import org.mjsip.sip.message.SipMessage;
import org.mjsip.sip.provider.MethodId;
import org.mjsip.sip.provider.SipProvider;
import org.mjsip.sip.provider.SipProviderListener;

class ProxyDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    Proxy actualProxy = new Proxy();

    // Assert
    assertNull(actualProxy.as);
    assertNull(actualProxy.sip_provider);
    assertNull(actualProxy.server_profile);
    assertNull(actualProxy.logger);
    assertNull(actualProxy.location_service);
    assertNull(actualProxy.call_logger);
    assertNull(actualProxy.authentication_service);
  }

  @Test
  void testConstructor2() {
    // Arrange
    SipProvider provider = new SipProvider("File");

    // Act
    Proxy actualProxy = new Proxy(provider, new ServerProfile("File"));

    // Assert
    assertEquals("", actualProxy.getLocalDomains());
    assertNull(actualProxy.as);
    LocationService locationService = actualProxy.location_service;
    assertTrue(locationService instanceof LocationServiceImpl);
    assertNull(actualProxy.logger);
    assertTrue(((LocationServiceImpl) locationService).users.isEmpty());
    assertEquals("users.db", ((LocationServiceImpl) locationService).file_name);
    assertFalse(((LocationServiceImpl) locationService).changed);
    assertEquals(1, actualProxy.sip_provider.getListeners().size());
  }

  @Test
  void testConstructor3() {
    // Arrange
    SipProvider provider = new SipProvider("");

    // Act
    Proxy actualProxy = new Proxy(provider, new ServerProfile("File"));

    // Assert
    assertEquals("", actualProxy.getLocalDomains());
    assertNull(actualProxy.as);
    LocationService locationService = actualProxy.location_service;
    assertTrue(locationService instanceof LocationServiceImpl);
    assertNull(actualProxy.logger);
    assertTrue(((LocationServiceImpl) locationService).users.isEmpty());
    assertEquals("users.db", ((LocationServiceImpl) locationService).file_name);
    assertFalse(((LocationServiceImpl) locationService).changed);
    assertEquals(1, actualProxy.sip_provider.getListeners().size());
  }

  @Test
  void testConstructor4() {
    // Arrange
    SipProvider sipProvider = new SipProvider("File");
    sipProvider.addSelectiveListener(new MethodId("ANY"), mock(SipProviderListener.class));

    // Act
    Proxy actualProxy = new Proxy(sipProvider, new ServerProfile("File"));

    // Assert
    assertEquals("", actualProxy.getLocalDomains());
    assertNull(actualProxy.as);
    LocationService locationService = actualProxy.location_service;
    assertTrue(locationService instanceof LocationServiceImpl);
    assertNull(actualProxy.logger);
    assertTrue(((LocationServiceImpl) locationService).users.isEmpty());
    assertEquals("users.db", ((LocationServiceImpl) locationService).file_name);
    assertFalse(((LocationServiceImpl) locationService).changed);
    assertEquals(1, actualProxy.sip_provider.getListeners().size());
  }

  @Test
  void testProcessRequestToLocalServer() {
    // Arrange
    Proxy proxy = new Proxy();
    SipMessage sipMessage = mock(SipMessage.class);
    when(sipMessage.isAck()).thenReturn(true);
    when(sipMessage.isRegister()).thenReturn(false);

    // Act
    proxy.processRequestToLocalServer(sipMessage);

    // Assert
    verify(sipMessage).isAck();
    verify(sipMessage).isRegister();
  }

  @Test
  void testProcessResponse() {
    // Arrange
    Proxy proxy = new Proxy();

    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addViaHeader(new ViaHeader("42"));

    // Act
    proxy.processResponse(sipMessage);

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
    assertEquals(-1, sipMessage.getRemotePort());
    assertNull(sipMessage.getRemoteAddress());
    assertNull(sipMessage.getConnectionId());
  }

  @Test
  void testProcessResponse2() {
    // Arrange
    Proxy proxy = new Proxy();

    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addViaHeader(new ViaHeader("42"));
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    proxy.processResponse(sipMessage);

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
    assertEquals(-1, sipMessage.getRemotePort());
    assertNull(sipMessage.getRemoteAddress());
    assertNull(sipMessage.getConnectionId());
  }

  @Test
  void testUpdateProxyingResponse() {
    // Arrange
    Proxy proxy = new Proxy();

    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addViaHeader(new ViaHeader("42"));

    // Act
    SipMessage actualUpdateProxyingResponseResult = proxy.updateProxyingResponse(sipMessage);

    // Assert
    assertSame(sipMessage, actualUpdateProxyingResponseResult);
    assertNull(actualUpdateProxyingResponseResult.getAcceptEncodingHeader());
    assertEquals(-1, actualUpdateProxyingResponseResult.getRemotePort());
    assertNull(actualUpdateProxyingResponseResult.getRemoteAddress());
    assertNull(actualUpdateProxyingResponseResult.getConnectionId());
  }

  @Test
  void testUpdateProxyingResponse2() {
    // Arrange
    Proxy proxy = new Proxy();

    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addViaHeader(new ViaHeader("42"));
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    SipMessage actualUpdateProxyingResponseResult = proxy.updateProxyingResponse(sipMessage);

    // Assert
    assertSame(sipMessage, actualUpdateProxyingResponseResult);
    assertNull(actualUpdateProxyingResponseResult.getAcceptEncodingHeader());
    assertEquals(-1, actualUpdateProxyingResponseResult.getRemotePort());
    assertNull(actualUpdateProxyingResponseResult.getRemoteAddress());
    assertNull(actualUpdateProxyingResponseResult.getConnectionId());
  }

  @Test
  void testUpdateProxyingResponse3() {
    // Arrange
    Proxy proxy = new Proxy();

    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addViaHeader(new ViaHeader("42"));
    sipMessage.addHeader(new Header("Hname", "42"), true);

    // Act
    SipMessage actualUpdateProxyingResponseResult = proxy.updateProxyingResponse(sipMessage);

    // Assert
    assertSame(sipMessage, actualUpdateProxyingResponseResult);
    assertNull(actualUpdateProxyingResponseResult.getAcceptEncodingHeader());
    assertEquals(-1, actualUpdateProxyingResponseResult.getRemotePort());
    assertNull(actualUpdateProxyingResponseResult.getRemoteAddress());
    assertNull(actualUpdateProxyingResponseResult.getConnectionId());
  }

  @Test
  void testGetAuthDomainBasedProxyingTarget() {
    // Arrange
    SipProvider provider = new SipProvider("File");
    Proxy proxy = new Proxy(provider, new ServerProfile("File"));

    // Act and Assert
    assertNull(proxy.getAuthDomainBasedProxyingTarget(new GenericURI("Uri")));
  }

  @Test
  void testGetDomainBasedProxyingTarget() {
    // Arrange
    SipProvider provider = new SipProvider("File");
    Proxy proxy = new Proxy(provider, new ServerProfile("File"));

    // Act and Assert
    assertNull(proxy.getDomainBasedProxyingTarget(new GenericURI("Uri")));
  }

  @Test
  void testGetAuthPrefixBasedProxyingTarget() {
    // Arrange
    Proxy proxy = new Proxy();
    GenericURI genericURI = mock(GenericURI.class);
    when(genericURI.isSipURI()).thenReturn(false);

    // Act and Assert
    assertNull(proxy.getAuthPrefixBasedProxyingTarget(genericURI));
    verify(genericURI).isSipURI();
  }

  @Test
  void testGetPrefixBasedProxyingTarget() {
    // Arrange
    Proxy proxy = new Proxy();
    GenericURI genericURI = mock(GenericURI.class);
    when(genericURI.isSipURI()).thenReturn(false);

    // Act and Assert
    assertNull(proxy.getPrefixBasedProxyingTarget(genericURI));
    verify(genericURI).isSipURI();
  }

  @Test
  void testIsPhoneNumber() {
    // Arrange, Act and Assert
    assertFalse((new Proxy()).isPhoneNumber("Str"));
    assertFalse((new Proxy()).isPhoneNumber(null));
    assertFalse((new Proxy()).isPhoneNumber("--prompt"));
    assertTrue((new Proxy()).isPhoneNumber("42"));
    assertFalse((new Proxy()).isPhoneNumber(""));
  }

  @Test
  void testGetLocalDomains() {
    // Arrange
    SipProvider provider = new SipProvider("File");

    // Act and Assert
    assertEquals("", (new Proxy(provider, new ServerProfile("File"))).getLocalDomains());
  }

  @Test
  void testIsResponsibleFor() {
    // Arrange
    SipProvider provider = new SipProvider("File");

    // Act and Assert
    assertFalse((new Proxy(provider, new ServerProfile("File"))).isResponsibleFor("Domain", 8080));
  }

  @Test
  void testIsResponsibleFor2() {
    // Arrange
    SipProvider provider = new SipProvider("42 Main St", 8080);

    // Act and Assert
    assertFalse((new Proxy(provider, new ServerProfile("File"))).isResponsibleFor("Domain", 8080));
  }

  @Test
  void testIsResponsibleFor3() {
    // Arrange
    SipProvider provider = new SipProvider("File");

    // Act and Assert
    assertFalse((new Proxy(provider, new ServerProfile("File"))).isResponsibleFor("Domain", 0));
  }

  @Test
  void testIsResponsibleFor4() {
    // Arrange
    SipProvider provider = new SipProvider("Domain", 8080);

    // Act and Assert
    assertTrue((new Proxy(provider, new ServerProfile("File"))).isResponsibleFor("Domain", 8080));
  }

  @Test
  void testIsResponsibleFor5() {
    // Arrange
    Proxy proxy = new Proxy();
    GenericURI genericURI = mock(GenericURI.class);
    when(genericURI.isSipURI()).thenReturn(false);

    // Act and Assert
    assertFalse(proxy.isResponsibleFor(genericURI));
    verify(genericURI).isSipURI();
  }

  @Test
  void testOnReceivedMessage() {
    // Arrange
    Proxy proxy = new Proxy();
    SipProvider sipProvider = new SipProvider("File");

    // Act
    proxy.onReceivedMessage(sipProvider, new SipMessage("Str"));

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
    Proxy proxy = new Proxy();
    SipProvider sipProvider = new SipProvider("File");

    SipMessage sipMessage = new SipMessage();
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    proxy.onReceivedMessage(sipProvider, sipMessage);

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
  void testOnReceivedMessage3() {
    // Arrange
    Proxy proxy = new Proxy();
    SipProvider sipProvider = new SipProvider("File");

    SipMessage sipMessage = new SipMessage();
    sipMessage.addRecordRouteHeader(new RecordRouteHeader(new NameAddress("Str")));

    // Act
    proxy.onReceivedMessage(sipProvider, sipMessage);

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
  void testOnReceivedMessage4() throws UnsupportedEncodingException {
    // Arrange
    Proxy proxy = new Proxy();
    SipProvider provider = new SipProvider("File");
    StatusLine status_line = new StatusLine(1, "foo");

    Vector headers = new Vector(1);

    SipMessage sipMessage = new SipMessage(status_line, headers, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));
    sipMessage.addViaHeader(new ViaHeader("42"));

    // Act
    proxy.onReceivedMessage(provider, sipMessage);

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
  }
}

