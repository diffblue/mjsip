package org.mjsip.sip.call;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import org.junit.jupiter.api.Test;
import org.mjsip.sip.address.NameAddress;

class SipUserDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    SipUser actualSipUser = new SipUser(new NameAddress("Str"));

    // Assert
    NameAddress expectedAddress = actualSipUser.user_naddr;
    assertSame(expectedAddress, actualSipUser.getAddress());
    assertNull(actualSipUser.getAuhPasswd());
    assertNull(actualSipUser.getAuhRealm());
    assertNull(actualSipUser.getAuhUserName());
    assertNull(actualSipUser.getContactAddress());
  }

  @Test
  void testConstructor2() {
    // Arrange and Act
    SipUser actualSipUser = new SipUser(new NameAddress("Str"));

    // Assert
    NameAddress expectedAddress = actualSipUser.user_naddr;
    assertSame(expectedAddress, actualSipUser.getAddress());
    assertNull(actualSipUser.getContactAddress());
    assertNull(actualSipUser.getAuhPasswd());
    assertNull(actualSipUser.getAuhUserName());
    assertNull(actualSipUser.getAuhRealm());
  }

  @Test
  void testConstructor3() {
    // Arrange and Act
    SipUser actualSipUser = new SipUser(new NameAddress("Str"), "janedoe", "Realm", "iloveyou");

    // Assert
    NameAddress expectedAddress = actualSipUser.user_naddr;
    assertSame(expectedAddress, actualSipUser.getAddress());
    assertNull(actualSipUser.getContactAddress());
    assertEquals("iloveyou", actualSipUser.getAuhPasswd());
    assertEquals("janedoe", actualSipUser.getAuhUserName());
    assertEquals("Realm", actualSipUser.getAuhRealm());
  }

  @Test
  void testConstructor4() {
    // Arrange
    NameAddress user_naddr = new NameAddress("Str");

    // Act
    SipUser actualSipUser = new SipUser(user_naddr, new NameAddress("Str"));

    // Assert
    NameAddress expectedAddress = actualSipUser.contact_naddr;
    NameAddress address = actualSipUser.getAddress();
    assertEquals(expectedAddress, address);
    assertEquals(address, actualSipUser.getContactAddress());
    assertNull(actualSipUser.getAuhPasswd());
    assertNull(actualSipUser.getAuhUserName());
    assertNull(actualSipUser.getAuhRealm());
  }

  @Test
  void testConstructor5() {
    // Arrange
    NameAddress user_naddr = new NameAddress("Str");

    // Act
    SipUser actualSipUser = new SipUser(user_naddr, new NameAddress("Str"), "janedoe", "Realm", "iloveyou");

    // Assert
    NameAddress expectedAddress = actualSipUser.contact_naddr;
    NameAddress address = actualSipUser.getAddress();
    assertEquals(expectedAddress, address);
    assertEquals(address, actualSipUser.getContactAddress());
    assertEquals("iloveyou", actualSipUser.getAuhPasswd());
    assertEquals("janedoe", actualSipUser.getAuhUserName());
    assertEquals("Realm", actualSipUser.getAuhRealm());
  }
}

