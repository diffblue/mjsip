package org.mjsip.sip.header;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class SubjectHeaderDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange, Act and Assert
    assertEquals("42", (new SubjectHeader("42")).getSubject());
  }

  @Test
  void testConstructor2() {
    // Arrange and Act
    SubjectHeader actualSubjectHeader = new SubjectHeader(new Header("Hname", "42"));

    // Assert
    assertEquals("Hname", actualSubjectHeader.getName());
    assertEquals("42", actualSubjectHeader.getValue());
  }

  @Test
  void testConstructor3() {
    // Arrange
    Header header = new Header("Hname", "42");
    header.setValue("42");

    // Act
    SubjectHeader actualSubjectHeader = new SubjectHeader(header);

    // Assert
    assertEquals("Hname", actualSubjectHeader.getName());
    assertEquals("42", actualSubjectHeader.getValue());
  }
}

