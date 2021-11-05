package org.mjsip.sip.provider;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.io.UnsupportedEncodingException;
import java.util.Hashtable;
import java.util.Vector;
import org.junit.jupiter.api.Test;
import org.mjsip.sip.address.GenericURI;
import org.mjsip.sip.address.NameAddress;
import org.mjsip.sip.address.SipURI;
import org.mjsip.sip.address.UnexpectedUriSchemeException;
import org.mjsip.sip.header.MultipleHeader;
import org.mjsip.sip.header.RequestLine;
import org.mjsip.sip.header.StatusLine;
import org.mjsip.sip.header.ViaHeader;
import org.mjsip.sip.message.SipMessage;
import org.zoolu.net.SocketAddress;
import org.zoolu.net.UdpSocket;

class SipProviderDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    SipProvider actualSipProvider = new SipProvider("File");
    actualSipProvider.setDefaultTransport("alice.liddell@example.org");
    actualSipProvider.setForceRport(true);
    actualSipProvider.setNMaxConnections(1);
    SipURI sipURI = new SipURI("Uri");
    actualSipProvider.setOutboundProxy(sipURI);
    actualSipProvider.setRport(true);
    SipURI sipURI1 = new SipURI("Uri");
    actualSipProvider.setTelGateway(sipURI1);

    // Assert
    assertNull(actualSipProvider.getBindingIpAddress());
    assertEquals("alice.liddell@example.org", actualSipProvider.getDefaultTransport());
    Hashtable expectedListeners = actualSipProvider.sip_listeners;
    Hashtable listeners = actualSipProvider.getListeners();
    assertSame(expectedListeners, listeners);
    Hashtable hashtable = actualSipProvider.sip_transports;
    assertEquals(hashtable, listeners);
    assertNull(actualSipProvider.getLogger());
    assertEquals(1, actualSipProvider.getNMaxConnections());
    assertSame(sipURI, actualSipProvider.getOutboundProxy());
    assertEquals(5060, actualSipProvider.getPort());
    assertSame(sipURI1, actualSipProvider.getTelGateway());

    assertTrue(actualSipProvider.isForceRportSet());
    assertTrue(actualSipProvider.isRportSet());
    assertTrue(actualSipProvider.exception_listeners.isEmpty());
    assertTrue(actualSipProvider.promiscuous_listeners.isEmpty());
    assertTrue(hashtable.isEmpty());
  }

  @Test
  void testConstructor2() {
    // Arrange and Act
    SipProvider actualSipProvider = new SipProvider("File");

    // Assert
    assertNull(actualSipProvider.getBindingIpAddress());
    assertNull(actualSipProvider.trusted_certs);
    assertEquals("cert", actualSipProvider.trust_folder);
    assertFalse(actualSipProvider.trust_all);
    assertEquals(2, actualSipProvider.transport_protocols.length);
    assertNull(actualSipProvider.transport_ports);
    Hashtable hashtable = actualSipProvider.sip_transports;
    assertEquals(actualSipProvider.sip_listeners, hashtable);
    Vector vector = actualSipProvider.promiscuous_listeners;
    assertEquals(actualSipProvider.exception_listeners, vector);
    assertNull(actualSipProvider.message_logger);
    assertFalse(actualSipProvider.log_all_packets);
    assertEquals("cert/ssl.key", actualSipProvider.key_file);
    assertEquals(vector, actualSipProvider.exception_listeners);
    assertEquals("cert/ssl.crt", actualSipProvider.cert_file);
    assertTrue(actualSipProvider.isRportSet());
    assertFalse(actualSipProvider.isForceRportSet());
    assertFalse(actualSipProvider.hasTelGateway());
    assertFalse(actualSipProvider.hasOutboundProxy());

    assertEquals(5060, actualSipProvider.getPort());
    assertEquals(Integer.SIZE, actualSipProvider.getNMaxConnections());
    assertNull(actualSipProvider.getLogger());
    assertEquals(hashtable, actualSipProvider.getListeners());
    assertEquals(SipProvider.PROTO_UDP, actualSipProvider.getDefaultTransport());
  }

  @Test
  void testConstructor3() {
    // Arrange and Act
    SipProvider actualSipProvider = new SipProvider(null);

    // Assert
    assertNull(actualSipProvider.getBindingIpAddress());
    assertNull(actualSipProvider.trusted_certs);
    assertEquals("cert", actualSipProvider.trust_folder);
    assertFalse(actualSipProvider.trust_all);
    assertEquals(2, actualSipProvider.transport_protocols.length);
    assertNull(actualSipProvider.transport_ports);
    Hashtable hashtable = actualSipProvider.sip_transports;
    assertEquals(actualSipProvider.sip_listeners, hashtable);
    Vector vector = actualSipProvider.promiscuous_listeners;
    assertEquals(actualSipProvider.exception_listeners, vector);
    assertNull(actualSipProvider.message_logger);
    assertFalse(actualSipProvider.log_all_packets);
    assertEquals("cert/ssl.key", actualSipProvider.key_file);
    assertEquals(vector, actualSipProvider.exception_listeners);
    assertEquals("cert/ssl.crt", actualSipProvider.cert_file);
    assertTrue(actualSipProvider.isRportSet());
    assertFalse(actualSipProvider.isForceRportSet());
    assertFalse(actualSipProvider.hasTelGateway());
    assertFalse(actualSipProvider.hasOutboundProxy());

    assertEquals(5060, actualSipProvider.getPort());
    assertEquals(Integer.SIZE, actualSipProvider.getNMaxConnections());
    assertNull(actualSipProvider.getLogger());
    assertEquals(hashtable, actualSipProvider.getListeners());
    assertEquals(SipProvider.PROTO_UDP, actualSipProvider.getDefaultTransport());
  }

  @Test
  void testConstructor4() {
    // Arrange and Act
    SipProvider actualSipProvider = new SipProvider(SipProvider.PROTO_DTLS);

    // Assert
    assertNull(actualSipProvider.getBindingIpAddress());
    assertNull(actualSipProvider.trusted_certs);
    assertEquals("cert", actualSipProvider.trust_folder);
    assertFalse(actualSipProvider.trust_all);
    assertEquals(2, actualSipProvider.transport_protocols.length);
    assertNull(actualSipProvider.transport_ports);
    Hashtable hashtable = actualSipProvider.sip_transports;
    assertEquals(actualSipProvider.sip_listeners, hashtable);
    Vector vector = actualSipProvider.promiscuous_listeners;
    assertEquals(actualSipProvider.exception_listeners, vector);
    assertNull(actualSipProvider.message_logger);
    assertFalse(actualSipProvider.log_all_packets);
    assertEquals("cert/ssl.key", actualSipProvider.key_file);
    assertEquals(vector, actualSipProvider.exception_listeners);
    assertEquals("cert/ssl.crt", actualSipProvider.cert_file);
    assertTrue(actualSipProvider.isRportSet());
    assertFalse(actualSipProvider.isForceRportSet());
    assertFalse(actualSipProvider.hasTelGateway());
    assertFalse(actualSipProvider.hasOutboundProxy());

    assertEquals(5060, actualSipProvider.getPort());
    assertEquals(Integer.SIZE, actualSipProvider.getNMaxConnections());
    assertNull(actualSipProvider.getLogger());
    assertEquals(hashtable, actualSipProvider.getListeners());
    assertEquals(SipProvider.PROTO_UDP, actualSipProvider.getDefaultTransport());
  }

  @Test
  void testConstructor5() {
    // Arrange and Act
    SipProvider actualSipProvider = new SipProvider("host_addr");

    // Assert
    assertNull(actualSipProvider.getBindingIpAddress());
    assertNull(actualSipProvider.trusted_certs);
    assertEquals("cert", actualSipProvider.trust_folder);
    assertFalse(actualSipProvider.trust_all);
    assertEquals(2, actualSipProvider.transport_protocols.length);
    assertNull(actualSipProvider.transport_ports);
    Hashtable hashtable = actualSipProvider.sip_transports;
    assertEquals(actualSipProvider.sip_listeners, hashtable);
    Vector vector = actualSipProvider.promiscuous_listeners;
    assertEquals(actualSipProvider.exception_listeners, vector);
    assertNull(actualSipProvider.message_logger);
    assertFalse(actualSipProvider.log_all_packets);
    assertEquals("cert/ssl.key", actualSipProvider.key_file);
    assertEquals(vector, actualSipProvider.exception_listeners);
    assertEquals("cert/ssl.crt", actualSipProvider.cert_file);
    assertTrue(actualSipProvider.isRportSet());
    assertFalse(actualSipProvider.isForceRportSet());
    assertFalse(actualSipProvider.hasTelGateway());
    assertFalse(actualSipProvider.hasOutboundProxy());

    assertEquals(5060, actualSipProvider.getPort());
    assertEquals(Integer.SIZE, actualSipProvider.getNMaxConnections());
    assertNull(actualSipProvider.getLogger());
    assertEquals(hashtable, actualSipProvider.getListeners());
    assertEquals(SipProvider.PROTO_UDP, actualSipProvider.getDefaultTransport());
  }

  @Test
  void testConstructor6() {
    // Arrange and Act
    SipProvider actualSipProvider = new SipProvider("42 Main St", 8080);

    // Assert
    assertNull(actualSipProvider.getBindingIpAddress());
    assertNull(actualSipProvider.trusted_certs);
    assertEquals("cert", actualSipProvider.trust_folder);
    assertFalse(actualSipProvider.trust_all);
    assertEquals(2, actualSipProvider.transport_protocols.length);
    assertNull(actualSipProvider.transport_ports);
    Hashtable hashtable = actualSipProvider.sip_transports;
    assertEquals(actualSipProvider.sip_listeners, hashtable);
    Vector vector = actualSipProvider.promiscuous_listeners;
    assertEquals(actualSipProvider.exception_listeners, vector);
    assertNull(actualSipProvider.message_logger);
    assertFalse(actualSipProvider.log_all_packets);
    assertEquals("cert/ssl.key", actualSipProvider.key_file);
    assertEquals(vector, actualSipProvider.exception_listeners);
    assertEquals("cert/ssl.crt", actualSipProvider.cert_file);
    assertTrue(actualSipProvider.isRportSet());
    assertFalse(actualSipProvider.isForceRportSet());
    assertFalse(actualSipProvider.hasTelGateway());
    assertFalse(actualSipProvider.hasOutboundProxy());
    assertEquals("42 Main St", actualSipProvider.getViaAddress());
    assertEquals(8080, actualSipProvider.getPort());
    assertEquals(Integer.SIZE, actualSipProvider.getNMaxConnections());
    assertNull(actualSipProvider.getLogger());
    assertEquals(hashtable, actualSipProvider.getListeners());
    assertEquals(SipProvider.PROTO_UDP, actualSipProvider.getDefaultTransport());
  }

  @Test
  void testConstructor7() {
    // Arrange and Act
    SipProvider actualSipProvider = new SipProvider(SipProvider.AUTO_CONFIGURATION, 8080);

    // Assert
    assertNull(actualSipProvider.getBindingIpAddress());
    assertNull(actualSipProvider.trusted_certs);
    assertEquals("cert", actualSipProvider.trust_folder);
    assertFalse(actualSipProvider.trust_all);
    assertEquals(2, actualSipProvider.transport_protocols.length);
    assertNull(actualSipProvider.transport_ports);
    Hashtable hashtable = actualSipProvider.sip_transports;
    assertEquals(actualSipProvider.sip_listeners, hashtable);
    Vector vector = actualSipProvider.promiscuous_listeners;
    assertEquals(actualSipProvider.exception_listeners, vector);
    assertNull(actualSipProvider.message_logger);
    assertFalse(actualSipProvider.log_all_packets);
    assertEquals("cert/ssl.key", actualSipProvider.key_file);
    assertEquals(vector, actualSipProvider.exception_listeners);
    assertEquals("cert/ssl.crt", actualSipProvider.cert_file);
    assertTrue(actualSipProvider.isRportSet());
    assertFalse(actualSipProvider.isForceRportSet());
    assertFalse(actualSipProvider.hasTelGateway());
    assertFalse(actualSipProvider.hasOutboundProxy());

    assertEquals(8080, actualSipProvider.getPort());
    assertEquals(Integer.SIZE, actualSipProvider.getNMaxConnections());
    assertNull(actualSipProvider.getLogger());
    assertEquals(hashtable, actualSipProvider.getListeners());
    assertEquals(SipProvider.PROTO_UDP, actualSipProvider.getDefaultTransport());
  }

  @Test
  void testConstructor8() {
    // Arrange and Act
    SipProvider actualSipProvider = new SipProvider("", 8080);

    // Assert
    assertNull(actualSipProvider.getBindingIpAddress());
    assertNull(actualSipProvider.trusted_certs);
    assertEquals("cert", actualSipProvider.trust_folder);
    assertFalse(actualSipProvider.trust_all);
    assertEquals(2, actualSipProvider.transport_protocols.length);
    assertNull(actualSipProvider.transport_ports);
    Hashtable hashtable = actualSipProvider.sip_transports;
    assertEquals(actualSipProvider.sip_listeners, hashtable);
    Vector vector = actualSipProvider.promiscuous_listeners;
    assertEquals(actualSipProvider.exception_listeners, vector);
    assertNull(actualSipProvider.message_logger);
    assertFalse(actualSipProvider.log_all_packets);
    assertEquals("cert/ssl.key", actualSipProvider.key_file);
    assertEquals(vector, actualSipProvider.exception_listeners);
    assertEquals("cert/ssl.crt", actualSipProvider.cert_file);
    assertTrue(actualSipProvider.isRportSet());
    assertFalse(actualSipProvider.isForceRportSet());
    assertFalse(actualSipProvider.hasTelGateway());
    assertFalse(actualSipProvider.hasOutboundProxy());
    assertEquals("", actualSipProvider.getViaAddress());
    assertEquals(8080, actualSipProvider.getPort());
    assertEquals(Integer.SIZE, actualSipProvider.getNMaxConnections());
    assertNull(actualSipProvider.getLogger());
    assertEquals(hashtable, actualSipProvider.getListeners());
    assertEquals(SipProvider.PROTO_UDP, actualSipProvider.getDefaultTransport());
  }

  @Test
  void testConstructor9() {
    // Arrange and Act
    SipProvider actualSipProvider = new SipProvider(null, 8080);

    // Assert
    assertNull(actualSipProvider.getBindingIpAddress());
    assertNull(actualSipProvider.trusted_certs);
    assertEquals("cert", actualSipProvider.trust_folder);
    assertFalse(actualSipProvider.trust_all);
    assertEquals(2, actualSipProvider.transport_protocols.length);
    assertNull(actualSipProvider.transport_ports);
    Hashtable hashtable = actualSipProvider.sip_transports;
    assertEquals(actualSipProvider.sip_listeners, hashtable);
    Vector vector = actualSipProvider.promiscuous_listeners;
    assertEquals(actualSipProvider.exception_listeners, vector);
    assertNull(actualSipProvider.message_logger);
    assertFalse(actualSipProvider.log_all_packets);
    assertEquals("cert/ssl.key", actualSipProvider.key_file);
    assertEquals(vector, actualSipProvider.exception_listeners);
    assertEquals("cert/ssl.crt", actualSipProvider.cert_file);
    assertTrue(actualSipProvider.isRportSet());
    assertFalse(actualSipProvider.isForceRportSet());
    assertFalse(actualSipProvider.hasTelGateway());
    assertFalse(actualSipProvider.hasOutboundProxy());

    assertEquals(8080, actualSipProvider.getPort());
    assertEquals(Integer.SIZE, actualSipProvider.getNMaxConnections());
    assertNull(actualSipProvider.getLogger());
    assertEquals(hashtable, actualSipProvider.getListeners());
    assertEquals(SipProvider.PROTO_UDP, actualSipProvider.getDefaultTransport());
  }

  @Test
  void testConstructor10() {
    // Arrange and Act
    SipProvider actualSipProvider = new SipProvider("42 Main St", 0);

    // Assert
    assertNull(actualSipProvider.getBindingIpAddress());
    assertNull(actualSipProvider.trusted_certs);
    assertEquals("cert", actualSipProvider.trust_folder);
    assertFalse(actualSipProvider.trust_all);
    assertEquals(2, actualSipProvider.transport_protocols.length);
    assertNull(actualSipProvider.transport_ports);
    Hashtable hashtable = actualSipProvider.sip_transports;
    assertEquals(actualSipProvider.sip_listeners, hashtable);
    Vector vector = actualSipProvider.promiscuous_listeners;
    assertEquals(actualSipProvider.exception_listeners, vector);
    assertNull(actualSipProvider.message_logger);
    assertFalse(actualSipProvider.log_all_packets);
    assertEquals("cert/ssl.key", actualSipProvider.key_file);
    assertEquals(vector, actualSipProvider.exception_listeners);
    assertEquals("cert/ssl.crt", actualSipProvider.cert_file);
    assertTrue(actualSipProvider.isRportSet());
    assertFalse(actualSipProvider.isForceRportSet());
    assertFalse(actualSipProvider.hasTelGateway());
    assertFalse(actualSipProvider.hasOutboundProxy());
    assertEquals("42 Main St", actualSipProvider.getViaAddress());
    assertEquals(5060, actualSipProvider.getPort());
    assertEquals(Integer.SIZE, actualSipProvider.getNMaxConnections());
    assertNull(actualSipProvider.getLogger());
    assertEquals(hashtable, actualSipProvider.getListeners());
    assertEquals(SipProvider.PROTO_UDP, actualSipProvider.getDefaultTransport());
  }

  @Test
  void testConstructor11() {
    // Arrange and Act
    SipProvider actualSipProvider = new SipProvider("42 Main St", 8080, new String[]{"Transport protocols"});

    // Assert
    assertNull(actualSipProvider.getBindingIpAddress());
    assertNull(actualSipProvider.trusted_certs);
    assertEquals("cert", actualSipProvider.trust_folder);
    assertFalse(actualSipProvider.trust_all);
    assertEquals(1, actualSipProvider.transport_protocols.length);
    assertNull(actualSipProvider.transport_ports);
    Hashtable hashtable = actualSipProvider.sip_transports;
    assertEquals(actualSipProvider.sip_listeners, hashtable);
    Vector vector = actualSipProvider.promiscuous_listeners;
    assertEquals(actualSipProvider.exception_listeners, vector);
    assertNull(actualSipProvider.message_logger);
    assertFalse(actualSipProvider.log_all_packets);
    assertEquals("cert/ssl.key", actualSipProvider.key_file);
    assertEquals(vector, actualSipProvider.exception_listeners);
    assertEquals("cert/ssl.crt", actualSipProvider.cert_file);
    assertTrue(actualSipProvider.isRportSet());
    assertFalse(actualSipProvider.isForceRportSet());
    assertFalse(actualSipProvider.hasTelGateway());
    assertFalse(actualSipProvider.hasOutboundProxy());
    assertEquals("42 Main St", actualSipProvider.getViaAddress());
    assertEquals(8080, actualSipProvider.getPort());
    assertEquals(Integer.SIZE, actualSipProvider.getNMaxConnections());
    assertNull(actualSipProvider.getLogger());
    assertEquals(hashtable, actualSipProvider.getListeners());
    assertEquals("Transport protocols", actualSipProvider.getDefaultTransport());
  }

  @Test
  void testConstructor12() {
    // Arrange and Act
    SipProvider actualSipProvider = new SipProvider(SipProvider.AUTO_CONFIGURATION, 8080,
        new String[]{"Transport protocols"});

    // Assert
    assertNull(actualSipProvider.getBindingIpAddress());
    assertNull(actualSipProvider.trusted_certs);
    assertEquals("cert", actualSipProvider.trust_folder);
    assertFalse(actualSipProvider.trust_all);
    assertEquals(1, actualSipProvider.transport_protocols.length);
    assertNull(actualSipProvider.transport_ports);
    Hashtable hashtable = actualSipProvider.sip_transports;
    assertEquals(actualSipProvider.sip_listeners, hashtable);
    Vector vector = actualSipProvider.promiscuous_listeners;
    assertEquals(actualSipProvider.exception_listeners, vector);
    assertNull(actualSipProvider.message_logger);
    assertFalse(actualSipProvider.log_all_packets);
    assertEquals("cert/ssl.key", actualSipProvider.key_file);
    assertEquals(vector, actualSipProvider.exception_listeners);
    assertEquals("cert/ssl.crt", actualSipProvider.cert_file);
    assertTrue(actualSipProvider.isRportSet());
    assertFalse(actualSipProvider.isForceRportSet());
    assertFalse(actualSipProvider.hasTelGateway());
    assertFalse(actualSipProvider.hasOutboundProxy());

    assertEquals(8080, actualSipProvider.getPort());
    assertEquals(Integer.SIZE, actualSipProvider.getNMaxConnections());
    assertNull(actualSipProvider.getLogger());
    assertEquals(hashtable, actualSipProvider.getListeners());
    assertEquals("Transport protocols", actualSipProvider.getDefaultTransport());
  }

  @Test
  void testConstructor13() {
    // Arrange and Act
    SipProvider actualSipProvider = new SipProvider(null, 8080, new String[]{"Transport protocols"});

    // Assert
    assertNull(actualSipProvider.getBindingIpAddress());
    assertNull(actualSipProvider.trusted_certs);
    assertEquals("cert", actualSipProvider.trust_folder);
    assertFalse(actualSipProvider.trust_all);
    assertEquals(1, actualSipProvider.transport_protocols.length);
    assertNull(actualSipProvider.transport_ports);
    Hashtable hashtable = actualSipProvider.sip_transports;
    assertEquals(actualSipProvider.sip_listeners, hashtable);
    Vector vector = actualSipProvider.promiscuous_listeners;
    assertEquals(actualSipProvider.exception_listeners, vector);
    assertNull(actualSipProvider.message_logger);
    assertFalse(actualSipProvider.log_all_packets);
    assertEquals("cert/ssl.key", actualSipProvider.key_file);
    assertEquals(vector, actualSipProvider.exception_listeners);
    assertEquals("cert/ssl.crt", actualSipProvider.cert_file);
    assertTrue(actualSipProvider.isRportSet());
    assertFalse(actualSipProvider.isForceRportSet());
    assertFalse(actualSipProvider.hasTelGateway());
    assertFalse(actualSipProvider.hasOutboundProxy());

    assertEquals(8080, actualSipProvider.getPort());
    assertEquals(Integer.SIZE, actualSipProvider.getNMaxConnections());
    assertNull(actualSipProvider.getLogger());
    assertEquals(hashtable, actualSipProvider.getListeners());
    assertEquals("Transport protocols", actualSipProvider.getDefaultTransport());
  }

  @Test
  void testConstructor14() {
    // Arrange and Act
    SipProvider actualSipProvider = new SipProvider("42 Main St", 0, new String[]{"Transport protocols"});

    // Assert
    assertNull(actualSipProvider.getBindingIpAddress());
    assertNull(actualSipProvider.trusted_certs);
    assertEquals("cert", actualSipProvider.trust_folder);
    assertFalse(actualSipProvider.trust_all);
    assertEquals(1, actualSipProvider.transport_protocols.length);
    assertNull(actualSipProvider.transport_ports);
    Hashtable hashtable = actualSipProvider.sip_transports;
    assertEquals(actualSipProvider.sip_listeners, hashtable);
    Vector vector = actualSipProvider.promiscuous_listeners;
    assertEquals(actualSipProvider.exception_listeners, vector);
    assertNull(actualSipProvider.message_logger);
    assertFalse(actualSipProvider.log_all_packets);
    assertEquals("cert/ssl.key", actualSipProvider.key_file);
    assertEquals(vector, actualSipProvider.exception_listeners);
    assertEquals("cert/ssl.crt", actualSipProvider.cert_file);
    assertTrue(actualSipProvider.isRportSet());
    assertFalse(actualSipProvider.isForceRportSet());
    assertFalse(actualSipProvider.hasTelGateway());
    assertFalse(actualSipProvider.hasOutboundProxy());
    assertEquals("42 Main St", actualSipProvider.getViaAddress());
    assertEquals(5060, actualSipProvider.getPort());
    assertEquals(Integer.SIZE, actualSipProvider.getNMaxConnections());
    assertNull(actualSipProvider.getLogger());
    assertEquals(hashtable, actualSipProvider.getListeners());
    assertEquals("Transport protocols", actualSipProvider.getDefaultTransport());
  }

  @Test
  void testConstructor15() {
    // Arrange and Act
    SipProvider actualSipProvider = new SipProvider("42 Main St", 8080, new String[]{SipProvider.PROTO_UDP});

    // Assert
    assertNull(actualSipProvider.getBindingIpAddress());
    assertNull(actualSipProvider.trusted_certs);
    assertEquals("cert", actualSipProvider.trust_folder);
    assertFalse(actualSipProvider.trust_all);
    assertEquals(1, actualSipProvider.transport_protocols.length);
    assertNull(actualSipProvider.transport_ports);
    Hashtable hashtable = actualSipProvider.sip_transports;
    assertEquals(actualSipProvider.sip_listeners, hashtable);
    Vector vector = actualSipProvider.promiscuous_listeners;
    assertEquals(actualSipProvider.exception_listeners, vector);
    assertNull(actualSipProvider.message_logger);
    assertFalse(actualSipProvider.log_all_packets);
    assertEquals("cert/ssl.key", actualSipProvider.key_file);
    assertEquals(vector, actualSipProvider.exception_listeners);
    assertEquals("cert/ssl.crt", actualSipProvider.cert_file);
    assertTrue(actualSipProvider.isRportSet());
    assertFalse(actualSipProvider.isForceRportSet());
    assertFalse(actualSipProvider.hasTelGateway());
    assertFalse(actualSipProvider.hasOutboundProxy());
    assertEquals("42 Main St", actualSipProvider.getViaAddress());
    assertEquals(8080, actualSipProvider.getPort());
    assertEquals(Integer.SIZE, actualSipProvider.getNMaxConnections());
    assertNull(actualSipProvider.getLogger());
    assertEquals(hashtable, actualSipProvider.getListeners());
    assertEquals(SipProvider.PROTO_UDP, actualSipProvider.getDefaultTransport());
  }

  @Test
  void testConstructor16() {
    // Arrange and Act
    SipProvider actualSipProvider = new SipProvider("42 Main St", 8080, new String[]{null});

    // Assert
    assertNull(actualSipProvider.getBindingIpAddress());
    assertNull(actualSipProvider.trusted_certs);
    assertEquals("cert", actualSipProvider.trust_folder);
    assertFalse(actualSipProvider.trust_all);
    assertEquals(1, actualSipProvider.transport_protocols.length);
    assertNull(actualSipProvider.transport_ports);
    assertNull(actualSipProvider.getDefaultTransport());
    Hashtable hashtable = actualSipProvider.sip_transports;
    assertEquals(actualSipProvider.sip_listeners, hashtable);
    Vector vector = actualSipProvider.promiscuous_listeners;
    assertEquals(actualSipProvider.exception_listeners, vector);
    assertNull(actualSipProvider.message_logger);
    assertFalse(actualSipProvider.log_all_packets);
    assertEquals("cert/ssl.key", actualSipProvider.key_file);
    assertEquals(vector, actualSipProvider.exception_listeners);
    assertEquals("cert/ssl.crt", actualSipProvider.cert_file);
    assertTrue(actualSipProvider.isRportSet());
    assertNull(actualSipProvider.getLogger());
    assertFalse(actualSipProvider.hasTelGateway());
    assertFalse(actualSipProvider.hasOutboundProxy());
    assertEquals("42 Main St", actualSipProvider.getViaAddress());
    assertEquals(8080, actualSipProvider.getPort());
    assertEquals(Integer.SIZE, actualSipProvider.getNMaxConnections());
    assertFalse(actualSipProvider.isForceRportSet());
    assertEquals(hashtable, actualSipProvider.getListeners());
  }

  @Test
  void testConstructor17() {
    // Arrange and Act
    SipProvider actualSipProvider = new SipProvider("42 Main St", 8080, new String[]{"transport protocols"});

    // Assert
    assertNull(actualSipProvider.getBindingIpAddress());
    assertNull(actualSipProvider.trusted_certs);
    assertEquals("cert", actualSipProvider.trust_folder);
    assertFalse(actualSipProvider.trust_all);
    assertEquals(1, actualSipProvider.transport_protocols.length);
    assertNull(actualSipProvider.transport_ports);
    Hashtable hashtable = actualSipProvider.sip_transports;
    assertEquals(actualSipProvider.sip_listeners, hashtable);
    Vector vector = actualSipProvider.promiscuous_listeners;
    assertEquals(actualSipProvider.exception_listeners, vector);
    assertNull(actualSipProvider.message_logger);
    assertFalse(actualSipProvider.log_all_packets);
    assertEquals("cert/ssl.key", actualSipProvider.key_file);
    assertEquals(vector, actualSipProvider.exception_listeners);
    assertEquals("cert/ssl.crt", actualSipProvider.cert_file);
    assertTrue(actualSipProvider.isRportSet());
    assertFalse(actualSipProvider.isForceRportSet());
    assertFalse(actualSipProvider.hasTelGateway());
    assertFalse(actualSipProvider.hasOutboundProxy());
    assertEquals("42 Main St", actualSipProvider.getViaAddress());
    assertEquals(8080, actualSipProvider.getPort());
    assertEquals(Integer.SIZE, actualSipProvider.getNMaxConnections());
    assertNull(actualSipProvider.getLogger());
    assertEquals(hashtable, actualSipProvider.getListeners());
    assertEquals("transport protocols", actualSipProvider.getDefaultTransport());
  }

  @Test
  void testConstructor18() {
    // Arrange and Act
    SipProvider actualSipProvider = new SipProvider("42 Main St", 8080, new String[]{SipProvider.PROTO_TCP});

    // Assert
    assertNull(actualSipProvider.getBindingIpAddress());
    assertNull(actualSipProvider.trusted_certs);
    assertEquals("cert", actualSipProvider.trust_folder);
    assertFalse(actualSipProvider.trust_all);
    assertEquals(1, actualSipProvider.transport_protocols.length);
    assertNull(actualSipProvider.transport_ports);
    Hashtable hashtable = actualSipProvider.sip_transports;
    assertEquals(actualSipProvider.sip_listeners, hashtable);
    Vector vector = actualSipProvider.promiscuous_listeners;
    assertEquals(actualSipProvider.exception_listeners, vector);
    assertNull(actualSipProvider.message_logger);
    assertFalse(actualSipProvider.log_all_packets);
    assertEquals("cert/ssl.key", actualSipProvider.key_file);
    assertEquals(vector, actualSipProvider.exception_listeners);
    assertEquals("cert/ssl.crt", actualSipProvider.cert_file);
    assertTrue(actualSipProvider.isRportSet());
    assertFalse(actualSipProvider.isForceRportSet());
    assertFalse(actualSipProvider.hasTelGateway());
    assertFalse(actualSipProvider.hasOutboundProxy());
    assertEquals("42 Main St", actualSipProvider.getViaAddress());
    assertEquals(8080, actualSipProvider.getPort());
    assertEquals(Integer.SIZE, actualSipProvider.getNMaxConnections());
    assertNull(actualSipProvider.getLogger());
    assertEquals(hashtable, actualSipProvider.getListeners());
    assertEquals(SipProvider.PROTO_TCP, actualSipProvider.getDefaultTransport());
  }

  @Test
  void testConstructor19() {
    // Arrange and Act
    SipProvider actualSipProvider = new SipProvider("42 Main St", 8080, new String[]{SipProvider.PROTO_TLS});

    // Assert
    assertNull(actualSipProvider.getBindingIpAddress());
    assertNull(actualSipProvider.trusted_certs);
    assertEquals("cert", actualSipProvider.trust_folder);
    assertFalse(actualSipProvider.trust_all);
    assertEquals(1, actualSipProvider.transport_protocols.length);
    assertNull(actualSipProvider.transport_ports);
    Hashtable hashtable = actualSipProvider.sip_transports;
    assertEquals(actualSipProvider.sip_listeners, hashtable);
    Vector vector = actualSipProvider.promiscuous_listeners;
    assertEquals(actualSipProvider.exception_listeners, vector);
    assertNull(actualSipProvider.message_logger);
    assertFalse(actualSipProvider.log_all_packets);
    assertEquals("cert/ssl.key", actualSipProvider.key_file);
    assertEquals(vector, actualSipProvider.exception_listeners);
    assertEquals("cert/ssl.crt", actualSipProvider.cert_file);
    assertTrue(actualSipProvider.isRportSet());
    assertFalse(actualSipProvider.isForceRportSet());
    assertFalse(actualSipProvider.hasTelGateway());
    assertFalse(actualSipProvider.hasOutboundProxy());
    assertEquals("42 Main St", actualSipProvider.getViaAddress());
    assertEquals(8080, actualSipProvider.getPort());
    assertEquals(Integer.SIZE, actualSipProvider.getNMaxConnections());
    assertNull(actualSipProvider.getLogger());
    assertEquals(hashtable, actualSipProvider.getListeners());
    assertEquals(SipProvider.PROTO_TLS, actualSipProvider.getDefaultTransport());
  }

  @Test
  void testConstructor20() {
    // Arrange and Act
    SipProvider actualSipProvider = new SipProvider("42 Main St", 8080, new String[]{SipProvider.PROTO_DTLS});

    // Assert
    assertNull(actualSipProvider.getBindingIpAddress());
    assertNull(actualSipProvider.trusted_certs);
    assertEquals("cert", actualSipProvider.trust_folder);
    assertFalse(actualSipProvider.trust_all);
    assertEquals(1, actualSipProvider.transport_protocols.length);
    assertNull(actualSipProvider.transport_ports);
    Hashtable hashtable = actualSipProvider.sip_transports;
    assertEquals(actualSipProvider.sip_listeners, hashtable);
    Vector vector = actualSipProvider.promiscuous_listeners;
    assertEquals(actualSipProvider.exception_listeners, vector);
    assertNull(actualSipProvider.message_logger);
    assertFalse(actualSipProvider.log_all_packets);
    assertEquals("cert/ssl.key", actualSipProvider.key_file);
    assertEquals(vector, actualSipProvider.exception_listeners);
    assertEquals("cert/ssl.crt", actualSipProvider.cert_file);
    assertTrue(actualSipProvider.isRportSet());
    assertFalse(actualSipProvider.isForceRportSet());
    assertFalse(actualSipProvider.hasTelGateway());
    assertFalse(actualSipProvider.hasOutboundProxy());
    assertEquals("42 Main St", actualSipProvider.getViaAddress());
    assertEquals(8080, actualSipProvider.getPort());
    assertEquals(Integer.SIZE, actualSipProvider.getNMaxConnections());
    assertNull(actualSipProvider.getLogger());
    assertEquals(hashtable, actualSipProvider.getListeners());
    assertEquals(SipProvider.PROTO_DTLS, actualSipProvider.getDefaultTransport());
  }

  @Test
  void testConstructor21() {
    // Arrange and Act
    SipProvider actualSipProvider = new SipProvider("42 Main St", 8080, new String[]{SipProvider.PROTO_SCTP});

    // Assert
    assertNull(actualSipProvider.getBindingIpAddress());
    assertNull(actualSipProvider.trusted_certs);
    assertEquals("cert", actualSipProvider.trust_folder);
    assertFalse(actualSipProvider.trust_all);
    assertEquals(1, actualSipProvider.transport_protocols.length);
    assertNull(actualSipProvider.transport_ports);
    Hashtable hashtable = actualSipProvider.sip_transports;
    assertEquals(actualSipProvider.sip_listeners, hashtable);
    Vector vector = actualSipProvider.promiscuous_listeners;
    assertEquals(actualSipProvider.exception_listeners, vector);
    assertNull(actualSipProvider.message_logger);
    assertFalse(actualSipProvider.log_all_packets);
    assertEquals("cert/ssl.key", actualSipProvider.key_file);
    assertEquals(vector, actualSipProvider.exception_listeners);
    assertEquals("cert/ssl.crt", actualSipProvider.cert_file);
    assertTrue(actualSipProvider.isRportSet());
    assertFalse(actualSipProvider.isForceRportSet());
    assertFalse(actualSipProvider.hasTelGateway());
    assertFalse(actualSipProvider.hasOutboundProxy());
    assertEquals("42 Main St", actualSipProvider.getViaAddress());
    assertEquals(8080, actualSipProvider.getPort());
    assertEquals(Integer.SIZE, actualSipProvider.getNMaxConnections());
    assertNull(actualSipProvider.getLogger());
    assertEquals(hashtable, actualSipProvider.getListeners());
    assertEquals(SipProvider.PROTO_SCTP, actualSipProvider.getDefaultTransport());
  }

  @Test
  void testConstructor22() {
    // Arrange and Act
    SipProvider actualSipProvider = new SipProvider("42 Main St", 8080, (String[]) null);

    // Assert
    assertNull(actualSipProvider.getBindingIpAddress());
    assertNull(actualSipProvider.trusted_certs);
    assertEquals("cert", actualSipProvider.trust_folder);
    assertFalse(actualSipProvider.trust_all);
    assertEquals(2, actualSipProvider.transport_protocols.length);
    assertNull(actualSipProvider.transport_ports);
    Hashtable hashtable = actualSipProvider.sip_transports;
    assertEquals(actualSipProvider.sip_listeners, hashtable);
    Vector vector = actualSipProvider.promiscuous_listeners;
    assertEquals(actualSipProvider.exception_listeners, vector);
    assertNull(actualSipProvider.message_logger);
    assertFalse(actualSipProvider.log_all_packets);
    assertEquals("cert/ssl.key", actualSipProvider.key_file);
    assertEquals(vector, actualSipProvider.exception_listeners);
    assertEquals("cert/ssl.crt", actualSipProvider.cert_file);
    assertTrue(actualSipProvider.isRportSet());
    assertFalse(actualSipProvider.isForceRportSet());
    assertFalse(actualSipProvider.hasTelGateway());
    assertFalse(actualSipProvider.hasOutboundProxy());
    assertEquals("42 Main St", actualSipProvider.getViaAddress());
    assertEquals(8080, actualSipProvider.getPort());
    assertEquals(Integer.SIZE, actualSipProvider.getNMaxConnections());
    assertNull(actualSipProvider.getLogger());
    assertEquals(hashtable, actualSipProvider.getListeners());
    assertEquals(SipProvider.PROTO_UDP, actualSipProvider.getDefaultTransport());
  }

  @Test
  void testConstructor23() {
    // Arrange and Act
    SipProvider actualSipProvider = new SipProvider("42 Main St", 8080, new String[]{});

    // Assert
    assertNull(actualSipProvider.getBindingIpAddress());
    assertNull(actualSipProvider.trusted_certs);
    assertEquals("cert", actualSipProvider.trust_folder);
    assertFalse(actualSipProvider.trust_all);
    assertEquals(0, actualSipProvider.transport_protocols.length);
    assertNull(actualSipProvider.transport_ports);
    Hashtable hashtable = actualSipProvider.sip_transports;
    assertEquals(actualSipProvider.sip_listeners, hashtable);
    Vector vector = actualSipProvider.promiscuous_listeners;
    assertEquals(actualSipProvider.exception_listeners, vector);
    assertNull(actualSipProvider.message_logger);
    assertFalse(actualSipProvider.log_all_packets);
    assertEquals("cert/ssl.key", actualSipProvider.key_file);
    assertEquals(vector, actualSipProvider.exception_listeners);
    assertEquals("cert/ssl.crt", actualSipProvider.cert_file);
    assertTrue(actualSipProvider.isRportSet());
    assertFalse(actualSipProvider.isForceRportSet());
    assertFalse(actualSipProvider.hasTelGateway());
    assertFalse(actualSipProvider.hasOutboundProxy());
    assertEquals("42 Main St", actualSipProvider.getViaAddress());
    assertEquals(8080, actualSipProvider.getPort());
    assertEquals(Integer.SIZE, actualSipProvider.getNMaxConnections());
    assertNull(actualSipProvider.getLogger());
    assertEquals(hashtable, actualSipProvider.getListeners());
    assertNull(actualSipProvider.getDefaultTransport());
  }

  @Test
  void testConstructor24() {
    // Arrange and Act
    SipProvider actualSipProvider = new SipProvider("42 Main St", 0, new String[]{SipProvider.PROTO_TLS});

    // Assert
    assertNull(actualSipProvider.getBindingIpAddress());
    assertNull(actualSipProvider.trusted_certs);
    assertEquals("cert", actualSipProvider.trust_folder);
    assertFalse(actualSipProvider.trust_all);
    assertEquals(1, actualSipProvider.transport_protocols.length);
    assertNull(actualSipProvider.transport_ports);
    Hashtable hashtable = actualSipProvider.sip_transports;
    assertEquals(actualSipProvider.sip_listeners, hashtable);
    Vector vector = actualSipProvider.promiscuous_listeners;
    assertEquals(actualSipProvider.exception_listeners, vector);
    assertNull(actualSipProvider.message_logger);
    assertFalse(actualSipProvider.log_all_packets);
    assertEquals("cert/ssl.key", actualSipProvider.key_file);
    assertEquals(vector, actualSipProvider.exception_listeners);
    assertEquals("cert/ssl.crt", actualSipProvider.cert_file);
    assertTrue(actualSipProvider.isRportSet());
    assertFalse(actualSipProvider.isForceRportSet());
    assertFalse(actualSipProvider.hasTelGateway());
    assertFalse(actualSipProvider.hasOutboundProxy());
    assertEquals("42 Main St", actualSipProvider.getViaAddress());
    assertEquals(5060, actualSipProvider.getPort());
    assertEquals(Integer.SIZE, actualSipProvider.getNMaxConnections());
    assertNull(actualSipProvider.getLogger());
    assertEquals(hashtable, actualSipProvider.getListeners());
    assertEquals(SipProvider.PROTO_TLS, actualSipProvider.getDefaultTransport());
  }

  @Test
  void testConstructor25() {
    // Arrange and Act
    SipProvider actualSipProvider = new SipProvider("42 Main St", 8080, new String[]{"Transport protocols"}, "");

    // Assert
    assertNull(actualSipProvider.trusted_certs);
    Hashtable expectedListeners = actualSipProvider.sip_transports;
    Hashtable listeners = actualSipProvider.getListeners();
    assertEquals(expectedListeners, listeners);
    assertEquals(8080, actualSipProvider.getPort());
    assertFalse(actualSipProvider.isAllInterfaces());
    assertFalse(actualSipProvider.isForceRportSet());
    assertNull(actualSipProvider.getTelGateway());
    assertEquals("cert", actualSipProvider.trust_folder);
    assertFalse(actualSipProvider.trust_all);
    assertEquals(1, actualSipProvider.transport_protocols.length);
    assertNull(actualSipProvider.transport_ports);
    assertEquals(listeners, actualSipProvider.sip_transports);
    Vector vector = actualSipProvider.promiscuous_listeners;
    assertEquals(actualSipProvider.exception_listeners, vector);
    assertNull(actualSipProvider.message_logger);
    assertFalse(actualSipProvider.log_all_packets);
    assertEquals("cert/ssl.key", actualSipProvider.key_file);
    assertEquals(vector, actualSipProvider.exception_listeners);
    assertEquals("cert/ssl.crt", actualSipProvider.cert_file);
    assertTrue(actualSipProvider.isRportSet());
    assertNull(actualSipProvider.getLogger());
    assertFalse(actualSipProvider.hasOutboundProxy());
    assertEquals("42 Main St", actualSipProvider.getViaAddress());
    assertEquals("Transport protocols", actualSipProvider.getDefaultTransport());
    assertEquals(Integer.SIZE, actualSipProvider.getNMaxConnections());
    assertEquals("127.0.0.1", actualSipProvider.getBindingIpAddress().toString());
  }

  @Test
  void testConstructor26() {
    // Arrange and Act
    SipProvider actualSipProvider = new SipProvider("42 Main St", 8080, new String[]{"Transport protocols"},
        SipProvider.ALL_INTERFACES);

    // Assert
    assertNull(actualSipProvider.getBindingIpAddress());
    assertNull(actualSipProvider.trusted_certs);
    assertEquals("cert", actualSipProvider.trust_folder);
    assertFalse(actualSipProvider.trust_all);
    assertEquals(1, actualSipProvider.transport_protocols.length);
    assertNull(actualSipProvider.transport_ports);
    Hashtable hashtable = actualSipProvider.sip_transports;
    assertEquals(actualSipProvider.sip_listeners, hashtable);
    Vector vector = actualSipProvider.promiscuous_listeners;
    assertEquals(actualSipProvider.exception_listeners, vector);
    assertNull(actualSipProvider.message_logger);
    assertFalse(actualSipProvider.log_all_packets);
    assertEquals("cert/ssl.key", actualSipProvider.key_file);
    assertEquals(vector, actualSipProvider.exception_listeners);
    assertEquals("cert/ssl.crt", actualSipProvider.cert_file);
    assertTrue(actualSipProvider.isRportSet());
    assertFalse(actualSipProvider.isForceRportSet());
    assertFalse(actualSipProvider.hasTelGateway());
    assertFalse(actualSipProvider.hasOutboundProxy());
    assertEquals("42 Main St", actualSipProvider.getViaAddress());
    assertEquals(8080, actualSipProvider.getPort());
    assertEquals(Integer.SIZE, actualSipProvider.getNMaxConnections());
    assertNull(actualSipProvider.getLogger());
    assertEquals(hashtable, actualSipProvider.getListeners());
    assertEquals("Transport protocols", actualSipProvider.getDefaultTransport());
  }

  @Test
  void testConstructor27() {
    // Arrange and Act
    SipProvider actualSipProvider = new SipProvider("42 Main St", 8080, new String[]{"Transport protocols"},
        (String) null);

    // Assert
    assertNull(actualSipProvider.getBindingIpAddress());
    assertNull(actualSipProvider.trusted_certs);
    assertEquals("cert", actualSipProvider.trust_folder);
    assertFalse(actualSipProvider.trust_all);
    assertEquals(1, actualSipProvider.transport_protocols.length);
    assertNull(actualSipProvider.transport_ports);
    Hashtable hashtable = actualSipProvider.sip_transports;
    assertEquals(actualSipProvider.sip_listeners, hashtable);
    Vector vector = actualSipProvider.promiscuous_listeners;
    assertEquals(actualSipProvider.exception_listeners, vector);
    assertNull(actualSipProvider.message_logger);
    assertFalse(actualSipProvider.log_all_packets);
    assertEquals("cert/ssl.key", actualSipProvider.key_file);
    assertEquals(vector, actualSipProvider.exception_listeners);
    assertEquals("cert/ssl.crt", actualSipProvider.cert_file);
    assertTrue(actualSipProvider.isRportSet());
    assertFalse(actualSipProvider.isForceRportSet());
    assertFalse(actualSipProvider.hasTelGateway());
    assertFalse(actualSipProvider.hasOutboundProxy());
    assertEquals("42 Main St", actualSipProvider.getViaAddress());
    assertEquals(8080, actualSipProvider.getPort());
    assertEquals(Integer.SIZE, actualSipProvider.getNMaxConnections());
    assertNull(actualSipProvider.getLogger());
    assertEquals(hashtable, actualSipProvider.getListeners());
    assertEquals("Transport protocols", actualSipProvider.getDefaultTransport());
  }

  @Test
  void testConstructor28() {
    // Arrange and Act
    SipProvider actualSipProvider = new SipProvider(SipProvider.ALL_INTERFACES, 8080,
        new String[]{"Transport protocols"}, "");

    // Assert
    assertNull(actualSipProvider.trusted_certs);
    Hashtable expectedListeners = actualSipProvider.sip_transports;
    Hashtable listeners = actualSipProvider.getListeners();
    assertEquals(expectedListeners, listeners);
    assertEquals(8080, actualSipProvider.getPort());
    assertFalse(actualSipProvider.isAllInterfaces());
    assertFalse(actualSipProvider.isForceRportSet());
    assertNull(actualSipProvider.getTelGateway());
    assertEquals("cert", actualSipProvider.trust_folder);
    assertFalse(actualSipProvider.trust_all);
    assertEquals(1, actualSipProvider.transport_protocols.length);
    assertNull(actualSipProvider.transport_ports);
    assertEquals(listeners, actualSipProvider.sip_transports);
    Vector vector = actualSipProvider.promiscuous_listeners;
    assertEquals(actualSipProvider.exception_listeners, vector);
    assertNull(actualSipProvider.message_logger);
    assertFalse(actualSipProvider.log_all_packets);
    assertEquals("cert/ssl.key", actualSipProvider.key_file);
    assertEquals(vector, actualSipProvider.exception_listeners);
    assertEquals("cert/ssl.crt", actualSipProvider.cert_file);
    assertTrue(actualSipProvider.isRportSet());
    assertNull(actualSipProvider.getLogger());
    assertFalse(actualSipProvider.hasOutboundProxy());
    assertEquals(SipProvider.ALL_INTERFACES, actualSipProvider.getViaAddress());
    assertEquals("Transport protocols", actualSipProvider.getDefaultTransport());
    assertEquals(Integer.SIZE, actualSipProvider.getNMaxConnections());
    assertEquals("127.0.0.1", actualSipProvider.getBindingIpAddress().toString());
  }

  @Test
  void testConstructor29() {
    // Arrange and Act
    SipProvider actualSipProvider = new SipProvider("42 Main St", 8080, new String[]{null}, "");

    // Assert
    assertNull(actualSipProvider.trusted_certs);
    assertNull(actualSipProvider.getDefaultTransport());
    assertNull(actualSipProvider.getLogger());
    assertEquals("cert", actualSipProvider.trust_folder);
    assertFalse(actualSipProvider.trust_all);
    assertEquals(1, actualSipProvider.transport_protocols.length);
    assertNull(actualSipProvider.transport_ports);
    assertNull(actualSipProvider.getTelGateway());
    Hashtable hashtable = actualSipProvider.sip_transports;
    assertEquals(actualSipProvider.sip_listeners, hashtable);
    Vector vector = actualSipProvider.promiscuous_listeners;
    assertEquals(actualSipProvider.exception_listeners, vector);
    assertNull(actualSipProvider.message_logger);
    assertFalse(actualSipProvider.log_all_packets);
    assertEquals("cert/ssl.key", actualSipProvider.key_file);
    assertEquals(vector, actualSipProvider.exception_listeners);
    assertEquals("cert/ssl.crt", actualSipProvider.cert_file);
    assertFalse(actualSipProvider.isForceRportSet());
    assertTrue(actualSipProvider.isRportSet());
    assertFalse(actualSipProvider.isAllInterfaces());
    assertFalse(actualSipProvider.hasOutboundProxy());
    assertEquals("42 Main St", actualSipProvider.getViaAddress());
    assertEquals(Integer.SIZE, actualSipProvider.getNMaxConnections());
    assertEquals(8080, actualSipProvider.getPort());
    assertEquals(hashtable, actualSipProvider.getListeners());
    assertEquals("127.0.0.1", actualSipProvider.getBindingIpAddress().toString());
  }

  @Test
  void testConstructor30() {
    // Arrange and Act
    SipProvider actualSipProvider = new SipProvider("42 Main St", 8080, null, "");

    // Assert
    assertNull(actualSipProvider.trusted_certs);
    Hashtable expectedListeners = actualSipProvider.sip_transports;
    Hashtable listeners = actualSipProvider.getListeners();
    assertEquals(expectedListeners, listeners);
    assertEquals(8080, actualSipProvider.getPort());
    assertFalse(actualSipProvider.isAllInterfaces());
    assertFalse(actualSipProvider.isForceRportSet());
    assertNull(actualSipProvider.getTelGateway());
    assertEquals("cert", actualSipProvider.trust_folder);
    assertFalse(actualSipProvider.trust_all);
    assertEquals(2, actualSipProvider.transport_protocols.length);
    assertNull(actualSipProvider.transport_ports);
    assertEquals(listeners, actualSipProvider.sip_transports);
    Vector vector = actualSipProvider.promiscuous_listeners;
    assertEquals(actualSipProvider.exception_listeners, vector);
    assertNull(actualSipProvider.message_logger);
    assertFalse(actualSipProvider.log_all_packets);
    assertEquals("cert/ssl.key", actualSipProvider.key_file);
    assertEquals(vector, actualSipProvider.exception_listeners);
    assertEquals("cert/ssl.crt", actualSipProvider.cert_file);
    assertTrue(actualSipProvider.isRportSet());
    assertNull(actualSipProvider.getLogger());
    assertFalse(actualSipProvider.hasOutboundProxy());
    assertEquals("42 Main St", actualSipProvider.getViaAddress());
    assertEquals(SipProvider.PROTO_UDP, actualSipProvider.getDefaultTransport());
    assertEquals(Integer.SIZE, actualSipProvider.getNMaxConnections());
    assertEquals("127.0.0.1", actualSipProvider.getBindingIpAddress().toString());
  }

  @Test
  void testConstructor31() {
    // Arrange and Act
    SipProvider actualSipProvider = new SipProvider("42 Main St", 8080, null, SipProvider.ALL_INTERFACES);

    // Assert
    assertNull(actualSipProvider.getBindingIpAddress());
    assertNull(actualSipProvider.trusted_certs);
    assertEquals("cert", actualSipProvider.trust_folder);
    assertFalse(actualSipProvider.trust_all);
    assertEquals(2, actualSipProvider.transport_protocols.length);
    assertNull(actualSipProvider.transport_ports);
    Hashtable hashtable = actualSipProvider.sip_transports;
    assertEquals(actualSipProvider.sip_listeners, hashtable);
    Vector vector = actualSipProvider.promiscuous_listeners;
    assertEquals(actualSipProvider.exception_listeners, vector);
    assertNull(actualSipProvider.message_logger);
    assertFalse(actualSipProvider.log_all_packets);
    assertEquals("cert/ssl.key", actualSipProvider.key_file);
    assertEquals(vector, actualSipProvider.exception_listeners);
    assertEquals("cert/ssl.crt", actualSipProvider.cert_file);
    assertTrue(actualSipProvider.isRportSet());
    assertFalse(actualSipProvider.isForceRportSet());
    assertFalse(actualSipProvider.hasTelGateway());
    assertFalse(actualSipProvider.hasOutboundProxy());
    assertEquals("42 Main St", actualSipProvider.getViaAddress());
    assertEquals(8080, actualSipProvider.getPort());
    assertEquals(Integer.SIZE, actualSipProvider.getNMaxConnections());
    assertNull(actualSipProvider.getLogger());
    assertEquals(hashtable, actualSipProvider.getListeners());
    assertEquals(SipProvider.PROTO_UDP, actualSipProvider.getDefaultTransport());
  }

  @Test
  void testConstructor32() {
    // Arrange and Act
    SipProvider actualSipProvider = new SipProvider("42 Main St", 8080, new String[]{}, "");

    // Assert
    assertNull(actualSipProvider.trusted_certs);
    assertNull(actualSipProvider.getDefaultTransport());
    assertNull(actualSipProvider.getLogger());
    assertEquals("cert", actualSipProvider.trust_folder);
    assertFalse(actualSipProvider.trust_all);
    assertEquals(0, actualSipProvider.transport_protocols.length);
    assertNull(actualSipProvider.transport_ports);
    Hashtable hashtable = actualSipProvider.sip_transports;
    assertEquals(actualSipProvider.sip_listeners, hashtable);
    Vector vector = actualSipProvider.promiscuous_listeners;
    assertEquals(actualSipProvider.exception_listeners, vector);
    assertNull(actualSipProvider.message_logger);
    assertFalse(actualSipProvider.log_all_packets);
    assertEquals("cert/ssl.key", actualSipProvider.key_file);
    assertEquals(vector, actualSipProvider.exception_listeners);
    assertEquals("cert/ssl.crt", actualSipProvider.cert_file);
    assertNull(actualSipProvider.getTelGateway());
    assertFalse(actualSipProvider.isForceRportSet());
    assertFalse(actualSipProvider.isAllInterfaces());
    assertFalse(actualSipProvider.hasOutboundProxy());
    assertEquals("42 Main St", actualSipProvider.getViaAddress());
    assertTrue(actualSipProvider.isRportSet());
    assertEquals(8080, actualSipProvider.getPort());
    assertEquals(Integer.SIZE, actualSipProvider.getNMaxConnections());
    assertEquals(hashtable, actualSipProvider.getListeners());
    assertEquals("127.0.0.1", actualSipProvider.getBindingIpAddress().toString());
  }

  @Test
  void testConstructor33() {
    // Arrange and Act
    SipProvider actualSipProvider = new SipProvider("42 Main St", 8080, new String[]{"Transport protocols"},
        new int[]{1, 1, 1, 1});

    // Assert
    assertNull(actualSipProvider.getBindingIpAddress());
    assertNull(actualSipProvider.trusted_certs);
    assertEquals("cert", actualSipProvider.trust_folder);
    assertFalse(actualSipProvider.trust_all);
    assertEquals(1, actualSipProvider.transport_protocols.length);
    assertNull(actualSipProvider.transport_ports);
    Hashtable hashtable = actualSipProvider.sip_transports;
    assertEquals(actualSipProvider.sip_listeners, hashtable);
    Vector vector = actualSipProvider.promiscuous_listeners;
    assertEquals(actualSipProvider.exception_listeners, vector);
    assertNull(actualSipProvider.message_logger);
    assertFalse(actualSipProvider.log_all_packets);
    assertEquals("cert/ssl.key", actualSipProvider.key_file);
    assertEquals(vector, actualSipProvider.exception_listeners);
    assertEquals("cert/ssl.crt", actualSipProvider.cert_file);
    assertTrue(actualSipProvider.isRportSet());
    assertFalse(actualSipProvider.isForceRportSet());
    assertFalse(actualSipProvider.hasTelGateway());
    assertFalse(actualSipProvider.hasOutboundProxy());
    assertEquals("42 Main St", actualSipProvider.getViaAddress());
    assertEquals(8080, actualSipProvider.getPort());
    assertEquals(Integer.SIZE, actualSipProvider.getNMaxConnections());
    assertNull(actualSipProvider.getLogger());
    assertEquals(hashtable, actualSipProvider.getListeners());
    assertEquals("Transport protocols", actualSipProvider.getDefaultTransport());
  }

  @Test
  void testConstructor34() {
    // Arrange and Act
    SipProvider actualSipProvider = new SipProvider(SipProvider.AUTO_CONFIGURATION, 8080,
        new String[]{"Transport protocols"}, new int[]{1, 1, 1, 1});

    // Assert
    assertNull(actualSipProvider.getBindingIpAddress());
    assertNull(actualSipProvider.trusted_certs);
    assertEquals("cert", actualSipProvider.trust_folder);
    assertFalse(actualSipProvider.trust_all);
    assertEquals(1, actualSipProvider.transport_protocols.length);
    assertNull(actualSipProvider.transport_ports);
    Hashtable hashtable = actualSipProvider.sip_transports;
    assertEquals(actualSipProvider.sip_listeners, hashtable);
    Vector vector = actualSipProvider.promiscuous_listeners;
    assertEquals(actualSipProvider.exception_listeners, vector);
    assertNull(actualSipProvider.message_logger);
    assertFalse(actualSipProvider.log_all_packets);
    assertEquals("cert/ssl.key", actualSipProvider.key_file);
    assertEquals(vector, actualSipProvider.exception_listeners);
    assertEquals("cert/ssl.crt", actualSipProvider.cert_file);
    assertTrue(actualSipProvider.isRportSet());
    assertFalse(actualSipProvider.isForceRportSet());
    assertFalse(actualSipProvider.hasTelGateway());
    assertFalse(actualSipProvider.hasOutboundProxy());

    assertEquals(8080, actualSipProvider.getPort());
    assertEquals(Integer.SIZE, actualSipProvider.getNMaxConnections());
    assertNull(actualSipProvider.getLogger());
    assertEquals(hashtable, actualSipProvider.getListeners());
    assertEquals("Transport protocols", actualSipProvider.getDefaultTransport());
  }

  @Test
  void testConstructor35() {
    // Arrange and Act
    SipProvider actualSipProvider = new SipProvider(null, 8080, new String[]{"Transport protocols"},
        new int[]{1, 1, 1, 1});

    // Assert
    assertNull(actualSipProvider.getBindingIpAddress());
    assertNull(actualSipProvider.trusted_certs);
    assertEquals("cert", actualSipProvider.trust_folder);
    assertFalse(actualSipProvider.trust_all);
    assertEquals(1, actualSipProvider.transport_protocols.length);
    assertNull(actualSipProvider.transport_ports);
    Hashtable hashtable = actualSipProvider.sip_transports;
    assertEquals(actualSipProvider.sip_listeners, hashtable);
    Vector vector = actualSipProvider.promiscuous_listeners;
    assertEquals(actualSipProvider.exception_listeners, vector);
    assertNull(actualSipProvider.message_logger);
    assertFalse(actualSipProvider.log_all_packets);
    assertEquals("cert/ssl.key", actualSipProvider.key_file);
    assertEquals(vector, actualSipProvider.exception_listeners);
    assertEquals("cert/ssl.crt", actualSipProvider.cert_file);
    assertTrue(actualSipProvider.isRportSet());
    assertFalse(actualSipProvider.isForceRportSet());
    assertFalse(actualSipProvider.hasTelGateway());
    assertFalse(actualSipProvider.hasOutboundProxy());

    assertEquals(8080, actualSipProvider.getPort());
    assertEquals(Integer.SIZE, actualSipProvider.getNMaxConnections());
    assertNull(actualSipProvider.getLogger());
    assertEquals(hashtable, actualSipProvider.getListeners());
    assertEquals("Transport protocols", actualSipProvider.getDefaultTransport());
  }

  @Test
  void testConstructor36() {
    // Arrange and Act
    SipProvider actualSipProvider = new SipProvider("42 Main St", 0, new String[]{"Transport protocols"},
        new int[]{1, 1, 1, 1});

    // Assert
    assertNull(actualSipProvider.getBindingIpAddress());
    assertNull(actualSipProvider.trusted_certs);
    assertEquals("cert", actualSipProvider.trust_folder);
    assertFalse(actualSipProvider.trust_all);
    assertEquals(1, actualSipProvider.transport_protocols.length);
    assertNull(actualSipProvider.transport_ports);
    Hashtable hashtable = actualSipProvider.sip_transports;
    assertEquals(actualSipProvider.sip_listeners, hashtable);
    Vector vector = actualSipProvider.promiscuous_listeners;
    assertEquals(actualSipProvider.exception_listeners, vector);
    assertNull(actualSipProvider.message_logger);
    assertFalse(actualSipProvider.log_all_packets);
    assertEquals("cert/ssl.key", actualSipProvider.key_file);
    assertEquals(vector, actualSipProvider.exception_listeners);
    assertEquals("cert/ssl.crt", actualSipProvider.cert_file);
    assertTrue(actualSipProvider.isRportSet());
    assertFalse(actualSipProvider.isForceRportSet());
    assertFalse(actualSipProvider.hasTelGateway());
    assertFalse(actualSipProvider.hasOutboundProxy());
    assertEquals("42 Main St", actualSipProvider.getViaAddress());
    assertEquals(5060, actualSipProvider.getPort());
    assertEquals(Integer.SIZE, actualSipProvider.getNMaxConnections());
    assertNull(actualSipProvider.getLogger());
    assertEquals(hashtable, actualSipProvider.getListeners());
    assertEquals("Transport protocols", actualSipProvider.getDefaultTransport());
  }

  @Test
  void testConstructor38() {
    // Arrange and Act
    SipProvider actualSipProvider = new SipProvider("42 Main St", 8080, new String[]{null}, new int[]{1, 1, 1, 1});

    // Assert
    assertNull(actualSipProvider.getBindingIpAddress());
    assertNull(actualSipProvider.trusted_certs);
    assertEquals("cert", actualSipProvider.trust_folder);
    assertFalse(actualSipProvider.trust_all);
    assertEquals(1, actualSipProvider.transport_protocols.length);
    assertNull(actualSipProvider.transport_ports);
    assertNull(actualSipProvider.getDefaultTransport());
    Hashtable hashtable = actualSipProvider.sip_transports;
    assertEquals(actualSipProvider.sip_listeners, hashtable);
    Vector vector = actualSipProvider.promiscuous_listeners;
    assertEquals(actualSipProvider.exception_listeners, vector);
    assertNull(actualSipProvider.message_logger);
    assertFalse(actualSipProvider.log_all_packets);
    assertEquals("cert/ssl.key", actualSipProvider.key_file);
    assertEquals(vector, actualSipProvider.exception_listeners);
    assertEquals("cert/ssl.crt", actualSipProvider.cert_file);
    assertTrue(actualSipProvider.isRportSet());
    assertNull(actualSipProvider.getLogger());
    assertFalse(actualSipProvider.hasTelGateway());
    assertFalse(actualSipProvider.hasOutboundProxy());
    assertEquals("42 Main St", actualSipProvider.getViaAddress());
    assertEquals(8080, actualSipProvider.getPort());
    assertEquals(Integer.SIZE, actualSipProvider.getNMaxConnections());
    assertFalse(actualSipProvider.isForceRportSet());
    assertEquals(hashtable, actualSipProvider.getListeners());
  }

  @Test
  void testConstructor40() {
    // Arrange and Act
    SipProvider actualSipProvider = new SipProvider("42 Main St", 8080, new String[]{SipProvider.PROTO_TLS},
        new int[]{1, 1, 1, 1});

    // Assert
    assertNull(actualSipProvider.getBindingIpAddress());
    assertNull(actualSipProvider.trusted_certs);
    assertEquals("cert", actualSipProvider.trust_folder);
    assertFalse(actualSipProvider.trust_all);
    assertEquals(1, actualSipProvider.transport_protocols.length);
    assertNull(actualSipProvider.transport_ports);
    Hashtable hashtable = actualSipProvider.sip_transports;
    assertEquals(actualSipProvider.sip_listeners, hashtable);
    Vector vector = actualSipProvider.promiscuous_listeners;
    assertEquals(actualSipProvider.exception_listeners, vector);
    assertNull(actualSipProvider.message_logger);
    assertFalse(actualSipProvider.log_all_packets);
    assertEquals("cert/ssl.key", actualSipProvider.key_file);
    assertEquals(vector, actualSipProvider.exception_listeners);
    assertEquals("cert/ssl.crt", actualSipProvider.cert_file);
    assertTrue(actualSipProvider.isRportSet());
    assertFalse(actualSipProvider.isForceRportSet());
    assertFalse(actualSipProvider.hasTelGateway());
    assertFalse(actualSipProvider.hasOutboundProxy());
    assertEquals("42 Main St", actualSipProvider.getViaAddress());
    assertEquals(8080, actualSipProvider.getPort());
    assertEquals(Integer.SIZE, actualSipProvider.getNMaxConnections());
    assertNull(actualSipProvider.getLogger());
    assertEquals(hashtable, actualSipProvider.getListeners());
    assertEquals(SipProvider.PROTO_TLS, actualSipProvider.getDefaultTransport());
  }

  @Test
  void testConstructor41() {
    // Arrange and Act
    SipProvider actualSipProvider = new SipProvider("42 Main St", 8080, new String[]{SipProvider.PROTO_DTLS},
        new int[]{1, 1, 1, 1});

    // Assert
    assertNull(actualSipProvider.getBindingIpAddress());
    assertNull(actualSipProvider.trusted_certs);
    assertEquals("cert", actualSipProvider.trust_folder);
    assertFalse(actualSipProvider.trust_all);
    assertEquals(1, actualSipProvider.transport_protocols.length);
    assertNull(actualSipProvider.transport_ports);
    Hashtable hashtable = actualSipProvider.sip_transports;
    assertEquals(actualSipProvider.sip_listeners, hashtable);
    Vector vector = actualSipProvider.promiscuous_listeners;
    assertEquals(actualSipProvider.exception_listeners, vector);
    assertNull(actualSipProvider.message_logger);
    assertFalse(actualSipProvider.log_all_packets);
    assertEquals("cert/ssl.key", actualSipProvider.key_file);
    assertEquals(vector, actualSipProvider.exception_listeners);
    assertEquals("cert/ssl.crt", actualSipProvider.cert_file);
    assertTrue(actualSipProvider.isRportSet());
    assertFalse(actualSipProvider.isForceRportSet());
    assertFalse(actualSipProvider.hasTelGateway());
    assertFalse(actualSipProvider.hasOutboundProxy());
    assertEquals("42 Main St", actualSipProvider.getViaAddress());
    assertEquals(8080, actualSipProvider.getPort());
    assertEquals(Integer.SIZE, actualSipProvider.getNMaxConnections());
    assertNull(actualSipProvider.getLogger());
    assertEquals(hashtable, actualSipProvider.getListeners());
    assertEquals(SipProvider.PROTO_DTLS, actualSipProvider.getDefaultTransport());
  }

  @Test
  void testConstructor42() {
    // Arrange and Act
    SipProvider actualSipProvider = new SipProvider("42 Main St", 8080, new String[]{SipProvider.PROTO_SCTP},
        new int[]{1, 1, 1, 1});

    // Assert
    assertNull(actualSipProvider.getBindingIpAddress());
    assertNull(actualSipProvider.trusted_certs);
    assertEquals("cert", actualSipProvider.trust_folder);
    assertFalse(actualSipProvider.trust_all);
    assertEquals(1, actualSipProvider.transport_protocols.length);
    assertNull(actualSipProvider.transport_ports);
    Hashtable hashtable = actualSipProvider.sip_transports;
    assertEquals(actualSipProvider.sip_listeners, hashtable);
    Vector vector = actualSipProvider.promiscuous_listeners;
    assertEquals(actualSipProvider.exception_listeners, vector);
    assertNull(actualSipProvider.message_logger);
    assertFalse(actualSipProvider.log_all_packets);
    assertEquals("cert/ssl.key", actualSipProvider.key_file);
    assertEquals(vector, actualSipProvider.exception_listeners);
    assertEquals("cert/ssl.crt", actualSipProvider.cert_file);
    assertTrue(actualSipProvider.isRportSet());
    assertFalse(actualSipProvider.isForceRportSet());
    assertFalse(actualSipProvider.hasTelGateway());
    assertFalse(actualSipProvider.hasOutboundProxy());
    assertEquals("42 Main St", actualSipProvider.getViaAddress());
    assertEquals(8080, actualSipProvider.getPort());
    assertEquals(Integer.SIZE, actualSipProvider.getNMaxConnections());
    assertNull(actualSipProvider.getLogger());
    assertEquals(hashtable, actualSipProvider.getListeners());
    assertEquals(SipProvider.PROTO_SCTP, actualSipProvider.getDefaultTransport());
  }

  @Test
  void testConstructor44() {
    // Arrange and Act
    SipProvider actualSipProvider = new SipProvider("42 Main St", 8080, new String[]{}, new int[]{1, 1, 1, 1});

    // Assert
    assertNull(actualSipProvider.getBindingIpAddress());
    assertNull(actualSipProvider.trusted_certs);
    assertEquals("cert", actualSipProvider.trust_folder);
    assertFalse(actualSipProvider.trust_all);
    assertEquals(0, actualSipProvider.transport_protocols.length);
    assertNull(actualSipProvider.transport_ports);
    Hashtable hashtable = actualSipProvider.sip_transports;
    assertEquals(actualSipProvider.sip_listeners, hashtable);
    Vector vector = actualSipProvider.promiscuous_listeners;
    assertEquals(actualSipProvider.exception_listeners, vector);
    assertNull(actualSipProvider.message_logger);
    assertFalse(actualSipProvider.log_all_packets);
    assertEquals("cert/ssl.key", actualSipProvider.key_file);
    assertEquals(vector, actualSipProvider.exception_listeners);
    assertEquals("cert/ssl.crt", actualSipProvider.cert_file);
    assertTrue(actualSipProvider.isRportSet());
    assertFalse(actualSipProvider.isForceRportSet());
    assertFalse(actualSipProvider.hasTelGateway());
    assertFalse(actualSipProvider.hasOutboundProxy());
    assertEquals("42 Main St", actualSipProvider.getViaAddress());
    assertEquals(8080, actualSipProvider.getPort());
    assertEquals(Integer.SIZE, actualSipProvider.getNMaxConnections());
    assertNull(actualSipProvider.getLogger());
    assertEquals(hashtable, actualSipProvider.getListeners());
    assertNull(actualSipProvider.getDefaultTransport());
  }

  @Test
  void testConstructor45() {
    // Arrange and Act
    SipProvider actualSipProvider = new SipProvider("42 Main St", 8080, new String[]{"Transport protocols"},
        (int[]) null);

    // Assert
    assertNull(actualSipProvider.getBindingIpAddress());
    assertNull(actualSipProvider.trusted_certs);
    assertEquals("cert", actualSipProvider.trust_folder);
    assertFalse(actualSipProvider.trust_all);
    assertEquals(1, actualSipProvider.transport_protocols.length);
    assertNull(actualSipProvider.transport_ports);
    Hashtable hashtable = actualSipProvider.sip_transports;
    assertEquals(actualSipProvider.sip_listeners, hashtable);
    Vector vector = actualSipProvider.promiscuous_listeners;
    assertEquals(actualSipProvider.exception_listeners, vector);
    assertNull(actualSipProvider.message_logger);
    assertFalse(actualSipProvider.log_all_packets);
    assertEquals("cert/ssl.key", actualSipProvider.key_file);
    assertEquals(vector, actualSipProvider.exception_listeners);
    assertEquals("cert/ssl.crt", actualSipProvider.cert_file);
    assertTrue(actualSipProvider.isRportSet());
    assertFalse(actualSipProvider.isForceRportSet());
    assertFalse(actualSipProvider.hasTelGateway());
    assertFalse(actualSipProvider.hasOutboundProxy());
    assertEquals("42 Main St", actualSipProvider.getViaAddress());
    assertEquals(8080, actualSipProvider.getPort());
    assertEquals(Integer.SIZE, actualSipProvider.getNMaxConnections());
    assertNull(actualSipProvider.getLogger());
    assertEquals(hashtable, actualSipProvider.getListeners());
    assertEquals("Transport protocols", actualSipProvider.getDefaultTransport());
  }

  @Test
  void testConstructor46() {
    // Arrange and Act
    SipProvider actualSipProvider = new SipProvider("42 Main St", 8080, new String[]{"Transport protocols"},
        new int[]{});

    // Assert
    assertNull(actualSipProvider.getBindingIpAddress());
    assertNull(actualSipProvider.trusted_certs);
    assertEquals("cert", actualSipProvider.trust_folder);
    assertFalse(actualSipProvider.trust_all);
    assertEquals(1, actualSipProvider.transport_protocols.length);
    assertNull(actualSipProvider.transport_ports);
    Hashtable hashtable = actualSipProvider.sip_transports;
    assertEquals(actualSipProvider.sip_listeners, hashtable);
    Vector vector = actualSipProvider.promiscuous_listeners;
    assertEquals(actualSipProvider.exception_listeners, vector);
    assertNull(actualSipProvider.message_logger);
    assertFalse(actualSipProvider.log_all_packets);
    assertEquals("cert/ssl.key", actualSipProvider.key_file);
    assertEquals(vector, actualSipProvider.exception_listeners);
    assertEquals("cert/ssl.crt", actualSipProvider.cert_file);
    assertTrue(actualSipProvider.isRportSet());
    assertFalse(actualSipProvider.isForceRportSet());
    assertFalse(actualSipProvider.hasTelGateway());
    assertFalse(actualSipProvider.hasOutboundProxy());
    assertEquals("42 Main St", actualSipProvider.getViaAddress());
    assertEquals(8080, actualSipProvider.getPort());
    assertEquals(Integer.SIZE, actualSipProvider.getNMaxConnections());
    assertNull(actualSipProvider.getLogger());
    assertEquals(hashtable, actualSipProvider.getListeners());
    assertEquals("Transport protocols", actualSipProvider.getDefaultTransport());
  }

  @Test
  void testConstructor47() {
    // Arrange and Act
    SipProvider actualSipProvider = new SipProvider(null, 8080, new String[]{"Transport protocols"},
        new int[]{4, 1, 1, 1});

    // Assert
    assertNull(actualSipProvider.getBindingIpAddress());
    assertNull(actualSipProvider.trusted_certs);
    assertEquals("cert", actualSipProvider.trust_folder);
    assertFalse(actualSipProvider.trust_all);
    assertEquals(1, actualSipProvider.transport_protocols.length);
    assertNull(actualSipProvider.transport_ports);
    Hashtable hashtable = actualSipProvider.sip_transports;
    assertEquals(actualSipProvider.sip_listeners, hashtable);
    Vector vector = actualSipProvider.promiscuous_listeners;
    assertEquals(actualSipProvider.exception_listeners, vector);
    assertNull(actualSipProvider.message_logger);
    assertFalse(actualSipProvider.log_all_packets);
    assertEquals("cert/ssl.key", actualSipProvider.key_file);
    assertEquals(vector, actualSipProvider.exception_listeners);
    assertEquals("cert/ssl.crt", actualSipProvider.cert_file);
    assertTrue(actualSipProvider.isRportSet());
    assertFalse(actualSipProvider.isForceRportSet());
    assertFalse(actualSipProvider.hasTelGateway());
    assertFalse(actualSipProvider.hasOutboundProxy());

    assertEquals(8080, actualSipProvider.getPort());
    assertEquals(Integer.SIZE, actualSipProvider.getNMaxConnections());
    assertNull(actualSipProvider.getLogger());
    assertEquals(hashtable, actualSipProvider.getListeners());
    assertEquals("Transport protocols", actualSipProvider.getDefaultTransport());
  }

  @Test
  void testConstructor48() {
    // Arrange and Act
    SipProvider actualSipProvider = new SipProvider(null, 8080, new String[]{"Transport protocols"},
        new int[]{1, 0, 1, 1});

    // Assert
    assertNull(actualSipProvider.getBindingIpAddress());
    assertNull(actualSipProvider.trusted_certs);
    assertEquals("cert", actualSipProvider.trust_folder);
    assertFalse(actualSipProvider.trust_all);
    assertEquals(1, actualSipProvider.transport_protocols.length);
    assertNull(actualSipProvider.transport_ports);
    Hashtable hashtable = actualSipProvider.sip_transports;
    assertEquals(actualSipProvider.sip_listeners, hashtable);
    Vector vector = actualSipProvider.promiscuous_listeners;
    assertEquals(actualSipProvider.exception_listeners, vector);
    assertNull(actualSipProvider.message_logger);
    assertFalse(actualSipProvider.log_all_packets);
    assertEquals("cert/ssl.key", actualSipProvider.key_file);
    assertEquals(vector, actualSipProvider.exception_listeners);
    assertEquals("cert/ssl.crt", actualSipProvider.cert_file);
    assertTrue(actualSipProvider.isRportSet());
    assertFalse(actualSipProvider.isForceRportSet());
    assertFalse(actualSipProvider.hasTelGateway());
    assertFalse(actualSipProvider.hasOutboundProxy());

    assertEquals(8080, actualSipProvider.getPort());
    assertEquals(Integer.SIZE, actualSipProvider.getNMaxConnections());
    assertNull(actualSipProvider.getLogger());
    assertEquals(hashtable, actualSipProvider.getListeners());
    assertEquals("Transport protocols", actualSipProvider.getDefaultTransport());
  }

  @Test
  void testConstructor49() {
    // Arrange and Act
    SipProvider actualSipProvider = new SipProvider("42 Main St", 8080, new String[]{SipProvider.PROTO_UDP},
        new int[]{0, 1, 1, 1});

    // Assert
    assertNull(actualSipProvider.getBindingIpAddress());
    assertNull(actualSipProvider.trusted_certs);
    assertEquals("cert", actualSipProvider.trust_folder);
    assertFalse(actualSipProvider.trust_all);
    assertEquals(1, actualSipProvider.transport_protocols.length);
    assertNull(actualSipProvider.transport_ports);
    Hashtable hashtable = actualSipProvider.sip_transports;
    assertEquals(actualSipProvider.sip_listeners, hashtable);
    Vector vector = actualSipProvider.promiscuous_listeners;
    assertEquals(actualSipProvider.exception_listeners, vector);
    assertNull(actualSipProvider.message_logger);
    assertFalse(actualSipProvider.log_all_packets);
    assertEquals("cert/ssl.key", actualSipProvider.key_file);
    assertEquals(vector, actualSipProvider.exception_listeners);
    assertEquals("cert/ssl.crt", actualSipProvider.cert_file);
    assertTrue(actualSipProvider.isRportSet());
    assertFalse(actualSipProvider.isForceRportSet());
    assertFalse(actualSipProvider.hasTelGateway());
    assertFalse(actualSipProvider.hasOutboundProxy());
    assertEquals("42 Main St", actualSipProvider.getViaAddress());
    assertEquals(8080, actualSipProvider.getPort());
    assertEquals(Integer.SIZE, actualSipProvider.getNMaxConnections());
    assertNull(actualSipProvider.getLogger());
    assertEquals(hashtable, actualSipProvider.getListeners());
    assertEquals(SipProvider.PROTO_UDP, actualSipProvider.getDefaultTransport());
  }

  @Test
  void testConstructor50() {
    // Arrange and Act
    SipProvider actualSipProvider = new SipProvider("42 Main St", 8080, new String[]{SipProvider.PROTO_TCP},
        new int[]{0, 1, 1, 1});

    // Assert
    assertNull(actualSipProvider.getBindingIpAddress());
    assertNull(actualSipProvider.trusted_certs);
    assertEquals("cert", actualSipProvider.trust_folder);
    assertFalse(actualSipProvider.trust_all);
    assertEquals(1, actualSipProvider.transport_protocols.length);
    assertNull(actualSipProvider.transport_ports);
    Hashtable hashtable = actualSipProvider.sip_transports;
    assertEquals(actualSipProvider.sip_listeners, hashtable);
    Vector vector = actualSipProvider.promiscuous_listeners;
    assertEquals(actualSipProvider.exception_listeners, vector);
    assertNull(actualSipProvider.message_logger);
    assertFalse(actualSipProvider.log_all_packets);
    assertEquals("cert/ssl.key", actualSipProvider.key_file);
    assertEquals(vector, actualSipProvider.exception_listeners);
    assertEquals("cert/ssl.crt", actualSipProvider.cert_file);
    assertTrue(actualSipProvider.isRportSet());
    assertFalse(actualSipProvider.isForceRportSet());
    assertFalse(actualSipProvider.hasTelGateway());
    assertFalse(actualSipProvider.hasOutboundProxy());
    assertEquals("42 Main St", actualSipProvider.getViaAddress());
    assertEquals(8080, actualSipProvider.getPort());
    assertEquals(Integer.SIZE, actualSipProvider.getNMaxConnections());
    assertNull(actualSipProvider.getLogger());
    assertEquals(hashtable, actualSipProvider.getListeners());
    assertEquals(SipProvider.PROTO_TCP, actualSipProvider.getDefaultTransport());
  }

  @Test
  void testConstructor51() {
    // Arrange and Act
    SipProvider actualSipProvider = new SipProvider("42 Main St", 8080, new String[]{SipProvider.PROTO_TLS},
        new int[]{0, 1, 1, 1});

    // Assert
    assertNull(actualSipProvider.getBindingIpAddress());
    assertNull(actualSipProvider.trusted_certs);
    assertEquals("cert", actualSipProvider.trust_folder);
    assertFalse(actualSipProvider.trust_all);
    assertEquals(1, actualSipProvider.transport_protocols.length);
    assertNull(actualSipProvider.transport_ports);
    Hashtable hashtable = actualSipProvider.sip_transports;
    assertEquals(actualSipProvider.sip_listeners, hashtable);
    Vector vector = actualSipProvider.promiscuous_listeners;
    assertEquals(actualSipProvider.exception_listeners, vector);
    assertNull(actualSipProvider.message_logger);
    assertFalse(actualSipProvider.log_all_packets);
    assertEquals("cert/ssl.key", actualSipProvider.key_file);
    assertEquals(vector, actualSipProvider.exception_listeners);
    assertEquals("cert/ssl.crt", actualSipProvider.cert_file);
    assertTrue(actualSipProvider.isRportSet());
    assertFalse(actualSipProvider.isForceRportSet());
    assertFalse(actualSipProvider.hasTelGateway());
    assertFalse(actualSipProvider.hasOutboundProxy());
    assertEquals("42 Main St", actualSipProvider.getViaAddress());
    assertEquals(8080, actualSipProvider.getPort());
    assertEquals(Integer.SIZE, actualSipProvider.getNMaxConnections());
    assertNull(actualSipProvider.getLogger());
    assertEquals(hashtable, actualSipProvider.getListeners());
    assertEquals(SipProvider.PROTO_TLS, actualSipProvider.getDefaultTransport());
  }

  @Test
  void testConstructor52() {
    // Arrange and Act
    SipProvider actualSipProvider = new SipProvider("42 Main St", 8080, new String[]{SipProvider.PROTO_SCTP},
        new int[]{0, 1, 1, 1});

    // Assert
    assertNull(actualSipProvider.getBindingIpAddress());
    assertNull(actualSipProvider.trusted_certs);
    assertEquals("cert", actualSipProvider.trust_folder);
    assertFalse(actualSipProvider.trust_all);
    assertEquals(1, actualSipProvider.transport_protocols.length);
    assertNull(actualSipProvider.transport_ports);
    Hashtable hashtable = actualSipProvider.sip_transports;
    assertEquals(actualSipProvider.sip_listeners, hashtable);
    Vector vector = actualSipProvider.promiscuous_listeners;
    assertEquals(actualSipProvider.exception_listeners, vector);
    assertNull(actualSipProvider.message_logger);
    assertFalse(actualSipProvider.log_all_packets);
    assertEquals("cert/ssl.key", actualSipProvider.key_file);
    assertEquals(vector, actualSipProvider.exception_listeners);
    assertEquals("cert/ssl.crt", actualSipProvider.cert_file);
    assertTrue(actualSipProvider.isRportSet());
    assertFalse(actualSipProvider.isForceRportSet());
    assertFalse(actualSipProvider.hasTelGateway());
    assertFalse(actualSipProvider.hasOutboundProxy());
    assertEquals("42 Main St", actualSipProvider.getViaAddress());
    assertEquals(8080, actualSipProvider.getPort());
    assertEquals(Integer.SIZE, actualSipProvider.getNMaxConnections());
    assertNull(actualSipProvider.getLogger());
    assertEquals(hashtable, actualSipProvider.getListeners());
    assertEquals(SipProvider.PROTO_SCTP, actualSipProvider.getDefaultTransport());
  }

  @Test
  void testConstructor53() {
    // Arrange and Act
    SipProvider actualSipProvider = new SipProvider("42 Main St", 8080, new String[]{"Transport protocols"},
        new int[]{1, 1, 1, 1}, "");

    // Assert
    assertNull(actualSipProvider.trusted_certs);
    Hashtable expectedListeners = actualSipProvider.sip_transports;
    Hashtable listeners = actualSipProvider.getListeners();
    assertEquals(expectedListeners, listeners);
    assertEquals(8080, actualSipProvider.getPort());
    assertFalse(actualSipProvider.isAllInterfaces());
    assertFalse(actualSipProvider.isForceRportSet());
    assertNull(actualSipProvider.getTelGateway());
    assertEquals("cert", actualSipProvider.trust_folder);
    assertFalse(actualSipProvider.trust_all);
    assertEquals(1, actualSipProvider.transport_protocols.length);
    assertNull(actualSipProvider.transport_ports);
    assertEquals(listeners, actualSipProvider.sip_transports);
    Vector vector = actualSipProvider.promiscuous_listeners;
    assertEquals(actualSipProvider.exception_listeners, vector);
    assertNull(actualSipProvider.message_logger);
    assertFalse(actualSipProvider.log_all_packets);
    assertEquals("cert/ssl.key", actualSipProvider.key_file);
    assertEquals(vector, actualSipProvider.exception_listeners);
    assertEquals("cert/ssl.crt", actualSipProvider.cert_file);
    assertTrue(actualSipProvider.isRportSet());
    assertNull(actualSipProvider.getLogger());
    assertFalse(actualSipProvider.hasOutboundProxy());
    assertEquals("42 Main St", actualSipProvider.getViaAddress());
    assertEquals("Transport protocols", actualSipProvider.getDefaultTransport());
    assertEquals(Integer.SIZE, actualSipProvider.getNMaxConnections());
    assertEquals("127.0.0.1", actualSipProvider.getBindingIpAddress().toString());
  }

  @Test
  void testConstructor54() {
    // Arrange and Act
    SipProvider actualSipProvider = new SipProvider("42 Main St", 8080, new String[]{"Transport protocols"},
        new int[]{1, 1, 1, 1}, SipProvider.ALL_INTERFACES);

    // Assert
    assertNull(actualSipProvider.getBindingIpAddress());
    assertNull(actualSipProvider.trusted_certs);
    assertEquals("cert", actualSipProvider.trust_folder);
    assertFalse(actualSipProvider.trust_all);
    assertEquals(1, actualSipProvider.transport_protocols.length);
    assertNull(actualSipProvider.transport_ports);
    Hashtable hashtable = actualSipProvider.sip_transports;
    assertEquals(actualSipProvider.sip_listeners, hashtable);
    Vector vector = actualSipProvider.promiscuous_listeners;
    assertEquals(actualSipProvider.exception_listeners, vector);
    assertNull(actualSipProvider.message_logger);
    assertFalse(actualSipProvider.log_all_packets);
    assertEquals("cert/ssl.key", actualSipProvider.key_file);
    assertEquals(vector, actualSipProvider.exception_listeners);
    assertEquals("cert/ssl.crt", actualSipProvider.cert_file);
    assertTrue(actualSipProvider.isRportSet());
    assertFalse(actualSipProvider.isForceRportSet());
    assertFalse(actualSipProvider.hasTelGateway());
    assertFalse(actualSipProvider.hasOutboundProxy());
    assertEquals("42 Main St", actualSipProvider.getViaAddress());
    assertEquals(8080, actualSipProvider.getPort());
    assertEquals(Integer.SIZE, actualSipProvider.getNMaxConnections());
    assertNull(actualSipProvider.getLogger());
    assertEquals(hashtable, actualSipProvider.getListeners());
    assertEquals("Transport protocols", actualSipProvider.getDefaultTransport());
  }

  @Test
  void testConstructor55() {
    // Arrange and Act
    SipProvider actualSipProvider = new SipProvider("42 Main St", 8080, new String[]{"Transport protocols"},
        new int[]{1, 1, 1, 1}, null);

    // Assert
    assertNull(actualSipProvider.getBindingIpAddress());
    assertNull(actualSipProvider.trusted_certs);
    assertEquals("cert", actualSipProvider.trust_folder);
    assertFalse(actualSipProvider.trust_all);
    assertEquals(1, actualSipProvider.transport_protocols.length);
    assertNull(actualSipProvider.transport_ports);
    Hashtable hashtable = actualSipProvider.sip_transports;
    assertEquals(actualSipProvider.sip_listeners, hashtable);
    Vector vector = actualSipProvider.promiscuous_listeners;
    assertEquals(actualSipProvider.exception_listeners, vector);
    assertNull(actualSipProvider.message_logger);
    assertFalse(actualSipProvider.log_all_packets);
    assertEquals("cert/ssl.key", actualSipProvider.key_file);
    assertEquals(vector, actualSipProvider.exception_listeners);
    assertEquals("cert/ssl.crt", actualSipProvider.cert_file);
    assertTrue(actualSipProvider.isRportSet());
    assertFalse(actualSipProvider.isForceRportSet());
    assertFalse(actualSipProvider.hasTelGateway());
    assertFalse(actualSipProvider.hasOutboundProxy());
    assertEquals("42 Main St", actualSipProvider.getViaAddress());
    assertEquals(8080, actualSipProvider.getPort());
    assertEquals(Integer.SIZE, actualSipProvider.getNMaxConnections());
    assertNull(actualSipProvider.getLogger());
    assertEquals(hashtable, actualSipProvider.getListeners());
    assertEquals("Transport protocols", actualSipProvider.getDefaultTransport());
  }

  @Test
  void testConstructor56() {
    // Arrange and Act
    SipProvider actualSipProvider = new SipProvider("SipStack: mjsip 1.8", 8080, new String[]{"Transport protocols"},
        new int[]{1, 1, 1, 1}, "");

    // Assert
    assertNull(actualSipProvider.trusted_certs);
    Hashtable expectedListeners = actualSipProvider.sip_transports;
    Hashtable listeners = actualSipProvider.getListeners();
    assertEquals(expectedListeners, listeners);
    assertEquals(8080, actualSipProvider.getPort());
    assertFalse(actualSipProvider.isAllInterfaces());
    assertFalse(actualSipProvider.isForceRportSet());
    assertNull(actualSipProvider.getTelGateway());
    assertEquals("cert", actualSipProvider.trust_folder);
    assertFalse(actualSipProvider.trust_all);
    assertEquals(1, actualSipProvider.transport_protocols.length);
    assertNull(actualSipProvider.transport_ports);
    assertEquals(listeners, actualSipProvider.sip_transports);
    Vector vector = actualSipProvider.promiscuous_listeners;
    assertEquals(actualSipProvider.exception_listeners, vector);
    assertNull(actualSipProvider.message_logger);
    assertFalse(actualSipProvider.log_all_packets);
    assertEquals("cert/ssl.key", actualSipProvider.key_file);
    assertEquals(vector, actualSipProvider.exception_listeners);
    assertEquals("cert/ssl.crt", actualSipProvider.cert_file);
    assertTrue(actualSipProvider.isRportSet());
    assertNull(actualSipProvider.getLogger());
    assertFalse(actualSipProvider.hasOutboundProxy());
    assertEquals("SipStack: mjsip 1.8", actualSipProvider.getViaAddress());
    assertEquals("Transport protocols", actualSipProvider.getDefaultTransport());
    assertEquals(Integer.SIZE, actualSipProvider.getNMaxConnections());
    assertEquals("127.0.0.1", actualSipProvider.getBindingIpAddress().toString());
  }

  @Test
  void testConstructor57() {
    // Arrange and Act
    SipProvider actualSipProvider = new SipProvider("42 Main St", 8080, new SipTransport[]{});

    // Assert
    assertNull(actualSipProvider.getBindingIpAddress());
    assertNull(actualSipProvider.trusted_certs);
    assertEquals("cert", actualSipProvider.trust_folder);
    assertFalse(actualSipProvider.trust_all);
    assertNull(actualSipProvider.transport_protocols);
    assertNull(actualSipProvider.transport_ports);
    Hashtable hashtable = actualSipProvider.sip_transports;
    assertEquals(actualSipProvider.sip_listeners, hashtable);
    Vector vector = actualSipProvider.promiscuous_listeners;
    assertEquals(actualSipProvider.exception_listeners, vector);
    assertNull(actualSipProvider.message_logger);
    assertFalse(actualSipProvider.log_all_packets);
    assertEquals("cert/ssl.key", actualSipProvider.key_file);
    assertEquals(vector, actualSipProvider.exception_listeners);
    assertEquals("cert/ssl.crt", actualSipProvider.cert_file);
    assertTrue(actualSipProvider.isRportSet());
    assertFalse(actualSipProvider.isForceRportSet());
    assertFalse(actualSipProvider.hasTelGateway());
    assertFalse(actualSipProvider.hasOutboundProxy());
    assertEquals("42 Main St", actualSipProvider.getViaAddress());
    assertEquals(8080, actualSipProvider.getPort());
    assertEquals(0, actualSipProvider.getNMaxConnections());
    assertNull(actualSipProvider.getLogger());
    assertEquals(hashtable, actualSipProvider.getListeners());
    assertNull(actualSipProvider.getDefaultTransport());
  }

  @Test
  void testConstructor58() {
    // Arrange and Act
    SipProvider actualSipProvider = new SipProvider("42 Main St", 8080, (SipTransport[]) null);

    // Assert
    assertNull(actualSipProvider.getBindingIpAddress());
    assertNull(actualSipProvider.trusted_certs);
    assertEquals("cert", actualSipProvider.trust_folder);
    assertFalse(actualSipProvider.trust_all);
    assertNull(actualSipProvider.transport_protocols);
    assertNull(actualSipProvider.transport_ports);
    Hashtable hashtable = actualSipProvider.sip_transports;
    assertEquals(actualSipProvider.sip_listeners, hashtable);
    Vector vector = actualSipProvider.promiscuous_listeners;
    assertEquals(actualSipProvider.exception_listeners, vector);
    assertNull(actualSipProvider.message_logger);
    assertFalse(actualSipProvider.log_all_packets);
    assertEquals("cert/ssl.key", actualSipProvider.key_file);
    assertEquals(vector, actualSipProvider.exception_listeners);
    assertEquals("cert/ssl.crt", actualSipProvider.cert_file);
    assertTrue(actualSipProvider.isRportSet());
    assertFalse(actualSipProvider.isForceRportSet());
    assertFalse(actualSipProvider.hasTelGateway());
    assertFalse(actualSipProvider.hasOutboundProxy());
    assertEquals("42 Main St", actualSipProvider.getViaAddress());
    assertEquals(8080, actualSipProvider.getPort());
    assertEquals(0, actualSipProvider.getNMaxConnections());
    assertNull(actualSipProvider.getLogger());
    assertEquals(hashtable, actualSipProvider.getListeners());
    assertNull(actualSipProvider.getDefaultTransport());
  }

  @Test
  void testConstructor59() {
    // Arrange and Act
    SipProvider actualSipProvider = new SipProvider(SipProvider.AUTO_CONFIGURATION, 8080, new SipTransport[]{});

    // Assert
    assertNull(actualSipProvider.getBindingIpAddress());
    assertNull(actualSipProvider.trusted_certs);
    assertEquals("cert", actualSipProvider.trust_folder);
    assertFalse(actualSipProvider.trust_all);
    assertNull(actualSipProvider.transport_protocols);
    assertNull(actualSipProvider.transport_ports);
    Hashtable hashtable = actualSipProvider.sip_transports;
    assertEquals(actualSipProvider.sip_listeners, hashtable);
    Vector vector = actualSipProvider.promiscuous_listeners;
    assertEquals(actualSipProvider.exception_listeners, vector);
    assertNull(actualSipProvider.message_logger);
    assertFalse(actualSipProvider.log_all_packets);
    assertEquals("cert/ssl.key", actualSipProvider.key_file);
    assertEquals(vector, actualSipProvider.exception_listeners);
    assertEquals("cert/ssl.crt", actualSipProvider.cert_file);
    assertTrue(actualSipProvider.isRportSet());
    assertFalse(actualSipProvider.isForceRportSet());
    assertFalse(actualSipProvider.hasTelGateway());
    assertFalse(actualSipProvider.hasOutboundProxy());

    assertEquals(8080, actualSipProvider.getPort());
    assertEquals(0, actualSipProvider.getNMaxConnections());
    assertNull(actualSipProvider.getLogger());
    assertEquals(hashtable, actualSipProvider.getListeners());
    assertNull(actualSipProvider.getDefaultTransport());
  }

  @Test
  void testConstructor60() {
    // Arrange and Act
    SipProvider actualSipProvider = new SipProvider(null, 8080, new SipTransport[]{});

    // Assert
    assertNull(actualSipProvider.getBindingIpAddress());
    assertNull(actualSipProvider.trusted_certs);
    assertEquals("cert", actualSipProvider.trust_folder);
    assertFalse(actualSipProvider.trust_all);
    assertNull(actualSipProvider.transport_protocols);
    assertNull(actualSipProvider.transport_ports);
    Hashtable hashtable = actualSipProvider.sip_transports;
    assertEquals(actualSipProvider.sip_listeners, hashtable);
    Vector vector = actualSipProvider.promiscuous_listeners;
    assertEquals(actualSipProvider.exception_listeners, vector);
    assertNull(actualSipProvider.message_logger);
    assertFalse(actualSipProvider.log_all_packets);
    assertEquals("cert/ssl.key", actualSipProvider.key_file);
    assertEquals(vector, actualSipProvider.exception_listeners);
    assertEquals("cert/ssl.crt", actualSipProvider.cert_file);
    assertTrue(actualSipProvider.isRportSet());
    assertFalse(actualSipProvider.isForceRportSet());
    assertFalse(actualSipProvider.hasTelGateway());
    assertFalse(actualSipProvider.hasOutboundProxy());

    assertEquals(8080, actualSipProvider.getPort());
    assertEquals(0, actualSipProvider.getNMaxConnections());
    assertNull(actualSipProvider.getLogger());
    assertEquals(hashtable, actualSipProvider.getListeners());
    assertNull(actualSipProvider.getDefaultTransport());
  }

  @Test
  void testConstructor61() {
    // Arrange and Act
    SipProvider actualSipProvider = new SipProvider("42 Main St", 0, new SipTransport[]{});

    // Assert
    assertNull(actualSipProvider.getBindingIpAddress());
    assertNull(actualSipProvider.trusted_certs);
    assertEquals("cert", actualSipProvider.trust_folder);
    assertFalse(actualSipProvider.trust_all);
    assertNull(actualSipProvider.transport_protocols);
    assertNull(actualSipProvider.transport_ports);
    Hashtable hashtable = actualSipProvider.sip_transports;
    assertEquals(actualSipProvider.sip_listeners, hashtable);
    Vector vector = actualSipProvider.promiscuous_listeners;
    assertEquals(actualSipProvider.exception_listeners, vector);
    assertNull(actualSipProvider.message_logger);
    assertFalse(actualSipProvider.log_all_packets);
    assertEquals("cert/ssl.key", actualSipProvider.key_file);
    assertEquals(vector, actualSipProvider.exception_listeners);
    assertEquals("cert/ssl.crt", actualSipProvider.cert_file);
    assertTrue(actualSipProvider.isRportSet());
    assertFalse(actualSipProvider.isForceRportSet());
    assertFalse(actualSipProvider.hasTelGateway());
    assertFalse(actualSipProvider.hasOutboundProxy());
    assertEquals("42 Main St", actualSipProvider.getViaAddress());
    assertEquals(5060, actualSipProvider.getPort());
    assertEquals(0, actualSipProvider.getNMaxConnections());
    assertNull(actualSipProvider.getLogger());
    assertEquals(hashtable, actualSipProvider.getListeners());
    assertNull(actualSipProvider.getDefaultTransport());
  }

  @Test
  void testConstructor62() {
    // Arrange and Act
    SipProvider actualSipProvider = new SipProvider(null, 0, new SipTransport[]{});

    // Assert
    assertNull(actualSipProvider.getBindingIpAddress());
    assertNull(actualSipProvider.trusted_certs);
    assertEquals("cert", actualSipProvider.trust_folder);
    assertFalse(actualSipProvider.trust_all);
    assertNull(actualSipProvider.transport_protocols);
    assertNull(actualSipProvider.transport_ports);
    Hashtable hashtable = actualSipProvider.sip_transports;
    assertEquals(actualSipProvider.sip_listeners, hashtable);
    Vector vector = actualSipProvider.promiscuous_listeners;
    assertEquals(actualSipProvider.exception_listeners, vector);
    assertNull(actualSipProvider.message_logger);
    assertFalse(actualSipProvider.log_all_packets);
    assertEquals("cert/ssl.key", actualSipProvider.key_file);
    assertEquals(vector, actualSipProvider.exception_listeners);
    assertEquals("cert/ssl.crt", actualSipProvider.cert_file);
    assertTrue(actualSipProvider.isRportSet());
    assertFalse(actualSipProvider.isForceRportSet());
    assertFalse(actualSipProvider.hasTelGateway());
    assertFalse(actualSipProvider.hasOutboundProxy());

    assertEquals(5060, actualSipProvider.getPort());
    assertEquals(0, actualSipProvider.getNMaxConnections());
    assertNull(actualSipProvider.getLogger());
    assertEquals(hashtable, actualSipProvider.getListeners());
    assertNull(actualSipProvider.getDefaultTransport());
  }

  @Test
  void testConstructor63() {
    // Arrange and Act
    SipProvider actualSipProvider = new SipProvider("42 Main St", 8080,
        new SipTransport[]{new UdpTransport((UdpSocket) null)});

    // Assert
    assertNull(actualSipProvider.getBindingIpAddress());
    assertNull(actualSipProvider.trusted_certs);
    assertEquals("cert", actualSipProvider.trust_folder);
    assertFalse(actualSipProvider.trust_all);
    assertNull(actualSipProvider.transport_protocols);
    assertNull(actualSipProvider.transport_ports);
    assertEquals(1, actualSipProvider.sip_transports.size());
    Vector vector = actualSipProvider.promiscuous_listeners;
    assertEquals(actualSipProvider.exception_listeners, vector);
    assertNull(actualSipProvider.message_logger);
    assertFalse(actualSipProvider.log_all_packets);
    assertEquals("cert/ssl.key", actualSipProvider.key_file);
    assertEquals(vector, actualSipProvider.exception_listeners);
    assertEquals("cert/ssl.crt", actualSipProvider.cert_file);
    assertTrue(actualSipProvider.isRportSet());
    assertFalse(actualSipProvider.isForceRportSet());
    assertFalse(actualSipProvider.hasTelGateway());
    assertFalse(actualSipProvider.hasOutboundProxy());
    assertEquals("42 Main St", actualSipProvider.getViaAddress());
    assertEquals(8080, actualSipProvider.getPort());
    assertEquals(0, actualSipProvider.getNMaxConnections());
    assertNull(actualSipProvider.getLogger());
    assertTrue(actualSipProvider.getListeners().isEmpty());
    assertEquals(SipProvider.PROTO_UDP, actualSipProvider.getDefaultTransport());
  }

  @Test
  void testConstructor64() {
    // Arrange and Act
    SipProvider actualSipProvider = new SipProvider(SipProvider.AUTO_CONFIGURATION, 8080,
        new SipTransport[]{new UdpTransport((UdpSocket) null)});

    // Assert
    assertNull(actualSipProvider.getBindingIpAddress());
    assertNull(actualSipProvider.trusted_certs);
    assertEquals("cert", actualSipProvider.trust_folder);
    assertFalse(actualSipProvider.trust_all);
    assertNull(actualSipProvider.transport_protocols);
    assertNull(actualSipProvider.transport_ports);
    assertEquals(1, actualSipProvider.sip_transports.size());
    Vector vector = actualSipProvider.promiscuous_listeners;
    assertEquals(actualSipProvider.exception_listeners, vector);
    assertNull(actualSipProvider.message_logger);
    assertFalse(actualSipProvider.log_all_packets);
    assertEquals("cert/ssl.key", actualSipProvider.key_file);
    assertEquals(vector, actualSipProvider.exception_listeners);
    assertEquals("cert/ssl.crt", actualSipProvider.cert_file);
    assertTrue(actualSipProvider.isRportSet());
    assertFalse(actualSipProvider.isForceRportSet());
    assertFalse(actualSipProvider.hasTelGateway());
    assertFalse(actualSipProvider.hasOutboundProxy());

    assertEquals(8080, actualSipProvider.getPort());
    assertEquals(0, actualSipProvider.getNMaxConnections());
    assertNull(actualSipProvider.getLogger());
    assertTrue(actualSipProvider.getListeners().isEmpty());
    assertEquals(SipProvider.PROTO_UDP, actualSipProvider.getDefaultTransport());
  }

  @Test
  void testConstructor65() {
    // Arrange
    UdpTransport udpTransport = new UdpTransport((UdpSocket) null);
    udpTransport.setListener(null);

    // Act
    SipProvider actualSipProvider = new SipProvider("42 Main St", 8080, new SipTransport[]{udpTransport});

    // Assert
    assertNull(actualSipProvider.getBindingIpAddress());
    assertNull(actualSipProvider.trusted_certs);
    assertEquals("cert", actualSipProvider.trust_folder);
    assertFalse(actualSipProvider.trust_all);
    assertNull(actualSipProvider.transport_protocols);
    assertNull(actualSipProvider.transport_ports);
    assertEquals(1, actualSipProvider.sip_transports.size());
    Vector vector = actualSipProvider.promiscuous_listeners;
    assertEquals(actualSipProvider.exception_listeners, vector);
    assertNull(actualSipProvider.message_logger);
    assertFalse(actualSipProvider.log_all_packets);
    assertEquals("cert/ssl.key", actualSipProvider.key_file);
    assertEquals(vector, actualSipProvider.exception_listeners);
    assertEquals("cert/ssl.crt", actualSipProvider.cert_file);
    assertTrue(actualSipProvider.isRportSet());
    assertFalse(actualSipProvider.isForceRportSet());
    assertFalse(actualSipProvider.hasTelGateway());
    assertFalse(actualSipProvider.hasOutboundProxy());
    assertEquals("42 Main St", actualSipProvider.getViaAddress());
    assertEquals(8080, actualSipProvider.getPort());
    assertEquals(0, actualSipProvider.getNMaxConnections());
    assertNull(actualSipProvider.getLogger());
    assertTrue(actualSipProvider.getListeners().isEmpty());
    assertEquals(SipProvider.PROTO_UDP, actualSipProvider.getDefaultTransport());
  }

  @Test
  void testIsSecureTransport() {
    // Arrange, Act and Assert
    assertFalse(SipProvider.isSecureTransport("alice.liddell@example.org"));
    assertTrue(SipProvider.isSecureTransport(SipProvider.PROTO_TLS));
    assertTrue(SipProvider.isSecureTransport(SipProvider.PROTO_DTLS));
  }

  @Test
  void testSetTransport() {
    // Arrange
    SipProvider sipProvider = new SipProvider("File");
    SipTransport sipTransport = mock(SipTransport.class);
    when(sipTransport.getLocalPort()).thenReturn(8080);
    doNothing().when(sipTransport).setListener((SipTransportListener) any());
    when(sipTransport.getProtocol()).thenReturn("Protocol");

    // Act
    sipProvider.setTransport(sipTransport);

    // Assert
    verify(sipTransport).getLocalPort();
    verify(sipTransport).getProtocol();
    verify(sipTransport).setListener((SipTransportListener) any());
    assertEquals(1, sipProvider.sip_transports.size());
  }

  @Test
  void testRemoveSipTransport() {
    // Arrange
    SipProvider sipProvider = new SipProvider("File");

    // Act
    sipProvider.removeSipTransport("alice.liddell@example.org");

    // Assert that nothing has changed
    assertEquals("cert", sipProvider.trust_folder);
    assertFalse(sipProvider.trust_all);
    assertEquals(2, sipProvider.transport_protocols.length);
    Hashtable hashtable = sipProvider.sip_transports;
    assertEquals(sipProvider.sip_listeners, hashtable);
    Vector vector = sipProvider.promiscuous_listeners;
    assertEquals(sipProvider.exception_listeners, vector);
    assertFalse(sipProvider.log_all_packets);
    assertEquals("cert/ssl.key", sipProvider.key_file);
    assertEquals(vector, sipProvider.exception_listeners);
    assertEquals("cert/ssl.crt", sipProvider.cert_file);
    assertTrue(sipProvider.isRportSet());
    assertFalse(sipProvider.isForceRportSet());
    assertFalse(sipProvider.hasTelGateway());
    assertFalse(sipProvider.hasOutboundProxy());

    assertEquals(5060, sipProvider.getPort());
    assertEquals(Integer.SIZE, sipProvider.getNMaxConnections());
    assertEquals(hashtable, sipProvider.getListeners());
    assertEquals(SipProvider.PROTO_UDP, sipProvider.getDefaultTransport());
  }

  @Test
  void testHalt() {
    // Arrange
    SipProvider sipProvider = new SipProvider("File");

    // Act
    sipProvider.halt();

    // Assert
    assertNull(sipProvider.sip_transports);
    Vector vector = sipProvider.promiscuous_listeners;
    assertEquals(sipProvider.exception_listeners, vector);
    assertEquals(vector, sipProvider.exception_listeners);
    assertTrue(sipProvider.getListeners().isEmpty());
  }

  @Test
  void testParseLine() {
    // Arrange
    SipProvider sipProvider = new SipProvider("File");

    // Act
    sipProvider.parseLine("via_addr");

    // Assert
    assertEquals("", sipProvider.getViaAddress());
  }

  @Test
  void testParseLine2() {
    // Arrange
    SipProvider sipProvider = new SipProvider("File");

    // Act
    sipProvider.parseLine("binding_ipaddr");

    // Assert
    assertFalse(sipProvider.isAllInterfaces());
    assertEquals("127.0.0.1", sipProvider.getBindingIpAddress().toString());
  }

  @Test
  void testParseLine3() {
    // Arrange
    SipProvider sipProvider = new SipProvider("File");

    // Act
    sipProvider.parseLine("transport_protocols");

    // Assert
    assertEquals(0, sipProvider.transport_protocols.length);
  }

  @Test
  void testToLines() {
    // Arrange, Act and Assert
    assertEquals("5060/", (new SipProvider("File")).toLines());
  }

  @Test
  void testHasTransport() {
    // Arrange, Act and Assert
    assertFalse((new SipProvider("File")).hasTransport("alice.liddell@example.org"));
  }

  @Test
  void testHasSecureTransport() {
    // Arrange, Act and Assert
    assertFalse((new SipProvider("File")).hasSecureTransport());
  }

  @Test
  void testGetTlsPort() {
    // Arrange, Act and Assert
    assertEquals(0, (new SipProvider("File")).getTlsPort());
  }

  @Test
  void testGetContactAddress6() {
    // Arrange, Act and Assert
    assertNull((new SipProvider("File")).getContactAddress("User", true));
  }

  @Test
  void testGetContactAddress7() {
    // Arrange, Act and Assert
    assertNull((new SipProvider("42 Main St", 8080)).getContactAddress("User", true));
  }

  @Test
  void testGetContactAddress8() {
    // Arrange, Act and Assert
    assertNull((new SipProvider("File")).getContactAddress(null, true));
  }

  @Test
  void testGetContactAddress9() {
    // Arrange and Act
    NameAddress actualContactAddress = (new SipProvider("File")).getContactAddress("User", false);

    // Assert

    assertFalse(actualContactAddress.hasDisplayName());
    GenericURI address = actualContactAddress.getAddress();
    assertFalse(address.isTelURI());
    assertFalse(((SipURI) address).isSecure());
  }

  @Test
  void testGetContactAddress10() {
    // Arrange, Act and Assert
    assertNull((new SipProvider("File")).getContactAddress(true));
  }

  @Test
  void testGetContactAddress11() {
    // Arrange, Act and Assert
    assertNull((new SipProvider("42 Main St", 8080)).getContactAddress(true));
  }

  @Test
  void testGetContactAddress12() {
    // Arrange and Act
    NameAddress actualContactAddress = (new SipProvider("File")).getContactAddress(false);

    // Assert

    assertFalse(actualContactAddress.hasDisplayName());
    GenericURI address = actualContactAddress.getAddress();
    assertFalse(address.isTelURI());
    assertFalse(((SipURI) address).isSecure());
  }

  @Test
  void testIsAllInterfaces() {
    // Arrange, Act and Assert
    assertTrue((new SipProvider("File")).isAllInterfaces());
  }

  @Test
  void testGetTransportProtocols() {
    // Arrange, Act and Assert
    assertEquals(0, (new SipProvider("File")).getTransportProtocols().length);
  }

  @Test
  void testIsSupportedTransport() {
    // Arrange, Act and Assert
    assertFalse((new SipProvider("File")).isSupportedTransport("alice.liddell@example.org"));
  }

  @Test
  void testIsReliableTransport() {
    // Arrange, Act and Assert
    assertFalse((new SipProvider("File")).isReliableTransport("alice.liddell@example.org"));
    assertFalse((new SipProvider("File")).isReliableTransport((SipTransport) null));
  }

  @Test
  void testSetForceSentBy() {
    // Arrange
    SipProvider sipProvider = new SipProvider("File");

    // Act
    sipProvider.setForceSentBy(true);

    // Assert that nothing has changed
    assertEquals("cert", sipProvider.trust_folder);
    assertFalse(sipProvider.trust_all);
    assertEquals(2, sipProvider.transport_protocols.length);
    Hashtable hashtable = sipProvider.sip_transports;
    assertEquals(sipProvider.sip_listeners, hashtable);
    Vector vector = sipProvider.promiscuous_listeners;
    assertEquals(sipProvider.exception_listeners, vector);
    assertFalse(sipProvider.log_all_packets);
    assertEquals("cert/ssl.key", sipProvider.key_file);
    assertEquals(vector, sipProvider.exception_listeners);
    assertEquals("cert/ssl.crt", sipProvider.cert_file);
    assertTrue(sipProvider.isRportSet());
    assertFalse(sipProvider.isForceRportSet());
    assertFalse(sipProvider.hasTelGateway());
    assertFalse(sipProvider.hasOutboundProxy());

    assertEquals(5060, sipProvider.getPort());
    assertEquals(Integer.SIZE, sipProvider.getNMaxConnections());
    assertEquals(hashtable, sipProvider.getListeners());
    assertEquals(SipProvider.PROTO_UDP, sipProvider.getDefaultTransport());
  }

  @Test
  void testIsForceSentBySet() {
    // Arrange, Act and Assert
    assertFalse((new SipProvider("File")).isForceSentBySet());
  }

  @Test
  void testHasOutboundProxy() {
    // Arrange, Act and Assert
    assertFalse((new SipProvider("File")).hasOutboundProxy());
  }

  @Test
  void testHasOutboundProxy2() {
    // Arrange
    SipProvider sipProvider = new SipProvider("File");
    sipProvider.setOutboundProxy(new SipURI("Uri"));
    sipProvider.addPromiscuousListener(mock(SipProviderListener.class));

    // Act and Assert
    assertTrue(sipProvider.hasOutboundProxy());
  }

  @Test
  void testHasTelGateway() {
    // Arrange, Act and Assert
    assertFalse((new SipProvider("File")).hasTelGateway());
  }

  @Test
  void testHasTelGateway2() {
    // Arrange
    SipProvider sipProvider = new SipProvider("File");
    sipProvider.setTelGateway(new SipURI("Uri"));
    sipProvider.addPromiscuousListener(mock(SipProviderListener.class));

    // Act and Assert
    assertTrue(sipProvider.hasTelGateway());
  }

  @Test
  void testAddSelectiveListener() {
    // Arrange
    SipProvider sipProvider = new SipProvider("File");

    // Act
    sipProvider.addSelectiveListener(new MethodId("Method"), mock(SipProviderListener.class));

    // Assert
    assertEquals(1, sipProvider.getListeners().size());
  }

  @Test
  void testAddSelectiveListener2() {
    // Arrange
    SipProvider sipProvider = new SipProvider("File");
    sipProvider.addSelectiveListener(new MethodId("Method"), mock(SipProviderListener.class));

    // Act
    sipProvider.addSelectiveListener(new MethodId("Method"), mock(SipProviderListener.class));

    // Assert
    assertEquals(1, sipProvider.getListeners().size());
  }

  @Test
  void testAddSelectiveListener3() {
    // Arrange
    SipProvider sipProvider = new SipProvider("File");

    // Act
    sipProvider.addSelectiveListener(new MethodId(new MethodId("Method")), mock(SipProviderListener.class));

    // Assert
    assertEquals(1, sipProvider.getListeners().size());
  }

  @Test
  void testRemoveSelectiveListener() {
    // Arrange
    SipProvider sipProvider = new SipProvider("File");
    MethodId methodId = new MethodId("Method");

    // Act
    sipProvider.removeSelectiveListener(methodId);

    // Assert that nothing has changed
    assertEquals("Method", methodId.toString());
    assertEquals("cert", sipProvider.trust_folder);
    assertFalse(sipProvider.trust_all);
    assertEquals(2, sipProvider.transport_protocols.length);
    Hashtable hashtable = sipProvider.sip_transports;
    assertEquals(sipProvider.sip_listeners, hashtable);
    Vector vector = sipProvider.promiscuous_listeners;
    assertEquals(sipProvider.exception_listeners, vector);
    assertFalse(sipProvider.log_all_packets);
    assertEquals("cert/ssl.key", sipProvider.key_file);
    assertEquals(vector, sipProvider.exception_listeners);
    assertEquals("cert/ssl.crt", sipProvider.cert_file);
    assertTrue(sipProvider.isRportSet());
    assertFalse(sipProvider.isForceRportSet());
    assertFalse(sipProvider.hasTelGateway());
    assertFalse(sipProvider.hasOutboundProxy());

    assertEquals(5060, sipProvider.getPort());
    assertEquals(Integer.SIZE, sipProvider.getNMaxConnections());
    assertEquals(hashtable, sipProvider.getListeners());
    assertEquals(SipProvider.PROTO_UDP, sipProvider.getDefaultTransport());
  }

  @Test
  void testRemoveSelectiveListener2() {
    // Arrange
    SipProvider sipProvider = new SipProvider("File");
    sipProvider.addSelectiveListener(new MethodId("Method"), mock(SipProviderListener.class));

    // Act
    sipProvider.removeSelectiveListener(new MethodId("Method"));

    // Assert
    assertTrue(sipProvider.getListeners().isEmpty());
  }

  @Test
  void testRemoveSelectiveListener3() {
    // Arrange
    SipProvider sipProvider = new SipProvider("File");
    MethodId methodId = new MethodId(new MethodId("Method"));

    // Act
    sipProvider.removeSelectiveListener(methodId);

    // Assert that nothing has changed
    assertEquals("Method", methodId.toString());
    assertEquals("cert", sipProvider.trust_folder);
    assertFalse(sipProvider.trust_all);
    assertEquals(2, sipProvider.transport_protocols.length);
    Hashtable hashtable = sipProvider.sip_transports;
    assertEquals(sipProvider.sip_listeners, hashtable);
    Vector vector = sipProvider.promiscuous_listeners;
    assertEquals(sipProvider.exception_listeners, vector);
    assertFalse(sipProvider.log_all_packets);
    assertEquals("cert/ssl.key", sipProvider.key_file);
    assertEquals(vector, sipProvider.exception_listeners);
    assertEquals("cert/ssl.crt", sipProvider.cert_file);
    assertTrue(sipProvider.isRportSet());
    assertFalse(sipProvider.isForceRportSet());
    assertFalse(sipProvider.hasTelGateway());
    assertFalse(sipProvider.hasOutboundProxy());

    assertEquals(5060, sipProvider.getPort());
    assertEquals(Integer.SIZE, sipProvider.getNMaxConnections());
    assertEquals(hashtable, sipProvider.getListeners());
    assertEquals(SipProvider.PROTO_UDP, sipProvider.getDefaultTransport());
  }

  @Test
  void testAddPromiscuousListener() {
    // Arrange
    SipProvider sipProvider = new SipProvider("File");

    // Act
    sipProvider.addPromiscuousListener(mock(SipProviderListener.class));

    // Assert
    assertEquals(1, sipProvider.promiscuous_listeners.size());
  }

  @Test
  void testAddPromiscuousListener2() {
    // Arrange
    SipProvider sipProvider = new SipProvider("File");
    sipProvider.addPromiscuousListener(mock(SipProviderListener.class));

    // Act
    sipProvider.addPromiscuousListener(mock(SipProviderListener.class));

    // Assert
    assertEquals(2, sipProvider.promiscuous_listeners.size());
  }

  @Test
  void testRemovePromiscuousListener() {
    // Arrange
    SipProvider sipProvider = new SipProvider("File");

    // Act
    sipProvider.removePromiscuousListener(mock(SipProviderListener.class));

    // Assert that nothing has changed
    assertEquals("cert", sipProvider.trust_folder);
    assertFalse(sipProvider.trust_all);
    assertEquals(2, sipProvider.transport_protocols.length);
    Hashtable hashtable = sipProvider.sip_transports;
    assertEquals(sipProvider.sip_listeners, hashtable);
    Vector vector = sipProvider.promiscuous_listeners;
    assertEquals(sipProvider.exception_listeners, vector);
    assertFalse(sipProvider.log_all_packets);
    assertEquals("cert/ssl.key", sipProvider.key_file);
    assertEquals(vector, sipProvider.exception_listeners);
    assertEquals("cert/ssl.crt", sipProvider.cert_file);
    assertTrue(sipProvider.isRportSet());
    assertFalse(sipProvider.isForceRportSet());
    assertFalse(sipProvider.hasTelGateway());
    assertFalse(sipProvider.hasOutboundProxy());

    assertEquals(5060, sipProvider.getPort());
    assertEquals(Integer.SIZE, sipProvider.getNMaxConnections());
    assertEquals(hashtable, sipProvider.getListeners());
    assertEquals(SipProvider.PROTO_UDP, sipProvider.getDefaultTransport());
  }

  @Test
  void testRemovePromiscuousListener2() {
    // Arrange
    SipProvider sipProvider = new SipProvider("File");
    sipProvider.addPromiscuousListener(mock(SipProviderListener.class));

    // Act
    sipProvider.removePromiscuousListener(mock(SipProviderListener.class));

    // Assert that nothing has changed
    assertEquals("cert", sipProvider.trust_folder);
    assertFalse(sipProvider.trust_all);
    assertEquals(2, sipProvider.transport_protocols.length);
    Hashtable hashtable = sipProvider.sip_transports;
    assertEquals(sipProvider.sip_listeners, hashtable);
    assertEquals(1, sipProvider.promiscuous_listeners.size());
    assertFalse(sipProvider.log_all_packets);
    assertEquals(hashtable, sipProvider.getListeners());
    assertEquals(5060, sipProvider.getPort());
    assertFalse(sipProvider.isForceRportSet());
    assertEquals("cert/ssl.key", sipProvider.key_file);
    assertTrue(sipProvider.exception_listeners.isEmpty());
    assertEquals("cert/ssl.crt", sipProvider.cert_file);
    assertTrue(sipProvider.isRportSet());
    assertFalse(sipProvider.hasOutboundProxy());

    assertEquals(SipProvider.PROTO_UDP, sipProvider.getDefaultTransport());
    assertEquals(Integer.SIZE, sipProvider.getNMaxConnections());
  }

  @Test
  void testAddExceptionListener() {
    // Arrange
    SipProvider sipProvider = new SipProvider("File");

    // Act
    sipProvider.addExceptionListener(mock(SipProviderExceptionListener.class));

    // Assert
    assertEquals(1, sipProvider.exception_listeners.size());
  }

  @Test
  void testAddExceptionListener2() {
    // Arrange
    SipProvider sipProvider = new SipProvider("File");
    sipProvider.addExceptionListener(mock(SipProviderExceptionListener.class));

    // Act
    sipProvider.addExceptionListener(mock(SipProviderExceptionListener.class));

    // Assert
    assertEquals(2, sipProvider.exception_listeners.size());
  }

  @Test
  void testRemoveExceptionListener() {
    // Arrange
    SipProvider sipProvider = new SipProvider("File");

    // Act
    sipProvider.removeExceptionListener(mock(SipProviderExceptionListener.class));

    // Assert that nothing has changed
    assertEquals("cert", sipProvider.trust_folder);
    assertFalse(sipProvider.trust_all);
    assertEquals(2, sipProvider.transport_protocols.length);
    Hashtable hashtable = sipProvider.sip_transports;
    assertEquals(sipProvider.sip_listeners, hashtable);
    Vector vector = sipProvider.promiscuous_listeners;
    assertEquals(sipProvider.exception_listeners, vector);
    assertFalse(sipProvider.log_all_packets);
    assertEquals("cert/ssl.key", sipProvider.key_file);
    assertEquals(vector, sipProvider.exception_listeners);
    assertEquals("cert/ssl.crt", sipProvider.cert_file);
    assertTrue(sipProvider.isRportSet());
    assertFalse(sipProvider.isForceRportSet());
    assertFalse(sipProvider.hasTelGateway());
    assertFalse(sipProvider.hasOutboundProxy());

    assertEquals(5060, sipProvider.getPort());
    assertEquals(Integer.SIZE, sipProvider.getNMaxConnections());
    assertEquals(hashtable, sipProvider.getListeners());
    assertEquals(SipProvider.PROTO_UDP, sipProvider.getDefaultTransport());
  }

  @Test
  void testRemoveExceptionListener2() {
    // Arrange
    SipProvider sipProvider = new SipProvider("File");
    sipProvider.addExceptionListener(mock(SipProviderExceptionListener.class));

    // Act
    sipProvider.removeExceptionListener(mock(SipProviderExceptionListener.class));

    // Assert that nothing has changed
    assertEquals("cert", sipProvider.trust_folder);
    assertFalse(sipProvider.trust_all);
    assertEquals(2, sipProvider.transport_protocols.length);
    Hashtable hashtable = sipProvider.sip_transports;
    assertEquals(sipProvider.sip_listeners, hashtable);
    assertTrue(sipProvider.promiscuous_listeners.isEmpty());
    assertFalse(sipProvider.log_all_packets);
    assertEquals("cert/ssl.key", sipProvider.key_file);
    assertEquals(1, sipProvider.exception_listeners.size());
    assertEquals("cert/ssl.crt", sipProvider.cert_file);
    assertEquals(hashtable, sipProvider.getListeners());
    assertEquals(5060, sipProvider.getPort());
    assertFalse(sipProvider.isForceRportSet());
    assertTrue(sipProvider.isRportSet());
    assertFalse(sipProvider.hasOutboundProxy());

    assertEquals(SipProvider.PROTO_UDP, sipProvider.getDefaultTransport());
    assertEquals(Integer.SIZE, sipProvider.getNMaxConnections());
  }

  @Test
  void testSendMessage() {
    // Arrange
    SipProvider sipProvider = new SipProvider("File");

    // Act and Assert
    assertNull(sipProvider.sendMessage(new SipMessage("Str"), "alice.liddell@example.org", "42 Main St", 8080, 1));
  }

  @Test
  void testSendMessage3() throws UnsupportedEncodingException {
    // Arrange
    SipProvider sipProvider = new SipProvider("File");
    StatusLine status_line = new StatusLine(1, "foo");

    Vector headers = new Vector(1);

    // Act and Assert
    assertNull(
        sipProvider.sendMessage(new SipMessage(status_line, headers, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8")),
            "alice.liddell@example.org", "42 Main St", 8080, 1));
  }

  @Test
  void testSendMessage4() {
    // Arrange
    SipProvider sipProvider = new SipProvider("File");

    // Act and Assert
    assertNull(sipProvider.sendMessage(new SipMessage("Str"), "alice.liddell@example.org", null, 8080, 1));
  }

  @Test
  void testSendMessage6() {
    // Arrange
    SipProvider sipProvider = new SipProvider("File");

    SipMessage sipMessage = new SipMessage("Accept");
    sipMessage.addViaHeader(new ViaHeader("42"));

    // Act and Assert
    assertNull(sipProvider.sendMessage(sipMessage, "alice.liddell@example.org", "42 Main St", 8080, 1));
  }

  @Test
  void testUpdateViaHeader() {
    // Arrange
    SipMessage sipMessage = mock(SipMessage.class);
    when(sipMessage.getViaHeader()).thenThrow(new UnexpectedUriSchemeException("Scheme"));

    // Act and Assert
    assertThrows(UnexpectedUriSchemeException.class,
        () -> SipProvider.updateViaHeader(sipMessage, "alice.liddell@example.org", "42 Main St", 8080));
    verify(sipMessage).getViaHeader();
  }

  @Test
  void testUpdateViaHeader2() {
    // Arrange
    SipMessage sipMessage = mock(SipMessage.class);
    doNothing().when(sipMessage).addViaHeader((ViaHeader) any());
    doNothing().when(sipMessage).removeViaHeader();
    when(sipMessage.getViaHeader()).thenReturn(new ViaHeader("localhost", 8080));

    // Act
    SipProvider.updateViaHeader(sipMessage, "alice.liddell@example.org", "42 Main St", 8080);

    // Assert
    verify(sipMessage).addViaHeader((ViaHeader) any());
    verify(sipMessage).getViaHeader();
    verify(sipMessage).removeViaHeader();
  }

  @Test
  void testUpdateViaHeader3() {
    // Arrange
    ViaHeader viaHeader = mock(ViaHeader.class);
    when(viaHeader.getBranch()).thenReturn("janedoe/featurebranch");
    when(viaHeader.hasRport()).thenReturn(true);
    when(viaHeader.getHost()).thenReturn("localhost");
    SipMessage sipMessage = mock(SipMessage.class);
    doNothing().when(sipMessage).addViaHeader((ViaHeader) any());
    doNothing().when(sipMessage).removeViaHeader();
    when(sipMessage.getViaHeader()).thenReturn(viaHeader);

    // Act
    SipProvider.updateViaHeader(sipMessage, "alice.liddell@example.org", "42 Main St", 8080);

    // Assert
    verify(sipMessage).addViaHeader((ViaHeader) any());
    verify(sipMessage).getViaHeader();
    verify(sipMessage).removeViaHeader();
    verify(viaHeader).getBranch();
    verify(viaHeader).getHost();
    verify(viaHeader).hasRport();
  }

  @Test
  void testUpdateViaHeader4() {
    // Arrange
    SipMessage sipMessage = mock(SipMessage.class);
    when(sipMessage.getViaHeader()).thenThrow(new UnexpectedUriSchemeException("Scheme"));

    // Act and Assert
    assertThrows(UnexpectedUriSchemeException.class, () -> SipProvider.updateViaHeader(sipMessage,
        "alice.liddell@example.org", "42 Main St", 8080, "42 Main St", 1));
    verify(sipMessage).getViaHeader();
  }

  @Test
  void testUpdateViaHeader5() {
    // Arrange
    SipMessage sipMessage = mock(SipMessage.class);
    doNothing().when(sipMessage).addViaHeader((ViaHeader) any());
    doNothing().when(sipMessage).removeViaHeader();
    when(sipMessage.getViaHeader()).thenReturn(new ViaHeader("localhost", 8080));

    // Act
    SipProvider.updateViaHeader(sipMessage, "alice.liddell@example.org", "42 Main St", 8080, "42 Main St", 1);

    // Assert
    verify(sipMessage).addViaHeader((ViaHeader) any());
    verify(sipMessage).getViaHeader();
    verify(sipMessage).removeViaHeader();
  }

  @Test
  void testUpdateViaHeader6() {
    // Arrange
    ViaHeader viaHeader = mock(ViaHeader.class);
    when(viaHeader.getBranch()).thenReturn("janedoe/featurebranch");
    when(viaHeader.hasRport()).thenReturn(true);
    when(viaHeader.getHost()).thenReturn("localhost");
    SipMessage sipMessage = mock(SipMessage.class);
    doNothing().when(sipMessage).addViaHeader((ViaHeader) any());
    doNothing().when(sipMessage).removeViaHeader();
    when(sipMessage.getViaHeader()).thenReturn(viaHeader);

    // Act
    SipProvider.updateViaHeader(sipMessage, "alice.liddell@example.org", "42 Main St", 8080, "42 Main St", 1);

    // Assert
    verify(sipMessage).addViaHeader((ViaHeader) any());
    verify(sipMessage).getViaHeader();
    verify(sipMessage).removeViaHeader();
    verify(viaHeader).getBranch();
    verify(viaHeader).getHost();
    verify(viaHeader).hasRport();
  }

  @Test
  void testSendRawMessage() {
    // Arrange
    SipProvider sipProvider = new SipProvider("File");

    // Act and Assert
    assertNull(sipProvider.sendRawMessage(new SipMessage("Str"), "alice.liddell@example.org", "42 Main St", 8080, 1));
  }

  @Test
  void testSendRawMessage2() {
    // Arrange
    SipProvider sipProvider = new SipProvider("File");

    // Act and Assert
    assertNull(
        sipProvider.sendRawMessage(new SipMessage("Accept"), "alice.liddell@example.org", "42 Main St", 8080, 1));
  }

  @Test
  void testSendRawMessage3() {
    // Arrange, Act and Assert
    assertNull((new SipProvider("File")).sendRawMessage(null, "alice.liddell@example.org", "42 Main St", 8080, 1));
  }

  @Test
  void testSendRawMessage4() {
    // Arrange
    SipProvider sipProvider = new SipProvider("File");

    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertNull(sipProvider.sendRawMessage(sipMessage, "alice.liddell@example.org", "42 Main St", 8080, 1));
  }

  @Test
  void testSendRawMessage5() throws UnsupportedEncodingException {
    // Arrange
    SipProvider sipProvider = new SipProvider("File");
    StatusLine status_line = new StatusLine(1, "foo");

    Vector headers = new Vector(1);

    // Act and Assert
    assertNull(
        sipProvider.sendRawMessage(new SipMessage(status_line, headers, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8")),
            "alice.liddell@example.org", "42 Main St", 8080, 1));
  }

  @Test
  void testSendRawMessage6() throws UnsupportedEncodingException {
    // Arrange
    SipProvider sipProvider = new SipProvider("File");
    RequestLine request_line = new RequestLine("Request", "Str uri");

    Vector headers = new Vector(1);

    SipMessage sipMessage = new SipMessage(request_line, headers, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertNull(sipProvider.sendRawMessage(sipMessage, "alice.liddell@example.org", "42 Main St", 8080, 1));
  }

  @Test
  void testSendRawMessage7() {
    // Arrange
    SipProvider sipProvider = new SipProvider("File");

    // Act and Assert
    assertNull(sipProvider.sendRawMessage(new SipMessage("Str"), "alice.liddell@example.org", null, 8080, 1));
  }

  @Test
  void testSendRawMessage8() {
    // Arrange
    SipProvider sipProvider = new SipProvider("File");

    // Act and Assert
    assertNull(sipProvider.sendRawMessage(new SipMessage("Accept"), "alice.liddell@example.org", null, 8080, 1));
  }

  @Test
  void testSendRawMessage9() throws UnsupportedEncodingException {
    // Arrange
    SipProvider sipProvider = new SipProvider("File");

    Vector vector = new Vector(1);
    vector.add("42");
    StatusLine status_line = new StatusLine(1, "foo");

    // Act and Assert
    assertNull(
        sipProvider.sendRawMessage(new SipMessage(status_line, vector, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8")),
            "alice.liddell@example.org", "42 Main St", 8080, 1));
  }

  @Test
  void testSendRawMessage10() throws UnsupportedEncodingException {
    // Arrange
    SipProvider sipProvider = new SipProvider("File");

    Vector vector = new Vector(1);
    vector.add(null);
    StatusLine status_line = new StatusLine(1, "foo");

    // Act and Assert
    assertNull(
        sipProvider.sendRawMessage(new SipMessage(status_line, vector, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8")),
            "alice.liddell@example.org", "42 Main St", 8080, 1));
  }

  @Test
  void testOnReceivedMessage() {
    // Arrange
    SipProviderExceptionListener sipProviderExceptionListener = mock(SipProviderExceptionListener.class);
    doNothing().when(sipProviderExceptionListener).onMessageException((SipMessage) any(), (Exception) any());

    SipProvider sipProvider = new SipProvider("File");
    sipProvider.addExceptionListener(sipProviderExceptionListener);

    // Act
    sipProvider.onReceivedMessage(null, new SipMessage("received"));

    // Assert
    verify(sipProviderExceptionListener).onMessageException((SipMessage) any(), (Exception) any());
  }

  @Test
  void testOnReceivedMessage2() {
    // Arrange
    SipProviderExceptionListener sipProviderExceptionListener = mock(SipProviderExceptionListener.class);
    doNothing().when(sipProviderExceptionListener).onMessageException((SipMessage) any(), (Exception) any());
    SipProviderExceptionListener sipProviderExceptionListener1 = mock(SipProviderExceptionListener.class);
    doThrow(new UnexpectedUriSchemeException("Scheme")).when(sipProviderExceptionListener1)
        .onMessageException((SipMessage) any(), (Exception) any());

    SipProvider sipProvider = new SipProvider("File");
    sipProvider.addExceptionListener(sipProviderExceptionListener1);
    sipProvider.addExceptionListener(sipProviderExceptionListener);

    // Act
    sipProvider.onReceivedMessage(null, new SipMessage("received"));

    // Assert
    verify(sipProviderExceptionListener1).onMessageException((SipMessage) any(), (Exception) any());
    verify(sipProviderExceptionListener).onMessageException((SipMessage) any(), (Exception) any());
  }

  @Test
  void testOnReceivedMessage3() {
    // Arrange
    SipProviderExceptionListener sipProviderExceptionListener = mock(SipProviderExceptionListener.class);
    doNothing().when(sipProviderExceptionListener).onMessageException((SipMessage) any(), (Exception) any());
    SipProviderExceptionListener sipProviderExceptionListener1 = mock(SipProviderExceptionListener.class);
    doThrow(new UnexpectedUriSchemeException("Scheme")).when(sipProviderExceptionListener1)
        .onMessageException((SipMessage) any(), (Exception) any());

    SipProvider sipProvider = new SipProvider("File");
    sipProvider.addExceptionListener(sipProviderExceptionListener1);
    sipProvider.addExceptionListener(sipProviderExceptionListener);

    // Act
    sipProvider.onReceivedMessage(null, null);

    // Assert
    verify(sipProviderExceptionListener1).onMessageException((SipMessage) any(), (Exception) any());
    verify(sipProviderExceptionListener).onMessageException((SipMessage) any(), (Exception) any());
  }

  @Test
  void testOnReceivedMessage4() {
    // Arrange
    SipProviderExceptionListener sipProviderExceptionListener = mock(SipProviderExceptionListener.class);
    doNothing().when(sipProviderExceptionListener).onMessageException((SipMessage) any(), (Exception) any());
    SipProviderExceptionListener sipProviderExceptionListener1 = mock(SipProviderExceptionListener.class);
    doThrow(new UnexpectedUriSchemeException("Scheme")).when(sipProviderExceptionListener1)
        .onMessageException((SipMessage) any(), (Exception) any());

    SipProvider sipProvider = new SipProvider("File");
    sipProvider.addExceptionListener(sipProviderExceptionListener1);
    sipProvider.addExceptionListener(sipProviderExceptionListener);

    SipMessage sipMessage = new SipMessage("received");
    sipMessage.addViaHeader(new ViaHeader("42"));

    // Act
    sipProvider.onReceivedMessage(null, sipMessage);

    // Assert
    verify(sipProviderExceptionListener1).onMessageException((SipMessage) any(), (Exception) any());
    verify(sipProviderExceptionListener).onMessageException((SipMessage) any(), (Exception) any());
  }

  @Test
  void testOnReceivedMessage5() {
    // Arrange
    SipProviderExceptionListener sipProviderExceptionListener = mock(SipProviderExceptionListener.class);
    doNothing().when(sipProviderExceptionListener).onMessageException((SipMessage) any(), (Exception) any());
    SipProviderExceptionListener sipProviderExceptionListener1 = mock(SipProviderExceptionListener.class);
    doThrow(new UnexpectedUriSchemeException("Scheme")).when(sipProviderExceptionListener1)
        .onMessageException((SipMessage) any(), (Exception) any());

    SipProvider sipProvider = new SipProvider("File");
    sipProvider.addExceptionListener(sipProviderExceptionListener1);
    sipProvider.addExceptionListener(sipProviderExceptionListener);

    SipMessage sipMessage = new SipMessage("received");
    sipMessage.addViaHeader(null);

    // Act
    sipProvider.onReceivedMessage(null, sipMessage);

    // Assert
    verify(sipProviderExceptionListener1).onMessageException((SipMessage) any(), (Exception) any());
    verify(sipProviderExceptionListener).onMessageException((SipMessage) any(), (Exception) any());
  }

  @Test
  void testOnReceivedMessage6() {
    // Arrange
    SipProviderExceptionListener sipProviderExceptionListener = mock(SipProviderExceptionListener.class);
    doNothing().when(sipProviderExceptionListener).onMessageException((SipMessage) any(), (Exception) any());
    SipProviderExceptionListener sipProviderExceptionListener1 = mock(SipProviderExceptionListener.class);
    doThrow(new UnexpectedUriSchemeException("Scheme")).when(sipProviderExceptionListener1)
        .onMessageException((SipMessage) any(), (Exception) any());

    SipProvider sipProvider = new SipProvider("File");
    sipProvider.addExceptionListener(sipProviderExceptionListener1);
    sipProvider.addExceptionListener(sipProviderExceptionListener);

    SipMessage sipMessage = new SipMessage("received");
    sipMessage.addViaHeader(new ViaHeader("localhost", 8080));

    // Act
    sipProvider.onReceivedMessage(null, sipMessage);

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
    assertEquals(67, sipMessage.getLength());
  }

  @Test
  void testOnTransportTerminated() {
    // Arrange
    SipProvider sipProvider = new SipProvider("File");

    // Act
    sipProvider.onTransportTerminated(null, null);

    // Assert that nothing has changed
    assertEquals("cert", sipProvider.trust_folder);
    assertFalse(sipProvider.trust_all);
    assertEquals(2, sipProvider.transport_protocols.length);
    Hashtable hashtable = sipProvider.sip_transports;
    assertEquals(sipProvider.sip_listeners, hashtable);
    Vector vector = sipProvider.promiscuous_listeners;
    assertEquals(sipProvider.exception_listeners, vector);
    assertFalse(sipProvider.log_all_packets);
    assertEquals("cert/ssl.key", sipProvider.key_file);
    assertEquals(vector, sipProvider.exception_listeners);
    assertEquals("cert/ssl.crt", sipProvider.cert_file);
    assertTrue(sipProvider.isRportSet());
    assertFalse(sipProvider.isForceRportSet());
    assertFalse(sipProvider.hasTelGateway());
    assertFalse(sipProvider.hasOutboundProxy());

    assertEquals(5060, sipProvider.getPort());
    assertEquals(Integer.SIZE, sipProvider.getNMaxConnections());
    assertEquals(hashtable, sipProvider.getListeners());
    assertEquals(SipProvider.PROTO_UDP, sipProvider.getDefaultTransport());
  }

  @Test
  void testOnIncomingTransportConnection() {
    // Arrange
    SipProvider sipProvider = new SipProvider("File");
    SocketAddress socketAddress = new SocketAddress("42 Main St");

    // Act
    sipProvider.onIncomingTransportConnection(null, socketAddress);

    // Assert that nothing has changed
    assertEquals("42 Main St:-1", socketAddress.toString());
    assertEquals(-1, socketAddress.getPort());
    assertEquals("cert", sipProvider.trust_folder);
    assertFalse(sipProvider.trust_all);
    assertEquals(2, sipProvider.transport_protocols.length);
    Hashtable hashtable = sipProvider.sip_transports;
    assertEquals(sipProvider.sip_listeners, hashtable);
    Vector vector = sipProvider.promiscuous_listeners;
    assertEquals(sipProvider.exception_listeners, vector);
    assertFalse(sipProvider.log_all_packets);
    assertEquals("cert/ssl.key", sipProvider.key_file);
    assertEquals(vector, sipProvider.exception_listeners);
    assertEquals("cert/ssl.crt", sipProvider.cert_file);
    assertTrue(sipProvider.isRportSet());
    assertFalse(sipProvider.isForceRportSet());
    assertFalse(sipProvider.hasTelGateway());
    assertFalse(sipProvider.hasOutboundProxy());

    assertEquals(5060, sipProvider.getPort());
    assertEquals(Integer.SIZE, sipProvider.getNMaxConnections());
    assertEquals(hashtable, sipProvider.getListeners());
    assertEquals(SipProvider.PROTO_UDP, sipProvider.getDefaultTransport());
  }

  @Test
  void testOnIncomingTransportConnection2() {
    // Arrange
    SipProvider sipProvider = new SipProvider("File");

    // Act
    sipProvider.onIncomingTransportConnection(null, null);

    // Assert that nothing has changed
    assertEquals("cert", sipProvider.trust_folder);
    assertFalse(sipProvider.trust_all);
    assertEquals(2, sipProvider.transport_protocols.length);
    Hashtable hashtable = sipProvider.sip_transports;
    assertEquals(sipProvider.sip_listeners, hashtable);
    Vector vector = sipProvider.promiscuous_listeners;
    assertEquals(sipProvider.exception_listeners, vector);
    assertFalse(sipProvider.log_all_packets);
    assertEquals("cert/ssl.key", sipProvider.key_file);
    assertEquals(vector, sipProvider.exception_listeners);
    assertEquals("cert/ssl.crt", sipProvider.cert_file);
    assertTrue(sipProvider.isRportSet());
    assertFalse(sipProvider.isForceRportSet());
    assertFalse(sipProvider.hasTelGateway());
    assertFalse(sipProvider.hasOutboundProxy());

    assertEquals(5060, sipProvider.getPort());
    assertEquals(Integer.SIZE, sipProvider.getNMaxConnections());
    assertEquals(hashtable, sipProvider.getListeners());
    assertEquals(SipProvider.PROTO_UDP, sipProvider.getDefaultTransport());
  }

  @Test
  void testOnTransportConnectionTerminated() {
    // Arrange
    SipProvider sipProvider = new SipProvider("File");
    SocketAddress socketAddress = new SocketAddress("42 Main St");

    // Act
    sipProvider.onTransportConnectionTerminated(null, socketAddress, new Exception("An error occurred"));

    // Assert that nothing has changed
    assertEquals("42 Main St:-1", socketAddress.toString());
    assertEquals(-1, socketAddress.getPort());
    assertEquals("cert", sipProvider.trust_folder);
    assertFalse(sipProvider.trust_all);
    assertEquals(2, sipProvider.transport_protocols.length);
    Hashtable hashtable = sipProvider.sip_transports;
    assertEquals(sipProvider.sip_listeners, hashtable);
    Vector vector = sipProvider.promiscuous_listeners;
    assertEquals(sipProvider.exception_listeners, vector);
    assertFalse(sipProvider.log_all_packets);
    assertEquals("cert/ssl.key", sipProvider.key_file);
    assertEquals(vector, sipProvider.exception_listeners);
    assertEquals("cert/ssl.crt", sipProvider.cert_file);
    assertTrue(sipProvider.isRportSet());
    assertFalse(sipProvider.isForceRportSet());
    assertFalse(sipProvider.hasTelGateway());
    assertFalse(sipProvider.hasOutboundProxy());

    assertEquals(5060, sipProvider.getPort());
    assertEquals(Integer.SIZE, sipProvider.getNMaxConnections());
    assertEquals(hashtable, sipProvider.getListeners());
    assertEquals(SipProvider.PROTO_UDP, sipProvider.getDefaultTransport());
  }

  @Test
  void testOnTransportConnectionTerminated2() {
    // Arrange
    SipProvider sipProvider = new SipProvider("File");

    // Act
    sipProvider.onTransportConnectionTerminated(null, null, new Exception("An error occurred"));

    // Assert that nothing has changed
    assertEquals("cert", sipProvider.trust_folder);
    assertFalse(sipProvider.trust_all);
    assertEquals(2, sipProvider.transport_protocols.length);
    Hashtable hashtable = sipProvider.sip_transports;
    assertEquals(sipProvider.sip_listeners, hashtable);
    Vector vector = sipProvider.promiscuous_listeners;
    assertEquals(sipProvider.exception_listeners, vector);
    assertFalse(sipProvider.log_all_packets);
    assertEquals("cert/ssl.key", sipProvider.key_file);
    assertEquals(vector, sipProvider.exception_listeners);
    assertEquals("cert/ssl.crt", sipProvider.cert_file);
    assertTrue(sipProvider.isRportSet());
    assertFalse(sipProvider.isForceRportSet());
    assertFalse(sipProvider.hasTelGateway());
    assertFalse(sipProvider.hasOutboundProxy());

    assertEquals(5060, sipProvider.getPort());
    assertEquals(Integer.SIZE, sipProvider.getNMaxConnections());
    assertEquals(hashtable, sipProvider.getListeners());
    assertEquals(SipProvider.PROTO_UDP, sipProvider.getDefaultTransport());
  }

  @Test
  void testPickBranch() {
    // Arrange
    SipProvider sipProvider = new SipProvider("File");
    RequestLine requestLine = mock(RequestLine.class);
    when(requestLine.getAddress()).thenThrow(new UnexpectedUriSchemeException("Scheme"));

    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.setRequestLine(requestLine);

    // Act and Assert
    assertThrows(UnexpectedUriSchemeException.class, () -> sipProvider.pickBranch(sipMessage));
    verify(requestLine).getAddress();
  }

  @Test
  void testPickTag() {
    // Arrange, Act and Assert
    assertEquals("1a0113131b1b1b1b", SipProvider.pickTag(new SipMessage("Str")));
    assertEquals("aef8fc036f8c419a", SipProvider.pickTag(new SipMessage("Accept")));
  }

  @Test
  void testPickTag2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertEquals("63a115f39a503c2f", SipProvider.pickTag(sipMessage));
  }

  @Test
  void testPickTag3() throws UnsupportedEncodingException {
    // Arrange
    StatusLine status_line = new StatusLine(1, "foo");

    Vector headers = new Vector(1);

    // Act and Assert
    assertEquals("29e7ea2f2f555c8e",
        SipProvider.pickTag(new SipMessage(status_line, headers, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"))));
  }

  @Test
  void testPickTag4() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.setRequestLine(new RequestLine("Request", "Str uri"));
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertEquals("5e3f62bad7099483", SipProvider.pickTag(sipMessage));
  }

  @Test
  void testPickInitialCSeq() {
    // Arrange, Act and Assert
    assertEquals(1, SipProvider.pickInitialCSeq());
  }

  @Test
  void testToString() {
    // Arrange, Act and Assert
    assertEquals("5060/", (new SipProvider("File")).toString());
  }
}

