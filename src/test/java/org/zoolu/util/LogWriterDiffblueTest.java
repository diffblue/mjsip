package org.zoolu.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.ByteArrayOutputStream;
import java.io.StringWriter;
import java.io.Writer;
import org.junit.jupiter.api.Test;

class LogWriterDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    LogWriter actualLogWriter = new LogWriter(new ByteArrayOutputStream(3));

    // Assert
    LogLevel expectedLoggingLevel = actualLogWriter.logging_level;
    assertSame(expectedLoggingLevel, actualLogWriter.getLoggingLevel());
    assertTrue(actualLogWriter.timestamp);
    assertEquals(0L, actualLogWriter.max_size);
    assertEquals(0L, actualLogWriter.counter);
  }

  @Test
  void testConstructor2() {
    // Arrange
    ByteArrayOutputStream out = new ByteArrayOutputStream(3);

    // Act
    LogWriter actualLogWriter = new LogWriter(out, new LogLevel("Name", 42));

    // Assert
    LogLevel expectedLoggingLevel = actualLogWriter.logging_level;
    assertSame(expectedLoggingLevel, actualLogWriter.getLoggingLevel());
    assertTrue(actualLogWriter.timestamp);
    assertEquals(0L, actualLogWriter.max_size);
    assertEquals(0L, actualLogWriter.counter);
  }

  @Test
  void testConstructor3() {
    // Arrange
    ByteArrayOutputStream out = new ByteArrayOutputStream(3);

    // Act
    LogWriter actualLogWriter = new LogWriter(out, new LogLevel("ALL", 42));

    // Assert
    LogLevel expectedLoggingLevel = actualLogWriter.logging_level;
    assertSame(expectedLoggingLevel, actualLogWriter.getLoggingLevel());
    assertTrue(actualLogWriter.timestamp);
    assertEquals(0L, actualLogWriter.max_size);
    assertEquals(0L, actualLogWriter.counter);
  }

  @Test
  void testConstructor4() {
    // Arrange and Act
    LogWriter actualLogWriter = new LogWriter(new StringWriter());

    // Assert
    LogLevel expectedLoggingLevel = actualLogWriter.logging_level;
    assertSame(expectedLoggingLevel, actualLogWriter.getLoggingLevel());
    assertTrue(actualLogWriter.timestamp);
    assertEquals(0L, actualLogWriter.max_size);
    assertEquals(0L, actualLogWriter.counter);
  }

  @Test
  void testConstructor5() {
    // Arrange
    StringWriter out = new StringWriter();

    // Act
    LogWriter actualLogWriter = new LogWriter(out, new LogLevel("Name", 42));

    // Assert
    LogLevel expectedLoggingLevel = actualLogWriter.logging_level;
    assertSame(expectedLoggingLevel, actualLogWriter.getLoggingLevel());
    assertTrue(actualLogWriter.timestamp);
    assertEquals(0L, actualLogWriter.max_size);
    assertEquals(0L, actualLogWriter.counter);
  }

  @Test
  void testClose() {
    // Arrange
    LogWriter logWriter = new LogWriter(new StringWriter());

    // Act
    logWriter.close();

    // Assert
    assertNull(logWriter.out);
  }

  @Test
  void testClose2() {
    // Arrange
    LogWriter logWriter = new LogWriter((Writer) null);

    // Act
    logWriter.close();

    // Assert
    assertNull(logWriter.out);
  }

  @Test
  void testLog() {
    // Arrange
    LogWriter logWriter = new LogWriter(new StringWriter());

    // Act
    logWriter.log("Not all who wander are lost");

    // Assert
    assertEquals(43L, logWriter.counter);
  }

  @Test
  void testLog2() {
    // Arrange
    LogWriter logWriter = new LogWriter((Writer) null);

    // Act
    logWriter.log("Not all who wander are lost");

    // Assert that nothing has changed
    LogLevel expectedLoggingLevel = logWriter.logging_level;
    assertSame(expectedLoggingLevel, logWriter.getLoggingLevel());
    assertTrue(logWriter.timestamp);
    assertEquals(0L, logWriter.max_size);
    assertEquals(0L, logWriter.counter);
  }

  @Test
  void testLog3() {
    // Arrange
    LogWriter logWriter = new LogWriter((Writer) null);
    logWriter.setLoggingLevel(null);

    // Act
    logWriter.log("Not all who wander are lost");

    // Assert that nothing has changed
    assertTrue(logWriter.timestamp);
    assertEquals(0L, logWriter.max_size);
    assertEquals(0L, logWriter.counter);
  }

  @Test
  void testLog4() {
    // Arrange
    LogWriter logWriter = new LogWriter(new StringWriter());
    LogLevel logLevel = new LogLevel("Name", 42);

    // Act
    logWriter.log(logLevel, Object.class, "Not all who wander are lost");

    // Assert that nothing has changed
    assertEquals("Name", logLevel.getName());
    assertEquals(42, logLevel.getValue());
    LogLevel expectedLoggingLevel = logLevel.INFO;
    assertSame(expectedLoggingLevel, logWriter.getLoggingLevel());
    assertTrue(logWriter.timestamp);
    assertEquals(0L, logWriter.max_size);
    assertEquals(0L, logWriter.counter);
  }

  @Test
  void testLog5() {
    // Arrange
    StringWriter out = new StringWriter();
    LogWriter logWriter = new LogWriter(out, new LogLevel("Name", 42));
    LogLevel level = new LogLevel("Name", 42);

    // Act
    logWriter.log(level, Object.class, "Not all who wander are lost");

    // Assert
    assertEquals(57L, logWriter.counter);
  }

  @Test
  void testLog6() {
    // Arrange
    LogWriter logWriter = new LogWriter((Writer) null);
    LogLevel logLevel = new LogLevel("Name", 42);

    // Act
    logWriter.log(logLevel, Object.class, "Not all who wander are lost");

    // Assert that nothing has changed
    assertEquals("Name", logLevel.getName());
    assertEquals(42, logLevel.getValue());
    LogLevel expectedLoggingLevel = logLevel.INFO;
    assertSame(expectedLoggingLevel, logWriter.getLoggingLevel());
    assertTrue(logWriter.timestamp);
    assertEquals(0L, logWriter.max_size);
    assertEquals(0L, logWriter.counter);
  }

  @Test
  void testLog7() {
    // Arrange
    LogWriter logWriter = new LogWriter((Writer) null);

    // Act
    logWriter.log(null, Object.class, "Not all who wander are lost");

    // Assert that nothing has changed
    LogLevel expectedLoggingLevel = logWriter.logging_level;
    assertSame(expectedLoggingLevel, logWriter.getLoggingLevel());
    assertTrue(logWriter.timestamp);
    assertEquals(0L, logWriter.max_size);
    assertEquals(0L, logWriter.counter);
  }

  @Test
  void testLog8() {
    // Arrange
    StringWriter out = new StringWriter(3);
    LogWriter logWriter = new LogWriter(out, new LogLevel("Name", 42));
    LogLevel level = new LogLevel("Name", 42);

    // Act
    logWriter.log(level, Object.class, "Not all who wander are lost");

    // Assert
    assertEquals(57L, logWriter.counter);
  }

  @Test
  void testLog9() {
    // Arrange
    LogWriter logWriter = new LogWriter((Writer) null, null);
    LogLevel logLevel = new LogLevel("Name", 42);

    // Act
    logWriter.log(logLevel, Object.class, "Not all who wander are lost");

    // Assert that nothing has changed
    assertEquals("Name", logLevel.getName());
    assertEquals(42, logLevel.getValue());
    assertTrue(logWriter.timestamp);
    assertEquals(0L, logWriter.max_size);
    assertEquals(0L, logWriter.counter);
  }

  @Test
  void testLog10() {
    // Arrange
    StringWriter out = new StringWriter();
    LogWriter logWriter = new LogWriter(out, new LogLevel("Name", 42));

    // Act
    logWriter.log(new LogLevel("Name", 42), null, "Not all who wander are lost");

    // Assert
    assertEquals(49L, logWriter.counter);
  }

  @Test
  void testLog11() {
    // Arrange
    StringWriter stringWriter = new StringWriter();
    stringWriter.append('\u0000');
    LogWriter logWriter = new LogWriter(stringWriter, new LogLevel("Name", 42));

    // Act
    logWriter.log(new LogLevel("Name", 42), null, "Not all who wander are lost");

    // Assert
    assertEquals(49L, logWriter.counter);
  }

  @Test
  void testLog12() {
    // Arrange
    LogWriter logWriter = new LogWriter(new StringWriter());
    LogLevel logLevel = new LogLevel("Name", 42);

    // Act
    logWriter.log(logLevel, "Not all who wander are lost");

    // Assert that nothing has changed
    assertEquals("Name", logLevel.getName());
    assertEquals(42, logLevel.getValue());
    LogLevel expectedLoggingLevel = logLevel.INFO;
    assertSame(expectedLoggingLevel, logWriter.getLoggingLevel());
    assertTrue(logWriter.timestamp);
    assertEquals(0L, logWriter.max_size);
    assertEquals(0L, logWriter.counter);
  }

  @Test
  void testLog13() {
    // Arrange
    StringWriter out = new StringWriter();
    LogWriter logWriter = new LogWriter(out, new LogLevel("Name", 42));

    // Act
    logWriter.log(new LogLevel("Name", 42), "Not all who wander are lost");

    // Assert
    assertEquals(49L, logWriter.counter);
  }

  @Test
  void testLog14() {
    // Arrange
    LogWriter logWriter = new LogWriter((Writer) null);
    LogLevel logLevel = new LogLevel("Name", 42);

    // Act
    logWriter.log(logLevel, "Not all who wander are lost");

    // Assert that nothing has changed
    assertEquals("Name", logLevel.getName());
    assertEquals(42, logLevel.getValue());
    LogLevel expectedLoggingLevel = logLevel.INFO;
    assertSame(expectedLoggingLevel, logWriter.getLoggingLevel());
    assertTrue(logWriter.timestamp);
    assertEquals(0L, logWriter.max_size);
    assertEquals(0L, logWriter.counter);
  }

  @Test
  void testLog15() {
    // Arrange
    LogWriter logWriter = new LogWriter(new StringWriter());

    // Act
    logWriter.log(new LogLevel("Name", 60), "Not all who wander are lost");

    // Assert
    assertEquals(49L, logWriter.counter);
  }

  @Test
  void testLog16() {
    // Arrange
    LogWriter logWriter = new LogWriter((Writer) null);

    // Act
    logWriter.log(null, "Not all who wander are lost");

    // Assert that nothing has changed
    LogLevel expectedLoggingLevel = logWriter.logging_level;
    assertSame(expectedLoggingLevel, logWriter.getLoggingLevel());
    assertTrue(logWriter.timestamp);
    assertEquals(0L, logWriter.max_size);
    assertEquals(0L, logWriter.counter);
  }

  @Test
  void testLog17() {
    // Arrange
    LogWriter logWriter = new LogWriter((Writer) null, null);
    LogLevel logLevel = new LogLevel("Name", 42);

    // Act
    logWriter.log(logLevel, "Not all who wander are lost");

    // Assert that nothing has changed
    assertEquals("Name", logLevel.getName());
    assertEquals(42, logLevel.getValue());
    assertTrue(logWriter.timestamp);
    assertEquals(0L, logWriter.max_size);
    assertEquals(0L, logWriter.counter);
  }

  @Test
  void testLog18() {
    // Arrange
    StringWriter out = new StringWriter();
    LogWriter logWriter = new LogWriter(out, new LogLevel("OFF", 42));

    // Act
    logWriter.log(new LogLevel("Name", 42), "Not all who wander are lost");

    // Assert
    assertEquals(49L, logWriter.counter);
  }
}

