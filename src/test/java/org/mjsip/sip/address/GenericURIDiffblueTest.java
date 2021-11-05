package org.mjsip.sip.address;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Objects;
import java.util.Vector;
import org.junit.jupiter.api.Test;

class GenericURIDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange, Act and Assert
    assertEquals("Uri", (new GenericURI("Uri")).toString());
    assertEquals("Uri", (new GenericURI(new GenericURI("Uri"))).toString());
  }

  @Test
  void testConstructor2() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("Name");

    // Act and Assert
    assertEquals("Uri;Name", (new GenericURI(genericURI)).toString());
  }

  @Test
  void testClone() {
    // Arrange, Act and Assert
    assertEquals("Uri", (new GenericURI("Uri")).clone().toString());
  }

  @Test
  void testEquals() {
    // Arrange, Act and Assert
    assertThrows(NullPointerException.class, () -> (new GenericURI("Uri")).equals(null));
    assertFalse((new GenericURI("Uri")).equals("Different type to GenericURI"));
  }

  @Test
  void testEquals2() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");

    // Act and Assert
    assertTrue(genericURI.equals(genericURI));
    int expectedHashCodeResult = genericURI.hashCode();
    assertEquals(expectedHashCodeResult, genericURI.hashCode());
  }

  @Test
  void testEquals3() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    GenericURI genericURI1 = new GenericURI("Uri");

    // Act and Assert
    assertTrue(genericURI.equals(genericURI1));
    int notExpectedHashCodeResult = genericURI.hashCode();
    assertFalse(Objects.equals(notExpectedHashCodeResult, genericURI1.hashCode()));
  }

  @Test
  void testEquals4() {
    // Arrange
    GenericURI genericURI = new GenericURI("");

    // Act and Assert
    assertFalse(genericURI.equals(new GenericURI("Uri")));
  }

  @Test
  void testGetSpecificPart() {
    // Arrange, Act and Assert
    assertEquals("Uri", (new GenericURI("Uri")).getSpecificPart());
  }

  @Test
  void testHasLr() {
    // Arrange, Act and Assert
    assertFalse((new GenericURI("Uri")).hasLr());
  }

  @Test
  void testHasLr2() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter(GenericURI.PARAM_LR);

    // Act and Assert
    assertTrue(genericURI.hasLr());
  }

  @Test
  void testHasLr3() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter(GenericURI.PARAM_LR, "42");

    // Act and Assert
    assertTrue(genericURI.hasLr());
  }

  @Test
  void testHasLr4() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("Name");

    // Act and Assert
    assertFalse(genericURI.hasLr());
  }

  @Test
  void testHasLr5() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("Name", "42");

    // Act and Assert
    assertFalse(genericURI.hasLr());
  }

  @Test
  void testHasLr6() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("", "42");

    // Act and Assert
    assertFalse(genericURI.hasLr());
  }

  @Test
  void testHasLr7() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("Name");
    genericURI.addParameter(GenericURI.PARAM_LR);

    // Act and Assert
    assertTrue(genericURI.hasLr());
  }

  @Test
  void testHasLr8() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("", "");

    // Act and Assert
    assertFalse(genericURI.hasLr());
  }

  @Test
  void testAddLr() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");

    // Act
    genericURI.addLr();

    // Assert
    assertEquals("Uri;lr", genericURI.toString());
  }

  @Test
  void testGetParameter() {
    // Arrange, Act and Assert
    assertNull((new GenericURI("Uri")).getParameter("Name"));
  }

  @Test
  void testGetParameter2() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("Name");
    genericURI.addParameter("Name");

    // Act and Assert
    assertNull(genericURI.getParameter("Name"));
  }

  @Test
  void testGetParameter3() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("Name", "42");
    genericURI.addParameter("Name");

    // Act and Assert
    assertEquals("42", genericURI.getParameter("Name"));
  }

  @Test
  void testGetParameterNames() {
    // Arrange, Act and Assert
    assertTrue((new GenericURI("Uri")).getParameterNames().isEmpty());
  }

  @Test
  void testGetParameterNames2() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("Name");

    // Act
    Vector actualParameterNames = genericURI.getParameterNames();

    // Assert
    assertEquals(1, actualParameterNames.size());
    assertEquals("Name", actualParameterNames.get(0));
  }

  @Test
  void testGetParameterNames3() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("Name", "42");

    // Act
    Vector actualParameterNames = genericURI.getParameterNames();

    // Assert
    assertEquals(1, actualParameterNames.size());
    assertEquals("Name", actualParameterNames.get(0));
  }

  @Test
  void testGetParameterNames4() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("Name");
    genericURI.addParameter("Name");

    // Act
    Vector actualParameterNames = genericURI.getParameterNames();

    // Assert
    assertEquals(2, actualParameterNames.size());
    assertEquals("Name", actualParameterNames.get(0));
    assertEquals("Name", actualParameterNames.get(1));
  }

  @Test
  void testGetParameterNames5() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("", "42");

    // Act
    Vector actualParameterNames = genericURI.getParameterNames();

    // Assert
    assertEquals(1, actualParameterNames.size());
    assertEquals("42", actualParameterNames.get(0));
  }

  @Test
  void testGetParameterNames6() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("", "");

    // Act and Assert
    assertTrue(genericURI.getParameterNames().isEmpty());
  }

  @Test
  void testHasParameter() {
    // Arrange, Act and Assert
    assertFalse((new GenericURI("Uri")).hasParameter("Name"));
  }

  @Test
  void testHasParameter2() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("Name");

    // Act and Assert
    assertTrue(genericURI.hasParameter("Name"));
  }

  @Test
  void testHasParameter3() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addLr();

    // Act and Assert
    assertFalse(genericURI.hasParameter("Name"));
  }

  @Test
  void testHasParameter4() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("Name", "42");

    // Act and Assert
    assertTrue(genericURI.hasParameter("Name"));
  }

  @Test
  void testHasParameter5() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addLr();
    genericURI.addParameter("Name");

    // Act and Assert
    assertTrue(genericURI.hasParameter("Name"));
  }

  @Test
  void testHasParameter6() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter(GenericURI.PARAM_LR, "42");
    genericURI.addLr();

    // Act and Assert
    assertFalse(genericURI.hasParameter("Name"));
  }

  @Test
  void testHasParameter7() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("", "42");

    // Act and Assert
    assertFalse(genericURI.hasParameter("Name"));
  }

  @Test
  void testHasParameter8() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("", "");

    // Act and Assert
    assertFalse(genericURI.hasParameter("Name"));
  }

  @Test
  void testHasParameters() {
    // Arrange, Act and Assert
    assertFalse((new GenericURI("Uri")).hasParameters());
    assertFalse((new GenericURI((String) null)).hasParameters());
  }

  @Test
  void testHasParameters2() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("Name");

    // Act and Assert
    assertTrue(genericURI.hasParameters());
  }

  @Test
  void testAddParameter() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");

    // Act
    genericURI.addParameter("Name");

    // Assert
    assertEquals("Uri;Name", genericURI.toString());
  }

  @Test
  void testAddParameter2() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");

    // Act
    genericURI.addParameter("Name", "42");

    // Assert
    assertEquals("Uri;Name=42", genericURI.toString());
  }

  @Test
  void testAddParameter3() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");

    // Act
    genericURI.addParameter("Name", null);

    // Assert
    assertEquals("Uri;Name", genericURI.toString());
  }

  @Test
  void testRemoveParameters() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");

    // Act
    genericURI.removeParameters();

    // Assert that nothing has changed
    assertEquals("Uri", genericURI.toString());
  }

  @Test
  void testRemoveParameters2() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("Name");

    // Act
    genericURI.removeParameters();

    // Assert
    assertEquals("Uri", genericURI.toString());
  }

  @Test
  void testRemoveParameter() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");

    // Act
    genericURI.removeParameter("Name");

    // Assert that nothing has changed
    assertEquals("Uri", genericURI.toString());
  }

  @Test
  void testRemoveParameter2() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("Name");

    // Act
    genericURI.removeParameter("Name");

    // Assert
    assertEquals("Uri", genericURI.toString());
  }

  @Test
  void testRemoveParameter3() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("Name", "42");

    // Act
    genericURI.removeParameter("Name");

    // Assert
    assertEquals("Uri", genericURI.toString());
  }

  @Test
  void testRemoveParameter4() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addParameter("Name");
    genericURI.addParameter("Name");

    // Act
    genericURI.removeParameter("Name");

    // Assert
    assertEquals("Uri;Name", genericURI.toString());
  }

  @Test
  void testRemoveParameter5() {
    // Arrange
    GenericURI genericURI = new GenericURI("Uri");
    genericURI.addLr();
    genericURI.addParameter("Name");

    // Act
    genericURI.removeParameter("Name");

    // Assert
    assertEquals("Uri;lr", genericURI.toString());
  }
}

