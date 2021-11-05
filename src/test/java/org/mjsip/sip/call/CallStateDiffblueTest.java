package org.mjsip.sip.call;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Objects;
import org.junit.jupiter.api.Test;

class CallStateDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    CallState actualCallState = new CallState();

    // Assert
    assertEquals(0, actualCallState.getState());
    assertEquals("C_IDLE", actualCallState.toString());
    assertFalse(actualCallState.isOutgoing());
    assertFalse(actualCallState.isIncoming());
    assertTrue(actualCallState.isIdle());
    assertFalse(actualCallState.isClosed());
    assertFalse(actualCallState.isActive());
  }

  @Test
  void testConstructor2() {
    // Arrange and Act
    CallState actualCallState = new CallState(1);

    // Assert
    assertEquals(1, actualCallState.getState());
    assertEquals("C_INCOMING", actualCallState.toString());
    assertFalse(actualCallState.isOutgoing());
    assertTrue(actualCallState.isIncoming());
    assertFalse(actualCallState.isIdle());
    assertFalse(actualCallState.isClosed());
    assertFalse(actualCallState.isActive());
  }

  @Test
  void testConstructor3() {
    // Arrange
    CallState callState = new CallState(1);

    // Act
    CallState actualCallState = new CallState(callState);

    // Assert
    assertEquals(1, actualCallState.getState());
    assertEquals("C_INCOMING", actualCallState.toString());
    assertFalse(actualCallState.isOutgoing());
    assertTrue(actualCallState.isIncoming());
    assertFalse(actualCallState.isIdle());
    assertFalse(actualCallState.isClosed());
    assertFalse(actualCallState.isActive());
    assertEquals(1, callState.getState());
    assertEquals("C_INCOMING", callState.toString());
    assertFalse(callState.isOutgoing());
    assertTrue(callState.isIncoming());
    assertFalse(callState.isIdle());
    assertFalse(callState.isClosed());
    assertFalse(callState.isActive());
  }

  @Test
  void testIsIdle() {
    // Arrange, Act and Assert
    assertFalse((new CallState(1)).isIdle());
    assertTrue((new CallState(0)).isIdle());
  }

  @Test
  void testIsIncoming() {
    // Arrange, Act and Assert
    assertTrue((new CallState(1)).isIncoming());
    assertFalse((new CallState(0)).isIncoming());
  }

  @Test
  void testIsOutgoing() {
    // Arrange, Act and Assert
    assertFalse((new CallState(1)).isOutgoing());
    assertTrue((new CallState(2)).isOutgoing());
  }

  @Test
  void testIsActive() {
    // Arrange, Act and Assert
    assertFalse((new CallState(1)).isActive());
    assertTrue((new CallState(6)).isActive());
  }

  @Test
  void testIsClosed() {
    // Arrange, Act and Assert
    assertFalse((new CallState(1)).isClosed());
    assertTrue((new CallState(CallState.C_CLOSED)).isClosed());
  }

  @Test
  void testToString() {
    // Arrange, Act and Assert
    assertEquals("C_INCOMING", (new CallState(1)).toString());
    assertEquals("C_IDLE", (new CallState(0)).toString());
    assertEquals("C_OUTGOING", (new CallState(2)).toString());
    assertNull((new CallState(3)).toString());
    assertEquals("C_ACTIVE", (new CallState(6)).toString());
    assertEquals("C_CLOSED", (new CallState(CallState.C_CLOSED)).toString());
  }

  @Test
  void testEquals() {
    // Arrange, Act and Assert
    assertFalse((new CallState(1)).equals((Object) null));
    assertFalse((new CallState(1)).equals("Different type to CallState"));
    assertTrue((new CallState(1)).equals(1));
    assertFalse((new CallState(0)).equals(1));
  }

  @Test
  void testEquals2() {
    // Arrange
    CallState callState = new CallState(1);

    // Act and Assert
    assertTrue(callState.equals(callState));
    int expectedHashCodeResult = callState.hashCode();
    assertEquals(expectedHashCodeResult, callState.hashCode());
  }

  @Test
  void testEquals3() {
    // Arrange
    CallState callState = new CallState(1);
    CallState callState1 = new CallState(1);

    // Act and Assert
    assertTrue(callState.equals(callState1));
    int notExpectedHashCodeResult = callState.hashCode();
    assertFalse(Objects.equals(notExpectedHashCodeResult, callState1.hashCode()));
  }

  @Test
  void testEquals4() {
    // Arrange
    CallState callState = new CallState(0);

    // Act and Assert
    assertFalse(callState.equals(new CallState(1)));
  }

  @Test
  void testGetState() {
    // Arrange, Act and Assert
    assertEquals(1, (new CallState(1)).getState());
  }

  @Test
  void testSetState() {
    // Arrange
    CallState callState = new CallState(1);

    // Act
    callState.setState(1);

    // Assert
    assertEquals(1, callState.getState());
  }
}

