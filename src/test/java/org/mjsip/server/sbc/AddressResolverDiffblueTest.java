package org.mjsip.server.sbc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import java.io.ByteArrayOutputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Hashtable;
import org.junit.jupiter.api.Test;
import org.zoolu.net.SocketAddress;
import org.zoolu.util.LogLevel;
import org.zoolu.util.LogWriter;
import org.zoolu.util.Timer;
import org.zoolu.util.TimerListener;

class AddressResolverDiffblueTest {
  @Test
  void testSize() {
    // Arrange, Act and Assert
    assertEquals(0, (new AddressResolver(10L, new LogWriter(new StringWriter()))).size());
  }

  @Test
  void testContains() {
    // Arrange
    AddressResolver addressResolver = new AddressResolver(10L, new LogWriter(new StringWriter()));

    // Act and Assert
    assertFalse(addressResolver.contains(new SocketAddress("42 Main St")));
  }

  @Test
  void testContains2() {
    // Arrange, Act and Assert
    assertFalse((new AddressResolver(10L, new LogWriter(new StringWriter()))).contains(null));
  }

  @Test
  void testUpdateBinding() {
    // Arrange
    AddressResolver addressResolver = new AddressResolver(10L, new LogWriter(new StringWriter()));
    SocketAddress refer_soaddr = new SocketAddress("42 Main St");

    // Act
    addressResolver.updateBinding(refer_soaddr, new SocketAddress("42 Main St"));

    // Assert
    assertEquals(1, addressResolver.time_table.size());
    assertEquals(1, addressResolver.size());
  }

  @Test
  void testGetSocketAddress() {
    // Arrange
    AddressResolver addressResolver = new AddressResolver(10L, new LogWriter(new StringWriter()));

    // Act and Assert
    assertNull(addressResolver.getSocketAddress(new SocketAddress("42 Main St")));
  }

  @Test
  void testGetSocketAddress2() {
    // Arrange, Act and Assert
    assertNull((new AddressResolver(10L, new LogWriter(new StringWriter()))).getSocketAddress(null));
  }

  @Test
  void testOnTimeout() {
    // Arrange
    AddressResolver addressResolver = new AddressResolver(10L, new LogWriter(new StringWriter()));

    // Act
    addressResolver.onTimeout(new Timer(10L, mock(TimerListener.class)));

    // Assert
    Timer timer = addressResolver.timer;
    assertTrue(timer.isRunning());
    assertEquals(10L, timer.getTime());
  }

  @Test
  void testOnTimeout2() {
    // Arrange
    AddressResolver addressResolver = new AddressResolver(-1L, new LogWriter(new StringWriter()));

    // Act
    addressResolver.onTimeout(new Timer(10L, mock(TimerListener.class)));

    // Assert
    Timer timer = addressResolver.timer;
    assertFalse(timer.isRunning());
    assertEquals(-1L, timer.getTime());
  }

  @Test
  void testOnTimeout3() {
    // Arrange
    AddressResolver addressResolver = new AddressResolver(-1L, null);

    // Act
    addressResolver.onTimeout(new Timer(10L, mock(TimerListener.class)));

    // Assert
    Timer timer = addressResolver.timer;
    assertFalse(timer.isRunning());
    assertEquals(-1L, timer.getTime());
  }

  @Test
  void testOnTimeout4() {
    // Arrange
    LogWriter logWriter = new LogWriter((Writer) null);
    logWriter.setLoggingLevel(null);
    AddressResolver addressResolver = new AddressResolver(10L, logWriter);

    // Act
    addressResolver.onTimeout(new Timer(10L, mock(TimerListener.class)));

    // Assert
    Timer timer = addressResolver.timer;
    assertTrue(timer.isRunning());
    assertEquals(10L, timer.getTime());
  }
}

