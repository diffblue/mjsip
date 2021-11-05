package org.mjsip.sip.header;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;
import org.junit.jupiter.api.Test;

class ExpiresHeaderDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    ExpiresHeader actualExpiresHeader = new ExpiresHeader("42");

    // Assert
    assertEquals("Expires: 42\r\n", actualExpiresHeader.toString());
    assertEquals("42", actualExpiresHeader.getValue());
    assertEquals(CoreSipHeaders.Expires, actualExpiresHeader.getName());
    assertEquals(42, actualExpiresHeader.getDeltaSeconds());
  }

  @Test
  void testConstructor2() {
    // Arrange and Act
    ExpiresHeader actualExpiresHeader = new ExpiresHeader(1);

    // Assert
    assertEquals("1", actualExpiresHeader.getValue());
    assertEquals(CoreSipHeaders.Expires, actualExpiresHeader.getName());
  }

  @Test
  void testConstructor3() {
    // Arrange
    LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();

    // Act
    ExpiresHeader actualExpiresHeader = new ExpiresHeader(
        Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));

    // Assert
    assertNull(actualExpiresHeader.getDate());
    assertEquals(CoreSipHeaders.Expires, actualExpiresHeader.getName());
  }

  @Test
  void testConstructor4() {
    // Arrange
    LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();

    // Act
    ExpiresHeader actualExpiresHeader = new ExpiresHeader(
        Date.from(atStartOfDayResult.atZone(ZoneOffset.ofTotalSeconds(1)).toInstant()));

    // Assert
    assertNull(actualExpiresHeader.getDate());
    assertEquals(CoreSipHeaders.Expires, actualExpiresHeader.getName());
  }

  @Test
  void testConstructor5() {
    // Arrange
    LocalDateTime atStartOfDayResult = LocalDate.of(0, 1, 1).atStartOfDay();

    // Act
    ExpiresHeader actualExpiresHeader = new ExpiresHeader(
        Date.from(atStartOfDayResult.atZone(ZoneOffset.ofTotalSeconds(1)).toInstant()));

    // Assert
    assertNull(actualExpiresHeader.getDate());
    assertEquals(CoreSipHeaders.Expires, actualExpiresHeader.getName());
  }

  @Test
  void testConstructor6() {
    // Arrange
    LocalDateTime atStartOfDayResult = LocalDate.ofEpochDay(-1L).atStartOfDay();

    // Act
    ExpiresHeader actualExpiresHeader = new ExpiresHeader(
        Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));

    // Assert
    assertNull(actualExpiresHeader.getDate());
    assertEquals(CoreSipHeaders.Expires, actualExpiresHeader.getName());
  }

  @Test
  void testConstructor7() {
    // Arrange and Act
    ExpiresHeader actualExpiresHeader = new ExpiresHeader(new Header("Hname", "42"));

    // Assert
    assertEquals("42", actualExpiresHeader.getValue());
    assertEquals("Hname", actualExpiresHeader.getName());
  }

  @Test
  void testConstructor8() {
    // Arrange
    Header header = new Header("Hname", "42");
    header.setValue("42");

    // Act
    ExpiresHeader actualExpiresHeader = new ExpiresHeader(header);

    // Assert
    assertEquals("42", actualExpiresHeader.getValue());
    assertEquals("Hname", actualExpiresHeader.getName());
  }

  @Test
  void testIsDate() {
    // Arrange, Act and Assert
    assertFalse((new ExpiresHeader("42")).isDate());
    assertTrue((new ExpiresHeader("GMT")).isDate());
  }

  @Test
  void testIsDate2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertFalse((new ExpiresHeader(header)).isDate());
  }

  @Test
  void testGetDeltaSeconds() {
    // Arrange, Act and Assert
    assertEquals(42, (new ExpiresHeader("42")).getDeltaSeconds());
    assertEquals(1, (new ExpiresHeader(1)).getDeltaSeconds());
  }

  @Test
  void testGetDeltaSeconds2() {
    // Arrange
    ExpiresHeader expiresHeader = new ExpiresHeader("GMT");
    expiresHeader.setValue("42");

    // Act and Assert
    assertEquals(42, expiresHeader.getDeltaSeconds());
  }

  @Test
  void testGetDeltaSeconds3() {
    // Arrange
    ExpiresHeader expiresHeader = new ExpiresHeader("Hvalue");
    expiresHeader.setValue("42");

    // Act and Assert
    assertEquals(42, expiresHeader.getDeltaSeconds());
  }

  @Test
  void testGetDeltaSeconds4() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertEquals(42, (new ExpiresHeader(header)).getDeltaSeconds());
  }

  @Test
  void testGetDate() {
    // Arrange, Act and Assert
    assertNull((new ExpiresHeader("GMT")).getDate());
    assertNull((new ExpiresHeader(new Date(1L))).getDate());
    assertNull((new ExpiresHeader(-1)).getDate());
  }
}

