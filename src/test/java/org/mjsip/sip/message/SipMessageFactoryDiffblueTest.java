package org.mjsip.sip.message;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.io.UnsupportedEncodingException;
import java.util.Vector;
import org.junit.jupiter.api.Test;
import org.mjsip.sip.address.GenericURI;
import org.mjsip.sip.address.NameAddress;
import org.mjsip.sip.address.UnexpectedUriSchemeException;
import org.mjsip.sip.dialog.DialogInfo;
import org.mjsip.sip.header.CSeqHeader;
import org.mjsip.sip.header.CallIdHeader;
import org.mjsip.sip.header.ContactHeader;
import org.mjsip.sip.header.FromHeader;
import org.mjsip.sip.header.Header;
import org.mjsip.sip.header.MultipleHeader;
import org.mjsip.sip.header.RequestLine;
import org.mjsip.sip.header.ToHeader;

class SipMessageFactoryDiffblueTest {
  @Test
  void testCreateInviteRequest() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");

    // Act
    SipMessage actualCreateInviteRequestResult = SipMessageFactory.createInviteRequest(request_uri, to, from, contact,
        "Call id", "Not all who wander are lost", "AAAAAAAA".getBytes("UTF-8"));

    // Assert
    assertNull(actualCreateInviteRequestResult.getAcceptEncodingHeader());
    assertEquals(0, actualCreateInviteRequestResult.getRemotePort());
    assertNull(actualCreateInviteRequestResult.getRemoteAddress());
    assertEquals(302, actualCreateInviteRequestResult.getLength());
    Vector expectedHeaders = actualCreateInviteRequestResult.headers;
    assertEquals(expectedHeaders, actualCreateInviteRequestResult.getHeaders());
    assertEquals("INVITE Uri SIP/2.0\r\n", actualCreateInviteRequestResult.getFirstLine());
    assertNull(actualCreateInviteRequestResult.getConnectionId());
    assertEquals("Not", actualCreateInviteRequestResult.getBodyType());
    assertEquals(8, actualCreateInviteRequestResult.getBody().length);
  }

  @Test
  void testCreateInviteRequest2() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Display name", new GenericURI("Uri"));

    NameAddress contact = new NameAddress("Str");

    // Act
    SipMessage actualCreateInviteRequestResult = SipMessageFactory.createInviteRequest(request_uri, to, from, contact,
        "Call id", "Not all who wander are lost", "AAAAAAAA".getBytes("UTF-8"));

    // Assert
    assertNull(actualCreateInviteRequestResult.getAcceptEncodingHeader());
    assertEquals(0, actualCreateInviteRequestResult.getRemotePort());
    assertNull(actualCreateInviteRequestResult.getRemoteAddress());
    assertEquals(313, actualCreateInviteRequestResult.getLength());
    Vector expectedHeaders = actualCreateInviteRequestResult.headers;
    assertEquals(expectedHeaders, actualCreateInviteRequestResult.getHeaders());
    assertEquals("INVITE Uri SIP/2.0\r\n", actualCreateInviteRequestResult.getFirstLine());
    assertNull(actualCreateInviteRequestResult.getConnectionId());
    assertEquals("Not", actualCreateInviteRequestResult.getBodyType());
    assertEquals(8, actualCreateInviteRequestResult.getBody().length);
  }

  @Test
  void testCreateInviteRequest3() {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");

    // Act
    SipMessage actualCreateInviteRequestResult = SipMessageFactory.createInviteRequest(request_uri, to, from,
        new NameAddress("Str"), "Call id", "Not all who wander are lost", new byte[]{});

    // Assert
    assertNull(actualCreateInviteRequestResult.getAcceptEncodingHeader());
    assertEquals(0, actualCreateInviteRequestResult.getRemotePort());
    assertNull(actualCreateInviteRequestResult.getRemoteAddress());
    assertEquals(251, actualCreateInviteRequestResult.getLength());
    Vector expectedHeaders = actualCreateInviteRequestResult.headers;
    assertEquals(expectedHeaders, actualCreateInviteRequestResult.getHeaders());
    assertEquals("INVITE Uri SIP/2.0\r\n", actualCreateInviteRequestResult.getFirstLine());
    assertNull(actualCreateInviteRequestResult.getConnectionId());
    assertNull(actualCreateInviteRequestResult.getBody());
  }

  @Test
  void testCreateInviteRequest4() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("\" <", new GenericURI("Uri"));

    NameAddress contact = new NameAddress("Str");

    // Act
    SipMessage actualCreateInviteRequestResult = SipMessageFactory.createInviteRequest(request_uri, to, from, contact,
        "Call id", "Not all who wander are lost", "AAAAAAAA".getBytes("UTF-8"));

    // Assert
    assertNull(actualCreateInviteRequestResult.getAcceptEncodingHeader());
    assertEquals(0, actualCreateInviteRequestResult.getRemotePort());
    assertNull(actualCreateInviteRequestResult.getRemoteAddress());
    assertEquals(304, actualCreateInviteRequestResult.getLength());
    Vector expectedHeaders = actualCreateInviteRequestResult.headers;
    assertEquals(expectedHeaders, actualCreateInviteRequestResult.getHeaders());
    assertEquals("INVITE Uri SIP/2.0\r\n", actualCreateInviteRequestResult.getFirstLine());
    assertNull(actualCreateInviteRequestResult.getConnectionId());
    assertEquals("Not", actualCreateInviteRequestResult.getBodyType());
    assertEquals(8, actualCreateInviteRequestResult.getBody().length);
  }

  @Test
  void testCreateInviteRequest5() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Display name", null);

    NameAddress contact = new NameAddress("Str");

    // Act
    SipMessage actualCreateInviteRequestResult = SipMessageFactory.createInviteRequest(request_uri, to, from, contact,
        "Call id", "Not all who wander are lost", "AAAAAAAA".getBytes("UTF-8"));

    // Assert
    assertNull(actualCreateInviteRequestResult.getAcceptEncodingHeader());
    assertEquals(0, actualCreateInviteRequestResult.getRemotePort());
    assertNull(actualCreateInviteRequestResult.getRemoteAddress());
    assertEquals(314, actualCreateInviteRequestResult.getLength());
    Vector expectedHeaders = actualCreateInviteRequestResult.headers;
    assertEquals(expectedHeaders, actualCreateInviteRequestResult.getHeaders());
    assertEquals("INVITE Uri SIP/2.0\r\n", actualCreateInviteRequestResult.getFirstLine());
    assertNull(actualCreateInviteRequestResult.getConnectionId());
    assertEquals("Not", actualCreateInviteRequestResult.getBodyType());
    assertEquals(8, actualCreateInviteRequestResult.getBody().length);
  }

  @Test
  void testCreate2xxAckRequest() throws UnsupportedEncodingException {
    // Arrange
    RequestLine requestLine = mock(RequestLine.class);
    when(requestLine.getAddress()).thenThrow(new UnexpectedUriSchemeException("Scheme"));

    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.setRequestLine(requestLine);
    SipMessage resp = new SipMessage("Str");

    // Act and Assert
    assertThrows(UnexpectedUriSchemeException.class, () -> SipMessageFactory.create2xxAckRequest(sipMessage, resp,
        "Not all who wander are lost", "AAAAAAAA".getBytes("UTF-8")));
    verify(requestLine).getAddress();
  }

  @Test
  void testCreate2xxAckRequest2() throws UnsupportedEncodingException {
    // Arrange
    RequestLine requestLine = mock(RequestLine.class);
    when(requestLine.getAddress()).thenReturn(new GenericURI("Uri"));

    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.setRequestLine(requestLine);
    SipMessage sipMessage1 = mock(SipMessage.class);
    when(sipMessage1.getToHeader()).thenThrow(new UnexpectedUriSchemeException("Scheme"));
    when(sipMessage1.getContactHeader()).thenReturn(new ContactHeader());
    when(sipMessage1.hasContactHeader()).thenReturn(true);

    // Act and Assert
    assertThrows(UnexpectedUriSchemeException.class, () -> SipMessageFactory.create2xxAckRequest(sipMessage,
        sipMessage1, "Not all who wander are lost", "AAAAAAAA".getBytes("UTF-8")));
    verify(sipMessage1).getContactHeader();
    verify(sipMessage1).getToHeader();
    verify(sipMessage1).hasContactHeader();
  }

  @Test
  void testCreate2xxAckRequest3() throws UnsupportedEncodingException {
    // Arrange
    RequestLine requestLine = mock(RequestLine.class);
    when(requestLine.getAddress()).thenReturn(new GenericURI("Uri"));
    SipMessage sipMessage = mock(SipMessage.class);
    when(sipMessage.getContactHeader()).thenReturn(new ContactHeader());
    when(sipMessage.hasContactHeader()).thenReturn(true);
    when(sipMessage.getCSeqHeader()).thenReturn(new CSeqHeader("42"));
    when(sipMessage.getCallIdHeader()).thenReturn(new CallIdHeader("42"));
    when(sipMessage.getFromHeader()).thenReturn(new FromHeader(new Header("Hname", "42")));
    sipMessage.setRequestLine(requestLine);
    SipMessage sipMessage1 = mock(SipMessage.class);
    when(sipMessage1.getRecordRoutes()).thenReturn(new MultipleHeader("Hname"));
    when(sipMessage1.hasRecordRouteHeader()).thenReturn(true);
    when(sipMessage1.getToHeader()).thenReturn(new ToHeader(new Header("Hname", "42")));
    when(sipMessage1.getContactHeader()).thenReturn(new ContactHeader());
    when(sipMessage1.hasContactHeader()).thenReturn(true);

    // Act
    SipMessage actualCreate2xxAckRequestResult = SipMessageFactory.create2xxAckRequest(sipMessage, sipMessage1,
        "Not all who wander are lost", "AAAAAAAA".getBytes("UTF-8"));

    // Assert
    assertNull(actualCreate2xxAckRequestResult.getAcceptEncodingHeader());
    assertEquals(0, actualCreate2xxAckRequestResult.getRemotePort());
    assertNull(actualCreate2xxAckRequestResult.getRemoteAddress());
    assertEquals(237, actualCreate2xxAckRequestResult.getLength());
    Vector expectedHeaders = actualCreate2xxAckRequestResult.headers;
    assertEquals(expectedHeaders, actualCreate2xxAckRequestResult.getHeaders());
    assertEquals("ACK sip: SIP/2.0\r\n", actualCreate2xxAckRequestResult.getFirstLine());
    assertNull(actualCreate2xxAckRequestResult.getConnectionId());
    assertNull(actualCreate2xxAckRequestResult.getBody());
    verify(sipMessage).getCSeqHeader();
    verify(sipMessage).getCallIdHeader();
    verify(sipMessage).getContactHeader();
    verify(sipMessage).getFromHeader();
    verify(sipMessage).hasContactHeader();
    verify(sipMessage1).getContactHeader();
    verify(sipMessage1).getRecordRoutes();
    verify(sipMessage1).getToHeader();
    verify(sipMessage1).hasContactHeader();
    verify(sipMessage1).hasRecordRouteHeader();
  }

  @Test
  void testCreate2xxAckRequest4() throws UnsupportedEncodingException {
    // Arrange
    RequestLine requestLine = mock(RequestLine.class);
    when(requestLine.getAddress()).thenReturn(new GenericURI("Uri"));
    SipMessage sipMessage = mock(SipMessage.class);
    when(sipMessage.getContactHeader()).thenReturn(mock(ContactHeader.class));
    when(sipMessage.hasContactHeader()).thenReturn(true);
    when(sipMessage.getCSeqHeader()).thenReturn(new CSeqHeader("42"));
    when(sipMessage.getCallIdHeader()).thenReturn(new CallIdHeader("42"));
    when(sipMessage.getFromHeader()).thenReturn(new FromHeader(new Header("Hname", "42")));
    sipMessage.setRequestLine(requestLine);
    SipMessage sipMessage1 = mock(SipMessage.class);
    when(sipMessage1.getRecordRoutes()).thenReturn(new MultipleHeader("Hname"));
    when(sipMessage1.hasRecordRouteHeader()).thenReturn(true);
    when(sipMessage1.getToHeader()).thenReturn(new ToHeader(new Header("Hname", "42")));
    when(sipMessage1.getContactHeader()).thenReturn(new ContactHeader());
    when(sipMessage1.hasContactHeader()).thenReturn(true);

    // Act
    SipMessage actualCreate2xxAckRequestResult = SipMessageFactory.create2xxAckRequest(sipMessage, sipMessage1,
        "Not all who wander are lost", "AAAAAAAA".getBytes("UTF-8"));

    // Assert
    assertNull(actualCreate2xxAckRequestResult.getAcceptEncodingHeader());
    assertEquals(0, actualCreate2xxAckRequestResult.getRemotePort());
    assertNull(actualCreate2xxAckRequestResult.getRemoteAddress());
    assertEquals(219, actualCreate2xxAckRequestResult.getLength());
    Vector expectedHeaders = actualCreate2xxAckRequestResult.headers;
    assertEquals(expectedHeaders, actualCreate2xxAckRequestResult.getHeaders());
    assertEquals("ACK sip: SIP/2.0\r\n", actualCreate2xxAckRequestResult.getFirstLine());
    assertNull(actualCreate2xxAckRequestResult.getConnectionId());
    assertNull(actualCreate2xxAckRequestResult.getBody());
    verify(sipMessage).getCSeqHeader();
    verify(sipMessage).getCallIdHeader();
    verify(sipMessage).getContactHeader();
    verify(sipMessage).getFromHeader();
    verify(sipMessage).hasContactHeader();
    verify(sipMessage1).getContactHeader();
    verify(sipMessage1).getRecordRoutes();
    verify(sipMessage1).getToHeader();
    verify(sipMessage1).hasContactHeader();
    verify(sipMessage1).hasRecordRouteHeader();
  }

  @Test
  void testCreate2xxAckRequest5() throws UnsupportedEncodingException {
    // Arrange
    RequestLine requestLine = mock(RequestLine.class);
    when(requestLine.getAddress()).thenReturn(new GenericURI("Uri"));
    CSeqHeader cSeqHeader = mock(CSeqHeader.class);
    when(cSeqHeader.getSequenceNumber()).thenThrow(new UnexpectedUriSchemeException("Scheme"));
    SipMessage sipMessage = mock(SipMessage.class);
    when(sipMessage.getContactHeader()).thenReturn(null);
    when(sipMessage.hasContactHeader()).thenReturn(true);
    when(sipMessage.getCSeqHeader()).thenReturn(cSeqHeader);
    when(sipMessage.getCallIdHeader()).thenReturn(new CallIdHeader("42"));
    when(sipMessage.getFromHeader()).thenReturn(new FromHeader(new Header("Hname", "42")));
    sipMessage.setRequestLine(requestLine);
    SipMessage sipMessage1 = mock(SipMessage.class);
    when(sipMessage1.getRecordRoutes()).thenReturn(new MultipleHeader("Hname"));
    when(sipMessage1.hasRecordRouteHeader()).thenReturn(true);
    when(sipMessage1.getToHeader()).thenReturn(new ToHeader(new Header("Hname", "42")));
    when(sipMessage1.getContactHeader()).thenReturn(new ContactHeader());
    when(sipMessage1.hasContactHeader()).thenReturn(true);

    // Act and Assert
    assertThrows(UnexpectedUriSchemeException.class, () -> SipMessageFactory.create2xxAckRequest(sipMessage,
        sipMessage1, "Not all who wander are lost", "AAAAAAAA".getBytes("UTF-8")));
    verify(sipMessage).getCSeqHeader();
    verify(sipMessage).getCallIdHeader();
    verify(sipMessage).getFromHeader();
    verify(cSeqHeader).getSequenceNumber();
    verify(sipMessage1).getContactHeader();
    verify(sipMessage1).getToHeader();
    verify(sipMessage1).hasContactHeader();
  }

  @Test
  void testCreate2xxAckRequest6() throws UnsupportedEncodingException {
    // Arrange
    RequestLine requestLine = mock(RequestLine.class);
    when(requestLine.getAddress()).thenReturn(new GenericURI("Uri"));
    CallIdHeader callIdHeader = mock(CallIdHeader.class);
    when(callIdHeader.getCallId()).thenReturn("42");
    CSeqHeader cSeqHeader = mock(CSeqHeader.class);
    when(cSeqHeader.getSequenceNumber()).thenThrow(new UnexpectedUriSchemeException("Scheme"));
    SipMessage sipMessage = mock(SipMessage.class);
    when(sipMessage.getContactHeader()).thenReturn(null);
    when(sipMessage.hasContactHeader()).thenReturn(true);
    when(sipMessage.getCSeqHeader()).thenReturn(cSeqHeader);
    when(sipMessage.getCallIdHeader()).thenReturn(callIdHeader);
    when(sipMessage.getFromHeader()).thenReturn(new FromHeader(new Header("Hname", "42")));
    sipMessage.setRequestLine(requestLine);
    SipMessage sipMessage1 = mock(SipMessage.class);
    when(sipMessage1.getRecordRoutes()).thenReturn(new MultipleHeader("Hname"));
    when(sipMessage1.hasRecordRouteHeader()).thenReturn(true);
    when(sipMessage1.getToHeader()).thenReturn(new ToHeader(new Header("Hname", "42")));
    when(sipMessage1.getContactHeader()).thenReturn(new ContactHeader());
    when(sipMessage1.hasContactHeader()).thenReturn(true);

    // Act and Assert
    assertThrows(UnexpectedUriSchemeException.class, () -> SipMessageFactory.create2xxAckRequest(sipMessage,
        sipMessage1, "Not all who wander are lost", "AAAAAAAA".getBytes("UTF-8")));
    verify(sipMessage).getCSeqHeader();
    verify(sipMessage).getCallIdHeader();
    verify(sipMessage).getFromHeader();
    verify(cSeqHeader).getSequenceNumber();
    verify(callIdHeader).getCallId();
    verify(sipMessage1).getContactHeader();
    verify(sipMessage1).getToHeader();
    verify(sipMessage1).hasContactHeader();
  }

  @Test
  void testCreate2xxAckRequest7() throws UnsupportedEncodingException {
    // Arrange
    RequestLine requestLine = mock(RequestLine.class);
    when(requestLine.getAddress()).thenReturn(new GenericURI("Uri"));
    CallIdHeader callIdHeader = mock(CallIdHeader.class);
    when(callIdHeader.getCallId()).thenReturn("42");
    CSeqHeader cSeqHeader = mock(CSeqHeader.class);
    when(cSeqHeader.getSequenceNumber()).thenThrow(new UnexpectedUriSchemeException("Scheme"));
    SipMessage sipMessage = mock(SipMessage.class);
    when(sipMessage.getContactHeader()).thenReturn(null);
    when(sipMessage.hasContactHeader()).thenReturn(true);
    when(sipMessage.getCSeqHeader()).thenReturn(cSeqHeader);
    when(sipMessage.getCallIdHeader()).thenReturn(callIdHeader);
    when(sipMessage.getFromHeader()).thenReturn(new FromHeader(new Header("Hname", "42")));
    sipMessage.setRequestLine(requestLine);
    SipMessage sipMessage1 = mock(SipMessage.class);
    when(sipMessage1.getRecordRoutes()).thenReturn(new MultipleHeader("Hname"));
    when(sipMessage1.hasRecordRouteHeader()).thenReturn(true);
    when(sipMessage1.getToHeader()).thenReturn(new ToHeader(new Header("Hname", "42")));
    when(sipMessage1.getContactHeader()).thenReturn(new ContactHeader(new GenericURI("Uri")));
    when(sipMessage1.hasContactHeader()).thenReturn(true);

    // Act and Assert
    assertThrows(UnexpectedUriSchemeException.class, () -> SipMessageFactory.create2xxAckRequest(sipMessage,
        sipMessage1, "Not all who wander are lost", "AAAAAAAA".getBytes("UTF-8")));
    verify(sipMessage).getCSeqHeader();
    verify(sipMessage).getCallIdHeader();
    verify(sipMessage).getFromHeader();
    verify(cSeqHeader).getSequenceNumber();
    verify(callIdHeader).getCallId();
    verify(sipMessage1).getContactHeader();
    verify(sipMessage1).getToHeader();
    verify(sipMessage1).hasContactHeader();
  }

  @Test
  void testCreateNon2xxAckRequest() throws UnsupportedEncodingException {
    // Arrange
    DialogInfo dialog = new DialogInfo();
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");

    // Act
    SipMessage actualCreateNon2xxAckRequestResult = SipMessageFactory.createNon2xxAckRequest(dialog,
        BasicSipMessageFactory.createRequest("Method", request_uri, to, from, "Call id", 1L, "Local tag", "Remote tag",
            contact, "Not all who wander are lost", "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8")));

    // Assert
    assertNull(actualCreateNon2xxAckRequestResult.getAcceptEncodingHeader());
    assertEquals(0, actualCreateNon2xxAckRequestResult.getRemotePort());
    assertNull(actualCreateNon2xxAckRequestResult.getRemoteAddress());
    assertEquals(266, actualCreateNon2xxAckRequestResult.getLength());
    assertEquals("ACK sip:Str SIP/2.0\r\n", actualCreateNon2xxAckRequestResult.getFirstLine());
    assertNull(actualCreateNon2xxAckRequestResult.getConnectionId());
    assertNull(actualCreateNon2xxAckRequestResult.getBody());
  }

  @Test
  void testCreateNon2xxAckRequest2() {
    // Arrange
    RequestLine requestLine = mock(RequestLine.class);
    when(requestLine.getAddress()).thenThrow(new UnexpectedUriSchemeException("Scheme"));

    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.setRequestLine(requestLine);

    // Act and Assert
    assertThrows(UnexpectedUriSchemeException.class,
        () -> SipMessageFactory.createNon2xxAckRequest(sipMessage, new SipMessage("Str")));
    verify(requestLine).getAddress();
  }

  @Test
  void testCreateNon2xxAckRequest3() {
    // Arrange
    RequestLine requestLine = mock(RequestLine.class);
    when(requestLine.getAddress()).thenReturn(new GenericURI("Uri"));

    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.setRequestLine(requestLine);
    SipMessage sipMessage1 = mock(SipMessage.class);
    when(sipMessage1.getToHeader()).thenThrow(new UnexpectedUriSchemeException("Scheme"));

    // Act and Assert
    assertThrows(UnexpectedUriSchemeException.class,
        () -> SipMessageFactory.createNon2xxAckRequest(sipMessage, sipMessage1));
    verify(requestLine).getAddress();
    verify(sipMessage1).getToHeader();
  }

  @Test
  void testCreateNon2xxAckRequest4() throws UnsupportedEncodingException {
    // Arrange
    RequestLine requestLine = mock(RequestLine.class);
    when(requestLine.getAddress()).thenReturn(new GenericURI("Uri"));
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");
    SipMessage createRequestResult = BasicSipMessageFactory.createRequest("Method", request_uri, to, from, "Call id",
        1L, "Local tag", "Remote tag", contact, "Not all who wander are lost",
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));
    createRequestResult.setRequestLine(requestLine);
    SipMessage sipMessage = mock(SipMessage.class);
    when(sipMessage.getViaHeader()).thenThrow(new UnexpectedUriSchemeException("Scheme"));
    when(sipMessage.getToHeader()).thenReturn(new ToHeader(new Header("Hname", "42")));

    // Act and Assert
    assertThrows(UnexpectedUriSchemeException.class,
        () -> SipMessageFactory.createNon2xxAckRequest(createRequestResult, sipMessage));
    verify(requestLine).getAddress();
    verify(sipMessage).getToHeader();
    verify(sipMessage).getViaHeader();
  }

  @Test
  void testCreateCancelRequest() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");
    SipMessage createRequestResult = BasicSipMessageFactory.createRequest("Method", request_uri, to, from, "Call id",
        1L, "Local tag", "Remote tag", contact, "Not all who wander are lost",
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));
    createRequestResult.setRequestLine(new RequestLine("Method", new GenericURI("Uri")));

    // Act
    SipMessage actualCreateCancelRequestResult = SipMessageFactory.createCancelRequest(createRequestResult);

    // Assert
    assertNull(actualCreateCancelRequestResult.getAcceptEncodingHeader());
    assertEquals(0, actualCreateCancelRequestResult.getRemotePort());
    assertNull(actualCreateCancelRequestResult.getRemoteAddress());
    assertEquals(303, actualCreateCancelRequestResult.getLength());
    Vector expectedHeaders = actualCreateCancelRequestResult.headers;
    assertEquals(expectedHeaders, actualCreateCancelRequestResult.getHeaders());
    assertEquals("CANCEL Uri SIP/2.0\r\n", actualCreateCancelRequestResult.getFirstLine());
    assertNull(actualCreateCancelRequestResult.getConnectionId());
    assertNull(actualCreateCancelRequestResult.getBody());
  }

  @Test
  void testCreateByeRequest() {
    // Arrange
    DialogInfo dialogInfo = mock(DialogInfo.class);
    when(dialogInfo.getRoute()).thenReturn(new Vector(1));
    when(dialogInfo.getRemoteTag()).thenReturn("Remote Tag");
    when(dialogInfo.getLocalTag()).thenReturn("Local Tag");
    when(dialogInfo.getLocalCSeq()).thenReturn(1L);
    when(dialogInfo.getCallID()).thenReturn("Call ID");
    doNothing().when(dialogInfo).incLocalCSeq();
    when(dialogInfo.getLocalContact()).thenReturn(new NameAddress("Str"));
    when(dialogInfo.getRemoteContact()).thenReturn(new NameAddress("Str"));
    when(dialogInfo.getLocalName()).thenReturn(new NameAddress("Str"));
    when(dialogInfo.getRemoteName()).thenReturn(new NameAddress("Str"));

    // Act
    SipMessage actualCreateByeRequestResult = SipMessageFactory.createByeRequest(dialogInfo);

    // Assert
    assertNull(actualCreateByeRequestResult.getAcceptEncodingHeader());
    assertEquals(0, actualCreateByeRequestResult.getRemotePort());
    assertNull(actualCreateByeRequestResult.getRemoteAddress());
    assertEquals(226, actualCreateByeRequestResult.getLength());
    Vector expectedHeaders = actualCreateByeRequestResult.headers;
    assertEquals(expectedHeaders, actualCreateByeRequestResult.getHeaders());
    assertEquals("BYE sip:Str SIP/2.0\r\n", actualCreateByeRequestResult.getFirstLine());
    assertNull(actualCreateByeRequestResult.getConnectionId());
    assertNull(actualCreateByeRequestResult.getBody());
    verify(dialogInfo).getCallID();
    verify(dialogInfo).getLocalCSeq();
    verify(dialogInfo).getLocalContact();
    verify(dialogInfo).getLocalName();
    verify(dialogInfo).getLocalTag();
    verify(dialogInfo).getRemoteContact();
    verify(dialogInfo).getRemoteName();
    verify(dialogInfo).getRemoteTag();
    verify(dialogInfo).getRoute();
    verify(dialogInfo).incLocalCSeq();
  }

  @Test
  void testCreateByeRequest2() {
    // Arrange
    Vector vector = new Vector(1);
    vector.add("42");
    DialogInfo dialogInfo = mock(DialogInfo.class);
    when(dialogInfo.getRoute()).thenReturn(vector);
    when(dialogInfo.getRemoteTag()).thenReturn("Remote Tag");
    when(dialogInfo.getLocalTag()).thenReturn("Local Tag");
    when(dialogInfo.getLocalCSeq()).thenReturn(1L);
    when(dialogInfo.getCallID()).thenReturn("Call ID");
    doNothing().when(dialogInfo).incLocalCSeq();
    when(dialogInfo.getLocalContact()).thenReturn(new NameAddress("Str"));
    when(dialogInfo.getRemoteContact()).thenReturn(new NameAddress("Str"));
    when(dialogInfo.getLocalName()).thenReturn(new NameAddress("Str"));
    when(dialogInfo.getRemoteName()).thenReturn(new NameAddress("Str"));

    // Act
    SipMessage actualCreateByeRequestResult = SipMessageFactory.createByeRequest(dialogInfo);

    // Assert
    assertNull(actualCreateByeRequestResult.getAcceptEncodingHeader());
    assertEquals(0, actualCreateByeRequestResult.getRemotePort());
    assertNull(actualCreateByeRequestResult.getRemoteAddress());
    assertEquals(243, actualCreateByeRequestResult.getLength());
    Vector expectedHeaders = actualCreateByeRequestResult.headers;
    assertEquals(expectedHeaders, actualCreateByeRequestResult.getHeaders());
    assertEquals("BYE sip:42 SIP/2.0\r\n", actualCreateByeRequestResult.getFirstLine());
    assertNull(actualCreateByeRequestResult.getConnectionId());
    assertNull(actualCreateByeRequestResult.getBody());
    verify(dialogInfo).getCallID();
    verify(dialogInfo).getLocalCSeq();
    verify(dialogInfo).getLocalContact();
    verify(dialogInfo).getLocalName();
    verify(dialogInfo).getLocalTag();
    verify(dialogInfo).getRemoteContact();
    verify(dialogInfo).getRemoteName();
    verify(dialogInfo).getRemoteTag();
    verify(dialogInfo).getRoute();
    verify(dialogInfo).incLocalCSeq();
  }

  @Test
  void testCreateByeRequest3() {
    // Arrange
    Vector vector = new Vector(1);
    vector.add("42");
    vector.add("42");
    DialogInfo dialogInfo = mock(DialogInfo.class);
    when(dialogInfo.getRoute()).thenReturn(vector);
    when(dialogInfo.getRemoteTag()).thenReturn("Remote Tag");
    when(dialogInfo.getLocalTag()).thenReturn("Local Tag");
    when(dialogInfo.getLocalCSeq()).thenReturn(1L);
    when(dialogInfo.getCallID()).thenReturn("Call ID");
    doNothing().when(dialogInfo).incLocalCSeq();
    when(dialogInfo.getLocalContact()).thenReturn(new NameAddress("Str"));
    when(dialogInfo.getRemoteContact()).thenReturn(new NameAddress("Str"));
    when(dialogInfo.getLocalName()).thenReturn(new NameAddress("Str"));
    when(dialogInfo.getRemoteName()).thenReturn(new NameAddress("Str"));

    // Act
    SipMessage actualCreateByeRequestResult = SipMessageFactory.createByeRequest(dialogInfo);

    // Assert
    assertNull(actualCreateByeRequestResult.getAcceptEncodingHeader());
    assertEquals(0, actualCreateByeRequestResult.getRemotePort());
    assertNull(actualCreateByeRequestResult.getRemoteAddress());
    assertEquals(254, actualCreateByeRequestResult.getLength());
    Vector expectedHeaders = actualCreateByeRequestResult.headers;
    assertEquals(expectedHeaders, actualCreateByeRequestResult.getHeaders());
    assertEquals("BYE sip:42 SIP/2.0\r\n", actualCreateByeRequestResult.getFirstLine());
    assertNull(actualCreateByeRequestResult.getConnectionId());
    assertNull(actualCreateByeRequestResult.getBody());
    verify(dialogInfo).getCallID();
    verify(dialogInfo).getLocalCSeq();
    verify(dialogInfo).getLocalContact();
    verify(dialogInfo).getLocalName();
    verify(dialogInfo).getLocalTag();
    verify(dialogInfo).getRemoteContact();
    verify(dialogInfo).getRemoteName();
    verify(dialogInfo).getRemoteTag();
    verify(dialogInfo).getRoute();
    verify(dialogInfo).incLocalCSeq();
  }

  @Test
  void testCreateRegisterRequest() {
    // Arrange
    GenericURI registrar = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");

    // Act
    SipMessage actualCreateRegisterRequestResult = SipMessageFactory.createRegisterRequest(registrar, to, from,
        new NameAddress("Str"), "Call id");

    // Assert
    assertNull(actualCreateRegisterRequestResult.getAcceptEncodingHeader());
    assertEquals(0, actualCreateRegisterRequestResult.getRemotePort());
    assertNull(actualCreateRegisterRequestResult.getRemoteAddress());
    assertEquals(255, actualCreateRegisterRequestResult.getLength());
    Vector expectedHeaders = actualCreateRegisterRequestResult.headers;
    assertEquals(expectedHeaders, actualCreateRegisterRequestResult.getHeaders());
    assertEquals("REGISTER Uri SIP/2.0\r\n", actualCreateRegisterRequestResult.getFirstLine());
    assertNull(actualCreateRegisterRequestResult.getConnectionId());
    assertNull(actualCreateRegisterRequestResult.getBody());
  }

  @Test
  void testCreateRegisterRequest2() {
    // Arrange
    NameAddress to = new NameAddress("Str");

    // Act
    SipMessage actualCreateRegisterRequestResult = SipMessageFactory.createRegisterRequest(null, to,
        new NameAddress("Str"), null, "foo");

    // Assert
    assertNull(actualCreateRegisterRequestResult.getAcceptEncodingHeader());
    assertEquals(0, actualCreateRegisterRequestResult.getRemotePort());
    assertNull(actualCreateRegisterRequestResult.getRemoteAddress());
    assertEquals(247, actualCreateRegisterRequestResult.getLength());
    Vector expectedHeaders = actualCreateRegisterRequestResult.headers;
    assertEquals(expectedHeaders, actualCreateRegisterRequestResult.getHeaders());
    assertEquals("REGISTER sip:Str SIP/2.0\r\n", actualCreateRegisterRequestResult.getFirstLine());
    assertNull(actualCreateRegisterRequestResult.getConnectionId());
    assertNull(actualCreateRegisterRequestResult.getBody());
  }

  @Test
  void testCreateRegisterRequest3() {
    // Arrange
    GenericURI registrar = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Display name", new GenericURI("Uri"));

    // Act
    SipMessage actualCreateRegisterRequestResult = SipMessageFactory.createRegisterRequest(registrar, to, from,
        new NameAddress("Str"), "Call id");

    // Assert
    assertNull(actualCreateRegisterRequestResult.getAcceptEncodingHeader());
    assertEquals(0, actualCreateRegisterRequestResult.getRemotePort());
    assertNull(actualCreateRegisterRequestResult.getRemoteAddress());
    assertEquals(266, actualCreateRegisterRequestResult.getLength());
    Vector expectedHeaders = actualCreateRegisterRequestResult.headers;
    assertEquals(expectedHeaders, actualCreateRegisterRequestResult.getHeaders());
    assertEquals("REGISTER Uri SIP/2.0\r\n", actualCreateRegisterRequestResult.getFirstLine());
    assertNull(actualCreateRegisterRequestResult.getConnectionId());
    assertNull(actualCreateRegisterRequestResult.getBody());
  }

  @Test
  void testCreateRegisterRequest4() {
    // Arrange
    GenericURI registrar = new GenericURI("Uri");

    NameAddress nameAddress = new NameAddress("Str");
    nameAddress.setAddress(null);
    NameAddress from = new NameAddress("Str");

    // Act
    SipMessage actualCreateRegisterRequestResult = SipMessageFactory.createRegisterRequest(registrar, nameAddress, from,
        new NameAddress("Str"), "Call id");

    // Assert
    assertNull(actualCreateRegisterRequestResult.getAcceptEncodingHeader());
    assertEquals(0, actualCreateRegisterRequestResult.getRemotePort());
    assertNull(actualCreateRegisterRequestResult.getRemoteAddress());
    assertEquals(252, actualCreateRegisterRequestResult.getLength());
    Vector expectedHeaders = actualCreateRegisterRequestResult.headers;
    assertEquals(expectedHeaders, actualCreateRegisterRequestResult.getHeaders());
    assertEquals("REGISTER Uri SIP/2.0\r\n", actualCreateRegisterRequestResult.getFirstLine());
    assertNull(actualCreateRegisterRequestResult.getConnectionId());
    assertNull(actualCreateRegisterRequestResult.getBody());
  }

  @Test
  void testCreateRegisterRequest5() {
    // Arrange
    GenericURI registrar = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("\" <", new GenericURI("Uri"));

    // Act
    SipMessage actualCreateRegisterRequestResult = SipMessageFactory.createRegisterRequest(registrar, to, from,
        new NameAddress("Str"), "Call id");

    // Assert
    assertNull(actualCreateRegisterRequestResult.getAcceptEncodingHeader());
    assertEquals(0, actualCreateRegisterRequestResult.getRemotePort());
    assertNull(actualCreateRegisterRequestResult.getRemoteAddress());
    assertEquals(257, actualCreateRegisterRequestResult.getLength());
    Vector expectedHeaders = actualCreateRegisterRequestResult.headers;
    assertEquals(expectedHeaders, actualCreateRegisterRequestResult.getHeaders());
    assertEquals("REGISTER Uri SIP/2.0\r\n", actualCreateRegisterRequestResult.getFirstLine());
    assertNull(actualCreateRegisterRequestResult.getConnectionId());
    assertNull(actualCreateRegisterRequestResult.getBody());
  }

  @Test
  void testCreateMessageRequest() throws UnsupportedEncodingException {
    // Arrange
    NameAddress recipient = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");

    // Act
    SipMessage actualCreateMessageRequestResult = SipMessageFactory.createMessageRequest(recipient, from, "Call id",
        "Hello from the Dreaming Spires", "Not all who wander are lost", "AAAAAAAA".getBytes("UTF-8"));

    // Assert
    assertNull(actualCreateMessageRequestResult.getAcceptEncodingHeader());
    assertEquals(0, actualCreateMessageRequestResult.getRemotePort());
    assertNull(actualCreateMessageRequestResult.getRemoteAddress());
    assertEquals(329, actualCreateMessageRequestResult.getLength());
    Vector expectedHeaders = actualCreateMessageRequestResult.headers;
    assertEquals(expectedHeaders, actualCreateMessageRequestResult.getHeaders());
    assertEquals("MESSAGE sip:Str SIP/2.0\r\n", actualCreateMessageRequestResult.getFirstLine());
    assertNull(actualCreateMessageRequestResult.getConnectionId());
    assertEquals("Not", actualCreateMessageRequestResult.getBodyType());
    assertEquals(8, actualCreateMessageRequestResult.getBody().length);
  }

  @Test
  void testCreateMessageRequest2() {
    // Arrange
    NameAddress recipient = new NameAddress("Str");

    // Act
    SipMessage actualCreateMessageRequestResult = SipMessageFactory.createMessageRequest(recipient,
        new NameAddress("Str"), "foo", null, "foo", new byte[]{'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A'});

    // Assert
    assertNull(actualCreateMessageRequestResult.getAcceptEncodingHeader());
    assertEquals(0, actualCreateMessageRequestResult.getRemotePort());
    assertNull(actualCreateMessageRequestResult.getRemoteAddress());
    assertEquals(260, actualCreateMessageRequestResult.getLength());
    Vector expectedHeaders = actualCreateMessageRequestResult.headers;
    assertEquals(expectedHeaders, actualCreateMessageRequestResult.getHeaders());
    assertEquals("MESSAGE sip:Str SIP/2.0\r\n", actualCreateMessageRequestResult.getFirstLine());
    assertNull(actualCreateMessageRequestResult.getConnectionId());
    assertEquals("foo", actualCreateMessageRequestResult.getBodyType());
    assertEquals(8, actualCreateMessageRequestResult.getBody().length);
  }

  @Test
  void testCreateReferRequest() {
    // Arrange
    NameAddress recipient = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");

    // Act
    SipMessage actualCreateReferRequestResult = SipMessageFactory.createReferRequest(recipient, from, contact,
        "Call id", new NameAddress("Str"));

    // Assert
    assertNull(actualCreateReferRequestResult.getAcceptEncodingHeader());
    assertEquals(0, actualCreateReferRequestResult.getRemotePort());
    assertNull(actualCreateReferRequestResult.getRemoteAddress());
    assertEquals(298, actualCreateReferRequestResult.getLength());
    Vector expectedHeaders = actualCreateReferRequestResult.headers;
    assertEquals(expectedHeaders, actualCreateReferRequestResult.getHeaders());
    assertEquals("REFER sip:Str SIP/2.0\r\n", actualCreateReferRequestResult.getFirstLine());
    assertNull(actualCreateReferRequestResult.getConnectionId());
    assertNull(actualCreateReferRequestResult.getBody());
  }

  @Test
  void testCreateReferRequest2() {
    // Arrange
    NameAddress recipient = new NameAddress("Str");
    NameAddress from = new NameAddress("Display name", new GenericURI("Uri"));

    NameAddress contact = new NameAddress("Str");

    // Act
    SipMessage actualCreateReferRequestResult = SipMessageFactory.createReferRequest(recipient, from, contact,
        "Call id", new NameAddress("Str"));

    // Assert
    assertNull(actualCreateReferRequestResult.getAcceptEncodingHeader());
    assertEquals(0, actualCreateReferRequestResult.getRemotePort());
    assertNull(actualCreateReferRequestResult.getRemoteAddress());
    assertEquals(320, actualCreateReferRequestResult.getLength());
    Vector expectedHeaders = actualCreateReferRequestResult.headers;
    assertEquals(expectedHeaders, actualCreateReferRequestResult.getHeaders());
    assertEquals("REFER sip:Str SIP/2.0\r\n", actualCreateReferRequestResult.getFirstLine());
    assertNull(actualCreateReferRequestResult.getConnectionId());
    assertNull(actualCreateReferRequestResult.getBody());
  }

  @Test
  void testCreateSubscribeRequest() throws UnsupportedEncodingException {
    // Arrange
    GenericURI recipient = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");

    // Act
    SipMessage actualCreateSubscribeRequestResult = SipMessageFactory.createSubscribeRequest(recipient, to, from,
        contact, "Call id", "Event", "42", "Not all who wander are lost", "AAAAAAAA".getBytes("UTF-8"));

    // Assert
    assertNull(actualCreateSubscribeRequestResult.getAcceptEncodingHeader());
    assertEquals(0, actualCreateSubscribeRequestResult.getRemotePort());
    assertNull(actualCreateSubscribeRequestResult.getRemoteAddress());
    assertEquals(328, actualCreateSubscribeRequestResult.getLength());
    Vector expectedHeaders = actualCreateSubscribeRequestResult.headers;
    assertEquals(expectedHeaders, actualCreateSubscribeRequestResult.getHeaders());
    assertEquals("SUBSCRIBE Uri SIP/2.0\r\n", actualCreateSubscribeRequestResult.getFirstLine());
    assertNull(actualCreateSubscribeRequestResult.getConnectionId());
    assertEquals("Not", actualCreateSubscribeRequestResult.getBodyType());
    assertEquals(8, actualCreateSubscribeRequestResult.getBody().length);
  }

  @Test
  void testCreateSubscribeRequest2() throws UnsupportedEncodingException {
    // Arrange
    GenericURI recipient = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Display name", new GenericURI("Uri"));

    NameAddress contact = new NameAddress("Str");

    // Act
    SipMessage actualCreateSubscribeRequestResult = SipMessageFactory.createSubscribeRequest(recipient, to, from,
        contact, "Call id", "Event", "42", "Not all who wander are lost", "AAAAAAAA".getBytes("UTF-8"));

    // Assert
    assertNull(actualCreateSubscribeRequestResult.getAcceptEncodingHeader());
    assertEquals(0, actualCreateSubscribeRequestResult.getRemotePort());
    assertNull(actualCreateSubscribeRequestResult.getRemoteAddress());
    assertEquals(339, actualCreateSubscribeRequestResult.getLength());
    Vector expectedHeaders = actualCreateSubscribeRequestResult.headers;
    assertEquals(expectedHeaders, actualCreateSubscribeRequestResult.getHeaders());
    assertEquals("SUBSCRIBE Uri SIP/2.0\r\n", actualCreateSubscribeRequestResult.getFirstLine());
    assertNull(actualCreateSubscribeRequestResult.getConnectionId());
    assertEquals("Not", actualCreateSubscribeRequestResult.getBodyType());
    assertEquals(8, actualCreateSubscribeRequestResult.getBody().length);
  }

  @Test
  void testCreateSubscribeRequest3() {
    // Arrange
    GenericURI recipient = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");

    // Act
    SipMessage actualCreateSubscribeRequestResult = SipMessageFactory.createSubscribeRequest(recipient, to, from,
        new NameAddress("Str"), "Call id", "Event", "42", "Not all who wander are lost", new byte[]{});

    // Assert
    assertNull(actualCreateSubscribeRequestResult.getAcceptEncodingHeader());
    assertEquals(0, actualCreateSubscribeRequestResult.getRemotePort());
    assertNull(actualCreateSubscribeRequestResult.getRemoteAddress());
    assertEquals(277, actualCreateSubscribeRequestResult.getLength());
    Vector expectedHeaders = actualCreateSubscribeRequestResult.headers;
    assertEquals(expectedHeaders, actualCreateSubscribeRequestResult.getHeaders());
    assertEquals("SUBSCRIBE Uri SIP/2.0\r\n", actualCreateSubscribeRequestResult.getFirstLine());
    assertNull(actualCreateSubscribeRequestResult.getConnectionId());
    assertNull(actualCreateSubscribeRequestResult.getBody());
  }

  @Test
  void testConstructor() {
    // Arrange and Act
    new SipMessageFactory();

    // Assert
    assertEquals("0.0.0.0", BasicSipMessageFactory.DEFAULT_VIA_ADDRESS);
  }
}

