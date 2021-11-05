package org.mjsip.sip.provider;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.mjsip.sip.header.CSeqHeader;
import org.mjsip.sip.header.CallIdHeader;
import org.mjsip.sip.header.ViaHeader;
import org.mjsip.sip.message.SipMessage;

class TransactionServerIdDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange, Act and Assert
    assertEquals("Method", (new TransactionServerId("Method")).toString());
    assertEquals("Call id-1-Method-server-janedoe/featurebranch",
        (new TransactionServerId("Call id", 1L, "Method", "Sent by", "janedoe/featurebranch")).toString());
    assertEquals("Call id-1-INVITE-server-janedoe/featurebranch",
        (new TransactionServerId("Call id", 1L, "ACK", "Sent by", "janedoe/featurebranch")).toString());
    assertEquals("Method", (new TransactionServerId(new TransactionId("Method"))).toString());
  }

  @Test
  void testConstructor2() {
    // Arrange
    SipMessage sipMessage = mock(SipMessage.class);
    when(sipMessage.getCSeqHeader()).thenReturn(new CSeqHeader("42"));
    when(sipMessage.getViaHeader()).thenReturn(new ViaHeader("42"));
    when(sipMessage.getCallIdHeader()).thenReturn(new CallIdHeader("42"));

    // Act and Assert
    assertEquals("42-42--server-null", (new TransactionServerId(sipMessage)).toString());
    verify(sipMessage).getCSeqHeader();
    verify(sipMessage).getCallIdHeader();
    verify(sipMessage).getViaHeader();
  }

  @Test
  void testConstructor3() {
    // Arrange
    SipMessage sipMessage = mock(SipMessage.class);
    when(sipMessage.getCSeqHeader()).thenReturn(new CSeqHeader(1L, "Method"));
    when(sipMessage.getViaHeader()).thenReturn(new ViaHeader("42"));
    when(sipMessage.getCallIdHeader()).thenReturn(new CallIdHeader("42"));

    // Act and Assert
    assertEquals("42-1-Method-server-null", (new TransactionServerId(sipMessage)).toString());
    verify(sipMessage).getCSeqHeader();
    verify(sipMessage).getCallIdHeader();
    verify(sipMessage).getViaHeader();
  }

  @Test
  void testConstructor4() {
    // Arrange
    CSeqHeader cSeqHeader = mock(CSeqHeader.class);
    when(cSeqHeader.getMethod()).thenReturn("Method");
    when(cSeqHeader.getSequenceNumber()).thenReturn(1L);
    SipMessage sipMessage = mock(SipMessage.class);
    when(sipMessage.getCSeqHeader()).thenReturn(cSeqHeader);
    when(sipMessage.getViaHeader()).thenReturn(new ViaHeader("42"));
    when(sipMessage.getCallIdHeader()).thenReturn(new CallIdHeader("42"));

    // Act and Assert
    assertEquals("42-1-Method-server-null", (new TransactionServerId(sipMessage)).toString());
    verify(sipMessage).getCSeqHeader();
    verify(sipMessage).getCallIdHeader();
    verify(sipMessage).getViaHeader();
    verify(cSeqHeader).getMethod();
    verify(cSeqHeader).getSequenceNumber();
  }

  @Test
  void testConstructor5() {
    // Arrange
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

    // Act and Assert
    assertEquals("42-1-Method-server-janedoe/featurebranch", (new TransactionServerId(sipMessage)).toString());
    verify(sipMessage).getCSeqHeader();
    verify(sipMessage).getCallIdHeader();
    verify(sipMessage).getViaHeader();
    verify(cSeqHeader).getMethod();
    verify(cSeqHeader).getSequenceNumber();
    verify(viaHeader).getBranch();
    verify(viaHeader).getSentBy();
    verify(viaHeader).hasBranch();
  }
}

