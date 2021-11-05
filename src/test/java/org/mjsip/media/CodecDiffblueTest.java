package org.mjsip.media;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.Test;
import org.zoolu.util.Encoder;

class CodecDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    Codec actualCodec = new Codec(mock(Encoder.class), mock(Encoder.class));

    // Assert
    Encoder expectedDecoder = actualCodec.decoder;
    assertSame(expectedDecoder, actualCodec.getDecoder());
    Encoder expectedEncoder = actualCodec.encoder;
    assertSame(expectedEncoder, actualCodec.getEncoder());
  }
}

