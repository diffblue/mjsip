package org.mjsip.sip.address;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class SipNameAddressDiffblueTest {
  @Test
  void testIsSIPS() {
    // Arrange, Act and Assert
    assertFalse(SipNameAddress.isSIPS(new NameAddress("Str")));
    assertTrue(SipNameAddress.isSIPS(SipNameAddress.toSIPS(new NameAddress("Str"))));
  }

  @Test
  void testToSIPS() {
    // Arrange and Act
    NameAddress actualToSIPSResult = SipNameAddress.toSIPS(new NameAddress("Str"));

    // Assert
    assertEquals("<sips:Str>", actualToSIPSResult.toString());
    assertFalse(actualToSIPSResult.hasDisplayName());
    GenericURI address = actualToSIPSResult.getAddress();
    assertFalse(address.isTelURI());
    assertTrue(((SipURI) address).isSecure());
  }

  @Test
  void testToSIP() {
    // Arrange and Act
    NameAddress actualToSIPResult = SipNameAddress.toSIP(SipNameAddress.toSIPS(new NameAddress("Str")));

    // Assert
    assertEquals("<sips:Str>", actualToSIPResult.toString());
    assertFalse(actualToSIPResult.hasDisplayName());
    GenericURI address = actualToSIPResult.getAddress();
    assertFalse(address.isTelURI());
    assertFalse(((SipURI) address).isSecure());
  }
}

