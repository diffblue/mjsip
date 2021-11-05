package org.zoolu.net;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import org.junit.jupiter.api.Test;

class TlsSocketFactoryDiffblueTest {
  @Test
  void testConstructor() throws IOException, KeyManagementException, KeyStoreException, NoSuchAlgorithmException,
      UnrecoverableKeyException, CertificateException {
    // Arrange and Act
    TlsSocketFactory actualTlsSocketFactory = new TlsSocketFactory(new TlsContext());
    actualTlsSocketFactory.setEnabledProtocols(new String[]{"Enabled protocols"});
    actualTlsSocketFactory.setUseClientMode(true);

    // Assert
    assertTrue(actualTlsSocketFactory.getUseClientMode());
  }

  @Test
  void testConstructor2() throws IOException, KeyManagementException, KeyStoreException, NoSuchAlgorithmException,
      UnrecoverableKeyException, CertificateException {
    // Arrange and Act
    TlsSocketFactory actualTlsSocketFactory = new TlsSocketFactory(new TlsContext());

    // Assert
    assertEquals(2, actualTlsSocketFactory.getEnabledProtocols().length);
    assertEquals(6, actualTlsSocketFactory.supported_protocols.length);
    assertTrue(actualTlsSocketFactory.getUseClientMode());
  }

  @Test
  void testConstructor3() throws IOException, KeyManagementException, KeyStoreException, NoSuchAlgorithmException,
      UnrecoverableKeyException, CertificateException {
    // Arrange
    TlsContext tlsContext = new TlsContext();
    tlsContext.addTrustCert((Certificate) null);

    // Act
    TlsSocketFactory actualTlsSocketFactory = new TlsSocketFactory(tlsContext);

    // Assert
    assertEquals(2, actualTlsSocketFactory.getEnabledProtocols().length);
    assertEquals(6, actualTlsSocketFactory.supported_protocols.length);
    assertTrue(actualTlsSocketFactory.getUseClientMode());
  }

  @Test
  void testConstructor4() throws IOException, KeyManagementException, KeyStoreException, NoSuchAlgorithmException,
      UnrecoverableKeyException, CertificateException {
    // Arrange
    TlsContext tlsContext = new TlsContext();
    tlsContext.setTrustAll(true);
    tlsContext.addTrustCert((Certificate) null);

    // Act
    TlsSocketFactory actualTlsSocketFactory = new TlsSocketFactory(tlsContext);

    // Assert
    assertEquals(2, actualTlsSocketFactory.getEnabledProtocols().length);
    assertEquals(6, actualTlsSocketFactory.supported_protocols.length);
    assertTrue(actualTlsSocketFactory.getUseClientMode());
  }

  @Test
  void testGetSupportedProtocols() throws IOException, KeyManagementException, KeyStoreException,
      NoSuchAlgorithmException, UnrecoverableKeyException, CertificateException {
    // Arrange
    TlsSocketFactory tlsSocketFactory = new TlsSocketFactory(new TlsContext());

    // Act
    String[] actualSupportedProtocols = tlsSocketFactory.getSupportedProtocols();

    // Assert
    assertSame(tlsSocketFactory.supported_protocols, actualSupportedProtocols);
    assertEquals(6, actualSupportedProtocols.length);
    assertEquals("TLSv1.3", actualSupportedProtocols[0]);
    assertEquals("TLSv1.2", actualSupportedProtocols[1]);
    assertEquals("TLSv1.1", actualSupportedProtocols[2]);
    assertEquals("TLSv1", actualSupportedProtocols[3]);
    assertEquals("SSLv3", actualSupportedProtocols[4]);
    assertEquals("SSLv2Hello", actualSupportedProtocols[5]);
    assertEquals(2, tlsSocketFactory.getEnabledProtocols().length);
    assertSame(actualSupportedProtocols, tlsSocketFactory.supported_protocols);
  }

  @Test
  void testGetSupportedProtocols2() throws IOException, KeyManagementException, KeyStoreException,
      NoSuchAlgorithmException, UnrecoverableKeyException, CertificateException {
    // Arrange
    TlsSocketFactory tlsSocketFactory = new TlsSocketFactory(new TlsContext());
    tlsSocketFactory.setEnabledProtocols(new String[]{"Enabled protocols"});

    // Act
    String[] actualSupportedProtocols = tlsSocketFactory.getSupportedProtocols();

    // Assert
    assertSame(tlsSocketFactory.supported_protocols, actualSupportedProtocols);
    assertEquals(6, actualSupportedProtocols.length);
    assertEquals("TLSv1.3", actualSupportedProtocols[0]);
    assertEquals("TLSv1.2", actualSupportedProtocols[1]);
    assertEquals("TLSv1.1", actualSupportedProtocols[2]);
    assertEquals("TLSv1", actualSupportedProtocols[3]);
    assertEquals("SSLv3", actualSupportedProtocols[4]);
    assertEquals("SSLv2Hello", actualSupportedProtocols[5]);
    assertSame(actualSupportedProtocols, tlsSocketFactory.supported_protocols);
  }

  @Test
  void testGetEnabledProtocols() throws IOException, KeyManagementException, KeyStoreException,
      NoSuchAlgorithmException, UnrecoverableKeyException, CertificateException {
    // Arrange
    TlsSocketFactory tlsSocketFactory = new TlsSocketFactory(new TlsContext());

    // Act
    String[] actualEnabledProtocols = tlsSocketFactory.getEnabledProtocols();

    // Assert
    assertSame(tlsSocketFactory.enabled_protocols, actualEnabledProtocols);
    assertEquals(2, actualEnabledProtocols.length);
    assertEquals("TLSv1.3", actualEnabledProtocols[0]);
    assertEquals("TLSv1.2", actualEnabledProtocols[1]);
    assertEquals(6, tlsSocketFactory.getSupportedProtocols().length);
    assertSame(actualEnabledProtocols, tlsSocketFactory.enabled_protocols);
  }

  @Test
  void testGetEnabledProtocols2() throws IOException, KeyManagementException, KeyStoreException,
      NoSuchAlgorithmException, UnrecoverableKeyException, CertificateException {
    // Arrange
    TlsSocketFactory tlsSocketFactory = new TlsSocketFactory(new TlsContext());
    tlsSocketFactory.setEnabledProtocols(new String[]{"Enabled protocols"});

    // Act
    String[] actualEnabledProtocols = tlsSocketFactory.getEnabledProtocols();

    // Assert
    assertSame(tlsSocketFactory.enabled_protocols, actualEnabledProtocols);
    assertEquals(1, actualEnabledProtocols.length);
    assertEquals("Enabled protocols", actualEnabledProtocols[0]);
  }
}

