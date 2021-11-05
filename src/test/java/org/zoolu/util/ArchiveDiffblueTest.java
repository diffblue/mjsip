package org.zoolu.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import javax.sound.sampled.UnsupportedAudioFileException;
import org.junit.jupiter.api.Test;

class ArchiveDiffblueTest {
  @Test
  void testGetImage() throws FileNotFoundException {
    // Arrange, Act and Assert
    assertThrows(FileNotFoundException.class, () -> Archive.getImage("File name"));
    assertThrows(IOException.class,
        () -> Archive.getImage(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL()));
  }

  @Test
  void testGetImageIcon() throws FileNotFoundException {
    // Arrange, Act and Assert
    assertThrows(FileNotFoundException.class, () -> Archive.getImageIcon("File name"));
    assertThrows(IOException.class,
        () -> Archive.getImageIcon(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL()));
  }

  @Test
  void testGetInputStream() throws IOException {
    // Arrange, Act and Assert
    assertThrows(IOException.class,
        () -> Archive.getInputStream(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL()));
  }

  @Test
  void testConstructor() {
    // Arrange and Act
    new Archive();

    // Assert
    assertEquals(System.getProperty("user.dir"), Archive.BASE_PATH);
  }
}

