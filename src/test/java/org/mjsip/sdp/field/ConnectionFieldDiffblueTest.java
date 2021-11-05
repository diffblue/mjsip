package org.mjsip.sdp.field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.mjsip.sdp.SdpField;

class ConnectionFieldDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    ConnectionField actualConnectionField = new ConnectionField("Connection field");

    // Assert
    assertEquals("", actualConnectionField.getAddress());
    assertEquals("c=Connection field\r\n", actualConnectionField.toString());
    assertEquals("Connection field", actualConnectionField.getValue());
    assertEquals('c', actualConnectionField.getType());
    assertEquals(0, actualConnectionField.getTTL());
    assertEquals(0, actualConnectionField.getNum());
    assertEquals("field", actualConnectionField.getAddressType());
  }

  @Test
  void testConstructor2() {
    // Arrange and Act
    ConnectionField actualConnectionField = new ConnectionField("42 Main St", "42 Main St");

    // Assert
    assertEquals("IN 42 Main St 42 Main St", actualConnectionField.getValue());
    assertEquals('c', actualConnectionField.getType());
  }

  @Test
  void testConstructor3() {
    // Arrange and Act
    ConnectionField actualConnectionField = new ConnectionField(null, "42 Main St");

    // Assert
    assertEquals("IN IP4 42 Main St", actualConnectionField.getValue());
    assertEquals('c', actualConnectionField.getType());
  }

  @Test
  void testConstructor4() {
    // Arrange and Act
    ConnectionField actualConnectionField = new ConnectionField("", "42 Main St");

    // Assert
    assertEquals("IN IP4 42 Main St", actualConnectionField.getValue());
    assertEquals('c', actualConnectionField.getType());
  }

  @Test
  void testConstructor5() {
    // Arrange and Act
    ConnectionField actualConnectionField = new ConnectionField("42 Main St", "42 Main St", 1, 10);

    // Assert
    assertEquals("IN 42 Main St 42 Main St/1/10", actualConnectionField.getValue());
    assertEquals('c', actualConnectionField.getType());
  }

  @Test
  void testConstructor6() {
    // Arrange and Act
    ConnectionField actualConnectionField = new ConnectionField(null, "42 Main St", 1, 10);

    // Assert
    assertEquals("IN IP4 42 Main St/1/10", actualConnectionField.getValue());
    assertEquals('c', actualConnectionField.getType());
  }

  @Test
  void testConstructor7() {
    // Arrange and Act
    ConnectionField actualConnectionField = new ConnectionField("", "42 Main St", 1, 10);

    // Assert
    assertEquals("IN IP4 42 Main St/1/10", actualConnectionField.getValue());
    assertEquals('c', actualConnectionField.getType());
  }

  @Test
  void testConstructor8() {
    // Arrange and Act
    ConnectionField actualConnectionField = new ConnectionField("42 Main St", "42 Main St", 0, 10);

    // Assert
    assertEquals("IN 42 Main St 42 Main St/10", actualConnectionField.getValue());
    assertEquals('c', actualConnectionField.getType());
  }

  @Test
  void testConstructor9() {
    // Arrange and Act
    ConnectionField actualConnectionField = new ConnectionField("42 Main St", "42 Main St", 1, 0);

    // Assert
    assertEquals("IN 42 Main St 42 Main St/1", actualConnectionField.getValue());
    assertEquals('c', actualConnectionField.getType());
  }

  @Test
  void testConstructor10() {
    // Arrange and Act
    ConnectionField actualConnectionField = new ConnectionField(new SdpField('A', "42"));

    // Assert
    assertEquals("42", actualConnectionField.getValue());
    assertEquals('A', actualConnectionField.getType());
  }

  @Test
  void testConstructor11() {
    // Arrange and Act
    ConnectionField actualConnectionField = new ConnectionField(new SdpField(new SdpField('A', "42")));

    // Assert
    assertEquals("42", actualConnectionField.getValue());
    assertEquals('A', actualConnectionField.getType());
  }

  @Test
  void testGetAddressType() {
    // Arrange, Act and Assert
    assertEquals("field", (new ConnectionField("Connection field")).getAddressType());
    assertEquals("", (new ConnectionField("/")).getAddressType());
    assertEquals("42", (new ConnectionField("42 Main St", "42 Main St")).getAddressType());
    assertEquals("42", (new ConnectionField("42 Main St", "42 Main St", 1, 10)).getAddressType());
    assertEquals("", (new ConnectionField(new SdpField('A', "42"))).getAddressType());
    assertEquals("IP4", (new ConnectionField("", "42 Main St")).getAddressType());
    assertEquals("", (new ConnectionField(new SdpField(new SdpField('A', "42")))).getAddressType());
  }

  @Test
  void testGetAddress() {
    // Arrange, Act and Assert
    assertEquals("", (new ConnectionField("Connection field")).getAddress());
    assertEquals("", (new ConnectionField("/")).getAddress());
    assertEquals("Main", (new ConnectionField("42 Main St", "42 Main St")).getAddress());
    assertEquals("Main", (new ConnectionField("42 Main St", "42 Main St", 1, 10)).getAddress());
    assertEquals("", (new ConnectionField(new SdpField('A', "42"))).getAddress());
    assertEquals("42", (new ConnectionField("", "42 Main St")).getAddress());
    assertEquals("", (new ConnectionField("", "/")).getAddress());
    assertEquals("", (new ConnectionField(new SdpField(new SdpField('A', "42")))).getAddress());
  }

  @Test
  void testGetTTL() {
    // Arrange, Act and Assert
    assertEquals(0, (new ConnectionField("Connection field")).getTTL());
    assertEquals(0, (new ConnectionField("/")).getTTL());
    assertEquals(0, (new ConnectionField("42 Main St", "42 Main St")).getTTL());
    assertEquals(0, (new ConnectionField("42 Main St", "42 Main St", 1, 10)).getTTL());
    assertEquals(0, (new ConnectionField(new SdpField('A', "42"))).getTTL());
    assertEquals(0, (new ConnectionField("", "42 Main St")).getTTL());
    assertEquals(0, (new ConnectionField("Address type", "/")).getTTL());
    assertEquals(0, (new ConnectionField(new SdpField(new SdpField('A', "42")))).getTTL());
  }

  @Test
  void testGetNum() {
    // Arrange, Act and Assert
    assertEquals(0, (new ConnectionField("Connection field")).getNum());
    assertEquals(0, (new ConnectionField("/")).getNum());
    assertEquals(0, (new ConnectionField("42 Main St", "42 Main St")).getNum());
    assertEquals(0, (new ConnectionField("42 Main St", "42 Main St", 1, 10)).getNum());
    assertEquals(0, (new ConnectionField(new SdpField('A', "42"))).getNum());
    assertEquals(0, (new ConnectionField("", "42 Main St")).getNum());
    assertEquals(0, (new ConnectionField("Address type", "/")).getNum());
    assertEquals(0, (new ConnectionField(new SdpField(new SdpField('A', "42")))).getNum());
  }
}

