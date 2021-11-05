package org.zoolu.net;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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

class TlsServerFactoryDiffblueTest {
  @Test
  void testConstructor() throws IOException, KeyManagementException, KeyStoreException, NoSuchAlgorithmException,
      UnrecoverableKeyException, CertificateException {
    // Arrange and Act
    TlsServerFactory actualTlsServerFactory = new TlsServerFactory(new TlsContext());
    actualTlsServerFactory.setEnabledProtocols(new String[]{"Enabled protocols"});
    actualTlsServerFactory.setNeedClientAuth(true);
    actualTlsServerFactory.setUseClientMode(true);

    // Assert
    assertTrue(actualTlsServerFactory.getNeedClientAuth());
    assertTrue(actualTlsServerFactory.getUseClientMode());
  }

  @Test
  void testGetEnabledProtocols2() throws IOException, KeyManagementException, KeyStoreException,
      NoSuchAlgorithmException, UnrecoverableKeyException, CertificateException {
    // Arrange
    TlsServerFactory tlsServerFactory = new TlsServerFactory(new TlsContext());
    tlsServerFactory.setEnabledProtocols(new String[]{"Enabled protocols"});

    // Act
    String[] actualEnabledProtocols = tlsServerFactory.getEnabledProtocols();

    // Assert
    assertSame(tlsServerFactory.enabled_protocols, actualEnabledProtocols);
    assertEquals(1, actualEnabledProtocols.length);
    assertEquals("Enabled protocols", actualEnabledProtocols[0]);
  }
}

