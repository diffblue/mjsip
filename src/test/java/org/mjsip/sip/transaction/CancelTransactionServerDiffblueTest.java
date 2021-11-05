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
import org.junit.jupiter.api.Test;
import org.mjsip.sip.header.CSeqHeader;
import org.mjsip.sip.header.CallIdHeader;
import org.mjsip.sip.header.ViaHeader;
import org.mjsip.sip.message.SipMessage;
import org.mjsip.sip.provider.SipProvider;
import org.mjsip.sip.provider.TransactionId;
import org.zoolu.util.LogRotationWriter;
import org.zoolu.util.LogWriter;
import org.zoolu.util.Timer;

class CancelTransactionServerDiffblueTest {
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
    CancelTransactionServer actualCancelTransactionServer = new CancelTransactionServer(sipProvider, sipMessage,
        mock(TransactionServerListener.class));

    // Assert
    assertNull(actualCancelTransactionServer.getRequestMessage());
    assertEquals(0, actualCancelTransactionServer.status);
    assertNull(actualCancelTransactionServer.response);
    assertTrue(actualCancelTransactionServer.logger instanceof LogWriter);
    SipProvider expectedSipProvider = actualCancelTransactionServer.sip_provider;
    assertSame(expectedSipProvider, actualCancelTransactionServer.getSipProvider());
    TransactionId expectedTransactionId = actualCancelTransactionServer.transaction_id;
    TransactionId transactionId = actualCancelTransactionServer.getTransactionId();
    assertSame(expectedTransactionId, transactionId);
    assertNull(actualCancelTransactionServer.getTransportConnId());
    assertTrue(Timer.DEFAULT_DAEMON_MODE);
    Timer timer = actualCancelTransactionServer.clearing_to;
    assertEquals(32000L, timer.getTime());
    assertFalse(timer.isRunning());
    assertEquals("42-42-CANCEL-server-null", transactionId.toString());
    verify(sipProvider).getLogger();
    verify(sipMessage).getCSeqHeader();
    verify(sipMessage).getCallIdHeader();
    verify(sipMessage).getViaHeader();
  }

  @Test
  void testConstructor2() {
    // Arrange
    SipProvider sipProvider = mock(SipProvider.class);
    when(sipProvider.getLogger()).thenReturn(null);
    SipMessage sipMessage = mock(SipMessage.class);
    when(sipMessage.getCSeqHeader()).thenReturn(new CSeqHeader("42"));
    when(sipMessage.getViaHeader()).thenReturn(new ViaHeader("42"));
    when(sipMessage.getCallIdHeader()).thenReturn(new CallIdHeader("42"));

    // Act
    CancelTransactionServer actualCancelTransactionServer = new CancelTransactionServer(sipProvider, sipMessage,
        mock(TransactionServerListener.class));

    // Assert
    assertNull(actualCancelTransactionServer.getRequestMessage());
    assertEquals(0, actualCancelTransactionServer.status);
    assertNull(actualCancelTransactionServer.response);
    assertNull(actualCancelTransactionServer.logger);
    SipProvider expectedSipProvider = actualCancelTransactionServer.sip_provider;
    assertSame(expectedSipProvider, actualCancelTransactionServer.getSipProvider());
    TransactionId expectedTransactionId = actualCancelTransactionServer.transaction_id;
    TransactionId transactionId = actualCancelTransactionServer.getTransactionId();
    assertSame(expectedTransactionId, transactionId);
    assertNull(actualCancelTransactionServer.getTransportConnId());
    assertTrue(Timer.DEFAULT_DAEMON_MODE);
    Timer timer = actualCancelTransactionServer.clearing_to;
    assertEquals(32000L, timer.getTime());
    assertFalse(timer.isRunning());
    assertEquals("42-42-CANCEL-server-null", transactionId.toString());
    verify(sipProvider).getLogger();
    verify(sipMessage).getCSeqHeader();
    verify(sipMessage).getCallIdHeader();
    verify(sipMessage).getViaHeader();
  }

  @Test
  void testConstructor3() {
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
    CancelTransactionServer actualCancelTransactionServer = new CancelTransactionServer(sipProvider, sipMessage,
        mock(TransactionServerListener.class));

    // Assert
    assertNull(actualCancelTransactionServer.getRequestMessage());
    assertEquals(0, actualCancelTransactionServer.status);
    assertNull(actualCancelTransactionServer.response);
    SipProvider expectedSipProvider = actualCancelTransactionServer.sip_provider;
    assertSame(expectedSipProvider, actualCancelTransactionServer.getSipProvider());
    TransactionId expectedTransactionId = actualCancelTransactionServer.transaction_id;
    TransactionId transactionId = actualCancelTransactionServer.getTransactionId();
    assertSame(expectedTransactionId, transactionId);
    assertNull(actualCancelTransactionServer.getTransportConnId());
    assertTrue(Timer.DEFAULT_DAEMON_MODE);
    Timer timer = actualCancelTransactionServer.clearing_to;
    assertEquals(32000L, timer.getTime());
    assertFalse(timer.isRunning());
    assertEquals("42-42-CANCEL-server-null", transactionId.toString());
    verify(sipProvider).getLogger();
    verify(logRotationWriter).log((org.zoolu.util.LogLevel) any(), (String) any());
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
    when(sipMessage.getCSeqHeader()).thenReturn(new CSeqHeader(1L, "Method"));
    when(sipMessage.getViaHeader()).thenReturn(new ViaHeader("42"));
    when(sipMessage.getCallIdHeader()).thenReturn(new CallIdHeader("42"));

    // Act
    CancelTransactionServer actualCancelTransactionServer = new CancelTransactionServer(sipProvider, sipMessage,
        mock(TransactionServerListener.class));

    // Assert
    assertNull(actualCancelTransactionServer.getRequestMessage());
    assertEquals(0, actualCancelTransactionServer.status);
    assertNull(actualCancelTransactionServer.response);
    SipProvider expectedSipProvider = actualCancelTransactionServer.sip_provider;
    assertSame(expectedSipProvider, actualCancelTransactionServer.getSipProvider());
    TransactionId expectedTransactionId = actualCancelTransactionServer.transaction_id;
    TransactionId transactionId = actualCancelTransactionServer.getTransactionId();
    assertSame(expectedTransactionId, transactionId);
    assertNull(actualCancelTransactionServer.getTransportConnId());
    assertTrue(Timer.DEFAULT_DAEMON_MODE);
    Timer timer = actualCancelTransactionServer.clearing_to;
    assertEquals(32000L, timer.getTime());
    assertFalse(timer.isRunning());
    assertEquals("42-1-CANCEL-server-null", transactionId.toString());
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
    when(sipMessage.getViaHeader()).thenReturn(null);
    when(sipMessage.getCallIdHeader()).thenReturn(new CallIdHeader("42"));

    // Act
    CancelTransactionServer actualCancelTransactionServer = new CancelTransactionServer(sipProvider, sipMessage,
        mock(TransactionServerListener.class));

    // Assert
    assertNull(actualCancelTransactionServer.getRequestMessage());
    assertEquals(0, actualCancelTransactionServer.status);
    assertNull(actualCancelTransactionServer.response);
    SipProvider expectedSipProvider = actualCancelTransactionServer.sip_provider;
    assertSame(expectedSipProvider, actualCancelTransactionServer.getSipProvider());
    TransactionId expectedTransactionId = actualCancelTransactionServer.transaction_id;
    TransactionId transactionId = actualCancelTransactionServer.getTransactionId();
    assertSame(expectedTransactionId, transactionId);
    assertNull(actualCancelTransactionServer.getTransportConnId());
    assertTrue(Timer.DEFAULT_DAEMON_MODE);
    Timer timer = actualCancelTransactionServer.clearing_to;
    assertEquals(32000L, timer.getTime());
    assertFalse(timer.isRunning());
    assertEquals("42-1-CANCEL-server-null", transactionId.toString());
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
    ViaHeader viaHeader = mock(ViaHeader.class);
    when(viaHeader.getSentBy()).thenReturn("Sent By");
    when(viaHeader.getBranch()).thenReturn("janedoe/featurebranch");
    when(viaHeader.hasBranch()).thenReturn(true);
    SipMessage sipMessage = mock(SipMessage.class);
    when(sipMessage.getCSeqHeader()).thenReturn(new CSeqHeader(1L, "Method"));
    when(sipMessage.getViaHeader()).thenReturn(viaHeader);
    when(sipMessage.getCallIdHeader()).thenReturn(new CallIdHeader("42"));

    // Act
    CancelTransactionServer actualCancelTransactionServer = new CancelTransactionServer(sipProvider, sipMessage,
        mock(TransactionServerListener.class));

    // Assert
    assertNull(actualCancelTransactionServer.getRequestMessage());
    assertEquals(0, actualCancelTransactionServer.status);
    assertNull(actualCancelTransactionServer.response);
    SipProvider expectedSipProvider = actualCancelTransactionServer.sip_provider;
    assertSame(expectedSipProvider, actualCancelTransactionServer.getSipProvider());
    TransactionId expectedTransactionId = actualCancelTransactionServer.transaction_id;
    TransactionId transactionId = actualCancelTransactionServer.getTransactionId();
    assertSame(expectedTransactionId, transactionId);
    assertNull(actualCancelTransactionServer.getTransportConnId());
    assertTrue(Timer.DEFAULT_DAEMON_MODE);
    Timer timer = actualCancelTransactionServer.clearing_to;
    assertEquals(32000L, timer.getTime());
    assertFalse(timer.isRunning());
    assertEquals("42-1-CANCEL-server-janedoe/featurebranch", transactionId.toString());
    verify(sipProvider).getLogger();
    verify(logRotationWriter).log((org.zoolu.util.LogLevel) any(), (String) any());
    verify(sipMessage).getCSeqHeader();
    verify(sipMessage).getCallIdHeader();
    verify(sipMessage).getViaHeader();
    verify(viaHeader).getBranch();
    verify(viaHeader).getSentBy();
    verify(viaHeader).hasBranch();
  }
}

