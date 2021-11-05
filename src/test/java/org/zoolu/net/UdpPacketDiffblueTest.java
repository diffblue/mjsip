package org.zoolu.net;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import org.junit.jupiter.api.Test;

class UdpPacketDiffblueTest {
  @Test
  void testConstructor2() throws UnsupportedEncodingException, UnknownHostException {
    // Arrange
    byte[] buf = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertEquals(8, (new UdpPacket(buf, 2, 3, IpAddress.getByName("42"), 8080)).getData().length);
  }

  @Test
  void testConstructor3() throws UnsupportedEncodingException {
    // Arrange
    byte[] buf = "AAAAAAAA".getBytes("UTF-8");
    IpAddress ipAddress = new IpAddress((InetAddress) null);

    // Act
    UdpPacket actualUdpPacket = new UdpPacket(buf, 2, 3, ipAddress, 8080);

    // Assert
    assertEquals(8, actualUdpPacket.getData().length);
    InetAddress expectedInetAddress = actualUdpPacket.getIpAddress().inet_address;
    assertSame(expectedInetAddress, ipAddress.getInetAddress());
  }

  @Test
  void testConstructor4() throws UnsupportedEncodingException, UnknownHostException {
    // Arrange
    byte[] buf = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertEquals(8, (new UdpPacket(buf, 3, IpAddress.getByName("42"), 8080)).getData().length);
  }

  @Test
  void testConstructor5() throws UnsupportedEncodingException {
    // Arrange
    byte[] buf = "AAAAAAAA".getBytes("UTF-8");
    IpAddress ipAddress = new IpAddress((InetAddress) null);

    // Act
    UdpPacket actualUdpPacket = new UdpPacket(buf, 3, ipAddress, 8080);

    // Assert
    assertEquals(8, actualUdpPacket.getData().length);
    InetAddress expectedInetAddress = actualUdpPacket.getIpAddress().inet_address;
    assertSame(expectedInetAddress, ipAddress.getInetAddress());
  }

  @Test
  void testConstructor6() throws UnsupportedEncodingException, UnknownHostException {
    // Arrange
    byte[] data = "AAAAAAAA".getBytes("UTF-8");

    // Act and Assert
    assertEquals(8, (new UdpPacket(data, IpAddress.getByName("42"), 8080)).getData().length);
  }

  @Test
  void testConstructor7() throws UnsupportedEncodingException {
    // Arrange
    byte[] data = "AAAAAAAA".getBytes("UTF-8");
    IpAddress ipAddress = new IpAddress((InetAddress) null);

    // Act
    UdpPacket actualUdpPacket = new UdpPacket(data, ipAddress, 8080);

    // Assert
    assertEquals(8, actualUdpPacket.getData().length);
    InetAddress expectedInetAddress = actualUdpPacket.getIpAddress().inet_address;
    assertSame(expectedInetAddress, ipAddress.getInetAddress());
  }

  @Test
  void testGetIpAddress() throws UnsupportedEncodingException {
    // Arrange and Act
    IpAddress actualIpAddress = (new UdpPacket(new DatagramPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 3)))
        .getIpAddress();

    // Assert
    assertSame(actualIpAddress.getInetAddress(), actualIpAddress.inet_address);
    assertEquals("127.0.0.1", actualIpAddress.toString());
  }

  @Test
  void testGetData() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals(24,
        (new UdpPacket(new DatagramPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 3))).getData().length);
  }

  @Test
  void testGetLength() throws UnsupportedEncodingException {
    // Arrange, Act and Assert
    assertEquals(3, (new UdpPacket(new DatagramPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 3))).getLength());
  }

  @Test
  void testSetIpAddress() throws UnsupportedEncodingException, UnknownHostException {
    // Arrange
    UdpPacket udpPacket = new UdpPacket(new DatagramPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 3));
    IpAddress byName = IpAddress.getByName("42");

    // Act
    udpPacket.setIpAddress(byName);

    // Assert that nothing has changed
    InetAddress expectedInetAddress = byName.inet_address;
    assertSame(expectedInetAddress, byName.getInetAddress());
    assertEquals("0.0.0.42", byName.toString());
    assertEquals(24, udpPacket.getData().length);
  }

  @Test
  void testSetIpAddress2() throws UnsupportedEncodingException {
    // Arrange
    UdpPacket udpPacket = new UdpPacket(new DatagramPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 3));
    IpAddress ipAddress = new IpAddress((InetAddress) null);

    // Act
    udpPacket.setIpAddress(ipAddress);

    // Assert
    InetAddress expectedInetAddress = ipAddress.inet_address;
    assertSame(expectedInetAddress, ipAddress.getInetAddress());
  }

  @Test
  void testSetLength() throws UnsupportedEncodingException {
    // Arrange
    UdpPacket udpPacket = new UdpPacket(new DatagramPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 3));

    // Act
    udpPacket.setLength(3);

    // Assert that nothing has changed
    assertEquals(24, udpPacket.getData().length);
  }

  @Test
  void testSetPort() throws UnsupportedEncodingException {
    // Arrange
    UdpPacket udpPacket = new UdpPacket(new DatagramPacket("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"), 3));

    // Act
    udpPacket.setPort(8080);

    // Assert that nothing has changed
    assertEquals(24, udpPacket.getData().length);
  }
}

