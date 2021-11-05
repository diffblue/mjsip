package org.zoolu.net;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import org.junit.jupiter.api.Test;

class TlsContextDiffblueTest {
  @Test
  void testConstructor() throws IOException, KeyStoreException, NoSuchAlgorithmException, CertificateException {
    // Arrange and Act
    TlsContext actualTlsContext = new TlsContext();

    // Assert
    KeyStore expectedKeyStore = actualTlsContext.ks;
    assertSame(expectedKeyStore, actualTlsContext.getKeyStore());
    assertFalse(actualTlsContext.isTrustAll());
  }

  @Test
  void testConstructor2() throws IOException, KeyStoreException, NoSuchAlgorithmException, CertificateException {
    // Arrange and Act
    TlsContext actualTlsContext = new TlsContext();

    // Assert
    KeyStore expectedKeyStore = actualTlsContext.ks;
    assertSame(expectedKeyStore, actualTlsContext.getKeyStore());
    assertEquals(0, actualTlsContext.trust_count);
    assertFalse(actualTlsContext.isTrustAll());
    assertEquals(18, actualTlsContext.passwd.length);
  }

  @Test
  void testConstructor3() throws IOException, KeyStoreException, NoSuchAlgorithmException, CertificateException {
    // Arrange and Act
    TlsContext actualTlsContext = new TlsContext("AAAA".toCharArray());

    // Assert
    KeyStore expectedKeyStore = actualTlsContext.ks;
    assertSame(expectedKeyStore, actualTlsContext.getKeyStore());
    assertEquals(0, actualTlsContext.trust_count);
    assertFalse(actualTlsContext.isTrustAll());
    assertEquals(4, actualTlsContext.passwd.length);
  }

  @Test
  void testConstructor4() throws IOException, KeyStoreException, NoSuchAlgorithmException, CertificateException {
    // Arrange and Act
    TlsContext actualTlsContext = new TlsContext(null);

    // Assert
    KeyStore expectedKeyStore = actualTlsContext.ks;
    assertSame(expectedKeyStore, actualTlsContext.getKeyStore());
    assertEquals(0, actualTlsContext.trust_count);
    assertFalse(actualTlsContext.isTrustAll());
    assertEquals(18, actualTlsContext.passwd.length);
  }

  @Test
  void testAddTrustCert() throws IOException, KeyStoreException, NoSuchAlgorithmException, CertificateException {
    // Arrange
    TlsContext tlsContext = new TlsContext();

    // Act
    tlsContext.addTrustCert((Certificate) null);

    // Assert
    assertEquals(1, tlsContext.trust_count);
  }

  @Test
  void testSetTrustAll() throws IOException, KeyStoreException, NoSuchAlgorithmException, CertificateException {
    // Arrange
    TlsContext tlsContext = new TlsContext();

    // Act
    tlsContext.setTrustAll(true);

    // Assert
    assertTrue(tlsContext.isTrustAll());
  }

  @Test
  void testSetTrustAll2() throws IOException, KeyStoreException, NoSuchAlgorithmException, CertificateException {
    // Arrange
    TlsContext tlsContext = new TlsContext();

    // Act
    tlsContext.setTrustAll(false);

    // Assert
    assertFalse(tlsContext.isTrustAll());
  }
}

