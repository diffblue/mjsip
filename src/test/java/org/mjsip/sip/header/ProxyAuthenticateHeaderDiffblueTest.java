package org.mjsip.sip.header;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Vector;
import org.junit.jupiter.api.Test;

class ProxyAuthenticateHeaderDiffblueTest {
  @Test
  void testAddAlgorithParam() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");

    // Act
    proxyAuthenticateHeader.addAlgorithParam("Algorithm");

    // Assert
    assertEquals("42 algorithm=Algorithm", proxyAuthenticateHeader.getValue());
  }

  @Test
  void testAddAlgorithParam2() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addAutsParam("algorithm");

    // Act
    proxyAuthenticateHeader.addAlgorithParam("Algorithm");

    // Assert
    assertEquals("42 auts=\"algorithm\", algorithm=Algorithm", proxyAuthenticateHeader.getValue());
  }

  @Test
  void testAddAutsParam() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");

    // Act
    proxyAuthenticateHeader.addAutsParam("Unquoted auts");

    // Assert
    assertEquals("42 auts=\"Unquoted auts\"", proxyAuthenticateHeader.getValue());
  }

  @Test
  void testAddAutsParam2() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addAutsParam("auts");

    // Act
    proxyAuthenticateHeader.addAutsParam("Unquoted auts");

    // Assert
    assertEquals("42 auts=\"auts\", auts=\"Unquoted auts\"", proxyAuthenticateHeader.getValue());
  }

  @Test
  void testAddCnonceParam() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");

    // Act
    proxyAuthenticateHeader.addCnonceParam("Unquoted cnonce");

    // Assert
    assertEquals("42 cnonce=\"Unquoted cnonce\"", proxyAuthenticateHeader.getValue());
  }

  @Test
  void testAddCnonceParam2() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addAutsParam("cnonce");

    // Act
    proxyAuthenticateHeader.addCnonceParam("Unquoted cnonce");

    // Assert
    assertEquals("42 auts=\"cnonce\", cnonce=\"Unquoted cnonce\"", proxyAuthenticateHeader.getValue());
  }

  @Test
  void testAddNcParam() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");

    // Act
    proxyAuthenticateHeader.addNcParam("Nc");

    // Assert
    assertEquals("42 nc=Nc", proxyAuthenticateHeader.getValue());
  }

  @Test
  void testAddNcParam2() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addAutsParam("nc");

    // Act
    proxyAuthenticateHeader.addNcParam("Nc");

    // Assert
    assertEquals("42 auts=\"nc\", nc=Nc", proxyAuthenticateHeader.getValue());
  }

  @Test
  void testAddNextnonceParam() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");

    // Act
    proxyAuthenticateHeader.addNextnonceParam("Unquoted nextnonce");

    // Assert
    assertEquals("42 nextnonce=\"Unquoted nextnonce\"", proxyAuthenticateHeader.getValue());
  }

  @Test
  void testAddNextnonceParam2() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addAutsParam("nextnonce");

    // Act
    proxyAuthenticateHeader.addNextnonceParam("Unquoted nextnonce");

    // Assert
    assertEquals("42 auts=\"nextnonce\", nextnonce=\"Unquoted nextnonce\"", proxyAuthenticateHeader.getValue());
  }

  @Test
  void testAddNonceParam() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");

    // Act
    proxyAuthenticateHeader.addNonceParam("Unquoted nonce");

    // Assert
    assertEquals("42 nonce=\"Unquoted nonce\"", proxyAuthenticateHeader.getValue());
  }

  @Test
  void testAddNonceParam2() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addAutsParam("nonce");

    // Act
    proxyAuthenticateHeader.addNonceParam("Unquoted nonce");

    // Assert
    assertEquals("42 auts=\"nonce\", nonce=\"Unquoted nonce\"", proxyAuthenticateHeader.getValue());
  }

  @Test
  void testAddOpaqueParam() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");

    // Act
    proxyAuthenticateHeader.addOpaqueParam("Unquoted opaque");

    // Assert
    assertEquals("42 opaque=\"Unquoted opaque\"", proxyAuthenticateHeader.getValue());
  }

  @Test
  void testAddOpaqueParam2() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addAutsParam("opaque");

    // Act
    proxyAuthenticateHeader.addOpaqueParam("Unquoted opaque");

    // Assert
    assertEquals("42 auts=\"opaque\", opaque=\"Unquoted opaque\"", proxyAuthenticateHeader.getValue());
  }

  @Test
  void testAddParameter() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");

    // Act
    proxyAuthenticateHeader.addParameter("Param name", "42");

    // Assert
    assertEquals("42 Param name=42", proxyAuthenticateHeader.getValue());
  }

  @Test
  void testAddParameter2() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addAutsParam("Unquoted auts");

    // Act
    proxyAuthenticateHeader.addParameter("Param name", "42");

    // Assert
    assertEquals("42 auts=\"Unquoted auts\", Param name=42", proxyAuthenticateHeader.getValue());
  }

  @Test
  void testAddParameter3() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");

    // Act
    proxyAuthenticateHeader.addParameter("auts", "42");

    // Assert
    assertEquals("42 auts=\"42\"", proxyAuthenticateHeader.getValue());
  }

  @Test
  void testAddParameter4() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addAutsParam("Unquoted auts");

    // Act
    proxyAuthenticateHeader.addParameter("auts", "42");

    // Assert
    assertEquals("42 auts=\"Unquoted auts\", auts=\"42\"", proxyAuthenticateHeader.getValue());
  }

  @Test
  void testAddQopOptionsParam() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");

    // Act
    proxyAuthenticateHeader.addQopOptionsParam("Unquoted qop options");

    // Assert
    assertEquals("42 qop=\"Unquoted qop options\"", proxyAuthenticateHeader.getValue());
  }

  @Test
  void testAddQopOptionsParam2() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addAutsParam("qop");

    // Act
    proxyAuthenticateHeader.addQopOptionsParam("Unquoted qop options");

    // Assert
    assertEquals("42 auts=\"qop\", qop=\"Unquoted qop options\"", proxyAuthenticateHeader.getValue());
  }

  @Test
  void testAddQopParam() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");

    // Act
    proxyAuthenticateHeader.addQopParam("Qop");

    // Assert
    assertEquals("42 qop=Qop", proxyAuthenticateHeader.getValue());
  }

  @Test
  void testAddQopParam2() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addAutsParam("qop");

    // Act
    proxyAuthenticateHeader.addQopParam("Qop");

    // Assert
    assertEquals("42 auts=\"qop\", qop=Qop", proxyAuthenticateHeader.getValue());
  }

  @Test
  void testAddQuotedParameter() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");

    // Act
    proxyAuthenticateHeader.addQuotedParameter("Param name", "42");

    // Assert
    assertEquals("42 Param name=\"42\"", proxyAuthenticateHeader.getValue());
  }

  @Test
  void testAddQuotedParameter2() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addAutsParam("Unquoted auts");

    // Act
    proxyAuthenticateHeader.addQuotedParameter("Param name", "42");

    // Assert
    assertEquals("42 auts=\"Unquoted auts\", Param name=\"42\"", proxyAuthenticateHeader.getValue());
  }

  @Test
  void testAddRealmParam() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");

    // Act
    proxyAuthenticateHeader.addRealmParam("Unquoted realm");

    // Assert
    assertEquals("42 realm=\"Unquoted realm\"", proxyAuthenticateHeader.getValue());
  }

  @Test
  void testAddRealmParam2() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addAutsParam("realm");

    // Act
    proxyAuthenticateHeader.addRealmParam("Unquoted realm");

    // Assert
    assertEquals("42 auts=\"realm\", realm=\"Unquoted realm\"", proxyAuthenticateHeader.getValue());
  }

  @Test
  void testAddResponseParam() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");

    // Act
    proxyAuthenticateHeader.addResponseParam("Unquoted response");

    // Assert
    assertEquals("42 response=\"Unquoted response\"", proxyAuthenticateHeader.getValue());
  }

  @Test
  void testAddResponseParam2() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addAutsParam("response");

    // Act
    proxyAuthenticateHeader.addResponseParam("Unquoted response");

    // Assert
    assertEquals("42 auts=\"response\", response=\"Unquoted response\"", proxyAuthenticateHeader.getValue());
  }

  @Test
  void testAddRspauthParam() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");

    // Act
    proxyAuthenticateHeader.addRspauthParam("Unquoted rspauth");

    // Assert
    assertEquals("42 rspauth=\"Unquoted rspauth\"", proxyAuthenticateHeader.getValue());
  }

  @Test
  void testAddRspauthParam2() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addAutsParam("rspauth");

    // Act
    proxyAuthenticateHeader.addRspauthParam("Unquoted rspauth");

    // Assert
    assertEquals("42 auts=\"rspauth\", rspauth=\"Unquoted rspauth\"", proxyAuthenticateHeader.getValue());
  }

  @Test
  void testAddUnquotedParameter() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");

    // Act
    proxyAuthenticateHeader.addUnquotedParameter("Param name", "42");

    // Assert
    assertEquals("42 Param name=42", proxyAuthenticateHeader.getValue());
  }

  @Test
  void testAddUnquotedParameter2() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addAutsParam("Unquoted auts");

    // Act
    proxyAuthenticateHeader.addUnquotedParameter("Param name", "42");

    // Assert
    assertEquals("42 auts=\"Unquoted auts\", Param name=42", proxyAuthenticateHeader.getValue());
  }

  @Test
  void testAddUriParam() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");

    // Act
    proxyAuthenticateHeader.addUriParam("Unquoted uri");

    // Assert
    assertEquals("42 uri=\"Unquoted uri\"", proxyAuthenticateHeader.getValue());
  }

  @Test
  void testAddUriParam2() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addAutsParam("uri");

    // Act
    proxyAuthenticateHeader.addUriParam("Unquoted uri");

    // Assert
    assertEquals("42 auts=\"uri\", uri=\"Unquoted uri\"", proxyAuthenticateHeader.getValue());
  }

  @Test
  void testAddUsernameParam() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");

    // Act
    proxyAuthenticateHeader.addUsernameParam("janedoe");

    // Assert
    assertEquals("42 username=\"janedoe\"", proxyAuthenticateHeader.getValue());
  }

  @Test
  void testAddUsernameParam2() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addAutsParam("username");

    // Act
    proxyAuthenticateHeader.addUsernameParam("janedoe");

    // Assert
    assertEquals("42 auts=\"username\", username=\"janedoe\"", proxyAuthenticateHeader.getValue());
  }

  @Test
  void testConstructor() {
    // Arrange and Act
    ProxyAuthenticateHeader actualProxyAuthenticateHeader = new ProxyAuthenticateHeader("42");

    // Assert
    assertNull(actualProxyAuthenticateHeader.getAlgorithParam());
    String[] stringArray = AuthenticationHeader.QUOTED_PARAMETERS;
    assertSame(actualProxyAuthenticateHeader.QUOTED_PARAMETERS, stringArray);
    assertEquals(10, stringArray.length);
    assertEquals(" ", AuthenticationHeader.LWS_SEPARATOR);
    assertEquals("Proxy-Authenticate: 42\r\n", actualProxyAuthenticateHeader.toString());
    assertFalse(actualProxyAuthenticateHeader.hasUsernameParam());
    assertFalse(actualProxyAuthenticateHeader.hasUriParam());
    assertFalse(actualProxyAuthenticateHeader.hasRspauthParam());
    assertFalse(actualProxyAuthenticateHeader.hasResponseParam());
    assertFalse(actualProxyAuthenticateHeader.hasRealmParam());
    assertFalse(actualProxyAuthenticateHeader.hasQopParam());
    assertFalse(actualProxyAuthenticateHeader.hasQopOptionsParam());
    assertFalse(actualProxyAuthenticateHeader.hasOpaqueParam());
    assertFalse(actualProxyAuthenticateHeader.hasNonceParam());
    assertFalse(actualProxyAuthenticateHeader.hasNextnonceParam());
    assertFalse(actualProxyAuthenticateHeader.hasNcParam());
    assertFalse(actualProxyAuthenticateHeader.hasCnonceParam());
    assertFalse(actualProxyAuthenticateHeader.hasAutsParam());
    assertFalse(actualProxyAuthenticateHeader.hasAlgorithmParam());
    assertEquals("42", actualProxyAuthenticateHeader.getValue());
    assertNull(actualProxyAuthenticateHeader.getUsernameParam());
    assertNull(actualProxyAuthenticateHeader.getUriParam());
    assertNull(actualProxyAuthenticateHeader.getRspauthParam());
    assertNull(actualProxyAuthenticateHeader.getResponseParam());
    assertNull(actualProxyAuthenticateHeader.getRealmParam());
    assertNull(actualProxyAuthenticateHeader.getQopParam());
    assertNull(actualProxyAuthenticateHeader.getQopOptionsParam());
    assertTrue(actualProxyAuthenticateHeader.getParameterNames().isEmpty());
    assertNull(actualProxyAuthenticateHeader.getOpaqueParam());
    assertNull(actualProxyAuthenticateHeader.getNonceParam());
    assertNull(actualProxyAuthenticateHeader.getNextnonceParam());
    assertNull(actualProxyAuthenticateHeader.getNcParam());
    assertEquals(CoreSipHeaders.Proxy_Authenticate, actualProxyAuthenticateHeader.getName());
    assertNull(actualProxyAuthenticateHeader.getCnonceParam());
    assertNull(actualProxyAuthenticateHeader.getAutsParam());
    assertEquals("42", actualProxyAuthenticateHeader.getAuthScheme());
  }

  @Test
  void testConstructor2() {
    // Arrange and Act
    ProxyAuthenticateHeader actualProxyAuthenticateHeader = new ProxyAuthenticateHeader("Auth scheme", new Vector(1));

    // Assert
    assertEquals("Auth scheme", actualProxyAuthenticateHeader.getValue());
    assertEquals(CoreSipHeaders.Proxy_Authenticate, actualProxyAuthenticateHeader.getName());
  }

  @Test
  void testConstructor3() {
    // Arrange
    Vector vector = new Vector(1);
    vector.add("42");

    // Act
    ProxyAuthenticateHeader actualProxyAuthenticateHeader = new ProxyAuthenticateHeader("Auth scheme", vector);

    // Assert
    assertEquals("Auth scheme 42", actualProxyAuthenticateHeader.getValue());
    assertEquals(CoreSipHeaders.Proxy_Authenticate, actualProxyAuthenticateHeader.getName());
  }

  @Test
  void testConstructor4() {
    // Arrange
    Vector vector = new Vector(1);
    vector.add("42");
    vector.add("42");

    // Act
    ProxyAuthenticateHeader actualProxyAuthenticateHeader = new ProxyAuthenticateHeader("Auth scheme", vector);

    // Assert
    assertEquals("Auth scheme 42, 42", actualProxyAuthenticateHeader.getValue());
    assertEquals(CoreSipHeaders.Proxy_Authenticate, actualProxyAuthenticateHeader.getName());
  }

  @Test
  void testConstructor5() {
    // Arrange
    Vector vector = new Vector(1);
    vector.add("foo");

    // Act
    ProxyAuthenticateHeader actualProxyAuthenticateHeader = new ProxyAuthenticateHeader("Auth scheme", vector);

    // Assert
    assertEquals("Auth scheme foo", actualProxyAuthenticateHeader.getValue());
    assertEquals(CoreSipHeaders.Proxy_Authenticate, actualProxyAuthenticateHeader.getName());
  }

  @Test
  void testConstructor6() {
    // Arrange and Act
    ProxyAuthenticateHeader actualProxyAuthenticateHeader = new ProxyAuthenticateHeader(new Header("Hname", "42"));

    // Assert
    assertEquals("42", actualProxyAuthenticateHeader.getValue());
    assertEquals("Hname", actualProxyAuthenticateHeader.getName());
  }

  @Test
  void testConstructor7() {
    // Arrange
    Header header = new Header("Hname", "42");
    header.setValue("42");

    // Act
    ProxyAuthenticateHeader actualProxyAuthenticateHeader = new ProxyAuthenticateHeader(header);

    // Assert
    assertEquals("42", actualProxyAuthenticateHeader.getValue());
    assertEquals("Hname", actualProxyAuthenticateHeader.getName());
  }

  @Test
  void testGetAlgorithParam() {
    // Arrange, Act and Assert
    assertNull((new ProxyAuthenticateHeader("42")).getAlgorithParam());
    assertNull((new ProxyAuthenticateHeader("")).getAlgorithParam());
    assertNull((new ProxyAuthenticateHeader("Auth scheme", new Vector(1))).getAlgorithParam());
  }

  @Test
  void testGetAlgorithParam2() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addAutsParam("algorithm");

    // Act and Assert
    assertNull(proxyAuthenticateHeader.getAlgorithParam());
  }

  @Test
  void testGetAlgorithParam3() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addParameter("algorithm", "42");

    // Act and Assert
    assertEquals("42", proxyAuthenticateHeader.getAlgorithParam());
  }

  @Test
  void testGetAlgorithParam4() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addQuotedParameter("algorithm", "42");

    // Act and Assert
    assertEquals("42", proxyAuthenticateHeader.getAlgorithParam());
  }

  @Test
  void testGetAlgorithParam5() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addAutsParam("algorithm");
    proxyAuthenticateHeader.addAutsParam("algorithm");

    // Act and Assert
    assertNull(proxyAuthenticateHeader.getAlgorithParam());
  }

  @Test
  void testGetAlgorithParam6() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addParameter("algorithm", "42");
    proxyAuthenticateHeader.addAutsParam("algorithm");

    // Act and Assert
    assertEquals("42", proxyAuthenticateHeader.getAlgorithParam());
  }

  @Test
  void testGetAlgorithParam7() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addParameter("", "42");

    // Act and Assert
    assertNull(proxyAuthenticateHeader.getAlgorithParam());
  }

  @Test
  void testGetAlgorithParam8() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addParameter("algorithm", "");

    // Act and Assert
    assertEquals("", proxyAuthenticateHeader.getAlgorithParam());
  }

  @Test
  void testGetAuthScheme() {
    // Arrange, Act and Assert
    assertEquals("42", (new ProxyAuthenticateHeader("42")).getAuthScheme());
    assertEquals("", (new ProxyAuthenticateHeader("")).getAuthScheme());
    assertEquals("Auth", (new ProxyAuthenticateHeader("Auth scheme", new Vector(1))).getAuthScheme());
  }

  @Test
  void testGetAuthScheme2() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("");
    proxyAuthenticateHeader.addAutsParam("Unquoted auts");

    // Act and Assert
    assertEquals("auts=\"Unquoted", proxyAuthenticateHeader.getAuthScheme());
  }

  @Test
  void testGetAutsParam() {
    // Arrange, Act and Assert
    assertNull((new ProxyAuthenticateHeader("42")).getAutsParam());
    assertNull((new ProxyAuthenticateHeader("")).getAutsParam());
    assertNull((new ProxyAuthenticateHeader("Auth scheme", new Vector(1))).getAutsParam());
  }

  @Test
  void testGetAutsParam2() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addAutsParam("auts");

    // Act and Assert
    assertEquals("auts", proxyAuthenticateHeader.getAutsParam());
  }

  @Test
  void testGetAutsParam3() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addUnquotedParameter("auts", "42");

    // Act and Assert
    assertEquals("42", proxyAuthenticateHeader.getAutsParam());
  }

  @Test
  void testGetAutsParam4() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addAutsParam("auts");
    proxyAuthenticateHeader.addAutsParam("auts");

    // Act and Assert
    assertEquals("auts", proxyAuthenticateHeader.getAutsParam());
  }

  @Test
  void testGetAutsParam5() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addCnonceParam("auts");
    proxyAuthenticateHeader.addAutsParam("auts");

    // Act and Assert
    assertEquals("auts", proxyAuthenticateHeader.getAutsParam());
  }

  @Test
  void testGetAutsParam6() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addParameter("", "42");

    // Act and Assert
    assertNull(proxyAuthenticateHeader.getAutsParam());
  }

  @Test
  void testGetAutsParam7() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addUnquotedParameter("auts", "");

    // Act and Assert
    assertEquals("", proxyAuthenticateHeader.getAutsParam());
  }

  @Test
  void testGetCnonceParam() {
    // Arrange, Act and Assert
    assertNull((new ProxyAuthenticateHeader("42")).getCnonceParam());
    assertNull((new ProxyAuthenticateHeader("")).getCnonceParam());
    assertNull((new ProxyAuthenticateHeader("Auth scheme", new Vector(1))).getCnonceParam());
  }

  @Test
  void testGetCnonceParam2() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addAutsParam("cnonce");

    // Act and Assert
    assertNull(proxyAuthenticateHeader.getCnonceParam());
  }

  @Test
  void testGetCnonceParam3() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addCnonceParam("cnonce");

    // Act and Assert
    assertEquals("cnonce", proxyAuthenticateHeader.getCnonceParam());
  }

  @Test
  void testGetCnonceParam4() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addUnquotedParameter("cnonce", "42");

    // Act and Assert
    assertEquals("42", proxyAuthenticateHeader.getCnonceParam());
  }

  @Test
  void testGetCnonceParam5() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addAutsParam("cnonce");
    proxyAuthenticateHeader.addAutsParam("cnonce");

    // Act and Assert
    assertNull(proxyAuthenticateHeader.getCnonceParam());
  }

  @Test
  void testGetCnonceParam6() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addCnonceParam("cnonce");
    proxyAuthenticateHeader.addAutsParam("cnonce");

    // Act and Assert
    assertEquals("cnonce", proxyAuthenticateHeader.getCnonceParam());
  }

  @Test
  void testGetCnonceParam7() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addParameter("", "42");

    // Act and Assert
    assertNull(proxyAuthenticateHeader.getCnonceParam());
  }

  @Test
  void testGetCnonceParam8() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addUnquotedParameter("cnonce", "");

    // Act and Assert
    assertEquals("", proxyAuthenticateHeader.getCnonceParam());
  }

  @Test
  void testGetNcParam() {
    // Arrange, Act and Assert
    assertNull((new ProxyAuthenticateHeader("42")).getNcParam());
    assertNull((new ProxyAuthenticateHeader("")).getNcParam());
    assertNull((new ProxyAuthenticateHeader("Auth scheme", new Vector(1))).getNcParam());
  }

  @Test
  void testGetNcParam2() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addAutsParam("nc");

    // Act and Assert
    assertNull(proxyAuthenticateHeader.getNcParam());
  }

  @Test
  void testGetNcParam3() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addParameter("nc", "42");

    // Act and Assert
    assertEquals("42", proxyAuthenticateHeader.getNcParam());
  }

  @Test
  void testGetNcParam4() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addQuotedParameter("nc", "42");

    // Act and Assert
    assertEquals("42", proxyAuthenticateHeader.getNcParam());
  }

  @Test
  void testGetNcParam5() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addAutsParam("nc");
    proxyAuthenticateHeader.addAutsParam("nc");

    // Act and Assert
    assertNull(proxyAuthenticateHeader.getNcParam());
  }

  @Test
  void testGetNcParam6() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addParameter("nc", "42");
    proxyAuthenticateHeader.addAutsParam("nc");

    // Act and Assert
    assertEquals("42", proxyAuthenticateHeader.getNcParam());
  }

  @Test
  void testGetNcParam7() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addParameter("", "42");

    // Act and Assert
    assertNull(proxyAuthenticateHeader.getNcParam());
  }

  @Test
  void testGetNcParam8() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addParameter("nc", "");

    // Act and Assert
    assertEquals("", proxyAuthenticateHeader.getNcParam());
  }

  @Test
  void testGetNextnonceParam() {
    // Arrange, Act and Assert
    assertNull((new ProxyAuthenticateHeader("42")).getNextnonceParam());
    assertNull((new ProxyAuthenticateHeader("")).getNextnonceParam());
    assertNull((new ProxyAuthenticateHeader("Auth scheme", new Vector(1))).getNextnonceParam());
  }

  @Test
  void testGetNextnonceParam2() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addAutsParam("nextnonce");

    // Act and Assert
    assertNull(proxyAuthenticateHeader.getNextnonceParam());
  }

  @Test
  void testGetNextnonceParam3() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addParameter("nextnonce", "42");

    // Act and Assert
    assertEquals("42", proxyAuthenticateHeader.getNextnonceParam());
  }

  @Test
  void testGetNextnonceParam4() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addUnquotedParameter("nextnonce", "42");

    // Act and Assert
    assertEquals("42", proxyAuthenticateHeader.getNextnonceParam());
  }

  @Test
  void testGetNextnonceParam5() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addAutsParam("nextnonce");
    proxyAuthenticateHeader.addAutsParam("nextnonce");

    // Act and Assert
    assertNull(proxyAuthenticateHeader.getNextnonceParam());
  }

  @Test
  void testGetNextnonceParam6() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addParameter("nextnonce", "42");
    proxyAuthenticateHeader.addAutsParam("nextnonce");

    // Act and Assert
    assertEquals("42", proxyAuthenticateHeader.getNextnonceParam());
  }

  @Test
  void testGetNextnonceParam7() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addParameter("", "42");

    // Act and Assert
    assertNull(proxyAuthenticateHeader.getNextnonceParam());
  }

  @Test
  void testGetNextnonceParam8() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addUnquotedParameter("nextnonce", "");

    // Act and Assert
    assertEquals("", proxyAuthenticateHeader.getNextnonceParam());
  }

  @Test
  void testGetNonceParam() {
    // Arrange, Act and Assert
    assertNull((new ProxyAuthenticateHeader("42")).getNonceParam());
    assertNull((new ProxyAuthenticateHeader("")).getNonceParam());
    assertNull((new ProxyAuthenticateHeader("Auth scheme", new Vector(1))).getNonceParam());
  }

  @Test
  void testGetNonceParam2() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addAutsParam("nonce");

    // Act and Assert
    assertNull(proxyAuthenticateHeader.getNonceParam());
  }

  @Test
  void testGetNonceParam3() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addParameter("nonce", "42");

    // Act and Assert
    assertEquals("42", proxyAuthenticateHeader.getNonceParam());
  }

  @Test
  void testGetNonceParam4() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addUnquotedParameter("nonce", "42");

    // Act and Assert
    assertEquals("42", proxyAuthenticateHeader.getNonceParam());
  }

  @Test
  void testGetNonceParam5() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addAutsParam("nonce");
    proxyAuthenticateHeader.addAutsParam("nonce");

    // Act and Assert
    assertNull(proxyAuthenticateHeader.getNonceParam());
  }

  @Test
  void testGetNonceParam6() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addParameter("nonce", "42");
    proxyAuthenticateHeader.addAutsParam("nonce");

    // Act and Assert
    assertEquals("42", proxyAuthenticateHeader.getNonceParam());
  }

  @Test
  void testGetNonceParam7() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addParameter("", "42");

    // Act and Assert
    assertNull(proxyAuthenticateHeader.getNonceParam());
  }

  @Test
  void testGetNonceParam8() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addUnquotedParameter("nonce", "");

    // Act and Assert
    assertEquals("", proxyAuthenticateHeader.getNonceParam());
  }

  @Test
  void testGetOpaqueParam() {
    // Arrange, Act and Assert
    assertNull((new ProxyAuthenticateHeader("42")).getOpaqueParam());
    assertNull((new ProxyAuthenticateHeader("")).getOpaqueParam());
    assertNull((new ProxyAuthenticateHeader("Auth scheme", new Vector(1))).getOpaqueParam());
  }

  @Test
  void testGetOpaqueParam2() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addAutsParam("opaque");

    // Act and Assert
    assertNull(proxyAuthenticateHeader.getOpaqueParam());
  }

  @Test
  void testGetOpaqueParam3() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addParameter("opaque", "42");

    // Act and Assert
    assertEquals("42", proxyAuthenticateHeader.getOpaqueParam());
  }

  @Test
  void testGetOpaqueParam4() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addUnquotedParameter("opaque", "42");

    // Act and Assert
    assertEquals("42", proxyAuthenticateHeader.getOpaqueParam());
  }

  @Test
  void testGetOpaqueParam5() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addAutsParam("opaque");
    proxyAuthenticateHeader.addAutsParam("opaque");

    // Act and Assert
    assertNull(proxyAuthenticateHeader.getOpaqueParam());
  }

  @Test
  void testGetOpaqueParam6() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addParameter("opaque", "42");
    proxyAuthenticateHeader.addAutsParam("opaque");

    // Act and Assert
    assertEquals("42", proxyAuthenticateHeader.getOpaqueParam());
  }

  @Test
  void testGetOpaqueParam7() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addParameter("", "42");

    // Act and Assert
    assertNull(proxyAuthenticateHeader.getOpaqueParam());
  }

  @Test
  void testGetOpaqueParam8() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addUnquotedParameter("opaque", "");

    // Act and Assert
    assertEquals("", proxyAuthenticateHeader.getOpaqueParam());
  }

  @Test
  void testGetParameter() {
    // Arrange, Act and Assert
    assertNull((new ProxyAuthenticateHeader("42")).getParameter("Param name"));
    assertNull((new ProxyAuthenticateHeader("")).getParameter("Param name"));
    assertNull((new ProxyAuthenticateHeader("Auth scheme", new Vector(1))).getParameter("Param name"));
  }

  @Test
  void testGetParameter2() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addAutsParam("Unquoted auts");

    // Act and Assert
    assertNull(proxyAuthenticateHeader.getParameter("Param name"));
  }

  @Test
  void testGetParameter3() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addAutsParam("auts");
    proxyAuthenticateHeader.addAutsParam("Unquoted auts");

    // Act and Assert
    assertNull(proxyAuthenticateHeader.getParameter("Param name"));
  }

  @Test
  void testGetParameter4() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addParameter("", "42");

    // Act and Assert
    assertNull(proxyAuthenticateHeader.getParameter("Param name"));
  }

  @Test
  void testGetParameter5() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("");
    proxyAuthenticateHeader.addAutsParam("Unquoted auts");

    // Act and Assert
    assertNull(proxyAuthenticateHeader.getParameter("Param name"));
  }

  @Test
  void testGetParameterNames() {
    // Arrange, Act and Assert
    assertTrue((new ProxyAuthenticateHeader("42")).getParameterNames().isEmpty());
    assertTrue((new ProxyAuthenticateHeader("")).getParameterNames().isEmpty());
  }

  @Test
  void testGetParameterNames2() {
    // Arrange and Act
    Vector actualParameterNames = (new ProxyAuthenticateHeader("Auth scheme", new Vector(1))).getParameterNames();

    // Assert
    assertEquals(1, actualParameterNames.size());
    assertEquals("scheme", actualParameterNames.get(0));
  }

  @Test
  void testGetParameterNames3() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addAutsParam("Unquoted auts");

    // Act
    Vector actualParameterNames = proxyAuthenticateHeader.getParameterNames();

    // Assert
    assertEquals(1, actualParameterNames.size());
    assertEquals("auts", actualParameterNames.get(0));
  }

  @Test
  void testGetParameterNames4() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addAutsParam("Unquoted auts");
    proxyAuthenticateHeader.addAutsParam("Unquoted auts");

    // Act
    Vector actualParameterNames = proxyAuthenticateHeader.getParameterNames();

    // Assert
    assertEquals(2, actualParameterNames.size());
    assertEquals("auts", actualParameterNames.get(0));
    assertEquals("auts", actualParameterNames.get(1));
  }

  @Test
  void testGetParameterNames5() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addParameter("", "42");

    // Act
    Vector actualParameterNames = proxyAuthenticateHeader.getParameterNames();

    // Assert
    assertEquals(1, actualParameterNames.size());
    assertEquals("42", actualParameterNames.get(0));
  }

  @Test
  void testGetParameterNames6() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("");
    proxyAuthenticateHeader.addAutsParam("Unquoted auts");

    // Act
    Vector actualParameterNames = proxyAuthenticateHeader.getParameterNames();

    // Assert
    assertEquals(1, actualParameterNames.size());
    assertEquals("auts\"", actualParameterNames.get(0));
  }

  @Test
  void testGetQopOptionsParam() {
    // Arrange, Act and Assert
    assertNull((new ProxyAuthenticateHeader("42")).getQopOptionsParam());
    assertNull((new ProxyAuthenticateHeader("")).getQopOptionsParam());
    assertNull((new ProxyAuthenticateHeader("Auth scheme", new Vector(1))).getQopOptionsParam());
  }

  @Test
  void testGetQopOptionsParam2() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addAutsParam("qop");

    // Act and Assert
    assertNull(proxyAuthenticateHeader.getQopOptionsParam());
  }

  @Test
  void testGetQopOptionsParam3() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addParameter("qop", "42");

    // Act and Assert
    assertEquals("42", proxyAuthenticateHeader.getQopOptionsParam());
  }

  @Test
  void testGetQopOptionsParam4() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addQuotedParameter("qop", "42");

    // Act and Assert
    assertEquals("42", proxyAuthenticateHeader.getQopOptionsParam());
  }

  @Test
  void testGetQopOptionsParam5() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addAutsParam("qop");
    proxyAuthenticateHeader.addAutsParam("qop");

    // Act and Assert
    assertNull(proxyAuthenticateHeader.getQopOptionsParam());
  }

  @Test
  void testGetQopOptionsParam6() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addParameter("qop", "42");
    proxyAuthenticateHeader.addAutsParam("qop");

    // Act and Assert
    assertEquals("42", proxyAuthenticateHeader.getQopOptionsParam());
  }

  @Test
  void testGetQopOptionsParam7() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addParameter("", "42");

    // Act and Assert
    assertNull(proxyAuthenticateHeader.getQopOptionsParam());
  }

  @Test
  void testGetQopOptionsParam8() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addParameter("qop", "");

    // Act and Assert
    assertEquals("", proxyAuthenticateHeader.getQopOptionsParam());
  }

  @Test
  void testGetQopParam() {
    // Arrange, Act and Assert
    assertNull((new ProxyAuthenticateHeader("42")).getQopParam());
    assertNull((new ProxyAuthenticateHeader("")).getQopParam());
    assertNull((new ProxyAuthenticateHeader("Auth scheme", new Vector(1))).getQopParam());
  }

  @Test
  void testGetQopParam2() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addAutsParam("qop");

    // Act and Assert
    assertNull(proxyAuthenticateHeader.getQopParam());
  }

  @Test
  void testGetQopParam3() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addParameter("qop", "42");

    // Act and Assert
    assertEquals("42", proxyAuthenticateHeader.getQopParam());
  }

  @Test
  void testGetQopParam4() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addQuotedParameter("qop", "42");

    // Act and Assert
    assertEquals("42", proxyAuthenticateHeader.getQopParam());
  }

  @Test
  void testGetQopParam5() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addAutsParam("qop");
    proxyAuthenticateHeader.addAutsParam("qop");

    // Act and Assert
    assertNull(proxyAuthenticateHeader.getQopParam());
  }

  @Test
  void testGetQopParam6() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addParameter("qop", "42");
    proxyAuthenticateHeader.addAutsParam("qop");

    // Act and Assert
    assertEquals("42", proxyAuthenticateHeader.getQopParam());
  }

  @Test
  void testGetQopParam7() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addParameter("", "42");

    // Act and Assert
    assertNull(proxyAuthenticateHeader.getQopParam());
  }

  @Test
  void testGetQopParam8() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addParameter("qop", "");

    // Act and Assert
    assertEquals("", proxyAuthenticateHeader.getQopParam());
  }

  @Test
  void testGetRealmParam() {
    // Arrange, Act and Assert
    assertNull((new ProxyAuthenticateHeader("42")).getRealmParam());
    assertNull((new ProxyAuthenticateHeader("")).getRealmParam());
    assertNull((new ProxyAuthenticateHeader("Auth scheme", new Vector(1))).getRealmParam());
  }

  @Test
  void testGetRealmParam2() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addAutsParam("realm");

    // Act and Assert
    assertNull(proxyAuthenticateHeader.getRealmParam());
  }

  @Test
  void testGetRealmParam3() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addParameter("realm", "42");

    // Act and Assert
    assertEquals("42", proxyAuthenticateHeader.getRealmParam());
  }

  @Test
  void testGetRealmParam4() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addUnquotedParameter("realm", "42");

    // Act and Assert
    assertEquals("42", proxyAuthenticateHeader.getRealmParam());
  }

  @Test
  void testGetRealmParam5() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addAutsParam("realm");
    proxyAuthenticateHeader.addAutsParam("realm");

    // Act and Assert
    assertNull(proxyAuthenticateHeader.getRealmParam());
  }

  @Test
  void testGetRealmParam6() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addParameter("realm", "42");
    proxyAuthenticateHeader.addAutsParam("realm");

    // Act and Assert
    assertEquals("42", proxyAuthenticateHeader.getRealmParam());
  }

  @Test
  void testGetRealmParam7() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addParameter("", "42");

    // Act and Assert
    assertNull(proxyAuthenticateHeader.getRealmParam());
  }

  @Test
  void testGetRealmParam8() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addUnquotedParameter("realm", "");

    // Act and Assert
    assertEquals("", proxyAuthenticateHeader.getRealmParam());
  }

  @Test
  void testGetResponseParam() {
    // Arrange, Act and Assert
    assertNull((new ProxyAuthenticateHeader("42")).getResponseParam());
    assertNull((new ProxyAuthenticateHeader("")).getResponseParam());
    assertNull((new ProxyAuthenticateHeader("Auth scheme", new Vector(1))).getResponseParam());
  }

  @Test
  void testGetResponseParam2() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addAutsParam("response");

    // Act and Assert
    assertNull(proxyAuthenticateHeader.getResponseParam());
  }

  @Test
  void testGetResponseParam3() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addParameter("response", "42");

    // Act and Assert
    assertEquals("42", proxyAuthenticateHeader.getResponseParam());
  }

  @Test
  void testGetResponseParam4() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addUnquotedParameter("response", "42");

    // Act and Assert
    assertEquals("42", proxyAuthenticateHeader.getResponseParam());
  }

  @Test
  void testGetResponseParam5() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addAutsParam("response");
    proxyAuthenticateHeader.addAutsParam("response");

    // Act and Assert
    assertNull(proxyAuthenticateHeader.getResponseParam());
  }

  @Test
  void testGetResponseParam6() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addParameter("response", "42");
    proxyAuthenticateHeader.addAutsParam("response");

    // Act and Assert
    assertEquals("42", proxyAuthenticateHeader.getResponseParam());
  }

  @Test
  void testGetResponseParam7() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addParameter("", "42");

    // Act and Assert
    assertNull(proxyAuthenticateHeader.getResponseParam());
  }

  @Test
  void testGetResponseParam8() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addUnquotedParameter("response", "");

    // Act and Assert
    assertEquals("", proxyAuthenticateHeader.getResponseParam());
  }

  @Test
  void testGetRspauthParam() {
    // Arrange, Act and Assert
    assertNull((new ProxyAuthenticateHeader("42")).getRspauthParam());
    assertNull((new ProxyAuthenticateHeader("")).getRspauthParam());
    assertNull((new ProxyAuthenticateHeader("Auth scheme", new Vector(1))).getRspauthParam());
  }

  @Test
  void testGetRspauthParam2() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addAutsParam("rspauth");

    // Act and Assert
    assertNull(proxyAuthenticateHeader.getRspauthParam());
  }

  @Test
  void testGetRspauthParam3() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addParameter("rspauth", "42");

    // Act and Assert
    assertEquals("42", proxyAuthenticateHeader.getRspauthParam());
  }

  @Test
  void testGetRspauthParam4() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addUnquotedParameter("rspauth", "42");

    // Act and Assert
    assertEquals("42", proxyAuthenticateHeader.getRspauthParam());
  }

  @Test
  void testGetRspauthParam5() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addAutsParam("rspauth");
    proxyAuthenticateHeader.addAutsParam("rspauth");

    // Act and Assert
    assertNull(proxyAuthenticateHeader.getRspauthParam());
  }

  @Test
  void testGetRspauthParam6() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addParameter("rspauth", "42");
    proxyAuthenticateHeader.addAutsParam("rspauth");

    // Act and Assert
    assertEquals("42", proxyAuthenticateHeader.getRspauthParam());
  }

  @Test
  void testGetRspauthParam7() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addParameter("", "42");

    // Act and Assert
    assertNull(proxyAuthenticateHeader.getRspauthParam());
  }

  @Test
  void testGetRspauthParam8() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addUnquotedParameter("rspauth", "");

    // Act and Assert
    assertEquals("", proxyAuthenticateHeader.getRspauthParam());
  }

  @Test
  void testGetUriParam() {
    // Arrange, Act and Assert
    assertNull((new ProxyAuthenticateHeader("42")).getUriParam());
    assertNull((new ProxyAuthenticateHeader("")).getUriParam());
    assertNull((new ProxyAuthenticateHeader("Auth scheme", new Vector(1))).getUriParam());
  }

  @Test
  void testGetUriParam2() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addAutsParam("uri");

    // Act and Assert
    assertNull(proxyAuthenticateHeader.getUriParam());
  }

  @Test
  void testGetUriParam3() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addParameter("uri", "42");

    // Act and Assert
    assertEquals("42", proxyAuthenticateHeader.getUriParam());
  }

  @Test
  void testGetUriParam4() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addUnquotedParameter("uri", "42");

    // Act and Assert
    assertEquals("42", proxyAuthenticateHeader.getUriParam());
  }

  @Test
  void testGetUriParam5() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addAutsParam("uri");
    proxyAuthenticateHeader.addAutsParam("uri");

    // Act and Assert
    assertNull(proxyAuthenticateHeader.getUriParam());
  }

  @Test
  void testGetUriParam6() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addParameter("uri", "42");
    proxyAuthenticateHeader.addAutsParam("uri");

    // Act and Assert
    assertEquals("42", proxyAuthenticateHeader.getUriParam());
  }

  @Test
  void testGetUriParam7() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addParameter("", "42");

    // Act and Assert
    assertNull(proxyAuthenticateHeader.getUriParam());
  }

  @Test
  void testGetUriParam8() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addUnquotedParameter("uri", "");

    // Act and Assert
    assertEquals("", proxyAuthenticateHeader.getUriParam());
  }

  @Test
  void testGetUsernameParam() {
    // Arrange, Act and Assert
    assertNull((new ProxyAuthenticateHeader("42")).getUsernameParam());
    assertNull((new ProxyAuthenticateHeader("")).getUsernameParam());
    assertNull((new ProxyAuthenticateHeader("Auth scheme", new Vector(1))).getUsernameParam());
  }

  @Test
  void testGetUsernameParam2() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addAutsParam("username");

    // Act and Assert
    assertNull(proxyAuthenticateHeader.getUsernameParam());
  }

  @Test
  void testGetUsernameParam3() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addParameter("username", "42");

    // Act and Assert
    assertEquals("42", proxyAuthenticateHeader.getUsernameParam());
  }

  @Test
  void testGetUsernameParam4() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addUnquotedParameter("username", "42");

    // Act and Assert
    assertEquals("42", proxyAuthenticateHeader.getUsernameParam());
  }

  @Test
  void testGetUsernameParam5() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addAutsParam("username");
    proxyAuthenticateHeader.addAutsParam("username");

    // Act and Assert
    assertNull(proxyAuthenticateHeader.getUsernameParam());
  }

  @Test
  void testGetUsernameParam6() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addParameter("username", "42");
    proxyAuthenticateHeader.addAutsParam("username");

    // Act and Assert
    assertEquals("42", proxyAuthenticateHeader.getUsernameParam());
  }

  @Test
  void testGetUsernameParam7() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addParameter("", "42");

    // Act and Assert
    assertNull(proxyAuthenticateHeader.getUsernameParam());
  }

  @Test
  void testGetUsernameParam8() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addUnquotedParameter("username", "");

    // Act and Assert
    assertEquals("", proxyAuthenticateHeader.getUsernameParam());
  }

  @Test
  void testHasAlgorithmParam() {
    // Arrange, Act and Assert
    assertFalse((new ProxyAuthenticateHeader("42")).hasAlgorithmParam());
    assertFalse((new ProxyAuthenticateHeader("")).hasAlgorithmParam());
    assertFalse((new ProxyAuthenticateHeader("Auth scheme", new Vector(1))).hasAlgorithmParam());
  }

  @Test
  void testHasAlgorithmParam2() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addAutsParam("algorithm");

    // Act and Assert
    assertFalse(proxyAuthenticateHeader.hasAlgorithmParam());
  }

  @Test
  void testHasAlgorithmParam3() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addParameter("algorithm", "42");

    // Act and Assert
    assertTrue(proxyAuthenticateHeader.hasAlgorithmParam());
  }

  @Test
  void testHasAlgorithmParam4() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addAutsParam("algorithm");
    proxyAuthenticateHeader.addAutsParam("algorithm");

    // Act and Assert
    assertFalse(proxyAuthenticateHeader.hasAlgorithmParam());
  }

  @Test
  void testHasAlgorithmParam5() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addParameter("", "42");

    // Act and Assert
    assertFalse(proxyAuthenticateHeader.hasAlgorithmParam());
  }

  @Test
  void testHasAutsParam() {
    // Arrange, Act and Assert
    assertFalse((new ProxyAuthenticateHeader("42")).hasAutsParam());
    assertFalse((new ProxyAuthenticateHeader("")).hasAutsParam());
    assertFalse((new ProxyAuthenticateHeader("Auth scheme", new Vector(1))).hasAutsParam());
  }

  @Test
  void testHasAutsParam2() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addAutsParam("auts");

    // Act and Assert
    assertTrue(proxyAuthenticateHeader.hasAutsParam());
  }

  @Test
  void testHasAutsParam3() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addCnonceParam("auts");

    // Act and Assert
    assertFalse(proxyAuthenticateHeader.hasAutsParam());
  }

  @Test
  void testHasAutsParam4() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addCnonceParam("auts");
    proxyAuthenticateHeader.addAutsParam("auts");

    // Act and Assert
    assertTrue(proxyAuthenticateHeader.hasAutsParam());
  }

  @Test
  void testHasAutsParam5() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addParameter("", "42");

    // Act and Assert
    assertFalse(proxyAuthenticateHeader.hasAutsParam());
  }

  @Test
  void testHasCnonceParam() {
    // Arrange, Act and Assert
    assertFalse((new ProxyAuthenticateHeader("42")).hasCnonceParam());
    assertFalse((new ProxyAuthenticateHeader("")).hasCnonceParam());
    assertFalse((new ProxyAuthenticateHeader("Auth scheme", new Vector(1))).hasCnonceParam());
  }

  @Test
  void testHasCnonceParam2() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addAutsParam("cnonce");

    // Act and Assert
    assertFalse(proxyAuthenticateHeader.hasCnonceParam());
  }

  @Test
  void testHasCnonceParam3() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addCnonceParam("cnonce");

    // Act and Assert
    assertTrue(proxyAuthenticateHeader.hasCnonceParam());
  }

  @Test
  void testHasCnonceParam4() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addAutsParam("cnonce");
    proxyAuthenticateHeader.addAutsParam("cnonce");

    // Act and Assert
    assertFalse(proxyAuthenticateHeader.hasCnonceParam());
  }

  @Test
  void testHasCnonceParam5() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addParameter("", "42");

    // Act and Assert
    assertFalse(proxyAuthenticateHeader.hasCnonceParam());
  }

  @Test
  void testHasNcParam() {
    // Arrange, Act and Assert
    assertFalse((new ProxyAuthenticateHeader("42")).hasNcParam());
    assertFalse((new ProxyAuthenticateHeader("")).hasNcParam());
    assertFalse((new ProxyAuthenticateHeader("Auth scheme", new Vector(1))).hasNcParam());
  }

  @Test
  void testHasNcParam2() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addAutsParam("nc");

    // Act and Assert
    assertFalse(proxyAuthenticateHeader.hasNcParam());
  }

  @Test
  void testHasNcParam3() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addParameter("nc", "42");

    // Act and Assert
    assertTrue(proxyAuthenticateHeader.hasNcParam());
  }

  @Test
  void testHasNcParam4() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addAutsParam("nc");
    proxyAuthenticateHeader.addAutsParam("nc");

    // Act and Assert
    assertFalse(proxyAuthenticateHeader.hasNcParam());
  }

  @Test
  void testHasNcParam5() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addParameter("", "42");

    // Act and Assert
    assertFalse(proxyAuthenticateHeader.hasNcParam());
  }

  @Test
  void testHasNextnonceParam() {
    // Arrange, Act and Assert
    assertFalse((new ProxyAuthenticateHeader("42")).hasNextnonceParam());
    assertFalse((new ProxyAuthenticateHeader("")).hasNextnonceParam());
    assertFalse((new ProxyAuthenticateHeader("Auth scheme", new Vector(1))).hasNextnonceParam());
  }

  @Test
  void testHasNextnonceParam2() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addAutsParam("nextnonce");

    // Act and Assert
    assertFalse(proxyAuthenticateHeader.hasNextnonceParam());
  }

  @Test
  void testHasNextnonceParam3() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addParameter("nextnonce", "42");

    // Act and Assert
    assertTrue(proxyAuthenticateHeader.hasNextnonceParam());
  }

  @Test
  void testHasNextnonceParam4() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addAutsParam("nextnonce");
    proxyAuthenticateHeader.addAutsParam("nextnonce");

    // Act and Assert
    assertFalse(proxyAuthenticateHeader.hasNextnonceParam());
  }

  @Test
  void testHasNextnonceParam5() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addParameter("", "42");

    // Act and Assert
    assertFalse(proxyAuthenticateHeader.hasNextnonceParam());
  }

  @Test
  void testHasNonceParam() {
    // Arrange, Act and Assert
    assertFalse((new ProxyAuthenticateHeader("42")).hasNonceParam());
    assertFalse((new ProxyAuthenticateHeader("")).hasNonceParam());
    assertFalse((new ProxyAuthenticateHeader("Auth scheme", new Vector(1))).hasNonceParam());
  }

  @Test
  void testHasNonceParam2() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addAutsParam("nonce");

    // Act and Assert
    assertFalse(proxyAuthenticateHeader.hasNonceParam());
  }

  @Test
  void testHasNonceParam3() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addParameter("nonce", "42");

    // Act and Assert
    assertTrue(proxyAuthenticateHeader.hasNonceParam());
  }

  @Test
  void testHasNonceParam4() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addAutsParam("nonce");
    proxyAuthenticateHeader.addAutsParam("nonce");

    // Act and Assert
    assertFalse(proxyAuthenticateHeader.hasNonceParam());
  }

  @Test
  void testHasNonceParam5() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addParameter("", "42");

    // Act and Assert
    assertFalse(proxyAuthenticateHeader.hasNonceParam());
  }

  @Test
  void testHasOpaqueParam() {
    // Arrange, Act and Assert
    assertFalse((new ProxyAuthenticateHeader("42")).hasOpaqueParam());
    assertFalse((new ProxyAuthenticateHeader("")).hasOpaqueParam());
    assertFalse((new ProxyAuthenticateHeader("Auth scheme", new Vector(1))).hasOpaqueParam());
  }

  @Test
  void testHasOpaqueParam2() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addAutsParam("opaque");

    // Act and Assert
    assertFalse(proxyAuthenticateHeader.hasOpaqueParam());
  }

  @Test
  void testHasOpaqueParam3() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addParameter("opaque", "42");

    // Act and Assert
    assertTrue(proxyAuthenticateHeader.hasOpaqueParam());
  }

  @Test
  void testHasOpaqueParam4() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addAutsParam("opaque");
    proxyAuthenticateHeader.addAutsParam("opaque");

    // Act and Assert
    assertFalse(proxyAuthenticateHeader.hasOpaqueParam());
  }

  @Test
  void testHasOpaqueParam5() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addParameter("", "42");

    // Act and Assert
    assertFalse(proxyAuthenticateHeader.hasOpaqueParam());
  }

  @Test
  void testHasParameter() {
    // Arrange, Act and Assert
    assertFalse((new ProxyAuthenticateHeader("42")).hasParameter("Param name"));
    assertFalse((new ProxyAuthenticateHeader("")).hasParameter("Param name"));
    assertFalse((new ProxyAuthenticateHeader("Auth scheme", new Vector(1))).hasParameter("Param name"));
  }

  @Test
  void testHasParameter2() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addAutsParam("Unquoted auts");

    // Act and Assert
    assertFalse(proxyAuthenticateHeader.hasParameter("Param name"));
  }

  @Test
  void testHasParameter3() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addAutsParam("auts");
    proxyAuthenticateHeader.addAutsParam("Unquoted auts");

    // Act and Assert
    assertFalse(proxyAuthenticateHeader.hasParameter("Param name"));
  }

  @Test
  void testHasParameter4() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addParameter("", "42");

    // Act and Assert
    assertFalse(proxyAuthenticateHeader.hasParameter("Param name"));
  }

  @Test
  void testHasParameter5() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("");
    proxyAuthenticateHeader.addAutsParam("Unquoted auts");

    // Act and Assert
    assertFalse(proxyAuthenticateHeader.hasParameter("Param name"));
  }

  @Test
  void testHasQopOptionsParam() {
    // Arrange, Act and Assert
    assertFalse((new ProxyAuthenticateHeader("42")).hasQopOptionsParam());
    assertFalse((new ProxyAuthenticateHeader("")).hasQopOptionsParam());
    assertFalse((new ProxyAuthenticateHeader("Auth scheme", new Vector(1))).hasQopOptionsParam());
  }

  @Test
  void testHasQopOptionsParam2() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addAutsParam("qop");

    // Act and Assert
    assertFalse(proxyAuthenticateHeader.hasQopOptionsParam());
  }

  @Test
  void testHasQopOptionsParam3() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addParameter("qop", "42");

    // Act and Assert
    assertTrue(proxyAuthenticateHeader.hasQopOptionsParam());
  }

  @Test
  void testHasQopOptionsParam4() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addAutsParam("qop");
    proxyAuthenticateHeader.addAutsParam("qop");

    // Act and Assert
    assertFalse(proxyAuthenticateHeader.hasQopOptionsParam());
  }

  @Test
  void testHasQopOptionsParam5() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addParameter("", "42");

    // Act and Assert
    assertFalse(proxyAuthenticateHeader.hasQopOptionsParam());
  }

  @Test
  void testHasQopParam() {
    // Arrange, Act and Assert
    assertFalse((new ProxyAuthenticateHeader("42")).hasQopParam());
    assertFalse((new ProxyAuthenticateHeader("")).hasQopParam());
    assertFalse((new ProxyAuthenticateHeader("Auth scheme", new Vector(1))).hasQopParam());
  }

  @Test
  void testHasQopParam2() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addAutsParam("qop");

    // Act and Assert
    assertFalse(proxyAuthenticateHeader.hasQopParam());
  }

  @Test
  void testHasQopParam3() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addParameter("qop", "42");

    // Act and Assert
    assertTrue(proxyAuthenticateHeader.hasQopParam());
  }

  @Test
  void testHasQopParam4() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addAutsParam("qop");
    proxyAuthenticateHeader.addAutsParam("qop");

    // Act and Assert
    assertFalse(proxyAuthenticateHeader.hasQopParam());
  }

  @Test
  void testHasQopParam5() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addParameter("", "42");

    // Act and Assert
    assertFalse(proxyAuthenticateHeader.hasQopParam());
  }

  @Test
  void testHasRealmParam() {
    // Arrange, Act and Assert
    assertFalse((new ProxyAuthenticateHeader("42")).hasRealmParam());
    assertFalse((new ProxyAuthenticateHeader("")).hasRealmParam());
    assertFalse((new ProxyAuthenticateHeader("Auth scheme", new Vector(1))).hasRealmParam());
  }

  @Test
  void testHasRealmParam2() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addAutsParam("realm");

    // Act and Assert
    assertFalse(proxyAuthenticateHeader.hasRealmParam());
  }

  @Test
  void testHasRealmParam3() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addParameter("realm", "42");

    // Act and Assert
    assertTrue(proxyAuthenticateHeader.hasRealmParam());
  }

  @Test
  void testHasRealmParam4() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addAutsParam("realm");
    proxyAuthenticateHeader.addAutsParam("realm");

    // Act and Assert
    assertFalse(proxyAuthenticateHeader.hasRealmParam());
  }

  @Test
  void testHasRealmParam5() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addParameter("", "42");

    // Act and Assert
    assertFalse(proxyAuthenticateHeader.hasRealmParam());
  }

  @Test
  void testHasResponseParam() {
    // Arrange, Act and Assert
    assertFalse((new ProxyAuthenticateHeader("42")).hasResponseParam());
    assertFalse((new ProxyAuthenticateHeader("")).hasResponseParam());
    assertFalse((new ProxyAuthenticateHeader("Auth scheme", new Vector(1))).hasResponseParam());
  }

  @Test
  void testHasResponseParam2() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addAutsParam("response");

    // Act and Assert
    assertFalse(proxyAuthenticateHeader.hasResponseParam());
  }

  @Test
  void testHasResponseParam3() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addParameter("response", "42");

    // Act and Assert
    assertTrue(proxyAuthenticateHeader.hasResponseParam());
  }

  @Test
  void testHasResponseParam4() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addAutsParam("response");
    proxyAuthenticateHeader.addAutsParam("response");

    // Act and Assert
    assertFalse(proxyAuthenticateHeader.hasResponseParam());
  }

  @Test
  void testHasResponseParam5() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addParameter("", "42");

    // Act and Assert
    assertFalse(proxyAuthenticateHeader.hasResponseParam());
  }

  @Test
  void testHasRspauthParam() {
    // Arrange, Act and Assert
    assertFalse((new ProxyAuthenticateHeader("42")).hasRspauthParam());
    assertFalse((new ProxyAuthenticateHeader("")).hasRspauthParam());
    assertFalse((new ProxyAuthenticateHeader("Auth scheme", new Vector(1))).hasRspauthParam());
  }

  @Test
  void testHasRspauthParam2() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addAutsParam("rspauth");

    // Act and Assert
    assertFalse(proxyAuthenticateHeader.hasRspauthParam());
  }

  @Test
  void testHasRspauthParam3() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addParameter("rspauth", "42");

    // Act and Assert
    assertTrue(proxyAuthenticateHeader.hasRspauthParam());
  }

  @Test
  void testHasRspauthParam4() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addAutsParam("rspauth");
    proxyAuthenticateHeader.addAutsParam("rspauth");

    // Act and Assert
    assertFalse(proxyAuthenticateHeader.hasRspauthParam());
  }

  @Test
  void testHasRspauthParam5() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addParameter("", "42");

    // Act and Assert
    assertFalse(proxyAuthenticateHeader.hasRspauthParam());
  }

  @Test
  void testHasUriParam() {
    // Arrange, Act and Assert
    assertFalse((new ProxyAuthenticateHeader("42")).hasUriParam());
    assertFalse((new ProxyAuthenticateHeader("")).hasUriParam());
    assertFalse((new ProxyAuthenticateHeader("Auth scheme", new Vector(1))).hasUriParam());
  }

  @Test
  void testHasUriParam2() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addAutsParam("uri");

    // Act and Assert
    assertFalse(proxyAuthenticateHeader.hasUriParam());
  }

  @Test
  void testHasUriParam3() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addParameter("uri", "42");

    // Act and Assert
    assertTrue(proxyAuthenticateHeader.hasUriParam());
  }

  @Test
  void testHasUriParam4() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addAutsParam("uri");
    proxyAuthenticateHeader.addAutsParam("uri");

    // Act and Assert
    assertFalse(proxyAuthenticateHeader.hasUriParam());
  }

  @Test
  void testHasUriParam5() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addParameter("", "42");

    // Act and Assert
    assertFalse(proxyAuthenticateHeader.hasUriParam());
  }

  @Test
  void testHasUsernameParam() {
    // Arrange, Act and Assert
    assertFalse((new ProxyAuthenticateHeader("42")).hasUsernameParam());
    assertFalse((new ProxyAuthenticateHeader("")).hasUsernameParam());
    assertFalse((new ProxyAuthenticateHeader("Auth scheme", new Vector(1))).hasUsernameParam());
  }

  @Test
  void testHasUsernameParam2() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addAutsParam("username");

    // Act and Assert
    assertFalse(proxyAuthenticateHeader.hasUsernameParam());
  }

  @Test
  void testHasUsernameParam3() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addParameter("username", "42");

    // Act and Assert
    assertTrue(proxyAuthenticateHeader.hasUsernameParam());
  }

  @Test
  void testHasUsernameParam4() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addAutsParam("username");
    proxyAuthenticateHeader.addAutsParam("username");

    // Act and Assert
    assertFalse(proxyAuthenticateHeader.hasUsernameParam());
  }

  @Test
  void testHasUsernameParam5() {
    // Arrange
    ProxyAuthenticateHeader proxyAuthenticateHeader = new ProxyAuthenticateHeader("42");
    proxyAuthenticateHeader.addParameter("", "42");

    // Act and Assert
    assertFalse(proxyAuthenticateHeader.hasUsernameParam());
  }
}

