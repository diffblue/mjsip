package org.mjsip.sip.header;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Vector;
import org.junit.jupiter.api.Test;

class AuthorizationHeaderDiffblueTest {
  @Test
  void testAddAlgorithParam() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");

    // Act
    authorizationHeader.addAlgorithParam("Algorithm");

    // Assert
    assertEquals("42 algorithm=Algorithm", authorizationHeader.getValue());
  }

  @Test
  void testAddAlgorithParam2() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addAutsParam("algorithm");

    // Act
    authorizationHeader.addAlgorithParam("Algorithm");

    // Assert
    assertEquals("42 auts=\"algorithm\", algorithm=Algorithm", authorizationHeader.getValue());
  }

  @Test
  void testAddAutsParam() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");

    // Act
    authorizationHeader.addAutsParam("Unquoted auts");

    // Assert
    assertEquals("42 auts=\"Unquoted auts\"", authorizationHeader.getValue());
  }

  @Test
  void testAddAutsParam2() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addAutsParam("auts");

    // Act
    authorizationHeader.addAutsParam("Unquoted auts");

    // Assert
    assertEquals("42 auts=\"auts\", auts=\"Unquoted auts\"", authorizationHeader.getValue());
  }

  @Test
  void testAddCnonceParam() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");

    // Act
    authorizationHeader.addCnonceParam("Unquoted cnonce");

    // Assert
    assertEquals("42 cnonce=\"Unquoted cnonce\"", authorizationHeader.getValue());
  }

  @Test
  void testAddCnonceParam2() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addAutsParam("cnonce");

    // Act
    authorizationHeader.addCnonceParam("Unquoted cnonce");

    // Assert
    assertEquals("42 auts=\"cnonce\", cnonce=\"Unquoted cnonce\"", authorizationHeader.getValue());
  }

  @Test
  void testAddNcParam() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");

    // Act
    authorizationHeader.addNcParam("Nc");

    // Assert
    assertEquals("42 nc=Nc", authorizationHeader.getValue());
  }

  @Test
  void testAddNcParam2() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addAutsParam("nc");

    // Act
    authorizationHeader.addNcParam("Nc");

    // Assert
    assertEquals("42 auts=\"nc\", nc=Nc", authorizationHeader.getValue());
  }

  @Test
  void testAddNextnonceParam() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");

    // Act
    authorizationHeader.addNextnonceParam("Unquoted nextnonce");

    // Assert
    assertEquals("42 nextnonce=\"Unquoted nextnonce\"", authorizationHeader.getValue());
  }

  @Test
  void testAddNextnonceParam2() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addAutsParam("nextnonce");

    // Act
    authorizationHeader.addNextnonceParam("Unquoted nextnonce");

    // Assert
    assertEquals("42 auts=\"nextnonce\", nextnonce=\"Unquoted nextnonce\"", authorizationHeader.getValue());
  }

  @Test
  void testAddNonceParam() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");

    // Act
    authorizationHeader.addNonceParam("Unquoted nonce");

    // Assert
    assertEquals("42 nonce=\"Unquoted nonce\"", authorizationHeader.getValue());
  }

  @Test
  void testAddNonceParam2() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addAutsParam("nonce");

    // Act
    authorizationHeader.addNonceParam("Unquoted nonce");

    // Assert
    assertEquals("42 auts=\"nonce\", nonce=\"Unquoted nonce\"", authorizationHeader.getValue());
  }

  @Test
  void testAddOpaqueParam() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");

    // Act
    authorizationHeader.addOpaqueParam("Unquoted opaque");

    // Assert
    assertEquals("42 opaque=\"Unquoted opaque\"", authorizationHeader.getValue());
  }

  @Test
  void testAddOpaqueParam2() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addAutsParam("opaque");

    // Act
    authorizationHeader.addOpaqueParam("Unquoted opaque");

    // Assert
    assertEquals("42 auts=\"opaque\", opaque=\"Unquoted opaque\"", authorizationHeader.getValue());
  }

  @Test
  void testAddParameter() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");

    // Act
    authorizationHeader.addParameter("Param name", "42");

    // Assert
    assertEquals("42 Param name=42", authorizationHeader.getValue());
  }

  @Test
  void testAddParameter2() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addAutsParam("Unquoted auts");

    // Act
    authorizationHeader.addParameter("Param name", "42");

    // Assert
    assertEquals("42 auts=\"Unquoted auts\", Param name=42", authorizationHeader.getValue());
  }

  @Test
  void testAddParameter3() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");

    // Act
    authorizationHeader.addParameter("auts", "42");

    // Assert
    assertEquals("42 auts=\"42\"", authorizationHeader.getValue());
  }

  @Test
  void testAddParameter4() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addAutsParam("Unquoted auts");

    // Act
    authorizationHeader.addParameter("auts", "42");

    // Assert
    assertEquals("42 auts=\"Unquoted auts\", auts=\"42\"", authorizationHeader.getValue());
  }

  @Test
  void testAddQopOptionsParam() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");

    // Act
    authorizationHeader.addQopOptionsParam("Unquoted qop options");

    // Assert
    assertEquals("42 qop=\"Unquoted qop options\"", authorizationHeader.getValue());
  }

  @Test
  void testAddQopOptionsParam2() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addAutsParam("qop");

    // Act
    authorizationHeader.addQopOptionsParam("Unquoted qop options");

    // Assert
    assertEquals("42 auts=\"qop\", qop=\"Unquoted qop options\"", authorizationHeader.getValue());
  }

  @Test
  void testAddQopParam() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");

    // Act
    authorizationHeader.addQopParam("Qop");

    // Assert
    assertEquals("42 qop=Qop", authorizationHeader.getValue());
  }

  @Test
  void testAddQopParam2() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addAutsParam("qop");

    // Act
    authorizationHeader.addQopParam("Qop");

    // Assert
    assertEquals("42 auts=\"qop\", qop=Qop", authorizationHeader.getValue());
  }

  @Test
  void testAddQuotedParameter() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");

    // Act
    authorizationHeader.addQuotedParameter("Param name", "42");

    // Assert
    assertEquals("42 Param name=\"42\"", authorizationHeader.getValue());
  }

  @Test
  void testAddQuotedParameter2() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addAutsParam("Unquoted auts");

    // Act
    authorizationHeader.addQuotedParameter("Param name", "42");

    // Assert
    assertEquals("42 auts=\"Unquoted auts\", Param name=\"42\"", authorizationHeader.getValue());
  }

  @Test
  void testAddRealmParam() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");

    // Act
    authorizationHeader.addRealmParam("Unquoted realm");

    // Assert
    assertEquals("42 realm=\"Unquoted realm\"", authorizationHeader.getValue());
  }

  @Test
  void testAddRealmParam2() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addAutsParam("realm");

    // Act
    authorizationHeader.addRealmParam("Unquoted realm");

    // Assert
    assertEquals("42 auts=\"realm\", realm=\"Unquoted realm\"", authorizationHeader.getValue());
  }

  @Test
  void testAddResponseParam() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");

    // Act
    authorizationHeader.addResponseParam("Unquoted response");

    // Assert
    assertEquals("42 response=\"Unquoted response\"", authorizationHeader.getValue());
  }

  @Test
  void testAddResponseParam2() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addAutsParam("response");

    // Act
    authorizationHeader.addResponseParam("Unquoted response");

    // Assert
    assertEquals("42 auts=\"response\", response=\"Unquoted response\"", authorizationHeader.getValue());
  }

  @Test
  void testAddRspauthParam() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");

    // Act
    authorizationHeader.addRspauthParam("Unquoted rspauth");

    // Assert
    assertEquals("42 rspauth=\"Unquoted rspauth\"", authorizationHeader.getValue());
  }

  @Test
  void testAddRspauthParam2() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addAutsParam("rspauth");

    // Act
    authorizationHeader.addRspauthParam("Unquoted rspauth");

    // Assert
    assertEquals("42 auts=\"rspauth\", rspauth=\"Unquoted rspauth\"", authorizationHeader.getValue());
  }

  @Test
  void testAddUnquotedParameter() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");

    // Act
    authorizationHeader.addUnquotedParameter("Param name", "42");

    // Assert
    assertEquals("42 Param name=42", authorizationHeader.getValue());
  }

  @Test
  void testAddUnquotedParameter2() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addAutsParam("Unquoted auts");

    // Act
    authorizationHeader.addUnquotedParameter("Param name", "42");

    // Assert
    assertEquals("42 auts=\"Unquoted auts\", Param name=42", authorizationHeader.getValue());
  }

  @Test
  void testAddUriParam() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");

    // Act
    authorizationHeader.addUriParam("Unquoted uri");

    // Assert
    assertEquals("42 uri=\"Unquoted uri\"", authorizationHeader.getValue());
  }

  @Test
  void testAddUriParam2() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addAutsParam("uri");

    // Act
    authorizationHeader.addUriParam("Unquoted uri");

    // Assert
    assertEquals("42 auts=\"uri\", uri=\"Unquoted uri\"", authorizationHeader.getValue());
  }

  @Test
  void testAddUsernameParam() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");

    // Act
    authorizationHeader.addUsernameParam("janedoe");

    // Assert
    assertEquals("42 username=\"janedoe\"", authorizationHeader.getValue());
  }

  @Test
  void testAddUsernameParam2() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addAutsParam("username");

    // Act
    authorizationHeader.addUsernameParam("janedoe");

    // Assert
    assertEquals("42 auts=\"username\", username=\"janedoe\"", authorizationHeader.getValue());
  }

  @Test
  void testConstructor() {
    // Arrange and Act
    AuthorizationHeader actualAuthorizationHeader = new AuthorizationHeader("42");

    // Assert
    assertNull(actualAuthorizationHeader.getAlgorithParam());
    String[] stringArray = AuthenticationHeader.QUOTED_PARAMETERS;
    assertSame(actualAuthorizationHeader.QUOTED_PARAMETERS, stringArray);
    assertEquals(10, stringArray.length);
    assertEquals(" ", AuthenticationHeader.LWS_SEPARATOR);
    assertEquals("Authorization: 42\r\n", actualAuthorizationHeader.toString());
    assertFalse(actualAuthorizationHeader.hasUsernameParam());
    assertFalse(actualAuthorizationHeader.hasUriParam());
    assertFalse(actualAuthorizationHeader.hasRspauthParam());
    assertFalse(actualAuthorizationHeader.hasResponseParam());
    assertFalse(actualAuthorizationHeader.hasRealmParam());
    assertFalse(actualAuthorizationHeader.hasQopParam());
    assertFalse(actualAuthorizationHeader.hasQopOptionsParam());
    assertFalse(actualAuthorizationHeader.hasOpaqueParam());
    assertFalse(actualAuthorizationHeader.hasNonceParam());
    assertFalse(actualAuthorizationHeader.hasNextnonceParam());
    assertFalse(actualAuthorizationHeader.hasNcParam());
    assertFalse(actualAuthorizationHeader.hasCnonceParam());
    assertFalse(actualAuthorizationHeader.hasAutsParam());
    assertFalse(actualAuthorizationHeader.hasAlgorithmParam());
    assertEquals("42", actualAuthorizationHeader.getValue());
    assertNull(actualAuthorizationHeader.getUsernameParam());
    assertNull(actualAuthorizationHeader.getUriParam());
    assertNull(actualAuthorizationHeader.getRspauthParam());
    assertNull(actualAuthorizationHeader.getResponseParam());
    assertNull(actualAuthorizationHeader.getRealmParam());
    assertNull(actualAuthorizationHeader.getQopParam());
    assertNull(actualAuthorizationHeader.getQopOptionsParam());
    assertTrue(actualAuthorizationHeader.getParameterNames().isEmpty());
    assertNull(actualAuthorizationHeader.getOpaqueParam());
    assertNull(actualAuthorizationHeader.getNonceParam());
    assertNull(actualAuthorizationHeader.getNextnonceParam());
    assertNull(actualAuthorizationHeader.getNcParam());
    assertEquals(CoreSipHeaders.Authorization, actualAuthorizationHeader.getName());
    assertNull(actualAuthorizationHeader.getCnonceParam());
    assertNull(actualAuthorizationHeader.getAutsParam());
    assertEquals("42", actualAuthorizationHeader.getAuthScheme());
  }

  @Test
  void testConstructor2() {
    // Arrange and Act
    AuthorizationHeader actualAuthorizationHeader = new AuthorizationHeader("Auth scheme", new Vector(1));

    // Assert
    assertEquals("Auth scheme", actualAuthorizationHeader.getValue());
    assertEquals(CoreSipHeaders.Authorization, actualAuthorizationHeader.getName());
  }

  @Test
  void testConstructor3() {
    // Arrange
    Vector vector = new Vector(1);
    vector.add("42");

    // Act
    AuthorizationHeader actualAuthorizationHeader = new AuthorizationHeader("Auth scheme", vector);

    // Assert
    assertEquals("Auth scheme 42", actualAuthorizationHeader.getValue());
    assertEquals(CoreSipHeaders.Authorization, actualAuthorizationHeader.getName());
  }

  @Test
  void testConstructor4() {
    // Arrange
    Vector vector = new Vector(1);
    vector.add("42");
    vector.add("42");

    // Act
    AuthorizationHeader actualAuthorizationHeader = new AuthorizationHeader("Auth scheme", vector);

    // Assert
    assertEquals("Auth scheme 42, 42", actualAuthorizationHeader.getValue());
    assertEquals(CoreSipHeaders.Authorization, actualAuthorizationHeader.getName());
  }

  @Test
  void testConstructor5() {
    // Arrange
    Vector vector = new Vector(1);
    vector.add("foo");

    // Act
    AuthorizationHeader actualAuthorizationHeader = new AuthorizationHeader("Auth scheme", vector);

    // Assert
    assertEquals("Auth scheme foo", actualAuthorizationHeader.getValue());
    assertEquals(CoreSipHeaders.Authorization, actualAuthorizationHeader.getName());
  }

  @Test
  void testConstructor6() {
    // Arrange and Act
    AuthorizationHeader actualAuthorizationHeader = new AuthorizationHeader(new Header("Hname", "42"));

    // Assert
    assertEquals("42", actualAuthorizationHeader.getValue());
    assertEquals("Hname", actualAuthorizationHeader.getName());
  }

  @Test
  void testConstructor7() {
    // Arrange
    Header header = new Header("Hname", "42");
    header.setValue("42");

    // Act
    AuthorizationHeader actualAuthorizationHeader = new AuthorizationHeader(header);

    // Assert
    assertEquals("42", actualAuthorizationHeader.getValue());
    assertEquals("Hname", actualAuthorizationHeader.getName());
  }

  @Test
  void testGetAlgorithParam() {
    // Arrange, Act and Assert
    assertNull((new AuthorizationHeader("42")).getAlgorithParam());
    assertNull((new AuthorizationHeader("")).getAlgorithParam());
    assertNull((new AuthorizationHeader("Auth scheme", new Vector(1))).getAlgorithParam());
  }

  @Test
  void testGetAlgorithParam2() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addAutsParam("algorithm");

    // Act and Assert
    assertNull(authorizationHeader.getAlgorithParam());
  }

  @Test
  void testGetAlgorithParam3() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addParameter("algorithm", "42");

    // Act and Assert
    assertEquals("42", authorizationHeader.getAlgorithParam());
  }

  @Test
  void testGetAlgorithParam4() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addQuotedParameter("algorithm", "42");

    // Act and Assert
    assertEquals("42", authorizationHeader.getAlgorithParam());
  }

  @Test
  void testGetAlgorithParam5() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addAutsParam("algorithm");
    authorizationHeader.addAutsParam("algorithm");

    // Act and Assert
    assertNull(authorizationHeader.getAlgorithParam());
  }

  @Test
  void testGetAlgorithParam6() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addParameter("algorithm", "42");
    authorizationHeader.addAutsParam("algorithm");

    // Act and Assert
    assertEquals("42", authorizationHeader.getAlgorithParam());
  }

  @Test
  void testGetAlgorithParam7() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addParameter("", "42");

    // Act and Assert
    assertNull(authorizationHeader.getAlgorithParam());
  }

  @Test
  void testGetAlgorithParam8() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addParameter("algorithm", "");

    // Act and Assert
    assertEquals("", authorizationHeader.getAlgorithParam());
  }

  @Test
  void testGetAuthScheme() {
    // Arrange, Act and Assert
    assertEquals("42", (new AuthorizationHeader("42")).getAuthScheme());
    assertEquals("", (new AuthorizationHeader("")).getAuthScheme());
    assertEquals("Auth", (new AuthorizationHeader("Auth scheme", new Vector(1))).getAuthScheme());
  }

  @Test
  void testGetAuthScheme2() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("");
    authorizationHeader.addAutsParam("Unquoted auts");

    // Act and Assert
    assertEquals("auts=\"Unquoted", authorizationHeader.getAuthScheme());
  }

  @Test
  void testGetAutsParam() {
    // Arrange, Act and Assert
    assertNull((new AuthorizationHeader("42")).getAutsParam());
    assertNull((new AuthorizationHeader("")).getAutsParam());
    assertNull((new AuthorizationHeader("Auth scheme", new Vector(1))).getAutsParam());
  }

  @Test
  void testGetAutsParam2() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addAutsParam("auts");

    // Act and Assert
    assertEquals("auts", authorizationHeader.getAutsParam());
  }

  @Test
  void testGetAutsParam3() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addUnquotedParameter("auts", "42");

    // Act and Assert
    assertEquals("42", authorizationHeader.getAutsParam());
  }

  @Test
  void testGetAutsParam4() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addAutsParam("auts");
    authorizationHeader.addAutsParam("auts");

    // Act and Assert
    assertEquals("auts", authorizationHeader.getAutsParam());
  }

  @Test
  void testGetAutsParam5() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addCnonceParam("auts");
    authorizationHeader.addAutsParam("auts");

    // Act and Assert
    assertEquals("auts", authorizationHeader.getAutsParam());
  }

  @Test
  void testGetAutsParam6() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addParameter("", "42");

    // Act and Assert
    assertNull(authorizationHeader.getAutsParam());
  }

  @Test
  void testGetAutsParam7() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addUnquotedParameter("auts", "");

    // Act and Assert
    assertEquals("", authorizationHeader.getAutsParam());
  }

  @Test
  void testGetCnonceParam() {
    // Arrange, Act and Assert
    assertNull((new AuthorizationHeader("42")).getCnonceParam());
    assertNull((new AuthorizationHeader("")).getCnonceParam());
    assertNull((new AuthorizationHeader("Auth scheme", new Vector(1))).getCnonceParam());
  }

  @Test
  void testGetCnonceParam2() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addAutsParam("cnonce");

    // Act and Assert
    assertNull(authorizationHeader.getCnonceParam());
  }

  @Test
  void testGetCnonceParam3() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addCnonceParam("cnonce");

    // Act and Assert
    assertEquals("cnonce", authorizationHeader.getCnonceParam());
  }

  @Test
  void testGetCnonceParam4() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addUnquotedParameter("cnonce", "42");

    // Act and Assert
    assertEquals("42", authorizationHeader.getCnonceParam());
  }

  @Test
  void testGetCnonceParam5() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addAutsParam("cnonce");
    authorizationHeader.addAutsParam("cnonce");

    // Act and Assert
    assertNull(authorizationHeader.getCnonceParam());
  }

  @Test
  void testGetCnonceParam6() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addCnonceParam("cnonce");
    authorizationHeader.addAutsParam("cnonce");

    // Act and Assert
    assertEquals("cnonce", authorizationHeader.getCnonceParam());
  }

  @Test
  void testGetCnonceParam7() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addParameter("", "42");

    // Act and Assert
    assertNull(authorizationHeader.getCnonceParam());
  }

  @Test
  void testGetCnonceParam8() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addUnquotedParameter("cnonce", "");

    // Act and Assert
    assertEquals("", authorizationHeader.getCnonceParam());
  }

  @Test
  void testGetNcParam() {
    // Arrange, Act and Assert
    assertNull((new AuthorizationHeader("42")).getNcParam());
    assertNull((new AuthorizationHeader("")).getNcParam());
    assertNull((new AuthorizationHeader("Auth scheme", new Vector(1))).getNcParam());
  }

  @Test
  void testGetNcParam2() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addAutsParam("nc");

    // Act and Assert
    assertNull(authorizationHeader.getNcParam());
  }

  @Test
  void testGetNcParam3() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addParameter("nc", "42");

    // Act and Assert
    assertEquals("42", authorizationHeader.getNcParam());
  }

  @Test
  void testGetNcParam4() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addQuotedParameter("nc", "42");

    // Act and Assert
    assertEquals("42", authorizationHeader.getNcParam());
  }

  @Test
  void testGetNcParam5() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addAutsParam("nc");
    authorizationHeader.addAutsParam("nc");

    // Act and Assert
    assertNull(authorizationHeader.getNcParam());
  }

  @Test
  void testGetNcParam6() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addParameter("nc", "42");
    authorizationHeader.addAutsParam("nc");

    // Act and Assert
    assertEquals("42", authorizationHeader.getNcParam());
  }

  @Test
  void testGetNcParam7() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addParameter("", "42");

    // Act and Assert
    assertNull(authorizationHeader.getNcParam());
  }

  @Test
  void testGetNcParam8() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addParameter("nc", "");

    // Act and Assert
    assertEquals("", authorizationHeader.getNcParam());
  }

  @Test
  void testGetNextnonceParam() {
    // Arrange, Act and Assert
    assertNull((new AuthorizationHeader("42")).getNextnonceParam());
    assertNull((new AuthorizationHeader("")).getNextnonceParam());
    assertNull((new AuthorizationHeader("Auth scheme", new Vector(1))).getNextnonceParam());
  }

  @Test
  void testGetNextnonceParam2() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addAutsParam("nextnonce");

    // Act and Assert
    assertNull(authorizationHeader.getNextnonceParam());
  }

  @Test
  void testGetNextnonceParam3() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addParameter("nextnonce", "42");

    // Act and Assert
    assertEquals("42", authorizationHeader.getNextnonceParam());
  }

  @Test
  void testGetNextnonceParam4() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addUnquotedParameter("nextnonce", "42");

    // Act and Assert
    assertEquals("42", authorizationHeader.getNextnonceParam());
  }

  @Test
  void testGetNextnonceParam5() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addAutsParam("nextnonce");
    authorizationHeader.addAutsParam("nextnonce");

    // Act and Assert
    assertNull(authorizationHeader.getNextnonceParam());
  }

  @Test
  void testGetNextnonceParam6() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addParameter("nextnonce", "42");
    authorizationHeader.addAutsParam("nextnonce");

    // Act and Assert
    assertEquals("42", authorizationHeader.getNextnonceParam());
  }

  @Test
  void testGetNextnonceParam7() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addParameter("", "42");

    // Act and Assert
    assertNull(authorizationHeader.getNextnonceParam());
  }

  @Test
  void testGetNextnonceParam8() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addUnquotedParameter("nextnonce", "");

    // Act and Assert
    assertEquals("", authorizationHeader.getNextnonceParam());
  }

  @Test
  void testGetNonceParam() {
    // Arrange, Act and Assert
    assertNull((new AuthorizationHeader("42")).getNonceParam());
    assertNull((new AuthorizationHeader("")).getNonceParam());
    assertNull((new AuthorizationHeader("Auth scheme", new Vector(1))).getNonceParam());
  }

  @Test
  void testGetNonceParam2() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addAutsParam("nonce");

    // Act and Assert
    assertNull(authorizationHeader.getNonceParam());
  }

  @Test
  void testGetNonceParam3() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addParameter("nonce", "42");

    // Act and Assert
    assertEquals("42", authorizationHeader.getNonceParam());
  }

  @Test
  void testGetNonceParam4() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addUnquotedParameter("nonce", "42");

    // Act and Assert
    assertEquals("42", authorizationHeader.getNonceParam());
  }

  @Test
  void testGetNonceParam5() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addAutsParam("nonce");
    authorizationHeader.addAutsParam("nonce");

    // Act and Assert
    assertNull(authorizationHeader.getNonceParam());
  }

  @Test
  void testGetNonceParam6() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addParameter("nonce", "42");
    authorizationHeader.addAutsParam("nonce");

    // Act and Assert
    assertEquals("42", authorizationHeader.getNonceParam());
  }

  @Test
  void testGetNonceParam7() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addParameter("", "42");

    // Act and Assert
    assertNull(authorizationHeader.getNonceParam());
  }

  @Test
  void testGetNonceParam8() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addUnquotedParameter("nonce", "");

    // Act and Assert
    assertEquals("", authorizationHeader.getNonceParam());
  }

  @Test
  void testGetOpaqueParam() {
    // Arrange, Act and Assert
    assertNull((new AuthorizationHeader("42")).getOpaqueParam());
    assertNull((new AuthorizationHeader("")).getOpaqueParam());
    assertNull((new AuthorizationHeader("Auth scheme", new Vector(1))).getOpaqueParam());
  }

  @Test
  void testGetOpaqueParam2() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addAutsParam("opaque");

    // Act and Assert
    assertNull(authorizationHeader.getOpaqueParam());
  }

  @Test
  void testGetOpaqueParam3() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addParameter("opaque", "42");

    // Act and Assert
    assertEquals("42", authorizationHeader.getOpaqueParam());
  }

  @Test
  void testGetOpaqueParam4() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addUnquotedParameter("opaque", "42");

    // Act and Assert
    assertEquals("42", authorizationHeader.getOpaqueParam());
  }

  @Test
  void testGetOpaqueParam5() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addAutsParam("opaque");
    authorizationHeader.addAutsParam("opaque");

    // Act and Assert
    assertNull(authorizationHeader.getOpaqueParam());
  }

  @Test
  void testGetOpaqueParam6() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addParameter("opaque", "42");
    authorizationHeader.addAutsParam("opaque");

    // Act and Assert
    assertEquals("42", authorizationHeader.getOpaqueParam());
  }

  @Test
  void testGetOpaqueParam7() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addParameter("", "42");

    // Act and Assert
    assertNull(authorizationHeader.getOpaqueParam());
  }

  @Test
  void testGetOpaqueParam8() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addUnquotedParameter("opaque", "");

    // Act and Assert
    assertEquals("", authorizationHeader.getOpaqueParam());
  }

  @Test
  void testGetParameter() {
    // Arrange, Act and Assert
    assertNull((new AuthorizationHeader("42")).getParameter("Param name"));
    assertNull((new AuthorizationHeader("")).getParameter("Param name"));
    assertNull((new AuthorizationHeader("Auth scheme", new Vector(1))).getParameter("Param name"));
  }

  @Test
  void testGetParameter2() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addAutsParam("Unquoted auts");

    // Act and Assert
    assertNull(authorizationHeader.getParameter("Param name"));
  }

  @Test
  void testGetParameter3() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addAutsParam("auts");
    authorizationHeader.addAutsParam("Unquoted auts");

    // Act and Assert
    assertNull(authorizationHeader.getParameter("Param name"));
  }

  @Test
  void testGetParameter4() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addParameter("", "42");

    // Act and Assert
    assertNull(authorizationHeader.getParameter("Param name"));
  }

  @Test
  void testGetParameter5() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("");
    authorizationHeader.addAutsParam("Unquoted auts");

    // Act and Assert
    assertNull(authorizationHeader.getParameter("Param name"));
  }

  @Test
  void testGetParameterNames() {
    // Arrange, Act and Assert
    assertTrue((new AuthorizationHeader("42")).getParameterNames().isEmpty());
    assertTrue((new AuthorizationHeader("")).getParameterNames().isEmpty());
  }

  @Test
  void testGetParameterNames2() {
    // Arrange and Act
    Vector actualParameterNames = (new AuthorizationHeader("Auth scheme", new Vector(1))).getParameterNames();

    // Assert
    assertEquals(1, actualParameterNames.size());
    assertEquals("scheme", actualParameterNames.get(0));
  }

  @Test
  void testGetParameterNames3() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addAutsParam("Unquoted auts");

    // Act
    Vector actualParameterNames = authorizationHeader.getParameterNames();

    // Assert
    assertEquals(1, actualParameterNames.size());
    assertEquals("auts", actualParameterNames.get(0));
  }

  @Test
  void testGetParameterNames4() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addAutsParam("Unquoted auts");
    authorizationHeader.addAutsParam("Unquoted auts");

    // Act
    Vector actualParameterNames = authorizationHeader.getParameterNames();

    // Assert
    assertEquals(2, actualParameterNames.size());
    assertEquals("auts", actualParameterNames.get(0));
    assertEquals("auts", actualParameterNames.get(1));
  }

  @Test
  void testGetParameterNames5() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addParameter("", "42");

    // Act
    Vector actualParameterNames = authorizationHeader.getParameterNames();

    // Assert
    assertEquals(1, actualParameterNames.size());
    assertEquals("42", actualParameterNames.get(0));
  }

  @Test
  void testGetParameterNames6() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("");
    authorizationHeader.addAutsParam("Unquoted auts");

    // Act
    Vector actualParameterNames = authorizationHeader.getParameterNames();

    // Assert
    assertEquals(1, actualParameterNames.size());
    assertEquals("auts\"", actualParameterNames.get(0));
  }

  @Test
  void testGetQopOptionsParam() {
    // Arrange, Act and Assert
    assertNull((new AuthorizationHeader("42")).getQopOptionsParam());
    assertNull((new AuthorizationHeader("")).getQopOptionsParam());
    assertNull((new AuthorizationHeader("Auth scheme", new Vector(1))).getQopOptionsParam());
  }

  @Test
  void testGetQopOptionsParam2() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addAutsParam("qop");

    // Act and Assert
    assertNull(authorizationHeader.getQopOptionsParam());
  }

  @Test
  void testGetQopOptionsParam3() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addParameter("qop", "42");

    // Act and Assert
    assertEquals("42", authorizationHeader.getQopOptionsParam());
  }

  @Test
  void testGetQopOptionsParam4() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addQuotedParameter("qop", "42");

    // Act and Assert
    assertEquals("42", authorizationHeader.getQopOptionsParam());
  }

  @Test
  void testGetQopOptionsParam5() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addAutsParam("qop");
    authorizationHeader.addAutsParam("qop");

    // Act and Assert
    assertNull(authorizationHeader.getQopOptionsParam());
  }

  @Test
  void testGetQopOptionsParam6() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addParameter("qop", "42");
    authorizationHeader.addAutsParam("qop");

    // Act and Assert
    assertEquals("42", authorizationHeader.getQopOptionsParam());
  }

  @Test
  void testGetQopOptionsParam7() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addParameter("", "42");

    // Act and Assert
    assertNull(authorizationHeader.getQopOptionsParam());
  }

  @Test
  void testGetQopOptionsParam8() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addParameter("qop", "");

    // Act and Assert
    assertEquals("", authorizationHeader.getQopOptionsParam());
  }

  @Test
  void testGetQopParam() {
    // Arrange, Act and Assert
    assertNull((new AuthorizationHeader("42")).getQopParam());
    assertNull((new AuthorizationHeader("")).getQopParam());
    assertNull((new AuthorizationHeader("Auth scheme", new Vector(1))).getQopParam());
  }

  @Test
  void testGetQopParam2() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addAutsParam("qop");

    // Act and Assert
    assertNull(authorizationHeader.getQopParam());
  }

  @Test
  void testGetQopParam3() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addParameter("qop", "42");

    // Act and Assert
    assertEquals("42", authorizationHeader.getQopParam());
  }

  @Test
  void testGetQopParam4() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addQuotedParameter("qop", "42");

    // Act and Assert
    assertEquals("42", authorizationHeader.getQopParam());
  }

  @Test
  void testGetQopParam5() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addAutsParam("qop");
    authorizationHeader.addAutsParam("qop");

    // Act and Assert
    assertNull(authorizationHeader.getQopParam());
  }

  @Test
  void testGetQopParam6() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addParameter("qop", "42");
    authorizationHeader.addAutsParam("qop");

    // Act and Assert
    assertEquals("42", authorizationHeader.getQopParam());
  }

  @Test
  void testGetQopParam7() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addParameter("", "42");

    // Act and Assert
    assertNull(authorizationHeader.getQopParam());
  }

  @Test
  void testGetQopParam8() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addParameter("qop", "");

    // Act and Assert
    assertEquals("", authorizationHeader.getQopParam());
  }

  @Test
  void testGetRealmParam() {
    // Arrange, Act and Assert
    assertNull((new AuthorizationHeader("42")).getRealmParam());
    assertNull((new AuthorizationHeader("")).getRealmParam());
    assertNull((new AuthorizationHeader("Auth scheme", new Vector(1))).getRealmParam());
  }

  @Test
  void testGetRealmParam2() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addAutsParam("realm");

    // Act and Assert
    assertNull(authorizationHeader.getRealmParam());
  }

  @Test
  void testGetRealmParam3() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addParameter("realm", "42");

    // Act and Assert
    assertEquals("42", authorizationHeader.getRealmParam());
  }

  @Test
  void testGetRealmParam4() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addUnquotedParameter("realm", "42");

    // Act and Assert
    assertEquals("42", authorizationHeader.getRealmParam());
  }

  @Test
  void testGetRealmParam5() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addAutsParam("realm");
    authorizationHeader.addAutsParam("realm");

    // Act and Assert
    assertNull(authorizationHeader.getRealmParam());
  }

  @Test
  void testGetRealmParam6() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addParameter("realm", "42");
    authorizationHeader.addAutsParam("realm");

    // Act and Assert
    assertEquals("42", authorizationHeader.getRealmParam());
  }

  @Test
  void testGetRealmParam7() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addParameter("", "42");

    // Act and Assert
    assertNull(authorizationHeader.getRealmParam());
  }

  @Test
  void testGetRealmParam8() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addUnquotedParameter("realm", "");

    // Act and Assert
    assertEquals("", authorizationHeader.getRealmParam());
  }

  @Test
  void testGetResponseParam() {
    // Arrange, Act and Assert
    assertNull((new AuthorizationHeader("42")).getResponseParam());
    assertNull((new AuthorizationHeader("")).getResponseParam());
    assertNull((new AuthorizationHeader("Auth scheme", new Vector(1))).getResponseParam());
  }

  @Test
  void testGetResponseParam2() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addAutsParam("response");

    // Act and Assert
    assertNull(authorizationHeader.getResponseParam());
  }

  @Test
  void testGetResponseParam3() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addParameter("response", "42");

    // Act and Assert
    assertEquals("42", authorizationHeader.getResponseParam());
  }

  @Test
  void testGetResponseParam4() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addUnquotedParameter("response", "42");

    // Act and Assert
    assertEquals("42", authorizationHeader.getResponseParam());
  }

  @Test
  void testGetResponseParam5() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addAutsParam("response");
    authorizationHeader.addAutsParam("response");

    // Act and Assert
    assertNull(authorizationHeader.getResponseParam());
  }

  @Test
  void testGetResponseParam6() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addParameter("response", "42");
    authorizationHeader.addAutsParam("response");

    // Act and Assert
    assertEquals("42", authorizationHeader.getResponseParam());
  }

  @Test
  void testGetResponseParam7() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addParameter("", "42");

    // Act and Assert
    assertNull(authorizationHeader.getResponseParam());
  }

  @Test
  void testGetResponseParam8() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addUnquotedParameter("response", "");

    // Act and Assert
    assertEquals("", authorizationHeader.getResponseParam());
  }

  @Test
  void testGetRspauthParam() {
    // Arrange, Act and Assert
    assertNull((new AuthorizationHeader("42")).getRspauthParam());
    assertNull((new AuthorizationHeader("")).getRspauthParam());
    assertNull((new AuthorizationHeader("Auth scheme", new Vector(1))).getRspauthParam());
  }

  @Test
  void testGetRspauthParam2() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addAutsParam("rspauth");

    // Act and Assert
    assertNull(authorizationHeader.getRspauthParam());
  }

  @Test
  void testGetRspauthParam3() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addParameter("rspauth", "42");

    // Act and Assert
    assertEquals("42", authorizationHeader.getRspauthParam());
  }

  @Test
  void testGetRspauthParam4() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addUnquotedParameter("rspauth", "42");

    // Act and Assert
    assertEquals("42", authorizationHeader.getRspauthParam());
  }

  @Test
  void testGetRspauthParam5() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addAutsParam("rspauth");
    authorizationHeader.addAutsParam("rspauth");

    // Act and Assert
    assertNull(authorizationHeader.getRspauthParam());
  }

  @Test
  void testGetRspauthParam6() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addParameter("rspauth", "42");
    authorizationHeader.addAutsParam("rspauth");

    // Act and Assert
    assertEquals("42", authorizationHeader.getRspauthParam());
  }

  @Test
  void testGetRspauthParam7() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addParameter("", "42");

    // Act and Assert
    assertNull(authorizationHeader.getRspauthParam());
  }

  @Test
  void testGetRspauthParam8() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addUnquotedParameter("rspauth", "");

    // Act and Assert
    assertEquals("", authorizationHeader.getRspauthParam());
  }

  @Test
  void testGetUriParam() {
    // Arrange, Act and Assert
    assertNull((new AuthorizationHeader("42")).getUriParam());
    assertNull((new AuthorizationHeader("")).getUriParam());
    assertNull((new AuthorizationHeader("Auth scheme", new Vector(1))).getUriParam());
  }

  @Test
  void testGetUriParam2() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addAutsParam("uri");

    // Act and Assert
    assertNull(authorizationHeader.getUriParam());
  }

  @Test
  void testGetUriParam3() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addParameter("uri", "42");

    // Act and Assert
    assertEquals("42", authorizationHeader.getUriParam());
  }

  @Test
  void testGetUriParam4() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addUnquotedParameter("uri", "42");

    // Act and Assert
    assertEquals("42", authorizationHeader.getUriParam());
  }

  @Test
  void testGetUriParam5() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addAutsParam("uri");
    authorizationHeader.addAutsParam("uri");

    // Act and Assert
    assertNull(authorizationHeader.getUriParam());
  }

  @Test
  void testGetUriParam6() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addParameter("uri", "42");
    authorizationHeader.addAutsParam("uri");

    // Act and Assert
    assertEquals("42", authorizationHeader.getUriParam());
  }

  @Test
  void testGetUriParam7() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addParameter("", "42");

    // Act and Assert
    assertNull(authorizationHeader.getUriParam());
  }

  @Test
  void testGetUriParam8() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addUnquotedParameter("uri", "");

    // Act and Assert
    assertEquals("", authorizationHeader.getUriParam());
  }

  @Test
  void testGetUsernameParam() {
    // Arrange, Act and Assert
    assertNull((new AuthorizationHeader("42")).getUsernameParam());
    assertNull((new AuthorizationHeader("")).getUsernameParam());
    assertNull((new AuthorizationHeader("Auth scheme", new Vector(1))).getUsernameParam());
  }

  @Test
  void testGetUsernameParam2() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addAutsParam("username");

    // Act and Assert
    assertNull(authorizationHeader.getUsernameParam());
  }

  @Test
  void testGetUsernameParam3() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addParameter("username", "42");

    // Act and Assert
    assertEquals("42", authorizationHeader.getUsernameParam());
  }

  @Test
  void testGetUsernameParam4() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addUnquotedParameter("username", "42");

    // Act and Assert
    assertEquals("42", authorizationHeader.getUsernameParam());
  }

  @Test
  void testGetUsernameParam5() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addAutsParam("username");
    authorizationHeader.addAutsParam("username");

    // Act and Assert
    assertNull(authorizationHeader.getUsernameParam());
  }

  @Test
  void testGetUsernameParam6() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addParameter("username", "42");
    authorizationHeader.addAutsParam("username");

    // Act and Assert
    assertEquals("42", authorizationHeader.getUsernameParam());
  }

  @Test
  void testGetUsernameParam7() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addParameter("", "42");

    // Act and Assert
    assertNull(authorizationHeader.getUsernameParam());
  }

  @Test
  void testGetUsernameParam8() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addUnquotedParameter("username", "");

    // Act and Assert
    assertEquals("", authorizationHeader.getUsernameParam());
  }

  @Test
  void testHasAlgorithmParam() {
    // Arrange, Act and Assert
    assertFalse((new AuthorizationHeader("42")).hasAlgorithmParam());
    assertFalse((new AuthorizationHeader("")).hasAlgorithmParam());
    assertFalse((new AuthorizationHeader("Auth scheme", new Vector(1))).hasAlgorithmParam());
  }

  @Test
  void testHasAlgorithmParam2() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addAutsParam("algorithm");

    // Act and Assert
    assertFalse(authorizationHeader.hasAlgorithmParam());
  }

  @Test
  void testHasAlgorithmParam3() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addParameter("algorithm", "42");

    // Act and Assert
    assertTrue(authorizationHeader.hasAlgorithmParam());
  }

  @Test
  void testHasAlgorithmParam4() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addAutsParam("algorithm");
    authorizationHeader.addAutsParam("algorithm");

    // Act and Assert
    assertFalse(authorizationHeader.hasAlgorithmParam());
  }

  @Test
  void testHasAlgorithmParam5() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addParameter("", "42");

    // Act and Assert
    assertFalse(authorizationHeader.hasAlgorithmParam());
  }

  @Test
  void testHasAutsParam() {
    // Arrange, Act and Assert
    assertFalse((new AuthorizationHeader("42")).hasAutsParam());
    assertFalse((new AuthorizationHeader("")).hasAutsParam());
    assertFalse((new AuthorizationHeader("Auth scheme", new Vector(1))).hasAutsParam());
  }

  @Test
  void testHasAutsParam2() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addAutsParam("auts");

    // Act and Assert
    assertTrue(authorizationHeader.hasAutsParam());
  }

  @Test
  void testHasAutsParam3() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addCnonceParam("auts");

    // Act and Assert
    assertFalse(authorizationHeader.hasAutsParam());
  }

  @Test
  void testHasAutsParam4() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addCnonceParam("auts");
    authorizationHeader.addAutsParam("auts");

    // Act and Assert
    assertTrue(authorizationHeader.hasAutsParam());
  }

  @Test
  void testHasAutsParam5() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addParameter("", "42");

    // Act and Assert
    assertFalse(authorizationHeader.hasAutsParam());
  }

  @Test
  void testHasCnonceParam() {
    // Arrange, Act and Assert
    assertFalse((new AuthorizationHeader("42")).hasCnonceParam());
    assertFalse((new AuthorizationHeader("")).hasCnonceParam());
    assertFalse((new AuthorizationHeader("Auth scheme", new Vector(1))).hasCnonceParam());
  }

  @Test
  void testHasCnonceParam2() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addAutsParam("cnonce");

    // Act and Assert
    assertFalse(authorizationHeader.hasCnonceParam());
  }

  @Test
  void testHasCnonceParam3() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addCnonceParam("cnonce");

    // Act and Assert
    assertTrue(authorizationHeader.hasCnonceParam());
  }

  @Test
  void testHasCnonceParam4() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addAutsParam("cnonce");
    authorizationHeader.addAutsParam("cnonce");

    // Act and Assert
    assertFalse(authorizationHeader.hasCnonceParam());
  }

  @Test
  void testHasCnonceParam5() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addParameter("", "42");

    // Act and Assert
    assertFalse(authorizationHeader.hasCnonceParam());
  }

  @Test
  void testHasNcParam() {
    // Arrange, Act and Assert
    assertFalse((new AuthorizationHeader("42")).hasNcParam());
    assertFalse((new AuthorizationHeader("")).hasNcParam());
    assertFalse((new AuthorizationHeader("Auth scheme", new Vector(1))).hasNcParam());
  }

  @Test
  void testHasNcParam2() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addAutsParam("nc");

    // Act and Assert
    assertFalse(authorizationHeader.hasNcParam());
  }

  @Test
  void testHasNcParam3() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addParameter("nc", "42");

    // Act and Assert
    assertTrue(authorizationHeader.hasNcParam());
  }

  @Test
  void testHasNcParam4() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addAutsParam("nc");
    authorizationHeader.addAutsParam("nc");

    // Act and Assert
    assertFalse(authorizationHeader.hasNcParam());
  }

  @Test
  void testHasNcParam5() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addParameter("", "42");

    // Act and Assert
    assertFalse(authorizationHeader.hasNcParam());
  }

  @Test
  void testHasNextnonceParam() {
    // Arrange, Act and Assert
    assertFalse((new AuthorizationHeader("42")).hasNextnonceParam());
    assertFalse((new AuthorizationHeader("")).hasNextnonceParam());
    assertFalse((new AuthorizationHeader("Auth scheme", new Vector(1))).hasNextnonceParam());
  }

  @Test
  void testHasNextnonceParam2() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addAutsParam("nextnonce");

    // Act and Assert
    assertFalse(authorizationHeader.hasNextnonceParam());
  }

  @Test
  void testHasNextnonceParam3() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addParameter("nextnonce", "42");

    // Act and Assert
    assertTrue(authorizationHeader.hasNextnonceParam());
  }

  @Test
  void testHasNextnonceParam4() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addAutsParam("nextnonce");
    authorizationHeader.addAutsParam("nextnonce");

    // Act and Assert
    assertFalse(authorizationHeader.hasNextnonceParam());
  }

  @Test
  void testHasNextnonceParam5() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addParameter("", "42");

    // Act and Assert
    assertFalse(authorizationHeader.hasNextnonceParam());
  }

  @Test
  void testHasNonceParam() {
    // Arrange, Act and Assert
    assertFalse((new AuthorizationHeader("42")).hasNonceParam());
    assertFalse((new AuthorizationHeader("")).hasNonceParam());
    assertFalse((new AuthorizationHeader("Auth scheme", new Vector(1))).hasNonceParam());
  }

  @Test
  void testHasNonceParam2() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addAutsParam("nonce");

    // Act and Assert
    assertFalse(authorizationHeader.hasNonceParam());
  }

  @Test
  void testHasNonceParam3() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addParameter("nonce", "42");

    // Act and Assert
    assertTrue(authorizationHeader.hasNonceParam());
  }

  @Test
  void testHasNonceParam4() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addAutsParam("nonce");
    authorizationHeader.addAutsParam("nonce");

    // Act and Assert
    assertFalse(authorizationHeader.hasNonceParam());
  }

  @Test
  void testHasNonceParam5() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addParameter("", "42");

    // Act and Assert
    assertFalse(authorizationHeader.hasNonceParam());
  }

  @Test
  void testHasOpaqueParam() {
    // Arrange, Act and Assert
    assertFalse((new AuthorizationHeader("42")).hasOpaqueParam());
    assertFalse((new AuthorizationHeader("")).hasOpaqueParam());
    assertFalse((new AuthorizationHeader("Auth scheme", new Vector(1))).hasOpaqueParam());
  }

  @Test
  void testHasOpaqueParam2() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addAutsParam("opaque");

    // Act and Assert
    assertFalse(authorizationHeader.hasOpaqueParam());
  }

  @Test
  void testHasOpaqueParam3() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addParameter("opaque", "42");

    // Act and Assert
    assertTrue(authorizationHeader.hasOpaqueParam());
  }

  @Test
  void testHasOpaqueParam4() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addAutsParam("opaque");
    authorizationHeader.addAutsParam("opaque");

    // Act and Assert
    assertFalse(authorizationHeader.hasOpaqueParam());
  }

  @Test
  void testHasOpaqueParam5() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addParameter("", "42");

    // Act and Assert
    assertFalse(authorizationHeader.hasOpaqueParam());
  }

  @Test
  void testHasParameter() {
    // Arrange, Act and Assert
    assertFalse((new AuthorizationHeader("42")).hasParameter("Param name"));
    assertFalse((new AuthorizationHeader("")).hasParameter("Param name"));
    assertFalse((new AuthorizationHeader("Auth scheme", new Vector(1))).hasParameter("Param name"));
  }

  @Test
  void testHasParameter2() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addAutsParam("Unquoted auts");

    // Act and Assert
    assertFalse(authorizationHeader.hasParameter("Param name"));
  }

  @Test
  void testHasParameter3() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addAutsParam("auts");
    authorizationHeader.addAutsParam("Unquoted auts");

    // Act and Assert
    assertFalse(authorizationHeader.hasParameter("Param name"));
  }

  @Test
  void testHasParameter4() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addParameter("", "42");

    // Act and Assert
    assertFalse(authorizationHeader.hasParameter("Param name"));
  }

  @Test
  void testHasParameter5() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("");
    authorizationHeader.addAutsParam("Unquoted auts");

    // Act and Assert
    assertFalse(authorizationHeader.hasParameter("Param name"));
  }

  @Test
  void testHasQopOptionsParam() {
    // Arrange, Act and Assert
    assertFalse((new AuthorizationHeader("42")).hasQopOptionsParam());
    assertFalse((new AuthorizationHeader("")).hasQopOptionsParam());
    assertFalse((new AuthorizationHeader("Auth scheme", new Vector(1))).hasQopOptionsParam());
  }

  @Test
  void testHasQopOptionsParam2() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addAutsParam("qop");

    // Act and Assert
    assertFalse(authorizationHeader.hasQopOptionsParam());
  }

  @Test
  void testHasQopOptionsParam3() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addParameter("qop", "42");

    // Act and Assert
    assertTrue(authorizationHeader.hasQopOptionsParam());
  }

  @Test
  void testHasQopOptionsParam4() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addAutsParam("qop");
    authorizationHeader.addAutsParam("qop");

    // Act and Assert
    assertFalse(authorizationHeader.hasQopOptionsParam());
  }

  @Test
  void testHasQopOptionsParam5() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addParameter("", "42");

    // Act and Assert
    assertFalse(authorizationHeader.hasQopOptionsParam());
  }

  @Test
  void testHasQopParam() {
    // Arrange, Act and Assert
    assertFalse((new AuthorizationHeader("42")).hasQopParam());
    assertFalse((new AuthorizationHeader("")).hasQopParam());
    assertFalse((new AuthorizationHeader("Auth scheme", new Vector(1))).hasQopParam());
  }

  @Test
  void testHasQopParam2() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addAutsParam("qop");

    // Act and Assert
    assertFalse(authorizationHeader.hasQopParam());
  }

  @Test
  void testHasQopParam3() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addParameter("qop", "42");

    // Act and Assert
    assertTrue(authorizationHeader.hasQopParam());
  }

  @Test
  void testHasQopParam4() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addAutsParam("qop");
    authorizationHeader.addAutsParam("qop");

    // Act and Assert
    assertFalse(authorizationHeader.hasQopParam());
  }

  @Test
  void testHasQopParam5() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addParameter("", "42");

    // Act and Assert
    assertFalse(authorizationHeader.hasQopParam());
  }

  @Test
  void testHasRealmParam() {
    // Arrange, Act and Assert
    assertFalse((new AuthorizationHeader("42")).hasRealmParam());
    assertFalse((new AuthorizationHeader("")).hasRealmParam());
    assertFalse((new AuthorizationHeader("Auth scheme", new Vector(1))).hasRealmParam());
  }

  @Test
  void testHasRealmParam2() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addAutsParam("realm");

    // Act and Assert
    assertFalse(authorizationHeader.hasRealmParam());
  }

  @Test
  void testHasRealmParam3() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addParameter("realm", "42");

    // Act and Assert
    assertTrue(authorizationHeader.hasRealmParam());
  }

  @Test
  void testHasRealmParam4() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addAutsParam("realm");
    authorizationHeader.addAutsParam("realm");

    // Act and Assert
    assertFalse(authorizationHeader.hasRealmParam());
  }

  @Test
  void testHasRealmParam5() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addParameter("", "42");

    // Act and Assert
    assertFalse(authorizationHeader.hasRealmParam());
  }

  @Test
  void testHasResponseParam() {
    // Arrange, Act and Assert
    assertFalse((new AuthorizationHeader("42")).hasResponseParam());
    assertFalse((new AuthorizationHeader("")).hasResponseParam());
    assertFalse((new AuthorizationHeader("Auth scheme", new Vector(1))).hasResponseParam());
  }

  @Test
  void testHasResponseParam2() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addAutsParam("response");

    // Act and Assert
    assertFalse(authorizationHeader.hasResponseParam());
  }

  @Test
  void testHasResponseParam3() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addParameter("response", "42");

    // Act and Assert
    assertTrue(authorizationHeader.hasResponseParam());
  }

  @Test
  void testHasResponseParam4() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addAutsParam("response");
    authorizationHeader.addAutsParam("response");

    // Act and Assert
    assertFalse(authorizationHeader.hasResponseParam());
  }

  @Test
  void testHasResponseParam5() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addParameter("", "42");

    // Act and Assert
    assertFalse(authorizationHeader.hasResponseParam());
  }

  @Test
  void testHasRspauthParam() {
    // Arrange, Act and Assert
    assertFalse((new AuthorizationHeader("42")).hasRspauthParam());
    assertFalse((new AuthorizationHeader("")).hasRspauthParam());
    assertFalse((new AuthorizationHeader("Auth scheme", new Vector(1))).hasRspauthParam());
  }

  @Test
  void testHasRspauthParam2() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addAutsParam("rspauth");

    // Act and Assert
    assertFalse(authorizationHeader.hasRspauthParam());
  }

  @Test
  void testHasRspauthParam3() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addParameter("rspauth", "42");

    // Act and Assert
    assertTrue(authorizationHeader.hasRspauthParam());
  }

  @Test
  void testHasRspauthParam4() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addAutsParam("rspauth");
    authorizationHeader.addAutsParam("rspauth");

    // Act and Assert
    assertFalse(authorizationHeader.hasRspauthParam());
  }

  @Test
  void testHasRspauthParam5() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addParameter("", "42");

    // Act and Assert
    assertFalse(authorizationHeader.hasRspauthParam());
  }

  @Test
  void testHasUriParam() {
    // Arrange, Act and Assert
    assertFalse((new AuthorizationHeader("42")).hasUriParam());
    assertFalse((new AuthorizationHeader("")).hasUriParam());
    assertFalse((new AuthorizationHeader("Auth scheme", new Vector(1))).hasUriParam());
  }

  @Test
  void testHasUriParam2() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addAutsParam("uri");

    // Act and Assert
    assertFalse(authorizationHeader.hasUriParam());
  }

  @Test
  void testHasUriParam3() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addParameter("uri", "42");

    // Act and Assert
    assertTrue(authorizationHeader.hasUriParam());
  }

  @Test
  void testHasUriParam4() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addAutsParam("uri");
    authorizationHeader.addAutsParam("uri");

    // Act and Assert
    assertFalse(authorizationHeader.hasUriParam());
  }

  @Test
  void testHasUriParam5() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addParameter("", "42");

    // Act and Assert
    assertFalse(authorizationHeader.hasUriParam());
  }

  @Test
  void testHasUsernameParam() {
    // Arrange, Act and Assert
    assertFalse((new AuthorizationHeader("42")).hasUsernameParam());
    assertFalse((new AuthorizationHeader("")).hasUsernameParam());
    assertFalse((new AuthorizationHeader("Auth scheme", new Vector(1))).hasUsernameParam());
  }

  @Test
  void testHasUsernameParam2() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addAutsParam("username");

    // Act and Assert
    assertFalse(authorizationHeader.hasUsernameParam());
  }

  @Test
  void testHasUsernameParam3() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addParameter("username", "42");

    // Act and Assert
    assertTrue(authorizationHeader.hasUsernameParam());
  }

  @Test
  void testHasUsernameParam4() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addAutsParam("username");
    authorizationHeader.addAutsParam("username");

    // Act and Assert
    assertFalse(authorizationHeader.hasUsernameParam());
  }

  @Test
  void testHasUsernameParam5() {
    // Arrange
    AuthorizationHeader authorizationHeader = new AuthorizationHeader("42");
    authorizationHeader.addParameter("", "42");

    // Act and Assert
    assertFalse(authorizationHeader.hasUsernameParam());
  }
}

