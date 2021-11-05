package org.mjsip.server;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;
import org.junit.jupiter.api.Test;
import org.mjsip.sip.address.GenericURI;
import org.mjsip.sip.address.NameAddress;
import org.mjsip.sip.address.SipURI;

class UserBindingInfoDiffblueTest {
  @Test
  void testAddContact() {
    // Arrange
    UserBindingInfo userBindingInfo = new UserBindingInfo("Name");
    NameAddress contact = new NameAddress("Str");
    LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();

    // Act and Assert
    assertSame(userBindingInfo,
        userBindingInfo.addContact(contact, Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant())));
  }

  @Test
  void testAddContact2() {
    // Arrange
    UserBindingInfo userBindingInfo = new UserBindingInfo("Name");
    NameAddress contact = new NameAddress("Str");
    LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
    userBindingInfo.addContact(contact, Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
    NameAddress contact1 = new NameAddress("Str");
    LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();

    // Act and Assert
    assertSame(userBindingInfo,
        userBindingInfo.addContact(contact1, Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant())));
  }

  @Test
  void testAddContact3() {
    // Arrange
    UserBindingInfo userBindingInfo = new UserBindingInfo("Name");
    NameAddress contact = new NameAddress("Display name", new GenericURI("Uri"));

    LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();

    // Act and Assert
    assertSame(userBindingInfo,
        userBindingInfo.addContact(contact, Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant())));
  }

  @Test
  void testAddContact4() {
    // Arrange
    UserBindingInfo userBindingInfo = new UserBindingInfo("Name");
    NameAddress contact = new NameAddress("Str");
    LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();

    // Act and Assert
    assertSame(userBindingInfo, userBindingInfo.addContact(contact,
        Date.from(atStartOfDayResult.atZone(ZoneOffset.ofTotalSeconds(1)).toInstant())));
  }

  @Test
  void testAddContact5() {
    // Arrange
    UserBindingInfo userBindingInfo = new UserBindingInfo("Name");
    NameAddress contact = new NameAddress("Str");
    LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
    userBindingInfo.addContact(contact, Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));

    // Act and Assert
    assertSame(userBindingInfo, userBindingInfo.addContact(new NameAddress("Str"), null));
  }

  @Test
  void testConstructor() {
    // Arrange and Act
    UserBindingInfo actualUserBindingInfo = new UserBindingInfo("Name");

    // Assert
    assertEquals("Name", actualUserBindingInfo.getName());
    assertTrue(actualUserBindingInfo.contact_list.isEmpty());
  }

  @Test
  void testGetExpirationDate() {
    // Arrange, Act and Assert
    assertNull((new UserBindingInfo("Name")).getExpirationDate("Uri"));
  }

  @Test
  void testGetNameAddress() {
    // Arrange, Act and Assert
    assertNull((new UserBindingInfo("Name")).getNameAddress("Uri"));
  }

  @Test
  void testGetNameAddress2() {
    // Arrange
    UserBindingInfo userBindingInfo = new UserBindingInfo("Name");
    NameAddress contact = new NameAddress(new GenericURI("Uri"));
    LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
    userBindingInfo.addContact(contact, Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));

    // Act
    NameAddress actualNameAddress = userBindingInfo.getNameAddress("Uri");

    // Assert
    assertEquals("<sip:<Uri>>", actualNameAddress.toString());
    assertFalse(actualNameAddress.hasDisplayName());
    GenericURI address = actualNameAddress.getAddress();
    assertFalse(address.isTelURI());
    assertFalse(((SipURI) address).isSecure());
  }

  @Test
  void testGetNameAddress3() {
    // Arrange
    NameAddress nameAddress = new NameAddress(new GenericURI("Uri"));
    nameAddress.setDisplayName("sips:");

    UserBindingInfo userBindingInfo = new UserBindingInfo("Name");
    LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
    userBindingInfo.addContact(nameAddress, Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));

    // Act
    NameAddress actualNameAddress = userBindingInfo.getNameAddress("Uri");

    // Assert
    assertEquals("<sips:\">", actualNameAddress.toString());
    assertNull(actualNameAddress.getDisplayName());
    assertTrue(actualNameAddress.getAddress().isSipURI());
  }

  @Test
  void testHasContact() {
    // Arrange, Act and Assert
    assertFalse((new UserBindingInfo("Name")).hasContact("Uri"));
  }

  @Test
  void testHasContact2() {
    // Arrange
    UserBindingInfo userBindingInfo = new UserBindingInfo("Name");
    NameAddress contact = new NameAddress(new GenericURI("Uri"));
    LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
    userBindingInfo.addContact(contact, Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));

    // Act and Assert
    assertTrue(userBindingInfo.hasContact("Uri"));
  }

  @Test
  void testIsExpired() {
    // Arrange, Act and Assert
    assertTrue((new UserBindingInfo("Name")).isExpired("Uri"));
  }

  @Test
  void testIsExpired2() {
    // Arrange
    UserBindingInfo userBindingInfo = new UserBindingInfo("Name");
    NameAddress contact = new NameAddress(new GenericURI("Uri"));
    LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
    userBindingInfo.addContact(contact, Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));

    // Act and Assert
    assertTrue(userBindingInfo.isExpired("Uri"));
  }

  @Test
  void testIsExpired3() {
    // Arrange
    UserBindingInfo userBindingInfo = new UserBindingInfo("Name");
    NameAddress contact = new NameAddress(new GenericURI("Uri"));
    LocalDateTime atStartOfDayResult = LocalDate.of(0, 1, 1).atStartOfDay();
    userBindingInfo.addContact(contact, Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));

    // Act and Assert
    assertFalse(userBindingInfo.isExpired("Uri"));
  }

  @Test
  void testIsExpired4() {
    // Arrange
    UserBindingInfo userBindingInfo = new UserBindingInfo("Name");
    NameAddress contact = new NameAddress(new GenericURI("Uri"));
    LocalDateTime atStartOfDayResult = LocalDate.of(1970, 4, 1).atStartOfDay();
    userBindingInfo.addContact(contact, Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));

    // Act and Assert
    assertTrue(userBindingInfo.isExpired("Uri"));
  }

  @Test
  void testIsExpired5() {
    // Arrange
    NameAddress nameAddress = new NameAddress(new GenericURI("Uri"));
    nameAddress.setDisplayName("expires");

    UserBindingInfo userBindingInfo = new UserBindingInfo("Name");
    LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
    userBindingInfo.addContact(nameAddress, Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));

    // Act and Assert
    assertTrue(userBindingInfo.isExpired("Uri"));
  }

  @Test
  void testRemoveContact() {
    // Arrange
    UserBindingInfo userBindingInfo = new UserBindingInfo("Name");

    // Act and Assert
    assertSame(userBindingInfo, userBindingInfo.removeContact("Uri"));
  }

  @Test
  void testRemoveContact2() {
    // Arrange
    UserBindingInfo userBindingInfo = new UserBindingInfo("Name");
    NameAddress contact = new NameAddress(new GenericURI("Uri"));
    LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
    userBindingInfo.addContact(contact, Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));

    // Act and Assert
    assertSame(userBindingInfo, userBindingInfo.removeContact("Uri"));
  }

  @Test
  void testToString() {
    // Arrange, Act and Assert
    assertEquals("To: Name\r\n", (new UserBindingInfo("Name")).toString());
  }
}

