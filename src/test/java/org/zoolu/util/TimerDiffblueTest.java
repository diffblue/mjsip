package org.zoolu.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.Test;

class TimerDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    Timer actualTimer = new Timer(10L, mock(TimerListener.class));

    // Assert
    assertEquals(10L, actualTimer.getTime());
    assertFalse(actualTimer.isRunning());
  }

  @Test
  void testConstructor2() {
    // Arrange and Act
    Timer actualTimer = new Timer(10L, mock(TimerListener.class));

    // Assert
    assertEquals(0L, actualTimer.start_time);
    assertFalse(actualTimer.isRunning());
    assertEquals(10L, actualTimer.getTime());
  }

  @Test
  void testGetExpirationTime() {
    // Arrange, Act and Assert
    assertEquals(10L, (new Timer(10L, mock(TimerListener.class))).getExpirationTime());
  }

  @Test
  void testStart4() {
    // Arrange
    TimerListener timerListener = mock(TimerListener.class);
    doNothing().when(timerListener).onTimeout((Timer) any());
    Timer timer = new Timer(-1L, timerListener);

    // Act
    timer.start();

    // Assert that nothing has changed
    assertEquals(0L, timer.start_time);
    assertFalse(timer.daemon_mode);
    assertFalse(timer.isRunning());
    assertEquals(-1L, timer.getTime());
  }

  @Test
  void testStart5() {
    // Arrange
    TimerListener timerListener = mock(TimerListener.class);
    doNothing().when(timerListener).onTimeout((Timer) any());
    Timer timer = new Timer(Long.MIN_VALUE, timerListener);

    // Act
    timer.start();

    // Assert that nothing has changed
    assertEquals(0L, timer.start_time);
    assertFalse(timer.daemon_mode);
    assertFalse(timer.isRunning());
    assertEquals(Long.MIN_VALUE, timer.getTime());
  }

  @Test
  void testStart7() {
    // Arrange
    TimerListener timerListener = mock(TimerListener.class);
    doNothing().when(timerListener).onTimeout((Timer) any());
    Timer timer = new Timer(0L, timerListener);

    // Act
    timer.start(true);

    // Assert
    verify(timerListener).onTimeout((Timer) any());
    assertNull(timer.listener);
    assertTrue(timer.daemon_mode);
    assertFalse(timer.isRunning());
  }

  @Test
  void testStart8() {
    // Arrange
    TimerListener timerListener = mock(TimerListener.class);
    doNothing().when(timerListener).onTimeout((Timer) any());
    Timer timer = new Timer(10L, timerListener);

    // Act
    timer.start(true);

    // Assert
    assertTrue(timer.daemon_mode);
    assertTrue(timer.isRunning());
  }

  @Test
  void testStart9() {
    // Arrange
    TimerListener timerListener = mock(TimerListener.class);
    doNothing().when(timerListener).onTimeout((Timer) any());
    Timer timer = new Timer(-1L, timerListener);

    // Act
    timer.start(true);

    // Assert that nothing has changed
    assertEquals(0L, timer.start_time);
    assertFalse(timer.daemon_mode);
    assertFalse(timer.isRunning());
    assertEquals(-1L, timer.getTime());
  }

  @Test
  void testStart10() {
    // Arrange
    TimerListener timerListener = mock(TimerListener.class);
    doNothing().when(timerListener).onTimeout((Timer) any());
    Timer timer = new Timer(10L, timerListener);

    // Act
    timer.start(false);

    // Assert
    assertFalse(timer.daemon_mode);
    assertTrue(timer.isRunning());
  }

  @Test
  void testStart11() {
    // Arrange
    TimerListener timerListener = mock(TimerListener.class);
    doNothing().when(timerListener).onTimeout((Timer) any());
    Timer timer = new Timer(0L, timerListener);

    // Act
    timer.start(false);

    // Assert
    verify(timerListener).onTimeout((Timer) any());
    assertNull(timer.listener);
    assertFalse(timer.daemon_mode);
    assertFalse(timer.isRunning());
  }

  @Test
  void testStart12() {
    // Arrange
    Timer timer = new Timer(0L, null);

    // Act
    timer.start(true);

    // Assert
    assertNull(timer.listener);
    assertTrue(timer.daemon_mode);
    assertFalse(timer.isRunning());
  }

  @Test
  void testHalt() {
    // Arrange
    Timer timer = new Timer(10L, mock(TimerListener.class));

    // Act
    timer.halt();

    // Assert that nothing has changed
    assertEquals(0L, timer.start_time);
    assertFalse(timer.daemon_mode);
    assertFalse(timer.isRunning());
    assertEquals(10L, timer.getTime());
  }
}

