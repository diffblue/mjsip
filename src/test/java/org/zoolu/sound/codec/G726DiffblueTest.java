package org.zoolu.sound.codec;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

class G726DiffblueTest {
  @Test
  void testQuantize() {
    // Arrange, Act and Assert
    assertEquals(7, G726.quantize(1, 3, new int[]{1, 1, 1, 1}, 3));
    assertEquals(1, G726.quantize(0, 1, new int[]{1, 1, 1, 1}, 0));
    assertEquals(3, G726.quantize(AMR.M15_NO_DATA, 3, new int[]{1, 1, 1, 1}, 3));
    assertEquals(4, G726.quantize(-32768, 3, new int[]{1, 1, 1, 1}, 3));
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> G726.quantize(1, 3, new int[]{}, 3));
  }

  @Test
  void testReconstruct() {
    // Arrange, Act and Assert
    assertEquals(-32767, G726.reconstruct(1, 1, 3));
    assertEquals(-32767, G726.reconstruct(1, 1, 1));
    assertEquals(1, G726.reconstruct(0, 1, 3));
    assertEquals(-32767, G726.reconstruct(2, 1, 3));
    assertEquals(-32768, G726.reconstruct(1, -32768, 3));
    assertEquals(0, G726.reconstruct(0, -32768, 3));
  }

  @Test
  void testUpdate() {
    // Arrange
    G726State g726State = new G726State();

    // Act
    G726.update(3, 3, 1, 1, 1, 1, 1, g726State);

    // Assert
    assertEquals(544, g726State.yu);
    assertEquals(34816, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(0, g726State.dms);
    assertEquals(0, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
  }

  @Test
  void testUpdate2() {
    // Arrange
    G726State g726State = new G726State();

    // Act
    G726.update(5, 1536, 1, 1, 0, 0, 0, g726State);

    // Assert
    assertEquals(1488, g726State.yu);
    assertEquals(35760, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(0, g726State.dms);
    assertEquals(0, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
  }

  @Test
  void testUpdate3() {
    // Arrange
    G726State g726State = new G726State();

    // Act
    G726.update(5, 1536, 1, 1, 0, -32768, 0, g726State);

    // Assert
    assertEquals(1488, g726State.yu);
    assertEquals(35760, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(0, g726State.dms);
    assertEquals(0, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
  }

  @Test
  void testUpdate4() {
    // Arrange
    G726State g726State = new G726State();

    // Act
    G726.update(3, 32767, 1, 1, 1, 1, 1, g726State);

    // Assert
    assertEquals(5120, g726State.yu);
    assertEquals(39392, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(0, g726State.dms);
    assertEquals(0, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
  }

  @Test
  void testUpdate5() {
    // Arrange
    G726State g726State = new G726State();

    // Act
    G726.update(3, 3, 1, 1, 32767, 1, 1, g726State);

    // Assert
    assertEquals(544, g726State.yu);
    assertEquals(34816, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(0, g726State.dms);
    assertEquals(0, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
  }

  @Test
  void testUpdate6() {
    // Arrange
    G726State g726State = new G726State();

    // Act
    G726.update(3, 3, 1, 1, -32768, 1, 1, g726State);

    // Assert
    assertEquals(544, g726State.yu);
    assertEquals(34816, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(0, g726State.dms);
    assertEquals(0, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
  }

  @Test
  void testUpdate7() {
    // Arrange
    G726State g726State = new G726State();

    // Act
    G726.update(3, 3, 1, 1, -1, 1, 1, g726State);

    // Assert
    assertEquals(544, g726State.yu);
    assertEquals(34816, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(0, g726State.dms);
    assertEquals(0, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
  }

  @Test
  void testUpdate8() {
    // Arrange
    G726State g726State = new G726State();

    // Act
    G726.update(3, 3, 1, 1, 1, -1, 1, g726State);

    // Assert
    assertEquals(544, g726State.yu);
    assertEquals(34816, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(0, g726State.dms);
    assertEquals(0, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
  }

  @Test
  void testUpdate9() {
    // Arrange
    G726State g726State = new G726State();

    // Act
    G726.update(3, 3, 1, 1, 1, 1, -32768, g726State);

    // Assert
    assertEquals(544, g726State.yu);
    assertEquals(34816, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(0, g726State.dms);
    assertEquals(0, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
  }

  @Test
  void testUpdate10() {
    // Arrange
    G726State g726State = new G726State();

    // Act
    G726.update(544, 3, 1, 1, 1, 1, 1, g726State);

    // Assert
    assertEquals(544, g726State.yu);
    assertEquals(34816, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(0, g726State.dms);
    assertEquals(0, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
  }

  @Test
  void testTandem_adjust_alaw() {
    // Arrange, Act and Assert
    assertEquals(213, G726.tandem_adjust_alaw(1, 1, 3, 1, 1, new int[]{1, 1, 1, 1}));
    assertEquals(85, G726.tandem_adjust_alaw(-32768, 1, 1, 1, 1, new int[]{1, 1, 1, 1}));
    assertEquals(255, G726.tandem_adjust_alaw(213, 1, 3, 1, 1, new int[]{1, 1, 1, 1}));
    assertEquals(42, G726.tandem_adjust_alaw(-12416, 1, 3, 1, 1, new int[]{1, 1, 1, 1}));
    assertEquals(212, G726.tandem_adjust_alaw(1, 1, 3, 0, 1, new int[]{1, 1, 1, 1}));
    assertEquals(85, G726.tandem_adjust_alaw(1, 1, 3, -32768, 1, new int[]{1, 1, 1, 1}));
    assertEquals(252, G726.tandem_adjust_alaw(213, 1, 3, -32768, 1, new int[]{1, 1, 1, 1}));
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> G726.tandem_adjust_alaw(213, 1, 3, 1, 213, new int[]{1, 1, 1, 1}));
    assertEquals(254, G726.tandem_adjust_alaw(213, 1, 3, 1, 3, new int[]{1, 1, 1, 1}));
    assertEquals(43, G726.tandem_adjust_alaw(-12416, 1, 3, 0, 1, new int[]{1, 1, 1, 1}));
    assertEquals(42, G726.tandem_adjust_alaw(-12416, 1, 3, -32768, 1, new int[]{1, 1, 1, 1}));
    assertEquals(213, G726.tandem_adjust_alaw(-32768, 1, 3, 0, 1, new int[]{1, 1, 1, 1}));
    assertEquals(84, G726.tandem_adjust_alaw(-32768, 1, 3, -32768, 1, new int[]{1, 1, 1, 1}));
  }

  @Test
  void testTandem_adjust_ulaw() {
    // Arrange, Act and Assert
    assertEquals(254, G726.tandem_adjust_ulaw(1, 1, 3, 1, 1, new int[]{1, 1, 1, 1}));
    assertEquals(255, G726.tandem_adjust_ulaw(-32768, 1, 1, 1, 1, new int[]{1, 1, 1, 1}));
    assertEquals(253, G726.tandem_adjust_ulaw(4, 1, 3, 1, 1, new int[]{1, 1, 1, 1}));
    assertEquals(205, G726.tandem_adjust_ulaw(255, 1, 3, 1, 1, new int[]{1, 1, 1, 1}));
    assertEquals(126, G726.tandem_adjust_ulaw(-1, 1, 3, 1, 1, new int[]{1, 1, 1, 1}));
    assertEquals(0, G726.tandem_adjust_ulaw(-12416, 1, 3, 1, 1, new int[]{1, 1, 1, 1}));
    assertEquals(253, G726.tandem_adjust_ulaw(1, 1, 3, 0, 1, new int[]{1, 1, 1, 1}));
    assertEquals(255, G726.tandem_adjust_ulaw(1, 1, 3, -32768, 1, new int[]{1, 1, 1, 1}));
    assertEquals(254, G726.tandem_adjust_ulaw(4, 1, 3, 1, 4, new int[]{1, 1, 1, 1}));
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> G726.tandem_adjust_ulaw(4, 1, 3, 1, Short.SIZE, new int[]{1, 1, 1, 1}));
    assertEquals(Float.MAX_EXPONENT, G726.tandem_adjust_ulaw(-1, 1, 3, 0, 1, new int[]{1, 1, 1, 1}));
    assertEquals(125, G726.tandem_adjust_ulaw(-1, 1, 3, -32768, 1, new int[]{1, 1, 1, 1}));
    assertEquals(0, G726.tandem_adjust_ulaw(-12416, 1, 3, -32768, 1, new int[]{1, 1, 1, 1}));
    assertEquals(126, G726.tandem_adjust_ulaw(0, 1, 3, -32768, 1, new int[]{1, 1, 1, 1}));
  }

  @Test
  void testUnsignedInt() {
    // Arrange, Act and Assert
    assertEquals(65, G726.unsignedInt((byte) 'A'));
  }

  @Test
  void testUnsignedIntLittleEndian() {
    // Arrange, Act and Assert
    assertEquals(16705, G726.unsignedIntLittleEndian((byte) 'A', (byte) 'A'));
    assertEquals(65, G726.unsignedIntLittleEndian((byte) 0, (byte) 'A'));
    assertEquals(2113, G726.unsignedIntLittleEndian((byte) 8, (byte) 'A'));
  }

  @Test
  void testSignedIntLittleEndian() {
    // Arrange, Act and Assert
    assertEquals(16705, G726.signedIntLittleEndian((byte) 'A', (byte) 'A'));
    assertEquals(65, G726.signedIntLittleEndian((byte) 0, (byte) 'A'));
    assertEquals(1857, G726.signedIntLittleEndian((byte) 7, (byte) 'A'));
  }
}

