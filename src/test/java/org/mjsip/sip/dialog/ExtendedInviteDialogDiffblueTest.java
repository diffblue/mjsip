package org.mjsip.sip.dialog;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.io.UnsupportedEncodingException;
import java.util.Vector;
import org.junit.jupiter.api.Test;
import org.mjsip.sip.address.NameAddress;
import org.mjsip.sip.header.CSeqHeader;
import org.mjsip.sip.header.CallIdHeader;
import org.mjsip.sip.header.ContactHeader;
import org.mjsip.sip.header.FromHeader;
import org.mjsip.sip.header.Header;
import org.mjsip.sip.header.MultipleHeader;
import org.mjsip.sip.header.StatusLine;
import org.mjsip.sip.header.ToHeader;
import org.mjsip.sip.header.ViaHeader;
import org.mjsip.sip.message.SipMessage;
import org.mjsip.sip.provider.DialogId;
import org.mjsip.sip.provider.SipProvider;
import org.zoolu.util.LogLevel;

class ExtendedInviteDialogDiffblueTest {
  @Test
  void testChangeStatus() {
    // Arrange
    ExtendedInviteDialog extendedInviteDialog = new ExtendedInviteDialog(new SipProvider("File"), null);

    // Act
    extendedInviteDialog.changeStatus(1);

    // Assert
    assertEquals(1, extendedInviteDialog.status);
  }

  @Test
  void testChangeStatus2() {
    // Arrange
    ExtendedInviteDialog extendedInviteDialog = new ExtendedInviteDialog(new SipProvider("File"), null);

    // Act
    extendedInviteDialog.changeStatus(0);

    // Assert
    assertEquals(0, extendedInviteDialog.status);
  }

  @Test
  void testChangeStatus3() {
    // Arrange
    ExtendedInviteDialog extendedInviteDialog = new ExtendedInviteDialog(new SipProvider("File"), null);

    // Act
    extendedInviteDialog.changeStatus(2);

    // Assert
    assertEquals(2, extendedInviteDialog.status);
  }

  @Test
  void testChangeStatus4() {
    // Arrange
    ExtendedInviteDialog extendedInviteDialog = new ExtendedInviteDialog(new SipProvider("File"), null);

    // Act
    extendedInviteDialog.changeStatus(3);

    // Assert
    assertEquals(3, extendedInviteDialog.status);
  }

  @Test
  void testChangeStatus5() {
    // Arrange
    ExtendedInviteDialog extendedInviteDialog = new ExtendedInviteDialog(new SipProvider("File"), null);

    // Act
    extendedInviteDialog.changeStatus(4);

    // Assert
    assertEquals(4, extendedInviteDialog.status);
  }

  @Test
  void testChangeStatus6() {
    // Arrange
    ExtendedInviteDialog extendedInviteDialog = new ExtendedInviteDialog(new SipProvider("File"), null);

    // Act
    extendedInviteDialog.changeStatus(5);

    // Assert
    assertEquals(5, extendedInviteDialog.status);
  }

  @Test
  void testChangeStatus7() {
    // Arrange
    ExtendedInviteDialog extendedInviteDialog = new ExtendedInviteDialog(new SipProvider("File"), null);

    // Act
    extendedInviteDialog.changeStatus(6);

    // Assert
    assertEquals(6, extendedInviteDialog.status);
  }

  @Test
  void testChangeStatus8() {
    // Arrange
    ExtendedInviteDialog extendedInviteDialog = new ExtendedInviteDialog(new SipProvider("File"), null);

    // Act
    extendedInviteDialog.changeStatus(-1);

    // Assert
    assertEquals(-1, extendedInviteDialog.status);
  }

  @Test
  void testChangeStatus9() {
    // Arrange
    ExtendedInviteDialog extendedInviteDialog = new ExtendedInviteDialog(new SipProvider("File"), null);

    // Act
    extendedInviteDialog.changeStatus(7);

    // Assert
    assertEquals(7, extendedInviteDialog.status);
  }

  @Test
  void testChangeStatus10() {
    // Arrange
    ExtendedInviteDialog extendedInviteDialog = new ExtendedInviteDialog(new SipProvider("File"), null);

    // Act
    extendedInviteDialog.changeStatus(8);

    // Assert
    assertEquals(8, extendedInviteDialog.status);
  }

  @Test
  void testChangeStatus11() {
    // Arrange
    ExtendedInviteDialog extendedInviteDialog = new ExtendedInviteDialog(new SipProvider("File"), null);

    // Act
    extendedInviteDialog.changeStatus(9);

    // Assert
    assertEquals(9, extendedInviteDialog.status);
  }

  @Test
  void testConstructor() {
    // Arrange and Act
    ExtendedInviteDialog actualExtendedInviteDialog = new ExtendedInviteDialog(new SipProvider("File"), "janedoe",
        "Realm", "iloveyou", mock(ExtendedInviteDialogListener.class));

    // Assert
    assertNull(actualExtendedInviteDialog.getCallID());
    assertEquals("janedoe", actualExtendedInviteDialog.username);
    assertTrue(actualExtendedInviteDialog.transactions.isEmpty());
    assertEquals(2, actualExtendedInviteDialog.supported_option_tags.length);
    assertEquals(0, actualExtendedInviteDialog.status);
    assertNull(actualExtendedInviteDialog.required_option_tags);
    assertNull(actualExtendedInviteDialog.reliable_responder);
    assertEquals("Realm", actualExtendedInviteDialog.realm);
    assertNull(actualExtendedInviteDialog.qop);
    assertNull(actualExtendedInviteDialog.proxy_required_option_tags);
    assertEquals("iloveyou", actualExtendedInviteDialog.passwd);
    assertNull(actualExtendedInviteDialog.next_nonce);
    assertNull(actualExtendedInviteDialog.logger);
    assertTrue(actualExtendedInviteDialog.invite_offer);
    assertNull(actualExtendedInviteDialog.inv2xx_resp);
    assertNull(actualExtendedInviteDialog.info_packages);
    assertSame(actualExtendedInviteDialog.listener, actualExtendedInviteDialog.ext_listener);
    assertEquals(0, actualExtendedInviteDialog.attempts);
    assertEquals(10, actualExtendedInviteDialog.allowed_methods.length);
    assertNull(actualExtendedInviteDialog.ack_req);
    assertFalse(actualExtendedInviteDialog.isSecure());
    SipProvider expectedSipProvider = actualExtendedInviteDialog.sip_provider;
    assertSame(expectedSipProvider, actualExtendedInviteDialog.getSipProvider());
    assertEquals(0, actualExtendedInviteDialog.getSessionInterval());
    assertNull(actualExtendedInviteDialog.getDialogID());
    assertEquals(-1L, actualExtendedInviteDialog.getLastRSeq());
    assertNull(actualExtendedInviteDialog.getLocalTag());
    assertNull(actualExtendedInviteDialog.getRefresher());
    assertEquals(-1L, actualExtendedInviteDialog.getLocalCSeq());
    assertEquals(-1L, actualExtendedInviteDialog.getRemoteCSeq());
    assertNull(actualExtendedInviteDialog.getRemoteContact());
    assertNull(actualExtendedInviteDialog.getInviteMessage());
    assertNull(actualExtendedInviteDialog.getLocalContact());
    assertNull(actualExtendedInviteDialog.getRemoteName());
    assertNull(actualExtendedInviteDialog.getRemoteTag());
    assertNull(actualExtendedInviteDialog.getLocalName());
    assertNull(actualExtendedInviteDialog.getRoute());
    assertEquals(0L, actualExtendedInviteDialog.getSessionExpiration());
  }

  @Test
  void testConstructor3() {
    // Arrange and Act
    ExtendedInviteDialog actualExtendedInviteDialog = new ExtendedInviteDialog(new SipProvider("File"),
        mock(ExtendedInviteDialogListener.class));

    // Assert
    assertNull(actualExtendedInviteDialog.getCallID());
    assertNull(actualExtendedInviteDialog.username);
    assertTrue(actualExtendedInviteDialog.transactions.isEmpty());
    assertEquals(2, actualExtendedInviteDialog.supported_option_tags.length);
    assertEquals(0, actualExtendedInviteDialog.status);
    assertNull(actualExtendedInviteDialog.required_option_tags);
    assertNull(actualExtendedInviteDialog.reliable_responder);
    assertNull(actualExtendedInviteDialog.realm);
    assertNull(actualExtendedInviteDialog.qop);
    assertNull(actualExtendedInviteDialog.proxy_required_option_tags);
    assertNull(actualExtendedInviteDialog.passwd);
    assertNull(actualExtendedInviteDialog.next_nonce);
    assertNull(actualExtendedInviteDialog.logger);
    assertTrue(actualExtendedInviteDialog.invite_offer);
    assertNull(actualExtendedInviteDialog.inv2xx_resp);
    assertNull(actualExtendedInviteDialog.info_packages);
    assertSame(actualExtendedInviteDialog.listener, actualExtendedInviteDialog.ext_listener);
    assertEquals(0, actualExtendedInviteDialog.attempts);
    assertEquals(10, actualExtendedInviteDialog.allowed_methods.length);
    assertNull(actualExtendedInviteDialog.ack_req);
    assertFalse(actualExtendedInviteDialog.isSecure());
    SipProvider expectedSipProvider = actualExtendedInviteDialog.sip_provider;
    assertSame(expectedSipProvider, actualExtendedInviteDialog.getSipProvider());
    assertEquals(0, actualExtendedInviteDialog.getSessionInterval());
    assertNull(actualExtendedInviteDialog.getDialogID());
    assertEquals(-1L, actualExtendedInviteDialog.getLastRSeq());
    assertNull(actualExtendedInviteDialog.getLocalTag());
    assertNull(actualExtendedInviteDialog.getRefresher());
    assertEquals(-1L, actualExtendedInviteDialog.getLocalCSeq());
    assertEquals(-1L, actualExtendedInviteDialog.getRemoteCSeq());
    assertNull(actualExtendedInviteDialog.getRemoteContact());
    assertNull(actualExtendedInviteDialog.getInviteMessage());
    assertNull(actualExtendedInviteDialog.getLocalContact());
    assertNull(actualExtendedInviteDialog.getRemoteName());
    assertNull(actualExtendedInviteDialog.getRemoteTag());
    assertNull(actualExtendedInviteDialog.getLocalName());
    assertNull(actualExtendedInviteDialog.getRoute());
    assertEquals(0L, actualExtendedInviteDialog.getSessionExpiration());
  }

  @Test
  void testConstructor4() {
    // Arrange and Act
    ExtendedInviteDialog actualExtendedInviteDialog = new ExtendedInviteDialog(new SipProvider("42 Main St", 8080),
        mock(ExtendedInviteDialogListener.class));

    // Assert
    assertNull(actualExtendedInviteDialog.getCallID());
    assertNull(actualExtendedInviteDialog.username);
    assertTrue(actualExtendedInviteDialog.transactions.isEmpty());
    assertEquals(2, actualExtendedInviteDialog.supported_option_tags.length);
    assertEquals(0, actualExtendedInviteDialog.status);
    assertNull(actualExtendedInviteDialog.required_option_tags);
    assertNull(actualExtendedInviteDialog.reliable_responder);
    assertNull(actualExtendedInviteDialog.realm);
    assertNull(actualExtendedInviteDialog.qop);
    assertNull(actualExtendedInviteDialog.proxy_required_option_tags);
    assertNull(actualExtendedInviteDialog.passwd);
    assertNull(actualExtendedInviteDialog.next_nonce);
    assertNull(actualExtendedInviteDialog.logger);
    assertTrue(actualExtendedInviteDialog.invite_offer);
    assertNull(actualExtendedInviteDialog.inv2xx_resp);
    assertNull(actualExtendedInviteDialog.info_packages);
    assertSame(actualExtendedInviteDialog.listener, actualExtendedInviteDialog.ext_listener);
    assertEquals(0, actualExtendedInviteDialog.attempts);
    assertEquals(10, actualExtendedInviteDialog.allowed_methods.length);
    assertNull(actualExtendedInviteDialog.ack_req);
    assertFalse(actualExtendedInviteDialog.isSecure());
    SipProvider expectedSipProvider = actualExtendedInviteDialog.sip_provider;
    assertSame(expectedSipProvider, actualExtendedInviteDialog.getSipProvider());
    assertEquals(0, actualExtendedInviteDialog.getSessionInterval());
    assertNull(actualExtendedInviteDialog.getDialogID());
    assertEquals(-1L, actualExtendedInviteDialog.getLastRSeq());
    assertNull(actualExtendedInviteDialog.getLocalTag());
    assertNull(actualExtendedInviteDialog.getRefresher());
    assertEquals(-1L, actualExtendedInviteDialog.getLocalCSeq());
    assertEquals(-1L, actualExtendedInviteDialog.getRemoteCSeq());
    assertNull(actualExtendedInviteDialog.getRemoteContact());
    assertNull(actualExtendedInviteDialog.getInviteMessage());
    assertNull(actualExtendedInviteDialog.getLocalContact());
    assertNull(actualExtendedInviteDialog.getRemoteName());
    assertNull(actualExtendedInviteDialog.getRemoteTag());
    assertNull(actualExtendedInviteDialog.getLocalName());
    assertNull(actualExtendedInviteDialog.getRoute());
    assertEquals(0L, actualExtendedInviteDialog.getSessionExpiration());
  }

  @Test
  void testConstructor5() throws UnsupportedEncodingException {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    StatusLine status_line = new StatusLine(1, "foo");

    Vector headers = new Vector(1);

    // Act
    ExtendedInviteDialog actualExtendedInviteDialog = new ExtendedInviteDialog(sip_provider,
        new SipMessage(status_line, headers, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8")), "janedoe", "Realm",
        "iloveyou", mock(ExtendedInviteDialogListener.class));

    // Assert
    assertNull(actualExtendedInviteDialog.getCallID());
    assertEquals("janedoe", actualExtendedInviteDialog.username);
    assertTrue(actualExtendedInviteDialog.transactions.isEmpty());
    assertEquals(2, actualExtendedInviteDialog.supported_option_tags.length);
    assertEquals(0, actualExtendedInviteDialog.status);
    assertNull(actualExtendedInviteDialog.required_option_tags);
    assertNull(actualExtendedInviteDialog.reliable_responder);
    assertEquals("Realm", actualExtendedInviteDialog.realm);
    assertNull(actualExtendedInviteDialog.qop);
    assertNull(actualExtendedInviteDialog.proxy_required_option_tags);
    assertEquals("iloveyou", actualExtendedInviteDialog.passwd);
    assertNull(actualExtendedInviteDialog.next_nonce);
    assertNull(actualExtendedInviteDialog.logger);
    assertTrue(actualExtendedInviteDialog.invite_offer);
    assertNull(actualExtendedInviteDialog.inv2xx_resp);
    assertNull(actualExtendedInviteDialog.info_packages);
    assertSame(actualExtendedInviteDialog.listener, actualExtendedInviteDialog.ext_listener);
    assertEquals(0, actualExtendedInviteDialog.attempts);
    assertEquals(10, actualExtendedInviteDialog.allowed_methods.length);
    assertNull(actualExtendedInviteDialog.ack_req);
    assertFalse(actualExtendedInviteDialog.isSecure());
    SipProvider expectedSipProvider = actualExtendedInviteDialog.sip_provider;
    assertSame(expectedSipProvider, actualExtendedInviteDialog.getSipProvider());
    assertEquals(0, actualExtendedInviteDialog.getSessionInterval());
    assertNull(actualExtendedInviteDialog.getDialogID());
    assertEquals(-1L, actualExtendedInviteDialog.getLastRSeq());
    assertNull(actualExtendedInviteDialog.getLocalTag());
    assertNull(actualExtendedInviteDialog.getRefresher());
    assertEquals(-1L, actualExtendedInviteDialog.getLocalCSeq());
    assertEquals(-1L, actualExtendedInviteDialog.getRemoteCSeq());
    assertNull(actualExtendedInviteDialog.getRemoteContact());
    assertNull(actualExtendedInviteDialog.getInviteMessage());
    assertNull(actualExtendedInviteDialog.getLocalContact());
    assertNull(actualExtendedInviteDialog.getRemoteName());
    assertNull(actualExtendedInviteDialog.getRemoteTag());
    assertNull(actualExtendedInviteDialog.getLocalName());
    assertNull(actualExtendedInviteDialog.getRoute());
    assertEquals(0L, actualExtendedInviteDialog.getSessionExpiration());
  }

  @Test
  void testConstructor6() throws UnsupportedEncodingException {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    StatusLine status_line = new StatusLine(1, "foo");

    Vector headers = new Vector(1);

    // Act
    ExtendedInviteDialog actualExtendedInviteDialog = new ExtendedInviteDialog(sip_provider,
        new SipMessage(status_line, headers, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8")),
        mock(ExtendedInviteDialogListener.class));

    // Assert
    assertNull(actualExtendedInviteDialog.getCallID());
    assertNull(actualExtendedInviteDialog.username);
    assertTrue(actualExtendedInviteDialog.transactions.isEmpty());
    assertEquals(2, actualExtendedInviteDialog.supported_option_tags.length);
    assertEquals(0, actualExtendedInviteDialog.status);
    assertNull(actualExtendedInviteDialog.required_option_tags);
    assertNull(actualExtendedInviteDialog.reliable_responder);
    assertNull(actualExtendedInviteDialog.realm);
    assertNull(actualExtendedInviteDialog.qop);
    assertNull(actualExtendedInviteDialog.proxy_required_option_tags);
    assertNull(actualExtendedInviteDialog.passwd);
    assertNull(actualExtendedInviteDialog.next_nonce);
    assertNull(actualExtendedInviteDialog.logger);
    assertTrue(actualExtendedInviteDialog.invite_offer);
    assertNull(actualExtendedInviteDialog.inv2xx_resp);
    assertNull(actualExtendedInviteDialog.info_packages);
    assertSame(actualExtendedInviteDialog.listener, actualExtendedInviteDialog.ext_listener);
    assertEquals(0, actualExtendedInviteDialog.attempts);
    assertEquals(10, actualExtendedInviteDialog.allowed_methods.length);
    assertNull(actualExtendedInviteDialog.ack_req);
    assertFalse(actualExtendedInviteDialog.isSecure());
    SipProvider expectedSipProvider = actualExtendedInviteDialog.sip_provider;
    assertSame(expectedSipProvider, actualExtendedInviteDialog.getSipProvider());
    assertEquals(0, actualExtendedInviteDialog.getSessionInterval());
    assertNull(actualExtendedInviteDialog.getDialogID());
    assertEquals(-1L, actualExtendedInviteDialog.getLastRSeq());
    assertNull(actualExtendedInviteDialog.getLocalTag());
    assertNull(actualExtendedInviteDialog.getRefresher());
    assertEquals(-1L, actualExtendedInviteDialog.getLocalCSeq());
    assertEquals(-1L, actualExtendedInviteDialog.getRemoteCSeq());
    assertNull(actualExtendedInviteDialog.getRemoteContact());
    assertNull(actualExtendedInviteDialog.getInviteMessage());
    assertNull(actualExtendedInviteDialog.getLocalContact());
    assertNull(actualExtendedInviteDialog.getRemoteName());
    assertNull(actualExtendedInviteDialog.getRemoteTag());
    assertNull(actualExtendedInviteDialog.getLocalName());
    assertNull(actualExtendedInviteDialog.getRoute());
    assertEquals(0L, actualExtendedInviteDialog.getSessionExpiration());
  }

  @Test
  void testRespond() {
    // Arrange
    ExtendedInviteDialog extendedInviteDialog = new ExtendedInviteDialog(new SipProvider("File"), null);
    SipMessage sipMessage = mock(SipMessage.class);
    when(sipMessage.getViaHeader()).thenReturn(new ViaHeader("42"));
    when(sipMessage.getCallIdHeader()).thenReturn(new CallIdHeader("42"));
    when(sipMessage.getCSeqHeader()).thenReturn(new CSeqHeader("42"));

    // Act
    extendedInviteDialog.respond(sipMessage);

    // Assert
    verify(sipMessage, atLeast(1)).getCSeqHeader();
    verify(sipMessage).getCallIdHeader();
    verify(sipMessage).getViaHeader();
  }

  @Test
  void testRespond2() {
    // Arrange
    ExtendedInviteDialog extendedInviteDialog = new ExtendedInviteDialog(new SipProvider("File"), null);
    SipMessage sipMessage = mock(SipMessage.class);
    when(sipMessage.getViaHeader()).thenReturn(new ViaHeader("BYE"));
    when(sipMessage.getCallIdHeader()).thenReturn(new CallIdHeader("42"));
    when(sipMessage.getCSeqHeader()).thenReturn(new CSeqHeader("42"));

    // Act
    extendedInviteDialog.respond(sipMessage);

    // Assert
    verify(sipMessage, atLeast(1)).getCSeqHeader();
    verify(sipMessage).getCallIdHeader();
    verify(sipMessage).getViaHeader();
  }

  @Test
  void testRespond3() {
    // Arrange
    ExtendedInviteDialog extendedInviteDialog = new ExtendedInviteDialog(new SipProvider("File"), null);
    SipMessage sipMessage = mock(SipMessage.class);
    when(sipMessage.getViaHeader()).thenReturn(null);
    when(sipMessage.getCallIdHeader()).thenReturn(new CallIdHeader("42"));
    when(sipMessage.getCSeqHeader()).thenReturn(new CSeqHeader("42"));

    // Act
    extendedInviteDialog.respond(sipMessage);

    // Assert
    verify(sipMessage, atLeast(1)).getCSeqHeader();
    verify(sipMessage).getCallIdHeader();
    verify(sipMessage).getViaHeader();
  }

  @Test
  void testRespond4() {
    // Arrange
    ExtendedInviteDialog extendedInviteDialog = new ExtendedInviteDialog(new SipProvider("File"), null);
    SipMessage sipMessage = mock(SipMessage.class);
    when(sipMessage.getViaHeader()).thenReturn(new ViaHeader("localhost", 8080));
    when(sipMessage.getCallIdHeader()).thenReturn(new CallIdHeader("42"));
    when(sipMessage.getCSeqHeader()).thenReturn(new CSeqHeader("42"));

    // Act
    extendedInviteDialog.respond(sipMessage);

    // Assert
    verify(sipMessage, atLeast(1)).getCSeqHeader();
    verify(sipMessage).getCallIdHeader();
    verify(sipMessage).getViaHeader();
  }

  @Test
  void testRespond5() {
    // Arrange
    ExtendedInviteDialog extendedInviteDialog = new ExtendedInviteDialog(new SipProvider("File"), null);
    ViaHeader viaHeader = mock(ViaHeader.class);
    when(viaHeader.getSentBy()).thenReturn("Sent By");
    when(viaHeader.getBranch()).thenReturn("janedoe/featurebranch");
    when(viaHeader.hasBranch()).thenReturn(true);
    SipMessage sipMessage = mock(SipMessage.class);
    when(sipMessage.getViaHeader()).thenReturn(viaHeader);
    when(sipMessage.getCallIdHeader()).thenReturn(new CallIdHeader("42"));
    when(sipMessage.getCSeqHeader()).thenReturn(new CSeqHeader("42"));

    // Act
    extendedInviteDialog.respond(sipMessage);

    // Assert
    verify(sipMessage, atLeast(1)).getCSeqHeader();
    verify(sipMessage).getCallIdHeader();
    verify(sipMessage).getViaHeader();
    verify(viaHeader).getBranch();
    verify(viaHeader).getSentBy();
    verify(viaHeader).hasBranch();
  }

  @Test
  void testRespond6() {
    // Arrange
    ExtendedInviteDialog extendedInviteDialog = new ExtendedInviteDialog(new SipProvider("File"), null);
    CallIdHeader callIdHeader = mock(CallIdHeader.class);
    when(callIdHeader.getCallId()).thenReturn("42");
    ViaHeader viaHeader = mock(ViaHeader.class);
    when(viaHeader.getSentBy()).thenReturn("Sent By");
    when(viaHeader.getBranch()).thenReturn("janedoe/featurebranch");
    when(viaHeader.hasBranch()).thenReturn(true);
    SipMessage sipMessage = mock(SipMessage.class);
    when(sipMessage.getViaHeader()).thenReturn(viaHeader);
    when(sipMessage.getCallIdHeader()).thenReturn(callIdHeader);
    when(sipMessage.getCSeqHeader()).thenReturn(new CSeqHeader("42"));

    // Act
    extendedInviteDialog.respond(sipMessage);

    // Assert
    verify(sipMessage, atLeast(1)).getCSeqHeader();
    verify(sipMessage).getCallIdHeader();
    verify(sipMessage).getViaHeader();
    verify(viaHeader).getBranch();
    verify(viaHeader).getSentBy();
    verify(viaHeader).hasBranch();
    verify(callIdHeader).getCallId();
  }

  @Test
  void testRespond7() {
    // Arrange
    ExtendedInviteDialog extendedInviteDialog = new ExtendedInviteDialog(new SipProvider(SipProvider.ALL_INTERFACES),
        null);
    CSeqHeader cSeqHeader = mock(CSeqHeader.class);
    when(cSeqHeader.getSequenceNumber()).thenReturn(1L);
    when(cSeqHeader.getMethod()).thenReturn("Method");
    CallIdHeader callIdHeader = mock(CallIdHeader.class);
    when(callIdHeader.getCallId()).thenReturn("42");
    ViaHeader viaHeader = mock(ViaHeader.class);
    when(viaHeader.getSentBy()).thenReturn("Sent By");
    when(viaHeader.getBranch()).thenReturn("janedoe/featurebranch");
    when(viaHeader.hasBranch()).thenReturn(true);
    SipMessage sipMessage = mock(SipMessage.class);
    when(sipMessage.getViaHeader()).thenReturn(viaHeader);
    when(sipMessage.getCallIdHeader()).thenReturn(callIdHeader);
    when(sipMessage.getCSeqHeader()).thenReturn(cSeqHeader);

    // Act
    extendedInviteDialog.respond(sipMessage);

    // Assert
    verify(sipMessage, atLeast(1)).getCSeqHeader();
    verify(sipMessage).getCallIdHeader();
    verify(sipMessage).getViaHeader();
    verify(viaHeader).getBranch();
    verify(viaHeader).getSentBy();
    verify(viaHeader).hasBranch();
    verify(callIdHeader).getCallId();
    verify(cSeqHeader, atLeast(1)).getMethod();
    verify(cSeqHeader).getSequenceNumber();
  }

  @Test
  void testAcceptRefer() {
    // Arrange
    ExtendedInviteDialog extendedInviteDialog = new ExtendedInviteDialog(new SipProvider("File"), null);
    SipMessage sipMessage = mock(SipMessage.class);
    when(sipMessage.getCSeqHeader()).thenReturn(new CSeqHeader("42"));
    when(sipMessage.getCallIdHeader()).thenReturn(new CallIdHeader("42"));
    when(sipMessage.getFromHeader()).thenReturn(new FromHeader(new Header("Hname", "42")));
    when(sipMessage.getToHeader()).thenReturn(new ToHeader(new Header("Hname", "42")));
    when(sipMessage.getRecordRoutes()).thenReturn(new MultipleHeader("Hname"));
    when(sipMessage.hasRecordRouteHeader()).thenReturn(true);
    when(sipMessage.getVias()).thenReturn(new MultipleHeader("Hname"));

    // Act
    extendedInviteDialog.acceptRefer(sipMessage);

    // Assert
    verify(sipMessage).getCSeqHeader();
    verify(sipMessage).getCallIdHeader();
    verify(sipMessage).getFromHeader();
    verify(sipMessage).getRecordRoutes();
    verify(sipMessage).getToHeader();
    verify(sipMessage).getVias();
    verify(sipMessage).hasRecordRouteHeader();
  }

  @Test
  void testAcceptRefer2() {
    // Arrange
    ExtendedInviteDialog extendedInviteDialog = new ExtendedInviteDialog(new SipProvider("File"), null);
    CSeqHeader cSeqHeader = mock(CSeqHeader.class);
    when(cSeqHeader.getName()).thenReturn("Name");
    SipMessage sipMessage = mock(SipMessage.class);
    when(sipMessage.getCSeqHeader()).thenReturn(cSeqHeader);
    when(sipMessage.getCallIdHeader()).thenReturn(new CallIdHeader("42"));
    when(sipMessage.getFromHeader()).thenReturn(new FromHeader(new CSeqHeader("42")));
    when(sipMessage.getToHeader()).thenReturn(new ToHeader(new Header("Hname", "42")));
    when(sipMessage.getRecordRoutes()).thenReturn(new MultipleHeader("Hname"));
    when(sipMessage.hasRecordRouteHeader()).thenReturn(true);
    when(sipMessage.getVias()).thenReturn(new MultipleHeader("Hname"));

    // Act
    extendedInviteDialog.acceptRefer(sipMessage);

    // Assert
    verify(sipMessage).getCSeqHeader();
    verify(sipMessage).getCallIdHeader();
    verify(sipMessage).getFromHeader();
    verify(sipMessage).getRecordRoutes();
    verify(sipMessage).getToHeader();
    verify(sipMessage).getVias();
    verify(sipMessage).hasRecordRouteHeader();
    verify(cSeqHeader, atLeast(1)).getName();
  }

  @Test
  void testRefuseRefer() {
    // Arrange
    ExtendedInviteDialog extendedInviteDialog = new ExtendedInviteDialog(new SipProvider("File"), null);
    SipMessage sipMessage = mock(SipMessage.class);
    when(sipMessage.getCSeqHeader()).thenReturn(new CSeqHeader("42"));
    when(sipMessage.getCallIdHeader()).thenReturn(new CallIdHeader("42"));
    when(sipMessage.getFromHeader()).thenReturn(new FromHeader(new Header("Hname", "42")));
    when(sipMessage.getToHeader()).thenReturn(new ToHeader(new Header("Hname", "42")));
    when(sipMessage.getVias()).thenReturn(new MultipleHeader("Hname"));

    // Act
    extendedInviteDialog.refuseRefer(sipMessage);

    // Assert
    verify(sipMessage).getCSeqHeader();
    verify(sipMessage).getCallIdHeader();
    verify(sipMessage).getFromHeader();
    verify(sipMessage).getToHeader();
    verify(sipMessage).getVias();
  }

  @Test
  void testRefuseRefer2() {
    // Arrange
    ExtendedInviteDialog extendedInviteDialog = new ExtendedInviteDialog(new SipProvider("File"), null);
    CSeqHeader cSeqHeader = mock(CSeqHeader.class);
    when(cSeqHeader.getName()).thenReturn("Name");
    SipMessage sipMessage = mock(SipMessage.class);
    when(sipMessage.getCSeqHeader()).thenReturn(cSeqHeader);
    when(sipMessage.getCallIdHeader()).thenReturn(new CallIdHeader("42"));
    when(sipMessage.getFromHeader()).thenReturn(new FromHeader(new CSeqHeader("42")));
    when(sipMessage.getToHeader()).thenReturn(new ToHeader(new Header("Hname", "42")));
    when(sipMessage.getVias()).thenReturn(new MultipleHeader("Hname"));

    // Act
    extendedInviteDialog.refuseRefer(sipMessage);

    // Assert
    verify(sipMessage).getCSeqHeader();
    verify(sipMessage).getCallIdHeader();
    verify(sipMessage).getFromHeader();
    verify(sipMessage).getToHeader();
    verify(sipMessage).getVias();
    verify(cSeqHeader, atLeast(1)).getName();
  }

  @Test
  void testLog() {
    // Arrange
    ExtendedInviteDialog extendedInviteDialog = new ExtendedInviteDialog(new SipProvider("File"), null);
    LogLevel logLevel = new LogLevel("Name", 42);

    // Act
    extendedInviteDialog.log(logLevel, new Exception("An error occurred"));

    // Assert that nothing has changed
    assertEquals("Name", logLevel.getName());
    assertEquals(42, logLevel.getValue());
    assertTrue(extendedInviteDialog.transactions.isEmpty());
    assertEquals(2, extendedInviteDialog.supported_option_tags.length);
    assertEquals(0, extendedInviteDialog.status);
    assertTrue(extendedInviteDialog.invite_offer);
    assertEquals(0, extendedInviteDialog.attempts);
    assertEquals(10, extendedInviteDialog.allowed_methods.length);
    assertFalse(extendedInviteDialog.isSecure());
    SipProvider expectedSipProvider = extendedInviteDialog.sip_provider;
    assertSame(expectedSipProvider, extendedInviteDialog.getSipProvider());
    assertEquals(0, extendedInviteDialog.getSessionInterval());
    assertEquals(-1L, extendedInviteDialog.getLastRSeq());
    assertEquals(-1L, extendedInviteDialog.getLocalCSeq());
    assertEquals(-1L, extendedInviteDialog.getRemoteCSeq());
    assertEquals(0L, extendedInviteDialog.getSessionExpiration());
  }

  @Test
  void testLog2() {
    // Arrange
    ExtendedInviteDialog extendedInviteDialog = new ExtendedInviteDialog(new SipProvider("File"), null);
    LogLevel logLevel = new LogLevel("Name", 42);

    // Act
    extendedInviteDialog.log(logLevel, "Str");

    // Assert that nothing has changed
    assertEquals("Name", logLevel.getName());
    assertEquals(42, logLevel.getValue());
    assertTrue(extendedInviteDialog.transactions.isEmpty());
    assertEquals(2, extendedInviteDialog.supported_option_tags.length);
    assertEquals(0, extendedInviteDialog.status);
    assertTrue(extendedInviteDialog.invite_offer);
    assertEquals(0, extendedInviteDialog.attempts);
    assertEquals(10, extendedInviteDialog.allowed_methods.length);
    assertFalse(extendedInviteDialog.isSecure());
    SipProvider expectedSipProvider = extendedInviteDialog.sip_provider;
    assertSame(expectedSipProvider, extendedInviteDialog.getSipProvider());
    assertEquals(0, extendedInviteDialog.getSessionInterval());
    assertEquals(-1L, extendedInviteDialog.getLastRSeq());
    assertEquals(-1L, extendedInviteDialog.getLocalCSeq());
    assertEquals(-1L, extendedInviteDialog.getRemoteCSeq());
    assertEquals(0L, extendedInviteDialog.getSessionExpiration());
  }

  @Test
  void testGetDialogID() {
    // Arrange, Act and Assert
    assertNull((new ExtendedInviteDialog(new SipProvider("File"), null)).getDialogID());
  }

  @Test
  void testGetSipProvider() {
    // Arrange
    ExtendedInviteDialog extendedInviteDialog = new ExtendedInviteDialog(new SipProvider("File"), null);

    // Act and Assert
    assertSame(extendedInviteDialog.sip_provider, extendedInviteDialog.getSipProvider());
  }

  @Test
  void testStatusIs() {
    // Arrange, Act and Assert
    assertFalse((new ExtendedInviteDialog(new SipProvider("File"), null)).statusIs(1));
    assertTrue((new ExtendedInviteDialog(new SipProvider("File"), null)).statusIs(0));
  }

  @Test
  void testUpdateDialogInfo() {
    // Arrange
    ExtendedInviteDialog extendedInviteDialog = new ExtendedInviteDialog(new SipProvider("File"), null);
    SipMessage sipMessage = mock(SipMessage.class);
    when(sipMessage.getContactHeader()).thenReturn(new ContactHeader());
    when(sipMessage.hasContactHeader()).thenReturn(true);
    when(sipMessage.getCSeqHeader()).thenReturn(new CSeqHeader("42"));
    when(sipMessage.getFromHeader()).thenReturn(new FromHeader(new Header("Hname", "42")));
    when(sipMessage.getToHeader()).thenReturn(new ToHeader(new Header("Hname", "42")));
    when(sipMessage.getCallIdHeader()).thenReturn(new CallIdHeader("42"));

    // Act
    extendedInviteDialog.updateDialogInfo(true, sipMessage);

    // Assert
    verify(sipMessage).getCSeqHeader();
    verify(sipMessage).getCallIdHeader();
    verify(sipMessage).getContactHeader();
    verify(sipMessage).getFromHeader();
    verify(sipMessage).getToHeader();
    verify(sipMessage).hasContactHeader();
    assertEquals("42", extendedInviteDialog.getCallID());
    DialogId expectedDialogID = extendedInviteDialog.dialog_id;
    DialogId dialogID = extendedInviteDialog.getDialogID();
    assertSame(expectedDialogID, dialogID);
    assertNull(extendedInviteDialog.getLocalTag());
    assertEquals(42L, extendedInviteDialog.getLocalCSeq());
    NameAddress expectedRemoteContact = extendedInviteDialog.remote_contact;
    NameAddress remoteContact = extendedInviteDialog.getRemoteContact();
    assertSame(expectedRemoteContact, remoteContact);
    NameAddress expectedRemoteName = extendedInviteDialog.local_name;
    NameAddress remoteName = extendedInviteDialog.getRemoteName();
    assertEquals(expectedRemoteName, remoteName);
    assertNull(extendedInviteDialog.getRemoteTag());
    assertEquals(remoteName, extendedInviteDialog.getLocalName());
    assertNull(remoteContact.getDisplayName());
    assertEquals("<sip:*>", remoteContact.toString());
    assertEquals("42-null-null", dialogID.toString());
  }

  @Test
  void testUpdateDialogInfo2() {
    // Arrange
    ExtendedInviteDialog extendedInviteDialog = new ExtendedInviteDialog(new SipProvider("File"), null);
    SipMessage sipMessage = mock(SipMessage.class);
    when(sipMessage.getContactHeader()).thenReturn(new ContactHeader());
    when(sipMessage.hasContactHeader()).thenReturn(true);
    when(sipMessage.getCSeqHeader()).thenReturn(new CSeqHeader("42"));
    when(sipMessage.getFromHeader()).thenReturn(new FromHeader(new Header("Hname", "42")));
    when(sipMessage.getToHeader()).thenReturn(new ToHeader(new Header("Hname", "42")));
    when(sipMessage.getCallIdHeader()).thenReturn(new CallIdHeader("42"));

    // Act
    extendedInviteDialog.updateDialogInfo(false, sipMessage);

    // Assert
    verify(sipMessage).getCSeqHeader();
    verify(sipMessage).getCallIdHeader();
    verify(sipMessage).getContactHeader();
    verify(sipMessage).getFromHeader();
    verify(sipMessage).getToHeader();
    verify(sipMessage).hasContactHeader();
    assertEquals("42", extendedInviteDialog.getCallID());
    DialogId expectedDialogID = extendedInviteDialog.dialog_id;
    DialogId dialogID = extendedInviteDialog.getDialogID();
    assertSame(expectedDialogID, dialogID);
    assertNull(extendedInviteDialog.getLocalTag());
    assertEquals(0L, extendedInviteDialog.getLocalCSeq());
    assertEquals(42L, extendedInviteDialog.getRemoteCSeq());
    NameAddress expectedRemoteContact = extendedInviteDialog.remote_contact;
    NameAddress remoteContact = extendedInviteDialog.getRemoteContact();
    assertSame(expectedRemoteContact, remoteContact);
    NameAddress expectedRemoteName = extendedInviteDialog.local_name;
    NameAddress remoteName = extendedInviteDialog.getRemoteName();
    assertEquals(expectedRemoteName, remoteName);
    assertNull(extendedInviteDialog.getRemoteTag());
    assertEquals(remoteName, extendedInviteDialog.getLocalName());
    assertNull(remoteContact.getDisplayName());
    assertEquals("<sip:*>", remoteContact.toString());
    assertEquals("42-null-null", dialogID.toString());
  }

  @Test
  void testUpdateDialogInfo3() {
    // Arrange
    ExtendedInviteDialog extendedInviteDialog = new ExtendedInviteDialog(new SipProvider("File"), null);
    SipMessage sipMessage = mock(SipMessage.class);
    NameAddress nameAddress = new NameAddress("Str");
    when(sipMessage.getContactHeader()).thenReturn(new ContactHeader(nameAddress));
    when(sipMessage.hasContactHeader()).thenReturn(true);
    when(sipMessage.getCSeqHeader()).thenReturn(new CSeqHeader("42"));
    when(sipMessage.getFromHeader()).thenReturn(new FromHeader(new Header("Hname", "42")));
    when(sipMessage.getToHeader()).thenReturn(new ToHeader(new Header("Hname", "42")));
    when(sipMessage.getCallIdHeader()).thenReturn(new CallIdHeader("42"));

    // Act
    extendedInviteDialog.updateDialogInfo(true, sipMessage);

    // Assert
    verify(sipMessage).getCSeqHeader();
    verify(sipMessage).getCallIdHeader();
    verify(sipMessage).getContactHeader();
    verify(sipMessage).getFromHeader();
    verify(sipMessage).getToHeader();
    verify(sipMessage).hasContactHeader();
    assertEquals("42", extendedInviteDialog.getCallID());
    DialogId expectedDialogID = extendedInviteDialog.dialog_id;
    DialogId dialogID = extendedInviteDialog.getDialogID();
    assertSame(expectedDialogID, dialogID);
    assertNull(extendedInviteDialog.getLocalTag());
    assertEquals(42L, extendedInviteDialog.getLocalCSeq());
    NameAddress remoteContact = extendedInviteDialog.getRemoteContact();
    assertEquals(nameAddress, remoteContact);
    NameAddress expectedRemoteName = extendedInviteDialog.local_name;
    NameAddress remoteName = extendedInviteDialog.getRemoteName();
    assertEquals(expectedRemoteName, remoteName);
    assertNull(extendedInviteDialog.getRemoteTag());
    assertEquals(remoteName, extendedInviteDialog.getLocalName());
    assertNull(remoteContact.getDisplayName());
    assertEquals("<sip:Str>", remoteContact.toString());
    assertEquals("42-null-null", dialogID.toString());
  }

  @Test
  void testVerifyStatus() {
    // Arrange, Act and Assert
    assertTrue((new ExtendedInviteDialog(new SipProvider("File"), null)).verifyStatus(true));
    assertFalse((new ExtendedInviteDialog(new SipProvider("File"), null)).verifyStatus(false));
  }

  @Test
  void testVerifyThat() {
    // Arrange, Act and Assert
    assertTrue((new ExtendedInviteDialog(new SipProvider("File"), null)).verifyThat(true, "Str"));
    assertFalse((new ExtendedInviteDialog(new SipProvider("File"), null)).verifyThat(false, "Str"));
    assertFalse((new ExtendedInviteDialog(new SipProvider("File"), null)).verifyThat(false, null));
    assertFalse((new ExtendedInviteDialog(new SipProvider("File"), null)).verifyThat(false, ""));
  }
}

