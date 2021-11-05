package org.mjsip.sip.header;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Vector;
import org.junit.jupiter.api.Test;

class WwwAuthenticateHeaderDiffblueTest {
  @Test
  void testAddAlgorithParam() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");

    // Act
    wwwAuthenticateHeader.addAlgorithParam("Algorithm");

    // Assert
    assertEquals("42 algorithm=Algorithm", wwwAuthenticateHeader.getValue());
  }

  @Test
  void testAddAlgorithParam2() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addAutsParam("algorithm");

    // Act
    wwwAuthenticateHeader.addAlgorithParam("Algorithm");

    // Assert
    assertEquals("42 auts=\"algorithm\", algorithm=Algorithm", wwwAuthenticateHeader.getValue());
  }

  @Test
  void testAddAutsParam() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");

    // Act
    wwwAuthenticateHeader.addAutsParam("Unquoted auts");

    // Assert
    assertEquals("42 auts=\"Unquoted auts\"", wwwAuthenticateHeader.getValue());
  }

  @Test
  void testAddAutsParam2() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addAutsParam("auts");

    // Act
    wwwAuthenticateHeader.addAutsParam("Unquoted auts");

    // Assert
    assertEquals("42 auts=\"auts\", auts=\"Unquoted auts\"", wwwAuthenticateHeader.getValue());
  }

  @Test
  void testAddCnonceParam() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");

    // Act
    wwwAuthenticateHeader.addCnonceParam("Unquoted cnonce");

    // Assert
    assertEquals("42 cnonce=\"Unquoted cnonce\"", wwwAuthenticateHeader.getValue());
  }

  @Test
  void testAddCnonceParam2() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addAutsParam("cnonce");

    // Act
    wwwAuthenticateHeader.addCnonceParam("Unquoted cnonce");

    // Assert
    assertEquals("42 auts=\"cnonce\", cnonce=\"Unquoted cnonce\"", wwwAuthenticateHeader.getValue());
  }

  @Test
  void testAddNcParam() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");

    // Act
    wwwAuthenticateHeader.addNcParam("Nc");

    // Assert
    assertEquals("42 nc=Nc", wwwAuthenticateHeader.getValue());
  }

  @Test
  void testAddNcParam2() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addAutsParam("nc");

    // Act
    wwwAuthenticateHeader.addNcParam("Nc");

    // Assert
    assertEquals("42 auts=\"nc\", nc=Nc", wwwAuthenticateHeader.getValue());
  }

  @Test
  void testAddNextnonceParam() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");

    // Act
    wwwAuthenticateHeader.addNextnonceParam("Unquoted nextnonce");

    // Assert
    assertEquals("42 nextnonce=\"Unquoted nextnonce\"", wwwAuthenticateHeader.getValue());
  }

  @Test
  void testAddNextnonceParam2() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addAutsParam("nextnonce");

    // Act
    wwwAuthenticateHeader.addNextnonceParam("Unquoted nextnonce");

    // Assert
    assertEquals("42 auts=\"nextnonce\", nextnonce=\"Unquoted nextnonce\"", wwwAuthenticateHeader.getValue());
  }

  @Test
  void testAddNonceParam() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");

    // Act
    wwwAuthenticateHeader.addNonceParam("Unquoted nonce");

    // Assert
    assertEquals("42 nonce=\"Unquoted nonce\"", wwwAuthenticateHeader.getValue());
  }

  @Test
  void testAddNonceParam2() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addAutsParam("nonce");

    // Act
    wwwAuthenticateHeader.addNonceParam("Unquoted nonce");

    // Assert
    assertEquals("42 auts=\"nonce\", nonce=\"Unquoted nonce\"", wwwAuthenticateHeader.getValue());
  }

  @Test
  void testAddOpaqueParam() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");

    // Act
    wwwAuthenticateHeader.addOpaqueParam("Unquoted opaque");

    // Assert
    assertEquals("42 opaque=\"Unquoted opaque\"", wwwAuthenticateHeader.getValue());
  }

  @Test
  void testAddOpaqueParam2() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addAutsParam("opaque");

    // Act
    wwwAuthenticateHeader.addOpaqueParam("Unquoted opaque");

    // Assert
    assertEquals("42 auts=\"opaque\", opaque=\"Unquoted opaque\"", wwwAuthenticateHeader.getValue());
  }

  @Test
  void testAddParameter() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");

    // Act
    wwwAuthenticateHeader.addParameter("Param name", "42");

    // Assert
    assertEquals("42 Param name=42", wwwAuthenticateHeader.getValue());
  }

  @Test
  void testAddParameter2() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addAutsParam("Unquoted auts");

    // Act
    wwwAuthenticateHeader.addParameter("Param name", "42");

    // Assert
    assertEquals("42 auts=\"Unquoted auts\", Param name=42", wwwAuthenticateHeader.getValue());
  }

  @Test
  void testAddParameter3() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");

    // Act
    wwwAuthenticateHeader.addParameter("auts", "42");

    // Assert
    assertEquals("42 auts=\"42\"", wwwAuthenticateHeader.getValue());
  }

  @Test
  void testAddParameter4() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addAutsParam("Unquoted auts");

    // Act
    wwwAuthenticateHeader.addParameter("auts", "42");

    // Assert
    assertEquals("42 auts=\"Unquoted auts\", auts=\"42\"", wwwAuthenticateHeader.getValue());
  }

  @Test
  void testAddQopOptionsParam() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");

    // Act
    wwwAuthenticateHeader.addQopOptionsParam("Unquoted qop options");

    // Assert
    assertEquals("42 qop=\"Unquoted qop options\"", wwwAuthenticateHeader.getValue());
  }

  @Test
  void testAddQopOptionsParam2() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addAutsParam("qop");

    // Act
    wwwAuthenticateHeader.addQopOptionsParam("Unquoted qop options");

    // Assert
    assertEquals("42 auts=\"qop\", qop=\"Unquoted qop options\"", wwwAuthenticateHeader.getValue());
  }

  @Test
  void testAddQopParam() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");

    // Act
    wwwAuthenticateHeader.addQopParam("Qop");

    // Assert
    assertEquals("42 qop=Qop", wwwAuthenticateHeader.getValue());
  }

  @Test
  void testAddQopParam2() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addAutsParam("qop");

    // Act
    wwwAuthenticateHeader.addQopParam("Qop");

    // Assert
    assertEquals("42 auts=\"qop\", qop=Qop", wwwAuthenticateHeader.getValue());
  }

  @Test
  void testAddQuotedParameter() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");

    // Act
    wwwAuthenticateHeader.addQuotedParameter("Param name", "42");

    // Assert
    assertEquals("42 Param name=\"42\"", wwwAuthenticateHeader.getValue());
  }

  @Test
  void testAddQuotedParameter2() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addAutsParam("Unquoted auts");

    // Act
    wwwAuthenticateHeader.addQuotedParameter("Param name", "42");

    // Assert
    assertEquals("42 auts=\"Unquoted auts\", Param name=\"42\"", wwwAuthenticateHeader.getValue());
  }

  @Test
  void testAddRealmParam() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");

    // Act
    wwwAuthenticateHeader.addRealmParam("Unquoted realm");

    // Assert
    assertEquals("42 realm=\"Unquoted realm\"", wwwAuthenticateHeader.getValue());
  }

  @Test
  void testAddRealmParam2() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addAutsParam("realm");

    // Act
    wwwAuthenticateHeader.addRealmParam("Unquoted realm");

    // Assert
    assertEquals("42 auts=\"realm\", realm=\"Unquoted realm\"", wwwAuthenticateHeader.getValue());
  }

  @Test
  void testAddResponseParam() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");

    // Act
    wwwAuthenticateHeader.addResponseParam("Unquoted response");

    // Assert
    assertEquals("42 response=\"Unquoted response\"", wwwAuthenticateHeader.getValue());
  }

  @Test
  void testAddResponseParam2() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addAutsParam("response");

    // Act
    wwwAuthenticateHeader.addResponseParam("Unquoted response");

    // Assert
    assertEquals("42 auts=\"response\", response=\"Unquoted response\"", wwwAuthenticateHeader.getValue());
  }

  @Test
  void testAddRspauthParam() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");

    // Act
    wwwAuthenticateHeader.addRspauthParam("Unquoted rspauth");

    // Assert
    assertEquals("42 rspauth=\"Unquoted rspauth\"", wwwAuthenticateHeader.getValue());
  }

  @Test
  void testAddRspauthParam2() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addAutsParam("rspauth");

    // Act
    wwwAuthenticateHeader.addRspauthParam("Unquoted rspauth");

    // Assert
    assertEquals("42 auts=\"rspauth\", rspauth=\"Unquoted rspauth\"", wwwAuthenticateHeader.getValue());
  }

  @Test
  void testAddUnquotedParameter() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");

    // Act
    wwwAuthenticateHeader.addUnquotedParameter("Param name", "42");

    // Assert
    assertEquals("42 Param name=42", wwwAuthenticateHeader.getValue());
  }

  @Test
  void testAddUnquotedParameter2() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addAutsParam("Unquoted auts");

    // Act
    wwwAuthenticateHeader.addUnquotedParameter("Param name", "42");

    // Assert
    assertEquals("42 auts=\"Unquoted auts\", Param name=42", wwwAuthenticateHeader.getValue());
  }

  @Test
  void testAddUriParam() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");

    // Act
    wwwAuthenticateHeader.addUriParam("Unquoted uri");

    // Assert
    assertEquals("42 uri=\"Unquoted uri\"", wwwAuthenticateHeader.getValue());
  }

  @Test
  void testAddUriParam2() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addAutsParam("uri");

    // Act
    wwwAuthenticateHeader.addUriParam("Unquoted uri");

    // Assert
    assertEquals("42 auts=\"uri\", uri=\"Unquoted uri\"", wwwAuthenticateHeader.getValue());
  }

  @Test
  void testAddUsernameParam() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");

    // Act
    wwwAuthenticateHeader.addUsernameParam("janedoe");

    // Assert
    assertEquals("42 username=\"janedoe\"", wwwAuthenticateHeader.getValue());
  }

  @Test
  void testAddUsernameParam2() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addAutsParam("username");

    // Act
    wwwAuthenticateHeader.addUsernameParam("janedoe");

    // Assert
    assertEquals("42 auts=\"username\", username=\"janedoe\"", wwwAuthenticateHeader.getValue());
  }

  @Test
  void testConstructor() {
    // Arrange and Act
    WwwAuthenticateHeader actualWwwAuthenticateHeader = new WwwAuthenticateHeader("42");

    // Assert
    assertNull(actualWwwAuthenticateHeader.getAlgorithParam());
    String[] stringArray = AuthenticationHeader.QUOTED_PARAMETERS;
    assertSame(actualWwwAuthenticateHeader.QUOTED_PARAMETERS, stringArray);
    assertEquals(10, stringArray.length);
    assertEquals(" ", AuthenticationHeader.LWS_SEPARATOR);
    assertEquals("WWW-Authenticate: 42\r\n", actualWwwAuthenticateHeader.toString());
    assertFalse(actualWwwAuthenticateHeader.hasUsernameParam());
    assertFalse(actualWwwAuthenticateHeader.hasUriParam());
    assertFalse(actualWwwAuthenticateHeader.hasRspauthParam());
    assertFalse(actualWwwAuthenticateHeader.hasResponseParam());
    assertFalse(actualWwwAuthenticateHeader.hasRealmParam());
    assertFalse(actualWwwAuthenticateHeader.hasQopParam());
    assertFalse(actualWwwAuthenticateHeader.hasQopOptionsParam());
    assertFalse(actualWwwAuthenticateHeader.hasOpaqueParam());
    assertFalse(actualWwwAuthenticateHeader.hasNonceParam());
    assertFalse(actualWwwAuthenticateHeader.hasNextnonceParam());
    assertFalse(actualWwwAuthenticateHeader.hasNcParam());
    assertFalse(actualWwwAuthenticateHeader.hasCnonceParam());
    assertFalse(actualWwwAuthenticateHeader.hasAutsParam());
    assertFalse(actualWwwAuthenticateHeader.hasAlgorithmParam());
    assertEquals("42", actualWwwAuthenticateHeader.getValue());
    assertNull(actualWwwAuthenticateHeader.getUsernameParam());
    assertNull(actualWwwAuthenticateHeader.getUriParam());
    assertNull(actualWwwAuthenticateHeader.getRspauthParam());
    assertNull(actualWwwAuthenticateHeader.getResponseParam());
    assertNull(actualWwwAuthenticateHeader.getRealmParam());
    assertNull(actualWwwAuthenticateHeader.getQopParam());
    assertNull(actualWwwAuthenticateHeader.getQopOptionsParam());
    assertTrue(actualWwwAuthenticateHeader.getParameterNames().isEmpty());
    assertNull(actualWwwAuthenticateHeader.getOpaqueParam());
    assertNull(actualWwwAuthenticateHeader.getNonceParam());
    assertNull(actualWwwAuthenticateHeader.getNextnonceParam());
    assertNull(actualWwwAuthenticateHeader.getNcParam());
    assertEquals(CoreSipHeaders.WWW_Authenticate, actualWwwAuthenticateHeader.getName());
    assertNull(actualWwwAuthenticateHeader.getCnonceParam());
    assertNull(actualWwwAuthenticateHeader.getAutsParam());
    assertEquals("42", actualWwwAuthenticateHeader.getAuthScheme());
  }

  @Test
  void testConstructor2() {
    // Arrange and Act
    WwwAuthenticateHeader actualWwwAuthenticateHeader = new WwwAuthenticateHeader("Auth scheme", new Vector(1));

    // Assert
    assertEquals("Auth scheme", actualWwwAuthenticateHeader.getValue());
    assertEquals(CoreSipHeaders.WWW_Authenticate, actualWwwAuthenticateHeader.getName());
  }

  @Test
  void testConstructor3() {
    // Arrange
    Vector vector = new Vector(1);
    vector.add("42");

    // Act
    WwwAuthenticateHeader actualWwwAuthenticateHeader = new WwwAuthenticateHeader("Auth scheme", vector);

    // Assert
    assertEquals("Auth scheme 42", actualWwwAuthenticateHeader.getValue());
    assertEquals(CoreSipHeaders.WWW_Authenticate, actualWwwAuthenticateHeader.getName());
  }

  @Test
  void testConstructor4() {
    // Arrange
    Vector vector = new Vector(1);
    vector.add("42");
    vector.add("42");

    // Act
    WwwAuthenticateHeader actualWwwAuthenticateHeader = new WwwAuthenticateHeader("Auth scheme", vector);

    // Assert
    assertEquals("Auth scheme 42, 42", actualWwwAuthenticateHeader.getValue());
    assertEquals(CoreSipHeaders.WWW_Authenticate, actualWwwAuthenticateHeader.getName());
  }

  @Test
  void testConstructor5() {
    // Arrange
    Vector vector = new Vector(1);
    vector.add("foo");

    // Act
    WwwAuthenticateHeader actualWwwAuthenticateHeader = new WwwAuthenticateHeader("Auth scheme", vector);

    // Assert
    assertEquals("Auth scheme foo", actualWwwAuthenticateHeader.getValue());
    assertEquals(CoreSipHeaders.WWW_Authenticate, actualWwwAuthenticateHeader.getName());
  }

  @Test
  void testConstructor6() {
    // Arrange and Act
    WwwAuthenticateHeader actualWwwAuthenticateHeader = new WwwAuthenticateHeader(new Header("Hname", "42"));

    // Assert
    assertEquals("42", actualWwwAuthenticateHeader.getValue());
    assertEquals("Hname", actualWwwAuthenticateHeader.getName());
  }

  @Test
  void testConstructor7() {
    // Arrange
    Header header = new Header("Hname", "42");
    header.setValue("42");

    // Act
    WwwAuthenticateHeader actualWwwAuthenticateHeader = new WwwAuthenticateHeader(header);

    // Assert
    assertEquals("42", actualWwwAuthenticateHeader.getValue());
    assertEquals("Hname", actualWwwAuthenticateHeader.getName());
  }

  @Test
  void testGetAlgorithParam() {
    // Arrange, Act and Assert
    assertNull((new WwwAuthenticateHeader("42")).getAlgorithParam());
    assertNull((new WwwAuthenticateHeader("")).getAlgorithParam());
    assertNull((new WwwAuthenticateHeader("Auth scheme", new Vector(1))).getAlgorithParam());
  }

  @Test
  void testGetAlgorithParam2() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addAutsParam("algorithm");

    // Act and Assert
    assertNull(wwwAuthenticateHeader.getAlgorithParam());
  }

  @Test
  void testGetAlgorithParam3() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addParameter("algorithm", "42");

    // Act and Assert
    assertEquals("42", wwwAuthenticateHeader.getAlgorithParam());
  }

  @Test
  void testGetAlgorithParam4() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addQuotedParameter("algorithm", "42");

    // Act and Assert
    assertEquals("42", wwwAuthenticateHeader.getAlgorithParam());
  }

  @Test
  void testGetAlgorithParam5() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addAutsParam("algorithm");
    wwwAuthenticateHeader.addAutsParam("algorithm");

    // Act and Assert
    assertNull(wwwAuthenticateHeader.getAlgorithParam());
  }

  @Test
  void testGetAlgorithParam6() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addParameter("algorithm", "42");
    wwwAuthenticateHeader.addAutsParam("algorithm");

    // Act and Assert
    assertEquals("42", wwwAuthenticateHeader.getAlgorithParam());
  }

  @Test
  void testGetAlgorithParam7() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addParameter("", "42");

    // Act and Assert
    assertNull(wwwAuthenticateHeader.getAlgorithParam());
  }

  @Test
  void testGetAlgorithParam8() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addParameter("algorithm", "");

    // Act and Assert
    assertEquals("", wwwAuthenticateHeader.getAlgorithParam());
  }

  @Test
  void testGetAuthScheme() {
    // Arrange, Act and Assert
    assertEquals("42", (new WwwAuthenticateHeader("42")).getAuthScheme());
    assertEquals("", (new WwwAuthenticateHeader("")).getAuthScheme());
    assertEquals("Auth", (new WwwAuthenticateHeader("Auth scheme", new Vector(1))).getAuthScheme());
  }

  @Test
  void testGetAuthScheme2() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("");
    wwwAuthenticateHeader.addAutsParam("Unquoted auts");

    // Act and Assert
    assertEquals("auts=\"Unquoted", wwwAuthenticateHeader.getAuthScheme());
  }

  @Test
  void testGetAutsParam() {
    // Arrange, Act and Assert
    assertNull((new WwwAuthenticateHeader("42")).getAutsParam());
    assertNull((new WwwAuthenticateHeader("")).getAutsParam());
    assertNull((new WwwAuthenticateHeader("Auth scheme", new Vector(1))).getAutsParam());
  }

  @Test
  void testGetAutsParam2() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addAutsParam("auts");

    // Act and Assert
    assertEquals("auts", wwwAuthenticateHeader.getAutsParam());
  }

  @Test
  void testGetAutsParam3() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addUnquotedParameter("auts", "42");

    // Act and Assert
    assertEquals("42", wwwAuthenticateHeader.getAutsParam());
  }

  @Test
  void testGetAutsParam4() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addAutsParam("auts");
    wwwAuthenticateHeader.addAutsParam("auts");

    // Act and Assert
    assertEquals("auts", wwwAuthenticateHeader.getAutsParam());
  }

  @Test
  void testGetAutsParam5() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addCnonceParam("auts");
    wwwAuthenticateHeader.addAutsParam("auts");

    // Act and Assert
    assertEquals("auts", wwwAuthenticateHeader.getAutsParam());
  }

  @Test
  void testGetAutsParam6() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addParameter("", "42");

    // Act and Assert
    assertNull(wwwAuthenticateHeader.getAutsParam());
  }

  @Test
  void testGetAutsParam7() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addUnquotedParameter("auts", "");

    // Act and Assert
    assertEquals("", wwwAuthenticateHeader.getAutsParam());
  }

  @Test
  void testGetCnonceParam() {
    // Arrange, Act and Assert
    assertNull((new WwwAuthenticateHeader("42")).getCnonceParam());
    assertNull((new WwwAuthenticateHeader("")).getCnonceParam());
    assertNull((new WwwAuthenticateHeader("Auth scheme", new Vector(1))).getCnonceParam());
  }

  @Test
  void testGetCnonceParam2() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addAutsParam("cnonce");

    // Act and Assert
    assertNull(wwwAuthenticateHeader.getCnonceParam());
  }

  @Test
  void testGetCnonceParam3() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addCnonceParam("cnonce");

    // Act and Assert
    assertEquals("cnonce", wwwAuthenticateHeader.getCnonceParam());
  }

  @Test
  void testGetCnonceParam4() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addUnquotedParameter("cnonce", "42");

    // Act and Assert
    assertEquals("42", wwwAuthenticateHeader.getCnonceParam());
  }

  @Test
  void testGetCnonceParam5() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addAutsParam("cnonce");
    wwwAuthenticateHeader.addAutsParam("cnonce");

    // Act and Assert
    assertNull(wwwAuthenticateHeader.getCnonceParam());
  }

  @Test
  void testGetCnonceParam6() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addCnonceParam("cnonce");
    wwwAuthenticateHeader.addAutsParam("cnonce");

    // Act and Assert
    assertEquals("cnonce", wwwAuthenticateHeader.getCnonceParam());
  }

  @Test
  void testGetCnonceParam7() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addParameter("", "42");

    // Act and Assert
    assertNull(wwwAuthenticateHeader.getCnonceParam());
  }

  @Test
  void testGetCnonceParam8() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addUnquotedParameter("cnonce", "");

    // Act and Assert
    assertEquals("", wwwAuthenticateHeader.getCnonceParam());
  }

  @Test
  void testGetNcParam() {
    // Arrange, Act and Assert
    assertNull((new WwwAuthenticateHeader("42")).getNcParam());
    assertNull((new WwwAuthenticateHeader("")).getNcParam());
    assertNull((new WwwAuthenticateHeader("Auth scheme", new Vector(1))).getNcParam());
  }

  @Test
  void testGetNcParam2() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addAutsParam("nc");

    // Act and Assert
    assertNull(wwwAuthenticateHeader.getNcParam());
  }

  @Test
  void testGetNcParam3() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addParameter("nc", "42");

    // Act and Assert
    assertEquals("42", wwwAuthenticateHeader.getNcParam());
  }

  @Test
  void testGetNcParam4() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addQuotedParameter("nc", "42");

    // Act and Assert
    assertEquals("42", wwwAuthenticateHeader.getNcParam());
  }

  @Test
  void testGetNcParam5() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addAutsParam("nc");
    wwwAuthenticateHeader.addAutsParam("nc");

    // Act and Assert
    assertNull(wwwAuthenticateHeader.getNcParam());
  }

  @Test
  void testGetNcParam6() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addParameter("nc", "42");
    wwwAuthenticateHeader.addAutsParam("nc");

    // Act and Assert
    assertEquals("42", wwwAuthenticateHeader.getNcParam());
  }

  @Test
  void testGetNcParam7() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addParameter("", "42");

    // Act and Assert
    assertNull(wwwAuthenticateHeader.getNcParam());
  }

  @Test
  void testGetNcParam8() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addParameter("nc", "");

    // Act and Assert
    assertEquals("", wwwAuthenticateHeader.getNcParam());
  }

  @Test
  void testGetNextnonceParam() {
    // Arrange, Act and Assert
    assertNull((new WwwAuthenticateHeader("42")).getNextnonceParam());
    assertNull((new WwwAuthenticateHeader("")).getNextnonceParam());
    assertNull((new WwwAuthenticateHeader("Auth scheme", new Vector(1))).getNextnonceParam());
  }

  @Test
  void testGetNextnonceParam2() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addAutsParam("nextnonce");

    // Act and Assert
    assertNull(wwwAuthenticateHeader.getNextnonceParam());
  }

  @Test
  void testGetNextnonceParam3() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addParameter("nextnonce", "42");

    // Act and Assert
    assertEquals("42", wwwAuthenticateHeader.getNextnonceParam());
  }

  @Test
  void testGetNextnonceParam4() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addUnquotedParameter("nextnonce", "42");

    // Act and Assert
    assertEquals("42", wwwAuthenticateHeader.getNextnonceParam());
  }

  @Test
  void testGetNextnonceParam5() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addAutsParam("nextnonce");
    wwwAuthenticateHeader.addAutsParam("nextnonce");

    // Act and Assert
    assertNull(wwwAuthenticateHeader.getNextnonceParam());
  }

  @Test
  void testGetNextnonceParam6() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addParameter("nextnonce", "42");
    wwwAuthenticateHeader.addAutsParam("nextnonce");

    // Act and Assert
    assertEquals("42", wwwAuthenticateHeader.getNextnonceParam());
  }

  @Test
  void testGetNextnonceParam7() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addParameter("", "42");

    // Act and Assert
    assertNull(wwwAuthenticateHeader.getNextnonceParam());
  }

  @Test
  void testGetNextnonceParam8() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addUnquotedParameter("nextnonce", "");

    // Act and Assert
    assertEquals("", wwwAuthenticateHeader.getNextnonceParam());
  }

  @Test
  void testGetNonceParam() {
    // Arrange, Act and Assert
    assertNull((new WwwAuthenticateHeader("42")).getNonceParam());
    assertNull((new WwwAuthenticateHeader("")).getNonceParam());
    assertNull((new WwwAuthenticateHeader("Auth scheme", new Vector(1))).getNonceParam());
  }

  @Test
  void testGetNonceParam2() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addAutsParam("nonce");

    // Act and Assert
    assertNull(wwwAuthenticateHeader.getNonceParam());
  }

  @Test
  void testGetNonceParam3() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addParameter("nonce", "42");

    // Act and Assert
    assertEquals("42", wwwAuthenticateHeader.getNonceParam());
  }

  @Test
  void testGetNonceParam4() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addUnquotedParameter("nonce", "42");

    // Act and Assert
    assertEquals("42", wwwAuthenticateHeader.getNonceParam());
  }

  @Test
  void testGetNonceParam5() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addAutsParam("nonce");
    wwwAuthenticateHeader.addAutsParam("nonce");

    // Act and Assert
    assertNull(wwwAuthenticateHeader.getNonceParam());
  }

  @Test
  void testGetNonceParam6() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addParameter("nonce", "42");
    wwwAuthenticateHeader.addAutsParam("nonce");

    // Act and Assert
    assertEquals("42", wwwAuthenticateHeader.getNonceParam());
  }

  @Test
  void testGetNonceParam7() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addParameter("", "42");

    // Act and Assert
    assertNull(wwwAuthenticateHeader.getNonceParam());
  }

  @Test
  void testGetNonceParam8() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addUnquotedParameter("nonce", "");

    // Act and Assert
    assertEquals("", wwwAuthenticateHeader.getNonceParam());
  }

  @Test
  void testGetOpaqueParam() {
    // Arrange, Act and Assert
    assertNull((new WwwAuthenticateHeader("42")).getOpaqueParam());
    assertNull((new WwwAuthenticateHeader("")).getOpaqueParam());
    assertNull((new WwwAuthenticateHeader("Auth scheme", new Vector(1))).getOpaqueParam());
  }

  @Test
  void testGetOpaqueParam2() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addAutsParam("opaque");

    // Act and Assert
    assertNull(wwwAuthenticateHeader.getOpaqueParam());
  }

  @Test
  void testGetOpaqueParam3() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addParameter("opaque", "42");

    // Act and Assert
    assertEquals("42", wwwAuthenticateHeader.getOpaqueParam());
  }

  @Test
  void testGetOpaqueParam4() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addUnquotedParameter("opaque", "42");

    // Act and Assert
    assertEquals("42", wwwAuthenticateHeader.getOpaqueParam());
  }

  @Test
  void testGetOpaqueParam5() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addAutsParam("opaque");
    wwwAuthenticateHeader.addAutsParam("opaque");

    // Act and Assert
    assertNull(wwwAuthenticateHeader.getOpaqueParam());
  }

  @Test
  void testGetOpaqueParam6() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addParameter("opaque", "42");
    wwwAuthenticateHeader.addAutsParam("opaque");

    // Act and Assert
    assertEquals("42", wwwAuthenticateHeader.getOpaqueParam());
  }

  @Test
  void testGetOpaqueParam7() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addParameter("", "42");

    // Act and Assert
    assertNull(wwwAuthenticateHeader.getOpaqueParam());
  }

  @Test
  void testGetOpaqueParam8() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addUnquotedParameter("opaque", "");

    // Act and Assert
    assertEquals("", wwwAuthenticateHeader.getOpaqueParam());
  }

  @Test
  void testGetParameter() {
    // Arrange, Act and Assert
    assertNull((new WwwAuthenticateHeader("42")).getParameter("Param name"));
    assertNull((new WwwAuthenticateHeader("")).getParameter("Param name"));
    assertNull((new WwwAuthenticateHeader("Auth scheme", new Vector(1))).getParameter("Param name"));
  }

  @Test
  void testGetParameter2() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addAutsParam("Unquoted auts");

    // Act and Assert
    assertNull(wwwAuthenticateHeader.getParameter("Param name"));
  }

  @Test
  void testGetParameter3() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addAutsParam("auts");
    wwwAuthenticateHeader.addAutsParam("Unquoted auts");

    // Act and Assert
    assertNull(wwwAuthenticateHeader.getParameter("Param name"));
  }

  @Test
  void testGetParameter4() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addParameter("", "42");

    // Act and Assert
    assertNull(wwwAuthenticateHeader.getParameter("Param name"));
  }

  @Test
  void testGetParameter5() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("");
    wwwAuthenticateHeader.addAutsParam("Unquoted auts");

    // Act and Assert
    assertNull(wwwAuthenticateHeader.getParameter("Param name"));
  }

  @Test
  void testGetParameterNames() {
    // Arrange, Act and Assert
    assertTrue((new WwwAuthenticateHeader("42")).getParameterNames().isEmpty());
    assertTrue((new WwwAuthenticateHeader("")).getParameterNames().isEmpty());
  }

  @Test
  void testGetParameterNames2() {
    // Arrange and Act
    Vector actualParameterNames = (new WwwAuthenticateHeader("Auth scheme", new Vector(1))).getParameterNames();

    // Assert
    assertEquals(1, actualParameterNames.size());
    assertEquals("scheme", actualParameterNames.get(0));
  }

  @Test
  void testGetParameterNames3() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addAutsParam("Unquoted auts");

    // Act
    Vector actualParameterNames = wwwAuthenticateHeader.getParameterNames();

    // Assert
    assertEquals(1, actualParameterNames.size());
    assertEquals("auts", actualParameterNames.get(0));
  }

  @Test
  void testGetParameterNames4() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addAutsParam("Unquoted auts");
    wwwAuthenticateHeader.addAutsParam("Unquoted auts");

    // Act
    Vector actualParameterNames = wwwAuthenticateHeader.getParameterNames();

    // Assert
    assertEquals(2, actualParameterNames.size());
    assertEquals("auts", actualParameterNames.get(0));
    assertEquals("auts", actualParameterNames.get(1));
  }

  @Test
  void testGetParameterNames5() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addParameter("", "42");

    // Act
    Vector actualParameterNames = wwwAuthenticateHeader.getParameterNames();

    // Assert
    assertEquals(1, actualParameterNames.size());
    assertEquals("42", actualParameterNames.get(0));
  }

  @Test
  void testGetParameterNames6() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("");
    wwwAuthenticateHeader.addAutsParam("Unquoted auts");

    // Act
    Vector actualParameterNames = wwwAuthenticateHeader.getParameterNames();

    // Assert
    assertEquals(1, actualParameterNames.size());
    assertEquals("auts\"", actualParameterNames.get(0));
  }

  @Test
  void testGetQopOptionsParam() {
    // Arrange, Act and Assert
    assertNull((new WwwAuthenticateHeader("42")).getQopOptionsParam());
    assertNull((new WwwAuthenticateHeader("")).getQopOptionsParam());
    assertNull((new WwwAuthenticateHeader("Auth scheme", new Vector(1))).getQopOptionsParam());
  }

  @Test
  void testGetQopOptionsParam2() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addAutsParam("qop");

    // Act and Assert
    assertNull(wwwAuthenticateHeader.getQopOptionsParam());
  }

  @Test
  void testGetQopOptionsParam3() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addParameter("qop", "42");

    // Act and Assert
    assertEquals("42", wwwAuthenticateHeader.getQopOptionsParam());
  }

  @Test
  void testGetQopOptionsParam4() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addQuotedParameter("qop", "42");

    // Act and Assert
    assertEquals("42", wwwAuthenticateHeader.getQopOptionsParam());
  }

  @Test
  void testGetQopOptionsParam5() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addAutsParam("qop");
    wwwAuthenticateHeader.addAutsParam("qop");

    // Act and Assert
    assertNull(wwwAuthenticateHeader.getQopOptionsParam());
  }

  @Test
  void testGetQopOptionsParam6() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addParameter("qop", "42");
    wwwAuthenticateHeader.addAutsParam("qop");

    // Act and Assert
    assertEquals("42", wwwAuthenticateHeader.getQopOptionsParam());
  }

  @Test
  void testGetQopOptionsParam7() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addParameter("", "42");

    // Act and Assert
    assertNull(wwwAuthenticateHeader.getQopOptionsParam());
  }

  @Test
  void testGetQopOptionsParam8() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addParameter("qop", "");

    // Act and Assert
    assertEquals("", wwwAuthenticateHeader.getQopOptionsParam());
  }

  @Test
  void testGetQopParam() {
    // Arrange, Act and Assert
    assertNull((new WwwAuthenticateHeader("42")).getQopParam());
    assertNull((new WwwAuthenticateHeader("")).getQopParam());
    assertNull((new WwwAuthenticateHeader("Auth scheme", new Vector(1))).getQopParam());
  }

  @Test
  void testGetQopParam2() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addAutsParam("qop");

    // Act and Assert
    assertNull(wwwAuthenticateHeader.getQopParam());
  }

  @Test
  void testGetQopParam3() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addParameter("qop", "42");

    // Act and Assert
    assertEquals("42", wwwAuthenticateHeader.getQopParam());
  }

  @Test
  void testGetQopParam4() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addQuotedParameter("qop", "42");

    // Act and Assert
    assertEquals("42", wwwAuthenticateHeader.getQopParam());
  }

  @Test
  void testGetQopParam5() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addAutsParam("qop");
    wwwAuthenticateHeader.addAutsParam("qop");

    // Act and Assert
    assertNull(wwwAuthenticateHeader.getQopParam());
  }

  @Test
  void testGetQopParam6() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addParameter("qop", "42");
    wwwAuthenticateHeader.addAutsParam("qop");

    // Act and Assert
    assertEquals("42", wwwAuthenticateHeader.getQopParam());
  }

  @Test
  void testGetQopParam7() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addParameter("", "42");

    // Act and Assert
    assertNull(wwwAuthenticateHeader.getQopParam());
  }

  @Test
  void testGetQopParam8() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addParameter("qop", "");

    // Act and Assert
    assertEquals("", wwwAuthenticateHeader.getQopParam());
  }

  @Test
  void testGetRealmParam() {
    // Arrange, Act and Assert
    assertNull((new WwwAuthenticateHeader("42")).getRealmParam());
    assertNull((new WwwAuthenticateHeader("")).getRealmParam());
    assertNull((new WwwAuthenticateHeader("Auth scheme", new Vector(1))).getRealmParam());
  }

  @Test
  void testGetRealmParam2() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addAutsParam("realm");

    // Act and Assert
    assertNull(wwwAuthenticateHeader.getRealmParam());
  }

  @Test
  void testGetRealmParam3() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addParameter("realm", "42");

    // Act and Assert
    assertEquals("42", wwwAuthenticateHeader.getRealmParam());
  }

  @Test
  void testGetRealmParam4() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addUnquotedParameter("realm", "42");

    // Act and Assert
    assertEquals("42", wwwAuthenticateHeader.getRealmParam());
  }

  @Test
  void testGetRealmParam5() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addAutsParam("realm");
    wwwAuthenticateHeader.addAutsParam("realm");

    // Act and Assert
    assertNull(wwwAuthenticateHeader.getRealmParam());
  }

  @Test
  void testGetRealmParam6() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addParameter("realm", "42");
    wwwAuthenticateHeader.addAutsParam("realm");

    // Act and Assert
    assertEquals("42", wwwAuthenticateHeader.getRealmParam());
  }

  @Test
  void testGetRealmParam7() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addParameter("", "42");

    // Act and Assert
    assertNull(wwwAuthenticateHeader.getRealmParam());
  }

  @Test
  void testGetRealmParam8() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addUnquotedParameter("realm", "");

    // Act and Assert
    assertEquals("", wwwAuthenticateHeader.getRealmParam());
  }

  @Test
  void testGetResponseParam() {
    // Arrange, Act and Assert
    assertNull((new WwwAuthenticateHeader("42")).getResponseParam());
    assertNull((new WwwAuthenticateHeader("")).getResponseParam());
    assertNull((new WwwAuthenticateHeader("Auth scheme", new Vector(1))).getResponseParam());
  }

  @Test
  void testGetResponseParam2() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addAutsParam("response");

    // Act and Assert
    assertNull(wwwAuthenticateHeader.getResponseParam());
  }

  @Test
  void testGetResponseParam3() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addParameter("response", "42");

    // Act and Assert
    assertEquals("42", wwwAuthenticateHeader.getResponseParam());
  }

  @Test
  void testGetResponseParam4() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addUnquotedParameter("response", "42");

    // Act and Assert
    assertEquals("42", wwwAuthenticateHeader.getResponseParam());
  }

  @Test
  void testGetResponseParam5() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addAutsParam("response");
    wwwAuthenticateHeader.addAutsParam("response");

    // Act and Assert
    assertNull(wwwAuthenticateHeader.getResponseParam());
  }

  @Test
  void testGetResponseParam6() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addParameter("response", "42");
    wwwAuthenticateHeader.addAutsParam("response");

    // Act and Assert
    assertEquals("42", wwwAuthenticateHeader.getResponseParam());
  }

  @Test
  void testGetResponseParam7() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addParameter("", "42");

    // Act and Assert
    assertNull(wwwAuthenticateHeader.getResponseParam());
  }

  @Test
  void testGetResponseParam8() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addUnquotedParameter("response", "");

    // Act and Assert
    assertEquals("", wwwAuthenticateHeader.getResponseParam());
  }

  @Test
  void testGetRspauthParam() {
    // Arrange, Act and Assert
    assertNull((new WwwAuthenticateHeader("42")).getRspauthParam());
    assertNull((new WwwAuthenticateHeader("")).getRspauthParam());
    assertNull((new WwwAuthenticateHeader("Auth scheme", new Vector(1))).getRspauthParam());
  }

  @Test
  void testGetRspauthParam2() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addAutsParam("rspauth");

    // Act and Assert
    assertNull(wwwAuthenticateHeader.getRspauthParam());
  }

  @Test
  void testGetRspauthParam3() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addParameter("rspauth", "42");

    // Act and Assert
    assertEquals("42", wwwAuthenticateHeader.getRspauthParam());
  }

  @Test
  void testGetRspauthParam4() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addUnquotedParameter("rspauth", "42");

    // Act and Assert
    assertEquals("42", wwwAuthenticateHeader.getRspauthParam());
  }

  @Test
  void testGetRspauthParam5() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addAutsParam("rspauth");
    wwwAuthenticateHeader.addAutsParam("rspauth");

    // Act and Assert
    assertNull(wwwAuthenticateHeader.getRspauthParam());
  }

  @Test
  void testGetRspauthParam6() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addParameter("rspauth", "42");
    wwwAuthenticateHeader.addAutsParam("rspauth");

    // Act and Assert
    assertEquals("42", wwwAuthenticateHeader.getRspauthParam());
  }

  @Test
  void testGetRspauthParam7() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addParameter("", "42");

    // Act and Assert
    assertNull(wwwAuthenticateHeader.getRspauthParam());
  }

  @Test
  void testGetRspauthParam8() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addUnquotedParameter("rspauth", "");

    // Act and Assert
    assertEquals("", wwwAuthenticateHeader.getRspauthParam());
  }

  @Test
  void testGetUriParam() {
    // Arrange, Act and Assert
    assertNull((new WwwAuthenticateHeader("42")).getUriParam());
    assertNull((new WwwAuthenticateHeader("")).getUriParam());
    assertNull((new WwwAuthenticateHeader("Auth scheme", new Vector(1))).getUriParam());
  }

  @Test
  void testGetUriParam2() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addAutsParam("uri");

    // Act and Assert
    assertNull(wwwAuthenticateHeader.getUriParam());
  }

  @Test
  void testGetUriParam3() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addParameter("uri", "42");

    // Act and Assert
    assertEquals("42", wwwAuthenticateHeader.getUriParam());
  }

  @Test
  void testGetUriParam4() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addUnquotedParameter("uri", "42");

    // Act and Assert
    assertEquals("42", wwwAuthenticateHeader.getUriParam());
  }

  @Test
  void testGetUriParam5() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addAutsParam("uri");
    wwwAuthenticateHeader.addAutsParam("uri");

    // Act and Assert
    assertNull(wwwAuthenticateHeader.getUriParam());
  }

  @Test
  void testGetUriParam6() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addParameter("uri", "42");
    wwwAuthenticateHeader.addAutsParam("uri");

    // Act and Assert
    assertEquals("42", wwwAuthenticateHeader.getUriParam());
  }

  @Test
  void testGetUriParam7() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addParameter("", "42");

    // Act and Assert
    assertNull(wwwAuthenticateHeader.getUriParam());
  }

  @Test
  void testGetUriParam8() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addUnquotedParameter("uri", "");

    // Act and Assert
    assertEquals("", wwwAuthenticateHeader.getUriParam());
  }

  @Test
  void testGetUsernameParam() {
    // Arrange, Act and Assert
    assertNull((new WwwAuthenticateHeader("42")).getUsernameParam());
    assertNull((new WwwAuthenticateHeader("")).getUsernameParam());
    assertNull((new WwwAuthenticateHeader("Auth scheme", new Vector(1))).getUsernameParam());
  }

  @Test
  void testGetUsernameParam2() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addAutsParam("username");

    // Act and Assert
    assertNull(wwwAuthenticateHeader.getUsernameParam());
  }

  @Test
  void testGetUsernameParam3() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addParameter("username", "42");

    // Act and Assert
    assertEquals("42", wwwAuthenticateHeader.getUsernameParam());
  }

  @Test
  void testGetUsernameParam4() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addUnquotedParameter("username", "42");

    // Act and Assert
    assertEquals("42", wwwAuthenticateHeader.getUsernameParam());
  }

  @Test
  void testGetUsernameParam5() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addAutsParam("username");
    wwwAuthenticateHeader.addAutsParam("username");

    // Act and Assert
    assertNull(wwwAuthenticateHeader.getUsernameParam());
  }

  @Test
  void testGetUsernameParam6() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addParameter("username", "42");
    wwwAuthenticateHeader.addAutsParam("username");

    // Act and Assert
    assertEquals("42", wwwAuthenticateHeader.getUsernameParam());
  }

  @Test
  void testGetUsernameParam7() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addParameter("", "42");

    // Act and Assert
    assertNull(wwwAuthenticateHeader.getUsernameParam());
  }

  @Test
  void testGetUsernameParam8() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addUnquotedParameter("username", "");

    // Act and Assert
    assertEquals("", wwwAuthenticateHeader.getUsernameParam());
  }

  @Test
  void testHasAlgorithmParam() {
    // Arrange, Act and Assert
    assertFalse((new WwwAuthenticateHeader("42")).hasAlgorithmParam());
    assertFalse((new WwwAuthenticateHeader("")).hasAlgorithmParam());
    assertFalse((new WwwAuthenticateHeader("Auth scheme", new Vector(1))).hasAlgorithmParam());
  }

  @Test
  void testHasAlgorithmParam2() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addAutsParam("algorithm");

    // Act and Assert
    assertFalse(wwwAuthenticateHeader.hasAlgorithmParam());
  }

  @Test
  void testHasAlgorithmParam3() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addParameter("algorithm", "42");

    // Act and Assert
    assertTrue(wwwAuthenticateHeader.hasAlgorithmParam());
  }

  @Test
  void testHasAlgorithmParam4() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addAutsParam("algorithm");
    wwwAuthenticateHeader.addAutsParam("algorithm");

    // Act and Assert
    assertFalse(wwwAuthenticateHeader.hasAlgorithmParam());
  }

  @Test
  void testHasAlgorithmParam5() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addParameter("", "42");

    // Act and Assert
    assertFalse(wwwAuthenticateHeader.hasAlgorithmParam());
  }

  @Test
  void testHasAutsParam() {
    // Arrange, Act and Assert
    assertFalse((new WwwAuthenticateHeader("42")).hasAutsParam());
    assertFalse((new WwwAuthenticateHeader("")).hasAutsParam());
    assertFalse((new WwwAuthenticateHeader("Auth scheme", new Vector(1))).hasAutsParam());
  }

  @Test
  void testHasAutsParam2() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addAutsParam("auts");

    // Act and Assert
    assertTrue(wwwAuthenticateHeader.hasAutsParam());
  }

  @Test
  void testHasAutsParam3() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addCnonceParam("auts");

    // Act and Assert
    assertFalse(wwwAuthenticateHeader.hasAutsParam());
  }

  @Test
  void testHasAutsParam4() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addCnonceParam("auts");
    wwwAuthenticateHeader.addAutsParam("auts");

    // Act and Assert
    assertTrue(wwwAuthenticateHeader.hasAutsParam());
  }

  @Test
  void testHasAutsParam5() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addParameter("", "42");

    // Act and Assert
    assertFalse(wwwAuthenticateHeader.hasAutsParam());
  }

  @Test
  void testHasCnonceParam() {
    // Arrange, Act and Assert
    assertFalse((new WwwAuthenticateHeader("42")).hasCnonceParam());
    assertFalse((new WwwAuthenticateHeader("")).hasCnonceParam());
    assertFalse((new WwwAuthenticateHeader("Auth scheme", new Vector(1))).hasCnonceParam());
  }

  @Test
  void testHasCnonceParam2() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addAutsParam("cnonce");

    // Act and Assert
    assertFalse(wwwAuthenticateHeader.hasCnonceParam());
  }

  @Test
  void testHasCnonceParam3() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addCnonceParam("cnonce");

    // Act and Assert
    assertTrue(wwwAuthenticateHeader.hasCnonceParam());
  }

  @Test
  void testHasCnonceParam4() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addAutsParam("cnonce");
    wwwAuthenticateHeader.addAutsParam("cnonce");

    // Act and Assert
    assertFalse(wwwAuthenticateHeader.hasCnonceParam());
  }

  @Test
  void testHasCnonceParam5() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addParameter("", "42");

    // Act and Assert
    assertFalse(wwwAuthenticateHeader.hasCnonceParam());
  }

  @Test
  void testHasNcParam() {
    // Arrange, Act and Assert
    assertFalse((new WwwAuthenticateHeader("42")).hasNcParam());
    assertFalse((new WwwAuthenticateHeader("")).hasNcParam());
    assertFalse((new WwwAuthenticateHeader("Auth scheme", new Vector(1))).hasNcParam());
  }

  @Test
  void testHasNcParam2() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addAutsParam("nc");

    // Act and Assert
    assertFalse(wwwAuthenticateHeader.hasNcParam());
  }

  @Test
  void testHasNcParam3() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addParameter("nc", "42");

    // Act and Assert
    assertTrue(wwwAuthenticateHeader.hasNcParam());
  }

  @Test
  void testHasNcParam4() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addAutsParam("nc");
    wwwAuthenticateHeader.addAutsParam("nc");

    // Act and Assert
    assertFalse(wwwAuthenticateHeader.hasNcParam());
  }

  @Test
  void testHasNcParam5() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addParameter("", "42");

    // Act and Assert
    assertFalse(wwwAuthenticateHeader.hasNcParam());
  }

  @Test
  void testHasNextnonceParam() {
    // Arrange, Act and Assert
    assertFalse((new WwwAuthenticateHeader("42")).hasNextnonceParam());
    assertFalse((new WwwAuthenticateHeader("")).hasNextnonceParam());
    assertFalse((new WwwAuthenticateHeader("Auth scheme", new Vector(1))).hasNextnonceParam());
  }

  @Test
  void testHasNextnonceParam2() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addAutsParam("nextnonce");

    // Act and Assert
    assertFalse(wwwAuthenticateHeader.hasNextnonceParam());
  }

  @Test
  void testHasNextnonceParam3() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addParameter("nextnonce", "42");

    // Act and Assert
    assertTrue(wwwAuthenticateHeader.hasNextnonceParam());
  }

  @Test
  void testHasNextnonceParam4() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addAutsParam("nextnonce");
    wwwAuthenticateHeader.addAutsParam("nextnonce");

    // Act and Assert
    assertFalse(wwwAuthenticateHeader.hasNextnonceParam());
  }

  @Test
  void testHasNextnonceParam5() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addParameter("", "42");

    // Act and Assert
    assertFalse(wwwAuthenticateHeader.hasNextnonceParam());
  }

  @Test
  void testHasNonceParam() {
    // Arrange, Act and Assert
    assertFalse((new WwwAuthenticateHeader("42")).hasNonceParam());
    assertFalse((new WwwAuthenticateHeader("")).hasNonceParam());
    assertFalse((new WwwAuthenticateHeader("Auth scheme", new Vector(1))).hasNonceParam());
  }

  @Test
  void testHasNonceParam2() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addAutsParam("nonce");

    // Act and Assert
    assertFalse(wwwAuthenticateHeader.hasNonceParam());
  }

  @Test
  void testHasNonceParam3() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addParameter("nonce", "42");

    // Act and Assert
    assertTrue(wwwAuthenticateHeader.hasNonceParam());
  }

  @Test
  void testHasNonceParam4() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addAutsParam("nonce");
    wwwAuthenticateHeader.addAutsParam("nonce");

    // Act and Assert
    assertFalse(wwwAuthenticateHeader.hasNonceParam());
  }

  @Test
  void testHasNonceParam5() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addParameter("", "42");

    // Act and Assert
    assertFalse(wwwAuthenticateHeader.hasNonceParam());
  }

  @Test
  void testHasOpaqueParam() {
    // Arrange, Act and Assert
    assertFalse((new WwwAuthenticateHeader("42")).hasOpaqueParam());
    assertFalse((new WwwAuthenticateHeader("")).hasOpaqueParam());
    assertFalse((new WwwAuthenticateHeader("Auth scheme", new Vector(1))).hasOpaqueParam());
  }

  @Test
  void testHasOpaqueParam2() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addAutsParam("opaque");

    // Act and Assert
    assertFalse(wwwAuthenticateHeader.hasOpaqueParam());
  }

  @Test
  void testHasOpaqueParam3() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addParameter("opaque", "42");

    // Act and Assert
    assertTrue(wwwAuthenticateHeader.hasOpaqueParam());
  }

  @Test
  void testHasOpaqueParam4() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addAutsParam("opaque");
    wwwAuthenticateHeader.addAutsParam("opaque");

    // Act and Assert
    assertFalse(wwwAuthenticateHeader.hasOpaqueParam());
  }

  @Test
  void testHasOpaqueParam5() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addParameter("", "42");

    // Act and Assert
    assertFalse(wwwAuthenticateHeader.hasOpaqueParam());
  }

  @Test
  void testHasParameter() {
    // Arrange, Act and Assert
    assertFalse((new WwwAuthenticateHeader("42")).hasParameter("Param name"));
    assertFalse((new WwwAuthenticateHeader("")).hasParameter("Param name"));
    assertFalse((new WwwAuthenticateHeader("Auth scheme", new Vector(1))).hasParameter("Param name"));
  }

  @Test
  void testHasParameter2() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addAutsParam("Unquoted auts");

    // Act and Assert
    assertFalse(wwwAuthenticateHeader.hasParameter("Param name"));
  }

  @Test
  void testHasParameter3() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addAutsParam("auts");
    wwwAuthenticateHeader.addAutsParam("Unquoted auts");

    // Act and Assert
    assertFalse(wwwAuthenticateHeader.hasParameter("Param name"));
  }

  @Test
  void testHasParameter4() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addParameter("", "42");

    // Act and Assert
    assertFalse(wwwAuthenticateHeader.hasParameter("Param name"));
  }

  @Test
  void testHasParameter5() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("");
    wwwAuthenticateHeader.addAutsParam("Unquoted auts");

    // Act and Assert
    assertFalse(wwwAuthenticateHeader.hasParameter("Param name"));
  }

  @Test
  void testHasQopOptionsParam() {
    // Arrange, Act and Assert
    assertFalse((new WwwAuthenticateHeader("42")).hasQopOptionsParam());
    assertFalse((new WwwAuthenticateHeader("")).hasQopOptionsParam());
    assertFalse((new WwwAuthenticateHeader("Auth scheme", new Vector(1))).hasQopOptionsParam());
  }

  @Test
  void testHasQopOptionsParam2() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addAutsParam("qop");

    // Act and Assert
    assertFalse(wwwAuthenticateHeader.hasQopOptionsParam());
  }

  @Test
  void testHasQopOptionsParam3() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addParameter("qop", "42");

    // Act and Assert
    assertTrue(wwwAuthenticateHeader.hasQopOptionsParam());
  }

  @Test
  void testHasQopOptionsParam4() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addAutsParam("qop");
    wwwAuthenticateHeader.addAutsParam("qop");

    // Act and Assert
    assertFalse(wwwAuthenticateHeader.hasQopOptionsParam());
  }

  @Test
  void testHasQopOptionsParam5() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addParameter("", "42");

    // Act and Assert
    assertFalse(wwwAuthenticateHeader.hasQopOptionsParam());
  }

  @Test
  void testHasQopParam() {
    // Arrange, Act and Assert
    assertFalse((new WwwAuthenticateHeader("42")).hasQopParam());
    assertFalse((new WwwAuthenticateHeader("")).hasQopParam());
    assertFalse((new WwwAuthenticateHeader("Auth scheme", new Vector(1))).hasQopParam());
  }

  @Test
  void testHasQopParam2() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addAutsParam("qop");

    // Act and Assert
    assertFalse(wwwAuthenticateHeader.hasQopParam());
  }

  @Test
  void testHasQopParam3() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addParameter("qop", "42");

    // Act and Assert
    assertTrue(wwwAuthenticateHeader.hasQopParam());
  }

  @Test
  void testHasQopParam4() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addAutsParam("qop");
    wwwAuthenticateHeader.addAutsParam("qop");

    // Act and Assert
    assertFalse(wwwAuthenticateHeader.hasQopParam());
  }

  @Test
  void testHasQopParam5() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addParameter("", "42");

    // Act and Assert
    assertFalse(wwwAuthenticateHeader.hasQopParam());
  }

  @Test
  void testHasRealmParam() {
    // Arrange, Act and Assert
    assertFalse((new WwwAuthenticateHeader("42")).hasRealmParam());
    assertFalse((new WwwAuthenticateHeader("")).hasRealmParam());
    assertFalse((new WwwAuthenticateHeader("Auth scheme", new Vector(1))).hasRealmParam());
  }

  @Test
  void testHasRealmParam2() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addAutsParam("realm");

    // Act and Assert
    assertFalse(wwwAuthenticateHeader.hasRealmParam());
  }

  @Test
  void testHasRealmParam3() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addParameter("realm", "42");

    // Act and Assert
    assertTrue(wwwAuthenticateHeader.hasRealmParam());
  }

  @Test
  void testHasRealmParam4() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addAutsParam("realm");
    wwwAuthenticateHeader.addAutsParam("realm");

    // Act and Assert
    assertFalse(wwwAuthenticateHeader.hasRealmParam());
  }

  @Test
  void testHasRealmParam5() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addParameter("", "42");

    // Act and Assert
    assertFalse(wwwAuthenticateHeader.hasRealmParam());
  }

  @Test
  void testHasResponseParam() {
    // Arrange, Act and Assert
    assertFalse((new WwwAuthenticateHeader("42")).hasResponseParam());
    assertFalse((new WwwAuthenticateHeader("")).hasResponseParam());
    assertFalse((new WwwAuthenticateHeader("Auth scheme", new Vector(1))).hasResponseParam());
  }

  @Test
  void testHasResponseParam2() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addAutsParam("response");

    // Act and Assert
    assertFalse(wwwAuthenticateHeader.hasResponseParam());
  }

  @Test
  void testHasResponseParam3() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addParameter("response", "42");

    // Act and Assert
    assertTrue(wwwAuthenticateHeader.hasResponseParam());
  }

  @Test
  void testHasResponseParam4() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addAutsParam("response");
    wwwAuthenticateHeader.addAutsParam("response");

    // Act and Assert
    assertFalse(wwwAuthenticateHeader.hasResponseParam());
  }

  @Test
  void testHasResponseParam5() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addParameter("", "42");

    // Act and Assert
    assertFalse(wwwAuthenticateHeader.hasResponseParam());
  }

  @Test
  void testHasRspauthParam() {
    // Arrange, Act and Assert
    assertFalse((new WwwAuthenticateHeader("42")).hasRspauthParam());
    assertFalse((new WwwAuthenticateHeader("")).hasRspauthParam());
    assertFalse((new WwwAuthenticateHeader("Auth scheme", new Vector(1))).hasRspauthParam());
  }

  @Test
  void testHasRspauthParam2() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addAutsParam("rspauth");

    // Act and Assert
    assertFalse(wwwAuthenticateHeader.hasRspauthParam());
  }

  @Test
  void testHasRspauthParam3() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addParameter("rspauth", "42");

    // Act and Assert
    assertTrue(wwwAuthenticateHeader.hasRspauthParam());
  }

  @Test
  void testHasRspauthParam4() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addAutsParam("rspauth");
    wwwAuthenticateHeader.addAutsParam("rspauth");

    // Act and Assert
    assertFalse(wwwAuthenticateHeader.hasRspauthParam());
  }

  @Test
  void testHasRspauthParam5() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addParameter("", "42");

    // Act and Assert
    assertFalse(wwwAuthenticateHeader.hasRspauthParam());
  }

  @Test
  void testHasUriParam() {
    // Arrange, Act and Assert
    assertFalse((new WwwAuthenticateHeader("42")).hasUriParam());
    assertFalse((new WwwAuthenticateHeader("")).hasUriParam());
    assertFalse((new WwwAuthenticateHeader("Auth scheme", new Vector(1))).hasUriParam());
  }

  @Test
  void testHasUriParam2() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addAutsParam("uri");

    // Act and Assert
    assertFalse(wwwAuthenticateHeader.hasUriParam());
  }

  @Test
  void testHasUriParam3() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addParameter("uri", "42");

    // Act and Assert
    assertTrue(wwwAuthenticateHeader.hasUriParam());
  }

  @Test
  void testHasUriParam4() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addAutsParam("uri");
    wwwAuthenticateHeader.addAutsParam("uri");

    // Act and Assert
    assertFalse(wwwAuthenticateHeader.hasUriParam());
  }

  @Test
  void testHasUriParam5() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addParameter("", "42");

    // Act and Assert
    assertFalse(wwwAuthenticateHeader.hasUriParam());
  }

  @Test
  void testHasUsernameParam() {
    // Arrange, Act and Assert
    assertFalse((new WwwAuthenticateHeader("42")).hasUsernameParam());
    assertFalse((new WwwAuthenticateHeader("")).hasUsernameParam());
    assertFalse((new WwwAuthenticateHeader("Auth scheme", new Vector(1))).hasUsernameParam());
  }

  @Test
  void testHasUsernameParam2() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addAutsParam("username");

    // Act and Assert
    assertFalse(wwwAuthenticateHeader.hasUsernameParam());
  }

  @Test
  void testHasUsernameParam3() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addParameter("username", "42");

    // Act and Assert
    assertTrue(wwwAuthenticateHeader.hasUsernameParam());
  }

  @Test
  void testHasUsernameParam4() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addAutsParam("username");
    wwwAuthenticateHeader.addAutsParam("username");

    // Act and Assert
    assertFalse(wwwAuthenticateHeader.hasUsernameParam());
  }

  @Test
  void testHasUsernameParam5() {
    // Arrange
    WwwAuthenticateHeader wwwAuthenticateHeader = new WwwAuthenticateHeader("42");
    wwwAuthenticateHeader.addParameter("", "42");

    // Act and Assert
    assertFalse(wwwAuthenticateHeader.hasUsernameParam());
  }
}

