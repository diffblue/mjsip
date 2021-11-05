package org.mjsip.server.sbc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import java.io.UnsupportedEncodingException;
import java.util.Hashtable;
import java.util.Vector;
import org.junit.jupiter.api.Test;
import org.mjsip.sip.header.MultipleHeader;
import org.mjsip.sip.header.StatusLine;
import org.mjsip.sip.header.ViaHeader;
import org.mjsip.sip.message.SipMessage;
import org.mjsip.sip.provider.SipProviderExceptionListener;
import org.zoolu.util.Timer;

class ExtendedSipProviderDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    ExtendedSipProvider actualExtendedSipProvider = new ExtendedSipProvider("42 Main St", 8080,
        new String[]{"Protocols"}, 10L, 10L);

    // Assert
    assertNull(actualExtendedSipProvider.getBindingIpAddress());
    AddressResolver addressResolver = actualExtendedSipProvider.address_resolver;
    assertTrue(addressResolver instanceof AddressResolverKeepAlive);
    assertTrue(actualExtendedSipProvider.isRportSet());
    Hashtable listeners = actualExtendedSipProvider.getListeners();
    assertTrue(listeners.isEmpty());
    assertEquals(8080, actualExtendedSipProvider.getPort());
    assertFalse(actualExtendedSipProvider.isForceRportSet());
    assertNull(actualExtendedSipProvider.getTelGateway());
    assertNull(actualExtendedSipProvider.getLogger());
    assertEquals(0, actualExtendedSipProvider.getTlsPort());
    assertEquals("Protocols", actualExtendedSipProvider.getDefaultTransport());
    assertEquals(Integer.SIZE, actualExtendedSipProvider.getNMaxConnections());
    assertEquals("42 Main St", actualExtendedSipProvider.getViaAddress());
    assertFalse(actualExtendedSipProvider.hasOutboundProxy());
    assertNull(((AddressResolverKeepAlive) addressResolver).logger);
    assertEquals(listeners, ((AddressResolverKeepAlive) addressResolver).binding_table);
    assertEquals(listeners, ((AddressResolverKeepAlive) addressResolver).keepalive_daemons);
    assertEquals(10L, ((AddressResolverKeepAlive) addressResolver).keepalive_time);
    assertSame(actualExtendedSipProvider, ((AddressResolverKeepAlive) addressResolver).sip_provider);
    assertEquals(10L, ((AddressResolverKeepAlive) addressResolver).refresh_time);
    assertEquals(listeners, ((AddressResolverKeepAlive) addressResolver).time_table);
    assertEquals(5L, ((AddressResolverKeepAlive) addressResolver).expire_time);
    assertTrue(Timer.DEFAULT_DAEMON_MODE);
    Timer timer = ((AddressResolverKeepAlive) addressResolver).timer;
    assertTrue(timer.isRunning());
    assertEquals(10L, timer.getTime());
  }

  @Test
  void testConstructor2() {
    // Arrange and Act
    ExtendedSipProvider actualExtendedSipProvider = new ExtendedSipProvider("AUTO-CONFIGURATION", 8080,
        new String[]{"Protocols"}, 10L, 10L);

    // Assert
    assertNull(actualExtendedSipProvider.getBindingIpAddress());
    AddressResolver addressResolver = actualExtendedSipProvider.address_resolver;
    assertTrue(addressResolver instanceof AddressResolverKeepAlive);
    assertTrue(actualExtendedSipProvider.isRportSet());
    Hashtable listeners = actualExtendedSipProvider.getListeners();
    assertTrue(listeners.isEmpty());
    assertEquals(8080, actualExtendedSipProvider.getPort());
    assertFalse(actualExtendedSipProvider.isForceRportSet());
    assertNull(actualExtendedSipProvider.getTelGateway());
    assertNull(actualExtendedSipProvider.getLogger());
    assertEquals(0, actualExtendedSipProvider.getTlsPort());
    assertEquals("Protocols", actualExtendedSipProvider.getDefaultTransport());
    assertEquals(Integer.SIZE, actualExtendedSipProvider.getNMaxConnections());

    assertFalse(actualExtendedSipProvider.hasOutboundProxy());
    assertNull(((AddressResolverKeepAlive) addressResolver).logger);
    assertEquals(listeners, ((AddressResolverKeepAlive) addressResolver).binding_table);
    assertEquals(listeners, ((AddressResolverKeepAlive) addressResolver).keepalive_daemons);
    assertEquals(10L, ((AddressResolverKeepAlive) addressResolver).keepalive_time);
    assertSame(actualExtendedSipProvider, ((AddressResolverKeepAlive) addressResolver).sip_provider);
    assertEquals(10L, ((AddressResolverKeepAlive) addressResolver).refresh_time);
    assertEquals(listeners, ((AddressResolverKeepAlive) addressResolver).time_table);
    assertEquals(5L, ((AddressResolverKeepAlive) addressResolver).expire_time);
    assertTrue(Timer.DEFAULT_DAEMON_MODE);
    Timer timer = ((AddressResolverKeepAlive) addressResolver).timer;
    assertTrue(timer.isRunning());
    assertEquals(10L, timer.getTime());
  }

  @Test
  void testConstructor3() {
    // Arrange and Act
    ExtendedSipProvider actualExtendedSipProvider = new ExtendedSipProvider("42 Main St", 0, new String[]{"Protocols"},
        10L, 10L);

    // Assert
    assertNull(actualExtendedSipProvider.getBindingIpAddress());
    AddressResolver addressResolver = actualExtendedSipProvider.address_resolver;
    assertTrue(addressResolver instanceof AddressResolverKeepAlive);
    assertTrue(actualExtendedSipProvider.isRportSet());
    Hashtable listeners = actualExtendedSipProvider.getListeners();
    assertTrue(listeners.isEmpty());
    assertEquals(5060, actualExtendedSipProvider.getPort());
    assertFalse(actualExtendedSipProvider.isForceRportSet());
    assertNull(actualExtendedSipProvider.getTelGateway());
    assertNull(actualExtendedSipProvider.getLogger());
    assertEquals(0, actualExtendedSipProvider.getTlsPort());
    assertEquals("Protocols", actualExtendedSipProvider.getDefaultTransport());
    assertEquals(Integer.SIZE, actualExtendedSipProvider.getNMaxConnections());
    assertEquals("42 Main St", actualExtendedSipProvider.getViaAddress());
    assertFalse(actualExtendedSipProvider.hasOutboundProxy());
    assertNull(((AddressResolverKeepAlive) addressResolver).logger);
    assertEquals(listeners, ((AddressResolverKeepAlive) addressResolver).binding_table);
    assertEquals(listeners, ((AddressResolverKeepAlive) addressResolver).keepalive_daemons);
    assertEquals(10L, ((AddressResolverKeepAlive) addressResolver).keepalive_time);
    assertSame(actualExtendedSipProvider, ((AddressResolverKeepAlive) addressResolver).sip_provider);
    assertEquals(10L, ((AddressResolverKeepAlive) addressResolver).refresh_time);
    assertEquals(listeners, ((AddressResolverKeepAlive) addressResolver).time_table);
    assertEquals(5L, ((AddressResolverKeepAlive) addressResolver).expire_time);
    assertTrue(Timer.DEFAULT_DAEMON_MODE);
    Timer timer = ((AddressResolverKeepAlive) addressResolver).timer;
    assertTrue(timer.isRunning());
    assertEquals(10L, timer.getTime());
  }

  @Test
  void testConstructor4() {
    // Arrange and Act
    ExtendedSipProvider actualExtendedSipProvider = new ExtendedSipProvider("42 Main St", 8080, new String[]{"udp"},
        10L, 10L);

    // Assert
    assertNull(actualExtendedSipProvider.getBindingIpAddress());
    AddressResolver addressResolver = actualExtendedSipProvider.address_resolver;
    assertTrue(addressResolver instanceof AddressResolverKeepAlive);
    assertTrue(actualExtendedSipProvider.isRportSet());
    Hashtable listeners = actualExtendedSipProvider.getListeners();
    assertTrue(listeners.isEmpty());
    assertEquals(8080, actualExtendedSipProvider.getPort());
    assertFalse(actualExtendedSipProvider.isForceRportSet());
    assertNull(actualExtendedSipProvider.getTelGateway());
    assertNull(actualExtendedSipProvider.getLogger());
    assertEquals(0, actualExtendedSipProvider.getTlsPort());
    assertEquals("udp", actualExtendedSipProvider.getDefaultTransport());
    assertEquals(Integer.SIZE, actualExtendedSipProvider.getNMaxConnections());
    assertEquals("42 Main St", actualExtendedSipProvider.getViaAddress());
    assertFalse(actualExtendedSipProvider.hasOutboundProxy());
    assertNull(((AddressResolverKeepAlive) addressResolver).logger);
    assertEquals(listeners, ((AddressResolverKeepAlive) addressResolver).binding_table);
    assertEquals(listeners, ((AddressResolverKeepAlive) addressResolver).keepalive_daemons);
    assertEquals(10L, ((AddressResolverKeepAlive) addressResolver).keepalive_time);
    assertSame(actualExtendedSipProvider, ((AddressResolverKeepAlive) addressResolver).sip_provider);
    assertEquals(10L, ((AddressResolverKeepAlive) addressResolver).refresh_time);
    assertEquals(listeners, ((AddressResolverKeepAlive) addressResolver).time_table);
    assertEquals(5L, ((AddressResolverKeepAlive) addressResolver).expire_time);
    assertTrue(Timer.DEFAULT_DAEMON_MODE);
    Timer timer = ((AddressResolverKeepAlive) addressResolver).timer;
    assertTrue(timer.isRunning());
    assertEquals(10L, timer.getTime());
  }

  @Test
  void testConstructor5() {
    // Arrange and Act
    ExtendedSipProvider actualExtendedSipProvider = new ExtendedSipProvider("42 Main St", 8080, new String[]{"tcp"},
        10L, 10L);

    // Assert
    assertNull(actualExtendedSipProvider.getBindingIpAddress());
    AddressResolver addressResolver = actualExtendedSipProvider.address_resolver;
    assertTrue(addressResolver instanceof AddressResolverKeepAlive);
    assertTrue(actualExtendedSipProvider.isRportSet());
    Hashtable listeners = actualExtendedSipProvider.getListeners();
    assertTrue(listeners.isEmpty());
    assertEquals(8080, actualExtendedSipProvider.getPort());
    assertFalse(actualExtendedSipProvider.isForceRportSet());
    assertNull(actualExtendedSipProvider.getTelGateway());
    assertNull(actualExtendedSipProvider.getLogger());
    assertEquals(0, actualExtendedSipProvider.getTlsPort());
    assertEquals("tcp", actualExtendedSipProvider.getDefaultTransport());
    assertEquals(Integer.SIZE, actualExtendedSipProvider.getNMaxConnections());
    assertEquals("42 Main St", actualExtendedSipProvider.getViaAddress());
    assertFalse(actualExtendedSipProvider.hasOutboundProxy());
    assertNull(((AddressResolverKeepAlive) addressResolver).logger);
    assertEquals(listeners, ((AddressResolverKeepAlive) addressResolver).binding_table);
    assertEquals(listeners, ((AddressResolverKeepAlive) addressResolver).keepalive_daemons);
    assertEquals(10L, ((AddressResolverKeepAlive) addressResolver).keepalive_time);
    assertSame(actualExtendedSipProvider, ((AddressResolverKeepAlive) addressResolver).sip_provider);
    assertEquals(10L, ((AddressResolverKeepAlive) addressResolver).refresh_time);
    assertEquals(listeners, ((AddressResolverKeepAlive) addressResolver).time_table);
    assertEquals(5L, ((AddressResolverKeepAlive) addressResolver).expire_time);
    assertTrue(Timer.DEFAULT_DAEMON_MODE);
    Timer timer = ((AddressResolverKeepAlive) addressResolver).timer;
    assertTrue(timer.isRunning());
    assertEquals(10L, timer.getTime());
  }

  @Test
  void testConstructor6() {
    // Arrange and Act
    ExtendedSipProvider actualExtendedSipProvider = new ExtendedSipProvider("42 Main St", 8080, new String[]{"tls"},
        10L, 10L);

    // Assert
    assertNull(actualExtendedSipProvider.getBindingIpAddress());
    AddressResolver addressResolver = actualExtendedSipProvider.address_resolver;
    assertTrue(addressResolver instanceof AddressResolverKeepAlive);
    assertTrue(actualExtendedSipProvider.isRportSet());
    Hashtable listeners = actualExtendedSipProvider.getListeners();
    assertTrue(listeners.isEmpty());
    assertEquals(8080, actualExtendedSipProvider.getPort());
    assertFalse(actualExtendedSipProvider.isForceRportSet());
    assertNull(actualExtendedSipProvider.getTelGateway());
    assertNull(actualExtendedSipProvider.getLogger());
    assertEquals(0, actualExtendedSipProvider.getTlsPort());
    assertEquals("tls", actualExtendedSipProvider.getDefaultTransport());
    assertEquals(Integer.SIZE, actualExtendedSipProvider.getNMaxConnections());
    assertEquals("42 Main St", actualExtendedSipProvider.getViaAddress());
    assertFalse(actualExtendedSipProvider.hasOutboundProxy());
    assertNull(((AddressResolverKeepAlive) addressResolver).logger);
    assertEquals(listeners, ((AddressResolverKeepAlive) addressResolver).binding_table);
    assertEquals(listeners, ((AddressResolverKeepAlive) addressResolver).keepalive_daemons);
    assertEquals(10L, ((AddressResolverKeepAlive) addressResolver).keepalive_time);
    assertSame(actualExtendedSipProvider, ((AddressResolverKeepAlive) addressResolver).sip_provider);
    assertEquals(10L, ((AddressResolverKeepAlive) addressResolver).refresh_time);
    assertEquals(listeners, ((AddressResolverKeepAlive) addressResolver).time_table);
    assertEquals(5L, ((AddressResolverKeepAlive) addressResolver).expire_time);
    assertTrue(Timer.DEFAULT_DAEMON_MODE);
    Timer timer = ((AddressResolverKeepAlive) addressResolver).timer;
    assertTrue(timer.isRunning());
    assertEquals(10L, timer.getTime());
  }

  @Test
  void testConstructor7() {
    // Arrange and Act
    ExtendedSipProvider actualExtendedSipProvider = new ExtendedSipProvider("42 Main St", 8080, new String[]{"dtls"},
        10L, 10L);

    // Assert
    assertNull(actualExtendedSipProvider.getBindingIpAddress());
    AddressResolver addressResolver = actualExtendedSipProvider.address_resolver;
    assertTrue(addressResolver instanceof AddressResolverKeepAlive);
    assertTrue(actualExtendedSipProvider.isRportSet());
    Hashtable listeners = actualExtendedSipProvider.getListeners();
    assertTrue(listeners.isEmpty());
    assertEquals(8080, actualExtendedSipProvider.getPort());
    assertFalse(actualExtendedSipProvider.isForceRportSet());
    assertNull(actualExtendedSipProvider.getTelGateway());
    assertNull(actualExtendedSipProvider.getLogger());
    assertEquals(0, actualExtendedSipProvider.getTlsPort());
    assertEquals("dtls", actualExtendedSipProvider.getDefaultTransport());
    assertEquals(Integer.SIZE, actualExtendedSipProvider.getNMaxConnections());
    assertEquals("42 Main St", actualExtendedSipProvider.getViaAddress());
    assertFalse(actualExtendedSipProvider.hasOutboundProxy());
    assertNull(((AddressResolverKeepAlive) addressResolver).logger);
    assertEquals(listeners, ((AddressResolverKeepAlive) addressResolver).binding_table);
    assertEquals(listeners, ((AddressResolverKeepAlive) addressResolver).keepalive_daemons);
    assertEquals(10L, ((AddressResolverKeepAlive) addressResolver).keepalive_time);
    assertSame(actualExtendedSipProvider, ((AddressResolverKeepAlive) addressResolver).sip_provider);
    assertEquals(10L, ((AddressResolverKeepAlive) addressResolver).refresh_time);
    assertEquals(listeners, ((AddressResolverKeepAlive) addressResolver).time_table);
    assertEquals(5L, ((AddressResolverKeepAlive) addressResolver).expire_time);
    assertTrue(Timer.DEFAULT_DAEMON_MODE);
    Timer timer = ((AddressResolverKeepAlive) addressResolver).timer;
    assertTrue(timer.isRunning());
    assertEquals(10L, timer.getTime());
  }

  @Test
  void testConstructor8() {
    // Arrange and Act
    ExtendedSipProvider actualExtendedSipProvider = new ExtendedSipProvider("42 Main St", 8080, new String[]{"sctp"},
        10L, 10L);

    // Assert
    assertNull(actualExtendedSipProvider.getBindingIpAddress());
    AddressResolver addressResolver = actualExtendedSipProvider.address_resolver;
    assertTrue(addressResolver instanceof AddressResolverKeepAlive);
    assertTrue(actualExtendedSipProvider.isRportSet());
    Hashtable listeners = actualExtendedSipProvider.getListeners();
    assertTrue(listeners.isEmpty());
    assertEquals(8080, actualExtendedSipProvider.getPort());
    assertFalse(actualExtendedSipProvider.isForceRportSet());
    assertNull(actualExtendedSipProvider.getTelGateway());
    assertNull(actualExtendedSipProvider.getLogger());
    assertEquals(0, actualExtendedSipProvider.getTlsPort());
    assertEquals("sctp", actualExtendedSipProvider.getDefaultTransport());
    assertEquals(Integer.SIZE, actualExtendedSipProvider.getNMaxConnections());
    assertEquals("42 Main St", actualExtendedSipProvider.getViaAddress());
    assertFalse(actualExtendedSipProvider.hasOutboundProxy());
    assertNull(((AddressResolverKeepAlive) addressResolver).logger);
    assertEquals(listeners, ((AddressResolverKeepAlive) addressResolver).binding_table);
    assertEquals(listeners, ((AddressResolverKeepAlive) addressResolver).keepalive_daemons);
    assertEquals(10L, ((AddressResolverKeepAlive) addressResolver).keepalive_time);
    assertSame(actualExtendedSipProvider, ((AddressResolverKeepAlive) addressResolver).sip_provider);
    assertEquals(10L, ((AddressResolverKeepAlive) addressResolver).refresh_time);
    assertEquals(listeners, ((AddressResolverKeepAlive) addressResolver).time_table);
    assertEquals(5L, ((AddressResolverKeepAlive) addressResolver).expire_time);
    assertTrue(Timer.DEFAULT_DAEMON_MODE);
    Timer timer = ((AddressResolverKeepAlive) addressResolver).timer;
    assertTrue(timer.isRunning());
    assertEquals(10L, timer.getTime());
  }

  @Test
  void testConstructor9() {
    // Arrange and Act
    ExtendedSipProvider actualExtendedSipProvider = new ExtendedSipProvider("42 Main St", 8080, new String[]{}, 10L,
        10L);

    // Assert
    assertNull(actualExtendedSipProvider.getBindingIpAddress());
    AddressResolver addressResolver = actualExtendedSipProvider.address_resolver;
    assertTrue(addressResolver instanceof AddressResolverKeepAlive);
    assertTrue(actualExtendedSipProvider.isRportSet());
    assertNull(actualExtendedSipProvider.getDefaultTransport());
    assertNull(actualExtendedSipProvider.getLogger());
    assertNull(actualExtendedSipProvider.getTelGateway());
    assertFalse(actualExtendedSipProvider.isForceRportSet());
    assertEquals(0, actualExtendedSipProvider.getTlsPort());
    assertEquals(Integer.SIZE, actualExtendedSipProvider.getNMaxConnections());
    assertEquals("42 Main St", actualExtendedSipProvider.getViaAddress());
    Hashtable listeners = actualExtendedSipProvider.getListeners();
    assertTrue(listeners.isEmpty());
    assertNull(actualExtendedSipProvider.getOutboundProxy());
    assertEquals(8080, actualExtendedSipProvider.getPort());
    assertEquals(listeners, ((AddressResolverKeepAlive) addressResolver).binding_table);
    assertEquals(10L, ((AddressResolverKeepAlive) addressResolver).keepalive_time);
    assertNull(((AddressResolverKeepAlive) addressResolver).logger);
    assertSame(actualExtendedSipProvider, ((AddressResolverKeepAlive) addressResolver).sip_provider);
    assertEquals(listeners, ((AddressResolverKeepAlive) addressResolver).time_table);
    assertEquals(5L, ((AddressResolverKeepAlive) addressResolver).expire_time);
    assertEquals(10L, ((AddressResolverKeepAlive) addressResolver).refresh_time);
    assertEquals(listeners, ((AddressResolverKeepAlive) addressResolver).keepalive_daemons);
    Timer timer = ((AddressResolverKeepAlive) addressResolver).timer;
    assertTrue(timer.isRunning());
    assertTrue(Timer.DEFAULT_DAEMON_MODE);
    assertEquals(10L, timer.getTime());
  }

  @Test
  void testConstructor10() {
    // Arrange and Act
    ExtendedSipProvider actualExtendedSipProvider = new ExtendedSipProvider("42 Main St", 8080,
        new String[]{"Protocols"}, -1L, 10L);

    // Assert
    assertNull(actualExtendedSipProvider.getBindingIpAddress());
    AddressResolver addressResolver = actualExtendedSipProvider.address_resolver;
    assertTrue(addressResolver instanceof AddressResolverKeepAlive);
    assertTrue(actualExtendedSipProvider.isRportSet());
    Hashtable listeners = actualExtendedSipProvider.getListeners();
    assertTrue(listeners.isEmpty());
    assertEquals(8080, actualExtendedSipProvider.getPort());
    assertFalse(actualExtendedSipProvider.isForceRportSet());
    assertNull(actualExtendedSipProvider.getTelGateway());
    assertNull(actualExtendedSipProvider.getLogger());
    assertEquals(0, actualExtendedSipProvider.getTlsPort());
    assertEquals("Protocols", actualExtendedSipProvider.getDefaultTransport());
    assertEquals(Integer.SIZE, actualExtendedSipProvider.getNMaxConnections());
    assertEquals("42 Main St", actualExtendedSipProvider.getViaAddress());
    assertFalse(actualExtendedSipProvider.hasOutboundProxy());
    assertNull(((AddressResolverKeepAlive) addressResolver).logger);
    assertEquals(listeners, ((AddressResolverKeepAlive) addressResolver).binding_table);
    assertEquals(listeners, ((AddressResolverKeepAlive) addressResolver).keepalive_daemons);
    assertEquals(10L, ((AddressResolverKeepAlive) addressResolver).keepalive_time);
    assertSame(actualExtendedSipProvider, ((AddressResolverKeepAlive) addressResolver).sip_provider);
    assertEquals(-1L, ((AddressResolverKeepAlive) addressResolver).refresh_time);
    assertEquals(listeners, ((AddressResolverKeepAlive) addressResolver).time_table);
    assertEquals(0L, ((AddressResolverKeepAlive) addressResolver).expire_time);
    assertTrue(Timer.DEFAULT_DAEMON_MODE);
    Timer timer = ((AddressResolverKeepAlive) addressResolver).timer;
    assertFalse(timer.isRunning());
    assertEquals(-1L, timer.getTime());
  }

  @Test
  void testConstructor11() {
    // Arrange and Act
    ExtendedSipProvider actualExtendedSipProvider = new ExtendedSipProvider("42 Main St", 0, new String[]{"Protocols"},
        -1L, 10L);

    // Assert
    assertNull(actualExtendedSipProvider.getBindingIpAddress());
    AddressResolver addressResolver = actualExtendedSipProvider.address_resolver;
    assertTrue(addressResolver instanceof AddressResolverKeepAlive);
    assertTrue(actualExtendedSipProvider.isRportSet());
    Hashtable listeners = actualExtendedSipProvider.getListeners();
    assertTrue(listeners.isEmpty());
    assertEquals(5060, actualExtendedSipProvider.getPort());
    assertFalse(actualExtendedSipProvider.isForceRportSet());
    assertNull(actualExtendedSipProvider.getTelGateway());
    assertNull(actualExtendedSipProvider.getLogger());
    assertEquals(0, actualExtendedSipProvider.getTlsPort());
    assertEquals("Protocols", actualExtendedSipProvider.getDefaultTransport());
    assertEquals(Integer.SIZE, actualExtendedSipProvider.getNMaxConnections());
    assertEquals("42 Main St", actualExtendedSipProvider.getViaAddress());
    assertFalse(actualExtendedSipProvider.hasOutboundProxy());
    assertNull(((AddressResolverKeepAlive) addressResolver).logger);
    assertEquals(listeners, ((AddressResolverKeepAlive) addressResolver).binding_table);
    assertEquals(listeners, ((AddressResolverKeepAlive) addressResolver).keepalive_daemons);
    assertEquals(10L, ((AddressResolverKeepAlive) addressResolver).keepalive_time);
    assertSame(actualExtendedSipProvider, ((AddressResolverKeepAlive) addressResolver).sip_provider);
    assertEquals(-1L, ((AddressResolverKeepAlive) addressResolver).refresh_time);
    assertEquals(listeners, ((AddressResolverKeepAlive) addressResolver).time_table);
    assertEquals(0L, ((AddressResolverKeepAlive) addressResolver).expire_time);
    assertTrue(Timer.DEFAULT_DAEMON_MODE);
    Timer timer = ((AddressResolverKeepAlive) addressResolver).timer;
    assertFalse(timer.isRunning());
    assertEquals(-1L, timer.getTime());
  }

  @Test
  void testConstructor12() {
    // Arrange and Act
    ExtendedSipProvider actualExtendedSipProvider = new ExtendedSipProvider("42 Main St", 0, new String[]{"Protocols"},
        Long.MIN_VALUE, 10L);

    // Assert
    assertNull(actualExtendedSipProvider.getBindingIpAddress());
    AddressResolver addressResolver = actualExtendedSipProvider.address_resolver;
    assertTrue(addressResolver instanceof AddressResolverKeepAlive);
    assertTrue(actualExtendedSipProvider.isRportSet());
    Hashtable listeners = actualExtendedSipProvider.getListeners();
    assertTrue(listeners.isEmpty());
    assertEquals(5060, actualExtendedSipProvider.getPort());
    assertFalse(actualExtendedSipProvider.isForceRportSet());
    assertNull(actualExtendedSipProvider.getTelGateway());
    assertNull(actualExtendedSipProvider.getLogger());
    assertEquals(0, actualExtendedSipProvider.getTlsPort());
    assertEquals("Protocols", actualExtendedSipProvider.getDefaultTransport());
    assertEquals(Integer.SIZE, actualExtendedSipProvider.getNMaxConnections());
    assertEquals("42 Main St", actualExtendedSipProvider.getViaAddress());
    assertFalse(actualExtendedSipProvider.hasOutboundProxy());
    assertNull(((AddressResolverKeepAlive) addressResolver).logger);
    assertEquals(listeners, ((AddressResolverKeepAlive) addressResolver).binding_table);
    assertEquals(listeners, ((AddressResolverKeepAlive) addressResolver).keepalive_daemons);
    assertEquals(10L, ((AddressResolverKeepAlive) addressResolver).keepalive_time);
    assertSame(actualExtendedSipProvider, ((AddressResolverKeepAlive) addressResolver).sip_provider);
    assertEquals(Long.MIN_VALUE, ((AddressResolverKeepAlive) addressResolver).refresh_time);
    assertEquals(listeners, ((AddressResolverKeepAlive) addressResolver).time_table);
    assertEquals(-4611686018427387904L, ((AddressResolverKeepAlive) addressResolver).expire_time);
    assertTrue(Timer.DEFAULT_DAEMON_MODE);
    Timer timer = ((AddressResolverKeepAlive) addressResolver).timer;
    assertFalse(timer.isRunning());
    assertEquals(Long.MIN_VALUE, timer.getTime());
  }

  @Test
  void testConstructor13() {
    // Arrange and Act
    ExtendedSipProvider actualExtendedSipProvider = new ExtendedSipProvider("42 Main St", 8080,
        new String[]{"protocols"}, -1L, 10L);

    // Assert
    assertNull(actualExtendedSipProvider.getBindingIpAddress());
    AddressResolver addressResolver = actualExtendedSipProvider.address_resolver;
    assertTrue(addressResolver instanceof AddressResolverKeepAlive);
    assertTrue(actualExtendedSipProvider.isRportSet());
    Hashtable listeners = actualExtendedSipProvider.getListeners();
    assertTrue(listeners.isEmpty());
    assertEquals(8080, actualExtendedSipProvider.getPort());
    assertFalse(actualExtendedSipProvider.isForceRportSet());
    assertNull(actualExtendedSipProvider.getTelGateway());
    assertNull(actualExtendedSipProvider.getLogger());
    assertEquals(0, actualExtendedSipProvider.getTlsPort());
    assertEquals("protocols", actualExtendedSipProvider.getDefaultTransport());
    assertEquals(Integer.SIZE, actualExtendedSipProvider.getNMaxConnections());
    assertEquals("42 Main St", actualExtendedSipProvider.getViaAddress());
    assertFalse(actualExtendedSipProvider.hasOutboundProxy());
    assertNull(((AddressResolverKeepAlive) addressResolver).logger);
    assertEquals(listeners, ((AddressResolverKeepAlive) addressResolver).binding_table);
    assertEquals(listeners, ((AddressResolverKeepAlive) addressResolver).keepalive_daemons);
    assertEquals(10L, ((AddressResolverKeepAlive) addressResolver).keepalive_time);
    assertSame(actualExtendedSipProvider, ((AddressResolverKeepAlive) addressResolver).sip_provider);
    assertEquals(-1L, ((AddressResolverKeepAlive) addressResolver).refresh_time);
    assertEquals(listeners, ((AddressResolverKeepAlive) addressResolver).time_table);
    assertEquals(0L, ((AddressResolverKeepAlive) addressResolver).expire_time);
    assertTrue(Timer.DEFAULT_DAEMON_MODE);
    Timer timer = ((AddressResolverKeepAlive) addressResolver).timer;
    assertFalse(timer.isRunning());
    assertEquals(-1L, timer.getTime());
  }

  @Test
  void testConstructor14() {
    // Arrange and Act
    ExtendedSipProvider actualExtendedSipProvider = new ExtendedSipProvider("42 Main St", 8080, new String[]{"dtls"},
        -1L, 10L);

    // Assert
    assertNull(actualExtendedSipProvider.getBindingIpAddress());
    AddressResolver addressResolver = actualExtendedSipProvider.address_resolver;
    assertTrue(addressResolver instanceof AddressResolverKeepAlive);
    assertTrue(actualExtendedSipProvider.isRportSet());
    Hashtable listeners = actualExtendedSipProvider.getListeners();
    assertTrue(listeners.isEmpty());
    assertEquals(8080, actualExtendedSipProvider.getPort());
    assertFalse(actualExtendedSipProvider.isForceRportSet());
    assertNull(actualExtendedSipProvider.getTelGateway());
    assertNull(actualExtendedSipProvider.getLogger());
    assertEquals(0, actualExtendedSipProvider.getTlsPort());
    assertEquals("dtls", actualExtendedSipProvider.getDefaultTransport());
    assertEquals(Integer.SIZE, actualExtendedSipProvider.getNMaxConnections());
    assertEquals("42 Main St", actualExtendedSipProvider.getViaAddress());
    assertFalse(actualExtendedSipProvider.hasOutboundProxy());
    assertNull(((AddressResolverKeepAlive) addressResolver).logger);
    assertEquals(listeners, ((AddressResolverKeepAlive) addressResolver).binding_table);
    assertEquals(listeners, ((AddressResolverKeepAlive) addressResolver).keepalive_daemons);
    assertEquals(10L, ((AddressResolverKeepAlive) addressResolver).keepalive_time);
    assertSame(actualExtendedSipProvider, ((AddressResolverKeepAlive) addressResolver).sip_provider);
    assertEquals(-1L, ((AddressResolverKeepAlive) addressResolver).refresh_time);
    assertEquals(listeners, ((AddressResolverKeepAlive) addressResolver).time_table);
    assertEquals(0L, ((AddressResolverKeepAlive) addressResolver).expire_time);
    assertTrue(Timer.DEFAULT_DAEMON_MODE);
    Timer timer = ((AddressResolverKeepAlive) addressResolver).timer;
    assertFalse(timer.isRunning());
    assertEquals(-1L, timer.getTime());
  }

  @Test
  void testConstructor15() {
    // Arrange and Act
    ExtendedSipProvider actualExtendedSipProvider = new ExtendedSipProvider("42 Main St", 8080,
        new String[]{"Protocols"}, "", 10L, 10L);

    // Assert
    AddressResolver addressResolver = actualExtendedSipProvider.address_resolver;
    assertTrue(addressResolver instanceof AddressResolverKeepAlive);
    Hashtable listeners = actualExtendedSipProvider.getListeners();
    assertTrue(listeners.isEmpty());
    assertEquals(8080, actualExtendedSipProvider.getPort());
    assertFalse(actualExtendedSipProvider.isAllInterfaces());
    assertFalse(actualExtendedSipProvider.isForceRportSet());
    assertNull(actualExtendedSipProvider.getTelGateway());
    assertTrue(actualExtendedSipProvider.isRportSet());
    assertNull(actualExtendedSipProvider.getLogger());
    assertEquals(0, actualExtendedSipProvider.getTlsPort());
    assertEquals("Protocols", actualExtendedSipProvider.getDefaultTransport());
    assertEquals(Integer.SIZE, actualExtendedSipProvider.getNMaxConnections());
    assertEquals("42 Main St", actualExtendedSipProvider.getViaAddress());
    assertFalse(actualExtendedSipProvider.hasOutboundProxy());
    assertEquals(listeners, ((AddressResolverKeepAlive) addressResolver).time_table);
    assertEquals("127.0.0.1", actualExtendedSipProvider.getBindingIpAddress().toString());
    assertEquals(listeners, ((AddressResolverKeepAlive) addressResolver).binding_table);
    assertNull(((AddressResolverKeepAlive) addressResolver).logger);
    assertEquals(10L, ((AddressResolverKeepAlive) addressResolver).refresh_time);
    assertEquals(5L, ((AddressResolverKeepAlive) addressResolver).expire_time);
    assertEquals(10L, ((AddressResolverKeepAlive) addressResolver).keepalive_time);
    assertEquals(listeners, ((AddressResolverKeepAlive) addressResolver).keepalive_daemons);
    assertSame(actualExtendedSipProvider, ((AddressResolverKeepAlive) addressResolver).sip_provider);
    assertTrue(Timer.DEFAULT_DAEMON_MODE);
  }

  @Test
  void testConstructor16() {
    // Arrange and Act
    ExtendedSipProvider actualExtendedSipProvider = new ExtendedSipProvider("42 Main St", 8080,
        new String[]{"Protocols"}, "ALL-INTERFACES", 10L, 10L);

    // Assert
    assertNull(actualExtendedSipProvider.getBindingIpAddress());
    AddressResolver addressResolver = actualExtendedSipProvider.address_resolver;
    assertTrue(addressResolver instanceof AddressResolverKeepAlive);
    assertTrue(actualExtendedSipProvider.isRportSet());
    Hashtable listeners = actualExtendedSipProvider.getListeners();
    assertTrue(listeners.isEmpty());
    assertEquals(8080, actualExtendedSipProvider.getPort());
    assertFalse(actualExtendedSipProvider.isForceRportSet());
    assertNull(actualExtendedSipProvider.getTelGateway());
    assertNull(actualExtendedSipProvider.getLogger());
    assertEquals(0, actualExtendedSipProvider.getTlsPort());
    assertEquals("Protocols", actualExtendedSipProvider.getDefaultTransport());
    assertEquals(Integer.SIZE, actualExtendedSipProvider.getNMaxConnections());
    assertEquals("42 Main St", actualExtendedSipProvider.getViaAddress());
    assertFalse(actualExtendedSipProvider.hasOutboundProxy());
    assertNull(((AddressResolverKeepAlive) addressResolver).logger);
    assertEquals(listeners, ((AddressResolverKeepAlive) addressResolver).binding_table);
    assertEquals(listeners, ((AddressResolverKeepAlive) addressResolver).keepalive_daemons);
    assertEquals(10L, ((AddressResolverKeepAlive) addressResolver).keepalive_time);
    assertSame(actualExtendedSipProvider, ((AddressResolverKeepAlive) addressResolver).sip_provider);
    assertEquals(10L, ((AddressResolverKeepAlive) addressResolver).refresh_time);
    assertEquals(listeners, ((AddressResolverKeepAlive) addressResolver).time_table);
    assertEquals(5L, ((AddressResolverKeepAlive) addressResolver).expire_time);
    assertTrue(Timer.DEFAULT_DAEMON_MODE);
    Timer timer = ((AddressResolverKeepAlive) addressResolver).timer;
    assertTrue(timer.isRunning());
    assertEquals(10L, timer.getTime());
  }

  @Test
  void testConstructor17() {
    // Arrange and Act
    ExtendedSipProvider actualExtendedSipProvider = new ExtendedSipProvider("SipStack: mjsip 1.8", 8080,
        new String[]{"Protocols"}, "", 10L, 10L);

    // Assert
    AddressResolver addressResolver = actualExtendedSipProvider.address_resolver;
    assertTrue(addressResolver instanceof AddressResolverKeepAlive);
    Hashtable listeners = actualExtendedSipProvider.getListeners();
    assertTrue(listeners.isEmpty());
    assertEquals(8080, actualExtendedSipProvider.getPort());
    assertFalse(actualExtendedSipProvider.isAllInterfaces());
    assertFalse(actualExtendedSipProvider.isForceRportSet());
    assertNull(actualExtendedSipProvider.getTelGateway());
    assertTrue(actualExtendedSipProvider.isRportSet());
    assertNull(actualExtendedSipProvider.getLogger());
    assertEquals(0, actualExtendedSipProvider.getTlsPort());
    assertEquals("Protocols", actualExtendedSipProvider.getDefaultTransport());
    assertEquals(Integer.SIZE, actualExtendedSipProvider.getNMaxConnections());
    assertEquals("SipStack: mjsip 1.8", actualExtendedSipProvider.getViaAddress());
    assertFalse(actualExtendedSipProvider.hasOutboundProxy());
    assertEquals(listeners, ((AddressResolverKeepAlive) addressResolver).time_table);
    assertEquals("127.0.0.1", actualExtendedSipProvider.getBindingIpAddress().toString());
    assertEquals(listeners, ((AddressResolverKeepAlive) addressResolver).binding_table);
    assertNull(((AddressResolverKeepAlive) addressResolver).logger);
    assertEquals(10L, ((AddressResolverKeepAlive) addressResolver).refresh_time);
    assertEquals(5L, ((AddressResolverKeepAlive) addressResolver).expire_time);
    assertEquals(10L, ((AddressResolverKeepAlive) addressResolver).keepalive_time);
    assertEquals(listeners, ((AddressResolverKeepAlive) addressResolver).keepalive_daemons);
    assertSame(actualExtendedSipProvider, ((AddressResolverKeepAlive) addressResolver).sip_provider);
    assertTrue(Timer.DEFAULT_DAEMON_MODE);
  }

  @Test
  void testConstructor18() {
    // Arrange and Act
    ExtendedSipProvider actualExtendedSipProvider = new ExtendedSipProvider("SipStack: mjsip 1.8", 8080,
        new String[]{"Protocols"}, "ALL-INTERFACES", 10L, 10L);

    // Assert
    assertNull(actualExtendedSipProvider.getBindingIpAddress());
    AddressResolver addressResolver = actualExtendedSipProvider.address_resolver;
    assertTrue(addressResolver instanceof AddressResolverKeepAlive);
    assertTrue(actualExtendedSipProvider.isRportSet());
    Hashtable listeners = actualExtendedSipProvider.getListeners();
    assertTrue(listeners.isEmpty());
    assertEquals(8080, actualExtendedSipProvider.getPort());
    assertFalse(actualExtendedSipProvider.isForceRportSet());
    assertNull(actualExtendedSipProvider.getTelGateway());
    assertNull(actualExtendedSipProvider.getLogger());
    assertEquals(0, actualExtendedSipProvider.getTlsPort());
    assertEquals("Protocols", actualExtendedSipProvider.getDefaultTransport());
    assertEquals(Integer.SIZE, actualExtendedSipProvider.getNMaxConnections());
    assertEquals("SipStack: mjsip 1.8", actualExtendedSipProvider.getViaAddress());
    assertFalse(actualExtendedSipProvider.hasOutboundProxy());
    assertNull(((AddressResolverKeepAlive) addressResolver).logger);
    assertEquals(listeners, ((AddressResolverKeepAlive) addressResolver).binding_table);
    assertEquals(listeners, ((AddressResolverKeepAlive) addressResolver).keepalive_daemons);
    assertEquals(10L, ((AddressResolverKeepAlive) addressResolver).keepalive_time);
    assertSame(actualExtendedSipProvider, ((AddressResolverKeepAlive) addressResolver).sip_provider);
    assertEquals(10L, ((AddressResolverKeepAlive) addressResolver).refresh_time);
    assertEquals(listeners, ((AddressResolverKeepAlive) addressResolver).time_table);
    assertEquals(5L, ((AddressResolverKeepAlive) addressResolver).expire_time);
    assertTrue(Timer.DEFAULT_DAEMON_MODE);
    Timer timer = ((AddressResolverKeepAlive) addressResolver).timer;
    assertTrue(timer.isRunning());
    assertEquals(10L, timer.getTime());
  }

  @Test
  void testConstructor19() {
    // Arrange and Act
    ExtendedSipProvider actualExtendedSipProvider = new ExtendedSipProvider("File", 10L, 10L);

    // Assert
    assertNull(actualExtendedSipProvider.getBindingIpAddress());
    AddressResolver addressResolver = actualExtendedSipProvider.address_resolver;
    assertTrue(addressResolver instanceof AddressResolverKeepAlive);
    assertTrue(actualExtendedSipProvider.isRportSet());
    Hashtable listeners = actualExtendedSipProvider.getListeners();
    assertTrue(listeners.isEmpty());
    assertEquals(5060, actualExtendedSipProvider.getPort());
    assertFalse(actualExtendedSipProvider.isForceRportSet());
    assertNull(actualExtendedSipProvider.getTelGateway());
    assertNull(actualExtendedSipProvider.getLogger());
    assertEquals(0, actualExtendedSipProvider.getTlsPort());
    assertEquals("udp", actualExtendedSipProvider.getDefaultTransport());
    assertEquals(Integer.SIZE, actualExtendedSipProvider.getNMaxConnections());

    assertFalse(actualExtendedSipProvider.hasOutboundProxy());
    assertNull(((AddressResolverKeepAlive) addressResolver).logger);
    assertEquals(listeners, ((AddressResolverKeepAlive) addressResolver).binding_table);
    assertEquals(listeners, ((AddressResolverKeepAlive) addressResolver).keepalive_daemons);
    assertEquals(10L, ((AddressResolverKeepAlive) addressResolver).keepalive_time);
    assertSame(actualExtendedSipProvider, ((AddressResolverKeepAlive) addressResolver).sip_provider);
    assertEquals(10L, ((AddressResolverKeepAlive) addressResolver).refresh_time);
    assertEquals(listeners, ((AddressResolverKeepAlive) addressResolver).time_table);
    assertEquals(5L, ((AddressResolverKeepAlive) addressResolver).expire_time);
    assertTrue(Timer.DEFAULT_DAEMON_MODE);
    Timer timer = ((AddressResolverKeepAlive) addressResolver).timer;
    assertTrue(timer.isRunning());
    assertEquals(10L, timer.getTime());
  }

  @Test
  void testConstructor20() {
    // Arrange and Act
    ExtendedSipProvider actualExtendedSipProvider = new ExtendedSipProvider("File", -1L, 10L);

    // Assert
    assertNull(actualExtendedSipProvider.getBindingIpAddress());
    AddressResolver addressResolver = actualExtendedSipProvider.address_resolver;
    assertTrue(addressResolver instanceof AddressResolverKeepAlive);
    assertTrue(actualExtendedSipProvider.isRportSet());
    Hashtable listeners = actualExtendedSipProvider.getListeners();
    assertTrue(listeners.isEmpty());
    assertEquals(5060, actualExtendedSipProvider.getPort());
    assertFalse(actualExtendedSipProvider.isForceRportSet());
    assertNull(actualExtendedSipProvider.getTelGateway());
    assertNull(actualExtendedSipProvider.getLogger());
    assertEquals(0, actualExtendedSipProvider.getTlsPort());
    assertEquals("udp", actualExtendedSipProvider.getDefaultTransport());
    assertEquals(Integer.SIZE, actualExtendedSipProvider.getNMaxConnections());

    assertFalse(actualExtendedSipProvider.hasOutboundProxy());
    assertNull(((AddressResolverKeepAlive) addressResolver).logger);
    assertEquals(listeners, ((AddressResolverKeepAlive) addressResolver).binding_table);
    assertEquals(listeners, ((AddressResolverKeepAlive) addressResolver).keepalive_daemons);
    assertEquals(10L, ((AddressResolverKeepAlive) addressResolver).keepalive_time);
    assertSame(actualExtendedSipProvider, ((AddressResolverKeepAlive) addressResolver).sip_provider);
    assertEquals(-1L, ((AddressResolverKeepAlive) addressResolver).refresh_time);
    assertEquals(listeners, ((AddressResolverKeepAlive) addressResolver).time_table);
    assertEquals(0L, ((AddressResolverKeepAlive) addressResolver).expire_time);
    assertTrue(Timer.DEFAULT_DAEMON_MODE);
    Timer timer = ((AddressResolverKeepAlive) addressResolver).timer;
    assertFalse(timer.isRunning());
    assertEquals(-1L, timer.getTime());
  }

  @Test
  void testConstructor21() {
    // Arrange and Act
    ExtendedSipProvider actualExtendedSipProvider = new ExtendedSipProvider("File", 10L, 0L);

    // Assert
    assertNull(actualExtendedSipProvider.getBindingIpAddress());
    Hashtable listeners = actualExtendedSipProvider.getListeners();
    assertTrue(listeners.isEmpty());
    assertEquals(5060, actualExtendedSipProvider.getPort());
    assertFalse(actualExtendedSipProvider.isForceRportSet());
    assertNull(actualExtendedSipProvider.getTelGateway());
    assertTrue(actualExtendedSipProvider.isRportSet());
    assertNull(actualExtendedSipProvider.getLogger());
    assertEquals(0, actualExtendedSipProvider.getTlsPort());
    assertEquals("udp", actualExtendedSipProvider.getDefaultTransport());
    assertEquals(Integer.SIZE, actualExtendedSipProvider.getNMaxConnections());

    assertFalse(actualExtendedSipProvider.hasOutboundProxy());
    AddressResolver addressResolver = actualExtendedSipProvider.address_resolver;
    assertEquals(0, addressResolver.size());
    assertNull(addressResolver.logger);
    assertEquals(10L, addressResolver.refresh_time);
    assertEquals(5L, addressResolver.expire_time);
    assertEquals(listeners, addressResolver.time_table);
    Timer timer = addressResolver.timer;
    assertTrue(timer.isRunning());
    assertTrue(Timer.DEFAULT_DAEMON_MODE);
    assertEquals(10L, timer.getTime());
  }

  @Test
  void testConstructor22() {
    // Arrange and Act
    ExtendedSipProvider actualExtendedSipProvider = new ExtendedSipProvider("File", Long.MIN_VALUE, 0L);

    // Assert
    assertNull(actualExtendedSipProvider.getBindingIpAddress());
    Hashtable listeners = actualExtendedSipProvider.getListeners();
    assertTrue(listeners.isEmpty());
    assertEquals(5060, actualExtendedSipProvider.getPort());
    assertFalse(actualExtendedSipProvider.isForceRportSet());
    assertNull(actualExtendedSipProvider.getTelGateway());
    assertTrue(actualExtendedSipProvider.isRportSet());
    assertNull(actualExtendedSipProvider.getLogger());
    assertEquals(0, actualExtendedSipProvider.getTlsPort());
    assertEquals("udp", actualExtendedSipProvider.getDefaultTransport());
    assertEquals(Integer.SIZE, actualExtendedSipProvider.getNMaxConnections());

    assertFalse(actualExtendedSipProvider.hasOutboundProxy());
    AddressResolver addressResolver = actualExtendedSipProvider.address_resolver;
    assertEquals(0, addressResolver.size());
    assertNull(addressResolver.logger);
    assertEquals(Long.MIN_VALUE, addressResolver.refresh_time);
    assertEquals(-4611686018427387904L, addressResolver.expire_time);
    assertEquals(listeners, addressResolver.time_table);
    Timer timer = addressResolver.timer;
    assertFalse(timer.isRunning());
    assertTrue(Timer.DEFAULT_DAEMON_MODE);
    assertEquals(Long.MIN_VALUE, timer.getTime());
  }

  @Test
  void testOnReceivedMessage() {
    // Arrange
    SipProviderExceptionListener sipProviderExceptionListener = mock(SipProviderExceptionListener.class);
    doNothing().when(sipProviderExceptionListener).onMessageException((SipMessage) any(), (Exception) any());

    ExtendedSipProvider extendedSipProvider = new ExtendedSipProvider("File", 10L, 10L);
    extendedSipProvider.addExceptionListener(sipProviderExceptionListener);

    // Act
    extendedSipProvider.onReceivedMessage(null, new SipMessage("received"));

    // Assert
    verify(sipProviderExceptionListener).onMessageException((SipMessage) any(), (Exception) any());
  }

  @Test
  void testOnReceivedMessage2() {
    // Arrange
    SipProviderExceptionListener sipProviderExceptionListener = mock(SipProviderExceptionListener.class);
    doNothing().when(sipProviderExceptionListener).onMessageException((SipMessage) any(), (Exception) any());

    ExtendedSipProvider extendedSipProvider = new ExtendedSipProvider("File", 10L, 10L);
    extendedSipProvider.addExceptionListener(sipProviderExceptionListener);

    SipMessage sipMessage = new SipMessage("received");
    sipMessage.addViaHeader(new ViaHeader("42"));

    // Act
    extendedSipProvider.onReceivedMessage(null, sipMessage);

    // Assert
    verify(sipProviderExceptionListener).onMessageException((SipMessage) any(), (Exception) any());
  }

  @Test
  void testSendMessage() {
    // Arrange
    ExtendedSipProvider extendedSipProvider = new ExtendedSipProvider("File", 10L, 10L);

    // Act and Assert
    assertNull(
        extendedSipProvider.sendMessage(new SipMessage("Str"), "alice.liddell@example.org", "42 Main St", 8080, 1));
  }

  @Test
  void testSendMessage2() {
    // Arrange
    ExtendedSipProvider extendedSipProvider = new ExtendedSipProvider("File", 10L, 10L);

    // Act and Assert
    assertNull(extendedSipProvider.sendMessage(new SipMessage("NOT a SIP message\r\n"), "alice.liddell@example.org",
        "42 Main St", 8080, 1));
  }

  @Test
  void testSendMessage3() {
    // Arrange
    ExtendedSipProvider extendedSipProvider = new ExtendedSipProvider("File", 10L, 10L);

    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertNull(extendedSipProvider.sendMessage(sipMessage, "alice.liddell@example.org", "42 Main St", 8080, 1));
  }

  @Test
  void testSendMessage4() throws UnsupportedEncodingException {
    // Arrange
    ExtendedSipProvider extendedSipProvider = new ExtendedSipProvider("File", 10L, 10L);
    StatusLine status_line = new StatusLine(1, "foo");

    Vector headers = new Vector(1);

    SipMessage sipMessage = new SipMessage(status_line, headers, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));
    sipMessage.addViaHeader(new ViaHeader("42"));

    // Act and Assert
    assertNull(extendedSipProvider.sendMessage(sipMessage, "alice.liddell@example.org", "42 Main St", 8080, 1));
  }
}

