package org.mjsip.server.sbc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import java.io.StringWriter;
import java.util.Hashtable;
import org.junit.jupiter.api.Test;
import org.mjsip.sip.provider.SipProvider;
import org.zoolu.net.SocketAddress;
import org.zoolu.util.LogWriter;
import org.zoolu.util.Timer;
import org.zoolu.util.TimerListener;

class AddressResolverKeepAliveDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange
    LogWriter logger = new LogWriter(new StringWriter());

    // Act
    AddressResolverKeepAlive actualAddressResolverKeepAlive = new AddressResolverKeepAlive(10L, logger,
        new SipProvider("File"), 10L);

    // Assert
    Hashtable hashtable = actualAddressResolverKeepAlive.binding_table;
    assertEquals(hashtable, actualAddressResolverKeepAlive.time_table);
    assertEquals(5L, actualAddressResolverKeepAlive.expire_time);
    assertEquals(10L, actualAddressResolverKeepAlive.refresh_time);
    Hashtable hashtable1 = actualAddressResolverKeepAlive.keepalive_daemons;
    assertEquals(hashtable, hashtable1);
    assertEquals(hashtable1, actualAddressResolverKeepAlive.binding_table);
    assertEquals(10L, actualAddressResolverKeepAlive.keepalive_time);
    assertTrue(actualAddressResolverKeepAlive.logger instanceof LogWriter);
    Timer timer = actualAddressResolverKeepAlive.timer;
    assertTrue(timer.isRunning());
    assertEquals(10L, timer.getTime());
    assertTrue(Timer.DEFAULT_DAEMON_MODE);
  }

  @Test
  void testConstructor2() {
    // Arrange
    LogWriter logger = new LogWriter(new StringWriter());

    // Act
    AddressResolverKeepAlive actualAddressResolverKeepAlive = new AddressResolverKeepAlive(2L, logger,
        new SipProvider("File"), 10L);

    // Assert
    Hashtable hashtable = actualAddressResolverKeepAlive.binding_table;
    assertEquals(hashtable, actualAddressResolverKeepAlive.time_table);
    assertEquals(1L, actualAddressResolverKeepAlive.expire_time);
    assertEquals(2L, actualAddressResolverKeepAlive.refresh_time);
    Hashtable hashtable1 = actualAddressResolverKeepAlive.keepalive_daemons;
    assertEquals(hashtable, hashtable1);
    assertEquals(hashtable1, actualAddressResolverKeepAlive.binding_table);
    assertEquals(10L, actualAddressResolverKeepAlive.keepalive_time);
    assertTrue(actualAddressResolverKeepAlive.logger instanceof LogWriter);
    Timer timer = actualAddressResolverKeepAlive.timer;
    assertTrue(timer.isRunning());
    assertEquals(2L, timer.getTime());
    assertTrue(Timer.DEFAULT_DAEMON_MODE);
  }

  @Test
  void testConstructor3() {
    // Arrange
    LogWriter logger = new LogWriter(new StringWriter());

    // Act
    AddressResolverKeepAlive actualAddressResolverKeepAlive = new AddressResolverKeepAlive(-1L, logger,
        new SipProvider("File"), 10L);

    // Assert
    Hashtable hashtable = actualAddressResolverKeepAlive.binding_table;
    assertEquals(hashtable, actualAddressResolverKeepAlive.time_table);
    assertEquals(0L, actualAddressResolverKeepAlive.expire_time);
    assertEquals(-1L, actualAddressResolverKeepAlive.refresh_time);
    Hashtable hashtable1 = actualAddressResolverKeepAlive.keepalive_daemons;
    assertEquals(hashtable, hashtable1);
    assertEquals(hashtable1, actualAddressResolverKeepAlive.binding_table);
    assertEquals(10L, actualAddressResolverKeepAlive.keepalive_time);
    assertTrue(actualAddressResolverKeepAlive.logger instanceof LogWriter);
    Timer timer = actualAddressResolverKeepAlive.timer;
    assertFalse(timer.isRunning());
    assertEquals(-1L, timer.getTime());
    assertTrue(Timer.DEFAULT_DAEMON_MODE);
  }

  @Test
  void testUpdateBinding() {
    // Arrange
    LogWriter logger = new LogWriter(new StringWriter());
    AddressResolverKeepAlive addressResolverKeepAlive = new AddressResolverKeepAlive(10L, logger,
        new SipProvider("File"), 10L);
    SocketAddress refer_soaddr = new SocketAddress("42 Main St");

    // Act
    addressResolverKeepAlive.updateBinding(refer_soaddr, new SocketAddress("42 Main St"));

    // Assert
    assertEquals(1, addressResolverKeepAlive.time_table.size());
    assertEquals(1, addressResolverKeepAlive.size());
    assertEquals(1, addressResolverKeepAlive.keepalive_daemons.size());
  }

  @Test
  void testUpdateBinding2() {
    // Arrange
    AddressResolverKeepAlive addressResolverKeepAlive = new AddressResolverKeepAlive(10L,
        new LogWriter(new StringWriter()), null, 10L);
    SocketAddress refer_soaddr = new SocketAddress("42 Main St");

    // Act
    addressResolverKeepAlive.updateBinding(refer_soaddr, new SocketAddress("42 Main St"));

    // Assert
    assertEquals(1, addressResolverKeepAlive.time_table.size());
    assertEquals(1, addressResolverKeepAlive.size());
    assertEquals(1, addressResolverKeepAlive.keepalive_daemons.size());
  }

  @Test
  void testUpdateBinding3() {
    // Arrange
    LogWriter logger = new LogWriter(new StringWriter());
    AddressResolverKeepAlive addressResolverKeepAlive = new AddressResolverKeepAlive(10L, logger,
        new SipProvider("File"), 10L);
    SocketAddress socketAddress = new SocketAddress("42 Main St");

    // Act
    addressResolverKeepAlive.updateBinding(null, socketAddress);

    // Assert that nothing has changed
    assertEquals("42 Main St:-1", socketAddress.toString());
    assertEquals(-1, socketAddress.getPort());
    Hashtable hashtable = addressResolverKeepAlive.binding_table;
    assertEquals(hashtable, addressResolverKeepAlive.time_table);
    assertEquals(5L, addressResolverKeepAlive.expire_time);
    assertEquals(10L, addressResolverKeepAlive.refresh_time);
    Hashtable hashtable1 = addressResolverKeepAlive.keepalive_daemons;
    assertEquals(hashtable, hashtable1);
    assertEquals(hashtable1, addressResolverKeepAlive.binding_table);
    assertEquals(10L, addressResolverKeepAlive.keepalive_time);
    assertTrue(addressResolverKeepAlive.logger instanceof LogWriter);
    assertTrue(Timer.DEFAULT_DAEMON_MODE);
  }

  @Test
  void testRemoveBinding() {
    // Arrange
    LogWriter logger = new LogWriter(new StringWriter());
    AddressResolverKeepAlive addressResolverKeepAlive = new AddressResolverKeepAlive(10L, logger,
        new SipProvider("File"), 10L);
    SocketAddress socketAddress = new SocketAddress("42 Main St");

    // Act
    addressResolverKeepAlive.removeBinding(socketAddress);

    // Assert that nothing has changed
    assertEquals("42 Main St:-1", socketAddress.toString());
    assertEquals(-1, socketAddress.getPort());
    Hashtable hashtable = addressResolverKeepAlive.binding_table;
    assertEquals(hashtable, addressResolverKeepAlive.time_table);
    assertEquals(5L, addressResolverKeepAlive.expire_time);
    assertEquals(10L, addressResolverKeepAlive.refresh_time);
    Hashtable hashtable1 = addressResolverKeepAlive.keepalive_daemons;
    assertEquals(hashtable, hashtable1);
    assertEquals(hashtable1, addressResolverKeepAlive.binding_table);
    assertEquals(10L, addressResolverKeepAlive.keepalive_time);
    assertTrue(addressResolverKeepAlive.logger instanceof LogWriter);
    assertTrue(Timer.DEFAULT_DAEMON_MODE);
  }

  @Test
  void testRemoveBinding2() {
    // Arrange
    LogWriter logger = new LogWriter(new StringWriter());
    AddressResolverKeepAlive addressResolverKeepAlive = new AddressResolverKeepAlive(10L, logger,
        new SipProvider("File"), 10L);

    // Act
    addressResolverKeepAlive.removeBinding(null);

    // Assert that nothing has changed
    Hashtable hashtable = addressResolverKeepAlive.binding_table;
    assertEquals(hashtable, addressResolverKeepAlive.time_table);
    assertEquals(5L, addressResolverKeepAlive.expire_time);
    assertEquals(10L, addressResolverKeepAlive.refresh_time);
    Hashtable hashtable1 = addressResolverKeepAlive.keepalive_daemons;
    assertEquals(hashtable, hashtable1);
    assertEquals(hashtable1, addressResolverKeepAlive.binding_table);
    assertEquals(10L, addressResolverKeepAlive.keepalive_time);
    assertTrue(addressResolverKeepAlive.logger instanceof LogWriter);
    assertTrue(Timer.DEFAULT_DAEMON_MODE);
  }

  @Test
  void testOnTimeout() {
    // Arrange
    LogWriter logger = new LogWriter(new StringWriter());
    AddressResolverKeepAlive addressResolverKeepAlive = new AddressResolverKeepAlive(10L, logger,
        new SipProvider("File"), 10L);

    // Act
    addressResolverKeepAlive.onTimeout(new Timer(10L, mock(TimerListener.class)));

    // Assert
    Timer timer = addressResolverKeepAlive.timer;
    assertEquals(10L, timer.getTime());
    assertTrue(timer.isRunning());
  }

  @Test
  void testOnTimeout2() {
    // Arrange
    LogWriter logger = new LogWriter(new StringWriter());
    AddressResolverKeepAlive addressResolverKeepAlive = new AddressResolverKeepAlive(-1L, logger,
        new SipProvider("File"), 10L);

    // Act
    addressResolverKeepAlive.onTimeout(new Timer(10L, mock(TimerListener.class)));

    // Assert
    Timer timer = addressResolverKeepAlive.timer;
    assertEquals(-1L, timer.getTime());
    assertFalse(timer.isRunning());
  }

  @Test
  void testOnTimeout3() {
    // Arrange
    AddressResolverKeepAlive addressResolverKeepAlive = new AddressResolverKeepAlive(10L, null, new SipProvider("File"),
        10L);

    // Act
    addressResolverKeepAlive.onTimeout(new Timer(10L, mock(TimerListener.class)));

    // Assert
    Timer timer = addressResolverKeepAlive.timer;
    assertTrue(timer.isRunning());
    assertEquals(10L, timer.getTime());
  }
}

