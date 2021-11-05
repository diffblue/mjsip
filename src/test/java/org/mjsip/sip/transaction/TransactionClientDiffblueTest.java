package org.mjsip.sip.transaction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import java.io.UnsupportedEncodingException;
import java.util.Vector;
import org.junit.jupiter.api.Test;
import org.mjsip.server.ServerProfile;
import org.mjsip.server.StatefulProxy;
import org.mjsip.sip.header.StatusLine;
import org.mjsip.sip.message.SipMessage;
import org.mjsip.sip.provider.SipProvider;
import org.mjsip.sip.provider.TransactionClientId;
import org.zoolu.util.LogLevel;
import org.zoolu.util.Timer;
import org.zoolu.util.TimerListener;

class TransactionClientDiffblueTest {
  @Test
  void testChangeStatus() {
    // Arrange
    TransactionClient transactionClient = new TransactionClient(new SipProvider("File"));

    // Act
    transactionClient.changeStatus(1);

    // Assert
    assertEquals(1, transactionClient.status);
  }

  @Test
  void testChangeStatus2() {
    // Arrange
    TransactionClient transactionClient = new TransactionClient(new SipProvider("File"));

    // Act
    transactionClient.changeStatus(0);

    // Assert
    assertEquals(0, transactionClient.status);
  }

  @Test
  void testChangeStatus3() {
    // Arrange
    TransactionClient transactionClient = new TransactionClient(new SipProvider("File"));

    // Act
    transactionClient.changeStatus(2);

    // Assert
    assertEquals(2, transactionClient.status);
  }

  @Test
  void testChangeStatus4() {
    // Arrange
    TransactionClient transactionClient = new TransactionClient(new SipProvider("File"));

    // Act
    transactionClient.changeStatus(3);

    // Assert
    assertEquals(3, transactionClient.status);
  }

  @Test
  void testChangeStatus5() {
    // Arrange
    TransactionClient transactionClient = new TransactionClient(new SipProvider("File"));

    // Act
    transactionClient.changeStatus(4);

    // Assert
    assertEquals(4, transactionClient.status);
  }

  @Test
  void testChangeStatus6() {
    // Arrange
    TransactionClient transactionClient = new TransactionClient(new SipProvider("File"));

    // Act
    transactionClient.changeStatus(5);

    // Assert
    assertEquals(5, transactionClient.status);
  }

  @Test
  void testChangeStatus7() {
    // Arrange
    TransactionClient transactionClient = new TransactionClient(new SipProvider("File"));

    // Act
    transactionClient.changeStatus(6);

    // Assert
    assertEquals(6, transactionClient.status);
  }

  @Test
  void testChangeStatus8() {
    // Arrange
    TransactionClient transactionClient = new TransactionClient(new SipProvider("File"));

    // Act
    transactionClient.changeStatus(7);

    // Assert
    assertEquals(7, transactionClient.status);
  }

  @Test
  void testConstructor() {
    // Arrange and Act
    TransactionClient actualTransactionClient = new TransactionClient(new SipProvider("File"));

    // Assert
    assertNull(actualTransactionClient.getRequestMessage());
    assertNull(actualTransactionClient.transaction_listener);
    assertEquals(0, actualTransactionClient.status);
    assertNull(actualTransactionClient.logger);
    assertNull(actualTransactionClient.getTransportConnId());
    assertNull(actualTransactionClient.getTransactionId());
    SipProvider expectedSipProvider = actualTransactionClient.sip_provider;
    assertSame(expectedSipProvider, actualTransactionClient.getSipProvider());
  }

  @Test
  void testInit() {
    // Arrange
    TransactionClient transactionClient = new TransactionClient(new SipProvider("File"));
    SipProvider provider = new SipProvider("File");
    StatefulProxy statefulProxy = new StatefulProxy(provider, new ServerProfile("File"));

    TransactionClientId transactionClientId = new TransactionClientId("Method");

    // Act
    transactionClient.init(statefulProxy, transactionClientId);

    // Assert
    assertSame(transactionClientId, transactionClient.getTransactionId());
    assertSame(statefulProxy, transactionClient.transaction_listener);
    assertTrue(Timer.DEFAULT_DAEMON_MODE);
    Timer timer = transactionClient.transaction_to;
    assertEquals(32000L, timer.getTime());
    assertFalse(timer.isRunning());
    Timer timer1 = transactionClient.retransmission_to;
    assertFalse(timer1.isRunning());
    Timer timer2 = transactionClient.clearing_to;
    assertEquals(5000L, timer2.getTime());
    assertFalse(timer2.isRunning());
    assertEquals(500L, timer1.getTime());
  }

  @Test
  void testOnReceivedMessage() {
    // Arrange
    TransactionClient transactionClient = new TransactionClient(new SipProvider("File"));
    SipProvider sipProvider = new SipProvider("File");

    // Act
    transactionClient.onReceivedMessage(sipProvider, new SipMessage("Str"));

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
    assertEquals(0, transactionClient.status);
  }

  @Test
  void testOnReceivedMessage2() throws UnsupportedEncodingException {
    // Arrange
    TransactionClient transactionClient = new TransactionClient(new SipProvider("File"));
    SipProvider sipProvider = new SipProvider("File");
    StatusLine status_line = new StatusLine(1, "foo");

    Vector headers = new Vector(1);

    // Act
    transactionClient.onReceivedMessage(sipProvider,
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
    assertEquals(0, transactionClient.status);
  }

  @Test
  void testOnReceivedMessage3() throws UnsupportedEncodingException {
    // Arrange
    TransactionClient transactionClient = new TransactionClient(new SipProvider("File"));
    SipProvider sipProvider = new SipProvider("File");
    StatusLine status_line = new StatusLine(100, "foo");

    Vector headers = new Vector(1);

    // Act
    transactionClient.onReceivedMessage(sipProvider,
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
    assertEquals(0, transactionClient.status);
  }

  @Test
  void testOnReceivedMessage4() throws UnsupportedEncodingException {
    // Arrange
    TransactionClient transactionClient = new TransactionClient(new SipProvider("File"));
    SipProvider sipProvider = new SipProvider("File");
    StatusLine status_line = new StatusLine(200, "foo");

    Vector headers = new Vector(1);

    // Act
    transactionClient.onReceivedMessage(sipProvider,
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
    assertEquals(0, transactionClient.status);
  }

  @Test
  void testOnTimeout() {
    // Arrange
    TransactionClient transactionClient = new TransactionClient(new SipProvider("File"));
    Timer timer = new Timer(10L, mock(TimerListener.class));

    // Act
    transactionClient.onTimeout(timer);

    // Assert that nothing has changed
    assertFalse(timer.isRunning());
    assertEquals(10L, timer.getTime());
    assertEquals(0, transactionClient.status);
    SipProvider expectedSipProvider = transactionClient.sip_provider;
    assertSame(expectedSipProvider, transactionClient.getSipProvider());
  }

  @Test
  void testOnTimeout2() {
    // Arrange
    TransactionClient transactionClient = new TransactionClient(new SipProvider("File"));

    // Act
    transactionClient.onTimeout(null);

    // Assert that nothing has changed
    assertEquals(0, transactionClient.status);
    SipProvider expectedSipProvider = transactionClient.sip_provider;
    assertSame(expectedSipProvider, transactionClient.getSipProvider());
  }

  @Test
  void testLog() {
    // Arrange
    TransactionClient transactionClient = new TransactionClient(new SipProvider("File"));
    LogLevel logLevel = new LogLevel("Name", 42);

    // Act
    transactionClient.log(logLevel, new Exception("An error occurred"));

    // Assert that nothing has changed
    assertEquals("Name", logLevel.getName());
    assertEquals(42, logLevel.getValue());
    assertEquals(0, transactionClient.status);
    SipProvider expectedSipProvider = transactionClient.sip_provider;
    assertSame(expectedSipProvider, transactionClient.getSipProvider());
  }

  @Test
  void testLog2() {
    // Arrange
    TransactionClient transactionClient = new TransactionClient(new SipProvider("File"));
    LogLevel logLevel = new LogLevel("Name", 42);

    // Act
    transactionClient.log(logLevel, "Str");

    // Assert that nothing has changed
    assertEquals("Name", logLevel.getName());
    assertEquals(42, logLevel.getValue());
    assertEquals(0, transactionClient.status);
    SipProvider expectedSipProvider = transactionClient.sip_provider;
    assertSame(expectedSipProvider, transactionClient.getSipProvider());
  }

  @Test
  void testGetRequestMessage() {
    // Arrange, Act and Assert
    assertNull((new TransactionClient(new SipProvider("File"))).getRequestMessage());
  }

  @Test
  void testGetSipProvider() {
    // Arrange
    TransactionClient transactionClient = new TransactionClient(new SipProvider("File"));

    // Act and Assert
    assertSame(transactionClient.sip_provider, transactionClient.getSipProvider());
  }

  @Test
  void testGetStatus() {
    // Arrange, Act and Assert
    assertEquals("T_Idle", (new TransactionClient(new SipProvider("File"))).getStatus());
  }

  @Test
  void testGetTransactionId() {
    // Arrange, Act and Assert
    assertNull((new TransactionClient(new SipProvider("File"))).getTransactionId());
  }

  @Test
  void testGetTransportConnId() {
    // Arrange, Act and Assert
    assertNull((new TransactionClient(new SipProvider("File"))).getTransportConnId());
  }

  @Test
  void testIsCompleted() {
    // Arrange, Act and Assert
    assertFalse((new TransactionClient(new SipProvider("File"))).isCompleted());
  }

  @Test
  void testIsProceeding() {
    // Arrange, Act and Assert
    assertFalse((new TransactionClient(new SipProvider("File"))).isProceeding());
  }

  @Test
  void testIsTerminated() {
    // Arrange, Act and Assert
    assertFalse((new TransactionClient(new SipProvider("File"))).isTerminated());
  }

  @Test
  void testIsTrying() {
    // Arrange, Act and Assert
    assertFalse((new TransactionClient(new SipProvider("File"))).isTrying());
  }

  @Test
  void testStatusIs() {
    // Arrange, Act and Assert
    assertFalse((new TransactionClient(new SipProvider("File"))).statusIs(1));
    assertTrue((new TransactionClient(new SipProvider("File"))).statusIs(0));
  }
}

