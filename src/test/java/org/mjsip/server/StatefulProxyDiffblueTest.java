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
import java.util.Hashtable;
import java.util.Vector;
import org.junit.jupiter.api.Test;
import org.mjsip.sip.address.NameAddress;
import org.mjsip.sip.header.MultipleHeader;
import org.mjsip.sip.header.RecordRouteHeader;
import org.mjsip.sip.header.StatusLine;
import org.mjsip.sip.header.ViaHeader;
import org.mjsip.sip.message.SipMessage;
import org.mjsip.sip.provider.MethodId;
import org.mjsip.sip.provider.SipProvider;
import org.mjsip.sip.provider.SipProviderListener;

class StatefulProxyDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    StatefulProxy actualStatefulProxy = new StatefulProxy();

    // Assert
    assertNull(actualStatefulProxy.as);
    assertNull(actualStatefulProxy.state);
    assertNull(actualStatefulProxy.sip_provider_server);
    assertNull(actualStatefulProxy.sip_provider_client);
    assertNull(actualStatefulProxy.sip_provider);
    assertNull(actualStatefulProxy.server_profile);
    assertNull(actualStatefulProxy.logger);
    assertNull(actualStatefulProxy.location_service);
    assertNull(actualStatefulProxy.call_logger);
    assertNull(actualStatefulProxy.authentication_service);
  }

  @Test
  void testConstructor2() {
    // Arrange
    SipProvider provider = new SipProvider("File");

    // Act
    StatefulProxy actualStatefulProxy = new StatefulProxy(provider, new ServerProfile("File"));

    // Assert
    assertEquals("", actualStatefulProxy.getLocalDomains());
    assertNull(actualStatefulProxy.as);
    SipProvider sipProvider = actualStatefulProxy.sip_provider_server;
    assertSame(sipProvider, actualStatefulProxy.sip_provider);
    assertSame(sipProvider, actualStatefulProxy.sip_provider_client);
    LocationService locationService = actualStatefulProxy.location_service;
    assertTrue(locationService instanceof LocationServiceImpl);
    assertNull(actualStatefulProxy.logger);
    Hashtable hashtable = ((LocationServiceImpl) locationService).users;
    assertTrue(hashtable.isEmpty());
    assertEquals("users.db", ((LocationServiceImpl) locationService).file_name);
    assertFalse(((LocationServiceImpl) locationService).changed);
    assertEquals(1, sipProvider.getListeners().size());
    StatefulProxyState statefulProxyState = actualStatefulProxy.state;
    Hashtable hashtable1 = statefulProxyState.s_clients;
    assertEquals(hashtable, hashtable1);
    Hashtable hashtable2 = statefulProxyState.c_server;
    assertEquals(hashtable1, hashtable2);
    assertEquals(hashtable2, statefulProxyState.s_response);
  }

  @Test
  void testConstructor3() {
    // Arrange
    SipProvider sipProvider = new SipProvider("File");
    sipProvider.addSelectiveListener(new MethodId("ANY"), mock(SipProviderListener.class));

    // Act
    StatefulProxy actualStatefulProxy = new StatefulProxy(sipProvider, new ServerProfile("File"));

    // Assert
    assertEquals("", actualStatefulProxy.getLocalDomains());
    assertNull(actualStatefulProxy.as);
    SipProvider sipProvider1 = actualStatefulProxy.sip_provider_server;
    assertSame(sipProvider1, actualStatefulProxy.sip_provider);
    assertSame(sipProvider1, actualStatefulProxy.sip_provider_client);
    LocationService locationService = actualStatefulProxy.location_service;
    assertTrue(locationService instanceof LocationServiceImpl);
    assertNull(actualStatefulProxy.logger);
    Hashtable hashtable = ((LocationServiceImpl) locationService).users;
    assertTrue(hashtable.isEmpty());
    assertEquals("users.db", ((LocationServiceImpl) locationService).file_name);
    assertFalse(((LocationServiceImpl) locationService).changed);
    assertEquals(1, sipProvider1.getListeners().size());
    StatefulProxyState statefulProxyState = actualStatefulProxy.state;
    Hashtable hashtable1 = statefulProxyState.s_clients;
    assertEquals(hashtable, hashtable1);
    Hashtable hashtable2 = statefulProxyState.c_server;
    assertEquals(hashtable1, hashtable2);
    assertEquals(hashtable2, statefulProxyState.s_response);
  }

  @Test
  void testProcessRequestToLocalServer() {
    // Arrange
    StatefulProxy statefulProxy = new StatefulProxy();
    SipMessage sipMessage = mock(SipMessage.class);
    when(sipMessage.isAck()).thenReturn(true);
    when(sipMessage.isRegister()).thenReturn(false);

    // Act
    statefulProxy.processRequestToLocalServer(sipMessage);

    // Assert
    verify(sipMessage).isAck();
    verify(sipMessage).isRegister();
  }

  @Test
  void testProcessResponse() {
    // Arrange
    StatefulProxy statefulProxy = new StatefulProxy();

    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addViaHeader(new ViaHeader("42"));

    // Act
    statefulProxy.processResponse(sipMessage);

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
    assertEquals(-1, sipMessage.getRemotePort());
    assertNull(sipMessage.getRemoteAddress());
    assertNull(sipMessage.getConnectionId());
  }

  @Test
  void testProcessResponse2() {
    // Arrange
    StatefulProxy statefulProxy = new StatefulProxy();

    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addViaHeader(new ViaHeader("42"));
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    statefulProxy.processResponse(sipMessage);

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
    assertEquals(-1, sipMessage.getRemotePort());
    assertNull(sipMessage.getRemoteAddress());
    assertNull(sipMessage.getConnectionId());
  }

  @Test
  void testGetLocalDomains() {
    // Arrange
    SipProvider provider = new SipProvider("File");

    // Act and Assert
    assertEquals("", (new StatefulProxy(provider, new ServerProfile("File"))).getLocalDomains());
  }

  @Test
  void testIsResponsibleFor() {
    // Arrange
    SipProvider provider = new SipProvider("File");

    // Act and Assert
    assertFalse((new StatefulProxy(provider, new ServerProfile("File"))).isResponsibleFor("Domain", 8080));
  }

  @Test
  void testIsResponsibleFor2() {
    // Arrange
    SipProvider provider = new SipProvider("42 Main St", 8080);

    // Act and Assert
    assertFalse((new StatefulProxy(provider, new ServerProfile("File"))).isResponsibleFor("Domain", 8080));
  }

  @Test
  void testIsResponsibleFor3() {
    // Arrange
    SipProvider provider = new SipProvider("File");

    // Act and Assert
    assertFalse((new StatefulProxy(provider, new ServerProfile("File"))).isResponsibleFor("Domain", 0));
  }

  @Test
  void testIsResponsibleFor4() {
    // Arrange
    SipProvider provider = new SipProvider("Domain", 8080);

    // Act and Assert
    assertTrue((new StatefulProxy(provider, new ServerProfile("File"))).isResponsibleFor("Domain", 8080));
  }

  @Test
  void testOnReceivedMessage() {
    // Arrange
    StatefulProxy statefulProxy = new StatefulProxy();
    SipProvider sipProvider = new SipProvider("File");

    // Act
    statefulProxy.onReceivedMessage(sipProvider, new SipMessage("Str"));

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
    StatefulProxy statefulProxy = new StatefulProxy();
    SipProvider sipProvider = new SipProvider("File");

    SipMessage sipMessage = new SipMessage();
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    statefulProxy.onReceivedMessage(sipProvider, sipMessage);

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
    StatefulProxy statefulProxy = new StatefulProxy();
    SipProvider sipProvider = new SipProvider("File");

    SipMessage sipMessage = new SipMessage();
    sipMessage.addRecordRouteHeader(new RecordRouteHeader(new NameAddress("Str")));

    // Act
    statefulProxy.onReceivedMessage(sipProvider, sipMessage);

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
    StatefulProxy statefulProxy = new StatefulProxy();
    SipProvider provider = new SipProvider("File");
    StatusLine status_line = new StatusLine(1, "foo");

    Vector headers = new Vector(1);

    SipMessage sipMessage = new SipMessage(status_line, headers, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));
    sipMessage.addViaHeader(new ViaHeader("42"));

    // Act
    statefulProxy.onReceivedMessage(provider, sipMessage);

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
  }
}

