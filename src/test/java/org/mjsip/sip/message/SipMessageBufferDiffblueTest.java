package org.mjsip.sip.message;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.io.UnsupportedEncodingException;
import org.junit.jupiter.api.Test;

class SipMessageBufferDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    SipMessageBuffer actualSipMessageBuffer = new SipMessageBuffer();

    // Assert
    assertNull(actualSipMessageBuffer.getBuffer());
    assertEquals(0, actualSipMessageBuffer.getOffset());
  }

  @Test
  void testGetLength() {
    // Arrange, Act and Assert
    assertEquals(0, (new SipMessageBuffer()).getLength());
  }

  @Test
  void testGetLength2() throws UnsupportedEncodingException {
    // Arrange
    SipMessageBuffer sipMessageBuffer = new SipMessageBuffer();
    sipMessageBuffer.append("AAAAAAAA".getBytes("UTF-8"), 0, 3);

    // Act and Assert
    assertEquals(3, sipMessageBuffer.getLength());
  }

  @Test
  void testByteAt() throws UnsupportedEncodingException {
    // Arrange
    SipMessageBuffer sipMessageBuffer = new SipMessageBuffer();
    sipMessageBuffer.append("AAAAAAAA".getBytes("UTF-8"), 0, 3);

    // Act and Assert
    assertEquals('A', sipMessageBuffer.byteAt(1));
  }

  @Test
  void testSkip() {
    // Arrange, Act and Assert
    assertThrows(RuntimeException.class, () -> (new SipMessageBuffer()).skip(1));
  }

  @Test
  void testSkip2() throws UnsupportedEncodingException {
    // Arrange
    SipMessageBuffer sipMessageBuffer = new SipMessageBuffer();
    sipMessageBuffer.append("AAAAAAAA".getBytes("UTF-8"), 0, 3);

    // Act
    SipMessageBuffer actualSkipResult = sipMessageBuffer.skip(1);

    // Assert
    assertSame(sipMessageBuffer, actualSkipResult);
    assertEquals(1, actualSkipResult.getOffset());
  }

  @Test
  void testSkip3() throws UnsupportedEncodingException {
    // Arrange
    SipMessageBuffer sipMessageBuffer = new SipMessageBuffer();
    sipMessageBuffer.append("AAAAAAAA".getBytes("UTF-8"), 0, 0);

    // Act and Assert
    assertThrows(RuntimeException.class, () -> sipMessageBuffer.skip(1));
  }

  @Test
  void testAppend() throws UnsupportedEncodingException {
    // Arrange
    SipMessageBuffer sipMessageBuffer = new SipMessageBuffer();

    // Act
    SipMessageBuffer actualAppendResult = sipMessageBuffer.append("AAAAAAAA".getBytes("UTF-8"));

    // Assert
    assertSame(sipMessageBuffer, actualAppendResult);
    assertEquals(8, actualAppendResult.getBuffer().length);
    assertEquals(0, actualAppendResult.getOffset());
  }

  @Test
  void testAppend2() throws UnsupportedEncodingException {
    // Arrange
    SipMessageBuffer sipMessageBuffer = new SipMessageBuffer();
    sipMessageBuffer.append("AAAAAAAA".getBytes("UTF-8"), 0, 3);

    // Act
    SipMessageBuffer actualAppendResult = sipMessageBuffer.append("AAAAAAAA".getBytes("UTF-8"));

    // Assert
    assertSame(sipMessageBuffer, actualAppendResult);
    assertEquals(11, actualAppendResult.getBuffer().length);
    assertEquals(0, actualAppendResult.getOffset());
  }

  @Test
  void testAppend3() throws UnsupportedEncodingException {
    // Arrange
    SipMessageBuffer sipMessageBuffer = new SipMessageBuffer();

    // Act
    SipMessageBuffer actualAppendResult = sipMessageBuffer.append("AAAAAAAA".getBytes("UTF-8"), 1, 3);

    // Assert
    assertSame(sipMessageBuffer, actualAppendResult);
    assertEquals(3, actualAppendResult.getBuffer().length);
    assertEquals(0, actualAppendResult.getOffset());
  }

  @Test
  void testAppend4() throws UnsupportedEncodingException {
    // Arrange
    SipMessageBuffer sipMessageBuffer = new SipMessageBuffer();
    sipMessageBuffer.append("AAAAAAAA".getBytes("UTF-8"), 0, 3);

    // Act
    SipMessageBuffer actualAppendResult = sipMessageBuffer.append("AAAAAAAA".getBytes("UTF-8"), 1, 3);

    // Assert
    assertSame(sipMessageBuffer, actualAppendResult);
    assertEquals(6, actualAppendResult.getBuffer().length);
    assertEquals(0, actualAppendResult.getOffset());
  }

  @Test
  void testAppend5() throws UnsupportedEncodingException {
    // Arrange
    SipMessageBuffer sipMessageBuffer = new SipMessageBuffer();

    // Act and Assert
    assertThrows(NegativeArraySizeException.class, () -> sipMessageBuffer.append("AAAAAAAA".getBytes("UTF-8"), 1, -1));
  }

  @Test
  void testAppend6() throws UnsupportedEncodingException {
    // Arrange
    SipMessageBuffer sipMessageBuffer = new SipMessageBuffer();
    sipMessageBuffer.append("AAAAAAAA".getBytes("UTF-8"), 0, 3);

    // Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> sipMessageBuffer.append("AAAAAAAA".getBytes("UTF-8"), 1, -1));
  }
}

