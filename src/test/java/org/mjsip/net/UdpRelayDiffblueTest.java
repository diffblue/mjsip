package org.mjsip.net;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import javax.management.loading.MLet;
import org.junit.jupiter.api.Test;

class UdpRelayDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    UdpRelay actualUdpRelay = new UdpRelay(8080, "42 Main St", 8080, null);
    actualUdpRelay.setDestPort(8080);
    actualUdpRelay.setSoTimeout(1);

    // Assert
    assertEquals(8080, actualUdpRelay.getLocalPort());
    assertEquals(1, actualUdpRelay.getSoTimeout());
    assertEquals("localhost:8080-->42 Main St:8080", actualUdpRelay.toString());
    assertEquals("42 Main St", actualUdpRelay.dest_addr.toString());
    assertEquals("0.0.0.0", actualUdpRelay.src_addr.toString());
  }

  @Test
  void testConstructor2() {
    // Arrange and Act
    UdpRelay actualUdpRelay = new UdpRelay(8080, "42 Main St", 8080, 1, mock(UdpRelayListener.class));

    // Assert
    assertFalse(actualUdpRelay.stop);
    assertEquals(8080, actualUdpRelay.dest_port);
    assertEquals(0, actualUdpRelay.src_port);
    assertEquals(8080, actualUdpRelay.getLocalPort());
    assertEquals(1, actualUdpRelay.alive_to);
    assertEquals("localhost:8080-->42 Main St:8080", actualUdpRelay.toString());
    assertEquals(3000, actualUdpRelay.getSoTimeout());
    assertEquals("42 Main St", actualUdpRelay.dest_addr.toString());
    assertEquals("0.0.0.0", actualUdpRelay.src_addr.toString());
  }

  @Test
  void testConstructor3() {
    // Arrange and Act
    UdpRelay actualUdpRelay = new UdpRelay(0, "42 Main St", 8080, 1, mock(UdpRelayListener.class));

    // Assert
    assertFalse(actualUdpRelay.stop);
    assertEquals(8080, actualUdpRelay.dest_port);
    assertEquals(0, actualUdpRelay.src_port);
    assertEquals(0, actualUdpRelay.getLocalPort());
    assertEquals(1, actualUdpRelay.alive_to);
    assertEquals("localhost:0-->42 Main St:8080", actualUdpRelay.toString());
    assertEquals(3000, actualUdpRelay.getSoTimeout());
    assertEquals("42 Main St", actualUdpRelay.dest_addr.toString());
    assertEquals("0.0.0.0", actualUdpRelay.src_addr.toString());
  }

  @Test
  void testConstructor4() {
    // Arrange and Act
    UdpRelay actualUdpRelay = new UdpRelay(8080, "42 Main St", 8080, mock(UdpRelayListener.class));

    // Assert
    assertFalse(actualUdpRelay.stop);
    assertEquals(8080, actualUdpRelay.dest_port);
    assertEquals(0, actualUdpRelay.src_port);
    assertEquals(8080, actualUdpRelay.getLocalPort());
    assertEquals(0, actualUdpRelay.alive_to);
    assertEquals("localhost:8080-->42 Main St:8080", actualUdpRelay.toString());
    assertEquals(3000, actualUdpRelay.getSoTimeout());
    assertEquals("42 Main St", actualUdpRelay.dest_addr.toString());
    assertEquals("0.0.0.0", actualUdpRelay.src_addr.toString());
  }

  @Test
  void testConstructor5() {
    // Arrange and Act
    UdpRelay actualUdpRelay = new UdpRelay(0, "42 Main St", 8080, mock(UdpRelayListener.class));

    // Assert
    assertFalse(actualUdpRelay.stop);
    assertEquals(8080, actualUdpRelay.dest_port);
    assertEquals(0, actualUdpRelay.src_port);
    assertEquals(0, actualUdpRelay.getLocalPort());
    assertEquals(0, actualUdpRelay.alive_to);
    assertEquals("localhost:0-->42 Main St:8080", actualUdpRelay.toString());
    assertEquals(3000, actualUdpRelay.getSoTimeout());
    assertEquals("42 Main St", actualUdpRelay.dest_addr.toString());
    assertEquals("0.0.0.0", actualUdpRelay.src_addr.toString());
  }

  @Test
  void testSetDestAddress() {
    // Arrange
    UdpRelay udpRelay = new UdpRelay(8080, "42 Main St", 8080, null);

    // Act
    UdpRelay actualSetDestAddressResult = udpRelay.setDestAddress("42 Main St");

    // Assert
    assertSame(udpRelay, actualSetDestAddressResult);
    assertEquals("localhost:8080-->42 Main St:8080", actualSetDestAddressResult.toString());
    assertEquals("42 Main St", actualSetDestAddressResult.dest_addr.toString());
  }

  @Test
  void testSetDestAddress2() {
    // Arrange
    UdpRelay udpRelay = new UdpRelay(0, "42 Main St", 8080, null);

    // Act
    UdpRelay actualSetDestAddressResult = udpRelay.setDestAddress("42 Main St");

    // Assert
    assertSame(udpRelay, actualSetDestAddressResult);
    assertEquals("localhost:0-->42 Main St:8080", actualSetDestAddressResult.toString());
    assertEquals("42 Main St", actualSetDestAddressResult.dest_addr.toString());
  }

  @Test
  void testSetDestAddress3() {
    // Arrange
    UdpRelay udpRelay = new UdpRelay(-1, "42 Main St", 8080, null);

    // Act
    UdpRelay actualSetDestAddressResult = udpRelay.setDestAddress("42 Main St");

    // Assert
    assertSame(udpRelay, actualSetDestAddressResult);
    assertEquals("localhost:-1-->42 Main St:8080", actualSetDestAddressResult.toString());
    assertEquals("42 Main St", actualSetDestAddressResult.dest_addr.toString());
  }

  @Test
  void testSetDestAddress4() {
    // Arrange
    UdpRelay udpRelay = new UdpRelay(Integer.MIN_VALUE, "42 Main St", 8080, null);

    // Act
    UdpRelay actualSetDestAddressResult = udpRelay.setDestAddress("42 Main St");

    // Assert
    assertSame(udpRelay, actualSetDestAddressResult);
    assertEquals("localhost:-2147483648-->42 Main St:8080", actualSetDestAddressResult.toString());
    assertEquals("42 Main St", actualSetDestAddressResult.dest_addr.toString());
  }

  @Test
  void testSetDestAddress5() {
    // Arrange
    UdpRelay udpRelay = new UdpRelay(8080, "42 Main St", 8080, mock(UdpRelayListener.class));

    // Act
    UdpRelay actualSetDestAddressResult = udpRelay.setDestAddress("42 Main St");

    // Assert
    assertSame(udpRelay, actualSetDestAddressResult);
    assertEquals("localhost:8080-->42 Main St:8080", actualSetDestAddressResult.toString());
    assertEquals("42 Main St", actualSetDestAddressResult.dest_addr.toString());
  }

  @Test
  void testSetDestAddress6() {
    // Arrange
    UdpRelay udpRelay = new UdpRelay(8080, "42 Main St", 8080, 1, null);

    // Act
    UdpRelay actualSetDestAddressResult = udpRelay.setDestAddress("42 Main St");

    // Assert
    assertSame(udpRelay, actualSetDestAddressResult);
    assertEquals("localhost:8080-->42 Main St:8080", actualSetDestAddressResult.toString());
    assertEquals("42 Main St", actualSetDestAddressResult.dest_addr.toString());
  }

  @Test
  void testSetDestAddress7() {
    // Arrange
    UdpRelay udpRelay = new UdpRelay(8080, "42 Main St", 8080, null);
    udpRelay.setDestPort(8080);

    // Act
    UdpRelay actualSetDestAddressResult = udpRelay.setDestAddress("42 Main St");

    // Assert
    assertSame(udpRelay, actualSetDestAddressResult);
    assertEquals("localhost:8080-->42 Main St:8080", actualSetDestAddressResult.toString());
    assertEquals("42 Main St", actualSetDestAddressResult.dest_addr.toString());
  }

  @Test
  void testSetDestAddress8() {
    // Arrange
    UdpRelay udpRelay = new UdpRelay(8080, "42 Main St", 8080, null);
    udpRelay.setUncaughtExceptionHandler(new ThreadGroup("Name"));

    // Act
    UdpRelay actualSetDestAddressResult = udpRelay.setDestAddress("42 Main St");

    // Assert
    assertSame(udpRelay, actualSetDestAddressResult);
    assertEquals("localhost:8080-->42 Main St:8080", actualSetDestAddressResult.toString());
    assertEquals("42 Main St", actualSetDestAddressResult.dest_addr.toString());
  }

  @Test
  void testSetDestAddress9() {
    // Arrange
    UdpRelay udpRelay = new UdpRelay(8080, "42 Main St", 8080, null);
    udpRelay.setSoTimeout(0);

    // Act
    UdpRelay actualSetDestAddressResult = udpRelay.setDestAddress("42 Main St");

    // Assert
    assertSame(udpRelay, actualSetDestAddressResult);
    assertEquals("localhost:8080-->42 Main St:8080", actualSetDestAddressResult.toString());
    assertEquals("42 Main St", actualSetDestAddressResult.dest_addr.toString());
  }

  @Test
  void testSetDestAddress10() {
    // Arrange
    UdpRelay udpRelay = new UdpRelay(8080, "42 Main St", 8080, 1, mock(UdpRelayListener.class));

    // Act
    UdpRelay actualSetDestAddressResult = udpRelay.setDestAddress("42 Main St");

    // Assert
    assertSame(udpRelay, actualSetDestAddressResult);
    assertEquals("localhost:8080-->42 Main St:8080", actualSetDestAddressResult.toString());
    assertEquals("42 Main St", actualSetDestAddressResult.dest_addr.toString());
  }

  @Test
  void testSetDestAddress11() {
    // Arrange
    UdpRelay udpRelay = new UdpRelay(-1, "42 Main St", 8080, null);
    udpRelay.setName("Name");

    // Act
    UdpRelay actualSetDestAddressResult = udpRelay.setDestAddress("42 Main St");

    // Assert
    assertSame(udpRelay, actualSetDestAddressResult);
    assertEquals("localhost:-1-->42 Main St:8080", actualSetDestAddressResult.toString());
    assertEquals("42 Main St", actualSetDestAddressResult.dest_addr.toString());
  }

  @Test
  void testSetDestAddress12() {
    // Arrange
    UdpRelay udpRelay = new UdpRelay(8080, "42 Main St", 8080, null);
    udpRelay.setSoTimeout(1);

    // Act
    UdpRelay actualSetDestAddressResult = udpRelay.setDestAddress("42 Main St");

    // Assert
    assertSame(udpRelay, actualSetDestAddressResult);
    assertEquals("localhost:8080-->42 Main St:8080", actualSetDestAddressResult.toString());
    assertEquals("42 Main St", actualSetDestAddressResult.dest_addr.toString());
  }

  @Test
  void testSetDestAddress13() {
    // Arrange
    UdpRelay udpRelay = new UdpRelay(-1, "42 Main St", 8080, mock(UdpRelayListener.class));
    udpRelay.setName("Name");

    // Act
    UdpRelay actualSetDestAddressResult = udpRelay.setDestAddress("42 Main St");

    // Assert
    assertSame(udpRelay, actualSetDestAddressResult);
    assertEquals("localhost:-1-->42 Main St:8080", actualSetDestAddressResult.toString());
    assertEquals("42 Main St", actualSetDestAddressResult.dest_addr.toString());
  }

  @Test
  void testIsRunning() {
    // Arrange, Act and Assert
    assertTrue((new UdpRelay(8080, "42 Main St", 8080, null)).isRunning());
    assertTrue((new UdpRelay(0, "42 Main St", 8080, null)).isRunning());
    assertTrue((new UdpRelay(Integer.MIN_VALUE, "42 Main St", 8080, null)).isRunning());
    assertTrue((new UdpRelay(3, "42 Main St", 8080, null)).isRunning());
    assertTrue((new UdpRelay(8080, "42 Main St", 8080, mock(UdpRelayListener.class))).isRunning());
    assertTrue((new UdpRelay(8080, "42 Main St", 8080, 1, null)).isRunning());
    assertTrue((new UdpRelay(8080, "42 Main St", 8080, 1, mock(UdpRelayListener.class))).isRunning());
  }

  @Test
  void testIsRunning2() {
    // Arrange
    UdpRelay udpRelay = new UdpRelay(8080, "42 Main St", 8080, null);
    udpRelay.setDestPort(8080);

    // Act and Assert
    assertTrue(udpRelay.isRunning());
  }

  @Test
  void testIsRunning3() {
    // Arrange
    UdpRelay udpRelay = new UdpRelay(8080, "42 Main St", 8080, null);
    udpRelay.setContextClassLoader(new MLet());

    // Act and Assert
    assertTrue(udpRelay.isRunning());
  }

  @Test
  void testIsRunning4() {
    // Arrange
    UdpRelay udpRelay = new UdpRelay(8080, "42 Main St", 8080, null);
    udpRelay.setSoTimeout(0);

    // Act and Assert
    assertTrue(udpRelay.isRunning());
  }

  @Test
  void testIsRunning5() {
    // Arrange
    UdpRelay udpRelay = new UdpRelay(8080, "42 Main St", 8080, null);
    udpRelay.setDestAddress("42 Main St");

    // Act and Assert
    assertTrue(udpRelay.isRunning());
  }

  @Test
  void testIsRunning6() {
    // Arrange
    UdpRelay udpRelay = new UdpRelay(Integer.MIN_VALUE, "42 Main St", 8080, null);
    udpRelay.setSoTimeout(0);

    // Act and Assert
    assertTrue(udpRelay.isRunning());
  }

  @Test
  void testIsRunning7() {
    // Arrange
    UdpRelay udpRelay = new UdpRelay(Integer.MIN_VALUE, "", 8080, null);
    udpRelay.setSoTimeout(0);

    // Act and Assert
    assertTrue(udpRelay.isRunning());
  }
}

