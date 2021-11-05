package org.zoolu.sound.codec;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.io.UnsupportedEncodingException;
import javax.sound.sampled.AudioFormat;
import org.junit.jupiter.api.Test;
import org.zoolu.sound.codec.g726.G726Encoding;

class G726_24DiffblueTest {
  @Test
  void testEncode() {
    // Arrange
    G726_24 g726_24 = new G726_24();

    // Act and Assert
    assertEquals(4, g726_24.encode(1, 1));
    G726State g726State = g726_24.state;
    assertEquals(35381, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(112, g726State.dms);
    assertEquals(112, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
    assertEquals(1109, g726State.yu);
  }

  @Test
  void testEncode2() {
    // Arrange
    G726_24 g726_24 = new G726_24();

    // Act and Assert
    assertEquals(4, g726_24.encode(0, 1));
    G726State g726State = g726_24.state;
    assertEquals(35381, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(112, g726State.dms);
    assertEquals(112, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
    assertEquals(1109, g726State.yu);
  }

  @Test
  void testEncode3() {
    // Arrange
    G726_24 g726_24 = new G726_24();

    // Act and Assert
    assertEquals(4, g726_24.encode(2, 1));
    G726State g726State = g726_24.state;
    assertEquals(35381, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(112, g726State.dms);
    assertEquals(112, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
    assertEquals(1109, g726State.yu);
  }

  @Test
  void testEncode4() {
    // Arrange
    G726_24 g726_24 = new G726_24();

    // Act and Assert
    assertEquals(4, g726_24.encode(3, 1));
    G726State g726State = g726_24.state;
    assertEquals(35381, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(112, g726State.dms);
    assertEquals(112, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
    assertEquals(1109, g726State.yu);
  }

  @Test
  void testEncode5() {
    // Arrange
    G726_24 g726_24 = new G726_24();

    // Act and Assert
    assertEquals(7, g726_24.encode(-1, 1));
    G726State g726State = g726_24.state;
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
    G726_24 g726_24 = new G726_24();

    // Act and Assert
    assertEquals(3, g726_24.encode(G711.BIAS, 1));
    G726State g726State = g726_24.state;
    assertEquals(35381, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(112, g726State.dms);
    assertEquals(112, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
    assertEquals(1109, g726State.yu);
  }

  @Test
  void testEncode7() {
    // Arrange, Act and Assert
    assertEquals(-1, (new G726_24()).encode(1, 0));
  }

  @Test
  void testEncode8() {
    // Arrange
    G726_24 g726_24 = new G726_24();

    // Act and Assert
    assertEquals(4, g726_24.encode(1, 2));
    G726State g726State = g726_24.state;
    assertEquals(35381, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(112, g726State.dms);
    assertEquals(112, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
    assertEquals(1109, g726State.yu);
  }

  @Test
  void testEncode9() {
    // Arrange
    G726_24 g726_24 = new G726_24();

    // Act and Assert
    assertEquals(7, g726_24.encode(1, 3));
    G726State g726State = g726_24.state;
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
    G726_24 g726_24 = new G726_24();

    // Act and Assert
    assertEquals(3, g726_24.encode(-1, 2));
    G726State g726State = g726_24.state;
    assertEquals(35381, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(112, g726State.dms);
    assertEquals(112, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
    assertEquals(1109, g726State.yu);
  }

  @Test
  void testEncode11() {
    // Arrange
    G726_24 g726_24 = new G726_24();

    // Act and Assert
    assertEquals(7, g726_24.encode(85, 2));
    G726State g726State = g726_24.state;
    assertEquals(34816, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(0, g726State.dms);
    assertEquals(0, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
    assertEquals(544, g726State.yu);
  }

  @Test
  void testEncode12() {
    // Arrange
    G726State g726State = new G726State();

    // Act and Assert
    assertEquals(4, G726_24.encode(1, 1, g726State));
    assertEquals(1109, g726State.yu);
    assertEquals(35381, g726State.yl);
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
    assertEquals(4, G726_24.encode(1, 2, g726State));
    assertEquals(1109, g726State.yu);
    assertEquals(35381, g726State.yl);
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
    assertEquals(7, G726_24.encode(1, 3, g726State));
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
    assertEquals(7, G726_24.encode(-1, 1, g726State));
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
    assertEquals(3, G726_24.encode(G711.BIAS, 1, g726State));
    assertEquals(1109, g726State.yu);
    assertEquals(35381, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(112, g726State.dms);
    assertEquals(112, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
  }

  @Test
  void testEncode17() {
    // Arrange, Act and Assert
    assertEquals(-1, G726_24.encode(1, 0, new G726State()));
  }

  @Test
  void testEncode18() {
    // Arrange
    G726State g726State = new G726State();

    // Act and Assert
    assertEquals(3, G726_24.encode(128, 1, g726State));
    assertEquals(1109, g726State.yu);
    assertEquals(35381, g726State.yl);
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
    assertEquals(3, G726_24.encode(-1, 2, g726State));
    assertEquals(1109, g726State.yu);
    assertEquals(35381, g726State.yl);
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
    assertEquals(7, G726_24.encode(85, 2, g726State));
    assertEquals(544, g726State.yu);
    assertEquals(34816, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(0, g726State.dms);
    assertEquals(0, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
  }

  @Test
  void testEncode21() throws UnsupportedEncodingException {
    // Arrange
    G726_24 g726_24 = new G726_24();
    byte[] in_buff = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertEquals(0, g726_24.encode(in_buff, 2, 3, 1, "AAAAAAAA".getBytes("UTF-8"), 2));
  }

  @Test
  void testEncode22() throws UnsupportedEncodingException {
    // Arrange
    G726_24 g726_24 = new G726_24();
    byte[] in_buff = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> g726_24.encode(in_buff, 2, 8, 1, "AAAAAAAA".getBytes("UTF-8"), 2));
  }

  @Test
  void testEncode23() throws UnsupportedEncodingException {
    // Arrange
    G726_24 g726_24 = new G726_24();
    byte[] in_buff = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertEquals(-1, g726_24.encode(in_buff, 2, 3, 0, "AAAAAAAA".getBytes("UTF-8"), 2));
  }

  @Test
  void testEncode24() throws UnsupportedEncodingException {
    // Arrange
    G726_24 g726_24 = new G726_24();
    byte[] in_buff = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertEquals(0, g726_24.encode(in_buff, 2, 3, 2, "AAAAAAAA".getBytes("UTF-8"), 2));
  }

  @Test
  void testEncode25() throws UnsupportedEncodingException {
    // Arrange
    G726_24 g726_24 = new G726_24();
    byte[] in_buff = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertEquals(0, g726_24.encode(in_buff, 2, 3, 3, "AAAAAAAA".getBytes("UTF-8"), 2));
  }

  @Test
  void testEncode26() throws UnsupportedEncodingException {
    // Arrange
    G726_24 g726_24 = new G726_24();
    byte[] in_buff = "AAAAAAAAAAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertEquals(3, g726_24.encode(in_buff, 2, 8, 1, "AAAAAAAA".getBytes("UTF-8"), 2));
    G726State g726State = g726_24.state;
    assertEquals(41990, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(800, g726State.dms);
    assertEquals(869, g726State.dml);
    assertEquals(204, g726State.ap);
    assertEquals(2003, g726State.yu);
  }

  @Test
  void testEncode27() throws UnsupportedEncodingException {
    // Arrange
    G726_24 g726_24 = new G726_24();
    byte[] in_buff = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> g726_24.encode(in_buff, 2, 8, 2, "AAAAAAAA".getBytes("UTF-8"), 2));
  }

  @Test
  void testEncode28() throws UnsupportedEncodingException {
    // Arrange
    byte[] in_buff = "AAAAAAAA".getBytes("UTF-8");
    byte[] out_buff = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertEquals(0, G726_24.encode(in_buff, 2, 3, 1, out_buff, 2, new G726State()));
  }

  @Test
  void testEncode29() throws UnsupportedEncodingException {
    // Arrange
    byte[] in_buff = "AAAAAAAA".getBytes("UTF-8");
    byte[] out_buff = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> G726_24.encode(in_buff, 2, 8, 1, out_buff, 2, new G726State()));
  }

  @Test
  void testEncode30() throws UnsupportedEncodingException {
    // Arrange
    byte[] in_buff = "AAAAAAAA".getBytes("UTF-8");
    byte[] out_buff = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertEquals(-1, G726_24.encode(in_buff, 2, 3, 0, out_buff, 2, new G726State()));
  }

  @Test
  void testEncode31() throws UnsupportedEncodingException {
    // Arrange
    byte[] in_buff = "AAAAAAAA".getBytes("UTF-8");
    byte[] out_buff = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertEquals(0, G726_24.encode(in_buff, 2, 3, 2, out_buff, 2, new G726State()));
  }

  @Test
  void testEncode32() throws UnsupportedEncodingException {
    // Arrange
    byte[] in_buff = "AAAAAAAA".getBytes("UTF-8");
    byte[] out_buff = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertEquals(0, G726_24.encode(in_buff, 2, 3, 3, out_buff, 2, new G726State()));
  }

  @Test
  void testEncode33() throws UnsupportedEncodingException {
    // Arrange
    byte[] in_buff = "AAAAAAAAAAAAAAAA".getBytes("UTF-8");
    byte[] out_buff = "AAAAAAAA".getBytes("UTF-8");
    G726State g726State = new G726State();

    // Act and Assert
    assertEquals(3, G726_24.encode(in_buff, 2, 8, 1, out_buff, 2, g726State));
    assertEquals(2003, g726State.yu);
    assertEquals(41990, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(800, g726State.dms);
    assertEquals(869, g726State.dml);
    assertEquals(204, g726State.ap);
  }

  @Test
  void testEncode34() throws UnsupportedEncodingException {
    // Arrange
    byte[] in_buff = "AAAAAAAA".getBytes("UTF-8");
    byte[] out_buff = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> G726_24.encode(in_buff, 2, 8, 2, out_buff, 2, new G726State()));
  }

  @Test
  void testEncode35() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> G726_24.encode(new byte[]{}, 2, 8, 1, "AAAAAAAA".getBytes("UTF-8"), 2, null));
  }

  @Test
  void testDecode() {
    // Arrange
    G726_24 g726_24 = new G726_24();

    // Act and Assert
    assertEquals(253, g726_24.decode(1, 1));
    G726State g726State = g726_24.state;
    assertEquals(34829, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(Short.SIZE, g726State.dms);
    assertEquals(Short.SIZE, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
    assertEquals(557, g726State.yu);
  }

  @Test
  void testDecode2() {
    // Arrange
    G726_24 g726_24 = new G726_24();

    // Act and Assert
    assertEquals(254, g726_24.decode(0, 1));
    G726State g726State = g726_24.state;
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
    G726_24 g726_24 = new G726_24();

    // Act and Assert
    assertEquals(255, g726_24.decode(7, 1));
    G726State g726State = g726_24.state;
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
    G726_24 g726_24 = new G726_24();

    // Act and Assert
    assertEquals(250, g726_24.decode(2, 1));
    G726State g726State = g726_24.state;
    assertEquals(34936, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(Integer.SIZE, g726State.dms);
    assertEquals(Integer.SIZE, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
    assertEquals(664, g726State.yu);
  }

  @Test
  void testDecode5() {
    // Arrange
    G726_24 g726_24 = new G726_24();

    // Act and Assert
    assertEquals(247, g726_24.decode(3, 1));
    G726State g726State = g726_24.state;
    assertEquals(35381, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(112, g726State.dms);
    assertEquals(112, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
    assertEquals(1109, g726State.yu);
  }

  @Test
  void testDecode6() {
    // Arrange
    G726_24 g726_24 = new G726_24();

    // Act and Assert
    assertEquals(125, g726_24.decode(6, 1));
    G726State g726State = g726_24.state;
    assertEquals(34829, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(Short.SIZE, g726State.dms);
    assertEquals(Short.SIZE, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
    assertEquals(557, g726State.yu);
  }

  @Test
  void testDecode7() {
    // Arrange
    G726_24 g726_24 = new G726_24();

    // Act and Assert
    assertEquals(-1, g726_24.decode(1, 0));
    G726State g726State = g726_24.state;
    assertEquals(34829, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(Short.SIZE, g726State.dms);
    assertEquals(Short.SIZE, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
    assertEquals(557, g726State.yu);
  }

  @Test
  void testDecode8() {
    // Arrange
    G726_24 g726_24 = new G726_24();

    // Act and Assert
    assertEquals(212, g726_24.decode(1, 2));
    G726State g726State = g726_24.state;
    assertEquals(34829, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(Short.SIZE, g726State.dms);
    assertEquals(Short.SIZE, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
    assertEquals(557, g726State.yu);
  }

  @Test
  void testDecode9() {
    // Arrange
    G726_24 g726_24 = new G726_24();

    // Act and Assert
    assertEquals(Short.SIZE, g726_24.decode(1, 3));
    G726State g726State = g726_24.state;
    assertEquals(34829, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(Short.SIZE, g726State.dms);
    assertEquals(Short.SIZE, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
    assertEquals(557, g726State.yu);
  }

  @Test
  void testDecode10() {
    // Arrange
    G726_24 g726_24 = new G726_24();

    // Act and Assert
    assertEquals(212, g726_24.decode(0, 2));
    G726State g726State = g726_24.state;
    assertEquals(34816, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(0, g726State.dms);
    assertEquals(0, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
    assertEquals(544, g726State.yu);
  }

  @Test
  void testDecode11() {
    // Arrange
    G726_24 g726_24 = new G726_24();

    // Act and Assert
    assertEquals(84, g726_24.decode(6, 2));
    G726State g726State = g726_24.state;
    assertEquals(34829, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(Short.SIZE, g726State.dms);
    assertEquals(Short.SIZE, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
    assertEquals(557, g726State.yu);
  }

  @Test
  void testDecode12() {
    // Arrange
    G726State g726State = new G726State();

    // Act and Assert
    assertEquals(253, G726_24.decode(1, 1, g726State));
    assertEquals(557, g726State.yu);
    assertEquals(34829, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(Short.SIZE, g726State.dms);
    assertEquals(Short.SIZE, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
  }

  @Test
  void testDecode13() {
    // Arrange
    G726State g726State = new G726State();

    // Act and Assert
    assertEquals(212, G726_24.decode(1, 2, g726State));
    assertEquals(557, g726State.yu);
    assertEquals(34829, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(Short.SIZE, g726State.dms);
    assertEquals(Short.SIZE, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
  }

  @Test
  void testDecode14() {
    // Arrange
    G726State g726State = new G726State();

    // Act and Assert
    assertEquals(Short.SIZE, G726_24.decode(1, 3, g726State));
    assertEquals(557, g726State.yu);
    assertEquals(34829, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(Short.SIZE, g726State.dms);
    assertEquals(Short.SIZE, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
  }

  @Test
  void testDecode15() {
    // Arrange
    G726State g726State = new G726State();

    // Act and Assert
    assertEquals(254, G726_24.decode(0, 1, g726State));
    assertEquals(544, g726State.yu);
    assertEquals(34816, g726State.yl);
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
    assertEquals(255, G726_24.decode(7, 1, g726State));
    assertEquals(544, g726State.yu);
    assertEquals(34816, g726State.yl);
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
    assertEquals(125, G726_24.decode(6, 1, g726State));
    assertEquals(557, g726State.yu);
    assertEquals(34829, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(Short.SIZE, g726State.dms);
    assertEquals(Short.SIZE, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
  }

  @Test
  void testDecode18() {
    // Arrange
    G726State g726State = new G726State();

    // Act and Assert
    assertEquals(-1, G726_24.decode(1, 0, g726State));
    assertEquals(557, g726State.yu);
    assertEquals(34829, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(Short.SIZE, g726State.dms);
    assertEquals(Short.SIZE, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
  }

  @Test
  void testDecode19() {
    // Arrange
    G726State g726State = new G726State();

    // Act and Assert
    assertEquals(255, G726_24.decode(63, 1, g726State));
    assertEquals(544, g726State.yu);
    assertEquals(34816, g726State.yl);
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
    assertEquals(212, G726_24.decode(0, 2, g726State));
    assertEquals(544, g726State.yu);
    assertEquals(34816, g726State.yl);
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
    assertEquals(84, G726_24.decode(6, 2, g726State));
    assertEquals(557, g726State.yu);
    assertEquals(34829, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(Short.SIZE, g726State.dms);
    assertEquals(Short.SIZE, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
  }

  @Test
  void testDecode22() {
    // Arrange
    G726State g726State = new G726State();

    // Act and Assert
    assertEquals(119, G726_24.decode(4, 1, g726State));
    assertEquals(1109, g726State.yu);
    assertEquals(35381, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(112, g726State.dms);
    assertEquals(112, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
  }

  @Test
  void testDecode23() throws UnsupportedEncodingException {
    // Arrange
    G726_24 g726_24 = new G726_24();
    byte[] in_buff = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> g726_24.decode(in_buff, 2, 3, 1, "AAAAAAAA".getBytes("UTF-8"), 2));
  }

  @Test
  void testDecode24() throws UnsupportedEncodingException {
    // Arrange
    G726_24 g726_24 = new G726_24();
    byte[] in_buff = "AA￿AAAAA".getBytes("UTF-8");

    // Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> g726_24.decode(in_buff, 2, 3, 1, "AAAAAAAA".getBytes("UTF-8"), 2));
  }

  @Test
  void testDecode25() throws UnsupportedEncodingException {
    // Arrange
    G726_24 g726_24 = new G726_24();

    // Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> g726_24.decode(new byte[]{}, 2, 3, 1, "AAAAAAAA".getBytes("UTF-8"), 2));
  }

  @Test
  void testDecode26() throws UnsupportedEncodingException {
    // Arrange
    G726_24 g726_24 = new G726_24();
    byte[] in_buff = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertEquals(0, g726_24.decode(in_buff, 2, 0, 1, "AAAAAAAA".getBytes("UTF-8"), 2));
  }

  @Test
  void testDecode27() throws UnsupportedEncodingException {
    // Arrange
    G726_24 g726_24 = new G726_24();
    byte[] in_buff = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertEquals(-1, g726_24.decode(in_buff, 2, 3, 0, "AAAAAAAA".getBytes("UTF-8"), 2));
  }

  @Test
  void testDecode28() throws UnsupportedEncodingException {
    // Arrange
    G726_24 g726_24 = new G726_24();
    byte[] in_buff = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> g726_24.decode(in_buff, 2, 3, 2, "AAAAAAAA".getBytes("UTF-8"), 2));
  }

  @Test
  void testDecode29() throws UnsupportedEncodingException {
    // Arrange
    G726_24 g726_24 = new G726_24();
    byte[] in_buff = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> g726_24.decode(in_buff, 2, 3, 3, "AAAAAAAA".getBytes("UTF-8"), 2));
  }

  @Test
  void testDecode30() throws UnsupportedEncodingException {
    // Arrange
    G726_24 g726_24 = new G726_24();
    byte[] in_buff = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertEquals(8, g726_24.decode(in_buff, 2, 3, 1, "AAAAAAAAAAAAAAAA".getBytes("UTF-8"), 2));
    G726State g726State = g726_24.state;
    assertEquals(36348, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(196, g726State.dms);
    assertEquals(213, g726State.dml);
    assertEquals(204, g726State.ap);
    assertEquals(661, g726State.yu);
  }

  @Test
  void testDecode31() throws UnsupportedEncodingException {
    // Arrange
    G726_24 g726_24 = new G726_24();
    byte[] in_buff = "AA￿AAAAA".getBytes("UTF-8");

    // Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> g726_24.decode(in_buff, 2, 3, 2, "AAAAAAAA".getBytes("UTF-8"), 2));
  }

  @Test
  void testDecode32() throws UnsupportedEncodingException {
    // Arrange
    G726_24 g726_24 = new G726_24();

    // Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> g726_24.decode(new byte[]{}, 2, 3, 3, "AAAAAAAA".getBytes("UTF-8"), 2));
  }

  @Test
  void testDecode33() throws UnsupportedEncodingException {
    // Arrange
    G726_24 g726_24 = new G726_24();
    byte[] in_buff = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertEquals(0, g726_24.decode(in_buff, 2, 0, 3, "AAAAAAAA".getBytes("UTF-8"), 2));
  }

  @Test
  void testDecode34() throws UnsupportedEncodingException {
    // Arrange
    byte[] in_buff = "AAAAAAAA".getBytes("UTF-8");
    byte[] out_buff = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> G726_24.decode(in_buff, 2, 3, 1, out_buff, 2, new G726State()));
  }

  @Test
  void testDecode35() {
    // Arrange, Act and Assert
    assertEquals(0, G726_24.decode(new byte[]{'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A'}, 1, 1, 1,
        new byte[]{'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A'}, 1, new G726State()));
  }

  @Test
  void testDecode36() {
    // Arrange, Act and Assert
    assertEquals(0, G726_24.decode(new byte[]{'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A'}, 1, 1, 2,
        new byte[]{'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A'}, 1, new G726State()));
  }

  @Test
  void testDecode37() {
    // Arrange, Act and Assert
    assertEquals(0, G726_24.decode(new byte[]{'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A'}, 1, 1, 3,
        new byte[]{'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A'}, 1, new G726State()));
  }

  @Test
  void testDecode38() throws UnsupportedEncodingException {
    // Arrange
    byte[] in_buff = "AA￿AAAAA".getBytes("UTF-8");
    byte[] out_buff = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> G726_24.decode(in_buff, 2, 3, 1, out_buff, 2, new G726State()));
  }

  @Test
  void testDecode39() throws UnsupportedEncodingException {
    // Arrange
    byte[] out_buff = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> G726_24.decode(new byte[]{}, 2, 3, 1, out_buff, 2, new G726State()));
  }

  @Test
  void testDecode40() throws UnsupportedEncodingException {
    // Arrange
    byte[] in_buff = "AAAAAAAA".getBytes("UTF-8");
    byte[] out_buff = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertEquals(-1, G726_24.decode(in_buff, 2, 3, 0, out_buff, 2, new G726State()));
  }

  @Test
  void testDecode41() throws UnsupportedEncodingException {
    // Arrange
    byte[] in_buff = "AAAAAAAA".getBytes("UTF-8");
    byte[] out_buff = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> G726_24.decode(in_buff, 2, 3, 2, out_buff, 2, new G726State()));
  }

  @Test
  void testDecode42() throws UnsupportedEncodingException {
    // Arrange
    byte[] in_buff = "AAAAAAAA".getBytes("UTF-8");
    byte[] out_buff = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> G726_24.decode(in_buff, 2, 3, 3, out_buff, 2, new G726State()));
  }

  @Test
  void testDecode43() throws UnsupportedEncodingException {
    // Arrange
    byte[] in_buff = "AAAAAAAA".getBytes("UTF-8");
    byte[] out_buff = "AAAAAAAAAAAAAAAA".getBytes("UTF-8");
    G726State g726State = new G726State();

    // Act and Assert
    assertEquals(8, G726_24.decode(in_buff, 2, 3, 1, out_buff, 2, g726State));
    assertEquals(661, g726State.yu);
    assertEquals(36348, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(196, g726State.dms);
    assertEquals(213, g726State.dml);
    assertEquals(204, g726State.ap);
  }

  @Test
  void testDecode44() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> G726_24.decode(new byte[]{}, 2, 3, 1, "AAAAAAAA".getBytes("UTF-8"), 2, null));
  }

  @Test
  void testDecode45() throws UnsupportedEncodingException {
    // Arrange
    byte[] in_buff = "AA￿AAAAA".getBytes("UTF-8");
    byte[] out_buff = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> G726_24.decode(in_buff, 2, 3, 2, out_buff, 2, new G726State()));
  }

  @Test
  void testDecode46() throws UnsupportedEncodingException {
    // Arrange
    byte[] out_buff = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> G726_24.decode(new byte[]{}, 2, 3, 3, out_buff, 2, new G726State()));
  }

  @Test
  void testConstructor() {
    // Arrange and Act
    G726_24 actualG726_24 = new G726_24();

    // Assert
    AudioFormat.Encoding expectedEncoding = actualG726_24.encoding;
    assertSame(expectedEncoding, actualG726_24.getEncoding());
    G726State g726State = actualG726_24.state;
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
    AudioFormat.Encoding actualEncoding = (new G726_24()).getEncoding();

    // Assert
    assertSame(((G726Encoding) actualEncoding).G726_24, actualEncoding);
  }
}

