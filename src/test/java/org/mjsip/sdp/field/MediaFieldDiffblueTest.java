package org.mjsip.sdp.field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Vector;
import org.junit.jupiter.api.Test;
import org.mjsip.sdp.SdpField;

class MediaFieldDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    MediaField actualMediaField = new MediaField("Media field");

    // Assert
    assertTrue(actualMediaField.getFormatList().isEmpty());
    assertEquals("m=Media field\r\n", actualMediaField.toString());
    assertEquals("Media field", actualMediaField.getValue());
    assertEquals('m', actualMediaField.getType());
    assertEquals("", actualMediaField.getTransport());
    assertEquals("Media", actualMediaField.getMedia());
    assertEquals("", actualMediaField.getFormats());
  }

  @Test
  void testConstructor2() {
    // Arrange and Act
    MediaField actualMediaField = new MediaField("Media", 8080, 10, "Transport", "Formats");

    // Assert
    assertEquals("Media 8080/10 Transport Formats", actualMediaField.getValue());
    assertEquals('m', actualMediaField.getType());
  }

  @Test
  void testConstructor3() {
    // Arrange and Act
    MediaField actualMediaField = new MediaField("Media", 8080, 0, "Transport", "Formats");

    // Assert
    assertEquals("Media 8080 Transport Formats", actualMediaField.getValue());
    assertEquals('m', actualMediaField.getType());
  }

  @Test
  void testConstructor4() {
    // Arrange and Act
    MediaField actualMediaField = new MediaField("Media", 8080, 10, "Transport", new Vector(1));

    // Assert
    assertEquals("Media 8080/10 Transport", actualMediaField.getValue());
    assertEquals('m', actualMediaField.getType());
  }

  @Test
  void testConstructor5() {
    // Arrange and Act
    MediaField actualMediaField = new MediaField("Media", 8080, 0, "Transport", new Vector(1));

    // Assert
    assertEquals("Media 8080 Transport", actualMediaField.getValue());
    assertEquals('m', actualMediaField.getType());
  }

  @Test
  void testConstructor6() {
    // Arrange
    Vector vector = new Vector(1);
    vector.add("42");

    // Act
    MediaField actualMediaField = new MediaField("Media", 8080, 10, "Transport", vector);

    // Assert
    assertEquals("Media 8080/10 Transport 42", actualMediaField.getValue());
    assertEquals('m', actualMediaField.getType());
  }

  @Test
  void testConstructor7() {
    // Arrange and Act
    MediaField actualMediaField = new MediaField(new SdpField('A', "42"));

    // Assert
    assertEquals("42", actualMediaField.getValue());
    assertEquals('A', actualMediaField.getType());
  }

  @Test
  void testConstructor8() {
    // Arrange and Act
    MediaField actualMediaField = new MediaField(new SdpField('\u0000', "42"));

    // Assert
    assertEquals("42", actualMediaField.getValue());
    assertEquals('\u0000', actualMediaField.getType());
  }

  @Test
  void testGetMedia() {
    // Arrange, Act and Assert
    assertEquals("Media", (new MediaField("Media field")).getMedia());
    assertEquals("42", (new MediaField("42")).getMedia());
    assertEquals("", (new MediaField("")).getMedia());
    assertEquals("8080/10", (new MediaField("", 8080, 10, "Transport", "Formats")).getMedia());
  }

  @Test
  void testGetPort() {
    // Arrange, Act and Assert
    assertEquals(8080, (new MediaField("Media", 8080, 10, "Transport", "Formats")).getPort());
    assertEquals(8080, (new MediaField("Media", 8080, 0, "Transport", "Formats")).getPort());
  }

  @Test
  void testGetTransport() {
    // Arrange, Act and Assert
    assertEquals("", (new MediaField("Media field")).getTransport());
  }

  @Test
  void testGetFormats() {
    // Arrange, Act and Assert
    assertEquals("", (new MediaField("Media field")).getFormats());
    assertEquals("Formats", (new MediaField("Media", 8080, 10, "Transport", "Formats")).getFormats());
  }

  @Test
  void testGetFormatList() {
    // Arrange, Act and Assert
    assertTrue((new MediaField("Media field")).getFormatList().isEmpty());
    assertTrue((new MediaField("Media", 8080, 10, "Transport", "")).getFormatList().isEmpty());
  }

  @Test
  void testGetFormatList2() {
    // Arrange and Act
    Vector actualFormatList = (new MediaField("Media", 8080, 10, "Transport", "Formats")).getFormatList();

    // Assert
    assertEquals(1, actualFormatList.size());
    assertEquals("Formats", actualFormatList.get(0));
  }
}

