package org.mjsip.sip.address;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class SipURIDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange, Act and Assert
    assertFalse((new SipURI("Uri")).isSecure());
  }

  @Test
  void testConstructor2() {
    // Arrange and Act
    SipURI actualSipURI = new SipURI("Uri");

    // Assert
    assertEquals("sip:Uri", actualSipURI.toString());
    assertFalse(actualSipURI.isSecure());
  }

  @Test
  void testConstructor3() {
    // Arrange and Act
    SipURI actualSipURI = new SipURI("sips:");

    // Assert
    assertEquals("sips:", actualSipURI.toString());
    assertTrue(actualSipURI.isSecure());
  }

  @Test
  void testConstructor4() {
    // Arrange and Act
    SipURI actualSipURI = new SipURI("sip:");

    // Assert
    assertEquals("sip:", actualSipURI.toString());
    assertFalse(actualSipURI.isSecure());
  }

  @Test
  void testConstructor5() {
    // Arrange and Act
    SipURI actualSipURI = new SipURI("localhost", 10);

    // Assert
    assertEquals("sip:localhost:10", actualSipURI.toString());
    assertFalse(actualSipURI.isSecure());
  }

  @Test
  void testConstructor6() {
    // Arrange and Act
    SipURI actualSipURI = new SipURI("localhost", 0);

    // Assert
    assertEquals("sip:localhost", actualSipURI.toString());
    assertFalse(actualSipURI.isSecure());
  }

  @Test
  void testConstructor7() {
    // Arrange and Act
    SipURI actualSipURI = new SipURI("janedoe", "localhost");

    // Assert
    assertEquals("sip:janedoe@localhost", actualSipURI.toString());
    assertFalse(actualSipURI.isSecure());
  }

  @Test
  void testConstructor8() {
    // Arrange and Act
    SipURI actualSipURI = new SipURI(null, "localhost");

    // Assert
    assertEquals("sip:localhost", actualSipURI.toString());
    assertFalse(actualSipURI.isSecure());
  }

  @Test
  void testConstructor9() {
    // Arrange and Act
    SipURI actualSipURI = new SipURI("janedoe", "localhost", 10);

    // Assert
    assertEquals("sip:janedoe@localhost:10", actualSipURI.toString());
    assertFalse(actualSipURI.isSecure());
  }

  @Test
  void testConstructor10() {
    // Arrange and Act
    SipURI actualSipURI = new SipURI(null, "localhost", 10);

    // Assert
    assertEquals("sip:localhost:10", actualSipURI.toString());
    assertFalse(actualSipURI.isSecure());
  }

  @Test
  void testConstructor11() {
    // Arrange and Act
    SipURI actualSipURI = new SipURI("janedoe", "localhost", 0);

    // Assert
    assertEquals("sip:janedoe@localhost", actualSipURI.toString());
    assertFalse(actualSipURI.isSecure());
  }

  @Test
  void testConstructor12() {
    // Arrange and Act
    SipURI actualSipURI = new SipURI("janedoe", "localhost", 10, true);

    // Assert
    assertEquals("sips:janedoe@localhost:10", actualSipURI.toString());
    assertFalse(actualSipURI.isSecure());
  }

  @Test
  void testConstructor13() {
    // Arrange and Act
    SipURI actualSipURI = new SipURI(null, "localhost", 10, true);

    // Assert
    assertEquals("sips:localhost:10", actualSipURI.toString());
    assertFalse(actualSipURI.isSecure());
  }

  @Test
  void testConstructor14() {
    // Arrange and Act
    SipURI actualSipURI = new SipURI("janedoe", "localhost", 0, true);

    // Assert
    assertEquals("sips:janedoe@localhost", actualSipURI.toString());
    assertFalse(actualSipURI.isSecure());
  }

  @Test
  void testConstructor15() {
    // Arrange and Act
    SipURI actualSipURI = new SipURI("janedoe", "localhost", 10, false);

    // Assert
    assertEquals("sip:janedoe@localhost:10", actualSipURI.toString());
    assertFalse(actualSipURI.isSecure());
  }

  @Test
  void testGetUserName() {
    // Arrange, Act and Assert
    assertNull((new SipURI("Uri")).getUserName());
    assertEquals("janedoe", (new SipURI("janedoe", "localhost")).getUserName());
  }

  @Test
  void testGetHost() {
    // Arrange, Act and Assert
    assertEquals("Uri", (new SipURI("Uri")).getHost());
    assertEquals("localhost", (new SipURI("localhost", 10)).getHost());
    assertEquals("localhost", (new SipURI("janedoe", "localhost")).getHost());
  }

  @Test
  void testGetPort() {
    // Arrange, Act and Assert
    assertEquals(-1, (new SipURI("Uri")).getPort());
    assertEquals(10, (new SipURI("localhost", 10)).getPort());
  }

  @Test
  void testGetPort2() {
    // Arrange
    SipURI sipURI = new SipURI("localhost", 10);
    sipURI.addMaddr("sip:");

    // Act and Assert
    assertEquals(10, sipURI.getPort());
  }

  @Test
  void testGetPort3() {
    // Arrange
    SipURI sipURI = new SipURI("localhost", 10);
    sipURI.addMaddr("sip:");
    sipURI.addMaddr("sip:");

    // Act and Assert
    assertEquals(10, sipURI.getPort());
  }

  @Test
  void testHasUserName() {
    // Arrange, Act and Assert
    assertFalse((new SipURI("Uri")).hasUserName());
    assertTrue((new SipURI("janedoe", "localhost")).hasUserName());
  }

  @Test
  void testHasPort() {
    // Arrange, Act and Assert
    assertFalse((new SipURI("Uri")).hasPort());
    assertTrue((new SipURI("localhost", 10)).hasPort());
  }

  @Test
  void testHasPort2() {
    // Arrange
    SipURI sipURI = new SipURI("localhost", 10);
    sipURI.addMaddr("sip:");

    // Act and Assert
    assertTrue(sipURI.hasPort());
  }

  @Test
  void testHasPort3() {
    // Arrange
    SipURI sipURI = new SipURI("localhost", 10);
    sipURI.addMaddr("sip:");
    sipURI.addMaddr("sip:");

    // Act and Assert
    assertTrue(sipURI.hasPort());
  }

  @Test
  void testSetSecure() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");

    // Act
    sipURI.setSecure(true);

    // Assert
    assertEquals("sips:Uri", sipURI.toString());
    assertTrue(sipURI.isSecure());
  }

  @Test
  void testSetSecure2() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");

    // Act
    sipURI.setSecure(false);

    // Assert that nothing has changed
    assertEquals("sip:Uri", sipURI.toString());
    assertFalse(sipURI.isSecure());
  }

  @Test
  void testGetTransport() {
    // Arrange, Act and Assert
    assertNull((new SipURI("Uri")).getTransport());
  }

  @Test
  void testGetTransport2() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addMaddr("42 Main St");

    // Act and Assert
    assertNull(sipURI.getTransport());
  }

  @Test
  void testGetTransport3() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addParameter(SipURI.PARAM_TRANSPORT);
    sipURI.addParameter(SipURI.PARAM_TRANSPORT);

    // Act and Assert
    assertNull(sipURI.getTransport());
  }

  @Test
  void testGetTransport4() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addTransport("alice.liddell@example.org");
    sipURI.addParameter(SipURI.PARAM_TRANSPORT);

    // Act and Assert
    assertEquals("alice.liddell@example.org", sipURI.getTransport());
  }

  @Test
  void testGetTransport5() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addTransport("alice.liddell@example.org");

    // Act and Assert
    assertEquals("alice.liddell@example.org", sipURI.getTransport());
  }

  @Test
  void testGetTransport6() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addParameter(SipURI.PARAM_TRANSPORT);
    sipURI.addParameter("");
    sipURI.addParameter(SipURI.PARAM_TRANSPORT);

    // Act and Assert
    assertNull(sipURI.getTransport());
  }

  @Test
  void testGetTransport7() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addParameter("");
    sipURI.addParameter("");

    // Act and Assert
    assertNull(sipURI.getTransport());
  }

  @Test
  void testGetTransport8() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addTransport("");
    sipURI.addParameter(SipURI.PARAM_TRANSPORT);

    // Act and Assert
    assertEquals(SipURI.PARAM_TRANSPORT, sipURI.getTransport());
  }

  @Test
  void testGetTransport9() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addTransport("");

    // Act and Assert
    assertEquals("", sipURI.getTransport());
  }

  @Test
  void testHasTransport() {
    // Arrange, Act and Assert
    assertFalse((new SipURI("Uri")).hasTransport());
  }

  @Test
  void testHasTransport2() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addMaddr("42 Main St");

    // Act and Assert
    assertFalse(sipURI.hasTransport());
  }

  @Test
  void testHasTransport3() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addParameter(SipURI.PARAM_TRANSPORT);

    // Act and Assert
    assertTrue(sipURI.hasTransport());
  }

  @Test
  void testHasTransport4() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addMaddr("42 Main St");
    sipURI.addMaddr("42 Main St");

    // Act and Assert
    assertFalse(sipURI.hasTransport());
  }

  @Test
  void testHasTransport5() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addParameter("", "42");

    // Act and Assert
    assertFalse(sipURI.hasTransport());
  }

  @Test
  void testHasTransport6() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addParameter("", "");

    // Act and Assert
    assertFalse(sipURI.hasTransport());
  }

  @Test
  void testAddTransport() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");

    // Act
    sipURI.addTransport("alice.liddell@example.org");

    // Assert
    assertEquals("sip:Uri;transport=alice.liddell@example.org", sipURI.toString());
  }

  @Test
  void testGetMaddr() {
    // Arrange, Act and Assert
    assertNull((new SipURI("Uri")).getMaddr());
  }

  @Test
  void testGetMaddr2() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addMaddr("42 Main St");

    // Act and Assert
    assertEquals("42", sipURI.getMaddr());
  }

  @Test
  void testGetMaddr3() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addMaddr("42 Main St");
    sipURI.addParameter(SipURI.PARAM_MADDR);

    // Act and Assert
    assertEquals("42", sipURI.getMaddr());
  }

  @Test
  void testGetMaddr4() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addParameter(SipURI.PARAM_MADDR);
    sipURI.addParameter(SipURI.PARAM_MADDR);

    // Act and Assert
    assertNull(sipURI.getMaddr());
  }

  @Test
  void testGetMaddr5() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addParameter(SipURI.PARAM_MADDR, "42");
    sipURI.addParameter(SipURI.PARAM_MADDR);

    // Act and Assert
    assertEquals("42", sipURI.getMaddr());
  }

  @Test
  void testGetMaddr6() {
    // Arrange
    SipURI sipURI = new SipURI("localhost", 10);
    sipURI.addParameter("Name");

    // Act and Assert
    assertNull(sipURI.getMaddr());
  }

  @Test
  void testGetMaddr7() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addParameter(SipURI.PARAM_MADDR, "42");

    // Act and Assert
    assertEquals("42", sipURI.getMaddr());
  }

  @Test
  void testGetMaddr8() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addMaddr("");

    // Act and Assert
    assertEquals("", sipURI.getMaddr());
  }

  @Test
  void testGetMaddr9() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addMaddr("");
    sipURI.addParameter(SipURI.PARAM_MADDR);

    // Act and Assert
    assertEquals(SipURI.PARAM_MADDR, sipURI.getMaddr());
  }

  @Test
  void testGetMaddr10() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addMaddr("42 Main St");
    sipURI.addParameter("");
    sipURI.addParameter(SipURI.PARAM_MADDR);

    // Act and Assert
    assertEquals("42", sipURI.getMaddr());
  }

  @Test
  void testGetMaddr11() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addParameter("");
    sipURI.addParameter("");

    // Act and Assert
    assertNull(sipURI.getMaddr());
  }

  @Test
  void testHasMaddr() {
    // Arrange, Act and Assert
    assertFalse((new SipURI("Uri")).hasMaddr());
  }

  @Test
  void testHasMaddr2() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addMaddr("42 Main St");

    // Act and Assert
    assertTrue(sipURI.hasMaddr());
  }

  @Test
  void testHasMaddr3() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addParameter(SipURI.PARAM_MADDR);

    // Act and Assert
    assertTrue(sipURI.hasMaddr());
  }

  @Test
  void testHasMaddr4() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addTransport("alice.liddell@example.org");

    // Act and Assert
    assertFalse(sipURI.hasMaddr());
  }

  @Test
  void testHasMaddr5() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addTransport("alice.liddell@example.org");
    sipURI.addMaddr("42 Main St");

    // Act and Assert
    assertTrue(sipURI.hasMaddr());
  }

  @Test
  void testHasMaddr6() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addParameter("", "42");

    // Act and Assert
    assertFalse(sipURI.hasMaddr());
  }

  @Test
  void testHasMaddr7() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addParameter("", "");

    // Act and Assert
    assertFalse(sipURI.hasMaddr());
  }

  @Test
  void testAddMaddr() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");

    // Act
    sipURI.addMaddr("42 Main St");

    // Assert
    assertEquals("sip:Uri;maddr=42 Main St", sipURI.toString());
  }

  @Test
  void testGetTtl() {
    // Arrange, Act and Assert
    assertEquals(1, (new SipURI("Uri")).getTtl());
  }

  @Test
  void testGetTtl2() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addMaddr("42 Main St");

    // Act and Assert
    assertEquals(1, sipURI.getTtl());
  }

  @Test
  void testGetTtl3() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addParameter(SipURI.PARAM_TTL);

    // Act and Assert
    assertEquals(1, sipURI.getTtl());
  }

  @Test
  void testGetTtl4() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addTtl(2);

    // Act and Assert
    assertEquals(2, sipURI.getTtl());
  }

  @Test
  void testGetTtl5() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addMaddr("42 Main St");
    sipURI.addMaddr("42 Main St");

    // Act and Assert
    assertEquals(1, sipURI.getTtl());
  }

  @Test
  void testGetTtl6() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addParameter(SipURI.PARAM_TTL);
    sipURI.addMaddr("42 Main St");

    // Act and Assert
    assertEquals(1, sipURI.getTtl());
  }

  @Test
  void testGetTtl7() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addTtl(2);
    sipURI.addMaddr("42 Main St");

    // Act and Assert
    assertEquals(2, sipURI.getTtl());
  }

  @Test
  void testGetTtl8() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addParameter("", "42");

    // Act and Assert
    assertEquals(1, sipURI.getTtl());
  }

  @Test
  void testGetTtl9() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addParameter(SipURI.PARAM_TTL, "");

    // Act and Assert
    assertEquals(1, sipURI.getTtl());
  }

  @Test
  void testGetTtl10() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addParameter(SipURI.PARAM_TTL, "");
    sipURI.addMaddr("42 Main St");

    // Act and Assert
    assertEquals(1, sipURI.getTtl());
  }

  @Test
  void testGetTtl11() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addParameter("", "");

    // Act and Assert
    assertEquals(1, sipURI.getTtl());
  }

  @Test
  void testHasTtl() {
    // Arrange, Act and Assert
    assertFalse((new SipURI("Uri")).hasTtl());
  }

  @Test
  void testHasTtl2() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addMaddr("42 Main St");

    // Act and Assert
    assertFalse(sipURI.hasTtl());
  }

  @Test
  void testHasTtl3() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addParameter(SipURI.PARAM_TTL);

    // Act and Assert
    assertTrue(sipURI.hasTtl());
  }

  @Test
  void testHasTtl4() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addMaddr("42 Main St");
    sipURI.addMaddr("42 Main St");

    // Act and Assert
    assertFalse(sipURI.hasTtl());
  }

  @Test
  void testHasTtl5() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addParameter("", "42");

    // Act and Assert
    assertFalse(sipURI.hasTtl());
  }

  @Test
  void testHasTtl6() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addParameter("", "");

    // Act and Assert
    assertFalse(sipURI.hasTtl());
  }

  @Test
  void testAddTtl() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");

    // Act
    sipURI.addTtl(2);

    // Assert
    assertEquals("sip:Uri;ttl=2", sipURI.toString());
  }
}

