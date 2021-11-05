package org.zoolu.sound.codec;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.io.UnsupportedEncodingException;
import javax.sound.sampled.AudioFormat;
import org.junit.jupiter.api.Test;
import org.zoolu.sound.codec.g726.G726Encoding;

class G726_40DiffblueTest {
  @Test
  void testEncode() {
    // Arrange
    G726_40 g726_40 = new G726_40();

    // Act and Assert
    assertEquals(Short.SIZE, g726_40.encode(1, 1));
    G726State g726State = g726_40.state;
    assertEquals(35495, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(96, g726State.dms);
    assertEquals(96, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
    assertEquals(1223, g726State.yu);
  }

  @Test
  void testEncode2() {
    // Arrange
    G726_40 g726_40 = new G726_40();

    // Act and Assert
    assertEquals(Short.SIZE, g726_40.encode(0, 1));
    G726State g726State = g726_40.state;
    assertEquals(35495, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(96, g726State.dms);
    assertEquals(96, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
    assertEquals(1223, g726State.yu);
  }

  @Test
  void testEncode3() {
    // Arrange
    G726_40 g726_40 = new G726_40();

    // Act and Assert
    assertEquals(Short.SIZE, g726_40.encode(2, 1));
    G726State g726State = g726_40.state;
    assertEquals(35495, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(96, g726State.dms);
    assertEquals(96, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
    assertEquals(1223, g726State.yu);
  }

  @Test
  void testEncode4() {
    // Arrange
    G726_40 g726_40 = new G726_40();

    // Act and Assert
    assertEquals(Short.SIZE, g726_40.encode(3, 1));
    G726State g726State = g726_40.state;
    assertEquals(35495, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(96, g726State.dms);
    assertEquals(96, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
    assertEquals(1223, g726State.yu);
  }

  @Test
  void testEncode5() {
    // Arrange
    G726_40 g726_40 = new G726_40();

    // Act and Assert
    assertEquals(31, g726_40.encode(-1, 1));
    G726State g726State = g726_40.state;
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
    G726_40 g726_40 = new G726_40();

    // Act and Assert
    assertEquals(AMR.M15_NO_DATA, g726_40.encode(G711.BIAS, 1));
    G726State g726State = g726_40.state;
    assertEquals(35495, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(96, g726State.dms);
    assertEquals(96, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
    assertEquals(1223, g726State.yu);
  }

  @Test
  void testEncode7() {
    // Arrange, Act and Assert
    assertEquals(-1, (new G726_40()).encode(1, 0));
  }

  @Test
  void testEncode8() {
    // Arrange
    G726_40 g726_40 = new G726_40();

    // Act and Assert
    assertEquals(Short.SIZE, g726_40.encode(1, 2));
    G726State g726State = g726_40.state;
    assertEquals(35495, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(96, g726State.dms);
    assertEquals(96, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
    assertEquals(1223, g726State.yu);
  }

  @Test
  void testEncode9() {
    // Arrange
    G726_40 g726_40 = new G726_40();

    // Act and Assert
    assertEquals(31, g726_40.encode(1, 3));
    G726State g726State = g726_40.state;
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
    G726_40 g726_40 = new G726_40();

    // Act and Assert
    assertEquals(AMR.M15_NO_DATA, g726_40.encode(-1, 2));
    G726State g726State = g726_40.state;
    assertEquals(35495, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(96, g726State.dms);
    assertEquals(96, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
    assertEquals(1223, g726State.yu);
  }

  @Test
  void testEncode11() {
    // Arrange
    G726_40 g726_40 = new G726_40();

    // Act and Assert
    assertEquals(29, g726_40.encode(85, 2));
    G726State g726State = g726_40.state;
    assertEquals(34823, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(0, g726State.dms);
    assertEquals(0, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
    assertEquals(551, g726State.yu);
  }

  @Test
  void testEncode12() {
    // Arrange
    G726State g726State = new G726State();

    // Act and Assert
    assertEquals(Short.SIZE, G726_40.encode(1, 1, g726State));
    assertEquals(1223, g726State.yu);
    assertEquals(35495, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(96, g726State.dms);
    assertEquals(96, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
  }

  @Test
  void testEncode13() {
    // Arrange
    G726State g726State = new G726State();

    // Act and Assert
    assertEquals(Short.SIZE, G726_40.encode(1, 2, g726State));
    assertEquals(1223, g726State.yu);
    assertEquals(35495, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(96, g726State.dms);
    assertEquals(96, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
  }

  @Test
  void testEncode14() {
    // Arrange
    G726State g726State = new G726State();

    // Act and Assert
    assertEquals(31, G726_40.encode(1, 3, g726State));
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
    assertEquals(31, G726_40.encode(-1, 1, g726State));
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
    assertEquals(AMR.M15_NO_DATA, G726_40.encode(G711.BIAS, 1, g726State));
    assertEquals(1223, g726State.yu);
    assertEquals(35495, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(96, g726State.dms);
    assertEquals(96, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
  }

  @Test
  void testEncode17() {
    // Arrange, Act and Assert
    assertEquals(-1, G726_40.encode(1, 0, new G726State()));
  }

  @Test
  void testEncode18() {
    // Arrange
    G726State g726State = new G726State();

    // Act and Assert
    assertEquals(AMR.M15_NO_DATA, G726_40.encode(128, 1, g726State));
    assertEquals(1223, g726State.yu);
    assertEquals(35495, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(96, g726State.dms);
    assertEquals(96, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
  }

  @Test
  void testEncode19() {
    // Arrange
    G726State g726State = new G726State();

    // Act and Assert
    assertEquals(AMR.M15_NO_DATA, G726_40.encode(-1, 2, g726State));
    assertEquals(1223, g726State.yu);
    assertEquals(35495, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(96, g726State.dms);
    assertEquals(96, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
  }

  @Test
  void testEncode20() {
    // Arrange
    G726State g726State = new G726State();

    // Act and Assert
    assertEquals(29, G726_40.encode(85, 2, g726State));
    assertEquals(551, g726State.yu);
    assertEquals(34823, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(0, g726State.dms);
    assertEquals(0, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
  }

  @Test
  void testEncode21() throws UnsupportedEncodingException {
    // Arrange
    G726_40 g726_40 = new G726_40();
    byte[] in_buff = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertEquals(0, g726_40.encode(in_buff, 2, 3, 1, "AAAAAAAA".getBytes("UTF-8"), 2));
  }

  @Test
  void testEncode22() throws UnsupportedEncodingException {
    // Arrange
    G726_40 g726_40 = new G726_40();
    byte[] in_buff = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> g726_40.encode(in_buff, 2, 8, 1, "AAAAAAAA".getBytes("UTF-8"), 2));
  }

  @Test
  void testEncode23() throws UnsupportedEncodingException {
    // Arrange
    G726_40 g726_40 = new G726_40();
    byte[] in_buff = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertEquals(-1, g726_40.encode(in_buff, 2, 3, 0, "AAAAAAAA".getBytes("UTF-8"), 2));
  }

  @Test
  void testEncode24() throws UnsupportedEncodingException {
    // Arrange
    G726_40 g726_40 = new G726_40();
    byte[] in_buff = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertEquals(0, g726_40.encode(in_buff, 2, 3, 2, "AAAAAAAA".getBytes("UTF-8"), 2));
  }

  @Test
  void testEncode25() throws UnsupportedEncodingException {
    // Arrange
    G726_40 g726_40 = new G726_40();
    byte[] in_buff = "AAAAAAAAAAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertEquals(5, g726_40.encode(in_buff, 2, 8, 1, "AAAAAAAA".getBytes("UTF-8"), 2));
    G726State g726State = g726_40.state;
    assertEquals(43443, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(686, g726State.dms);
    assertEquals(744, g726State.dml);
    assertEquals(204, g726State.ap);
    assertEquals(2298, g726State.yu);
  }

  @Test
  void testEncode26() throws UnsupportedEncodingException {
    // Arrange
    G726_40 g726_40 = new G726_40();
    byte[] in_buff = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> g726_40.encode(in_buff, 2, 8, 2, "AAAAAAAA".getBytes("UTF-8"), 2));
  }

  @Test
  void testEncode27() throws UnsupportedEncodingException {
    // Arrange
    G726_40 g726_40 = new G726_40();
    byte[] in_buff = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertEquals(0, g726_40.encode(in_buff, 2, 8, 3, "AAAAAAAA".getBytes("UTF-8"), 2));
  }

  @Test
  void testEncode28() throws UnsupportedEncodingException {
    // Arrange
    byte[] in_buff = "AAAAAAAA".getBytes("UTF-8");
    byte[] out_buff = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertEquals(0, G726_40.encode(in_buff, 2, 3, 1, out_buff, 2, new G726State()));
  }

  @Test
  void testEncode29() throws UnsupportedEncodingException {
    // Arrange
    byte[] in_buff = "AAAAAAAA".getBytes("UTF-8");
    byte[] out_buff = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> G726_40.encode(in_buff, 2, 8, 1, out_buff, 2, new G726State()));
  }

  @Test
  void testEncode30() throws UnsupportedEncodingException {
    // Arrange
    byte[] in_buff = "AAAAAAAA".getBytes("UTF-8");
    byte[] out_buff = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertEquals(-1, G726_40.encode(in_buff, 2, 3, 0, out_buff, 2, new G726State()));
  }

  @Test
  void testEncode31() throws UnsupportedEncodingException {
    // Arrange
    byte[] in_buff = "AAAAAAAA".getBytes("UTF-8");
    byte[] out_buff = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertEquals(0, G726_40.encode(in_buff, 2, 3, 2, out_buff, 2, new G726State()));
  }

  @Test
  void testEncode32() throws UnsupportedEncodingException {
    // Arrange
    byte[] in_buff = "AAAAAAAAAAAAAAAA".getBytes("UTF-8");
    byte[] out_buff = "AAAAAAAA".getBytes("UTF-8");
    G726State g726State = new G726State();

    // Act and Assert
    assertEquals(5, G726_40.encode(in_buff, 2, 8, 1, out_buff, 2, g726State));
    assertEquals(2298, g726State.yu);
    assertEquals(43443, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(686, g726State.dms);
    assertEquals(744, g726State.dml);
    assertEquals(204, g726State.ap);
  }

  @Test
  void testEncode33() throws UnsupportedEncodingException {
    // Arrange
    byte[] in_buff = "AAAAAAAA".getBytes("UTF-8");
    byte[] out_buff = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> G726_40.encode(in_buff, 2, 8, 2, out_buff, 2, new G726State()));
  }

  @Test
  void testEncode34() throws UnsupportedEncodingException {
    // Arrange
    byte[] in_buff = "AAAAAAAA".getBytes("UTF-8");
    byte[] out_buff = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertEquals(0, G726_40.encode(in_buff, 2, 8, 3, out_buff, 2, new G726State()));
  }

  @Test
  void testEncode35() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> G726_40.encode(new byte[]{}, 2, 8, 1, "AAAAAAAA".getBytes("UTF-8"), 2, null));
  }

  @Test
  void testDecode() {
    // Arrange
    G726_40 g726_40 = new G726_40();

    // Act and Assert
    assertEquals(255, g726_40.decode(1, 1));
    G726State g726State = g726_40.state;
    assertEquals(34816, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(0, g726State.dms);
    assertEquals(0, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
    assertEquals(544, g726State.yu);
  }

  @Test
  void testDecode2() {
    // Arrange
    G726_40 g726_40 = new G726_40();

    // Act and Assert
    assertEquals(254, g726_40.decode(0, 1));
    G726State g726State = g726_40.state;
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
    G726_40 g726_40 = new G726_40();

    // Act and Assert
    assertEquals(255, g726_40.decode(31, 1));
    G726State g726State = g726_40.state;
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
    G726_40 g726_40 = new G726_40();

    // Act and Assert
    assertEquals(254, g726_40.decode(2, 1));
    G726State g726State = g726_40.state;
    assertEquals(34823, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(0, g726State.dms);
    assertEquals(0, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
    assertEquals(551, g726State.yu);
  }

  @Test
  void testDecode5() {
    // Arrange
    G726_40 g726_40 = new G726_40();

    // Act and Assert
    assertEquals(235, g726_40.decode(AMR.M15_NO_DATA, 1));
    G726State g726State = g726_40.state;
    assertEquals(35495, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(96, g726State.dms);
    assertEquals(96, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
    assertEquals(1223, g726State.yu);
  }

  @Test
  void testDecode6() {
    // Arrange
    G726_40 g726_40 = new G726_40();

    // Act and Assert
    assertEquals(-1, g726_40.decode(1, 0));
    G726State g726State = g726_40.state;
    assertEquals(34816, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(0, g726State.dms);
    assertEquals(0, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
    assertEquals(544, g726State.yu);
  }

  @Test
  void testDecode7() {
    // Arrange
    G726_40 g726_40 = new G726_40();

    // Act and Assert
    assertEquals(85, g726_40.decode(1, 2));
    G726State g726State = g726_40.state;
    assertEquals(34816, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(0, g726State.dms);
    assertEquals(0, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
    assertEquals(544, g726State.yu);
  }

  @Test
  void testDecode8() {
    // Arrange
    G726_40 g726_40 = new G726_40();

    // Act and Assert
    assertEquals(4, g726_40.decode(1, 3));
    G726State g726State = g726_40.state;
    assertEquals(34816, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(0, g726State.dms);
    assertEquals(0, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
    assertEquals(544, g726State.yu);
  }

  @Test
  void testDecode9() {
    // Arrange
    G726_40 g726_40 = new G726_40();

    // Act and Assert
    assertEquals(213, g726_40.decode(2, 2));
    G726State g726State = g726_40.state;
    assertEquals(34823, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(0, g726State.dms);
    assertEquals(0, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
    assertEquals(551, g726State.yu);
  }

  @Test
  void testDecode10() {
    // Arrange
    G726_40 g726_40 = new G726_40();

    // Act and Assert
    assertEquals(212, g726_40.decode(3, 2));
    G726State g726State = g726_40.state;
    assertEquals(34838, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(0, g726State.dms);
    assertEquals(0, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
    assertEquals(566, g726State.yu);
  }

  @Test
  void testDecode11() {
    // Arrange
    G726_40 g726_40 = new G726_40();

    // Act and Assert
    assertEquals(107, g726_40.decode(48, 1));
    G726State g726State = g726_40.state;
    assertEquals(35495, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(96, g726State.dms);
    assertEquals(96, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
    assertEquals(1223, g726State.yu);
  }

  @Test
  void testDecode12() {
    // Arrange
    G726_40 g726_40 = new G726_40();

    // Act and Assert
    assertEquals(94, g726_40.decode(48, 2));
    G726State g726State = g726_40.state;
    assertEquals(35495, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(96, g726State.dms);
    assertEquals(96, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
    assertEquals(1223, g726State.yu);
  }

  @Test
  void testDecode13() {
    // Arrange
    G726State g726State = new G726State();

    // Act and Assert
    assertEquals(255, G726_40.decode(1, 1, g726State));
    assertEquals(544, g726State.yu);
    assertEquals(34816, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(0, g726State.dms);
    assertEquals(0, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
  }

  @Test
  void testDecode14() {
    // Arrange
    G726State g726State = new G726State();

    // Act and Assert
    assertEquals(85, G726_40.decode(1, 2, g726State));
    assertEquals(544, g726State.yu);
    assertEquals(34816, g726State.yl);
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
    assertEquals(4, G726_40.decode(1, 3, g726State));
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
    assertEquals(254, G726_40.decode(0, 1, g726State));
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
    assertEquals(255, G726_40.decode(31, 1, g726State));
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
    assertEquals(254, G726_40.decode(2, 1, g726State));
    assertEquals(551, g726State.yu);
    assertEquals(34823, g726State.yl);
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
    assertEquals(235, G726_40.decode(AMR.M15_NO_DATA, 1, g726State));
    assertEquals(1223, g726State.yu);
    assertEquals(35495, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(96, g726State.dms);
    assertEquals(96, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
  }

  @Test
  void testDecode20() {
    // Arrange
    G726State g726State = new G726State();

    // Act and Assert
    assertEquals(-1, G726_40.decode(1, 0, g726State));
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
    assertEquals(255, G726_40.decode(63, 1, g726State));
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
    assertEquals(213, G726_40.decode(2, 2, g726State));
    assertEquals(551, g726State.yu);
    assertEquals(34823, g726State.yl);
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
    assertEquals(107, G726_40.decode(48, 1, g726State));
    assertEquals(1223, g726State.yu);
    assertEquals(35495, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(96, g726State.dms);
    assertEquals(96, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
  }

  @Test
  void testDecode24() {
    // Arrange
    G726State g726State = new G726State();

    // Act and Assert
    assertEquals(94, G726_40.decode(48, 2, g726State));
    assertEquals(1223, g726State.yu);
    assertEquals(35495, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(96, g726State.dms);
    assertEquals(96, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
  }

  @Test
  void testDecode25() {
    // Arrange
    G726State g726State = new G726State();

    // Act and Assert
    assertEquals(212, G726_40.decode(3, 2, g726State));
    assertEquals(566, g726State.yu);
    assertEquals(34838, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(0, g726State.dms);
    assertEquals(0, g726State.dml);
    assertEquals(Integer.SIZE, g726State.ap);
  }

  @Test
  void testDecode26() throws UnsupportedEncodingException {
    // Arrange
    G726_40 g726_40 = new G726_40();
    byte[] in_buff = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertEquals(0, g726_40.decode(in_buff, 2, 3, 1, "AAAAAAAA".getBytes("UTF-8"), 2));
  }

  @Test
  void testDecode27() throws UnsupportedEncodingException {
    // Arrange
    G726_40 g726_40 = new G726_40();
    byte[] in_buff = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> g726_40.decode(in_buff, 2, 5, 1, "AAAAAAAA".getBytes("UTF-8"), 2));
  }

  @Test
  void testDecode28() throws UnsupportedEncodingException {
    // Arrange
    G726_40 g726_40 = new G726_40();
    byte[] in_buff = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertEquals(-1, g726_40.decode(in_buff, 2, 3, 0, "AAAAAAAA".getBytes("UTF-8"), 2));
  }

  @Test
  void testDecode29() throws UnsupportedEncodingException {
    // Arrange
    G726_40 g726_40 = new G726_40();
    byte[] in_buff = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertEquals(0, g726_40.decode(in_buff, 2, 3, 2, "AAAAAAAA".getBytes("UTF-8"), 2));
  }

  @Test
  void testDecode30() throws UnsupportedEncodingException {
    // Arrange
    G726_40 g726_40 = new G726_40();

    // Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> g726_40
        .decode(new byte[]{'A', 'A', 0, 'A', 'A', 'A', 'A', 'A'}, 2, 5, 1, "AAAAAAAA".getBytes("UTF-8"), 2));
  }

  @Test
  void testDecode31() throws UnsupportedEncodingException {
    // Arrange
    G726_40 g726_40 = new G726_40();
    byte[] in_buff = "AA￿AAAAA".getBytes("UTF-8");

    // Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> g726_40.decode(in_buff, 2, 5, 1, "AAAAAAAA".getBytes("UTF-8"), 2));
  }

  @Test
  void testDecode32() throws UnsupportedEncodingException {
    // Arrange
    G726_40 g726_40 = new G726_40();

    // Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> g726_40.decode(new byte[]{'A', 'A', Byte.MAX_VALUE, 'A', 'A', 'A', 'A', 'A'}, 2, 5, 1,
            "AAAAAAAA".getBytes("UTF-8"), 2));
  }

  @Test
  void testDecode33() throws UnsupportedEncodingException {
    // Arrange
    G726_40 g726_40 = new G726_40();

    // Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> g726_40
        .decode(new byte[]{'A', 'A', 'A', 'A', 0, 'A', 'A', 'A'}, 2, 5, 1, "AAAAAAAA".getBytes("UTF-8"), 2));
  }

  @Test
  void testDecode34() throws UnsupportedEncodingException {
    // Arrange
    G726_40 g726_40 = new G726_40();
    byte[] in_buff = "AAAA￿AAA".getBytes("UTF-8");

    // Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> g726_40.decode(in_buff, 2, 5, 1, "AAAAAAAA".getBytes("UTF-8"), 2));
  }

  @Test
  void testDecode35() throws UnsupportedEncodingException {
    // Arrange
    G726_40 g726_40 = new G726_40();

    // Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> g726_40.decode(new byte[]{}, 2, 5, 1, "AAAAAAAA".getBytes("UTF-8"), 2));
  }

  @Test
  void testDecode36() throws UnsupportedEncodingException {
    // Arrange
    G726_40 g726_40 = new G726_40();
    byte[] in_buff = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> g726_40.decode(in_buff, 2, 5, 2, "AAAAAAAA".getBytes("UTF-8"), 2));
  }

  @Test
  void testDecode37() throws UnsupportedEncodingException {
    // Arrange
    G726_40 g726_40 = new G726_40();
    byte[] in_buff = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> g726_40.decode(in_buff, 2, 5, 3, "AAAAAAAA".getBytes("UTF-8"), 2));
  }

  @Test
  void testDecode38() throws UnsupportedEncodingException {
    // Arrange
    G726_40 g726_40 = new G726_40();
    byte[] in_buff = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertEquals(8, g726_40.decode(in_buff, 2, 5, 1, "AAAAAAAAAAAAAAAA".getBytes("UTF-8"), 2));
    G726State g726State = g726_40.state;
    assertEquals(37175, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(186, g726State.dms);
    assertEquals(199, g726State.dml);
    assertEquals(204, g726State.ap);
    assertEquals(1009, g726State.yu);
  }

  @Test
  void testDecode39() throws UnsupportedEncodingException {
    // Arrange
    byte[] in_buff = "AAAAAAAA".getBytes("UTF-8");
    byte[] out_buff = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertEquals(0, G726_40.decode(in_buff, 2, 3, 1, out_buff, 2, new G726State()));
  }

  @Test
  void testDecode40() {
    // Arrange, Act and Assert
    assertEquals(0, G726_40.decode(new byte[]{'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A'}, 1, 1, 2,
        new byte[]{'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A'}, 1, new G726State()));
  }

  @Test
  void testDecode41() {
    // Arrange, Act and Assert
    assertEquals(0, G726_40.decode(new byte[]{'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A'}, 1, 1, 3,
        new byte[]{'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A'}, 1, new G726State()));
  }

  @Test
  void testDecode42() throws UnsupportedEncodingException {
    // Arrange
    byte[] in_buff = "AAAAAAAA".getBytes("UTF-8");
    byte[] out_buff = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> G726_40.decode(in_buff, 2, 5, 1, out_buff, 2, new G726State()));
  }

  @Test
  void testDecode43() throws UnsupportedEncodingException {
    // Arrange
    byte[] in_buff = "AAAAAAAA".getBytes("UTF-8");
    byte[] out_buff = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertEquals(-1, G726_40.decode(in_buff, 2, 3, 0, out_buff, 2, new G726State()));
  }

  @Test
  void testDecode44() throws UnsupportedEncodingException {
    // Arrange
    byte[] out_buff = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> G726_40.decode(new byte[]{'A', 'A', 0, 'A', 'A', 'A', 'A', 'A'}, 2, 5, 1, out_buff, 2, new G726State()));
  }

  @Test
  void testDecode45() throws UnsupportedEncodingException {
    // Arrange
    byte[] in_buff = "AA￿AAAAA".getBytes("UTF-8");
    byte[] out_buff = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> G726_40.decode(in_buff, 2, 5, 1, out_buff, 2, new G726State()));
  }

  @Test
  void testDecode46() throws UnsupportedEncodingException {
    // Arrange
    byte[] out_buff = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> G726_40
        .decode(new byte[]{'A', 'A', Byte.MAX_VALUE, 'A', 'A', 'A', 'A', 'A'}, 2, 5, 1, out_buff, 2, new G726State()));
  }

  @Test
  void testDecode47() throws UnsupportedEncodingException {
    // Arrange
    byte[] out_buff = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> G726_40.decode(new byte[]{'A', 'A', 'A', 'A', 0, 'A', 'A', 'A'}, 2, 5, 1, out_buff, 2, new G726State()));
  }

  @Test
  void testDecode48() throws UnsupportedEncodingException {
    // Arrange
    byte[] in_buff = "AAAA￿AAA".getBytes("UTF-8");
    byte[] out_buff = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> G726_40.decode(in_buff, 2, 5, 1, out_buff, 2, new G726State()));
  }

  @Test
  void testDecode49() throws UnsupportedEncodingException {
    // Arrange
    byte[] out_buff = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> G726_40.decode(new byte[]{}, 2, 5, 1, out_buff, 2, new G726State()));
  }

  @Test
  void testDecode50() throws UnsupportedEncodingException {
    // Arrange
    byte[] in_buff = "AAAAAAAA".getBytes("UTF-8");
    byte[] out_buff = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> G726_40.decode(in_buff, 2, 5, 2, out_buff, 2, new G726State()));
  }

  @Test
  void testDecode51() throws UnsupportedEncodingException {
    // Arrange
    byte[] in_buff = "AAAAAAAA".getBytes("UTF-8");
    byte[] out_buff = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> G726_40.decode(in_buff, 2, 5, 3, out_buff, 2, new G726State()));
  }

  @Test
  void testDecode52() throws UnsupportedEncodingException {
    // Arrange
    byte[] in_buff = "AAAAAAAA".getBytes("UTF-8");
    byte[] out_buff = "AAAAAAAAAAAAAAAA".getBytes("UTF-8");
    G726State g726State = new G726State();

    // Act and Assert
    assertEquals(8, G726_40.decode(in_buff, 2, 5, 1, out_buff, 2, g726State));
    assertEquals(1009, g726State.yu);
    assertEquals(37175, g726State.yl);
    assertEquals(0, g726State.td);
    assertEquals(186, g726State.dms);
    assertEquals(199, g726State.dml);
    assertEquals(204, g726State.ap);
  }

  @Test
  void testDecode53() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> G726_40.decode(new byte[]{}, 2, 5, 1, "AAAAAAAA".getBytes("UTF-8"), 2, null));
  }

  @Test
  void testConstructor() {
    // Arrange and Act
    G726_40 actualG726_40 = new G726_40();

    // Assert
    AudioFormat.Encoding expectedEncoding = actualG726_40.encoding;
    assertSame(expectedEncoding, actualG726_40.getEncoding());
    G726State g726State = actualG726_40.state;
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
    AudioFormat.Encoding actualEncoding = (new G726_40()).getEncoding();

    // Assert
    assertSame(((G726Encoding) actualEncoding).G726_40, actualEncoding);
  }
}

