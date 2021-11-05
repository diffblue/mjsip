package org.mjsip.media;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import org.junit.jupiter.api.Test;

class FlowSpecDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange
    MediaSpec media_spec = new MediaSpec("Type", 1, "Codec", 1, 1, 3);

    // Act
    FlowSpec actualFlowSpec = new FlowSpec(media_spec, 8080, "42 Main St", 8080, new FlowSpec.Direction());

    // Assert
    FlowSpec.Direction expectedDirection = actualFlowSpec.direction;
    assertSame(expectedDirection, actualFlowSpec.getDirection());
    assertEquals(8080, actualFlowSpec.getRemotePort());
    assertEquals("42 Main St", actualFlowSpec.getRemoteAddress());
    MediaSpec expectedMediaSpec = actualFlowSpec.media_spec;
    assertSame(expectedMediaSpec, actualFlowSpec.getMediaSpec());
    assertEquals(8080, actualFlowSpec.getLocalPort());
  }
}

