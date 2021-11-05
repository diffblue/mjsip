package org.mjsip.sip.authentication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.UnsupportedEncodingException;
import java.util.Vector;
import org.junit.jupiter.api.Test;
import org.mjsip.sip.header.AuthorizationHeader;
import org.mjsip.sip.header.Header;
import org.mjsip.sip.header.ProxyAuthorizationHeader;
import org.mjsip.sip.header.WwwAuthenticateHeader;

class DigestAuthenticationDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    DigestAuthentication actualDigestAuthentication = new DigestAuthentication();

    // Assert
    assertEquals("dda66cb4f71fbceb12f680e55c08d6a7", actualDigestAuthentication.getResponse());
    assertNull(actualDigestAuthentication.username);
    assertNull(actualDigestAuthentication.uri);
    assertNull(actualDigestAuthentication.response);
    assertNull(actualDigestAuthentication.realm);
    assertNull(actualDigestAuthentication.qop);
    assertNull(actualDigestAuthentication.passwd);
    assertNull(actualDigestAuthentication.opaque);
    assertNull(actualDigestAuthentication.nonce);
    assertNull(actualDigestAuthentication.nc);
    assertNull(actualDigestAuthentication.method);
    assertNull(actualDigestAuthentication.cnonce);
    assertNull(actualDigestAuthentication.body);
    assertNull(actualDigestAuthentication.algorithm);
  }

  @Test
  void testConstructor2() throws UnsupportedEncodingException {
    // Arrange
    WwwAuthenticateHeader ah = new WwwAuthenticateHeader("42");

    // Act
    DigestAuthentication actualDigestAuthentication = new DigestAuthentication("Method", "Uri", ah, "Qop", "Cnonce", 1,
        "AAAAAAAA".getBytes("UTF-8"), "janedoe", "iloveyou");

    // Assert
    assertEquals("janedoe", actualDigestAuthentication.username);
    assertEquals("Uri", actualDigestAuthentication.uri);
    assertNull(actualDigestAuthentication.response);
    assertNull(actualDigestAuthentication.realm);
    assertEquals("Qop", actualDigestAuthentication.qop);
    assertEquals("iloveyou", actualDigestAuthentication.passwd);
    assertNull(actualDigestAuthentication.opaque);
    assertNull(actualDigestAuthentication.nonce);
    assertEquals("00000001", actualDigestAuthentication.nc);
    assertEquals("Method", actualDigestAuthentication.method);
    assertEquals(8, actualDigestAuthentication.body.length);
    assertNull(actualDigestAuthentication.algorithm);
  }

  @Test
  void testConstructor3() throws UnsupportedEncodingException {
    // Arrange
    WwwAuthenticateHeader ah = new WwwAuthenticateHeader("algorithm");

    // Act
    DigestAuthentication actualDigestAuthentication = new DigestAuthentication("Method", "Uri", ah, "Qop", "Cnonce", 1,
        "AAAAAAAA".getBytes("UTF-8"), "janedoe", "iloveyou");

    // Assert
    assertEquals("janedoe", actualDigestAuthentication.username);
    assertEquals("Uri", actualDigestAuthentication.uri);
    assertNull(actualDigestAuthentication.response);
    assertNull(actualDigestAuthentication.realm);
    assertEquals("Qop", actualDigestAuthentication.qop);
    assertEquals("iloveyou", actualDigestAuthentication.passwd);
    assertNull(actualDigestAuthentication.opaque);
    assertNull(actualDigestAuthentication.nonce);
    assertEquals("00000001", actualDigestAuthentication.nc);
    assertEquals("Method", actualDigestAuthentication.method);
    assertEquals(8, actualDigestAuthentication.body.length);
    assertNull(actualDigestAuthentication.algorithm);
  }

  @Test
  void testConstructor4() throws UnsupportedEncodingException {
    // Arrange
    WwwAuthenticateHeader ah = new WwwAuthenticateHeader("Auth scheme", new Vector(1));

    // Act
    DigestAuthentication actualDigestAuthentication = new DigestAuthentication("Method", "Uri", ah, "Qop", "Cnonce", 1,
        "AAAAAAAA".getBytes("UTF-8"), "janedoe", "iloveyou");

    // Assert
    assertEquals("janedoe", actualDigestAuthentication.username);
    assertEquals("Uri", actualDigestAuthentication.uri);
    assertNull(actualDigestAuthentication.response);
    assertNull(actualDigestAuthentication.realm);
    assertEquals("Qop", actualDigestAuthentication.qop);
    assertEquals("iloveyou", actualDigestAuthentication.passwd);
    assertNull(actualDigestAuthentication.opaque);
    assertNull(actualDigestAuthentication.nonce);
    assertEquals("00000001", actualDigestAuthentication.nc);
    assertEquals("Method", actualDigestAuthentication.method);
    assertEquals(8, actualDigestAuthentication.body.length);
    assertNull(actualDigestAuthentication.algorithm);
  }

  @Test
  void testConstructor5() throws UnsupportedEncodingException {
    // Arrange
    WwwAuthenticateHeader ah = new WwwAuthenticateHeader("");

    // Act
    DigestAuthentication actualDigestAuthentication = new DigestAuthentication("Method", "Uri", ah, "Qop", "Cnonce", 1,
        "AAAAAAAA".getBytes("UTF-8"), "janedoe", "iloveyou");

    // Assert
    assertEquals("janedoe", actualDigestAuthentication.username);
    assertEquals("Uri", actualDigestAuthentication.uri);
    assertNull(actualDigestAuthentication.response);
    assertNull(actualDigestAuthentication.realm);
    assertEquals("Qop", actualDigestAuthentication.qop);
    assertEquals("iloveyou", actualDigestAuthentication.passwd);
    assertNull(actualDigestAuthentication.opaque);
    assertNull(actualDigestAuthentication.nonce);
    assertEquals("00000001", actualDigestAuthentication.nc);
    assertEquals("Method", actualDigestAuthentication.method);
    assertEquals(8, actualDigestAuthentication.body.length);
    assertNull(actualDigestAuthentication.algorithm);
  }

  @Test
  void testConstructor6() throws UnsupportedEncodingException {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addAutsParam("username");

    // Act
    DigestAuthentication actualDigestAuthentication = new DigestAuthentication("Method", "Uri", wwwAuthenticateHeader,
        "Qop", "Cnonce", 1, "AAAAAAAA".getBytes("UTF-8"), "janedoe", "iloveyou");

    // Assert
    assertEquals("janedoe", actualDigestAuthentication.username);
    assertEquals("Uri", actualDigestAuthentication.uri);
    assertNull(actualDigestAuthentication.response);
    assertNull(actualDigestAuthentication.realm);
    assertEquals("Qop", actualDigestAuthentication.qop);
    assertEquals("iloveyou", actualDigestAuthentication.passwd);
    assertNull(actualDigestAuthentication.opaque);
    assertNull(actualDigestAuthentication.nonce);
    assertEquals("00000001", actualDigestAuthentication.nc);
    assertEquals("Method", actualDigestAuthentication.method);
    assertEquals(8, actualDigestAuthentication.body.length);
    assertNull(actualDigestAuthentication.algorithm);
  }

  @Test
  void testConstructor7() throws UnsupportedEncodingException {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addCnonceParam("username");

    // Act
    DigestAuthentication actualDigestAuthentication = new DigestAuthentication("Method", "Uri", wwwAuthenticateHeader,
        "Qop", "Cnonce", 1, "AAAAAAAA".getBytes("UTF-8"), "janedoe", "iloveyou");

    // Assert
    assertEquals("janedoe", actualDigestAuthentication.username);
    assertEquals("Uri", actualDigestAuthentication.uri);
    assertNull(actualDigestAuthentication.response);
    assertNull(actualDigestAuthentication.realm);
    assertEquals("Qop", actualDigestAuthentication.qop);
    assertEquals("iloveyou", actualDigestAuthentication.passwd);
    assertNull(actualDigestAuthentication.opaque);
    assertNull(actualDigestAuthentication.nonce);
    assertEquals("00000001", actualDigestAuthentication.nc);
    assertEquals("Method", actualDigestAuthentication.method);
    assertEquals("Cnonce", actualDigestAuthentication.cnonce);
    assertEquals(8, actualDigestAuthentication.body.length);
    assertNull(actualDigestAuthentication.algorithm);
  }

  @Test
  void testConstructor8() throws UnsupportedEncodingException {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addNcParam("username");

    // Act
    DigestAuthentication actualDigestAuthentication = new DigestAuthentication("Method", "Uri", wwwAuthenticateHeader,
        "Qop", "Cnonce", 1, "AAAAAAAA".getBytes("UTF-8"), "janedoe", "iloveyou");

    // Assert
    assertEquals("janedoe", actualDigestAuthentication.username);
    assertEquals("Uri", actualDigestAuthentication.uri);
    assertNull(actualDigestAuthentication.response);
    assertNull(actualDigestAuthentication.realm);
    assertEquals("Qop", actualDigestAuthentication.qop);
    assertEquals("iloveyou", actualDigestAuthentication.passwd);
    assertNull(actualDigestAuthentication.opaque);
    assertNull(actualDigestAuthentication.nonce);
    assertEquals("00000001", actualDigestAuthentication.nc);
    assertEquals("Method", actualDigestAuthentication.method);
    assertEquals(8, actualDigestAuthentication.body.length);
    assertNull(actualDigestAuthentication.algorithm);
  }

  @Test
  void testConstructor9() throws UnsupportedEncodingException {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addAutsParam("username");
    wwwAuthenticateHeader.addAutsParam("username");

    // Act
    DigestAuthentication actualDigestAuthentication = new DigestAuthentication("Method", "Uri", wwwAuthenticateHeader,
        "Qop", "Cnonce", 1, "AAAAAAAA".getBytes("UTF-8"), "janedoe", "iloveyou");

    // Assert
    assertEquals("janedoe", actualDigestAuthentication.username);
    assertEquals("Uri", actualDigestAuthentication.uri);
    assertNull(actualDigestAuthentication.response);
    assertNull(actualDigestAuthentication.realm);
    assertEquals("Qop", actualDigestAuthentication.qop);
    assertEquals("iloveyou", actualDigestAuthentication.passwd);
    assertNull(actualDigestAuthentication.opaque);
    assertNull(actualDigestAuthentication.nonce);
    assertEquals("00000001", actualDigestAuthentication.nc);
    assertEquals("Method", actualDigestAuthentication.method);
    assertEquals(8, actualDigestAuthentication.body.length);
    assertNull(actualDigestAuthentication.algorithm);
  }

  @Test
  void testConstructor10() throws UnsupportedEncodingException {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addCnonceParam("username");
    wwwAuthenticateHeader.addAutsParam("username");

    // Act
    DigestAuthentication actualDigestAuthentication = new DigestAuthentication("Method", "Uri", wwwAuthenticateHeader,
        "Qop", "Cnonce", 1, "AAAAAAAA".getBytes("UTF-8"), "janedoe", "iloveyou");

    // Assert
    assertEquals("janedoe", actualDigestAuthentication.username);
    assertEquals("Uri", actualDigestAuthentication.uri);
    assertNull(actualDigestAuthentication.response);
    assertNull(actualDigestAuthentication.realm);
    assertEquals("Qop", actualDigestAuthentication.qop);
    assertEquals("iloveyou", actualDigestAuthentication.passwd);
    assertNull(actualDigestAuthentication.opaque);
    assertNull(actualDigestAuthentication.nonce);
    assertEquals("00000001", actualDigestAuthentication.nc);
    assertEquals("Method", actualDigestAuthentication.method);
    assertEquals("Cnonce", actualDigestAuthentication.cnonce);
    assertEquals(8, actualDigestAuthentication.body.length);
    assertNull(actualDigestAuthentication.algorithm);
  }

  @Test
  void testConstructor11() throws UnsupportedEncodingException {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addParameter("", "42");

    // Act
    DigestAuthentication actualDigestAuthentication = new DigestAuthentication("Method", "Uri", wwwAuthenticateHeader,
        "Qop", "Cnonce", 1, "AAAAAAAA".getBytes("UTF-8"), "janedoe", "iloveyou");

    // Assert
    assertEquals("janedoe", actualDigestAuthentication.username);
    assertEquals("Uri", actualDigestAuthentication.uri);
    assertNull(actualDigestAuthentication.response);
    assertNull(actualDigestAuthentication.realm);
    assertEquals("Qop", actualDigestAuthentication.qop);
    assertEquals("iloveyou", actualDigestAuthentication.passwd);
    assertNull(actualDigestAuthentication.opaque);
    assertNull(actualDigestAuthentication.nonce);
    assertEquals("00000001", actualDigestAuthentication.nc);
    assertEquals("Method", actualDigestAuthentication.method);
    assertEquals(8, actualDigestAuthentication.body.length);
    assertNull(actualDigestAuthentication.algorithm);
  }

  @Test
  void testConstructor12() throws UnsupportedEncodingException {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addNcParam("");

    // Act
    DigestAuthentication actualDigestAuthentication = new DigestAuthentication("Method", "Uri", wwwAuthenticateHeader,
        "Qop", "Cnonce", 1, "AAAAAAAA".getBytes("UTF-8"), "janedoe", "iloveyou");

    // Assert
    assertEquals("janedoe", actualDigestAuthentication.username);
    assertEquals("Uri", actualDigestAuthentication.uri);
    assertNull(actualDigestAuthentication.response);
    assertNull(actualDigestAuthentication.realm);
    assertEquals("Qop", actualDigestAuthentication.qop);
    assertEquals("iloveyou", actualDigestAuthentication.passwd);
    assertNull(actualDigestAuthentication.opaque);
    assertNull(actualDigestAuthentication.nonce);
    assertEquals("00000001", actualDigestAuthentication.nc);
    assertEquals("Method", actualDigestAuthentication.method);
    assertEquals(8, actualDigestAuthentication.body.length);
    assertNull(actualDigestAuthentication.algorithm);
  }

  @Test
  void testConstructor13() throws UnsupportedEncodingException {
    // Arrange
    AuthorizationHeader ah = new AuthorizationHeader("42");

    // Act
    DigestAuthentication actualDigestAuthentication = new DigestAuthentication("Method", ah,
        "AAAAAAAA".getBytes("UTF-8"), "iloveyou");

    // Assert
    assertNull(actualDigestAuthentication.username);
    assertNull(actualDigestAuthentication.uri);
    assertNull(actualDigestAuthentication.response);
    assertNull(actualDigestAuthentication.realm);
    assertNull(actualDigestAuthentication.qop);
    assertEquals("iloveyou", actualDigestAuthentication.passwd);
    assertNull(actualDigestAuthentication.opaque);
    assertNull(actualDigestAuthentication.nonce);
    assertNull(actualDigestAuthentication.nc);
    assertEquals("Method", actualDigestAuthentication.method);
    assertNull(actualDigestAuthentication.cnonce);
    assertEquals(8, actualDigestAuthentication.body.length);
    assertNull(actualDigestAuthentication.algorithm);
  }

  @Test
  void testConstructor14() throws UnsupportedEncodingException {
    // Arrange
    AuthorizationHeader ah = new AuthorizationHeader("");

    // Act
    DigestAuthentication actualDigestAuthentication = new DigestAuthentication("Method", ah,
        "AAAAAAAA".getBytes("UTF-8"), "iloveyou");

    // Assert
    assertNull(actualDigestAuthentication.username);
    assertNull(actualDigestAuthentication.uri);
    assertNull(actualDigestAuthentication.response);
    assertNull(actualDigestAuthentication.realm);
    assertNull(actualDigestAuthentication.qop);
    assertEquals("iloveyou", actualDigestAuthentication.passwd);
    assertNull(actualDigestAuthentication.opaque);
    assertNull(actualDigestAuthentication.nonce);
    assertNull(actualDigestAuthentication.nc);
    assertEquals("Method", actualDigestAuthentication.method);
    assertNull(actualDigestAuthentication.cnonce);
    assertEquals(8, actualDigestAuthentication.body.length);
    assertNull(actualDigestAuthentication.algorithm);
  }

  @Test
  void testConstructor15() throws UnsupportedEncodingException {
    // Arrange
    AuthorizationHeader ah = new AuthorizationHeader("Auth scheme", new Vector(1));

    // Act
    DigestAuthentication actualDigestAuthentication = new DigestAuthentication("Method", ah,
        "AAAAAAAA".getBytes("UTF-8"), "iloveyou");

    // Assert
    assertNull(actualDigestAuthentication.username);
    assertNull(actualDigestAuthentication.uri);
    assertNull(actualDigestAuthentication.response);
    assertNull(actualDigestAuthentication.realm);
    assertNull(actualDigestAuthentication.qop);
    assertEquals("iloveyou", actualDigestAuthentication.passwd);
    assertNull(actualDigestAuthentication.opaque);
    assertNull(actualDigestAuthentication.nonce);
    assertNull(actualDigestAuthentication.nc);
    assertEquals("Method", actualDigestAuthentication.method);
    assertNull(actualDigestAuthentication.cnonce);
    assertEquals(8, actualDigestAuthentication.body.length);
    assertNull(actualDigestAuthentication.algorithm);
  }

  @Test
  void testConstructor16() throws UnsupportedEncodingException {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addAutsParam("username");

    // Act
    DigestAuthentication actualDigestAuthentication = new DigestAuthentication("Method", authorizationHeader,
        "AAAAAAAA".getBytes("UTF-8"), "iloveyou");

    // Assert
    assertNull(actualDigestAuthentication.username);
    assertNull(actualDigestAuthentication.uri);
    assertNull(actualDigestAuthentication.response);
    assertNull(actualDigestAuthentication.realm);
    assertNull(actualDigestAuthentication.qop);
    assertEquals("iloveyou", actualDigestAuthentication.passwd);
    assertNull(actualDigestAuthentication.opaque);
    assertNull(actualDigestAuthentication.nonce);
    assertNull(actualDigestAuthentication.nc);
    assertEquals("Method", actualDigestAuthentication.method);
    assertNull(actualDigestAuthentication.cnonce);
    assertEquals(8, actualDigestAuthentication.body.length);
    assertNull(actualDigestAuthentication.algorithm);
  }

  @Test
  void testConstructor17() throws UnsupportedEncodingException {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addCnonceParam("username");

    // Act
    DigestAuthentication actualDigestAuthentication = new DigestAuthentication("Method", authorizationHeader,
        "AAAAAAAA".getBytes("UTF-8"), "iloveyou");

    // Assert
    assertNull(actualDigestAuthentication.username);
    assertNull(actualDigestAuthentication.uri);
    assertNull(actualDigestAuthentication.response);
    assertNull(actualDigestAuthentication.realm);
    assertNull(actualDigestAuthentication.qop);
    assertEquals("iloveyou", actualDigestAuthentication.passwd);
    assertNull(actualDigestAuthentication.opaque);
    assertNull(actualDigestAuthentication.nonce);
    assertNull(actualDigestAuthentication.nc);
    assertEquals("Method", actualDigestAuthentication.method);
    assertEquals("username", actualDigestAuthentication.cnonce);
    assertEquals(8, actualDigestAuthentication.body.length);
    assertNull(actualDigestAuthentication.algorithm);
  }

  @Test
  void testConstructor18() throws UnsupportedEncodingException {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addNcParam("username");

    // Act
    DigestAuthentication actualDigestAuthentication = new DigestAuthentication("Method", authorizationHeader,
        "AAAAAAAA".getBytes("UTF-8"), "iloveyou");

    // Assert
    assertNull(actualDigestAuthentication.username);
    assertNull(actualDigestAuthentication.uri);
    assertNull(actualDigestAuthentication.response);
    assertNull(actualDigestAuthentication.realm);
    assertNull(actualDigestAuthentication.qop);
    assertEquals("iloveyou", actualDigestAuthentication.passwd);
    assertNull(actualDigestAuthentication.opaque);
    assertNull(actualDigestAuthentication.nonce);
    assertEquals("username", actualDigestAuthentication.nc);
    assertEquals("Method", actualDigestAuthentication.method);
    assertNull(actualDigestAuthentication.cnonce);
    assertEquals(8, actualDigestAuthentication.body.length);
    assertNull(actualDigestAuthentication.algorithm);
  }

  @Test
  void testConstructor19() throws UnsupportedEncodingException {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader(new Header("Hname", "42"));
    authorizationHeader.addAutsParam("username");

    // Act
    DigestAuthentication actualDigestAuthentication = new DigestAuthentication("Method", authorizationHeader,
        "AAAAAAAA".getBytes("UTF-8"), "iloveyou");

    // Assert
    assertNull(actualDigestAuthentication.username);
    assertNull(actualDigestAuthentication.uri);
    assertNull(actualDigestAuthentication.response);
    assertNull(actualDigestAuthentication.realm);
    assertNull(actualDigestAuthentication.qop);
    assertEquals("iloveyou", actualDigestAuthentication.passwd);
    assertNull(actualDigestAuthentication.opaque);
    assertNull(actualDigestAuthentication.nonce);
    assertNull(actualDigestAuthentication.nc);
    assertEquals("Method", actualDigestAuthentication.method);
    assertNull(actualDigestAuthentication.cnonce);
    assertEquals(8, actualDigestAuthentication.body.length);
    assertNull(actualDigestAuthentication.algorithm);
  }

  @Test
  void testConstructor20() throws UnsupportedEncodingException {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addAutsParam("username");
    authorizationHeader.addAutsParam("username");

    // Act
    DigestAuthentication actualDigestAuthentication = new DigestAuthentication("Method", authorizationHeader,
        "AAAAAAAA".getBytes("UTF-8"), "iloveyou");

    // Assert
    assertNull(actualDigestAuthentication.username);
    assertNull(actualDigestAuthentication.uri);
    assertNull(actualDigestAuthentication.response);
    assertNull(actualDigestAuthentication.realm);
    assertNull(actualDigestAuthentication.qop);
    assertEquals("iloveyou", actualDigestAuthentication.passwd);
    assertNull(actualDigestAuthentication.opaque);
    assertNull(actualDigestAuthentication.nonce);
    assertNull(actualDigestAuthentication.nc);
    assertEquals("Method", actualDigestAuthentication.method);
    assertNull(actualDigestAuthentication.cnonce);
    assertEquals(8, actualDigestAuthentication.body.length);
    assertNull(actualDigestAuthentication.algorithm);
  }

  @Test
  void testConstructor21() throws UnsupportedEncodingException {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addCnonceParam("username");
    authorizationHeader.addAutsParam("username");

    // Act
    DigestAuthentication actualDigestAuthentication = new DigestAuthentication("Method", authorizationHeader,
        "AAAAAAAA".getBytes("UTF-8"), "iloveyou");

    // Assert
    assertNull(actualDigestAuthentication.username);
    assertNull(actualDigestAuthentication.uri);
    assertNull(actualDigestAuthentication.response);
    assertNull(actualDigestAuthentication.realm);
    assertNull(actualDigestAuthentication.qop);
    assertEquals("iloveyou", actualDigestAuthentication.passwd);
    assertNull(actualDigestAuthentication.opaque);
    assertNull(actualDigestAuthentication.nonce);
    assertNull(actualDigestAuthentication.nc);
    assertEquals("Method", actualDigestAuthentication.method);
    assertEquals("username", actualDigestAuthentication.cnonce);
    assertEquals(8, actualDigestAuthentication.body.length);
    assertNull(actualDigestAuthentication.algorithm);
  }

  @Test
  void testConstructor22() throws UnsupportedEncodingException {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addParameter("", "42");

    // Act
    DigestAuthentication actualDigestAuthentication = new DigestAuthentication("Method", authorizationHeader,
        "AAAAAAAA".getBytes("UTF-8"), "iloveyou");

    // Assert
    assertNull(actualDigestAuthentication.username);
    assertNull(actualDigestAuthentication.uri);
    assertNull(actualDigestAuthentication.response);
    assertNull(actualDigestAuthentication.realm);
    assertNull(actualDigestAuthentication.qop);
    assertEquals("iloveyou", actualDigestAuthentication.passwd);
    assertNull(actualDigestAuthentication.opaque);
    assertNull(actualDigestAuthentication.nonce);
    assertNull(actualDigestAuthentication.nc);
    assertEquals("Method", actualDigestAuthentication.method);
    assertNull(actualDigestAuthentication.cnonce);
    assertEquals(8, actualDigestAuthentication.body.length);
    assertNull(actualDigestAuthentication.algorithm);
  }

  @Test
  void testConstructor23() throws UnsupportedEncodingException {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addNcParam("");

    // Act
    DigestAuthentication actualDigestAuthentication = new DigestAuthentication("Method", authorizationHeader,
        "AAAAAAAA".getBytes("UTF-8"), "iloveyou");

    // Assert
    assertNull(actualDigestAuthentication.username);
    assertNull(actualDigestAuthentication.uri);
    assertNull(actualDigestAuthentication.response);
    assertNull(actualDigestAuthentication.realm);
    assertNull(actualDigestAuthentication.qop);
    assertEquals("iloveyou", actualDigestAuthentication.passwd);
    assertNull(actualDigestAuthentication.opaque);
    assertNull(actualDigestAuthentication.nonce);
    assertEquals("", actualDigestAuthentication.nc);
    assertEquals("Method", actualDigestAuthentication.method);
    assertNull(actualDigestAuthentication.cnonce);
    assertEquals(8, actualDigestAuthentication.body.length);
    assertNull(actualDigestAuthentication.algorithm);
  }

  @Test
  void testCheckResponse() {
    // Arrange, Act and Assert
    assertFalse((new DigestAuthentication()).checkResponse());
  }

  @Test
  void testCheckResponse2() throws UnsupportedEncodingException {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addResponseParam("Unquoted response");

    // Act and Assert
    assertFalse((new DigestAuthentication("Method", authorizationHeader, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"),
        "iloveyou")).checkResponse());
  }

  @Test
  void testCheckResponse3() throws UnsupportedEncodingException {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addRealmParam(":");
    authorizationHeader.addResponseParam("Unquoted response");

    // Act and Assert
    assertFalse((new DigestAuthentication("Method", authorizationHeader, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"),
        "iloveyou")).checkResponse());
  }

  @Test
  void testCheckResponse4() throws UnsupportedEncodingException {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addAlgorithParam(":");
    authorizationHeader.addResponseParam("Unquoted response");

    // Act and Assert
    assertFalse((new DigestAuthentication("Method", authorizationHeader, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"),
        "iloveyou")).checkResponse());
  }

  @Test
  void testCheckResponse5() throws UnsupportedEncodingException {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addUriParam(":");
    authorizationHeader.addResponseParam("Unquoted response");

    // Act and Assert
    assertFalse((new DigestAuthentication("Method", authorizationHeader, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"),
        "iloveyou")).checkResponse());
  }

  @Test
  void testCheckResponse6() throws UnsupportedEncodingException {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addQopParam(":");
    authorizationHeader.addResponseParam("Unquoted response");

    // Act and Assert
    assertFalse((new DigestAuthentication("Method", authorizationHeader, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"),
        "iloveyou")).checkResponse());
  }

  @Test
  void testCheckResponse7() throws UnsupportedEncodingException {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addNonceParam(":");
    authorizationHeader.addResponseParam("Unquoted response");

    // Act and Assert
    assertFalse((new DigestAuthentication("Method", authorizationHeader, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"),
        "iloveyou")).checkResponse());
  }

  @Test
  void testCheckResponse8() throws UnsupportedEncodingException {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addUsernameParam("janedoe");
    authorizationHeader.addResponseParam("Unquoted response");

    // Act and Assert
    assertFalse((new DigestAuthentication("Method", authorizationHeader, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"),
        "iloveyou")).checkResponse());
  }

  @Test
  void testCheckResponse9() throws UnsupportedEncodingException {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addResponseParam("dd73b82f0c0f577c2143b7d4ff275f8f");

    // Act and Assert
    assertTrue((new DigestAuthentication("Method", authorizationHeader, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"),
        "iloveyou")).checkResponse());
  }

  @Test
  void testCheckResponse10() throws UnsupportedEncodingException {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addAlgorithParam("MD5-sess");
    authorizationHeader.addResponseParam("Unquoted response");

    // Act and Assert
    assertFalse((new DigestAuthentication("Method", authorizationHeader, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"),
        "iloveyou")).checkResponse());
  }

  @Test
  void testCheckResponse11() throws UnsupportedEncodingException {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addCnonceParam(":");
    authorizationHeader.addQopParam(":");
    authorizationHeader.addResponseParam("Unquoted response");

    // Act and Assert
    assertFalse((new DigestAuthentication("Method", authorizationHeader, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"),
        "iloveyou")).checkResponse());
  }

  @Test
  void testCheckResponse12() throws UnsupportedEncodingException {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addNcParam(":");
    authorizationHeader.addQopParam(":");
    authorizationHeader.addResponseParam("Unquoted response");

    // Act and Assert
    assertFalse((new DigestAuthentication("Method", authorizationHeader, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"),
        "iloveyou")).checkResponse());
  }

  @Test
  void testCheckResponse13() throws UnsupportedEncodingException {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addQopParam("auth-int");
    authorizationHeader.addResponseParam("Unquoted response");

    // Act and Assert
    assertFalse((new DigestAuthentication("Method", authorizationHeader, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"),
        "iloveyou")).checkResponse());
  }

  @Test
  void testGetAuthorizationHeader() throws UnsupportedEncodingException {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addUriParam("Digest");
    authorizationHeader.addNonceParam("Digest");
    authorizationHeader.addUsernameParam("janedoe");
    authorizationHeader.addRealmParam("Digest");

    // Act
    AuthorizationHeader actualAuthorizationHeader = (new DigestAuthentication("Method", authorizationHeader,
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), "iloveyou")).getAuthorizationHeader();

    // Assert
    assertEquals(
        "Digest username=\"janedoe\", realm=\"Digest\", nonce=\"Digest\", uri=\"Digest\", response=\"91384a6f9fc4f1beb"
            + "3552196a00272cb\"",
        actualAuthorizationHeader.getValue());
    assertEquals("Authorization", actualAuthorizationHeader.getName());
  }

  @Test
  void testGetProxyAuthorizationHeader() throws UnsupportedEncodingException {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addUriParam("Digest");
    authorizationHeader.addNonceParam("Digest");
    authorizationHeader.addUsernameParam("janedoe");
    authorizationHeader.addRealmParam("Digest");

    // Act
    ProxyAuthorizationHeader actualProxyAuthorizationHeader = (new DigestAuthentication("Method", authorizationHeader,
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), "iloveyou")).getProxyAuthorizationHeader();

    // Assert
    assertEquals(
        "Digest username=\"janedoe\", realm=\"Digest\", nonce=\"Digest\", uri=\"Digest\", response=\"91384a6f9fc4f1beb"
            + "3552196a00272cb\"",
        actualProxyAuthorizationHeader.getValue());
    assertEquals("Proxy-Authorization", actualProxyAuthorizationHeader.getName());
  }

  @Test
  void testGetResponse() {
    // Arrange, Act and Assert
    assertEquals("dda66cb4f71fbceb12f680e55c08d6a7", (new DigestAuthentication()).getResponse());
  }

  @Test
  void testGetResponse2() throws UnsupportedEncodingException {
    // Arrange
    AuthorizationHeader ah = new AuthorizationHeader("42");

    // Act and Assert
    assertEquals("dd73b82f0c0f577c2143b7d4ff275f8f",
        (new DigestAuthentication("Method", ah, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), "iloveyou"))
            .getResponse());
  }

  @Test
  void testGetResponse3() throws UnsupportedEncodingException {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addRealmParam(":");

    // Act and Assert
    assertEquals("76eabadba7df584f81ba605b89bb32f9", (new DigestAuthentication("Method", authorizationHeader,
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), "iloveyou")).getResponse());
  }

  @Test
  void testGetResponse4() throws UnsupportedEncodingException {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addAlgorithParam(":");

    // Act and Assert
    assertEquals("dd73b82f0c0f577c2143b7d4ff275f8f", (new DigestAuthentication("Method", authorizationHeader,
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), "iloveyou")).getResponse());
  }

  @Test
  void testGetResponse5() throws UnsupportedEncodingException {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addQopParam(":");

    // Act and Assert
    assertEquals("924cef451aa21b316a88f07c395792d2", (new DigestAuthentication("Method", authorizationHeader,
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), "iloveyou")).getResponse());
  }

  @Test
  void testGetResponse6() throws UnsupportedEncodingException {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addNonceParam(":");

    // Act and Assert
    assertEquals("84c256d94a317d614d4fa3ed9c1d29b0", (new DigestAuthentication("Method", authorizationHeader,
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), "iloveyou")).getResponse());
  }

  @Test
  void testGetResponse7() throws UnsupportedEncodingException {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addAlgorithParam("MD5-sess");

    // Act and Assert
    assertEquals("0c8e6ad2cbfcaae2a8cf05c810d75170", (new DigestAuthentication("Method", authorizationHeader,
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), "iloveyou")).getResponse());
  }

  @Test
  void testGetResponse8() throws UnsupportedEncodingException {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addQopParam("auth-int");

    // Act and Assert
    assertEquals("685eae17ababf6325330d1169e512077", (new DigestAuthentication("Method", authorizationHeader,
        "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), "iloveyou")).getResponse());
  }
}

