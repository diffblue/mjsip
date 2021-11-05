package org.mjsip.sip.message;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.io.UnsupportedEncodingException;
import java.util.Vector;
import org.junit.jupiter.api.Test;
import org.mjsip.sip.address.GenericURI;
import org.mjsip.sip.address.NameAddress;
import org.mjsip.sip.dialog.DialogInfo;

class BasicSipMessageFactoryDiffblueTest {
  @Test
  void testCreateRequest() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");

    // Act
    SipMessage actualCreateRequestResult = BasicSipMessageFactory.createRequest("Method", request_uri, to, from,
        "Call id", 1L, "Local tag", "Remote tag", contact, "Not all who wander are lost", "AAAAAAAA".getBytes("UTF-8"));

    // Assert
    assertNull(actualCreateRequestResult.getAcceptEncodingHeader());
    assertEquals(0, actualCreateRequestResult.getRemotePort());
    assertNull(actualCreateRequestResult.getRemoteAddress());
    assertEquals(314, actualCreateRequestResult.getLength());
    Vector expectedHeaders = actualCreateRequestResult.headers;
    assertEquals(expectedHeaders, actualCreateRequestResult.getHeaders());
    assertEquals("Method Uri SIP/2.0\r\n", actualCreateRequestResult.getFirstLine());
    assertNull(actualCreateRequestResult.getConnectionId());
    assertEquals("Not", actualCreateRequestResult.getBodyType());
    assertEquals(8, actualCreateRequestResult.getBody().length);
  }

  @Test
  void testCreateRequest2() {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");

    // Act
    SipMessage actualCreateRequestResult = BasicSipMessageFactory.createRequest("foo", request_uri, to,
        new NameAddress("Str"), "foo", 1L, "foo", "foo", null, "foo",
        new byte[]{'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A'});

    // Assert
    assertNull(actualCreateRequestResult.getAcceptEncodingHeader());
    assertEquals(0, actualCreateRequestResult.getRemotePort());
    assertNull(actualCreateRequestResult.getRemoteAddress());
    assertEquals(247, actualCreateRequestResult.getLength());
    Vector expectedHeaders = actualCreateRequestResult.headers;
    assertEquals(expectedHeaders, actualCreateRequestResult.getHeaders());
    assertEquals("foo Uri SIP/2.0\r\n", actualCreateRequestResult.getFirstLine());
    assertNull(actualCreateRequestResult.getConnectionId());
    assertEquals("foo", actualCreateRequestResult.getBodyType());
    assertEquals(8, actualCreateRequestResult.getBody().length);
  }

  @Test
  void testCreateRequest3() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Display name", new GenericURI("Uri"));

    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");

    // Act
    SipMessage actualCreateRequestResult = BasicSipMessageFactory.createRequest("Method", request_uri, to, from,
        "Call id", 1L, "Local tag", "Remote tag", contact, "Not all who wander are lost", "AAAAAAAA".getBytes("UTF-8"));

    // Assert
    assertNull(actualCreateRequestResult.getAcceptEncodingHeader());
    assertEquals(0, actualCreateRequestResult.getRemotePort());
    assertNull(actualCreateRequestResult.getRemoteAddress());
    assertEquals(325, actualCreateRequestResult.getLength());
    Vector expectedHeaders = actualCreateRequestResult.headers;
    assertEquals(expectedHeaders, actualCreateRequestResult.getHeaders());
    assertEquals("Method Uri SIP/2.0\r\n", actualCreateRequestResult.getFirstLine());
    assertNull(actualCreateRequestResult.getConnectionId());
    assertEquals("Not", actualCreateRequestResult.getBodyType());
    assertEquals(8, actualCreateRequestResult.getBody().length);
  }

  @Test
  void testCreateRequest4() {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");

    // Act
    SipMessage actualCreateRequestResult = BasicSipMessageFactory.createRequest("foo", request_uri, to,
        new NameAddress("Str"), "foo", 1L, null, "foo", null, "foo",
        new byte[]{'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A'});

    // Assert
    assertNull(actualCreateRequestResult.getAcceptEncodingHeader());
    assertEquals(0, actualCreateRequestResult.getRemotePort());
    assertNull(actualCreateRequestResult.getRemoteAddress());
    assertEquals(239, actualCreateRequestResult.getLength());
    Vector expectedHeaders = actualCreateRequestResult.headers;
    assertEquals(expectedHeaders, actualCreateRequestResult.getHeaders());
    assertEquals("foo Uri SIP/2.0\r\n", actualCreateRequestResult.getFirstLine());
    assertNull(actualCreateRequestResult.getConnectionId());
    assertEquals("foo", actualCreateRequestResult.getBodyType());
    assertEquals(8, actualCreateRequestResult.getBody().length);
  }

  @Test
  void testCreateRequest5() {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");

    // Act
    SipMessage actualCreateRequestResult = BasicSipMessageFactory.createRequest("foo", request_uri, to,
        new NameAddress("Str"), "foo", 1L, "foo", "foo", null, null,
        new byte[]{'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A'});

    // Assert
    assertNull(actualCreateRequestResult.getAcceptEncodingHeader());
    assertEquals(0, actualCreateRequestResult.getRemotePort());
    assertNull(actualCreateRequestResult.getRemoteAddress());
    assertEquals(228, actualCreateRequestResult.getLength());
    Vector expectedHeaders = actualCreateRequestResult.headers;
    assertEquals(expectedHeaders, actualCreateRequestResult.getHeaders());
    assertEquals("foo Uri SIP/2.0\r\n", actualCreateRequestResult.getFirstLine());
    assertNull(actualCreateRequestResult.getConnectionId());
    assertEquals(8, actualCreateRequestResult.getBody().length);
  }

  @Test
  void testCreateRequest6() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");

    // Act
    SipMessage actualCreateRequestResult = BasicSipMessageFactory.createRequest("Method", request_uri, to, from,
        "Call id", contact, "Not all who wander are lost", "AAAAAAAA".getBytes("UTF-8"));

    // Assert
    assertNull(actualCreateRequestResult.getAcceptEncodingHeader());
    assertEquals(0, actualCreateRequestResult.getRemotePort());
    assertNull(actualCreateRequestResult.getRemoteAddress());
    assertEquals(302, actualCreateRequestResult.getLength());
    Vector expectedHeaders = actualCreateRequestResult.headers;
    assertEquals(expectedHeaders, actualCreateRequestResult.getHeaders());
    assertEquals("Method Uri SIP/2.0\r\n", actualCreateRequestResult.getFirstLine());
    assertNull(actualCreateRequestResult.getConnectionId());
    assertEquals("Not", actualCreateRequestResult.getBodyType());
    assertEquals(8, actualCreateRequestResult.getBody().length);
  }

  @Test
  void testCreateRequest7() {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");

    // Act
    SipMessage actualCreateRequestResult = BasicSipMessageFactory.createRequest("foo", request_uri, to,
        new NameAddress("Str"), "foo", null, "foo", new byte[]{'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A'});

    // Assert
    assertNull(actualCreateRequestResult.getAcceptEncodingHeader());
    assertEquals(0, actualCreateRequestResult.getRemotePort());
    assertNull(actualCreateRequestResult.getRemoteAddress());
    assertEquals(248, actualCreateRequestResult.getLength());
    Vector expectedHeaders = actualCreateRequestResult.headers;
    assertEquals(expectedHeaders, actualCreateRequestResult.getHeaders());
    assertEquals("foo Uri SIP/2.0\r\n", actualCreateRequestResult.getFirstLine());
    assertNull(actualCreateRequestResult.getConnectionId());
    assertEquals("foo", actualCreateRequestResult.getBodyType());
    assertEquals(8, actualCreateRequestResult.getBody().length);
  }

  @Test
  void testCreateRequest8() throws UnsupportedEncodingException {
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
    SipMessage actualCreateRequestResult = BasicSipMessageFactory.createRequest(dialogInfo, "Method",
        "Not all who wander are lost", "AAAAAAAA".getBytes("UTF-8"));

    // Assert
    assertNull(actualCreateRequestResult.getAcceptEncodingHeader());
    assertEquals(0, actualCreateRequestResult.getRemotePort());
    assertNull(actualCreateRequestResult.getRemoteAddress());
    assertEquals(318, actualCreateRequestResult.getLength());
    Vector expectedHeaders = actualCreateRequestResult.headers;
    assertEquals(expectedHeaders, actualCreateRequestResult.getHeaders());
    assertEquals("Method sip:Str SIP/2.0\r\n", actualCreateRequestResult.getFirstLine());
    assertNull(actualCreateRequestResult.getConnectionId());
    assertEquals("Not", actualCreateRequestResult.getBodyType());
    assertEquals(8, actualCreateRequestResult.getBody().length);
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
  void testCreateRequest9() throws UnsupportedEncodingException {
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
    SipMessage actualCreateRequestResult = BasicSipMessageFactory.createRequest(dialogInfo, "Method",
        "Not all who wander are lost", "AAAAAAAA".getBytes("UTF-8"));

    // Assert
    assertNull(actualCreateRequestResult.getAcceptEncodingHeader());
    assertEquals(0, actualCreateRequestResult.getRemotePort());
    assertNull(actualCreateRequestResult.getRemoteAddress());
    assertEquals(335, actualCreateRequestResult.getLength());
    Vector expectedHeaders = actualCreateRequestResult.headers;
    assertEquals(expectedHeaders, actualCreateRequestResult.getHeaders());
    assertEquals("Method sip:42 SIP/2.0\r\n", actualCreateRequestResult.getFirstLine());
    assertNull(actualCreateRequestResult.getConnectionId());
    assertEquals("Not", actualCreateRequestResult.getBodyType());
    assertEquals(8, actualCreateRequestResult.getBody().length);
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
  void testCreateRequest10() throws UnsupportedEncodingException {
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
    SipMessage actualCreateRequestResult = BasicSipMessageFactory.createRequest(dialogInfo, "Method",
        "Not all who wander are lost", "AAAAAAAA".getBytes("UTF-8"));

    // Assert
    assertNull(actualCreateRequestResult.getAcceptEncodingHeader());
    assertEquals(0, actualCreateRequestResult.getRemotePort());
    assertNull(actualCreateRequestResult.getRemoteAddress());
    assertEquals(346, actualCreateRequestResult.getLength());
    Vector expectedHeaders = actualCreateRequestResult.headers;
    assertEquals(expectedHeaders, actualCreateRequestResult.getHeaders());
    assertEquals("Method sip:42 SIP/2.0\r\n", actualCreateRequestResult.getFirstLine());
    assertNull(actualCreateRequestResult.getConnectionId());
    assertEquals("Not", actualCreateRequestResult.getBodyType());
    assertEquals(8, actualCreateRequestResult.getBody().length);
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
  void testCreateRequest11() throws UnsupportedEncodingException {
    // Arrange
    DialogInfo dialogInfo = mock(DialogInfo.class);
    when(dialogInfo.getRoute()).thenReturn(new Vector(1));
    when(dialogInfo.getRemoteTag()).thenReturn(null);
    when(dialogInfo.getLocalTag()).thenReturn("Local Tag");
    when(dialogInfo.getLocalCSeq()).thenReturn(1L);
    when(dialogInfo.getCallID()).thenReturn("Call ID");
    doNothing().when(dialogInfo).incLocalCSeq();
    when(dialogInfo.getLocalContact()).thenReturn(new NameAddress("Str"));
    when(dialogInfo.getRemoteContact()).thenReturn(new NameAddress("Str"));
    when(dialogInfo.getLocalName()).thenReturn(new NameAddress("Str"));
    when(dialogInfo.getRemoteName()).thenReturn(new NameAddress("Str"));

    // Act
    SipMessage actualCreateRequestResult = BasicSipMessageFactory.createRequest(dialogInfo, "Method",
        "Not all who wander are lost", "AAAAAAAA".getBytes("UTF-8"));

    // Assert
    assertNull(actualCreateRequestResult.getAcceptEncodingHeader());
    assertEquals(0, actualCreateRequestResult.getRemotePort());
    assertNull(actualCreateRequestResult.getRemoteAddress());
    assertEquals(303, actualCreateRequestResult.getLength());
    Vector expectedHeaders = actualCreateRequestResult.headers;
    assertEquals(expectedHeaders, actualCreateRequestResult.getHeaders());
    assertEquals("Method sip:Str SIP/2.0\r\n", actualCreateRequestResult.getFirstLine());
    assertNull(actualCreateRequestResult.getConnectionId());
    assertEquals("Not", actualCreateRequestResult.getBodyType());
    assertEquals(8, actualCreateRequestResult.getBody().length);
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
  void testCreateRequest12() throws UnsupportedEncodingException {
    // Arrange
    DialogInfo dialogInfo = mock(DialogInfo.class);
    when(dialogInfo.getRoute()).thenReturn(new Vector(1));
    when(dialogInfo.getRemoteTag()).thenReturn("Remote Tag");
    when(dialogInfo.getLocalTag()).thenReturn("Local Tag");
    when(dialogInfo.getLocalCSeq()).thenReturn(1L);
    when(dialogInfo.getCallID()).thenReturn("Call ID");
    doNothing().when(dialogInfo).incLocalCSeq();
    when(dialogInfo.getLocalContact()).thenReturn(null);
    when(dialogInfo.getRemoteContact()).thenReturn(new NameAddress("Str"));
    when(dialogInfo.getLocalName()).thenReturn(new NameAddress("Str"));
    when(dialogInfo.getRemoteName()).thenReturn(new NameAddress("Str"));

    // Act
    SipMessage actualCreateRequestResult = BasicSipMessageFactory.createRequest(dialogInfo, "Method",
        "Not all who wander are lost", "AAAAAAAA".getBytes("UTF-8"));

    // Assert
    assertNull(actualCreateRequestResult.getAcceptEncodingHeader());
    assertEquals(0, actualCreateRequestResult.getRemotePort());
    assertNull(actualCreateRequestResult.getRemoteAddress());
    assertEquals(298, actualCreateRequestResult.getLength());
    Vector expectedHeaders = actualCreateRequestResult.headers;
    assertEquals(expectedHeaders, actualCreateRequestResult.getHeaders());
    assertEquals("Method sip:Str SIP/2.0\r\n", actualCreateRequestResult.getFirstLine());
    assertNull(actualCreateRequestResult.getConnectionId());
    assertEquals("Not", actualCreateRequestResult.getBodyType());
    assertEquals(8, actualCreateRequestResult.getBody().length);
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
  void testCreateResponse() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");
    SipMessage req = BasicSipMessageFactory.createRequest("Method", request_uri, to, from, "Call id", 1L, "Local tag",
        "Remote tag", contact, "Not all who wander are lost", "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));
    NameAddress contact1 = new NameAddress("Str");

    // Act
    SipMessage actualCreateResponseResult = BasicSipMessageFactory.createResponse(req, 1, "Just cause", "Local tag",
        contact1, "Not all who wander are lost", "AAAAAAAA".getBytes("UTF-8"));

    // Assert
    assertNull(actualCreateResponseResult.getAcceptEncodingHeader());
    assertNull(actualCreateResponseResult.getRequestLine());
    assertEquals(0, actualCreateResponseResult.getRemotePort());
    assertNull(actualCreateResponseResult.getRemoteAddress());
    assertEquals(278, actualCreateResponseResult.getLength());
    Vector expectedHeaders = actualCreateResponseResult.headers;
    assertEquals(expectedHeaders, actualCreateResponseResult.getHeaders());
    assertEquals("SIP/2.0 1 Just cause\r\n", actualCreateResponseResult.getFirstLine());
    assertNull(actualCreateResponseResult.getConnectionId());
    assertEquals("Not", actualCreateResponseResult.getBodyType());
    assertEquals(8, actualCreateResponseResult.getBody().length);
  }

  @Test
  void testCreateResponse2() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");
    SipMessage req = BasicSipMessageFactory.createRequest("Method", request_uri, to, from, "Call id", 1L, "Local tag",
        "Remote tag", contact, "Not all who wander are lost", "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    // Act
    SipMessage actualCreateResponseResult = BasicSipMessageFactory.createResponse(req, 1, "Just cause",
        new NameAddress("Str"));

    // Assert
    assertNull(actualCreateResponseResult.getAcceptEncodingHeader());
    assertNull(actualCreateResponseResult.getRequestLine());
    assertEquals(0, actualCreateResponseResult.getRemotePort());
    assertNull(actualCreateResponseResult.getRemoteAddress());
    assertEquals(228, actualCreateResponseResult.getLength());
    Vector expectedHeaders = actualCreateResponseResult.headers;
    assertEquals(expectedHeaders, actualCreateResponseResult.getHeaders());
    assertEquals("SIP/2.0 1 Just cause\r\n", actualCreateResponseResult.getFirstLine());
    assertNull(actualCreateResponseResult.getConnectionId());
    assertNull(actualCreateResponseResult.getBody());
  }
}

