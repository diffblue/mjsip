package org.mjsip.sip.header;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Vector;
import org.junit.jupiter.api.Test;
import org.mjsip.sip.address.GenericURI;
import org.mjsip.sip.address.NameAddress;
import org.mjsip.sip.address.SipURI;

class ServiceRouteHeaderDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange
    Header header = new Header("Hname", "42");

    // Act
    ServiceRouteHeader actualServiceRouteHeader = new ServiceRouteHeader(header);

    // Assert
    assertEquals("Hname", actualServiceRouteHeader.getName());
    assertEquals("Hname: 42\r\n", actualServiceRouteHeader.toString());
    assertEquals("42", actualServiceRouteHeader.getValue());
    assertNull(actualServiceRouteHeader.getParameters());
    Vector parameterNames = actualServiceRouteHeader.getParameterNames();
    assertTrue(parameterNames.isEmpty());
    NameAddress nameAddress = actualServiceRouteHeader.getNameAddress();
    GenericURI address = nameAddress.getAddress();
    assertTrue(address instanceof SipURI);
    assertEquals("<sip:42>", nameAddress.toString());
    assertFalse(nameAddress.hasDisplayName());
    assertNull(nameAddress.getDisplayName());
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
    assertEquals("42", ((SipURI) address).getHost());
    assertEquals("sip:42", address.toString());
    assertEquals("Hname", header.getName());
    assertEquals("Hname: 42\r\n", header.toString());
    assertEquals("42", header.getValue());
  }

  @Test
  void testConstructor2() {
    // Arrange and Act
    ServiceRouteHeader actualServiceRouteHeader = new ServiceRouteHeader(new NameAddress("Str"));

    // Assert
    assertEquals(SipHeaders.ServiceRoute, actualServiceRouteHeader.getName());
    assertEquals("<sip:Str>", actualServiceRouteHeader.getValue());
  }

  @Test
  void testGetNameAddress() {
    // Arrange and Act
    NameAddress actualNameAddress = (new ServiceRouteHeader(new NameAddress("Str"))).getNameAddress();

    // Assert
    assertEquals("<sip:Str>", actualNameAddress.toString());
    assertNull(actualNameAddress.getDisplayName());
    assertTrue(actualNameAddress.getAddress().isSipURI());
  }

  @Test
  void testGetNameAddress2() {
    // Arrange and Act
    NameAddress actualNameAddress = (new ServiceRouteHeader(new NameAddress(new GenericURI("Uri")))).getNameAddress();

    // Assert
    assertEquals("<sip:<Uri>>", actualNameAddress.toString());
    assertFalse(actualNameAddress.hasDisplayName());
    GenericURI address = actualNameAddress.getAddress();
    assertFalse(address.isTelURI());
    assertFalse(((SipURI) address).isSecure());
  }

  @Test
  void testGetNameAddress3() {
    // Arrange and Act
    NameAddress actualNameAddress = (new ServiceRouteHeader(new NameAddress("Display name", new GenericURI("Uri"))))
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
    NameAddress actualNameAddress = (new ServiceRouteHeader(nameAddress)).getNameAddress();

    // Assert
    assertEquals("\"Display name\" <sip:Str>", actualNameAddress.toString());
    assertEquals("Display name", actualNameAddress.getDisplayName());
    assertTrue(actualNameAddress.getAddress().isSipURI());
  }

  @Test
  void testGetNameAddress5() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("sips:");

    // Act
    NameAddress actualNameAddress = (new ServiceRouteHeader(new NameAddress(genericURI))).getNameAddress();

    // Assert
    assertEquals("<sips:>", actualNameAddress.toString());
    assertNull(actualNameAddress.getDisplayName());
    assertTrue(actualNameAddress.getAddress().isSipURI());
  }

  @Test
  void testGetNameAddress6() {
    // Arrange and Act
    NameAddress actualNameAddress = (new ServiceRouteHeader(new NameAddress("sips:", new GenericURI("Uri"))))
        .getNameAddress();

    // Assert
    assertEquals("<sips:\">", actualNameAddress.toString());
    assertNull(actualNameAddress.getDisplayName());
    assertTrue(actualNameAddress.getAddress().isSipURI());
  }

  @Test
  void testGetNameAddress7() {
    // Arrange and Act
    NameAddress actualNameAddress = (new ServiceRouteHeader(new Header("Hname", ""))).getNameAddress();

    // Assert
    assertEquals("<sip:>", actualNameAddress.toString());
    assertFalse(actualNameAddress.hasDisplayName());
    GenericURI address = actualNameAddress.getAddress();
    assertFalse(address.isTelURI());
    assertFalse(((SipURI) address).isSecure());
  }

  @Test
  void testGetNameAddress8() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act
    NameAddress actualNameAddress = (new ServiceRouteHeader(header)).getNameAddress();

    // Assert
    assertEquals("<sip:42>", actualNameAddress.toString());
    assertFalse(actualNameAddress.hasDisplayName());
    GenericURI address = actualNameAddress.getAddress();
    assertFalse(address.isTelURI());
    assertFalse(((SipURI) address).isSecure());
  }

  @Test
  void testGetNameAddress9() {
    // Arrange
    Header header = new Header();
    header.setValue("sips:");

    // Act
    NameAddress actualNameAddress = (new ServiceRouteHeader(header)).getNameAddress();

    // Assert
    assertEquals("<sips:>", actualNameAddress.toString());
    assertNull(actualNameAddress.getDisplayName());
    assertTrue(actualNameAddress.getAddress().isSipURI());
  }

  @Test
  void testGetParameter() {
    // Arrange, Act and Assert
    assertNull((new ServiceRouteHeader(new NameAddress("Str"))).getParameter("Pname"));
    assertNull((new ServiceRouteHeader(new NameAddress("Display name", new GenericURI("Uri")))).getParameter("Pname"));
    assertNull((new ServiceRouteHeader(new Header("Hname", "42"))).getParameter("Pname"));
  }

  @Test
  void testGetParameter2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertNull((new ServiceRouteHeader(header)).getParameter("Pname"));
  }

  @Test
  void testGetParameterNames() {
    // Arrange, Act and Assert
    assertTrue((new ServiceRouteHeader(new NameAddress("Str"))).getParameterNames().isEmpty());
    assertTrue(
        (new ServiceRouteHeader(new NameAddress("Display name", new GenericURI("Uri")))).getParameterNames().isEmpty());
    assertTrue((new ServiceRouteHeader(new Header("Hname", "42"))).getParameterNames().isEmpty());
  }

  @Test
  void testGetParameterNames2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertTrue((new ServiceRouteHeader(header)).getParameterNames().isEmpty());
  }

  @Test
  void testGetParameters() {
    // Arrange, Act and Assert
    assertNull((new ServiceRouteHeader(new NameAddress("Str"))).getParameters());
    assertNull((new ServiceRouteHeader(new NameAddress("Display name", new GenericURI("Uri")))).getParameters());
    assertNull((new ServiceRouteHeader(new Header("Hname", "42"))).getParameters());
  }

  @Test
  void testGetParameters2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertNull((new ServiceRouteHeader(header)).getParameters());
  }

  @Test
  void testHasParameter() {
    // Arrange, Act and Assert
    assertFalse((new ServiceRouteHeader(new NameAddress("Str"))).hasParameter("Pname"));
    assertFalse((new ServiceRouteHeader(new NameAddress("Display name", new GenericURI("Uri")))).hasParameter("Pname"));
    assertFalse((new ServiceRouteHeader(new Header("Hname", "42"))).hasParameter("Pname"));
  }

  @Test
  void testHasParameter2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertFalse((new ServiceRouteHeader(header)).hasParameter("Pname"));
  }

  @Test
  void testHasParameters() {
    // Arrange, Act and Assert
    assertFalse((new ServiceRouteHeader(new NameAddress("Str"))).hasParameters());
    assertFalse((new ServiceRouteHeader(new NameAddress("Display name", new GenericURI("Uri")))).hasParameters());
    assertFalse((new ServiceRouteHeader(new Header("Hname", "42"))).hasParameters());
  }

  @Test
  void testHasParameters2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertFalse((new ServiceRouteHeader(header)).hasParameters());
  }

  @Test
  void testIndexOfFirstSemi() {
    // Arrange, Act and Assert
    assertEquals(-1, (new ServiceRouteHeader(new NameAddress("Str"))).indexOfFirstSemi());
    assertEquals(-1,
        (new ServiceRouteHeader(new NameAddress("Display name", new GenericURI("Uri")))).indexOfFirstSemi());
    assertEquals(-1, (new ServiceRouteHeader(new Header("Hname", "42"))).indexOfFirstSemi());
  }

  @Test
  void testIndexOfFirstSemi2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertEquals(-1, (new ServiceRouteHeader(header)).indexOfFirstSemi());
  }

  @Test
  void testSetNameAddress() {
    // Arrange
    ServiceRouteHeader serviceRouteHeader = new ServiceRouteHeader(new NameAddress("Str"));

    // Act
    serviceRouteHeader.setNameAddress(new NameAddress("Str"));

    // Assert
    assertEquals("<sip:Str>", serviceRouteHeader.getValue());
  }

  @Test
  void testSetParameter() {
    // Arrange
    ServiceRouteHeader serviceRouteHeader = new ServiceRouteHeader(new NameAddress("Str"));

    // Act
    serviceRouteHeader.setParameter("Pname", "42");

    // Assert
    assertEquals("<sip:Str>;Pname=42", serviceRouteHeader.getValue());
  }

  @Test
  void testSetParameter2() {
    // Arrange
    ServiceRouteHeader serviceRouteHeader = new ServiceRouteHeader(
        new NameAddress("Display name", new GenericURI("Uri")));

    // Act
    serviceRouteHeader.setParameter("Pname", "42");

    // Assert
    assertEquals("\"Display name\" <Uri>;Pname=42", serviceRouteHeader.getValue());
  }

  @Test
  void testSetParameter3() {
    // Arrange
    ServiceRouteHeader serviceRouteHeader = new ServiceRouteHeader(new Header("Hname", "42"));

    // Act
    serviceRouteHeader.setParameter("Pname", "42");

    // Assert
    assertEquals("42;Pname=42", serviceRouteHeader.getValue());
  }

  @Test
  void testSetParameter4() {
    // Arrange
    ServiceRouteHeader serviceRouteHeader = new ServiceRouteHeader(new Header());

    // Act
    serviceRouteHeader.setParameter("Pname", "42");

    // Assert
    assertEquals(";Pname=42", serviceRouteHeader.getValue());
  }
}

