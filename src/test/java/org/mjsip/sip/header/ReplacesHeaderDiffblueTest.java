package org.mjsip.sip.header;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Vector;
import org.junit.jupiter.api.Test;

class ReplacesHeaderDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    ReplacesHeader actualReplacesHeader = new ReplacesHeader("Call id", "To tag", "jane.doe@example.org");

    // Assert
    assertEquals("Call id;to-tag=To tag;from-tag=jane.doe@example.org", actualReplacesHeader.getValue());
    assertEquals(SipHeaders.Replaces, actualReplacesHeader.getName());
  }

  @Test
  void testConstructor2() {
    // Arrange and Act
    ReplacesHeader actualReplacesHeader = new ReplacesHeader(null, "To tag", "jane.doe@example.org");

    // Assert
    assertEquals(";to-tag=To tag;from-tag=jane.doe@example.org", actualReplacesHeader.getValue());
    assertEquals(SipHeaders.Replaces, actualReplacesHeader.getName());
  }

  @Test
  void testConstructor3() {
    // Arrange and Act
    ReplacesHeader actualReplacesHeader = new ReplacesHeader("Call id", null, "jane.doe@example.org");

    // Assert
    assertEquals("Call id;from-tag=jane.doe@example.org", actualReplacesHeader.getValue());
    assertEquals(SipHeaders.Replaces, actualReplacesHeader.getName());
  }

  @Test
  void testConstructor4() {
    // Arrange and Act
    ReplacesHeader actualReplacesHeader = new ReplacesHeader("Call id", "To tag", null);

    // Assert
    assertEquals("Call id;to-tag=To tag", actualReplacesHeader.getValue());
    assertEquals(SipHeaders.Replaces, actualReplacesHeader.getName());
  }

  @Test
  void testConstructor5() {
    // Arrange and Act
    ReplacesHeader actualReplacesHeader = new ReplacesHeader("Call id", "To tag", "jane.doe@example.org", true);

    // Assert
    assertEquals("Call id;to-tag=To tag;from-tag=jane.doe@example.org;early-only", actualReplacesHeader.getValue());
    assertEquals(SipHeaders.Replaces, actualReplacesHeader.getName());
  }

  @Test
  void testConstructor6() {
    // Arrange and Act
    ReplacesHeader actualReplacesHeader = new ReplacesHeader(null, "To tag", "jane.doe@example.org", true);

    // Assert
    assertEquals(";to-tag=To tag;from-tag=jane.doe@example.org;early-only", actualReplacesHeader.getValue());
    assertEquals(SipHeaders.Replaces, actualReplacesHeader.getName());
  }

  @Test
  void testConstructor7() {
    // Arrange and Act
    ReplacesHeader actualReplacesHeader = new ReplacesHeader("Call id", null, "jane.doe@example.org", true);

    // Assert
    assertEquals("Call id;from-tag=jane.doe@example.org;early-only", actualReplacesHeader.getValue());
    assertEquals(SipHeaders.Replaces, actualReplacesHeader.getName());
  }

  @Test
  void testConstructor8() {
    // Arrange and Act
    ReplacesHeader actualReplacesHeader = new ReplacesHeader("Call id", "To tag", null, true);

    // Assert
    assertEquals("Call id;to-tag=To tag;early-only", actualReplacesHeader.getValue());
    assertEquals(SipHeaders.Replaces, actualReplacesHeader.getName());
  }

  @Test
  void testConstructor9() {
    // Arrange and Act
    ReplacesHeader actualReplacesHeader = new ReplacesHeader("Call id", "To tag", "jane.doe@example.org", false);

    // Assert
    assertEquals("Call id;to-tag=To tag;from-tag=jane.doe@example.org", actualReplacesHeader.getValue());
    assertEquals(SipHeaders.Replaces, actualReplacesHeader.getName());
  }

  @Test
  void testConstructor10() {
    // Arrange and Act
    ReplacesHeader actualReplacesHeader = new ReplacesHeader(new Header("Hname", "42"));

    // Assert
    assertEquals("42", actualReplacesHeader.getValue());
    assertEquals("Hname", actualReplacesHeader.getName());
  }

  @Test
  void testConstructor11() {
    // Arrange
    Header header = new Header("Hname", "42");
    header.setValue("42");

    // Act
    ReplacesHeader actualReplacesHeader = new ReplacesHeader(header);

    // Assert
    assertEquals("42", actualReplacesHeader.getValue());
    assertEquals("Hname", actualReplacesHeader.getName());
  }

  @Test
  void testGetCallId() {
    // Arrange, Act and Assert
    assertEquals("Call", (new ReplacesHeader("Call id", "To tag", "jane.doe@example.org")).getCallId());
    assertEquals("to-tag=To", (new ReplacesHeader("", "To tag", "jane.doe@example.org")).getCallId());
    assertEquals("42", (new ReplacesHeader(new Header("Hname", "42"))).getCallId());
    assertEquals("", (new ReplacesHeader(new Header("Hname", ""))).getCallId());
  }

  @Test
  void testGetCallId2() {
    // Arrange
    ReplacesHeader replacesHeader = new ReplacesHeader(new Header());
    replacesHeader.setValue("42");

    // Act and Assert
    assertEquals("42", replacesHeader.getCallId());
  }

  @Test
  void testHasToTag() {
    // Arrange, Act and Assert
    assertTrue((new ReplacesHeader("Call id", "To tag", "jane.doe@example.org")).hasToTag());
    assertFalse((new ReplacesHeader(new Header("Hname", "42"))).hasToTag());
  }

  @Test
  void testHasToTag2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertFalse((new ReplacesHeader(header)).hasToTag());
  }

  @Test
  void testGetToTag() {
    // Arrange, Act and Assert
    assertEquals(CoreSipHeaders.To, (new ReplacesHeader("Call id", "To tag", "jane.doe@example.org")).getToTag());
    assertEquals("from-tag", (new ReplacesHeader("Call id", "", "jane.doe@example.org")).getToTag());
    assertNull((new ReplacesHeader(new Header("Hname", "42"))).getToTag());
  }

  @Test
  void testGetToTag2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertNull((new ReplacesHeader(header)).getToTag());
  }

  @Test
  void testHasFromTag() {
    // Arrange, Act and Assert
    assertTrue((new ReplacesHeader("Call id", "To tag", "jane.doe@example.org")).hasFromTag());
    assertFalse((new ReplacesHeader(new Header("Hname", "42"))).hasFromTag());
  }

  @Test
  void testHasFromTag2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertFalse((new ReplacesHeader(header)).hasFromTag());
  }

  @Test
  void testGetFromTag() {
    // Arrange, Act and Assert
    assertEquals("jane.doe@example.org",
        (new ReplacesHeader("Call id", "To tag", "jane.doe@example.org")).getFromTag());
    assertEquals(CoreSipHeaders.From, (new ReplacesHeader("Call id", "To tag", "From tag")).getFromTag());
    assertEquals("", (new ReplacesHeader("Call id", "To tag", "")).getFromTag());
    assertNull((new ReplacesHeader(new Header("Hname", "42"))).getFromTag());
    assertEquals("early-only", (new ReplacesHeader("Call id", "To tag", "", true)).getFromTag());
  }

  @Test
  void testGetFromTag2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertNull((new ReplacesHeader(header)).getFromTag());
  }

  @Test
  void testHasEarlyFlag() {
    // Arrange, Act and Assert
    assertFalse((new ReplacesHeader("Call id", "To tag", "jane.doe@example.org")).hasEarlyFlag());
    assertTrue((new ReplacesHeader("Call id", "To tag", "jane.doe@example.org", true)).hasEarlyFlag());
    assertFalse((new ReplacesHeader(new Header("Hname", "42"))).hasEarlyFlag());
  }

  @Test
  void testHasEarlyFlag2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertFalse((new ReplacesHeader(header)).hasEarlyFlag());
  }

  @Test
  void testSetEarlyFlag() {
    // Arrange
    ReplacesHeader replacesHeader = new ReplacesHeader("Call id", "To tag", "jane.doe@example.org");

    // Act
    replacesHeader.setEarlyFlag(true);

    // Assert
    assertEquals("Call id;to-tag=To tag;from-tag=jane.doe@example.org;early-only", replacesHeader.getValue());
  }

  @Test
  void testSetEarlyFlag2() {
    // Arrange
    ReplacesHeader replacesHeader = new ReplacesHeader("Call id", "To tag", "jane.doe@example.org", true);

    // Act
    replacesHeader.setEarlyFlag(true);

    // Assert
    assertEquals("Call id;to-tag=To tag;from-tag=jane.doe@example.org;early-only", replacesHeader.getValue());
  }

  @Test
  void testSetEarlyFlag3() {
    // Arrange
    ReplacesHeader replacesHeader = new ReplacesHeader(new Header("Hname", "42"));

    // Act
    replacesHeader.setEarlyFlag(true);

    // Assert
    assertEquals("42;early-only", replacesHeader.getValue());
  }

  @Test
  void testSetEarlyFlag4() {
    // Arrange
    ReplacesHeader replacesHeader = new ReplacesHeader(new Header());

    // Act
    replacesHeader.setEarlyFlag(true);

    // Assert
    assertEquals(";early-only", replacesHeader.getValue());
  }

  @Test
  void testGetParameter() {
    // Arrange, Act and Assert
    assertNull((new ReplacesHeader("Call id", "To tag", "jane.doe@example.org")).getParameter("Pname"));
    assertNull((new ReplacesHeader("Call id", "To tag", "jane.doe@example.org", true)).getParameter("Pname"));
    assertNull((new ReplacesHeader(new Header("Hname", "42"))).getParameter("Pname"));
    assertEquals(CoreSipHeaders.To,
        (new ReplacesHeader("Call id", "To tag", "jane.doe@example.org")).getParameter("to-tag"));
    assertEquals("jane.doe@example.org",
        (new ReplacesHeader("Call id", "To tag", "jane.doe@example.org")).getParameter("from-tag"));
    assertNull((new ReplacesHeader("Call id", "To tag", "jane.doe@example.org", false)).getParameter("early-only"));
    assertEquals("from-tag", (new ReplacesHeader("Call id", "", "jane.doe@example.org", true)).getParameter("to-tag"));
    assertEquals("", (new ReplacesHeader("Call id", "To tag", "")).getParameter("from-tag"));
  }

  @Test
  void testGetParameter2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertNull((new ReplacesHeader(header)).getParameter("Pname"));
  }

  @Test
  void testGetParameterNames() {
    // Arrange and Act
    Vector actualParameterNames = (new ReplacesHeader("Call id", "To tag", "jane.doe@example.org")).getParameterNames();

    // Assert
    assertEquals(2, actualParameterNames.size());
    assertEquals("to-tag", actualParameterNames.get(0));
    assertEquals("from-tag", actualParameterNames.get(1));
  }

  @Test
  void testGetParameterNames2() {
    // Arrange and Act
    Vector actualParameterNames = (new ReplacesHeader("Call id", "To tag", "jane.doe@example.org", true))
        .getParameterNames();

    // Assert
    assertEquals(3, actualParameterNames.size());
    assertEquals("to-tag", actualParameterNames.get(0));
    assertEquals("from-tag", actualParameterNames.get(1));
    assertEquals("early-only", actualParameterNames.get(2));
  }

  @Test
  void testGetParameterNames3() {
    // Arrange, Act and Assert
    assertTrue((new ReplacesHeader(new Header("Hname", "42"))).getParameterNames().isEmpty());
  }

  @Test
  void testGetParameterNames4() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertTrue((new ReplacesHeader(header)).getParameterNames().isEmpty());
  }

  @Test
  void testGetParameters() {
    // Arrange, Act and Assert
    assertEquals("to-tag=To tag;from-tag=jane.doe@example.org",
        (new ReplacesHeader("Call id", "To tag", "jane.doe@example.org")).getParameters());
    assertNull((new ReplacesHeader(new Header("Hname", "42"))).getParameters());
  }

  @Test
  void testGetParameters2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertNull((new ReplacesHeader(header)).getParameters());
  }

  @Test
  void testHasParameter() {
    // Arrange, Act and Assert
    assertFalse((new ReplacesHeader("Call id", "To tag", "jane.doe@example.org")).hasParameter("Pname"));
    assertFalse((new ReplacesHeader("Call id", "To tag", "jane.doe@example.org", true)).hasParameter("Pname"));
    assertFalse((new ReplacesHeader(new Header("Hname", "42"))).hasParameter("Pname"));
    assertTrue((new ReplacesHeader("Call id", "To tag", "jane.doe@example.org")).hasParameter("to-tag"));
  }

  @Test
  void testHasParameter2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertFalse((new ReplacesHeader(header)).hasParameter("Pname"));
  }

  @Test
  void testHasParameters() {
    // Arrange, Act and Assert
    assertTrue((new ReplacesHeader("Call id", "To tag", "jane.doe@example.org")).hasParameters());
    assertFalse((new ReplacesHeader(new Header("Hname", "42"))).hasParameters());
  }

  @Test
  void testHasParameters2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertFalse((new ReplacesHeader(header)).hasParameters());
  }

  @Test
  void testIndexOfFirstSemi() {
    // Arrange, Act and Assert
    assertEquals(7, (new ReplacesHeader("Call id", "To tag", "jane.doe@example.org")).indexOfFirstSemi());
    assertEquals(-1, (new ReplacesHeader(new Header("Hname", "42"))).indexOfFirstSemi());
  }

  @Test
  void testIndexOfFirstSemi2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertEquals(-1, (new ReplacesHeader(header)).indexOfFirstSemi());
  }

  @Test
  void testRemoveParameter() {
    // Arrange
    ReplacesHeader replacesHeader = new ReplacesHeader("Call id", "To tag", "jane.doe@example.org");

    // Act
    replacesHeader.removeParameter("to-tag");

    // Assert
    assertEquals("Call id;from-tag=jane.doe@example.org", replacesHeader.getValue());
  }

  @Test
  void testRemoveParameter2() {
    // Arrange
    ReplacesHeader replacesHeader = new ReplacesHeader("Call id", "To tag", "jane.doe@example.org");

    // Act
    replacesHeader.removeParameter("from-tag");

    // Assert
    assertEquals("Call id;to-tag=To tag", replacesHeader.getValue());
  }

  @Test
  void testRemoveParameters() {
    // Arrange
    ReplacesHeader replacesHeader = new ReplacesHeader("Call id", "To tag", "jane.doe@example.org");

    // Act
    replacesHeader.removeParameters();

    // Assert
    assertEquals("Call id", replacesHeader.getValue());
  }

  @Test
  void testSetParameter() {
    // Arrange
    ReplacesHeader replacesHeader = new ReplacesHeader("Call id", "To tag", "jane.doe@example.org");

    // Act
    replacesHeader.setParameter("Pname", "42");

    // Assert
    assertEquals("Call id;to-tag=To tag;from-tag=jane.doe@example.org;Pname=42", replacesHeader.getValue());
  }

  @Test
  void testSetParameter2() {
    // Arrange
    ReplacesHeader replacesHeader = new ReplacesHeader("Call id", "To tag", "jane.doe@example.org", true);

    // Act
    replacesHeader.setParameter("Pname", "42");

    // Assert
    assertEquals("Call id;to-tag=To tag;from-tag=jane.doe@example.org;early-only;Pname=42", replacesHeader.getValue());
  }

  @Test
  void testSetParameter3() {
    // Arrange
    ReplacesHeader replacesHeader = new ReplacesHeader(new Header("Hname", "42"));

    // Act
    replacesHeader.setParameter("Pname", "42");

    // Assert
    assertEquals("42;Pname=42", replacesHeader.getValue());
  }

  @Test
  void testSetParameter4() {
    // Arrange
    ReplacesHeader replacesHeader = new ReplacesHeader("Call id", "To tag", "jane.doe@example.org");

    // Act
    replacesHeader.setParameter("to-tag", "42");

    // Assert
    assertEquals("Call id;from-tag=jane.doe@example.org;to-tag=42", replacesHeader.getValue());
  }

  @Test
  void testSetParameter5() {
    // Arrange
    ReplacesHeader replacesHeader = new ReplacesHeader("Call id", "To tag", "jane.doe@example.org");

    // Act
    replacesHeader.setParameter("from-tag", "42");

    // Assert
    assertEquals("Call id;to-tag=To tag;from-tag=42", replacesHeader.getValue());
  }

  @Test
  void testSetParameter6() {
    // Arrange
    ReplacesHeader replacesHeader = new ReplacesHeader(new Header());

    // Act
    replacesHeader.setParameter("Pname", "42");

    // Assert
    assertEquals(";Pname=42", replacesHeader.getValue());
  }
}

