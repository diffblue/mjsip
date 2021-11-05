package org.mjsip.sip.dialog;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Vector;
import org.junit.jupiter.api.Test;
import org.mjsip.sip.address.NameAddress;
import org.mjsip.sip.address.SipURI;
import org.mjsip.sip.header.CSeqHeader;
import org.mjsip.sip.header.CallIdHeader;
import org.mjsip.sip.header.ContactHeader;
import org.mjsip.sip.header.FromHeader;
import org.mjsip.sip.header.Header;
import org.mjsip.sip.header.ToHeader;
import org.mjsip.sip.message.SipMessage;
import org.mjsip.sip.provider.SipProvider;

class DialogInfoDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    DialogInfo actualDialogInfo = new DialogInfo();
    actualDialogInfo.setCallID("42");
    actualDialogInfo.setLastRSeq(1L);
    actualDialogInfo.setLocalCSeq(1L);
    NameAddress nameAddress = new NameAddress("Str");
    actualDialogInfo.setLocalContact(nameAddress);
    NameAddress nameAddress1 = new NameAddress("Str");
    actualDialogInfo.setLocalName(nameAddress1);
    actualDialogInfo.setLocalTag("Tag");
    actualDialogInfo.setRefresher("Refresher");
    actualDialogInfo.setRemoteCSeq(1L);
    NameAddress nameAddress2 = new NameAddress("Str");
    actualDialogInfo.setRemoteContact(nameAddress2);
    NameAddress nameAddress3 = new NameAddress("Str");
    actualDialogInfo.setRemoteName(nameAddress3);
    actualDialogInfo.setRemoteTag("Tag");
    Vector vector = new Vector(1);
    actualDialogInfo.setRoute(vector);
    actualDialogInfo.setSecure(true);
    actualDialogInfo.setSessionExpiration(1L);
    actualDialogInfo.setSessionInterval(42);

    // Assert
    assertEquals("42", actualDialogInfo.getCallID());
    assertEquals(1L, actualDialogInfo.getLastRSeq());
    assertEquals(1L, actualDialogInfo.getLocalCSeq());
    NameAddress localContact = actualDialogInfo.getLocalContact();
    assertSame(nameAddress, localContact);
    assertEquals(nameAddress1, localContact);
    NameAddress remoteContact = actualDialogInfo.getRemoteContact();
    assertEquals(remoteContact, localContact);
    NameAddress remoteName = actualDialogInfo.getRemoteName();
    assertEquals(remoteName, localContact);
    NameAddress localName = actualDialogInfo.getLocalName();
    assertSame(nameAddress1, localName);
    assertEquals(localContact, localName);
    assertEquals(remoteContact, localName);
    assertEquals(remoteName, localName);
    assertEquals("Tag", actualDialogInfo.getLocalTag());
    assertEquals("Refresher", actualDialogInfo.getRefresher());
    assertEquals(1L, actualDialogInfo.getRemoteCSeq());
    assertSame(nameAddress2, remoteContact);
    assertEquals(nameAddress, remoteContact);
    assertEquals(nameAddress1, remoteContact);
    assertEquals(remoteName, remoteContact);
    assertSame(nameAddress3, remoteName);
    assertEquals(nameAddress, remoteName);
    assertEquals(nameAddress1, remoteName);
    assertEquals(nameAddress2, remoteName);
    assertEquals("Tag", actualDialogInfo.getRemoteTag());
    assertSame(vector, actualDialogInfo.getRoute());
    assertEquals(1L, actualDialogInfo.getSessionExpiration());
    assertEquals(42, actualDialogInfo.getSessionInterval());
    assertTrue(actualDialogInfo.isSecure());
  }

  @Test
  void testConstructor2() {
    // Arrange and Act
    DialogInfo actualDialogInfo = new DialogInfo();

    // Assert
    assertNull(actualDialogInfo.getCallID());
    assertFalse(actualDialogInfo.isSecure());
    assertEquals(0, actualDialogInfo.getSessionInterval());
    assertEquals(0L, actualDialogInfo.getSessionExpiration());
    assertNull(actualDialogInfo.getRoute());
    assertNull(actualDialogInfo.getRemoteTag());
    assertNull(actualDialogInfo.getRemoteName());
    assertNull(actualDialogInfo.getRemoteContact());
    assertEquals(-1L, actualDialogInfo.getRemoteCSeq());
    assertNull(actualDialogInfo.getRefresher());
    assertNull(actualDialogInfo.getLocalTag());
    assertNull(actualDialogInfo.getLocalName());
    assertNull(actualDialogInfo.getLocalContact());
    assertEquals(-1L, actualDialogInfo.getLocalCSeq());
    assertEquals(-1L, actualDialogInfo.getLastRSeq());
  }

  @Test
  void testUpdate() {
    // Arrange
    DialogInfo dialogInfo = new DialogInfo();
    SipProvider sipProvider = mock(SipProvider.class);
    when(sipProvider.getPort()).thenReturn(8080);
    when(sipProvider.getViaAddress()).thenReturn("42 Main St");
    SipMessage sipMessage = mock(SipMessage.class);
    when(sipMessage.getContactHeader()).thenReturn(new ContactHeader());
    when(sipMessage.hasContactHeader()).thenReturn(true);
    when(sipMessage.getCSeqHeader()).thenReturn(new CSeqHeader("42"));
    when(sipMessage.getFromHeader()).thenReturn(new FromHeader(new Header("Hname", "42")));
    when(sipMessage.getToHeader()).thenReturn(new ToHeader(new Header("Hname", "42")));
    when(sipMessage.getCallIdHeader()).thenReturn(new CallIdHeader("42"));

    // Act
    dialogInfo.update(true, sipProvider, sipMessage);

    // Assert
    verify(sipProvider).getPort();
    verify(sipProvider).getViaAddress();
    verify(sipMessage).getCSeqHeader();
    verify(sipMessage).getCallIdHeader();
    verify(sipMessage).getContactHeader();
    verify(sipMessage).getFromHeader();
    verify(sipMessage).getToHeader();
    verify(sipMessage).hasContactHeader();
    assertEquals("42", dialogInfo.getCallID());
    assertNull(dialogInfo.getRemoteTag());
    NameAddress expectedRemoteName = dialogInfo.local_name;
    NameAddress remoteName = dialogInfo.getRemoteName();
    assertEquals(expectedRemoteName, remoteName);
    NameAddress expectedRemoteContact = dialogInfo.remote_contact;
    NameAddress remoteContact = dialogInfo.getRemoteContact();
    assertSame(expectedRemoteContact, remoteContact);
    NameAddress localName = dialogInfo.getLocalName();
    assertEquals(remoteName, localName);
    assertEquals(42L, dialogInfo.getLocalCSeq());
    assertNull(dialogInfo.getLocalTag());
    assertNull(localName.getDisplayName());
    assertFalse(remoteName.hasDisplayName());
    assertNull(remoteContact.getDisplayName());
    assertEquals("<sip:*>", remoteContact.toString());
    assertEquals("<sip:42>", remoteName.toString());
    assertEquals("<sip:42>", localName.toString());
    assertNull(((SipURI) remoteName.getAddress()).getMaddr());
    assertNull(((SipURI) localName.getAddress()).getUserName());
    assertFalse(((SipURI) localName.getAddress()).isSecure());
  }

  @Test
  void testUpdate2() {
    // Arrange
    DialogInfo dialogInfo = new DialogInfo();
    SipProvider sipProvider = mock(SipProvider.class);
    when(sipProvider.getPort()).thenReturn(8080);
    when(sipProvider.getViaAddress()).thenReturn("42 Main St");
    SipMessage sipMessage = mock(SipMessage.class);
    when(sipMessage.getContactHeader()).thenReturn(new ContactHeader());
    when(sipMessage.hasContactHeader()).thenReturn(true);
    when(sipMessage.getCSeqHeader()).thenReturn(new CSeqHeader("42"));
    when(sipMessage.getFromHeader()).thenReturn(new FromHeader(new Header("Hname", "42")));
    when(sipMessage.getToHeader()).thenReturn(new ToHeader(new Header("Hname", "42")));
    when(sipMessage.getCallIdHeader()).thenReturn(new CallIdHeader("42"));

    // Act
    dialogInfo.update(false, sipProvider, sipMessage);

    // Assert
    verify(sipProvider).getPort();
    verify(sipProvider).getViaAddress();
    verify(sipMessage).getCSeqHeader();
    verify(sipMessage).getCallIdHeader();
    verify(sipMessage).getContactHeader();
    verify(sipMessage).getFromHeader();
    verify(sipMessage).getToHeader();
    verify(sipMessage).hasContactHeader();
    assertEquals("42", dialogInfo.getCallID());
    assertNull(dialogInfo.getRemoteTag());
    NameAddress expectedRemoteName = dialogInfo.local_name;
    NameAddress remoteName = dialogInfo.getRemoteName();
    assertEquals(expectedRemoteName, remoteName);
    NameAddress expectedRemoteContact = dialogInfo.remote_contact;
    NameAddress remoteContact = dialogInfo.getRemoteContact();
    assertSame(expectedRemoteContact, remoteContact);
    assertEquals(42L, dialogInfo.getRemoteCSeq());
    NameAddress localName = dialogInfo.getLocalName();
    assertEquals(remoteName, localName);
    assertEquals(0L, dialogInfo.getLocalCSeq());
    assertNull(dialogInfo.getLocalTag());
    assertNull(localName.getDisplayName());
    assertFalse(remoteName.hasDisplayName());
    assertNull(remoteContact.getDisplayName());
    assertEquals("<sip:*>", remoteContact.toString());
    assertEquals("<sip:42>", remoteName.toString());
    assertEquals("<sip:42>", localName.toString());
    assertNull(((SipURI) remoteName.getAddress()).getMaddr());
    assertNull(((SipURI) localName.getAddress()).getUserName());
    assertFalse(((SipURI) localName.getAddress()).isSecure());
  }

  @Test
  void testUpdate3() {
    // Arrange
    DialogInfo dialogInfo = new DialogInfo();
    SipProvider sipProvider = mock(SipProvider.class);
    when(sipProvider.getPort()).thenReturn(8080);
    when(sipProvider.getViaAddress()).thenReturn("42 Main St");
    SipMessage sipMessage = mock(SipMessage.class);
    NameAddress nameAddress = new NameAddress("Str");
    when(sipMessage.getContactHeader()).thenReturn(new ContactHeader(nameAddress));
    when(sipMessage.hasContactHeader()).thenReturn(true);
    when(sipMessage.getCSeqHeader()).thenReturn(new CSeqHeader("42"));
    when(sipMessage.getFromHeader()).thenReturn(new FromHeader(new Header("Hname", "42")));
    when(sipMessage.getToHeader()).thenReturn(new ToHeader(new Header("Hname", "42")));
    when(sipMessage.getCallIdHeader()).thenReturn(new CallIdHeader("42"));

    // Act
    dialogInfo.update(true, sipProvider, sipMessage);

    // Assert
    verify(sipProvider).getPort();
    verify(sipProvider).getViaAddress();
    verify(sipMessage).getCSeqHeader();
    verify(sipMessage).getCallIdHeader();
    verify(sipMessage).getContactHeader();
    verify(sipMessage).getFromHeader();
    verify(sipMessage).getToHeader();
    verify(sipMessage).hasContactHeader();
    assertEquals("42", dialogInfo.getCallID());
    assertNull(dialogInfo.getRemoteTag());
    NameAddress expectedRemoteName = dialogInfo.local_name;
    NameAddress remoteName = dialogInfo.getRemoteName();
    assertEquals(expectedRemoteName, remoteName);
    NameAddress remoteContact = dialogInfo.getRemoteContact();
    assertEquals(nameAddress, remoteContact);
    NameAddress localName = dialogInfo.getLocalName();
    assertEquals(remoteName, localName);
    assertEquals(42L, dialogInfo.getLocalCSeq());
    assertNull(dialogInfo.getLocalTag());
    assertNull(localName.getDisplayName());
    assertFalse(remoteName.hasDisplayName());
    assertNull(remoteContact.getDisplayName());
    assertEquals("<sip:Str>", remoteContact.toString());
    assertEquals("<sip:42>", remoteName.toString());
    assertEquals("<sip:42>", localName.toString());
    assertEquals("sip", remoteContact.getAddress().getScheme());
    assertNull(((SipURI) localName.getAddress()).getUserName());
    assertFalse(((SipURI) localName.getAddress()).isSecure());
  }
}

