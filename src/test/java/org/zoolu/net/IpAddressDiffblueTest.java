package org.zoolu.net;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.net.InetAddress;
import java.net.UnknownHostException;
import org.junit.jupiter.api.Test;

class IpAddressDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    IpAddress actualIpAddress = new IpAddress("42 Main St");

    // Assert
    assertEquals("42 Main St", actualIpAddress.toString());
    assertNull(actualIpAddress.inet_address);
  }

  @Test
  void testConstructor2() {
    // Arrange and Act
    IpAddress actualIpAddress = new IpAddress((InetAddress) null);

    // Assert
    assertSame(actualIpAddress.getInetAddress(), actualIpAddress.inet_address);
    assertEquals("127.0.0.1", actualIpAddress.toString());
  }

  @Test
  void testConstructor3() throws UnknownHostException {
    // Arrange and Act
    IpAddress actualIpAddress = new IpAddress(IpAddress.getByName("42"));

    // Assert
    InetAddress expectedInetAddress = actualIpAddress.inet_address;
    assertSame(expectedInetAddress, actualIpAddress.getInetAddress());
    assertEquals("0.0.0.42", actualIpAddress.toString());
  }

  @Test
  void testGetInetAddress() throws UnknownHostException {
    // Arrange
    IpAddress byName = IpAddress.getByName("42");

    // Act and Assert
    assertSame(byName.inet_address, byName.getInetAddress());
  }

  @Test
  void testGetInetAddress2() {
    // Arrange
    IpAddress ipAddress = new IpAddress((InetAddress) null);

    // Act
    InetAddress actualInetAddress = ipAddress.getInetAddress();

    // Assert
    assertSame(ipAddress.inet_address, actualInetAddress);
    assertSame(actualInetAddress, ipAddress.inet_address);
  }

  @Test
  void testClone() throws UnknownHostException {
    // Arrange and Act
    Object actualCloneResult = IpAddress.getByName("42").clone();

    // Assert
    InetAddress expectedInetAddress = ((IpAddress) actualCloneResult).inet_address;
    assertSame(expectedInetAddress, ((IpAddress) actualCloneResult).getInetAddress());
    assertEquals("0.0.0.42", actualCloneResult.toString());
  }

  @Test
  void testEquals() throws UnknownHostException {
    // Arrange
    IpAddress byName = IpAddress.getByName("42");
    IpAddress byName1 = IpAddress.getByName("42");

    // Act and Assert
    assertTrue(byName.equals(byName1));
    int expectedHashCodeResult = byName.hashCode();
    assertEquals(expectedHashCodeResult, byName1.hashCode());
  }

  @Test
  void testEquals2() throws UnknownHostException {
    // Arrange
    IpAddress byName = IpAddress.getByName(null);

    // Act and Assert
    assertFalse(byName.equals(IpAddress.getByName("42")));
  }

  @Test
  void testEquals3() throws UnknownHostException {
    // Arrange
    IpAddress ipAddress = new IpAddress("42 Main St");

    // Act and Assert
    assertFalse(ipAddress.equals(IpAddress.getByName("42")));
  }

  @Test
  void testEquals4() throws UnknownHostException {
    // Arrange
    IpAddress ipAddress = new IpAddress((InetAddress) null);

    // Act and Assert
    assertFalse(ipAddress.equals(IpAddress.getByName("42")));
  }

  @Test
  void testEquals5() throws UnknownHostException {
    // Arrange, Act and Assert
    assertFalse(IpAddress.getByName("42").equals(null));
  }

  @Test
  void testEquals6() throws UnknownHostException {
    // Arrange, Act and Assert
    assertFalse(IpAddress.getByName("42").equals(0));
  }

  @Test
  void testToString() throws UnknownHostException {
    // Arrange
    IpAddress byName = IpAddress.getByName("42");

    // Act and Assert
    assertEquals("0.0.0.42", byName.toString());
    assertEquals("0.0.0.42", byName.address);
  }

  @Test
  void testToString2() {
    // Arrange, Act and Assert
    assertEquals("42 Main St", (new IpAddress("42 Main St")).toString());
  }

  @Test
  void testToString3() {
    // Arrange, Act and Assert
    assertNull((new IpAddress((InetAddress) null)).toString());
  }

  @Test
  void testGetByName() throws UnknownHostException {
    // Arrange and Act
    IpAddress actualByName = IpAddress.getByName("42");

    // Assert
    InetAddress expectedInetAddress = actualByName.inet_address;
    assertSame(expectedInetAddress, actualByName.getInetAddress());
    assertEquals("0.0.0.42", actualByName.toString());
  }

  @Test
  void testIsPrivateAddress() throws UnknownHostException {
    // Arrange
    IpAddress byName = IpAddress.getByName("42");

    // Act and Assert
    assertFalse(byName.isPrivateAddress());
    assertEquals("0.0.0.42", byName.toString());
  }

  @Test
  void testIsPrivateAddress2() {
    // Arrange, Act and Assert
    assertFalse((new IpAddress("42 Main St")).isPrivateAddress());
  }

  @Test
  void testIsPrivateAddress3() {
    // Arrange, Act and Assert
    assertTrue((new IpAddress("10.")).isPrivateAddress());
  }

  @Test
  void testIsPrivateAddress4() {
    // Arrange, Act and Assert
    assertTrue((new IpAddress("192.168.")).isPrivateAddress());
  }

  @Test
  void testGetLocalHostAddress() {
    // Arrange and Act
    IpAddress actualLocalHostAddress = IpAddress.getLocalHostAddress();

    // Assert
    InetAddress expectedInetAddress = actualLocalHostAddress.inet_address;
    assertSame(expectedInetAddress, actualLocalHostAddress.getInetAddress());

  }
}

