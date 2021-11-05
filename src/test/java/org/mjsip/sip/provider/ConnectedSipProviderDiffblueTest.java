package org.mjsip.sip.provider;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Vector;
import org.junit.jupiter.api.Test;
import org.mjsip.sip.address.GenericURI;
import org.mjsip.sip.address.NameAddress;
import org.mjsip.sip.address.SipURI;

class ConnectedSipProviderDiffblueTest {
  @Test
  void testConstructor() throws IOException {
    // Arrange and Act
    ConnectedSipProvider actualConnectedSipProvider = new ConnectedSipProvider("42 Main St", 8080, new SipURI(""));

    // Assert
    assertNull(actualConnectedSipProvider.getBindingIpAddress());
    assertNull(actualConnectedSipProvider.trusted_certs);
    assertEquals("cert", actualConnectedSipProvider.trust_folder);
    assertFalse(actualConnectedSipProvider.trust_all);
    assertEquals(2, actualConnectedSipProvider.transport_protocols.length);
    assertNull(actualConnectedSipProvider.transport_ports);
    assertEquals(actualConnectedSipProvider.connections, actualConnectedSipProvider.sip_transports);
    Vector vector = actualConnectedSipProvider.promiscuous_listeners;
    assertEquals(actualConnectedSipProvider.exception_listeners, vector);
    assertNull(actualConnectedSipProvider.message_logger);
    assertFalse(actualConnectedSipProvider.log_all_packets);
    assertEquals("cert/ssl.key", actualConnectedSipProvider.key_file);
    assertEquals(vector, actualConnectedSipProvider.exception_listeners);
    Hashtable hashtable = actualConnectedSipProvider.connections;
    assertEquals(actualConnectedSipProvider.sip_listeners, hashtable);
    assertEquals("cert/ssl.crt", actualConnectedSipProvider.cert_file);
    assertTrue(actualConnectedSipProvider.isRportSet());
    assertTrue(actualConnectedSipProvider.isForceRportSet());
    assertFalse(actualConnectedSipProvider.hasTelGateway());
    assertTrue(actualConnectedSipProvider.hasOutboundProxy());
    assertEquals("42 Main St", actualConnectedSipProvider.getViaAddress());
    assertEquals(8080, actualConnectedSipProvider.getPort());
    assertEquals(Integer.SIZE, actualConnectedSipProvider.getNMaxConnections());
    assertEquals(hashtable, actualConnectedSipProvider.getListeners());
    assertNull(actualConnectedSipProvider.getLogger());
    assertEquals(SipProvider.PROTO_UDP, actualConnectedSipProvider.getDefaultTransport());
  }

  @Test
  void testConstructor2() throws IOException {
    // Arrange and Act
    ConnectedSipProvider actualConnectedSipProvider = new ConnectedSipProvider("42 Main St", 8080, null);

    // Assert
    assertNull(actualConnectedSipProvider.getBindingIpAddress());
    assertNull(actualConnectedSipProvider.trusted_certs);
    assertEquals("cert", actualConnectedSipProvider.trust_folder);
    assertFalse(actualConnectedSipProvider.trust_all);
    assertEquals(2, actualConnectedSipProvider.transport_protocols.length);
    assertNull(actualConnectedSipProvider.transport_ports);
    assertEquals(actualConnectedSipProvider.connections, actualConnectedSipProvider.sip_transports);
    Vector vector = actualConnectedSipProvider.promiscuous_listeners;
    assertEquals(actualConnectedSipProvider.exception_listeners, vector);
    assertNull(actualConnectedSipProvider.message_logger);
    assertFalse(actualConnectedSipProvider.log_all_packets);
    assertEquals("cert/ssl.key", actualConnectedSipProvider.key_file);
    assertEquals(vector, actualConnectedSipProvider.exception_listeners);
    Hashtable hashtable = actualConnectedSipProvider.connections;
    assertEquals(actualConnectedSipProvider.sip_listeners, hashtable);
    assertEquals("cert/ssl.crt", actualConnectedSipProvider.cert_file);
    assertTrue(actualConnectedSipProvider.isRportSet());
    assertTrue(actualConnectedSipProvider.isForceRportSet());
    assertFalse(actualConnectedSipProvider.hasTelGateway());
    assertFalse(actualConnectedSipProvider.hasOutboundProxy());
    assertEquals("42 Main St", actualConnectedSipProvider.getViaAddress());
    assertEquals(8080, actualConnectedSipProvider.getPort());
    assertEquals(Integer.SIZE, actualConnectedSipProvider.getNMaxConnections());
    assertNull(actualConnectedSipProvider.getLogger());
    assertEquals(hashtable, actualConnectedSipProvider.getListeners());
    assertEquals(SipProvider.PROTO_UDP, actualConnectedSipProvider.getDefaultTransport());
  }

  @Test
  void testConstructor3() throws IOException {
    // Arrange and Act
    ConnectedSipProvider actualConnectedSipProvider = new ConnectedSipProvider("42 Main St", 8080,
        new String[]{"Transport protocols"}, new SipURI(""));

    // Assert
    assertNull(actualConnectedSipProvider.getBindingIpAddress());
    assertNull(actualConnectedSipProvider.trusted_certs);
    assertEquals("cert", actualConnectedSipProvider.trust_folder);
    assertFalse(actualConnectedSipProvider.trust_all);
    assertEquals(1, actualConnectedSipProvider.transport_protocols.length);
    assertNull(actualConnectedSipProvider.transport_ports);
    assertEquals(actualConnectedSipProvider.connections, actualConnectedSipProvider.sip_transports);
    Vector vector = actualConnectedSipProvider.promiscuous_listeners;
    assertEquals(actualConnectedSipProvider.exception_listeners, vector);
    assertNull(actualConnectedSipProvider.message_logger);
    assertFalse(actualConnectedSipProvider.log_all_packets);
    assertEquals("cert/ssl.key", actualConnectedSipProvider.key_file);
    assertEquals(vector, actualConnectedSipProvider.exception_listeners);
    Hashtable hashtable = actualConnectedSipProvider.connections;
    assertEquals(actualConnectedSipProvider.sip_listeners, hashtable);
    assertEquals("cert/ssl.crt", actualConnectedSipProvider.cert_file);
    assertTrue(actualConnectedSipProvider.isRportSet());
    assertTrue(actualConnectedSipProvider.isForceRportSet());
    assertFalse(actualConnectedSipProvider.hasTelGateway());
    assertTrue(actualConnectedSipProvider.hasOutboundProxy());
    assertEquals("42 Main St", actualConnectedSipProvider.getViaAddress());
    assertEquals(8080, actualConnectedSipProvider.getPort());
    assertEquals(Integer.SIZE, actualConnectedSipProvider.getNMaxConnections());
    assertEquals(hashtable, actualConnectedSipProvider.getListeners());
    assertNull(actualConnectedSipProvider.getLogger());
    assertEquals("Transport protocols", actualConnectedSipProvider.getDefaultTransport());
  }

  @Test
  void testConstructor4() throws IOException {
    // Arrange and Act
    ConnectedSipProvider actualConnectedSipProvider = new ConnectedSipProvider("42 Main St", 8080,
        new String[]{"Transport protocols"}, null);

    // Assert
    assertNull(actualConnectedSipProvider.getBindingIpAddress());
    assertNull(actualConnectedSipProvider.trusted_certs);
    assertEquals("cert", actualConnectedSipProvider.trust_folder);
    assertFalse(actualConnectedSipProvider.trust_all);
    assertEquals(1, actualConnectedSipProvider.transport_protocols.length);
    assertNull(actualConnectedSipProvider.transport_ports);
    assertEquals(actualConnectedSipProvider.connections, actualConnectedSipProvider.sip_transports);
    Vector vector = actualConnectedSipProvider.promiscuous_listeners;
    assertEquals(actualConnectedSipProvider.exception_listeners, vector);
    assertNull(actualConnectedSipProvider.message_logger);
    assertFalse(actualConnectedSipProvider.log_all_packets);
    assertEquals("cert/ssl.key", actualConnectedSipProvider.key_file);
    assertEquals(vector, actualConnectedSipProvider.exception_listeners);
    Hashtable hashtable = actualConnectedSipProvider.connections;
    assertEquals(actualConnectedSipProvider.sip_listeners, hashtable);
    assertEquals("cert/ssl.crt", actualConnectedSipProvider.cert_file);
    assertTrue(actualConnectedSipProvider.isRportSet());
    assertTrue(actualConnectedSipProvider.isForceRportSet());
    assertFalse(actualConnectedSipProvider.hasTelGateway());
    assertFalse(actualConnectedSipProvider.hasOutboundProxy());
    assertEquals("42 Main St", actualConnectedSipProvider.getViaAddress());
    assertEquals(8080, actualConnectedSipProvider.getPort());
    assertEquals(Integer.SIZE, actualConnectedSipProvider.getNMaxConnections());
    assertNull(actualConnectedSipProvider.getLogger());
    assertEquals(hashtable, actualConnectedSipProvider.getListeners());
    assertEquals("Transport protocols", actualConnectedSipProvider.getDefaultTransport());
  }

  @Test
  void testConstructor5() throws IOException {
    // Arrange and Act
    ConnectedSipProvider actualConnectedSipProvider = new ConnectedSipProvider(SipProvider.AUTO_CONFIGURATION, 8080,
        new String[]{"Transport protocols"}, new SipURI(""));

    // Assert
    assertNull(actualConnectedSipProvider.getBindingIpAddress());
    assertNull(actualConnectedSipProvider.trusted_certs);
    assertEquals("cert", actualConnectedSipProvider.trust_folder);
    assertFalse(actualConnectedSipProvider.trust_all);
    assertEquals(1, actualConnectedSipProvider.transport_protocols.length);
    assertNull(actualConnectedSipProvider.transport_ports);
    assertEquals(actualConnectedSipProvider.connections, actualConnectedSipProvider.sip_transports);
    Vector vector = actualConnectedSipProvider.promiscuous_listeners;
    assertEquals(actualConnectedSipProvider.exception_listeners, vector);
    assertNull(actualConnectedSipProvider.message_logger);
    assertFalse(actualConnectedSipProvider.log_all_packets);
    assertEquals("cert/ssl.key", actualConnectedSipProvider.key_file);
    assertEquals(vector, actualConnectedSipProvider.exception_listeners);
    Hashtable hashtable = actualConnectedSipProvider.connections;
    assertEquals(actualConnectedSipProvider.sip_listeners, hashtable);
    assertEquals("cert/ssl.crt", actualConnectedSipProvider.cert_file);
    assertTrue(actualConnectedSipProvider.isRportSet());
    assertTrue(actualConnectedSipProvider.isForceRportSet());
    assertFalse(actualConnectedSipProvider.hasTelGateway());
    assertTrue(actualConnectedSipProvider.hasOutboundProxy());

    assertEquals(8080, actualConnectedSipProvider.getPort());
    assertEquals(Integer.SIZE, actualConnectedSipProvider.getNMaxConnections());
    assertEquals(hashtable, actualConnectedSipProvider.getListeners());
    assertNull(actualConnectedSipProvider.getLogger());
    assertEquals("Transport protocols", actualConnectedSipProvider.getDefaultTransport());
  }

  @Test
  void testConstructor6() throws IOException {
    // Arrange and Act
    ConnectedSipProvider actualConnectedSipProvider = new ConnectedSipProvider("42 Main St", 8080,
        new String[]{"Transport protocols"}, new int[]{1, 1, 1, 1}, new SipURI(""));

    // Assert
    assertNull(actualConnectedSipProvider.getBindingIpAddress());
    assertNull(actualConnectedSipProvider.trusted_certs);
    assertEquals("cert", actualConnectedSipProvider.trust_folder);
    assertFalse(actualConnectedSipProvider.trust_all);
    assertEquals(1, actualConnectedSipProvider.transport_protocols.length);
    assertNull(actualConnectedSipProvider.transport_ports);
    assertEquals(actualConnectedSipProvider.connections, actualConnectedSipProvider.sip_transports);
    Vector vector = actualConnectedSipProvider.promiscuous_listeners;
    assertEquals(actualConnectedSipProvider.exception_listeners, vector);
    assertNull(actualConnectedSipProvider.message_logger);
    assertFalse(actualConnectedSipProvider.log_all_packets);
    assertEquals("cert/ssl.key", actualConnectedSipProvider.key_file);
    assertEquals(vector, actualConnectedSipProvider.exception_listeners);
    Hashtable hashtable = actualConnectedSipProvider.connections;
    assertEquals(actualConnectedSipProvider.sip_listeners, hashtable);
    assertEquals("cert/ssl.crt", actualConnectedSipProvider.cert_file);
    assertTrue(actualConnectedSipProvider.isRportSet());
    assertTrue(actualConnectedSipProvider.isForceRportSet());
    assertFalse(actualConnectedSipProvider.hasTelGateway());
    assertTrue(actualConnectedSipProvider.hasOutboundProxy());
    assertEquals("42 Main St", actualConnectedSipProvider.getViaAddress());
    assertEquals(8080, actualConnectedSipProvider.getPort());
    assertEquals(Integer.SIZE, actualConnectedSipProvider.getNMaxConnections());
    assertEquals(hashtable, actualConnectedSipProvider.getListeners());
    assertNull(actualConnectedSipProvider.getLogger());
    assertEquals("Transport protocols", actualConnectedSipProvider.getDefaultTransport());
  }

  @Test
  void testConstructor7() throws IOException {
    // Arrange and Act
    ConnectedSipProvider actualConnectedSipProvider = new ConnectedSipProvider("42 Main St", 8080,
        new String[]{"Transport protocols"}, new int[]{1, 1, 1, 1}, null);

    // Assert
    assertNull(actualConnectedSipProvider.getBindingIpAddress());
    assertNull(actualConnectedSipProvider.trusted_certs);
    assertEquals("cert", actualConnectedSipProvider.trust_folder);
    assertFalse(actualConnectedSipProvider.trust_all);
    assertEquals(1, actualConnectedSipProvider.transport_protocols.length);
    assertNull(actualConnectedSipProvider.transport_ports);
    assertEquals(actualConnectedSipProvider.connections, actualConnectedSipProvider.sip_transports);
    Vector vector = actualConnectedSipProvider.promiscuous_listeners;
    assertEquals(actualConnectedSipProvider.exception_listeners, vector);
    assertNull(actualConnectedSipProvider.message_logger);
    assertFalse(actualConnectedSipProvider.log_all_packets);
    assertEquals("cert/ssl.key", actualConnectedSipProvider.key_file);
    assertEquals(vector, actualConnectedSipProvider.exception_listeners);
    Hashtable hashtable = actualConnectedSipProvider.connections;
    assertEquals(actualConnectedSipProvider.sip_listeners, hashtable);
    assertEquals("cert/ssl.crt", actualConnectedSipProvider.cert_file);
    assertTrue(actualConnectedSipProvider.isRportSet());
    assertTrue(actualConnectedSipProvider.isForceRportSet());
    assertFalse(actualConnectedSipProvider.hasTelGateway());
    assertFalse(actualConnectedSipProvider.hasOutboundProxy());
    assertEquals("42 Main St", actualConnectedSipProvider.getViaAddress());
    assertEquals(8080, actualConnectedSipProvider.getPort());
    assertEquals(Integer.SIZE, actualConnectedSipProvider.getNMaxConnections());
    assertNull(actualConnectedSipProvider.getLogger());
    assertEquals(hashtable, actualConnectedSipProvider.getListeners());
    assertEquals("Transport protocols", actualConnectedSipProvider.getDefaultTransport());
  }

  @Test
  void testConstructor8() throws IOException {
    // Arrange and Act
    ConnectedSipProvider actualConnectedSipProvider = new ConnectedSipProvider(SipProvider.AUTO_CONFIGURATION, 8080,
        new String[]{"Transport protocols"}, new int[]{1, 1, 1, 1}, new SipURI(""));

    // Assert
    assertNull(actualConnectedSipProvider.getBindingIpAddress());
    assertNull(actualConnectedSipProvider.trusted_certs);
    assertEquals("cert", actualConnectedSipProvider.trust_folder);
    assertFalse(actualConnectedSipProvider.trust_all);
    assertEquals(1, actualConnectedSipProvider.transport_protocols.length);
    assertNull(actualConnectedSipProvider.transport_ports);
    assertEquals(actualConnectedSipProvider.connections, actualConnectedSipProvider.sip_transports);
    Vector vector = actualConnectedSipProvider.promiscuous_listeners;
    assertEquals(actualConnectedSipProvider.exception_listeners, vector);
    assertNull(actualConnectedSipProvider.message_logger);
    assertFalse(actualConnectedSipProvider.log_all_packets);
    assertEquals("cert/ssl.key", actualConnectedSipProvider.key_file);
    assertEquals(vector, actualConnectedSipProvider.exception_listeners);
    Hashtable hashtable = actualConnectedSipProvider.connections;
    assertEquals(actualConnectedSipProvider.sip_listeners, hashtable);
    assertEquals("cert/ssl.crt", actualConnectedSipProvider.cert_file);
    assertTrue(actualConnectedSipProvider.isRportSet());
    assertTrue(actualConnectedSipProvider.isForceRportSet());
    assertFalse(actualConnectedSipProvider.hasTelGateway());
    assertTrue(actualConnectedSipProvider.hasOutboundProxy());

    assertEquals(8080, actualConnectedSipProvider.getPort());
    assertEquals(Integer.SIZE, actualConnectedSipProvider.getNMaxConnections());
    assertEquals(hashtable, actualConnectedSipProvider.getListeners());
    assertNull(actualConnectedSipProvider.getLogger());
    assertEquals("Transport protocols", actualConnectedSipProvider.getDefaultTransport());
  }

  @Test
  void testConstructor9() throws IOException {
    // Arrange and Act
    ConnectedSipProvider actualConnectedSipProvider = new ConnectedSipProvider("File", new SipURI(""));

    // Assert
    assertNull(actualConnectedSipProvider.getBindingIpAddress());
    assertNull(actualConnectedSipProvider.trusted_certs);
    assertEquals("cert", actualConnectedSipProvider.trust_folder);
    assertFalse(actualConnectedSipProvider.trust_all);
    assertEquals(2, actualConnectedSipProvider.transport_protocols.length);
    assertNull(actualConnectedSipProvider.transport_ports);
    assertEquals(actualConnectedSipProvider.connections, actualConnectedSipProvider.sip_transports);
    Vector vector = actualConnectedSipProvider.promiscuous_listeners;
    assertEquals(actualConnectedSipProvider.exception_listeners, vector);
    assertNull(actualConnectedSipProvider.message_logger);
    assertFalse(actualConnectedSipProvider.log_all_packets);
    assertEquals("cert/ssl.key", actualConnectedSipProvider.key_file);
    assertEquals(vector, actualConnectedSipProvider.exception_listeners);
    Hashtable hashtable = actualConnectedSipProvider.connections;
    assertEquals(actualConnectedSipProvider.sip_listeners, hashtable);
    assertEquals("cert/ssl.crt", actualConnectedSipProvider.cert_file);
    assertTrue(actualConnectedSipProvider.isRportSet());
    assertTrue(actualConnectedSipProvider.isForceRportSet());
    assertFalse(actualConnectedSipProvider.hasTelGateway());
    assertTrue(actualConnectedSipProvider.hasOutboundProxy());

    assertEquals(5060, actualConnectedSipProvider.getPort());
    assertEquals(Integer.SIZE, actualConnectedSipProvider.getNMaxConnections());
    assertEquals(hashtable, actualConnectedSipProvider.getListeners());
    assertNull(actualConnectedSipProvider.getLogger());
    assertEquals(SipProvider.PROTO_UDP, actualConnectedSipProvider.getDefaultTransport());
  }

  @Test
  void testConstructor10() throws IOException {
    // Arrange and Act
    ConnectedSipProvider actualConnectedSipProvider = new ConnectedSipProvider("File", null);

    // Assert
    assertNull(actualConnectedSipProvider.getBindingIpAddress());
    assertNull(actualConnectedSipProvider.trusted_certs);
    assertEquals("cert", actualConnectedSipProvider.trust_folder);
    assertFalse(actualConnectedSipProvider.trust_all);
    assertEquals(2, actualConnectedSipProvider.transport_protocols.length);
    assertNull(actualConnectedSipProvider.transport_ports);
    assertEquals(actualConnectedSipProvider.connections, actualConnectedSipProvider.sip_transports);
    Vector vector = actualConnectedSipProvider.promiscuous_listeners;
    assertEquals(actualConnectedSipProvider.exception_listeners, vector);
    assertNull(actualConnectedSipProvider.message_logger);
    assertFalse(actualConnectedSipProvider.log_all_packets);
    assertEquals("cert/ssl.key", actualConnectedSipProvider.key_file);
    assertEquals(vector, actualConnectedSipProvider.exception_listeners);
    Hashtable hashtable = actualConnectedSipProvider.connections;
    assertEquals(actualConnectedSipProvider.sip_listeners, hashtable);
    assertEquals("cert/ssl.crt", actualConnectedSipProvider.cert_file);
    assertTrue(actualConnectedSipProvider.isRportSet());
    assertTrue(actualConnectedSipProvider.isForceRportSet());
    assertFalse(actualConnectedSipProvider.hasTelGateway());
    assertFalse(actualConnectedSipProvider.hasOutboundProxy());

    assertEquals(5060, actualConnectedSipProvider.getPort());
    assertEquals(Integer.SIZE, actualConnectedSipProvider.getNMaxConnections());
    assertNull(actualConnectedSipProvider.getLogger());
    assertEquals(hashtable, actualConnectedSipProvider.getListeners());
    assertEquals(SipProvider.PROTO_UDP, actualConnectedSipProvider.getDefaultTransport());
  }

  @Test
  void testConstructor11() throws IOException {
    // Arrange and Act
    ConnectedSipProvider actualConnectedSipProvider = new ConnectedSipProvider(SipProvider.PROTO_UDP, new SipURI(""));

    // Assert
    assertNull(actualConnectedSipProvider.getBindingIpAddress());
    assertNull(actualConnectedSipProvider.trusted_certs);
    assertEquals("cert", actualConnectedSipProvider.trust_folder);
    assertFalse(actualConnectedSipProvider.trust_all);
    assertEquals(2, actualConnectedSipProvider.transport_protocols.length);
    assertNull(actualConnectedSipProvider.transport_ports);
    assertEquals(actualConnectedSipProvider.connections, actualConnectedSipProvider.sip_transports);
    Vector vector = actualConnectedSipProvider.promiscuous_listeners;
    assertEquals(actualConnectedSipProvider.exception_listeners, vector);
    assertNull(actualConnectedSipProvider.message_logger);
    assertFalse(actualConnectedSipProvider.log_all_packets);
    assertEquals("cert/ssl.key", actualConnectedSipProvider.key_file);
    assertEquals(vector, actualConnectedSipProvider.exception_listeners);
    Hashtable hashtable = actualConnectedSipProvider.connections;
    assertEquals(actualConnectedSipProvider.sip_listeners, hashtable);
    assertEquals("cert/ssl.crt", actualConnectedSipProvider.cert_file);
    assertTrue(actualConnectedSipProvider.isRportSet());
    assertTrue(actualConnectedSipProvider.isForceRportSet());
    assertFalse(actualConnectedSipProvider.hasTelGateway());
    assertTrue(actualConnectedSipProvider.hasOutboundProxy());

    assertEquals(5060, actualConnectedSipProvider.getPort());
    assertEquals(Integer.SIZE, actualConnectedSipProvider.getNMaxConnections());
    assertEquals(hashtable, actualConnectedSipProvider.getListeners());
    assertNull(actualConnectedSipProvider.getLogger());
    assertEquals(SipProvider.PROTO_UDP, actualConnectedSipProvider.getDefaultTransport());
  }

  @Test
  void testConstructor12() throws IOException {
    // Arrange
    SipURI sipURI = new SipURI("");
    sipURI.addMaddr("42 Main St");

    // Act
    ConnectedSipProvider actualConnectedSipProvider = new ConnectedSipProvider("File", sipURI);

    // Assert
    assertNull(actualConnectedSipProvider.getBindingIpAddress());
    assertNull(actualConnectedSipProvider.trusted_certs);
    assertEquals("cert", actualConnectedSipProvider.trust_folder);
    assertFalse(actualConnectedSipProvider.trust_all);
    assertEquals(2, actualConnectedSipProvider.transport_protocols.length);
    assertNull(actualConnectedSipProvider.transport_ports);
    assertEquals(actualConnectedSipProvider.connections, actualConnectedSipProvider.sip_transports);
    Vector vector = actualConnectedSipProvider.promiscuous_listeners;
    assertEquals(actualConnectedSipProvider.exception_listeners, vector);
    assertNull(actualConnectedSipProvider.message_logger);
    assertFalse(actualConnectedSipProvider.log_all_packets);
    assertEquals("cert/ssl.key", actualConnectedSipProvider.key_file);
    assertEquals(vector, actualConnectedSipProvider.exception_listeners);
    Hashtable hashtable = actualConnectedSipProvider.connections;
    assertEquals(actualConnectedSipProvider.sip_listeners, hashtable);
    assertEquals("cert/ssl.crt", actualConnectedSipProvider.cert_file);
    assertTrue(actualConnectedSipProvider.isRportSet());
    assertTrue(actualConnectedSipProvider.isForceRportSet());
    assertFalse(actualConnectedSipProvider.hasTelGateway());
    assertTrue(actualConnectedSipProvider.hasOutboundProxy());

    assertEquals(5060, actualConnectedSipProvider.getPort());
    assertEquals(Integer.SIZE, actualConnectedSipProvider.getNMaxConnections());
    assertEquals(hashtable, actualConnectedSipProvider.getListeners());
    assertNull(actualConnectedSipProvider.getLogger());
    assertEquals(SipProvider.PROTO_UDP, actualConnectedSipProvider.getDefaultTransport());
  }

  @Test
  void testConstructor13() throws IOException {
    // Arrange
    SipURI sipURI = new SipURI("");
    sipURI.addLr();

    // Act
    ConnectedSipProvider actualConnectedSipProvider = new ConnectedSipProvider("File", sipURI);

    // Assert
    assertNull(actualConnectedSipProvider.getBindingIpAddress());
    assertNull(actualConnectedSipProvider.trusted_certs);
    assertEquals("cert", actualConnectedSipProvider.trust_folder);
    assertFalse(actualConnectedSipProvider.trust_all);
    assertEquals(2, actualConnectedSipProvider.transport_protocols.length);
    assertNull(actualConnectedSipProvider.transport_ports);
    assertEquals(actualConnectedSipProvider.connections, actualConnectedSipProvider.sip_transports);
    Vector vector = actualConnectedSipProvider.promiscuous_listeners;
    assertEquals(actualConnectedSipProvider.exception_listeners, vector);
    assertNull(actualConnectedSipProvider.message_logger);
    assertFalse(actualConnectedSipProvider.log_all_packets);
    assertEquals("cert/ssl.key", actualConnectedSipProvider.key_file);
    assertEquals(vector, actualConnectedSipProvider.exception_listeners);
    Hashtable hashtable = actualConnectedSipProvider.connections;
    assertEquals(actualConnectedSipProvider.sip_listeners, hashtable);
    assertEquals("cert/ssl.crt", actualConnectedSipProvider.cert_file);
    assertTrue(actualConnectedSipProvider.isRportSet());
    assertTrue(actualConnectedSipProvider.isForceRportSet());
    assertFalse(actualConnectedSipProvider.hasTelGateway());
    assertTrue(actualConnectedSipProvider.hasOutboundProxy());

    assertEquals(5060, actualConnectedSipProvider.getPort());
    assertEquals(Integer.SIZE, actualConnectedSipProvider.getNMaxConnections());
    assertEquals(hashtable, actualConnectedSipProvider.getListeners());
    assertNull(actualConnectedSipProvider.getLogger());
    assertEquals(SipProvider.PROTO_UDP, actualConnectedSipProvider.getDefaultTransport());
  }

  @Test
  void testConstructor14() throws IOException {
    // Arrange and Act
    ConnectedSipProvider actualConnectedSipProvider = new ConnectedSipProvider("File", new SipURI("janedoe", "", 10));

    // Assert
    assertNull(actualConnectedSipProvider.getBindingIpAddress());
    assertNull(actualConnectedSipProvider.trusted_certs);
    assertEquals("cert", actualConnectedSipProvider.trust_folder);
    assertFalse(actualConnectedSipProvider.trust_all);
    assertEquals(2, actualConnectedSipProvider.transport_protocols.length);
    assertNull(actualConnectedSipProvider.transport_ports);
    assertEquals(actualConnectedSipProvider.connections, actualConnectedSipProvider.sip_transports);
    Vector vector = actualConnectedSipProvider.promiscuous_listeners;
    assertEquals(actualConnectedSipProvider.exception_listeners, vector);
    assertNull(actualConnectedSipProvider.message_logger);
    assertFalse(actualConnectedSipProvider.log_all_packets);
    assertEquals("cert/ssl.key", actualConnectedSipProvider.key_file);
    assertEquals(vector, actualConnectedSipProvider.exception_listeners);
    Hashtable hashtable = actualConnectedSipProvider.connections;
    assertEquals(actualConnectedSipProvider.sip_listeners, hashtable);
    assertEquals("cert/ssl.crt", actualConnectedSipProvider.cert_file);
    assertTrue(actualConnectedSipProvider.isRportSet());
    assertTrue(actualConnectedSipProvider.isForceRportSet());
    assertFalse(actualConnectedSipProvider.hasTelGateway());
    assertTrue(actualConnectedSipProvider.hasOutboundProxy());

    assertEquals(5060, actualConnectedSipProvider.getPort());
    assertEquals(Integer.SIZE, actualConnectedSipProvider.getNMaxConnections());
    assertEquals(hashtable, actualConnectedSipProvider.getListeners());
    assertNull(actualConnectedSipProvider.getLogger());
    assertEquals(SipProvider.PROTO_UDP, actualConnectedSipProvider.getDefaultTransport());
  }

  @Test
  void testConstructor15() throws IOException {
    // Arrange
    SipURI sipURI = new SipURI("");
    sipURI.addTtl(2);
    sipURI.addParameter("");

    // Act
    ConnectedSipProvider actualConnectedSipProvider = new ConnectedSipProvider("File", sipURI);

    // Assert
    assertNull(actualConnectedSipProvider.getBindingIpAddress());
    assertNull(actualConnectedSipProvider.trusted_certs);
    assertEquals("cert", actualConnectedSipProvider.trust_folder);
    assertFalse(actualConnectedSipProvider.trust_all);
    assertEquals(2, actualConnectedSipProvider.transport_protocols.length);
    assertNull(actualConnectedSipProvider.transport_ports);
    assertEquals(actualConnectedSipProvider.connections, actualConnectedSipProvider.sip_transports);
    Vector vector = actualConnectedSipProvider.promiscuous_listeners;
    assertEquals(actualConnectedSipProvider.exception_listeners, vector);
    assertNull(actualConnectedSipProvider.message_logger);
    assertFalse(actualConnectedSipProvider.log_all_packets);
    assertEquals("cert/ssl.key", actualConnectedSipProvider.key_file);
    assertEquals(vector, actualConnectedSipProvider.exception_listeners);
    Hashtable hashtable = actualConnectedSipProvider.connections;
    assertEquals(actualConnectedSipProvider.sip_listeners, hashtable);
    assertEquals("cert/ssl.crt", actualConnectedSipProvider.cert_file);
    assertTrue(actualConnectedSipProvider.isRportSet());
    assertTrue(actualConnectedSipProvider.isForceRportSet());
    assertFalse(actualConnectedSipProvider.hasTelGateway());
    assertTrue(actualConnectedSipProvider.hasOutboundProxy());

    assertEquals(5060, actualConnectedSipProvider.getPort());
    assertEquals(Integer.SIZE, actualConnectedSipProvider.getNMaxConnections());
    assertEquals(hashtable, actualConnectedSipProvider.getListeners());
    assertNull(actualConnectedSipProvider.getLogger());
    assertEquals(SipProvider.PROTO_UDP, actualConnectedSipProvider.getDefaultTransport());
  }

  @Test
  void testGetContactAddress() throws IOException {
    // Arrange, Act and Assert
    assertNull((new ConnectedSipProvider("File", new SipURI("42"))).getContactAddress("User", true));
  }

  @Test
  void testGetContactAddress2() throws IOException {
    // Arrange and Act
    NameAddress actualContactAddress = (new ConnectedSipProvider("File", new SipURI("42"))).getContactAddress("User",
        false);

    // Assert

    assertFalse(actualContactAddress.hasDisplayName());
    GenericURI address = actualContactAddress.getAddress();
    assertFalse(address.isTelURI());
    assertFalse(((SipURI) address).isSecure());
  }
}

