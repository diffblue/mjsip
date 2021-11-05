package org.zoolu.sound;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Objects;
import org.junit.jupiter.api.Test;

class CodecTypeDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    CodecType actualCodecType = new CodecType("Name", 1, 3, 1);

    // Assert
    assertEquals(3, actualCodecType.getFrameSize());
    assertEquals("Name", actualCodecType.getName());
    assertEquals(1, actualCodecType.getPayloadType());
    assertEquals(1, actualCodecType.getSamplesPerFrame());
    assertEquals("Name", actualCodecType.toString());
  }

  @Test
  void testGetByName() {
    // Arrange, Act and Assert
    assertNull(CodecType.getByName("Name"));
  }

  @Test
  void testGetByName2() {
    // Arrange and Act
    CodecType actualByName = CodecType.getByName("PCM_SIGNED");

    // Assert
    assertSame(actualByName.PCM_LINEAR, actualByName);
  }

  @Test
  void testGetByName3() {
    // Arrange and Act
    CodecType actualByName = CodecType.getByName("PCM_LINEAR");

    // Assert
    assertSame(actualByName.PCM_LINEAR, actualByName);
  }

  @Test
  void testGetByName4() {
    // Arrange and Act
    CodecType actualByName = CodecType.getByName("linear");

    // Assert
    assertSame(actualByName.PCM_LINEAR, actualByName);
  }

  @Test
  void testGetByName5() {
    // Arrange and Act
    CodecType actualByName = CodecType.getByName("PCMU");

    // Assert
    assertSame(actualByName.G711_ULAW, actualByName);
  }

  @Test
  void testGetByName6() {
    // Arrange and Act
    CodecType actualByName = CodecType.getByName("ULAW");

    // Assert
    assertSame(actualByName.G711_ULAW, actualByName);
  }

  @Test
  void testGetByName7() {
    // Arrange and Act
    CodecType actualByName = CodecType.getByName("PCM_ULAW");

    // Assert
    assertSame(actualByName.G711_ULAW, actualByName);
  }

  @Test
  void testGetByName8() {
    // Arrange and Act
    CodecType actualByName = CodecType.getByName("G711_ULAW");

    // Assert
    assertSame(actualByName.G711_ULAW, actualByName);
  }

  @Test
  void testGetByName9() {
    // Arrange and Act
    CodecType actualByName = CodecType.getByName("ALAW");

    // Assert
    assertSame(actualByName.G711_ALAW, actualByName);
  }

  @Test
  void testGetByName10() {
    // Arrange and Act
    CodecType actualByName = CodecType.getByName("PCM-ulaw");

    // Assert
    assertSame(actualByName.G711_ULAW, actualByName);
  }

  @Test
  void testGetByName11() {
    // Arrange and Act
    CodecType actualByName = CodecType.getByName("AMR");

    // Assert
    assertSame(actualByName.AMR_0475, actualByName);
  }

  @Test
  void testGetByName12() {
    // Arrange and Act
    CodecType actualByName = CodecType.getByName("G711-ulaw");

    // Assert
    assertSame(actualByName.G711_ULAW, actualByName);
  }

  @Test
  void testGetByName13() {
    // Arrange and Act
    CodecType actualByName = CodecType.getByName("AMR-10.2");

    // Assert
    assertSame(actualByName.AMR_1020, actualByName);
  }

  @Test
  void testGetByName14() {
    // Arrange and Act
    CodecType actualByName = CodecType.getByName("PCMA");

    // Assert
    assertSame(actualByName.G711_ALAW, actualByName);
  }

  @Test
  void testGetByName15() {
    // Arrange and Act
    CodecType actualByName = CodecType.getByName("AMR-12.2");

    // Assert
    assertSame(actualByName.AMR_1220, actualByName);
  }

  @Test
  void testGetByName16() {
    // Arrange and Act
    CodecType actualByName = CodecType.getByName("AMR-4.75");

    // Assert
    assertSame(actualByName.AMR_0475, actualByName);
  }

  @Test
  void testGetByName17() {
    // Arrange and Act
    CodecType actualByName = CodecType.getByName("PCM_ALAW");

    // Assert
    assertSame(actualByName.G711_ALAW, actualByName);
  }

  @Test
  void testGetByName18() {
    // Arrange and Act
    CodecType actualByName = CodecType.getByName("AMR-5.15");

    // Assert
    assertSame(actualByName.AMR_0515, actualByName);
  }

  @Test
  void testGetByName19() {
    // Arrange and Act
    CodecType actualByName = CodecType.getByName("AMR-5.9");

    // Assert
    assertSame(actualByName.AMR_0590, actualByName);
  }

  @Test
  void testGetByName20() {
    // Arrange and Act
    CodecType actualByName = CodecType.getByName("AMR-6.7");

    // Assert
    assertSame(actualByName.AMR_0670, actualByName);
  }

  @Test
  void testGetByName21() {
    // Arrange and Act
    CodecType actualByName = CodecType.getByName("AMR-7.4");

    // Assert
    assertSame(actualByName.AMR_0740, actualByName);
  }

  @Test
  void testGetByName22() {
    // Arrange and Act
    CodecType actualByName = CodecType.getByName("AMR-7.95");

    // Assert
    assertSame(actualByName.AMR_0795, actualByName);
  }

  @Test
  void testGetByName23() {
    // Arrange and Act
    CodecType actualByName = CodecType.getByName("AMR-NB");

    // Assert
    assertSame(actualByName.AMR_NB, actualByName);
  }

  @Test
  void testGetByName24() {
    // Arrange and Act
    CodecType actualByName = CodecType.getByName("AMR_0475");

    // Assert
    assertSame(actualByName.AMR_0475, actualByName);
  }

  @Test
  void testGetByName25() {
    // Arrange and Act
    CodecType actualByName = CodecType.getByName("AMR_0515");

    // Assert
    assertSame(actualByName.AMR_0515, actualByName);
  }

  @Test
  void testGetByName26() {
    // Arrange and Act
    CodecType actualByName = CodecType.getByName("AMR_0590");

    // Assert
    assertSame(actualByName.AMR_0590, actualByName);
  }

  @Test
  void testGetByName27() {
    // Arrange and Act
    CodecType actualByName = CodecType.getByName("AMR_0670");

    // Assert
    assertSame(actualByName.AMR_0670, actualByName);
  }

  @Test
  void testGetByName28() {
    // Arrange and Act
    CodecType actualByName = CodecType.getByName("AMR_0740");

    // Assert
    assertSame(actualByName.AMR_0740, actualByName);
  }

  @Test
  void testGetByName29() {
    // Arrange and Act
    CodecType actualByName = CodecType.getByName("AMR_0795");

    // Assert
    assertSame(actualByName.AMR_0795, actualByName);
  }

  @Test
  void testEquals() {
    // Arrange, Act and Assert
    assertFalse((new CodecType("Name", 1, 3, 1)).equals(null));
    assertFalse((new CodecType("Name", 1, 3, 1)).equals("Different type to CodecType"));
  }

  @Test
  void testEquals2() {
    // Arrange
    CodecType codecType = new CodecType("Name", 1, 3, 1);

    // Act and Assert
    assertTrue(codecType.equals(codecType));
    int expectedHashCodeResult = codecType.hashCode();
    assertEquals(expectedHashCodeResult, codecType.hashCode());
  }

  @Test
  void testEquals3() {
    // Arrange
    CodecType codecType = new CodecType("Name", 1, 3, 1);
    CodecType codecType1 = new CodecType("Name", 1, 3, 1);

    // Act and Assert
    assertTrue(codecType.equals(codecType1));
    int notExpectedHashCodeResult = codecType.hashCode();
    assertFalse(Objects.equals(notExpectedHashCodeResult, codecType1.hashCode()));
  }

  @Test
  void testEquals4() {
    // Arrange
    CodecType codecType = new CodecType("ALAW", 1, 3, 1);

    // Act and Assert
    assertFalse(codecType.equals(new CodecType("Name", 1, 3, 1)));
  }
}

