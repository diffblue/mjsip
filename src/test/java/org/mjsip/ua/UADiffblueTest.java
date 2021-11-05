package org.mjsip.ua;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class UADiffblueTest {
  @Test
  void testInit() {
    // Arrange, Act and Assert
    assertFalse(UA.init("Program", new String[]{"Args"}));
    assertTrue(UA.init("Program", new String[]{"--skip"}));
    assertFalse(UA.init("Program", new String[]{"-h"}));
    assertFalse(UA.init("Program", new String[]{"<file>"}));
    assertTrue(UA.init("Program", new String[]{"-u"}));
    assertTrue(UA.init("Program", new String[]{"-z"}));
    assertFalse(UA.init("unregisters ALL contact addresses", new String[]{"Args"}));
    assertTrue(UA.init("--skip", new String[]{"-u"}));
    assertFalse(UA.init("-h", new String[]{"prints this message"}));
    assertTrue(UA.init("Program", new String[]{"-n"}));
  }

  @Test
  void testConstructor() {
    // Arrange and Act
    new UA();

    // Assert
    assertFalse(UA.no_gui);
  }
}

