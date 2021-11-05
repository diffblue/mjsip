package org.mjsip.sip.header;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;
import org.junit.jupiter.api.Test;

class DateHeaderDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    DateHeader actualDateHeader = new DateHeader("42");

    // Assert
    assertNull(actualDateHeader.getDate());
    assertEquals("Date: 42\r\n", actualDateHeader.toString());
    assertEquals("42", actualDateHeader.getValue());
    assertEquals(CoreSipHeaders.Date, actualDateHeader.getName());
  }

  @Test
  void testConstructor2() {
    // Arrange
    LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();

    // Act and Assert
    assertEquals(CoreSipHeaders.Date,
        (new DateHeader(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()))).getName());
  }

  @Test
  void testConstructor3() {
    // Arrange
    LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();

    // Act and Assert
    assertEquals(CoreSipHeaders.Date,
        (new DateHeader(Date.from(atStartOfDayResult.atZone(ZoneOffset.ofTotalSeconds(1)).toInstant()))).getName());
  }

  @Test
  void testConstructor4() {
    // Arrange
    LocalDateTime atStartOfDayResult = LocalDate.of(0, 1, 1).atStartOfDay();

    // Act
    DateHeader actualDateHeader = new DateHeader(
        Date.from(atStartOfDayResult.atZone(ZoneOffset.ofTotalSeconds(1)).toInstant()));

    // Assert
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    assertEquals("0001-01-02", simpleDateFormat.format(actualDateHeader.getDate()));
    assertEquals(CoreSipHeaders.Date, actualDateHeader.getName());
  }

  @Test
  void testConstructor5() {
    // Arrange
    LocalDateTime atStartOfDayResult = LocalDate.ofEpochDay(-1L).atStartOfDay();

    // Act and Assert
    assertEquals(CoreSipHeaders.Date,
        (new DateHeader(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()))).getName());
  }

  @Test
  void testConstructor6() {
    // Arrange and Act
    DateHeader actualDateHeader = new DateHeader(new Header("Hname", "42"));

    // Assert
    assertEquals("42", actualDateHeader.getValue());
    assertEquals("Hname", actualDateHeader.getName());
  }

  @Test
  void testConstructor7() {
    // Arrange
    Header header = new Header("Hname", "42");
    header.setValue("42");

    // Act
    DateHeader actualDateHeader = new DateHeader(header);

    // Assert
    assertEquals("42", actualDateHeader.getValue());
    assertEquals("Hname", actualDateHeader.getName());
  }

  @Test
  void testGetDate() {
    // Arrange, Act and Assert
    assertNull((new DateHeader("42")).getDate());
  }

  @Test
  void testGetDate3() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertNull((new DateHeader(header)).getDate());
  }
}

