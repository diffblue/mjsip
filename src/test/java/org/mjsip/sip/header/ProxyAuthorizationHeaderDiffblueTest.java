package org.mjsip.sip.header;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Vector;
import org.junit.jupiter.api.Test;

class ProxyAuthorizationHeaderDiffblueTest {
  @Test
  void testAddAlgorithParam() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");

    // Act
    proxyAuthorizationHeader.addAlgorithParam("Algorithm");

    // Assert
    assertEquals("42 algorithm=Algorithm", proxyAuthorizationHeader.getValue());
  }

  @Test
  void testAddAlgorithParam2() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addAutsParam("algorithm");

    // Act
    proxyAuthorizationHeader.addAlgorithParam("Algorithm");

    // Assert
    assertEquals("42 auts=\"algorithm\", algorithm=Algorithm", proxyAuthorizationHeader.getValue());
  }

  @Test
  void testAddAutsParam() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");

    // Act
    proxyAuthorizationHeader.addAutsParam("Unquoted auts");

    // Assert
    assertEquals("42 auts=\"Unquoted auts\"", proxyAuthorizationHeader.getValue());
  }

  @Test
  void testAddAutsParam2() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addAutsParam("auts");

    // Act
    proxyAuthorizationHeader.addAutsParam("Unquoted auts");

    // Assert
    assertEquals("42 auts=\"auts\", auts=\"Unquoted auts\"", proxyAuthorizationHeader.getValue());
  }

  @Test
  void testAddCnonceParam() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");

    // Act
    proxyAuthorizationHeader.addCnonceParam("Unquoted cnonce");

    // Assert
    assertEquals("42 cnonce=\"Unquoted cnonce\"", proxyAuthorizationHeader.getValue());
  }

  @Test
  void testAddCnonceParam2() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addAutsParam("cnonce");

    // Act
    proxyAuthorizationHeader.addCnonceParam("Unquoted cnonce");

    // Assert
    assertEquals("42 auts=\"cnonce\", cnonce=\"Unquoted cnonce\"", proxyAuthorizationHeader.getValue());
  }

  @Test
  void testAddNcParam() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");

    // Act
    proxyAuthorizationHeader.addNcParam("Nc");

    // Assert
    assertEquals("42 nc=Nc", proxyAuthorizationHeader.getValue());
  }

  @Test
  void testAddNcParam2() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addAutsParam("nc");

    // Act
    proxyAuthorizationHeader.addNcParam("Nc");

    // Assert
    assertEquals("42 auts=\"nc\", nc=Nc", proxyAuthorizationHeader.getValue());
  }

  @Test
  void testAddNextnonceParam() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");

    // Act
    proxyAuthorizationHeader.addNextnonceParam("Unquoted nextnonce");

    // Assert
    assertEquals("42 nextnonce=\"Unquoted nextnonce\"", proxyAuthorizationHeader.getValue());
  }

  @Test
  void testAddNextnonceParam2() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addAutsParam("nextnonce");

    // Act
    proxyAuthorizationHeader.addNextnonceParam("Unquoted nextnonce");

    // Assert
    assertEquals("42 auts=\"nextnonce\", nextnonce=\"Unquoted nextnonce\"", proxyAuthorizationHeader.getValue());
  }

  @Test
  void testAddNonceParam() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");

    // Act
    proxyAuthorizationHeader.addNonceParam("Unquoted nonce");

    // Assert
    assertEquals("42 nonce=\"Unquoted nonce\"", proxyAuthorizationHeader.getValue());
  }

  @Test
  void testAddNonceParam2() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addAutsParam("nonce");

    // Act
    proxyAuthorizationHeader.addNonceParam("Unquoted nonce");

    // Assert
    assertEquals("42 auts=\"nonce\", nonce=\"Unquoted nonce\"", proxyAuthorizationHeader.getValue());
  }

  @Test
  void testAddOpaqueParam() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");

    // Act
    proxyAuthorizationHeader.addOpaqueParam("Unquoted opaque");

    // Assert
    assertEquals("42 opaque=\"Unquoted opaque\"", proxyAuthorizationHeader.getValue());
  }

  @Test
  void testAddOpaqueParam2() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addAutsParam("opaque");

    // Act
    proxyAuthorizationHeader.addOpaqueParam("Unquoted opaque");

    // Assert
    assertEquals("42 auts=\"opaque\", opaque=\"Unquoted opaque\"", proxyAuthorizationHeader.getValue());
  }

  @Test
  void testAddParameter() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");

    // Act
    proxyAuthorizationHeader.addParameter("Param name", "42");

    // Assert
    assertEquals("42 Param name=42", proxyAuthorizationHeader.getValue());
  }

  @Test
  void testAddParameter2() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addAutsParam("Unquoted auts");

    // Act
    proxyAuthorizationHeader.addParameter("Param name", "42");

    // Assert
    assertEquals("42 auts=\"Unquoted auts\", Param name=42", proxyAuthorizationHeader.getValue());
  }

  @Test
  void testAddParameter3() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");

    // Act
    proxyAuthorizationHeader.addParameter("auts", "42");

    // Assert
    assertEquals("42 auts=\"42\"", proxyAuthorizationHeader.getValue());
  }

  @Test
  void testAddParameter4() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addAutsParam("Unquoted auts");

    // Act
    proxyAuthorizationHeader.addParameter("auts", "42");

    // Assert
    assertEquals("42 auts=\"Unquoted auts\", auts=\"42\"", proxyAuthorizationHeader.getValue());
  }

  @Test
  void testAddQopOptionsParam() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");

    // Act
    proxyAuthorizationHeader.addQopOptionsParam("Unquoted qop options");

    // Assert
    assertEquals("42 qop=\"Unquoted qop options\"", proxyAuthorizationHeader.getValue());
  }

  @Test
  void testAddQopOptionsParam2() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addAutsParam("qop");

    // Act
    proxyAuthorizationHeader.addQopOptionsParam("Unquoted qop options");

    // Assert
    assertEquals("42 auts=\"qop\", qop=\"Unquoted qop options\"", proxyAuthorizationHeader.getValue());
  }

  @Test
  void testAddQopParam() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");

    // Act
    proxyAuthorizationHeader.addQopParam("Qop");

    // Assert
    assertEquals("42 qop=Qop", proxyAuthorizationHeader.getValue());
  }

  @Test
  void testAddQopParam2() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addAutsParam("qop");

    // Act
    proxyAuthorizationHeader.addQopParam("Qop");

    // Assert
    assertEquals("42 auts=\"qop\", qop=Qop", proxyAuthorizationHeader.getValue());
  }

  @Test
  void testAddQuotedParameter() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");

    // Act
    proxyAuthorizationHeader.addQuotedParameter("Param name", "42");

    // Assert
    assertEquals("42 Param name=\"42\"", proxyAuthorizationHeader.getValue());
  }

  @Test
  void testAddQuotedParameter2() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addAutsParam("Unquoted auts");

    // Act
    proxyAuthorizationHeader.addQuotedParameter("Param name", "42");

    // Assert
    assertEquals("42 auts=\"Unquoted auts\", Param name=\"42\"", proxyAuthorizationHeader.getValue());
  }

  @Test
  void testAddRealmParam() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");

    // Act
    proxyAuthorizationHeader.addRealmParam("Unquoted realm");

    // Assert
    assertEquals("42 realm=\"Unquoted realm\"", proxyAuthorizationHeader.getValue());
  }

  @Test
  void testAddRealmParam2() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addAutsParam("realm");

    // Act
    proxyAuthorizationHeader.addRealmParam("Unquoted realm");

    // Assert
    assertEquals("42 auts=\"realm\", realm=\"Unquoted realm\"", proxyAuthorizationHeader.getValue());
  }

  @Test
  void testAddResponseParam() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");

    // Act
    proxyAuthorizationHeader.addResponseParam("Unquoted response");

    // Assert
    assertEquals("42 response=\"Unquoted response\"", proxyAuthorizationHeader.getValue());
  }

  @Test
  void testAddResponseParam2() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addAutsParam("response");

    // Act
    proxyAuthorizationHeader.addResponseParam("Unquoted response");

    // Assert
    assertEquals("42 auts=\"response\", response=\"Unquoted response\"", proxyAuthorizationHeader.getValue());
  }

  @Test
  void testAddRspauthParam() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");

    // Act
    proxyAuthorizationHeader.addRspauthParam("Unquoted rspauth");

    // Assert
    assertEquals("42 rspauth=\"Unquoted rspauth\"", proxyAuthorizationHeader.getValue());
  }

  @Test
  void testAddRspauthParam2() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addAutsParam("rspauth");

    // Act
    proxyAuthorizationHeader.addRspauthParam("Unquoted rspauth");

    // Assert
    assertEquals("42 auts=\"rspauth\", rspauth=\"Unquoted rspauth\"", proxyAuthorizationHeader.getValue());
  }

  @Test
  void testAddUnquotedParameter() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");

    // Act
    proxyAuthorizationHeader.addUnquotedParameter("Param name", "42");

    // Assert
    assertEquals("42 Param name=42", proxyAuthorizationHeader.getValue());
  }

  @Test
  void testAddUnquotedParameter2() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addAutsParam("Unquoted auts");

    // Act
    proxyAuthorizationHeader.addUnquotedParameter("Param name", "42");

    // Assert
    assertEquals("42 auts=\"Unquoted auts\", Param name=42", proxyAuthorizationHeader.getValue());
  }

  @Test
  void testAddUriParam() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");

    // Act
    proxyAuthorizationHeader.addUriParam("Unquoted uri");

    // Assert
    assertEquals("42 uri=\"Unquoted uri\"", proxyAuthorizationHeader.getValue());
  }

  @Test
  void testAddUriParam2() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addAutsParam("uri");

    // Act
    proxyAuthorizationHeader.addUriParam("Unquoted uri");

    // Assert
    assertEquals("42 auts=\"uri\", uri=\"Unquoted uri\"", proxyAuthorizationHeader.getValue());
  }

  @Test
  void testAddUsernameParam() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");

    // Act
    proxyAuthorizationHeader.addUsernameParam("janedoe");

    // Assert
    assertEquals("42 username=\"janedoe\"", proxyAuthorizationHeader.getValue());
  }

  @Test
  void testAddUsernameParam2() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addAutsParam("username");

    // Act
    proxyAuthorizationHeader.addUsernameParam("janedoe");

    // Assert
    assertEquals("42 auts=\"username\", username=\"janedoe\"", proxyAuthorizationHeader.getValue());
  }

  @Test
  void testConstructor() {
    // Arrange and Act
    ProxyAuthorizationHeader actualProxyAuthorizationHeader = new ProxyAuthorizationHeader("42");

    // Assert
    assertNull(actualProxyAuthorizationHeader.getAlgorithParam());
    String[] stringArray = AuthenticationHeader.QUOTED_PARAMETERS;
    assertSame(actualProxyAuthorizationHeader.QUOTED_PARAMETERS, stringArray);
    assertEquals(10, stringArray.length);
    assertEquals(" ", AuthenticationHeader.LWS_SEPARATOR);
    assertEquals("Proxy-Authorization: 42\r\n", actualProxyAuthorizationHeader.toString());
    assertFalse(actualProxyAuthorizationHeader.hasUsernameParam());
    assertFalse(actualProxyAuthorizationHeader.hasUriParam());
    assertFalse(actualProxyAuthorizationHeader.hasRspauthParam());
    assertFalse(actualProxyAuthorizationHeader.hasResponseParam());
    assertFalse(actualProxyAuthorizationHeader.hasRealmParam());
    assertFalse(actualProxyAuthorizationHeader.hasQopParam());
    assertFalse(actualProxyAuthorizationHeader.hasQopOptionsParam());
    assertFalse(actualProxyAuthorizationHeader.hasOpaqueParam());
    assertFalse(actualProxyAuthorizationHeader.hasNonceParam());
    assertFalse(actualProxyAuthorizationHeader.hasNextnonceParam());
    assertFalse(actualProxyAuthorizationHeader.hasNcParam());
    assertFalse(actualProxyAuthorizationHeader.hasCnonceParam());
    assertFalse(actualProxyAuthorizationHeader.hasAutsParam());
    assertFalse(actualProxyAuthorizationHeader.hasAlgorithmParam());
    assertEquals("42", actualProxyAuthorizationHeader.getValue());
    assertNull(actualProxyAuthorizationHeader.getUsernameParam());
    assertNull(actualProxyAuthorizationHeader.getUriParam());
    assertNull(actualProxyAuthorizationHeader.getRspauthParam());
    assertNull(actualProxyAuthorizationHeader.getResponseParam());
    assertNull(actualProxyAuthorizationHeader.getRealmParam());
    assertNull(actualProxyAuthorizationHeader.getQopParam());
    assertNull(actualProxyAuthorizationHeader.getQopOptionsParam());
    assertTrue(actualProxyAuthorizationHeader.getParameterNames().isEmpty());
    assertNull(actualProxyAuthorizationHeader.getOpaqueParam());
    assertNull(actualProxyAuthorizationHeader.getNonceParam());
    assertNull(actualProxyAuthorizationHeader.getNextnonceParam());
    assertNull(actualProxyAuthorizationHeader.getNcParam());
    assertEquals(CoreSipHeaders.Proxy_Authorization, actualProxyAuthorizationHeader.getName());
    assertNull(actualProxyAuthorizationHeader.getCnonceParam());
    assertNull(actualProxyAuthorizationHeader.getAutsParam());
    assertEquals("42", actualProxyAuthorizationHeader.getAuthScheme());
  }

  @Test
  void testConstructor2() {
    // Arrange
    Vector vector = new Vector(1);

    // Act
    ProxyAuthorizationHeader actualProxyAuthorizationHeader = new ProxyAuthorizationHeader("Auth scheme", vector);

    // Assert
    assertNull(actualProxyAuthorizationHeader.getAlgorithParam());
    String[] stringArray = AuthenticationHeader.QUOTED_PARAMETERS;
    assertSame(actualProxyAuthorizationHeader.QUOTED_PARAMETERS, stringArray);
    assertEquals(10, stringArray.length);
    assertEquals(" ", AuthenticationHeader.LWS_SEPARATOR);
    assertEquals("Proxy-Authorization: Auth scheme\r\n", actualProxyAuthorizationHeader.toString());
    assertFalse(actualProxyAuthorizationHeader.hasUsernameParam());
    assertFalse(actualProxyAuthorizationHeader.hasUriParam());
    assertFalse(actualProxyAuthorizationHeader.hasRspauthParam());
    assertFalse(actualProxyAuthorizationHeader.hasResponseParam());
    assertFalse(actualProxyAuthorizationHeader.hasRealmParam());
    assertFalse(actualProxyAuthorizationHeader.hasQopParam());
    assertFalse(actualProxyAuthorizationHeader.hasQopOptionsParam());
    assertFalse(actualProxyAuthorizationHeader.hasOpaqueParam());
    assertFalse(actualProxyAuthorizationHeader.hasNonceParam());
    assertFalse(actualProxyAuthorizationHeader.hasNextnonceParam());
    assertFalse(actualProxyAuthorizationHeader.hasNcParam());
    assertFalse(actualProxyAuthorizationHeader.hasCnonceParam());
    assertFalse(actualProxyAuthorizationHeader.hasAutsParam());
    assertFalse(actualProxyAuthorizationHeader.hasAlgorithmParam());
    assertEquals("Auth scheme", actualProxyAuthorizationHeader.getValue());
    assertNull(actualProxyAuthorizationHeader.getUsernameParam());
    assertNull(actualProxyAuthorizationHeader.getUriParam());
    assertNull(actualProxyAuthorizationHeader.getRspauthParam());
    assertNull(actualProxyAuthorizationHeader.getResponseParam());
    assertNull(actualProxyAuthorizationHeader.getRealmParam());
    assertNull(actualProxyAuthorizationHeader.getQopParam());
    assertNull(actualProxyAuthorizationHeader.getQopOptionsParam());
    Vector parameterNames = actualProxyAuthorizationHeader.getParameterNames();
    assertEquals(1, parameterNames.size());
    assertEquals("scheme", parameterNames.get(0));
    assertNull(actualProxyAuthorizationHeader.getOpaqueParam());
    assertNull(actualProxyAuthorizationHeader.getNonceParam());
    assertNull(actualProxyAuthorizationHeader.getNextnonceParam());
    assertNull(actualProxyAuthorizationHeader.getNcParam());
    assertEquals(CoreSipHeaders.Proxy_Authorization, actualProxyAuthorizationHeader.getName());
    assertNull(actualProxyAuthorizationHeader.getCnonceParam());
    assertNull(actualProxyAuthorizationHeader.getAutsParam());
    assertEquals("Auth", actualProxyAuthorizationHeader.getAuthScheme());
    assertTrue(vector.isEmpty());
  }

  @Test
  void testConstructor3() {
    // Arrange
    Header header = new Header("Hname", "42");

    // Act
    ProxyAuthorizationHeader actualProxyAuthorizationHeader = new ProxyAuthorizationHeader(header);

    // Assert
    assertNull(actualProxyAuthorizationHeader.getAlgorithParam());
    String[] stringArray = AuthenticationHeader.QUOTED_PARAMETERS;
    assertSame(actualProxyAuthorizationHeader.QUOTED_PARAMETERS, stringArray);
    assertEquals(10, stringArray.length);
    assertEquals(" ", AuthenticationHeader.LWS_SEPARATOR);
    assertEquals("Hname: 42\r\n", actualProxyAuthorizationHeader.toString());
    assertFalse(actualProxyAuthorizationHeader.hasUsernameParam());
    assertFalse(actualProxyAuthorizationHeader.hasUriParam());
    assertFalse(actualProxyAuthorizationHeader.hasRspauthParam());
    assertFalse(actualProxyAuthorizationHeader.hasResponseParam());
    assertFalse(actualProxyAuthorizationHeader.hasRealmParam());
    assertFalse(actualProxyAuthorizationHeader.hasQopParam());
    assertFalse(actualProxyAuthorizationHeader.hasQopOptionsParam());
    assertFalse(actualProxyAuthorizationHeader.hasOpaqueParam());
    assertFalse(actualProxyAuthorizationHeader.hasNonceParam());
    assertFalse(actualProxyAuthorizationHeader.hasNextnonceParam());
    assertFalse(actualProxyAuthorizationHeader.hasNcParam());
    assertFalse(actualProxyAuthorizationHeader.hasCnonceParam());
    assertFalse(actualProxyAuthorizationHeader.hasAutsParam());
    assertFalse(actualProxyAuthorizationHeader.hasAlgorithmParam());
    assertEquals("42", actualProxyAuthorizationHeader.getValue());
    assertNull(actualProxyAuthorizationHeader.getUsernameParam());
    assertNull(actualProxyAuthorizationHeader.getUriParam());
    assertNull(actualProxyAuthorizationHeader.getRspauthParam());
    assertNull(actualProxyAuthorizationHeader.getResponseParam());
    assertNull(actualProxyAuthorizationHeader.getRealmParam());
    assertNull(actualProxyAuthorizationHeader.getQopParam());
    assertNull(actualProxyAuthorizationHeader.getQopOptionsParam());
    assertTrue(actualProxyAuthorizationHeader.getParameterNames().isEmpty());
    assertNull(actualProxyAuthorizationHeader.getOpaqueParam());
    assertNull(actualProxyAuthorizationHeader.getNonceParam());
    assertNull(actualProxyAuthorizationHeader.getNextnonceParam());
    assertNull(actualProxyAuthorizationHeader.getNcParam());
    assertEquals("Hname", actualProxyAuthorizationHeader.getName());
    assertNull(actualProxyAuthorizationHeader.getCnonceParam());
    assertNull(actualProxyAuthorizationHeader.getAutsParam());
    assertEquals("42", actualProxyAuthorizationHeader.getAuthScheme());
    assertEquals("Hname", header.getName());
    assertEquals("Hname: 42\r\n", header.toString());
    assertEquals("42", header.getValue());
  }

  @Test
  void testGetAlgorithParam() {
    // Arrange, Act and Assert
    assertNull((new ProxyAuthorizationHeader("42")).getAlgorithParam());
    assertNull((new ProxyAuthorizationHeader("")).getAlgorithParam());
    assertNull((new ProxyAuthorizationHeader("Auth scheme", new Vector(1))).getAlgorithParam());
  }

  @Test
  void testGetAlgorithParam2() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addAutsParam("algorithm");

    // Act and Assert
    assertNull(proxyAuthorizationHeader.getAlgorithParam());
  }

  @Test
  void testGetAlgorithParam3() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addParameter("algorithm", "42");

    // Act and Assert
    assertEquals("42", proxyAuthorizationHeader.getAlgorithParam());
  }

  @Test
  void testGetAlgorithParam4() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addQuotedParameter("algorithm", "42");

    // Act and Assert
    assertEquals("42", proxyAuthorizationHeader.getAlgorithParam());
  }

  @Test
  void testGetAlgorithParam5() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addAutsParam("algorithm");
    proxyAuthorizationHeader.addAutsParam("algorithm");

    // Act and Assert
    assertNull(proxyAuthorizationHeader.getAlgorithParam());
  }

  @Test
  void testGetAlgorithParam6() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addParameter("algorithm", "42");
    proxyAuthorizationHeader.addAutsParam("algorithm");

    // Act and Assert
    assertEquals("42", proxyAuthorizationHeader.getAlgorithParam());
  }

  @Test
  void testGetAlgorithParam7() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addParameter("", "42");

    // Act and Assert
    assertNull(proxyAuthorizationHeader.getAlgorithParam());
  }

  @Test
  void testGetAlgorithParam8() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addParameter("algorithm", "");

    // Act and Assert
    assertEquals("", proxyAuthorizationHeader.getAlgorithParam());
  }

  @Test
  void testGetAuthScheme() {
    // Arrange, Act and Assert
    assertEquals("42", (new ProxyAuthorizationHeader("42")).getAuthScheme());
    assertEquals("", (new ProxyAuthorizationHeader("")).getAuthScheme());
    assertEquals("Auth", (new ProxyAuthorizationHeader("Auth scheme", new Vector(1))).getAuthScheme());
  }

  @Test
  void testGetAuthScheme2() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("");
    proxyAuthorizationHeader.addAutsParam("Unquoted auts");

    // Act and Assert
    assertEquals("auts=\"Unquoted", proxyAuthorizationHeader.getAuthScheme());
  }

  @Test
  void testGetAutsParam() {
    // Arrange, Act and Assert
    assertNull((new ProxyAuthorizationHeader("42")).getAutsParam());
    assertNull((new ProxyAuthorizationHeader("")).getAutsParam());
    assertNull((new ProxyAuthorizationHeader("Auth scheme", new Vector(1))).getAutsParam());
  }

  @Test
  void testGetAutsParam2() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addAutsParam("auts");

    // Act and Assert
    assertEquals("auts", proxyAuthorizationHeader.getAutsParam());
  }

  @Test
  void testGetAutsParam3() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addUnquotedParameter("auts", "42");

    // Act and Assert
    assertEquals("42", proxyAuthorizationHeader.getAutsParam());
  }

  @Test
  void testGetAutsParam4() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addAutsParam("auts");
    proxyAuthorizationHeader.addAutsParam("auts");

    // Act and Assert
    assertEquals("auts", proxyAuthorizationHeader.getAutsParam());
  }

  @Test
  void testGetAutsParam5() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addCnonceParam("auts");
    proxyAuthorizationHeader.addAutsParam("auts");

    // Act and Assert
    assertEquals("auts", proxyAuthorizationHeader.getAutsParam());
  }

  @Test
  void testGetAutsParam6() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addParameter("", "42");

    // Act and Assert
    assertNull(proxyAuthorizationHeader.getAutsParam());
  }

  @Test
  void testGetAutsParam7() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addUnquotedParameter("auts", "");

    // Act and Assert
    assertEquals("", proxyAuthorizationHeader.getAutsParam());
  }

  @Test
  void testGetCnonceParam() {
    // Arrange, Act and Assert
    assertNull((new ProxyAuthorizationHeader("42")).getCnonceParam());
    assertNull((new ProxyAuthorizationHeader("")).getCnonceParam());
    assertNull((new ProxyAuthorizationHeader("Auth scheme", new Vector(1))).getCnonceParam());
  }

  @Test
  void testGetCnonceParam2() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addAutsParam("cnonce");

    // Act and Assert
    assertNull(proxyAuthorizationHeader.getCnonceParam());
  }

  @Test
  void testGetCnonceParam3() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addCnonceParam("cnonce");

    // Act and Assert
    assertEquals("cnonce", proxyAuthorizationHeader.getCnonceParam());
  }

  @Test
  void testGetCnonceParam4() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addUnquotedParameter("cnonce", "42");

    // Act and Assert
    assertEquals("42", proxyAuthorizationHeader.getCnonceParam());
  }

  @Test
  void testGetCnonceParam5() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addAutsParam("cnonce");
    proxyAuthorizationHeader.addAutsParam("cnonce");

    // Act and Assert
    assertNull(proxyAuthorizationHeader.getCnonceParam());
  }

  @Test
  void testGetCnonceParam6() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addCnonceParam("cnonce");
    proxyAuthorizationHeader.addAutsParam("cnonce");

    // Act and Assert
    assertEquals("cnonce", proxyAuthorizationHeader.getCnonceParam());
  }

  @Test
  void testGetCnonceParam7() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addParameter("", "42");

    // Act and Assert
    assertNull(proxyAuthorizationHeader.getCnonceParam());
  }

  @Test
  void testGetCnonceParam8() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addUnquotedParameter("cnonce", "");

    // Act and Assert
    assertEquals("", proxyAuthorizationHeader.getCnonceParam());
  }

  @Test
  void testGetNcParam() {
    // Arrange, Act and Assert
    assertNull((new ProxyAuthorizationHeader("42")).getNcParam());
    assertNull((new ProxyAuthorizationHeader("")).getNcParam());
    assertNull((new ProxyAuthorizationHeader("Auth scheme", new Vector(1))).getNcParam());
  }

  @Test
  void testGetNcParam2() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addAutsParam("nc");

    // Act and Assert
    assertNull(proxyAuthorizationHeader.getNcParam());
  }

  @Test
  void testGetNcParam3() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addParameter("nc", "42");

    // Act and Assert
    assertEquals("42", proxyAuthorizationHeader.getNcParam());
  }

  @Test
  void testGetNcParam4() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addQuotedParameter("nc", "42");

    // Act and Assert
    assertEquals("42", proxyAuthorizationHeader.getNcParam());
  }

  @Test
  void testGetNcParam5() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addAutsParam("nc");
    proxyAuthorizationHeader.addAutsParam("nc");

    // Act and Assert
    assertNull(proxyAuthorizationHeader.getNcParam());
  }

  @Test
  void testGetNcParam6() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addParameter("nc", "42");
    proxyAuthorizationHeader.addAutsParam("nc");

    // Act and Assert
    assertEquals("42", proxyAuthorizationHeader.getNcParam());
  }

  @Test
  void testGetNcParam7() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addParameter("", "42");

    // Act and Assert
    assertNull(proxyAuthorizationHeader.getNcParam());
  }

  @Test
  void testGetNcParam8() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addParameter("nc", "");

    // Act and Assert
    assertEquals("", proxyAuthorizationHeader.getNcParam());
  }

  @Test
  void testGetNextnonceParam() {
    // Arrange, Act and Assert
    assertNull((new ProxyAuthorizationHeader("42")).getNextnonceParam());
    assertNull((new ProxyAuthorizationHeader("")).getNextnonceParam());
    assertNull((new ProxyAuthorizationHeader("Auth scheme", new Vector(1))).getNextnonceParam());
  }

  @Test
  void testGetNextnonceParam2() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addAutsParam("nextnonce");

    // Act and Assert
    assertNull(proxyAuthorizationHeader.getNextnonceParam());
  }

  @Test
  void testGetNextnonceParam3() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addParameter("nextnonce", "42");

    // Act and Assert
    assertEquals("42", proxyAuthorizationHeader.getNextnonceParam());
  }

  @Test
  void testGetNextnonceParam4() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addUnquotedParameter("nextnonce", "42");

    // Act and Assert
    assertEquals("42", proxyAuthorizationHeader.getNextnonceParam());
  }

  @Test
  void testGetNextnonceParam5() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addAutsParam("nextnonce");
    proxyAuthorizationHeader.addAutsParam("nextnonce");

    // Act and Assert
    assertNull(proxyAuthorizationHeader.getNextnonceParam());
  }

  @Test
  void testGetNextnonceParam6() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addParameter("nextnonce", "42");
    proxyAuthorizationHeader.addAutsParam("nextnonce");

    // Act and Assert
    assertEquals("42", proxyAuthorizationHeader.getNextnonceParam());
  }

  @Test
  void testGetNextnonceParam7() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addParameter("", "42");

    // Act and Assert
    assertNull(proxyAuthorizationHeader.getNextnonceParam());
  }

  @Test
  void testGetNextnonceParam8() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addUnquotedParameter("nextnonce", "");

    // Act and Assert
    assertEquals("", proxyAuthorizationHeader.getNextnonceParam());
  }

  @Test
  void testGetNonceParam() {
    // Arrange, Act and Assert
    assertNull((new ProxyAuthorizationHeader("42")).getNonceParam());
    assertNull((new ProxyAuthorizationHeader("")).getNonceParam());
    assertNull((new ProxyAuthorizationHeader("Auth scheme", new Vector(1))).getNonceParam());
  }

  @Test
  void testGetNonceParam2() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addAutsParam("nonce");

    // Act and Assert
    assertNull(proxyAuthorizationHeader.getNonceParam());
  }

  @Test
  void testGetNonceParam3() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addParameter("nonce", "42");

    // Act and Assert
    assertEquals("42", proxyAuthorizationHeader.getNonceParam());
  }

  @Test
  void testGetNonceParam4() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addUnquotedParameter("nonce", "42");

    // Act and Assert
    assertEquals("42", proxyAuthorizationHeader.getNonceParam());
  }

  @Test
  void testGetNonceParam5() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addAutsParam("nonce");
    proxyAuthorizationHeader.addAutsParam("nonce");

    // Act and Assert
    assertNull(proxyAuthorizationHeader.getNonceParam());
  }

  @Test
  void testGetNonceParam6() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addParameter("nonce", "42");
    proxyAuthorizationHeader.addAutsParam("nonce");

    // Act and Assert
    assertEquals("42", proxyAuthorizationHeader.getNonceParam());
  }

  @Test
  void testGetNonceParam7() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addParameter("", "42");

    // Act and Assert
    assertNull(proxyAuthorizationHeader.getNonceParam());
  }

  @Test
  void testGetNonceParam8() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addUnquotedParameter("nonce", "");

    // Act and Assert
    assertEquals("", proxyAuthorizationHeader.getNonceParam());
  }

  @Test
  void testGetOpaqueParam() {
    // Arrange, Act and Assert
    assertNull((new ProxyAuthorizationHeader("42")).getOpaqueParam());
    assertNull((new ProxyAuthorizationHeader("")).getOpaqueParam());
    assertNull((new ProxyAuthorizationHeader("Auth scheme", new Vector(1))).getOpaqueParam());
  }

  @Test
  void testGetOpaqueParam2() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addAutsParam("opaque");

    // Act and Assert
    assertNull(proxyAuthorizationHeader.getOpaqueParam());
  }

  @Test
  void testGetOpaqueParam3() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addParameter("opaque", "42");

    // Act and Assert
    assertEquals("42", proxyAuthorizationHeader.getOpaqueParam());
  }

  @Test
  void testGetOpaqueParam4() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addUnquotedParameter("opaque", "42");

    // Act and Assert
    assertEquals("42", proxyAuthorizationHeader.getOpaqueParam());
  }

  @Test
  void testGetOpaqueParam5() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addAutsParam("opaque");
    proxyAuthorizationHeader.addAutsParam("opaque");

    // Act and Assert
    assertNull(proxyAuthorizationHeader.getOpaqueParam());
  }

  @Test
  void testGetOpaqueParam6() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addParameter("opaque", "42");
    proxyAuthorizationHeader.addAutsParam("opaque");

    // Act and Assert
    assertEquals("42", proxyAuthorizationHeader.getOpaqueParam());
  }

  @Test
  void testGetOpaqueParam7() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addParameter("", "42");

    // Act and Assert
    assertNull(proxyAuthorizationHeader.getOpaqueParam());
  }

  @Test
  void testGetOpaqueParam8() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addUnquotedParameter("opaque", "");

    // Act and Assert
    assertEquals("", proxyAuthorizationHeader.getOpaqueParam());
  }

  @Test
  void testGetParameter() {
    // Arrange, Act and Assert
    assertNull((new ProxyAuthorizationHeader("42")).getParameter("Param name"));
    assertNull((new ProxyAuthorizationHeader("")).getParameter("Param name"));
    assertNull((new ProxyAuthorizationHeader("Auth scheme", new Vector(1))).getParameter("Param name"));
  }

  @Test
  void testGetParameter2() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addAutsParam("Unquoted auts");

    // Act and Assert
    assertNull(proxyAuthorizationHeader.getParameter("Param name"));
  }

  @Test
  void testGetParameter3() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addAutsParam("auts");
    proxyAuthorizationHeader.addAutsParam("Unquoted auts");

    // Act and Assert
    assertNull(proxyAuthorizationHeader.getParameter("Param name"));
  }

  @Test
  void testGetParameter4() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addParameter("", "42");

    // Act and Assert
    assertNull(proxyAuthorizationHeader.getParameter("Param name"));
  }

  @Test
  void testGetParameter5() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("");
    proxyAuthorizationHeader.addAutsParam("Unquoted auts");

    // Act and Assert
    assertNull(proxyAuthorizationHeader.getParameter("Param name"));
  }

  @Test
  void testGetParameterNames() {
    // Arrange, Act and Assert
    assertTrue((new ProxyAuthorizationHeader("42")).getParameterNames().isEmpty());
    assertTrue((new ProxyAuthorizationHeader("")).getParameterNames().isEmpty());
  }

  @Test
  void testGetParameterNames2() {
    // Arrange and Act
    Vector actualParameterNames = (new ProxyAuthorizationHeader("Auth scheme", new Vector(1))).getParameterNames();

    // Assert
    assertEquals(1, actualParameterNames.size());
    assertEquals("scheme", actualParameterNames.get(0));
  }

  @Test
  void testGetParameterNames3() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addAutsParam("Unquoted auts");

    // Act
    Vector actualParameterNames = proxyAuthorizationHeader.getParameterNames();

    // Assert
    assertEquals(1, actualParameterNames.size());
    assertEquals("auts", actualParameterNames.get(0));
  }

  @Test
  void testGetParameterNames4() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addAutsParam("Unquoted auts");
    proxyAuthorizationHeader.addAutsParam("Unquoted auts");

    // Act
    Vector actualParameterNames = proxyAuthorizationHeader.getParameterNames();

    // Assert
    assertEquals(2, actualParameterNames.size());
    assertEquals("auts", actualParameterNames.get(0));
    assertEquals("auts", actualParameterNames.get(1));
  }

  @Test
  void testGetParameterNames5() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addParameter("", "42");

    // Act
    Vector actualParameterNames = proxyAuthorizationHeader.getParameterNames();

    // Assert
    assertEquals(1, actualParameterNames.size());
    assertEquals("42", actualParameterNames.get(0));
  }

  @Test
  void testGetParameterNames6() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("");
    proxyAuthorizationHeader.addAutsParam("Unquoted auts");

    // Act
    Vector actualParameterNames = proxyAuthorizationHeader.getParameterNames();

    // Assert
    assertEquals(1, actualParameterNames.size());
    assertEquals("auts\"", actualParameterNames.get(0));
  }

  @Test
  void testGetQopOptionsParam() {
    // Arrange, Act and Assert
    assertNull((new ProxyAuthorizationHeader("42")).getQopOptionsParam());
    assertNull((new ProxyAuthorizationHeader("")).getQopOptionsParam());
    assertNull((new ProxyAuthorizationHeader("Auth scheme", new Vector(1))).getQopOptionsParam());
  }

  @Test
  void testGetQopOptionsParam2() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addAutsParam("qop");

    // Act and Assert
    assertNull(proxyAuthorizationHeader.getQopOptionsParam());
  }

  @Test
  void testGetQopOptionsParam3() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addParameter("qop", "42");

    // Act and Assert
    assertEquals("42", proxyAuthorizationHeader.getQopOptionsParam());
  }

  @Test
  void testGetQopOptionsParam4() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addQuotedParameter("qop", "42");

    // Act and Assert
    assertEquals("42", proxyAuthorizationHeader.getQopOptionsParam());
  }

  @Test
  void testGetQopOptionsParam5() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addAutsParam("qop");
    proxyAuthorizationHeader.addAutsParam("qop");

    // Act and Assert
    assertNull(proxyAuthorizationHeader.getQopOptionsParam());
  }

  @Test
  void testGetQopOptionsParam6() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addParameter("qop", "42");
    proxyAuthorizationHeader.addAutsParam("qop");

    // Act and Assert
    assertEquals("42", proxyAuthorizationHeader.getQopOptionsParam());
  }

  @Test
  void testGetQopOptionsParam7() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addParameter("", "42");

    // Act and Assert
    assertNull(proxyAuthorizationHeader.getQopOptionsParam());
  }

  @Test
  void testGetQopOptionsParam8() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addParameter("qop", "");

    // Act and Assert
    assertEquals("", proxyAuthorizationHeader.getQopOptionsParam());
  }

  @Test
  void testGetQopParam() {
    // Arrange, Act and Assert
    assertNull((new ProxyAuthorizationHeader("42")).getQopParam());
    assertNull((new ProxyAuthorizationHeader("")).getQopParam());
    assertNull((new ProxyAuthorizationHeader("Auth scheme", new Vector(1))).getQopParam());
  }

  @Test
  void testGetQopParam2() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addAutsParam("qop");

    // Act and Assert
    assertNull(proxyAuthorizationHeader.getQopParam());
  }

  @Test
  void testGetQopParam3() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addParameter("qop", "42");

    // Act and Assert
    assertEquals("42", proxyAuthorizationHeader.getQopParam());
  }

  @Test
  void testGetQopParam4() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addQuotedParameter("qop", "42");

    // Act and Assert
    assertEquals("42", proxyAuthorizationHeader.getQopParam());
  }

  @Test
  void testGetQopParam5() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addAutsParam("qop");
    proxyAuthorizationHeader.addAutsParam("qop");

    // Act and Assert
    assertNull(proxyAuthorizationHeader.getQopParam());
  }

  @Test
  void testGetQopParam6() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addParameter("qop", "42");
    proxyAuthorizationHeader.addAutsParam("qop");

    // Act and Assert
    assertEquals("42", proxyAuthorizationHeader.getQopParam());
  }

  @Test
  void testGetQopParam7() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addParameter("", "42");

    // Act and Assert
    assertNull(proxyAuthorizationHeader.getQopParam());
  }

  @Test
  void testGetQopParam8() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addParameter("qop", "");

    // Act and Assert
    assertEquals("", proxyAuthorizationHeader.getQopParam());
  }

  @Test
  void testGetRealmParam() {
    // Arrange, Act and Assert
    assertNull((new ProxyAuthorizationHeader("42")).getRealmParam());
    assertNull((new ProxyAuthorizationHeader("")).getRealmParam());
    assertNull((new ProxyAuthorizationHeader("Auth scheme", new Vector(1))).getRealmParam());
  }

  @Test
  void testGetRealmParam2() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addAutsParam("realm");

    // Act and Assert
    assertNull(proxyAuthorizationHeader.getRealmParam());
  }

  @Test
  void testGetRealmParam3() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addParameter("realm", "42");

    // Act and Assert
    assertEquals("42", proxyAuthorizationHeader.getRealmParam());
  }

  @Test
  void testGetRealmParam4() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addUnquotedParameter("realm", "42");

    // Act and Assert
    assertEquals("42", proxyAuthorizationHeader.getRealmParam());
  }

  @Test
  void testGetRealmParam5() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addAutsParam("realm");
    proxyAuthorizationHeader.addAutsParam("realm");

    // Act and Assert
    assertNull(proxyAuthorizationHeader.getRealmParam());
  }

  @Test
  void testGetRealmParam6() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addParameter("realm", "42");
    proxyAuthorizationHeader.addAutsParam("realm");

    // Act and Assert
    assertEquals("42", proxyAuthorizationHeader.getRealmParam());
  }

  @Test
  void testGetRealmParam7() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addParameter("", "42");

    // Act and Assert
    assertNull(proxyAuthorizationHeader.getRealmParam());
  }

  @Test
  void testGetRealmParam8() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addUnquotedParameter("realm", "");

    // Act and Assert
    assertEquals("", proxyAuthorizationHeader.getRealmParam());
  }

  @Test
  void testGetResponseParam() {
    // Arrange, Act and Assert
    assertNull((new ProxyAuthorizationHeader("42")).getResponseParam());
    assertNull((new ProxyAuthorizationHeader("")).getResponseParam());
    assertNull((new ProxyAuthorizationHeader("Auth scheme", new Vector(1))).getResponseParam());
  }

  @Test
  void testGetResponseParam2() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addAutsParam("response");

    // Act and Assert
    assertNull(proxyAuthorizationHeader.getResponseParam());
  }

  @Test
  void testGetResponseParam3() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addParameter("response", "42");

    // Act and Assert
    assertEquals("42", proxyAuthorizationHeader.getResponseParam());
  }

  @Test
  void testGetResponseParam4() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addUnquotedParameter("response", "42");

    // Act and Assert
    assertEquals("42", proxyAuthorizationHeader.getResponseParam());
  }

  @Test
  void testGetResponseParam5() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addAutsParam("response");
    proxyAuthorizationHeader.addAutsParam("response");

    // Act and Assert
    assertNull(proxyAuthorizationHeader.getResponseParam());
  }

  @Test
  void testGetResponseParam6() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addParameter("response", "42");
    proxyAuthorizationHeader.addAutsParam("response");

    // Act and Assert
    assertEquals("42", proxyAuthorizationHeader.getResponseParam());
  }

  @Test
  void testGetResponseParam7() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addParameter("", "42");

    // Act and Assert
    assertNull(proxyAuthorizationHeader.getResponseParam());
  }

  @Test
  void testGetResponseParam8() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addUnquotedParameter("response", "");

    // Act and Assert
    assertEquals("", proxyAuthorizationHeader.getResponseParam());
  }

  @Test
  void testGetRspauthParam() {
    // Arrange, Act and Assert
    assertNull((new ProxyAuthorizationHeader("42")).getRspauthParam());
    assertNull((new ProxyAuthorizationHeader("")).getRspauthParam());
    assertNull((new ProxyAuthorizationHeader("Auth scheme", new Vector(1))).getRspauthParam());
  }

  @Test
  void testGetRspauthParam2() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addAutsParam("rspauth");

    // Act and Assert
    assertNull(proxyAuthorizationHeader.getRspauthParam());
  }

  @Test
  void testGetRspauthParam3() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addParameter("rspauth", "42");

    // Act and Assert
    assertEquals("42", proxyAuthorizationHeader.getRspauthParam());
  }

  @Test
  void testGetRspauthParam4() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addUnquotedParameter("rspauth", "42");

    // Act and Assert
    assertEquals("42", proxyAuthorizationHeader.getRspauthParam());
  }

  @Test
  void testGetRspauthParam5() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addAutsParam("rspauth");
    proxyAuthorizationHeader.addAutsParam("rspauth");

    // Act and Assert
    assertNull(proxyAuthorizationHeader.getRspauthParam());
  }

  @Test
  void testGetRspauthParam6() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addParameter("rspauth", "42");
    proxyAuthorizationHeader.addAutsParam("rspauth");

    // Act and Assert
    assertEquals("42", proxyAuthorizationHeader.getRspauthParam());
  }

  @Test
  void testGetRspauthParam7() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addParameter("", "42");

    // Act and Assert
    assertNull(proxyAuthorizationHeader.getRspauthParam());
  }

  @Test
  void testGetRspauthParam8() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addUnquotedParameter("rspauth", "");

    // Act and Assert
    assertEquals("", proxyAuthorizationHeader.getRspauthParam());
  }

  @Test
  void testGetUriParam() {
    // Arrange, Act and Assert
    assertNull((new ProxyAuthorizationHeader("42")).getUriParam());
    assertNull((new ProxyAuthorizationHeader("")).getUriParam());
    assertNull((new ProxyAuthorizationHeader("Auth scheme", new Vector(1))).getUriParam());
  }

  @Test
  void testGetUriParam2() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addAutsParam("uri");

    // Act and Assert
    assertNull(proxyAuthorizationHeader.getUriParam());
  }

  @Test
  void testGetUriParam3() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addParameter("uri", "42");

    // Act and Assert
    assertEquals("42", proxyAuthorizationHeader.getUriParam());
  }

  @Test
  void testGetUriParam4() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addUnquotedParameter("uri", "42");

    // Act and Assert
    assertEquals("42", proxyAuthorizationHeader.getUriParam());
  }

  @Test
  void testGetUriParam5() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addAutsParam("uri");
    proxyAuthorizationHeader.addAutsParam("uri");

    // Act and Assert
    assertNull(proxyAuthorizationHeader.getUriParam());
  }

  @Test
  void testGetUriParam6() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addParameter("uri", "42");
    proxyAuthorizationHeader.addAutsParam("uri");

    // Act and Assert
    assertEquals("42", proxyAuthorizationHeader.getUriParam());
  }

  @Test
  void testGetUriParam7() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addParameter("", "42");

    // Act and Assert
    assertNull(proxyAuthorizationHeader.getUriParam());
  }

  @Test
  void testGetUriParam8() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addUnquotedParameter("uri", "");

    // Act and Assert
    assertEquals("", proxyAuthorizationHeader.getUriParam());
  }

  @Test
  void testGetUsernameParam() {
    // Arrange, Act and Assert
    assertNull((new ProxyAuthorizationHeader("42")).getUsernameParam());
    assertNull((new ProxyAuthorizationHeader("")).getUsernameParam());
    assertNull((new ProxyAuthorizationHeader("Auth scheme", new Vector(1))).getUsernameParam());
  }

  @Test
  void testGetUsernameParam2() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addAutsParam("username");

    // Act and Assert
    assertNull(proxyAuthorizationHeader.getUsernameParam());
  }

  @Test
  void testGetUsernameParam3() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addParameter("username", "42");

    // Act and Assert
    assertEquals("42", proxyAuthorizationHeader.getUsernameParam());
  }

  @Test
  void testGetUsernameParam4() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addUnquotedParameter("username", "42");

    // Act and Assert
    assertEquals("42", proxyAuthorizationHeader.getUsernameParam());
  }

  @Test
  void testGetUsernameParam5() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addAutsParam("username");
    proxyAuthorizationHeader.addAutsParam("username");

    // Act and Assert
    assertNull(proxyAuthorizationHeader.getUsernameParam());
  }

  @Test
  void testGetUsernameParam6() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addParameter("username", "42");
    proxyAuthorizationHeader.addAutsParam("username");

    // Act and Assert
    assertEquals("42", proxyAuthorizationHeader.getUsernameParam());
  }

  @Test
  void testGetUsernameParam7() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addParameter("", "42");

    // Act and Assert
    assertNull(proxyAuthorizationHeader.getUsernameParam());
  }

  @Test
  void testGetUsernameParam8() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addUnquotedParameter("username", "");

    // Act and Assert
    assertEquals("", proxyAuthorizationHeader.getUsernameParam());
  }

  @Test
  void testHasAlgorithmParam() {
    // Arrange, Act and Assert
    assertFalse((new ProxyAuthorizationHeader("42")).hasAlgorithmParam());
    assertFalse((new ProxyAuthorizationHeader("")).hasAlgorithmParam());
    assertFalse((new ProxyAuthorizationHeader("Auth scheme", new Vector(1))).hasAlgorithmParam());
  }

  @Test
  void testHasAlgorithmParam2() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addAutsParam("algorithm");

    // Act and Assert
    assertFalse(proxyAuthorizationHeader.hasAlgorithmParam());
  }

  @Test
  void testHasAlgorithmParam3() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addParameter("algorithm", "42");

    // Act and Assert
    assertTrue(proxyAuthorizationHeader.hasAlgorithmParam());
  }

  @Test
  void testHasAlgorithmParam4() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addAutsParam("algorithm");
    proxyAuthorizationHeader.addAutsParam("algorithm");

    // Act and Assert
    assertFalse(proxyAuthorizationHeader.hasAlgorithmParam());
  }

  @Test
  void testHasAlgorithmParam5() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addParameter("", "42");

    // Act and Assert
    assertFalse(proxyAuthorizationHeader.hasAlgorithmParam());
  }

  @Test
  void testHasAutsParam() {
    // Arrange, Act and Assert
    assertFalse((new ProxyAuthorizationHeader("42")).hasAutsParam());
    assertFalse((new ProxyAuthorizationHeader("")).hasAutsParam());
    assertFalse((new ProxyAuthorizationHeader("Auth scheme", new Vector(1))).hasAutsParam());
  }

  @Test
  void testHasAutsParam2() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addAutsParam("auts");

    // Act and Assert
    assertTrue(proxyAuthorizationHeader.hasAutsParam());
  }

  @Test
  void testHasAutsParam3() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addCnonceParam("auts");

    // Act and Assert
    assertFalse(proxyAuthorizationHeader.hasAutsParam());
  }

  @Test
  void testHasAutsParam4() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addCnonceParam("auts");
    proxyAuthorizationHeader.addAutsParam("auts");

    // Act and Assert
    assertTrue(proxyAuthorizationHeader.hasAutsParam());
  }

  @Test
  void testHasAutsParam5() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addParameter("", "42");

    // Act and Assert
    assertFalse(proxyAuthorizationHeader.hasAutsParam());
  }

  @Test
  void testHasCnonceParam() {
    // Arrange, Act and Assert
    assertFalse((new ProxyAuthorizationHeader("42")).hasCnonceParam());
    assertFalse((new ProxyAuthorizationHeader("")).hasCnonceParam());
    assertFalse((new ProxyAuthorizationHeader("Auth scheme", new Vector(1))).hasCnonceParam());
  }

  @Test
  void testHasCnonceParam2() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addAutsParam("cnonce");

    // Act and Assert
    assertFalse(proxyAuthorizationHeader.hasCnonceParam());
  }

  @Test
  void testHasCnonceParam3() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addCnonceParam("cnonce");

    // Act and Assert
    assertTrue(proxyAuthorizationHeader.hasCnonceParam());
  }

  @Test
  void testHasCnonceParam4() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addAutsParam("cnonce");
    proxyAuthorizationHeader.addAutsParam("cnonce");

    // Act and Assert
    assertFalse(proxyAuthorizationHeader.hasCnonceParam());
  }

  @Test
  void testHasCnonceParam5() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addParameter("", "42");

    // Act and Assert
    assertFalse(proxyAuthorizationHeader.hasCnonceParam());
  }

  @Test
  void testHasNcParam() {
    // Arrange, Act and Assert
    assertFalse((new ProxyAuthorizationHeader("42")).hasNcParam());
    assertFalse((new ProxyAuthorizationHeader("")).hasNcParam());
    assertFalse((new ProxyAuthorizationHeader("Auth scheme", new Vector(1))).hasNcParam());
  }

  @Test
  void testHasNcParam2() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addAutsParam("nc");

    // Act and Assert
    assertFalse(proxyAuthorizationHeader.hasNcParam());
  }

  @Test
  void testHasNcParam3() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addParameter("nc", "42");

    // Act and Assert
    assertTrue(proxyAuthorizationHeader.hasNcParam());
  }

  @Test
  void testHasNcParam4() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addAutsParam("nc");
    proxyAuthorizationHeader.addAutsParam("nc");

    // Act and Assert
    assertFalse(proxyAuthorizationHeader.hasNcParam());
  }

  @Test
  void testHasNcParam5() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addParameter("", "42");

    // Act and Assert
    assertFalse(proxyAuthorizationHeader.hasNcParam());
  }

  @Test
  void testHasNextnonceParam() {
    // Arrange, Act and Assert
    assertFalse((new ProxyAuthorizationHeader("42")).hasNextnonceParam());
    assertFalse((new ProxyAuthorizationHeader("")).hasNextnonceParam());
    assertFalse((new ProxyAuthorizationHeader("Auth scheme", new Vector(1))).hasNextnonceParam());
  }

  @Test
  void testHasNextnonceParam2() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addAutsParam("nextnonce");

    // Act and Assert
    assertFalse(proxyAuthorizationHeader.hasNextnonceParam());
  }

  @Test
  void testHasNextnonceParam3() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addParameter("nextnonce", "42");

    // Act and Assert
    assertTrue(proxyAuthorizationHeader.hasNextnonceParam());
  }

  @Test
  void testHasNextnonceParam4() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addAutsParam("nextnonce");
    proxyAuthorizationHeader.addAutsParam("nextnonce");

    // Act and Assert
    assertFalse(proxyAuthorizationHeader.hasNextnonceParam());
  }

  @Test
  void testHasNextnonceParam5() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addParameter("", "42");

    // Act and Assert
    assertFalse(proxyAuthorizationHeader.hasNextnonceParam());
  }

  @Test
  void testHasNonceParam() {
    // Arrange, Act and Assert
    assertFalse((new ProxyAuthorizationHeader("42")).hasNonceParam());
    assertFalse((new ProxyAuthorizationHeader("")).hasNonceParam());
    assertFalse((new ProxyAuthorizationHeader("Auth scheme", new Vector(1))).hasNonceParam());
  }

  @Test
  void testHasNonceParam2() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addAutsParam("nonce");

    // Act and Assert
    assertFalse(proxyAuthorizationHeader.hasNonceParam());
  }

  @Test
  void testHasNonceParam3() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addParameter("nonce", "42");

    // Act and Assert
    assertTrue(proxyAuthorizationHeader.hasNonceParam());
  }

  @Test
  void testHasNonceParam4() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addAutsParam("nonce");
    proxyAuthorizationHeader.addAutsParam("nonce");

    // Act and Assert
    assertFalse(proxyAuthorizationHeader.hasNonceParam());
  }

  @Test
  void testHasNonceParam5() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addParameter("", "42");

    // Act and Assert
    assertFalse(proxyAuthorizationHeader.hasNonceParam());
  }

  @Test
  void testHasOpaqueParam() {
    // Arrange, Act and Assert
    assertFalse((new ProxyAuthorizationHeader("42")).hasOpaqueParam());
    assertFalse((new ProxyAuthorizationHeader("")).hasOpaqueParam());
    assertFalse((new ProxyAuthorizationHeader("Auth scheme", new Vector(1))).hasOpaqueParam());
  }

  @Test
  void testHasOpaqueParam2() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addAutsParam("opaque");

    // Act and Assert
    assertFalse(proxyAuthorizationHeader.hasOpaqueParam());
  }

  @Test
  void testHasOpaqueParam3() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addParameter("opaque", "42");

    // Act and Assert
    assertTrue(proxyAuthorizationHeader.hasOpaqueParam());
  }

  @Test
  void testHasOpaqueParam4() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addAutsParam("opaque");
    proxyAuthorizationHeader.addAutsParam("opaque");

    // Act and Assert
    assertFalse(proxyAuthorizationHeader.hasOpaqueParam());
  }

  @Test
  void testHasOpaqueParam5() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addParameter("", "42");

    // Act and Assert
    assertFalse(proxyAuthorizationHeader.hasOpaqueParam());
  }

  @Test
  void testHasParameter() {
    // Arrange, Act and Assert
    assertFalse((new ProxyAuthorizationHeader("42")).hasParameter("Param name"));
    assertFalse((new ProxyAuthorizationHeader("")).hasParameter("Param name"));
    assertFalse((new ProxyAuthorizationHeader("Auth scheme", new Vector(1))).hasParameter("Param name"));
  }

  @Test
  void testHasParameter2() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addAutsParam("Unquoted auts");

    // Act and Assert
    assertFalse(proxyAuthorizationHeader.hasParameter("Param name"));
  }

  @Test
  void testHasParameter3() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addAutsParam("auts");
    proxyAuthorizationHeader.addAutsParam("Unquoted auts");

    // Act and Assert
    assertFalse(proxyAuthorizationHeader.hasParameter("Param name"));
  }

  @Test
  void testHasParameter4() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addParameter("", "42");

    // Act and Assert
    assertFalse(proxyAuthorizationHeader.hasParameter("Param name"));
  }

  @Test
  void testHasParameter5() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("");
    proxyAuthorizationHeader.addAutsParam("Unquoted auts");

    // Act and Assert
    assertFalse(proxyAuthorizationHeader.hasParameter("Param name"));
  }

  @Test
  void testHasQopOptionsParam() {
    // Arrange, Act and Assert
    assertFalse((new ProxyAuthorizationHeader("42")).hasQopOptionsParam());
    assertFalse((new ProxyAuthorizationHeader("")).hasQopOptionsParam());
    assertFalse((new ProxyAuthorizationHeader("Auth scheme", new Vector(1))).hasQopOptionsParam());
  }

  @Test
  void testHasQopOptionsParam2() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addAutsParam("qop");

    // Act and Assert
    assertFalse(proxyAuthorizationHeader.hasQopOptionsParam());
  }

  @Test
  void testHasQopOptionsParam3() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addParameter("qop", "42");

    // Act and Assert
    assertTrue(proxyAuthorizationHeader.hasQopOptionsParam());
  }

  @Test
  void testHasQopOptionsParam4() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addAutsParam("qop");
    proxyAuthorizationHeader.addAutsParam("qop");

    // Act and Assert
    assertFalse(proxyAuthorizationHeader.hasQopOptionsParam());
  }

  @Test
  void testHasQopOptionsParam5() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addParameter("", "42");

    // Act and Assert
    assertFalse(proxyAuthorizationHeader.hasQopOptionsParam());
  }

  @Test
  void testHasQopParam() {
    // Arrange, Act and Assert
    assertFalse((new ProxyAuthorizationHeader("42")).hasQopParam());
    assertFalse((new ProxyAuthorizationHeader("")).hasQopParam());
    assertFalse((new ProxyAuthorizationHeader("Auth scheme", new Vector(1))).hasQopParam());
  }

  @Test
  void testHasQopParam2() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addAutsParam("qop");

    // Act and Assert
    assertFalse(proxyAuthorizationHeader.hasQopParam());
  }

  @Test
  void testHasQopParam3() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addParameter("qop", "42");

    // Act and Assert
    assertTrue(proxyAuthorizationHeader.hasQopParam());
  }

  @Test
  void testHasQopParam4() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addAutsParam("qop");
    proxyAuthorizationHeader.addAutsParam("qop");

    // Act and Assert
    assertFalse(proxyAuthorizationHeader.hasQopParam());
  }

  @Test
  void testHasQopParam5() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addParameter("", "42");

    // Act and Assert
    assertFalse(proxyAuthorizationHeader.hasQopParam());
  }

  @Test
  void testHasRealmParam() {
    // Arrange, Act and Assert
    assertFalse((new ProxyAuthorizationHeader("42")).hasRealmParam());
    assertFalse((new ProxyAuthorizationHeader("")).hasRealmParam());
    assertFalse((new ProxyAuthorizationHeader("Auth scheme", new Vector(1))).hasRealmParam());
  }

  @Test
  void testHasRealmParam2() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addAutsParam("realm");

    // Act and Assert
    assertFalse(proxyAuthorizationHeader.hasRealmParam());
  }

  @Test
  void testHasRealmParam3() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addParameter("realm", "42");

    // Act and Assert
    assertTrue(proxyAuthorizationHeader.hasRealmParam());
  }

  @Test
  void testHasRealmParam4() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addAutsParam("realm");
    proxyAuthorizationHeader.addAutsParam("realm");

    // Act and Assert
    assertFalse(proxyAuthorizationHeader.hasRealmParam());
  }

  @Test
  void testHasRealmParam5() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addParameter("", "42");

    // Act and Assert
    assertFalse(proxyAuthorizationHeader.hasRealmParam());
  }

  @Test
  void testHasResponseParam() {
    // Arrange, Act and Assert
    assertFalse((new ProxyAuthorizationHeader("42")).hasResponseParam());
    assertFalse((new ProxyAuthorizationHeader("")).hasResponseParam());
    assertFalse((new ProxyAuthorizationHeader("Auth scheme", new Vector(1))).hasResponseParam());
  }

  @Test
  void testHasResponseParam2() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addAutsParam("response");

    // Act and Assert
    assertFalse(proxyAuthorizationHeader.hasResponseParam());
  }

  @Test
  void testHasResponseParam3() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addParameter("response", "42");

    // Act and Assert
    assertTrue(proxyAuthorizationHeader.hasResponseParam());
  }

  @Test
  void testHasResponseParam4() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addAutsParam("response");
    proxyAuthorizationHeader.addAutsParam("response");

    // Act and Assert
    assertFalse(proxyAuthorizationHeader.hasResponseParam());
  }

  @Test
  void testHasResponseParam5() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addParameter("", "42");

    // Act and Assert
    assertFalse(proxyAuthorizationHeader.hasResponseParam());
  }

  @Test
  void testHasRspauthParam() {
    // Arrange, Act and Assert
    assertFalse((new ProxyAuthorizationHeader("42")).hasRspauthParam());
    assertFalse((new ProxyAuthorizationHeader("")).hasRspauthParam());
    assertFalse((new ProxyAuthorizationHeader("Auth scheme", new Vector(1))).hasRspauthParam());
  }

  @Test
  void testHasRspauthParam2() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addAutsParam("rspauth");

    // Act and Assert
    assertFalse(proxyAuthorizationHeader.hasRspauthParam());
  }

  @Test
  void testHasRspauthParam3() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addParameter("rspauth", "42");

    // Act and Assert
    assertTrue(proxyAuthorizationHeader.hasRspauthParam());
  }

  @Test
  void testHasRspauthParam4() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addAutsParam("rspauth");
    proxyAuthorizationHeader.addAutsParam("rspauth");

    // Act and Assert
    assertFalse(proxyAuthorizationHeader.hasRspauthParam());
  }

  @Test
  void testHasRspauthParam5() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addParameter("", "42");

    // Act and Assert
    assertFalse(proxyAuthorizationHeader.hasRspauthParam());
  }

  @Test
  void testHasUriParam() {
    // Arrange, Act and Assert
    assertFalse((new ProxyAuthorizationHeader("42")).hasUriParam());
    assertFalse((new ProxyAuthorizationHeader("")).hasUriParam());
    assertFalse((new ProxyAuthorizationHeader("Auth scheme", new Vector(1))).hasUriParam());
  }

  @Test
  void testHasUriParam2() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addAutsParam("uri");

    // Act and Assert
    assertFalse(proxyAuthorizationHeader.hasUriParam());
  }

  @Test
  void testHasUriParam3() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addParameter("uri", "42");

    // Act and Assert
    assertTrue(proxyAuthorizationHeader.hasUriParam());
  }

  @Test
  void testHasUriParam4() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addAutsParam("uri");
    proxyAuthorizationHeader.addAutsParam("uri");

    // Act and Assert
    assertFalse(proxyAuthorizationHeader.hasUriParam());
  }

  @Test
  void testHasUriParam5() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addParameter("", "42");

    // Act and Assert
    assertFalse(proxyAuthorizationHeader.hasUriParam());
  }

  @Test
  void testHasUsernameParam() {
    // Arrange, Act and Assert
    assertFalse((new ProxyAuthorizationHeader("42")).hasUsernameParam());
    assertFalse((new ProxyAuthorizationHeader("")).hasUsernameParam());
    assertFalse((new ProxyAuthorizationHeader("Auth scheme", new Vector(1))).hasUsernameParam());
  }

  @Test
  void testHasUsernameParam2() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addAutsParam("username");

    // Act and Assert
    assertFalse(proxyAuthorizationHeader.hasUsernameParam());
  }

  @Test
  void testHasUsernameParam3() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addParameter("username", "42");

    // Act and Assert
    assertTrue(proxyAuthorizationHeader.hasUsernameParam());
  }

  @Test
  void testHasUsernameParam4() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addAutsParam("username");
    proxyAuthorizationHeader.addAutsParam("username");

    // Act and Assert
    assertFalse(proxyAuthorizationHeader.hasUsernameParam());
  }

  @Test
  void testHasUsernameParam5() {
    // Arrange
    ProxyAuthorizationHeader proxyAuthorizationHeader = new ProxyAuthorizationHeader("42");
    proxyAuthorizationHeader.addParameter("", "42");

    // Act and Assert
    assertFalse(proxyAuthorizationHeader.hasUsernameParam());
  }
}

