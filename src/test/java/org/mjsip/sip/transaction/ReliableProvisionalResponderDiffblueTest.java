package org.mjsip.sip.transaction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.UnsupportedEncodingException;
import java.util.Vector;
import org.junit.jupiter.api.Test;
import org.mjsip.sip.dialog.ExtendedInviteDialog;
import org.mjsip.sip.header.RequestLine;
import org.mjsip.sip.header.StatusLine;
import org.mjsip.sip.message.SipMessage;
import org.mjsip.sip.provider.SipProvider;
import org.zoolu.util.LogLevel;
import org.zoolu.util.Timer;

class ReliableProvisionalResponderDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    InviteTransactionServer invite_ts = new InviteTransactionServer(sip_provider,
        new ExtendedInviteDialog(new SipProvider("File"), null));

    // Act
    ReliableProvisionalResponder actualReliableProvisionalResponder = new ReliableProvisionalResponder(invite_ts,
        new ExtendedInviteDialog(new SipProvider("File"), null));

    // Assert
    assertFalse(actualReliableProvisionalResponder.hasPendingResponses());
    assertNull(actualReliableProvisionalResponder.transaction_to);
    assertEquals(1L, actualReliableProvisionalResponder.rseq_counter);
    assertNull(actualReliableProvisionalResponder.retransmission_to);
    assertNull(actualReliableProvisionalResponder.logger);
    assertTrue(actualReliableProvisionalResponder.listener instanceof ExtendedInviteDialog);
    assertNull(actualReliableProvisionalResponder.invite_ts.logger);
  }

  @Test
  void testRespond() throws UnsupportedEncodingException {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    InviteTransactionServer invite_ts = new InviteTransactionServer(sip_provider,
        new ExtendedInviteDialog(new SipProvider("File"), null));

    ReliableProvisionalResponder reliableProvisionalResponder = new ReliableProvisionalResponder(invite_ts,
        new ExtendedInviteDialog(new SipProvider("File"), null));
    StatusLine status_line = new StatusLine(1, "foo");

    Vector vector = new Vector(1);
    SipMessage sipMessage = new SipMessage(status_line, vector, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    // Act
    reliableProvisionalResponder.respond(sipMessage);

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
    assertEquals(67, sipMessage.getLength());
    assertEquals(vector, sipMessage.getHeaders());
    assertNull(sipMessage.getConnectionId());
    assertEquals(2L, reliableProvisionalResponder.rseq_counter);
    assertSame(sipMessage, reliableProvisionalResponder.invite_ts.response);
    assertTrue(Timer.DEFAULT_DAEMON_MODE);
  }

  @Test
  void testProcessPrack() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    InviteTransactionServer invite_ts = new InviteTransactionServer(sip_provider,
        new ExtendedInviteDialog(new SipProvider("File"), null));

    ReliableProvisionalResponder reliableProvisionalResponder = new ReliableProvisionalResponder(invite_ts,
        new ExtendedInviteDialog(new SipProvider("File"), null));

    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.setRequestLine(new RequestLine("Request", "Str uri"));
    Vector vector = new Vector(1);
    sipMessage.addHeaders(vector, true);

    // Act
    reliableProvisionalResponder.processPrack(sipMessage);

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
    assertEquals("Request Str uri SIP/2.0\r\n", sipMessage.getFirstLine());
    assertEquals(1L, reliableProvisionalResponder.rseq_counter);
    assertEquals(vector, reliableProvisionalResponder.responses);
    assertTrue(reliableProvisionalResponder.listener instanceof ExtendedInviteDialog);
  }

  @Test
  void testHasPendingResponses() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    InviteTransactionServer invite_ts = new InviteTransactionServer(sip_provider,
        new ExtendedInviteDialog(new SipProvider("File"), null));

    // Act and Assert
    assertFalse((new ReliableProvisionalResponder(invite_ts, new ExtendedInviteDialog(new SipProvider("File"), null)))
        .hasPendingResponses());
  }

  @Test
  void testTerminate() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    InviteTransactionServer invite_ts = new InviteTransactionServer(sip_provider,
        new ExtendedInviteDialog(new SipProvider("File"), null));

    ReliableProvisionalResponder reliableProvisionalResponder = new ReliableProvisionalResponder(invite_ts,
        new ExtendedInviteDialog(new SipProvider("File"), null));

    // Act
    reliableProvisionalResponder.terminate();

    // Assert
    assertNull(reliableProvisionalResponder.transaction_to);
    assertNull(reliableProvisionalResponder.retransmission_to);
    assertNull(reliableProvisionalResponder.listener);
  }

  @Test
  void testLog() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    InviteTransactionServer invite_ts = new InviteTransactionServer(sip_provider,
        new ExtendedInviteDialog(new SipProvider("File"), null));

    ReliableProvisionalResponder reliableProvisionalResponder = new ReliableProvisionalResponder(invite_ts,
        new ExtendedInviteDialog(new SipProvider("File"), null));
    LogLevel logLevel = new LogLevel("Name", 42);

    // Act
    reliableProvisionalResponder.log(logLevel, new Exception("An error occurred"));

    // Assert that nothing has changed
    assertEquals("Name", logLevel.getName());
    assertEquals(42, logLevel.getValue());
    assertFalse(reliableProvisionalResponder.hasPendingResponses());
    assertEquals(1L, reliableProvisionalResponder.rseq_counter);
    assertTrue(reliableProvisionalResponder.listener instanceof ExtendedInviteDialog);
  }

  @Test
  void testLog2() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    InviteTransactionServer invite_ts = new InviteTransactionServer(sip_provider,
        new ExtendedInviteDialog(new SipProvider("File"), null));

    ReliableProvisionalResponder reliableProvisionalResponder = new ReliableProvisionalResponder(invite_ts,
        new ExtendedInviteDialog(new SipProvider("File"), null));
    LogLevel logLevel = new LogLevel("Name", 42);

    // Act
    reliableProvisionalResponder.log(logLevel, "Str");

    // Assert that nothing has changed
    assertEquals("Name", logLevel.getName());
    assertEquals(42, logLevel.getValue());
    assertFalse(reliableProvisionalResponder.hasPendingResponses());
    assertEquals(1L, reliableProvisionalResponder.rseq_counter);
    assertTrue(reliableProvisionalResponder.listener instanceof ExtendedInviteDialog);
  }
}

