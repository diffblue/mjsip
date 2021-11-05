package org.zoolu.net;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import java.io.IOException;
import java.net.ServerSocket;
import javax.management.ServiceNotFoundException;
import javax.management.loading.MLet;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.FilteredClassLoader;

class TcpServerDiffblueTest {
  @Test
  void testConstructor() throws IOException {
    // Arrange and Act
    TcpServer actualTcpServer = new TcpServer(new ServerSocket(), mock(TcpServerListener.class));

    // Assert
    assertEquals(-1, actualTcpServer.getPort());
    assertNull(actualTcpServer.server_ipaddr);
    assertEquals(TcpServer.DEFAULT_SOCKET_TIMEOUT, actualTcpServer.socket_timeout);
    assertTrue(actualTcpServer.isRunning());
    assertEquals(0L, actualTcpServer.alive_time);
  }

  @Test
  void testToString() throws IOException {
    // Arrange, Act and Assert
    assertEquals("tcp:0.0.0.0:-1", (new TcpServer(new ServerSocket(), null)).toString());
  }

  @Test
  void testToString2() throws IOException {
    // Arrange
    TcpServer tcpServer = new TcpServer(new ServerSocket(), null);
    tcpServer.setPriority(1);

    // Act and Assert
    assertEquals("tcp:0.0.0.0:-1", tcpServer.toString());
  }

  @Test
  void testToString3() throws IOException {
    // Arrange
    TcpServer tcpServer = new TcpServer(new ServerSocket(), null);
    tcpServer.setContextClassLoader(new MLet());

    // Act and Assert
    assertEquals("tcp:0.0.0.0:-1", tcpServer.toString());
  }

  @Test
  void testToString4() throws IOException, ServiceNotFoundException {
    // Arrange
    MLet mLet = new MLet();
    mLet.addURL("https://example.org/example");

    TcpServer tcpServer = new TcpServer(new ServerSocket(), null);
    tcpServer.setContextClassLoader(mLet);

    // Act and Assert
    assertEquals("tcp:0.0.0.0:-1", tcpServer.toString());
  }

  @Test
  void testToString6() throws IOException, ServiceNotFoundException {
    // Arrange
    MLet mLet = new MLet();
    mLet.addURL("https://example.org/example");

    TcpServer tcpServer = new TcpServer(new ServerSocket(), mock(TcpServerListener.class));
    tcpServer.setContextClassLoader(mLet);

    // Act and Assert
    assertEquals("tcp:0.0.0.0:-1", tcpServer.toString());
  }

  @Test
  void testToString7() throws IOException {
    // Arrange
    TcpServer tcpServer = new TcpServer(new ServerSocket(), mock(TcpServerListener.class));
    tcpServer.setUncaughtExceptionHandler(null);
    tcpServer.setContextClassLoader(new MLet());

    // Act and Assert
    assertEquals("tcp:0.0.0.0:-1", tcpServer.toString());
  }

  @Test
  void testToString8() throws IOException, ServiceNotFoundException {
    // Arrange
    MLet mLet = new MLet();
    mLet.addURL("https://example.org/example");

    TcpServer tcpServer = new TcpServer(new ServerSocket(), mock(TcpServerListener.class));
    tcpServer.setUncaughtExceptionHandler(null);
    tcpServer.setContextClassLoader(mLet);

    // Act and Assert
    assertEquals("tcp:0.0.0.0:-1", tcpServer.toString());
  }

  @Test
  void testToString9() throws IOException {
    // Arrange
    TcpServer tcpServer = new TcpServer(new ServerSocket(), mock(TcpServerListener.class));
    tcpServer.setUncaughtExceptionHandler(null);
    tcpServer.setContextClassLoader(new FilteredClassLoader("foo", "foo", "foo"));

    // Act and Assert
    assertEquals("tcp:0.0.0.0:-1", tcpServer.toString());
  }

  @Test
  void testToString10() throws IOException {
    // Arrange
    TcpServer tcpServer = new TcpServer(new ServerSocket(), mock(TcpServerListener.class));
    tcpServer.setUncaughtExceptionHandler(null);
    tcpServer.setContextClassLoader(new FilteredClassLoader("java.text", "foo", "foo"));

    // Act and Assert
    assertEquals("tcp:0.0.0.0:-1", tcpServer.toString());
  }
}

