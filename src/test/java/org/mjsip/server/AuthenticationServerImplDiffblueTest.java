package org.mjsip.server;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.io.StringWriter;
import org.junit.jupiter.api.Test;
import org.mjsip.sip.header.AuthenticationHeader;
import org.mjsip.sip.header.AuthenticationInfoHeader;
import org.mjsip.sip.header.CSeqHeader;
import org.mjsip.sip.header.CallIdHeader;
import org.mjsip.sip.header.FromHeader;
import org.mjsip.sip.header.Header;
import org.mjsip.sip.header.MultipleHeader;
import org.mjsip.sip.header.ToHeader;
import org.mjsip.sip.message.SipMessage;
import org.zoolu.util.LogLevel;
import org.zoolu.util.LogWriter;
import org.zoolu.util.Logger;

class AuthenticationServerImplDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange
    AuthenticationServiceImpl authentication_service = new AuthenticationServiceImpl("File name");

    // Act
    AuthenticationServerImpl actualAuthenticationServerImpl = new AuthenticationServerImpl("Realm",
        authentication_service, new LogWriter(new StringWriter()));

    // Assert
    assertEquals("Realm", actualAuthenticationServerImpl.realm);
    assertEquals("Digest", actualAuthenticationServerImpl.authentication_scheme);
    assertTrue(actualAuthenticationServerImpl.logger instanceof LogWriter);
    assertEquals("auth", actualAuthenticationServerImpl.qop_options);
    assertTrue(actualAuthenticationServerImpl.authentication_service instanceof AuthenticationServiceImpl);
    assertEquals(Short.SIZE, actualAuthenticationServerImpl.rand.length);
    assertEquals(" ", AuthenticationHeader.LWS_SEPARATOR);
  }

  @Test
  void testAuthenticateRequest() {
    // Arrange
    Logger logger = mock(Logger.class);
    doNothing().when(logger).log((org.zoolu.util.LogLevel) any(), (String) any());
    AuthenticationServerImpl authenticationServerImpl = new AuthenticationServerImpl("Realm",
        new AuthenticationServiceImpl("File name"), logger);
    SipMessage sipMessage = mock(SipMessage.class);
    when(sipMessage.getCSeqHeader()).thenReturn(new CSeqHeader("42"));
    when(sipMessage.getCallIdHeader()).thenReturn(new CallIdHeader("42"));
    when(sipMessage.getFromHeader()).thenReturn(new FromHeader(new Header("Hname", "42")));
    when(sipMessage.getToHeader()).thenReturn(new ToHeader(new Header("Hname", "42")));
    when(sipMessage.getVias()).thenReturn(new MultipleHeader("Hname"));
    when(sipMessage.getAuthorizationHeader()).thenReturn(null);

    // Act
    SipMessage actualAuthenticateRequestResult = authenticationServerImpl.authenticateRequest(sipMessage);

    // Assert
    assertNull(actualAuthenticateRequestResult.getAcceptEncodingHeader());
    assertNull(actualAuthenticateRequestResult.getRequestLine());
    assertEquals(0, actualAuthenticateRequestResult.getRemotePort());
    assertNull(actualAuthenticateRequestResult.getRemoteAddress());
    assertEquals(194, actualAuthenticateRequestResult.getLength());
    assertEquals("SIP/2.0 401 Unauthorized\r\n", actualAuthenticateRequestResult.getFirstLine());
    assertNull(actualAuthenticateRequestResult.getConnectionId());
    assertNull(actualAuthenticateRequestResult.getBody());
    verify(logger).log((org.zoolu.util.LogLevel) any(), (String) any());
    verify(sipMessage).getAuthorizationHeader();
    verify(sipMessage).getCSeqHeader();
    verify(sipMessage).getCallIdHeader();
    verify(sipMessage).getFromHeader();
    verify(sipMessage).getToHeader();
    verify(sipMessage).getVias();
  }

  @Test
  void testAuthenticateRequest2() {
    // Arrange
    Logger logger = mock(Logger.class);
    doNothing().when(logger).log((org.zoolu.util.LogLevel) any(), (String) any());
    AuthenticationServerImpl authenticationServerImpl = new AuthenticationServerImpl("Content-Length",
        new AuthenticationServiceImpl("File name"), logger);
    SipMessage sipMessage = mock(SipMessage.class);
    when(sipMessage.getCSeqHeader()).thenReturn(new CSeqHeader("42"));
    when(sipMessage.getCallIdHeader()).thenReturn(new CallIdHeader("42"));
    when(sipMessage.getFromHeader()).thenReturn(new FromHeader(new Header("Hname", "42")));
    when(sipMessage.getToHeader()).thenReturn(new ToHeader(new Header("Hname", "42")));
    when(sipMessage.getVias()).thenReturn(new MultipleHeader("Hname"));
    when(sipMessage.getAuthorizationHeader()).thenReturn(null);

    // Act
    SipMessage actualAuthenticateRequestResult = authenticationServerImpl.authenticateRequest(sipMessage);

    // Assert
    assertNull(actualAuthenticateRequestResult.getAcceptEncodingHeader());
    assertNull(actualAuthenticateRequestResult.getRequestLine());
    assertEquals(0, actualAuthenticateRequestResult.getRemotePort());
    assertNull(actualAuthenticateRequestResult.getRemoteAddress());
    assertEquals(203, actualAuthenticateRequestResult.getLength());
    assertEquals("SIP/2.0 401 Unauthorized\r\n", actualAuthenticateRequestResult.getFirstLine());
    assertNull(actualAuthenticateRequestResult.getConnectionId());
    assertNull(actualAuthenticateRequestResult.getBody());
    verify(logger).log((org.zoolu.util.LogLevel) any(), (String) any());
    verify(sipMessage).getAuthorizationHeader();
    verify(sipMessage).getCSeqHeader();
    verify(sipMessage).getCallIdHeader();
    verify(sipMessage).getFromHeader();
    verify(sipMessage).getToHeader();
    verify(sipMessage).getVias();
  }

  @Test
  void testAuthenticateRequest3() {
    // Arrange
    Logger logger = mock(Logger.class);
    doNothing().when(logger).log((org.zoolu.util.LogLevel) any(), (String) any());
    AuthenticationServerImpl authenticationServerImpl = new AuthenticationServerImpl("Realm",
        new AuthenticationServiceImpl("File name"), logger);
    SipMessage sipMessage = mock(SipMessage.class);
    when(sipMessage.getCSeqHeader()).thenReturn(new CSeqHeader("42"));
    when(sipMessage.getCallIdHeader()).thenReturn(new CallIdHeader("42"));
    when(sipMessage.getFromHeader()).thenReturn(new FromHeader(new Header("Hname", "42")));
    when(sipMessage.getToHeader()).thenReturn(new ToHeader(new Header("Hname", "42")));
    when(sipMessage.getVias()).thenReturn(new MultipleHeader("Hname"));
    when(sipMessage.getProxyAuthorizationHeader()).thenReturn(null);

    // Act
    SipMessage actualAuthenticateRequestResult = authenticationServerImpl.authenticateRequest(sipMessage, 1);

    // Assert
    assertNull(actualAuthenticateRequestResult.getAcceptEncodingHeader());
    assertNull(actualAuthenticateRequestResult.getRequestLine());
    assertEquals(0, actualAuthenticateRequestResult.getRemotePort());
    assertNull(actualAuthenticateRequestResult.getRemoteAddress());
    assertEquals(213, actualAuthenticateRequestResult.getLength());
    assertEquals("SIP/2.0 407 Proxy Authentication Required\r\n", actualAuthenticateRequestResult.getFirstLine());
    assertNull(actualAuthenticateRequestResult.getConnectionId());
    assertNull(actualAuthenticateRequestResult.getBody());
    assertEquals(10, AuthenticationHeader.QUOTED_PARAMETERS.length);
    verify(logger).log((org.zoolu.util.LogLevel) any(), (String) any());
    verify(sipMessage).getCSeqHeader();
    verify(sipMessage).getCallIdHeader();
    verify(sipMessage).getFromHeader();
    verify(sipMessage).getProxyAuthorizationHeader();
    verify(sipMessage).getToHeader();
    verify(sipMessage).getVias();
  }

  @Test
  void testAuthenticateRequest4() {
    // Arrange
    Logger logger = mock(Logger.class);
    doNothing().when(logger).log((org.zoolu.util.LogLevel) any(), (String) any());
    AuthenticationServerImpl authenticationServerImpl = new AuthenticationServerImpl("Content-Length",
        new AuthenticationServiceImpl("File name"), logger);
    SipMessage sipMessage = mock(SipMessage.class);
    when(sipMessage.getCSeqHeader()).thenReturn(new CSeqHeader("42"));
    when(sipMessage.getCallIdHeader()).thenReturn(new CallIdHeader("42"));
    when(sipMessage.getFromHeader()).thenReturn(new FromHeader(new Header("Hname", "42")));
    when(sipMessage.getToHeader()).thenReturn(new ToHeader(new Header("Hname", "42")));
    when(sipMessage.getVias()).thenReturn(new MultipleHeader("Hname"));
    when(sipMessage.getProxyAuthorizationHeader()).thenReturn(null);

    // Act
    SipMessage actualAuthenticateRequestResult = authenticationServerImpl.authenticateRequest(sipMessage, 1);

    // Assert
    assertNull(actualAuthenticateRequestResult.getAcceptEncodingHeader());
    assertNull(actualAuthenticateRequestResult.getRequestLine());
    assertEquals(0, actualAuthenticateRequestResult.getRemotePort());
    assertNull(actualAuthenticateRequestResult.getRemoteAddress());
    assertEquals(222, actualAuthenticateRequestResult.getLength());
    assertEquals("SIP/2.0 407 Proxy Authentication Required\r\n", actualAuthenticateRequestResult.getFirstLine());
    assertNull(actualAuthenticateRequestResult.getConnectionId());
    assertNull(actualAuthenticateRequestResult.getBody());
    assertEquals(10, AuthenticationHeader.QUOTED_PARAMETERS.length);
    verify(logger).log((org.zoolu.util.LogLevel) any(), (String) any());
    verify(sipMessage).getCSeqHeader();
    verify(sipMessage).getCallIdHeader();
    verify(sipMessage).getFromHeader();
    verify(sipMessage).getProxyAuthorizationHeader();
    verify(sipMessage).getToHeader();
    verify(sipMessage).getVias();
  }

  @Test
  void testGetAuthenticationInfoHeader() {
    // Arrange
    AuthenticationServiceImpl authentication_service = new AuthenticationServiceImpl("File name");

    // Act
    AuthenticationInfoHeader actualAuthenticationInfoHeader = (new AuthenticationServerImpl("Realm",
        authentication_service, new LogWriter(new StringWriter()))).getAuthenticationInfoHeader();

    // Assert
    assertNull(actualAuthenticationInfoHeader.getAlgorithParam());
    assertEquals("Authentication-Info", actualAuthenticationInfoHeader.getName());
  }

  @Test
  void testLog() {
    // Arrange
    AuthenticationServiceImpl authentication_service = new AuthenticationServiceImpl("File name");
    AuthenticationServerImpl authenticationServerImpl = new AuthenticationServerImpl("Realm", authentication_service,
        new LogWriter(new StringWriter()));
    LogLevel logLevel = new LogLevel("Name", 42);

    // Act
    authenticationServerImpl.log(logLevel, "Str");

    // Assert that nothing has changed
    assertEquals("Name", logLevel.getName());
    assertEquals(42, logLevel.getValue());
    assertEquals("Realm", authenticationServerImpl.realm);
    assertEquals("Digest", authenticationServerImpl.authentication_scheme);
    assertTrue(authenticationServerImpl.logger instanceof LogWriter);
    assertEquals("auth", authenticationServerImpl.qop_options);
    assertTrue(authenticationServerImpl.authentication_service instanceof AuthenticationServiceImpl);
    assertEquals(Short.SIZE, authenticationServerImpl.rand.length);
  }

  @Test
  void testLog2() {
    // Arrange
    AuthenticationServerImpl authenticationServerImpl = new AuthenticationServerImpl("Realm",
        new AuthenticationServiceImpl("File name"), null);

    // Act
    authenticationServerImpl.log(null, "Str");

    // Assert that nothing has changed
    assertEquals("Realm", authenticationServerImpl.realm);
    assertEquals("Digest", authenticationServerImpl.authentication_scheme);
    assertEquals("auth", authenticationServerImpl.qop_options);
    assertTrue(authenticationServerImpl.authentication_service instanceof AuthenticationServiceImpl);
    assertEquals(Short.SIZE, authenticationServerImpl.rand.length);
  }
}

