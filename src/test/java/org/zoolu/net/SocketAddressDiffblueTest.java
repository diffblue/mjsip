package org.zoolu.net;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.net.InetAddress;
import java.net.UnknownHostException;
import org.junit.jupiter.api.Test;

class SocketAddressDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    SocketAddress actualSocketAddress = new SocketAddress("42 Main St");

    // Assert
    IpAddress expectedAddress = actualSocketAddress.ipaddr;
    assertSame(expectedAddress, actualSocketAddress.getAddress());
    assertEquals(-1, actualSocketAddress.getPort());
  }

  @Test
  void testConstructor2() {
    // Arrange and Act
    SocketAddress actualSocketAddress = new SocketAddress("42 Main St");

    // Assert
    assertEquals(-1, actualSocketAddress.getPort());
    IpAddress address = actualSocketAddress.getAddress();
    assertEquals("42 Main St", address.address);
    assertEquals("42 Main St", address.toString());
    assertNull(address.inet_address);
  }

  @Test
  void testConstructor3() {
    // Arrange and Act
    SocketAddress actualSocketAddress = new SocketAddress("org.zoolu.net.SocketAddress");

    // Assert
    assertEquals(-1, actualSocketAddress.getPort());
    IpAddress address = actualSocketAddress.getAddress();
    assertEquals("org.zoolu.net.SocketAddress", address.address);
    assertEquals("org.zoolu.net.SocketAddress", address.toString());
    assertNull(address.inet_address);
  }

  @Test
  void testConstructor4() {
    // Arrange and Act
    SocketAddress actualSocketAddress = new SocketAddress("SoaddrSoaddr");

    // Assert
    assertEquals(-1, actualSocketAddress.getPort());
    IpAddress address = actualSocketAddress.getAddress();
    assertEquals("SoaddrSoaddr", address.address);
    assertEquals("SoaddrSoaddr", address.toString());
    assertNull(address.inet_address);
  }

  @Test
  void testConstructor5() {
    // Arrange and Act
    SocketAddress actualSocketAddress = new SocketAddress("42 Main St", 8080);

    // Assert
    assertEquals(8080, actualSocketAddress.getPort());
    IpAddress address = actualSocketAddress.getAddress();
    assertEquals("42 Main St", address.address);
    assertEquals("42 Main St", address.toString());
    assertNull(address.inet_address);
  }

  @Test
  void testConstructor6() {
    // Arrange and Act
    SocketAddress actualSocketAddress = new SocketAddress(new SocketAddress("42 Main St"));

    // Assert
    assertEquals(-1, actualSocketAddress.getPort());
    assertEquals("42 Main St", actualSocketAddress.getAddress().address);
  }

  @Test
  void testConstructor7() {
    // Arrange and Act
    SocketAddress actualSocketAddress = new SocketAddress(new SocketAddress("Addr", 8080));

    // Assert
    assertEquals(8080, actualSocketAddress.getPort());
    assertEquals("Addr", actualSocketAddress.getAddress().address);
  }

  @Test
  void testClone() {
    // Arrange, Act and Assert
    assertEquals(-1, ((SocketAddress) (new SocketAddress("42 Main St")).clone()).getPort());
    assertEquals("42 Main St", ((SocketAddress) (new SocketAddress("42 Main St")).clone()).getAddress().address);
  }

  @Test
  void testEquals() {
    // Arrange, Act and Assert
    assertFalse((new SocketAddress("42 Main St")).equals(null));
    assertFalse((new SocketAddress("42 Main St")).equals("Different type to SocketAddress"));
  }

  @Test
  void testEquals2() {
    // Arrange
    SocketAddress socketAddress = new SocketAddress("42 Main St");

    // Act and Assert
    assertTrue(socketAddress.equals(socketAddress));
    int expectedHashCodeResult = socketAddress.hashCode();
    assertEquals(expectedHashCodeResult, socketAddress.hashCode());
  }

  @Test
  void testEquals3() {
    // Arrange
    SocketAddress socketAddress = new SocketAddress("42 Main St");
    SocketAddress socketAddress1 = new SocketAddress("42 Main St");

    // Act and Assert
    assertTrue(socketAddress.equals(socketAddress1));
    int expectedHashCodeResult = socketAddress.hashCode();
    assertEquals(expectedHashCodeResult, socketAddress1.hashCode());
  }

  @Test
  void testEquals4() {
    // Arrange
    SocketAddress socketAddress = new SocketAddress("Soaddr");

    // Act and Assert
    assertFalse(socketAddress.equals(new SocketAddress("42 Main St")));
  }

  @Test
  void testEquals5() {
    // Arrange
    SocketAddress socketAddress = new SocketAddress("42 Main St", 8080);

    // Act and Assert
    assertFalse(socketAddress.equals(new SocketAddress("42 Main St")));
  }

  @Test
  void testToString() {
    // Arrange, Act and Assert
    assertEquals("42 Main St:-1", (new SocketAddress("42 Main St")).toString());
    assertEquals("null:8080", (new SocketAddress((IpAddress) null, 8080)).toString());
    assertEquals("null:8080", (new SocketAddress(new IpAddress((InetAddress) null), 8080)).toString());
  }

  @Test
  void testToString2() throws UnknownHostException {
    // Arrange
    SocketAddress socketAddress = new SocketAddress(IpAddress.getByName("42"), 8080);

    // Act and Assert
    assertEquals("0.0.0.42:8080", socketAddress.toString());
    assertEquals("0.0.0.42", socketAddress.getAddress().toString());
  }
}

