package org.mjsip.sdp.field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.mjsip.sdp.SdpField;

class SessionNameFieldDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange, Act and Assert
    assertEquals("-", (new SessionNameField()).getSession());
    assertEquals("Session name", (new SessionNameField("Session name")).getSession());
  }

  @Test
  void testConstructor2() {
    // Arrange and Act
    SessionNameField actualSessionNameField = new SessionNameField(new SdpField('A', "42"));

    // Assert
    assertEquals("42", actualSessionNameField.getSession());
    assertEquals('A', actualSessionNameField.getType());
  }

  @Test
  void testConstructor3() {
    // Arrange and Act
    SessionNameField actualSessionNameField = new SessionNameField(new SdpField('\u0000', "42"));

    // Assert
    assertEquals("42", actualSessionNameField.getSession());
    assertEquals('\u0000', actualSessionNameField.getType());
  }
}

