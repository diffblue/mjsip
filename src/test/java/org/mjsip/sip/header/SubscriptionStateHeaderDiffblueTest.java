package org.mjsip.sip.header;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class SubscriptionStateHeaderDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    SubscriptionStateHeader actualSubscriptionStateHeader = new SubscriptionStateHeader("MD");

    // Assert
    assertEquals(-1, actualSubscriptionStateHeader.getExpires());
    assertEquals("Subscription-State: MD\r\n", actualSubscriptionStateHeader.toString());
    assertFalse(actualSubscriptionStateHeader.isTerminated());
    assertFalse(actualSubscriptionStateHeader.isPending());
    assertFalse(actualSubscriptionStateHeader.isActive());
    assertFalse(actualSubscriptionStateHeader.hasReason());
    assertFalse(actualSubscriptionStateHeader.hasExpires());
    assertEquals("MD", actualSubscriptionStateHeader.getValue());
    assertEquals("MD", actualSubscriptionStateHeader.getState());
    assertNull(actualSubscriptionStateHeader.getReason());
    assertNull(actualSubscriptionStateHeader.getParameters());
    assertTrue(actualSubscriptionStateHeader.getParameterNames().isEmpty());
    assertEquals(SipHeaders.Subscription_State, actualSubscriptionStateHeader.getName());
  }

  @Test
  void testConstructor2() {
    // Arrange and Act
    SubscriptionStateHeader actualSubscriptionStateHeader = new SubscriptionStateHeader(new Header("Hname", "42"));

    // Assert
    assertEquals("42", actualSubscriptionStateHeader.getValue());
    assertEquals("Hname", actualSubscriptionStateHeader.getName());
  }

  @Test
  void testConstructor3() {
    // Arrange
    Header header = new Header("Hname", "42");
    header.setValue("42");

    // Act
    SubscriptionStateHeader actualSubscriptionStateHeader = new SubscriptionStateHeader(header);

    // Assert
    assertEquals("42", actualSubscriptionStateHeader.getValue());
    assertEquals("Hname", actualSubscriptionStateHeader.getName());
  }

  @Test
  void testGetState() {
    // Arrange, Act and Assert
    assertEquals("MD", (new SubscriptionStateHeader("MD")).getState());
    assertEquals("", (new SubscriptionStateHeader("")).getState());
  }

  @Test
  void testGetState2() {
    // Arrange
    SubscriptionStateHeader subscriptionStateHeader = new SubscriptionStateHeader(new Header());
    subscriptionStateHeader.setValue("42");

    // Act and Assert
    assertEquals("42", subscriptionStateHeader.getState());
  }

  @Test
  void testIsActive() {
    // Arrange, Act and Assert
    assertFalse((new SubscriptionStateHeader("MD")).isActive());
    assertTrue((new SubscriptionStateHeader(SubscriptionStateHeader.ACTIVE)).isActive());
    assertFalse((new SubscriptionStateHeader("")).isActive());
  }

  @Test
  void testIsActive2() {
    // Arrange
    SubscriptionStateHeader subscriptionStateHeader = new SubscriptionStateHeader(new Header());
    subscriptionStateHeader.setValue("42");

    // Act and Assert
    assertFalse(subscriptionStateHeader.isActive());
  }

  @Test
  void testIsPending() {
    // Arrange, Act and Assert
    assertFalse((new SubscriptionStateHeader("MD")).isPending());
    assertTrue((new SubscriptionStateHeader(SubscriptionStateHeader.PENDING)).isPending());
    assertFalse((new SubscriptionStateHeader("")).isPending());
  }

  @Test
  void testIsPending2() {
    // Arrange
    SubscriptionStateHeader subscriptionStateHeader = new SubscriptionStateHeader(new Header());
    subscriptionStateHeader.setValue("42");

    // Act and Assert
    assertFalse(subscriptionStateHeader.isPending());
  }

  @Test
  void testIsTerminated() {
    // Arrange, Act and Assert
    assertFalse((new SubscriptionStateHeader("MD")).isTerminated());
    assertTrue((new SubscriptionStateHeader(SubscriptionStateHeader.TERMINATED)).isTerminated());
    assertFalse((new SubscriptionStateHeader("")).isTerminated());
  }

  @Test
  void testIsTerminated2() {
    // Arrange
    SubscriptionStateHeader subscriptionStateHeader = new SubscriptionStateHeader(new Header());
    subscriptionStateHeader.setValue("42");

    // Act and Assert
    assertFalse(subscriptionStateHeader.isTerminated());
  }

  @Test
  void testSetExpires() {
    // Arrange
    SubscriptionStateHeader subscriptionStateHeader = new SubscriptionStateHeader("MD");

    // Act
    SubscriptionStateHeader actualSetExpiresResult = subscriptionStateHeader.setExpires(1);

    // Assert
    assertSame(subscriptionStateHeader, actualSetExpiresResult);
    assertEquals("MD;expires=1", actualSetExpiresResult.getValue());
  }

  @Test
  void testSetExpires2() {
    // Arrange
    SubscriptionStateHeader subscriptionStateHeader = new SubscriptionStateHeader(new Header());

    // Act
    SubscriptionStateHeader actualSetExpiresResult = subscriptionStateHeader.setExpires(1);

    // Assert
    assertSame(subscriptionStateHeader, actualSetExpiresResult);
    assertEquals(";expires=1", actualSetExpiresResult.getValue());
  }

  @Test
  void testHasExpires() {
    // Arrange, Act and Assert
    assertFalse((new SubscriptionStateHeader("MD")).hasExpires());
  }

  @Test
  void testHasExpires2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertFalse((new SubscriptionStateHeader(header)).hasExpires());
  }

  @Test
  void testGetExpires() {
    // Arrange, Act and Assert
    assertEquals(-1, (new SubscriptionStateHeader("MD")).getExpires());
    assertEquals(-1, (new SubscriptionStateHeader(new Header("Hname", "42"))).getExpires());
  }

  @Test
  void testSetReason() {
    // Arrange
    SubscriptionStateHeader subscriptionStateHeader = new SubscriptionStateHeader("MD");

    // Act
    SubscriptionStateHeader actualSetReasonResult = subscriptionStateHeader.setReason("Just cause");

    // Assert
    assertSame(subscriptionStateHeader, actualSetReasonResult);
    assertEquals("MD;reason=Just cause", actualSetReasonResult.getValue());
  }

  @Test
  void testSetReason2() {
    // Arrange
    SubscriptionStateHeader subscriptionStateHeader = new SubscriptionStateHeader(new Header());

    // Act
    SubscriptionStateHeader actualSetReasonResult = subscriptionStateHeader.setReason("Just cause");

    // Assert
    assertSame(subscriptionStateHeader, actualSetReasonResult);
    assertEquals(";reason=Just cause", actualSetReasonResult.getValue());
  }

  @Test
  void testHasReason() {
    // Arrange, Act and Assert
    assertFalse((new SubscriptionStateHeader("MD")).hasReason());
  }

  @Test
  void testHasReason2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertFalse((new SubscriptionStateHeader(header)).hasReason());
  }

  @Test
  void testGetReason() {
    // Arrange, Act and Assert
    assertNull((new SubscriptionStateHeader("MD")).getReason());
  }

  @Test
  void testGetReason2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertNull((new SubscriptionStateHeader(header)).getReason());
  }

  @Test
  void testGetParameter() {
    // Arrange, Act and Assert
    assertNull((new SubscriptionStateHeader("MD")).getParameter("Pname"));
  }

  @Test
  void testGetParameter2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertNull((new SubscriptionStateHeader(header)).getParameter("Pname"));
  }

  @Test
  void testGetParameterNames() {
    // Arrange, Act and Assert
    assertTrue((new SubscriptionStateHeader("MD")).getParameterNames().isEmpty());
  }

  @Test
  void testGetParameterNames2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertTrue((new SubscriptionStateHeader(header)).getParameterNames().isEmpty());
  }

  @Test
  void testGetParameters() {
    // Arrange, Act and Assert
    assertNull((new SubscriptionStateHeader("MD")).getParameters());
  }

  @Test
  void testGetParameters2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertNull((new SubscriptionStateHeader(header)).getParameters());
  }

  @Test
  void testHasParameter() {
    // Arrange, Act and Assert
    assertFalse((new SubscriptionStateHeader("MD")).hasParameter("Pname"));
  }

  @Test
  void testHasParameter2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertFalse((new SubscriptionStateHeader(header)).hasParameter("Pname"));
  }

  @Test
  void testHasParameters() {
    // Arrange, Act and Assert
    assertFalse((new SubscriptionStateHeader("MD")).hasParameters());
  }

  @Test
  void testHasParameters2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertFalse((new SubscriptionStateHeader(header)).hasParameters());
  }

  @Test
  void testIndexOfFirstSemi() {
    // Arrange, Act and Assert
    assertEquals(-1, (new SubscriptionStateHeader("MD")).indexOfFirstSemi());
  }

  @Test
  void testIndexOfFirstSemi2() {
    // Arrange
    Header header = new Header();
    header.setValue("42");

    // Act and Assert
    assertEquals(-1, (new SubscriptionStateHeader(header)).indexOfFirstSemi());
  }

  @Test
  void testSetParameter() {
    // Arrange
    SubscriptionStateHeader subscriptionStateHeader = new SubscriptionStateHeader("MD");

    // Act
    subscriptionStateHeader.setParameter("Pname", "42");

    // Assert
    assertEquals("MD;Pname=42", subscriptionStateHeader.getValue());
  }

  @Test
  void testSetParameter2() {
    // Arrange
    SubscriptionStateHeader subscriptionStateHeader = new SubscriptionStateHeader(new Header());

    // Act
    subscriptionStateHeader.setParameter("Pname", "42");

    // Assert
    assertEquals(";Pname=42", subscriptionStateHeader.getValue());
  }
}

