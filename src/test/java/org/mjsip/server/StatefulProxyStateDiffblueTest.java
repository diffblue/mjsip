package org.mjsip.server;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import java.util.Hashtable;
import org.junit.jupiter.api.Test;
import org.mjsip.sip.provider.SipProvider;
import org.mjsip.sip.transaction.TransactionServer;
import org.mjsip.sip.transaction.TransactionServerListener;

class StatefulProxyStateDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    StatefulProxyState actualStatefulProxyState = new StatefulProxyState();

    // Assert
    Hashtable hashtable = actualStatefulProxyState.s_clients;
    Hashtable hashtable1 = actualStatefulProxyState.c_server;
    assertEquals(hashtable, hashtable1);
    assertEquals(hashtable, actualStatefulProxyState.s_response);
    assertEquals(hashtable1, actualStatefulProxyState.s_clients);
  }

  @Test
  void testHasServer() {
    // Arrange
    StatefulProxyState statefulProxyState = new StatefulProxyState();

    // Act and Assert
    assertFalse(statefulProxyState
        .hasServer(new TransactionServer(new SipProvider("File"), "Method", mock(TransactionServerListener.class))));
  }

  @Test
  void testGetClients() {
    // Arrange
    StatefulProxyState statefulProxyState = new StatefulProxyState();

    // Act and Assert
    assertNull(statefulProxyState
        .getClients(new TransactionServer(new SipProvider("File"), "Method", mock(TransactionServerListener.class))));
  }

  @Test
  void testGetFinalResponse() {
    // Arrange
    StatefulProxyState statefulProxyState = new StatefulProxyState();

    // Act and Assert
    assertNull(statefulProxyState.getFinalResponse(
        new TransactionServer(new SipProvider("File"), "Method", mock(TransactionServerListener.class))));
  }

  @Test
  void testNumOfServers() {
    // Arrange, Act and Assert
    assertEquals(0, (new StatefulProxyState()).numOfServers());
  }

  @Test
  void testNumOfClients() {
    // Arrange, Act and Assert
    assertEquals(0, (new StatefulProxyState()).numOfClients());
  }
}

