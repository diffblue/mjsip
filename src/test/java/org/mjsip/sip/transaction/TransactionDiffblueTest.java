package org.mjsip.sip.transaction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

class TransactionDiffblueTest {
  @Test
  void testGetStatus() {
    // Arrange, Act and Assert
    assertEquals("T_Waiting", Transaction.getStatus(1));
    assertEquals("T_Idle", Transaction.getStatus(0));
    assertEquals("T_Trying", Transaction.getStatus(2));
    assertEquals("T_Proceeding", Transaction.getStatus(3));
    assertEquals("T_Completed", Transaction.getStatus(4));
    assertEquals("T_Confirmed", Transaction.getStatus(5));
    assertNull(Transaction.getStatus(6));
    assertEquals("T_Terminated", Transaction.getStatus(7));
  }
}

