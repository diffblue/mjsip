package org.mjsip.sip.transaction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.Test;
import org.mjsip.sip.message.SipMessage;
import org.mjsip.sip.provider.ConnectionId;
import org.mjsip.sip.provider.MethodId;
import org.mjsip.sip.provider.SipProvider;
import org.mjsip.sip.provider.SipProviderListener;
import org.mjsip.sip.provider.TransactionId;
import org.mjsip.sip.provider.TransactionServerId;
import org.zoolu.net.SocketAddress;
import org.zoolu.util.LogLevel;
import org.zoolu.util.Timer;
import org.zoolu.util.TimerListener;

class TransactionServerDiffblueTest {
  @Test
  void testChangeStatus() {
    // Arrange
    TransactionServer transactionServer = new TransactionServer(new SipProvider("File"));

    // Act
    transactionServer.changeStatus(1);

    // Assert
    assertEquals(1, transactionServer.status);
  }

  @Test
  void testChangeStatus2() {
    // Arrange
    TransactionServer transactionServer = new TransactionServer(new SipProvider("File"));

    // Act
    transactionServer.changeStatus(0);

    // Assert
    assertEquals(0, transactionServer.status);
  }

  @Test
  void testChangeStatus3() {
    // Arrange
    TransactionServer transactionServer = new TransactionServer(new SipProvider("File"));

    // Act
    transactionServer.changeStatus(2);

    // Assert
    assertEquals(2, transactionServer.status);
  }

  @Test
  void testChangeStatus4() {
    // Arrange
    TransactionServer transactionServer = new TransactionServer(new SipProvider("File"));

    // Act
    transactionServer.changeStatus(3);

    // Assert
    assertEquals(3, transactionServer.status);
  }

  @Test
  void testChangeStatus5() {
    // Arrange
    TransactionServer transactionServer = new TransactionServer(new SipProvider("File"));

    // Act
    transactionServer.changeStatus(4);

    // Assert
    assertEquals(4, transactionServer.status);
  }

  @Test
  void testChangeStatus6() {
    // Arrange
    TransactionServer transactionServer = new TransactionServer(new SipProvider("File"));

    // Act
    transactionServer.changeStatus(5);

    // Assert
    assertEquals(5, transactionServer.status);
  }

  @Test
  void testChangeStatus7() {
    // Arrange
    TransactionServer transactionServer = new TransactionServer(new SipProvider("File"));

    // Act
    transactionServer.changeStatus(6);

    // Assert
    assertEquals(6, transactionServer.status);
  }

  @Test
  void testChangeStatus8() {
    // Arrange
    TransactionServer transactionServer = new TransactionServer(new SipProvider("File"));

    // Act
    transactionServer.changeStatus(7);

    // Assert
    assertEquals(7, transactionServer.status);
  }

  @Test
  void testConstructor() {
    // Arrange and Act
    TransactionServer actualTransactionServer = new TransactionServer(new SipProvider("File"));

    // Assert
    assertNull(actualTransactionServer.getRequestMessage());
    assertNull(actualTransactionServer.transaction_listener);
    assertEquals(0, actualTransactionServer.status);
    assertNull(actualTransactionServer.response);
    assertNull(actualTransactionServer.logger);
    assertNull(actualTransactionServer.getTransportConnId());
    assertNull(actualTransactionServer.getTransactionId());
    SipProvider expectedSipProvider = actualTransactionServer.sip_provider;
    assertSame(expectedSipProvider, actualTransactionServer.getSipProvider());
  }

  @Test
  void testConstructor2() {
    // Arrange and Act
    TransactionServer actualTransactionServer = new TransactionServer(new SipProvider("File"), "Method",
        mock(TransactionServerListener.class));

    // Assert
    assertNull(actualTransactionServer.getRequestMessage());
    assertEquals(0, actualTransactionServer.status);
    assertNull(actualTransactionServer.response);
    assertNull(actualTransactionServer.logger);
    SipProvider expectedSipProvider = actualTransactionServer.sip_provider;
    assertSame(expectedSipProvider, actualTransactionServer.getSipProvider());
    TransactionId expectedTransactionId = actualTransactionServer.transaction_id;
    TransactionId transactionId = actualTransactionServer.getTransactionId();
    assertSame(expectedTransactionId, transactionId);
    assertNull(actualTransactionServer.getTransportConnId());
    assertEquals("Method", transactionId.toString());
    Timer timer = actualTransactionServer.clearing_to;
    assertFalse(timer.isRunning());
    assertTrue(Timer.DEFAULT_DAEMON_MODE);
    assertEquals(32000L, timer.getTime());
  }

  @Test
  void testConstructor3() {
    // Arrange and Act
    TransactionServer actualTransactionServer = new TransactionServer(new SipProvider("42 Main St", 8080), "Method",
        mock(TransactionServerListener.class));

    // Assert
    assertNull(actualTransactionServer.getRequestMessage());
    assertEquals(0, actualTransactionServer.status);
    assertNull(actualTransactionServer.response);
    assertNull(actualTransactionServer.logger);
    SipProvider expectedSipProvider = actualTransactionServer.sip_provider;
    assertSame(expectedSipProvider, actualTransactionServer.getSipProvider());
    TransactionId expectedTransactionId = actualTransactionServer.transaction_id;
    TransactionId transactionId = actualTransactionServer.getTransactionId();
    assertSame(expectedTransactionId, transactionId);
    assertNull(actualTransactionServer.getTransportConnId());
    assertEquals("Method", transactionId.toString());
    Timer timer = actualTransactionServer.clearing_to;
    assertFalse(timer.isRunning());
    assertTrue(Timer.DEFAULT_DAEMON_MODE);
    assertEquals(32000L, timer.getTime());
  }

  @Test
  void testInit() {
    // Arrange
    TransactionServer transactionServer = new TransactionServer(new SipProvider("File"));
    TransactionServerListener listener = mock(TransactionServerListener.class);
    TransactionServerId transactionServerId = new TransactionServerId("Method");
    ConnectionId connectionId = new ConnectionId("Protocol", new SocketAddress("42 Main St"));

    // Act
    transactionServer.init(listener, transactionServerId, connectionId);

    // Assert
    assertNull(transactionServer.response);
    assertSame(transactionServerId, transactionServer.getTransactionId());
    assertSame(connectionId, transactionServer.getTransportConnId());
    Timer timer = transactionServer.clearing_to;
    assertFalse(timer.isRunning());
    assertTrue(Timer.DEFAULT_DAEMON_MODE);
    assertEquals(32000L, timer.getTime());
  }

  @Test
  void testListen() {
    // Arrange
    TransactionServer transactionServer = new TransactionServer(new SipProvider("File"), "Method",
        mock(TransactionServerListener.class));

    // Act
    transactionServer.listen();

    // Assert
    assertEquals(1, transactionServer.status);
    assertEquals(1, transactionServer.getSipProvider().getListeners().size());
  }

  @Test
  void testListen2() {
    // Arrange
    SipProvider sipProvider = new SipProvider("File");
    sipProvider.addSelectiveListener(new MethodId("Method"), mock(SipProviderListener.class));
    TransactionServer transactionServer = new TransactionServer(sipProvider, "Method",
        mock(TransactionServerListener.class));

    // Act
    transactionServer.listen();

    // Assert
    assertEquals(1, transactionServer.status);
    assertEquals(1, transactionServer.getSipProvider().getListeners().size());
  }

  @Test
  void testRespondWith() {
    // Arrange
    TransactionServer transactionServer = new TransactionServer(new SipProvider("File"));
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    transactionServer.respondWith(sipMessage);

    // Assert
    assertNull(sipMessage.getConnectionId());
    assertSame(sipMessage, transactionServer.response);
  }

  @Test
  void testTerminate() {
    // Arrange
    TransactionServer transactionServer = new TransactionServer(new SipProvider("File"), "Method",
        mock(TransactionServerListener.class));

    // Act
    transactionServer.terminate();

    // Assert
    assertNull(transactionServer.transaction_listener);
    assertEquals(7, transactionServer.status);
  }

  @Test
  void testTerminate2() {
    // Arrange
    SipProvider sipProvider = new SipProvider("File");
    sipProvider.addSelectiveListener(new MethodId("Method"), mock(SipProviderListener.class));
    TransactionServer transactionServer = new TransactionServer(sipProvider, "Method",
        mock(TransactionServerListener.class));

    // Act
    transactionServer.terminate();

    // Assert
    assertNull(transactionServer.transaction_listener);
    assertEquals(7, transactionServer.status);
    assertTrue(transactionServer.getSipProvider().getListeners().isEmpty());
  }

  @Test
  void testOnReceivedMessage() {
    // Arrange
    TransactionServer transactionServer = new TransactionServer(new SipProvider("File"));
    SipProvider sipProvider = new SipProvider("File");

    // Act
    transactionServer.onReceivedMessage(sipProvider, new SipMessage("Str"));

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
    assertEquals(0, transactionServer.status);
  }

  @Test
  void testOnReceivedMessage2() {
    // Arrange
    TransactionServer transactionServer = new TransactionServer(new SipProvider("File"));
    SipProvider sipProvider = new SipProvider("File");

    // Act
    transactionServer.onReceivedMessage(sipProvider, new SipMessage("Accept"));

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
    assertEquals(0, transactionServer.status);
  }

  @Test
  void testOnTimeout() {
    // Arrange
    TransactionServer transactionServer = new TransactionServer(new SipProvider("File"));
    Timer timer = new Timer(10L, mock(TimerListener.class));

    // Act
    transactionServer.onTimeout(timer);

    // Assert that nothing has changed
    assertFalse(timer.isRunning());
    assertEquals(10L, timer.getTime());
    assertEquals(0, transactionServer.status);
    SipProvider expectedSipProvider = transactionServer.sip_provider;
    assertSame(expectedSipProvider, transactionServer.getSipProvider());
  }

  @Test
  void testOnTimeout2() {
    // Arrange
    TransactionServer transactionServer = new TransactionServer(new SipProvider("File"));

    // Act
    transactionServer.onTimeout(null);

    // Assert that nothing has changed
    assertEquals(0, transactionServer.status);
    SipProvider expectedSipProvider = transactionServer.sip_provider;
    assertSame(expectedSipProvider, transactionServer.getSipProvider());
  }

  @Test
  void testDoTerminate() {
    // Arrange
    TransactionServer transactionServer = new TransactionServer(new SipProvider("File"), "Method",
        mock(TransactionServerListener.class));

    // Act
    transactionServer.doTerminate();

    // Assert
    assertEquals(7, transactionServer.status);
  }

  @Test
  void testDoTerminate2() {
    // Arrange
    SipProvider sipProvider = new SipProvider("File");
    sipProvider.addSelectiveListener(new MethodId("Method"), mock(SipProviderListener.class));
    TransactionServer transactionServer = new TransactionServer(sipProvider, "Method",
        mock(TransactionServerListener.class));

    // Act
    transactionServer.doTerminate();

    // Assert
    assertEquals(7, transactionServer.status);
    assertTrue(transactionServer.getSipProvider().getListeners().isEmpty());
  }

  @Test
  void testLog() {
    // Arrange
    TransactionServer transactionServer = new TransactionServer(new SipProvider("File"));
    LogLevel logLevel = new LogLevel("Name", 42);

    // Act
    transactionServer.log(logLevel, new Exception("An error occurred"));

    // Assert that nothing has changed
    assertEquals("Name", logLevel.getName());
    assertEquals(42, logLevel.getValue());
    assertEquals(0, transactionServer.status);
    SipProvider expectedSipProvider = transactionServer.sip_provider;
    assertSame(expectedSipProvider, transactionServer.getSipProvider());
  }

  @Test
  void testLog2() {
    // Arrange
    TransactionServer transactionServer = new TransactionServer(new SipProvider("File"));
    LogLevel logLevel = new LogLevel("Name", 42);

    // Act
    transactionServer.log(logLevel, "Str");

    // Assert that nothing has changed
    assertEquals("Name", logLevel.getName());
    assertEquals(42, logLevel.getValue());
    assertEquals(0, transactionServer.status);
    SipProvider expectedSipProvider = transactionServer.sip_provider;
    assertSame(expectedSipProvider, transactionServer.getSipProvider());
  }

  @Test
  void testGetRequestMessage() {
    // Arrange, Act and Assert
    assertNull((new TransactionServer(new SipProvider("File"))).getRequestMessage());
  }

  @Test
  void testGetSipProvider() {
    // Arrange
    TransactionServer transactionServer = new TransactionServer(new SipProvider("File"));

    // Act and Assert
    assertSame(transactionServer.sip_provider, transactionServer.getSipProvider());
  }

  @Test
  void testGetStatus() {
    // Arrange, Act and Assert
    assertEquals("T_Idle", (new TransactionServer(new SipProvider("File"))).getStatus());
  }

  @Test
  void testGetTransactionId() {
    // Arrange, Act and Assert
    assertNull((new TransactionServer(new SipProvider("File"))).getTransactionId());
  }

  @Test
  void testGetTransportConnId() {
    // Arrange, Act and Assert
    assertNull((new TransactionServer(new SipProvider("File"))).getTransportConnId());
  }

  @Test
  void testIsCompleted() {
    // Arrange, Act and Assert
    assertFalse((new TransactionServer(new SipProvider("File"))).isCompleted());
  }

  @Test
  void testIsProceeding() {
    // Arrange, Act and Assert
    assertFalse((new TransactionServer(new SipProvider("File"))).isProceeding());
  }

  @Test
  void testIsTerminated() {
    // Arrange, Act and Assert
    assertFalse((new TransactionServer(new SipProvider("File"))).isTerminated());
  }

  @Test
  void testIsTrying() {
    // Arrange, Act and Assert
    assertFalse((new TransactionServer(new SipProvider("File"))).isTrying());
  }

  @Test
  void testStatusIs() {
    // Arrange, Act and Assert
    assertFalse((new TransactionServer(new SipProvider("File"))).statusIs(1));
    assertTrue((new TransactionServer(new SipProvider("File"))).statusIs(0));
  }
}

