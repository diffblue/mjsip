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

class ToHeaderDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    ToHeader actualToHeader = new ToHeader(new NameAddress("Str"));

    // Assert
    assertEquals(CoreSipHeaders.To, actualToHeader.getName());
    assertEquals("<sip:Str>", actualToHeader.getValue());
  }

  @Test
  void testConstructor2() {
    // Arrange and Act
    ToHeader actualToHeader = new ToHeader(new NameAddress((GenericURI) null));

    // Assert
    assertEquals(CoreSipHeaders.To, actualToHeader.getName());
    assertEquals("<null>", actualToHeader.getValue());
  }

  @Test
  void testConstructor3() {
    // Arrange and Act
    ToHeader actualToHeader = new ToHeader(new NameAddress("Str"), "Tag");

    // Assert
    assertEquals(CoreSipHeaders.To, actualToHeader.getName());
    assertEquals("<sip:Str>;tag=Tag", actualToHeader.getValue());
  }

  @Test
  void testConstructor4() {
    // Arrange and Act
    ToHeader actualToHeader = new ToHeader(new NameAddress("Display name", new GenericURI("Uri")), "Tag");

    // Assert
    assertEquals(CoreSipHeaders.To, actualToHeader.getName());
    assertEquals("\"Display name\" <Uri>;tag=Tag", actualToHeader.getValue());
  }

  @Test
  void testConstructor5() {
    // Arrange and Act
    ToHeader actualToHeader = new ToHeader(new NameAddress("\" <", new GenericURI("Uri")), "Tag");

    // Assert
    assertEquals(CoreSipHeaders.To, actualToHeader.getName());
    assertEquals("\"\" <\" <Uri>;tag=Tag", actualToHeader.getValue());
  }

  @Test
  void testConstructor6() {
    // Arrange and Act
    ToHeader actualToHeader = new ToHeader(new NameAddress("Display name", null), "Tag");

    // Assert
    assertEquals(CoreSipHeaders.To, actualToHeader.getName());
    assertEquals("\"Display name\" <null>;tag=Tag", actualToHeader.getValue());
  }

  @Test
  void testConstructor7() {
    // Arrange and Act
    ToHeader actualToHeader = new ToHeader(new SipURI("Uri"));

    // Assert
    assertEquals(CoreSipHeaders.To, actualToHeader.getName());
    assertEquals("sip:Uri", actualToHeader.getValue());
  }

  @Test
  void testConstructor8() {
    // Arrange and Act
    ToHeader actualToHeader = new ToHeader(new SipURI("Uri"), "Tag");

    // Assert
    assertEquals(CoreSipHeaders.To, actualToHeader.getName());
    assertEquals("sip:Uri;tag=Tag", actualToHeader.getValue());
  }

  @Test
  void testConstructor9() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addMaddr("42 Main St");

    // Act
    ToHeader actualToHeader = new ToHeader(sipURI, "Tag");

    // Assert
    assertEquals(CoreSipHeaders.To, actualToHeader.getName());
    assertEquals("sip:Uri;maddr=42 Main St;tag=Tag", actualToHeader.getValue());
  }

  @Test
  void testConstructor10() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addParameter(CoreSipHeaders.To);

    // Act
    ToHeader actualToHeader = new ToHeader(sipURI, "Tag");

    // Assert
    assertEquals(CoreSipHeaders.To, actualToHeader.getName());
    assertEquals("sip:Uri;To;tag=Tag", actualToHeader.getValue());
  }

  @Test
  void testConstructor11() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addMaddr("42 Main St");
    sipURI.addMaddr("42 Main St");

    // Act
    ToHeader actualToHeader = new ToHeader(sipURI, "Tag");

    // Assert
    assertEquals(CoreSipHeaders.To, actualToHeader.getName());
    assertEquals("sip:Uri;maddr=42 Main St;maddr=42 Main St;tag=Tag", actualToHeader.getValue());
  }

  @Test
  void testConstructor12() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addParameter("tag");

    // Act
    ToHeader actualToHeader = new ToHeader(sipURI, "Tag");

    // Assert
    assertEquals(CoreSipHeaders.To, actualToHeader.getName());
    assertEquals("sip:Uri;tag=Tag", actualToHeader.getValue());
  }

  @Test
  void testConstructor13() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addParameter("");

    // Act
    ToHeader actualToHeader = new ToHeader(sipURI, "Tag");

    // Assert
    assertEquals(CoreSipHeaders.To, actualToHeader.getName());
    assertEquals("sip:Uri;;tag=Tag", actualToHeader.getValue());
  }

  @Test
  void testConstructor14() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addParameter("", "42");

    // Act
    ToHeader actualToHeader = new ToHeader(sipURI, "Tag");

    // Assert
    assertEquals(CoreSipHeaders.To, actualToHeader.getName());
    assertEquals("sip:Uri;=42;tag=Tag", actualToHeader.getValue());
  }

  @Test
  void testConstructor15() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addMaddr("42 Main St");
    sipURI.addParameter("tag");

    // Act
    ToHeader actualToHeader = new ToHeader(sipURI, "Tag");

    // Assert
    assertEquals(CoreSipHeaders.To, actualToHeader.getName());
    assertEquals("sip:Uri;maddr=42 Main St;tag=Tag", actualToHeader.getValue());
  }

  @Test
  void testConstructor16() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addParameter("");
    sipURI.addParameter("");

    // Act
    ToHeader actualToHeader = new ToHeader(sipURI, "Tag");

    // Assert
    assertEquals(CoreSipHeaders.To, actualToHeader.getName());
    assertEquals("sip:Uri;;;tag=Tag", actualToHeader.getValue());
  }

  @Test
  void testConstructor17() {
    // Arrange and Act
    ToHeader actualToHeader = new ToHeader(new Header("Hname", "42"));

    // Assert
    assertEquals("Hname", actualToHeader.getName());
    assertEquals("42", actualToHeader.getValue());
  }

  @Test
  void testConstructor18() {
    // Arrange
    Header header = new Header("Hname", "42");
    header.setValue("42");

    // Act
    ToHeader actualToHeader = new ToHeader(header);

    // Assert
    assertEquals("Hname", actualToHeader.getName());
    assertEquals("42", actualToHeader.getValue());
  }

  @Test
  void testGetNameAddress() {
    // Arrange and Act
    NameAddress actualNameAddress = (new ToHeader(new Header("Hname", "42"))).getNameAddress();

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
    NameAddress actualNameAddress = (new ToHeader(new Header("Hname", "sips:"))).getNameAddress();

    // Assert
    assertEquals("<sips:>", actualNameAddress.toString());
    assertNull(actualNameAddress.getDisplayName());
    assertTrue(actualNameAddress.getAddress().isSipURI());
  }

  @Test
  void testGetNameAddress3() {
    // Arrange and Act
    NameAddress actualNameAddress = (new ToHeader(new Header("Hname", ""))).getNameAddress();

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
    NameAddress actualNameAddress = (new ToHeader(new SipURI("Uri"))).getNameAddress();

    // Assert
    assertEquals("<sip:Uri>", actualNameAddress.toString());
    assertNull(actualNameAddress.getDisplayName());
    assertTrue(actualNameAddress.getAddress().isSipURI());
  }

  @Test
  void testGetNameAddress5() {
    // Arrange and Act
    NameAddress actualNameAddress = (new ToHeader(new NameAddress("Str"))).getNameAddress();

    // Assert
    assertEquals("<sip:Str>", actualNameAddress.toString());
    assertNull(actualNameAddress.getDisplayName());
    assertTrue(actualNameAddress.getAddress().isSipURI());
  }

  @Test
  void testGetNameAddress6() {
    // Arrange and Act
    NameAddress actualNameAddress = (new ToHeader(new SipURI("Uri"), "Tag")).getNameAddress();

    // Assert
    assertEquals("<sip:Uri>", actualNameAddress.toString());
    assertNull(actualNameAddress.getDisplayName());
    assertTrue(actualNameAddress.getAddress().isSipURI());
  }

  @Test
  void testGetNameAddress7() {
    // Arrange and Act
    NameAddress actualNameAddress = (new ToHeader(new NameAddress("Str"), "Tag")).getNameAddress();

    // Assert
    assertEquals("<sip:Str>", actualNameAddress.toString());
    assertNull(actualNameAddress.getDisplayName());
    assertTrue(actualNameAddress.getAddress().isSipURI());
  }

  @Test
  void testGetNameAddress8() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addMaddr("42 Main St");

    // Act
    NameAddress actualNameAddress = (new ToHeader(sipURI)).getNameAddress();

    // Assert
    assertEquals("<sip:Uri;maddr=42>", actualNameAddress.toString());
    assertNull(actualNameAddress.getDisplayName());
    assertTrue(actualNameAddress.getAddress().isSipURI());
  }

  @Test
  void testGetNameAddress9() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addParameter("Name");

    // Act
    NameAddress actualNameAddress = (new ToHeader(sipURI)).getNameAddress();

    // Assert
    assertEquals("<sip:Uri;Name>", actualNameAddress.toString());
    assertNull(actualNameAddress.getDisplayName());
    assertTrue(actualNameAddress.getAddress().isSipURI());
  }

  @Test
  void testGetNameAddress10() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addParameter("maddr");
    sipURI.addMaddr("42 Main St");

    // Act
    NameAddress actualNameAddress = (new ToHeader(sipURI)).getNameAddress();

    // Assert
    assertEquals("<sip:Uri;maddr;maddr=42>", actualNameAddress.toString());
    assertNull(actualNameAddress.getDisplayName());
    assertTrue(actualNameAddress.getAddress().isSipURI());
  }

  @Test
  void testGetNameAddress11() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addParameter("", "42");

    // Act
    NameAddress actualNameAddress = (new ToHeader(sipURI)).getNameAddress();

    // Assert
    assertEquals("<sip:Uri;=42>", actualNameAddress.toString());
    assertNull(actualNameAddress.getDisplayName());
    assertTrue(actualNameAddress.getAddress().isSipURI());
  }

  @Test
  void testGetNameAddress12() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addParameter("tag");
    sipURI.addMaddr("42 Main St");

    // Act
    NameAddress actualNameAddress = (new ToHeader(sipURI)).getNameAddress();

    // Assert
    assertEquals("<sip:Uri;maddr=42>", actualNameAddress.toString());
    assertNull(actualNameAddress.getDisplayName());
    assertTrue(actualNameAddress.getAddress().isSipURI());
  }

  @Test
  void testGetNameAddress13() {
    // Arrange and Act
    NameAddress actualNameAddress = (new ToHeader(new NameAddress("Display name", new GenericURI("Uri"))))
        .getNameAddress();

    // Assert
    assertEquals("<sip:\"Display>", actualNameAddress.toString());
    assertFalse(actualNameAddress.hasDisplayName());
    GenericURI address = actualNameAddress.getAddress();
    assertFalse(address.isTelURI());
    assertFalse(((SipURI) address).isSecure());
  }

  @Test
  void testGetNameAddress14() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addTransport("alice.liddell@example.org");

    // Act
    NameAddress actualNameAddress = (new ToHeader(sipURI, "Tag")).getNameAddress();

    // Assert
    assertEquals("<sip:Uri;transport=alice.liddell@example.org>", actualNameAddress.toString());
    assertNull(actualNameAddress.getDisplayName());
    assertTrue(actualNameAddress.getAddress().isSipURI());
  }

  @Test
  void testGetNameAddress15() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addParameter("", "");

    // Act
    NameAddress actualNameAddress = (new ToHeader(sipURI)).getNameAddress();

    // Assert
    assertEquals("<sip:Uri;=>", actualNameAddress.toString());
    assertNull(actualNameAddress.getDisplayName());
    assertTrue(actualNameAddress.getAddress().isSipURI());
  }

  @Test
  void testGetNameAddress16() {
    // Arrange
    NameAddress nameAddress = new NameAddress("Str");
    nameAddress.setDisplayName("Display name");

    // Act
    NameAddress actualNameAddress = (new ToHeader(nameAddress)).getNameAddress();

    // Assert
    assertEquals("\"Display name\" <sip:Str>", actualNameAddress.toString());
    assertEquals("Display name", actualNameAddress.getDisplayName());
    assertTrue(actualNameAddress.getAddress().isSipURI());
  }

  @Test
  void testGetParameter() {
    // Arrange, Act and Assert
    assertNull((new ToHeader(new Header("Hname", "42"))).getParameter("Pname"));
    assertNull((new ToHeader(new SipURI("Uri"))).getParameter("Pname"));
    assertNull((new ToHeader(new NameAddress("Str"))).getParameter("Pname"));
    assertNull((new ToHeader(new SipURI("Uri"), "Tag")).getParameter("Pname"));
  }

  @Test
  void testGetParameter2() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addParameter("Name");

    // Act and Assert
    assertNull((new ToHeader(sipURI)).getParameter("Pname"));
  }

  @Test
  void testGetParameter3() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addMaddr("42 Main St");
    sipURI.addParameter("Name");

    // Act and Assert
    assertNull((new ToHeader(sipURI)).getParameter("Pname"));
  }

  @Test
  void testGetParameter4() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addParameter("Pname");
    sipURI.addParameter("Pname");

    // Act and Assert
    assertNull((new ToHeader(sipURI)).getParameter("Pname"));
  }

  @Test
  void testGetParameter5() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addParameter("Pname", "42");
    sipURI.addParameter("Pname");

    // Act and Assert
    assertEquals("42", (new ToHeader(sipURI)).getParameter("Pname"));
  }

  @Test
  void testGetParameter6() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addParameter("", "42");

    // Act and Assert
    assertNull((new ToHeader(sipURI)).getParameter("Pname"));
  }

  @Test
  void testGetParameter7() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addParameter("Pname", "");
    sipURI.addParameter("Pname");

    // Act and Assert
    assertEquals("Pname", (new ToHeader(sipURI)).getParameter("Pname"));
  }

  @Test
  void testGetParameterNames() {
    // Arrange, Act and Assert
    assertTrue((new ToHeader(new Header("Hname", "42"))).getParameterNames().isEmpty());
    assertTrue((new ToHeader(new SipURI("Uri"))).getParameterNames().isEmpty());
    assertTrue((new ToHeader(new NameAddress("Str"))).getParameterNames().isEmpty());
    assertTrue((new ToHeader(new NameAddress("Display name", new GenericURI("Uri")))).getParameterNames().isEmpty());
  }

  @Test
  void testGetParameterNames2() {
    // Arrange and Act
    Vector actualParameterNames = (new ToHeader(new SipURI("Uri"), "Tag")).getParameterNames();

    // Assert
    assertEquals(1, actualParameterNames.size());
    assertEquals("tag", actualParameterNames.get(0));
  }

  @Test
  void testGetParameterNames3() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addParameter("Name");

    // Act
    Vector actualParameterNames = (new ToHeader(sipURI)).getParameterNames();

    // Assert
    assertEquals(1, actualParameterNames.size());
    assertEquals("Name", actualParameterNames.get(0));
  }

  @Test
  void testGetParameterNames4() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addMaddr("42 Main St");
    sipURI.addParameter("Name");

    // Act
    Vector actualParameterNames = (new ToHeader(sipURI)).getParameterNames();

    // Assert
    assertEquals(2, actualParameterNames.size());
    assertEquals("maddr", actualParameterNames.get(0));
    assertEquals("Name", actualParameterNames.get(1));
  }

  @Test
  void testGetParameterNames5() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addParameter("");

    // Act and Assert
    assertTrue((new ToHeader(sipURI)).getParameterNames().isEmpty());
  }

  @Test
  void testGetParameterNames6() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addParameter("", "42");

    // Act
    Vector actualParameterNames = (new ToHeader(sipURI)).getParameterNames();

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
    assertTrue((new ToHeader(sipURI)).getParameterNames().isEmpty());
  }

  @Test
  void testGetParameters() {
    // Arrange, Act and Assert
    assertNull((new ToHeader(new Header("Hname", "42"))).getParameters());
    assertNull((new ToHeader(new SipURI("Uri"))).getParameters());
    assertNull((new ToHeader(new NameAddress("Str"))).getParameters());
    assertEquals("tag=Tag", (new ToHeader(new SipURI("Uri"), "Tag")).getParameters());
    assertNull((new ToHeader(new NameAddress("Display name", new GenericURI("Uri")))).getParameters());
  }

  @Test
  void testGetTag() {
    // Arrange, Act and Assert
    assertNull((new ToHeader(new Header("Hname", "42"))).getTag());
    assertNull((new ToHeader(new SipURI("Uri"))).getTag());
    assertNull((new ToHeader(new NameAddress("Str"))).getTag());
    assertEquals("Tag", (new ToHeader(new SipURI("Uri"), "Tag")).getTag());
  }

  @Test
  void testGetTag2() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addMaddr("42 Main St");

    // Act and Assert
    assertNull((new ToHeader(sipURI)).getTag());
  }

  @Test
  void testGetTag3() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addParameter("tag");
    sipURI.addParameter("tag");

    // Act and Assert
    assertNull((new ToHeader(sipURI)).getTag());
  }

  @Test
  void testGetTag4() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addParameter("tag", "42");
    sipURI.addParameter("tag");

    // Act and Assert
    assertEquals("42", (new ToHeader(sipURI)).getTag());
  }

  @Test
  void testGetTag5() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addParameter("tag");
    sipURI.addParameter("");
    sipURI.addParameter("tag");

    // Act and Assert
    assertNull((new ToHeader(sipURI)).getTag());
  }

  @Test
  void testGetTag6() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addParameter("");
    sipURI.addParameter("");

    // Act and Assert
    assertNull((new ToHeader(sipURI)).getTag());
  }

  @Test
  void testGetTag7() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addParameter("tag", "");
    sipURI.addParameter("tag");

    // Act and Assert
    assertEquals("tag", (new ToHeader(sipURI)).getTag());
  }

  @Test
  void testGetTag8() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addParameter("tag", "");

    // Act and Assert
    assertEquals("", (new ToHeader(sipURI)).getTag());
  }

  @Test
  void testHasParameter() {
    // Arrange, Act and Assert
    assertFalse((new ToHeader(new Header("Hname", "42"))).hasParameter("Pname"));
    assertFalse((new ToHeader(new SipURI("Uri"))).hasParameter("Pname"));
    assertFalse((new ToHeader(new NameAddress("Str"))).hasParameter("Pname"));
    assertFalse((new ToHeader(new SipURI("Uri"), "Tag")).hasParameter("Pname"));
    assertFalse((new ToHeader(new NameAddress("Display name", new GenericURI("Uri")))).hasParameter("Pname"));
  }

  @Test
  void testHasParameter2() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addParameter("Name");

    // Act and Assert
    assertFalse((new ToHeader(sipURI)).hasParameter("Pname"));
  }

  @Test
  void testHasParameter3() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addMaddr("42 Main St");
    sipURI.addParameter("Name");

    // Act and Assert
    assertFalse((new ToHeader(sipURI)).hasParameter("Pname"));
  }

  @Test
  void testHasParameter4() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addParameter("Pname");

    // Act and Assert
    assertTrue((new ToHeader(sipURI)).hasParameter("Pname"));
  }

  @Test
  void testHasParameter5() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addParameter("");

    // Act and Assert
    assertFalse((new ToHeader(sipURI)).hasParameter("Pname"));
  }

  @Test
  void testHasParameter6() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addParameter("", "42");

    // Act and Assert
    assertFalse((new ToHeader(sipURI)).hasParameter("Pname"));
  }

  @Test
  void testHasParameter7() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addParameter("");
    sipURI.addParameter("");

    // Act and Assert
    assertFalse((new ToHeader(sipURI)).hasParameter("Pname"));
  }

  @Test
  void testHasParameters() {
    // Arrange, Act and Assert
    assertFalse((new ToHeader(new Header("Hname", "42"))).hasParameters());
    assertFalse((new ToHeader(new SipURI("Uri"))).hasParameters());
    assertFalse((new ToHeader(new NameAddress("Str"))).hasParameters());
    assertTrue((new ToHeader(new SipURI("Uri"), "Tag")).hasParameters());
    assertFalse((new ToHeader(new NameAddress("Display name", new GenericURI("Uri")))).hasParameters());
  }

  @Test
  void testHasTag() {
    // Arrange, Act and Assert
    assertFalse((new ToHeader(new Header("Hname", "42"))).hasTag());
    assertFalse((new ToHeader(new SipURI("Uri"))).hasTag());
    assertFalse((new ToHeader(new NameAddress("Str"))).hasTag());
    assertTrue((new ToHeader(new SipURI("Uri"), "Tag")).hasTag());
    assertFalse((new ToHeader(new NameAddress("Display name", new GenericURI("Uri")))).hasTag());
  }

  @Test
  void testHasTag2() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addMaddr("42 Main St");

    // Act and Assert
    assertFalse((new ToHeader(sipURI)).hasTag());
  }

  @Test
  void testHasTag3() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addParameter("tag");

    // Act and Assert
    assertTrue((new ToHeader(sipURI)).hasTag());
  }

  @Test
  void testHasTag4() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addMaddr("42 Main St");
    sipURI.addMaddr("42 Main St");

    // Act and Assert
    assertFalse((new ToHeader(sipURI)).hasTag());
  }

  @Test
  void testHasTag5() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addParameter("");

    // Act and Assert
    assertFalse((new ToHeader(sipURI)).hasTag());
  }

  @Test
  void testHasTag6() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addParameter("", "42");

    // Act and Assert
    assertFalse((new ToHeader(sipURI)).hasTag());
  }

  @Test
  void testHasTag7() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addParameter("");
    sipURI.addParameter("");

    // Act and Assert
    assertFalse((new ToHeader(sipURI)).hasTag());
  }

  @Test
  void testIndexOfFirstSemi() {
    // Arrange, Act and Assert
    assertEquals(-1, (new ToHeader(new Header("Hname", "42"))).indexOfFirstSemi());
    assertEquals(-1, (new ToHeader(new SipURI("Uri"))).indexOfFirstSemi());
    assertEquals(-1, (new ToHeader(new NameAddress("Str"))).indexOfFirstSemi());
    assertEquals(7, (new ToHeader(new SipURI("Uri"), "Tag")).indexOfFirstSemi());
    assertEquals(-1, (new ToHeader(new NameAddress("Display name", new GenericURI("Uri")))).indexOfFirstSemi());
  }

  @Test
  void testRemoveParameter() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addParameter("Pname");
    ToHeader toHeader = new ToHeader(sipURI);

    // Act
    toHeader.removeParameter("Pname");

    // Assert
    assertEquals("sip:Uri", toHeader.getValue());
  }

  @Test
  void testRemoveParameter2() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addMaddr("42 Main St");
    sipURI.addParameter("Name");
    ToHeader toHeader = new ToHeader(sipURI);

    // Act
    toHeader.removeParameter("maddr");

    // Assert
    assertEquals("sip:Uri;Name", toHeader.getValue());
  }

  @Test
  void testRemoveParameters() {
    // Arrange
    ToHeader toHeader = new ToHeader(new SipURI("Uri"), "Tag");

    // Act
    toHeader.removeParameters();

    // Assert
    assertEquals("sip:Uri", toHeader.getValue());
  }

  @Test
  void testSetNameAddress() {
    // Arrange
    ToHeader toHeader = new ToHeader(new Header("Hname", "42"));

    // Act
    toHeader.setNameAddress(new NameAddress("Str"));

    // Assert
    assertEquals("<sip:Str>", toHeader.getValue());
  }

  @Test
  void testSetNameAddress2() {
    // Arrange
    ToHeader toHeader = new ToHeader(new Header("Hname", "42"));

    // Act
    toHeader.setNameAddress(new NameAddress((GenericURI) null));

    // Assert
    assertEquals("<null>", toHeader.getValue());
  }

  @Test
  void testSetParameter() {
    // Arrange
    ToHeader toHeader = new ToHeader(new Header("Hname", "42"));

    // Act
    toHeader.setParameter("Pname", "42");

    // Assert
    assertEquals("42;Pname=42", toHeader.getValue());
  }

  @Test
  void testSetParameter2() {
    // Arrange
    ToHeader toHeader = new ToHeader(new Header());

    // Act
    toHeader.setParameter("Pname", "42");

    // Assert
    assertEquals(";Pname=42", toHeader.getValue());
  }

  @Test
  void testSetParameter3() {
    // Arrange
    ToHeader toHeader = new ToHeader(new NameAddress("Str"));

    // Act
    toHeader.setParameter("Pname", "42");

    // Assert
    assertEquals("<sip:Str>;Pname=42", toHeader.getValue());
  }

  @Test
  void testSetParameter4() {
    // Arrange
    ToHeader toHeader = new ToHeader(new SipURI("Uri"), "Tag");

    // Act
    toHeader.setParameter("Pname", "42");

    // Assert
    assertEquals("sip:Uri;tag=Tag;Pname=42", toHeader.getValue());
  }

  @Test
  void testSetParameter5() {
    // Arrange
    ToHeader toHeader = new ToHeader(new NameAddress("Display name", new GenericURI("Uri")));

    // Act
    toHeader.setParameter("Pname", "42");

    // Assert
    assertEquals("\"Display name\" <Uri>;Pname=42", toHeader.getValue());
  }

  @Test
  void testSetParameter6() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addMaddr("42 Main St");
    ToHeader toHeader = new ToHeader(sipURI, "Tag");

    // Act
    toHeader.setParameter("Pname", "42");

    // Assert
    assertEquals("sip:Uri;maddr=42 Main St;tag=Tag;Pname=42", toHeader.getValue());
  }

  @Test
  void testSetParameter7() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addParameter("");
    ToHeader toHeader = new ToHeader(sipURI, "Tag");

    // Act
    toHeader.setParameter("Pname", "42");

    // Assert
    assertEquals("sip:Uri;;tag=Tag;Pname=42", toHeader.getValue());
  }

  @Test
  void testSetParameter8() {
    // Arrange
    ToHeader toHeader = new ToHeader(new SipURI("Uri"), "Tag");

    // Act
    toHeader.setParameter("tag", "42");

    // Assert
    assertEquals("sip:Uri;tag=42", toHeader.getValue());
  }

  @Test
  void testSetParameter9() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addMaddr("42 Main St");
    ToHeader toHeader = new ToHeader(sipURI, "Tag");

    // Act
    toHeader.setParameter("maddr", "42");

    // Assert
    assertEquals("sip:Uri;tag=Tag;maddr=42", toHeader.getValue());
  }

  @Test
  void testSetParameter10() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addMaddr("42 Main St");
    ToHeader toHeader = new ToHeader(sipURI, "Tag");

    // Act
    toHeader.setParameter("tag", "42");

    // Assert
    assertEquals("sip:Uri;maddr=42 Main St;tag=42", toHeader.getValue());
  }

  @Test
  void testSetTag() {
    // Arrange
    ToHeader toHeader = new ToHeader(new Header("Hname", "42"));

    // Act
    toHeader.setTag("Tag");

    // Assert
    assertEquals("42;tag=Tag", toHeader.getValue());
  }

  @Test
  void testSetTag2() {
    // Arrange
    ToHeader toHeader = new ToHeader(new Header());

    // Act
    toHeader.setTag("Tag");

    // Assert
    assertEquals(";tag=Tag", toHeader.getValue());
  }

  @Test
  void testSetTag3() {
    // Arrange
    ToHeader toHeader = new ToHeader(new NameAddress("Str"));

    // Act
    toHeader.setTag("Tag");

    // Assert
    assertEquals("<sip:Str>;tag=Tag", toHeader.getValue());
  }

  @Test
  void testSetTag4() {
    // Arrange
    ToHeader toHeader = new ToHeader(new SipURI("Uri"), "Tag");

    // Act
    toHeader.setTag("Tag");

    // Assert
    assertEquals("sip:Uri;tag=Tag", toHeader.getValue());
  }

  @Test
  void testSetTag5() {
    // Arrange
    ToHeader toHeader = new ToHeader(new NameAddress("Display name", new GenericURI("Uri")));

    // Act
    toHeader.setTag("Tag");

    // Assert
    assertEquals("\"Display name\" <Uri>;tag=Tag", toHeader.getValue());
  }

  @Test
  void testSetTag6() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addMaddr("42 Main St");
    ToHeader toHeader = new ToHeader(sipURI, "Tag");

    // Act
    toHeader.setTag("Tag");

    // Assert
    assertEquals("sip:Uri;maddr=42 Main St;tag=Tag", toHeader.getValue());
  }

  @Test
  void testSetTag7() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addParameter("");
    ToHeader toHeader = new ToHeader(sipURI, "Tag");

    // Act
    toHeader.setTag("Tag");

    // Assert
    assertEquals("sip:Uri;tag=Tag", toHeader.getValue());
  }
}

