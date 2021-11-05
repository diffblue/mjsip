package org.mjsip.rtp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Objects;
import org.junit.jupiter.api.Test;

class NtpTimeStampDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    NtpTimeStamp actualNtpTimeStamp = new NtpTimeStamp(10L);

    // Assert
    assertEquals(42949672L, actualNtpTimeStamp.getNtpFraction());
    assertEquals(2208988800L, actualNtpTimeStamp.getNtpSeconds());
    assertEquals("83aa7e80.28f5c28", actualNtpTimeStamp.toString());
  }

  @Test
  void testConstructor2() {
    // Arrange and Act
    NtpTimeStamp actualNtpTimeStamp = new NtpTimeStamp(10L);

    // Assert
    assertEquals(42949672L, actualNtpTimeStamp.getNtpFraction());
    assertEquals(2208988800L, actualNtpTimeStamp.getNtpSeconds());
  }

  @Test
  void testConstructor3() {
    // Arrange and Act
    NtpTimeStamp actualNtpTimeStamp = new NtpTimeStamp(1L, 1L);

    // Assert
    assertEquals(1L, actualNtpTimeStamp.getNtpFraction());
    assertEquals(1L, actualNtpTimeStamp.getNtpSeconds());
  }

  @Test
  void testGetNtpTime() {
    // Arrange, Act and Assert
    assertEquals(-8959209420436317144L, (new NtpTimeStamp(10L)).getNtpTime());
    assertEquals(4294967297L, (new NtpTimeStamp(1L, 1L)).getNtpTime());
  }

  @Test
  void testGetTime() {
    // Arrange, Act and Assert
    assertEquals(42949672L, (new NtpTimeStamp(10L)).getTime());
    assertEquals(2085978497001L, (new NtpTimeStamp(1L, 1L)).getTime());
  }

  @Test
  void testCompareTo() throws ClassCastException {
    // Arrange
    NtpTimeStamp ntpTimeStamp = new NtpTimeStamp(10L);

    // Act and Assert
    assertEquals(0, ntpTimeStamp.compareTo(new NtpTimeStamp(10L)));
  }

  @Test
  void testCompareTo2() throws ClassCastException {
    // Arrange
    NtpTimeStamp ntpTimeStamp = new NtpTimeStamp(10L);

    // Act and Assert
    assertEquals(42949671, ntpTimeStamp.compareTo(new NtpTimeStamp(1L, 1L)));
  }

  @Test
  void testCompareTo3() throws ClassCastException {
    // Arrange
    NtpTimeStamp ntpTimeStamp = new NtpTimeStamp(1L, 1L);

    // Act and Assert
    assertEquals(-42949671, ntpTimeStamp.compareTo(new NtpTimeStamp(10L)));
  }

  @Test
  void testEquals() {
    // Arrange, Act and Assert
    assertFalse((new NtpTimeStamp(10L)).equals(null));
    assertFalse((new NtpTimeStamp(10L)).equals("Different type to NtpTimeStamp"));
  }

  @Test
  void testEquals2() {
    // Arrange
    NtpTimeStamp ntpTimeStamp = new NtpTimeStamp(10L);

    // Act and Assert
    assertTrue(ntpTimeStamp.equals(ntpTimeStamp));
    int expectedHashCodeResult = ntpTimeStamp.hashCode();
    assertEquals(expectedHashCodeResult, ntpTimeStamp.hashCode());
  }

  @Test
  void testEquals3() {
    // Arrange
    NtpTimeStamp ntpTimeStamp = new NtpTimeStamp(10L);
    NtpTimeStamp ntpTimeStamp1 = new NtpTimeStamp(10L);

    // Act and Assert
    assertTrue(ntpTimeStamp.equals(ntpTimeStamp1));
    int notExpectedHashCodeResult = ntpTimeStamp.hashCode();
    assertFalse(Objects.equals(notExpectedHashCodeResult, ntpTimeStamp1.hashCode()));
  }

  @Test
  void testEquals4() {
    // Arrange
    NtpTimeStamp ntpTimeStamp = new NtpTimeStamp(0L);

    // Act and Assert
    assertFalse(ntpTimeStamp.equals(new NtpTimeStamp(10L)));
  }

  @Test
  void testEquals5() {
    // Arrange
    NtpTimeStamp ntpTimeStamp = new NtpTimeStamp(-2085978496L);

    // Act and Assert
    assertFalse(ntpTimeStamp.equals(new NtpTimeStamp(10L)));
  }

  @Test
  void testEquals6() {
    // Arrange
    NtpTimeStamp ntpTimeStamp = new NtpTimeStamp(1L, 1L);

    // Act and Assert
    assertFalse(ntpTimeStamp.equals(new NtpTimeStamp(10L)));
  }

  @Test
  void testEquals7() {
    // Arrange
    NtpTimeStamp ntpTimeStamp = new NtpTimeStamp(10L);

    // Act and Assert
    assertFalse(ntpTimeStamp.equals(new NtpTimeStamp(1L, 1L)));
  }
}

