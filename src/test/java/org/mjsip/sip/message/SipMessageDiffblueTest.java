package org.mjsip.sip.message;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.UnsupportedEncodingException;
import java.util.Vector;
import org.junit.jupiter.api.Test;
import org.mjsip.sip.address.GenericURI;
import org.mjsip.sip.address.NameAddress;
import org.mjsip.sip.header.AcceptEncodingHeader;
import org.mjsip.sip.header.AcceptHeader;
import org.mjsip.sip.header.AcceptLanguageHeader;
import org.mjsip.sip.header.AlertInfoHeader;
import org.mjsip.sip.header.AllowEventsHeader;
import org.mjsip.sip.header.AllowHeader;
import org.mjsip.sip.header.AuthenticationInfoHeader;
import org.mjsip.sip.header.AuthorizationHeader;
import org.mjsip.sip.header.CSeqHeader;
import org.mjsip.sip.header.CallIdHeader;
import org.mjsip.sip.header.ContactHeader;
import org.mjsip.sip.header.ContentDispositionHeader;
import org.mjsip.sip.header.ContentLengthHeader;
import org.mjsip.sip.header.ContentTypeHeader;
import org.mjsip.sip.header.DateHeader;
import org.mjsip.sip.header.EventHeader;
import org.mjsip.sip.header.ExpiresHeader;
import org.mjsip.sip.header.FromHeader;
import org.mjsip.sip.header.Header;
import org.mjsip.sip.header.InfoPackageHeader;
import org.mjsip.sip.header.MaxForwardsHeader;
import org.mjsip.sip.header.MinSEHeader;
import org.mjsip.sip.header.MultipleHeader;
import org.mjsip.sip.header.ProxyAuthenticateHeader;
import org.mjsip.sip.header.ProxyAuthorizationHeader;
import org.mjsip.sip.header.ProxyRequireHeader;
import org.mjsip.sip.header.RAckHeader;
import org.mjsip.sip.header.RSeqHeader;
import org.mjsip.sip.header.RecordRouteHeader;
import org.mjsip.sip.header.RecvInfoHeader;
import org.mjsip.sip.header.ReferToHeader;
import org.mjsip.sip.header.ReferredByHeader;
import org.mjsip.sip.header.ReplacesHeader;
import org.mjsip.sip.header.RequestLine;
import org.mjsip.sip.header.RequireHeader;
import org.mjsip.sip.header.RouteHeader;
import org.mjsip.sip.header.ServerHeader;
import org.mjsip.sip.header.SessionExpiresHeader;
import org.mjsip.sip.header.StatusLine;
import org.mjsip.sip.header.SubjectHeader;
import org.mjsip.sip.header.SubscriptionStateHeader;
import org.mjsip.sip.header.SupportedHeader;
import org.mjsip.sip.header.ToHeader;
import org.mjsip.sip.header.UnsupportedHeader;
import org.mjsip.sip.header.UserAgentHeader;
import org.mjsip.sip.header.ViaHeader;
import org.mjsip.sip.header.WwwAuthenticateHeader;
import org.mjsip.sip.provider.ConnectionId;
import org.zoolu.net.SocketAddress;

class SipMessageDiffblueTest {
  @Test
  void testAddHeader() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.addHeader(new Header("Hname", "42"), true);

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testAddHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.addHeader(new Header("Hname", "42"), false);

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testAddHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.addHeader(new Header("Hname", "42"), false);

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testAddHeader4() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");
    SipMessage createRequestResult = BasicSipMessageFactory.createRequest("Method", request_uri, to, from, "Call id",
        1L, "Local tag", "Remote tag", contact, "Not all who wander are lost",
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    // Act
    createRequestResult.addHeader(new Header("Hname", "42"), false);

    // Assert
    Vector expectedHeaders = createRequestResult.headers;
    assertEquals(expectedHeaders, createRequestResult.getHeaders());
  }

  @Test
  void testAddHeader5() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Content-Type"));

    // Act
    sipMessage.addHeader(new Header("Hname", "42"), false);

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testAddHeaderAfter() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.addHeaderAfter(new Header("Hname", "42"), "Refer hname");

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testAddHeaderAfter2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.addHeaderAfter(new Header("Hname", "42"), "Refer hname");

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testAddHeaderBefore() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.addHeaderBefore(new Header("Hname", "42"), "Refer hname");

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testAddHeaderBefore2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.addHeaderBefore(new Header("Hname", "42"), "Refer hname");

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testAddHeaders() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    Vector vector = new Vector(1);

    // Act
    sipMessage.addHeaders(vector, true);

    // Assert that nothing has changed
    assertEquals(vector, sipMessage.headers);
    assertEquals(0, sipMessage.getRemotePort());
  }

  @Test
  void testAddHeaders2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    Vector vector = new Vector(1);
    vector.add("42");

    // Act
    sipMessage.addHeaders(vector, true);

    // Assert
    assertEquals(vector, sipMessage.getHeaders());
  }

  @Test
  void testAddHeaders3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    Vector vector = new Vector(1);

    // Act
    sipMessage.addHeaders(vector, false);

    // Assert that nothing has changed
    assertEquals(vector, sipMessage.headers);
    assertEquals(0, sipMessage.getRemotePort());
  }

  @Test
  void testAddHeaders4() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    Vector vector = new Vector(1);
    vector.add("42");

    // Act
    sipMessage.addHeaders(vector, false);

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testAddHeaders5() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");
    SipMessage createRequestResult = BasicSipMessageFactory.createRequest("Method", request_uri, to, from, "Call id",
        1L, "Local tag", "Remote tag", contact, "Not all who wander are lost",
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    Vector vector = new Vector(1);
    vector.add("42");

    // Act
    createRequestResult.addHeaders(vector, false);

    // Assert
    Vector expectedHeaders = createRequestResult.headers;
    assertEquals(expectedHeaders, createRequestResult.getHeaders());
  }

  @Test
  void testClearTransport() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.clearTransport();

    // Assert
    assertEquals(-1, sipMessage.getRemotePort());
    assertNull(sipMessage.getRemoteAddress());
    assertNull(sipMessage.getConnectionId());
  }

  @Test
  void testConstructor() {
    // Arrange and Act
    SipMessage actualSipMessage = new SipMessage();

    // Assert
    assertNull(actualSipMessage.getAcceptEncodingHeader());
    assertEquals(8000, BasicSipMessage.MAX_PKT_SIZE);
    assertFalse(BasicSipMessage.DEBUG);
    assertNull(actualSipMessage.getServiceRoutes());
    assertNull(actualSipMessage.getServerHeader());
    assertNull(actualSipMessage.getRoutes());
    assertNull(actualSipMessage.getRouteHeader());
    assertNull(actualSipMessage.getRequireHeader());
    assertNull(actualSipMessage.getRequestLine());
    assertNull(actualSipMessage.getReplacesHeader());
    assertEquals(0, actualSipMessage.getRemotePort());
    assertNull(actualSipMessage.getRemoteAddress());
    assertNull(actualSipMessage.getReferredByHeader());
    assertNull(actualSipMessage.getReferToHeader());
    assertNull(actualSipMessage.getRecvInfoHeader());
    assertNull(actualSipMessage.getRecordRoutes());
    assertNull(actualSipMessage.getRecordRouteHeader());
    assertNull(actualSipMessage.getRSeqHeader());
    assertNull(actualSipMessage.getRAckHeader());
    assertNull(actualSipMessage.getProxyRequireHeader());
    assertNull(actualSipMessage.getProxyAuthorizationHeader());
    assertNull(actualSipMessage.getProxyAuthenticateHeader());
    assertNull(actualSipMessage.getMinSEHeader());
    assertNull(actualSipMessage.getMaxForwardsHeader());
    assertEquals(2, actualSipMessage.getLength());
    assertNull(actualSipMessage.getInfoPackageHeader());
    Vector expectedHeaders = actualSipMessage.headers;
    assertEquals(expectedHeaders, actualSipMessage.getHeaders());
    assertNull(actualSipMessage.getFromHeader());
    assertNull(actualSipMessage.getFirstLine());
    assertNull(actualSipMessage.getExpiresHeader());
    assertNull(actualSipMessage.getEventHeader());
    assertNull(actualSipMessage.getDateHeader());
    assertNull(actualSipMessage.getContentTypeHeader());
    assertNull(actualSipMessage.getContentLengthHeader());
    assertNull(actualSipMessage.getContentDispositionHeader());
    assertNull(actualSipMessage.getContacts());
    assertNull(actualSipMessage.getContactHeader());
    assertNull(actualSipMessage.getConnectionId());
    assertNull(actualSipMessage.getCallIdHeader());
    assertNull(actualSipMessage.getCSeqHeader());
    byte[] bytes = actualSipMessage.getBytes();
    assertEquals(2, bytes.length);
    assertArrayEquals(new byte[]{13, 10}, bytes);
    assertEquals((byte) 13, bytes[0]);
    assertEquals((byte) 10, bytes[1]);
    assertNull(actualSipMessage.getBody());
    assertNull(actualSipMessage.getAuthorizationHeader());
    assertNull(actualSipMessage.getAuthenticationInfoHeader());
    assertNull(actualSipMessage.getAllowHeader());
    assertNull(actualSipMessage.getAllowEventsHeader());
    assertNull(actualSipMessage.getAlertInfoHeader());
    assertNull(actualSipMessage.getAcceptLanguageHeader());
    assertNull(actualSipMessage.getAcceptHeader());
  }

  @Test
  void testConstructor2() throws UnsupportedEncodingException {
    // Arrange
    RequestLine requestLine = new RequestLine("Request", "Str uri");

    Vector vector = new Vector(1);
    byte[] bytes = "AAAAAAAA".getBytes("UTF-8");

    // Act
    SipMessage actualSipMessage = new SipMessage(requestLine, vector, bytes);

    // Assert
    assertNull(actualSipMessage.getAcceptEncodingHeader());
    assertEquals(8000, BasicSipMessage.MAX_PKT_SIZE);
    assertFalse(BasicSipMessage.DEBUG);
    assertNull(actualSipMessage.getServiceRoutes());
    assertNull(actualSipMessage.getServerHeader());
    assertNull(actualSipMessage.getRoutes());
    assertNull(actualSipMessage.getRouteHeader());
    assertNull(actualSipMessage.getRequireHeader());
    RequestLine expectedRequestLine = actualSipMessage.request_line;
    RequestLine requestLine1 = actualSipMessage.getRequestLine();
    assertSame(expectedRequestLine, requestLine1);
    assertNull(actualSipMessage.getReplacesHeader());
    assertEquals(0, actualSipMessage.getRemotePort());
    assertNull(actualSipMessage.getRemoteAddress());
    assertNull(actualSipMessage.getReferredByHeader());
    assertNull(actualSipMessage.getReferToHeader());
    assertNull(actualSipMessage.getRecvInfoHeader());
    assertNull(actualSipMessage.getRecordRoutes());
    assertNull(actualSipMessage.getRecordRouteHeader());
    assertNull(actualSipMessage.getRSeqHeader());
    assertNull(actualSipMessage.getRAckHeader());
    assertNull(actualSipMessage.getProxyRequireHeader());
    assertNull(actualSipMessage.getProxyAuthorizationHeader());
    assertNull(actualSipMessage.getProxyAuthenticateHeader());
    assertNull(actualSipMessage.getMinSEHeader());
    assertNull(actualSipMessage.getMaxForwardsHeader());
    assertEquals(35, actualSipMessage.getLength());
    assertNull(actualSipMessage.getInfoPackageHeader());
    Vector vector1 = actualSipMessage.headers;
    assertEquals(vector1, actualSipMessage.getHeaders());
    assertNull(actualSipMessage.getFromHeader());
    assertEquals("Request Str uri SIP/2.0\r\n", actualSipMessage.getFirstLine());
    assertNull(actualSipMessage.getExpiresHeader());
    assertNull(actualSipMessage.getEventHeader());
    assertNull(actualSipMessage.getDateHeader());
    assertNull(actualSipMessage.getContentTypeHeader());
    assertNull(actualSipMessage.getContentLengthHeader());
    assertNull(actualSipMessage.getContentDispositionHeader());
    assertNull(actualSipMessage.getContacts());
    assertNull(actualSipMessage.getContactHeader());
    assertNull(actualSipMessage.getConnectionId());
    assertNull(actualSipMessage.getCallIdHeader());
    assertNull(actualSipMessage.getCSeqHeader());
    assertEquals(35, actualSipMessage.getBytes().length);
    byte[] expectedBody = actualSipMessage.body;
    byte[] body = actualSipMessage.getBody();
    assertSame(expectedBody, body);
    assertEquals(8, body.length);
    assertNull(actualSipMessage.getAuthorizationHeader());
    assertNull(actualSipMessage.getAuthenticationInfoHeader());
    assertNull(actualSipMessage.getAllowHeader());
    assertNull(actualSipMessage.getAllowEventsHeader());
    assertNull(actualSipMessage.getAlertInfoHeader());
    assertNull(actualSipMessage.getAcceptLanguageHeader());
    assertNull(actualSipMessage.getAcceptHeader());
    assertSame(requestLine1, requestLine);
    assertSame(vector1, vector);
    assertSame(body, bytes);
  }

  @Test
  void testConstructor3() throws UnsupportedEncodingException {
    // Arrange
    StatusLine statusLine = new StatusLine(1, "foo");

    Vector vector = new Vector(1);
    byte[] bytes = "AAAAAAAA".getBytes("UTF-8");

    // Act
    SipMessage actualSipMessage = new SipMessage(statusLine, vector, bytes);

    // Assert
    assertNull(actualSipMessage.getAcceptEncodingHeader());
    assertEquals(8000, BasicSipMessage.MAX_PKT_SIZE);
    assertFalse(BasicSipMessage.DEBUG);
    assertNull(actualSipMessage.getServiceRoutes());
    assertNull(actualSipMessage.getServerHeader());
    assertNull(actualSipMessage.getRoutes());
    assertNull(actualSipMessage.getRouteHeader());
    assertNull(actualSipMessage.getRequireHeader());
    assertNull(actualSipMessage.getRequestLine());
    assertNull(actualSipMessage.getReplacesHeader());
    assertEquals(0, actualSipMessage.getRemotePort());
    assertNull(actualSipMessage.getRemoteAddress());
    assertNull(actualSipMessage.getReferredByHeader());
    assertNull(actualSipMessage.getReferToHeader());
    assertNull(actualSipMessage.getRecvInfoHeader());
    assertNull(actualSipMessage.getRecordRoutes());
    assertNull(actualSipMessage.getRecordRouteHeader());
    assertNull(actualSipMessage.getRSeqHeader());
    assertNull(actualSipMessage.getRAckHeader());
    assertNull(actualSipMessage.getProxyRequireHeader());
    assertNull(actualSipMessage.getProxyAuthorizationHeader());
    assertNull(actualSipMessage.getProxyAuthenticateHeader());
    assertNull(actualSipMessage.getMinSEHeader());
    assertNull(actualSipMessage.getMaxForwardsHeader());
    assertEquals(25, actualSipMessage.getLength());
    assertNull(actualSipMessage.getInfoPackageHeader());
    Vector vector1 = actualSipMessage.headers;
    assertEquals(vector1, actualSipMessage.getHeaders());
    assertNull(actualSipMessage.getFromHeader());
    assertEquals("SIP/2.0 1 foo\r\n", actualSipMessage.getFirstLine());
    assertNull(actualSipMessage.getExpiresHeader());
    assertNull(actualSipMessage.getEventHeader());
    assertNull(actualSipMessage.getDateHeader());
    assertNull(actualSipMessage.getContentTypeHeader());
    assertNull(actualSipMessage.getContentLengthHeader());
    assertNull(actualSipMessage.getContentDispositionHeader());
    assertNull(actualSipMessage.getContacts());
    assertNull(actualSipMessage.getContactHeader());
    assertNull(actualSipMessage.getConnectionId());
    assertNull(actualSipMessage.getCallIdHeader());
    assertNull(actualSipMessage.getCSeqHeader());
    assertEquals(25, actualSipMessage.getBytes().length);
    byte[] expectedBody = actualSipMessage.body;
    byte[] body = actualSipMessage.getBody();
    assertSame(expectedBody, body);
    assertEquals(8, body.length);
    assertNull(actualSipMessage.getAuthorizationHeader());
    assertNull(actualSipMessage.getAuthenticationInfoHeader());
    assertNull(actualSipMessage.getAllowHeader());
    assertNull(actualSipMessage.getAllowEventsHeader());
    assertNull(actualSipMessage.getAlertInfoHeader());
    assertNull(actualSipMessage.getAcceptLanguageHeader());
    assertNull(actualSipMessage.getAcceptHeader());
    assertSame(actualSipMessage.status_line, statusLine);
    assertSame(vector1, vector);
    assertSame(body, bytes);
  }

  @Test
  void testConstructor4() {
    // Arrange and Act
    SipMessage actualSipMessage = new SipMessage("Str");

    // Assert
    assertNull(actualSipMessage.getAcceptEncodingHeader());
    assertNull(actualSipMessage.getRequestLine());
    assertEquals(0, actualSipMessage.getRemotePort());
    assertNull(actualSipMessage.getRemoteAddress());
    assertNull(actualSipMessage.getFirstLine());
    assertNull(actualSipMessage.getConnectionId());
    assertNull(actualSipMessage.getBody());
  }

  @Test
  void testConstructor5() {
    // Arrange and Act
    SipMessage actualSipMessage = new SipMessage("SIP/");

    // Assert
    assertNull(actualSipMessage.getAcceptEncodingHeader());
    assertNull(actualSipMessage.getRequestLine());
    assertEquals(0, actualSipMessage.getRemotePort());
    assertNull(actualSipMessage.getRemoteAddress());
    assertNull(actualSipMessage.getFirstLine());
    assertNull(actualSipMessage.getConnectionId());
    assertNull(actualSipMessage.getBody());
  }

  @Test
  void testConstructor6() {
    // Arrange and Act
    SipMessage actualSipMessage = new SipMessage("Accept");

    // Assert
    assertNull(actualSipMessage.getAcceptEncodingHeader());
    assertEquals(0, actualSipMessage.getRemotePort());
    assertNull(actualSipMessage.getRemoteAddress());
    assertEquals("Accept null SIP/2.0\r\n", actualSipMessage.getFirstLine());
    assertNull(actualSipMessage.getConnectionId());
    assertNull(actualSipMessage.getBody());
  }

  @Test
  void testConstructor7() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    SipMessage actualSipMessage = new SipMessage(sipMessage);

    // Assert
    assertEquals(sipMessage.headers, actualSipMessage.headers);
    assertNull(actualSipMessage.getRequestLine());
    assertEquals(0, actualSipMessage.getRemotePort());
    assertNull(actualSipMessage.getRemoteAddress());
    assertNull(actualSipMessage.getFirstLine());
    assertNull(actualSipMessage.getConnectionId());
    assertNull(actualSipMessage.getBody());
  }

  @Test
  void testConstructor8() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    SipMessage actualSipMessage = new SipMessage(sipMessage);

    // Assert
    Vector vector = sipMessage.headers;
    assertEquals(vector, actualSipMessage.headers);
    assertNull(actualSipMessage.getRequestLine());
    assertEquals(0, actualSipMessage.getRemotePort());
    assertNull(actualSipMessage.getRemoteAddress());
    assertEquals(vector, actualSipMessage.getHeaders());
    assertNull(actualSipMessage.getFirstLine());
    assertNull(actualSipMessage.getConnectionId());
    assertNull(actualSipMessage.getBody());
  }

  @Test
  void testConstructor9() throws UnsupportedEncodingException {
    // Arrange and Act
    SipMessage actualSipMessage = new SipMessage("AAAAAAAA".getBytes("UTF-8"), 2, 3);

    // Assert
    assertNull(actualSipMessage.getAcceptEncodingHeader());
    assertNull(actualSipMessage.getRequestLine());
    assertEquals(0, actualSipMessage.getRemotePort());
    assertNull(actualSipMessage.getRemoteAddress());
    assertNull(actualSipMessage.getFirstLine());
    assertNull(actualSipMessage.getConnectionId());
    assertNull(actualSipMessage.getBody());
  }

  @Test
  void testConstructor10() {
    // Arrange and Act
    SipMessage actualSipMessage = new SipMessage(new byte[]{'A', 'A', 10, 'A', 'A', 'A', 'A', 'A'}, 2, 3);

    // Assert
    assertNull(actualSipMessage.getAcceptEncodingHeader());
    assertNull(actualSipMessage.getRequestLine());
    assertEquals(0, actualSipMessage.getRemotePort());
    assertNull(actualSipMessage.getRemoteAddress());
    assertNull(actualSipMessage.getFirstLine());
    assertNull(actualSipMessage.getConnectionId());
    assertNull(actualSipMessage.getBody());
  }

  @Test
  void testConstructor11() {
    // Arrange and Act
    SipMessage actualSipMessage = new SipMessage(new byte[]{}, 2, 3);

    // Assert
    assertNull(actualSipMessage.getAcceptEncodingHeader());
    assertNull(actualSipMessage.getRequestLine());
    assertEquals(0, actualSipMessage.getRemotePort());
    assertNull(actualSipMessage.getRemoteAddress());
    assertNull(actualSipMessage.getFirstLine());
    assertNull(actualSipMessage.getConnectionId());
    assertNull(actualSipMessage.getBody());
  }

  @Test
  void testConstructor12() throws UnsupportedEncodingException {
    // Arrange and Act
    SipMessage actualSipMessage = new SipMessage("AAAAAAAA".getBytes("UTF-8"), 2, 13);

    // Assert
    assertNull(actualSipMessage.getAcceptEncodingHeader());
    assertNull(actualSipMessage.getRequestLine());
    assertEquals(0, actualSipMessage.getRemotePort());
    assertNull(actualSipMessage.getRemoteAddress());
    assertNull(actualSipMessage.getFirstLine());
    assertNull(actualSipMessage.getConnectionId());
    assertNull(actualSipMessage.getBody());
  }

  @Test
  void testConstructor13() {
    // Arrange and Act
    SipMessage actualSipMessage = new SipMessage(new byte[]{'A', 'A', 10, 10, 'A', 'A', 'A', 'A'}, 2, 3);

    // Assert
    assertNull(actualSipMessage.getAcceptEncodingHeader());
    assertNull(actualSipMessage.getRequestLine());
    assertEquals(0, actualSipMessage.getRemotePort());
    assertNull(actualSipMessage.getRemoteAddress());
    assertNull(actualSipMessage.getFirstLine());
    assertNull(actualSipMessage.getConnectionId());
    assertNull(actualSipMessage.getBody());
  }

  @Test
  void testClone() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act and Assert
    assertEquals(sipMessage.headers, ((SipMessage) sipMessage.clone()).headers);
    assertNull(((SipMessage) sipMessage.clone()).getRequestLine());
    assertEquals(0, ((SipMessage) sipMessage.clone()).getRemotePort());
    assertNull(((SipMessage) sipMessage.clone()).getRemoteAddress());
    assertNull(((SipMessage) sipMessage.clone()).getFirstLine());
    assertNull(((SipMessage) sipMessage.clone()).getConnectionId());
    assertNull(((SipMessage) sipMessage.clone()).getBody());
  }

  @Test
  void testClone2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    Vector vector = sipMessage.headers;
    assertEquals(vector, ((SipMessage) sipMessage.clone()).headers);
    assertNull(((SipMessage) sipMessage.clone()).getRequestLine());
    assertEquals(0, ((SipMessage) sipMessage.clone()).getRemotePort());
    assertNull(((SipMessage) sipMessage.clone()).getRemoteAddress());
    assertEquals(vector, ((SipMessage) sipMessage.clone()).getHeaders());
    assertNull(((SipMessage) sipMessage.clone()).getFirstLine());
    assertNull(((SipMessage) sipMessage.clone()).getConnectionId());
    assertNull(((SipMessage) sipMessage.clone()).getBody());
  }

  @Test
  void testIsInvite() {
    // Arrange, Act and Assert
    assertFalse((new SipMessage("Str")).isInvite());
    assertTrue((new SipMessage(SipMethods.INVITE)).isInvite());
    assertFalse((new SipMessage("Accept")).isInvite());
  }

  @Test
  void testIsAck() {
    // Arrange, Act and Assert
    assertFalse((new SipMessage("Str")).isAck());
    assertFalse((new SipMessage("Accept")).isAck());
  }

  @Test
  void testIsOptions() {
    // Arrange, Act and Assert
    assertFalse((new SipMessage("Str")).isOptions());
    assertTrue((new SipMessage(SipMethods.OPTIONS)).isOptions());
    assertFalse((new SipMessage("Accept")).isOptions());
  }

  @Test
  void testIsBye() {
    // Arrange, Act and Assert
    assertFalse((new SipMessage("Str")).isBye());
    assertFalse((new SipMessage("Accept")).isBye());
  }

  @Test
  void testIsCancel() {
    // Arrange, Act and Assert
    assertFalse((new SipMessage("Str")).isCancel());
    assertTrue((new SipMessage(SipMethods.CANCEL)).isCancel());
    assertFalse((new SipMessage("Accept")).isCancel());
  }

  @Test
  void testIsRegister() {
    // Arrange, Act and Assert
    assertFalse((new SipMessage("Str")).isRegister());
    assertTrue((new SipMessage(SipMethods.REGISTER)).isRegister());
    assertFalse((new SipMessage("Accept")).isRegister());
  }

  @Test
  void testGetTransactionMethod() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");

    // Act and Assert
    assertEquals("Method",
        BasicSipMessageFactory
            .createRequest("Method", request_uri, to, from, "Call id", 1L, "Local tag", "Remote tag", contact,
                "Not all who wander are lost", "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"))
            .getTransactionMethod());
  }

  @Test
  void testHasMaxForwardsHeader() {
    // Arrange, Act and Assert
    assertFalse((new SipMessage("Str")).hasMaxForwardsHeader());
  }

  @Test
  void testHasMaxForwardsHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertFalse(sipMessage.hasMaxForwardsHeader());
  }

  @Test
  void testHasMaxForwardsHeader3() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");

    // Act and Assert
    assertTrue(
        BasicSipMessageFactory
            .createRequest("Method", request_uri, to, from, "Call id", 1L, "Local tag", "Remote tag", contact,
                "Not all who wander are lost", "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"))
            .hasMaxForwardsHeader());
  }

  @Test
  void testGetMaxForwardsHeader() {
    // Arrange, Act and Assert
    assertNull((new SipMessage("Str")).getMaxForwardsHeader());
  }

  @Test
  void testGetMaxForwardsHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertNull(sipMessage.getMaxForwardsHeader());
  }

  @Test
  void testGetMaxForwardsHeader3() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");

    // Act
    MaxForwardsHeader actualMaxForwardsHeader = BasicSipMessageFactory
        .createRequest("Method", request_uri, to, from, "Call id", 1L, "Local tag", "Remote tag", contact,
            "Not all who wander are lost", "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"))
        .getMaxForwardsHeader();

    // Assert
    assertEquals("Max-Forwards", actualMaxForwardsHeader.getName());
    assertEquals("70", actualMaxForwardsHeader.getValue());
  }

  @Test
  void testSetMaxForwardsHeader() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.setMaxForwardsHeader(new MaxForwardsHeader(1));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetMaxForwardsHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.setMaxForwardsHeader(new MaxForwardsHeader(1));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetMaxForwardsHeader3() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");
    SipMessage createRequestResult = BasicSipMessageFactory.createRequest("Method", request_uri, to, from, "Call id",
        1L, "Local tag", "Remote tag", contact, "Not all who wander are lost",
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    // Act
    createRequestResult.setMaxForwardsHeader(new MaxForwardsHeader(1));

    // Assert
    Vector expectedHeaders = createRequestResult.headers;
    assertEquals(expectedHeaders, createRequestResult.getHeaders());
  }

  @Test
  void testSetMaxForwardsHeader4() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Content-Length"));

    // Act
    sipMessage.setMaxForwardsHeader(new MaxForwardsHeader(1));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetMaxForwardsHeader5() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Content-Type"));

    // Act
    sipMessage.setMaxForwardsHeader(new MaxForwardsHeader(1));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetMaxForwardsHeader6() throws UnsupportedEncodingException {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.setBody("Not all who wander are lost", "Not all who wander are lost", "AAAAAAAA".getBytes("UTF-8"));
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.setMaxForwardsHeader(new MaxForwardsHeader(1));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testRemoveMaxForwardsHeader() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.removeMaxForwardsHeader();

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
  }

  @Test
  void testRemoveMaxForwardsHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.removeMaxForwardsHeader();

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testRemoveMaxForwardsHeader3() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");
    SipMessage createRequestResult = BasicSipMessageFactory.createRequest("Method", request_uri, to, from, "Call id",
        1L, "Local tag", "Remote tag", contact, "Not all who wander are lost",
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    // Act
    createRequestResult.removeMaxForwardsHeader();

    // Assert
    Vector expectedHeaders = createRequestResult.headers;
    assertEquals(expectedHeaders, createRequestResult.getHeaders());
  }

  @Test
  void testHasFromHeader() {
    // Arrange, Act and Assert
    assertFalse((new SipMessage("Str")).hasFromHeader());
  }

  @Test
  void testHasFromHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertFalse(sipMessage.hasFromHeader());
  }

  @Test
  void testHasFromHeader3() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");

    // Act and Assert
    assertTrue(
        BasicSipMessageFactory
            .createRequest("Method", request_uri, to, from, "Call id", 1L, "Local tag", "Remote tag", contact,
                "Not all who wander are lost", "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"))
            .hasFromHeader());
  }

  @Test
  void testGetFromHeader() {
    // Arrange, Act and Assert
    assertNull((new SipMessage("Str")).getFromHeader());
  }

  @Test
  void testGetFromHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertNull(sipMessage.getFromHeader());
  }

  @Test
  void testGetFromHeader3() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");

    // Act
    FromHeader actualFromHeader = BasicSipMessageFactory
        .createRequest("Method", request_uri, to, from, "Call id", 1L, "Local tag", "Remote tag", contact,
            "Not all who wander are lost", "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"))
        .getFromHeader();

    // Assert
    assertEquals("From", actualFromHeader.getName());
    assertEquals("<sip:Str>;tag=Local tag", actualFromHeader.getValue());
  }

  @Test
  void testSetFromHeader() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.setFromHeader(new FromHeader(new Header("Hname", "42")));

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
  }

  @Test
  void testSetFromHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.setFromHeader(new FromHeader(new Header("Hname", "42")));

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
  }

  @Test
  void testSetFromHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRecordRouteHeader(new RecordRouteHeader(new NameAddress("Str")));

    // Act
    sipMessage.setFromHeader(new FromHeader(new Header("Hname", "42")));

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
  }

  @Test
  void testSetFromHeader4() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");
    SipMessage createRequestResult = BasicSipMessageFactory.createRequest("Method", request_uri, to, from, "Call id",
        1L, "Local tag", "Remote tag", contact, "Not all who wander are lost",
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    // Act
    createRequestResult.setFromHeader(new FromHeader(new Header("Hname", "42")));

    // Assert
    assertNull(createRequestResult.getAcceptEncodingHeader());
  }

  @Test
  void testSetFromHeader5() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.setFromHeader(new FromHeader(new Header("Hname", "42")));

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
  }

  @Test
  void testRemoveFromHeader() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.removeFromHeader();

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
  }

  @Test
  void testRemoveFromHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.removeFromHeader();

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testRemoveFromHeader3() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");
    SipMessage createRequestResult = BasicSipMessageFactory.createRequest("Method", request_uri, to, from, "Call id",
        1L, "Local tag", "Remote tag", contact, "Not all who wander are lost",
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    // Act
    createRequestResult.removeFromHeader();

    // Assert
    Vector expectedHeaders = createRequestResult.headers;
    assertEquals(expectedHeaders, createRequestResult.getHeaders());
  }

  @Test
  void testHasToHeader() {
    // Arrange, Act and Assert
    assertFalse((new SipMessage("Str")).hasToHeader());
  }

  @Test
  void testHasToHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertFalse(sipMessage.hasToHeader());
  }

  @Test
  void testHasToHeader3() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");

    // Act and Assert
    assertTrue(
        BasicSipMessageFactory
            .createRequest("Method", request_uri, to, from, "Call id", 1L, "Local tag", "Remote tag", contact,
                "Not all who wander are lost", "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"))
            .hasToHeader());
  }

  @Test
  void testGetToHeader() {
    // Arrange, Act and Assert
    assertNull((new SipMessage("Str")).getToHeader());
  }

  @Test
  void testGetToHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertNull(sipMessage.getToHeader());
  }

  @Test
  void testGetToHeader3() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");

    // Act
    ToHeader actualToHeader = BasicSipMessageFactory
        .createRequest("Method", request_uri, to, from, "Call id", 1L, "Local tag", "Remote tag", contact,
            "Not all who wander are lost", "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"))
        .getToHeader();

    // Assert
    assertEquals("To", actualToHeader.getName());
    assertEquals("<sip:Str>;tag=Remote tag", actualToHeader.getValue());
  }

  @Test
  void testSetToHeader() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.setToHeader(new ToHeader(new Header("Hname", "42")));

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
  }

  @Test
  void testSetToHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.setToHeader(new ToHeader(new Header("Hname", "42")));

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
  }

  @Test
  void testSetToHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRecordRouteHeader(new RecordRouteHeader(new NameAddress("Str")));

    // Act
    sipMessage.setToHeader(new ToHeader(new Header("Hname", "42")));

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
  }

  @Test
  void testSetToHeader4() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");
    SipMessage createRequestResult = BasicSipMessageFactory.createRequest("Method", request_uri, to, from, "Call id",
        1L, "Local tag", "Remote tag", contact, "Not all who wander are lost",
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    // Act
    createRequestResult.setToHeader(new ToHeader(new Header("Hname", "42")));

    // Assert
    assertNull(createRequestResult.getAcceptEncodingHeader());
  }

  @Test
  void testSetToHeader5() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.setToHeader(new ToHeader(new Header("Hname", "42")));

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
  }

  @Test
  void testRemoveToHeader() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.removeToHeader();

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
  }

  @Test
  void testRemoveToHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.removeToHeader();

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testRemoveToHeader3() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");
    SipMessage createRequestResult = BasicSipMessageFactory.createRequest("Method", request_uri, to, from, "Call id",
        1L, "Local tag", "Remote tag", contact, "Not all who wander are lost",
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    // Act
    createRequestResult.removeToHeader();

    // Assert
    Vector expectedHeaders = createRequestResult.headers;
    assertEquals(expectedHeaders, createRequestResult.getHeaders());
  }

  @Test
  void testHasContactHeader() {
    // Arrange, Act and Assert
    assertFalse((new SipMessage("Str")).hasContactHeader());
  }

  @Test
  void testHasContactHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertFalse(sipMessage.hasContactHeader());
  }

  @Test
  void testHasContactHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addContactHeader(new ContactHeader());

    // Act and Assert
    assertTrue(sipMessage.hasContactHeader());
  }

  @Test
  void testGetContactHeader() {
    // Arrange, Act and Assert
    assertNull((new SipMessage("Str")).getContactHeader());
  }

  @Test
  void testGetContactHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertNull(sipMessage.getContactHeader());
  }

  @Test
  void testGetContactHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addContactHeader(new ContactHeader());

    // Act
    ContactHeader actualContactHeader = sipMessage.getContactHeader();

    // Assert
    assertEquals("*", actualContactHeader.getValue());
    assertEquals("Contact", actualContactHeader.getName());
  }

  @Test
  void testAddContactHeader() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.addContactHeader(new ContactHeader());

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
  }

  @Test
  void testAddContactHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.addContactHeader(new ContactHeader());

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
  }

  @Test
  void testAddContactHeader3() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");
    SipMessage createRequestResult = BasicSipMessageFactory.createRequest("Method", request_uri, to, from, "Call id",
        1L, "Local tag", "Remote tag", contact, "Not all who wander are lost",
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    // Act
    createRequestResult.addContactHeader(new ContactHeader());

    // Assert
    assertNull(createRequestResult.getAcceptEncodingHeader());
  }

  @Test
  void testAddContactHeader4() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Content-Type"));

    // Act
    sipMessage.addContactHeader(new ContactHeader());

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
  }

  @Test
  void testSetContactHeader() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.setContactHeader(new ContactHeader());

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
  }

  @Test
  void testSetContactHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.setContactHeader(new ContactHeader());

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
  }

  @Test
  void testSetContactHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addContactHeader(new ContactHeader());

    // Act
    sipMessage.setContactHeader(new ContactHeader());

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
  }

  @Test
  void testSetContactHeader4() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");
    SipMessage createRequestResult = BasicSipMessageFactory.createRequest("Method", request_uri, to, from, "Call id",
        1L, "Local tag", "Remote tag", contact, "Not all who wander are lost",
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    // Act
    createRequestResult.setContactHeader(new ContactHeader());

    // Assert
    assertNull(createRequestResult.getAcceptEncodingHeader());
  }

  @Test
  void testSetContactHeader5() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Content-Type"));

    // Act
    sipMessage.setContactHeader(new ContactHeader());

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
  }

  @Test
  void testRemoveContacts() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.removeContacts();

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
  }

  @Test
  void testRemoveContacts2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.removeContacts();

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testRemoveContacts3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addContactHeader(new ContactHeader());

    // Act
    sipMessage.removeContacts();

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
  }

  @Test
  void testHasViaHeader() {
    // Arrange, Act and Assert
    assertFalse((new SipMessage("Str")).hasViaHeader());
  }

  @Test
  void testHasViaHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertFalse(sipMessage.hasViaHeader());
  }

  @Test
  void testHasViaHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addViaHeader(new ViaHeader("42"));

    // Act and Assert
    assertTrue(sipMessage.hasViaHeader());
  }

  @Test
  void testGetViaHeader() {
    // Arrange, Act and Assert
    assertNull((new SipMessage("Str")).getViaHeader());
  }

  @Test
  void testGetViaHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertNull(sipMessage.getViaHeader());
  }

  @Test
  void testGetViaHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addViaHeader(new ViaHeader("42"));

    // Act
    ViaHeader actualViaHeader = sipMessage.getViaHeader();

    // Assert
    assertEquals("42", actualViaHeader.getValue());
    assertEquals("Via", actualViaHeader.getName());
  }

  @Test
  void testGetViaHeader4() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addViaHeader(new ViaHeader("Hvalue"));

    // Act
    ViaHeader actualViaHeader = sipMessage.getViaHeader();

    // Assert
    assertEquals("Hvalue", actualViaHeader.getValue());
    assertEquals("Via", actualViaHeader.getName());
  }

  @Test
  void testAddViaHeader() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.addViaHeader(new ViaHeader("42"));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetVias() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.setVias(new MultipleHeader("Hname"));

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
    assertEquals(11, sipMessage.getLength());
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetVias2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.setVias(new MultipleHeader("Hname"));

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
    assertEquals(20, sipMessage.getLength());
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetVias3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addViaHeader(new ViaHeader("42"));

    // Act
    sipMessage.setVias(new MultipleHeader("Hname"));

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
    assertEquals(11, sipMessage.getLength());
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetVias4() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");
    SipMessage createRequestResult = BasicSipMessageFactory.createRequest("Method", request_uri, to, from, "Call id",
        1L, "Local tag", "Remote tag", contact, "Not all who wander are lost",
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    // Act
    createRequestResult.setVias(new MultipleHeader("Hname"));

    // Assert
    assertNull(createRequestResult.getAcceptEncodingHeader());
    assertEquals(289, createRequestResult.getLength());
    Vector expectedHeaders = createRequestResult.headers;
    assertEquals(expectedHeaders, createRequestResult.getHeaders());
  }

  @Test
  void testRemoveViaHeader() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addViaHeader(new ViaHeader("42"));

    // Act
    sipMessage.removeViaHeader();

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
  }

  @Test
  void testRemoveViaHeader2() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");
    SipMessage createRequestResult = BasicSipMessageFactory.createRequest("Method", request_uri, to, from, "Call id",
        1L, "Local tag", "Remote tag", contact, "Not all who wander are lost",
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    // Act
    createRequestResult.removeViaHeader();

    // Assert
    Vector expectedHeaders = createRequestResult.headers;
    assertEquals(expectedHeaders, createRequestResult.getHeaders());
  }

  @Test
  void testRemoveViaHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addViaHeader(new ViaHeader("42"));
    sipMessage.addHeader(new Header("Hname", "42"), true);

    // Act
    sipMessage.removeViaHeader();

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testRemoveVias() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.removeVias();

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
  }

  @Test
  void testRemoveVias2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.removeVias();

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testRemoveVias3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addViaHeader(new ViaHeader("42"));

    // Act
    sipMessage.removeVias();

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
  }

  @Test
  void testHasRouteHeader() {
    // Arrange, Act and Assert
    assertFalse((new SipMessage("Str")).hasRouteHeader());
  }

  @Test
  void testHasRouteHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertFalse(sipMessage.hasRouteHeader());
  }

  @Test
  void testHasRouteHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRouteHeader(new RouteHeader(new NameAddress("Str")));

    // Act and Assert
    assertTrue(sipMessage.hasRouteHeader());
  }

  @Test
  void testGetRouteHeader() {
    // Arrange, Act and Assert
    assertNull((new SipMessage("Str")).getRouteHeader());
  }

  @Test
  void testGetRouteHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertNull(sipMessage.getRouteHeader());
  }

  @Test
  void testGetRouteHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRouteHeader(new RouteHeader(new NameAddress("Str")));

    // Act
    RouteHeader actualRouteHeader = sipMessage.getRouteHeader();

    // Assert
    assertEquals("Route", actualRouteHeader.getName());
    assertEquals("<sip:Str>", actualRouteHeader.getValue());
  }

  @Test
  void testAddRouteHeader() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.addRouteHeader(new RouteHeader(new NameAddress("Str")));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testAddRouteHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.addRouteHeader(new RouteHeader(new NameAddress("Str")));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testAddRouteHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addViaHeader(new ViaHeader("42"));

    // Act
    sipMessage.addRouteHeader(new RouteHeader(new NameAddress("Str")));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testAddRoutes() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
    assertEquals(11, sipMessage.getLength());
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testAddRoutes2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
    assertEquals(20, sipMessage.getLength());
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testAddRoutes3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addViaHeader(new ViaHeader("42"));

    // Act
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
    assertEquals(20, sipMessage.getLength());
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetRoutes() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.setRoutes(new MultipleHeader("Hname"));

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
    assertEquals(11, sipMessage.getLength());
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetRoutes2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.setRoutes(new MultipleHeader("Hname"));

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
    assertEquals(20, sipMessage.getLength());
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetRoutes3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addViaHeader(new ViaHeader("42"));

    // Act
    sipMessage.setRoutes(new MultipleHeader("Hname"));

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
    assertEquals(20, sipMessage.getLength());
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetRoutes4() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRouteHeader(new RouteHeader(new NameAddress("Str")));

    // Act
    sipMessage.setRoutes(new MultipleHeader("Hname"));

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
    assertEquals(11, sipMessage.getLength());
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetRoutes5() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRouteHeader(new RouteHeader(new NameAddress("Str")));
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.setRoutes(new MultipleHeader("Hname"));

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
    assertEquals(20, sipMessage.getLength());
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testRemoveRouteHeader() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRouteHeader(new RouteHeader(new NameAddress("Str")));

    // Act
    sipMessage.removeRouteHeader();

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
  }

  @Test
  void testRemoveRouteHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRouteHeader(new RouteHeader(new NameAddress("Str")));
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.removeRouteHeader();

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testRemoveRouteHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRouteHeader(new RouteHeader(new NameAddress("Str")));
    sipMessage.addViaHeader(new ViaHeader("42"));

    // Act
    sipMessage.removeRouteHeader();

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testRemoveRoutes() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.removeRoutes();

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
  }

  @Test
  void testRemoveRoutes2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.removeRoutes();

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testRemoveRoutes3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRouteHeader(new RouteHeader(new NameAddress("Str")));

    // Act
    sipMessage.removeRoutes();

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
  }

  @Test
  void testHasRecordRouteHeader() {
    // Arrange, Act and Assert
    assertFalse((new SipMessage("Str")).hasRecordRouteHeader());
  }

  @Test
  void testHasRecordRouteHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertFalse(sipMessage.hasRecordRouteHeader());
  }

  @Test
  void testHasRecordRouteHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRecordRouteHeader(new RecordRouteHeader(new NameAddress("Str")));

    // Act and Assert
    assertTrue(sipMessage.hasRecordRouteHeader());
  }

  @Test
  void testGetRecordRouteHeader() {
    // Arrange, Act and Assert
    assertNull((new SipMessage("Str")).getRecordRouteHeader());
  }

  @Test
  void testGetRecordRouteHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertNull(sipMessage.getRecordRouteHeader());
  }

  @Test
  void testGetRecordRouteHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRecordRouteHeader(new RecordRouteHeader(new NameAddress("Str")));

    // Act
    RecordRouteHeader actualRecordRouteHeader = sipMessage.getRecordRouteHeader();

    // Assert
    assertEquals("Record-Route", actualRecordRouteHeader.getName());
    assertEquals("<sip:Str>", actualRecordRouteHeader.getValue());
  }

  @Test
  void testAddRecordRouteHeader() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.addRecordRouteHeader(new RecordRouteHeader(new NameAddress("Str")));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testAddRecordRouteHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.addRecordRouteHeader(new RecordRouteHeader(new NameAddress("Str")));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testAddRecordRouteHeader3() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");
    SipMessage createRequestResult = BasicSipMessageFactory.createRequest("Method", request_uri, to, from, "Call id",
        1L, "Local tag", "Remote tag", contact, "Not all who wander are lost",
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    // Act
    createRequestResult.addRecordRouteHeader(new RecordRouteHeader(new NameAddress("Str")));

    // Assert
    Vector expectedHeaders = createRequestResult.headers;
    assertEquals(expectedHeaders, createRequestResult.getHeaders());
  }

  @Test
  void testSetRecordRoutes() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.setRecordRoutes(new MultipleHeader("Hname"));

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
    assertEquals(11, sipMessage.getLength());
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetRecordRoutes2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.setRecordRoutes(new MultipleHeader("Hname"));

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
    assertEquals(20, sipMessage.getLength());
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetRecordRoutes3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRecordRouteHeader(new RecordRouteHeader(new NameAddress("Str")));

    // Act
    sipMessage.setRecordRoutes(new MultipleHeader("Hname"));

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
    assertEquals(11, sipMessage.getLength());
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetRecordRoutes4() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");
    SipMessage createRequestResult = BasicSipMessageFactory.createRequest("Method", request_uri, to, from, "Call id",
        1L, "Local tag", "Remote tag", contact, "Not all who wander are lost",
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    // Act
    createRequestResult.setRecordRoutes(new MultipleHeader("Hname"));

    // Assert
    assertNull(createRequestResult.getAcceptEncodingHeader());
    assertEquals(340, createRequestResult.getLength());
    Vector expectedHeaders = createRequestResult.headers;
    assertEquals(expectedHeaders, createRequestResult.getHeaders());
  }

  @Test
  void testSetRecordRoutes5() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRecordRouteHeader(new RecordRouteHeader(new NameAddress("Str")));
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.setRecordRoutes(new MultipleHeader("Hname"));

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
    assertEquals(20, sipMessage.getLength());
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testRemoveRecordRouteHeader() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRecordRouteHeader(new RecordRouteHeader(new NameAddress("Str")));

    // Act
    sipMessage.removeRecordRouteHeader();

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
  }

  @Test
  void testRemoveRecordRouteHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRecordRouteHeader(new RecordRouteHeader(new NameAddress("Str")));
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.removeRecordRouteHeader();

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testRemoveRecordRouteHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRecordRouteHeader(new RecordRouteHeader(new NameAddress("Str")));
    sipMessage.addRecordRouteHeader(new RecordRouteHeader(new NameAddress("Str")));

    // Act
    sipMessage.removeRecordRouteHeader();

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
    assertEquals(27, sipMessage.getLength());
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testRemoveRecordRouteHeader4() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");
    SipMessage createRequestResult = BasicSipMessageFactory.createRequest("Method", request_uri, to, from, "Call id",
        1L, "Local tag", "Remote tag", contact, "Not all who wander are lost",
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));
    createRequestResult.addRecordRouteHeader(new RecordRouteHeader(new NameAddress("Str")));

    // Act
    createRequestResult.removeRecordRouteHeader();

    // Assert
    Vector expectedHeaders = createRequestResult.headers;
    assertEquals(expectedHeaders, createRequestResult.getHeaders());
  }

  @Test
  void testRemoveRecordRoutes() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.removeRecordRoutes();

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
  }

  @Test
  void testRemoveRecordRoutes2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.removeRecordRoutes();

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testRemoveRecordRoutes3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRecordRouteHeader(new RecordRouteHeader(new NameAddress("Str")));

    // Act
    sipMessage.removeRecordRoutes();

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
  }

  @Test
  void testHasServiceRouteHeader() {
    // Arrange, Act and Assert
    assertFalse((new SipMessage("Str")).hasServiceRouteHeader());
  }

  @Test
  void testHasServiceRouteHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertFalse(sipMessage.hasServiceRouteHeader());
  }

  @Test
  void testHasServiceRouteHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Service-Route"));

    // Act and Assert
    assertTrue(sipMessage.hasServiceRouteHeader());
  }

  @Test
  void testRemoveServiceRoutes() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.removeServiceRoutes();

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
  }

  @Test
  void testRemoveServiceRoutes2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.removeServiceRoutes();

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testRemoveServiceRoutes3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Service-Route"));

    // Act
    sipMessage.removeServiceRoutes();

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
  }

  @Test
  void testHasCSeqHeader() {
    // Arrange, Act and Assert
    assertFalse((new SipMessage("Str")).hasCSeqHeader());
  }

  @Test
  void testHasCSeqHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertFalse(sipMessage.hasCSeqHeader());
  }

  @Test
  void testHasCSeqHeader3() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");

    // Act and Assert
    assertTrue(
        BasicSipMessageFactory
            .createRequest("Method", request_uri, to, from, "Call id", 1L, "Local tag", "Remote tag", contact,
                "Not all who wander are lost", "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"))
            .hasCSeqHeader());
  }

  @Test
  void testGetCSeqHeader() {
    // Arrange, Act and Assert
    assertNull((new SipMessage("Str")).getCSeqHeader());
  }

  @Test
  void testGetCSeqHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertNull(sipMessage.getCSeqHeader());
  }

  @Test
  void testGetCSeqHeader3() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");

    // Act
    CSeqHeader actualCSeqHeader = BasicSipMessageFactory
        .createRequest("Method", request_uri, to, from, "Call id", 1L, "Local tag", "Remote tag", contact,
            "Not all who wander are lost", "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"))
        .getCSeqHeader();

    // Assert
    assertEquals("1 Method", actualCSeqHeader.getValue());
    assertEquals("CSeq", actualCSeqHeader.getName());
  }

  @Test
  void testSetCSeqHeader() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.setCSeqHeader(new CSeqHeader("42"));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetCSeqHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.setCSeqHeader(new CSeqHeader("42"));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetCSeqHeader3() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");
    SipMessage createRequestResult = BasicSipMessageFactory.createRequest("Method", request_uri, to, from, "Call id",
        1L, "Local tag", "Remote tag", contact, "Not all who wander are lost",
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    // Act
    createRequestResult.setCSeqHeader(new CSeqHeader("42"));

    // Assert
    Vector expectedHeaders = createRequestResult.headers;
    assertEquals(expectedHeaders, createRequestResult.getHeaders());
  }

  @Test
  void testSetCSeqHeader4() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Content-Length"));

    // Act
    sipMessage.setCSeqHeader(new CSeqHeader("42"));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetCSeqHeader5() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Content-Type"));

    // Act
    sipMessage.setCSeqHeader(new CSeqHeader("42"));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetCSeqHeader6() throws UnsupportedEncodingException {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.setBody("Not all who wander are lost", "Not all who wander are lost", "AAAAAAAA".getBytes("UTF-8"));
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.setCSeqHeader(new CSeqHeader("42"));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testRemoveCSeqHeader() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.removeCSeqHeader();

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
  }

  @Test
  void testRemoveCSeqHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.removeCSeqHeader();

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testRemoveCSeqHeader3() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");
    SipMessage createRequestResult = BasicSipMessageFactory.createRequest("Method", request_uri, to, from, "Call id",
        1L, "Local tag", "Remote tag", contact, "Not all who wander are lost",
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    // Act
    createRequestResult.removeCSeqHeader();

    // Assert
    Vector expectedHeaders = createRequestResult.headers;
    assertEquals(expectedHeaders, createRequestResult.getHeaders());
  }

  @Test
  void testHasCallIdHeader() {
    // Arrange, Act and Assert
    assertFalse((new SipMessage("Str")).hasCallIdHeader());
  }

  @Test
  void testHasCallIdHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertFalse(sipMessage.hasCallIdHeader());
  }

  @Test
  void testHasCallIdHeader3() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");

    // Act and Assert
    assertTrue(
        BasicSipMessageFactory
            .createRequest("Method", request_uri, to, from, "Call id", 1L, "Local tag", "Remote tag", contact,
                "Not all who wander are lost", "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"))
            .hasCallIdHeader());
  }

  @Test
  void testSetCallIdHeader() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.setCallIdHeader(new CallIdHeader("42"));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetCallIdHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.setCallIdHeader(new CallIdHeader("42"));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetCallIdHeader3() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");
    SipMessage createRequestResult = BasicSipMessageFactory.createRequest("Method", request_uri, to, from, "Call id",
        1L, "Local tag", "Remote tag", contact, "Not all who wander are lost",
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    // Act
    createRequestResult.setCallIdHeader(new CallIdHeader("42"));

    // Assert
    Vector expectedHeaders = createRequestResult.headers;
    assertEquals(expectedHeaders, createRequestResult.getHeaders());
  }

  @Test
  void testSetCallIdHeader4() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Content-Length"));

    // Act
    sipMessage.setCallIdHeader(new CallIdHeader("42"));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetCallIdHeader5() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Content-Type"));

    // Act
    sipMessage.setCallIdHeader(new CallIdHeader("42"));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetCallIdHeader6() throws UnsupportedEncodingException {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.setBody("Not all who wander are lost", "Not all who wander are lost", "AAAAAAAA".getBytes("UTF-8"));
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.setCallIdHeader(new CallIdHeader("42"));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testGetCallIdHeader() {
    // Arrange, Act and Assert
    assertNull((new SipMessage("Str")).getCallIdHeader());
  }

  @Test
  void testGetCallIdHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertNull(sipMessage.getCallIdHeader());
  }

  @Test
  void testGetCallIdHeader3() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");

    // Act
    CallIdHeader actualCallIdHeader = BasicSipMessageFactory
        .createRequest("Method", request_uri, to, from, "Call id", 1L, "Local tag", "Remote tag", contact,
            "Not all who wander are lost", "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"))
        .getCallIdHeader();

    // Assert
    assertEquals("Call id", actualCallIdHeader.getValue());
    assertEquals("Call-ID", actualCallIdHeader.getName());
  }

  @Test
  void testRemoveCallIdHeader() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.removeCallIdHeader();

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
  }

  @Test
  void testRemoveCallIdHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.removeCallIdHeader();

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testRemoveCallIdHeader3() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");
    SipMessage createRequestResult = BasicSipMessageFactory.createRequest("Method", request_uri, to, from, "Call id",
        1L, "Local tag", "Remote tag", contact, "Not all who wander are lost",
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    // Act
    createRequestResult.removeCallIdHeader();

    // Assert
    Vector expectedHeaders = createRequestResult.headers;
    assertEquals(expectedHeaders, createRequestResult.getHeaders());
  }

  @Test
  void testHasSubjectHeader() {
    // Arrange, Act and Assert
    assertFalse((new SipMessage("Str")).hasSubjectHeader());
  }

  @Test
  void testHasSubjectHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertFalse(sipMessage.hasSubjectHeader());
  }

  @Test
  void testHasSubjectHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Subject"));

    // Act and Assert
    assertTrue(sipMessage.hasSubjectHeader());
  }

  @Test
  void testSetSubjectHeader() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.setSubjectHeader(new SubjectHeader("42"));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetSubjectHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.setSubjectHeader(new SubjectHeader("42"));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetSubjectHeader3() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");
    SipMessage createRequestResult = BasicSipMessageFactory.createRequest("Method", request_uri, to, from, "Call id",
        1L, "Local tag", "Remote tag", contact, "Not all who wander are lost",
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    // Act
    createRequestResult.setSubjectHeader(new SubjectHeader("42"));

    // Assert
    Vector expectedHeaders = createRequestResult.headers;
    assertEquals(expectedHeaders, createRequestResult.getHeaders());
  }

  @Test
  void testSetSubjectHeader4() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Content-Type"));

    // Act
    sipMessage.setSubjectHeader(new SubjectHeader("42"));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testGetSubjectHeader() {
    // Arrange, Act and Assert
    assertNull((new SipMessage("Str")).getSubjectHeader());
  }

  @Test
  void testGetSubjectHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertNull(sipMessage.getSubjectHeader());
  }

  @Test
  void testGetSubjectHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Subject"));

    // Act
    SubjectHeader actualSubjectHeader = sipMessage.getSubjectHeader();

    // Assert
    assertEquals("Subject", actualSubjectHeader.getName());
    assertEquals("", actualSubjectHeader.getValue());
  }

  @Test
  void testRemoveSubjectHeader() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.removeSubjectHeader();

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
  }

  @Test
  void testRemoveSubjectHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.removeSubjectHeader();

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testRemoveSubjectHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Subject"));

    // Act
    sipMessage.removeSubjectHeader();

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
  }

  @Test
  void testHasDateHeader() {
    // Arrange, Act and Assert
    assertFalse((new SipMessage("Str")).hasDateHeader());
  }

  @Test
  void testHasDateHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertFalse(sipMessage.hasDateHeader());
  }

  @Test
  void testHasDateHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Date"));

    // Act and Assert
    assertTrue(sipMessage.hasDateHeader());
  }

  @Test
  void testGetDateHeader() {
    // Arrange, Act and Assert
    assertNull((new SipMessage("Str")).getDateHeader());
  }

  @Test
  void testGetDateHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertNull(sipMessage.getDateHeader());
  }

  @Test
  void testGetDateHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Date"));

    // Act
    DateHeader actualDateHeader = sipMessage.getDateHeader();

    // Assert
    assertEquals("", actualDateHeader.getValue());
    assertEquals("Date", actualDateHeader.getName());
  }

  @Test
  void testSetDateHeader() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.setDateHeader(new DateHeader("42"));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetDateHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.setDateHeader(new DateHeader("42"));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetDateHeader3() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");
    SipMessage createRequestResult = BasicSipMessageFactory.createRequest("Method", request_uri, to, from, "Call id",
        1L, "Local tag", "Remote tag", contact, "Not all who wander are lost",
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    // Act
    createRequestResult.setDateHeader(new DateHeader("42"));

    // Assert
    Vector expectedHeaders = createRequestResult.headers;
    assertEquals(expectedHeaders, createRequestResult.getHeaders());
  }

  @Test
  void testSetDateHeader4() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Content-Type"));

    // Act
    sipMessage.setDateHeader(new DateHeader("42"));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testRemoveDateHeader() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.removeDateHeader();

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
  }

  @Test
  void testRemoveDateHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.removeDateHeader();

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testRemoveDateHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Date"));

    // Act
    sipMessage.removeDateHeader();

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
  }

  @Test
  void testHasUserAgentHeader() {
    // Arrange, Act and Assert
    assertFalse((new SipMessage("Str")).hasUserAgentHeader());
  }

  @Test
  void testHasUserAgentHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertFalse(sipMessage.hasUserAgentHeader());
  }

  @Test
  void testHasUserAgentHeader3() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");

    // Act and Assert
    assertTrue(
        BasicSipMessageFactory
            .createRequest("Method", request_uri, to, from, "Call id", 1L, "Local tag", "Remote tag", contact,
                "Not all who wander are lost", "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"))
            .hasUserAgentHeader());
  }

  @Test
  void testSetUserAgentHeader() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.setUserAgentHeader(new UserAgentHeader("Info"));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetUserAgentHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.setUserAgentHeader(new UserAgentHeader("Info"));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetUserAgentHeader3() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");
    SipMessage createRequestResult = BasicSipMessageFactory.createRequest("Method", request_uri, to, from, "Call id",
        1L, "Local tag", "Remote tag", contact, "Not all who wander are lost",
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    // Act
    createRequestResult.setUserAgentHeader(new UserAgentHeader("Info"));

    // Assert
    Vector expectedHeaders = createRequestResult.headers;
    assertEquals(expectedHeaders, createRequestResult.getHeaders());
  }

  @Test
  void testSetUserAgentHeader4() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Content-Length"));

    // Act
    sipMessage.setUserAgentHeader(new UserAgentHeader("Info"));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetUserAgentHeader5() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Content-Type"));

    // Act
    sipMessage.setUserAgentHeader(new UserAgentHeader("Info"));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetUserAgentHeader6() throws UnsupportedEncodingException {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.setBody("Not all who wander are lost", "Not all who wander are lost", "AAAAAAAA".getBytes("UTF-8"));
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.setUserAgentHeader(new UserAgentHeader("Info"));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testGetUserAgentHeader() {
    // Arrange, Act and Assert
    assertNull((new SipMessage("Str")).getUserAgentHeader());
  }

  @Test
  void testGetUserAgentHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertNull(sipMessage.getUserAgentHeader());
  }

  @Test
  void testGetUserAgentHeader3() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");

    // Act
    UserAgentHeader actualUserAgentHeader = BasicSipMessageFactory
        .createRequest("Method", request_uri, to, from, "Call id", 1L, "Local tag", "Remote tag", contact,
            "Not all who wander are lost", "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"))
        .getUserAgentHeader();

    // Assert
    assertEquals("mjsip 1.8", actualUserAgentHeader.getInfo());
    assertEquals("User-Agent", actualUserAgentHeader.getName());
  }

  @Test
  void testRemoveUserAgentHeader() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.removeUserAgentHeader();

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
  }

  @Test
  void testRemoveUserAgentHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.removeUserAgentHeader();

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testRemoveUserAgentHeader3() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");
    SipMessage createRequestResult = BasicSipMessageFactory.createRequest("Method", request_uri, to, from, "Call id",
        1L, "Local tag", "Remote tag", contact, "Not all who wander are lost",
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    // Act
    createRequestResult.removeUserAgentHeader();

    // Assert
    Vector expectedHeaders = createRequestResult.headers;
    assertEquals(expectedHeaders, createRequestResult.getHeaders());
  }

  @Test
  void testHasServerHeader() {
    // Arrange, Act and Assert
    assertFalse((new SipMessage("Str")).hasServerHeader());
  }

  @Test
  void testHasServerHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertFalse(sipMessage.hasServerHeader());
  }

  @Test
  void testHasServerHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Server"));

    // Act and Assert
    assertTrue(sipMessage.hasServerHeader());
  }

  @Test
  void testSetServerHeader() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.setServerHeader(new ServerHeader("Info"));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetServerHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.setServerHeader(new ServerHeader("Info"));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetServerHeader3() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");
    SipMessage createRequestResult = BasicSipMessageFactory.createRequest("Method", request_uri, to, from, "Call id",
        1L, "Local tag", "Remote tag", contact, "Not all who wander are lost",
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    // Act
    createRequestResult.setServerHeader(new ServerHeader("Info"));

    // Assert
    Vector expectedHeaders = createRequestResult.headers;
    assertEquals(expectedHeaders, createRequestResult.getHeaders());
  }

  @Test
  void testSetServerHeader4() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Content-Type"));

    // Act
    sipMessage.setServerHeader(new ServerHeader("Info"));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testGetServerHeader() {
    // Arrange, Act and Assert
    assertNull((new SipMessage("Str")).getServerHeader());
  }

  @Test
  void testGetServerHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertNull(sipMessage.getServerHeader());
  }

  @Test
  void testGetServerHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Server"));

    // Act
    ServerHeader actualServerHeader = sipMessage.getServerHeader();

    // Assert
    assertEquals("", actualServerHeader.getInfo());
    assertEquals("Server", actualServerHeader.getName());
  }

  @Test
  void testRemoveServerHeader() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.removeServerHeader();

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
  }

  @Test
  void testRemoveServerHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.removeServerHeader();

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testRemoveServerHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Server"));

    // Act
    sipMessage.removeServerHeader();

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
  }

  @Test
  void testHasAcceptHeader() {
    // Arrange, Act and Assert
    assertFalse((new SipMessage("Str")).hasAcceptHeader());
  }

  @Test
  void testHasAcceptHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertFalse(sipMessage.hasAcceptHeader());
  }

  @Test
  void testHasAcceptHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Accept"));

    // Act and Assert
    assertTrue(sipMessage.hasAcceptHeader());
  }

  @Test
  void testSetAcceptHeader() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.setAcceptHeader(new AcceptHeader("42"));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetAcceptHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.setAcceptHeader(new AcceptHeader("42"));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetAcceptHeader3() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");
    SipMessage createRequestResult = BasicSipMessageFactory.createRequest("Method", request_uri, to, from, "Call id",
        1L, "Local tag", "Remote tag", contact, "Not all who wander are lost",
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    // Act
    createRequestResult.setAcceptHeader(new AcceptHeader("42"));

    // Assert
    Vector expectedHeaders = createRequestResult.headers;
    assertEquals(expectedHeaders, createRequestResult.getHeaders());
  }

  @Test
  void testSetAcceptHeader4() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Content-Type"));

    // Act
    sipMessage.setAcceptHeader(new AcceptHeader("42"));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testGetAcceptHeader() {
    // Arrange, Act and Assert
    assertNull((new SipMessage("Str")).getAcceptHeader());
  }

  @Test
  void testGetAcceptHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertNull(sipMessage.getAcceptHeader());
  }

  @Test
  void testGetAcceptHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Accept"));

    // Act
    AcceptHeader actualAcceptHeader = sipMessage.getAcceptHeader();

    // Assert
    assertEquals("", actualAcceptHeader.getAcceptRange());
    assertEquals("Accept", actualAcceptHeader.getName());
  }

  @Test
  void testRemoveAcceptHeader() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.removeAcceptHeader();

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
  }

  @Test
  void testRemoveAcceptHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.removeAcceptHeader();

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testRemoveAcceptHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Accept"));

    // Act
    sipMessage.removeAcceptHeader();

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
  }

  @Test
  void testHasAcceptEncodingHeader() {
    // Arrange, Act and Assert
    assertFalse((new SipMessage("Str")).hasAcceptEncodingHeader());
  }

  @Test
  void testHasAcceptEncodingHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertFalse(sipMessage.hasAcceptEncodingHeader());
  }

  @Test
  void testHasAcceptEncodingHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Accept-Encoding"));

    // Act and Assert
    assertTrue(sipMessage.hasAcceptEncodingHeader());
  }

  @Test
  void testSetAcceptEncodingHeader() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.setAcceptEncodingHeader(new AcceptEncodingHeader("42"));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetAcceptEncodingHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.setAcceptEncodingHeader(new AcceptEncodingHeader("42"));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetAcceptEncodingHeader3() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");
    SipMessage createRequestResult = BasicSipMessageFactory.createRequest("Method", request_uri, to, from, "Call id",
        1L, "Local tag", "Remote tag", contact, "Not all who wander are lost",
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    // Act
    createRequestResult.setAcceptEncodingHeader(new AcceptEncodingHeader("42"));

    // Assert
    Vector expectedHeaders = createRequestResult.headers;
    assertEquals(expectedHeaders, createRequestResult.getHeaders());
  }

  @Test
  void testSetAcceptEncodingHeader4() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Content-Type"));

    // Act
    sipMessage.setAcceptEncodingHeader(new AcceptEncodingHeader("42"));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testGetAcceptEncodingHeader() {
    // Arrange, Act and Assert
    assertNull((new SipMessage("Str")).getAcceptEncodingHeader());
  }

  @Test
  void testGetAcceptEncodingHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
  }

  @Test
  void testGetAcceptEncodingHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Accept-Encoding"));

    // Act
    AcceptEncodingHeader actualAcceptEncodingHeader = sipMessage.getAcceptEncodingHeader();

    // Assert
    assertEquals("", actualAcceptEncodingHeader.getAcceptEncoding());
    assertEquals("Accept-Encoding", actualAcceptEncodingHeader.getName());
  }

  @Test
  void testRemoveAcceptEncodingHeader() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.removeAcceptEncodingHeader();

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
  }

  @Test
  void testRemoveAcceptEncodingHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.removeAcceptEncodingHeader();

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testRemoveAcceptEncodingHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Accept-Encoding"));

    // Act
    sipMessage.removeAcceptEncodingHeader();

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
  }

  @Test
  void testHasAcceptLanguageHeader() {
    // Arrange, Act and Assert
    assertFalse((new SipMessage("Str")).hasAcceptLanguageHeader());
  }

  @Test
  void testHasAcceptLanguageHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertFalse(sipMessage.hasAcceptLanguageHeader());
  }

  @Test
  void testHasAcceptLanguageHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Accept-Language"));

    // Act and Assert
    assertTrue(sipMessage.hasAcceptLanguageHeader());
  }

  @Test
  void testSetAcceptLanguageHeader() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.setAcceptLanguageHeader(new AcceptLanguageHeader("42"));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetAcceptLanguageHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.setAcceptLanguageHeader(new AcceptLanguageHeader("42"));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetAcceptLanguageHeader3() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");
    SipMessage createRequestResult = BasicSipMessageFactory.createRequest("Method", request_uri, to, from, "Call id",
        1L, "Local tag", "Remote tag", contact, "Not all who wander are lost",
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    // Act
    createRequestResult.setAcceptLanguageHeader(new AcceptLanguageHeader("42"));

    // Assert
    Vector expectedHeaders = createRequestResult.headers;
    assertEquals(expectedHeaders, createRequestResult.getHeaders());
  }

  @Test
  void testSetAcceptLanguageHeader4() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Content-Type"));

    // Act
    sipMessage.setAcceptLanguageHeader(new AcceptLanguageHeader("42"));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testGetAcceptLanguageHeader() {
    // Arrange, Act and Assert
    assertNull((new SipMessage("Str")).getAcceptLanguageHeader());
  }

  @Test
  void testGetAcceptLanguageHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertNull(sipMessage.getAcceptLanguageHeader());
  }

  @Test
  void testGetAcceptLanguageHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Accept-Language"));

    // Act
    AcceptLanguageHeader actualAcceptLanguageHeader = sipMessage.getAcceptLanguageHeader();

    // Assert
    assertEquals("", actualAcceptLanguageHeader.getAcceptLanguage());
    assertEquals("Accept-Language", actualAcceptLanguageHeader.getName());
  }

  @Test
  void testRemoveAcceptLanguageHeader() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.removeAcceptLanguageHeader();

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
  }

  @Test
  void testRemoveAcceptLanguageHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.removeAcceptLanguageHeader();

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testRemoveAcceptLanguageHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Accept-Language"));

    // Act
    sipMessage.removeAcceptLanguageHeader();

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
  }

  @Test
  void testHasAlertInfoHeader() {
    // Arrange, Act and Assert
    assertFalse((new SipMessage("Str")).hasAlertInfoHeader());
  }

  @Test
  void testHasAlertInfoHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertFalse(sipMessage.hasAlertInfoHeader());
  }

  @Test
  void testHasAlertInfoHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Alert-Info"));

    // Act and Assert
    assertTrue(sipMessage.hasAlertInfoHeader());
  }

  @Test
  void testSetAlertInfoHeader() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.setAlertInfoHeader(new AlertInfoHeader("Absolute uri"));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetAlertInfoHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.setAlertInfoHeader(new AlertInfoHeader("Absolute uri"));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetAlertInfoHeader3() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");
    SipMessage createRequestResult = BasicSipMessageFactory.createRequest("Method", request_uri, to, from, "Call id",
        1L, "Local tag", "Remote tag", contact, "Not all who wander are lost",
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    // Act
    createRequestResult.setAlertInfoHeader(new AlertInfoHeader("Absolute uri"));

    // Assert
    Vector expectedHeaders = createRequestResult.headers;
    assertEquals(expectedHeaders, createRequestResult.getHeaders());
  }

  @Test
  void testSetAlertInfoHeader4() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Content-Type"));

    // Act
    sipMessage.setAlertInfoHeader(new AlertInfoHeader("Absolute uri"));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testGetAlertInfoHeader() {
    // Arrange, Act and Assert
    assertNull((new SipMessage("Str")).getAlertInfoHeader());
  }

  @Test
  void testGetAlertInfoHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertNull(sipMessage.getAlertInfoHeader());
  }

  @Test
  void testGetAlertInfoHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Alert-Info"));

    // Act
    AlertInfoHeader actualAlertInfoHeader = sipMessage.getAlertInfoHeader();

    // Assert
    assertEquals("", actualAlertInfoHeader.getAbsoluteURI());
    assertEquals("Alert-Info", actualAlertInfoHeader.getName());
  }

  @Test
  void testRemoveAlertInfoHeader() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.removeAlertInfoHeader();

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
  }

  @Test
  void testRemoveAlertInfoHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.removeAlertInfoHeader();

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testRemoveAlertInfoHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Alert-Info"));

    // Act
    sipMessage.removeAlertInfoHeader();

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
  }

  @Test
  void testHasAllowHeader() {
    // Arrange, Act and Assert
    assertFalse((new SipMessage("Str")).hasAllowHeader());
  }

  @Test
  void testHasAllowHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertFalse(sipMessage.hasAllowHeader());
  }

  @Test
  void testHasAllowHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Allow"));

    // Act and Assert
    assertTrue(sipMessage.hasAllowHeader());
  }

  @Test
  void testSetAllowHeader() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.setAllowHeader(new AllowHeader("42"));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetAllowHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.setAllowHeader(new AllowHeader("42"));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetAllowHeader3() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");
    SipMessage createRequestResult = BasicSipMessageFactory.createRequest("Method", request_uri, to, from, "Call id",
        1L, "Local tag", "Remote tag", contact, "Not all who wander are lost",
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    // Act
    createRequestResult.setAllowHeader(new AllowHeader("42"));

    // Assert
    Vector expectedHeaders = createRequestResult.headers;
    assertEquals(expectedHeaders, createRequestResult.getHeaders());
  }

  @Test
  void testSetAllowHeader4() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Content-Type"));

    // Act
    sipMessage.setAllowHeader(new AllowHeader("42"));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetAllowHeader5() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.setAllowHeader(new AllowHeader(new Header("Hname", "42")));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testGetAllowHeader() {
    // Arrange, Act and Assert
    assertNull((new SipMessage("Str")).getAllowHeader());
  }

  @Test
  void testGetAllowHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertNull(sipMessage.getAllowHeader());
  }

  @Test
  void testGetAllowHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Allow"));

    // Act
    AllowHeader actualAllowHeader = sipMessage.getAllowHeader();

    // Assert
    assertEquals("", actualAllowHeader.getValue());
    assertEquals("Allow", actualAllowHeader.getName());
  }

  @Test
  void testRemoveAllowHeader() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.removeAllowHeader();

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
  }

  @Test
  void testRemoveAllowHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.removeAllowHeader();

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testRemoveAllowHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Allow"));

    // Act
    sipMessage.removeAllowHeader();

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
  }

  @Test
  void testHasExpiresHeader() {
    // Arrange, Act and Assert
    assertFalse((new SipMessage("Str")).hasExpiresHeader());
  }

  @Test
  void testHasExpiresHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertFalse(sipMessage.hasExpiresHeader());
  }

  @Test
  void testHasExpiresHeader3() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");

    // Act and Assert
    assertTrue(
        BasicSipMessageFactory
            .createRequest("Method", request_uri, to, from, "Call id", 1L, "Local tag", "Remote tag", contact,
                "Not all who wander are lost", "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"))
            .hasExpiresHeader());
  }

  @Test
  void testGetExpiresHeader() {
    // Arrange, Act and Assert
    assertNull((new SipMessage("Str")).getExpiresHeader());
  }

  @Test
  void testGetExpiresHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertNull(sipMessage.getExpiresHeader());
  }

  @Test
  void testGetExpiresHeader3() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");

    // Act
    ExpiresHeader actualExpiresHeader = BasicSipMessageFactory
        .createRequest("Method", request_uri, to, from, "Call id", 1L, "Local tag", "Remote tag", contact,
            "Not all who wander are lost", "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"))
        .getExpiresHeader();

    // Assert
    assertEquals("3600", actualExpiresHeader.getValue());
    assertEquals("Expires", actualExpiresHeader.getName());
  }

  @Test
  void testSetExpiresHeader() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.setExpiresHeader(new ExpiresHeader("42"));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetExpiresHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.setExpiresHeader(new ExpiresHeader("42"));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetExpiresHeader3() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");
    SipMessage createRequestResult = BasicSipMessageFactory.createRequest("Method", request_uri, to, from, "Call id",
        1L, "Local tag", "Remote tag", contact, "Not all who wander are lost",
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    // Act
    createRequestResult.setExpiresHeader(new ExpiresHeader("42"));

    // Assert
    Vector expectedHeaders = createRequestResult.headers;
    assertEquals(expectedHeaders, createRequestResult.getHeaders());
  }

  @Test
  void testSetExpiresHeader4() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Content-Length"));

    // Act
    sipMessage.setExpiresHeader(new ExpiresHeader("42"));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetExpiresHeader5() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Content-Type"));

    // Act
    sipMessage.setExpiresHeader(new ExpiresHeader("42"));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetExpiresHeader6() throws UnsupportedEncodingException {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.setBody("Not all who wander are lost", "Not all who wander are lost", "AAAAAAAA".getBytes("UTF-8"));
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.setExpiresHeader(new ExpiresHeader("42"));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testRemoveExpiresHeader() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.removeExpiresHeader();

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
  }

  @Test
  void testRemoveExpiresHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.removeExpiresHeader();

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testRemoveExpiresHeader3() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");
    SipMessage createRequestResult = BasicSipMessageFactory.createRequest("Method", request_uri, to, from, "Call id",
        1L, "Local tag", "Remote tag", contact, "Not all who wander are lost",
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    // Act
    createRequestResult.removeExpiresHeader();

    // Assert
    Vector expectedHeaders = createRequestResult.headers;
    assertEquals(expectedHeaders, createRequestResult.getHeaders());
  }

  @Test
  void testHasAuthenticationInfoHeader() {
    // Arrange, Act and Assert
    assertFalse((new SipMessage("Str")).hasAuthenticationInfoHeader());
  }

  @Test
  void testHasAuthenticationInfoHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertFalse(sipMessage.hasAuthenticationInfoHeader());
  }

  @Test
  void testHasAuthenticationInfoHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Authentication-Info"));

    // Act and Assert
    assertTrue(sipMessage.hasAuthenticationInfoHeader());
  }

  @Test
  void testSetAuthenticationInfoHeader() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.setAuthenticationInfoHeader(new AuthenticationInfoHeader("42"));

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
  }

  @Test
  void testSetAuthenticationInfoHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.setAuthenticationInfoHeader(new AuthenticationInfoHeader("42"));

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
  }

  @Test
  void testSetAuthenticationInfoHeader3() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");
    SipMessage createRequestResult = BasicSipMessageFactory.createRequest("Method", request_uri, to, from, "Call id",
        1L, "Local tag", "Remote tag", contact, "Not all who wander are lost",
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    // Act
    createRequestResult.setAuthenticationInfoHeader(new AuthenticationInfoHeader("42"));

    // Assert
    assertNull(createRequestResult.getAcceptEncodingHeader());
  }

  @Test
  void testSetAuthenticationInfoHeader4() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Content-Type"));

    // Act
    sipMessage.setAuthenticationInfoHeader(new AuthenticationInfoHeader("42"));

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
  }

  @Test
  void testGetAuthenticationInfoHeader() {
    // Arrange, Act and Assert
    assertNull((new SipMessage("Str")).getAuthenticationInfoHeader());
  }

  @Test
  void testGetAuthenticationInfoHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertNull(sipMessage.getAuthenticationInfoHeader());
  }

  @Test
  void testGetAuthenticationInfoHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Authentication-Info"));

    // Act
    AuthenticationInfoHeader actualAuthenticationInfoHeader = sipMessage.getAuthenticationInfoHeader();

    // Assert
    assertEquals("", actualAuthenticationInfoHeader.getValue());
    assertEquals("Authentication-Info", actualAuthenticationInfoHeader.getName());
  }

  @Test
  void testRemoveAuthenticationInfoHeader() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.removeAuthenticationInfoHeader();

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
  }

  @Test
  void testRemoveAuthenticationInfoHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.removeAuthenticationInfoHeader();

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testRemoveAuthenticationInfoHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Authentication-Info"));

    // Act
    sipMessage.removeAuthenticationInfoHeader();

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
  }

  @Test
  void testHasAuthorizationHeader() {
    // Arrange, Act and Assert
    assertFalse((new SipMessage("Str")).hasAuthorizationHeader());
  }

  @Test
  void testHasAuthorizationHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertFalse(sipMessage.hasAuthorizationHeader());
  }

  @Test
  void testHasAuthorizationHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Authorization"));

    // Act and Assert
    assertTrue(sipMessage.hasAuthorizationHeader());
  }

  @Test
  void testSetAuthorizationHeader() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.setAuthorizationHeader(new AuthorizationHeader("42"));

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
  }

  @Test
  void testSetAuthorizationHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.setAuthorizationHeader(new AuthorizationHeader("42"));

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
  }

  @Test
  void testSetAuthorizationHeader3() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");
    SipMessage createRequestResult = BasicSipMessageFactory.createRequest("Method", request_uri, to, from, "Call id",
        1L, "Local tag", "Remote tag", contact, "Not all who wander are lost",
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    // Act
    createRequestResult.setAuthorizationHeader(new AuthorizationHeader("42"));

    // Assert
    assertNull(createRequestResult.getAcceptEncodingHeader());
  }

  @Test
  void testSetAuthorizationHeader4() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Content-Type"));

    // Act
    sipMessage.setAuthorizationHeader(new AuthorizationHeader("42"));

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
  }

  @Test
  void testGetAuthorizationHeader() {
    // Arrange, Act and Assert
    assertNull((new SipMessage("Str")).getAuthorizationHeader());
  }

  @Test
  void testGetAuthorizationHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertNull(sipMessage.getAuthorizationHeader());
  }

  @Test
  void testGetAuthorizationHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Authorization"));

    // Act
    AuthorizationHeader actualAuthorizationHeader = sipMessage.getAuthorizationHeader();

    // Assert
    assertEquals("", actualAuthorizationHeader.getValue());
    assertEquals("Authorization", actualAuthorizationHeader.getName());
  }

  @Test
  void testRemoveAuthorizationHeader() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.removeAuthorizationHeader();

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
  }

  @Test
  void testRemoveAuthorizationHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.removeAuthorizationHeader();

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testRemoveAuthorizationHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Authorization"));

    // Act
    sipMessage.removeAuthorizationHeader();

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
  }

  @Test
  void testHasWwwAuthenticateHeader() {
    // Arrange, Act and Assert
    assertFalse((new SipMessage("Str")).hasWwwAuthenticateHeader());
  }

  @Test
  void testHasWwwAuthenticateHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertFalse(sipMessage.hasWwwAuthenticateHeader());
  }

  @Test
  void testHasWwwAuthenticateHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("WWW-Authenticate"));

    // Act and Assert
    assertTrue(sipMessage.hasWwwAuthenticateHeader());
  }

  @Test
  void testSetWwwAuthenticateHeader() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.setWwwAuthenticateHeader(new WwwAuthenticateHeader("42"));

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
  }

  @Test
  void testSetWwwAuthenticateHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.setWwwAuthenticateHeader(new WwwAuthenticateHeader("42"));

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
  }

  @Test
  void testSetWwwAuthenticateHeader3() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");
    SipMessage createRequestResult = BasicSipMessageFactory.createRequest("Method", request_uri, to, from, "Call id",
        1L, "Local tag", "Remote tag", contact, "Not all who wander are lost",
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    // Act
    createRequestResult.setWwwAuthenticateHeader(new WwwAuthenticateHeader("42"));

    // Assert
    assertNull(createRequestResult.getAcceptEncodingHeader());
  }

  @Test
  void testSetWwwAuthenticateHeader4() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Content-Type"));

    // Act
    sipMessage.setWwwAuthenticateHeader(new WwwAuthenticateHeader("42"));

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
  }

  @Test
  void testGetWwwAuthenticateHeader() {
    // Arrange, Act and Assert
    assertNull((new SipMessage("Str")).getWwwAuthenticateHeader());
  }

  @Test
  void testGetWwwAuthenticateHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertNull(sipMessage.getWwwAuthenticateHeader());
  }

  @Test
  void testGetWwwAuthenticateHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("WWW-Authenticate"));

    // Act
    WwwAuthenticateHeader actualWwwAuthenticateHeader = sipMessage.getWwwAuthenticateHeader();

    // Assert
    assertEquals("", actualWwwAuthenticateHeader.getValue());
    assertEquals("WWW-Authenticate", actualWwwAuthenticateHeader.getName());
  }

  @Test
  void testRemoveWwwAuthenticateHeader() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.removeWwwAuthenticateHeader();

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
  }

  @Test
  void testRemoveWwwAuthenticateHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.removeWwwAuthenticateHeader();

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testRemoveWwwAuthenticateHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("WWW-Authenticate"));

    // Act
    sipMessage.removeWwwAuthenticateHeader();

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
  }

  @Test
  void testHasProxyAuthenticateHeader() {
    // Arrange, Act and Assert
    assertFalse((new SipMessage("Str")).hasProxyAuthenticateHeader());
  }

  @Test
  void testHasProxyAuthenticateHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertFalse(sipMessage.hasProxyAuthenticateHeader());
  }

  @Test
  void testHasProxyAuthenticateHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Proxy-Authenticate"));

    // Act and Assert
    assertTrue(sipMessage.hasProxyAuthenticateHeader());
  }

  @Test
  void testSetProxyAuthenticateHeader() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.setProxyAuthenticateHeader(new ProxyAuthenticateHeader("42"));

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
  }

  @Test
  void testSetProxyAuthenticateHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.setProxyAuthenticateHeader(new ProxyAuthenticateHeader("42"));

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
  }

  @Test
  void testSetProxyAuthenticateHeader3() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");
    SipMessage createRequestResult = BasicSipMessageFactory.createRequest("Method", request_uri, to, from, "Call id",
        1L, "Local tag", "Remote tag", contact, "Not all who wander are lost",
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    // Act
    createRequestResult.setProxyAuthenticateHeader(new ProxyAuthenticateHeader("42"));

    // Assert
    assertNull(createRequestResult.getAcceptEncodingHeader());
  }

  @Test
  void testSetProxyAuthenticateHeader4() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Content-Type"));

    // Act
    sipMessage.setProxyAuthenticateHeader(new ProxyAuthenticateHeader("42"));

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
  }

  @Test
  void testGetProxyAuthenticateHeader() {
    // Arrange, Act and Assert
    assertNull((new SipMessage("Str")).getProxyAuthenticateHeader());
  }

  @Test
  void testGetProxyAuthenticateHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertNull(sipMessage.getProxyAuthenticateHeader());
  }

  @Test
  void testGetProxyAuthenticateHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Proxy-Authenticate"));

    // Act
    ProxyAuthenticateHeader actualProxyAuthenticateHeader = sipMessage.getProxyAuthenticateHeader();

    // Assert
    assertEquals("", actualProxyAuthenticateHeader.getValue());
    assertEquals("Proxy-Authenticate", actualProxyAuthenticateHeader.getName());
  }

  @Test
  void testRemoveProxyAuthenticateHeader() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.removeProxyAuthenticateHeader();

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
  }

  @Test
  void testRemoveProxyAuthenticateHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.removeProxyAuthenticateHeader();

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testRemoveProxyAuthenticateHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Proxy-Authenticate"));

    // Act
    sipMessage.removeProxyAuthenticateHeader();

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
  }

  @Test
  void testHasProxyAuthorizationHeader() {
    // Arrange, Act and Assert
    assertFalse((new SipMessage("Str")).hasProxyAuthorizationHeader());
  }

  @Test
  void testHasProxyAuthorizationHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertFalse(sipMessage.hasProxyAuthorizationHeader());
  }

  @Test
  void testHasProxyAuthorizationHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Proxy-Authorization"));

    // Act and Assert
    assertTrue(sipMessage.hasProxyAuthorizationHeader());
  }

  @Test
  void testSetProxyAuthorizationHeader() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.setProxyAuthorizationHeader(new ProxyAuthorizationHeader("42"));

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
  }

  @Test
  void testSetProxyAuthorizationHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.setProxyAuthorizationHeader(new ProxyAuthorizationHeader("42"));

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
  }

  @Test
  void testSetProxyAuthorizationHeader3() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");
    SipMessage createRequestResult = BasicSipMessageFactory.createRequest("Method", request_uri, to, from, "Call id",
        1L, "Local tag", "Remote tag", contact, "Not all who wander are lost",
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    // Act
    createRequestResult.setProxyAuthorizationHeader(new ProxyAuthorizationHeader("42"));

    // Assert
    assertNull(createRequestResult.getAcceptEncodingHeader());
  }

  @Test
  void testSetProxyAuthorizationHeader4() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Content-Type"));

    // Act
    sipMessage.setProxyAuthorizationHeader(new ProxyAuthorizationHeader("42"));

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
  }

  @Test
  void testGetProxyAuthorizationHeader() {
    // Arrange, Act and Assert
    assertNull((new SipMessage("Str")).getProxyAuthorizationHeader());
  }

  @Test
  void testGetProxyAuthorizationHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertNull(sipMessage.getProxyAuthorizationHeader());
  }

  @Test
  void testGetProxyAuthorizationHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Proxy-Authorization"));

    // Act
    ProxyAuthorizationHeader actualProxyAuthorizationHeader = sipMessage.getProxyAuthorizationHeader();

    // Assert
    assertEquals("", actualProxyAuthorizationHeader.getValue());
    assertEquals("Proxy-Authorization", actualProxyAuthorizationHeader.getName());
  }

  @Test
  void testRemoveProxyAuthorizationHeader() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.removeProxyAuthorizationHeader();

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
  }

  @Test
  void testRemoveProxyAuthorizationHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.removeProxyAuthorizationHeader();

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testRemoveProxyAuthorizationHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Proxy-Authorization"));

    // Act
    sipMessage.removeProxyAuthorizationHeader();

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
  }

  @Test
  void testHasSupportedHeader() {
    // Arrange, Act and Assert
    assertFalse((new SipMessage("Str")).hasSupportedHeader());
  }

  @Test
  void testHasSupportedHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertFalse(sipMessage.hasSupportedHeader());
  }

  @Test
  void testHasSupportedHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Supported"));

    // Act and Assert
    assertTrue(sipMessage.hasSupportedHeader());
  }

  @Test
  void testSetSupportedHeader() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.setSupportedHeader(new SupportedHeader("Option tag"));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetSupportedHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.setSupportedHeader(new SupportedHeader("Option tag"));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetSupportedHeader3() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");
    SipMessage createRequestResult = BasicSipMessageFactory.createRequest("Method", request_uri, to, from, "Call id",
        1L, "Local tag", "Remote tag", contact, "Not all who wander are lost",
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    // Act
    createRequestResult.setSupportedHeader(new SupportedHeader("Option tag"));

    // Assert
    Vector expectedHeaders = createRequestResult.headers;
    assertEquals(expectedHeaders, createRequestResult.getHeaders());
  }

  @Test
  void testSetSupportedHeader4() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Content-Type"));

    // Act
    sipMessage.setSupportedHeader(new SupportedHeader("Option tag"));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetSupportedHeader5() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.setSupportedHeader(new SupportedHeader(new Header("Hname", "42")));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testGetSupportedHeader() {
    // Arrange, Act and Assert
    assertNull((new SipMessage("Str")).getSupportedHeader());
  }

  @Test
  void testGetSupportedHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertNull(sipMessage.getSupportedHeader());
  }

  @Test
  void testGetSupportedHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Supported"));

    // Act
    SupportedHeader actualSupportedHeader = sipMessage.getSupportedHeader();

    // Assert
    assertEquals("", actualSupportedHeader.getValue());
    assertEquals("Supported", actualSupportedHeader.getName());
  }

  @Test
  void testRemoveSupportedHeader() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.removeSupportedHeader();

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
  }

  @Test
  void testRemoveSupportedHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.removeSupportedHeader();

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testRemoveSupportedHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Supported"));

    // Act
    sipMessage.removeSupportedHeader();

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
  }

  @Test
  void testHasRequireHeader() {
    // Arrange, Act and Assert
    assertFalse((new SipMessage("Str")).hasRequireHeader());
  }

  @Test
  void testHasRequireHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertFalse(sipMessage.hasRequireHeader());
  }

  @Test
  void testHasRequireHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Require"));

    // Act and Assert
    assertTrue(sipMessage.hasRequireHeader());
  }

  @Test
  void testSetRequireHeader() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.setRequireHeader(new RequireHeader("Option tag"));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetRequireHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.setRequireHeader(new RequireHeader("Option tag"));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetRequireHeader3() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");
    SipMessage createRequestResult = BasicSipMessageFactory.createRequest("Method", request_uri, to, from, "Call id",
        1L, "Local tag", "Remote tag", contact, "Not all who wander are lost",
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    // Act
    createRequestResult.setRequireHeader(new RequireHeader("Option tag"));

    // Assert
    Vector expectedHeaders = createRequestResult.headers;
    assertEquals(expectedHeaders, createRequestResult.getHeaders());
  }

  @Test
  void testSetRequireHeader4() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Content-Type"));

    // Act
    sipMessage.setRequireHeader(new RequireHeader("Option tag"));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetRequireHeader5() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.setRequireHeader(new RequireHeader(new Header("Hname", "42")));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testGetRequireHeader() {
    // Arrange, Act and Assert
    assertNull((new SipMessage("Str")).getRequireHeader());
  }

  @Test
  void testGetRequireHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertNull(sipMessage.getRequireHeader());
  }

  @Test
  void testGetRequireHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Require"));

    // Act
    RequireHeader actualRequireHeader = sipMessage.getRequireHeader();

    // Assert
    assertEquals("", actualRequireHeader.getValue());
    assertEquals("Require", actualRequireHeader.getName());
  }

  @Test
  void testRemoveRequireHeader() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.removeRequireHeader();

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
  }

  @Test
  void testRemoveRequireHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.removeRequireHeader();

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testRemoveRequireHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Require"));

    // Act
    sipMessage.removeRequireHeader();

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
  }

  @Test
  void testHasUnsupportedHeader() {
    // Arrange, Act and Assert
    assertFalse((new SipMessage("Str")).hasUnsupportedHeader());
  }

  @Test
  void testHasUnsupportedHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertFalse(sipMessage.hasUnsupportedHeader());
  }

  @Test
  void testHasUnsupportedHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Unsupported"));

    // Act and Assert
    assertTrue(sipMessage.hasUnsupportedHeader());
  }

  @Test
  void testSetUnsupportedHeader() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.setUnsupportedHeader(new UnsupportedHeader("Option tag"));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetUnsupportedHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.setUnsupportedHeader(new UnsupportedHeader("Option tag"));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetUnsupportedHeader3() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");
    SipMessage createRequestResult = BasicSipMessageFactory.createRequest("Method", request_uri, to, from, "Call id",
        1L, "Local tag", "Remote tag", contact, "Not all who wander are lost",
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    // Act
    createRequestResult.setUnsupportedHeader(new UnsupportedHeader("Option tag"));

    // Assert
    Vector expectedHeaders = createRequestResult.headers;
    assertEquals(expectedHeaders, createRequestResult.getHeaders());
  }

  @Test
  void testSetUnsupportedHeader4() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Content-Type"));

    // Act
    sipMessage.setUnsupportedHeader(new UnsupportedHeader("Option tag"));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetUnsupportedHeader5() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.setUnsupportedHeader(new UnsupportedHeader(new Header("Hname", "42")));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testGetUnsupportedHeader() {
    // Arrange, Act and Assert
    assertNull((new SipMessage("Str")).getUnsupportedHeader());
  }

  @Test
  void testGetUnsupportedHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertNull(sipMessage.getUnsupportedHeader());
  }

  @Test
  void testGetUnsupportedHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Unsupported"));

    // Act
    UnsupportedHeader actualUnsupportedHeader = sipMessage.getUnsupportedHeader();

    // Assert
    assertEquals("", actualUnsupportedHeader.getValue());
    assertEquals("Unsupported", actualUnsupportedHeader.getName());
  }

  @Test
  void testRemoveUnsupportedHeader() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.removeUnsupportedHeader();

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
  }

  @Test
  void testRemoveUnsupportedHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.removeUnsupportedHeader();

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testRemoveUnsupportedHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Unsupported"));

    // Act
    sipMessage.removeUnsupportedHeader();

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
  }

  @Test
  void testHasProxyRequireHeader() {
    // Arrange, Act and Assert
    assertFalse((new SipMessage("Str")).hasProxyRequireHeader());
  }

  @Test
  void testHasProxyRequireHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertFalse(sipMessage.hasProxyRequireHeader());
  }

  @Test
  void testHasProxyRequireHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Proxy-Require"));

    // Act and Assert
    assertTrue(sipMessage.hasProxyRequireHeader());
  }

  @Test
  void testSetProxyRequireHeader() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.setProxyRequireHeader(new ProxyRequireHeader("Option tag"));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetProxyRequireHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.setProxyRequireHeader(new ProxyRequireHeader("Option tag"));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetProxyRequireHeader3() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");
    SipMessage createRequestResult = BasicSipMessageFactory.createRequest("Method", request_uri, to, from, "Call id",
        1L, "Local tag", "Remote tag", contact, "Not all who wander are lost",
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    // Act
    createRequestResult.setProxyRequireHeader(new ProxyRequireHeader("Option tag"));

    // Assert
    Vector expectedHeaders = createRequestResult.headers;
    assertEquals(expectedHeaders, createRequestResult.getHeaders());
  }

  @Test
  void testSetProxyRequireHeader4() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Content-Type"));

    // Act
    sipMessage.setProxyRequireHeader(new ProxyRequireHeader("Option tag"));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetProxyRequireHeader5() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.setProxyRequireHeader(new ProxyRequireHeader(new Header("Hname", "42")));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testGetProxyRequireHeader() {
    // Arrange, Act and Assert
    assertNull((new SipMessage("Str")).getProxyRequireHeader());
  }

  @Test
  void testGetProxyRequireHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertNull(sipMessage.getProxyRequireHeader());
  }

  @Test
  void testGetProxyRequireHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Proxy-Require"));

    // Act
    ProxyRequireHeader actualProxyRequireHeader = sipMessage.getProxyRequireHeader();

    // Assert
    assertEquals("", actualProxyRequireHeader.getValue());
    assertEquals("Proxy-Require", actualProxyRequireHeader.getName());
  }

  @Test
  void testRemoveProxyRequireHeader() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.removeProxyRequireHeader();

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
  }

  @Test
  void testRemoveProxyRequireHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.removeProxyRequireHeader();

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testRemoveProxyRequireHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Proxy-Require"));

    // Act
    sipMessage.removeProxyRequireHeader();

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
  }

  @Test
  void testRfc2543RouteAdapt() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.rfc2543RouteAdapt();

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
  }

  @Test
  void testRfc2543RouteAdapt2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.rfc2543RouteAdapt();

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testRfc2543RouteAdapt3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.setRequestLine(new RequestLine("Request", "Str uri"));
    sipMessage.addRouteHeader(new RouteHeader(new NameAddress("Str")));

    // Act
    sipMessage.rfc2543RouteAdapt();

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
    assertEquals(45, sipMessage.getLength());
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
    assertEquals("Request sip:Str SIP/2.0\r\n", sipMessage.getFirstLine());
  }

  @Test
  void testRfc2543RouteAdapt4() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");
    SipMessage createRequestResult = BasicSipMessageFactory.createRequest("Method", request_uri, to, from, "Call id",
        1L, "Local tag", "Remote tag", contact, "Not all who wander are lost",
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));
    createRequestResult.addRouteHeader(new RouteHeader(new NameAddress("Str")));

    // Act
    createRequestResult.rfc2543RouteAdapt();

    // Assert
    assertNull(createRequestResult.getAcceptEncodingHeader());
    assertEquals(349, createRequestResult.getLength());
    Vector expectedHeaders = createRequestResult.headers;
    assertEquals(expectedHeaders, createRequestResult.getHeaders());
    assertEquals("Method sip:Str SIP/2.0\r\n", createRequestResult.getFirstLine());
  }

  @Test
  void testRfc2543RouteAdapt5() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRouteHeader(new RouteHeader(new Header("Hname", "42")));

    // Act
    sipMessage.rfc2543RouteAdapt();

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testRfc2543toRfc3261RouteUpdate() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRouteHeader(new RouteHeader(new NameAddress("Str")));
    sipMessage.setRequestLine(new RequestLine("Method", new GenericURI("Uri")));

    // Act
    sipMessage.rfc2543toRfc3261RouteUpdate();

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
    assertEquals(43, sipMessage.getLength());
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
    assertEquals("Method sip:Str SIP/2.0\r\n", sipMessage.getFirstLine());
  }

  @Test
  void testRfc2543toRfc3261RouteUpdate2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRouteHeader(new RouteHeader(new NameAddress("Str")));
    sipMessage.addRoutes(new MultipleHeader("Hname"));
    sipMessage.setRequestLine(new RequestLine("Method", new GenericURI("Uri")));

    // Act
    sipMessage.rfc2543toRfc3261RouteUpdate();

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
    assertEquals(52, sipMessage.getLength());
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
    assertEquals("Method sip:Str SIP/2.0\r\n", sipMessage.getFirstLine());
  }

  @Test
  void testRfc2543toRfc3261RouteUpdate3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRouteHeader(new RouteHeader(new NameAddress("Str")));
    sipMessage.addViaHeader(new ViaHeader("42"));
    sipMessage.setRequestLine(new RequestLine("Method", new GenericURI("Uri")));

    // Act
    sipMessage.rfc2543toRfc3261RouteUpdate();

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
    assertEquals(52, sipMessage.getLength());
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
    assertEquals("Method sip:Str SIP/2.0\r\n", sipMessage.getFirstLine());
  }

  @Test
  void testIsInfo() throws NullPointerException {
    // Arrange, Act and Assert
    assertFalse((new SipMessage("Str")).isInfo());
    assertTrue((new SipMessage(SipMethods.INFO)).isInfo());
    assertFalse((new SipMessage("Accept")).isInfo());
  }

  @Test
  void testIsPrack() throws NullPointerException {
    // Arrange, Act and Assert
    assertFalse((new SipMessage("Str")).isPrack());
    assertTrue((new SipMessage(SipMethods.PRACK)).isPrack());
    assertFalse((new SipMessage("Accept")).isPrack());
  }

  @Test
  void testIsUpdate() throws NullPointerException {
    // Arrange, Act and Assert
    assertFalse((new SipMessage("Str")).isUpdate());
    assertTrue((new SipMessage(SipMethods.UPDATE)).isUpdate());
    assertFalse((new SipMessage("Accept")).isUpdate());
  }

  @Test
  void testIsMessage() throws NullPointerException {
    // Arrange, Act and Assert
    assertFalse((new SipMessage("Str")).isMessage());
    assertTrue((new SipMessage(SipMethods.MESSAGE)).isMessage());
    assertFalse((new SipMessage("Accept")).isMessage());
  }

  @Test
  void testIsRefer() throws NullPointerException {
    // Arrange, Act and Assert
    assertFalse((new SipMessage("Str")).isRefer());
    assertTrue((new SipMessage(SipMethods.REFER)).isRefer());
    assertFalse((new SipMessage("Accept")).isRefer());
  }

  @Test
  void testIsNotify() throws NullPointerException {
    // Arrange, Act and Assert
    assertFalse((new SipMessage("Str")).isNotify());
    assertTrue((new SipMessage(SipMethods.NOTIFY)).isNotify());
    assertFalse((new SipMessage("Accept")).isNotify());
  }

  @Test
  void testIsSubscribe() throws NullPointerException {
    // Arrange, Act and Assert
    assertFalse((new SipMessage("Str")).isSubscribe());
    assertTrue((new SipMessage(SipMethods.SUBSCRIBE)).isSubscribe());
    assertFalse((new SipMessage("Accept")).isSubscribe());
  }

  @Test
  void testIsPublish() throws NullPointerException {
    // Arrange, Act and Assert
    assertFalse((new SipMessage("Str")).isPublish());
    assertTrue((new SipMessage(SipMethods.PUBLISH)).isPublish());
    assertFalse((new SipMessage("Accept")).isPublish());
  }

  @Test
  void testHasRSeqHeader() {
    // Arrange, Act and Assert
    assertFalse((new SipMessage("Str")).hasRSeqHeader());
  }

  @Test
  void testHasRSeqHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertFalse(sipMessage.hasRSeqHeader());
  }

  @Test
  void testHasRSeqHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("RSeq"));

    // Act and Assert
    assertTrue(sipMessage.hasRSeqHeader());
  }

  @Test
  void testGetRSeqHeader() {
    // Arrange, Act and Assert
    assertNull((new SipMessage("Str")).getRSeqHeader());
  }

  @Test
  void testGetRSeqHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertNull(sipMessage.getRSeqHeader());
  }

  @Test
  void testGetRSeqHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("RSeq"));

    // Act
    RSeqHeader actualRSeqHeader = sipMessage.getRSeqHeader();

    // Assert
    assertEquals("RSeq", actualRSeqHeader.getName());
    assertEquals("", actualRSeqHeader.getValue());
  }

  @Test
  void testSetRSeqHeader() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.setRSeqHeader(new RSeqHeader(1L));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetRSeqHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.setRSeqHeader(new RSeqHeader(1L));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetRSeqHeader3() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");
    SipMessage createRequestResult = BasicSipMessageFactory.createRequest("Method", request_uri, to, from, "Call id",
        1L, "Local tag", "Remote tag", contact, "Not all who wander are lost",
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    // Act
    createRequestResult.setRSeqHeader(new RSeqHeader(1L));

    // Assert
    Vector expectedHeaders = createRequestResult.headers;
    assertEquals(expectedHeaders, createRequestResult.getHeaders());
  }

  @Test
  void testSetRSeqHeader4() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Content-Type"));

    // Act
    sipMessage.setRSeqHeader(new RSeqHeader(1L));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testRemoveRSeqHeader() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.removeRSeqHeader();

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
  }

  @Test
  void testRemoveRSeqHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.removeRSeqHeader();

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testRemoveRSeqHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("RSeq"));

    // Act
    sipMessage.removeRSeqHeader();

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
  }

  @Test
  void testHasRAckHeader() {
    // Arrange, Act and Assert
    assertFalse((new SipMessage("Str")).hasRAckHeader());
  }

  @Test
  void testHasRAckHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertFalse(sipMessage.hasRAckHeader());
  }

  @Test
  void testHasRAckHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("RAck"));

    // Act and Assert
    assertTrue(sipMessage.hasRAckHeader());
  }

  @Test
  void testGetRAckHeader() {
    // Arrange, Act and Assert
    assertNull((new SipMessage("Str")).getRAckHeader());
  }

  @Test
  void testGetRAckHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertNull(sipMessage.getRAckHeader());
  }

  @Test
  void testGetRAckHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("RAck"));

    // Act
    RAckHeader actualRAckHeader = sipMessage.getRAckHeader();

    // Assert
    assertEquals("", actualRAckHeader.getValue());
    assertEquals("RAck", actualRAckHeader.getName());
  }

  @Test
  void testSetRAckHeader() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.setRAckHeader(new RAckHeader("42"));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetRAckHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.setRAckHeader(new RAckHeader("42"));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetRAckHeader3() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");
    SipMessage createRequestResult = BasicSipMessageFactory.createRequest("Method", request_uri, to, from, "Call id",
        1L, "Local tag", "Remote tag", contact, "Not all who wander are lost",
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    // Act
    createRequestResult.setRAckHeader(new RAckHeader("42"));

    // Assert
    Vector expectedHeaders = createRequestResult.headers;
    assertEquals(expectedHeaders, createRequestResult.getHeaders());
  }

  @Test
  void testSetRAckHeader4() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Content-Type"));

    // Act
    sipMessage.setRAckHeader(new RAckHeader("42"));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testRemoveRAckHeader() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.removeRAckHeader();

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
  }

  @Test
  void testRemoveRAckHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.removeRAckHeader();

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testRemoveRAckHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("RAck"));

    // Act
    sipMessage.removeRAckHeader();

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
  }

  @Test
  void testHasSessionExpiresHeader() {
    // Arrange, Act and Assert
    assertFalse((new SipMessage("Str")).hasSessionExpiresHeader());
  }

  @Test
  void testHasSessionExpiresHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertFalse(sipMessage.hasSessionExpiresHeader());
  }

  @Test
  void testHasSessionExpiresHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Session-Expires"));

    // Act and Assert
    assertTrue(sipMessage.hasSessionExpiresHeader());
  }

  @Test
  void testGetSessionExpiresHeader() {
    // Arrange, Act and Assert
    assertNull((new SipMessage("Str")).getSessionExpiresHeader());
  }

  @Test
  void testGetSessionExpiresHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertNull(sipMessage.getSessionExpiresHeader());
  }

  @Test
  void testGetSessionExpiresHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Session-Expires"));

    // Act
    SessionExpiresHeader actualSessionExpiresHeader = sipMessage.getSessionExpiresHeader();

    // Assert
    assertEquals("Session-Expires", actualSessionExpiresHeader.getName());
    assertEquals("", actualSessionExpiresHeader.getValue());
  }

  @Test
  void testSetSessionExpiresHeader() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.setSessionExpiresHeader(new SessionExpiresHeader(2));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetSessionExpiresHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.setSessionExpiresHeader(new SessionExpiresHeader(2));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetSessionExpiresHeader3() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");
    SipMessage createRequestResult = BasicSipMessageFactory.createRequest("Method", request_uri, to, from, "Call id",
        1L, "Local tag", "Remote tag", contact, "Not all who wander are lost",
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    // Act
    createRequestResult.setSessionExpiresHeader(new SessionExpiresHeader(2));

    // Assert
    Vector expectedHeaders = createRequestResult.headers;
    assertEquals(expectedHeaders, createRequestResult.getHeaders());
  }

  @Test
  void testSetSessionExpiresHeader4() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Content-Type"));

    // Act
    sipMessage.setSessionExpiresHeader(new SessionExpiresHeader(2));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testRemoveSessionExpiresHeader() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.removeSessionExpiresHeader();

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
  }

  @Test
  void testRemoveSessionExpiresHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.removeSessionExpiresHeader();

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testRemoveSessionExpiresHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Session-Expires"));

    // Act
    sipMessage.removeSessionExpiresHeader();

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
  }

  @Test
  void testHasMinSEHeader() {
    // Arrange, Act and Assert
    assertFalse((new SipMessage("Str")).hasMinSEHeader());
  }

  @Test
  void testHasMinSEHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertFalse(sipMessage.hasMinSEHeader());
  }

  @Test
  void testHasMinSEHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Min-SE"));

    // Act and Assert
    assertTrue(sipMessage.hasMinSEHeader());
  }

  @Test
  void testGetMinSEHeader() {
    // Arrange, Act and Assert
    assertNull((new SipMessage("Str")).getMinSEHeader());
  }

  @Test
  void testGetMinSEHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertNull(sipMessage.getMinSEHeader());
  }

  @Test
  void testGetMinSEHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Min-SE"));

    // Act
    MinSEHeader actualMinSEHeader = sipMessage.getMinSEHeader();

    // Assert
    assertEquals("Min-SE", actualMinSEHeader.getName());
    assertEquals("", actualMinSEHeader.getValue());
  }

  @Test
  void testSetMinSEHeader() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.setMinSEHeader(new MinSEHeader(2));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetMinSEHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.setMinSEHeader(new MinSEHeader(2));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetMinSEHeader3() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");
    SipMessage createRequestResult = BasicSipMessageFactory.createRequest("Method", request_uri, to, from, "Call id",
        1L, "Local tag", "Remote tag", contact, "Not all who wander are lost",
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    // Act
    createRequestResult.setMinSEHeader(new MinSEHeader(2));

    // Assert
    Vector expectedHeaders = createRequestResult.headers;
    assertEquals(expectedHeaders, createRequestResult.getHeaders());
  }

  @Test
  void testSetMinSEHeader4() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Content-Type"));

    // Act
    sipMessage.setMinSEHeader(new MinSEHeader(2));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testRemoveMinSEHeader() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.removeMinSEHeader();

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
  }

  @Test
  void testRemoveMinSEHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.removeMinSEHeader();

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testRemoveMinSEHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Min-SE"));

    // Act
    sipMessage.removeMinSEHeader();

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
  }

  @Test
  void testHasReferToHeader() {
    // Arrange, Act and Assert
    assertFalse((new SipMessage("Str")).hasReferToHeader());
  }

  @Test
  void testHasReferToHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertFalse(sipMessage.hasReferToHeader());
  }

  @Test
  void testHasReferToHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Refer-To"));

    // Act and Assert
    assertTrue(sipMessage.hasReferToHeader());
  }

  @Test
  void testGetReferToHeader() {
    // Arrange, Act and Assert
    assertNull((new SipMessage("Str")).getReferToHeader());
  }

  @Test
  void testGetReferToHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertNull(sipMessage.getReferToHeader());
  }

  @Test
  void testGetReferToHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Refer-To"));

    // Act
    ReferToHeader actualReferToHeader = sipMessage.getReferToHeader();

    // Assert
    assertEquals("Refer-To", actualReferToHeader.getName());
    assertEquals("", actualReferToHeader.getValue());
  }

  @Test
  void testSetReferToHeader() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.setReferToHeader(new ReferToHeader(new NameAddress("Str")));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetReferToHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.setReferToHeader(new ReferToHeader(new NameAddress("Str")));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetReferToHeader3() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");
    SipMessage createRequestResult = BasicSipMessageFactory.createRequest("Method", request_uri, to, from, "Call id",
        1L, "Local tag", "Remote tag", contact, "Not all who wander are lost",
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    // Act
    createRequestResult.setReferToHeader(new ReferToHeader(new NameAddress("Str")));

    // Assert
    Vector expectedHeaders = createRequestResult.headers;
    assertEquals(expectedHeaders, createRequestResult.getHeaders());
  }

  @Test
  void testSetReferToHeader4() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Content-Type"));

    // Act
    sipMessage.setReferToHeader(new ReferToHeader(new NameAddress("Str")));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testRemoveReferToHeader() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.removeReferToHeader();

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
  }

  @Test
  void testRemoveReferToHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.removeReferToHeader();

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testRemoveReferToHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Refer-To"));

    // Act
    sipMessage.removeReferToHeader();

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
  }

  @Test
  void testHasReplacesHeader() {
    // Arrange, Act and Assert
    assertFalse((new SipMessage("Str")).hasReplacesHeader());
  }

  @Test
  void testHasReplacesHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertFalse(sipMessage.hasReplacesHeader());
  }

  @Test
  void testHasReplacesHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Replaces"));

    // Act and Assert
    assertTrue(sipMessage.hasReplacesHeader());
  }

  @Test
  void testGetReplacesHeader() {
    // Arrange, Act and Assert
    assertNull((new SipMessage("Str")).getReplacesHeader());
  }

  @Test
  void testGetReplacesHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertNull(sipMessage.getReplacesHeader());
  }

  @Test
  void testGetReplacesHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Replaces"));

    // Act
    ReplacesHeader actualReplacesHeader = sipMessage.getReplacesHeader();

    // Assert
    assertEquals("", actualReplacesHeader.getValue());
    assertEquals("Replaces", actualReplacesHeader.getName());
  }

  @Test
  void testSetReplacesHeader() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.setReplacesHeader(new ReplacesHeader("Call id", "To tag", "jane.doe@example.org"));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetReplacesHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.setReplacesHeader(new ReplacesHeader("Call id", "To tag", "jane.doe@example.org"));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetReplacesHeader3() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");
    SipMessage createRequestResult = BasicSipMessageFactory.createRequest("Method", request_uri, to, from, "Call id",
        1L, "Local tag", "Remote tag", contact, "Not all who wander are lost",
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    // Act
    createRequestResult.setReplacesHeader(new ReplacesHeader("Call id", "To tag", "jane.doe@example.org"));

    // Assert
    Vector expectedHeaders = createRequestResult.headers;
    assertEquals(expectedHeaders, createRequestResult.getHeaders());
  }

  @Test
  void testSetReplacesHeader4() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Content-Type"));

    // Act
    sipMessage.setReplacesHeader(new ReplacesHeader("Call id", "To tag", "jane.doe@example.org"));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testRemoveReplacesHeader() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.removeReplacesHeader();

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
  }

  @Test
  void testRemoveReplacesHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.removeReplacesHeader();

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testRemoveReplacesHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Replaces"));

    // Act
    sipMessage.removeReplacesHeader();

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
  }

  @Test
  void testHasReferredByHeader() {
    // Arrange, Act and Assert
    assertFalse((new SipMessage("Str")).hasReferredByHeader());
  }

  @Test
  void testHasReferredByHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertFalse(sipMessage.hasReferredByHeader());
  }

  @Test
  void testHasReferredByHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Refer-To"));

    // Act and Assert
    assertTrue(sipMessage.hasReferredByHeader());
  }

  @Test
  void testGetReferredByHeader() {
    // Arrange, Act and Assert
    assertNull((new SipMessage("Str")).getReferredByHeader());
  }

  @Test
  void testGetReferredByHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertNull(sipMessage.getReferredByHeader());
  }

  @Test
  void testGetReferredByHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Referred-By"));

    // Act
    ReferredByHeader actualReferredByHeader = sipMessage.getReferredByHeader();

    // Assert
    assertEquals("Referred-By", actualReferredByHeader.getName());
    assertEquals("", actualReferredByHeader.getValue());
  }

  @Test
  void testSetReferredByHeader() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.setReferredByHeader(new ReferredByHeader(new NameAddress("Str")));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetReferredByHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.setReferredByHeader(new ReferredByHeader(new NameAddress("Str")));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetReferredByHeader3() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");
    SipMessage createRequestResult = BasicSipMessageFactory.createRequest("Method", request_uri, to, from, "Call id",
        1L, "Local tag", "Remote tag", contact, "Not all who wander are lost",
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    // Act
    createRequestResult.setReferredByHeader(new ReferredByHeader(new NameAddress("Str")));

    // Assert
    Vector expectedHeaders = createRequestResult.headers;
    assertEquals(expectedHeaders, createRequestResult.getHeaders());
  }

  @Test
  void testSetReferredByHeader4() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Content-Type"));

    // Act
    sipMessage.setReferredByHeader(new ReferredByHeader(new NameAddress("Str")));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testRemoveReferredByHeader() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.removeReferredByHeader();

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
  }

  @Test
  void testRemoveReferredByHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.removeReferredByHeader();

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testRemoveReferredByHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Referred-By"));

    // Act
    sipMessage.removeReferredByHeader();

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
  }

  @Test
  void testHasEventHeader() {
    // Arrange, Act and Assert
    assertFalse((new SipMessage("Str")).hasEventHeader());
  }

  @Test
  void testHasEventHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertFalse(sipMessage.hasEventHeader());
  }

  @Test
  void testHasEventHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Event"));

    // Act and Assert
    assertTrue(sipMessage.hasEventHeader());
  }

  @Test
  void testGetEventHeader() {
    // Arrange, Act and Assert
    assertNull((new SipMessage("Str")).getEventHeader());
  }

  @Test
  void testGetEventHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertNull(sipMessage.getEventHeader());
  }

  @Test
  void testGetEventHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Event"));

    // Act
    EventHeader actualEventHeader = sipMessage.getEventHeader();

    // Assert
    assertEquals("", actualEventHeader.getValue());
    assertEquals("Event", actualEventHeader.getName());
  }

  @Test
  void testSetEventHeader() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.setEventHeader(new EventHeader("java.text"));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetEventHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.setEventHeader(new EventHeader("java.text"));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetEventHeader3() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");
    SipMessage createRequestResult = BasicSipMessageFactory.createRequest("Method", request_uri, to, from, "Call id",
        1L, "Local tag", "Remote tag", contact, "Not all who wander are lost",
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    // Act
    createRequestResult.setEventHeader(new EventHeader("java.text"));

    // Assert
    Vector expectedHeaders = createRequestResult.headers;
    assertEquals(expectedHeaders, createRequestResult.getHeaders());
  }

  @Test
  void testSetEventHeader4() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Content-Type"));

    // Act
    sipMessage.setEventHeader(new EventHeader("java.text"));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testRemoveEventHeader() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.removeEventHeader();

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
  }

  @Test
  void testRemoveEventHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.removeEventHeader();

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testRemoveEventHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Event"));

    // Act
    sipMessage.removeEventHeader();

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
  }

  @Test
  void testHasAllowEventsHeader() {
    // Arrange, Act and Assert
    assertFalse((new SipMessage("Str")).hasAllowEventsHeader());
  }

  @Test
  void testHasAllowEventsHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertFalse(sipMessage.hasAllowEventsHeader());
  }

  @Test
  void testHasAllowEventsHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Allow-Events"));

    // Act and Assert
    assertTrue(sipMessage.hasAllowEventsHeader());
  }

  @Test
  void testGetAllowEventsHeader() {
    // Arrange, Act and Assert
    assertNull((new SipMessage("Str")).getAllowEventsHeader());
  }

  @Test
  void testGetAllowEventsHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertNull(sipMessage.getAllowEventsHeader());
  }

  @Test
  void testGetAllowEventsHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Allow-Events"));

    // Act
    AllowEventsHeader actualAllowEventsHeader = sipMessage.getAllowEventsHeader();

    // Assert
    assertEquals("", actualAllowEventsHeader.getValue());
    assertEquals("Allow-Events", actualAllowEventsHeader.getName());
  }

  @Test
  void testSetAllowEventsHeader() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.setAllowEventsHeader(new AllowEventsHeader("42"));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetAllowEventsHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.setAllowEventsHeader(new AllowEventsHeader("42"));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetAllowEventsHeader3() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");
    SipMessage createRequestResult = BasicSipMessageFactory.createRequest("Method", request_uri, to, from, "Call id",
        1L, "Local tag", "Remote tag", contact, "Not all who wander are lost",
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    // Act
    createRequestResult.setAllowEventsHeader(new AllowEventsHeader("42"));

    // Assert
    Vector expectedHeaders = createRequestResult.headers;
    assertEquals(expectedHeaders, createRequestResult.getHeaders());
  }

  @Test
  void testSetAllowEventsHeader4() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Content-Type"));

    // Act
    sipMessage.setAllowEventsHeader(new AllowEventsHeader("42"));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testRemoveAllowEventsHeader() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.removeAllowEventsHeader();

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
  }

  @Test
  void testRemoveAllowEventsHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.removeAllowEventsHeader();

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testRemoveAllowEventsHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Allow-Events"));

    // Act
    sipMessage.removeAllowEventsHeader();

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
  }

  @Test
  void testHasSubscriptionStateHeader() {
    // Arrange, Act and Assert
    assertFalse((new SipMessage("Str")).hasSubscriptionStateHeader());
  }

  @Test
  void testHasSubscriptionStateHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertFalse(sipMessage.hasSubscriptionStateHeader());
  }

  @Test
  void testHasSubscriptionStateHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Subscription-State"));

    // Act and Assert
    assertTrue(sipMessage.hasSubscriptionStateHeader());
  }

  @Test
  void testGetSubscriptionStateHeader() {
    // Arrange, Act and Assert
    assertNull((new SipMessage("Str")).getSubscriptionStateHeader());
  }

  @Test
  void testGetSubscriptionStateHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertNull(sipMessage.getSubscriptionStateHeader());
  }

  @Test
  void testGetSubscriptionStateHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Subscription-State"));

    // Act
    SubscriptionStateHeader actualSubscriptionStateHeader = sipMessage.getSubscriptionStateHeader();

    // Assert
    assertEquals("", actualSubscriptionStateHeader.getValue());
    assertEquals("Subscription-State", actualSubscriptionStateHeader.getName());
  }

  @Test
  void testSetSubscriptionStateHeader() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.setSubscriptionStateHeader(new SubscriptionStateHeader("MD"));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetSubscriptionStateHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.setSubscriptionStateHeader(new SubscriptionStateHeader("MD"));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetSubscriptionStateHeader3() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");
    SipMessage createRequestResult = BasicSipMessageFactory.createRequest("Method", request_uri, to, from, "Call id",
        1L, "Local tag", "Remote tag", contact, "Not all who wander are lost",
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    // Act
    createRequestResult.setSubscriptionStateHeader(new SubscriptionStateHeader("MD"));

    // Assert
    Vector expectedHeaders = createRequestResult.headers;
    assertEquals(expectedHeaders, createRequestResult.getHeaders());
  }

  @Test
  void testSetSubscriptionStateHeader4() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Content-Type"));

    // Act
    sipMessage.setSubscriptionStateHeader(new SubscriptionStateHeader("MD"));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testRemoveSubscriptionStateHeader() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.removeSubscriptionStateHeader();

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
  }

  @Test
  void testRemoveSubscriptionStateHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.removeSubscriptionStateHeader();

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testRemoveSubscriptionStateHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Subscription-State"));

    // Act
    sipMessage.removeSubscriptionStateHeader();

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
  }

  @Test
  void testHasInfoPackageHeader() {
    // Arrange, Act and Assert
    assertFalse((new SipMessage("Str")).hasInfoPackageHeader());
  }

  @Test
  void testHasInfoPackageHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertFalse(sipMessage.hasInfoPackageHeader());
  }

  @Test
  void testHasInfoPackageHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Info-Package"));

    // Act and Assert
    assertTrue(sipMessage.hasInfoPackageHeader());
  }

  @Test
  void testSetInfoPackageHeader() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.setInfoPackageHeader(new InfoPackageHeader("42"));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetInfoPackageHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.setInfoPackageHeader(new InfoPackageHeader("42"));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetInfoPackageHeader3() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");
    SipMessage createRequestResult = BasicSipMessageFactory.createRequest("Method", request_uri, to, from, "Call id",
        1L, "Local tag", "Remote tag", contact, "Not all who wander are lost",
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    // Act
    createRequestResult.setInfoPackageHeader(new InfoPackageHeader("42"));

    // Assert
    Vector expectedHeaders = createRequestResult.headers;
    assertEquals(expectedHeaders, createRequestResult.getHeaders());
  }

  @Test
  void testSetInfoPackageHeader4() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Content-Type"));

    // Act
    sipMessage.setInfoPackageHeader(new InfoPackageHeader("42"));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testGetInfoPackageHeader() {
    // Arrange, Act and Assert
    assertNull((new SipMessage("Str")).getInfoPackageHeader());
  }

  @Test
  void testGetInfoPackageHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertNull(sipMessage.getInfoPackageHeader());
  }

  @Test
  void testGetInfoPackageHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Info-Package"));

    // Act
    InfoPackageHeader actualInfoPackageHeader = sipMessage.getInfoPackageHeader();

    // Assert
    assertEquals("Info-Package", actualInfoPackageHeader.getName());
    assertEquals("", actualInfoPackageHeader.getValue());
  }

  @Test
  void testRemoveInfoPackageHeader() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.removeInfoPackageHeader();

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
  }

  @Test
  void testRemoveInfoPackageHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.removeInfoPackageHeader();

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testRemoveInfoPackageHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Info-Package"));

    // Act
    sipMessage.removeInfoPackageHeader();

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
  }

  @Test
  void testHasRecvInfoHeader() {
    // Arrange, Act and Assert
    assertFalse((new SipMessage("Str")).hasRecvInfoHeader());
  }

  @Test
  void testHasRecvInfoHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertFalse(sipMessage.hasRecvInfoHeader());
  }

  @Test
  void testHasRecvInfoHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Recv-Info"));

    // Act and Assert
    assertTrue(sipMessage.hasRecvInfoHeader());
  }

  @Test
  void testSetRecvInfoHeader() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.setRecvInfoHeader(new RecvInfoHeader("42"));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetRecvInfoHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.setRecvInfoHeader(new RecvInfoHeader("42"));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetRecvInfoHeader3() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");
    SipMessage createRequestResult = BasicSipMessageFactory.createRequest("Method", request_uri, to, from, "Call id",
        1L, "Local tag", "Remote tag", contact, "Not all who wander are lost",
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    // Act
    createRequestResult.setRecvInfoHeader(new RecvInfoHeader("42"));

    // Assert
    Vector expectedHeaders = createRequestResult.headers;
    assertEquals(expectedHeaders, createRequestResult.getHeaders());
  }

  @Test
  void testSetRecvInfoHeader4() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Content-Type"));

    // Act
    sipMessage.setRecvInfoHeader(new RecvInfoHeader("42"));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetRecvInfoHeader5() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.setRecvInfoHeader(new RecvInfoHeader(new Header("Hname", "42")));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testGetRecvInfoHeader() {
    // Arrange, Act and Assert
    assertNull((new SipMessage("Str")).getRecvInfoHeader());
  }

  @Test
  void testGetRecvInfoHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertNull(sipMessage.getRecvInfoHeader());
  }

  @Test
  void testGetRecvInfoHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Recv-Info"));

    // Act
    RecvInfoHeader actualRecvInfoHeader = sipMessage.getRecvInfoHeader();

    // Assert
    assertEquals("", actualRecvInfoHeader.getValue());
    assertEquals("Recv-Info", actualRecvInfoHeader.getName());
  }

  @Test
  void testRemoveRecvInfoHeader() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.removeRecvInfoHeader();

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
  }

  @Test
  void testRemoveRecvInfoHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.removeRecvInfoHeader();

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testRemoveRecvInfoHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Recv-Info"));

    // Act
    sipMessage.removeRecvInfoHeader();

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
  }

  @Test
  void testCreatesDialog() {
    // Arrange, Act and Assert
    assertFalse((new SipMessage("Str")).createsDialog());
    assertFalse((new SipMessage("Accept")).createsDialog());
  }

  @Test
  void testGetBody() {
    // Arrange, Act and Assert
    assertNull((new SipMessage("Str")).getBody());
  }

  @Test
  void testGetBodyDisposition() throws UnsupportedEncodingException {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.setBody("Not all who wander are lost", "Not all who wander are lost", "AAAAAAAA".getBytes("UTF-8"));
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertEquals("Not", sipMessage.getBodyDisposition());
  }

  @Test
  void testGetBodyType() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");

    // Act and Assert
    assertEquals("Not",
        BasicSipMessageFactory
            .createRequest("Method", request_uri, to, from, "Call id", 1L, "Local tag", "Remote tag", contact,
                "Not all who wander are lost", "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"))
            .getBodyType());
  }

  @Test
  void testGetBytes() {
    // Arrange and Act
    byte[] actualBytes = (new SipMessage("Str")).getBytes();

    // Assert
    assertEquals(2, actualBytes.length);
    assertEquals((byte) 13, actualBytes[0]);
    assertEquals((byte) 10, actualBytes[1]);
  }

  @Test
  void testGetBytes2() {
    // Arrange, Act and Assert
    assertEquals(23, (new SipMessage("Accept")).getBytes().length);
  }

  @Test
  void testGetBytes3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertEquals(11, sipMessage.getBytes().length);
  }

  @Test
  void testGetBytes4() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");

    // Act and Assert
    assertEquals(331,
        BasicSipMessageFactory
            .createRequest("Method", request_uri, to, from, "Call id", 1L, "Local tag", "Remote tag", contact,
                "Not all who wander are lost", "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"))
            .getBytes().length);
  }

  @Test
  void testGetBytes5() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.setStatusLine(new StatusLine(1, "foo"));
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertEquals(26, sipMessage.getBytes().length);
  }

  @Test
  void testGetConnectionId() {
    // Arrange, Act and Assert
    assertNull((new SipMessage("Str")).getConnectionId());
  }

  @Test
  void testGetContentDispositionHeader() {
    // Arrange, Act and Assert
    assertNull((new SipMessage("Str")).getContentDispositionHeader());
  }

  @Test
  void testGetContentDispositionHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertNull(sipMessage.getContentDispositionHeader());
  }

  @Test
  void testGetContentDispositionHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Content-Disposition"));

    // Act
    ContentDispositionHeader actualContentDispositionHeader = sipMessage.getContentDispositionHeader();

    // Assert
    assertEquals("", actualContentDispositionHeader.getValue());
    assertEquals("Content-Disposition", actualContentDispositionHeader.getName());
  }

  @Test
  void testGetContentLengthHeader() {
    // Arrange, Act and Assert
    assertNull((new SipMessage("Str")).getContentLengthHeader());
  }

  @Test
  void testGetContentLengthHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertNull(sipMessage.getContentLengthHeader());
  }

  @Test
  void testGetContentLengthHeader3() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");

    // Act
    ContentLengthHeader actualContentLengthHeader = BasicSipMessageFactory
        .createRequest("Method", request_uri, to, from, "Call id", 1L, "Local tag", "Remote tag", contact,
            "Not all who wander are lost", "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"))
        .getContentLengthHeader();

    // Assert
    assertEquals("24", actualContentLengthHeader.getValue());
    assertEquals("Content-Length", actualContentLengthHeader.getName());
  }

  @Test
  void testGetContentTypeHeader() {
    // Arrange, Act and Assert
    assertNull((new SipMessage("Str")).getContentTypeHeader());
  }

  @Test
  void testGetContentTypeHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertNull(sipMessage.getContentTypeHeader());
  }

  @Test
  void testGetContentTypeHeader3() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");

    // Act
    ContentTypeHeader actualContentTypeHeader = BasicSipMessageFactory
        .createRequest("Method", request_uri, to, from, "Call id", 1L, "Local tag", "Remote tag", contact,
            "Not all who wander are lost", "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"))
        .getContentTypeHeader();

    // Assert
    assertEquals("Not all who wander are lost", actualContentTypeHeader.getValue());
    assertEquals("Content-Type", actualContentTypeHeader.getName());
  }

  @Test
  void testGetFirstLine() {
    // Arrange, Act and Assert
    assertNull((new SipMessage("Str")).getFirstLine());
    assertEquals("Accept null SIP/2.0\r\n", (new SipMessage("Accept")).getFirstLine());
  }

  @Test
  void testGetFirstLine2() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");

    // Act and Assert
    assertEquals("Method Uri SIP/2.0\r\n",
        BasicSipMessageFactory
            .createRequest("Method", request_uri, to, from, "Call id", 1L, "Local tag", "Remote tag", contact,
                "Not all who wander are lost", "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"))
            .getFirstLine());
  }

  @Test
  void testGetFirstLine3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.setStatusLine(new StatusLine(1, "foo"));

    // Act and Assert
    assertEquals("SIP/2.0 1 foo\r\n", sipMessage.getFirstLine());
  }

  @Test
  void testGetHeader() {
    // Arrange, Act and Assert
    assertNull((new SipMessage("Str")).getHeader("Hname"));
  }

  @Test
  void testGetHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRecordRouteHeader(new RecordRouteHeader(new NameAddress("Str")));

    // Act and Assert
    assertNull(sipMessage.getHeader("Hname"));
  }

  @Test
  void testGetLength() {
    // Arrange, Act and Assert
    assertEquals(2, (new SipMessage("Str")).getLength());
    assertEquals(23, (new SipMessage("Accept")).getLength());
  }

  @Test
  void testGetLength2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertEquals(11, sipMessage.getLength());
  }

  @Test
  void testGetLength3() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");

    // Act and Assert
    assertEquals(331,
        BasicSipMessageFactory
            .createRequest("Method", request_uri, to, from, "Call id", 1L, "Local tag", "Remote tag", contact,
                "Not all who wander are lost", "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"))
            .getLength());
  }

  @Test
  void testGetLength4() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.setStatusLine(new StatusLine(1, "foo"));
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertEquals(26, sipMessage.getLength());
  }

  @Test
  void testGetRemoteAddress() {
    // Arrange, Act and Assert
    assertNull((new SipMessage("Str")).getRemoteAddress());
  }

  @Test
  void testGetRemotePort() {
    // Arrange, Act and Assert
    assertEquals(0, (new SipMessage("Str")).getRemotePort());
  }

  @Test
  void testGetRequestLine() {
    // Arrange, Act and Assert
    assertNull((new SipMessage("Str")).getRequestLine());
  }

  @Test
  void testGetStatusLine() {
    // Arrange, Act and Assert
    assertNull((new SipMessage("Str")).getStatusLine());
  }

  @Test
  void testGetStringBody() {
    // Arrange, Act and Assert
    assertNull((new SipMessage("Str")).getStringBody());
  }

  @Test
  void testGetStringBody2() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");

    // Act and Assert
    assertEquals("AAAAAAAAAAAAAAAAAAAAAAAA",
        BasicSipMessageFactory
            .createRequest("Method", request_uri, to, from, "Call id", 1L, "Local tag", "Remote tag", contact,
                "Not all who wander are lost", "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"))
            .getStringBody());
  }

  @Test
  void testGetTransportProtocol() {
    // Arrange, Act and Assert
    assertNull((new SipMessage("Str")).getTransportProtocol());
  }

  @Test
  void testHasBody() {
    // Arrange, Act and Assert
    assertFalse((new SipMessage("Str")).hasBody());
  }

  @Test
  void testHasBody2() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");

    // Act and Assert
    assertTrue(
        BasicSipMessageFactory
            .createRequest("Method", request_uri, to, from, "Call id", 1L, "Local tag", "Remote tag", contact,
                "Not all who wander are lost", "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"))
            .hasBody());
  }

  @Test
  void testHasContentDispositionHeader() {
    // Arrange, Act and Assert
    assertFalse((new SipMessage("Str")).hasContentDispositionHeader());
  }

  @Test
  void testHasContentDispositionHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertFalse(sipMessage.hasContentDispositionHeader());
  }

  @Test
  void testHasContentDispositionHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Content-Disposition"));

    // Act and Assert
    assertTrue(sipMessage.hasContentDispositionHeader());
  }

  @Test
  void testHasContentLengthHeader() {
    // Arrange, Act and Assert
    assertFalse((new SipMessage("Str")).hasContentLengthHeader());
  }

  @Test
  void testHasContentLengthHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertFalse(sipMessage.hasContentLengthHeader());
  }

  @Test
  void testHasContentLengthHeader3() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");

    // Act and Assert
    assertTrue(
        BasicSipMessageFactory
            .createRequest("Method", request_uri, to, from, "Call id", 1L, "Local tag", "Remote tag", contact,
                "Not all who wander are lost", "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"))
            .hasContentLengthHeader());
  }

  @Test
  void testHasContentTypeHeader() {
    // Arrange, Act and Assert
    assertFalse((new SipMessage("Str")).hasContentTypeHeader());
  }

  @Test
  void testHasContentTypeHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertFalse(sipMessage.hasContentTypeHeader());
  }

  @Test
  void testHasContentTypeHeader3() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");

    // Act and Assert
    assertTrue(
        BasicSipMessageFactory
            .createRequest("Method", request_uri, to, from, "Call id", 1L, "Local tag", "Remote tag", contact,
                "Not all who wander are lost", "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"))
            .hasContentTypeHeader());
  }

  @Test
  void testHasHeader() {
    // Arrange, Act and Assert
    assertFalse((new SipMessage("Str")).hasHeader("Name"));
  }

  @Test
  void testHasHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertFalse(sipMessage.hasHeader("Name"));
  }

  @Test
  void testHasRequestLine() {
    // Arrange, Act and Assert
    assertFalse((new SipMessage("Str")).hasRequestLine());
    assertTrue((new SipMessage("Accept")).hasRequestLine());
  }

  @Test
  void testHasStatusLine() {
    // Arrange, Act and Assert
    assertFalse((new SipMessage("Str")).hasStatusLine());
  }

  @Test
  void testHasStatusLine2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.setStatusLine(new StatusLine(1, "foo"));

    // Act and Assert
    assertTrue(sipMessage.hasStatusLine());
  }

  @Test
  void testIndexOfHeader() {
    // Arrange, Act and Assert
    assertEquals(-1, (new SipMessage("Str")).indexOfHeader("Hname"));
  }

  @Test
  void testIndexOfHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertEquals(0, sipMessage.indexOfHeader("Hname"));
  }

  @Test
  void testIndexOfHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRecordRouteHeader(new RecordRouteHeader(new NameAddress("Str")));

    // Act and Assert
    assertEquals(-1, sipMessage.indexOfHeader("Hname"));
  }

  @Test
  void testIsRequest() {
    // Arrange, Act and Assert
    assertFalse((new SipMessage("Str")).isRequest());
    assertTrue((new SipMessage("Accept")).isRequest());
    assertFalse((new SipMessage("Str")).isRequest("Method"));
    assertFalse((new SipMessage("Accept")).isRequest("Method"));
  }

  @Test
  void testIsRequest2() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");

    // Act and Assert
    assertTrue(
        BasicSipMessageFactory
            .createRequest("Method", request_uri, to, from, "Call id", 1L, "Local tag", "Remote tag", contact,
                "Not all who wander are lost", "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"))
            .isRequest("Method"));
  }

  @Test
  void testIsResponse() throws NullPointerException {
    // Arrange, Act and Assert
    assertFalse((new SipMessage("Str")).isResponse());
  }

  @Test
  void testIsResponse2() throws NullPointerException {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.setStatusLine(new StatusLine(1, "foo"));

    // Act and Assert
    assertTrue(sipMessage.isResponse());
  }

  @Test
  void testRemoveAllHeaders() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.removeAllHeaders("Hname");

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
  }

  @Test
  void testRemoveAllHeaders2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.removeAllHeaders("Hname");

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
  }

  @Test
  void testRemoveAllHeaders3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRecordRouteHeader(new RecordRouteHeader(new NameAddress("Str")));

    // Act
    sipMessage.removeAllHeaders("Hname");

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testRemoveBody() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.removeBody();

    // Assert
    assertNull(sipMessage.getBody());
  }

  @Test
  void testRemoveBody2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.removeBody();

    // Assert
    assertNull(sipMessage.getBody());
  }

  @Test
  void testRemoveBody3() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");
    SipMessage createRequestResult = BasicSipMessageFactory.createRequest("Method", request_uri, to, from, "Call id",
        1L, "Local tag", "Remote tag", contact, "Not all who wander are lost",
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    // Act
    createRequestResult.removeBody();

    // Assert
    Vector expectedHeaders = createRequestResult.headers;
    assertEquals(expectedHeaders, createRequestResult.getHeaders());
    assertNull(createRequestResult.getBody());
  }

  @Test
  void testRemoveContentDispositionHeader() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.removeContentDispositionHeader();

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
  }

  @Test
  void testRemoveContentDispositionHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.removeContentDispositionHeader();

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testRemoveContentDispositionHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Content-Disposition"));

    // Act
    sipMessage.removeContentDispositionHeader();

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
  }

  @Test
  void testRemoveContentLengthHeader() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.removeContentLengthHeader();

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
  }

  @Test
  void testRemoveContentLengthHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.removeContentLengthHeader();

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testRemoveContentLengthHeader3() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");
    SipMessage createRequestResult = BasicSipMessageFactory.createRequest("Method", request_uri, to, from, "Call id",
        1L, "Local tag", "Remote tag", contact, "Not all who wander are lost",
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    // Act
    createRequestResult.removeContentLengthHeader();

    // Assert
    Vector expectedHeaders = createRequestResult.headers;
    assertEquals(expectedHeaders, createRequestResult.getHeaders());
  }

  @Test
  void testRemoveContentTypeHeader() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.removeContentTypeHeader();

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
  }

  @Test
  void testRemoveContentTypeHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.removeContentTypeHeader();

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testRemoveContentTypeHeader3() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");
    SipMessage createRequestResult = BasicSipMessageFactory.createRequest("Method", request_uri, to, from, "Call id",
        1L, "Local tag", "Remote tag", contact, "Not all who wander are lost",
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    // Act
    createRequestResult.removeContentTypeHeader();

    // Assert
    Vector expectedHeaders = createRequestResult.headers;
    assertEquals(expectedHeaders, createRequestResult.getHeaders());
  }

  @Test
  void testRemoveFirstLine() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.removeFirstLine();

    // Assert
    assertNull(sipMessage.getRequestLine());
    assertNull(sipMessage.getFirstLine());
  }

  @Test
  void testRemoveHeader() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.removeHeader("Hname");

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
  }

  @Test
  void testRemoveHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.removeHeader("Hname");

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
  }

  @Test
  void testRemoveHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRecordRouteHeader(new RecordRouteHeader(new NameAddress("Str")));

    // Act
    sipMessage.removeHeader("Hname");

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testRemoveHeader4() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.removeHeader("Hname", true);

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
  }

  @Test
  void testRemoveHeader5() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.removeHeader("Hname", true);

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
  }

  @Test
  void testRemoveHeader6() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRecordRouteHeader(new RecordRouteHeader(new NameAddress("Str")));

    // Act
    sipMessage.removeHeader("Hname", true);

    // Assert that nothing has changed
    assertEquals(0, sipMessage.getRemotePort());
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testRemoveRequestLine() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.removeRequestLine();

    // Assert
    assertNull(sipMessage.getRequestLine());
  }

  @Test
  void testRemoveStatusLine() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.removeStatusLine();

    // Assert
    assertNull(sipMessage.getFirstLine());
  }

  @Test
  void testSetBody() throws UnsupportedEncodingException {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.setBody("Not all who wander are lost", "Not all who wander are lost", "AAAAAAAA".getBytes("UTF-8"));

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
    assertEquals(122, sipMessage.getLength());
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
    assertEquals("Not", sipMessage.getBodyType());
    assertEquals("Not", sipMessage.getBodyDisposition());
  }

  @Test
  void testSetBody2() throws UnsupportedEncodingException {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.setBody("Not all who wander are lost", "Not all who wander are lost", "AAAAAAAA".getBytes("UTF-8"));

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
    assertEquals(131, sipMessage.getLength());
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
    assertEquals("Not", sipMessage.getBodyType());
    assertEquals("Not", sipMessage.getBodyDisposition());
  }

  @Test
  void testSetBody3() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");
    SipMessage createRequestResult = BasicSipMessageFactory.createRequest("Method", request_uri, to, from, "Call id",
        1L, "Local tag", "Remote tag", contact, "Not all who wander are lost",
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    // Act
    createRequestResult.setBody("Not all who wander are lost", "Not all who wander are lost",
        "AAAAAAAA".getBytes("UTF-8"));

    // Assert
    assertNull(createRequestResult.getAcceptEncodingHeader());
    assertEquals(364, createRequestResult.getLength());
    Vector expectedHeaders = createRequestResult.headers;
    assertEquals(expectedHeaders, createRequestResult.getHeaders());
    assertEquals("Not", createRequestResult.getBodyType());
    assertEquals("Not", createRequestResult.getBodyDisposition());
  }

  @Test
  void testSetBody4() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.setBody("Not all who wander are lost", "Not all who wander are lost", new byte[]{});

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
    assertEquals(21, sipMessage.getLength());
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
    assertNull(sipMessage.getBody());
  }

  @Test
  void testSetBody5() throws UnsupportedEncodingException {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.setBody("Not all who wander are lost", "AAAAAAAA".getBytes("UTF-8"));

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
    assertEquals(72, sipMessage.getLength());
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
    assertEquals("Not", sipMessage.getBodyType());
  }

  @Test
  void testSetBody6() throws UnsupportedEncodingException {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.setBody("Not all who wander are lost", "AAAAAAAA".getBytes("UTF-8"));

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
    assertEquals(81, sipMessage.getLength());
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
    assertEquals("Not", sipMessage.getBodyType());
  }

  @Test
  void testSetBody7() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");
    SipMessage createRequestResult = BasicSipMessageFactory.createRequest("Method", request_uri, to, from, "Call id",
        1L, "Local tag", "Remote tag", contact, "Not all who wander are lost",
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    // Act
    createRequestResult.setBody("Not all who wander are lost", "AAAAAAAA".getBytes("UTF-8"));

    // Assert
    assertNull(createRequestResult.getAcceptEncodingHeader());
    assertEquals(314, createRequestResult.getLength());
    Vector expectedHeaders = createRequestResult.headers;
    assertEquals(expectedHeaders, createRequestResult.getHeaders());
    assertEquals("Not", createRequestResult.getBodyType());
  }

  @Test
  void testSetBody8() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.setBody("Not all who wander are lost", new byte[]{});

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
    assertEquals(21, sipMessage.getLength());
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
    assertNull(sipMessage.getBody());
  }

  @Test
  void testSetBody9() throws UnsupportedEncodingException {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.setBody("AAAAAAAA".getBytes("UTF-8"));

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
    assertEquals(29, sipMessage.getLength());
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetBody10() throws UnsupportedEncodingException {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.setBody("AAAAAAAA".getBytes("UTF-8"));

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
    assertEquals(38, sipMessage.getLength());
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetBody11() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");
    SipMessage createRequestResult = BasicSipMessageFactory.createRequest("Method", request_uri, to, from, "Call id",
        1L, "Local tag", "Remote tag", contact, "Not all who wander are lost",
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    // Act
    createRequestResult.setBody("AAAAAAAA".getBytes("UTF-8"));

    // Assert
    assertNull(createRequestResult.getAcceptEncodingHeader());
    assertEquals(271, createRequestResult.getLength());
    Vector expectedHeaders = createRequestResult.headers;
    assertEquals(expectedHeaders, createRequestResult.getHeaders());
  }

  @Test
  void testSetBody12() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.setBody(new byte[]{});

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
    assertEquals(21, sipMessage.getLength());
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
    assertNull(sipMessage.getBody());
  }

  @Test
  void testSetConnectionId() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    ConnectionId connectionId = new ConnectionId("Protocol", new SocketAddress("42 Main St"));

    // Act
    sipMessage.setConnectionId(connectionId);

    // Assert
    assertSame(connectionId, sipMessage.getConnectionId());
  }

  @Test
  void testSetContentDispositionHeader() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.setContentDispositionHeader(new ContentDispositionHeader("42"));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetContentDispositionHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.setContentDispositionHeader(new ContentDispositionHeader("42"));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetContentDispositionHeader3() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");
    SipMessage createRequestResult = BasicSipMessageFactory.createRequest("Method", request_uri, to, from, "Call id",
        1L, "Local tag", "Remote tag", contact, "Not all who wander are lost",
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    // Act
    createRequestResult.setContentDispositionHeader(new ContentDispositionHeader("42"));

    // Assert
    Vector expectedHeaders = createRequestResult.headers;
    assertEquals(expectedHeaders, createRequestResult.getHeaders());
  }

  @Test
  void testSetContentDispositionHeader4() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Content-Type"));

    // Act
    sipMessage.setContentDispositionHeader(new ContentDispositionHeader("42"));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetContentDispositionHeader5() throws UnsupportedEncodingException {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.setBody("Not all who wander are lost", "Not all who wander are lost", "AAAAAAAA".getBytes("UTF-8"));
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.setContentDispositionHeader(new ContentDispositionHeader("42"));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetContentLengthHeader() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.setContentLengthHeader(new ContentLengthHeader(3));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetContentLengthHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.setContentLengthHeader(new ContentLengthHeader(3));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetContentLengthHeader3() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");
    SipMessage createRequestResult = BasicSipMessageFactory.createRequest("Method", request_uri, to, from, "Call id",
        1L, "Local tag", "Remote tag", contact, "Not all who wander are lost",
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    // Act
    createRequestResult.setContentLengthHeader(new ContentLengthHeader(3));

    // Assert
    Vector expectedHeaders = createRequestResult.headers;
    assertEquals(expectedHeaders, createRequestResult.getHeaders());
  }

  @Test
  void testSetContentLengthHeader4() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Content-Type"));

    // Act
    sipMessage.setContentLengthHeader(new ContentLengthHeader(3));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetContentTypeHeader() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.setContentTypeHeader(new ContentTypeHeader("42"));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetContentTypeHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.setContentTypeHeader(new ContentTypeHeader("42"));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetContentTypeHeader3() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");
    SipMessage createRequestResult = BasicSipMessageFactory.createRequest("Method", request_uri, to, from, "Call id",
        1L, "Local tag", "Remote tag", contact, "Not all who wander are lost",
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    // Act
    createRequestResult.setContentTypeHeader(new ContentTypeHeader("42"));

    // Assert
    Vector expectedHeaders = createRequestResult.headers;
    assertEquals(expectedHeaders, createRequestResult.getHeaders());
  }

  @Test
  void testSetContentTypeHeader4() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Content-Length"));

    // Act
    sipMessage.setContentTypeHeader(new ContentTypeHeader("42"));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetHeader() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.setHeader(new Header("Hname", "42"));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetHeader2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.setHeader(new Header("Hname", "42"));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetHeader3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRecordRouteHeader(new RecordRouteHeader(new NameAddress("Str")));

    // Act
    sipMessage.setHeader(new Header("Hname", "42"));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetHeader4() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");
    SipMessage createRequestResult = BasicSipMessageFactory.createRequest("Method", request_uri, to, from, "Call id",
        1L, "Local tag", "Remote tag", contact, "Not all who wander are lost",
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    // Act
    createRequestResult.setHeader(new Header("Hname", "42"));

    // Assert
    Vector expectedHeaders = createRequestResult.headers;
    assertEquals(expectedHeaders, createRequestResult.getHeaders());
  }

  @Test
  void testSetHeader5() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.setHeader(new Header("Hname", "42"));

    // Assert
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetHeaders() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.setHeaders(new MultipleHeader("Hname"));

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
    assertEquals(11, sipMessage.getLength());
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetHeaders2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.setHeaders(new MultipleHeader("Hname"));

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
    assertEquals(11, sipMessage.getLength());
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetHeaders3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRecordRouteHeader(new RecordRouteHeader(new NameAddress("Str")));

    // Act
    sipMessage.setHeaders(new MultipleHeader("Hname"));

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
    assertEquals(36, sipMessage.getLength());
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetHeaders4() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");
    SipMessage createRequestResult = BasicSipMessageFactory.createRequest("Method", request_uri, to, from, "Call id",
        1L, "Local tag", "Remote tag", contact, "Not all who wander are lost",
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    // Act
    createRequestResult.setHeaders(new MultipleHeader("Hname"));

    // Assert
    assertNull(createRequestResult.getAcceptEncodingHeader());
    assertEquals(340, createRequestResult.getLength());
    Vector expectedHeaders = createRequestResult.headers;
    assertEquals(expectedHeaders, createRequestResult.getHeaders());
  }

  @Test
  void testSetHeaders5() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.setHeaders(new MultipleHeader("Hname"));

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
    assertEquals(11, sipMessage.getLength());
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
  }

  @Test
  void testSetMessage() throws MalformedSipMessageException {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act and Assert
    assertEquals(19, sipMessage.setMessage("Content-Disposition"));
    assertEquals("Content-Disposition null SIP/2.0\r\n", sipMessage.getFirstLine());
  }

  @Test
  void testSetRemoteAddress() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.setRemoteAddress("42 Main St");

    // Assert
    assertEquals("42 Main St", sipMessage.getRemoteAddress());
  }

  @Test
  void testSetRemotePort() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.setRemotePort(8080);

    // Assert
    assertEquals(8080, sipMessage.getRemotePort());
  }

  @Test
  void testSetRequestLine() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.setRequestLine(new RequestLine("Request", "Str uri"));

    // Assert
    assertEquals("Request Str uri SIP/2.0\r\n", sipMessage.getFirstLine());
  }

  @Test
  void testSetSdpBody() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.setSdpBody("Not all who wander are lost");

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
    assertEquals(80, sipMessage.getLength());
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
    assertEquals("application/sdp", sipMessage.getBodyType());
    assertEquals(27, sipMessage.getBody().length);
  }

  @Test
  void testSetSdpBody2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act
    sipMessage.setSdpBody("Not all who wander are lost");

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
    assertEquals(89, sipMessage.getLength());
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
    assertEquals("application/sdp", sipMessage.getBodyType());
    assertEquals(27, sipMessage.getBody().length);
  }

  @Test
  void testSetSdpBody3() throws UnsupportedEncodingException {
    // Arrange
    GenericURI request_uri = new GenericURI("Uri");
    NameAddress to = new NameAddress("Str");
    NameAddress from = new NameAddress("Str");
    NameAddress contact = new NameAddress("Str");
    SipMessage createRequestResult = BasicSipMessageFactory.createRequest("Method", request_uri, to, from, "Call id",
        1L, "Local tag", "Remote tag", contact, "Not all who wander are lost",
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    // Act
    createRequestResult.setSdpBody("Not all who wander are lost");

    // Assert
    assertNull(createRequestResult.getAcceptEncodingHeader());
    assertEquals(322, createRequestResult.getLength());
    Vector expectedHeaders = createRequestResult.headers;
    assertEquals(expectedHeaders, createRequestResult.getHeaders());
    assertEquals("application/sdp", createRequestResult.getBodyType());
    assertEquals(27, createRequestResult.getBody().length);
  }

  @Test
  void testSetSdpBody4() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.setSdpBody("");

    // Assert
    assertNull(sipMessage.getAcceptEncodingHeader());
    assertEquals(21, sipMessage.getLength());
    Vector expectedHeaders = sipMessage.headers;
    assertEquals(expectedHeaders, sipMessage.getHeaders());
    assertNull(sipMessage.getBody());
  }

  @Test
  void testSetStatusLine() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");

    // Act
    sipMessage.setStatusLine(new StatusLine(1, "foo"));

    // Assert
    assertEquals("SIP/2.0 1 foo\r\n", sipMessage.getFirstLine());
  }

  @Test
  void testToString() {
    // Arrange, Act and Assert
    assertEquals("\r\n", (new SipMessage("Str")).toString());
    assertEquals("Accept null SIP/2.0\r\n\r\n", (new SipMessage("Accept")).toString());
  }

  @Test
  void testToString2() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertEquals("Hname: \r\n\r\n", sipMessage.toString());
  }

  @Test
  void testToString3() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.setStatusLine(new StatusLine(1, "foo"));
    sipMessage.addRoutes(new MultipleHeader("Hname"));

    // Act and Assert
    assertEquals("SIP/2.0 1 foo\r\nHname: \r\n\r\n", sipMessage.toString());
  }
}

