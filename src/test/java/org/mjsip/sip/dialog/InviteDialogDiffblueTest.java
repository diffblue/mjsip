package org.mjsip.sip.dialog;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
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
import org.mjsip.sip.header.RequestLine;
import org.mjsip.sip.header.StatusLine;
import org.mjsip.sip.header.ToHeader;
import org.mjsip.sip.message.SipMessage;
import org.mjsip.sip.provider.DialogId;
import org.mjsip.sip.provider.SipProvider;
import org.mjsip.sip.transaction.InviteTransactionServer;
import org.mjsip.sip.transaction.ReliableProvisionalResponder;
import org.zoolu.util.LogLevel;

class InviteDialogDiffblueTest {
  @Test
  void testGetStatus() {
    // Arrange, Act and Assert
    assertEquals("D_INIT", (new InviteDialog(new SipProvider("File"), null)).getStatus());
  }

  @Test
  void testChangeStatus() {
    // Arrange
    InviteDialog inviteDialog = new InviteDialog(new SipProvider("File"), null);

    // Act
    inviteDialog.changeStatus(1);

    // Assert
    assertEquals(1, inviteDialog.status);
  }

  @Test
  void testChangeStatus2() {
    // Arrange
    InviteDialog inviteDialog = new InviteDialog(new SipProvider("File"), null);

    // Act
    inviteDialog.changeStatus(0);

    // Assert
    assertEquals(0, inviteDialog.status);
  }

  @Test
  void testChangeStatus3() {
    // Arrange
    InviteDialog inviteDialog = new InviteDialog(new SipProvider("File"), null);

    // Act
    inviteDialog.changeStatus(2);

    // Assert
    assertEquals(2, inviteDialog.status);
  }

  @Test
  void testChangeStatus4() {
    // Arrange
    InviteDialog inviteDialog = new InviteDialog(new SipProvider("File"), null);

    // Act
    inviteDialog.changeStatus(3);

    // Assert
    assertEquals(3, inviteDialog.status);
  }

  @Test
  void testChangeStatus5() {
    // Arrange
    InviteDialog inviteDialog = new InviteDialog(new SipProvider("File"), null);

    // Act
    inviteDialog.changeStatus(4);

    // Assert
    assertEquals(4, inviteDialog.status);
  }

  @Test
  void testChangeStatus6() {
    // Arrange
    InviteDialog inviteDialog = new InviteDialog(new SipProvider("File"), null);

    // Act
    inviteDialog.changeStatus(5);

    // Assert
    assertEquals(5, inviteDialog.status);
  }

  @Test
  void testChangeStatus7() {
    // Arrange
    InviteDialog inviteDialog = new InviteDialog(new SipProvider("File"), null);

    // Act
    inviteDialog.changeStatus(6);

    // Assert
    assertEquals(6, inviteDialog.status);
  }

  @Test
  void testChangeStatus8() {
    // Arrange
    InviteDialog inviteDialog = new InviteDialog(new SipProvider("File"), null);

    // Act
    inviteDialog.changeStatus(-1);

    // Assert
    assertEquals(-1, inviteDialog.status);
  }

  @Test
  void testChangeStatus9() {
    // Arrange
    InviteDialog inviteDialog = new InviteDialog(new SipProvider("File"), null);

    // Act
    inviteDialog.changeStatus(7);

    // Assert
    assertEquals(7, inviteDialog.status);
  }

  @Test
  void testChangeStatus10() {
    // Arrange
    InviteDialog inviteDialog = new InviteDialog(new SipProvider("File"), null);

    // Act
    inviteDialog.changeStatus(8);

    // Assert
    assertEquals(8, inviteDialog.status);
  }

  @Test
  void testChangeStatus11() {
    // Arrange
    InviteDialog inviteDialog = new InviteDialog(new SipProvider("File"), null);

    // Act
    inviteDialog.changeStatus(9);

    // Assert
    assertEquals(9, inviteDialog.status);
  }

  @Test
  void testConstructor() {
    // Arrange and Act
    InviteDialog actualInviteDialog = new InviteDialog(new SipProvider("File"), null);
    actualInviteDialog.setAllowedMethods(new String[]{"Allowed methods"});
    actualInviteDialog.setInfoPackages(new String[]{"java.text"});
    actualInviteDialog.setProxyRequiredExtensions(new String[]{"Option tags"});
    actualInviteDialog.setRequiredExtensions(new String[]{"Option tags"});
    actualInviteDialog.setSupportedExtensions(new String[]{"Option tags"});

    // Assert
    assertNull(actualInviteDialog.getInviteMessage());
  }

  @Test
  void testConstructor2() {
    // Arrange and Act
    InviteDialog actualInviteDialog = new InviteDialog(new SipProvider("File"), mock(InviteDialogListener.class));

    // Assert
    assertNull(actualInviteDialog.getCallID());
    assertEquals(2, actualInviteDialog.supported_option_tags.length);
    assertEquals(0, actualInviteDialog.status);
    assertNull(actualInviteDialog.required_option_tags);
    assertNull(actualInviteDialog.reliable_responder);
    assertNull(actualInviteDialog.proxy_required_option_tags);
    assertNull(actualInviteDialog.logger);
    assertTrue(actualInviteDialog.invite_offer);
    assertNull(actualInviteDialog.inv2xx_resp);
    assertNull(actualInviteDialog.info_packages);
    assertEquals(10, actualInviteDialog.allowed_methods.length);
    assertNull(actualInviteDialog.ack_req);
    assertFalse(actualInviteDialog.isSecure());
    SipProvider expectedSipProvider = actualInviteDialog.sip_provider;
    assertSame(expectedSipProvider, actualInviteDialog.getSipProvider());
    assertEquals(0, actualInviteDialog.getSessionInterval());
    assertNull(actualInviteDialog.getDialogID());
    assertEquals(-1L, actualInviteDialog.getLastRSeq());
    assertNull(actualInviteDialog.getLocalTag());
    assertNull(actualInviteDialog.getRefresher());
    assertEquals(-1L, actualInviteDialog.getLocalCSeq());
    assertEquals(-1L, actualInviteDialog.getRemoteCSeq());
    assertNull(actualInviteDialog.getRemoteContact());
    assertNull(actualInviteDialog.getInviteMessage());
    assertNull(actualInviteDialog.getLocalContact());
    assertNull(actualInviteDialog.getRemoteName());
    assertNull(actualInviteDialog.getRemoteTag());
    assertNull(actualInviteDialog.getLocalName());
    assertNull(actualInviteDialog.getRoute());
    assertEquals(0L, actualInviteDialog.getSessionExpiration());
  }

  @Test
  void testConstructor3() {
    // Arrange and Act
    InviteDialog actualInviteDialog = new InviteDialog(new SipProvider("42 Main St", 8080),
        mock(InviteDialogListener.class));

    // Assert
    assertNull(actualInviteDialog.getCallID());
    assertEquals(2, actualInviteDialog.supported_option_tags.length);
    assertEquals(0, actualInviteDialog.status);
    assertNull(actualInviteDialog.required_option_tags);
    assertNull(actualInviteDialog.reliable_responder);
    assertNull(actualInviteDialog.proxy_required_option_tags);
    assertNull(actualInviteDialog.logger);
    assertTrue(actualInviteDialog.invite_offer);
    assertNull(actualInviteDialog.inv2xx_resp);
    assertNull(actualInviteDialog.info_packages);
    assertEquals(10, actualInviteDialog.allowed_methods.length);
    assertNull(actualInviteDialog.ack_req);
    assertFalse(actualInviteDialog.isSecure());
    SipProvider expectedSipProvider = actualInviteDialog.sip_provider;
    assertSame(expectedSipProvider, actualInviteDialog.getSipProvider());
    assertEquals(0, actualInviteDialog.getSessionInterval());
    assertNull(actualInviteDialog.getDialogID());
    assertEquals(-1L, actualInviteDialog.getLastRSeq());
    assertNull(actualInviteDialog.getLocalTag());
    assertNull(actualInviteDialog.getRefresher());
    assertEquals(-1L, actualInviteDialog.getLocalCSeq());
    assertEquals(-1L, actualInviteDialog.getRemoteCSeq());
    assertNull(actualInviteDialog.getRemoteContact());
    assertNull(actualInviteDialog.getInviteMessage());
    assertNull(actualInviteDialog.getLocalContact());
    assertNull(actualInviteDialog.getRemoteName());
    assertNull(actualInviteDialog.getRemoteTag());
    assertNull(actualInviteDialog.getLocalName());
    assertNull(actualInviteDialog.getRoute());
    assertEquals(0L, actualInviteDialog.getSessionExpiration());
  }

  @Test
  void testConstructor4() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");

    // Act
    InviteDialog actualInviteDialog = new InviteDialog(sip_provider, new SipMessage("Str"),
        mock(InviteDialogListener.class));

    // Assert
    assertNull(actualInviteDialog.getCallID());
    assertEquals(2, actualInviteDialog.supported_option_tags.length);
    assertEquals(0, actualInviteDialog.status);
    assertNull(actualInviteDialog.required_option_tags);
    assertNull(actualInviteDialog.reliable_responder);
    assertNull(actualInviteDialog.proxy_required_option_tags);
    assertNull(actualInviteDialog.logger);
    assertTrue(actualInviteDialog.invite_offer);
    assertNull(actualInviteDialog.inv2xx_resp);
    assertNull(actualInviteDialog.info_packages);
    assertEquals(10, actualInviteDialog.allowed_methods.length);
    assertNull(actualInviteDialog.ack_req);
    assertFalse(actualInviteDialog.isSecure());
    SipProvider expectedSipProvider = actualInviteDialog.sip_provider;
    assertSame(expectedSipProvider, actualInviteDialog.getSipProvider());
    assertEquals(0, actualInviteDialog.getSessionInterval());
    assertNull(actualInviteDialog.getDialogID());
    assertEquals(-1L, actualInviteDialog.getLastRSeq());
    assertNull(actualInviteDialog.getLocalTag());
    assertNull(actualInviteDialog.getRefresher());
    assertEquals(-1L, actualInviteDialog.getLocalCSeq());
    assertEquals(-1L, actualInviteDialog.getRemoteCSeq());
    assertNull(actualInviteDialog.getRemoteContact());
    assertNull(actualInviteDialog.getInviteMessage());
    assertNull(actualInviteDialog.getLocalContact());
    assertNull(actualInviteDialog.getRemoteName());
    assertNull(actualInviteDialog.getRemoteTag());
    assertNull(actualInviteDialog.getLocalName());
    assertNull(actualInviteDialog.getRoute());
    assertEquals(0L, actualInviteDialog.getSessionExpiration());
  }

  @Test
  void testIsEarly() {
    // Arrange, Act and Assert
    assertTrue((new InviteDialog(new SipProvider("File"), null)).isEarly());
  }

  @Test
  void testIsConfirmed() {
    // Arrange, Act and Assert
    assertFalse((new InviteDialog(new SipProvider("File"), null)).isConfirmed());
  }

  @Test
  void testIsTerminated() {
    // Arrange, Act and Assert
    assertFalse((new InviteDialog(new SipProvider("File"), null)).isTerminated());
  }

  @Test
  void testIsSessionActive() {
    // Arrange, Act and Assert
    assertFalse((new InviteDialog(new SipProvider("File"), null)).isSessionActive());
  }

  @Test
  void testIsExtensionSupported() {
    // Arrange, Act and Assert
    assertFalse((new InviteDialog(new SipProvider("File"), null)).isExtensionSupported("Option tag"));
    assertTrue((new InviteDialog(new SipProvider("File"), null)).isExtensionSupported("100rel"));
  }

  @Test
  void testIsExtensionSupported2() {
    // Arrange
    InviteDialog inviteDialog = new InviteDialog(new SipProvider("File"), null);
    inviteDialog.setSupportedExtensions(null);

    // Act and Assert
    assertFalse(inviteDialog.isExtensionSupported("Option tag"));
  }

  @Test
  void testIsExtensionRequired() {
    // Arrange, Act and Assert
    assertFalse((new InviteDialog(new SipProvider("File"), null)).isExtensionRequired("Option tag"));
  }

  @Test
  void testListen() {
    // Arrange
    InviteDialog inviteDialog = new InviteDialog(new SipProvider("File"), null);

    // Act
    inviteDialog.listen();

    // Assert
    assertEquals(1, inviteDialog.status);
    SipProvider expectedSipProvider = inviteDialog.getSipProvider();
    InviteTransactionServer inviteTransactionServer = inviteDialog.invite_ts;
    assertSame(expectedSipProvider, inviteTransactionServer.getSipProvider());
    assertTrue(inviteTransactionServer.getTransactionId() instanceof org.mjsip.sip.provider.TransactionServerId);
    assertFalse(inviteTransactionServer.isCompleted());
    assertNull(inviteTransactionServer.getRequestMessage());
    assertNull(inviteTransactionServer.getTransportConnId());
  }

  @Test
  void testInvite() {
    // Arrange
    InviteDialog inviteDialog = new InviteDialog(new SipProvider("File"), null);
    NameAddress target = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");

    // Act
    inviteDialog.invite(target, from, new NameAddress("Str"), "Session descriptor");

    // Assert
    assertEquals(2, inviteDialog.status);
  }

  @Test
  void testInvite2() {
    // Arrange
    InviteDialog inviteDialog = new InviteDialog(new SipProvider("File"), null);
    NameAddress target = new NameAddress("42");
    NameAddress from = new NameAddress("Str");

    // Act
    inviteDialog.invite(target, from, new NameAddress("Str"), "Session descriptor");

    // Assert
    assertEquals(2, inviteDialog.status);
  }

  @Test
  void testReInvite() {
    // Arrange
    InviteDialog inviteDialog = new InviteDialog(new SipProvider("File"), null);
    NameAddress nameAddress = new NameAddress("Str");

    // Act
    inviteDialog.reInvite(nameAddress, "Session descriptor");

    // Assert that nothing has changed
    assertEquals("<sip:Str>", nameAddress.toString());
    assertFalse(nameAddress.hasDisplayName());
    assertEquals(2, inviteDialog.supported_option_tags.length);
    assertEquals(0, inviteDialog.status);
    assertTrue(inviteDialog.invite_offer);
    assertEquals(10, inviteDialog.allowed_methods.length);
    assertFalse(inviteDialog.isSecure());
    SipProvider expectedSipProvider = inviteDialog.sip_provider;
    assertSame(expectedSipProvider, inviteDialog.getSipProvider());
    assertEquals(0, inviteDialog.getSessionInterval());
    assertEquals(0L, inviteDialog.getSessionExpiration());
    assertEquals(-1L, inviteDialog.getRemoteCSeq());
    assertEquals(-1L, inviteDialog.getLastRSeq());
    assertEquals(-1L, inviteDialog.getLocalCSeq());
  }

  @Test
  void testReInvite2() {
    // Arrange
    InviteDialog inviteDialog = new InviteDialog(new SipProvider("File"), null);
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    inviteDialog.reInvite(sipMessage);

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
    assertEquals(2, inviteDialog.supported_option_tags.length);
    assertEquals(0, inviteDialog.status);
    assertTrue(inviteDialog.invite_offer);
    assertEquals(10, inviteDialog.allowed_methods.length);
  }

  @Test
  void testReInviteWithoutOffer() {
    // Arrange
    InviteDialog inviteDialog = new InviteDialog(new SipProvider("File"), null);

    // Act
    inviteDialog.reInviteWithoutOffer(new NameAddress("Str"), "Session descriptor");

    // Assert
    assertFalse(inviteDialog.invite_offer);
  }

  @Test
  void testReInviteWithoutOffer2() {
    // Arrange
    InviteDialog inviteDialog = new InviteDialog(new SipProvider("File"), null);

    // Act
    inviteDialog.reInviteWithoutOffer(new SipMessage("Str"));

    // Assert
    assertFalse(inviteDialog.invite_offer);
  }

  @Test
  void testRing() {
    // Arrange
    InviteDialog inviteDialog = new InviteDialog(new SipProvider("File"), null);

    // Act
    inviteDialog.ring();

    // Assert that nothing has changed
    assertEquals(2, inviteDialog.supported_option_tags.length);
    assertEquals(0, inviteDialog.status);
    assertTrue(inviteDialog.invite_offer);
    assertEquals(10, inviteDialog.allowed_methods.length);
    assertFalse(inviteDialog.isSecure());
    SipProvider expectedSipProvider = inviteDialog.sip_provider;
    assertSame(expectedSipProvider, inviteDialog.getSipProvider());
    assertEquals(0, inviteDialog.getSessionInterval());
    assertEquals(-1L, inviteDialog.getLastRSeq());
    assertEquals(-1L, inviteDialog.getLocalCSeq());
    assertEquals(-1L, inviteDialog.getRemoteCSeq());
    assertEquals(0L, inviteDialog.getSessionExpiration());
  }

  @Test
  void testAccept() {
    // Arrange
    InviteDialog inviteDialog = new InviteDialog(new SipProvider("File"), null);
    NameAddress nameAddress = new NameAddress("Str");

    // Act
    inviteDialog.accept(nameAddress, "Sdp");

    // Assert that nothing has changed
    assertEquals("<sip:Str>", nameAddress.toString());
    assertFalse(nameAddress.hasDisplayName());
    assertEquals(2, inviteDialog.supported_option_tags.length);
    assertEquals(0, inviteDialog.status);
    assertTrue(inviteDialog.invite_offer);
    assertEquals(10, inviteDialog.allowed_methods.length);
    assertFalse(inviteDialog.isSecure());
    SipProvider expectedSipProvider = inviteDialog.sip_provider;
    assertSame(expectedSipProvider, inviteDialog.getSipProvider());
    assertEquals(0, inviteDialog.getSessionInterval());
    assertEquals(0L, inviteDialog.getSessionExpiration());
    assertEquals(-1L, inviteDialog.getRemoteCSeq());
    assertEquals(-1L, inviteDialog.getLastRSeq());
    assertEquals(-1L, inviteDialog.getLocalCSeq());
  }

  @Test
  void testRefuse() {
    // Arrange
    InviteDialog inviteDialog = new InviteDialog(new SipProvider("File"), null);

    // Act
    inviteDialog.refuse();

    // Assert that nothing has changed
    assertEquals(2, inviteDialog.supported_option_tags.length);
    assertEquals(0, inviteDialog.status);
    assertTrue(inviteDialog.invite_offer);
    assertEquals(10, inviteDialog.allowed_methods.length);
    assertFalse(inviteDialog.isSecure());
    SipProvider expectedSipProvider = inviteDialog.sip_provider;
    assertSame(expectedSipProvider, inviteDialog.getSipProvider());
    assertEquals(0, inviteDialog.getSessionInterval());
    assertEquals(-1L, inviteDialog.getLastRSeq());
    assertEquals(-1L, inviteDialog.getLocalCSeq());
    assertEquals(-1L, inviteDialog.getRemoteCSeq());
    assertEquals(0L, inviteDialog.getSessionExpiration());
  }

  @Test
  void testRefuse2() {
    // Arrange
    InviteDialog inviteDialog = new InviteDialog(new SipProvider("File"), null);

    // Act
    inviteDialog.refuse(1, "Just cause");

    // Assert that nothing has changed
    assertEquals(2, inviteDialog.supported_option_tags.length);
    assertEquals(0, inviteDialog.status);
    assertTrue(inviteDialog.invite_offer);
    assertEquals(10, inviteDialog.allowed_methods.length);
    assertFalse(inviteDialog.isSecure());
    SipProvider expectedSipProvider = inviteDialog.sip_provider;
    assertSame(expectedSipProvider, inviteDialog.getSipProvider());
    assertEquals(0, inviteDialog.getSessionInterval());
    assertEquals(-1L, inviteDialog.getLastRSeq());
    assertEquals(-1L, inviteDialog.getLocalCSeq());
    assertEquals(-1L, inviteDialog.getRemoteCSeq());
    assertEquals(0L, inviteDialog.getSessionExpiration());
  }

  @Test
  void testRefuse3() {
    // Arrange
    InviteDialog inviteDialog = new InviteDialog(new SipProvider("File"), null);

    // Act
    inviteDialog.refuse(1, null);

    // Assert that nothing has changed
    assertEquals(2, inviteDialog.supported_option_tags.length);
    assertEquals(0, inviteDialog.status);
    assertTrue(inviteDialog.invite_offer);
    assertEquals(10, inviteDialog.allowed_methods.length);
    assertFalse(inviteDialog.isSecure());
    SipProvider expectedSipProvider = inviteDialog.sip_provider;
    assertSame(expectedSipProvider, inviteDialog.getSipProvider());
    assertEquals(0, inviteDialog.getSessionInterval());
    assertEquals(-1L, inviteDialog.getLastRSeq());
    assertEquals(-1L, inviteDialog.getLocalCSeq());
    assertEquals(-1L, inviteDialog.getRemoteCSeq());
    assertEquals(0L, inviteDialog.getSessionExpiration());
  }

  @Test
  void testRefuse4() {
    // Arrange
    InviteDialog inviteDialog = new InviteDialog(new SipProvider("File"), null);

    // Act
    inviteDialog.refuse(100, null);

    // Assert that nothing has changed
    assertEquals(2, inviteDialog.supported_option_tags.length);
    assertEquals(0, inviteDialog.status);
    assertTrue(inviteDialog.invite_offer);
    assertEquals(10, inviteDialog.allowed_methods.length);
    assertFalse(inviteDialog.isSecure());
    SipProvider expectedSipProvider = inviteDialog.sip_provider;
    assertSame(expectedSipProvider, inviteDialog.getSipProvider());
    assertEquals(0, inviteDialog.getSessionInterval());
    assertEquals(-1L, inviteDialog.getLastRSeq());
    assertEquals(-1L, inviteDialog.getLocalCSeq());
    assertEquals(-1L, inviteDialog.getRemoteCSeq());
    assertEquals(0L, inviteDialog.getSessionExpiration());
  }

  @Test
  void testRefuse5() {
    // Arrange
    InviteDialog inviteDialog = new InviteDialog(new SipProvider("File"), null);

    // Act
    inviteDialog.refuse(180, null);

    // Assert that nothing has changed
    assertEquals(2, inviteDialog.supported_option_tags.length);
    assertEquals(0, inviteDialog.status);
    assertTrue(inviteDialog.invite_offer);
    assertEquals(10, inviteDialog.allowed_methods.length);
    assertFalse(inviteDialog.isSecure());
    SipProvider expectedSipProvider = inviteDialog.sip_provider;
    assertSame(expectedSipProvider, inviteDialog.getSipProvider());
    assertEquals(0, inviteDialog.getSessionInterval());
    assertEquals(-1L, inviteDialog.getLastRSeq());
    assertEquals(-1L, inviteDialog.getLocalCSeq());
    assertEquals(-1L, inviteDialog.getRemoteCSeq());
    assertEquals(0L, inviteDialog.getSessionExpiration());
  }

  @Test
  void testRefuse6() {
    // Arrange
    InviteDialog inviteDialog = new InviteDialog(new SipProvider("File"), null);

    // Act
    inviteDialog.refuse(181, null);

    // Assert that nothing has changed
    assertEquals(2, inviteDialog.supported_option_tags.length);
    assertEquals(0, inviteDialog.status);
    assertTrue(inviteDialog.invite_offer);
    assertEquals(10, inviteDialog.allowed_methods.length);
    assertFalse(inviteDialog.isSecure());
    SipProvider expectedSipProvider = inviteDialog.sip_provider;
    assertSame(expectedSipProvider, inviteDialog.getSipProvider());
    assertEquals(0, inviteDialog.getSessionInterval());
    assertEquals(-1L, inviteDialog.getLastRSeq());
    assertEquals(-1L, inviteDialog.getLocalCSeq());
    assertEquals(-1L, inviteDialog.getRemoteCSeq());
    assertEquals(0L, inviteDialog.getSessionExpiration());
  }

  @Test
  void testRefuse7() {
    // Arrange
    InviteDialog inviteDialog = new InviteDialog(new SipProvider("File"), null);

    // Act
    inviteDialog.refuse(182, null);

    // Assert that nothing has changed
    assertEquals(2, inviteDialog.supported_option_tags.length);
    assertEquals(0, inviteDialog.status);
    assertTrue(inviteDialog.invite_offer);
    assertEquals(10, inviteDialog.allowed_methods.length);
    assertFalse(inviteDialog.isSecure());
    SipProvider expectedSipProvider = inviteDialog.sip_provider;
    assertSame(expectedSipProvider, inviteDialog.getSipProvider());
    assertEquals(0, inviteDialog.getSessionInterval());
    assertEquals(-1L, inviteDialog.getLastRSeq());
    assertEquals(-1L, inviteDialog.getLocalCSeq());
    assertEquals(-1L, inviteDialog.getRemoteCSeq());
    assertEquals(0L, inviteDialog.getSessionExpiration());
  }

  @Test
  void testRefuse8() {
    // Arrange
    InviteDialog inviteDialog = new InviteDialog(new SipProvider("File"), null);

    // Act
    inviteDialog.refuse(183, null);

    // Assert that nothing has changed
    assertEquals(2, inviteDialog.supported_option_tags.length);
    assertEquals(0, inviteDialog.status);
    assertTrue(inviteDialog.invite_offer);
    assertEquals(10, inviteDialog.allowed_methods.length);
    assertFalse(inviteDialog.isSecure());
    SipProvider expectedSipProvider = inviteDialog.sip_provider;
    assertSame(expectedSipProvider, inviteDialog.getSipProvider());
    assertEquals(0, inviteDialog.getSessionInterval());
    assertEquals(-1L, inviteDialog.getLastRSeq());
    assertEquals(-1L, inviteDialog.getLocalCSeq());
    assertEquals(-1L, inviteDialog.getRemoteCSeq());
    assertEquals(0L, inviteDialog.getSessionExpiration());
  }

  @Test
  void testRefuse9() {
    // Arrange
    InviteDialog inviteDialog = new InviteDialog(new SipProvider("File"), null);

    // Act
    inviteDialog.refuse(199, null);

    // Assert that nothing has changed
    assertEquals(2, inviteDialog.supported_option_tags.length);
    assertEquals(0, inviteDialog.status);
    assertTrue(inviteDialog.invite_offer);
    assertEquals(10, inviteDialog.allowed_methods.length);
    assertFalse(inviteDialog.isSecure());
    SipProvider expectedSipProvider = inviteDialog.sip_provider;
    assertSame(expectedSipProvider, inviteDialog.getSipProvider());
    assertEquals(0, inviteDialog.getSessionInterval());
    assertEquals(-1L, inviteDialog.getLastRSeq());
    assertEquals(-1L, inviteDialog.getLocalCSeq());
    assertEquals(-1L, inviteDialog.getRemoteCSeq());
    assertEquals(0L, inviteDialog.getSessionExpiration());
  }

  @Test
  void testRefuse10() {
    // Arrange
    InviteDialog inviteDialog = new InviteDialog(new SipProvider("File"), null);

    // Act
    inviteDialog.refuse(200, null);

    // Assert that nothing has changed
    assertEquals(2, inviteDialog.supported_option_tags.length);
    assertEquals(0, inviteDialog.status);
    assertTrue(inviteDialog.invite_offer);
    assertEquals(10, inviteDialog.allowed_methods.length);
    assertFalse(inviteDialog.isSecure());
    SipProvider expectedSipProvider = inviteDialog.sip_provider;
    assertSame(expectedSipProvider, inviteDialog.getSipProvider());
    assertEquals(0, inviteDialog.getSessionInterval());
    assertEquals(-1L, inviteDialog.getLastRSeq());
    assertEquals(-1L, inviteDialog.getLocalCSeq());
    assertEquals(-1L, inviteDialog.getRemoteCSeq());
    assertEquals(0L, inviteDialog.getSessionExpiration());
  }

  @Test
  void testRefuse11() {
    // Arrange
    InviteDialog inviteDialog = new InviteDialog(new SipProvider("File"), null);

    // Act
    inviteDialog.refuse(202, null);

    // Assert that nothing has changed
    assertEquals(2, inviteDialog.supported_option_tags.length);
    assertEquals(0, inviteDialog.status);
    assertTrue(inviteDialog.invite_offer);
    assertEquals(10, inviteDialog.allowed_methods.length);
    assertFalse(inviteDialog.isSecure());
    SipProvider expectedSipProvider = inviteDialog.sip_provider;
    assertSame(expectedSipProvider, inviteDialog.getSipProvider());
    assertEquals(0, inviteDialog.getSessionInterval());
    assertEquals(-1L, inviteDialog.getLastRSeq());
    assertEquals(-1L, inviteDialog.getLocalCSeq());
    assertEquals(-1L, inviteDialog.getRemoteCSeq());
    assertEquals(0L, inviteDialog.getSessionExpiration());
  }

  @Test
  void testRefuse12() {
    // Arrange
    InviteDialog inviteDialog = new InviteDialog(new SipProvider("File"), null);

    // Act
    inviteDialog.refuse(204, null);

    // Assert that nothing has changed
    assertEquals(2, inviteDialog.supported_option_tags.length);
    assertEquals(0, inviteDialog.status);
    assertTrue(inviteDialog.invite_offer);
    assertEquals(10, inviteDialog.allowed_methods.length);
    assertFalse(inviteDialog.isSecure());
    SipProvider expectedSipProvider = inviteDialog.sip_provider;
    assertSame(expectedSipProvider, inviteDialog.getSipProvider());
    assertEquals(0, inviteDialog.getSessionInterval());
    assertEquals(-1L, inviteDialog.getLastRSeq());
    assertEquals(-1L, inviteDialog.getLocalCSeq());
    assertEquals(-1L, inviteDialog.getRemoteCSeq());
    assertEquals(0L, inviteDialog.getSessionExpiration());
  }

  @Test
  void testRespond() throws UnsupportedEncodingException {
    // Arrange
    InviteDialog inviteDialog = new InviteDialog(new SipProvider("File"), null);
    NameAddress nameAddress = new NameAddress("Str");

    // Act
    inviteDialog.respond(1, "Just cause", nameAddress, "Not all who wander are lost", "AAAAAAAA".getBytes("UTF-8"));

    // Assert that nothing has changed
    assertEquals("<sip:Str>", nameAddress.toString());
    assertFalse(nameAddress.hasDisplayName());
    assertEquals(2, inviteDialog.supported_option_tags.length);
    assertEquals(0, inviteDialog.status);
    assertTrue(inviteDialog.invite_offer);
    assertEquals(10, inviteDialog.allowed_methods.length);
    assertFalse(inviteDialog.isSecure());
    SipProvider expectedSipProvider = inviteDialog.sip_provider;
    assertSame(expectedSipProvider, inviteDialog.getSipProvider());
    assertEquals(0, inviteDialog.getSessionInterval());
    assertEquals(0L, inviteDialog.getSessionExpiration());
    assertEquals(-1L, inviteDialog.getRemoteCSeq());
    assertEquals(-1L, inviteDialog.getLastRSeq());
    assertEquals(-1L, inviteDialog.getLocalCSeq());
  }

  @Test
  void testRespond2() {
    // Arrange
    InviteDialog inviteDialog = new InviteDialog(new SipProvider("File"), null);
    SipMessage sipMessage = mock(SipMessage.class);
    when(sipMessage.getCSeqHeader()).thenReturn(new CSeqHeader("42"));

    // Act
    inviteDialog.respond(sipMessage);

    // Assert
    verify(sipMessage).getCSeqHeader();
  }

  @Test
  void testRespond3() {
    // Arrange
    InviteDialog inviteDialog = new InviteDialog(new SipProvider("File"), null);
    SipMessage sipMessage = mock(SipMessage.class);
    when(sipMessage.getCSeqHeader()).thenReturn(new CSeqHeader("inside respond(resp)"));

    // Act
    inviteDialog.respond(sipMessage);

    // Assert
    verify(sipMessage).getCSeqHeader();
  }

  @Test
  void testRespond4() {
    // Arrange
    InviteDialog inviteDialog = new InviteDialog(new SipProvider("File"), null);
    SipMessage sipMessage = mock(SipMessage.class);
    when(sipMessage.getCSeqHeader()).thenReturn(new CSeqHeader("BYE"));

    // Act
    inviteDialog.respond(sipMessage);

    // Assert
    verify(sipMessage).getCSeqHeader();
  }

  @Test
  void testRespond5() {
    // Arrange
    InviteDialog inviteDialog = new InviteDialog(new SipProvider("File"), null);
    CSeqHeader cSeqHeader = mock(CSeqHeader.class);
    when(cSeqHeader.getMethod()).thenReturn("Method");
    SipMessage sipMessage = mock(SipMessage.class);
    when(sipMessage.getCSeqHeader()).thenReturn(cSeqHeader);

    // Act
    inviteDialog.respond(sipMessage);

    // Assert
    verify(sipMessage).getCSeqHeader();
    verify(cSeqHeader).getMethod();
  }

  @Test
  void testConfirm1xx() {
    // Arrange
    InviteDialog inviteDialog = new InviteDialog(new SipProvider("File"), null);
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    inviteDialog.confirm1xx(sipMessage);

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
    assertEquals(2, inviteDialog.supported_option_tags.length);
    assertEquals(0, inviteDialog.status);
    assertTrue(inviteDialog.invite_offer);
    assertEquals(10, inviteDialog.allowed_methods.length);
  }

  @Test
  void testConfirm1xx2() {
    // Arrange
    InviteDialog inviteDialog = new InviteDialog(new SipProvider("File"), null);
    SipMessage sipMessage = new SipMessage();

    // Act
    inviteDialog.confirm1xx(sipMessage);

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
    assertEquals(2, inviteDialog.supported_option_tags.length);
    assertEquals(0, inviteDialog.status);
    assertTrue(inviteDialog.invite_offer);
    assertEquals(10, inviteDialog.allowed_methods.length);
  }

  @Test
  void testConfirm1xx3() throws UnsupportedEncodingException {
    // Arrange
    InviteDialog inviteDialog = new InviteDialog(new SipProvider("File"), null);
    StatusLine status_line = new StatusLine(1, "foo");

    Vector headers = new Vector(1);
    SipMessage sipMessage = new SipMessage(status_line, headers, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    // Act
    inviteDialog.confirm1xx(sipMessage);

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
    assertEquals("SIP/2.0 1 foo\r\n", sipMessage.getFirstLine());
    assertEquals(24, sipMessage.getBody().length);
    assertEquals(2, inviteDialog.supported_option_tags.length);
    assertEquals(0, inviteDialog.status);
    assertTrue(inviteDialog.invite_offer);
    assertEquals(10, inviteDialog.allowed_methods.length);
  }

  @Test
  void testConfirm1xx4() throws UnsupportedEncodingException {
    // Arrange
    InviteDialog inviteDialog = new InviteDialog(new SipProvider("File"), null);
    RequestLine request_line = new RequestLine("Request", "Str uri");

    Vector vector = new Vector(1);

    SipMessage sipMessage = new SipMessage(request_line, vector, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    inviteDialog.confirm1xx(sipMessage);

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
    assertEquals(vector, sipMessage.getHeaders());
    assertEquals("Request Str uri SIP/2.0\r\n", sipMessage.getFirstLine());
    assertEquals(24, sipMessage.getBody().length);
    assertEquals(2, inviteDialog.supported_option_tags.length);
    assertEquals(0, inviteDialog.status);
    assertTrue(inviteDialog.invite_offer);
    assertEquals(10, inviteDialog.allowed_methods.length);
  }

  @Test
  void testHasPendingReliableProvisionalResponses() {
    // Arrange, Act and Assert
    assertFalse((new InviteDialog(new SipProvider("File"), null)).hasPendingReliableProvisionalResponses());
  }

  @Test
  void testBye() {
    // Arrange
    InviteDialog inviteDialog = new InviteDialog(new SipProvider("File"), null);

    // Act
    inviteDialog.bye();

    // Assert that nothing has changed
    assertEquals(2, inviteDialog.supported_option_tags.length);
    assertEquals(0, inviteDialog.status);
    assertTrue(inviteDialog.invite_offer);
    assertEquals(10, inviteDialog.allowed_methods.length);
    assertFalse(inviteDialog.isSecure());
    SipProvider expectedSipProvider = inviteDialog.sip_provider;
    assertSame(expectedSipProvider, inviteDialog.getSipProvider());
    assertEquals(0, inviteDialog.getSessionInterval());
    assertEquals(-1L, inviteDialog.getLastRSeq());
    assertEquals(-1L, inviteDialog.getLocalCSeq());
    assertEquals(-1L, inviteDialog.getRemoteCSeq());
    assertEquals(0L, inviteDialog.getSessionExpiration());
  }

  @Test
  void testBye2() {
    // Arrange
    InviteDialog inviteDialog = new InviteDialog(new SipProvider("File"), null);
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    inviteDialog.bye(sipMessage);

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
    assertEquals(2, inviteDialog.supported_option_tags.length);
    assertEquals(0, inviteDialog.status);
    assertTrue(inviteDialog.invite_offer);
    assertEquals(10, inviteDialog.allowed_methods.length);
  }

  @Test
  void testCancel() {
    // Arrange
    InviteDialog inviteDialog = new InviteDialog(new SipProvider("File"), null);

    // Act
    inviteDialog.cancel();

    // Assert that nothing has changed
    assertEquals(2, inviteDialog.supported_option_tags.length);
    assertEquals(0, inviteDialog.status);
    assertTrue(inviteDialog.invite_offer);
    assertEquals(10, inviteDialog.allowed_methods.length);
    assertFalse(inviteDialog.isSecure());
    SipProvider expectedSipProvider = inviteDialog.sip_provider;
    assertSame(expectedSipProvider, inviteDialog.getSipProvider());
    assertEquals(0, inviteDialog.getSessionInterval());
    assertEquals(-1L, inviteDialog.getLastRSeq());
    assertEquals(-1L, inviteDialog.getLocalCSeq());
    assertEquals(-1L, inviteDialog.getRemoteCSeq());
    assertEquals(0L, inviteDialog.getSessionExpiration());
  }

  @Test
  void testCancel2() {
    // Arrange
    InviteDialog inviteDialog = new InviteDialog(new SipProvider("File"), null);
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    inviteDialog.cancel(sipMessage);

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
    assertEquals(2, inviteDialog.supported_option_tags.length);
    assertEquals(0, inviteDialog.status);
    assertTrue(inviteDialog.invite_offer);
    assertEquals(10, inviteDialog.allowed_methods.length);
  }

  @Test
  void testInfo() throws UnsupportedEncodingException {
    // Arrange
    InviteDialog inviteDialog = new InviteDialog(new SipProvider("File"), null);

    // Act
    inviteDialog.info("Not all who wander are lost", "AAAAAAAA".getBytes("UTF-8"));

    // Assert that nothing has changed
    assertEquals(2, inviteDialog.supported_option_tags.length);
    assertEquals(0, inviteDialog.status);
    assertTrue(inviteDialog.invite_offer);
    assertEquals(10, inviteDialog.allowed_methods.length);
    assertFalse(inviteDialog.isSecure());
    SipProvider expectedSipProvider = inviteDialog.sip_provider;
    assertSame(expectedSipProvider, inviteDialog.getSipProvider());
    assertEquals(0, inviteDialog.getSessionInterval());
    assertEquals(-1L, inviteDialog.getLastRSeq());
    assertEquals(-1L, inviteDialog.getLocalCSeq());
    assertEquals(-1L, inviteDialog.getRemoteCSeq());
    assertEquals(0L, inviteDialog.getSessionExpiration());
  }

  @Test
  void testInfo2() {
    // Arrange
    InviteDialog inviteDialog = new InviteDialog(new SipProvider("File"), null);
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    inviteDialog.info(sipMessage);

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
    assertEquals(2, inviteDialog.supported_option_tags.length);
    assertEquals(0, inviteDialog.status);
    assertTrue(inviteDialog.invite_offer);
    assertEquals(10, inviteDialog.allowed_methods.length);
  }

  @Test
  void testAcceptUpdate() {
    // Arrange
    InviteDialog inviteDialog = new InviteDialog(new SipProvider("File"), null);

    // Act
    inviteDialog.acceptUpdate("Sdp");

    // Assert that nothing has changed
    assertEquals(2, inviteDialog.supported_option_tags.length);
    assertEquals(0, inviteDialog.status);
    assertTrue(inviteDialog.invite_offer);
    assertEquals(10, inviteDialog.allowed_methods.length);
    assertFalse(inviteDialog.isSecure());
    SipProvider expectedSipProvider = inviteDialog.sip_provider;
    assertSame(expectedSipProvider, inviteDialog.getSipProvider());
    assertEquals(0, inviteDialog.getSessionInterval());
    assertEquals(-1L, inviteDialog.getLastRSeq());
    assertEquals(-1L, inviteDialog.getLocalCSeq());
    assertEquals(-1L, inviteDialog.getRemoteCSeq());
    assertEquals(0L, inviteDialog.getSessionExpiration());
  }

  @Test
  void testRefuseUpdate() {
    // Arrange
    InviteDialog inviteDialog = new InviteDialog(new SipProvider("File"), null);

    // Act
    inviteDialog.refuseUpdate();

    // Assert that nothing has changed
    assertEquals(2, inviteDialog.supported_option_tags.length);
    assertEquals(0, inviteDialog.status);
    assertTrue(inviteDialog.invite_offer);
    assertEquals(10, inviteDialog.allowed_methods.length);
    assertFalse(inviteDialog.isSecure());
    SipProvider expectedSipProvider = inviteDialog.sip_provider;
    assertSame(expectedSipProvider, inviteDialog.getSipProvider());
    assertEquals(0, inviteDialog.getSessionInterval());
    assertEquals(-1L, inviteDialog.getLastRSeq());
    assertEquals(-1L, inviteDialog.getLocalCSeq());
    assertEquals(-1L, inviteDialog.getRemoteCSeq());
    assertEquals(0L, inviteDialog.getSessionExpiration());
  }

  @Test
  void testTerminate() {
    // Arrange
    InviteDialog inviteDialog = new InviteDialog(new SipProvider("File"), null);

    // Act
    inviteDialog.terminate();

    // Assert
    assertNull(inviteDialog.update_ts);
    assertEquals(9, inviteDialog.status);
    assertNull(inviteDialog.invite_ts);
    assertNull(inviteDialog.invite_tc);
    assertNull(inviteDialog.cancel_ts);
    assertNull(inviteDialog.bye_ts);
    assertNull(inviteDialog.ack_ts);
  }

  @Test
  void testTerminate2() {
    // Arrange
    InviteDialogListener inviteDialogListener = mock(InviteDialogListener.class);
    doNothing().when(inviteDialogListener).onDlgClosed((InviteDialog) any());
    InviteDialog inviteDialog = new InviteDialog(new SipProvider("File"), inviteDialogListener);

    // Act
    inviteDialog.terminate();

    // Assert
    verify(inviteDialogListener).onDlgClosed((InviteDialog) any());
    assertNull(inviteDialog.update_ts);
    assertEquals(9, inviteDialog.status);
    assertNull(inviteDialog.invite_ts);
    assertNull(inviteDialog.invite_tc);
    assertNull(inviteDialog.cancel_ts);
    assertNull(inviteDialog.bye_ts);
    assertNull(inviteDialog.ack_ts);
  }

  @Test
  void testOnReceivedMessage() {
    // Arrange
    InviteDialog inviteDialog = new InviteDialog(new SipProvider("File"), null);
    SipProvider sipProvider = new SipProvider("File");

    // Act
    inviteDialog.onReceivedMessage(sipProvider, new SipMessage("Str"));

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
    assertEquals(2, inviteDialog.supported_option_tags.length);
    assertEquals(0, inviteDialog.status);
    assertTrue(inviteDialog.invite_offer);
    assertEquals(10, inviteDialog.allowed_methods.length);
  }

  @Test
  void testOnReceivedMessage2() {
    // Arrange
    InviteDialog inviteDialog = new InviteDialog(new SipProvider("File"), null);
    SipProvider sipProvider = new SipProvider("File");

    // Act
    inviteDialog.onReceivedMessage(sipProvider, new SipMessage((String) null));

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
    assertEquals(2, inviteDialog.supported_option_tags.length);
    assertEquals(0, inviteDialog.status);
    assertTrue(inviteDialog.invite_offer);
    assertEquals(10, inviteDialog.allowed_methods.length);
  }

  @Test
  void testOnReceivedMessage3() {
    // Arrange
    InviteDialog inviteDialog = new InviteDialog(new SipProvider("File"), null);
    SipProvider sipProvider = new SipProvider("File");

    // Act
    inviteDialog.onReceivedMessage(sipProvider, new SipMessage());

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
    assertEquals(2, inviteDialog.supported_option_tags.length);
    assertEquals(0, inviteDialog.status);
    assertTrue(inviteDialog.invite_offer);
    assertEquals(10, inviteDialog.allowed_methods.length);
  }

  @Test
  void testOnReceivedMessage4() throws UnsupportedEncodingException {
    // Arrange
    InviteDialog inviteDialog = new InviteDialog(new SipProvider("File"), null);
    SipProvider sipProvider = new SipProvider("File");
    StatusLine status_line = new StatusLine(1, "foo");

    Vector headers = new Vector(1);

    // Act
    inviteDialog.onReceivedMessage(sipProvider,
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
    assertEquals(2, inviteDialog.supported_option_tags.length);
    assertEquals(0, inviteDialog.status);
    assertTrue(inviteDialog.invite_offer);
    assertEquals(10, inviteDialog.allowed_methods.length);
  }

  @Test
  void testOnTransFailureAck() {
    // Arrange
    InviteDialog inviteDialog = new InviteDialog(new SipProvider("File"), null);
    SipProvider sipProvider = new SipProvider("File");
    InviteTransactionServer inviteTransactionServer = new InviteTransactionServer(sipProvider,
        new ExtendedInviteDialog(new SipProvider("File"), null));

    // Act
    inviteDialog.onTransFailureAck(inviteTransactionServer, new SipMessage("Str"));

    // Assert that nothing has changed
    assertFalse(inviteTransactionServer.isTrying());
    assertTrue(inviteTransactionServer.getTransactionId() instanceof org.mjsip.sip.provider.TransactionServerId);
    assertSame(sipProvider, inviteTransactionServer.getSipProvider());
    assertEquals(2, inviteDialog.supported_option_tags.length);
    assertEquals(0, inviteDialog.status);
    assertTrue(inviteDialog.invite_offer);
    assertEquals(10, inviteDialog.allowed_methods.length);
  }

  @Test
  void testOnTransAckTimeout() {
    // Arrange
    InviteDialog inviteDialog = new InviteDialog(new SipProvider("File"), null);

    // Act
    inviteDialog.onTransAckTimeout(null);

    // Assert that nothing has changed
    assertEquals(2, inviteDialog.supported_option_tags.length);
    assertEquals(0, inviteDialog.status);
    assertTrue(inviteDialog.invite_offer);
    assertEquals(10, inviteDialog.allowed_methods.length);
    assertFalse(inviteDialog.isSecure());
    SipProvider expectedSipProvider = inviteDialog.sip_provider;
    assertSame(expectedSipProvider, inviteDialog.getSipProvider());
    assertEquals(0, inviteDialog.getSessionInterval());
    assertEquals(-1L, inviteDialog.getLastRSeq());
    assertEquals(-1L, inviteDialog.getLocalCSeq());
    assertEquals(-1L, inviteDialog.getRemoteCSeq());
    assertEquals(0L, inviteDialog.getSessionExpiration());
  }

  @Test
  void testOnReliableProvisionalResponseConfirmation() {
    // Arrange
    InviteDialog inviteDialog = new InviteDialog(new SipProvider("File"), null);
    SipProvider sip_provider = new SipProvider("File");
    InviteTransactionServer invite_ts = new InviteTransactionServer(sip_provider,
        new ExtendedInviteDialog(new SipProvider("File"), null));

    ReliableProvisionalResponder reliableProvisionalResponder = new ReliableProvisionalResponder(invite_ts,
        new ExtendedInviteDialog(new SipProvider("File"), null));

    SipMessage sipMessage = new SipMessage("Str");

    // Act
    inviteDialog.onReliableProvisionalResponseConfirmation(reliableProvisionalResponder, sipMessage,
        new SipMessage("Str"));

    // Assert that nothing has changed
    assertFalse(reliableProvisionalResponder.hasPendingResponses());
    assertEquals(0, sipMessage.getRemotePort());
    assertEquals(2, inviteDialog.supported_option_tags.length);
    assertEquals(0, inviteDialog.status);
    assertTrue(inviteDialog.invite_offer);
    assertEquals(10, inviteDialog.allowed_methods.length);
  }

  @Test
  void testOnReliableProvisionalResponseConfirmation2() throws UnsupportedEncodingException {
    // Arrange
    InviteDialogListener inviteDialogListener = mock(InviteDialogListener.class);
    doNothing().when(inviteDialogListener)
        .onDlgInviteReliableProvisionalResponseConfirmed((InviteDialog) any(), anyInt(), (SipMessage) any(),
            (String) any(), (byte[]) any(), (SipMessage) any());
    InviteDialog inviteDialog = new InviteDialog(new SipProvider("File"), inviteDialogListener);
    SipProvider sip_provider = new SipProvider("File");
    InviteTransactionServer invite_ts = new InviteTransactionServer(sip_provider,
        new ExtendedInviteDialog(new SipProvider("File"), null));

    ReliableProvisionalResponder rr = new ReliableProvisionalResponder(invite_ts,
        new ExtendedInviteDialog(new SipProvider("File"), null));

    StatusLine status_line = new StatusLine(1, "foo");

    Vector headers = new Vector(1);
    SipMessage resp = new SipMessage(status_line, headers, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    // Act
    inviteDialog.onReliableProvisionalResponseConfirmation(rr, resp, new SipMessage("Str"));

    // Assert
    verify(inviteDialogListener).onDlgInviteReliableProvisionalResponseConfirmed((InviteDialog) any(), anyInt(),
        (SipMessage) any(), (String) any(), (byte[]) any(), (SipMessage) any());
  }

  @Test
  void testOnReliableProvisionalResponseTimeout() {
    // Arrange
    InviteDialog inviteDialog = new InviteDialog(new SipProvider("File"), null);
    SipProvider sip_provider = new SipProvider("File");
    InviteTransactionServer invite_ts = new InviteTransactionServer(sip_provider,
        new ExtendedInviteDialog(new SipProvider("File"), null));

    ReliableProvisionalResponder reliableProvisionalResponder = new ReliableProvisionalResponder(invite_ts,
        new ExtendedInviteDialog(new SipProvider("File"), null));

    SipMessage sipMessage = new SipMessage("Str");

    // Act
    inviteDialog.onReliableProvisionalResponseTimeout(reliableProvisionalResponder, sipMessage);

    // Assert that nothing has changed
    assertFalse(reliableProvisionalResponder.hasPendingResponses());
    assertEquals(0, sipMessage.getRemotePort());
    assertEquals(2, inviteDialog.supported_option_tags.length);
    assertEquals(0, inviteDialog.status);
    assertTrue(inviteDialog.invite_offer);
    assertEquals(10, inviteDialog.allowed_methods.length);
  }

  @Test
  void testOnReliableProvisionalResponseTimeout2() throws UnsupportedEncodingException {
    // Arrange
    InviteDialogListener inviteDialogListener = mock(InviteDialogListener.class);
    doNothing().when(inviteDialogListener)
        .onDlgInviteReliableProvisionalResponseTimeout((InviteDialog) any(), anyInt(), (SipMessage) any());
    InviteDialog inviteDialog = new InviteDialog(new SipProvider("File"), inviteDialogListener);
    SipProvider sip_provider = new SipProvider("File");
    InviteTransactionServer invite_ts = new InviteTransactionServer(sip_provider,
        new ExtendedInviteDialog(new SipProvider("File"), null));

    ReliableProvisionalResponder rr = new ReliableProvisionalResponder(invite_ts,
        new ExtendedInviteDialog(new SipProvider("File"), null));

    StatusLine status_line = new StatusLine(1, "foo");

    Vector headers = new Vector(1);

    // Act
    inviteDialog.onReliableProvisionalResponseTimeout(rr,
        new SipMessage(status_line, headers, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8")));

    // Assert
    verify(inviteDialogListener).onDlgInviteReliableProvisionalResponseTimeout((InviteDialog) any(), anyInt(),
        (SipMessage) any());
  }

  @Test
  void testLog() {
    // Arrange
    InviteDialog inviteDialog = new InviteDialog(new SipProvider("File"), null);
    LogLevel logLevel = new LogLevel("Name", 42);

    // Act
    inviteDialog.log(logLevel, new Exception("An error occurred"));

    // Assert that nothing has changed
    assertEquals("Name", logLevel.getName());
    assertEquals(42, logLevel.getValue());
    assertEquals(2, inviteDialog.supported_option_tags.length);
    assertEquals(0, inviteDialog.status);
    assertTrue(inviteDialog.invite_offer);
    assertEquals(10, inviteDialog.allowed_methods.length);
    assertFalse(inviteDialog.isSecure());
    SipProvider expectedSipProvider = inviteDialog.sip_provider;
    assertSame(expectedSipProvider, inviteDialog.getSipProvider());
    assertEquals(0, inviteDialog.getSessionInterval());
    assertEquals(-1L, inviteDialog.getLastRSeq());
    assertEquals(-1L, inviteDialog.getLocalCSeq());
    assertEquals(-1L, inviteDialog.getRemoteCSeq());
    assertEquals(0L, inviteDialog.getSessionExpiration());
  }

  @Test
  void testLog2() {
    // Arrange
    InviteDialog inviteDialog = new InviteDialog(new SipProvider("File"), null);
    LogLevel logLevel = new LogLevel("Name", 42);

    // Act
    inviteDialog.log(logLevel, "Str");

    // Assert that nothing has changed
    assertEquals("Name", logLevel.getName());
    assertEquals(42, logLevel.getValue());
    assertEquals(2, inviteDialog.supported_option_tags.length);
    assertEquals(0, inviteDialog.status);
    assertTrue(inviteDialog.invite_offer);
    assertEquals(10, inviteDialog.allowed_methods.length);
    assertFalse(inviteDialog.isSecure());
    SipProvider expectedSipProvider = inviteDialog.sip_provider;
    assertSame(expectedSipProvider, inviteDialog.getSipProvider());
    assertEquals(0, inviteDialog.getSessionInterval());
    assertEquals(-1L, inviteDialog.getLastRSeq());
    assertEquals(-1L, inviteDialog.getLocalCSeq());
    assertEquals(-1L, inviteDialog.getRemoteCSeq());
    assertEquals(0L, inviteDialog.getSessionExpiration());
  }

  @Test
  void testGetDialogID() {
    // Arrange, Act and Assert
    assertNull((new InviteDialog(new SipProvider("File"), null)).getDialogID());
  }

  @Test
  void testGetSipProvider() {
    // Arrange
    InviteDialog inviteDialog = new InviteDialog(new SipProvider("File"), null);

    // Act and Assert
    assertSame(inviteDialog.sip_provider, inviteDialog.getSipProvider());
  }

  @Test
  void testStatusIs() {
    // Arrange, Act and Assert
    assertFalse((new InviteDialog(new SipProvider("File"), null)).statusIs(1));
    assertTrue((new InviteDialog(new SipProvider("File"), null)).statusIs(0));
  }

  @Test
  void testUpdateDialogInfo() {
    // Arrange
    InviteDialog inviteDialog = new InviteDialog(new SipProvider("File"), null);
    SipMessage sipMessage = mock(SipMessage.class);
    when(sipMessage.getContactHeader()).thenReturn(new ContactHeader());
    when(sipMessage.hasContactHeader()).thenReturn(true);
    when(sipMessage.getCSeqHeader()).thenReturn(new CSeqHeader("42"));
    when(sipMessage.getFromHeader()).thenReturn(new FromHeader(new Header("Hname", "42")));
    when(sipMessage.getToHeader()).thenReturn(new ToHeader(new Header("Hname", "42")));
    when(sipMessage.getCallIdHeader()).thenReturn(new CallIdHeader("42"));

    // Act
    inviteDialog.updateDialogInfo(true, sipMessage);

    // Assert
    verify(sipMessage).getCSeqHeader();
    verify(sipMessage).getCallIdHeader();
    verify(sipMessage).getContactHeader();
    verify(sipMessage).getFromHeader();
    verify(sipMessage).getToHeader();
    verify(sipMessage).hasContactHeader();
    assertEquals("42", inviteDialog.getCallID());
    DialogId expectedDialogID = inviteDialog.dialog_id;
    DialogId dialogID = inviteDialog.getDialogID();
    assertSame(expectedDialogID, dialogID);
    assertNull(inviteDialog.getLocalTag());
    assertEquals(42L, inviteDialog.getLocalCSeq());
    NameAddress expectedRemoteContact = inviteDialog.remote_contact;
    NameAddress remoteContact = inviteDialog.getRemoteContact();
    assertSame(expectedRemoteContact, remoteContact);
    NameAddress expectedRemoteName = inviteDialog.local_name;
    NameAddress remoteName = inviteDialog.getRemoteName();
    assertEquals(expectedRemoteName, remoteName);
    assertNull(inviteDialog.getRemoteTag());
    assertEquals(remoteName, inviteDialog.getLocalName());
    assertTrue(remoteName.getAddress() instanceof org.mjsip.sip.address.SipURI);
    assertNull(remoteContact.getDisplayName());
    assertEquals("<sip:*>", remoteContact.toString());
    assertEquals("42-null-null", dialogID.toString());
  }

  @Test
  void testUpdateDialogInfo2() {
    // Arrange
    InviteDialog inviteDialog = new InviteDialog(new SipProvider("File"), null);
    SipMessage sipMessage = mock(SipMessage.class);
    when(sipMessage.getContactHeader()).thenReturn(new ContactHeader());
    when(sipMessage.hasContactHeader()).thenReturn(true);
    when(sipMessage.getCSeqHeader()).thenReturn(new CSeqHeader("42"));
    when(sipMessage.getFromHeader()).thenReturn(new FromHeader(new Header("Hname", "42")));
    when(sipMessage.getToHeader()).thenReturn(new ToHeader(new Header("Hname", "42")));
    when(sipMessage.getCallIdHeader()).thenReturn(new CallIdHeader("42"));

    // Act
    inviteDialog.updateDialogInfo(false, sipMessage);

    // Assert
    verify(sipMessage).getCSeqHeader();
    verify(sipMessage).getCallIdHeader();
    verify(sipMessage).getContactHeader();
    verify(sipMessage).getFromHeader();
    verify(sipMessage).getToHeader();
    verify(sipMessage).hasContactHeader();
    assertEquals("42", inviteDialog.getCallID());
    DialogId expectedDialogID = inviteDialog.dialog_id;
    DialogId dialogID = inviteDialog.getDialogID();
    assertSame(expectedDialogID, dialogID);
    assertNull(inviteDialog.getLocalTag());
    assertEquals(0L, inviteDialog.getLocalCSeq());
    assertEquals(42L, inviteDialog.getRemoteCSeq());
    NameAddress expectedRemoteContact = inviteDialog.remote_contact;
    NameAddress remoteContact = inviteDialog.getRemoteContact();
    assertSame(expectedRemoteContact, remoteContact);
    NameAddress expectedRemoteName = inviteDialog.local_name;
    NameAddress remoteName = inviteDialog.getRemoteName();
    assertEquals(expectedRemoteName, remoteName);
    assertNull(inviteDialog.getRemoteTag());
    assertEquals(remoteName, inviteDialog.getLocalName());
    assertTrue(remoteName.getAddress() instanceof org.mjsip.sip.address.SipURI);
    assertNull(remoteContact.getDisplayName());
    assertEquals("<sip:*>", remoteContact.toString());
    assertEquals("42-null-null", dialogID.toString());
  }

  @Test
  void testUpdateDialogInfo3() {
    // Arrange
    InviteDialog inviteDialog = new InviteDialog(new SipProvider("File"), null);
    SipMessage sipMessage = mock(SipMessage.class);
    NameAddress nameAddress = new NameAddress("Str");
    when(sipMessage.getContactHeader()).thenReturn(new ContactHeader(nameAddress));
    when(sipMessage.hasContactHeader()).thenReturn(true);
    when(sipMessage.getCSeqHeader()).thenReturn(new CSeqHeader("42"));
    when(sipMessage.getFromHeader()).thenReturn(new FromHeader(new Header("Hname", "42")));
    when(sipMessage.getToHeader()).thenReturn(new ToHeader(new Header("Hname", "42")));
    when(sipMessage.getCallIdHeader()).thenReturn(new CallIdHeader("42"));

    // Act
    inviteDialog.updateDialogInfo(true, sipMessage);

    // Assert
    verify(sipMessage).getCSeqHeader();
    verify(sipMessage).getCallIdHeader();
    verify(sipMessage).getContactHeader();
    verify(sipMessage).getFromHeader();
    verify(sipMessage).getToHeader();
    verify(sipMessage).hasContactHeader();
    assertEquals("42", inviteDialog.getCallID());
    DialogId expectedDialogID = inviteDialog.dialog_id;
    DialogId dialogID = inviteDialog.getDialogID();
    assertSame(expectedDialogID, dialogID);
    assertNull(inviteDialog.getLocalTag());
    assertEquals(42L, inviteDialog.getLocalCSeq());
    NameAddress remoteContact = inviteDialog.getRemoteContact();
    assertEquals(nameAddress, remoteContact);
    NameAddress expectedRemoteName = inviteDialog.local_name;
    NameAddress remoteName = inviteDialog.getRemoteName();
    assertEquals(expectedRemoteName, remoteName);
    assertNull(inviteDialog.getRemoteTag());
    assertEquals(remoteName, inviteDialog.getLocalName());
    assertTrue(remoteName.getAddress() instanceof org.mjsip.sip.address.SipURI);
    assertNull(remoteContact.getDisplayName());
    assertEquals("<sip:Str>", remoteContact.toString());
    assertEquals("42-null-null", dialogID.toString());
  }

  @Test
  void testVerifyStatus() {
    // Arrange, Act and Assert
    assertTrue((new InviteDialog(new SipProvider("File"), null)).verifyStatus(true));
    assertFalse((new InviteDialog(new SipProvider("File"), null)).verifyStatus(false));
  }

  @Test
  void testVerifyThat() {
    // Arrange, Act and Assert
    assertTrue((new InviteDialog(new SipProvider("File"), null)).verifyThat(true, "Str"));
    assertFalse((new InviteDialog(new SipProvider("File"), null)).verifyThat(false, "Str"));
    assertFalse((new InviteDialog(new SipProvider("File"), null)).verifyThat(false, null));
    assertFalse((new InviteDialog(new SipProvider("File"), null)).verifyThat(false, ""));
  }
}

