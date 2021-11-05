package org.mjsip.sdp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.mjsip.sdp.field.TimeField;

class TimeDescriptionDiffblueTest {
  @Test
  void testToString() {
    // Arrange, Act and Assert
    assertEquals("t=Time field\r\n", (new TimeDescription(new TimeField("Time field"))).toString());
    assertEquals("t=Time field\r\n", (new TimeDescription(new TimeField("Time field"), new SdpField[]{})).toString());
  }
}

