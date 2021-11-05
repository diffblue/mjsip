package org.mjsip.sdp;

import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;

class SdesOfferAnswerModelDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    new SdesOfferAnswerModel();

    // Assert
    assertFalse(SdesOfferAnswerModel.DEBUG_LOOPBACK_MODE);
  }
}

