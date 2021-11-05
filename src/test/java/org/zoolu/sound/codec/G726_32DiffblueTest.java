package org.zoolu.sound.codec;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.io.UnsupportedEncodingException;
import javax.sound.sampled.AudioFormat;
import org.junit.jupiter.api.Test;
import org.zoolu.sound.codec.g726.G726Encoding;

class G726_32DiffblueTest {
  @Test
  void testEncode() {
    // Arrange
    G726_32 g726_32 = new G726_32();

    // Act and Assert
    assertEquals(8, g726_32.encode(1, 1));
    G726State g726State = g726_32.state;
    assertEquals(35921, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(112, g726State.dms);
    assertEquals(112, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
    assertEquals(1649, g726State.yu);
  }

  @Test
  void testEncode2() {
    // Arrange
    G726_32 g726_32 = new G726_32();

    // Act and Assert
    assertEquals(8, g726_32.encode(0, 1));
    G726State g726State = g726_32.state;
    assertEquals(35921, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(112, g726State.dms);
    assertEquals(112, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
    assertEquals(1649, g726State.yu);
  }

  @Test
  void testEncode3() {
    // Arrange
    G726_32 g726_32 = new G726_32();

    // Act and Assert
    assertEquals(8, g726_32.encode(2, 1));
    G726State g726State = g726_32.state;
    assertEquals(35921, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(112, g726State.dms);
    assertEquals(112, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
    assertEquals(1649, g726State.yu);
  }

  @Test
  void testEncode4() {
    // Arrange
    G726_32 g726_32 = new G726_32();

    // Act and Assert
    assertEquals(8, g726_32.encode(3, 1));
    G726State g726State = g726_32.state;
    assertEquals(35921, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(112, g726State.dms);
    assertEquals(112, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
    assertEquals(1649, g726State.yu);
  }

  @Test
  void testEncode5() {
    // Arrange
    G726_32 g726_32 = new G726_32();

    // Act and Assert
    assertEquals(AMR.M15_NO_DATA, g726_32.encode(-1, 1));
    G726State g726State = g726_32.state;
    assertEquals(34816, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(0, g726State.dms);
    assertEquals(0, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
    assertEquals(544, g726State.yu);
  }

  @Test
  void testEncode6() {
    // Arrange
    G726_32 g726_32 = new G726_32();

    // Act and Assert
    assertEquals(7, g726_32.encode(G711.BIAS, 1));
    G726State g726State = g726_32.state;
    assertEquals(35921, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(112, g726State.dms);
    assertEquals(112, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
    assertEquals(1649, g726State.yu);
  }

  @Test
  void testEncode7() {
    // Arrange, Act and Assert
    assertEquals(-1, (new G726_32()).encode(1, 0));
  }

  @Test
  void testEncode8() {
    // Arrange
    G726_32 g726_32 = new G726_32();

    // Act and Assert
    assertEquals(8, g726_32.encode(1, 2));
    G726State g726State = g726_32.state;
    assertEquals(35921, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(112, g726State.dms);
    assertEquals(112, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
    assertEquals(1649, g726State.yu);
  }

  @Test
  void testEncode9() {
    // Arrange
    G726_32 g726_32 = new G726_32();

    // Act and Assert
    assertEquals(AMR.M15_NO_DATA, g726_32.encode(1, 3));
    G726State g726State = g726_32.state;
    assertEquals(34816, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(0, g726State.dms);
    assertEquals(0, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
    assertEquals(544, g726State.yu);
  }

  @Test
  void testEncode10() {
    // Arrange
    G726_32 g726_32 = new G726_32();

    // Act and Assert
    assertEquals(7, g726_32.encode(-1, 2));
    G726State g726State = g726_32.state;
    assertEquals(35921, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(112, g726State.dms);
    assertEquals(112, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
    assertEquals(1649, g726State.yu);
  }

  @Test
  void testEncode11() {
    // Arrange
    G726_32 g726_32 = new G726_32();

    // Act and Assert
    assertEquals(14, g726_32.encode(85, 2));
    G726State g726State = g726_32.state;
    assertEquals(34817, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(0, g726State.dms);
    assertEquals(0, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
    assertEquals(545, g726State.yu);
  }

  @Test
  void testEncode12() {
    // Arrange
    G726State g726State = new G726State();

    // Act and Assert
    assertEquals(8, G726_32.encode(1, 1, g726State));
    assertEquals(1649, g726State.yu);
    assertEquals(35921, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(112, g726State.dms);
    assertEquals(112, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
  }

  @Test
  void testEncode13() {
    // Arrange
    G726State g726State = new G726State();

    // Act and Assert
    assertEquals(8, G726_32.encode(1, 2, g726State));
    assertEquals(1649, g726State.yu);
    assertEquals(35921, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(112, g726State.dms);
    assertEquals(112, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
  }

  @Test
  void testEncode14() {
    // Arrange
    G726State g726State = new G726State();

    // Act and Assert
    assertEquals(AMR.M15_NO_DATA, G726_32.encode(1, 3, g726State));
    assertEquals(544, g726State.yu);
    assertEquals(34816, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(0, g726State.dms);
    assertEquals(0, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
  }

  @Test
  void testEncode15() {
    // Arrange
    G726State g726State = new G726State();

    // Act and Assert
    assertEquals(AMR.M15_NO_DATA, G726_32.encode(-1, 1, g726State));
    assertEquals(544, g726State.yu);
    assertEquals(34816, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(0, g726State.dms);
    assertEquals(0, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
  }

  @Test
  void testEncode16() {
    // Arrange
    G726State g726State = new G726State();

    // Act and Assert
    assertEquals(7, G726_32.encode(G711.BIAS, 1, g726State));
    assertEquals(1649, g726State.yu);
    assertEquals(35921, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(112, g726State.dms);
    assertEquals(112, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
  }

  @Test
  void testEncode17() {
    // Arrange, Act and Assert
    assertEquals(-1, G726_32.encode(1, 0, new G726State()));
  }

  @Test
  void testEncode18() {
    // Arrange
    G726State g726State = new G726State();

    // Act and Assert
    assertEquals(7, G726_32.encode(128, 1, g726State));
    assertEquals(1649, g726State.yu);
    assertEquals(35921, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(112, g726State.dms);
    assertEquals(112, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
  }

  @Test
  void testEncode19() {
    // Arrange
    G726State g726State = new G726State();

    // Act and Assert
    assertEquals(7, G726_32.encode(-1, 2, g726State));
    assertEquals(1649, g726State.yu);
    assertEquals(35921, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(112, g726State.dms);
    assertEquals(112, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
  }

  @Test
  void testEncode20() {
    // Arrange
    G726State g726State = new G726State();

    // Act and Assert
    assertEquals(14, G726_32.encode(85, 2, g726State));
    assertEquals(545, g726State.yu);
    assertEquals(34817, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(0, g726State.dms);
    assertEquals(0, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
  }

  @Test
  void testEncode21() throws UnsupportedEncodingException {
    // Arrange
    G726_32 g726_32 = new G726_32();
    byte[] in_buff = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertEquals(1, g726_32.encode(in_buff, 2, 3, 1, "AAAAAAAA".getBytes("UTF-8"), 2));
    G726State g726State = g726_32.state;
    assertEquals(37156, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(220, g726State.dms);
    assertEquals(223, g726State.dml);
    assertEquals(62, g726State.ap);
    assertEquals(1797, g726State.yu);
  }

  @Test
  void testEncode22() throws UnsupportedEncodingException {
    // Arrange
    G726_32 g726_32 = new G726_32();
    byte[] in_buff = "AAﾄAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertEquals(1, g726_32.encode(in_buff, 2, 3, 1, "AAAAAAAA".getBytes("UTF-8"), 2));
    G726State g726State = g726_32.state;
    assertEquals(37156, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(220, g726State.dms);
    assertEquals(223, g726State.dml);
    assertEquals(62, g726State.ap);
    assertEquals(1797, g726State.yu);
  }

  @Test
  void testEncode23() throws UnsupportedEncodingException {
    // Arrange
    G726_32 g726_32 = new G726_32();

    // Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> g726_32.encode(new byte[]{}, 2, 3, 1, "AAAAAAAA".getBytes("UTF-8"), 2));
  }

  @Test
  void testEncode24() throws UnsupportedEncodingException {
    // Arrange
    G726_32 g726_32 = new G726_32();
    byte[] in_buff = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> g726_32.encode(in_buff, 2, AMR.M15_NO_DATA, 1, "AAAAAAAA".getBytes("UTF-8"), 2));
  }

  @Test
  void testEncode25() throws UnsupportedEncodingException {
    // Arrange
    G726_32 g726_32 = new G726_32();
    byte[] in_buff = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertEquals(-1, g726_32.encode(in_buff, 2, 3, 0, "AAAAAAAA".getBytes("UTF-8"), 2));
  }

  @Test
  void testEncode26() throws UnsupportedEncodingException {
    // Arrange
    G726_32 g726_32 = new G726_32();
    byte[] in_buff = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertEquals(1, g726_32.encode(in_buff, 2, 3, 2, "AAAAAAAA".getBytes("UTF-8"), 2));
    G726State g726State = g726_32.state;
    assertEquals(37156, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(220, g726State.dms);
    assertEquals(223, g726State.dml);
    assertEquals(62, g726State.ap);
    assertEquals(1797, g726State.yu);
  }

  @Test
  void testEncode27() throws UnsupportedEncodingException {
    // Arrange
    G726_32 g726_32 = new G726_32();
    byte[] in_buff = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertEquals(0, g726_32.encode(in_buff, 2, 3, 3, "AAAAAAAA".getBytes("UTF-8"), 2));
  }

  @Test
  void testEncode28() throws UnsupportedEncodingException {
    // Arrange
    G726_32 g726_32 = new G726_32();

    // Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> g726_32.encode("AAAAAAAA".getBytes("UTF-8"), 2, 3, 1, new byte[]{}, 2));
  }

  @Test
  void testEncode29() throws UnsupportedEncodingException {
    // Arrange
    G726_32 g726_32 = new G726_32();
    byte[] in_buff = "AA￿AAAAA".getBytes("UTF-8");

    // Act and Assert
    assertEquals(1, g726_32.encode(in_buff, 2, 3, 2, "AAAAAAAA".getBytes("UTF-8"), 2));
    G726State g726State = g726_32.state;
    assertEquals(37156, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(220, g726State.dms);
    assertEquals(223, g726State.dml);
    assertEquals(62, g726State.ap);
    assertEquals(1797, g726State.yu);
  }

  @Test
  void testEncode30() throws UnsupportedEncodingException {
    // Arrange
    byte[] in_buff = "AAAAAAAA".getBytes("UTF-8");
    byte[] out_buff = "AAAAAAAA".getBytes("UTF-8");
    G726State g726State = new G726State();

    // Act and Assert
    assertEquals(1, G726_32.encode(in_buff, 2, 3, 1, out_buff, 2, g726State));
    assertEquals(1797, g726State.yu);
    assertEquals(37156, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(220, g726State.dms);
    assertEquals(223, g726State.dml);
    assertEquals(62, g726State.ap);
  }

  @Test
  void testEncode31() throws UnsupportedEncodingException {
    // Arrange
    byte[] in_buff = "AAﾄAAAAA".getBytes("UTF-8");
    byte[] out_buff = "AAAAAAAA".getBytes("UTF-8");
    G726State g726State = new G726State();

    // Act and Assert
    assertEquals(1, G726_32.encode(in_buff, 2, 3, 1, out_buff, 2, g726State));
    assertEquals(1797, g726State.yu);
    assertEquals(37156, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(220, g726State.dms);
    assertEquals(223, g726State.dml);
    assertEquals(62, g726State.ap);
  }

  @Test
  void testEncode32() throws UnsupportedEncodingException {
    // Arrange
    byte[] out_buff = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> G726_32.encode(new byte[]{}, 2, 3, 1, out_buff, 2, new G726State()));
  }

  @Test
  void testEncode33() throws UnsupportedEncodingException {
    // Arrange
    byte[] in_buff = "AAAAAAAA".getBytes("UTF-8");
    byte[] out_buff = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> G726_32.encode(in_buff, 2, AMR.M15_NO_DATA, 1, out_buff, 2, new G726State()));
  }

  @Test
  void testEncode34() throws UnsupportedEncodingException {
    // Arrange
    byte[] in_buff = "AAAAAAAA".getBytes("UTF-8");
    byte[] out_buff = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertEquals(-1, G726_32.encode(in_buff, 2, 3, 0, out_buff, 2, new G726State()));
  }

  @Test
  void testEncode35() throws UnsupportedEncodingException {
    // Arrange
    byte[] in_buff = "AAAAAAAA".getBytes("UTF-8");
    byte[] out_buff = "AAAAAAAA".getBytes("UTF-8");
    G726State g726State = new G726State();

    // Act and Assert
    assertEquals(1, G726_32.encode(in_buff, 2, 3, 2, out_buff, 2, g726State));
    assertEquals(1797, g726State.yu);
    assertEquals(37156, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(220, g726State.dms);
    assertEquals(223, g726State.dml);
    assertEquals(62, g726State.ap);
  }

  @Test
  void testEncode36() throws UnsupportedEncodingException {
    // Arrange
    byte[] in_buff = "AAAAAAAA".getBytes("UTF-8");
    byte[] out_buff = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertEquals(0, G726_32.encode(in_buff, 2, 3, 3, out_buff, 2, new G726State()));
  }

  @Test
  void testEncode37() throws UnsupportedEncodingException {
    // Arrange
    byte[] in_buff = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> G726_32.encode(in_buff, 2, 3, 1, new byte[]{}, 2, new G726State()));
  }

  @Test
  void testEncode38() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> G726_32.encode(new byte[]{}, 2, 3, 1, "AAAAAAAA".getBytes("UTF-8"), 2, null));
  }

  @Test
  void testEncode39() throws UnsupportedEncodingException {
    // Arrange
    byte[] in_buff = "AA￿AAAAA".getBytes("UTF-8");
    byte[] out_buff = "AAAAAAAA".getBytes("UTF-8");
    G726State g726State = new G726State();

    // Act and Assert
    assertEquals(1, G726_32.encode(in_buff, 2, 3, 2, out_buff, 2, g726State));
    assertEquals(1797, g726State.yu);
    assertEquals(37156, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(220, g726State.dms);
    assertEquals(223, g726State.dml);
    assertEquals(62, g726State.ap);
  }

  @Test
  void testDecode() {
    // Arrange
    G726_32 g726_32 = new G726_32();

    // Act and Assert
    assertEquals(254, g726_32.decode(1, 1));
    G726State g726State = g726_32.state;
    assertEquals(34817, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(0, g726State.dms);
    assertEquals(0, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
    assertEquals(545, g726State.yu);
  }

  @Test
  void testDecode2() {
    // Arrange
    G726_32 g726_32 = new G726_32();

    // Act and Assert
    assertEquals(254, g726_32.decode(0, 1));
    G726State g726State = g726_32.state;
    assertEquals(34816, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(0, g726State.dms);
    assertEquals(0, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
    assertEquals(544, g726State.yu);
  }

  @Test
  void testDecode3() {
    // Arrange
    G726_32 g726_32 = new G726_32();

    // Act and Assert
    assertEquals(255, g726_32.decode(AMR.M15_NO_DATA, 1));
    G726State g726State = g726_32.state;
    assertEquals(34816, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(0, g726State.dms);
    assertEquals(0, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
    assertEquals(544, g726State.yu);
  }

  @Test
  void testDecode4() {
    // Arrange
    G726_32 g726_32 = new G726_32();

    // Act and Assert
    assertEquals(253, g726_32.decode(2, 1));
    G726State g726State = g726_32.state;
    assertEquals(34840, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(0, g726State.dms);
    assertEquals(0, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
    assertEquals(568, g726State.yu);
  }

  @Test
  void testDecode5() {
    // Arrange
    G726_32 g726_32 = new G726_32();

    // Act and Assert
    assertEquals(125, g726_32.decode(13, 1));
    G726State g726State = g726_32.state;
    assertEquals(34840, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(0, g726State.dms);
    assertEquals(0, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
    assertEquals(568, g726State.yu);
  }

  @Test
  void testDecode6() {
    // Arrange
    G726_32 g726_32 = new G726_32();

    // Act and Assert
    assertEquals(-1, g726_32.decode(1, 0));
    G726State g726State = g726_32.state;
    assertEquals(34817, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(0, g726State.dms);
    assertEquals(0, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
    assertEquals(545, g726State.yu);
  }

  @Test
  void testDecode7() {
    // Arrange
    G726_32 g726_32 = new G726_32();

    // Act and Assert
    assertEquals(213, g726_32.decode(1, 2));
    G726State g726State = g726_32.state;
    assertEquals(34817, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(0, g726State.dms);
    assertEquals(0, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
    assertEquals(545, g726State.yu);
  }

  @Test
  void testDecode8() {
    // Arrange
    G726_32 g726_32 = new G726_32();

    // Act and Assert
    assertEquals(8, g726_32.decode(1, 3));
    G726State g726State = g726_32.state;
    assertEquals(34817, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(0, g726State.dms);
    assertEquals(0, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
    assertEquals(545, g726State.yu);
  }

  @Test
  void testDecode9() {
    // Arrange
    G726_32 g726_32 = new G726_32();

    // Act and Assert
    assertEquals(85, g726_32.decode(0, 2));
    G726State g726State = g726_32.state;
    assertEquals(34816, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(0, g726State.dms);
    assertEquals(0, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
    assertEquals(544, g726State.yu);
  }

  @Test
  void testDecode10() {
    // Arrange
    G726_32 g726_32 = new G726_32();

    // Act and Assert
    assertEquals(213, g726_32.decode(2, 2));
    G726State g726State = g726_32.state;
    assertEquals(34840, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(0, g726State.dms);
    assertEquals(0, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
    assertEquals(568, g726State.yu);
  }

  @Test
  void testDecode11() {
    // Arrange
    G726_32 g726_32 = new G726_32();

    // Act and Assert
    assertEquals(84, g726_32.decode(13, 2));
    G726State g726State = g726_32.state;
    assertEquals(34840, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(0, g726State.dms);
    assertEquals(0, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
    assertEquals(568, g726State.yu);
  }

  @Test
  void testDecode12() {
    // Arrange
    G726_32 g726_32 = new G726_32();

    // Act and Assert
    assertEquals(209, g726_32.decode(6, 2));
    G726State g726State = g726_32.state;
    assertEquals(35154, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(48, g726State.dms);
    assertEquals(48, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
    assertEquals(882, g726State.yu);
  }

  @Test
  void testDecode13() {
    // Arrange
    G726_32 g726_32 = new G726_32();

    // Act and Assert
    assertEquals(244, g726_32.decode(7, 1));
    G726State g726State = g726_32.state;
    assertEquals(35921, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(112, g726State.dms);
    assertEquals(112, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
    assertEquals(1649, g726State.yu);
  }

  @Test
  void testDecode14() {
    // Arrange
    G726State g726State = new G726State();

    // Act and Assert
    assertEquals(254, G726_32.decode(1, 1, g726State));
    assertEquals(545, g726State.yu);
    assertEquals(34817, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(0, g726State.dms);
    assertEquals(0, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
  }

  @Test
  void testDecode15() {
    // Arrange
    G726State g726State = new G726State();

    // Act and Assert
    assertEquals(213, G726_32.decode(1, 2, g726State));
    assertEquals(545, g726State.yu);
    assertEquals(34817, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(0, g726State.dms);
    assertEquals(0, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
  }

  @Test
  void testDecode16() {
    // Arrange
    G726State g726State = new G726State();

    // Act and Assert
    assertEquals(8, G726_32.decode(1, 3, g726State));
    assertEquals(545, g726State.yu);
    assertEquals(34817, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(0, g726State.dms);
    assertEquals(0, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
  }

  @Test
  void testDecode17() {
    // Arrange
    G726State g726State = new G726State();

    // Act and Assert
    assertEquals(254, G726_32.decode(0, 1, g726State));
    assertEquals(544, g726State.yu);
    assertEquals(34816, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(0, g726State.dms);
    assertEquals(0, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
  }

  @Test
  void testDecode18() {
    // Arrange
    G726State g726State = new G726State();

    // Act and Assert
    assertEquals(255, G726_32.decode(AMR.M15_NO_DATA, 1, g726State));
    assertEquals(544, g726State.yu);
    assertEquals(34816, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(0, g726State.dms);
    assertEquals(0, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
  }

  @Test
  void testDecode19() {
    // Arrange
    G726State g726State = new G726State();

    // Act and Assert
    assertEquals(125, G726_32.decode(13, 1, g726State));
    assertEquals(568, g726State.yu);
    assertEquals(34840, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(0, g726State.dms);
    assertEquals(0, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
  }

  @Test
  void testDecode20() {
    // Arrange
    G726State g726State = new G726State();

    // Act and Assert
    assertEquals(-1, G726_32.decode(1, 0, g726State));
    assertEquals(545, g726State.yu);
    assertEquals(34817, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(0, g726State.dms);
    assertEquals(0, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
  }

  @Test
  void testDecode21() {
    // Arrange
    G726State g726State = new G726State();

    // Act and Assert
    assertEquals(254, G726_32.decode(48, 1, g726State));
    assertEquals(544, g726State.yu);
    assertEquals(34816, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(0, g726State.dms);
    assertEquals(0, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
  }

  @Test
  void testDecode22() {
    // Arrange
    G726State g726State = new G726State();

    // Act and Assert
    assertEquals(85, G726_32.decode(0, 2, g726State));
    assertEquals(544, g726State.yu);
    assertEquals(34816, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(0, g726State.dms);
    assertEquals(0, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
  }

  @Test
  void testDecode23() {
    // Arrange
    G726State g726State = new G726State();

    // Act and Assert
    assertEquals(84, G726_32.decode(13, 2, g726State));
    assertEquals(568, g726State.yu);
    assertEquals(34840, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(0, g726State.dms);
    assertEquals(0, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
  }

  @Test
  void testDecode24() {
    // Arrange
    G726State g726State = new G726State();

    // Act and Assert
    assertEquals(213, G726_32.decode(2, 2, g726State));
    assertEquals(568, g726State.yu);
    assertEquals(34840, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(0, g726State.dms);
    assertEquals(0, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
  }

  @Test
  void testDecode25() {
    // Arrange
    G726State g726State = new G726State();

    // Act and Assert
    assertEquals(209, G726_32.decode(6, 2, g726State));
    assertEquals(882, g726State.yu);
    assertEquals(35154, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(48, g726State.dms);
    assertEquals(48, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
  }

  @Test
  void testDecode26() {
    // Arrange
    G726State g726State = new G726State();

    // Act and Assert
    assertEquals(244, G726_32.decode(7, 1, g726State));
    assertEquals(1649, g726State.yu);
    assertEquals(35921, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(112, g726State.dms);
    assertEquals(112, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
  }

  @Test
  void testDecode27() throws UnsupportedEncodingException {
    // Arrange
    G726_32 g726_32 = new G726_32();
    byte[] in_buff = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertEquals(6, g726_32.decode(in_buff, 2, 3, 1, "AAAAAAAA".getBytes("UTF-8"), 2));
    G726State g726State = g726_32.state;
    assertEquals(35208, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(42, g726State.dms);
    assertEquals(43, g726State.dml);
    assertEquals(163, g726State.ap);
    assertEquals(605, g726State.yu);
  }

  @Test
  void testDecode28() throws UnsupportedEncodingException {
    // Arrange
    G726_32 g726_32 = new G726_32();

    // Act and Assert
    assertEquals(6,
        g726_32.decode(new byte[]{'A', 'A', 0, 'A', 'A', 'A', 'A', 'A'}, 2, 3, 1, "AAAAAAAA".getBytes("UTF-8"), 2));
    G726State g726State = g726_32.state;
    assertEquals(35103, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(29, g726State.dms);
    assertEquals(29, g726State.dml);
    assertEquals(163, g726State.ap);
    assertEquals(603, g726State.yu);
  }

  @Test
  void testDecode29() throws UnsupportedEncodingException {
    // Arrange
    G726_32 g726_32 = new G726_32();
    byte[] in_buff = "AAXAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertEquals(6, g726_32.decode(in_buff, 2, 3, 1, "AAAAAAAA".getBytes("UTF-8"), 2));
    G726State g726State = g726_32.state;
    assertEquals(36789, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(140, g726State.dms);
    assertEquals(152, g726State.dml);
    assertEquals(163, g726State.ap);
    assertEquals(646, g726State.yu);
  }

  @Test
  void testDecode30() throws UnsupportedEncodingException {
    // Arrange
    G726_32 g726_32 = new G726_32();

    // Act and Assert
    assertEquals(6,
        g726_32.decode(new byte[]{'A', 'A', 'A', 0, 'A', 'A', 'A', 'A'}, 2, 3, 1, "AAAAAAAA".getBytes("UTF-8"), 2));
    G726State g726State = g726_32.state;
    assertEquals(35058, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(27, g726State.dms);
    assertEquals(27, g726State.dml);
    assertEquals(163, g726State.ap);
    assertEquals(596, g726State.yu);
  }

  @Test
  void testDecode31() throws UnsupportedEncodingException {
    // Arrange
    G726_32 g726_32 = new G726_32();

    // Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> g726_32.decode(new byte[]{}, 2, 3, 1, "AAAAAAAA".getBytes("UTF-8"), 2));
  }

  @Test
  void testDecode32() throws UnsupportedEncodingException {
    // Arrange
    G726_32 g726_32 = new G726_32();
    byte[] in_buff = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> g726_32.decode(in_buff, 2, 256, 1, "AAAAAAAA".getBytes("UTF-8"), 2));
  }

  @Test
  void testDecode33() throws UnsupportedEncodingException {
    // Arrange
    G726_32 g726_32 = new G726_32();
    byte[] in_buff = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertEquals(-1, g726_32.decode(in_buff, 2, 3, 0, "AAAAAAAA".getBytes("UTF-8"), 2));
  }

  @Test
  void testDecode34() throws UnsupportedEncodingException {
    // Arrange
    G726_32 g726_32 = new G726_32();
    byte[] in_buff = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertEquals(6, g726_32.decode(in_buff, 2, 3, 2, "AAAAAAAA".getBytes("UTF-8"), 2));
    G726State g726State = g726_32.state;
    assertEquals(35208, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(42, g726State.dms);
    assertEquals(43, g726State.dml);
    assertEquals(163, g726State.ap);
    assertEquals(605, g726State.yu);
  }

  @Test
  void testDecode35() throws UnsupportedEncodingException {
    // Arrange
    G726_32 g726_32 = new G726_32();
    byte[] in_buff = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> g726_32.decode(in_buff, 2, 3, 3, "AAAAAAAA".getBytes("UTF-8"), 2));
  }

  @Test
  void testDecode36() throws UnsupportedEncodingException {
    // Arrange
    G726_32 g726_32 = new G726_32();
    byte[] in_buff = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> g726_32.decode(in_buff, 2, 3, 1, "AAAAAAAA".getBytes("UTF-8"), 3));
  }

  @Test
  void testDecode37() throws UnsupportedEncodingException {
    // Arrange
    G726_32 g726_32 = new G726_32();

    // Act and Assert
    assertEquals(6,
        g726_32.decode(new byte[]{'A', 'A', 0, 'A', 'A', 'A', 'A', 'A'}, 2, 3, 2, "AAAAAAAA".getBytes("UTF-8"), 2));
    G726State g726State = g726_32.state;
    assertEquals(35103, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(29, g726State.dms);
    assertEquals(29, g726State.dml);
    assertEquals(163, g726State.ap);
    assertEquals(603, g726State.yu);
  }

  @Test
  void testDecode38() throws UnsupportedEncodingException {
    // Arrange
    byte[] in_buff = "AAAAAAAA".getBytes("UTF-8");
    byte[] out_buff = "AAAAAAAA".getBytes("UTF-8");
    G726State g726State = new G726State();

    // Act and Assert
    assertEquals(6, G726_32.decode(in_buff, 2, 3, 1, out_buff, 2, g726State));
    assertEquals(605, g726State.yu);
    assertEquals(35208, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(42, g726State.dms);
    assertEquals(43, g726State.dml);
    assertEquals(163, g726State.ap);
  }

  @Test
  void testDecode39() {
    // Arrange, Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> G726_32.decode(new byte[]{'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A'}, 1, 4, 1,
            new byte[]{'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A'}, 1, new G726State()));
  }

  @Test
  void testDecode40() {
    // Arrange, Act and Assert
    assertEquals(0, G726_32.decode(new byte[]{'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A'}, 1, 0, 2,
        new byte[]{'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A'}, 1, new G726State()));
  }

  @Test
  void testDecode41() {
    // Arrange
    G726State g726State = new G726State();

    // Act and Assert
    assertEquals(2, G726_32.decode(new byte[]{'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A'}, 1, 1, 2,
        new byte[]{'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A'}, 1, g726State));
    assertEquals(556, g726State.yu);
    assertEquals(34921, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(AMR.M15_NO_DATA, g726State.dms);
    assertEquals(AMR.M15_NO_DATA, g726State.dml);
    assertEquals(62, g726State.ap);
  }

  @Test
  void testDecode42() {
    // Arrange, Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> G726_32.decode(new byte[]{'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A'}, 1, 4, 2,
            new byte[]{'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A'}, 1, new G726State()));
  }

  @Test
  void testDecode43() {
    // Arrange, Act and Assert
    assertEquals(0, G726_32.decode(new byte[]{'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A'}, 1, 0, 3,
        new byte[]{'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A'}, 1, new G726State()));
  }

  @Test
  void testDecode44() {
    // Arrange
    G726State g726State = new G726State();

    // Act and Assert
    assertEquals(4, G726_32.decode(new byte[]{'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A'}, 1, 1, 3,
        new byte[]{'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A'}, 1, g726State));
    assertEquals(556, g726State.yu);
    assertEquals(34921, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(AMR.M15_NO_DATA, g726State.dms);
    assertEquals(AMR.M15_NO_DATA, g726State.dml);
    assertEquals(62, g726State.ap);
  }

  @Test
  void testDecode45() {
    // Arrange, Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> G726_32.decode(new byte[]{'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A'}, 1, 2, 3,
            new byte[]{'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A'}, 1, new G726State()));
  }

  @Test
  void testDecode46() throws UnsupportedEncodingException {
    // Arrange
    byte[] out_buff = "AAAAAAAA".getBytes("UTF-8");
    G726State g726State = new G726State();

    // Act and Assert
    assertEquals(6, G726_32.decode(new byte[]{'A', 'A', 0, 'A', 'A', 'A', 'A', 'A'}, 2, 3, 1, out_buff, 2, g726State));
    assertEquals(603, g726State.yu);
    assertEquals(35103, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(29, g726State.dms);
    assertEquals(29, g726State.dml);
    assertEquals(163, g726State.ap);
  }

  @Test
  void testDecode47() throws UnsupportedEncodingException {
    // Arrange
    byte[] in_buff = "AAXAAAAA".getBytes("UTF-8");
    byte[] out_buff = "AAAAAAAA".getBytes("UTF-8");
    G726State g726State = new G726State();

    // Act and Assert
    assertEquals(6, G726_32.decode(in_buff, 2, 3, 1, out_buff, 2, g726State));
    assertEquals(646, g726State.yu);
    assertEquals(36789, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(140, g726State.dms);
    assertEquals(152, g726State.dml);
    assertEquals(163, g726State.ap);
  }

  @Test
  void testDecode48() throws UnsupportedEncodingException {
    // Arrange
    byte[] out_buff = "AAAAAAAA".getBytes("UTF-8");
    G726State g726State = new G726State();

    // Act and Assert
    assertEquals(6, G726_32.decode(new byte[]{'A', 'A', 'A', 0, 'A', 'A', 'A', 'A'}, 2, 3, 1, out_buff, 2, g726State));
    assertEquals(596, g726State.yu);
    assertEquals(35058, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(27, g726State.dms);
    assertEquals(27, g726State.dml);
    assertEquals(163, g726State.ap);
  }

  @Test
  void testDecode49() throws UnsupportedEncodingException {
    // Arrange
    byte[] out_buff = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> G726_32.decode(new byte[]{}, 2, 3, 1, out_buff, 2, new G726State()));
  }

  @Test
  void testDecode50() throws UnsupportedEncodingException {
    // Arrange
    byte[] in_buff = "AAAAAAAA".getBytes("UTF-8");
    byte[] out_buff = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> G726_32.decode(in_buff, 2, 256, 1, out_buff, 2, new G726State()));
  }

  @Test
  void testDecode51() throws UnsupportedEncodingException {
    // Arrange
    byte[] in_buff = "AAAAAAAA".getBytes("UTF-8");
    byte[] out_buff = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertEquals(-1, G726_32.decode(in_buff, 2, 3, 0, out_buff, 2, new G726State()));
  }

  @Test
  void testDecode52() throws UnsupportedEncodingException {
    // Arrange
    byte[] in_buff = "AAAAAAAA".getBytes("UTF-8");
    byte[] out_buff = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> G726_32.decode(in_buff, 2, 3, 3, out_buff, 2, new G726State()));
  }

  @Test
  void testDecode53() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> G726_32.decode(new byte[]{}, 2, 3, 1, "AAAAAAAA".getBytes("UTF-8"), 2, null));
  }

  @Test
  void testDecode54() throws UnsupportedEncodingException {
    // Arrange
    byte[] out_buff = "AAAAAAAA".getBytes("UTF-8");
    G726State g726State = new G726State();

    // Act and Assert
    assertEquals(6, G726_32.decode(new byte[]{'A', 'A', 0, 'A', 'A', 'A', 'A', 'A'}, 2, 3, 2, out_buff, 2, g726State));
    assertEquals(603, g726State.yu);
    assertEquals(35103, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(29, g726State.dms);
    assertEquals(29, g726State.dml);
    assertEquals(163, g726State.ap);
  }

  @Test
  void testDecode55() {
    // Arrange
    G726State g726State = new G726State();

    // Act and Assert
    assertEquals(2, G726_32.decode(new byte[]{'A', 2, 'A', 'A', 'A', 'A', 'A', 'A'}, 1, 1, 2,
        new byte[]{'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A'}, 1, g726State));
    assertEquals(568, g726State.yu);
    assertEquals(34840, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(0, g726State.dms);
    assertEquals(0, g726State.dml);
    assertEquals(62, g726State.ap);
  }

  @Test
  void testDecode56() {
    // Arrange
    G726State g726State = new G726State();

    // Act and Assert
    assertEquals(2, G726_32.decode(new byte[]{'A', 'X', 'A', 'A', 'A', 'A', 'A', 'A'}, 1, 1, 2,
        new byte[]{'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A'}, 1, g726State));
    assertEquals(1672, g726State.yu);
    assertEquals(36122, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(Float.MAX_EXPONENT, g726State.dms);
    assertEquals(Float.MAX_EXPONENT, g726State.dml);
    assertEquals(62, g726State.ap);
  }

  @Test
  void testConstructor() {
    // Arrange and Act
    G726_32 actualG726_32 = new G726_32();

    // Assert
    AudioFormat.Encoding expectedEncoding = actualG726_32.encoding;
    assertSame(expectedEncoding, actualG726_32.getEncoding());
    G726State g726State = actualG726_32.state;
    assertEquals(34816, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(2, g726State.sr.length);
    assertEquals(2, g726State.pk.length);
    assertEquals(6, g726State.dq.length);
    assertEquals(0, g726State.dms);
    assertEquals(0, g726State.dml);
    assertEquals(6, g726State.b.length);
    assertEquals(0, g726State.ap);
    assertEquals(2, g726State.a.length);
    assertEquals(544, g726State.yu);
  }

  @Test
  void testGetEncoding() {
    // Arrange and Act
    AudioFormat.Encoding actualEncoding = (new G726_32()).getEncoding();

    // Assert
    assertSame(((G726Encoding) actualEncoding).G726_32, actualEncoding);
  }
}

