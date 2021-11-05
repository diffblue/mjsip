package org.mjsip.sdp;

import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Vector;
import org.junit.jupiter.api.Test;

class OfferAnswerModelDiffblueTest {
  @Test
  void testMakeMediaDescriptorProduct() {
    // Arrange
    Vector start_md_list = new Vector(1);

    // Act and Assert
    assertTrue(OfferAnswerModel.makeMediaDescriptorProduct(start_md_list, new Vector(1)).isEmpty());
  }

  @Test
  void testMakeMediaDescriptorProduct2() {
    // Arrange
    Vector vector = new Vector(1);
    vector.add("42");

    // Act and Assert
    assertTrue(OfferAnswerModel.makeMediaDescriptorProduct(vector, new Vector(1)).isEmpty());
  }
}

