package org.mjsip.sip.address;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class TelURIDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange, Act and Assert
    assertEquals("tel:Uri", (new TelURI("Uri")).toString());
    assertEquals("tel:", (new TelURI("tel:")).toString());
  }

  @Test
  void testGetNumber() {
    // Arrange, Act and Assert
    assertEquals("Uri", (new TelURI("Uri")).getNumber());
  }

  @Test
  void testGetNumber2() {
    // Arrange
    TelURI telURI = new TelURI("Uri");
    telURI.addParameter("Name");

    // Act and Assert
    assertEquals("Uri", telURI.getNumber());
  }

  @Test
  void testGetIsdnSubaddress() {
    // Arrange, Act and Assert
    assertNull((new TelURI("Uri")).getIsdnSubaddress());
  }

  @Test
  void testGetIsdnSubaddress2() {
    // Arrange
    TelURI telURI = new TelURI("Uri");
    telURI.addParameter(TelURI.PARAM_ISUB);
    telURI.addParameter(TelURI.PARAM_ISUB);

    // Act and Assert
    assertNull(telURI.getIsdnSubaddress());
  }

  @Test
  void testGetIsdnSubaddress3() {
    // Arrange
    TelURI telURI = new TelURI("Uri");
    telURI.addIsdnSubaddress("42 Main St");
    telURI.addParameter(TelURI.PARAM_ISUB);

    // Act and Assert
    assertEquals("42", telURI.getIsdnSubaddress());
  }

  @Test
  void testGetIsdnSubaddress4() {
    // Arrange
    TelURI telURI = new TelURI("Uri");
    telURI.addExtension(TelURI.PARAM_ISUB);
    telURI.addParameter(TelURI.PARAM_EXT);

    // Act and Assert
    assertNull(telURI.getIsdnSubaddress());
  }

  @Test
  void testGetIsdnSubaddress5() {
    // Arrange
    TelURI telURI = new TelURI("Uri");
    telURI.addParameter(TelURI.PARAM_ISUB, "42");

    // Act and Assert
    assertEquals("42", telURI.getIsdnSubaddress());
  }

  @Test
  void testGetIsdnSubaddress6() {
    // Arrange
    TelURI telURI = new TelURI("Uri");
    telURI.addParameter("");
    telURI.addParameter("");

    // Act and Assert
    assertNull(telURI.getIsdnSubaddress());
  }

  @Test
  void testGetIsdnSubaddress7() {
    // Arrange
    TelURI telURI = new TelURI("Uri");
    telURI.addIsdnSubaddress("");
    telURI.addParameter(TelURI.PARAM_ISUB);

    // Act and Assert
    assertEquals(TelURI.PARAM_ISUB, telURI.getIsdnSubaddress());
  }

  @Test
  void testGetIsdnSubaddress8() {
    // Arrange
    TelURI telURI = new TelURI("Uri");
    telURI.addIsdnSubaddress("");

    // Act and Assert
    assertEquals("", telURI.getIsdnSubaddress());
  }

  @Test
  void testHasIsdnSubaddress() {
    // Arrange, Act and Assert
    assertFalse((new TelURI("Uri")).hasIsdnSubaddress());
  }

  @Test
  void testHasIsdnSubaddress2() {
    // Arrange
    TelURI telURI = new TelURI("Uri");
    telURI.addParameter(TelURI.PARAM_ISUB);

    // Act and Assert
    assertTrue(telURI.hasIsdnSubaddress());
  }

  @Test
  void testHasIsdnSubaddress3() {
    // Arrange
    TelURI telURI = new TelURI("Uri");
    telURI.addExtension(TelURI.PARAM_ISUB);

    // Act and Assert
    assertFalse(telURI.hasIsdnSubaddress());
  }

  @Test
  void testHasIsdnSubaddress4() {
    // Arrange
    TelURI telURI = new TelURI("Uri");
    telURI.addExtension(TelURI.PARAM_ISUB);
    telURI.addParameter(TelURI.PARAM_ISUB);

    // Act and Assert
    assertTrue(telURI.hasIsdnSubaddress());
  }

  @Test
  void testHasIsdnSubaddress5() {
    // Arrange
    TelURI telURI = new TelURI("Uri");
    telURI.addParameter("", "42");

    // Act and Assert
    assertFalse(telURI.hasIsdnSubaddress());
  }

  @Test
  void testHasIsdnSubaddress6() {
    // Arrange
    TelURI telURI = new TelURI("Uri");
    telURI.addParameter("", "");

    // Act and Assert
    assertFalse(telURI.hasIsdnSubaddress());
  }

  @Test
  void testAddIsdnSubaddress() {
    // Arrange
    TelURI telURI = new TelURI("Uri");

    // Act
    telURI.addIsdnSubaddress("42 Main St");

    // Assert
    assertEquals("tel:Uri;isub=42 Main St", telURI.toString());
  }

  @Test
  void testGetExtension() {
    // Arrange, Act and Assert
    assertNull((new TelURI("Uri")).getExtension());
  }

  @Test
  void testGetExtension2() {
    // Arrange
    TelURI telURI = new TelURI("Uri");
    telURI.addParameter(TelURI.PARAM_EXT);
    telURI.addParameter(TelURI.PARAM_EXT);

    // Act and Assert
    assertNull(telURI.getExtension());
  }

  @Test
  void testGetExtension3() {
    // Arrange
    TelURI telURI = new TelURI("Uri");
    telURI.addExtension(TelURI.PARAM_EXT);
    telURI.addParameter(TelURI.PARAM_EXT);

    // Act and Assert
    assertEquals(TelURI.PARAM_EXT, telURI.getExtension());
  }

  @Test
  void testGetExtension4() {
    // Arrange
    TelURI telURI = new TelURI("Uri");
    telURI.addParameter(TelURI.PARAM_EXT, "42");
    telURI.addParameter(TelURI.PARAM_EXT);

    // Act and Assert
    assertEquals("42", telURI.getExtension());
  }

  @Test
  void testGetExtension5() {
    // Arrange
    TelURI telURI = new TelURI("Uri");
    telURI.addLr();
    telURI.addParameter(GenericURI.PARAM_LR);

    // Act and Assert
    assertNull(telURI.getExtension());
  }

  @Test
  void testGetExtension6() {
    // Arrange
    TelURI telURI = new TelURI("Uri");
    telURI.addExtension(TelURI.PARAM_EXT);

    // Act and Assert
    assertEquals(TelURI.PARAM_EXT, telURI.getExtension());
  }

  @Test
  void testGetExtension7() {
    // Arrange
    TelURI telURI = new TelURI("Uri");
    telURI.addParameter("");
    telURI.addParameter("");

    // Act and Assert
    assertNull(telURI.getExtension());
  }

  @Test
  void testGetExtension8() {
    // Arrange
    TelURI telURI = new TelURI("Uri");
    telURI.addExtension("");
    telURI.addParameter(TelURI.PARAM_EXT);

    // Act and Assert
    assertEquals(TelURI.PARAM_EXT, telURI.getExtension());
  }

  @Test
  void testGetExtension9() {
    // Arrange
    TelURI telURI = new TelURI("Uri");
    telURI.addExtension("");

    // Act and Assert
    assertEquals("", telURI.getExtension());
  }

  @Test
  void testHasExtension() {
    // Arrange, Act and Assert
    assertFalse((new TelURI("Uri")).hasExtension());
  }

  @Test
  void testHasExtension2() {
    // Arrange
    TelURI telURI = new TelURI("Uri");
    telURI.addParameter(TelURI.PARAM_EXT);

    // Act and Assert
    assertTrue(telURI.hasExtension());
  }

  @Test
  void testHasExtension3() {
    // Arrange
    TelURI telURI = new TelURI("Uri");
    telURI.addExtension(TelURI.PARAM_EXT);

    // Act and Assert
    assertTrue(telURI.hasExtension());
  }

  @Test
  void testHasExtension4() {
    // Arrange
    TelURI telURI = new TelURI("Uri");
    telURI.addLr();

    // Act and Assert
    assertFalse(telURI.hasExtension());
  }

  @Test
  void testHasExtension5() {
    // Arrange
    TelURI telURI = new TelURI("Uri");
    telURI.addIsdnSubaddress("42 Main St");

    // Act and Assert
    assertFalse(telURI.hasExtension());
  }

  @Test
  void testHasExtension6() {
    // Arrange
    TelURI telURI = new TelURI("Uri");
    telURI.addLr();
    telURI.addParameter(TelURI.PARAM_EXT);

    // Act and Assert
    assertTrue(telURI.hasExtension());
  }

  @Test
  void testHasExtension7() {
    // Arrange
    TelURI telURI = new TelURI("Uri");
    telURI.addParameter("", "42");

    // Act and Assert
    assertFalse(telURI.hasExtension());
  }

  @Test
  void testHasExtension8() {
    // Arrange
    TelURI telURI = new TelURI("Uri");
    telURI.addParameter("", "");

    // Act and Assert
    assertFalse(telURI.hasExtension());
  }

  @Test
  void testAddExtension() {
    // Arrange
    TelURI telURI = new TelURI("Uri");

    // Act
    telURI.addExtension("Extension");

    // Assert
    assertEquals("tel:Uri;extension=Extension", telURI.toString());
  }

  @Test
  void testGetPhoneContext() {
    // Arrange, Act and Assert
    assertNull((new TelURI("Uri")).getPhoneContext());
  }

  @Test
  void testGetPhoneContext2() {
    // Arrange
    TelURI telURI = new TelURI("Uri");
    telURI.addParameter(TelURI.PARAM_CONTEXT);
    telURI.addParameter(TelURI.PARAM_CONTEXT);

    // Act and Assert
    assertNull(telURI.getPhoneContext());
  }

  @Test
  void testGetPhoneContext3() {
    // Arrange
    TelURI telURI = new TelURI("Uri");
    telURI.addParameter(TelURI.PARAM_CONTEXT, "42");
    telURI.addParameter(TelURI.PARAM_CONTEXT);

    // Act and Assert
    assertEquals("42", telURI.getPhoneContext());
  }

  @Test
  void testGetPhoneContext4() {
    // Arrange
    TelURI telURI = new TelURI("Uri");
    telURI.addExtension(TelURI.PARAM_CONTEXT);
    telURI.addParameter(TelURI.PARAM_EXT);

    // Act and Assert
    assertNull(telURI.getPhoneContext());
  }

  @Test
  void testGetPhoneContext5() {
    // Arrange
    TelURI telURI = new TelURI("Uri");
    telURI.addParameter(TelURI.PARAM_CONTEXT, "42");

    // Act and Assert
    assertEquals("42", telURI.getPhoneContext());
  }

  @Test
  void testGetPhoneContext6() {
    // Arrange
    TelURI telURI = new TelURI("Uri");
    telURI.addParameter("");
    telURI.addParameter("");

    // Act and Assert
    assertNull(telURI.getPhoneContext());
  }

  @Test
  void testGetPhoneContext7() {
    // Arrange
    TelURI telURI = new TelURI("Uri");
    telURI.addParameter(TelURI.PARAM_CONTEXT, "");
    telURI.addParameter(TelURI.PARAM_CONTEXT);

    // Act and Assert
    assertEquals(TelURI.PARAM_CONTEXT, telURI.getPhoneContext());
  }

  @Test
  void testGetPhoneContext8() {
    // Arrange
    TelURI telURI = new TelURI("Uri");
    telURI.addParameter(TelURI.PARAM_CONTEXT, "");

    // Act and Assert
    assertEquals("", telURI.getPhoneContext());
  }

  @Test
  void testHasPhoneContext() {
    // Arrange, Act and Assert
    assertFalse((new TelURI("Uri")).hasPhoneContext());
  }

  @Test
  void testHasPhoneContext2() {
    // Arrange
    TelURI telURI = new TelURI("Uri");
    telURI.addParameter(TelURI.PARAM_CONTEXT);

    // Act and Assert
    assertTrue(telURI.hasPhoneContext());
  }

  @Test
  void testHasPhoneContext3() {
    // Arrange
    TelURI telURI = new TelURI("Uri");
    telURI.addExtension(TelURI.PARAM_CONTEXT);

    // Act and Assert
    assertFalse(telURI.hasPhoneContext());
  }

  @Test
  void testHasPhoneContext4() {
    // Arrange
    TelURI telURI = new TelURI("Uri");
    telURI.addExtension(TelURI.PARAM_CONTEXT);
    telURI.addParameter(TelURI.PARAM_CONTEXT);

    // Act and Assert
    assertTrue(telURI.hasPhoneContext());
  }

  @Test
  void testHasPhoneContext5() {
    // Arrange
    TelURI telURI = new TelURI("Uri");
    telURI.addParameter("", "42");

    // Act and Assert
    assertFalse(telURI.hasPhoneContext());
  }

  @Test
  void testHasPhoneContext6() {
    // Arrange
    TelURI telURI = new TelURI("Uri");
    telURI.addParameter("", "");

    // Act and Assert
    assertFalse(telURI.hasPhoneContext());
  }

  @Test
  void testAddPhoneContext() {
    // Arrange
    TelURI telURI = new TelURI("Uri");

    // Act
    telURI.addPhoneContext("Context");

    // Assert
    assertEquals("tel:Uri;phone-context=Context", telURI.toString());
  }
}

