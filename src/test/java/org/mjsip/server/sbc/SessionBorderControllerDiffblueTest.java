package org.mjsip.server.sbc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.mjsip.server.ServerProfile;
import org.mjsip.sip.address.GenericURI;
import org.mjsip.sip.address.NameAddress;
import org.mjsip.sip.header.MultipleHeader;
import org.mjsip.sip.header.RecordRouteHeader;
import org.mjsip.sip.header.ViaHeader;
import org.mjsip.sip.message.SipMessage;
import org.mjsip.sip.provider.MethodId;
import org.mjsip.sip.provider.SipProvider;
import org.mjsip.sip.provider.SipProviderListener;

class SessionBorderControllerDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange
    ExtendedSipProvider extendedSipProvider = new ExtendedSipProvider("File", 10L, 10L);

    ServerProfile server_profile = new ServerProfile("File");

    // Act
    SessionBorderController actualSessionBorderController = new SessionBorderController(extendedSipProvider,
        server_profile, new SessionBorderControllerProfile());

    // Assert
    assertNull(actualSessionBorderController.keepalive_daemons);
    SessionBorderControllerProfile sessionBorderControllerProfile = actualSessionBorderController.sbc_profile;
    assertEquals(0, sessionBorderControllerProfile.sink_port);
    MediaGw mediaGw = actualSessionBorderController.media_gw;
    assertSame(sessionBorderControllerProfile, mediaGw.sbc_profile);
    assertTrue(mediaGw.masq_table.isEmpty());
    ExtendedSipProvider extendedSipProvider1 = actualSessionBorderController.sip_provider;
    assertSame(extendedSipProvider.address_resolver, extendedSipProvider1.address_resolver);

    assertTrue(mediaGw.call_set.isEmpty());
    assertTrue(mediaGw.dumper instanceof org.zoolu.util.LogWriter);
    assertNull(mediaGw.logger);
    assertEquals(1, extendedSipProvider1.getListeners().size());
    CircularEnumeration circularEnumeration = mediaGw.media_ports;
    assertSame(sessionBorderControllerProfile.media_ports, circularEnumeration.v);
    assertEquals(0, circularEnumeration.i);
  }

  @Test
  void testConstructor2() {
    // Arrange
    ExtendedSipProvider extendedSipProvider = new ExtendedSipProvider("42 Main St", 8080,
        new String[]{"foo", "foo", "foo"}, 10L, 10L);

    ServerProfile server_profile = new ServerProfile("File");

    // Act
    SessionBorderController actualSessionBorderController = new SessionBorderController(extendedSipProvider,
        server_profile, new SessionBorderControllerProfile());

    // Assert
    assertNull(actualSessionBorderController.keepalive_daemons);
    SessionBorderControllerProfile sessionBorderControllerProfile = actualSessionBorderController.sbc_profile;
    assertEquals(0, sessionBorderControllerProfile.sink_port);
    MediaGw mediaGw = actualSessionBorderController.media_gw;
    assertSame(sessionBorderControllerProfile, mediaGw.sbc_profile);
    assertTrue(mediaGw.masq_table.isEmpty());
    ExtendedSipProvider extendedSipProvider1 = actualSessionBorderController.sip_provider;
    assertSame(extendedSipProvider.address_resolver, extendedSipProvider1.address_resolver);
    assertEquals("42 Main St", sessionBorderControllerProfile.media_addr);
    assertTrue(mediaGw.call_set.isEmpty());
    assertTrue(mediaGw.dumper instanceof org.zoolu.util.LogWriter);
    assertNull(mediaGw.logger);
    assertEquals(1, extendedSipProvider1.getListeners().size());
    CircularEnumeration circularEnumeration = mediaGw.media_ports;
    assertSame(sessionBorderControllerProfile.media_ports, circularEnumeration.v);
    assertEquals(0, circularEnumeration.i);
  }

  @Test
  void testConstructor3() {
    // Arrange
    ExtendedSipProvider extendedSipProvider = new ExtendedSipProvider("File", 10L, 10L);
    extendedSipProvider.addSelectiveListener(new MethodId("ANY"), mock(SipProviderListener.class));
    ServerProfile server_profile = new ServerProfile("File");

    // Act
    SessionBorderController actualSessionBorderController = new SessionBorderController(extendedSipProvider,
        server_profile, new SessionBorderControllerProfile());

    // Assert
    assertNull(actualSessionBorderController.keepalive_daemons);
    SessionBorderControllerProfile sessionBorderControllerProfile = actualSessionBorderController.sbc_profile;
    assertEquals(0, sessionBorderControllerProfile.sink_port);
    MediaGw mediaGw = actualSessionBorderController.media_gw;
    assertSame(sessionBorderControllerProfile, mediaGw.sbc_profile);
    assertTrue(mediaGw.masq_table.isEmpty());
    ExtendedSipProvider extendedSipProvider1 = actualSessionBorderController.sip_provider;
    assertSame(extendedSipProvider.address_resolver, extendedSipProvider1.address_resolver);

    assertTrue(mediaGw.call_set.isEmpty());
    assertTrue(mediaGw.dumper instanceof org.zoolu.util.LogWriter);
    assertNull(mediaGw.logger);
    assertEquals(1, extendedSipProvider1.getListeners().size());
    CircularEnumeration circularEnumeration = mediaGw.media_ports;
    assertSame(sessionBorderControllerProfile.media_ports, circularEnumeration.v);
    assertEquals(0, circularEnumeration.i);
  }

  @Test
  void testUpdateProxyingResponse() {
    // Arrange
    ExtendedSipProvider sip_provider = new ExtendedSipProvider("File", 10L, 10L);

    ServerProfile server_profile = new ServerProfile("File");
    SessionBorderController sessionBorderController = new SessionBorderController(sip_provider, server_profile,
        new SessionBorderControllerProfile());

    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addViaHeader(new ViaHeader("42"));

    // Act
    SipMessage actualUpdateProxyingResponseResult = sessionBorderController.updateProxyingResponse(sipMessage);

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
    ExtendedSipProvider sip_provider = new ExtendedSipProvider("File", 10L, 10L);

    ServerProfile server_profile = new ServerProfile("File");
    SessionBorderController sessionBorderController = new SessionBorderController(sip_provider, server_profile,
        new SessionBorderControllerProfile());

    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addViaHeader(new ViaHeader("42"));
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    SipMessage actualUpdateProxyingResponseResult = sessionBorderController.updateProxyingResponse(sipMessage);

    // Assert
    assertSame(sipMessage, actualUpdateProxyingResponseResult);
    assertNull(actualUpdateProxyingResponseResult.getAcceptEncodingHeader());
    assertEquals(-1, actualUpdateProxyingResponseResult.getRemotePort());
    assertNull(actualUpdateProxyingResponseResult.getRemoteAddress());
    assertNull(actualUpdateProxyingResponseResult.getConnectionId());
  }

  @Test
  void testDoRelay() {
    // Arrange
    ExtendedSipProvider sip_provider = new ExtendedSipProvider("File", 10L, 10L);

    ServerProfile server_profile = new ServerProfile("File");

    // Act and Assert
    assertTrue((new SessionBorderController(sip_provider, server_profile, new SessionBorderControllerProfile()))
        .doRelay("42 Main St"));
  }
}

