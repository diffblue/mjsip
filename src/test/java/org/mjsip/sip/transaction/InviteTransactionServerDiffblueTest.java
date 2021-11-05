package org.mjsip.sip.transaction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.Test;
import org.mjsip.sip.dialog.ExtendedInviteDialog;
import org.mjsip.sip.header.StatusLine;
import org.mjsip.sip.message.SipMessage;
import org.mjsip.sip.provider.ConnectionId;
import org.mjsip.sip.provider.SipProvider;
import org.mjsip.sip.provider.TransactionId;
import org.mjsip.sip.provider.TransactionServerId;
import org.zoolu.net.SocketAddress;
import org.zoolu.util.LogLevel;
import org.zoolu.util.Timer;
import org.zoolu.util.TimerListener;

class InviteTransactionServerDiffblueTest {
  @Test
  void testChangeStatus() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    InviteTransactionServer inviteTransactionServer = new InviteTransactionServer(sip_provider,
        new ExtendedInviteDialog(new SipProvider("File"), null));

    // Act
    inviteTransactionServer.changeStatus(1);

    // Assert
    assertEquals(1, inviteTransactionServer.status);
  }

  @Test
  void testChangeStatus2() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    InviteTransactionServer inviteTransactionServer = new InviteTransactionServer(sip_provider,
        new ExtendedInviteDialog(new SipProvider("File"), null));

    // Act
    inviteTransactionServer.changeStatus(0);

    // Assert
    assertEquals(0, inviteTransactionServer.status);
  }

  @Test
  void testChangeStatus3() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    InviteTransactionServer inviteTransactionServer = new InviteTransactionServer(sip_provider,
        new ExtendedInviteDialog(new SipProvider("File"), null));

    // Act
    inviteTransactionServer.changeStatus(2);

    // Assert
    assertEquals(2, inviteTransactionServer.status);
  }

  @Test
  void testChangeStatus4() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    InviteTransactionServer inviteTransactionServer = new InviteTransactionServer(sip_provider,
        new ExtendedInviteDialog(new SipProvider("File"), null));

    // Act
    inviteTransactionServer.changeStatus(3);

    // Assert
    assertEquals(3, inviteTransactionServer.status);
  }

  @Test
  void testChangeStatus5() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    InviteTransactionServer inviteTransactionServer = new InviteTransactionServer(sip_provider,
        new ExtendedInviteDialog(new SipProvider("File"), null));

    // Act
    inviteTransactionServer.changeStatus(4);

    // Assert
    assertEquals(4, inviteTransactionServer.status);
  }

  @Test
  void testChangeStatus6() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    InviteTransactionServer inviteTransactionServer = new InviteTransactionServer(sip_provider,
        new ExtendedInviteDialog(new SipProvider("File"), null));

    // Act
    inviteTransactionServer.changeStatus(5);

    // Assert
    assertEquals(5, inviteTransactionServer.status);
  }

  @Test
  void testChangeStatus7() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    InviteTransactionServer inviteTransactionServer = new InviteTransactionServer(sip_provider,
        new ExtendedInviteDialog(new SipProvider("File"), null));

    // Act
    inviteTransactionServer.changeStatus(6);

    // Assert
    assertEquals(6, inviteTransactionServer.status);
  }

  @Test
  void testChangeStatus8() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    InviteTransactionServer inviteTransactionServer = new InviteTransactionServer(sip_provider,
        new ExtendedInviteDialog(new SipProvider("File"), null));

    // Act
    inviteTransactionServer.changeStatus(7);

    // Assert
    assertEquals(7, inviteTransactionServer.status);
  }

  @Test
  void testConstructor() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");

    // Act
    InviteTransactionServer actualInviteTransactionServer = new InviteTransactionServer(sip_provider,
        new ExtendedInviteDialog(new SipProvider("File"), null));

    // Assert
    Timer timer = actualInviteTransactionServer.clearing_to;
    assertEquals(5000L, timer.getExpirationTime());
    assertFalse(timer.isRunning());
    assertEquals(5000L, timer.getTime());
    Timer timer1 = actualInviteTransactionServer.end_to;
    assertEquals(32000L, timer1.getExpirationTime());
    assertFalse(timer1.isRunning());
    assertEquals(32000L, timer1.getTime());
    Timer timer2 = actualInviteTransactionServer.retransmission_to;
    assertEquals(500L, timer2.getExpirationTime());
    assertFalse(timer2.isRunning());
    assertEquals(500L, timer2.getTime());
  }

  @Test
  void testConstructor2() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");

    // Act
    InviteTransactionServer actualInviteTransactionServer = new InviteTransactionServer(sip_provider,
        new ExtendedInviteDialog(new SipProvider("File"), null));

    // Assert
    assertNull(actualInviteTransactionServer.getRequestMessage());
    assertNull(actualInviteTransactionServer.transaction_listener);
    assertEquals(0, actualInviteTransactionServer.status);
    assertNull(actualInviteTransactionServer.response);
    SipProvider expectedSipProvider = actualInviteTransactionServer.sip_provider;
    assertSame(expectedSipProvider, actualInviteTransactionServer.getSipProvider());
    TransactionId expectedTransactionId = actualInviteTransactionServer.transaction_id;
    TransactionId transactionId = actualInviteTransactionServer.getTransactionId();
    assertSame(expectedTransactionId, transactionId);
    assertNull(actualInviteTransactionServer.getTransportConnId());
    assertTrue(actualInviteTransactionServer.auto_trying);
    assertTrue(actualInviteTransactionServer.invite_ts_listener instanceof ExtendedInviteDialog);
    assertNull(actualInviteTransactionServer.logger);
    assertTrue(Timer.DEFAULT_DAEMON_MODE);
    Timer timer = actualInviteTransactionServer.clearing_to;
    assertFalse(timer.isRunning());
    Timer timer1 = actualInviteTransactionServer.retransmission_to;
    assertFalse(timer1.isRunning());
    Timer timer2 = actualInviteTransactionServer.end_to;
    assertFalse(timer2.isRunning());
    assertEquals(5000L, timer.getTime());
    assertEquals("INVITE", transactionId.toString());
    assertEquals(32000L, timer2.getTime());
    assertEquals(500L, timer1.getTime());
  }

  @Test
  void testInit() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    InviteTransactionServer inviteTransactionServer = new InviteTransactionServer(sip_provider,
        new ExtendedInviteDialog(new SipProvider("File"), null));
    ExtendedInviteDialog extendedInviteDialog = new ExtendedInviteDialog(new SipProvider("File"), null);

    TransactionServerId transaction_id = new TransactionServerId("Method");

    // Act
    inviteTransactionServer.init((InviteTransactionServerListener) extendedInviteDialog, transaction_id,
        new ConnectionId("Protocol", new SocketAddress("42 Main St")));

    // Assert
    assertTrue(inviteTransactionServer.auto_trying);
    assertSame(extendedInviteDialog, inviteTransactionServer.invite_ts_listener);
    assertTrue(Timer.DEFAULT_DAEMON_MODE);
  }

  @Test
  void testListen() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    InviteTransactionServer inviteTransactionServer = new InviteTransactionServer(sip_provider,
        new ExtendedInviteDialog(new SipProvider("File"), null));

    // Act
    inviteTransactionServer.listen();

    // Assert
    assertEquals(1, inviteTransactionServer.status);
  }

  @Test
  void testRespondWith() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    InviteTransactionServer inviteTransactionServer = new InviteTransactionServer(sip_provider,
        new ExtendedInviteDialog(new SipProvider("File"), null));

    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.setStatusLine(new StatusLine(1, "foo"));

    // Act
    inviteTransactionServer.respondWith(sipMessage);

    // Assert
    assertNull(sipMessage.getConnectionId());
    assertSame(sipMessage, inviteTransactionServer.response);
  }

  @Test
  void testOnReceivedMessage() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    InviteTransactionServer inviteTransactionServer = new InviteTransactionServer(sip_provider,
        new ExtendedInviteDialog(new SipProvider("File"), null));
    SipProvider sipProvider = new SipProvider("File");

    // Act
    inviteTransactionServer.onReceivedMessage(sipProvider, new SipMessage("Str"));

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
    assertTrue(inviteTransactionServer.auto_trying);
    assertEquals(0, inviteTransactionServer.status);
    assertTrue(inviteTransactionServer.invite_ts_listener instanceof ExtendedInviteDialog);
    assertTrue(Timer.DEFAULT_DAEMON_MODE);
  }

  @Test
  void testOnReceivedMessage2() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    InviteTransactionServer inviteTransactionServer = new InviteTransactionServer(sip_provider,
        new ExtendedInviteDialog(new SipProvider("File"), null));
    SipProvider sipProvider = new SipProvider("File");

    // Act
    inviteTransactionServer.onReceivedMessage(sipProvider, new SipMessage("Accept"));

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
    assertTrue(inviteTransactionServer.auto_trying);
    assertEquals(0, inviteTransactionServer.status);
    assertTrue(inviteTransactionServer.invite_ts_listener instanceof ExtendedInviteDialog);
    assertTrue(Timer.DEFAULT_DAEMON_MODE);
  }

  @Test
  void testOnTimeout() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    InviteTransactionServer inviteTransactionServer = new InviteTransactionServer(sip_provider,
        new ExtendedInviteDialog(new SipProvider("File"), null));
    Timer timer = new Timer(10L, mock(TimerListener.class));

    // Act
    inviteTransactionServer.onTimeout(timer);

    // Assert that nothing has changed
    assertFalse(timer.isRunning());
    assertEquals(10L, timer.getTime());
    assertEquals(0, inviteTransactionServer.status);
    SipProvider expectedSipProvider = inviteTransactionServer.sip_provider;
    assertSame(expectedSipProvider, inviteTransactionServer.getSipProvider());
    TransactionId expectedTransactionId = inviteTransactionServer.transaction_id;
    assertSame(expectedTransactionId, inviteTransactionServer.getTransactionId());
    assertTrue(inviteTransactionServer.auto_trying);
    assertTrue(inviteTransactionServer.invite_ts_listener instanceof ExtendedInviteDialog);
  }

  @Test
  void testOnTimeout2() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    InviteTransactionServer inviteTransactionServer = new InviteTransactionServer(sip_provider,
        new ExtendedInviteDialog(new SipProvider("File"), null));

    // Act
    inviteTransactionServer.onTimeout(null);

    // Assert that nothing has changed
    assertEquals(0, inviteTransactionServer.status);
    SipProvider expectedSipProvider = inviteTransactionServer.sip_provider;
    assertSame(expectedSipProvider, inviteTransactionServer.getSipProvider());
    TransactionId expectedTransactionId = inviteTransactionServer.transaction_id;
    assertSame(expectedTransactionId, inviteTransactionServer.getTransactionId());
    assertTrue(inviteTransactionServer.auto_trying);
    assertTrue(inviteTransactionServer.invite_ts_listener instanceof ExtendedInviteDialog);
    assertTrue(Timer.DEFAULT_DAEMON_MODE);
  }

  @Test
  void testTerminate() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    InviteTransactionServer inviteTransactionServer = new InviteTransactionServer(sip_provider,
        new ExtendedInviteDialog(new SipProvider("File"), null));

    // Act
    inviteTransactionServer.terminate();

    // Assert
    assertEquals(7, inviteTransactionServer.status);
    assertNull(inviteTransactionServer.invite_ts_listener);
  }

  @Test
  void testDoTerminate() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    InviteTransactionServer inviteTransactionServer = new InviteTransactionServer(sip_provider,
        new ExtendedInviteDialog(new SipProvider("File"), null));

    // Act
    inviteTransactionServer.doTerminate();

    // Assert
    assertEquals(7, inviteTransactionServer.status);
  }

  @Test
  void testGetRequestMessage() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");

    // Act and Assert
    assertNull((new InviteTransactionServer(sip_provider, new ExtendedInviteDialog(new SipProvider("File"), null)))
        .getRequestMessage());
  }

  @Test
  void testGetSipProvider() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    InviteTransactionServer inviteTransactionServer = new InviteTransactionServer(sip_provider,
        new ExtendedInviteDialog(new SipProvider("File"), null));

    // Act and Assert
    assertSame(inviteTransactionServer.sip_provider, inviteTransactionServer.getSipProvider());
  }

  @Test
  void testGetStatus() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");

    // Act and Assert
    assertEquals("T_Idle",
        (new InviteTransactionServer(sip_provider, new ExtendedInviteDialog(new SipProvider("File"), null)))
            .getStatus());
  }

  @Test
  void testGetTransactionId() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    InviteTransactionServer inviteTransactionServer = new InviteTransactionServer(sip_provider,
        new ExtendedInviteDialog(new SipProvider("File"), null));

    // Act and Assert
    assertSame(inviteTransactionServer.transaction_id, inviteTransactionServer.getTransactionId());
  }

  @Test
  void testGetTransportConnId() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");

    // Act and Assert
    assertNull((new InviteTransactionServer(sip_provider, new ExtendedInviteDialog(new SipProvider("File"), null)))
        .getTransportConnId());
  }

  @Test
  void testIsCompleted() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");

    // Act and Assert
    assertFalse((new InviteTransactionServer(sip_provider, new ExtendedInviteDialog(new SipProvider("File"), null)))
        .isCompleted());
  }

  @Test
  void testIsProceeding() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");

    // Act and Assert
    assertFalse((new InviteTransactionServer(sip_provider, new ExtendedInviteDialog(new SipProvider("File"), null)))
        .isProceeding());
  }

  @Test
  void testIsTerminated() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");

    // Act and Assert
    assertFalse((new InviteTransactionServer(sip_provider, new ExtendedInviteDialog(new SipProvider("File"), null)))
        .isTerminated());
  }

  @Test
  void testIsTrying() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");

    // Act and Assert
    assertFalse((new InviteTransactionServer(sip_provider, new ExtendedInviteDialog(new SipProvider("File"), null)))
        .isTrying());
  }

  @Test
  void testLog() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    InviteTransactionServer inviteTransactionServer = new InviteTransactionServer(sip_provider,
        new ExtendedInviteDialog(new SipProvider("File"), null));
    LogLevel logLevel = new LogLevel("Name", 42);

    // Act
    inviteTransactionServer.log(logLevel, new Exception("An error occurred"));

    // Assert that nothing has changed
    assertEquals("Name", logLevel.getName());
    assertEquals(42, logLevel.getValue());
    assertEquals(0, inviteTransactionServer.status);
    SipProvider expectedSipProvider = inviteTransactionServer.sip_provider;
    assertSame(expectedSipProvider, inviteTransactionServer.getSipProvider());
    TransactionId expectedTransactionId = inviteTransactionServer.transaction_id;
    assertSame(expectedTransactionId, inviteTransactionServer.getTransactionId());
    assertTrue(inviteTransactionServer.auto_trying);
    assertTrue(inviteTransactionServer.invite_ts_listener instanceof ExtendedInviteDialog);
    assertTrue(Timer.DEFAULT_DAEMON_MODE);
  }

  @Test
  void testLog2() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    InviteTransactionServer inviteTransactionServer = new InviteTransactionServer(sip_provider,
        new ExtendedInviteDialog(new SipProvider("File"), null));
    LogLevel logLevel = new LogLevel("Name", 42);

    // Act
    inviteTransactionServer.log(logLevel, "Str");

    // Assert that nothing has changed
    assertEquals("Name", logLevel.getName());
    assertEquals(42, logLevel.getValue());
    assertEquals(0, inviteTransactionServer.status);
    SipProvider expectedSipProvider = inviteTransactionServer.sip_provider;
    assertSame(expectedSipProvider, inviteTransactionServer.getSipProvider());
    TransactionId expectedTransactionId = inviteTransactionServer.transaction_id;
    assertSame(expectedTransactionId, inviteTransactionServer.getTransactionId());
    assertTrue(inviteTransactionServer.auto_trying);
    assertTrue(inviteTransactionServer.invite_ts_listener instanceof ExtendedInviteDialog);
    assertTrue(Timer.DEFAULT_DAEMON_MODE);
  }

  @Test
  void testStatusIs() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");

    // Act and Assert
    assertFalse((new InviteTransactionServer(sip_provider, new ExtendedInviteDialog(new SipProvider("File"), null)))
        .statusIs(1));
  }

  @Test
  void testStatusIs2() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");

    // Act and Assert
    assertTrue((new InviteTransactionServer(sip_provider, new ExtendedInviteDialog(new SipProvider("File"), null)))
        .statusIs(0));
  }
}

