package org.mjsip.sip.header;

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
import java.util.Vector;
import org.junit.jupiter.api.Test;
import org.mjsip.sip.address.GenericURI;
import org.mjsip.sip.address.NameAddress;
import org.mjsip.sip.address.SipURI;

class ContactHeaderDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");

    // Act
    ContactHeader actualContactHeader = new ContactHeader(genericURI);

    // Assert
    assertEquals(-1, actualContactHeader.getExpires());
    assertEquals("Contact: Uri\r\n", actualContactHeader.toString());
    assertFalse(actualContactHeader.isExpired());
    assertFalse(actualContactHeader.hasTag());
    assertFalse(actualContactHeader.hasExpires());
    assertEquals("Uri", actualContactHeader.getValue());
    assertNull(actualContactHeader.getTag());
    assertNull(actualContactHeader.getParameters());
    Vector parameterNames = actualContactHeader.getParameterNames();
    assertTrue(parameterNames.isEmpty());
    assertEquals(CoreSipHeaders.Contact, actualContactHeader.getName());
    assertNull(actualContactHeader.getExpiresDate());
    NameAddress nameAddress = actualContactHeader.getNameAddress();
    assertFalse(nameAddress.hasDisplayName());
    assertNull(nameAddress.getDisplayName());
    GenericURI address = nameAddress.getAddress();
    assertTrue(address instanceof SipURI);
    assertEquals("<sip:Uri>", nameAddress.toString());
    assertEquals("Uri", ((SipURI) address).getHost());
    assertEquals("sip:Uri", address.toString());
    assertFalse(address.isTelURI());
    assertTrue(address.isSipURI());
    assertFalse(((SipURI) address).isSecure());
    assertFalse(((SipURI) address).hasUserName());
    assertFalse(((SipURI) address).hasTtl());
    assertFalse(((SipURI) address).hasTransport());
    assertFalse(((SipURI) address).hasPort());
    assertFalse(((SipURI) address).hasMaddr());
    assertFalse(address.hasLr());
    assertNull(((SipURI) address).getUserName());
    assertEquals(1, ((SipURI) address).getTtl());
    assertNull(((SipURI) address).getTransport());
    assertEquals("Uri", address.getSpecificPart());
    assertEquals("sip", address.getScheme());
    assertEquals(-1, ((SipURI) address).getPort());
    Vector parameterNames1 = address.getParameterNames();
    assertEquals(parameterNames, parameterNames1);
    assertTrue(parameterNames1.isEmpty());
    assertNull(((SipURI) address).getMaddr());
    Vector parameterNames2 = genericURI.getParameterNames();
    assertEquals(parameterNames, parameterNames2);
    assertEquals(parameterNames1, parameterNames2);
    assertTrue(parameterNames2.isEmpty());
    assertEquals("Uri", genericURI.toString());
    assertFalse(genericURI.hasLr());
    assertEquals("Uri", genericURI.getSpecificPart());
  }

  @Test
  void testConstructor2() {
    // Arrange
    NameAddress nameAddress = new NameAddress("Str");

    // Act
    ContactHeader actualContactHeader = new ContactHeader(nameAddress);

    // Assert
    assertEquals(-1, actualContactHeader.getExpires());
    assertEquals("Contact: <sip:Str>\r\n", actualContactHeader.toString());
    assertFalse(actualContactHeader.isExpired());
    assertFalse(actualContactHeader.hasTag());
    assertFalse(actualContactHeader.hasExpires());
    assertEquals("<sip:Str>", actualContactHeader.getValue());
    assertNull(actualContactHeader.getTag());
    assertNull(actualContactHeader.getParameters());
    Vector parameterNames = actualContactHeader.getParameterNames();
    assertTrue(parameterNames.isEmpty());
    NameAddress nameAddress1 = actualContactHeader.getNameAddress();
    assertEquals(nameAddress, nameAddress1);
    assertEquals(CoreSipHeaders.Contact, actualContactHeader.getName());
    assertNull(actualContactHeader.getExpiresDate());
    assertFalse(nameAddress1.hasDisplayName());
    assertNull(nameAddress1.getDisplayName());
    assertEquals("<sip:Str>", nameAddress1.toString());
    GenericURI address = nameAddress1.getAddress();
    assertEquals("sip:Str", address.toString());
    assertFalse(address.isTelURI());
    assertTrue(address.isSipURI());
    assertFalse(address.hasLr());
    assertEquals("Str", address.getSpecificPart());
    assertEquals("sip", address.getScheme());
    Vector parameterNames1 = address.getParameterNames();
    assertEquals(parameterNames, parameterNames1);
    assertTrue(parameterNames1.isEmpty());
    assertEquals(nameAddress1, nameAddress);
    GenericURI address1 = nameAddress.getAddress();
    assertTrue(address1 instanceof SipURI);
    assertEquals("<sip:Str>", nameAddress.toString());
    assertFalse(nameAddress.hasDisplayName());
    assertNull(nameAddress.getDisplayName());
    assertFalse(address1.isTelURI());
    assertTrue(address1.isSipURI());
    assertFalse(((SipURI) address1).isSecure());
    assertFalse(((SipURI) address1).hasUserName());
    assertFalse(((SipURI) address1).hasTtl());
    assertFalse(((SipURI) address1).hasTransport());
    assertFalse(((SipURI) address1).hasPort());
    assertFalse(((SipURI) address1).hasMaddr());
    assertFalse(address1.hasLr());
    assertNull(((SipURI) address1).getUserName());
    assertEquals(1, ((SipURI) address1).getTtl());
    assertNull(((SipURI) address1).getTransport());
    assertEquals("Str", address1.getSpecificPart());
    assertEquals("sip", address1.getScheme());
    assertEquals(-1, ((SipURI) address1).getPort());
    Vector parameterNames2 = address1.getParameterNames();
    assertEquals(parameterNames, parameterNames2);
    assertEquals(parameterNames1, parameterNames2);
    assertTrue(parameterNames2.isEmpty());
    assertNull(((SipURI) address1).getMaddr());
    assertEquals("Str", ((SipURI) address1).getHost());
    assertEquals("sip:Str", address1.toString());
  }

  @Test
  void testConstructor3() {
    // Arrange
    Header header = new Header("Hname", "42");

    // Act
    ContactHeader actualContactHeader = new ContactHeader(header);

    // Assert
    assertEquals(-1, actualContactHeader.getExpires());
    assertEquals("Hname: 42\r\n", actualContactHeader.toString());
    assertFalse(actualContactHeader.isExpired());
    assertFalse(actualContactHeader.hasTag());
    assertFalse(actualContactHeader.hasExpires());
    assertEquals("42", actualContactHeader.getValue());
    assertNull(actualContactHeader.getTag());
    assertNull(actualContactHeader.getParameters());
    Vector parameterNames = actualContactHeader.getParameterNames();
    assertTrue(parameterNames.isEmpty());
    assertEquals("Hname", actualContactHeader.getName());
    assertNull(actualContactHeader.getExpiresDate());
    NameAddress nameAddress = actualContactHeader.getNameAddress();
    assertFalse(nameAddress.hasDisplayName());
    assertNull(nameAddress.getDisplayName());
    GenericURI address = nameAddress.getAddress();
    assertTrue(address instanceof SipURI);
    assertEquals("<sip:42>", nameAddress.toString());
    assertEquals("42", ((SipURI) address).getHost());
    assertEquals("sip:42", address.toString());
    assertFalse(address.isTelURI());
    assertTrue(address.isSipURI());
    assertFalse(((SipURI) address).isSecure());
    assertFalse(((SipURI) address).hasUserName());
    assertFalse(((SipURI) address).hasTtl());
    assertFalse(((SipURI) address).hasTransport());
    assertFalse(((SipURI) address).hasPort());
    assertFalse(((SipURI) address).hasMaddr());
    assertFalse(address.hasLr());
    assertNull(((SipURI) address).getUserName());
    assertEquals(1, ((SipURI) address).getTtl());
    assertNull(((SipURI) address).getTransport());
    assertEquals("42", address.getSpecificPart());
    assertEquals("sip", address.getScheme());
    assertEquals(-1, ((SipURI) address).getPort());
    Vector parameterNames1 = address.getParameterNames();
    assertEquals(parameterNames, parameterNames1);
    assertTrue(parameterNames1.isEmpty());
    assertNull(((SipURI) address).getMaddr());
    assertEquals("Hname", header.getName());
    assertEquals("Hname: 42\r\n", header.toString());
    assertEquals("42", header.getValue());
  }

  @Test
  void testConstructor4() {
    // Arrange and Act
    ContactHeader actualContactHeader = new ContactHeader();

    // Assert
    assertEquals("*", actualContactHeader.getValue());
    assertEquals(CoreSipHeaders.Contact, actualContactHeader.getName());
  }

  @Test
  void testSetExpires() {
    // Arrange
    ContactHeader contactHeader = new ContactHeader();

    // Act
    ContactHeader actualSetExpiresResult = contactHeader.setExpires(1);

    // Assert
    assertSame(contactHeader, actualSetExpiresResult);
    assertEquals("*;expires=1", actualSetExpiresResult.getValue());
  }

  @Test
  void testSetExpires2() {
    // Arrange
    ContactHeader contactHeader = new ContactHeader(new NameAddress("Str"));

    // Act
    ContactHeader actualSetExpiresResult = contactHeader.setExpires(1);

    // Assert
    assertSame(contactHeader, actualSetExpiresResult);
    assertEquals("<sip:Str>;expires=1", actualSetExpiresResult.getValue());
  }

  @Test
  void testSetExpires3() {
    // Arrange
    ContactHeader contactHeader = new ContactHeader(new NameAddress("Display name", new GenericURI("Uri")));

    // Act
    ContactHeader actualSetExpiresResult = contactHeader.setExpires(1);

    // Assert
    assertSame(contactHeader, actualSetExpiresResult);
    assertEquals("\"Display name\" <Uri>;expires=1", actualSetExpiresResult.getValue());
  }

  @Test
  void testSetExpires4() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("Name");
    ContactHeader contactHeader = new ContactHeader(genericURI);

    // Act
    ContactHeader actualSetExpiresResult = contactHeader.setExpires(1);

    // Assert
    assertSame(contactHeader, actualSetExpiresResult);
    assertEquals("Uri;Name;expires=1", actualSetExpiresResult.getValue());
  }

  @Test
  void testSetExpires5() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("expires");
    genericURI.addParameter("Name");
    ContactHeader contactHeader = new ContactHeader(genericURI);

    // Act
    ContactHeader actualSetExpiresResult = contactHeader.setExpires(1);

    // Assert
    assertSame(contactHeader, actualSetExpiresResult);
    assertEquals("Uri;Name;expires=1", actualSetExpiresResult.getValue());
  }

  @Test
  void testSetExpires6() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addLr();
    genericURI.addParameter("Name");
    ContactHeader contactHeader = new ContactHeader(genericURI);

    // Act
    ContactHeader actualSetExpiresResult = contactHeader.setExpires(1);

    // Assert
    assertSame(contactHeader, actualSetExpiresResult);
    assertEquals("Uri;lr;Name;expires=1", actualSetExpiresResult.getValue());
  }

  @Test
  void testSetExpires7() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("expires");
    ContactHeader contactHeader = new ContactHeader(genericURI);

    // Act
    ContactHeader actualSetExpiresResult = contactHeader.setExpires(1);

    // Assert
    assertSame(contactHeader, actualSetExpiresResult);
    assertEquals("Uri;expires=1", actualSetExpiresResult.getValue());
  }

  @Test
  void testSetExpires8() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("");
    ContactHeader contactHeader = new ContactHeader(genericURI);

    // Act
    ContactHeader actualSetExpiresResult = contactHeader.setExpires(1);

    // Assert
    assertSame(contactHeader, actualSetExpiresResult);
    assertEquals("Uri;;expires=1", actualSetExpiresResult.getValue());
  }

  @Test
  void testSetExpires9() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addLr();
    genericURI.addParameter("expires");
    genericURI.addParameter("Name");
    ContactHeader contactHeader = new ContactHeader(genericURI);

    // Act
    ContactHeader actualSetExpiresResult = contactHeader.setExpires(1);

    // Assert
    assertSame(contactHeader, actualSetExpiresResult);
    assertEquals("Uri;lr;Name;expires=1", actualSetExpiresResult.getValue());
  }

  @Test
  void testSetExpires10() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("");
    genericURI.addParameter("Name");
    ContactHeader contactHeader = new ContactHeader(genericURI);

    // Act
    ContactHeader actualSetExpiresResult = contactHeader.setExpires(1);

    // Assert
    assertSame(contactHeader, actualSetExpiresResult);
    assertEquals("Uri;;Name;expires=1", actualSetExpiresResult.getValue());
  }

  @Test
  void testSetExpires11() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("");
    genericURI.addParameter("");
    ContactHeader contactHeader = new ContactHeader(genericURI);

    // Act
    ContactHeader actualSetExpiresResult = contactHeader.setExpires(1);

    // Assert
    assertSame(contactHeader, actualSetExpiresResult);
    assertEquals("Uri;;;expires=1", actualSetExpiresResult.getValue());
  }

  @Test
  void testSetExpires12() {
    // Arrange
    ContactHeader contactHeader = new ContactHeader(new Header());

    // Act
    ContactHeader actualSetExpiresResult = contactHeader.setExpires(1);

    // Assert
    assertSame(contactHeader, actualSetExpiresResult);
    assertEquals(";expires=1", actualSetExpiresResult.getValue());
  }

  @Test
  void testSetExpires13() {
    // Arrange
    ContactHeader contactHeader = new ContactHeader();
    LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();

    // Act
    ContactHeader actualSetExpiresResult = contactHeader
        .setExpires(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));

    // Assert
    assertSame(contactHeader, actualSetExpiresResult);
    assertFalse(actualSetExpiresResult.hasTag());
  }

  @Test
  void testSetExpires14() {
    // Arrange
    ContactHeader contactHeader = new ContactHeader(new NameAddress("Str"));
    LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();

    // Act
    ContactHeader actualSetExpiresResult = contactHeader
        .setExpires(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));

    // Assert
    assertSame(contactHeader, actualSetExpiresResult);
    assertFalse(actualSetExpiresResult.hasTag());
  }

  @Test
  void testSetExpires15() {
    // Arrange
    ContactHeader contactHeader = new ContactHeader();
    LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();

    // Act
    ContactHeader actualSetExpiresResult = contactHeader
        .setExpires(Date.from(atStartOfDayResult.atZone(ZoneOffset.ofTotalSeconds(1)).toInstant()));

    // Assert
    assertSame(contactHeader, actualSetExpiresResult);
    assertFalse(actualSetExpiresResult.hasTag());
  }

  @Test
  void testSetExpires16() {
    // Arrange
    ContactHeader contactHeader = new ContactHeader(new NameAddress("Display name", new GenericURI("Uri")));
    LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();

    // Act
    ContactHeader actualSetExpiresResult = contactHeader
        .setExpires(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));

    // Assert
    assertSame(contactHeader, actualSetExpiresResult);
    assertFalse(actualSetExpiresResult.hasTag());
  }

  @Test
  void testSetExpires17() {
    // Arrange
    ContactHeader contactHeader = new ContactHeader();
    LocalDateTime atStartOfDayResult = LocalDate.of(0, 1, 1).atStartOfDay();

    // Act
    ContactHeader actualSetExpiresResult = contactHeader
        .setExpires(Date.from(atStartOfDayResult.atZone(ZoneOffset.ofTotalSeconds(1)).toInstant()));

    // Assert
    assertSame(contactHeader, actualSetExpiresResult);
    assertFalse(actualSetExpiresResult.hasTag());
  }

  @Test
  void testSetExpires18() {
    // Arrange
    ContactHeader contactHeader = new ContactHeader(new NameAddress("Display name", new GenericURI("Uri")));
    LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 20).atStartOfDay();

    // Act
    ContactHeader actualSetExpiresResult = contactHeader
        .setExpires(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));

    // Assert
    assertSame(contactHeader, actualSetExpiresResult);
    assertFalse(actualSetExpiresResult.hasTag());
  }

  @Test
  void testSetExpires19() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("Name");
    ContactHeader contactHeader = new ContactHeader(genericURI);
    LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();

    // Act
    ContactHeader actualSetExpiresResult = contactHeader
        .setExpires(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));

    // Assert
    assertSame(contactHeader, actualSetExpiresResult);
    assertFalse(actualSetExpiresResult.hasTag());
  }

  @Test
  void testSetExpires20() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("expires");
    genericURI.addParameter("Name");
    ContactHeader contactHeader = new ContactHeader(genericURI);
    LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();

    // Act
    ContactHeader actualSetExpiresResult = contactHeader
        .setExpires(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));

    // Assert
    assertSame(contactHeader, actualSetExpiresResult);
    assertFalse(actualSetExpiresResult.hasTag());
  }

  @Test
  void testSetExpires21() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addLr();
    genericURI.addParameter("Name");
    ContactHeader contactHeader = new ContactHeader(genericURI);
    LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();

    // Act
    ContactHeader actualSetExpiresResult = contactHeader
        .setExpires(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));

    // Assert
    assertSame(contactHeader, actualSetExpiresResult);
    assertFalse(actualSetExpiresResult.hasTag());
  }

  @Test
  void testSetExpires22() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("expires");
    ContactHeader contactHeader = new ContactHeader(genericURI);
    LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();

    // Act
    ContactHeader actualSetExpiresResult = contactHeader
        .setExpires(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));

    // Assert
    assertSame(contactHeader, actualSetExpiresResult);
    assertFalse(actualSetExpiresResult.hasTag());
  }

  @Test
  void testSetExpires23() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("");
    ContactHeader contactHeader = new ContactHeader(genericURI);
    LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();

    // Act
    ContactHeader actualSetExpiresResult = contactHeader
        .setExpires(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));

    // Assert
    assertSame(contactHeader, actualSetExpiresResult);
    assertFalse(actualSetExpiresResult.hasTag());
  }

  @Test
  void testSetExpires24() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addLr();
    genericURI.addParameter("expires");
    genericURI.addParameter("Name");
    ContactHeader contactHeader = new ContactHeader(genericURI);
    LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();

    // Act
    ContactHeader actualSetExpiresResult = contactHeader
        .setExpires(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));

    // Assert
    assertSame(contactHeader, actualSetExpiresResult);
    assertFalse(actualSetExpiresResult.hasTag());
  }

  @Test
  void testSetExpires25() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("");
    genericURI.addParameter("Name");
    ContactHeader contactHeader = new ContactHeader(genericURI);
    LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();

    // Act
    ContactHeader actualSetExpiresResult = contactHeader
        .setExpires(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));

    // Assert
    assertSame(contactHeader, actualSetExpiresResult);
    assertFalse(actualSetExpiresResult.hasTag());
  }

  @Test
  void testIsStar() {
    // Arrange, Act and Assert
    assertTrue((new ContactHeader()).isStar());
    assertFalse((new ContactHeader(new NameAddress("Str"))).isStar());
  }

  @Test
  void testIsStar2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertFalse((new ContactHeader(header)).isStar());
  }

  @Test
  void testHasExpires() {
    // Arrange, Act and Assert
    assertFalse((new ContactHeader()).hasExpires());
    assertFalse((new ContactHeader(new NameAddress("Str"))).hasExpires());
    assertFalse((new ContactHeader(new NameAddress("Display name", new GenericURI("Uri")))).hasExpires());
  }

  @Test
  void testHasExpires2() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("Name");

    // Act and Assert
    assertFalse((new ContactHeader(genericURI)).hasExpires());
  }

  @Test
  void testHasExpires3() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("expires");
    genericURI.addParameter("Name");

    // Act and Assert
    assertTrue((new ContactHeader(genericURI)).hasExpires());
  }

  @Test
  void testHasExpires4() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addLr();
    genericURI.addParameter("Name");

    // Act and Assert
    assertFalse((new ContactHeader(genericURI)).hasExpires());
  }

  @Test
  void testHasExpires5() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("");

    // Act and Assert
    assertFalse((new ContactHeader(genericURI)).hasExpires());
  }

  @Test
  void testHasExpires6() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("");
    genericURI.addParameter("Name");

    // Act and Assert
    assertFalse((new ContactHeader(genericURI)).hasExpires());
  }

  @Test
  void testHasExpires7() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("");
    genericURI.addParameter("");

    // Act and Assert
    assertFalse((new ContactHeader(genericURI)).hasExpires());
  }

  @Test
  void testHasExpires8() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertFalse((new ContactHeader(header)).hasExpires());
  }

  @Test
  void testIsExpired() {
    // Arrange, Act and Assert
    assertFalse((new ContactHeader()).isExpired());
    assertFalse((new ContactHeader(new NameAddress("Str"))).isExpired());
    assertFalse((new ContactHeader(new NameAddress("Display name", new GenericURI("Uri")))).isExpired());
  }

  @Test
  void testIsExpired2() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("Name");

    // Act and Assert
    assertFalse((new ContactHeader(genericURI)).isExpired());
  }

  @Test
  void testIsExpired3() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("expires");
    genericURI.addParameter("Name");

    // Act and Assert
    assertFalse((new ContactHeader(genericURI)).isExpired());
  }

  @Test
  void testIsExpired4() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addLr();
    genericURI.addParameter("Name");

    // Act and Assert
    assertFalse((new ContactHeader(genericURI)).isExpired());
  }

  @Test
  void testIsExpired5() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("expires", "42");
    genericURI.addParameter("Name");

    // Act and Assert
    assertFalse((new ContactHeader(genericURI)).isExpired());
  }

  @Test
  void testIsExpired6() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter(null);

    // Act and Assert
    assertFalse((new ContactHeader(genericURI)).isExpired());
  }

  @Test
  void testIsExpired7() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("");
    genericURI.addParameter("Name");

    // Act and Assert
    assertFalse((new ContactHeader(genericURI)).isExpired());
  }

  @Test
  void testIsExpired8() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("expires", null);
    genericURI.addParameter("Name");

    // Act and Assert
    assertFalse((new ContactHeader(genericURI)).isExpired());
  }

  @Test
  void testIsExpired9() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("expires", "");
    genericURI.addParameter("42");

    // Act and Assert
    assertFalse((new ContactHeader(genericURI)).isExpired());
  }

  @Test
  void testIsExpired10() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("", "");
    genericURI.addParameter("");

    // Act and Assert
    assertFalse((new ContactHeader(genericURI)).isExpired());
  }

  @Test
  void testGetExpires() {
    // Arrange, Act and Assert
    assertEquals(-1, (new ContactHeader()).getExpires());
    assertEquals(-1, (new ContactHeader(new NameAddress("Str"))).getExpires());
    assertEquals(-1, (new ContactHeader(new NameAddress("Display name", new GenericURI("Uri")))).getExpires());
  }

  @Test
  void testGetExpires2() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("Name");

    // Act and Assert
    assertEquals(-1, (new ContactHeader(genericURI)).getExpires());
  }

  @Test
  void testGetExpires3() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("expires");
    genericURI.addParameter("Name");

    // Act and Assert
    assertEquals(-1, (new ContactHeader(genericURI)).getExpires());
  }

  @Test
  void testGetExpires4() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addLr();
    genericURI.addParameter("Name");

    // Act and Assert
    assertEquals(-1, (new ContactHeader(genericURI)).getExpires());
  }

  @Test
  void testGetExpires5() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("expires", "42");
    genericURI.addParameter("Name");

    // Act and Assert
    assertEquals(42, (new ContactHeader(genericURI)).getExpires());
  }

  @Test
  void testGetExpires6() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter(null);

    // Act and Assert
    assertEquals(-1, (new ContactHeader(genericURI)).getExpires());
  }

  @Test
  void testGetExpires7() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("");
    genericURI.addParameter("Name");

    // Act and Assert
    assertEquals(-1, (new ContactHeader(genericURI)).getExpires());
  }

  @Test
  void testGetExpires8() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("expires", null);
    genericURI.addParameter("Name");

    // Act and Assert
    assertEquals(-1, (new ContactHeader(genericURI)).getExpires());
  }

  @Test
  void testGetExpires9() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("expires", "");
    genericURI.addParameter("42");

    // Act and Assert
    assertEquals(42, (new ContactHeader(genericURI)).getExpires());
  }

  @Test
  void testGetExpires10() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("", "");
    genericURI.addParameter("");

    // Act and Assert
    assertEquals(-1, (new ContactHeader(genericURI)).getExpires());
  }

  @Test
  void testGetExpiresDate() {
    // Arrange, Act and Assert
    assertNull((new ContactHeader()).getExpiresDate());
    assertNull((new ContactHeader(new NameAddress("Str"))).getExpiresDate());
    assertNull((new ContactHeader(new NameAddress("Display name", new GenericURI("Uri")))).getExpiresDate());
  }

  @Test
  void testGetExpiresDate2() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("Name");

    // Act and Assert
    assertNull((new ContactHeader(genericURI)).getExpiresDate());
  }

  @Test
  void testGetExpiresDate3() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("expires");
    genericURI.addParameter("Name");

    // Act and Assert
    assertNull((new ContactHeader(genericURI)).getExpiresDate());
  }

  @Test
  void testGetExpiresDate4() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addLr();
    genericURI.addParameter("Name");

    // Act and Assert
    assertNull((new ContactHeader(genericURI)).getExpiresDate());
  }

  @Test
  void testGetExpiresDate5() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter(null);

    // Act and Assert
    assertNull((new ContactHeader(genericURI)).getExpiresDate());
  }

  @Test
  void testGetExpiresDate6() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("");
    genericURI.addParameter("Name");

    // Act and Assert
    assertNull((new ContactHeader(genericURI)).getExpiresDate());
  }

  @Test
  void testGetExpiresDate7() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("expires", "GMT");
    genericURI.addParameter("Name");

    // Act and Assert
    assertNull((new ContactHeader(genericURI)).getExpiresDate());
  }

  @Test
  void testGetExpiresDate8() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("", "");
    genericURI.addParameter("");

    // Act and Assert
    assertNull((new ContactHeader(genericURI)).getExpiresDate());
  }

  @Test
  void testRemoveExpires() {
    // Arrange
    ContactHeader contactHeader = new ContactHeader();

    // Act and Assert
    assertSame(contactHeader, contactHeader.removeExpires());
  }

  @Test
  void testRemoveExpires2() {
    // Arrange
    ContactHeader contactHeader = new ContactHeader(new NameAddress("Str"));

    // Act and Assert
    assertSame(contactHeader, contactHeader.removeExpires());
  }

  @Test
  void testRemoveExpires3() {
    // Arrange
    ContactHeader contactHeader = new ContactHeader(new NameAddress("Display name", new GenericURI("Uri")));

    // Act and Assert
    assertSame(contactHeader, contactHeader.removeExpires());
  }

  @Test
  void testRemoveExpires4() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("Name");
    ContactHeader contactHeader = new ContactHeader(genericURI);

    // Act and Assert
    assertSame(contactHeader, contactHeader.removeExpires());
  }

  @Test
  void testRemoveExpires5() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("expires");
    genericURI.addParameter("Name");
    ContactHeader contactHeader = new ContactHeader(genericURI);

    // Act
    ContactHeader actualRemoveExpiresResult = contactHeader.removeExpires();

    // Assert
    assertSame(contactHeader, actualRemoveExpiresResult);
    assertEquals("Uri;Name", actualRemoveExpiresResult.getValue());
  }

  @Test
  void testRemoveExpires6() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addLr();
    genericURI.addParameter("Name");
    ContactHeader contactHeader = new ContactHeader(genericURI);

    // Act and Assert
    assertSame(contactHeader, contactHeader.removeExpires());
  }

  @Test
  void testRemoveExpires7() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("expires");
    ContactHeader contactHeader = new ContactHeader(genericURI);

    // Act
    ContactHeader actualRemoveExpiresResult = contactHeader.removeExpires();

    // Assert
    assertSame(contactHeader, actualRemoveExpiresResult);
    assertEquals("Uri", actualRemoveExpiresResult.getValue());
  }

  @Test
  void testRemoveExpires8() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("");
    ContactHeader contactHeader = new ContactHeader(genericURI);

    // Act and Assert
    assertSame(contactHeader, contactHeader.removeExpires());
  }

  @Test
  void testRemoveExpires9() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("");
    genericURI.addParameter("Name");
    ContactHeader contactHeader = new ContactHeader(genericURI);

    // Act and Assert
    assertSame(contactHeader, contactHeader.removeExpires());
  }

  @Test
  void testRemoveExpires10() {
    // Arrange
    Header header = new Header();
    header.setValue("42");
    ContactHeader contactHeader = new ContactHeader(header);

    // Act and Assert
    assertSame(contactHeader, contactHeader.removeExpires());
  }

  @Test
  void testGetNameAddress() {
    // Arrange and Act
    NameAddress actualNameAddress = (new ContactHeader()).getNameAddress();

    // Assert
    assertEquals("<sip:*>", actualNameAddress.toString());
    assertFalse(actualNameAddress.hasDisplayName());
    GenericURI address = actualNameAddress.getAddress();
    assertFalse(address.isTelURI());
    assertFalse(((SipURI) address).isSecure());
  }

  @Test
  void testGetNameAddress2() {
    // Arrange and Act
    NameAddress actualNameAddress = (new ContactHeader(new NameAddress("Str"))).getNameAddress();

    // Assert
    assertEquals("<sip:Str>", actualNameAddress.toString());
    assertNull(actualNameAddress.getDisplayName());
    assertTrue(actualNameAddress.getAddress().isSipURI());
  }

  @Test
  void testGetNameAddress3() {
    // Arrange and Act
    NameAddress actualNameAddress = (new ContactHeader(new NameAddress("Display name", new GenericURI("Uri"))))
        .getNameAddress();

    // Assert
    assertEquals("<sip:\"Display>", actualNameAddress.toString());
    assertFalse(actualNameAddress.hasDisplayName());
    GenericURI address = actualNameAddress.getAddress();
    assertFalse(address.isTelURI());
    assertFalse(((SipURI) address).isSecure());
  }

  @Test
  void testGetNameAddress4() {
    // Arrange
    NameAddress nameAddress = new NameAddress("Str");
    nameAddress.setDisplayName("Display name");

    // Act
    NameAddress actualNameAddress = (new ContactHeader(nameAddress)).getNameAddress();

    // Assert
    assertEquals("\"Display name\" <sip:Str>", actualNameAddress.toString());
    assertEquals("Display name", actualNameAddress.getDisplayName());
    assertTrue(actualNameAddress.getAddress().isSipURI());
  }

  @Test
  void testGetNameAddress5() {
    // Arrange and Act
    NameAddress actualNameAddress = (new ContactHeader(new NameAddress("sips:", new GenericURI("Uri"))))
        .getNameAddress();

    // Assert
    assertEquals("<sips:\">", actualNameAddress.toString());
    assertNull(actualNameAddress.getDisplayName());
    assertTrue(actualNameAddress.getAddress().isSipURI());
  }

  @Test
  void testGetNameAddress6() {
    // Arrange and Act
    NameAddress actualNameAddress = (new ContactHeader(new GenericURI(""))).getNameAddress();

    // Assert
    assertEquals("<sip:>", actualNameAddress.toString());
    assertFalse(actualNameAddress.hasDisplayName());
    GenericURI address = actualNameAddress.getAddress();
    assertFalse(address.isTelURI());
    assertFalse(((SipURI) address).isSecure());
  }

  @Test
  void testGetNameAddress7() {
    // Arrange
    GenericURI genericURI = new GenericURI("");
    genericURI.addParameter("sips:");

    // Act
    NameAddress actualNameAddress = (new ContactHeader(genericURI)).getNameAddress();

    // Assert
    assertEquals("<sips:>", actualNameAddress.toString());
    assertNull(actualNameAddress.getDisplayName());
    assertTrue(actualNameAddress.getAddress().isSipURI());
  }

  @Test
  void testGetNameAddress8() {
    // Arrange
    GenericURI genericURI = new GenericURI("");
    genericURI.addLr();

    // Act
    NameAddress actualNameAddress = (new ContactHeader(genericURI)).getNameAddress();

    // Assert
    assertEquals("<sip:;lr>", actualNameAddress.toString());
    assertFalse(actualNameAddress.hasDisplayName());
    GenericURI address = actualNameAddress.getAddress();
    assertFalse(address.isTelURI());
    assertFalse(((SipURI) address).isSecure());
  }

  @Test
  void testGetNameAddress9() {
    // Arrange
    GenericURI genericURI = new GenericURI("");
    genericURI.addLr();
    genericURI.addLr();

    // Act
    NameAddress actualNameAddress = (new ContactHeader(genericURI)).getNameAddress();

    // Assert
    assertEquals("<sip:;lr;lr>", actualNameAddress.toString());
    assertFalse(actualNameAddress.hasDisplayName());
    GenericURI address = actualNameAddress.getAddress();
    assertFalse(address.isTelURI());
    assertFalse(((SipURI) address).isSecure());
  }

  @Test
  void testGetNameAddress10() {
    // Arrange
    GenericURI genericURI = new GenericURI("");
    genericURI.addParameter("Name", "42");

    // Act
    NameAddress actualNameAddress = (new ContactHeader(genericURI)).getNameAddress();

    // Assert
    assertEquals("<sip:;Name=42>", actualNameAddress.toString());
    assertFalse(actualNameAddress.hasDisplayName());
    GenericURI address = actualNameAddress.getAddress();
    assertFalse(address.isTelURI());
    assertFalse(((SipURI) address).isSecure());
  }

  @Test
  void testGetNameAddress11() {
    // Arrange
    GenericURI genericURI = new GenericURI("");
    genericURI.addParameter("", "42");

    // Act
    NameAddress actualNameAddress = (new ContactHeader(genericURI)).getNameAddress();

    // Assert
    assertEquals("<sip:;=42>", actualNameAddress.toString());
    assertFalse(actualNameAddress.hasDisplayName());
    GenericURI address = actualNameAddress.getAddress();
    assertFalse(address.isTelURI());
    assertFalse(((SipURI) address).isSecure());
  }

  @Test
  void testGetNameAddress12() {
    // Arrange
    GenericURI genericURI = new GenericURI("");
    genericURI.addParameter("", "tag");

    // Act
    NameAddress actualNameAddress = (new ContactHeader(genericURI)).getNameAddress();

    // Assert
    assertEquals("<sip:>", actualNameAddress.toString());
    assertFalse(actualNameAddress.hasDisplayName());
    GenericURI address = actualNameAddress.getAddress();
    assertFalse(address.isTelURI());
    assertFalse(((SipURI) address).isSecure());
  }

  @Test
  void testGetNameAddress13() {
    // Arrange
    GenericURI genericURI = new GenericURI("");
    genericURI.addParameter("", "");

    // Act
    NameAddress actualNameAddress = (new ContactHeader(genericURI)).getNameAddress();

    // Assert
    assertEquals("<sip:;=>", actualNameAddress.toString());
    assertFalse(actualNameAddress.hasDisplayName());
    GenericURI address = actualNameAddress.getAddress();
    assertFalse(address.isTelURI());
    assertFalse(((SipURI) address).isSecure());
  }

  @Test
  void testGetNameAddress14() {
    // Arrange
    GenericURI genericURI = new GenericURI("");
    genericURI.addLr();
    genericURI.addParameter("", "tag");

    // Act
    NameAddress actualNameAddress = (new ContactHeader(genericURI)).getNameAddress();

    // Assert
    assertEquals("<sip:;lr>", actualNameAddress.toString());
    assertFalse(actualNameAddress.hasDisplayName());
    GenericURI address = actualNameAddress.getAddress();
    assertFalse(address.isTelURI());
    assertFalse(((SipURI) address).isSecure());
  }

  @Test
  void testGetNameAddress15() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act
    NameAddress actualNameAddress = (new ContactHeader(header)).getNameAddress();

    // Assert
    assertEquals("<sip:42>", actualNameAddress.toString());
    assertFalse(actualNameAddress.hasDisplayName());
    GenericURI address = actualNameAddress.getAddress();
    assertFalse(address.isTelURI());
    assertFalse(((SipURI) address).isSecure());
  }

  @Test
  void testGetParameter() {
    // Arrange, Act and Assert
    assertNull((new ContactHeader()).getParameter("Pname"));
    assertNull((new ContactHeader(new NameAddress("Str"))).getParameter("Pname"));
    assertNull((new ContactHeader(new NameAddress("Display name", new GenericURI("Uri")))).getParameter("Pname"));
  }

  @Test
  void testGetParameter2() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("Name");

    // Act and Assert
    assertNull((new ContactHeader(genericURI)).getParameter("Pname"));
  }

  @Test
  void testGetParameter3() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("Name");
    genericURI.addParameter("Name");

    // Act and Assert
    assertNull((new ContactHeader(genericURI)).getParameter("Pname"));
  }

  @Test
  void testGetParameter4() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("Pname");
    genericURI.addParameter("Pname");

    // Act and Assert
    assertNull((new ContactHeader(genericURI)).getParameter("Pname"));
  }

  @Test
  void testGetParameter5() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("Pname", "42");
    genericURI.addParameter("Pname");

    // Act and Assert
    assertEquals("42", (new ContactHeader(genericURI)).getParameter("Pname"));
  }

  @Test
  void testGetParameter6() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("");
    genericURI.addParameter("Name");

    // Act and Assert
    assertNull((new ContactHeader(genericURI)).getParameter("Pname"));
  }

  @Test
  void testGetParameter7() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("Pname", "");
    genericURI.addParameter("Pname");

    // Act and Assert
    assertEquals("Pname", (new ContactHeader(genericURI)).getParameter("Pname"));
  }

  @Test
  void testGetParameter8() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("");
    genericURI.addParameter("");

    // Act and Assert
    assertNull((new ContactHeader(genericURI)).getParameter("Pname"));
  }

  @Test
  void testGetParameter9() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("Pname", "");
    genericURI.addParameter("");

    // Act and Assert
    assertEquals("", (new ContactHeader(genericURI)).getParameter("Pname"));
  }

  @Test
  void testGetParameterNames() {
    // Arrange, Act and Assert
    assertTrue((new ContactHeader()).getParameterNames().isEmpty());
    assertTrue((new ContactHeader(new NameAddress("Str"))).getParameterNames().isEmpty());
    assertTrue(
        (new ContactHeader(new NameAddress("Display name", new GenericURI("Uri")))).getParameterNames().isEmpty());
  }

  @Test
  void testGetParameterNames2() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("Name");

    // Act
    Vector actualParameterNames = (new ContactHeader(genericURI)).getParameterNames();

    // Assert
    assertEquals(1, actualParameterNames.size());
    assertEquals("Name", actualParameterNames.get(0));
  }

  @Test
  void testGetParameterNames3() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("Name");
    genericURI.addParameter("Name");

    // Act
    Vector actualParameterNames = (new ContactHeader(genericURI)).getParameterNames();

    // Assert
    assertEquals(2, actualParameterNames.size());
    assertEquals("Name", actualParameterNames.get(0));
    assertEquals("Name", actualParameterNames.get(1));
  }

  @Test
  void testGetParameterNames4() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("");

    // Act and Assert
    assertTrue((new ContactHeader(genericURI)).getParameterNames().isEmpty());
  }

  @Test
  void testGetParameterNames5() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("");
    genericURI.addParameter("Name");

    // Act
    Vector actualParameterNames = (new ContactHeader(genericURI)).getParameterNames();

    // Assert
    assertEquals(1, actualParameterNames.size());
    assertEquals("Name", actualParameterNames.get(0));
  }

  @Test
  void testGetParameterNames6() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("");
    genericURI.addParameter("");

    // Act and Assert
    assertTrue((new ContactHeader(genericURI)).getParameterNames().isEmpty());
  }

  @Test
  void testGetParameterNames7() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertTrue((new ContactHeader(header)).getParameterNames().isEmpty());
  }

  @Test
  void testGetParameters() {
    // Arrange, Act and Assert
    assertNull((new ContactHeader()).getParameters());
    assertNull((new ContactHeader(new NameAddress("Str"))).getParameters());
    assertNull((new ContactHeader(new NameAddress("Display name", new GenericURI("Uri")))).getParameters());
  }

  @Test
  void testGetParameters2() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("Name");

    // Act and Assert
    assertEquals("Name", (new ContactHeader(genericURI)).getParameters());
  }

  @Test
  void testGetParameters3() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertNull((new ContactHeader(header)).getParameters());
  }

  @Test
  void testGetTag() {
    // Arrange, Act and Assert
    assertNull((new ContactHeader()).getTag());
    assertNull((new ContactHeader(new NameAddress("Str"))).getTag());
    assertNull((new ContactHeader(new NameAddress("Display name", new GenericURI("Uri")))).getTag());
  }

  @Test
  void testGetTag2() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("Name");

    // Act and Assert
    assertNull((new ContactHeader(genericURI)).getTag());
  }

  @Test
  void testGetTag3() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("tag");
    genericURI.addParameter("Name");

    // Act and Assert
    assertNull((new ContactHeader(genericURI)).getTag());
  }

  @Test
  void testGetTag4() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addLr();
    genericURI.addParameter("Name");

    // Act and Assert
    assertNull((new ContactHeader(genericURI)).getTag());
  }

  @Test
  void testGetTag5() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("tag", "42");
    genericURI.addParameter("Name");

    // Act and Assert
    assertEquals("42", (new ContactHeader(genericURI)).getTag());
  }

  @Test
  void testGetTag6() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("");
    genericURI.addParameter("Name");

    // Act and Assert
    assertNull((new ContactHeader(genericURI)).getTag());
  }

  @Test
  void testGetTag7() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("tag", "");
    genericURI.addParameter("Name");

    // Act and Assert
    assertEquals("Name", (new ContactHeader(genericURI)).getTag());
  }

  @Test
  void testGetTag8() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("");
    genericURI.addParameter("");

    // Act and Assert
    assertNull((new ContactHeader(genericURI)).getTag());
  }

  @Test
  void testGetTag9() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("tag", "");
    genericURI.addParameter("");

    // Act and Assert
    assertEquals("", (new ContactHeader(genericURI)).getTag());
  }

  @Test
  void testHasParameter() {
    // Arrange, Act and Assert
    assertFalse((new ContactHeader()).hasParameter("Pname"));
    assertFalse((new ContactHeader(new NameAddress("Str"))).hasParameter("Pname"));
    assertFalse((new ContactHeader(new NameAddress("Display name", new GenericURI("Uri")))).hasParameter("Pname"));
  }

  @Test
  void testHasParameter2() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("Name");

    // Act and Assert
    assertFalse((new ContactHeader(genericURI)).hasParameter("Pname"));
  }

  @Test
  void testHasParameter3() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("Name");
    genericURI.addParameter("Name");

    // Act and Assert
    assertFalse((new ContactHeader(genericURI)).hasParameter("Pname"));
  }

  @Test
  void testHasParameter4() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("Pname");

    // Act and Assert
    assertTrue((new ContactHeader(genericURI)).hasParameter("Pname"));
  }

  @Test
  void testHasParameter5() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("");

    // Act and Assert
    assertFalse((new ContactHeader(genericURI)).hasParameter("Pname"));
  }

  @Test
  void testHasParameter6() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("");
    genericURI.addParameter("Name");

    // Act and Assert
    assertFalse((new ContactHeader(genericURI)).hasParameter("Pname"));
  }

  @Test
  void testHasParameter7() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("");
    genericURI.addParameter("");

    // Act and Assert
    assertFalse((new ContactHeader(genericURI)).hasParameter("Pname"));
  }

  @Test
  void testHasParameter8() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertFalse((new ContactHeader(header)).hasParameter("Pname"));
  }

  @Test
  void testHasParameters() {
    // Arrange, Act and Assert
    assertFalse((new ContactHeader()).hasParameters());
    assertFalse((new ContactHeader(new NameAddress("Str"))).hasParameters());
    assertFalse((new ContactHeader(new NameAddress("Display name", new GenericURI("Uri")))).hasParameters());
  }

  @Test
  void testHasParameters2() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("Name");

    // Act and Assert
    assertTrue((new ContactHeader(genericURI)).hasParameters());
  }

  @Test
  void testHasParameters3() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertFalse((new ContactHeader(header)).hasParameters());
  }

  @Test
  void testHasTag() {
    // Arrange, Act and Assert
    assertFalse((new ContactHeader()).hasTag());
    assertFalse((new ContactHeader(new NameAddress("Str"))).hasTag());
    assertFalse((new ContactHeader(new NameAddress("Display name", new GenericURI("Uri")))).hasTag());
  }

  @Test
  void testHasTag2() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("Name");

    // Act and Assert
    assertFalse((new ContactHeader(genericURI)).hasTag());
  }

  @Test
  void testHasTag3() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("tag");
    genericURI.addParameter("Name");

    // Act and Assert
    assertTrue((new ContactHeader(genericURI)).hasTag());
  }

  @Test
  void testHasTag4() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addLr();
    genericURI.addParameter("Name");

    // Act and Assert
    assertFalse((new ContactHeader(genericURI)).hasTag());
  }

  @Test
  void testHasTag5() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("");

    // Act and Assert
    assertFalse((new ContactHeader(genericURI)).hasTag());
  }

  @Test
  void testHasTag6() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("");
    genericURI.addParameter("Name");

    // Act and Assert
    assertFalse((new ContactHeader(genericURI)).hasTag());
  }

  @Test
  void testHasTag7() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("");
    genericURI.addParameter("");

    // Act and Assert
    assertFalse((new ContactHeader(genericURI)).hasTag());
  }

  @Test
  void testHasTag8() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertFalse((new ContactHeader(header)).hasTag());
  }

  @Test
  void testIndexOfFirstSemi() {
    // Arrange, Act and Assert
    assertEquals(-1, (new ContactHeader()).indexOfFirstSemi());
    assertEquals(-1, (new ContactHeader(new NameAddress("Str"))).indexOfFirstSemi());
    assertEquals(-1, (new ContactHeader(new NameAddress("Display name", new GenericURI("Uri")))).indexOfFirstSemi());
  }

  @Test
  void testIndexOfFirstSemi2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertEquals(-1, (new ContactHeader(header)).indexOfFirstSemi());
  }

  @Test
  void testRemoveParameter() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("Pname");
    ContactHeader contactHeader = new ContactHeader(genericURI);

    // Act
    contactHeader.removeParameter("Pname");

    // Assert
    assertEquals("Uri", contactHeader.getValue());
  }

  @Test
  void testRemoveParameter2() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("Pname");
    genericURI.addParameter("Name");
    ContactHeader contactHeader = new ContactHeader(genericURI);

    // Act
    contactHeader.removeParameter("Pname");

    // Assert
    assertEquals("Uri;Name", contactHeader.getValue());
  }

  @Test
  void testRemoveParameters() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("Name");
    ContactHeader contactHeader = new ContactHeader(genericURI);

    // Act
    contactHeader.removeParameters();

    // Assert
    assertEquals("Uri", contactHeader.getValue());
  }

  @Test
  void testSetNameAddress() {
    // Arrange
    ContactHeader contactHeader = new ContactHeader();

    // Act
    contactHeader.setNameAddress(new NameAddress("Str"));

    // Assert
    assertEquals("<sip:Str>", contactHeader.getValue());
  }

  @Test
  void testSetParameter() {
    // Arrange
    ContactHeader contactHeader = new ContactHeader();

    // Act
    contactHeader.setParameter("Pname", "42");

    // Assert
    assertEquals("*;Pname=42", contactHeader.getValue());
  }

  @Test
  void testSetParameter2() {
    // Arrange
    ContactHeader contactHeader = new ContactHeader(new NameAddress("Str"));

    // Act
    contactHeader.setParameter("Pname", "42");

    // Assert
    assertEquals("<sip:Str>;Pname=42", contactHeader.getValue());
  }

  @Test
  void testSetParameter3() {
    // Arrange
    ContactHeader contactHeader = new ContactHeader(new NameAddress("Display name", new GenericURI("Uri")));

    // Act
    contactHeader.setParameter("Pname", "42");

    // Assert
    assertEquals("\"Display name\" <Uri>;Pname=42", contactHeader.getValue());
  }

  @Test
  void testSetParameter4() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("Name");
    ContactHeader contactHeader = new ContactHeader(genericURI);

    // Act
    contactHeader.setParameter("Pname", "42");

    // Assert
    assertEquals("Uri;Name;Pname=42", contactHeader.getValue());
  }

  @Test
  void testSetParameter5() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("Name");
    genericURI.addParameter("Name");
    ContactHeader contactHeader = new ContactHeader(genericURI);

    // Act
    contactHeader.setParameter("Pname", "42");

    // Assert
    assertEquals("Uri;Name;Name;Pname=42", contactHeader.getValue());
  }

  @Test
  void testSetParameter6() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("Pname");
    ContactHeader contactHeader = new ContactHeader(genericURI);

    // Act
    contactHeader.setParameter("Pname", "42");

    // Assert
    assertEquals("Uri;Pname=42", contactHeader.getValue());
  }

  @Test
  void testSetParameter7() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("");
    ContactHeader contactHeader = new ContactHeader(genericURI);

    // Act
    contactHeader.setParameter("Pname", "42");

    // Assert
    assertEquals("Uri;;Pname=42", contactHeader.getValue());
  }

  @Test
  void testSetParameter8() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("Pname");
    genericURI.addParameter("Name");
    ContactHeader contactHeader = new ContactHeader(genericURI);

    // Act
    contactHeader.setParameter("Pname", "42");

    // Assert
    assertEquals("Uri;Name;Pname=42", contactHeader.getValue());
  }

  @Test
  void testSetParameter9() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("");
    genericURI.addParameter("Name");
    ContactHeader contactHeader = new ContactHeader(genericURI);

    // Act
    contactHeader.setParameter("Pname", "42");

    // Assert
    assertEquals("Uri;;Name;Pname=42", contactHeader.getValue());
  }

  @Test
  void testSetParameter10() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("Name");
    genericURI.addParameter("Pname");
    ContactHeader contactHeader = new ContactHeader(genericURI);

    // Act
    contactHeader.setParameter("Pname", "42");

    // Assert
    assertEquals("Uri;Name;Pname=42", contactHeader.getValue());
  }

  @Test
  void testSetParameter11() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("");
    genericURI.addParameter("");
    ContactHeader contactHeader = new ContactHeader(genericURI);

    // Act
    contactHeader.setParameter("Pname", "42");

    // Assert
    assertEquals("Uri;;;Pname=42", contactHeader.getValue());
  }

  @Test
  void testSetParameter12() {
    // Arrange
    ContactHeader contactHeader = new ContactHeader(new Header());

    // Act
    contactHeader.setParameter("Pname", "42");

    // Assert
    assertEquals(";Pname=42", contactHeader.getValue());
  }

  @Test
  void testSetTag() {
    // Arrange
    ContactHeader contactHeader = new ContactHeader();

    // Act
    contactHeader.setTag("Tag");

    // Assert
    assertEquals("*;tag=Tag", contactHeader.getValue());
  }

  @Test
  void testSetTag2() {
    // Arrange
    ContactHeader contactHeader = new ContactHeader(new NameAddress("Str"));

    // Act
    contactHeader.setTag("Tag");

    // Assert
    assertEquals("<sip:Str>;tag=Tag", contactHeader.getValue());
  }

  @Test
  void testSetTag3() {
    // Arrange
    ContactHeader contactHeader = new ContactHeader(new NameAddress("Display name", new GenericURI("Uri")));

    // Act
    contactHeader.setTag("Tag");

    // Assert
    assertEquals("\"Display name\" <Uri>;tag=Tag", contactHeader.getValue());
  }

  @Test
  void testSetTag4() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("Name");
    ContactHeader contactHeader = new ContactHeader(genericURI);

    // Act
    contactHeader.setTag("Tag");

    // Assert
    assertEquals("Uri;Name;tag=Tag", contactHeader.getValue());
  }

  @Test
  void testSetTag5() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("tag");
    genericURI.addParameter("Name");
    ContactHeader contactHeader = new ContactHeader(genericURI);

    // Act
    contactHeader.setTag("Tag");

    // Assert
    assertEquals("Uri;Name;tag=Tag", contactHeader.getValue());
  }

  @Test
  void testSetTag6() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addLr();
    genericURI.addParameter("Name");
    ContactHeader contactHeader = new ContactHeader(genericURI);

    // Act
    contactHeader.setTag("Tag");

    // Assert
    assertEquals("Uri;lr;Name;tag=Tag", contactHeader.getValue());
  }

  @Test
  void testSetTag7() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("tag");
    ContactHeader contactHeader = new ContactHeader(genericURI);

    // Act
    contactHeader.setTag("Tag");

    // Assert
    assertEquals("Uri;tag=Tag", contactHeader.getValue());
  }

  @Test
  void testSetTag8() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("");
    ContactHeader contactHeader = new ContactHeader(genericURI);

    // Act
    contactHeader.setTag("Tag");

    // Assert
    assertEquals("Uri;;tag=Tag", contactHeader.getValue());
  }

  @Test
  void testSetTag9() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addLr();
    genericURI.addParameter("tag");
    genericURI.addParameter("Name");
    ContactHeader contactHeader = new ContactHeader(genericURI);

    // Act
    contactHeader.setTag("Tag");

    // Assert
    assertEquals("Uri;lr;Name;tag=Tag", contactHeader.getValue());
  }

  @Test
  void testSetTag10() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("");
    genericURI.addParameter("Name");
    ContactHeader contactHeader = new ContactHeader(genericURI);

    // Act
    contactHeader.setTag("Tag");

    // Assert
    assertEquals("Uri;;Name;tag=Tag", contactHeader.getValue());
  }

  @Test
  void testSetTag11() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("");
    genericURI.addParameter("");
    ContactHeader contactHeader = new ContactHeader(genericURI);

    // Act
    contactHeader.setTag("Tag");

    // Assert
    assertEquals("Uri;;;tag=Tag", contactHeader.getValue());
  }

  @Test
  void testSetTag12() {
    // Arrange
    ContactHeader contactHeader = new ContactHeader(new Header());

    // Act
    contactHeader.setTag("Tag");

    // Assert
    assertEquals(";tag=Tag", contactHeader.getValue());
  }
}

