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

class FromHeaderDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");

    // Act
    FromHeader actualFromHeader = new FromHeader(genericURI);

    // Assert
    assertEquals(CoreSipHeaders.From, actualFromHeader.getName());
    assertEquals("From: Uri\r\n", actualFromHeader.toString());
    assertFalse(actualFromHeader.hasTag());
    assertEquals("Uri", actualFromHeader.getValue());
    assertNull(actualFromHeader.getTag());
    assertNull(actualFromHeader.getParameters());
    Vector parameterNames = actualFromHeader.getParameterNames();
    assertTrue(parameterNames.isEmpty());
    NameAddress nameAddress = actualFromHeader.getNameAddress();
    GenericURI address = nameAddress.getAddress();
    assertTrue(address instanceof SipURI);
    assertEquals("<sip:Uri>", nameAddress.toString());
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
    assertEquals("Uri", address.getSpecificPart());
    assertEquals("sip", address.getScheme());
    assertEquals(-1, ((SipURI) address).getPort());
    Vector parameterNames1 = address.getParameterNames();
    assertEquals(parameterNames, parameterNames1);
    assertTrue(parameterNames1.isEmpty());
    assertNull(((SipURI) address).getMaddr());
    assertEquals("Uri", ((SipURI) address).getHost());
    assertEquals("sip:Uri", address.toString());
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
    FromHeader actualFromHeader = new FromHeader(nameAddress);

    // Assert
    assertEquals(CoreSipHeaders.From, actualFromHeader.getName());
    assertEquals("From: <sip:Str>\r\n", actualFromHeader.toString());
    assertFalse(actualFromHeader.hasTag());
    assertEquals("<sip:Str>", actualFromHeader.getValue());
    assertNull(actualFromHeader.getTag());
    assertNull(actualFromHeader.getParameters());
    Vector parameterNames = actualFromHeader.getParameterNames();
    assertTrue(parameterNames.isEmpty());
    NameAddress nameAddress1 = actualFromHeader.getNameAddress();
    assertEquals(nameAddress, nameAddress1);
    assertEquals("<sip:Str>", nameAddress1.toString());
    assertNull(nameAddress1.getDisplayName());
    assertFalse(nameAddress1.hasDisplayName());
    GenericURI address = nameAddress1.getAddress();
    assertTrue(address.isSipURI());
    assertFalse(address.hasLr());
    assertEquals("Str", address.getSpecificPart());
    assertEquals("sip", address.getScheme());
    Vector parameterNames1 = address.getParameterNames();
    assertEquals(parameterNames, parameterNames1);
    assertTrue(parameterNames1.isEmpty());
    assertFalse(address.isTelURI());
    assertEquals("sip:Str", address.toString());
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
    FromHeader actualFromHeader = new FromHeader(header);

    // Assert
    assertEquals("Hname", actualFromHeader.getName());
    assertEquals("Hname: 42\r\n", actualFromHeader.toString());
    assertFalse(actualFromHeader.hasTag());
    assertEquals("42", actualFromHeader.getValue());
    assertNull(actualFromHeader.getTag());
    assertNull(actualFromHeader.getParameters());
    Vector parameterNames = actualFromHeader.getParameterNames();
    assertTrue(parameterNames.isEmpty());
    NameAddress nameAddress = actualFromHeader.getNameAddress();
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
  void testConstructor4() {
    // Arrange and Act
    FromHeader actualFromHeader = new FromHeader(new GenericURI("Uri"), "Tag");

    // Assert
    assertEquals(CoreSipHeaders.From, actualFromHeader.getName());
    assertEquals("Uri;tag=Tag", actualFromHeader.getValue());
  }

  @Test
  void testConstructor5() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter(CoreSipHeaders.From);

    // Act
    FromHeader actualFromHeader = new FromHeader(genericURI, "Tag");

    // Assert
    assertEquals(CoreSipHeaders.From, actualFromHeader.getName());
    assertEquals("Uri;From;tag=Tag", actualFromHeader.getValue());
  }

  @Test
  void testConstructor6() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter(CoreSipHeaders.From, "42");

    // Act
    FromHeader actualFromHeader = new FromHeader(genericURI, "Tag");

    // Assert
    assertEquals(CoreSipHeaders.From, actualFromHeader.getName());
    assertEquals("Uri;From=42;tag=Tag", actualFromHeader.getValue());
  }

  @Test
  void testConstructor7() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter(CoreSipHeaders.From);
    genericURI.addParameter(CoreSipHeaders.From);

    // Act
    FromHeader actualFromHeader = new FromHeader(genericURI, "Tag");

    // Assert
    assertEquals(CoreSipHeaders.From, actualFromHeader.getName());
    assertEquals("Uri;From;From;tag=Tag", actualFromHeader.getValue());
  }

  @Test
  void testConstructor8() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("tag");

    // Act
    FromHeader actualFromHeader = new FromHeader(genericURI, "Tag");

    // Assert
    assertEquals(CoreSipHeaders.From, actualFromHeader.getName());
    assertEquals("Uri;tag=Tag", actualFromHeader.getValue());
  }

  @Test
  void testConstructor9() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("");

    // Act
    FromHeader actualFromHeader = new FromHeader(genericURI, "Tag");

    // Assert
    assertEquals(CoreSipHeaders.From, actualFromHeader.getName());
    assertEquals("Uri;;tag=Tag", actualFromHeader.getValue());
  }

  @Test
  void testConstructor10() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("", "42");

    // Act
    FromHeader actualFromHeader = new FromHeader(genericURI, "Tag");

    // Assert
    assertEquals(CoreSipHeaders.From, actualFromHeader.getName());
    assertEquals("Uri;=42;tag=Tag", actualFromHeader.getValue());
  }

  @Test
  void testConstructor11() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("tag");
    genericURI.addParameter(CoreSipHeaders.From);

    // Act
    FromHeader actualFromHeader = new FromHeader(genericURI, "Tag");

    // Assert
    assertEquals(CoreSipHeaders.From, actualFromHeader.getName());
    assertEquals("Uri;From;tag=Tag", actualFromHeader.getValue());
  }

  @Test
  void testConstructor12() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter(CoreSipHeaders.From);
    genericURI.addParameter("tag");

    // Act
    FromHeader actualFromHeader = new FromHeader(genericURI, "Tag");

    // Assert
    assertEquals(CoreSipHeaders.From, actualFromHeader.getName());
    assertEquals("Uri;From;tag=Tag", actualFromHeader.getValue());
  }

  @Test
  void testConstructor13() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("", "");

    // Act
    FromHeader actualFromHeader = new FromHeader(genericURI, "Tag");

    // Assert
    assertEquals(CoreSipHeaders.From, actualFromHeader.getName());
    assertEquals("Uri;=;tag=Tag", actualFromHeader.getValue());
  }

  @Test
  void testConstructor14() {
    // Arrange and Act
    FromHeader actualFromHeader = new FromHeader(new NameAddress("Str"), "Tag");

    // Assert
    assertEquals(CoreSipHeaders.From, actualFromHeader.getName());
    assertEquals("<sip:Str>;tag=Tag", actualFromHeader.getValue());
  }

  @Test
  void testConstructor15() {
    // Arrange and Act
    FromHeader actualFromHeader = new FromHeader(new NameAddress("Display name", new GenericURI("Uri")), "Tag");

    // Assert
    assertEquals(CoreSipHeaders.From, actualFromHeader.getName());
    assertEquals("\"Display name\" <Uri>;tag=Tag", actualFromHeader.getValue());
  }

  @Test
  void testConstructor16() {
    // Arrange and Act
    FromHeader actualFromHeader = new FromHeader(new NameAddress("\" <", new GenericURI("Uri")), "Tag");

    // Assert
    assertEquals(CoreSipHeaders.From, actualFromHeader.getName());
    assertEquals("\"\" <\" <Uri>;tag=Tag", actualFromHeader.getValue());
  }

  @Test
  void testConstructor17() {
    // Arrange and Act
    FromHeader actualFromHeader = new FromHeader(new NameAddress("Display name", null), "Tag");

    // Assert
    assertEquals(CoreSipHeaders.From, actualFromHeader.getName());
    assertEquals("\"Display name\" <null>;tag=Tag", actualFromHeader.getValue());
  }

  @Test
  void testGetNameAddress() {
    // Arrange and Act
    NameAddress actualNameAddress = (new FromHeader(new Header("Hname", "42"))).getNameAddress();

    // Assert
    assertEquals("<sip:42>", actualNameAddress.toString());
    assertFalse(actualNameAddress.hasDisplayName());
    GenericURI address = actualNameAddress.getAddress();
    assertFalse(address.isTelURI());
    assertFalse(((SipURI) address).isSecure());
  }

  @Test
  void testGetNameAddress2() {
    // Arrange and Act
    NameAddress actualNameAddress = (new FromHeader(new Header("Hname", "sips:"))).getNameAddress();

    // Assert
    assertEquals("<sips:>", actualNameAddress.toString());
    assertNull(actualNameAddress.getDisplayName());
    assertTrue(actualNameAddress.getAddress().isSipURI());
  }

  @Test
  void testGetNameAddress3() {
    // Arrange and Act
    NameAddress actualNameAddress = (new FromHeader(new Header("Hname", ""))).getNameAddress();

    // Assert
    assertEquals("<sip:>", actualNameAddress.toString());
    assertFalse(actualNameAddress.hasDisplayName());
    GenericURI address = actualNameAddress.getAddress();
    assertFalse(address.isTelURI());
    assertFalse(((SipURI) address).isSecure());
  }

  @Test
  void testGetNameAddress4() {
    // Arrange and Act
    NameAddress actualNameAddress = (new FromHeader(new GenericURI("Uri"))).getNameAddress();

    // Assert
    assertEquals("<sip:Uri>", actualNameAddress.toString());
    assertFalse(actualNameAddress.hasDisplayName());
    GenericURI address = actualNameAddress.getAddress();
    assertFalse(address.isTelURI());
    assertFalse(((SipURI) address).isSecure());
  }

  @Test
  void testGetNameAddress5() {
    // Arrange and Act
    NameAddress actualNameAddress = (new FromHeader(new NameAddress("Str"))).getNameAddress();

    // Assert
    assertEquals("<sip:Str>", actualNameAddress.toString());
    assertNull(actualNameAddress.getDisplayName());
    assertTrue(actualNameAddress.getAddress().isSipURI());
  }

  @Test
  void testGetNameAddress6() {
    // Arrange and Act
    NameAddress actualNameAddress = (new FromHeader(new GenericURI("Uri"), "Tag")).getNameAddress();

    // Assert
    assertEquals("<sip:Uri>", actualNameAddress.toString());
    assertFalse(actualNameAddress.hasDisplayName());
    GenericURI address = actualNameAddress.getAddress();
    assertFalse(address.isTelURI());
    assertFalse(((SipURI) address).isSecure());
  }

  @Test
  void testGetNameAddress7() {
    // Arrange and Act
    NameAddress actualNameAddress = (new FromHeader(new NameAddress("Str"), "Tag")).getNameAddress();

    // Assert
    assertEquals("<sip:Str>", actualNameAddress.toString());
    assertNull(actualNameAddress.getDisplayName());
    assertTrue(actualNameAddress.getAddress().isSipURI());
  }

  @Test
  void testGetNameAddress8() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addLr();

    // Act
    NameAddress actualNameAddress = (new FromHeader(genericURI)).getNameAddress();

    // Assert
    assertEquals("<sip:Uri;lr>", actualNameAddress.toString());
    assertFalse(actualNameAddress.hasDisplayName());
    GenericURI address = actualNameAddress.getAddress();
    assertFalse(address.isTelURI());
    assertFalse(((SipURI) address).isSecure());
  }

  @Test
  void testGetNameAddress9() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addLr();
    genericURI.addLr();

    // Act
    NameAddress actualNameAddress = (new FromHeader(genericURI)).getNameAddress();

    // Assert
    assertEquals("<sip:Uri;lr;lr>", actualNameAddress.toString());
    assertFalse(actualNameAddress.hasDisplayName());
    GenericURI address = actualNameAddress.getAddress();
    assertFalse(address.isTelURI());
    assertFalse(((SipURI) address).isSecure());
  }

  @Test
  void testGetNameAddress10() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("", "42");

    // Act
    NameAddress actualNameAddress = (new FromHeader(genericURI)).getNameAddress();

    // Assert
    assertEquals("<sip:Uri;=42>", actualNameAddress.toString());
    assertFalse(actualNameAddress.hasDisplayName());
    GenericURI address = actualNameAddress.getAddress();
    assertFalse(address.isTelURI());
    assertFalse(((SipURI) address).isSecure());
  }

  @Test
  void testGetNameAddress11() {
    // Arrange and Act
    NameAddress actualNameAddress = (new FromHeader(new NameAddress("Display name", new GenericURI("Uri"))))
        .getNameAddress();

    // Assert
    assertEquals("<sip:\"Display>", actualNameAddress.toString());
    assertFalse(actualNameAddress.hasDisplayName());
    GenericURI address = actualNameAddress.getAddress();
    assertFalse(address.isTelURI());
    assertFalse(((SipURI) address).isSecure());
  }

  @Test
  void testGetNameAddress12() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addLr();

    // Act
    NameAddress actualNameAddress = (new FromHeader(genericURI, "Tag")).getNameAddress();

    // Assert
    assertEquals("<sip:Uri;lr>", actualNameAddress.toString());
    assertFalse(actualNameAddress.hasDisplayName());
    GenericURI address = actualNameAddress.getAddress();
    assertFalse(address.isTelURI());
    assertFalse(((SipURI) address).isSecure());
  }

  @Test
  void testGetNameAddress13() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("", "");

    // Act
    NameAddress actualNameAddress = (new FromHeader(genericURI)).getNameAddress();

    // Assert
    assertEquals("<sip:Uri;=>", actualNameAddress.toString());
    assertFalse(actualNameAddress.hasDisplayName());
    GenericURI address = actualNameAddress.getAddress();
    assertFalse(address.isTelURI());
    assertFalse(((SipURI) address).isSecure());
  }

  @Test
  void testGetNameAddress14() {
    // Arrange
    NameAddress nameAddress = new NameAddress("Str");
    nameAddress.setDisplayName("Display name");

    // Act
    NameAddress actualNameAddress = (new FromHeader(nameAddress)).getNameAddress();

    // Assert
    assertEquals("\"Display name\" <sip:Str>", actualNameAddress.toString());
    assertEquals("Display name", actualNameAddress.getDisplayName());
    assertTrue(actualNameAddress.getAddress().isSipURI());
  }

  @Test
  void testGetParameter() {
    // Arrange, Act and Assert
    assertNull((new FromHeader(new Header("Hname", "42"))).getParameter("Pname"));
    assertNull((new FromHeader(new GenericURI("Uri"))).getParameter("Pname"));
    assertNull((new FromHeader(new NameAddress("Str"))).getParameter("Pname"));
    assertNull((new FromHeader(new GenericURI("Uri"), "Tag")).getParameter("Pname"));
    assertNull((new FromHeader(new NameAddress("Display name", new GenericURI("Uri")))).getParameter("Pname"));
  }

  @Test
  void testGetParameter2() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("Name");

    // Act and Assert
    assertNull((new FromHeader(genericURI)).getParameter("Pname"));
  }

  @Test
  void testGetParameter3() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("Name");
    genericURI.addParameter("Name");

    // Act and Assert
    assertNull((new FromHeader(genericURI)).getParameter("Pname"));
  }

  @Test
  void testGetParameter4() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("Pname");
    genericURI.addParameter("Pname");

    // Act and Assert
    assertNull((new FromHeader(genericURI)).getParameter("Pname"));
  }

  @Test
  void testGetParameter5() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("Pname", "42");
    genericURI.addParameter("Pname");

    // Act and Assert
    assertEquals("42", (new FromHeader(genericURI)).getParameter("Pname"));
  }

  @Test
  void testGetParameter6() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("", "42");

    // Act and Assert
    assertNull((new FromHeader(genericURI)).getParameter("Pname"));
  }

  @Test
  void testGetParameter7() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("Pname", "");
    genericURI.addParameter("Pname");

    // Act and Assert
    assertEquals("Pname", (new FromHeader(genericURI)).getParameter("Pname"));
  }

  @Test
  void testGetParameter8() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("", "");

    // Act and Assert
    assertNull((new FromHeader(genericURI)).getParameter("Pname"));
  }

  @Test
  void testGetParameter9() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("Pname", "");
    genericURI.addParameter("");

    // Act and Assert
    assertEquals("", (new FromHeader(genericURI)).getParameter("Pname"));
  }

  @Test
  void testGetParameterNames() {
    // Arrange, Act and Assert
    assertTrue((new FromHeader(new Header("Hname", "42"))).getParameterNames().isEmpty());
    assertTrue((new FromHeader(new GenericURI("Uri"))).getParameterNames().isEmpty());
    assertTrue((new FromHeader(new NameAddress("Str"))).getParameterNames().isEmpty());
    assertTrue((new FromHeader(new NameAddress("Display name", new GenericURI("Uri")))).getParameterNames().isEmpty());
  }

  @Test
  void testGetParameterNames2() {
    // Arrange and Act
    Vector actualParameterNames = (new FromHeader(new GenericURI("Uri"), "Tag")).getParameterNames();

    // Assert
    assertEquals(1, actualParameterNames.size());
    assertEquals("tag", actualParameterNames.get(0));
  }

  @Test
  void testGetParameterNames3() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("Name");

    // Act
    Vector actualParameterNames = (new FromHeader(genericURI)).getParameterNames();

    // Assert
    assertEquals(1, actualParameterNames.size());
    assertEquals("Name", actualParameterNames.get(0));
  }

  @Test
  void testGetParameterNames4() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("Name");
    genericURI.addParameter("Name");

    // Act
    Vector actualParameterNames = (new FromHeader(genericURI)).getParameterNames();

    // Assert
    assertEquals(2, actualParameterNames.size());
    assertEquals("Name", actualParameterNames.get(0));
    assertEquals("Name", actualParameterNames.get(1));
  }

  @Test
  void testGetParameterNames5() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("");

    // Act and Assert
    assertTrue((new FromHeader(genericURI)).getParameterNames().isEmpty());
  }

  @Test
  void testGetParameterNames6() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("", "42");

    // Act
    Vector actualParameterNames = (new FromHeader(genericURI)).getParameterNames();

    // Assert
    assertEquals(1, actualParameterNames.size());
    assertEquals("42", actualParameterNames.get(0));
  }

  @Test
  void testGetParameterNames7() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("", "");

    // Act and Assert
    assertTrue((new FromHeader(genericURI)).getParameterNames().isEmpty());
  }

  @Test
  void testGetParameters() {
    // Arrange, Act and Assert
    assertNull((new FromHeader(new Header("Hname", "42"))).getParameters());
    assertNull((new FromHeader(new GenericURI("Uri"))).getParameters());
    assertNull((new FromHeader(new NameAddress("Str"))).getParameters());
    assertEquals("tag=Tag", (new FromHeader(new GenericURI("Uri"), "Tag")).getParameters());
    assertNull((new FromHeader(new NameAddress("Display name", new GenericURI("Uri")))).getParameters());
  }

  @Test
  void testGetTag() {
    // Arrange, Act and Assert
    assertNull((new FromHeader(new Header("Hname", "42"))).getTag());
    assertNull((new FromHeader(new GenericURI("Uri"))).getTag());
    assertNull((new FromHeader(new NameAddress("Str"))).getTag());
    assertEquals("Tag", (new FromHeader(new GenericURI("Uri"), "Tag")).getTag());
    assertNull((new FromHeader(new NameAddress("Display name", new GenericURI("Uri")))).getTag());
  }

  @Test
  void testGetTag2() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("tag");
    genericURI.addParameter("tag");

    // Act and Assert
    assertNull((new FromHeader(genericURI)).getTag());
  }

  @Test
  void testGetTag3() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("tag", "42");
    genericURI.addParameter("tag");

    // Act and Assert
    assertEquals("42", (new FromHeader(genericURI)).getTag());
  }

  @Test
  void testGetTag4() {
    // Arrange
    GenericURI genericURI = new GenericURI(new GenericURI("Uri"));
    genericURI.addParameter("Name");

    // Act and Assert
    assertNull((new FromHeader(genericURI)).getTag());
  }

  @Test
  void testGetTag5() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("");
    genericURI.addParameter("");

    // Act and Assert
    assertNull((new FromHeader(genericURI)).getTag());
  }

  @Test
  void testGetTag6() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("tag", "");
    genericURI.addParameter("tag");

    // Act and Assert
    assertEquals("tag", (new FromHeader(genericURI)).getTag());
  }

  @Test
  void testGetTag7() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("tag", "");

    // Act and Assert
    assertEquals("", (new FromHeader(genericURI)).getTag());
  }

  @Test
  void testHasParameter() {
    // Arrange, Act and Assert
    assertFalse((new FromHeader(new Header("Hname", "42"))).hasParameter("Pname"));
    assertFalse((new FromHeader(new GenericURI("Uri"))).hasParameter("Pname"));
    assertFalse((new FromHeader(new NameAddress("Str"))).hasParameter("Pname"));
    assertFalse((new FromHeader(new GenericURI("Uri"), "Tag")).hasParameter("Pname"));
    assertFalse((new FromHeader(new NameAddress("Display name", new GenericURI("Uri")))).hasParameter("Pname"));
  }

  @Test
  void testHasParameter2() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("Name");

    // Act and Assert
    assertFalse((new FromHeader(genericURI)).hasParameter("Pname"));
  }

  @Test
  void testHasParameter3() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("Name");
    genericURI.addParameter("Name");

    // Act and Assert
    assertFalse((new FromHeader(genericURI)).hasParameter("Pname"));
  }

  @Test
  void testHasParameter4() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("Pname");

    // Act and Assert
    assertTrue((new FromHeader(genericURI)).hasParameter("Pname"));
  }

  @Test
  void testHasParameter5() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("");

    // Act and Assert
    assertFalse((new FromHeader(genericURI)).hasParameter("Pname"));
  }

  @Test
  void testHasParameter6() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("", "42");

    // Act and Assert
    assertFalse((new FromHeader(genericURI)).hasParameter("Pname"));
  }

  @Test
  void testHasParameter7() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("", "");

    // Act and Assert
    assertFalse((new FromHeader(genericURI)).hasParameter("Pname"));
  }

  @Test
  void testHasParameters() {
    // Arrange, Act and Assert
    assertFalse((new FromHeader(new Header("Hname", "42"))).hasParameters());
    assertFalse((new FromHeader(new GenericURI("Uri"))).hasParameters());
    assertFalse((new FromHeader(new NameAddress("Str"))).hasParameters());
    assertTrue((new FromHeader(new GenericURI("Uri"), "Tag")).hasParameters());
    assertFalse((new FromHeader(new NameAddress("Display name", new GenericURI("Uri")))).hasParameters());
  }

  @Test
  void testHasTag() {
    // Arrange, Act and Assert
    assertFalse((new FromHeader(new Header("Hname", "42"))).hasTag());
    assertFalse((new FromHeader(new GenericURI("Uri"))).hasTag());
    assertFalse((new FromHeader(new NameAddress("Str"))).hasTag());
    assertTrue((new FromHeader(new GenericURI("Uri"), "Tag")).hasTag());
    assertFalse((new FromHeader(new NameAddress("Display name", new GenericURI("Uri")))).hasTag());
  }

  @Test
  void testHasTag2() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("tag");

    // Act and Assert
    assertTrue((new FromHeader(genericURI)).hasTag());
  }

  @Test
  void testHasTag3() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addLr();

    // Act and Assert
    assertFalse((new FromHeader(genericURI)).hasTag());
  }

  @Test
  void testHasTag4() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addLr();
    genericURI.addParameter("tag");

    // Act and Assert
    assertTrue((new FromHeader(genericURI)).hasTag());
  }

  @Test
  void testHasTag5() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("");

    // Act and Assert
    assertFalse((new FromHeader(genericURI)).hasTag());
  }

  @Test
  void testHasTag6() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("", "42");

    // Act and Assert
    assertFalse((new FromHeader(genericURI)).hasTag());
  }

  @Test
  void testHasTag7() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("");
    genericURI.addParameter("");

    // Act and Assert
    assertFalse((new FromHeader(genericURI)).hasTag());
  }

  @Test
  void testIndexOfFirstSemi() {
    // Arrange, Act and Assert
    assertEquals(-1, (new FromHeader(new Header("Hname", "42"))).indexOfFirstSemi());
    assertEquals(-1, (new FromHeader(new GenericURI("Uri"))).indexOfFirstSemi());
    assertEquals(-1, (new FromHeader(new NameAddress("Str"))).indexOfFirstSemi());
    assertEquals(3, (new FromHeader(new GenericURI("Uri"), "Tag")).indexOfFirstSemi());
    assertEquals(-1, (new FromHeader(new NameAddress("Display name", new GenericURI("Uri")))).indexOfFirstSemi());
  }

  @Test
  void testRemoveParameter() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("Pname");
    FromHeader fromHeader = new FromHeader(genericURI);

    // Act
    fromHeader.removeParameter("Pname");

    // Assert
    assertEquals("Uri", fromHeader.getValue());
  }

  @Test
  void testRemoveParameter2() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("Pname");
    genericURI.addParameter("Name");
    FromHeader fromHeader = new FromHeader(genericURI);

    // Act
    fromHeader.removeParameter("Pname");

    // Assert
    assertEquals("Uri;Name", fromHeader.getValue());
  }

  @Test
  void testRemoveParameters() {
    // Arrange
    FromHeader fromHeader = new FromHeader(new GenericURI("Uri"), "Tag");

    // Act
    fromHeader.removeParameters();

    // Assert
    assertEquals("Uri", fromHeader.getValue());
  }

  @Test
  void testSetNameAddress() {
    // Arrange
    FromHeader fromHeader = new FromHeader(new Header("Hname", "42"));

    // Act
    fromHeader.setNameAddress(new NameAddress("Str"));

    // Assert
    assertEquals("<sip:Str>", fromHeader.getValue());
  }

  @Test
  void testSetNameAddress2() {
    // Arrange
    FromHeader fromHeader = new FromHeader(new Header("Hname", "42"));

    // Act
    fromHeader.setNameAddress(new NameAddress((GenericURI) null));

    // Assert
    assertEquals("<null>", fromHeader.getValue());
  }

  @Test
  void testSetParameter() {
    // Arrange
    FromHeader fromHeader = new FromHeader(new Header("Hname", "42"));

    // Act
    fromHeader.setParameter("Pname", "42");

    // Assert
    assertEquals("42;Pname=42", fromHeader.getValue());
  }

  @Test
  void testSetParameter2() {
    // Arrange
    FromHeader fromHeader = new FromHeader(new Header());

    // Act
    fromHeader.setParameter("Pname", "42");

    // Assert
    assertEquals(";Pname=42", fromHeader.getValue());
  }

  @Test
  void testSetParameter3() {
    // Arrange
    FromHeader fromHeader = new FromHeader(new NameAddress("Str"));

    // Act
    fromHeader.setParameter("Pname", "42");

    // Assert
    assertEquals("<sip:Str>;Pname=42", fromHeader.getValue());
  }

  @Test
  void testSetParameter4() {
    // Arrange
    FromHeader fromHeader = new FromHeader(new GenericURI("Uri"), "Tag");

    // Act
    fromHeader.setParameter("Pname", "42");

    // Assert
    assertEquals("Uri;tag=Tag;Pname=42", fromHeader.getValue());
  }

  @Test
  void testSetParameter5() {
    // Arrange
    FromHeader fromHeader = new FromHeader(new NameAddress("Display name", new GenericURI("Uri")));

    // Act
    fromHeader.setParameter("Pname", "42");

    // Assert
    assertEquals("\"Display name\" <Uri>;Pname=42", fromHeader.getValue());
  }

  @Test
  void testSetParameter6() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addLr();
    FromHeader fromHeader = new FromHeader(genericURI, "Tag");

    // Act
    fromHeader.setParameter("Pname", "42");

    // Assert
    assertEquals("Uri;lr;tag=Tag;Pname=42", fromHeader.getValue());
  }

  @Test
  void testSetParameter7() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("");
    FromHeader fromHeader = new FromHeader(genericURI, "Tag");

    // Act
    fromHeader.setParameter("Pname", "42");

    // Assert
    assertEquals("Uri;;tag=Tag;Pname=42", fromHeader.getValue());
  }

  @Test
  void testSetParameter8() {
    // Arrange
    FromHeader fromHeader = new FromHeader(new GenericURI("Uri"), "Tag");

    // Act
    fromHeader.setParameter("tag", "42");

    // Assert
    assertEquals("Uri;tag=42", fromHeader.getValue());
  }

  @Test
  void testSetParameter9() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addLr();
    FromHeader fromHeader = new FromHeader(genericURI, "Tag");

    // Act
    fromHeader.setParameter("lr", "42");

    // Assert
    assertEquals("Uri;tag=Tag;lr=42", fromHeader.getValue());
  }

  @Test
  void testSetParameter10() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addLr();
    FromHeader fromHeader = new FromHeader(genericURI, "Tag");

    // Act
    fromHeader.setParameter("tag", "42");

    // Assert
    assertEquals("Uri;lr;tag=42", fromHeader.getValue());
  }

  @Test
  void testSetTag() {
    // Arrange
    FromHeader fromHeader = new FromHeader(new Header("Hname", "42"));

    // Act
    fromHeader.setTag("Tag");

    // Assert
    assertEquals("42;tag=Tag", fromHeader.getValue());
  }

  @Test
  void testSetTag2() {
    // Arrange
    FromHeader fromHeader = new FromHeader(new Header());

    // Act
    fromHeader.setTag("Tag");

    // Assert
    assertEquals(";tag=Tag", fromHeader.getValue());
  }

  @Test
  void testSetTag3() {
    // Arrange
    FromHeader fromHeader = new FromHeader(new NameAddress("Str"));

    // Act
    fromHeader.setTag("Tag");

    // Assert
    assertEquals("<sip:Str>;tag=Tag", fromHeader.getValue());
  }

  @Test
  void testSetTag4() {
    // Arrange
    FromHeader fromHeader = new FromHeader(new GenericURI("Uri"), "Tag");

    // Act
    fromHeader.setTag("Tag");

    // Assert
    assertEquals("Uri;tag=Tag", fromHeader.getValue());
  }

  @Test
  void testSetTag5() {
    // Arrange
    FromHeader fromHeader = new FromHeader(new NameAddress("Display name", new GenericURI("Uri")));

    // Act
    fromHeader.setTag("Tag");

    // Assert
    assertEquals("\"Display name\" <Uri>;tag=Tag", fromHeader.getValue());
  }

  @Test
  void testSetTag6() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addLr();
    FromHeader fromHeader = new FromHeader(genericURI, "Tag");

    // Act
    fromHeader.setTag("Tag");

    // Assert
    assertEquals("Uri;lr;tag=Tag", fromHeader.getValue());
  }

  @Test
  void testSetTag7() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("");
    FromHeader fromHeader = new FromHeader(genericURI, "Tag");

    // Act
    fromHeader.setTag("Tag");

    // Assert
    assertEquals("Uri;tag=Tag", fromHeader.getValue());
  }
}

