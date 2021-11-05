package org.mjsip.sip.address;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Objects;
import org.junit.jupiter.api.Test;

class NameAddressDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");

    // Act
    NameAddress actualNameAddress = new NameAddress("Display name", genericURI);
    GenericURI genericURI1 = new GenericURI("Uri");
    actualNameAddress.setAddress(genericURI1);
    actualNameAddress.setDisplayName("Display name");

    // Assert
    GenericURI address = actualNameAddress.getAddress();
    assertSame(genericURI1, address);
    assertEquals(genericURI, address);
    assertEquals("Display name", actualNameAddress.getDisplayName());
  }

  @Test
  void testConstructor2() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");

    // Act
    NameAddress actualNameAddress = new NameAddress(genericURI);
    GenericURI genericURI1 = new GenericURI("Uri");
    actualNameAddress.setAddress(genericURI1);
    actualNameAddress.setDisplayName("Display name");

    // Assert
    GenericURI address = actualNameAddress.getAddress();
    assertSame(genericURI1, address);
    assertEquals(genericURI, address);
    assertEquals("Display name", actualNameAddress.getDisplayName());
  }

  @Test
  void testConstructor3() {
    // Arrange and Act
    NameAddress actualNameAddress = new NameAddress("Str");

    // Assert
    assertEquals("<sip:Str>", actualNameAddress.toString());
    assertFalse(actualNameAddress.hasDisplayName());
    GenericURI address = actualNameAddress.getAddress();
    assertFalse(address.isTelURI());
    assertFalse(((SipURI) address).isSecure());
  }

  @Test
  void testConstructor4() {
    // Arrange and Act
    NameAddress actualNameAddress = new NameAddress("sips:");

    // Assert
    assertEquals("<sips:>", actualNameAddress.toString());
    assertNull(actualNameAddress.getDisplayName());
    assertTrue(actualNameAddress.getAddress().isSipURI());
  }

  @Test
  void testConstructor5() {
    // Arrange and Act
    NameAddress actualNameAddress = new NameAddress("");

    // Assert
    assertEquals("<sip:>", actualNameAddress.toString());
    assertFalse(actualNameAddress.hasDisplayName());
    GenericURI address = actualNameAddress.getAddress();
    assertFalse(address.isTelURI());
    assertFalse(((SipURI) address).isSecure());
  }

  @Test
  void testConstructor6() {
    // Arrange and Act
    NameAddress actualNameAddress = new NameAddress(new NameAddress("Str"));

    // Assert
    assertEquals("<sip:Str>", actualNameAddress.toString());
    assertFalse(actualNameAddress.hasDisplayName());
  }

  @Test
  void testConstructor7() {
    // Arrange and Act
    NameAddress actualNameAddress = new NameAddress(
        SipNameAddress.toSIPS(new NameAddress("org.mjsip.sip.address.NameAddress")));

    // Assert
    assertEquals("<sips:org.mjsip.sip.address.NameAddress>", actualNameAddress.toString());
    assertFalse(actualNameAddress.hasDisplayName());
  }

  @Test
  void testClone() {
    // Arrange and Act
    Object actualCloneResult = (new NameAddress("Str")).clone();

    // Assert
    assertEquals("<sip:Str>", actualCloneResult.toString());
    assertFalse(((NameAddress) actualCloneResult).hasDisplayName());
  }

  @Test
  void testEquals() {
    // Arrange, Act and Assert
    assertFalse((new NameAddress("Str")).equals(null));
    assertFalse((new NameAddress("Str")).equals("Different type to NameAddress"));
  }

  @Test
  void testEquals2() {
    // Arrange
    NameAddress nameAddress = new NameAddress("Str");

    // Act and Assert
    assertTrue(nameAddress.equals(nameAddress));
    int expectedHashCodeResult = nameAddress.hashCode();
    assertEquals(expectedHashCodeResult, nameAddress.hashCode());
  }

  @Test
  void testEquals3() {
    // Arrange
    NameAddress nameAddress = new NameAddress("Str");
    NameAddress nameAddress1 = new NameAddress("Str");

    // Act and Assert
    assertTrue(nameAddress.equals(nameAddress1));
    int notExpectedHashCodeResult = nameAddress.hashCode();
    assertFalse(Objects.equals(notExpectedHashCodeResult, nameAddress1.hashCode()));
  }

  @Test
  void testEquals4() {
    // Arrange
    NameAddress nameAddress = new NameAddress("org.mjsip.sip.address.NameAddress");

    // Act and Assert
    assertFalse(nameAddress.equals(new NameAddress("Str")));
  }

  @Test
  void testEquals5() {
    // Arrange
    NameAddress nameAddress = new NameAddress("Display name", new GenericURI("Uri"));

    // Act and Assert
    assertFalse(nameAddress.equals(new NameAddress("Str")));
  }

  @Test
  void testEquals6() {
    // Arrange
    NameAddress nameAddress = new NameAddress("Str");

    // Act and Assert
    assertFalse(nameAddress.equals(new NameAddress("Display name", new GenericURI("Uri"))));
  }

  @Test
  void testEquals7() {
    // Arrange
    NameAddress nameAddress = new NameAddress("Display name", new GenericURI("Uri"));

    NameAddress nameAddress1 = new NameAddress("Str");
    nameAddress1.setDisplayName("Display name");

    // Act and Assert
    assertFalse(nameAddress.equals(nameAddress1));
  }

  @Test
  void testEquals8() {
    // Arrange
    NameAddress nameAddress = new NameAddress("Display name", null);

    NameAddress nameAddress1 = new NameAddress("Str");
    nameAddress1.setDisplayName("Display name");

    // Act and Assert
    assertFalse(nameAddress.equals(nameAddress1));
  }

  @Test
  void testHasDisplayName() {
    // Arrange, Act and Assert
    assertFalse((new NameAddress("Str")).hasDisplayName());
    assertTrue((new NameAddress("Display name", new GenericURI("Uri"))).hasDisplayName());
  }

  @Test
  void testToString() {
    // Arrange, Act and Assert
    assertEquals("<sip:Str>", (new NameAddress("Str")).toString());
    assertEquals("\"Display name\" <Uri>", (new NameAddress("Display name", new GenericURI("Uri"))).toString());
    assertEquals("\"Display name\" <null>", (new NameAddress("Display name", null)).toString());
  }

  @Test
  void testToString2() {
    // Arrange
    NameAddress nameAddress = new NameAddress("Str");
    nameAddress.setAddress(null);

    // Act and Assert
    assertEquals("<null>", nameAddress.toString());
  }
}

