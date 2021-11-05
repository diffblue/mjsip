package org.mjsip.sip.provider;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.mjsip.sip.address.NameAddress;
import org.mjsip.sip.address.SipURI;
import org.mjsip.sip.header.CallIdHeader;
import org.mjsip.sip.header.FromHeader;
import org.mjsip.sip.header.Header;
import org.mjsip.sip.header.ToHeader;
import org.mjsip.sip.message.SipMessage;

class DialogIdDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange
    DialogId dialogId = new DialogId("Call id", "Local tag", "Remote tag");

    // Act and Assert
    assertEquals("Call id-Local tag-Remote tag", (new DialogId(dialogId)).toString());
    assertEquals("Call id-Local tag-Remote tag", dialogId.toString());
  }

  @Test
  void testConstructor2() {
    // Arrange, Act and Assert
    assertEquals("Call id-Local tag-Remote tag", (new DialogId("Call id", "Local tag", "Remote tag")).toString());
  }

  @Test
  void testConstructor3() {
    // Arrange
    SipMessage sipMessage = mock(SipMessage.class);
    when(sipMessage.getToHeader()).thenReturn(new ToHeader(new Header("Hname", "42")));
    when(sipMessage.getFromHeader()).thenReturn(new FromHeader(new Header("Hname", "42")));
    when(sipMessage.getCallIdHeader()).thenReturn(new CallIdHeader("42"));

    // Act and Assert
    assertEquals("42-null-null", (new DialogId(sipMessage)).toString());
    verify(sipMessage).getCallIdHeader();
    verify(sipMessage).getFromHeader();
    verify(sipMessage).getToHeader();
  }

  @Test
  void testConstructor4() {
    // Arrange
    SipMessage sipMessage = mock(SipMessage.class);
    when(sipMessage.getToHeader()).thenReturn(new ToHeader(new NameAddress("Str")));
    when(sipMessage.getFromHeader()).thenReturn(new FromHeader(new Header("Hname", "42")));
    when(sipMessage.getCallIdHeader()).thenReturn(new CallIdHeader("42"));

    // Act and Assert
    assertEquals("42-null-null", (new DialogId(sipMessage)).toString());
    verify(sipMessage).getCallIdHeader();
    verify(sipMessage).getFromHeader();
    verify(sipMessage).getToHeader();
  }

  @Test
  void testConstructor5() {
    // Arrange
    SipMessage sipMessage = mock(SipMessage.class);
    when(sipMessage.getToHeader()).thenReturn(mock(ToHeader.class));
    when(sipMessage.getFromHeader()).thenReturn(new FromHeader(new Header("Hname", "42")));
    when(sipMessage.getCallIdHeader()).thenReturn(new CallIdHeader("42"));

    // Act and Assert
    assertEquals("42-null-null", (new DialogId(sipMessage)).toString());
    verify(sipMessage).getCallIdHeader();
    verify(sipMessage).getFromHeader();
    verify(sipMessage).getToHeader();
  }

  @Test
  void testConstructor6() {
    // Arrange
    SipMessage sipMessage = mock(SipMessage.class);
    when(sipMessage.getToHeader()).thenReturn(new ToHeader(new SipURI("Uri"), "Tag"));
    when(sipMessage.getFromHeader()).thenReturn(new FromHeader(new Header("Hname", "42")));
    when(sipMessage.getCallIdHeader()).thenReturn(new CallIdHeader("42"));

    // Act and Assert
    assertEquals("42-null-Tag", (new DialogId(sipMessage)).toString());
    verify(sipMessage).getCallIdHeader();
    verify(sipMessage).getFromHeader();
    verify(sipMessage).getToHeader();
  }
}

