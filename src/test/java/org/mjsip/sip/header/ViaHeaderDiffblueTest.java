package org.mjsip.sip.header;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.mjsip.sip.address.SipURI;

class ViaHeaderDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    ViaHeader actualViaHeader = new ViaHeader("42");

    // Assert
    assertNull(actualViaHeader.getBranch());
    assertEquals("Via: 42\r\n", actualViaHeader.toString());
    assertFalse(actualViaHeader.hasTtl());
    assertFalse(actualViaHeader.hasRport());
    assertFalse(actualViaHeader.hasReceived());
    assertFalse(actualViaHeader.hasMaddr());
    assertFalse(actualViaHeader.hasBranch());
    assertEquals("42", actualViaHeader.getValue());
    assertEquals(-1, actualViaHeader.getTtl());
    assertNull(actualViaHeader.getSentBy());
    assertEquals(-1, actualViaHeader.getRport());
    assertNull(actualViaHeader.getReceived());
    assertEquals("", actualViaHeader.getProtocol());
    assertNull(actualViaHeader.getParameters());
    assertTrue(actualViaHeader.getParameterNames().isEmpty());
    assertEquals(CoreSipHeaders.Via, actualViaHeader.getName());
    assertNull(actualViaHeader.getMaddr());
  }

  @Test
  void testConstructor2() {
    // Arrange and Act
    ViaHeader actualViaHeader = new ViaHeader("localhost", 8080);

    // Assert
    assertEquals("SIP/2.0/UDP localhost:8080", actualViaHeader.getValue());
    assertEquals(CoreSipHeaders.Via, actualViaHeader.getName());
  }

  @Test
  void testConstructor3() {
    // Arrange and Act
    ViaHeader actualViaHeader = new ViaHeader("alice.liddell@example.org", "localhost", 8080);

    // Assert
    assertEquals("SIP/2.0/ALICE.LIDDELL@EXAMPLE.ORG localhost:8080", actualViaHeader.getValue());
    assertEquals(CoreSipHeaders.Via, actualViaHeader.getName());
  }

  @Test
  void testConstructor4() {
    // Arrange and Act
    ViaHeader actualViaHeader = new ViaHeader(new Header("Hname", "42"));

    // Assert
    assertEquals("42", actualViaHeader.getValue());
    assertEquals("Hname", actualViaHeader.getName());
  }

  @Test
  void testConstructor5() {
    // Arrange
    Header header = new Header("Hname", "42");
    header.setValue("42");

    // Act
    ViaHeader actualViaHeader = new ViaHeader(header);

    // Assert
    assertEquals("42", actualViaHeader.getValue());
    assertEquals("Hname", actualViaHeader.getName());
  }

  @Test
  void testGetProtocol() {
    // Arrange, Act and Assert
    assertEquals("", (new ViaHeader("42")).getProtocol());
    assertEquals("", (new ViaHeader("Hvalue")).getProtocol());
    assertEquals("UDP", (new ViaHeader("localhost", 8080)).getProtocol());
    assertEquals("localhost:8080", (new ViaHeader("", "localhost", 8080)).getProtocol());
  }

  @Test
  void testGetProtocol2() {
    // Arrange
    ViaHeader viaHeader = new ViaHeader(new Header());
    viaHeader.setValue("42");

    // Act and Assert
    assertEquals("", viaHeader.getProtocol());
  }

  @Test
  void testSetProtocol() {
    // Arrange
    ViaHeader viaHeader = new ViaHeader("42");

    // Act
    viaHeader.setProtocol("alice.liddell@example.org");

    // Assert
    assertEquals("SIP/2.0/ALICE.LIDDELL@EXAMPLE.ORG ", viaHeader.getValue());
  }

  @Test
  void testSetProtocol2() {
    // Arrange
    ViaHeader viaHeader = new ViaHeader("Hvalue");

    // Act
    viaHeader.setProtocol("alice.liddell@example.org");

    // Assert
    assertEquals("SIP/2.0/ALICE.LIDDELL@EXAMPLE.ORG ", viaHeader.getValue());
  }

  @Test
  void testSetProtocol3() {
    // Arrange
    ViaHeader viaHeader = new ViaHeader("localhost", 8080);

    // Act
    viaHeader.setProtocol("alice.liddell@example.org");

    // Assert
    assertEquals("SIP/2.0/ALICE.LIDDELL@EXAMPLE.ORG  localhost:8080", viaHeader.getValue());
  }

  @Test
  void testSetProtocol4() {
    // Arrange
    ViaHeader viaHeader = new ViaHeader("", "localhost", 8080);

    // Act
    viaHeader.setProtocol("alice.liddell@example.org");

    // Assert
    assertEquals("SIP/2.0/ALICE.LIDDELL@EXAMPLE.ORG ", viaHeader.getValue());
  }

  @Test
  void testSetProtocol5() {
    // Arrange
    ViaHeader viaHeader = new ViaHeader(new Header());
    viaHeader.setValue("42");

    // Act
    viaHeader.setProtocol("alice.liddell@example.org");

    // Assert
    assertEquals("SIP/2.0/ALICE.LIDDELL@EXAMPLE.ORG ", viaHeader.getValue());
  }

  @Test
  void testGetSentBy() {
    // Arrange, Act and Assert
    assertNull((new ViaHeader("42")).getSentBy());
    assertEquals("localhost:8080", (new ViaHeader("localhost", 8080)).getSentBy());
  }

  @Test
  void testGetSentBy2() {
    // Arrange
    ViaHeader viaHeader = new ViaHeader(new Header());
    viaHeader.setValue("42");

    // Act and Assert
    assertNull(viaHeader.getSentBy());
  }

  @Test
  void testGetHost() {
    // Arrange, Act and Assert
    assertEquals("localhost", (new ViaHeader("localhost", 8080)).getHost());
  }

  @Test
  void testHasPort() {
    // Arrange, Act and Assert
    assertTrue((new ViaHeader("localhost", 8080)).hasPort());
    assertFalse((new ViaHeader(":", 8080)).hasPort());
  }

  @Test
  void testGetPort() {
    // Arrange, Act and Assert
    assertEquals(8080, (new ViaHeader("localhost", 8080)).getPort());
    assertEquals(0, (new ViaHeader("localhost", 0)).getPort());
  }

  @Test
  void testGetSipURI() {
    // Arrange and Act
    SipURI actualSipURI = (new ViaHeader("localhost", 8080)).getSipURI();

    // Assert
    assertEquals("sip:localhost:8080", actualSipURI.toString());
    assertFalse(actualSipURI.isSecure());
  }

  @Test
  void testGetSipURI2() {
    // Arrange and Act
    SipURI actualSipURI = (new ViaHeader("localhost", 0)).getSipURI();

    // Assert
    assertEquals("sip:localhost", actualSipURI.toString());
    assertFalse(actualSipURI.isSecure());
  }

  @Test
  void testHasBranch() {
    // Arrange, Act and Assert
    assertFalse((new ViaHeader("42")).hasBranch());
  }

  @Test
  void testHasBranch2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertFalse((new ViaHeader(header)).hasBranch());
  }

  @Test
  void testGetBranch() {
    // Arrange, Act and Assert
    assertNull((new ViaHeader("42")).getBranch());
  }

  @Test
  void testGetBranch2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertNull((new ViaHeader(header)).getBranch());
  }

  @Test
  void testSetBranch() {
    // Arrange
    ViaHeader viaHeader = new ViaHeader("42");

    // Act
    viaHeader.setBranch("42");

    // Assert
    assertEquals("42;branch=42", viaHeader.getValue());
  }

  @Test
  void testSetBranch2() {
    // Arrange
    ViaHeader viaHeader = new ViaHeader(new Header());

    // Act
    viaHeader.setBranch("42");

    // Assert
    assertEquals(";branch=42", viaHeader.getValue());
  }

  @Test
  void testHasReceived() {
    // Arrange, Act and Assert
    assertFalse((new ViaHeader("42")).hasReceived());
  }

  @Test
  void testHasReceived2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertFalse((new ViaHeader(header)).hasReceived());
  }

  @Test
  void testGetReceived() {
    // Arrange, Act and Assert
    assertNull((new ViaHeader("42")).getReceived());
  }

  @Test
  void testGetReceived2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertNull((new ViaHeader(header)).getReceived());
  }

  @Test
  void testSetReceived() {
    // Arrange
    ViaHeader viaHeader = new ViaHeader("42");

    // Act
    viaHeader.setReceived("42");

    // Assert
    assertEquals("42;received=42", viaHeader.getValue());
  }

  @Test
  void testSetReceived2() {
    // Arrange
    ViaHeader viaHeader = new ViaHeader(new Header());

    // Act
    viaHeader.setReceived("42");

    // Assert
    assertEquals(";received=42", viaHeader.getValue());
  }

  @Test
  void testHasRport() {
    // Arrange, Act and Assert
    assertFalse((new ViaHeader("42")).hasRport());
  }

  @Test
  void testHasRport2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertFalse((new ViaHeader(header)).hasRport());
  }

  @Test
  void testGetRport() {
    // Arrange, Act and Assert
    assertEquals(-1, (new ViaHeader("42")).getRport());
    assertEquals(-1, (new ViaHeader("localhost", 8080)).getRport());
  }

  @Test
  void testSetRport() {
    // Arrange
    ViaHeader viaHeader = new ViaHeader("42");

    // Act
    viaHeader.setRport();

    // Assert
    assertEquals("42;rport", viaHeader.getValue());
  }

  @Test
  void testSetRport2() {
    // Arrange
    ViaHeader viaHeader = new ViaHeader(new Header());

    // Act
    viaHeader.setRport();

    // Assert
    assertEquals(";rport", viaHeader.getValue());
  }

  @Test
  void testSetRport3() {
    // Arrange
    ViaHeader viaHeader = new ViaHeader("42");

    // Act
    viaHeader.setRport(8080);

    // Assert
    assertEquals("42;rport=8080", viaHeader.getValue());
  }

  @Test
  void testSetRport4() {
    // Arrange
    ViaHeader viaHeader = new ViaHeader("42");

    // Act
    viaHeader.setRport(-1);

    // Assert
    assertEquals("42;rport", viaHeader.getValue());
  }

  @Test
  void testSetRport5() {
    // Arrange
    ViaHeader viaHeader = new ViaHeader(new Header());

    // Act
    viaHeader.setRport(8080);

    // Assert
    assertEquals(";rport=8080", viaHeader.getValue());
  }

  @Test
  void testHasMaddr() {
    // Arrange, Act and Assert
    assertFalse((new ViaHeader("42")).hasMaddr());
  }

  @Test
  void testHasMaddr2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertFalse((new ViaHeader(header)).hasMaddr());
  }

  @Test
  void testGetMaddr() {
    // Arrange, Act and Assert
    assertNull((new ViaHeader("42")).getMaddr());
  }

  @Test
  void testGetMaddr2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertNull((new ViaHeader(header)).getMaddr());
  }

  @Test
  void testSetMaddr() {
    // Arrange
    ViaHeader viaHeader = new ViaHeader("42");

    // Act
    viaHeader.setMaddr("42");

    // Assert
    assertEquals("42;maddr=42", viaHeader.getValue());
  }

  @Test
  void testSetMaddr2() {
    // Arrange
    ViaHeader viaHeader = new ViaHeader(new Header());

    // Act
    viaHeader.setMaddr("42");

    // Assert
    assertEquals(";maddr=42", viaHeader.getValue());
  }

  @Test
  void testHasTtl() {
    // Arrange, Act and Assert
    assertFalse((new ViaHeader("42")).hasTtl());
  }

  @Test
  void testHasTtl2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertFalse((new ViaHeader(header)).hasTtl());
  }

  @Test
  void testGetTtl() {
    // Arrange, Act and Assert
    assertEquals(-1, (new ViaHeader("42")).getTtl());
    assertEquals(-1, (new ViaHeader("localhost", 8080)).getTtl());
  }

  @Test
  void testSetTtl() {
    // Arrange
    ViaHeader viaHeader = new ViaHeader("42");

    // Act
    viaHeader.setTtl(1);

    // Assert
    assertEquals("42;ttl=1", viaHeader.getValue());
  }

  @Test
  void testSetTtl2() {
    // Arrange
    ViaHeader viaHeader = new ViaHeader(new Header());

    // Act
    viaHeader.setTtl(1);

    // Assert
    assertEquals(";ttl=1", viaHeader.getValue());
  }

  @Test
  void testGetParameter() {
    // Arrange, Act and Assert
    assertNull((new ViaHeader("42")).getParameter("Pname"));
  }

  @Test
  void testGetParameter2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertNull((new ViaHeader(header)).getParameter("Pname"));
  }

  @Test
  void testGetParameterNames() {
    // Arrange, Act and Assert
    assertTrue((new ViaHeader("42")).getParameterNames().isEmpty());
  }

  @Test
  void testGetParameterNames2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertTrue((new ViaHeader(header)).getParameterNames().isEmpty());
  }

  @Test
  void testGetParameters() {
    // Arrange, Act and Assert
    assertNull((new ViaHeader("42")).getParameters());
  }

  @Test
  void testGetParameters2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertNull((new ViaHeader(header)).getParameters());
  }

  @Test
  void testHasParameter() {
    // Arrange, Act and Assert
    assertFalse((new ViaHeader("42")).hasParameter("Pname"));
  }

  @Test
  void testHasParameter2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertFalse((new ViaHeader(header)).hasParameter("Pname"));
  }

  @Test
  void testHasParameters() {
    // Arrange, Act and Assert
    assertFalse((new ViaHeader("42")).hasParameters());
  }

  @Test
  void testHasParameters2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertFalse((new ViaHeader(header)).hasParameters());
  }

  @Test
  void testIndexOfFirstSemi() {
    // Arrange, Act and Assert
    assertEquals(-1, (new ViaHeader("42")).indexOfFirstSemi());
  }

  @Test
  void testIndexOfFirstSemi2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertEquals(-1, (new ViaHeader(header)).indexOfFirstSemi());
  }

  @Test
  void testSetParameter() {
    // Arrange
    ViaHeader viaHeader = new ViaHeader("42");

    // Act
    viaHeader.setParameter("Pname", "42");

    // Assert
    assertEquals("42;Pname=42", viaHeader.getValue());
  }

  @Test
  void testSetParameter2() {
    // Arrange
    ViaHeader viaHeader = new ViaHeader(new Header());

    // Act
    viaHeader.setParameter("Pname", "42");

    // Assert
    assertEquals(";Pname=42", viaHeader.getValue());
  }
}

