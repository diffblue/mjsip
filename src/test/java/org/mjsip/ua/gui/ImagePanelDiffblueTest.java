package org.mjsip.ua.gui;

import static org.junit.jupiter.api.Assertions.assertSame;
import java.awt.image.BufferedImage;
import org.junit.jupiter.api.Test;

class ImagePanelDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange
    BufferedImage bufferedImage = new BufferedImage(1, 1, 1);

    // Act and Assert
    assertSame((new ImagePanel(bufferedImage)).image, bufferedImage);
  }
}

