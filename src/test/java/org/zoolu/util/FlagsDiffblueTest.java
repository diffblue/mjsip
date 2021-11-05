package org.zoolu.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

class FlagsDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    Flags actualFlags = new Flags(new String[]{"Args"});

    // Assert
    assertEquals(1, actualFlags.size());
    ArrayList arrayList = actualFlags.params;
    assertEquals(actualFlags.options, arrayList);
    assertEquals(arrayList, actualFlags.options);
    assertFalse(actualFlags.not_option);
  }

  @Test
  void testOptionConstructor() {
    // Arrange and Act
    Flags.Option actualOption = new Flags.Option("Tag", "Param", "The characteristics of someone or something");

    // Assert
    assertEquals("The characteristics of someone or something", actualOption.getDescription());
    assertEquals("Param", actualOption.getParam());
    assertEquals("Tag", actualOption.getTag());
    assertEquals("Tag Param : The characteristics of someone or something", actualOption.toString());
  }

  @Test
  void testOptionToString() {
    // Arrange, Act and Assert
    assertEquals("Tag ParamSepThe characteristics of someone or something",
        (new Flags.Option("Tag", "Param", "The characteristics of someone or something")).toString(3, "Sep"));
    assertEquals("ParamSepThe characteristics of someone or something",
        (new Flags.Option(Flags.OPTIONAL_PARAM, "Param", "The characteristics of someone or something")).toString(3,
            "Sep"));
    assertEquals("ParamSepThe characteristics of someone or something",
        (new Flags.Option(null, "Param", "The characteristics of someone or something")).toString(3, "Sep"));
    assertEquals("TagSepThe characteristics of someone or something",
        (new Flags.Option("Tag", null, "The characteristics of someone or something")).toString(3, "Sep"));
    assertEquals("Tag ParamSep", (new Flags.Option("Tag", "Param", null)).toString(3, "Sep"));
    assertEquals("Tag ParamSepThe characteristics of someone or something",
        (new Flags.Option("Tag", "Param", "The characteristics of someone or something")).toString(0, "Sep"));
    assertEquals("Tag ParamThe characteristics of someone or something",
        (new Flags.Option("Tag", "Param", "The characteristics of someone or something")).toString(3, null));
    assertEquals("   SepThe characteristics of someone or something",
        (new Flags.Option(Flags.OPTIONAL_PARAM, null, "The characteristics of someone or something")).toString(3,
            "Sep"));
  }

  @Test
  void testSize() {
    // Arrange, Act and Assert
    assertEquals(3, (new Flags(new String[]{"foo", "foo", "foo"})).size());
  }

  @Test
  void testToString() {
    // Arrange, Act and Assert
    assertEquals("", (new Flags(new String[]{"foo", "foo", "foo"})).toString());
  }

  @Test
  void testToUsageString() {
    // Arrange, Act and Assert
    assertEquals("Usage: java Program", (new Flags(new String[]{"foo", "foo", "foo"})).toUsageString("Program"));
  }
}

