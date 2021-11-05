package org.mjsip.sip.message;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class SipMethodsDiffblueTest {
  @Test
  void testIsAck() {
    // Arrange, Act and Assert
    assertFalse(SipMethods.isAck("Name"));
    assertTrue(SipMethods.isAck(SipMethods.ACK));
  }

  @Test
  void testIsBye() {
    // Arrange, Act and Assert
    assertFalse(SipMethods.isBye("Name"));
    assertTrue(SipMethods.isBye(SipMethods.BYE));
  }

  @Test
  void testIsCancel() {
    // Arrange, Act and Assert
    assertFalse(SipMethods.isCancel("Name"));
    assertTrue(SipMethods.isCancel(SipMethods.CANCEL));
  }

  @Test
  void testIsInfo() {
    // Arrange, Act and Assert
    assertFalse(SipMethods.isInfo("Name"));
    assertTrue(SipMethods.isInfo(SipMethods.INFO));
  }

  @Test
  void testIsInvite() {
    // Arrange, Act and Assert
    assertFalse(SipMethods.isInvite("Name"));
    assertTrue(SipMethods.isInvite(SipMethods.INVITE));
  }

  @Test
  void testIsMessage() {
    // Arrange, Act and Assert
    assertFalse(SipMethods.isMessage("Name"));
    assertTrue(SipMethods.isMessage(SipMethods.MESSAGE));
  }

  @Test
  void testIsNotify() {
    // Arrange, Act and Assert
    assertFalse(SipMethods.isNotify("Name"));
    assertTrue(SipMethods.isNotify(SipMethods.NOTIFY));
  }

  @Test
  void testIsOptions() {
    // Arrange, Act and Assert
    assertFalse(SipMethods.isOptions("Name"));
    assertTrue(SipMethods.isOptions(SipMethods.OPTIONS));
  }

  @Test
  void testIsPrack() {
    // Arrange, Act and Assert
    assertFalse(SipMethods.isPrack("Name"));
    assertTrue(SipMethods.isPrack(SipMethods.PRACK));
  }

  @Test
  void testIsPublish() {
    // Arrange, Act and Assert
    assertFalse(SipMethods.isPublish("Name"));
    assertTrue(SipMethods.isPublish(SipMethods.PUBLISH));
  }

  @Test
  void testIsRefer() {
    // Arrange, Act and Assert
    assertFalse(SipMethods.isRefer("Name"));
    assertTrue(SipMethods.isRefer(SipMethods.REFER));
  }

  @Test
  void testIsRegister() {
    // Arrange, Act and Assert
    assertFalse(SipMethods.isRegister("Name"));
    assertTrue(SipMethods.isRegister(SipMethods.REGISTER));
  }

  @Test
  void testIsSubscribe() {
    // Arrange, Act and Assert
    assertFalse(SipMethods.isSubscribe("Name"));
    assertTrue(SipMethods.isSubscribe(SipMethods.SUBSCRIBE));
  }

  @Test
  void testIsUpdate() {
    // Arrange, Act and Assert
    assertFalse(SipMethods.isUpdate("Name"));
    assertTrue(SipMethods.isUpdate(SipMethods.UPDATE));
  }

  @Test
  void testGetAllSipMethods() {
    // Arrange, Act and Assert
    assertEquals(14, SipMethods.getAllSipMethods().length);
  }

  @Test
  void testIsSipMethod() {
    // Arrange, Act and Assert
    assertFalse(SipMethods.isSipMethod("Name"));
    assertTrue(SipMethods.isSipMethod(SipMethods.ACK));
  }

  @Test
  void testIsCreateDialogMethod() {
    // Arrange, Act and Assert
    assertFalse(SipMethods.isCreateDialogMethod("Name"));
    assertTrue(SipMethods.isCreateDialogMethod(SipMethods.INVITE));
  }

  @Test
  void testSame() {
    // Arrange, Act and Assert
    assertFalse(SipMethods.same("S1", "S2"));
    assertTrue(SipMethods.same("foo", "foo"));
  }
}

