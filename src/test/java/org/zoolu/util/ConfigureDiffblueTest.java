package org.zoolu.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;

class ConfigureDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    Configure actualConfigure = new Configure();
    actualConfigure.parseLine("Line");

    // Assert that nothing has changed
    assertEquals("", actualConfigure.toLines());
  }

  @Test
  void testConstructor2() {
    // Arrange, Act and Assert
    assertEquals("", (new Configure(mock(Configurable.class), "File")).toLines());
    assertEquals("NONE", Configure.NONE);
  }

  @Test
  void testConstructor3() {
    // Arrange, Act and Assert
    assertEquals("", (new Configure(mock(Configurable.class), null)).toLines());
    assertEquals("NONE", Configure.NONE);
  }

  @Test
  void testLoadFile() {
    // Arrange
    Configure configure = new Configure();

    // Act
    configure.loadFile("File");

    // Assert that nothing has changed
    assertNull(configure.configurable);
  }

  @Test
  void testLoadFile2() {
    // Arrange
    Configure configure = new Configure();

    // Act
    configure.loadFile((String) null);

    // Assert that nothing has changed
    assertNull(configure.configurable);
  }

  @Test
  void testLoadFile3() throws MalformedURLException {
    // Arrange
    Configure configure = new Configure();

    // Act
    configure.loadFile(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL());

    // Assert that nothing has changed
    assertNull(configure.configurable);
  }

  @Test
  void testLoadFile4() throws MalformedURLException {
    // Arrange
    Configure configure = new Configure();

    // Act
    configure.loadFile(Paths.get(System.getProperty("java.io.tmpdir"), "").toUri().toURL());

    // Assert that nothing has changed
    assertNull(configure.configurable);
  }

  @Test
  void testLoadFile5() throws MalformedURLException {
    // Arrange
    Configurable configurable = mock(Configurable.class);
    doNothing().when(configurable).parseLine((String) any());

    // Act
    (new Configure(configurable, "File")).loadFile(Paths.get(System.getProperty("java.io.tmpdir"), "").toUri().toURL());

    // Assert
    verify(configurable, atLeast(1)).parseLine((String) any());
  }

  @Test
  void testSaveFile() {
    // Arrange
    Configure configure = new Configure();

    // Act
    configure.saveFile(null);

    // Assert that nothing has changed
    assertNull(configure.configurable);
  }

  @Test
  void testReadFrom() throws IOException {
    // Arrange
    Configure configure = new Configure();

    // Act
    configure.readFrom(new StringReader("foo"));

    // Assert that nothing has changed
    assertNull(configure.configurable);
  }

  @Test
  void testReadFrom2() throws IOException {
    // Arrange
    Configurable configurable = mock(Configurable.class);
    doNothing().when(configurable).parseLine((String) any());
    Configure configure = new Configure(configurable, "File");

    // Act
    configure.readFrom(new StringReader("foo"));

    // Assert
    verify(configurable).parseLine((String) any());
  }

  @Test
  void testWriteTo() throws IOException {
    // Arrange
    Configure configure = new Configure();

    // Act
    configure.writeTo(new StringWriter());

    // Assert that nothing has changed
    assertNull(configure.configurable);
  }
}

