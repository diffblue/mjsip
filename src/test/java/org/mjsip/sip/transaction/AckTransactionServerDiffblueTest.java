package org.mjsip.sip.transaction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.io.StringWriter;
import java.io.Writer;
import org.junit.jupiter.api.Test;
import org.mjsip.sip.header.CSeqHeader;
import org.mjsip.sip.header.CallIdHeader;
import org.mjsip.sip.header.ViaHeader;
import org.mjsip.sip.message.BasicSipMessage;
import org.mjsip.sip.message.SipMessage;
import org.mjsip.sip.provider.ConnectionId;
import org.mjsip.sip.provider.SipProvider;
import org.mjsip.sip.provider.TransactionId;
import org.zoolu.net.SocketAddress;
import org.zoolu.util.LogRotationWriter;
import org.zoolu.util.LogWriter;
import org.zoolu.util.Timer;

class AckTransactionServerDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange
    SipProvider sipProvider = mock(SipProvider.class);
    when(sipProvider.getLogger()).thenReturn(new LogWriter(new StringWriter()));
    SipMessage sipMessage = mock(SipMessage.class);
    when(sipMessage.getCSeqHeader()).thenReturn(new CSeqHeader("42"));
    when(sipMessage.getViaHeader()).thenReturn(new ViaHeader("42"));
    when(sipMessage.getCallIdHeader()).thenReturn(new CallIdHeader("42"));

    // Act
    AckTransactionServer actualAckTransactionServer = new AckTransactionServer(sipProvider, sipMessage,
        new SipMessage("Str"), mock(AckTransactionServerListener.class));

    // Assert
    assertNull(actualAckTransactionServer.getRequestMessage());
    SipProvider expectedSipProvider = actualAckTransactionServer.sip_provider;
    assertSame(expectedSipProvider, actualAckTransactionServer.getSipProvider());
    TransactionId expectedTransactionId = actualAckTransactionServer.transaction_id;
    TransactionId transactionId = actualAckTransactionServer.getTransactionId();
    assertSame(expectedTransactionId, transactionId);
    assertFalse(actualAckTransactionServer.isTerminated());
    assertNull(actualAckTransactionServer.getTransportConnId());
    assertTrue(actualAckTransactionServer.logger instanceof LogWriter);
    assertNull(actualAckTransactionServer.response.getConnectionId());
    Timer timer = actualAckTransactionServer.retransmission_to;
    assertEquals(500L, timer.getTime());
    Timer timer1 = actualAckTransactionServer.transaction_to;
    assertFalse(timer1.isRunning());
    assertEquals(32000L, timer1.getTime());
    assertFalse(BasicSipMessage.DEBUG);
    assertEquals("42-42--server-null", transactionId.toString());
    assertTrue(Timer.DEFAULT_DAEMON_MODE);
    assertFalse(timer.isRunning());
    verify(sipProvider).getLogger();
    verify(sipMessage).getCSeqHeader();
    verify(sipMessage).getCallIdHeader();
    verify(sipMessage).getViaHeader();
  }

  @Test
  void testConstructor2() {
    // Arrange
    SipProvider sipProvider = mock(SipProvider.class);
    when(sipProvider.getLogger()).thenReturn(new LogWriter((Writer) null));
    SipMessage sipMessage = mock(SipMessage.class);
    when(sipMessage.getCSeqHeader()).thenReturn(new CSeqHeader("42"));
    when(sipMessage.getViaHeader()).thenReturn(new ViaHeader("42"));
    when(sipMessage.getCallIdHeader()).thenReturn(new CallIdHeader("42"));

    // Act
    AckTransactionServer actualAckTransactionServer = new AckTransactionServer(sipProvider, sipMessage,
        new SipMessage("Str"), mock(AckTransactionServerListener.class));

    // Assert
    assertNull(actualAckTransactionServer.getRequestMessage());
    SipProvider expectedSipProvider = actualAckTransactionServer.sip_provider;
    assertSame(expectedSipProvider, actualAckTransactionServer.getSipProvider());
    TransactionId expectedTransactionId = actualAckTransactionServer.transaction_id;
    TransactionId transactionId = actualAckTransactionServer.getTransactionId();
    assertSame(expectedTransactionId, transactionId);
    assertFalse(actualAckTransactionServer.isTerminated());
    assertNull(actualAckTransactionServer.getTransportConnId());
    assertTrue(actualAckTransactionServer.logger instanceof LogWriter);
    assertNull(actualAckTransactionServer.response.getConnectionId());
    Timer timer = actualAckTransactionServer.retransmission_to;
    assertEquals(500L, timer.getTime());
    Timer timer1 = actualAckTransactionServer.transaction_to;
    assertFalse(timer1.isRunning());
    assertEquals(32000L, timer1.getTime());
    assertFalse(BasicSipMessage.DEBUG);
    assertEquals("42-42--server-null", transactionId.toString());
    assertTrue(Timer.DEFAULT_DAEMON_MODE);
    assertFalse(timer.isRunning());
    verify(sipProvider).getLogger();
    verify(sipMessage).getCSeqHeader();
    verify(sipMessage).getCallIdHeader();
    verify(sipMessage).getViaHeader();
  }

  @Test
  void testConstructor3() {
    // Arrange
    SipProvider sipProvider = mock(SipProvider.class);
    when(sipProvider.getLogger()).thenReturn(null);
    SipMessage sipMessage = mock(SipMessage.class);
    when(sipMessage.getCSeqHeader()).thenReturn(new CSeqHeader("42"));
    when(sipMessage.getViaHeader()).thenReturn(new ViaHeader("42"));
    when(sipMessage.getCallIdHeader()).thenReturn(new CallIdHeader("42"));

    // Act
    AckTransactionServer actualAckTransactionServer = new AckTransactionServer(sipProvider, sipMessage,
        new SipMessage("Str"), mock(AckTransactionServerListener.class));

    // Assert
    assertNull(actualAckTransactionServer.getRequestMessage());
    SipProvider expectedSipProvider = actualAckTransactionServer.sip_provider;
    assertSame(expectedSipProvider, actualAckTransactionServer.getSipProvider());
    TransactionId expectedTransactionId = actualAckTransactionServer.transaction_id;
    TransactionId transactionId = actualAckTransactionServer.getTransactionId();
    assertSame(expectedTransactionId, transactionId);
    assertFalse(actualAckTransactionServer.isTerminated());
    assertNull(actualAckTransactionServer.getTransportConnId());
    assertNull(actualAckTransactionServer.logger);
    assertNull(actualAckTransactionServer.response.getConnectionId());
    Timer timer = actualAckTransactionServer.transaction_to;
    assertFalse(timer.isRunning());
    assertEquals(32000L, timer.getTime());
    assertFalse(BasicSipMessage.DEBUG);
    Timer timer1 = actualAckTransactionServer.retransmission_to;
    assertEquals(500L, timer1.getTime());
    assertEquals("42-42--server-null", transactionId.toString());
    assertTrue(Timer.DEFAULT_DAEMON_MODE);
    assertFalse(timer1.isRunning());
    verify(sipProvider).getLogger();
    verify(sipMessage).getCSeqHeader();
    verify(sipMessage).getCallIdHeader();
    verify(sipMessage).getViaHeader();
  }

  @Test
  void testConstructor4() {
    // Arrange
    LogRotationWriter logRotationWriter = mock(LogRotationWriter.class);
    doNothing().when(logRotationWriter).log((org.zoolu.util.LogLevel) any(), (String) any());
    SipProvider sipProvider = mock(SipProvider.class);
    when(sipProvider.getLogger()).thenReturn(logRotationWriter);
    SipMessage sipMessage = mock(SipMessage.class);
    when(sipMessage.getCSeqHeader()).thenReturn(new CSeqHeader("42"));
    when(sipMessage.getViaHeader()).thenReturn(new ViaHeader("42"));
    when(sipMessage.getCallIdHeader()).thenReturn(new CallIdHeader("42"));

    // Act
    AckTransactionServer actualAckTransactionServer = new AckTransactionServer(sipProvider, sipMessage,
        new SipMessage("Str"), mock(AckTransactionServerListener.class));

    // Assert
    assertNull(actualAckTransactionServer.getRequestMessage());
    SipProvider expectedSipProvider = actualAckTransactionServer.sip_provider;
    assertSame(expectedSipProvider, actualAckTransactionServer.getSipProvider());
    TransactionId expectedTransactionId = actualAckTransactionServer.transaction_id;
    TransactionId transactionId = actualAckTransactionServer.getTransactionId();
    assertSame(expectedTransactionId, transactionId);
    assertFalse(actualAckTransactionServer.isTerminated());
    assertNull(actualAckTransactionServer.getTransportConnId());
    assertNull(actualAckTransactionServer.response.getConnectionId());
    Timer timer = actualAckTransactionServer.transaction_to;
    assertFalse(timer.isRunning());
    assertEquals(32000L, timer.getTime());
    assertFalse(BasicSipMessage.DEBUG);
    Timer timer1 = actualAckTransactionServer.retransmission_to;
    assertEquals(500L, timer1.getTime());
    assertEquals("42-42--server-null", transactionId.toString());
    assertTrue(Timer.DEFAULT_DAEMON_MODE);
    assertFalse(timer1.isRunning());
    verify(sipProvider).getLogger();
    verify(logRotationWriter).log((org.zoolu.util.LogLevel) any(), (String) any());
    verify(sipMessage).getCSeqHeader();
    verify(sipMessage).getCallIdHeader();
    verify(sipMessage).getViaHeader();
  }

  @Test
  void testConstructor5() {
    // Arrange
    LogRotationWriter logRotationWriter = mock(LogRotationWriter.class);
    doNothing().when(logRotationWriter).log((org.zoolu.util.LogLevel) any(), (String) any());
    SipProvider sipProvider = mock(SipProvider.class);
    when(sipProvider.getLogger()).thenReturn(logRotationWriter);
    SipMessage sipMessage = mock(SipMessage.class);
    when(sipMessage.getCSeqHeader()).thenReturn(new CSeqHeader(1L, "Method"));
    when(sipMessage.getViaHeader()).thenReturn(new ViaHeader("42"));
    when(sipMessage.getCallIdHeader()).thenReturn(new CallIdHeader("42"));

    // Act
    AckTransactionServer actualAckTransactionServer = new AckTransactionServer(sipProvider, sipMessage,
        new SipMessage("Str"), mock(AckTransactionServerListener.class));

    // Assert
    assertNull(actualAckTransactionServer.getRequestMessage());
    SipProvider expectedSipProvider = actualAckTransactionServer.sip_provider;
    assertSame(expectedSipProvider, actualAckTransactionServer.getSipProvider());
    TransactionId expectedTransactionId = actualAckTransactionServer.transaction_id;
    TransactionId transactionId = actualAckTransactionServer.getTransactionId();
    assertSame(expectedTransactionId, transactionId);
    assertFalse(actualAckTransactionServer.isTerminated());
    assertNull(actualAckTransactionServer.getTransportConnId());
    assertNull(actualAckTransactionServer.response.getConnectionId());
    Timer timer = actualAckTransactionServer.transaction_to;
    assertFalse(timer.isRunning());
    assertEquals(32000L, timer.getTime());
    assertFalse(BasicSipMessage.DEBUG);
    Timer timer1 = actualAckTransactionServer.retransmission_to;
    assertEquals(500L, timer1.getTime());
    assertEquals("42-1-Method-server-null", transactionId.toString());
    assertTrue(Timer.DEFAULT_DAEMON_MODE);
    assertFalse(timer1.isRunning());
    verify(sipProvider).getLogger();
    verify(logRotationWriter).log((org.zoolu.util.LogLevel) any(), (String) any());
    verify(sipMessage).getCSeqHeader();
    verify(sipMessage).getCallIdHeader();
    verify(sipMessage).getViaHeader();
  }

  @Test
  void testConstructor6() {
    // Arrange
    LogRotationWriter logRotationWriter = mock(LogRotationWriter.class);
    doNothing().when(logRotationWriter).log((org.zoolu.util.LogLevel) any(), (String) any());
    SipProvider sipProvider = mock(SipProvider.class);
    when(sipProvider.getLogger()).thenReturn(logRotationWriter);
    SipMessage sipMessage = mock(SipMessage.class);
    when(sipMessage.getCSeqHeader()).thenReturn(new CSeqHeader(1L, "ACK"));
    when(sipMessage.getViaHeader()).thenReturn(new ViaHeader("42"));
    when(sipMessage.getCallIdHeader()).thenReturn(new CallIdHeader("42"));

    // Act
    AckTransactionServer actualAckTransactionServer = new AckTransactionServer(sipProvider, sipMessage,
        new SipMessage("Str"), mock(AckTransactionServerListener.class));

    // Assert
    assertNull(actualAckTransactionServer.getRequestMessage());
    SipProvider expectedSipProvider = actualAckTransactionServer.sip_provider;
    assertSame(expectedSipProvider, actualAckTransactionServer.getSipProvider());
    TransactionId expectedTransactionId = actualAckTransactionServer.transaction_id;
    TransactionId transactionId = actualAckTransactionServer.getTransactionId();
    assertSame(expectedTransactionId, transactionId);
    assertFalse(actualAckTransactionServer.isTerminated());
    assertNull(actualAckTransactionServer.getTransportConnId());
    assertNull(actualAckTransactionServer.response.getConnectionId());
    Timer timer = actualAckTransactionServer.transaction_to;
    assertFalse(timer.isRunning());
    assertEquals(32000L, timer.getTime());
    assertFalse(BasicSipMessage.DEBUG);
    Timer timer1 = actualAckTransactionServer.retransmission_to;
    assertEquals(500L, timer1.getTime());
    assertEquals("42-1-INVITE-server-null", transactionId.toString());
    assertTrue(Timer.DEFAULT_DAEMON_MODE);
    assertFalse(timer1.isRunning());
    verify(sipProvider).getLogger();
    verify(logRotationWriter).log((org.zoolu.util.LogLevel) any(), (String) any());
    verify(sipMessage).getCSeqHeader();
    verify(sipMessage).getCallIdHeader();
    verify(sipMessage).getViaHeader();
  }

  @Test
  void testConstructor7() {
    // Arrange
    LogRotationWriter logRotationWriter = mock(LogRotationWriter.class);
    doNothing().when(logRotationWriter).log((org.zoolu.util.LogLevel) any(), (String) any());
    SipProvider sipProvider = mock(SipProvider.class);
    when(sipProvider.getLogger()).thenReturn(logRotationWriter);
    CSeqHeader cSeqHeader = mock(CSeqHeader.class);
    when(cSeqHeader.getMethod()).thenReturn("Method");
    when(cSeqHeader.getSequenceNumber()).thenReturn(1L);
    SipMessage sipMessage = mock(SipMessage.class);
    when(sipMessage.getCSeqHeader()).thenReturn(cSeqHeader);
    when(sipMessage.getViaHeader()).thenReturn(new ViaHeader("42"));
    when(sipMessage.getCallIdHeader()).thenReturn(new CallIdHeader("42"));

    // Act
    AckTransactionServer actualAckTransactionServer = new AckTransactionServer(sipProvider, sipMessage,
        new SipMessage("Str"), mock(AckTransactionServerListener.class));

    // Assert
    assertNull(actualAckTransactionServer.getRequestMessage());
    SipProvider expectedSipProvider = actualAckTransactionServer.sip_provider;
    assertSame(expectedSipProvider, actualAckTransactionServer.getSipProvider());
    TransactionId expectedTransactionId = actualAckTransactionServer.transaction_id;
    TransactionId transactionId = actualAckTransactionServer.getTransactionId();
    assertSame(expectedTransactionId, transactionId);
    assertFalse(actualAckTransactionServer.isTerminated());
    assertNull(actualAckTransactionServer.getTransportConnId());
    assertNull(actualAckTransactionServer.response.getConnectionId());
    Timer timer = actualAckTransactionServer.transaction_to;
    assertFalse(timer.isRunning());
    assertEquals(32000L, timer.getTime());
    assertFalse(BasicSipMessage.DEBUG);
    Timer timer1 = actualAckTransactionServer.retransmission_to;
    assertEquals(500L, timer1.getTime());
    assertEquals("42-1-Method-server-null", transactionId.toString());
    assertTrue(Timer.DEFAULT_DAEMON_MODE);
    assertFalse(timer1.isRunning());
    verify(sipProvider).getLogger();
    verify(logRotationWriter).log((org.zoolu.util.LogLevel) any(), (String) any());
    verify(sipMessage).getCSeqHeader();
    verify(sipMessage).getCallIdHeader();
    verify(sipMessage).getViaHeader();
    verify(cSeqHeader).getMethod();
    verify(cSeqHeader).getSequenceNumber();
  }

  @Test
  void testConstructor8() {
    // Arrange
    LogRotationWriter logRotationWriter = mock(LogRotationWriter.class);
    doNothing().when(logRotationWriter).log((org.zoolu.util.LogLevel) any(), (String) any());
    SipProvider sipProvider = mock(SipProvider.class);
    when(sipProvider.getLogger()).thenReturn(logRotationWriter);
    CSeqHeader cSeqHeader = mock(CSeqHeader.class);
    when(cSeqHeader.getMethod()).thenReturn("Method");
    when(cSeqHeader.getSequenceNumber()).thenReturn(1L);
    SipMessage sipMessage = mock(SipMessage.class);
    when(sipMessage.getCSeqHeader()).thenReturn(cSeqHeader);
    when(sipMessage.getViaHeader()).thenReturn(new ViaHeader("localhost", 8080));
    when(sipMessage.getCallIdHeader()).thenReturn(new CallIdHeader("42"));

    // Act
    AckTransactionServer actualAckTransactionServer = new AckTransactionServer(sipProvider, sipMessage,
        new SipMessage("Str"), mock(AckTransactionServerListener.class));

    // Assert
    assertNull(actualAckTransactionServer.getRequestMessage());
    SipProvider expectedSipProvider = actualAckTransactionServer.sip_provider;
    assertSame(expectedSipProvider, actualAckTransactionServer.getSipProvider());
    TransactionId expectedTransactionId = actualAckTransactionServer.transaction_id;
    TransactionId transactionId = actualAckTransactionServer.getTransactionId();
    assertSame(expectedTransactionId, transactionId);
    assertFalse(actualAckTransactionServer.isTerminated());
    assertNull(actualAckTransactionServer.getTransportConnId());
    assertNull(actualAckTransactionServer.response.getConnectionId());
    Timer timer = actualAckTransactionServer.transaction_to;
    assertFalse(timer.isRunning());
    assertEquals(32000L, timer.getTime());
    assertFalse(BasicSipMessage.DEBUG);
    Timer timer1 = actualAckTransactionServer.retransmission_to;
    assertEquals(500L, timer1.getTime());
    assertEquals("42-1-Method-server-localhost:8080", transactionId.toString());
    assertTrue(Timer.DEFAULT_DAEMON_MODE);
    assertFalse(timer1.isRunning());
    verify(sipProvider).getLogger();
    verify(logRotationWriter).log((org.zoolu.util.LogLevel) any(), (String) any());
    verify(sipMessage).getCSeqHeader();
    verify(sipMessage).getCallIdHeader();
    verify(sipMessage).getViaHeader();
    verify(cSeqHeader).getMethod();
    verify(cSeqHeader).getSequenceNumber();
  }

  @Test
  void testConstructor9() {
    // Arrange
    LogRotationWriter logRotationWriter = mock(LogRotationWriter.class);
    doNothing().when(logRotationWriter).log((org.zoolu.util.LogLevel) any(), (String) any());
    SipProvider sipProvider = mock(SipProvider.class);
    when(sipProvider.getLogger()).thenReturn(logRotationWriter);
    CSeqHeader cSeqHeader = mock(CSeqHeader.class);
    when(cSeqHeader.getMethod()).thenReturn("Method");
    when(cSeqHeader.getSequenceNumber()).thenReturn(1L);
    SipMessage sipMessage = mock(SipMessage.class);
    when(sipMessage.getCSeqHeader()).thenReturn(cSeqHeader);
    when(sipMessage.getViaHeader()).thenReturn(null);
    when(sipMessage.getCallIdHeader()).thenReturn(new CallIdHeader("42"));

    // Act
    AckTransactionServer actualAckTransactionServer = new AckTransactionServer(sipProvider, sipMessage,
        new SipMessage("Str"), mock(AckTransactionServerListener.class));

    // Assert
    assertNull(actualAckTransactionServer.getRequestMessage());
    SipProvider expectedSipProvider = actualAckTransactionServer.sip_provider;
    assertSame(expectedSipProvider, actualAckTransactionServer.getSipProvider());
    TransactionId expectedTransactionId = actualAckTransactionServer.transaction_id;
    TransactionId transactionId = actualAckTransactionServer.getTransactionId();
    assertSame(expectedTransactionId, transactionId);
    assertFalse(actualAckTransactionServer.isTerminated());
    assertNull(actualAckTransactionServer.getTransportConnId());
    assertNull(actualAckTransactionServer.response.getConnectionId());
    Timer timer = actualAckTransactionServer.transaction_to;
    assertFalse(timer.isRunning());
    assertEquals(32000L, timer.getTime());
    assertFalse(BasicSipMessage.DEBUG);
    Timer timer1 = actualAckTransactionServer.retransmission_to;
    assertEquals(500L, timer1.getTime());
    assertEquals("42-1-Method-server-null", transactionId.toString());
    assertTrue(Timer.DEFAULT_DAEMON_MODE);
    assertFalse(timer1.isRunning());
    verify(sipProvider).getLogger();
    verify(logRotationWriter).log((org.zoolu.util.LogLevel) any(), (String) any());
    verify(sipMessage).getCSeqHeader();
    verify(sipMessage).getCallIdHeader();
    verify(sipMessage).getViaHeader();
    verify(cSeqHeader).getMethod();
    verify(cSeqHeader).getSequenceNumber();
  }

  @Test
  void testConstructor10() {
    // Arrange
    LogRotationWriter logRotationWriter = mock(LogRotationWriter.class);
    doNothing().when(logRotationWriter).log((org.zoolu.util.LogLevel) any(), (String) any());
    SipProvider sipProvider = mock(SipProvider.class);
    when(sipProvider.getLogger()).thenReturn(logRotationWriter);
    ViaHeader viaHeader = mock(ViaHeader.class);
    when(viaHeader.getSentBy()).thenReturn("Sent By");
    when(viaHeader.getBranch()).thenReturn("janedoe/featurebranch");
    when(viaHeader.hasBranch()).thenReturn(true);
    CSeqHeader cSeqHeader = mock(CSeqHeader.class);
    when(cSeqHeader.getMethod()).thenReturn("Method");
    when(cSeqHeader.getSequenceNumber()).thenReturn(1L);
    SipMessage sipMessage = mock(SipMessage.class);
    when(sipMessage.getCSeqHeader()).thenReturn(cSeqHeader);
    when(sipMessage.getViaHeader()).thenReturn(viaHeader);
    when(sipMessage.getCallIdHeader()).thenReturn(new CallIdHeader("42"));

    // Act
    AckTransactionServer actualAckTransactionServer = new AckTransactionServer(sipProvider, sipMessage,
        new SipMessage("Str"), mock(AckTransactionServerListener.class));

    // Assert
    assertNull(actualAckTransactionServer.getRequestMessage());
    SipProvider expectedSipProvider = actualAckTransactionServer.sip_provider;
    assertSame(expectedSipProvider, actualAckTransactionServer.getSipProvider());
    TransactionId expectedTransactionId = actualAckTransactionServer.transaction_id;
    TransactionId transactionId = actualAckTransactionServer.getTransactionId();
    assertSame(expectedTransactionId, transactionId);
    assertFalse(actualAckTransactionServer.isTerminated());
    assertNull(actualAckTransactionServer.getTransportConnId());
    assertNull(actualAckTransactionServer.response.getConnectionId());
    Timer timer = actualAckTransactionServer.transaction_to;
    assertFalse(timer.isRunning());
    assertEquals(32000L, timer.getTime());
    assertFalse(BasicSipMessage.DEBUG);
    Timer timer1 = actualAckTransactionServer.retransmission_to;
    assertEquals(500L, timer1.getTime());
    assertEquals("42-1-Method-server-janedoe/featurebranch", transactionId.toString());
    assertTrue(Timer.DEFAULT_DAEMON_MODE);
    assertFalse(timer1.isRunning());
    verify(sipProvider).getLogger();
    verify(logRotationWriter).log((org.zoolu.util.LogLevel) any(), (String) any());
    verify(sipMessage).getCSeqHeader();
    verify(sipMessage).getCallIdHeader();
    verify(sipMessage).getViaHeader();
    verify(cSeqHeader).getMethod();
    verify(cSeqHeader).getSequenceNumber();
    verify(viaHeader).getBranch();
    verify(viaHeader).getSentBy();
    verify(viaHeader).hasBranch();
  }

  @Test
  void testConstructor11() {
    // Arrange
    LogRotationWriter logRotationWriter = mock(LogRotationWriter.class);
    doNothing().when(logRotationWriter).log((org.zoolu.util.LogLevel) any(), (String) any());
    SipProvider sipProvider = mock(SipProvider.class);
    when(sipProvider.getLogger()).thenReturn(logRotationWriter);
    CallIdHeader callIdHeader = mock(CallIdHeader.class);
    when(callIdHeader.getCallId()).thenReturn("42");
    ViaHeader viaHeader = mock(ViaHeader.class);
    when(viaHeader.getSentBy()).thenReturn("Sent By");
    when(viaHeader.getBranch()).thenReturn("janedoe/featurebranch");
    when(viaHeader.hasBranch()).thenReturn(true);
    CSeqHeader cSeqHeader = mock(CSeqHeader.class);
    when(cSeqHeader.getMethod()).thenReturn("Method");
    when(cSeqHeader.getSequenceNumber()).thenReturn(1L);
    SipMessage sipMessage = mock(SipMessage.class);
    when(sipMessage.getCSeqHeader()).thenReturn(cSeqHeader);
    when(sipMessage.getViaHeader()).thenReturn(viaHeader);
    when(sipMessage.getCallIdHeader()).thenReturn(callIdHeader);

    // Act
    AckTransactionServer actualAckTransactionServer = new AckTransactionServer(sipProvider, sipMessage,
        new SipMessage("Str"), mock(AckTransactionServerListener.class));

    // Assert
    assertNull(actualAckTransactionServer.getRequestMessage());
    SipProvider expectedSipProvider = actualAckTransactionServer.sip_provider;
    assertSame(expectedSipProvider, actualAckTransactionServer.getSipProvider());
    TransactionId expectedTransactionId = actualAckTransactionServer.transaction_id;
    TransactionId transactionId = actualAckTransactionServer.getTransactionId();
    assertSame(expectedTransactionId, transactionId);
    assertFalse(actualAckTransactionServer.isTerminated());
    assertNull(actualAckTransactionServer.getTransportConnId());
    assertNull(actualAckTransactionServer.response.getConnectionId());
    Timer timer = actualAckTransactionServer.transaction_to;
    assertFalse(timer.isRunning());
    assertEquals(32000L, timer.getTime());
    assertFalse(BasicSipMessage.DEBUG);
    Timer timer1 = actualAckTransactionServer.retransmission_to;
    assertEquals(500L, timer1.getTime());
    assertEquals("42-1-Method-server-janedoe/featurebranch", transactionId.toString());
    assertTrue(Timer.DEFAULT_DAEMON_MODE);
    assertFalse(timer1.isRunning());
    verify(sipProvider).getLogger();
    verify(logRotationWriter).log((org.zoolu.util.LogLevel) any(), (String) any());
    verify(sipMessage).getCSeqHeader();
    verify(sipMessage).getCallIdHeader();
    verify(sipMessage).getViaHeader();
    verify(cSeqHeader).getMethod();
    verify(cSeqHeader).getSequenceNumber();
    verify(viaHeader).getBranch();
    verify(viaHeader).getSentBy();
    verify(viaHeader).hasBranch();
    verify(callIdHeader).getCallId();
  }

  @Test
  void testConstructor12() {
    // Arrange
    LogRotationWriter logRotationWriter = mock(LogRotationWriter.class);
    doNothing().when(logRotationWriter).log((org.zoolu.util.LogLevel) any(), (String) any());
    SipProvider sipProvider = mock(SipProvider.class);
    when(sipProvider.getLogger()).thenReturn(logRotationWriter);
    CallIdHeader callIdHeader = mock(CallIdHeader.class);
    when(callIdHeader.getCallId()).thenReturn("42");
    ViaHeader viaHeader = mock(ViaHeader.class);
    when(viaHeader.getSentBy()).thenReturn("Sent By");
    when(viaHeader.getBranch()).thenReturn("janedoe/featurebranch");
    when(viaHeader.hasBranch()).thenReturn(true);
    CSeqHeader cSeqHeader = mock(CSeqHeader.class);
    when(cSeqHeader.getMethod()).thenReturn("Method");
    when(cSeqHeader.getSequenceNumber()).thenReturn(1L);
    SipMessage sipMessage = mock(SipMessage.class);
    when(sipMessage.getCSeqHeader()).thenReturn(cSeqHeader);
    when(sipMessage.getViaHeader()).thenReturn(viaHeader);
    when(sipMessage.getCallIdHeader()).thenReturn(callIdHeader);

    // Act
    AckTransactionServer actualAckTransactionServer = new AckTransactionServer(sipProvider, sipMessage,
        new SipMessage(), mock(AckTransactionServerListener.class));

    // Assert
    assertNull(actualAckTransactionServer.getRequestMessage());
    SipProvider expectedSipProvider = actualAckTransactionServer.sip_provider;
    assertSame(expectedSipProvider, actualAckTransactionServer.getSipProvider());
    TransactionId expectedTransactionId = actualAckTransactionServer.transaction_id;
    TransactionId transactionId = actualAckTransactionServer.getTransactionId();
    assertSame(expectedTransactionId, transactionId);
    assertFalse(actualAckTransactionServer.isTerminated());
    assertNull(actualAckTransactionServer.getTransportConnId());
    assertNull(actualAckTransactionServer.response.getConnectionId());
    Timer timer = actualAckTransactionServer.transaction_to;
    assertFalse(timer.isRunning());
    assertEquals(32000L, timer.getTime());
    assertFalse(BasicSipMessage.DEBUG);
    Timer timer1 = actualAckTransactionServer.retransmission_to;
    assertEquals(500L, timer1.getTime());
    assertEquals("42-1-Method-server-janedoe/featurebranch", transactionId.toString());
    assertTrue(Timer.DEFAULT_DAEMON_MODE);
    assertFalse(timer1.isRunning());
    verify(sipProvider).getLogger();
    verify(logRotationWriter).log((org.zoolu.util.LogLevel) any(), (String) any());
    verify(sipMessage).getCSeqHeader();
    verify(sipMessage).getCallIdHeader();
    verify(sipMessage).getViaHeader();
    verify(cSeqHeader).getMethod();
    verify(cSeqHeader).getSequenceNumber();
    verify(viaHeader).getBranch();
    verify(viaHeader).getSentBy();
    verify(viaHeader).hasBranch();
    verify(callIdHeader).getCallId();
  }

  @Test
  void testConstructor13() {
    // Arrange
    LogRotationWriter logRotationWriter = mock(LogRotationWriter.class);
    doNothing().when(logRotationWriter).log((org.zoolu.util.LogLevel) any(), (String) any());
    SipProvider sipProvider = mock(SipProvider.class);
    when(sipProvider.getLogger()).thenReturn(logRotationWriter);
    CallIdHeader callIdHeader = mock(CallIdHeader.class);
    when(callIdHeader.getCallId()).thenReturn("42");
    ViaHeader viaHeader = mock(ViaHeader.class);
    when(viaHeader.getSentBy()).thenReturn("Sent By");
    when(viaHeader.getBranch()).thenReturn("janedoe/featurebranch");
    when(viaHeader.hasBranch()).thenReturn(true);
    CSeqHeader cSeqHeader = mock(CSeqHeader.class);
    when(cSeqHeader.getMethod()).thenReturn("Method");
    when(cSeqHeader.getSequenceNumber()).thenReturn(1L);
    SipMessage sipMessage = mock(SipMessage.class);
    when(sipMessage.getCSeqHeader()).thenReturn(cSeqHeader);
    when(sipMessage.getViaHeader()).thenReturn(viaHeader);
    when(sipMessage.getCallIdHeader()).thenReturn(callIdHeader);

    // Act
    AckTransactionServer actualAckTransactionServer = new AckTransactionServer(sipProvider, sipMessage,
        mock(SipMessage.class), mock(AckTransactionServerListener.class));

    // Assert
    assertNull(actualAckTransactionServer.getRequestMessage());
    SipProvider expectedSipProvider = actualAckTransactionServer.sip_provider;
    assertSame(expectedSipProvider, actualAckTransactionServer.getSipProvider());
    TransactionId expectedTransactionId = actualAckTransactionServer.transaction_id;
    TransactionId transactionId = actualAckTransactionServer.getTransactionId();
    assertSame(expectedTransactionId, transactionId);
    assertFalse(actualAckTransactionServer.isTerminated());
    assertNull(actualAckTransactionServer.getTransportConnId());
    Timer timer = actualAckTransactionServer.transaction_to;
    assertFalse(timer.isRunning());
    assertEquals(32000L, timer.getTime());
    assertFalse(BasicSipMessage.DEBUG);
    Timer timer1 = actualAckTransactionServer.retransmission_to;
    assertEquals(500L, timer1.getTime());
    assertEquals("42-1-Method-server-janedoe/featurebranch", transactionId.toString());
    assertTrue(Timer.DEFAULT_DAEMON_MODE);
    assertFalse(timer1.isRunning());
    verify(sipProvider).getLogger();
    verify(logRotationWriter).log((org.zoolu.util.LogLevel) any(), (String) any());
    verify(sipMessage).getCSeqHeader();
    verify(sipMessage).getCallIdHeader();
    verify(sipMessage).getViaHeader();
    verify(cSeqHeader).getMethod();
    verify(cSeqHeader).getSequenceNumber();
    verify(viaHeader).getBranch();
    verify(viaHeader).getSentBy();
    verify(viaHeader).hasBranch();
    verify(callIdHeader).getCallId();
  }

  @Test
  void testConstructor14() {
    // Arrange
    SipProvider sipProvider = mock(SipProvider.class);
    when(sipProvider.getLogger()).thenReturn(new LogWriter(new StringWriter()));
    ConnectionId connection_id = new ConnectionId("Protocol", new SocketAddress("42 Main St"));

    SipMessage sipMessage = mock(SipMessage.class);
    when(sipMessage.getCSeqHeader()).thenReturn(new CSeqHeader("42"));
    when(sipMessage.getViaHeader()).thenReturn(new ViaHeader("42"));
    when(sipMessage.getCallIdHeader()).thenReturn(new CallIdHeader("42"));

    // Act
    AckTransactionServer actualAckTransactionServer = new AckTransactionServer(sipProvider, connection_id, sipMessage,
        new SipMessage("Str"), mock(AckTransactionServerListener.class));

    // Assert
    assertNull(actualAckTransactionServer.getRequestMessage());
    SipProvider expectedSipProvider = actualAckTransactionServer.sip_provider;
    assertSame(expectedSipProvider, actualAckTransactionServer.getSipProvider());
    TransactionId expectedTransactionId = actualAckTransactionServer.transaction_id;
    TransactionId transactionId = actualAckTransactionServer.getTransactionId();
    assertSame(expectedTransactionId, transactionId);
    assertFalse(actualAckTransactionServer.isTerminated());
    ConnectionId expectedTransportConnId = actualAckTransactionServer.connection_id;
    ConnectionId transportConnId = actualAckTransactionServer.getTransportConnId();
    assertSame(expectedTransportConnId, transportConnId);
    assertTrue(actualAckTransactionServer.logger instanceof LogWriter);
    assertSame(transportConnId, actualAckTransactionServer.response.getConnectionId());
    assertFalse(BasicSipMessage.DEBUG);
    assertEquals("42-42--server-null", transactionId.toString());
    Timer timer = actualAckTransactionServer.transaction_to;
    assertFalse(timer.isRunning());
    assertEquals(32000L, timer.getTime());
    assertTrue(Timer.DEFAULT_DAEMON_MODE);
    verify(sipProvider).getLogger();
    verify(sipMessage).getCSeqHeader();
    verify(sipMessage).getCallIdHeader();
    verify(sipMessage).getViaHeader();
  }

  @Test
  void testConstructor15() {
    // Arrange
    SipProvider sipProvider = mock(SipProvider.class);
    when(sipProvider.getLogger()).thenReturn(new LogWriter((Writer) null));
    ConnectionId connection_id = new ConnectionId("Protocol", new SocketAddress("42 Main St"));

    SipMessage sipMessage = mock(SipMessage.class);
    when(sipMessage.getCSeqHeader()).thenReturn(new CSeqHeader("42"));
    when(sipMessage.getViaHeader()).thenReturn(new ViaHeader("42"));
    when(sipMessage.getCallIdHeader()).thenReturn(new CallIdHeader("42"));

    // Act
    AckTransactionServer actualAckTransactionServer = new AckTransactionServer(sipProvider, connection_id, sipMessage,
        new SipMessage("Str"), mock(AckTransactionServerListener.class));

    // Assert
    assertNull(actualAckTransactionServer.getRequestMessage());
    SipProvider expectedSipProvider = actualAckTransactionServer.sip_provider;
    assertSame(expectedSipProvider, actualAckTransactionServer.getSipProvider());
    TransactionId expectedTransactionId = actualAckTransactionServer.transaction_id;
    TransactionId transactionId = actualAckTransactionServer.getTransactionId();
    assertSame(expectedTransactionId, transactionId);
    assertFalse(actualAckTransactionServer.isTerminated());
    ConnectionId expectedTransportConnId = actualAckTransactionServer.connection_id;
    ConnectionId transportConnId = actualAckTransactionServer.getTransportConnId();
    assertSame(expectedTransportConnId, transportConnId);
    assertTrue(actualAckTransactionServer.logger instanceof LogWriter);
    assertSame(transportConnId, actualAckTransactionServer.response.getConnectionId());
    assertFalse(BasicSipMessage.DEBUG);
    assertEquals("42-42--server-null", transactionId.toString());
    Timer timer = actualAckTransactionServer.transaction_to;
    assertFalse(timer.isRunning());
    assertEquals(32000L, timer.getTime());
    assertTrue(Timer.DEFAULT_DAEMON_MODE);
    verify(sipProvider).getLogger();
    verify(sipMessage).getCSeqHeader();
    verify(sipMessage).getCallIdHeader();
    verify(sipMessage).getViaHeader();
  }

  @Test
  void testConstructor16() {
    // Arrange
    SipProvider sipProvider = mock(SipProvider.class);
    when(sipProvider.getLogger()).thenReturn(null);
    ConnectionId connection_id = new ConnectionId("Protocol", new SocketAddress("42 Main St"));

    SipMessage sipMessage = mock(SipMessage.class);
    when(sipMessage.getCSeqHeader()).thenReturn(new CSeqHeader("42"));
    when(sipMessage.getViaHeader()).thenReturn(new ViaHeader("42"));
    when(sipMessage.getCallIdHeader()).thenReturn(new CallIdHeader("42"));

    // Act
    AckTransactionServer actualAckTransactionServer = new AckTransactionServer(sipProvider, connection_id, sipMessage,
        new SipMessage("Str"), mock(AckTransactionServerListener.class));

    // Assert
    assertNull(actualAckTransactionServer.getRequestMessage());
    SipProvider expectedSipProvider = actualAckTransactionServer.sip_provider;
    assertSame(expectedSipProvider, actualAckTransactionServer.getSipProvider());
    TransactionId expectedTransactionId = actualAckTransactionServer.transaction_id;
    TransactionId transactionId = actualAckTransactionServer.getTransactionId();
    assertSame(expectedTransactionId, transactionId);
    assertFalse(actualAckTransactionServer.isTerminated());
    ConnectionId expectedTransportConnId = actualAckTransactionServer.connection_id;
    ConnectionId transportConnId = actualAckTransactionServer.getTransportConnId();
    assertSame(expectedTransportConnId, transportConnId);
    assertNull(actualAckTransactionServer.logger);
    assertSame(transportConnId, actualAckTransactionServer.response.getConnectionId());
    assertFalse(BasicSipMessage.DEBUG);
    assertEquals("42-42--server-null", transactionId.toString());
    Timer timer = actualAckTransactionServer.transaction_to;
    assertFalse(timer.isRunning());
    assertEquals(32000L, timer.getTime());
    assertTrue(Timer.DEFAULT_DAEMON_MODE);
    verify(sipProvider).getLogger();
    verify(sipMessage).getCSeqHeader();
    verify(sipMessage).getCallIdHeader();
    verify(sipMessage).getViaHeader();
  }

  @Test
  void testConstructor17() {
    // Arrange
    LogRotationWriter logRotationWriter = mock(LogRotationWriter.class);
    doNothing().when(logRotationWriter).log((org.zoolu.util.LogLevel) any(), (String) any());
    SipProvider sipProvider = mock(SipProvider.class);
    when(sipProvider.getLogger()).thenReturn(logRotationWriter);
    ConnectionId connection_id = new ConnectionId("Protocol", new SocketAddress("42 Main St"));

    SipMessage sipMessage = mock(SipMessage.class);
    when(sipMessage.getCSeqHeader()).thenReturn(new CSeqHeader("42"));
    when(sipMessage.getViaHeader()).thenReturn(new ViaHeader("42"));
    when(sipMessage.getCallIdHeader()).thenReturn(new CallIdHeader("42"));

    // Act
    AckTransactionServer actualAckTransactionServer = new AckTransactionServer(sipProvider, connection_id, sipMessage,
        new SipMessage("Str"), mock(AckTransactionServerListener.class));

    // Assert
    assertNull(actualAckTransactionServer.getRequestMessage());
    SipProvider expectedSipProvider = actualAckTransactionServer.sip_provider;
    assertSame(expectedSipProvider, actualAckTransactionServer.getSipProvider());
    TransactionId expectedTransactionId = actualAckTransactionServer.transaction_id;
    TransactionId transactionId = actualAckTransactionServer.getTransactionId();
    assertSame(expectedTransactionId, transactionId);
    assertFalse(actualAckTransactionServer.isTerminated());
    ConnectionId expectedTransportConnId = actualAckTransactionServer.connection_id;
    ConnectionId transportConnId = actualAckTransactionServer.getTransportConnId();
    assertSame(expectedTransportConnId, transportConnId);
    assertSame(transportConnId, actualAckTransactionServer.response.getConnectionId());
    assertFalse(BasicSipMessage.DEBUG);
    assertEquals("42-42--server-null", transactionId.toString());
    Timer timer = actualAckTransactionServer.transaction_to;
    assertFalse(timer.isRunning());
    assertEquals(32000L, timer.getTime());
    assertTrue(Timer.DEFAULT_DAEMON_MODE);
    verify(sipProvider).getLogger();
    verify(logRotationWriter).log((org.zoolu.util.LogLevel) any(), (String) any());
    verify(sipMessage).getCSeqHeader();
    verify(sipMessage).getCallIdHeader();
    verify(sipMessage).getViaHeader();
  }

  @Test
  void testConstructor18() {
    // Arrange
    LogRotationWriter logRotationWriter = mock(LogRotationWriter.class);
    doNothing().when(logRotationWriter).log((org.zoolu.util.LogLevel) any(), (String) any());
    SipProvider sipProvider = mock(SipProvider.class);
    when(sipProvider.getLogger()).thenReturn(logRotationWriter);
    ConnectionId connection_id = new ConnectionId("Protocol", new SocketAddress("42 Main St"));

    SipMessage sipMessage = mock(SipMessage.class);
    when(sipMessage.getCSeqHeader()).thenReturn(new CSeqHeader(1L, "Method"));
    when(sipMessage.getViaHeader()).thenReturn(new ViaHeader("42"));
    when(sipMessage.getCallIdHeader()).thenReturn(new CallIdHeader("42"));

    // Act
    AckTransactionServer actualAckTransactionServer = new AckTransactionServer(sipProvider, connection_id, sipMessage,
        new SipMessage("Str"), mock(AckTransactionServerListener.class));

    // Assert
    assertNull(actualAckTransactionServer.getRequestMessage());
    SipProvider expectedSipProvider = actualAckTransactionServer.sip_provider;
    assertSame(expectedSipProvider, actualAckTransactionServer.getSipProvider());
    TransactionId expectedTransactionId = actualAckTransactionServer.transaction_id;
    TransactionId transactionId = actualAckTransactionServer.getTransactionId();
    assertSame(expectedTransactionId, transactionId);
    assertFalse(actualAckTransactionServer.isTerminated());
    ConnectionId expectedTransportConnId = actualAckTransactionServer.connection_id;
    ConnectionId transportConnId = actualAckTransactionServer.getTransportConnId();
    assertSame(expectedTransportConnId, transportConnId);
    assertSame(transportConnId, actualAckTransactionServer.response.getConnectionId());
    assertFalse(BasicSipMessage.DEBUG);
    assertEquals("42-1-Method-server-null", transactionId.toString());
    Timer timer = actualAckTransactionServer.transaction_to;
    assertFalse(timer.isRunning());
    assertEquals(32000L, timer.getTime());
    assertTrue(Timer.DEFAULT_DAEMON_MODE);
    verify(sipProvider).getLogger();
    verify(logRotationWriter).log((org.zoolu.util.LogLevel) any(), (String) any());
    verify(sipMessage).getCSeqHeader();
    verify(sipMessage).getCallIdHeader();
    verify(sipMessage).getViaHeader();
  }

  @Test
  void testConstructor19() {
    // Arrange
    LogRotationWriter logRotationWriter = mock(LogRotationWriter.class);
    doNothing().when(logRotationWriter).log((org.zoolu.util.LogLevel) any(), (String) any());
    SipProvider sipProvider = mock(SipProvider.class);
    when(sipProvider.getLogger()).thenReturn(logRotationWriter);
    ConnectionId connection_id = new ConnectionId("Protocol", new SocketAddress("42 Main St"));

    SipMessage sipMessage = mock(SipMessage.class);
    when(sipMessage.getCSeqHeader()).thenReturn(new CSeqHeader(1L, "ACK"));
    when(sipMessage.getViaHeader()).thenReturn(new ViaHeader("42"));
    when(sipMessage.getCallIdHeader()).thenReturn(new CallIdHeader("42"));

    // Act
    AckTransactionServer actualAckTransactionServer = new AckTransactionServer(sipProvider, connection_id, sipMessage,
        new SipMessage("Str"), mock(AckTransactionServerListener.class));

    // Assert
    assertNull(actualAckTransactionServer.getRequestMessage());
    SipProvider expectedSipProvider = actualAckTransactionServer.sip_provider;
    assertSame(expectedSipProvider, actualAckTransactionServer.getSipProvider());
    TransactionId expectedTransactionId = actualAckTransactionServer.transaction_id;
    TransactionId transactionId = actualAckTransactionServer.getTransactionId();
    assertSame(expectedTransactionId, transactionId);
    assertFalse(actualAckTransactionServer.isTerminated());
    ConnectionId expectedTransportConnId = actualAckTransactionServer.connection_id;
    ConnectionId transportConnId = actualAckTransactionServer.getTransportConnId();
    assertSame(expectedTransportConnId, transportConnId);
    assertSame(transportConnId, actualAckTransactionServer.response.getConnectionId());
    assertFalse(BasicSipMessage.DEBUG);
    assertEquals("42-1-INVITE-server-null", transactionId.toString());
    Timer timer = actualAckTransactionServer.transaction_to;
    assertFalse(timer.isRunning());
    assertEquals(32000L, timer.getTime());
    assertTrue(Timer.DEFAULT_DAEMON_MODE);
    verify(sipProvider).getLogger();
    verify(logRotationWriter).log((org.zoolu.util.LogLevel) any(), (String) any());
    verify(sipMessage).getCSeqHeader();
    verify(sipMessage).getCallIdHeader();
    verify(sipMessage).getViaHeader();
  }

  @Test
  void testConstructor20() {
    // Arrange
    LogRotationWriter logRotationWriter = mock(LogRotationWriter.class);
    doNothing().when(logRotationWriter).log((org.zoolu.util.LogLevel) any(), (String) any());
    SipProvider sipProvider = mock(SipProvider.class);
    when(sipProvider.getLogger()).thenReturn(logRotationWriter);
    ConnectionId connection_id = new ConnectionId("Protocol", new SocketAddress("42 Main St"));

    CSeqHeader cSeqHeader = mock(CSeqHeader.class);
    when(cSeqHeader.getMethod()).thenReturn("Method");
    when(cSeqHeader.getSequenceNumber()).thenReturn(1L);
    SipMessage sipMessage = mock(SipMessage.class);
    when(sipMessage.getCSeqHeader()).thenReturn(cSeqHeader);
    when(sipMessage.getViaHeader()).thenReturn(new ViaHeader("42"));
    when(sipMessage.getCallIdHeader()).thenReturn(new CallIdHeader("42"));

    // Act
    AckTransactionServer actualAckTransactionServer = new AckTransactionServer(sipProvider, connection_id, sipMessage,
        new SipMessage("Str"), mock(AckTransactionServerListener.class));

    // Assert
    assertNull(actualAckTransactionServer.getRequestMessage());
    SipProvider expectedSipProvider = actualAckTransactionServer.sip_provider;
    assertSame(expectedSipProvider, actualAckTransactionServer.getSipProvider());
    TransactionId expectedTransactionId = actualAckTransactionServer.transaction_id;
    TransactionId transactionId = actualAckTransactionServer.getTransactionId();
    assertSame(expectedTransactionId, transactionId);
    assertFalse(actualAckTransactionServer.isTerminated());
    ConnectionId expectedTransportConnId = actualAckTransactionServer.connection_id;
    ConnectionId transportConnId = actualAckTransactionServer.getTransportConnId();
    assertSame(expectedTransportConnId, transportConnId);
    assertSame(transportConnId, actualAckTransactionServer.response.getConnectionId());
    assertFalse(BasicSipMessage.DEBUG);
    assertEquals("42-1-Method-server-null", transactionId.toString());
    Timer timer = actualAckTransactionServer.transaction_to;
    assertFalse(timer.isRunning());
    assertEquals(32000L, timer.getTime());
    assertTrue(Timer.DEFAULT_DAEMON_MODE);
    verify(sipProvider).getLogger();
    verify(logRotationWriter).log((org.zoolu.util.LogLevel) any(), (String) any());
    verify(sipMessage).getCSeqHeader();
    verify(sipMessage).getCallIdHeader();
    verify(sipMessage).getViaHeader();
    verify(cSeqHeader).getMethod();
    verify(cSeqHeader).getSequenceNumber();
  }

  @Test
  void testConstructor21() {
    // Arrange
    LogRotationWriter logRotationWriter = mock(LogRotationWriter.class);
    doNothing().when(logRotationWriter).log((org.zoolu.util.LogLevel) any(), (String) any());
    SipProvider sipProvider = mock(SipProvider.class);
    when(sipProvider.getLogger()).thenReturn(logRotationWriter);
    ConnectionId connection_id = new ConnectionId("Protocol", new SocketAddress("42 Main St"));

    CSeqHeader cSeqHeader = mock(CSeqHeader.class);
    when(cSeqHeader.getMethod()).thenReturn("Method");
    when(cSeqHeader.getSequenceNumber()).thenReturn(1L);
    SipMessage sipMessage = mock(SipMessage.class);
    when(sipMessage.getCSeqHeader()).thenReturn(cSeqHeader);
    when(sipMessage.getViaHeader()).thenReturn(new ViaHeader("localhost", 8080));
    when(sipMessage.getCallIdHeader()).thenReturn(new CallIdHeader("42"));

    // Act
    AckTransactionServer actualAckTransactionServer = new AckTransactionServer(sipProvider, connection_id, sipMessage,
        new SipMessage("Str"), mock(AckTransactionServerListener.class));

    // Assert
    assertNull(actualAckTransactionServer.getRequestMessage());
    SipProvider expectedSipProvider = actualAckTransactionServer.sip_provider;
    assertSame(expectedSipProvider, actualAckTransactionServer.getSipProvider());
    TransactionId expectedTransactionId = actualAckTransactionServer.transaction_id;
    TransactionId transactionId = actualAckTransactionServer.getTransactionId();
    assertSame(expectedTransactionId, transactionId);
    assertFalse(actualAckTransactionServer.isTerminated());
    ConnectionId expectedTransportConnId = actualAckTransactionServer.connection_id;
    ConnectionId transportConnId = actualAckTransactionServer.getTransportConnId();
    assertSame(expectedTransportConnId, transportConnId);
    assertSame(transportConnId, actualAckTransactionServer.response.getConnectionId());
    assertFalse(BasicSipMessage.DEBUG);
    assertEquals("42-1-Method-server-localhost:8080", transactionId.toString());
    Timer timer = actualAckTransactionServer.transaction_to;
    assertFalse(timer.isRunning());
    assertEquals(32000L, timer.getTime());
    assertTrue(Timer.DEFAULT_DAEMON_MODE);
    verify(sipProvider).getLogger();
    verify(logRotationWriter).log((org.zoolu.util.LogLevel) any(), (String) any());
    verify(sipMessage).getCSeqHeader();
    verify(sipMessage).getCallIdHeader();
    verify(sipMessage).getViaHeader();
    verify(cSeqHeader).getMethod();
    verify(cSeqHeader).getSequenceNumber();
  }

  @Test
  void testConstructor22() {
    // Arrange
    LogRotationWriter logRotationWriter = mock(LogRotationWriter.class);
    doNothing().when(logRotationWriter).log((org.zoolu.util.LogLevel) any(), (String) any());
    SipProvider sipProvider = mock(SipProvider.class);
    when(sipProvider.getLogger()).thenReturn(logRotationWriter);
    ConnectionId connection_id = new ConnectionId("Protocol", new SocketAddress("42 Main St"));

    CSeqHeader cSeqHeader = mock(CSeqHeader.class);
    when(cSeqHeader.getMethod()).thenReturn("Method");
    when(cSeqHeader.getSequenceNumber()).thenReturn(1L);
    SipMessage sipMessage = mock(SipMessage.class);
    when(sipMessage.getCSeqHeader()).thenReturn(cSeqHeader);
    when(sipMessage.getViaHeader()).thenReturn(null);
    when(sipMessage.getCallIdHeader()).thenReturn(new CallIdHeader("42"));

    // Act
    AckTransactionServer actualAckTransactionServer = new AckTransactionServer(sipProvider, connection_id, sipMessage,
        new SipMessage("Str"), mock(AckTransactionServerListener.class));

    // Assert
    assertNull(actualAckTransactionServer.getRequestMessage());
    SipProvider expectedSipProvider = actualAckTransactionServer.sip_provider;
    assertSame(expectedSipProvider, actualAckTransactionServer.getSipProvider());
    TransactionId expectedTransactionId = actualAckTransactionServer.transaction_id;
    TransactionId transactionId = actualAckTransactionServer.getTransactionId();
    assertSame(expectedTransactionId, transactionId);
    assertFalse(actualAckTransactionServer.isTerminated());
    ConnectionId expectedTransportConnId = actualAckTransactionServer.connection_id;
    ConnectionId transportConnId = actualAckTransactionServer.getTransportConnId();
    assertSame(expectedTransportConnId, transportConnId);
    assertSame(transportConnId, actualAckTransactionServer.response.getConnectionId());
    assertFalse(BasicSipMessage.DEBUG);
    assertEquals("42-1-Method-server-null", transactionId.toString());
    Timer timer = actualAckTransactionServer.transaction_to;
    assertFalse(timer.isRunning());
    assertEquals(32000L, timer.getTime());
    assertTrue(Timer.DEFAULT_DAEMON_MODE);
    verify(sipProvider).getLogger();
    verify(logRotationWriter).log((org.zoolu.util.LogLevel) any(), (String) any());
    verify(sipMessage).getCSeqHeader();
    verify(sipMessage).getCallIdHeader();
    verify(sipMessage).getViaHeader();
    verify(cSeqHeader).getMethod();
    verify(cSeqHeader).getSequenceNumber();
  }
}

