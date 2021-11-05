package org.mjsip.sip.header;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Vector;
import org.junit.jupiter.api.Test;

class ProxyRequireHeaderDiffblueTest {
  @Test
  void testAddOptionTag() {
    // Arrange
    ProxyRequireHeader proxyRequireHeader = new ProxyRequireHeader("Option tag");

    // Act
    proxyRequireHeader.addOptionTag("Option tag");

    // Assert
    assertEquals("Option tag,Option tag", proxyRequireHeader.getValue());
  }

  @Test
  void testAddOptionTag2() {
    // Arrange
    ProxyRequireHeader proxyRequireHeader = new ProxyRequireHeader("");

    // Act
    proxyRequireHeader.addOptionTag("Option tag");

    // Assert
    assertEquals("Option tag", proxyRequireHeader.getValue());
  }

  @Test
  void testAddOptionTag3() {
    // Arrange
    ProxyRequireHeader proxyRequireHeader = new ProxyRequireHeader(new Vector(1));

    // Act
    proxyRequireHeader.addOptionTag("Option tag");

    // Assert
    assertEquals("Option tag", proxyRequireHeader.getValue());
  }

  @Test
  void testConstructor() {
    // Arrange and Act
    ProxyRequireHeader actualProxyRequireHeader = new ProxyRequireHeader("Option tag");

    // Assert
    Vector allOptionTags = actualProxyRequireHeader.getAllOptionTags();
    assertEquals(1, allOptionTags.size());
    assertEquals("Option tag", allOptionTags.get(0));
    assertEquals("Proxy-Require: Option tag\r\n", actualProxyRequireHeader.toString());
    assertEquals("Option tag", actualProxyRequireHeader.getValue());
    assertEquals(CoreSipHeaders.Proxy_Require, actualProxyRequireHeader.getName());
  }

  @Test
  void testConstructor2() {
    // Arrange and Act
    ProxyRequireHeader actualProxyRequireHeader = new ProxyRequireHeader(new Vector(1));

    // Assert
    assertNull(actualProxyRequireHeader.getAllOptionTags());
    assertEquals(CoreSipHeaders.Proxy_Require, actualProxyRequireHeader.getName());
  }

  @Test
  void testConstructor3() {
    // Arrange
    Vector vector = new Vector(1);
    vector.add("42");

    // Act
    ProxyRequireHeader actualProxyRequireHeader = new ProxyRequireHeader(vector);

    // Assert
    assertEquals("42", actualProxyRequireHeader.getValue());
    assertEquals(CoreSipHeaders.Proxy_Require, actualProxyRequireHeader.getName());
  }

  @Test
  void testConstructor4() {
    // Arrange
    Vector vector = new Vector(1);
    vector.add("42");
    vector.add("42");

    // Act
    ProxyRequireHeader actualProxyRequireHeader = new ProxyRequireHeader(vector);

    // Assert
    assertEquals("42,42", actualProxyRequireHeader.getValue());
    assertEquals(CoreSipHeaders.Proxy_Require, actualProxyRequireHeader.getName());
  }

  @Test
  void testConstructor5() {
    // Arrange
    Vector vector = new Vector(1);
    vector.add("foo");

    // Act
    ProxyRequireHeader actualProxyRequireHeader = new ProxyRequireHeader(vector);

    // Assert
    assertEquals("foo", actualProxyRequireHeader.getValue());
    assertEquals(CoreSipHeaders.Proxy_Require, actualProxyRequireHeader.getName());
  }

  @Test
  void testConstructor6() {
    // Arrange and Act
    ProxyRequireHeader actualProxyRequireHeader = new ProxyRequireHeader(new Header("Hname", "42"));

    // Assert
    assertEquals("42", actualProxyRequireHeader.getValue());
    assertEquals("Hname", actualProxyRequireHeader.getName());
  }

  @Test
  void testConstructor7() {
    // Arrange
    Header header = new Header("Hname", "42");
    header.setValue("42");

    // Act
    ProxyRequireHeader actualProxyRequireHeader = new ProxyRequireHeader(header);

    // Assert
    assertEquals("42", actualProxyRequireHeader.getValue());
    assertEquals("Hname", actualProxyRequireHeader.getName());
  }

  @Test
  void testConstructor8() {
    // Arrange and Act
    ProxyRequireHeader actualProxyRequireHeader = new ProxyRequireHeader(new String[]{"Option tags"});

    // Assert
    assertEquals("Option tags", actualProxyRequireHeader.getValue());
    assertEquals(CoreSipHeaders.Proxy_Require, actualProxyRequireHeader.getName());
  }

  @Test
  void testConstructor9() {
    // Arrange and Act
    ProxyRequireHeader actualProxyRequireHeader = new ProxyRequireHeader(new String[]{});

    // Assert
    assertNull(actualProxyRequireHeader.getAllOptionTags());
    assertEquals(CoreSipHeaders.Proxy_Require, actualProxyRequireHeader.getName());
  }

  @Test
  void testConstructor10() {
    // Arrange and Act
    ProxyRequireHeader actualProxyRequireHeader = new ProxyRequireHeader(
        new String[]{CoreSipHeaders.Proxy_Require, CoreSipHeaders.Proxy_Require});

    // Assert
    assertEquals("Proxy-Require,Proxy-Require", actualProxyRequireHeader.getValue());
    assertEquals(CoreSipHeaders.Proxy_Require, actualProxyRequireHeader.getName());
  }

  @Test
  void testGetAllOptionTags() {
    // Arrange and Act
    Vector actualAllOptionTags = (new ProxyRequireHeader("Option tag")).getAllOptionTags();

    // Assert
    assertEquals(1, actualAllOptionTags.size());
    assertEquals("Option tag", actualAllOptionTags.get(0));
  }

  @Test
  void testGetAllOptionTags2() {
    // Arrange and Act
    Vector actualAllOptionTags = (new ProxyRequireHeader(new String[]{"foo", "foo", "foo"})).getAllOptionTags();

    // Assert
    assertEquals(3, actualAllOptionTags.size());
    assertEquals("foo", actualAllOptionTags.get(0));
    assertEquals("foo", actualAllOptionTags.get(1));
    assertEquals("foo", actualAllOptionTags.get(2));
  }

  @Test
  void testGetAllOptionTags3() {
    // Arrange, Act and Assert
    assertNull((new ProxyRequireHeader(new Vector(1))).getAllOptionTags());
  }

  @Test
  void testGetAllOptionTags4() {
    // Arrange
    ProxyRequireHeader proxyRequireHeader = new ProxyRequireHeader("Option tag");
    proxyRequireHeader.addOptionTag("");

    // Act
    Vector actualAllOptionTags = proxyRequireHeader.getAllOptionTags();

    // Assert
    assertEquals(1, actualAllOptionTags.size());
    assertEquals("Option tag", actualAllOptionTags.get(0));
  }

  @Test
  void testHasOptionTag() {
    // Arrange, Act and Assert
    assertTrue((new ProxyRequireHeader("Option tag")).hasOptionTag("Option tag"));
    assertFalse((new ProxyRequireHeader(CoreSipHeaders.Proxy_Require)).hasOptionTag("Option tag"));
    assertFalse((new ProxyRequireHeader(new String[]{"foo", "foo", "foo"})).hasOptionTag("Option tag"));
    assertFalse((new ProxyRequireHeader(new Vector(1))).hasOptionTag("Option tag"));
  }

  @Test
  void testHasOptionTag2() {
    // Arrange
    ProxyRequireHeader proxyRequireHeader = new ProxyRequireHeader("Option tag");
    proxyRequireHeader.addOptionTag("");

    // Act and Assert
    assertTrue(proxyRequireHeader.hasOptionTag("Option tag"));
  }
}

