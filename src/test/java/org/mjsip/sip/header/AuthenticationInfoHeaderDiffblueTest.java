package org.mjsip.sip.header;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Vector;
import org.junit.jupiter.api.Test;

class AuthenticationInfoHeaderDiffblueTest {
  @Test
  void testAddAlgorithParam() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader("42");

    // Act
    authenticationInfoHeader.addAlgorithParam("Algorithm");

    // Assert
    assertEquals("42 algorithm=Algorithm", authenticationInfoHeader.getValue());
  }

  @Test
  void testAddAlgorithParam2() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader("42");
    authenticationInfoHeader.addAutsParam("algorithm");

    // Act
    authenticationInfoHeader.addAlgorithParam("Algorithm");

    // Assert
    assertEquals("42 auts=\"algorithm\", algorithm=Algorithm", authenticationInfoHeader.getValue());
  }

  @Test
  void testAddAutsParam() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader("42");

    // Act
    authenticationInfoHeader.addAutsParam("Unquoted auts");

    // Assert
    assertEquals("42 auts=\"Unquoted auts\"", authenticationInfoHeader.getValue());
  }

  @Test
  void testAddAutsParam2() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader("42");
    authenticationInfoHeader.addAutsParam("auts");

    // Act
    authenticationInfoHeader.addAutsParam("Unquoted auts");

    // Assert
    assertEquals("42 auts=\"auts\", auts=\"Unquoted auts\"", authenticationInfoHeader.getValue());
  }

  @Test
  void testAddCnonceParam() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader("42");

    // Act
    authenticationInfoHeader.addCnonceParam("Unquoted cnonce");

    // Assert
    assertEquals("42 cnonce=\"Unquoted cnonce\"", authenticationInfoHeader.getValue());
  }

  @Test
  void testAddCnonceParam2() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader("42");
    authenticationInfoHeader.addAutsParam("cnonce");

    // Act
    authenticationInfoHeader.addCnonceParam("Unquoted cnonce");

    // Assert
    assertEquals("42 auts=\"cnonce\", cnonce=\"Unquoted cnonce\"", authenticationInfoHeader.getValue());
  }

  @Test
  void testAddNcParam() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader("42");

    // Act
    authenticationInfoHeader.addNcParam("Nc");

    // Assert
    assertEquals("42 nc=Nc", authenticationInfoHeader.getValue());
  }

  @Test
  void testAddNcParam2() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader("42");
    authenticationInfoHeader.addAutsParam("nc");

    // Act
    authenticationInfoHeader.addNcParam("Nc");

    // Assert
    assertEquals("42 auts=\"nc\", nc=Nc", authenticationInfoHeader.getValue());
  }

  @Test
  void testAddNextnonceParam() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader("42");

    // Act
    authenticationInfoHeader.addNextnonceParam("Unquoted nextnonce");

    // Assert
    assertEquals("42 nextnonce=\"Unquoted nextnonce\"", authenticationInfoHeader.getValue());
  }

  @Test
  void testAddNextnonceParam2() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader("42");
    authenticationInfoHeader.addAutsParam("nextnonce");

    // Act
    authenticationInfoHeader.addNextnonceParam("Unquoted nextnonce");

    // Assert
    assertEquals("42 auts=\"nextnonce\", nextnonce=\"Unquoted nextnonce\"", authenticationInfoHeader.getValue());
  }

  @Test
  void testAddNonceParam() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader("42");

    // Act
    authenticationInfoHeader.addNonceParam("Unquoted nonce");

    // Assert
    assertEquals("42 nonce=\"Unquoted nonce\"", authenticationInfoHeader.getValue());
  }

  @Test
  void testAddNonceParam2() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader("42");
    authenticationInfoHeader.addAutsParam("nonce");

    // Act
    authenticationInfoHeader.addNonceParam("Unquoted nonce");

    // Assert
    assertEquals("42 auts=\"nonce\", nonce=\"Unquoted nonce\"", authenticationInfoHeader.getValue());
  }

  @Test
  void testAddOpaqueParam() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader("42");

    // Act
    authenticationInfoHeader.addOpaqueParam("Unquoted opaque");

    // Assert
    assertEquals("42 opaque=\"Unquoted opaque\"", authenticationInfoHeader.getValue());
  }

  @Test
  void testAddOpaqueParam2() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader("42");
    authenticationInfoHeader.addAutsParam("opaque");

    // Act
    authenticationInfoHeader.addOpaqueParam("Unquoted opaque");

    // Assert
    assertEquals("42 auts=\"opaque\", opaque=\"Unquoted opaque\"", authenticationInfoHeader.getValue());
  }

  @Test
  void testAddParameter() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader("42");

    // Act
    authenticationInfoHeader.addParameter("Param name", "42");

    // Assert
    assertEquals("42 Param name=42", authenticationInfoHeader.getValue());
  }

  @Test
  void testAddParameter2() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader("42");
    authenticationInfoHeader.addAutsParam("Unquoted auts");

    // Act
    authenticationInfoHeader.addParameter("Param name", "42");

    // Assert
    assertEquals("42 auts=\"Unquoted auts\", Param name=42", authenticationInfoHeader.getValue());
  }

  @Test
  void testAddParameter3() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader("42");

    // Act
    authenticationInfoHeader.addParameter("auts", "42");

    // Assert
    assertEquals("42 auts=\"42\"", authenticationInfoHeader.getValue());
  }

  @Test
  void testAddParameter4() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader("42");
    authenticationInfoHeader.addAutsParam("Unquoted auts");

    // Act
    authenticationInfoHeader.addParameter("auts", "42");

    // Assert
    assertEquals("42 auts=\"Unquoted auts\", auts=\"42\"", authenticationInfoHeader.getValue());
  }

  @Test
  void testAddQopOptionsParam() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader("42");

    // Act
    authenticationInfoHeader.addQopOptionsParam("Unquoted qop options");

    // Assert
    assertEquals("42 qop=\"Unquoted qop options\"", authenticationInfoHeader.getValue());
  }

  @Test
  void testAddQopOptionsParam2() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader("42");
    authenticationInfoHeader.addAutsParam("qop");

    // Act
    authenticationInfoHeader.addQopOptionsParam("Unquoted qop options");

    // Assert
    assertEquals("42 auts=\"qop\", qop=\"Unquoted qop options\"", authenticationInfoHeader.getValue());
  }

  @Test
  void testAddQopParam() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader("42");

    // Act
    authenticationInfoHeader.addQopParam("Qop");

    // Assert
    assertEquals("42 qop=Qop", authenticationInfoHeader.getValue());
  }

  @Test
  void testAddQopParam2() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader("42");
    authenticationInfoHeader.addAutsParam("qop");

    // Act
    authenticationInfoHeader.addQopParam("Qop");

    // Assert
    assertEquals("42 auts=\"qop\", qop=Qop", authenticationInfoHeader.getValue());
  }

  @Test
  void testAddQuotedParameter() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader("42");

    // Act
    authenticationInfoHeader.addQuotedParameter("Param name", "42");

    // Assert
    assertEquals("42 Param name=\"42\"", authenticationInfoHeader.getValue());
  }

  @Test
  void testAddQuotedParameter2() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader("42");
    authenticationInfoHeader.addAutsParam("Unquoted auts");

    // Act
    authenticationInfoHeader.addQuotedParameter("Param name", "42");

    // Assert
    assertEquals("42 auts=\"Unquoted auts\", Param name=\"42\"", authenticationInfoHeader.getValue());
  }

  @Test
  void testAddRealmParam() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader("42");

    // Act
    authenticationInfoHeader.addRealmParam("Unquoted realm");

    // Assert
    assertEquals("42 realm=\"Unquoted realm\"", authenticationInfoHeader.getValue());
  }

  @Test
  void testAddRealmParam2() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader("42");
    authenticationInfoHeader.addAutsParam("realm");

    // Act
    authenticationInfoHeader.addRealmParam("Unquoted realm");

    // Assert
    assertEquals("42 auts=\"realm\", realm=\"Unquoted realm\"", authenticationInfoHeader.getValue());
  }

  @Test
  void testAddResponseParam() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader("42");

    // Act
    authenticationInfoHeader.addResponseParam("Unquoted response");

    // Assert
    assertEquals("42 response=\"Unquoted response\"", authenticationInfoHeader.getValue());
  }

  @Test
  void testAddResponseParam2() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader("42");
    authenticationInfoHeader.addAutsParam("response");

    // Act
    authenticationInfoHeader.addResponseParam("Unquoted response");

    // Assert
    assertEquals("42 auts=\"response\", response=\"Unquoted response\"", authenticationInfoHeader.getValue());
  }

  @Test
  void testAddRspauthParam() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader("42");

    // Act
    authenticationInfoHeader.addRspauthParam("Unquoted rspauth");

    // Assert
    assertEquals("42 rspauth=\"Unquoted rspauth\"", authenticationInfoHeader.getValue());
  }

  @Test
  void testAddRspauthParam2() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader("42");
    authenticationInfoHeader.addAutsParam("rspauth");

    // Act
    authenticationInfoHeader.addRspauthParam("Unquoted rspauth");

    // Assert
    assertEquals("42 auts=\"rspauth\", rspauth=\"Unquoted rspauth\"", authenticationInfoHeader.getValue());
  }

  @Test
  void testAddUnquotedParameter() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader("42");

    // Act
    authenticationInfoHeader.addUnquotedParameter("Param name", "42");

    // Assert
    assertEquals("42 Param name=42", authenticationInfoHeader.getValue());
  }

  @Test
  void testAddUnquotedParameter2() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader("42");
    authenticationInfoHeader.addAutsParam("Unquoted auts");

    // Act
    authenticationInfoHeader.addUnquotedParameter("Param name", "42");

    // Assert
    assertEquals("42 auts=\"Unquoted auts\", Param name=42", authenticationInfoHeader.getValue());
  }

  @Test
  void testAddUriParam() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader("42");

    // Act
    authenticationInfoHeader.addUriParam("Unquoted uri");

    // Assert
    assertEquals("42 uri=\"Unquoted uri\"", authenticationInfoHeader.getValue());
  }

  @Test
  void testAddUriParam2() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader("42");
    authenticationInfoHeader.addAutsParam("uri");

    // Act
    authenticationInfoHeader.addUriParam("Unquoted uri");

    // Assert
    assertEquals("42 auts=\"uri\", uri=\"Unquoted uri\"", authenticationInfoHeader.getValue());
  }

  @Test
  void testAddUsernameParam() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader("42");

    // Act
    authenticationInfoHeader.addUsernameParam("janedoe");

    // Assert
    assertEquals("42 username=\"janedoe\"", authenticationInfoHeader.getValue());
  }

  @Test
  void testAddUsernameParam2() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader("42");
    authenticationInfoHeader.addAutsParam("username");

    // Act
    authenticationInfoHeader.addUsernameParam("janedoe");

    // Assert
    assertEquals("42 auts=\"username\", username=\"janedoe\"", authenticationInfoHeader.getValue());
  }

  @Test
  void testConstructor() {
    // Arrange, Act and Assert
    assertNull((new AuthenticationInfoHeader()).getAuthScheme());
    assertNull((new AuthenticationInfoHeader("42")).getAuthScheme());
  }

  @Test
  void testConstructor2() {
    // Arrange and Act
    AuthenticationInfoHeader actualAuthenticationInfoHeader = new AuthenticationInfoHeader(new Vector(1));

    // Assert
    assertEquals("", actualAuthenticationInfoHeader.getValue());
    assertEquals(CoreSipHeaders.Authentication_Info, actualAuthenticationInfoHeader.getName());
  }

  @Test
  void testConstructor3() {
    // Arrange
    Vector vector = new Vector(1);
    vector.add("42");

    // Act
    AuthenticationInfoHeader actualAuthenticationInfoHeader = new AuthenticationInfoHeader(vector);

    // Assert
    assertEquals(" 42", actualAuthenticationInfoHeader.getValue());
    assertEquals(CoreSipHeaders.Authentication_Info, actualAuthenticationInfoHeader.getName());
  }

  @Test
  void testConstructor4() {
    // Arrange
    Vector vector = new Vector(1);
    vector.add("42");
    vector.add("42");

    // Act
    AuthenticationInfoHeader actualAuthenticationInfoHeader = new AuthenticationInfoHeader(vector);

    // Assert
    assertEquals(" 42, 42", actualAuthenticationInfoHeader.getValue());
    assertEquals(CoreSipHeaders.Authentication_Info, actualAuthenticationInfoHeader.getName());
  }

  @Test
  void testConstructor5() {
    // Arrange
    Vector vector = new Vector(1);
    vector.add("foo");

    // Act
    AuthenticationInfoHeader actualAuthenticationInfoHeader = new AuthenticationInfoHeader(vector);

    // Assert
    assertEquals(" foo", actualAuthenticationInfoHeader.getValue());
    assertEquals(CoreSipHeaders.Authentication_Info, actualAuthenticationInfoHeader.getName());
  }

  @Test
  void testConstructor6() {
    // Arrange and Act
    AuthenticationInfoHeader actualAuthenticationInfoHeader = new AuthenticationInfoHeader(new Header("Hname", "42"));

    // Assert
    assertEquals("42", actualAuthenticationInfoHeader.getValue());
    assertEquals("Hname", actualAuthenticationInfoHeader.getName());
  }

  @Test
  void testConstructor7() {
    // Arrange
    Header header = new Header("Hname", "42");
    header.setValue("42");

    // Act
    AuthenticationInfoHeader actualAuthenticationInfoHeader = new AuthenticationInfoHeader(header);

    // Assert
    assertEquals("42", actualAuthenticationInfoHeader.getValue());
    assertEquals("Hname", actualAuthenticationInfoHeader.getName());
  }

  @Test
  void testHasParameter() {
    // Arrange, Act and Assert
    assertFalse((new AuthenticationInfoHeader("42")).hasParameter("Param name"));
    assertFalse((new AuthenticationInfoHeader("Param name")).hasParameter("Param name"));
  }

  @Test
  void testHasParameter2() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader("42");
    authenticationInfoHeader.addAutsParam("42");

    // Act and Assert
    assertFalse(authenticationInfoHeader.hasParameter("Param name"));
  }

  @Test
  void testHasParameter3() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader();
    authenticationInfoHeader.addAutsParam("42");

    // Act and Assert
    assertFalse(authenticationInfoHeader.hasParameter("Param name"));
  }

  @Test
  void testHasParameter4() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader("42");
    authenticationInfoHeader.addAutsParam("42");
    authenticationInfoHeader.addAutsParam("42");

    // Act and Assert
    assertFalse(authenticationInfoHeader.hasParameter("Param name"));
  }

  @Test
  void testGetParameter() {
    // Arrange, Act and Assert
    assertNull((new AuthenticationInfoHeader("42")).getParameter("Param name"));
    assertNull((new AuthenticationInfoHeader("Param name")).getParameter("Param name"));
  }

  @Test
  void testGetParameter2() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader("42");
    authenticationInfoHeader.addAutsParam("42");

    // Act and Assert
    assertNull(authenticationInfoHeader.getParameter("Param name"));
  }

  @Test
  void testGetParameter3() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader();
    authenticationInfoHeader.addAutsParam("42");

    // Act and Assert
    assertNull(authenticationInfoHeader.getParameter("Param name"));
  }

  @Test
  void testGetParameter4() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader("42");
    authenticationInfoHeader.addAutsParam("42");
    authenticationInfoHeader.addAutsParam("42");

    // Act and Assert
    assertNull(authenticationInfoHeader.getParameter("Param name"));
  }

  @Test
  void testGetParameterNames() {
    // Arrange and Act
    Vector actualParameterNames = (new AuthenticationInfoHeader("42")).getParameterNames();

    // Assert
    assertEquals(1, actualParameterNames.size());
    assertEquals("42", actualParameterNames.get(0));
  }

  @Test
  void testGetParameterNames2() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader("42");
    authenticationInfoHeader.addAutsParam("Unquoted auts");

    // Act
    Vector actualParameterNames = authenticationInfoHeader.getParameterNames();

    // Assert
    assertEquals(1, actualParameterNames.size());
    assertEquals("42", actualParameterNames.get(0));
  }

  @Test
  void testGetParameterNames3() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader();
    authenticationInfoHeader.addAutsParam("Unquoted auts");

    // Act
    Vector actualParameterNames = authenticationInfoHeader.getParameterNames();

    // Assert
    assertEquals(1, actualParameterNames.size());
    assertEquals("auts", actualParameterNames.get(0));
  }

  @Test
  void testGetParameterNames4() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader("42");
    authenticationInfoHeader.addAutsParam("Unquoted auts");
    authenticationInfoHeader.addAutsParam("Unquoted auts");

    // Act
    Vector actualParameterNames = authenticationInfoHeader.getParameterNames();

    // Assert
    assertEquals(2, actualParameterNames.size());
    assertEquals("42", actualParameterNames.get(0));
    assertEquals("auts", actualParameterNames.get(1));
  }

  @Test
  void testGetAlgorithParam() {
    // Arrange, Act and Assert
    assertNull((new AuthenticationInfoHeader("42")).getAlgorithParam());
    assertEquals("", (new AuthenticationInfoHeader("algorithm")).getAlgorithParam());
  }

  @Test
  void testGetAlgorithParam2() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader("42");
    authenticationInfoHeader.addAutsParam("algorithm");

    // Act and Assert
    assertNull(authenticationInfoHeader.getAlgorithParam());
  }

  @Test
  void testGetAlgorithParam3() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader();
    authenticationInfoHeader.addAutsParam("algorithm");

    // Act and Assert
    assertNull(authenticationInfoHeader.getAlgorithParam());
  }

  @Test
  void testGetAlgorithParam4() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader("42");
    authenticationInfoHeader.addAutsParam("algorithm");
    authenticationInfoHeader.addAutsParam("algorithm");

    // Act and Assert
    assertNull(authenticationInfoHeader.getAlgorithParam());
  }

  @Test
  void testGetAutsParam() {
    // Arrange, Act and Assert
    assertNull((new AuthenticationInfoHeader("42")).getAutsParam());
    assertEquals("", (new AuthenticationInfoHeader("auts")).getAutsParam());
  }

  @Test
  void testGetAutsParam2() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader("42");
    authenticationInfoHeader.addAutsParam("auts");

    // Act and Assert
    assertNull(authenticationInfoHeader.getAutsParam());
  }

  @Test
  void testGetAutsParam3() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader();
    authenticationInfoHeader.addAutsParam("auts");

    // Act and Assert
    assertEquals("auts", authenticationInfoHeader.getAutsParam());
  }

  @Test
  void testGetAutsParam4() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader("42");
    authenticationInfoHeader.addAutsParam("auts");
    authenticationInfoHeader.addAutsParam("auts");

    // Act and Assert
    assertEquals("auts", authenticationInfoHeader.getAutsParam());
  }

  @Test
  void testGetAutsParam5() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader();
    authenticationInfoHeader.addAutsParam("auts");
    authenticationInfoHeader.addAutsParam("auts");

    // Act and Assert
    assertEquals("auts", authenticationInfoHeader.getAutsParam());
  }

  @Test
  void testGetAutsParam6() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader("auts");
    authenticationInfoHeader.addNcParam("auts");

    // Act and Assert
    assertEquals("auts", authenticationInfoHeader.getAutsParam());
  }

  @Test
  void testGetAutsParam7() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader("auts");
    authenticationInfoHeader.addUnquotedParameter("auts", "Param value");

    // Act and Assert
    assertEquals("Param", authenticationInfoHeader.getAutsParam());
  }

  @Test
  void testGetAutsParam8() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader();
    authenticationInfoHeader.addParameter("", "42");
    authenticationInfoHeader.addAutsParam("auts");

    // Act and Assert
    assertNull(authenticationInfoHeader.getAutsParam());
  }

  @Test
  void testGetCnonceParam() {
    // Arrange, Act and Assert
    assertNull((new AuthenticationInfoHeader("42")).getCnonceParam());
    assertEquals("", (new AuthenticationInfoHeader("cnonce")).getCnonceParam());
  }

  @Test
  void testGetCnonceParam2() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader("42");
    authenticationInfoHeader.addAutsParam("cnonce");

    // Act and Assert
    assertNull(authenticationInfoHeader.getCnonceParam());
  }

  @Test
  void testGetCnonceParam3() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader();
    authenticationInfoHeader.addAutsParam("cnonce");

    // Act and Assert
    assertNull(authenticationInfoHeader.getCnonceParam());
  }

  @Test
  void testGetCnonceParam4() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader("42");
    authenticationInfoHeader.addAutsParam("cnonce");
    authenticationInfoHeader.addAutsParam("cnonce");

    // Act and Assert
    assertNull(authenticationInfoHeader.getCnonceParam());
  }

  @Test
  void testGetNcParam() {
    // Arrange, Act and Assert
    assertNull((new AuthenticationInfoHeader("42")).getNcParam());
    assertEquals("", (new AuthenticationInfoHeader("nc")).getNcParam());
  }

  @Test
  void testGetNcParam2() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader("42");
    authenticationInfoHeader.addAutsParam("nc");

    // Act and Assert
    assertNull(authenticationInfoHeader.getNcParam());
  }

  @Test
  void testGetNcParam3() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader();
    authenticationInfoHeader.addAutsParam("nc");

    // Act and Assert
    assertNull(authenticationInfoHeader.getNcParam());
  }

  @Test
  void testGetNcParam4() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader("42");
    authenticationInfoHeader.addAutsParam("nc");
    authenticationInfoHeader.addAutsParam("nc");

    // Act and Assert
    assertNull(authenticationInfoHeader.getNcParam());
  }

  @Test
  void testGetNextnonceParam() {
    // Arrange, Act and Assert
    assertNull((new AuthenticationInfoHeader("42")).getNextnonceParam());
    assertEquals("", (new AuthenticationInfoHeader("nextnonce")).getNextnonceParam());
  }

  @Test
  void testGetNextnonceParam2() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader("42");
    authenticationInfoHeader.addAutsParam("nextnonce");

    // Act and Assert
    assertNull(authenticationInfoHeader.getNextnonceParam());
  }

  @Test
  void testGetNextnonceParam3() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader();
    authenticationInfoHeader.addAutsParam("nextnonce");

    // Act and Assert
    assertNull(authenticationInfoHeader.getNextnonceParam());
  }

  @Test
  void testGetNextnonceParam4() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader("42");
    authenticationInfoHeader.addAutsParam("nextnonce");
    authenticationInfoHeader.addAutsParam("nextnonce");

    // Act and Assert
    assertNull(authenticationInfoHeader.getNextnonceParam());
  }

  @Test
  void testGetNonceParam() {
    // Arrange, Act and Assert
    assertNull((new AuthenticationInfoHeader("42")).getNonceParam());
    assertEquals("", (new AuthenticationInfoHeader("nonce")).getNonceParam());
  }

  @Test
  void testGetNonceParam2() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader("42");
    authenticationInfoHeader.addAutsParam("nonce");

    // Act and Assert
    assertNull(authenticationInfoHeader.getNonceParam());
  }

  @Test
  void testGetNonceParam3() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader();
    authenticationInfoHeader.addAutsParam("nonce");

    // Act and Assert
    assertNull(authenticationInfoHeader.getNonceParam());
  }

  @Test
  void testGetNonceParam4() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader("42");
    authenticationInfoHeader.addAutsParam("nonce");
    authenticationInfoHeader.addAutsParam("nonce");

    // Act and Assert
    assertNull(authenticationInfoHeader.getNonceParam());
  }

  @Test
  void testGetOpaqueParam() {
    // Arrange, Act and Assert
    assertNull((new AuthenticationInfoHeader("42")).getOpaqueParam());
    assertEquals("", (new AuthenticationInfoHeader("opaque")).getOpaqueParam());
  }

  @Test
  void testGetOpaqueParam2() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader("42");
    authenticationInfoHeader.addAutsParam("opaque");

    // Act and Assert
    assertNull(authenticationInfoHeader.getOpaqueParam());
  }

  @Test
  void testGetOpaqueParam3() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader();
    authenticationInfoHeader.addAutsParam("opaque");

    // Act and Assert
    assertNull(authenticationInfoHeader.getOpaqueParam());
  }

  @Test
  void testGetOpaqueParam4() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader("42");
    authenticationInfoHeader.addAutsParam("opaque");
    authenticationInfoHeader.addAutsParam("opaque");

    // Act and Assert
    assertNull(authenticationInfoHeader.getOpaqueParam());
  }

  @Test
  void testGetQopOptionsParam() {
    // Arrange, Act and Assert
    assertNull((new AuthenticationInfoHeader("42")).getQopOptionsParam());
    assertEquals("", (new AuthenticationInfoHeader("qop")).getQopOptionsParam());
  }

  @Test
  void testGetQopOptionsParam2() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader("42");
    authenticationInfoHeader.addAutsParam("qop");

    // Act and Assert
    assertNull(authenticationInfoHeader.getQopOptionsParam());
  }

  @Test
  void testGetQopOptionsParam3() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader();
    authenticationInfoHeader.addAutsParam("qop");

    // Act and Assert
    assertNull(authenticationInfoHeader.getQopOptionsParam());
  }

  @Test
  void testGetQopOptionsParam4() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader("42");
    authenticationInfoHeader.addAutsParam("qop");
    authenticationInfoHeader.addAutsParam("qop");

    // Act and Assert
    assertNull(authenticationInfoHeader.getQopOptionsParam());
  }

  @Test
  void testGetQopParam() {
    // Arrange, Act and Assert
    assertNull((new AuthenticationInfoHeader("42")).getQopParam());
    assertEquals("", (new AuthenticationInfoHeader("qop")).getQopParam());
  }

  @Test
  void testGetQopParam2() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader("42");
    authenticationInfoHeader.addAutsParam("qop");

    // Act and Assert
    assertNull(authenticationInfoHeader.getQopParam());
  }

  @Test
  void testGetQopParam3() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader();
    authenticationInfoHeader.addAutsParam("qop");

    // Act and Assert
    assertNull(authenticationInfoHeader.getQopParam());
  }

  @Test
  void testGetQopParam4() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader("42");
    authenticationInfoHeader.addAutsParam("qop");
    authenticationInfoHeader.addAutsParam("qop");

    // Act and Assert
    assertNull(authenticationInfoHeader.getQopParam());
  }

  @Test
  void testGetRealmParam() {
    // Arrange, Act and Assert
    assertNull((new AuthenticationInfoHeader("42")).getRealmParam());
    assertEquals("", (new AuthenticationInfoHeader("realm")).getRealmParam());
  }

  @Test
  void testGetRealmParam2() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader("42");
    authenticationInfoHeader.addAutsParam("realm");

    // Act and Assert
    assertNull(authenticationInfoHeader.getRealmParam());
  }

  @Test
  void testGetRealmParam3() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader();
    authenticationInfoHeader.addAutsParam("realm");

    // Act and Assert
    assertNull(authenticationInfoHeader.getRealmParam());
  }

  @Test
  void testGetRealmParam4() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader("42");
    authenticationInfoHeader.addAutsParam("realm");
    authenticationInfoHeader.addAutsParam("realm");

    // Act and Assert
    assertNull(authenticationInfoHeader.getRealmParam());
  }

  @Test
  void testGetResponseParam() {
    // Arrange, Act and Assert
    assertNull((new AuthenticationInfoHeader("42")).getResponseParam());
    assertEquals("", (new AuthenticationInfoHeader("response")).getResponseParam());
  }

  @Test
  void testGetResponseParam2() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader("42");
    authenticationInfoHeader.addAutsParam("response");

    // Act and Assert
    assertNull(authenticationInfoHeader.getResponseParam());
  }

  @Test
  void testGetResponseParam3() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader();
    authenticationInfoHeader.addAutsParam("response");

    // Act and Assert
    assertNull(authenticationInfoHeader.getResponseParam());
  }

  @Test
  void testGetResponseParam4() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader("42");
    authenticationInfoHeader.addAutsParam("response");
    authenticationInfoHeader.addAutsParam("response");

    // Act and Assert
    assertNull(authenticationInfoHeader.getResponseParam());
  }

  @Test
  void testGetRspauthParam() {
    // Arrange, Act and Assert
    assertNull((new AuthenticationInfoHeader("42")).getRspauthParam());
    assertEquals("", (new AuthenticationInfoHeader("rspauth")).getRspauthParam());
  }

  @Test
  void testGetRspauthParam2() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader("42");
    authenticationInfoHeader.addAutsParam("rspauth");

    // Act and Assert
    assertNull(authenticationInfoHeader.getRspauthParam());
  }

  @Test
  void testGetRspauthParam3() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader();
    authenticationInfoHeader.addAutsParam("rspauth");

    // Act and Assert
    assertNull(authenticationInfoHeader.getRspauthParam());
  }

  @Test
  void testGetRspauthParam4() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader("42");
    authenticationInfoHeader.addAutsParam("rspauth");
    authenticationInfoHeader.addAutsParam("rspauth");

    // Act and Assert
    assertNull(authenticationInfoHeader.getRspauthParam());
  }

  @Test
  void testGetUriParam() {
    // Arrange, Act and Assert
    assertNull((new AuthenticationInfoHeader("42")).getUriParam());
    assertEquals("", (new AuthenticationInfoHeader("uri")).getUriParam());
  }

  @Test
  void testGetUriParam2() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader("42");
    authenticationInfoHeader.addAutsParam("uri");

    // Act and Assert
    assertNull(authenticationInfoHeader.getUriParam());
  }

  @Test
  void testGetUriParam3() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader();
    authenticationInfoHeader.addAutsParam("uri");

    // Act and Assert
    assertNull(authenticationInfoHeader.getUriParam());
  }

  @Test
  void testGetUriParam4() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader("42");
    authenticationInfoHeader.addAutsParam("uri");
    authenticationInfoHeader.addAutsParam("uri");

    // Act and Assert
    assertNull(authenticationInfoHeader.getUriParam());
  }

  @Test
  void testGetUsernameParam() {
    // Arrange, Act and Assert
    assertNull((new AuthenticationInfoHeader("42")).getUsernameParam());
    assertEquals("", (new AuthenticationInfoHeader("username")).getUsernameParam());
  }

  @Test
  void testGetUsernameParam2() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader("42");
    authenticationInfoHeader.addAutsParam("username");

    // Act and Assert
    assertNull(authenticationInfoHeader.getUsernameParam());
  }

  @Test
  void testGetUsernameParam3() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader();
    authenticationInfoHeader.addAutsParam("username");

    // Act and Assert
    assertNull(authenticationInfoHeader.getUsernameParam());
  }

  @Test
  void testGetUsernameParam4() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader("42");
    authenticationInfoHeader.addAutsParam("username");
    authenticationInfoHeader.addAutsParam("username");

    // Act and Assert
    assertNull(authenticationInfoHeader.getUsernameParam());
  }

  @Test
  void testHasAlgorithmParam() {
    // Arrange, Act and Assert
    assertFalse((new AuthenticationInfoHeader("42")).hasAlgorithmParam());
    assertTrue((new AuthenticationInfoHeader("algorithm")).hasAlgorithmParam());
  }

  @Test
  void testHasAlgorithmParam2() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader("42");
    authenticationInfoHeader.addAutsParam("algorithm");

    // Act and Assert
    assertFalse(authenticationInfoHeader.hasAlgorithmParam());
  }

  @Test
  void testHasAlgorithmParam3() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader();
    authenticationInfoHeader.addAutsParam("algorithm");

    // Act and Assert
    assertFalse(authenticationInfoHeader.hasAlgorithmParam());
  }

  @Test
  void testHasAlgorithmParam4() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader("42");
    authenticationInfoHeader.addAutsParam("algorithm");
    authenticationInfoHeader.addAutsParam("algorithm");

    // Act and Assert
    assertFalse(authenticationInfoHeader.hasAlgorithmParam());
  }

  @Test
  void testHasAutsParam() {
    // Arrange, Act and Assert
    assertFalse((new AuthenticationInfoHeader("42")).hasAutsParam());
    assertTrue((new AuthenticationInfoHeader("auts")).hasAutsParam());
  }

  @Test
  void testHasAutsParam2() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader("42");
    authenticationInfoHeader.addAutsParam("auts");

    // Act and Assert
    assertFalse(authenticationInfoHeader.hasAutsParam());
  }

  @Test
  void testHasAutsParam3() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader();
    authenticationInfoHeader.addAutsParam("auts");

    // Act and Assert
    assertTrue(authenticationInfoHeader.hasAutsParam());
  }

  @Test
  void testHasAutsParam4() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader("42");
    authenticationInfoHeader.addAutsParam("auts");
    authenticationInfoHeader.addAutsParam("auts");

    // Act and Assert
    assertTrue(authenticationInfoHeader.hasAutsParam());
  }

  @Test
  void testHasCnonceParam() {
    // Arrange, Act and Assert
    assertFalse((new AuthenticationInfoHeader("42")).hasCnonceParam());
    assertTrue((new AuthenticationInfoHeader("cnonce")).hasCnonceParam());
  }

  @Test
  void testHasCnonceParam2() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader("42");
    authenticationInfoHeader.addAutsParam("cnonce");

    // Act and Assert
    assertFalse(authenticationInfoHeader.hasCnonceParam());
  }

  @Test
  void testHasCnonceParam3() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader();
    authenticationInfoHeader.addAutsParam("cnonce");

    // Act and Assert
    assertFalse(authenticationInfoHeader.hasCnonceParam());
  }

  @Test
  void testHasCnonceParam4() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader("42");
    authenticationInfoHeader.addAutsParam("cnonce");
    authenticationInfoHeader.addAutsParam("cnonce");

    // Act and Assert
    assertFalse(authenticationInfoHeader.hasCnonceParam());
  }

  @Test
  void testHasNcParam() {
    // Arrange, Act and Assert
    assertFalse((new AuthenticationInfoHeader("42")).hasNcParam());
    assertTrue((new AuthenticationInfoHeader("nc")).hasNcParam());
  }

  @Test
  void testHasNcParam2() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader("42");
    authenticationInfoHeader.addAutsParam("nc");

    // Act and Assert
    assertFalse(authenticationInfoHeader.hasNcParam());
  }

  @Test
  void testHasNcParam3() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader();
    authenticationInfoHeader.addAutsParam("nc");

    // Act and Assert
    assertFalse(authenticationInfoHeader.hasNcParam());
  }

  @Test
  void testHasNcParam4() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader("42");
    authenticationInfoHeader.addAutsParam("nc");
    authenticationInfoHeader.addAutsParam("nc");

    // Act and Assert
    assertFalse(authenticationInfoHeader.hasNcParam());
  }

  @Test
  void testHasNextnonceParam() {
    // Arrange, Act and Assert
    assertFalse((new AuthenticationInfoHeader("42")).hasNextnonceParam());
    assertTrue((new AuthenticationInfoHeader("nextnonce")).hasNextnonceParam());
  }

  @Test
  void testHasNextnonceParam2() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader("42");
    authenticationInfoHeader.addAutsParam("nextnonce");

    // Act and Assert
    assertFalse(authenticationInfoHeader.hasNextnonceParam());
  }

  @Test
  void testHasNextnonceParam3() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader();
    authenticationInfoHeader.addAutsParam("nextnonce");

    // Act and Assert
    assertFalse(authenticationInfoHeader.hasNextnonceParam());
  }

  @Test
  void testHasNextnonceParam4() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader("42");
    authenticationInfoHeader.addAutsParam("nextnonce");
    authenticationInfoHeader.addAutsParam("nextnonce");

    // Act and Assert
    assertFalse(authenticationInfoHeader.hasNextnonceParam());
  }

  @Test
  void testHasNonceParam() {
    // Arrange, Act and Assert
    assertFalse((new AuthenticationInfoHeader("42")).hasNonceParam());
    assertTrue((new AuthenticationInfoHeader("nonce")).hasNonceParam());
  }

  @Test
  void testHasNonceParam2() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader("42");
    authenticationInfoHeader.addAutsParam("nonce");

    // Act and Assert
    assertFalse(authenticationInfoHeader.hasNonceParam());
  }

  @Test
  void testHasNonceParam3() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader();
    authenticationInfoHeader.addAutsParam("nonce");

    // Act and Assert
    assertFalse(authenticationInfoHeader.hasNonceParam());
  }

  @Test
  void testHasNonceParam4() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader("42");
    authenticationInfoHeader.addAutsParam("nonce");
    authenticationInfoHeader.addAutsParam("nonce");

    // Act and Assert
    assertFalse(authenticationInfoHeader.hasNonceParam());
  }

  @Test
  void testHasOpaqueParam() {
    // Arrange, Act and Assert
    assertFalse((new AuthenticationInfoHeader("42")).hasOpaqueParam());
    assertTrue((new AuthenticationInfoHeader("opaque")).hasOpaqueParam());
  }

  @Test
  void testHasOpaqueParam2() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader("42");
    authenticationInfoHeader.addAutsParam("opaque");

    // Act and Assert
    assertFalse(authenticationInfoHeader.hasOpaqueParam());
  }

  @Test
  void testHasOpaqueParam3() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader();
    authenticationInfoHeader.addAutsParam("opaque");

    // Act and Assert
    assertFalse(authenticationInfoHeader.hasOpaqueParam());
  }

  @Test
  void testHasOpaqueParam4() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader("42");
    authenticationInfoHeader.addAutsParam("opaque");
    authenticationInfoHeader.addAutsParam("opaque");

    // Act and Assert
    assertFalse(authenticationInfoHeader.hasOpaqueParam());
  }

  @Test
  void testHasQopOptionsParam() {
    // Arrange, Act and Assert
    assertFalse((new AuthenticationInfoHeader("42")).hasQopOptionsParam());
    assertTrue((new AuthenticationInfoHeader("qop")).hasQopOptionsParam());
  }

  @Test
  void testHasQopOptionsParam2() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader("42");
    authenticationInfoHeader.addAutsParam("qop");

    // Act and Assert
    assertFalse(authenticationInfoHeader.hasQopOptionsParam());
  }

  @Test
  void testHasQopOptionsParam3() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader();
    authenticationInfoHeader.addAutsParam("qop");

    // Act and Assert
    assertFalse(authenticationInfoHeader.hasQopOptionsParam());
  }

  @Test
  void testHasQopOptionsParam4() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader("42");
    authenticationInfoHeader.addAutsParam("qop");
    authenticationInfoHeader.addAutsParam("qop");

    // Act and Assert
    assertFalse(authenticationInfoHeader.hasQopOptionsParam());
  }

  @Test
  void testHasQopParam() {
    // Arrange, Act and Assert
    assertFalse((new AuthenticationInfoHeader("42")).hasQopParam());
    assertTrue((new AuthenticationInfoHeader("qop")).hasQopParam());
  }

  @Test
  void testHasQopParam2() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader("42");
    authenticationInfoHeader.addAutsParam("qop");

    // Act and Assert
    assertFalse(authenticationInfoHeader.hasQopParam());
  }

  @Test
  void testHasQopParam3() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader();
    authenticationInfoHeader.addAutsParam("qop");

    // Act and Assert
    assertFalse(authenticationInfoHeader.hasQopParam());
  }

  @Test
  void testHasQopParam4() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader("42");
    authenticationInfoHeader.addAutsParam("qop");
    authenticationInfoHeader.addAutsParam("qop");

    // Act and Assert
    assertFalse(authenticationInfoHeader.hasQopParam());
  }

  @Test
  void testHasRealmParam() {
    // Arrange, Act and Assert
    assertFalse((new AuthenticationInfoHeader("42")).hasRealmParam());
    assertTrue((new AuthenticationInfoHeader("realm")).hasRealmParam());
  }

  @Test
  void testHasRealmParam2() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader("42");
    authenticationInfoHeader.addAutsParam("realm");

    // Act and Assert
    assertFalse(authenticationInfoHeader.hasRealmParam());
  }

  @Test
  void testHasRealmParam3() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader();
    authenticationInfoHeader.addAutsParam("realm");

    // Act and Assert
    assertFalse(authenticationInfoHeader.hasRealmParam());
  }

  @Test
  void testHasRealmParam4() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader("42");
    authenticationInfoHeader.addAutsParam("realm");
    authenticationInfoHeader.addAutsParam("realm");

    // Act and Assert
    assertFalse(authenticationInfoHeader.hasRealmParam());
  }

  @Test
  void testHasResponseParam() {
    // Arrange, Act and Assert
    assertFalse((new AuthenticationInfoHeader("42")).hasResponseParam());
    assertTrue((new AuthenticationInfoHeader("response")).hasResponseParam());
  }

  @Test
  void testHasResponseParam2() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader("42");
    authenticationInfoHeader.addAutsParam("response");

    // Act and Assert
    assertFalse(authenticationInfoHeader.hasResponseParam());
  }

  @Test
  void testHasResponseParam3() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader();
    authenticationInfoHeader.addAutsParam("response");

    // Act and Assert
    assertFalse(authenticationInfoHeader.hasResponseParam());
  }

  @Test
  void testHasResponseParam4() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader("42");
    authenticationInfoHeader.addAutsParam("response");
    authenticationInfoHeader.addAutsParam("response");

    // Act and Assert
    assertFalse(authenticationInfoHeader.hasResponseParam());
  }

  @Test
  void testHasRspauthParam() {
    // Arrange, Act and Assert
    assertFalse((new AuthenticationInfoHeader("42")).hasRspauthParam());
    assertTrue((new AuthenticationInfoHeader("rspauth")).hasRspauthParam());
  }

  @Test
  void testHasRspauthParam2() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader("42");
    authenticationInfoHeader.addAutsParam("rspauth");

    // Act and Assert
    assertFalse(authenticationInfoHeader.hasRspauthParam());
  }

  @Test
  void testHasRspauthParam3() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader();
    authenticationInfoHeader.addAutsParam("rspauth");

    // Act and Assert
    assertFalse(authenticationInfoHeader.hasRspauthParam());
  }

  @Test
  void testHasRspauthParam4() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader("42");
    authenticationInfoHeader.addAutsParam("rspauth");
    authenticationInfoHeader.addAutsParam("rspauth");

    // Act and Assert
    assertFalse(authenticationInfoHeader.hasRspauthParam());
  }

  @Test
  void testHasUriParam() {
    // Arrange, Act and Assert
    assertFalse((new AuthenticationInfoHeader("42")).hasUriParam());
    assertTrue((new AuthenticationInfoHeader("uri")).hasUriParam());
  }

  @Test
  void testHasUriParam2() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader("42");
    authenticationInfoHeader.addAutsParam("uri");

    // Act and Assert
    assertFalse(authenticationInfoHeader.hasUriParam());
  }

  @Test
  void testHasUriParam3() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader();
    authenticationInfoHeader.addAutsParam("uri");

    // Act and Assert
    assertFalse(authenticationInfoHeader.hasUriParam());
  }

  @Test
  void testHasUriParam4() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader("42");
    authenticationInfoHeader.addAutsParam("uri");
    authenticationInfoHeader.addAutsParam("uri");

    // Act and Assert
    assertFalse(authenticationInfoHeader.hasUriParam());
  }

  @Test
  void testHasUsernameParam() {
    // Arrange, Act and Assert
    assertFalse((new AuthenticationInfoHeader("42")).hasUsernameParam());
    assertTrue((new AuthenticationInfoHeader("username")).hasUsernameParam());
  }

  @Test
  void testHasUsernameParam2() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader("42");
    authenticationInfoHeader.addAutsParam("username");

    // Act and Assert
    assertFalse(authenticationInfoHeader.hasUsernameParam());
  }

  @Test
  void testHasUsernameParam3() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader();
    authenticationInfoHeader.addAutsParam("username");

    // Act and Assert
    assertFalse(authenticationInfoHeader.hasUsernameParam());
  }

  @Test
  void testHasUsernameParam4() {
    // Arrange
    AuthenticationInfoHeader authenticationInfoHeader = new AuthenticationInfoHeader("42");
    authenticationInfoHeader.addAutsParam("username");
    authenticationInfoHeader.addAutsParam("username");

    // Act and Assert
    assertFalse(authenticationInfoHeader.hasUsernameParam());
  }
}

