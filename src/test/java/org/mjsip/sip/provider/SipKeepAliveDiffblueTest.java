package org.mjsip.sip.provider;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.mjsip.sip.message.BasicSipMessage;
import org.mjsip.sip.message.SipMessage;
import org.zoolu.net.SocketAddress;

class SipKeepAliveDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");

    // Act
    SipKeepAlive actualSipKeepAlive = new SipKeepAlive(sip_provider, new SocketAddress("42 Main St"), 2L);

    // Assert
    assertNull(actualSipKeepAlive.udp_token);
    assertEquals(2L, actualSipKeepAlive.getDeltaTime());
    assertFalse(actualSipKeepAlive.stop);

    assertEquals(0L, actualSipKeepAlive.expire);
    assertEquals(2, actualSipKeepAlive.sip_provider.transport_protocols.length);
    SipMessage sipMessage = actualSipKeepAlive.sip_token;
    assertNull(sipMessage.getAcceptEncodingHeader());
    assertNull(sipMessage.getBody());
    assertFalse(BasicSipMessage.DEBUG);
  }

  @Test
  void testConstructor2() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    SocketAddress target = new SocketAddress("42 Main St");

    // Act
    SipKeepAlive actualSipKeepAlive = new SipKeepAlive(sip_provider, target, new SipMessage("Str"), 2L);

    // Assert
    assertNull(actualSipKeepAlive.udp_token);
    assertEquals(2L, actualSipKeepAlive.getDeltaTime());
    assertFalse(actualSipKeepAlive.stop);

    assertEquals(0L, actualSipKeepAlive.expire);
    assertEquals(2, actualSipKeepAlive.sip_provider.transport_protocols.length);
    assertFalse(BasicSipMessage.DEBUG);
  }

  @Test
  void testConstructor3() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");

    // Act
    SipKeepAlive actualSipKeepAlive = new SipKeepAlive(sip_provider, new SocketAddress("42 Main St"), null, 2L);

    // Assert
    assertNull(actualSipKeepAlive.udp_token);
    assertEquals(2L, actualSipKeepAlive.getDeltaTime());
    assertFalse(actualSipKeepAlive.stop);

    assertEquals(0L, actualSipKeepAlive.expire);
    assertEquals(2, actualSipKeepAlive.sip_provider.transport_protocols.length);
    SipMessage sipMessage = actualSipKeepAlive.sip_token;
    assertNull(sipMessage.getAcceptEncodingHeader());
    assertNull(sipMessage.getBody());
    assertFalse(BasicSipMessage.DEBUG);
  }

  @Test
  void testSendToken() throws IOException {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    SipKeepAlive sipKeepAlive = new SipKeepAlive(sip_provider, new SocketAddress("42 Main St"), 2L);

    // Act
    sipKeepAlive.sendToken();

    // Assert that nothing has changed
    assertEquals(2L, sipKeepAlive.getDeltaTime());
    assertFalse(sipKeepAlive.stop);

    assertEquals(0L, sipKeepAlive.expire);
    assertEquals(2, sipKeepAlive.sip_provider.transport_protocols.length);
    assertFalse(BasicSipMessage.DEBUG);
  }

  @Test
  void testSendToken2() throws IOException {
    // Arrange
    SipKeepAlive sipKeepAlive = new SipKeepAlive(null, new SocketAddress("42 Main St"), 2L);

    // Act
    sipKeepAlive.sendToken();

    // Assert that nothing has changed
    assertEquals(2L, sipKeepAlive.getDeltaTime());
    assertFalse(sipKeepAlive.stop);
    SocketAddress expectedDestSoAddress = sipKeepAlive.target;
    assertSame(expectedDestSoAddress, sipKeepAlive.getDestSoAddress());
    assertEquals(0L, sipKeepAlive.expire);
    assertFalse(BasicSipMessage.DEBUG);
  }

  @Test
  void testSendToken3() throws IOException {
    // Arrange
    SipKeepAlive sipKeepAlive = new SipKeepAlive(new SipProvider("File"), null, 2L);

    // Act
    sipKeepAlive.sendToken();

    // Assert that nothing has changed
    assertEquals(2L, sipKeepAlive.getDeltaTime());
    assertFalse(sipKeepAlive.stop);
    assertEquals(0L, sipKeepAlive.expire);
    assertFalse(BasicSipMessage.DEBUG);
  }

  @Test
  void testSendToken4() throws IOException {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");

    SipKeepAlive sipKeepAlive = new SipKeepAlive(sip_provider, new SocketAddress("42 Main St"), 2L);
    sipKeepAlive.setUncaughtExceptionHandler(null);

    // Act
    sipKeepAlive.sendToken();

    // Assert that nothing has changed
    assertEquals(2L, sipKeepAlive.getDeltaTime());
    assertFalse(sipKeepAlive.stop);

    assertEquals(0L, sipKeepAlive.expire);
    assertEquals(2, sipKeepAlive.sip_provider.transport_protocols.length);
    assertFalse(BasicSipMessage.DEBUG);
  }

  @Test
  void testSendToken5() throws IOException {
    // Arrange
    SipKeepAlive sipKeepAlive = new SipKeepAlive(
        new SipProvider("42 Main St", 8080, new String[]{"foo", "foo", "foo"}, new int[]{1, 1, 1, 1, 1, 1, 1, 1}), null,
        2L);

    // Act
    sipKeepAlive.sendToken();

    // Assert that nothing has changed
    assertEquals(2L, sipKeepAlive.getDeltaTime());
    assertFalse(sipKeepAlive.stop);
    assertEquals(0L, sipKeepAlive.expire);
    assertFalse(BasicSipMessage.DEBUG);
  }

  @Test
  void testSendToken6() throws IOException {
    // Arrange
    SipProvider sip_provider = new SipProvider(null);

    SipKeepAlive sipKeepAlive = new SipKeepAlive(sip_provider, new SocketAddress("42 Main St"), 2L);
    sipKeepAlive.setUncaughtExceptionHandler(null);

    // Act
    sipKeepAlive.sendToken();

    // Assert that nothing has changed
    assertEquals(2L, sipKeepAlive.getDeltaTime());
    assertFalse(sipKeepAlive.stop);

    assertEquals(0L, sipKeepAlive.expire);
    assertEquals(2, sipKeepAlive.sip_provider.transport_protocols.length);
    assertFalse(BasicSipMessage.DEBUG);
  }

  @Test
  void testSendToken7() throws IOException {
    // Arrange
    SipProvider sipProvider = new SipProvider("File");
    sipProvider.addSelectiveListener(new MethodId("Method"), mock(SipProviderListener.class));

    SipKeepAlive sipKeepAlive = new SipKeepAlive(sipProvider, new SocketAddress("42 Main St"), 2L);
    sipKeepAlive.setUncaughtExceptionHandler(null);

    // Act
    sipKeepAlive.sendToken();

    // Assert that nothing has changed
    assertEquals(2L, sipKeepAlive.getDeltaTime());
    assertFalse(sipKeepAlive.stop);

    assertEquals(0L, sipKeepAlive.expire);
    assertEquals(2, sipKeepAlive.sip_provider.transport_protocols.length);
    assertFalse(BasicSipMessage.DEBUG);
  }

  @Test
  void testSendToken8() throws IOException {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");

    SipKeepAlive sipKeepAlive = new SipKeepAlive(sip_provider, new SocketAddress("42 Main St"), 0L);
    sipKeepAlive.setUncaughtExceptionHandler(null);

    // Act
    sipKeepAlive.sendToken();

    // Assert that nothing has changed
    assertEquals(0L, sipKeepAlive.getDeltaTime());
    assertFalse(sipKeepAlive.stop);

    assertEquals(0L, sipKeepAlive.expire);
    assertEquals(2, sipKeepAlive.sip_provider.transport_protocols.length);
    assertFalse(BasicSipMessage.DEBUG);
  }

  @Test
  void testSendToken11() throws IOException {
    // Arrange
    SipProvider sip_provider = new SipProvider(SipProvider.AUTO_CONFIGURATION);

    SipKeepAlive sipKeepAlive = new SipKeepAlive(sip_provider, new SocketAddress("42 Main St"), 2L);
    sipKeepAlive.setUncaughtExceptionHandler(null);

    // Act
    sipKeepAlive.sendToken();

    // Assert that nothing has changed
    assertEquals(2L, sipKeepAlive.getDeltaTime());
    assertFalse(sipKeepAlive.stop);

    assertEquals(0L, sipKeepAlive.expire);
    assertEquals(2, sipKeepAlive.sip_provider.transport_protocols.length);
    assertFalse(BasicSipMessage.DEBUG);
  }

  @Test
  void testSendToken12() throws IOException {
    // Arrange
    SipProvider sipProvider = new SipProvider("File");
    sipProvider.setTelGateway(null);

    SipKeepAlive sipKeepAlive = new SipKeepAlive(sipProvider, new SocketAddress("42 Main St"), 2L);
    sipKeepAlive.setUncaughtExceptionHandler(null);

    // Act
    sipKeepAlive.sendToken();

    // Assert that nothing has changed
    assertEquals(2L, sipKeepAlive.getDeltaTime());
    assertFalse(sipKeepAlive.stop);

    assertEquals(0L, sipKeepAlive.expire);
    assertEquals(2, sipKeepAlive.sip_provider.transport_protocols.length);
    assertFalse(BasicSipMessage.DEBUG);
  }

  @Test
  void testSendToken13() throws IOException {
    // Arrange
    SipProvider sipProvider = new SipProvider("File");
    sipProvider.setNMaxConnections(0);

    SipKeepAlive sipKeepAlive = new SipKeepAlive(sipProvider, new SocketAddress("42 Main St"), 2L);
    sipKeepAlive.setUncaughtExceptionHandler(null);

    // Act
    sipKeepAlive.sendToken();

    // Assert that nothing has changed
    assertEquals(2L, sipKeepAlive.getDeltaTime());
    assertFalse(sipKeepAlive.stop);

    assertEquals(0L, sipKeepAlive.expire);
    assertEquals(2, sipKeepAlive.sip_provider.transport_protocols.length);
    assertFalse(BasicSipMessage.DEBUG);
  }

  @Test
  void testSendToken14() throws IOException {
    // Arrange
    SipProvider sip_provider = new SipProvider("42 Main St", 8080, new String[]{"foo", "foo", "foo"},
        new int[]{1, 1, 1, 1, 1, 1, 1, 1});

    SipKeepAlive sipKeepAlive = new SipKeepAlive(sip_provider, new SocketAddress("42 Main St"), 2L);
    sipKeepAlive.setUncaughtExceptionHandler(null);

    // Act
    sipKeepAlive.sendToken();

    // Assert that nothing has changed
    assertEquals(2L, sipKeepAlive.getDeltaTime());
    assertFalse(sipKeepAlive.stop);
    assertEquals("sip:42 Main St:8080-->42 Main St:-1 (2ms)", sipKeepAlive.toString());
    assertEquals(0L, sipKeepAlive.expire);
    assertEquals(3, sipKeepAlive.sip_provider.transport_protocols.length);
    assertFalse(BasicSipMessage.DEBUG);
  }

  @Test
  void testSendToken15() throws IOException {
    // Arrange
    SipKeepAlive sipKeepAlive = new SipKeepAlive(null, new SocketAddress("42 Main St"), 0L);
    sipKeepAlive.setUncaughtExceptionHandler(null);

    // Act
    sipKeepAlive.sendToken();

    // Assert that nothing has changed
    assertEquals(0L, sipKeepAlive.getDeltaTime());
    assertFalse(sipKeepAlive.stop);
    SocketAddress expectedDestSoAddress = sipKeepAlive.target;
    assertSame(expectedDestSoAddress, sipKeepAlive.getDestSoAddress());
    assertEquals(0L, sipKeepAlive.expire);
    assertFalse(BasicSipMessage.DEBUG);
  }

  @Test
  void testSendToken17() throws IOException {
    // Arrange
    SipKeepAlive sipKeepAlive = new SipKeepAlive(null, new SocketAddress("42 Main St"), -1L);
    sipKeepAlive.setUncaughtExceptionHandler(null);

    // Act
    sipKeepAlive.sendToken();

    // Assert that nothing has changed
    assertEquals(-1L, sipKeepAlive.getDeltaTime());
    assertFalse(sipKeepAlive.stop);
    SocketAddress expectedDestSoAddress = sipKeepAlive.target;
    assertSame(expectedDestSoAddress, sipKeepAlive.getDestSoAddress());
    assertEquals(0L, sipKeepAlive.expire);
    assertFalse(BasicSipMessage.DEBUG);
  }

  @Test
  void testSendToken19() throws IOException {
    // Arrange
    SipProvider sipProvider = new SipProvider("File");

    SipKeepAlive sipKeepAlive = new SipKeepAlive(sipProvider, new SocketAddress("42 Main St", 8080), Long.MAX_VALUE);
    sipKeepAlive.setContextClassLoader(null);

    // Act
    sipKeepAlive.sendToken();

    // Assert that nothing has changed
    assertFalse(sipKeepAlive.stop);
    assertEquals(Long.MAX_VALUE, sipKeepAlive.getDeltaTime());

    assertEquals(0L, sipKeepAlive.expire);
    assertEquals(sipProvider.promiscuous_listeners, sipKeepAlive.sip_provider.exception_listeners);
    assertFalse(BasicSipMessage.DEBUG);
  }

  @Test
  void testToString3() {
    // Arrange, Act and Assert
    assertEquals("null (2ms)", (new SipKeepAlive(null, new SocketAddress("42 Main St"), 2L)).toString());
  }

  @Test
  void testToString5() {
    // Arrange, Act and Assert
    assertEquals("null (2ms)", (new SipKeepAlive(null, null, 2L)).toString());
  }

  @Test
  void testToString6() {
    // Arrange, Act and Assert
    assertEquals("null (2ms)", (new SipKeepAlive(null, new SocketAddress("Soaddr"), 2L)).toString());
  }

  @Test
  void testToString7() {
    // Arrange, Act and Assert
    assertEquals("null (0ms)", (new SipKeepAlive(null, new SocketAddress("42 Main St"), 0L)).toString());
  }

  @Test
  void testToString8() {
    // Arrange, Act and Assert
    assertEquals("null (-1ms)", (new SipKeepAlive(null, new SocketAddress("42 Main St"), -1L)).toString());
  }

  @Test
  void testToString16() {
    // Arrange
    SipProvider sip_provider = new SipProvider("42 Main St", 8080, new String[]{"foo", "foo", "foo"});

    SipKeepAlive sipKeepAlive = new SipKeepAlive(sip_provider, new SocketAddress("42 Main St"), 2L);
    sipKeepAlive.setUncaughtExceptionHandler(null);

    // Act and Assert
    assertEquals("sip:42 Main St:8080-->42 Main St:-1 (2ms)", sipKeepAlive.toString());
  }

  @Test
  void testToString17() {
    // Arrange
    SipProvider sip_provider = new SipProvider("42 Main St", 8080, new String[]{"foo", "foo", "foo"},
        new int[]{1, 1, 1, 1, 1, 1, 1, 1});

    // Act and Assert
    assertEquals("sip:42 Main St:8080-->Soaddr:-1 (2ms)",
        (new SipKeepAlive(sip_provider, new SocketAddress("Soaddr"), 2L)).toString());
  }

  @Test
  void testToString18() {
    // Arrange
    SipKeepAlive sipKeepAlive = new SipKeepAlive(null, new SocketAddress("42 Main St"), 0L);
    sipKeepAlive.setContextClassLoader(null);

    // Act and Assert
    assertEquals("null (0ms)", sipKeepAlive.toString());
  }

  @Test
  void testToString19() {
    // Arrange
    SipKeepAlive sipKeepAlive = new SipKeepAlive(null, new SocketAddress("42 Main St"), 0L);
    sipKeepAlive.setUncaughtExceptionHandler(null);

    // Act and Assert
    assertEquals("null (0ms)", sipKeepAlive.toString());
  }

  @Test
  void testToString20() {
    // Arrange
    SipKeepAlive sipKeepAlive = new SipKeepAlive(null, new SocketAddress("42 Main St"), -1L);
    sipKeepAlive.setExpirationTime(10L);

    // Act and Assert
    assertEquals("null (-1ms)", sipKeepAlive.toString());
  }

  @Test
  void testToString23() {
    // Arrange
    SipProvider sip_provider = new SipProvider("42 Main St", 8080);

    SipKeepAlive sipKeepAlive = new SipKeepAlive(sip_provider, new SocketAddress("42"), 2L);
    sipKeepAlive.setUncaughtExceptionHandler(null);

    // Act and Assert
    assertEquals("sip:42 Main St:8080-->42:-1 (2ms)", sipKeepAlive.toString());
  }

  @Test
  void testToString28() {
    // Arrange
    SipProvider sip_provider = new SipProvider("Via addr", 8080, new String[]{"foo", "foo", "foo"});

    SipKeepAlive sipKeepAlive = new SipKeepAlive(sip_provider, new SocketAddress("42 Main St"), 2L);
    sipKeepAlive.setUncaughtExceptionHandler(null);

    // Act and Assert
    assertEquals("sip:Via addr:8080-->42 Main St:-1 (2ms)", sipKeepAlive.toString());
  }

  @Test
  void testToString29() {
    // Arrange
    SipProvider sip_provider = new SipProvider("42 Main St", Integer.MIN_VALUE, new String[]{"foo", "foo", "foo"});

    SipKeepAlive sipKeepAlive = new SipKeepAlive(sip_provider, new SocketAddress("42 Main St"), 2L);
    sipKeepAlive.setUncaughtExceptionHandler(null);

    // Act and Assert
    assertEquals("sip:42 Main St:5060-->42 Main St:-1 (2ms)", sipKeepAlive.toString());
  }

  @Test
  void testToString30() {
    // Arrange
    SipProvider sip_provider = new SipProvider("42 Main St", 8080, new String[]{"foo", null, "foo"});

    SipKeepAlive sipKeepAlive = new SipKeepAlive(sip_provider, new SocketAddress("42 Main St"), 2L);
    sipKeepAlive.setUncaughtExceptionHandler(null);

    // Act and Assert
    assertEquals("sip:42 Main St:8080-->42 Main St:-1 (2ms)", sipKeepAlive.toString());
  }

  @Test
  void testToString31() {
    // Arrange
    SipProvider sipProvider = new SipProvider("42 Main St", 8080, new String[]{"foo", "foo", "foo"});
    sipProvider.addExceptionListener(mock(SipProviderExceptionListener.class));

    SipKeepAlive sipKeepAlive = new SipKeepAlive(sipProvider, new SocketAddress("42 Main St"), 2L);
    sipKeepAlive.setUncaughtExceptionHandler(null);

    // Act and Assert
    assertEquals("sip:42 Main St:8080-->42 Main St:-1 (2ms)", sipKeepAlive.toString());
  }

  @Test
  void testToString32() {
    // Arrange
    SipProvider sipProvider = new SipProvider("42 Main St", 8080, new String[]{"foo", "foo", "foo"});
    sipProvider.addSelectiveListener(new MethodId("42"), mock(SipProviderListener.class));

    SipKeepAlive sipKeepAlive = new SipKeepAlive(sipProvider, new SocketAddress("42 Main St"), 2L);
    sipKeepAlive.setUncaughtExceptionHandler(null);

    // Act and Assert
    assertEquals("sip:42 Main St:8080-->42 Main St:-1 (2ms)", sipKeepAlive.toString());
  }

  @Test
  void testToString33() {
    // Arrange
    SipProvider sipProvider = new SipProvider("42 Main St", 8080, new String[]{"foo", "foo", "foo"});
    sipProvider.addSelectiveListener(new MethodId(""), mock(SipProviderListener.class));

    SipKeepAlive sipKeepAlive = new SipKeepAlive(sipProvider, new SocketAddress("42 Main St"), 2L);
    sipKeepAlive.setUncaughtExceptionHandler(null);

    // Act and Assert
    assertEquals("sip:42 Main St:8080-->42 Main St:-1 (2ms)", sipKeepAlive.toString());
  }

  @Test
  void testToString34() {
    // Arrange
    SipProvider sipProvider = new SipProvider("42 Main St", 8080, new String[]{"foo", "foo", "foo"});
    sipProvider.setDefaultTransport("alice.liddell@example.org");
    sipProvider.addExceptionListener(mock(SipProviderExceptionListener.class));

    SipKeepAlive sipKeepAlive = new SipKeepAlive(sipProvider, new SocketAddress("42 Main St"), 2L);
    sipKeepAlive.setUncaughtExceptionHandler(null);

    // Act and Assert
    assertEquals("sip:42 Main St:8080-->42 Main St:-1 (2ms)", sipKeepAlive.toString());
  }
}

