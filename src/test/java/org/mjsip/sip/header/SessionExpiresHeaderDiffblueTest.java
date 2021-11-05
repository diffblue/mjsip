package org.mjsip.sip.header;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Vector;
import org.junit.jupiter.api.Test;

class SessionExpiresHeaderDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    SessionExpiresHeader actualSessionExpiresHeader = new SessionExpiresHeader(2);

    // Assert
    assertEquals("2", actualSessionExpiresHeader.getValue());
    assertEquals(SipHeaders.Session_Expires, actualSessionExpiresHeader.getName());
  }

  @Test
  void testConstructor2() {
    // Arrange and Act
    SessionExpiresHeader actualSessionExpiresHeader = new SessionExpiresHeader(2, "Refresher");

    // Assert
    assertEquals("2;refresher=Refresher", actualSessionExpiresHeader.getValue());
    assertEquals(SipHeaders.Session_Expires, actualSessionExpiresHeader.getName());
  }

  @Test
  void testConstructor3() {
    // Arrange and Act
    SessionExpiresHeader actualSessionExpiresHeader = new SessionExpiresHeader(2, null);

    // Assert
    assertEquals("2", actualSessionExpiresHeader.getValue());
    assertEquals(SipHeaders.Session_Expires, actualSessionExpiresHeader.getName());
  }

  @Test
  void testConstructor4() {
    // Arrange and Act
    SessionExpiresHeader actualSessionExpiresHeader = new SessionExpiresHeader(new Header("Hname", "42"));

    // Assert
    assertEquals("42", actualSessionExpiresHeader.getValue());
    assertEquals("Hname", actualSessionExpiresHeader.getName());
  }

  @Test
  void testConstructor5() {
    // Arrange
    Header header = new Header("Hname", "42");
    header.setValue("42");

    // Act
    SessionExpiresHeader actualSessionExpiresHeader = new SessionExpiresHeader(header);

    // Assert
    assertEquals("42", actualSessionExpiresHeader.getValue());
    assertEquals("Hname", actualSessionExpiresHeader.getName());
  }

  @Test
  void testGetDeltaSeconds() {
    // Arrange, Act and Assert
    assertEquals(2, (new SessionExpiresHeader(2)).getDeltaSeconds());
    assertEquals(2, (new SessionExpiresHeader(2, "Refresher")).getDeltaSeconds());
    assertEquals(42, (new SessionExpiresHeader(new Header(new Header("Hname", "42")))).getDeltaSeconds());
  }

  @Test
  void testGetDeltaSeconds2() {
    // Arrange
    Header header = new Header("Hname", "Hvalue");
    header.setValue("42");

    // Act and Assert
    assertEquals(42, (new SessionExpiresHeader(header)).getDeltaSeconds());
  }

  @Test
  void testHasRefresher() {
    // Arrange, Act and Assert
    assertFalse((new SessionExpiresHeader(2)).hasRefresher());
    assertTrue((new SessionExpiresHeader(2, "Refresher")).hasRefresher());
  }

  @Test
  void testHasRefresher2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertFalse((new SessionExpiresHeader(header)).hasRefresher());
  }

  @Test
  void testGetRefresher() {
    // Arrange, Act and Assert
    assertNull((new SessionExpiresHeader(2)).getRefresher());
    assertEquals("Refresher", (new SessionExpiresHeader(2, "Refresher")).getRefresher());
    assertEquals("", (new SessionExpiresHeader(2, "")).getRefresher());
  }

  @Test
  void testGetRefresher2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertNull((new SessionExpiresHeader(header)).getRefresher());
  }

  @Test
  void testSetRefresher() {
    // Arrange
    SessionExpiresHeader sessionExpiresHeader = new SessionExpiresHeader(2);

    // Act
    sessionExpiresHeader.setRefresher("Refresher");

    // Assert
    assertEquals("2;refresher=Refresher", sessionExpiresHeader.getValue());
  }

  @Test
  void testSetRefresher2() {
    // Arrange
    SessionExpiresHeader sessionExpiresHeader = new SessionExpiresHeader(2, "Refresher");

    // Act
    sessionExpiresHeader.setRefresher("Refresher");

    // Assert
    assertEquals("2;refresher=Refresher", sessionExpiresHeader.getValue());
  }

  @Test
  void testSetRefresher3() {
    // Arrange
    SessionExpiresHeader sessionExpiresHeader = new SessionExpiresHeader(new Header());

    // Act
    sessionExpiresHeader.setRefresher("Refresher");

    // Assert
    assertEquals(";refresher=Refresher", sessionExpiresHeader.getValue());
  }

  @Test
  void testGetParameter() {
    // Arrange, Act and Assert
    assertNull((new SessionExpiresHeader(2)).getParameter("Pname"));
    assertNull((new SessionExpiresHeader(2, "Refresher")).getParameter("Pname"));
    assertEquals("Refresher",
        (new SessionExpiresHeader(2, "Refresher")).getParameter(SessionExpiresHeader.PARAM_REFRESHER));
    assertEquals("", (new SessionExpiresHeader(2, "")).getParameter(SessionExpiresHeader.PARAM_REFRESHER));
  }

  @Test
  void testGetParameter2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertNull((new SessionExpiresHeader(header)).getParameter("Pname"));
  }

  @Test
  void testGetParameterNames() {
    // Arrange, Act and Assert
    assertTrue((new SessionExpiresHeader(2)).getParameterNames().isEmpty());
  }

  @Test
  void testGetParameterNames2() {
    // Arrange and Act
    Vector actualParameterNames = (new SessionExpiresHeader(2, "Refresher")).getParameterNames();

    // Assert
    assertEquals(1, actualParameterNames.size());
    assertEquals(SessionExpiresHeader.PARAM_REFRESHER, actualParameterNames.get(0));
  }

  @Test
  void testGetParameterNames3() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertTrue((new SessionExpiresHeader(header)).getParameterNames().isEmpty());
  }

  @Test
  void testGetParameters() {
    // Arrange, Act and Assert
    assertNull((new SessionExpiresHeader(2)).getParameters());
    assertEquals("refresher=Refresher", (new SessionExpiresHeader(2, "Refresher")).getParameters());
  }

  @Test
  void testGetParameters2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertNull((new SessionExpiresHeader(header)).getParameters());
  }

  @Test
  void testHasParameter() {
    // Arrange, Act and Assert
    assertFalse((new SessionExpiresHeader(2)).hasParameter("Pname"));
    assertFalse((new SessionExpiresHeader(2, "Refresher")).hasParameter("Pname"));
    assertTrue((new SessionExpiresHeader(2, "Refresher")).hasParameter(SessionExpiresHeader.PARAM_REFRESHER));
  }

  @Test
  void testHasParameter2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertFalse((new SessionExpiresHeader(header)).hasParameter("Pname"));
  }

  @Test
  void testHasParameters() {
    // Arrange, Act and Assert
    assertFalse((new SessionExpiresHeader(2)).hasParameters());
    assertTrue((new SessionExpiresHeader(2, "Refresher")).hasParameters());
  }

  @Test
  void testHasParameters2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertFalse((new SessionExpiresHeader(header)).hasParameters());
  }

  @Test
  void testIndexOfFirstSemi() {
    // Arrange, Act and Assert
    assertEquals(-1, (new SessionExpiresHeader(2)).indexOfFirstSemi());
    assertEquals(1, (new SessionExpiresHeader(2, "Refresher")).indexOfFirstSemi());
  }

  @Test
  void testIndexOfFirstSemi2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertEquals(-1, (new SessionExpiresHeader(header)).indexOfFirstSemi());
  }

  @Test
  void testRemoveParameter() {
    // Arrange
    SessionExpiresHeader sessionExpiresHeader = new SessionExpiresHeader(2, "Refresher");

    // Act
    sessionExpiresHeader.removeParameter(SessionExpiresHeader.PARAM_REFRESHER);

    // Assert
    assertEquals("2", sessionExpiresHeader.getValue());
  }

  @Test
  void testRemoveParameters() {
    // Arrange
    SessionExpiresHeader sessionExpiresHeader = new SessionExpiresHeader(2, "Refresher");

    // Act
    sessionExpiresHeader.removeParameters();

    // Assert
    assertEquals("2", sessionExpiresHeader.getValue());
  }

  @Test
  void testSetParameter() {
    // Arrange
    SessionExpiresHeader sessionExpiresHeader = new SessionExpiresHeader(2);

    // Act
    sessionExpiresHeader.setParameter("Pname", "42");

    // Assert
    assertEquals("2;Pname=42", sessionExpiresHeader.getValue());
  }

  @Test
  void testSetParameter2() {
    // Arrange
    SessionExpiresHeader sessionExpiresHeader = new SessionExpiresHeader(2, "Refresher");

    // Act
    sessionExpiresHeader.setParameter("Pname", "42");

    // Assert
    assertEquals("2;refresher=Refresher;Pname=42", sessionExpiresHeader.getValue());
  }

  @Test
  void testSetParameter3() {
    // Arrange
    SessionExpiresHeader sessionExpiresHeader = new SessionExpiresHeader(2, "Refresher");

    // Act
    sessionExpiresHeader.setParameter(SessionExpiresHeader.PARAM_REFRESHER, "42");

    // Assert
    assertEquals("2;refresher=42", sessionExpiresHeader.getValue());
  }

  @Test
  void testSetParameter4() {
    // Arrange
    SessionExpiresHeader sessionExpiresHeader = new SessionExpiresHeader(new Header());

    // Act
    sessionExpiresHeader.setParameter("Pname", "42");

    // Assert
    assertEquals(";Pname=42", sessionExpiresHeader.getValue());
  }
}

