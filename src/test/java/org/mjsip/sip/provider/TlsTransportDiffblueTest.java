package org.mjsip.sip.provider;

import static org.junit.jupiter.api.Assertions.assertThrows;
import java.io.IOException;
import java.io.StringWriter;
import org.junit.jupiter.api.Test;
import org.zoolu.net.IpAddress;
import org.zoolu.util.LogWriter;

class TlsTransportDiffblueTest {
  @Test
  void testConstructor() throws IOException {
    // Arrange
    IpAddress host_ipaddr = IpAddress.getByName("42");

    // Act and Assert
    assertThrows(IOException.class, () -> new TlsTransport(8080, host_ipaddr, 3, "Key file", "Cert file",
        "Trust folder", new LogWriter(new StringWriter())));

  }

  @Test
  void testConstructor2() throws IOException {
    // Arrange
    IpAddress host_ipaddr = IpAddress.getByName("42");

    // Act and Assert
    assertThrows(IOException.class,
        () -> new TlsTransport(8080, host_ipaddr, 3, "Key file", "Cert file", new LogWriter(new StringWriter())));

  }

  @Test
  void testConstructor3() throws IOException {
    // Arrange
    IpAddress host_ipaddr = IpAddress.getByName("42");

    // Act and Assert
    assertThrows(IOException.class, () -> new TlsTransport(8080, host_ipaddr, 3, "Key file", "Cert file",
        new String[]{"Trusted certs"}, new LogWriter(new StringWriter())));

  }
}

