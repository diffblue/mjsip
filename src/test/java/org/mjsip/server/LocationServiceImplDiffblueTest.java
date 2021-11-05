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

class LocationServiceImplDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    LocationServiceImpl actualLocationServiceImpl = new LocationServiceImpl("File name");

    // Assert
    assertTrue(actualLocationServiceImpl.users.isEmpty());
    assertEquals("File name", actualLocationServiceImpl.file_name);
    assertFalse(actualLocationServiceImpl.changed);
  }

  @Test
  void testConstructor2() {
    // Arrange and Act
    LocationServiceImpl actualLocationServiceImpl = new LocationServiceImpl(null);

    // Assert
    assertTrue(actualLocationServiceImpl.users.isEmpty());
    assertNull(actualLocationServiceImpl.file_name);
    assertFalse(actualLocationServiceImpl.changed);
  }

  @Test
  void testSync() {
    // Arrange
    LocationServiceImpl locationServiceImpl = new LocationServiceImpl("File name");

    // Act
    locationServiceImpl.sync();

    // Assert that nothing has changed
    assertTrue(locationServiceImpl.users.isEmpty());
    assertEquals("File name", locationServiceImpl.file_name);
    assertFalse(locationServiceImpl.changed);
  }

  @Test
  void testSync2() {
    // Arrange
    LocationServiceImpl locationServiceImpl = new LocationServiceImpl(null);
    locationServiceImpl.addUser("User");

    // Act
    locationServiceImpl.sync();

    // Assert that nothing has changed
    assertEquals(1, locationServiceImpl.users.size());
    assertTrue(locationServiceImpl.changed);
  }

  @Test
  void testSize() {
    // Arrange, Act and Assert
    assertEquals(0, (new LocationServiceImpl("File name")).size());
  }

  @Test
  void testHasUser() {
    // Arrange, Act and Assert
    assertFalse((new LocationServiceImpl("File name")).hasUser("User"));
  }

  @Test
  void testHasUser2() {
    // Arrange
    LocationServiceImpl locationServiceImpl = new LocationServiceImpl("File name");
    locationServiceImpl.addUser("User");

    // Act and Assert
    assertTrue(locationServiceImpl.hasUser("User"));
  }

  @Test
  void testAddUser() {
    // Arrange
    LocationServiceImpl locationServiceImpl = new LocationServiceImpl("File name");

    // Act
    Repository actualAddUserResult = locationServiceImpl.addUser("User");

    // Assert
    assertSame(locationServiceImpl, actualAddUserResult);
    assertEquals(1, ((LocationServiceImpl) actualAddUserResult).users.size());
    assertTrue(((LocationServiceImpl) actualAddUserResult).changed);
    assertEquals("To: User\r\n", actualAddUserResult.toString());
  }

  @Test
  void testAddUser2() {
    // Arrange
    LocationServiceImpl locationServiceImpl = new LocationServiceImpl("File name");
    locationServiceImpl.addUser("User");

    // Act and Assert
    assertSame(locationServiceImpl, locationServiceImpl.addUser("User"));
  }

  @Test
  void testRemoveUser() {
    // Arrange
    LocationServiceImpl locationServiceImpl = new LocationServiceImpl("File name");

    // Act and Assert
    assertSame(locationServiceImpl, locationServiceImpl.removeUser("User"));
  }

  @Test
  void testRemoveUser2() {
    // Arrange
    LocationServiceImpl locationServiceImpl = new LocationServiceImpl("File name");
    locationServiceImpl.addUser("User");

    // Act and Assert
    assertSame(locationServiceImpl, locationServiceImpl.removeUser("User"));
    assertTrue(((LocationServiceImpl) locationServiceImpl.removeUser("User")).users.isEmpty());
    assertTrue(((LocationServiceImpl) locationServiceImpl.removeUser("User")).changed);
  }

  @Test
  void testRemoveAllUsers() {
    // Arrange
    LocationServiceImpl locationServiceImpl = new LocationServiceImpl("File name");

    // Act and Assert
    assertSame(locationServiceImpl, locationServiceImpl.removeAllUsers());
    assertTrue(((LocationServiceImpl) locationServiceImpl.removeAllUsers()).users.isEmpty());
    assertTrue(((LocationServiceImpl) locationServiceImpl.removeAllUsers()).changed);
  }

  @Test
  void testToString() {
    // Arrange, Act and Assert
    assertEquals("", (new LocationServiceImpl("File name")).toString());
  }

  @Test
  void testToString2() {
    // Arrange
    LocationServiceImpl locationServiceImpl = new LocationServiceImpl("File name");
    locationServiceImpl.addUser("");

    // Act and Assert
    assertEquals("To: \r\n", locationServiceImpl.toString());
  }

  @Test
  void testToString3() {
    // Arrange
    LocationServiceImpl locationServiceImpl = new LocationServiceImpl("File name");
    locationServiceImpl.addUserStaticContact("", new NameAddress("Str"));

    // Act and Assert
    assertEquals("To: \r\nContact: <sip:Str>;expires=\"NEVER\"\r\n", locationServiceImpl.toString());
  }

  @Test
  void testHasUserContact() {
    // Arrange, Act and Assert
    assertFalse((new LocationServiceImpl("File name")).hasUserContact("User", "Uri"));
  }

  @Test
  void testHasUserContact2() {
    // Arrange
    LocationServiceImpl locationServiceImpl = new LocationServiceImpl("File name");
    locationServiceImpl.addUser("User");

    // Act and Assert
    assertFalse(locationServiceImpl.hasUserContact("User", "Uri"));
  }

  @Test
  void testHasUserContact3() {
    // Arrange
    LocationServiceImpl locationServiceImpl = new LocationServiceImpl("File name");
    NameAddress name_addresss = new NameAddress(new GenericURI("Uri"));
    LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
    locationServiceImpl.addUserContact("User", name_addresss,
        Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));

    // Act and Assert
    assertTrue(locationServiceImpl.hasUserContact("User", "Uri"));
  }

  @Test
  void testAddUserContact() {
    // Arrange
    LocationServiceImpl locationServiceImpl = new LocationServiceImpl("File name");
    NameAddress name_addresss = new NameAddress("Str");
    LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();

    // Act and Assert
    assertSame(locationServiceImpl, locationServiceImpl.addUserContact("User", name_addresss,
        Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant())));
    assertEquals(1, ((LocationServiceImpl) locationServiceImpl.addUserContact("User", name_addresss,
        Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()))).users.size());
    assertTrue(((LocationServiceImpl) locationServiceImpl.addUserContact("User", name_addresss,
        Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()))).changed);
  }

  @Test
  void testAddUserContact2() {
    // Arrange
    LocationServiceImpl locationServiceImpl = new LocationServiceImpl("File name");
    locationServiceImpl.addUser("User");
    NameAddress name_addresss = new NameAddress("Str");
    LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();

    // Act and Assert
    assertSame(locationServiceImpl, locationServiceImpl.addUserContact("User", name_addresss,
        Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant())));
    assertTrue(((LocationServiceImpl) locationServiceImpl.addUserContact("User", name_addresss,
        Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()))).changed);
  }

  @Test
  void testAddUserContact3() {
    // Arrange
    LocationServiceImpl locationServiceImpl = new LocationServiceImpl("File name");
    NameAddress name_addresss = new NameAddress("Str");
    LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
    locationServiceImpl.addUserContact("User", name_addresss,
        Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
    NameAddress name_addresss1 = new NameAddress("Str");
    LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();

    // Act and Assert
    assertSame(locationServiceImpl, locationServiceImpl.addUserContact("User", name_addresss1,
        Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant())));
    assertTrue(((LocationServiceImpl) locationServiceImpl.addUserContact("User", name_addresss1,
        Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()))).changed);
  }

  @Test
  void testAddUserContact4() {
    // Arrange
    LocationServiceImpl locationServiceImpl = new LocationServiceImpl("File name");
    NameAddress name_addresss = new NameAddress("Display name", new GenericURI("Uri"));

    LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();

    // Act and Assert
    assertSame(locationServiceImpl, locationServiceImpl.addUserContact("User", name_addresss,
        Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant())));
    assertEquals(1, ((LocationServiceImpl) locationServiceImpl.addUserContact("User", name_addresss,
        Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()))).users.size());
    assertTrue(((LocationServiceImpl) locationServiceImpl.addUserContact("User", name_addresss,
        Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()))).changed);
  }

  @Test
  void testAddUserContact5() {
    // Arrange
    LocationServiceImpl locationServiceImpl = new LocationServiceImpl("File name");
    NameAddress name_addresss = new NameAddress("Str");
    LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();

    // Act and Assert
    assertSame(locationServiceImpl, locationServiceImpl.addUserContact("User", name_addresss,
        Date.from(atStartOfDayResult.atZone(ZoneOffset.ofTotalSeconds(1)).toInstant())));
    assertEquals(1, ((LocationServiceImpl) locationServiceImpl.addUserContact("User", name_addresss,
        Date.from(atStartOfDayResult.atZone(ZoneOffset.ofTotalSeconds(1)).toInstant()))).users.size());
    assertTrue(((LocationServiceImpl) locationServiceImpl.addUserContact("User", name_addresss,
        Date.from(atStartOfDayResult.atZone(ZoneOffset.ofTotalSeconds(1)).toInstant()))).changed);
  }

  @Test
  void testRemoveUserContact() {
    // Arrange
    LocationServiceImpl locationServiceImpl = new LocationServiceImpl("File name");

    // Act and Assert
    assertSame(locationServiceImpl, locationServiceImpl.removeUserContact("User", "Uri"));
  }

  @Test
  void testRemoveUserContact2() {
    // Arrange
    LocationServiceImpl locationServiceImpl = new LocationServiceImpl("File name");
    locationServiceImpl.addUser("User");

    // Act and Assert
    assertSame(locationServiceImpl, locationServiceImpl.removeUserContact("User", "Uri"));
    assertTrue(((LocationServiceImpl) locationServiceImpl.removeUserContact("User", "Uri")).changed);
  }

  @Test
  void testRemoveUserContact3() {
    // Arrange
    LocationServiceImpl locationServiceImpl = new LocationServiceImpl("File name");
    NameAddress name_addresss = new NameAddress(new GenericURI("Uri"));
    LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
    locationServiceImpl.addUserContact("User", name_addresss,
        Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));

    // Act
    LocationService actualRemoveUserContactResult = locationServiceImpl.removeUserContact("User", "Uri");

    // Assert
    assertSame(locationServiceImpl, actualRemoveUserContactResult);
    assertTrue(((LocationServiceImpl) actualRemoveUserContactResult).changed);
    assertEquals("To: User\r\n", actualRemoveUserContactResult.toString());
  }

  @Test
  void testGetUserContactURIs() {
    // Arrange, Act and Assert
    assertNull((new LocationServiceImpl("File name")).getUserContactURIs("User"));
  }

  @Test
  void testGetUserContactURIs2() {
    // Arrange
    LocationServiceImpl locationServiceImpl = new LocationServiceImpl("File name");
    locationServiceImpl.addUser("User");

    // Act
    locationServiceImpl.getUserContactURIs("User");

    // Assert
    assertTrue(locationServiceImpl.changed);
  }

  @Test
  void testGetUserContactNameAddress() {
    // Arrange, Act and Assert
    assertNull((new LocationServiceImpl("File name")).getUserContactNameAddress("User", "Uri"));
  }

  @Test
  void testGetUserContactNameAddress2() {
    // Arrange
    LocationServiceImpl locationServiceImpl = new LocationServiceImpl("File name");
    locationServiceImpl.addUser("User");

    // Act and Assert
    assertNull(locationServiceImpl.getUserContactNameAddress("User", "Uri"));
  }

  @Test
  void testGetUserContactNameAddress3() {
    // Arrange
    LocationServiceImpl locationServiceImpl = new LocationServiceImpl("File name");
    NameAddress name_addresss = new NameAddress(new GenericURI("Uri"));
    LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
    locationServiceImpl.addUserContact("User", name_addresss,
        Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));

    // Act
    NameAddress actualUserContactNameAddress = locationServiceImpl.getUserContactNameAddress("User", "Uri");

    // Assert
    assertEquals("<sip:<Uri>>", actualUserContactNameAddress.toString());
    assertFalse(actualUserContactNameAddress.hasDisplayName());
    GenericURI address = actualUserContactNameAddress.getAddress();
    assertFalse(address.isTelURI());
    assertFalse(((SipURI) address).isSecure());
  }

  @Test
  void testGetUserContactNameAddress4() {
    // Arrange
    NameAddress nameAddress = new NameAddress(new GenericURI("Uri"));
    nameAddress.setDisplayName("sips:");

    LocationServiceImpl locationServiceImpl = new LocationServiceImpl("File name");
    LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
    locationServiceImpl.addUserContact("User", nameAddress,
        Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));

    // Act
    NameAddress actualUserContactNameAddress = locationServiceImpl.getUserContactNameAddress("User", "Uri");

    // Assert
    assertEquals("<sips:\">", actualUserContactNameAddress.toString());
    assertNull(actualUserContactNameAddress.getDisplayName());
    assertTrue(actualUserContactNameAddress.getAddress().isSipURI());
  }

  @Test
  void testGetUserContactExpirationDate() {
    // Arrange, Act and Assert
    assertNull((new LocationServiceImpl("File name")).getUserContactExpirationDate("User", "Uri"));
  }

  @Test
  void testGetUserContactExpirationDate2() {
    // Arrange
    LocationServiceImpl locationServiceImpl = new LocationServiceImpl("File name");
    locationServiceImpl.addUser("User");

    // Act and Assert
    assertNull(locationServiceImpl.getUserContactExpirationDate("User", "Uri"));
  }

  @Test
  void testIsUserContactExpired() {
    // Arrange, Act and Assert
    assertTrue((new LocationServiceImpl("File name")).isUserContactExpired("User", "Uri"));
  }

  @Test
  void testIsUserContactExpired2() {
    // Arrange
    LocationServiceImpl locationServiceImpl = new LocationServiceImpl("File name");
    locationServiceImpl.addUser("User");

    // Act and Assert
    assertTrue(locationServiceImpl.isUserContactExpired("User", "Uri"));
  }

  @Test
  void testIsUserContactExpired3() {
    // Arrange
    LocationServiceImpl locationServiceImpl = new LocationServiceImpl("File name");
    NameAddress name_addresss = new NameAddress(new GenericURI("Uri"));
    LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
    locationServiceImpl.addUserContact("User", name_addresss,
        Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));

    // Act and Assert
    assertTrue(locationServiceImpl.isUserContactExpired("User", "Uri"));
  }

  @Test
  void testIsUserContactExpired4() {
    // Arrange
    LocationServiceImpl locationServiceImpl = new LocationServiceImpl("File name");
    NameAddress name_addresss = new NameAddress(new GenericURI("Uri"));
    LocalDateTime atStartOfDayResult = LocalDate.of(0, 1, 1).atStartOfDay();
    locationServiceImpl.addUserContact("User", name_addresss,
        Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));

    // Act and Assert
    assertFalse(locationServiceImpl.isUserContactExpired("User", "Uri"));
  }

  @Test
  void testIsUserContactExpired5() {
    // Arrange
    LocationServiceImpl locationServiceImpl = new LocationServiceImpl("File name");
    NameAddress name_addresss = new NameAddress(new GenericURI("Uri"));
    LocalDateTime atStartOfDayResult = LocalDate.of(1970, 5, 1).atStartOfDay();
    locationServiceImpl.addUserContact("User", name_addresss,
        Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));

    // Act and Assert
    assertTrue(locationServiceImpl.isUserContactExpired("User", "Uri"));
  }

  @Test
  void testIsUserContactExpired6() {
    // Arrange
    NameAddress nameAddress = new NameAddress(new GenericURI("Uri"));
    nameAddress.setDisplayName("expires");

    LocationServiceImpl locationServiceImpl = new LocationServiceImpl("File name");
    LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
    locationServiceImpl.addUserContact("User", nameAddress,
        Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));

    // Act and Assert
    assertTrue(locationServiceImpl.isUserContactExpired("User", "Uri"));
  }

  @Test
  void testAddUserStaticContact() {
    // Arrange
    LocationServiceImpl locationServiceImpl = new LocationServiceImpl("File name");

    // Act
    LocationService actualAddUserStaticContactResult = locationServiceImpl.addUserStaticContact("User",
        new NameAddress("Str"));

    // Assert
    assertSame(locationServiceImpl, actualAddUserStaticContactResult);
    assertEquals(1, ((LocationServiceImpl) actualAddUserStaticContactResult).users.size());
    assertTrue(((LocationServiceImpl) actualAddUserStaticContactResult).changed);
    assertEquals("To: User\r\nContact: <sip:Str>;expires=\"NEVER\"\r\n", actualAddUserStaticContactResult.toString());
  }

  @Test
  void testAddUserStaticContact2() {
    // Arrange
    LocationServiceImpl locationServiceImpl = new LocationServiceImpl("File name");
    locationServiceImpl.addUser("User");

    // Act
    LocationService actualAddUserStaticContactResult = locationServiceImpl.addUserStaticContact("User",
        new NameAddress("Str"));

    // Assert
    assertSame(locationServiceImpl, actualAddUserStaticContactResult);
    assertTrue(((LocationServiceImpl) actualAddUserStaticContactResult).changed);
    assertEquals("To: User\r\nContact: <sip:Str>;expires=\"NEVER\"\r\n", actualAddUserStaticContactResult.toString());
  }

  @Test
  void testAddUserStaticContact3() {
    // Arrange
    LocationServiceImpl locationServiceImpl = new LocationServiceImpl("File name");
    NameAddress name_addresss = new NameAddress("Str");
    LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
    locationServiceImpl.addUserContact("User", name_addresss,
        Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));

    // Act and Assert
    assertSame(locationServiceImpl, locationServiceImpl.addUserStaticContact("User", new NameAddress("Str")));
    assertTrue(
        ((LocationServiceImpl) locationServiceImpl.addUserStaticContact("User", new NameAddress("Str"))).changed);
  }

  @Test
  void testAddUserStaticContact4() {
    // Arrange
    LocationServiceImpl locationServiceImpl = new LocationServiceImpl("File name");

    // Act
    LocationService actualAddUserStaticContactResult = locationServiceImpl.addUserStaticContact("User",
        new NameAddress("Display name", new GenericURI("Uri")));

    // Assert
    assertSame(locationServiceImpl, actualAddUserStaticContactResult);
    assertEquals(1, ((LocationServiceImpl) actualAddUserStaticContactResult).users.size());
    assertTrue(((LocationServiceImpl) actualAddUserStaticContactResult).changed);
    assertEquals("To: User\r\nContact: \"Display name\" <Uri>;expires=\"NEVER\"\r\n",
        actualAddUserStaticContactResult.toString());
  }
}

