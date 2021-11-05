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

class RouteHeaderDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange
    Header header = new Header("Hname", "42");

    // Act
    RouteHeader actualRouteHeader = new RouteHeader(header);

    // Assert
    assertEquals("Hname", actualRouteHeader.getName());
    assertEquals("Hname: 42\r\n", actualRouteHeader.toString());
    assertEquals("42", actualRouteHeader.getValue());
    assertNull(actualRouteHeader.getParameters());
    Vector parameterNames = actualRouteHeader.getParameterNames();
    assertTrue(parameterNames.isEmpty());
    NameAddress nameAddress = actualRouteHeader.getNameAddress();
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
    RouteHeader actualRouteHeader = new RouteHeader(new NameAddress("Str"));

    // Assert
    assertEquals(CoreSipHeaders.Route, actualRouteHeader.getName());
    assertEquals("<sip:Str>", actualRouteHeader.getValue());
  }

  @Test
  void testGetNameAddress() {
    // Arrange and Act
    NameAddress actualNameAddress = (new RouteHeader(new NameAddress("Str"))).getNameAddress();

    // Assert
    assertEquals("<sip:Str>", actualNameAddress.toString());
    assertNull(actualNameAddress.getDisplayName());
    assertTrue(actualNameAddress.getAddress().isSipURI());
  }

  @Test
  void testGetNameAddress2() {
    // Arrange and Act
    NameAddress actualNameAddress = (new RouteHeader(new NameAddress(new GenericURI("Uri")))).getNameAddress();

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
    NameAddress actualNameAddress = (new RouteHeader(new NameAddress("Display name", new GenericURI("Uri"))))
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
    NameAddress actualNameAddress = (new RouteHeader(nameAddress)).getNameAddress();

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
    NameAddress actualNameAddress = (new RouteHeader(new NameAddress(genericURI))).getNameAddress();

    // Assert
    assertEquals("<sips:>", actualNameAddress.toString());
    assertNull(actualNameAddress.getDisplayName());
    assertTrue(actualNameAddress.getAddress().isSipURI());
  }

  @Test
  void testGetNameAddress6() {
    // Arrange and Act
    NameAddress actualNameAddress = (new RouteHeader(new NameAddress("sips:", new GenericURI("Uri")))).getNameAddress();

    // Assert
    assertEquals("<sips:\">", actualNameAddress.toString());
    assertNull(actualNameAddress.getDisplayName());
    assertTrue(actualNameAddress.getAddress().isSipURI());
  }

  @Test
  void testGetNameAddress7() {
    // Arrange and Act
    NameAddress actualNameAddress = (new RouteHeader(new Header("Hname", ""))).getNameAddress();

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
    NameAddress actualNameAddress = (new RouteHeader(header)).getNameAddress();

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
    NameAddress actualNameAddress = (new RouteHeader(header)).getNameAddress();

    // Assert
    assertEquals("<sips:>", actualNameAddress.toString());
    assertNull(actualNameAddress.getDisplayName());
    assertTrue(actualNameAddress.getAddress().isSipURI());
  }

  @Test
  void testGetParameter() {
    // Arrange, Act and Assert
    assertNull((new RouteHeader(new NameAddress("Str"))).getParameter("Pname"));
    assertNull((new RouteHeader(new NameAddress("Display name", new GenericURI("Uri")))).getParameter("Pname"));
    assertNull((new RouteHeader(new Header("Hname", "42"))).getParameter("Pname"));
  }

  @Test
  void testGetParameter2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertNull((new RouteHeader(header)).getParameter("Pname"));
  }

  @Test
  void testGetParameterNames() {
    // Arrange, Act and Assert
    assertTrue((new RouteHeader(new NameAddress("Str"))).getParameterNames().isEmpty());
    assertTrue((new RouteHeader(new NameAddress("Display name", new GenericURI("Uri")))).getParameterNames().isEmpty());
    assertTrue((new RouteHeader(new Header("Hname", "42"))).getParameterNames().isEmpty());
  }

  @Test
  void testGetParameterNames2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertTrue((new RouteHeader(header)).getParameterNames().isEmpty());
  }

  @Test
  void testGetParameters() {
    // Arrange, Act and Assert
    assertNull((new RouteHeader(new NameAddress("Str"))).getParameters());
    assertNull((new RouteHeader(new NameAddress("Display name", new GenericURI("Uri")))).getParameters());
    assertNull((new RouteHeader(new Header("Hname", "42"))).getParameters());
  }

  @Test
  void testGetParameters2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertNull((new RouteHeader(header)).getParameters());
  }

  @Test
  void testHasParameter() {
    // Arrange, Act and Assert
    assertFalse((new RouteHeader(new NameAddress("Str"))).hasParameter("Pname"));
    assertFalse((new RouteHeader(new NameAddress("Display name", new GenericURI("Uri")))).hasParameter("Pname"));
    assertFalse((new RouteHeader(new Header("Hname", "42"))).hasParameter("Pname"));
  }

  @Test
  void testHasParameter2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertFalse((new RouteHeader(header)).hasParameter("Pname"));
  }

  @Test
  void testHasParameters() {
    // Arrange, Act and Assert
    assertFalse((new RouteHeader(new NameAddress("Str"))).hasParameters());
    assertFalse((new RouteHeader(new NameAddress("Display name", new GenericURI("Uri")))).hasParameters());
    assertFalse((new RouteHeader(new Header("Hname", "42"))).hasParameters());
  }

  @Test
  void testHasParameters2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertFalse((new RouteHeader(header)).hasParameters());
  }

  @Test
  void testIndexOfFirstSemi() {
    // Arrange, Act and Assert
    assertEquals(-1, (new RouteHeader(new NameAddress("Str"))).indexOfFirstSemi());
    assertEquals(-1, (new RouteHeader(new NameAddress("Display name", new GenericURI("Uri")))).indexOfFirstSemi());
    assertEquals(-1, (new RouteHeader(new Header("Hname", "42"))).indexOfFirstSemi());
  }

  @Test
  void testIndexOfFirstSemi2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertEquals(-1, (new RouteHeader(header)).indexOfFirstSemi());
  }

  @Test
  void testSetNameAddress() {
    // Arrange
    RouteHeader routeHeader = new RouteHeader(new NameAddress("Str"));

    // Act
    routeHeader.setNameAddress(new NameAddress("Str"));

    // Assert
    assertEquals("<sip:Str>", routeHeader.getValue());
  }

  @Test
  void testSetParameter() {
    // Arrange
    RouteHeader routeHeader = new RouteHeader(new NameAddress("Str"));

    // Act
    routeHeader.setParameter("Pname", "42");

    // Assert
    assertEquals("<sip:Str>;Pname=42", routeHeader.getValue());
  }

  @Test
  void testSetParameter2() {
    // Arrange
    RouteHeader routeHeader = new RouteHeader(new NameAddress("Display name", new GenericURI("Uri")));

    // Act
    routeHeader.setParameter("Pname", "42");

    // Assert
    assertEquals("\"Display name\" <Uri>;Pname=42", routeHeader.getValue());
  }

  @Test
  void testSetParameter3() {
    // Arrange
    RouteHeader routeHeader = new RouteHeader(new Header("Hname", "42"));

    // Act
    routeHeader.setParameter("Pname", "42");

    // Assert
    assertEquals("42;Pname=42", routeHeader.getValue());
  }

  @Test
  void testSetParameter4() {
    // Arrange
    RouteHeader routeHeader = new RouteHeader(new Header());

    // Act
    routeHeader.setParameter("Pname", "42");

    // Assert
    assertEquals(";Pname=42", routeHeader.getValue());
  }
}

