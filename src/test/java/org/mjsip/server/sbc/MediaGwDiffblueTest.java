package org.mjsip.server.sbc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import java.io.StringWriter;
import java.io.Writer;
import org.junit.jupiter.api.Test;
import org.zoolu.net.SocketAddress;
import org.zoolu.util.LogLevel;
import org.zoolu.util.LogWriter;
import org.zoolu.util.Logger;

class MediaGwDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange
    SessionBorderControllerProfile sbc_profile = new SessionBorderControllerProfile();

    // Act
    MediaGw actualMediaGw = new MediaGw(sbc_profile, new LogWriter(new StringWriter()));

    // Assert
    assertTrue(actualMediaGw.call_set.isEmpty());
    Logger logger = actualMediaGw.dumper;
    assertTrue(logger instanceof LogWriter);
    assertTrue(actualMediaGw.masq_table.isEmpty());
    Logger logger1 = actualMediaGw.logger;
    assertTrue(logger1 instanceof LogWriter);
    LogLevel expectedLoggingLevel = ((LogWriter) logger1).getLoggingLevel();
    assertSame(expectedLoggingLevel, ((LogWriter) logger).getLoggingLevel());
    SessionBorderControllerProfile sessionBorderControllerProfile = actualMediaGw.sbc_profile;
    assertEquals(0, sessionBorderControllerProfile.sink_port);
    CircularEnumeration circularEnumeration = actualMediaGw.media_ports;
    assertSame(sessionBorderControllerProfile.media_ports, circularEnumeration.v);
    assertEquals(0, circularEnumeration.i);
  }

  @Test
  void testCreateSymmetricUdpRelay() {
    // Arrange
    SessionBorderControllerProfile sbc_profile = new SessionBorderControllerProfile();
    MediaGw mediaGw = new MediaGw(sbc_profile, new LogWriter(new StringWriter()));
    SocketAddress peer_soaddr = new SocketAddress("42 Main St");
    Masquerade masq_left = new Masquerade(peer_soaddr, new SocketAddress("42 Main St"));

    SocketAddress peer_soaddr1 = new SocketAddress("42 Main St");

    // Act and Assert
    assertNull(
        mediaGw.createSymmetricUdpRelay(masq_left, new Masquerade(peer_soaddr1, new SocketAddress("42 Main St"))));
  }

  @Test
  void testCreateSymmetricUdpRelay2() {
    // Arrange
    SessionBorderControllerProfile sbc_profile = new SessionBorderControllerProfile("File");
    MediaGw mediaGw = new MediaGw(sbc_profile, new LogWriter(new StringWriter()));
    SocketAddress peer_soaddr = new SocketAddress("42 Main St");
    Masquerade masq_left = new Masquerade(peer_soaddr, new SocketAddress("42 Main St"));

    SocketAddress peer_soaddr1 = new SocketAddress("42 Main St");

    // Act and Assert
    assertNull(
        mediaGw.createSymmetricUdpRelay(masq_left, new Masquerade(peer_soaddr1, new SocketAddress("42 Main St"))));
  }

  @Test
  void testCreateSymmetricUdpRelay3() {
    // Arrange
    SessionBorderControllerProfile sbc_profile = new SessionBorderControllerProfile();
    MediaGw mediaGw = new MediaGw(sbc_profile, new LogWriter((Writer) null));
    SocketAddress peer_soaddr = new SocketAddress("42 Main St");
    Masquerade masq_left = new Masquerade(peer_soaddr, new SocketAddress("42 Main St"));

    SocketAddress peer_soaddr1 = new SocketAddress("42 Main St");

    // Act and Assert
    assertNull(
        mediaGw.createSymmetricUdpRelay(masq_left, new Masquerade(peer_soaddr1, new SocketAddress("42 Main St"))));
  }

  @Test
  void testCreateSymmetricUdpRelay4() {
    // Arrange
    SessionBorderControllerProfile sbc_profile = new SessionBorderControllerProfile();
    MediaGw mediaGw = new MediaGw(sbc_profile, new LogWriter(new StringWriter()));
    Masquerade masq_left = new Masquerade(new SocketAddress("42 Main St"), null);

    SocketAddress peer_soaddr = new SocketAddress("42 Main St");

    // Act and Assert
    assertNull(
        mediaGw.createSymmetricUdpRelay(masq_left, new Masquerade(peer_soaddr, new SocketAddress("42 Main St"))));
  }

  @Test
  void testCreateSymmetricUdpRelay5() {
    // Arrange
    SessionBorderControllerProfile sbc_profile = new SessionBorderControllerProfile();
    MediaGw mediaGw = new MediaGw(sbc_profile, new LogWriter(new StringWriter()));
    SocketAddress peer_soaddr = new SocketAddress("42 Main St");

    // Act and Assert
    assertNull(mediaGw.createSymmetricUdpRelay(null, new Masquerade(peer_soaddr, new SocketAddress("42 Main St"))));
  }

  @Test
  void testCreateSymmetricUdpRelay6() {
    // Arrange
    SessionBorderControllerProfile sbc_profile = new SessionBorderControllerProfile();
    MediaGw mediaGw = new MediaGw(sbc_profile, new LogWriter(new StringWriter()));
    SocketAddress peer_soaddr = new SocketAddress("42 Main St");
    Masquerade masq_left = new Masquerade(peer_soaddr, new SocketAddress("42 Main St"));

    // Act and Assert
    assertNull(mediaGw.createSymmetricUdpRelay(masq_left, new Masquerade(new SocketAddress("42 Main St"), null)));
  }

  @Test
  void testCreateSymmetricUdpRelay7() {
    // Arrange
    SessionBorderControllerProfile sbc_profile = new SessionBorderControllerProfile();
    MediaGw mediaGw = new MediaGw(sbc_profile, new LogWriter(new StringWriter()));
    SocketAddress peer_soaddr = new SocketAddress("42 Main St");

    // Act and Assert
    assertNull(mediaGw.createSymmetricUdpRelay(new Masquerade(peer_soaddr, new SocketAddress("42 Main St")), null));
  }

  @Test
  void testCreateSymmetricUdpRelay8() {
    // Arrange
    MediaGw mediaGw = new MediaGw(new SessionBorderControllerProfile("File"), null);
    SocketAddress peer_soaddr = new SocketAddress("42 Main St");
    Masquerade masq_left = new Masquerade(peer_soaddr, new SocketAddress("42 Main St"));

    SocketAddress peer_soaddr1 = new SocketAddress("42 Main St");

    // Act and Assert
    assertNull(
        mediaGw.createSymmetricUdpRelay(masq_left, new Masquerade(peer_soaddr1, new SocketAddress("42 Main St"))));
  }

  @Test
  void testCreateSymmetricUdpRelay9() {
    // Arrange
    SessionBorderControllerProfile sbc_profile = new SessionBorderControllerProfile("File");
    MediaGw mediaGw = new MediaGw(sbc_profile, new LogWriter(new StringWriter()));
    SocketAddress peer_soaddr = new SocketAddress("42 Main St");
    Masquerade masq_left = new Masquerade(peer_soaddr, new SocketAddress(new SocketAddress("42 Main St")));

    SocketAddress peer_soaddr1 = new SocketAddress("42 Main St");

    // Act and Assert
    assertNull(
        mediaGw.createSymmetricUdpRelay(masq_left, new Masquerade(peer_soaddr1, new SocketAddress("42 Main St"))));
  }

  @Test
  void testOnSymmetricUdpRelayTerminated() {
    // Arrange
    Logger logger = mock(Logger.class);
    doNothing().when(logger).log((org.zoolu.util.LogLevel) any(), (String) any());

    // Act
    (new MediaGw(new SessionBorderControllerProfile(), logger)).onSymmetricUdpRelayTerminated(null);

    // Assert
    verify(logger).log((org.zoolu.util.LogLevel) any(), (String) any());
  }

  @Test
  void testOnSymmetricUdpRelayTerminated2() {
    // Arrange
    Logger logger = mock(Logger.class);
    doNothing().when(logger).log((org.zoolu.util.LogLevel) any(), (String) any());

    // Act
    (new MediaGw(new SessionBorderControllerProfile("File"), logger)).onSymmetricUdpRelayTerminated(null);

    // Assert
    verify(logger).log((org.zoolu.util.LogLevel) any(), (String) any());
  }
}

