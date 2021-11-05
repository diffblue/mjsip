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

class ReferredByHeaderDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange
    Header header = new Header("Hname", "42");

    // Act
    ReferredByHeader actualReferredByHeader = new ReferredByHeader(header);

    // Assert
    assertEquals("Hname", actualReferredByHeader.getName());
    assertEquals("Hname: 42\r\n", actualReferredByHeader.toString());
    assertEquals("42", actualReferredByHeader.getValue());
    assertNull(actualReferredByHeader.getParameters());
    Vector parameterNames = actualReferredByHeader.getParameterNames();
    assertTrue(parameterNames.isEmpty());
    NameAddress nameAddress = actualReferredByHeader.getNameAddress();
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
    ReferredByHeader actualReferredByHeader = new ReferredByHeader(new NameAddress("Str"));

    // Assert
    assertEquals(SipHeaders.Referred_By, actualReferredByHeader.getName());
    assertEquals("<sip:Str>", actualReferredByHeader.getValue());
  }

  @Test
  void testConstructor3() {
    // Arrange and Act
    ReferredByHeader actualReferredByHeader = new ReferredByHeader(new SipURI("Uri"));

    // Assert
    assertEquals(SipHeaders.Referred_By, actualReferredByHeader.getName());
    assertEquals("sip:Uri", actualReferredByHeader.getValue());
  }

  @Test
  void testGetNameAddress() {
    // Arrange and Act
    NameAddress actualNameAddress = (new ReferredByHeader(new NameAddress("Str"))).getNameAddress();

    // Assert
    assertEquals("<sip:Str>", actualNameAddress.toString());
    assertNull(actualNameAddress.getDisplayName());
    assertTrue(actualNameAddress.getAddress().isSipURI());
  }

  @Test
  void testGetNameAddress2() {
    // Arrange and Act
    NameAddress actualNameAddress = (new ReferredByHeader(new NameAddress(new GenericURI("Uri")))).getNameAddress();

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
    NameAddress actualNameAddress = (new ReferredByHeader(new NameAddress("Display name", new GenericURI("Uri"))))
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
    // Arrange and Act
    NameAddress actualNameAddress = (new ReferredByHeader(new SipURI("Uri"))).getNameAddress();

    // Assert
    assertEquals("<sip:Uri>", actualNameAddress.toString());
    assertNull(actualNameAddress.getDisplayName());
    assertTrue(actualNameAddress.getAddress().isSipURI());
  }

  @Test
  void testGetNameAddress5() {
    // Arrange
    NameAddress nameAddress = new NameAddress("Str");
    nameAddress.setDisplayName("Display name");

    // Act
    NameAddress actualNameAddress = (new ReferredByHeader(nameAddress)).getNameAddress();

    // Assert
    assertEquals("\"Display name\" <sip:Str>", actualNameAddress.toString());
    assertEquals("Display name", actualNameAddress.getDisplayName());
    assertTrue(actualNameAddress.getAddress().isSipURI());
  }

  @Test
  void testGetNameAddress6() {
    // Arrange and Act
    NameAddress actualNameAddress = (new ReferredByHeader(new NameAddress("sips:", new GenericURI("Uri"))))
        .getNameAddress();

    // Assert
    assertEquals("<sips:\">", actualNameAddress.toString());
    assertNull(actualNameAddress.getDisplayName());
    assertTrue(actualNameAddress.getAddress().isSipURI());
  }

  @Test
  void testGetNameAddress7() {
    // Arrange and Act
    NameAddress actualNameAddress = (new ReferredByHeader(new Header("Hname", ""))).getNameAddress();

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
    NameAddress actualNameAddress = (new ReferredByHeader(header)).getNameAddress();

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
    assertNull((new ReferredByHeader(new NameAddress("Str"))).getParameter("Pname"));
    assertNull((new ReferredByHeader(new NameAddress("Display name", new GenericURI("Uri")))).getParameter("Pname"));
    assertNull((new ReferredByHeader(new SipURI("Uri"))).getParameter("Pname"));
  }

  @Test
  void testGetParameter2() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addMaddr("42 Main St");

    // Act and Assert
    assertNull((new ReferredByHeader(sipURI)).getParameter("Pname"));
  }

  @Test
  void testGetParameter3() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addParameter("Name");

    // Act and Assert
    assertNull((new ReferredByHeader(sipURI)).getParameter("Pname"));
  }

  @Test
  void testGetParameter4() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addMaddr("42 Main St");
    sipURI.addMaddr("42 Main St");

    // Act and Assert
    assertNull((new ReferredByHeader(sipURI)).getParameter("Pname"));
  }

  @Test
  void testGetParameter5() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addParameter("Pname");
    sipURI.addParameter("Pname");

    // Act and Assert
    assertNull((new ReferredByHeader(sipURI)).getParameter("Pname"));
  }

  @Test
  void testGetParameter6() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addParameter("Pname", "42");
    sipURI.addParameter("Pname");

    // Act and Assert
    assertEquals("42", (new ReferredByHeader(sipURI)).getParameter("Pname"));
  }

  @Test
  void testGetParameter7() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addParameter("", "42");

    // Act and Assert
    assertNull((new ReferredByHeader(sipURI)).getParameter("Pname"));
  }

  @Test
  void testGetParameter8() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addParameter("Pname", "");
    sipURI.addParameter("Pname");

    // Act and Assert
    assertEquals("Pname", (new ReferredByHeader(sipURI)).getParameter("Pname"));
  }

  @Test
  void testGetParameterNames() {
    // Arrange, Act and Assert
    assertTrue((new ReferredByHeader(new NameAddress("Str"))).getParameterNames().isEmpty());
    assertTrue(
        (new ReferredByHeader(new NameAddress("Display name", new GenericURI("Uri")))).getParameterNames().isEmpty());
    assertTrue((new ReferredByHeader(new SipURI("Uri"))).getParameterNames().isEmpty());
  }

  @Test
  void testGetParameterNames2() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addMaddr("42 Main St");

    // Act
    Vector actualParameterNames = (new ReferredByHeader(sipURI)).getParameterNames();

    // Assert
    assertEquals(1, actualParameterNames.size());
    assertEquals("maddr", actualParameterNames.get(0));
  }

  @Test
  void testGetParameterNames3() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addParameter("Name");

    // Act
    Vector actualParameterNames = (new ReferredByHeader(sipURI)).getParameterNames();

    // Assert
    assertEquals(1, actualParameterNames.size());
    assertEquals("Name", actualParameterNames.get(0));
  }

  @Test
  void testGetParameterNames4() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addMaddr("42 Main St");
    sipURI.addMaddr("42 Main St");

    // Act
    Vector actualParameterNames = (new ReferredByHeader(sipURI)).getParameterNames();

    // Assert
    assertEquals(2, actualParameterNames.size());
    assertEquals("maddr", actualParameterNames.get(0));
    assertEquals("maddr", actualParameterNames.get(1));
  }

  @Test
  void testGetParameterNames5() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addParameter("");

    // Act and Assert
    assertTrue((new ReferredByHeader(sipURI)).getParameterNames().isEmpty());
  }

  @Test
  void testGetParameterNames6() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addParameter("", "42");

    // Act
    Vector actualParameterNames = (new ReferredByHeader(sipURI)).getParameterNames();

    // Assert
    assertEquals(1, actualParameterNames.size());
    assertEquals("42", actualParameterNames.get(0));
  }

  @Test
  void testGetParameterNames7() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addParameter("");
    sipURI.addParameter("");

    // Act and Assert
    assertTrue((new ReferredByHeader(sipURI)).getParameterNames().isEmpty());
  }

  @Test
  void testGetParameters() {
    // Arrange, Act and Assert
    assertNull((new ReferredByHeader(new NameAddress("Str"))).getParameters());
    assertNull((new ReferredByHeader(new NameAddress("Display name", new GenericURI("Uri")))).getParameters());
    assertNull((new ReferredByHeader(new SipURI("Uri"))).getParameters());
  }

  @Test
  void testGetParameters2() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addMaddr("42 Main St");

    // Act and Assert
    assertEquals("maddr=42 Main St", (new ReferredByHeader(sipURI)).getParameters());
  }

  @Test
  void testGetParameters3() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertNull((new ReferredByHeader(header)).getParameters());
  }

  @Test
  void testHasParameter() {
    // Arrange, Act and Assert
    assertFalse((new ReferredByHeader(new NameAddress("Str"))).hasParameter("Pname"));
    assertFalse((new ReferredByHeader(new NameAddress("Display name", new GenericURI("Uri")))).hasParameter("Pname"));
    assertFalse((new ReferredByHeader(new SipURI("Uri"))).hasParameter("Pname"));
  }

  @Test
  void testHasParameter2() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addMaddr("42 Main St");

    // Act and Assert
    assertFalse((new ReferredByHeader(sipURI)).hasParameter("Pname"));
  }

  @Test
  void testHasParameter3() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addParameter("Name");

    // Act and Assert
    assertFalse((new ReferredByHeader(sipURI)).hasParameter("Pname"));
  }

  @Test
  void testHasParameter4() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addMaddr("42 Main St");
    sipURI.addMaddr("42 Main St");

    // Act and Assert
    assertFalse((new ReferredByHeader(sipURI)).hasParameter("Pname"));
  }

  @Test
  void testHasParameter5() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addParameter("Pname");

    // Act and Assert
    assertTrue((new ReferredByHeader(sipURI)).hasParameter("Pname"));
  }

  @Test
  void testHasParameter6() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addParameter("");

    // Act and Assert
    assertFalse((new ReferredByHeader(sipURI)).hasParameter("Pname"));
  }

  @Test
  void testHasParameter7() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addParameter("", "42");

    // Act and Assert
    assertFalse((new ReferredByHeader(sipURI)).hasParameter("Pname"));
  }

  @Test
  void testHasParameter8() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addParameter("");
    sipURI.addParameter("");

    // Act and Assert
    assertFalse((new ReferredByHeader(sipURI)).hasParameter("Pname"));
  }

  @Test
  void testHasParameters() {
    // Arrange, Act and Assert
    assertFalse((new ReferredByHeader(new NameAddress("Str"))).hasParameters());
    assertFalse((new ReferredByHeader(new NameAddress("Display name", new GenericURI("Uri")))).hasParameters());
    assertFalse((new ReferredByHeader(new SipURI("Uri"))).hasParameters());
  }

  @Test
  void testHasParameters2() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addMaddr("42 Main St");

    // Act and Assert
    assertTrue((new ReferredByHeader(sipURI)).hasParameters());
  }

  @Test
  void testHasParameters3() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertFalse((new ReferredByHeader(header)).hasParameters());
  }

  @Test
  void testIndexOfFirstSemi() {
    // Arrange, Act and Assert
    assertEquals(-1, (new ReferredByHeader(new NameAddress("Str"))).indexOfFirstSemi());
    assertEquals(-1, (new ReferredByHeader(new NameAddress("Display name", new GenericURI("Uri")))).indexOfFirstSemi());
    assertEquals(-1, (new ReferredByHeader(new SipURI("Uri"))).indexOfFirstSemi());
  }

  @Test
  void testIndexOfFirstSemi2() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addMaddr("42 Main St");

    // Act and Assert
    assertEquals(7, (new ReferredByHeader(sipURI)).indexOfFirstSemi());
  }

  @Test
  void testIndexOfFirstSemi3() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertEquals(-1, (new ReferredByHeader(header)).indexOfFirstSemi());
  }

  @Test
  void testRemoveParameter() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addParameter("Pname");
    ReferredByHeader referredByHeader = new ReferredByHeader(sipURI);

    // Act
    referredByHeader.removeParameter("Pname");

    // Assert
    assertEquals("sip:Uri", referredByHeader.getValue());
  }

  @Test
  void testRemoveParameter2() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addMaddr("42 Main St");
    sipURI.addMaddr("42 Main St");
    ReferredByHeader referredByHeader = new ReferredByHeader(sipURI);

    // Act
    referredByHeader.removeParameter("maddr");

    // Assert
    assertEquals("sip:Uri;maddr=42 Main St", referredByHeader.getValue());
  }

  @Test
  void testRemoveParameters() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addMaddr("42 Main St");
    ReferredByHeader referredByHeader = new ReferredByHeader(sipURI);

    // Act
    referredByHeader.removeParameters();

    // Assert
    assertEquals("sip:Uri", referredByHeader.getValue());
  }

  @Test
  void testSetNameAddress() {
    // Arrange
    ReferredByHeader referredByHeader = new ReferredByHeader(new NameAddress("Str"));

    // Act
    referredByHeader.setNameAddress(new NameAddress("Str"));

    // Assert
    assertEquals("<sip:Str>", referredByHeader.getValue());
  }

  @Test
  void testSetParameter() {
    // Arrange
    ReferredByHeader referredByHeader = new ReferredByHeader(new NameAddress("Str"));

    // Act
    referredByHeader.setParameter("Pname", "42");

    // Assert
    assertEquals("<sip:Str>;Pname=42", referredByHeader.getValue());
  }

  @Test
  void testSetParameter2() {
    // Arrange
    ReferredByHeader referredByHeader = new ReferredByHeader(new NameAddress("Display name", new GenericURI("Uri")));

    // Act
    referredByHeader.setParameter("Pname", "42");

    // Assert
    assertEquals("\"Display name\" <Uri>;Pname=42", referredByHeader.getValue());
  }

  @Test
  void testSetParameter3() {
    // Arrange
    ReferredByHeader referredByHeader = new ReferredByHeader(new SipURI("Uri"));

    // Act
    referredByHeader.setParameter("Pname", "42");

    // Assert
    assertEquals("sip:Uri;Pname=42", referredByHeader.getValue());
  }

  @Test
  void testSetParameter4() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addMaddr("42 Main St");
    ReferredByHeader referredByHeader = new ReferredByHeader(sipURI);

    // Act
    referredByHeader.setParameter("Pname", "42");

    // Assert
    assertEquals("sip:Uri;maddr=42 Main St;Pname=42", referredByHeader.getValue());
  }

  @Test
  void testSetParameter5() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addParameter("Name");
    ReferredByHeader referredByHeader = new ReferredByHeader(sipURI);

    // Act
    referredByHeader.setParameter("Pname", "42");

    // Assert
    assertEquals("sip:Uri;Name;Pname=42", referredByHeader.getValue());
  }

  @Test
  void testSetParameter6() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addMaddr("42 Main St");
    sipURI.addMaddr("42 Main St");
    ReferredByHeader referredByHeader = new ReferredByHeader(sipURI);

    // Act
    referredByHeader.setParameter("Pname", "42");

    // Assert
    assertEquals("sip:Uri;maddr=42 Main St;maddr=42 Main St;Pname=42", referredByHeader.getValue());
  }

  @Test
  void testSetParameter7() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addParameter("Pname");
    ReferredByHeader referredByHeader = new ReferredByHeader(sipURI);

    // Act
    referredByHeader.setParameter("Pname", "42");

    // Assert
    assertEquals("sip:Uri;Pname=42", referredByHeader.getValue());
  }

  @Test
  void testSetParameter8() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addParameter("");
    ReferredByHeader referredByHeader = new ReferredByHeader(sipURI);

    // Act
    referredByHeader.setParameter("Pname", "42");

    // Assert
    assertEquals("sip:Uri;;Pname=42", referredByHeader.getValue());
  }

  @Test
  void testSetParameter9() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addParameter("", "42");
    ReferredByHeader referredByHeader = new ReferredByHeader(sipURI);

    // Act
    referredByHeader.setParameter("Pname", "42");

    // Assert
    assertEquals("sip:Uri;=42;Pname=42", referredByHeader.getValue());
  }

  @Test
  void testSetParameter10() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addMaddr("42 Main St");
    sipURI.addMaddr("42 Main St");
    ReferredByHeader referredByHeader = new ReferredByHeader(sipURI);

    // Act
    referredByHeader.setParameter("maddr", "42");

    // Assert
    assertEquals("sip:Uri;maddr=42 Main St;maddr=42", referredByHeader.getValue());
  }

  @Test
  void testSetParameter11() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addMaddr("42 Main St");
    sipURI.addParameter("Pname");
    ReferredByHeader referredByHeader = new ReferredByHeader(sipURI);

    // Act
    referredByHeader.setParameter("Pname", "42");

    // Assert
    assertEquals("sip:Uri;maddr=42 Main St;Pname=42", referredByHeader.getValue());
  }

  @Test
  void testSetParameter12() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addParameter("");
    sipURI.addParameter("");
    ReferredByHeader referredByHeader = new ReferredByHeader(sipURI);

    // Act
    referredByHeader.setParameter("Pname", "42");

    // Assert
    assertEquals("sip:Uri;;;Pname=42", referredByHeader.getValue());
  }
}

