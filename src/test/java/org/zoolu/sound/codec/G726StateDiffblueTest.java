package org.zoolu.sound.codec;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class G726StateDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    G726State actualG726State = new G726State();

    // Assert
    assertEquals(2, actualG726State.a.length);
    assertEquals(544, actualG726State.yu);
    assertEquals(34816, actualG726State.yl);
    assertEquals(0, actualG726State.td);
    assertEquals(2, actualG726State.sr.length);
    assertEquals(2, actualG726State.pk.length);
    assertEquals(6, actualG726State.dq.length);
    assertEquals(0, actualG726State.dms);
    assertEquals(0, actualG726State.dml);
    assertEquals(6, actualG726State.b.length);
    assertEquals(0, actualG726State.ap);
  }

  @Test
  void testPredictor_pole() {
    // Arrange, Act and Assert
    assertEquals(0, (new G726State()).predictor_pole());
  }

  @Test
  void testPredictor_zero() {
    // Arrange, Act and Assert
    assertEquals(0, (new G726State()).predictor_zero());
  }

  @Test
  void testStep_size() {
    // Arrange, Act and Assert
    assertEquals(544, (new G726State()).step_size());
  }
}

