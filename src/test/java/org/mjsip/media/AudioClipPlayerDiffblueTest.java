package org.mjsip.media;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.nio.file.Paths;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import org.junit.jupiter.api.Test;

class AudioClipPlayerDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    AudioClipPlayer actualAudioClipPlayer = new AudioClipPlayer("foo.txt", mock(AudioClipPlayerListener.class));
    AudioClipPlayer actualSetLoopCountResult = actualAudioClipPlayer.setLoopCount(3);

    // Assert
    assertEquals(0.0f, actualAudioClipPlayer.getVolumeGain());
    assertEquals(3, actualAudioClipPlayer.loop_count);
    assertNull(actualAudioClipPlayer.listener);
    assertNull(actualAudioClipPlayer.clip);
    assertSame(actualAudioClipPlayer, actualSetLoopCountResult);
  }

  @Test
  void testConstructor2() {
    // Arrange and Act
    AudioClipPlayer actualAudioClipPlayer = new AudioClipPlayer(
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), mock(AudioClipPlayerListener.class));

    // Assert
    assertEquals(1, actualAudioClipPlayer.loop_count);
    assertNull(actualAudioClipPlayer.listener);
    assertNull(actualAudioClipPlayer.clip);
  }

  @Test
  void testConstructor3() {
    // Arrange and Act
    AudioClipPlayer actualAudioClipPlayer = new AudioClipPlayer("foo.txt", mock(AudioClipPlayerListener.class));

    // Assert
    assertEquals(1, actualAudioClipPlayer.loop_count);
    assertNull(actualAudioClipPlayer.listener);
    assertNull(actualAudioClipPlayer.clip);
  }

  @Test
  void testConstructor4() throws MalformedURLException {
    // Arrange and Act
    AudioClipPlayer actualAudioClipPlayer = new AudioClipPlayer(
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL(),
        mock(AudioClipPlayerListener.class));

    // Assert
    assertEquals(1, actualAudioClipPlayer.loop_count);
    assertNull(actualAudioClipPlayer.listener);
    assertNull(actualAudioClipPlayer.clip);
  }

  @Test
  void testConstructor5() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayInputStream stream = new ByteArrayInputStream("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    // Act
    AudioClipPlayer actualAudioClipPlayer = new AudioClipPlayer(
        new AudioInputStream(stream, new AudioFormat(10.0f, 3, 1, true, true), 3L),
        mock(AudioClipPlayerListener.class));

    // Assert
    assertEquals(1, actualAudioClipPlayer.loop_count);
    assertNull(actualAudioClipPlayer.clip);
  }

  @Test
  void testConstructor6() {
    // Arrange and Act
    AudioClipPlayer actualAudioClipPlayer = new AudioClipPlayer((AudioInputStream) null,
        mock(AudioClipPlayerListener.class));

    // Assert
    assertEquals(1, actualAudioClipPlayer.loop_count);
    assertNull(actualAudioClipPlayer.clip);
  }

  @Test
  void testSetLoop() {
    // Arrange
    AudioClipPlayer audioClipPlayer = new AudioClipPlayer("foo.txt", mock(AudioClipPlayerListener.class));

    // Act
    AudioClipPlayer actualSetLoopResult = audioClipPlayer.setLoop();

    // Assert
    assertSame(audioClipPlayer, actualSetLoopResult);
    assertEquals(0, actualSetLoopResult.loop_count);
  }

  @Test
  void testSetLoop2() throws MalformedURLException {
    // Arrange
    AudioClipPlayer audioClipPlayer = new AudioClipPlayer(
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL(),
        mock(AudioClipPlayerListener.class));

    // Act
    AudioClipPlayer actualSetLoopResult = audioClipPlayer.setLoop();

    // Assert
    assertSame(audioClipPlayer, actualSetLoopResult);
    assertEquals(0, actualSetLoopResult.loop_count);
  }

  @Test
  void testSetLoop3() {
    // Arrange
    AudioClipPlayer audioClipPlayer = new AudioClipPlayer(
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), mock(AudioClipPlayerListener.class));

    // Act
    AudioClipPlayer actualSetLoopResult = audioClipPlayer.setLoop();

    // Assert
    assertSame(audioClipPlayer, actualSetLoopResult);
    assertEquals(0, actualSetLoopResult.loop_count);
  }

  @Test
  void testSetLoop4() {
    // Arrange
    AudioClipPlayer audioClipPlayer = new AudioClipPlayer("foo.txt", mock(AudioClipPlayerListener.class));
    audioClipPlayer.setLoopCount(3);

    // Act
    AudioClipPlayer actualSetLoopResult = audioClipPlayer.setLoop();

    // Assert
    assertSame(audioClipPlayer, actualSetLoopResult);
    assertEquals(0, actualSetLoopResult.loop_count);
  }

  @Test
  void testSetLoop5() {
    // Arrange
    AudioClipPlayer audioClipPlayer = new AudioClipPlayer("foo.txt", mock(AudioClipPlayerListener.class));
    audioClipPlayer.goTo(0L);

    // Act
    AudioClipPlayer actualSetLoopResult = audioClipPlayer.setLoop();

    // Assert
    assertSame(audioClipPlayer, actualSetLoopResult);
    assertEquals(0, actualSetLoopResult.loop_count);
  }

  @Test
  void testSetLoop6() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayInputStream stream = new ByteArrayInputStream("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));
    AudioClipPlayer audioClipPlayer = new AudioClipPlayer(
        new AudioInputStream(stream, new AudioFormat(10.0f, 3, 1, true, true), 3L),
        mock(AudioClipPlayerListener.class));

    // Act
    AudioClipPlayer actualSetLoopResult = audioClipPlayer.setLoop();

    // Assert
    assertSame(audioClipPlayer, actualSetLoopResult);
    assertEquals(0, actualSetLoopResult.loop_count);
  }

  @Test
  void testSetLoop7() {
    // Arrange
    AudioClipPlayer audioClipPlayer = new AudioClipPlayer((AudioInputStream) null, mock(AudioClipPlayerListener.class));

    // Act
    AudioClipPlayer actualSetLoopResult = audioClipPlayer.setLoop();

    // Assert
    assertSame(audioClipPlayer, actualSetLoopResult);
    assertEquals(0, actualSetLoopResult.loop_count);
  }

  @Test
  void testSetVolumeGain() {
    // Arrange
    AudioClipPlayer audioClipPlayer = new AudioClipPlayer("foo.txt", mock(AudioClipPlayerListener.class));

    // Act and Assert
    assertSame(audioClipPlayer, audioClipPlayer.setVolumeGain(10.0f));
  }

  @Test
  void testSetVolumeGain2() throws MalformedURLException {
    // Arrange
    AudioClipPlayer audioClipPlayer = new AudioClipPlayer(
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL(),
        mock(AudioClipPlayerListener.class));

    // Act and Assert
    assertSame(audioClipPlayer, audioClipPlayer.setVolumeGain(10.0f));
  }

  @Test
  void testSetVolumeGain3() {
    // Arrange
    AudioClipPlayer audioClipPlayer = new AudioClipPlayer(
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), mock(AudioClipPlayerListener.class));

    // Act and Assert
    assertSame(audioClipPlayer, audioClipPlayer.setVolumeGain(10.0f));
  }

  @Test
  void testSetVolumeGain4() {
    // Arrange
    AudioClipPlayer audioClipPlayer = new AudioClipPlayer("foo.txt", mock(AudioClipPlayerListener.class));
    audioClipPlayer.setLoopCount(3);

    // Act and Assert
    assertSame(audioClipPlayer, audioClipPlayer.setVolumeGain(10.0f));
  }

  @Test
  void testSetVolumeGain5() {
    // Arrange
    AudioClipPlayer audioClipPlayer = new AudioClipPlayer("foo.txt", mock(AudioClipPlayerListener.class));
    audioClipPlayer.goTo(0L);

    // Act and Assert
    assertSame(audioClipPlayer, audioClipPlayer.setVolumeGain(10.0f));
  }

  @Test
  void testSetVolumeGain6() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayInputStream stream = new ByteArrayInputStream("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));
    AudioClipPlayer audioClipPlayer = new AudioClipPlayer(
        new AudioInputStream(stream, new AudioFormat(10.0f, 3, 1, true, true), 3L),
        mock(AudioClipPlayerListener.class));

    // Act and Assert
    assertSame(audioClipPlayer, audioClipPlayer.setVolumeGain(10.0f));
  }

  @Test
  void testSetVolumeGain7() {
    // Arrange
    AudioClipPlayer audioClipPlayer = new AudioClipPlayer((AudioInputStream) null, mock(AudioClipPlayerListener.class));

    // Act and Assert
    assertSame(audioClipPlayer, audioClipPlayer.setVolumeGain(10.0f));
  }

  @Test
  void testGetVolumeGain2() {
    // Arrange
    AudioClipPlayer audioClipPlayer = new AudioClipPlayer("foo.txt", mock(AudioClipPlayerListener.class));
    audioClipPlayer.setLoopCount(3);

    // Act and Assert
    assertEquals(0.0f, audioClipPlayer.getVolumeGain());
  }

  @Test
  void testGetVolumeGain3() {
    // Arrange
    AudioClipPlayer audioClipPlayer = new AudioClipPlayer("foo.txt", mock(AudioClipPlayerListener.class));
    audioClipPlayer.goTo(0L);

    // Act and Assert
    assertEquals(0.0f, audioClipPlayer.getVolumeGain());
  }

  @Test
  void testGetVolumeGain4() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayInputStream stream = new ByteArrayInputStream("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));

    // Act and Assert
    assertEquals(0.0f, (new AudioClipPlayer(new AudioInputStream(stream, new AudioFormat(10.0f, 3, 1, true, true), 3L),
        mock(AudioClipPlayerListener.class))).getVolumeGain());
  }

  @Test
  void testPlay() {
    // Arrange
    AudioClipPlayer audioClipPlayer = new AudioClipPlayer("foo.txt", mock(AudioClipPlayerListener.class));

    // Act and Assert
    assertSame(audioClipPlayer, audioClipPlayer.play());
  }

  @Test
  void testPlay2() {
    // Arrange
    AudioClipPlayer audioClipPlayer = new AudioClipPlayer((String) null, mock(AudioClipPlayerListener.class));

    // Act and Assert
    assertSame(audioClipPlayer, audioClipPlayer.play());
  }

  @Test
  void testPlay3() {
    // Arrange
    AudioClipPlayer audioClipPlayer = new AudioClipPlayer("foo.txt", null);

    // Act and Assert
    assertSame(audioClipPlayer, audioClipPlayer.play());
  }

  @Test
  void testPlay4() throws MalformedURLException {
    // Arrange
    AudioClipPlayer audioClipPlayer = new AudioClipPlayer(
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL(),
        mock(AudioClipPlayerListener.class));

    // Act and Assert
    assertSame(audioClipPlayer, audioClipPlayer.play());
  }

  @Test
  void testPlay5() {
    // Arrange
    AudioClipPlayer audioClipPlayer = new AudioClipPlayer(
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), mock(AudioClipPlayerListener.class));

    // Act and Assert
    assertSame(audioClipPlayer, audioClipPlayer.play());
  }

  @Test
  void testPlay6() {
    // Arrange
    AudioClipPlayer audioClipPlayer = new AudioClipPlayer("foo.txt", mock(AudioClipPlayerListener.class));
    audioClipPlayer.setLoopCount(3);

    // Act and Assert
    assertSame(audioClipPlayer, audioClipPlayer.play());
  }

  @Test
  void testPlay7() {
    // Arrange
    AudioClipPlayer audioClipPlayer = new AudioClipPlayer("foo.txt", mock(AudioClipPlayerListener.class));
    audioClipPlayer.goTo(0L);

    // Act and Assert
    assertSame(audioClipPlayer, audioClipPlayer.play());
  }

  @Test
  void testPlay8() throws MalformedURLException {
    // Arrange
    AudioClipPlayer audioClipPlayer = new AudioClipPlayer(
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL(), null);

    // Act and Assert
    assertSame(audioClipPlayer, audioClipPlayer.play());
  }

  @Test
  void testPlay9() {
    // Arrange
    AudioClipPlayer audioClipPlayer = new AudioClipPlayer(
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), null);

    // Act and Assert
    assertSame(audioClipPlayer, audioClipPlayer.play());
  }

  @Test
  void testPlay10() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayInputStream stream = new ByteArrayInputStream("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));
    AudioClipPlayer audioClipPlayer = new AudioClipPlayer(
        new AudioInputStream(stream, new AudioFormat(10.0f, 3, 1, true, true), 3L),
        mock(AudioClipPlayerListener.class));

    // Act and Assert
    assertSame(audioClipPlayer, audioClipPlayer.play());
  }

  @Test
  void testPlay11() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayInputStream stream = new ByteArrayInputStream("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));
    AudioClipPlayer audioClipPlayer = new AudioClipPlayer(
        new AudioInputStream(stream, new AudioFormat(10.0f, 3, 1, true, true), 3L), null);

    // Act and Assert
    assertSame(audioClipPlayer, audioClipPlayer.play());
  }

  @Test
  void testPlay12() {
    // Arrange
    AudioClipPlayer audioClipPlayer = new AudioClipPlayer((AudioInputStream) null, null);

    // Act and Assert
    assertSame(audioClipPlayer, audioClipPlayer.play());
  }

  @Test
  void testPlay13() {
    // Arrange
    AudioClipPlayer audioClipPlayer = new AudioClipPlayer("foo.txt", mock(AudioClipPlayerListener.class));

    // Act
    AudioClipPlayer actualPlayResult = audioClipPlayer.play(1);

    // Assert
    assertSame(audioClipPlayer, actualPlayResult);
    assertEquals(1, actualPlayResult.loop_count);
  }

  @Test
  void testPlay14() {
    // Arrange
    AudioClipPlayer audioClipPlayer = new AudioClipPlayer((String) null, mock(AudioClipPlayerListener.class));

    // Act
    AudioClipPlayer actualPlayResult = audioClipPlayer.play(1);

    // Assert
    assertSame(audioClipPlayer, actualPlayResult);
    assertEquals(1, actualPlayResult.loop_count);
  }

  @Test
  void testPlay15() {
    // Arrange
    AudioClipPlayer audioClipPlayer = new AudioClipPlayer("foo.txt", null);

    // Act
    AudioClipPlayer actualPlayResult = audioClipPlayer.play(1);

    // Assert
    assertSame(audioClipPlayer, actualPlayResult);
    assertEquals(1, actualPlayResult.loop_count);
  }

  @Test
  void testPlay16() throws MalformedURLException {
    // Arrange
    AudioClipPlayer audioClipPlayer = new AudioClipPlayer(
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL(),
        mock(AudioClipPlayerListener.class));

    // Act
    AudioClipPlayer actualPlayResult = audioClipPlayer.play(1);

    // Assert
    assertSame(audioClipPlayer, actualPlayResult);
    assertEquals(1, actualPlayResult.loop_count);
  }

  @Test
  void testPlay17() {
    // Arrange
    AudioClipPlayer audioClipPlayer = new AudioClipPlayer(
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), mock(AudioClipPlayerListener.class));

    // Act
    AudioClipPlayer actualPlayResult = audioClipPlayer.play(1);

    // Assert
    assertSame(audioClipPlayer, actualPlayResult);
    assertEquals(1, actualPlayResult.loop_count);
  }

  @Test
  void testPlay18() {
    // Arrange
    AudioClipPlayer audioClipPlayer = new AudioClipPlayer("foo.txt", mock(AudioClipPlayerListener.class));
    audioClipPlayer.setLoopCount(3);

    // Act
    AudioClipPlayer actualPlayResult = audioClipPlayer.play(1);

    // Assert
    assertSame(audioClipPlayer, actualPlayResult);
    assertEquals(1, actualPlayResult.loop_count);
  }

  @Test
  void testPlay19() {
    // Arrange
    AudioClipPlayer audioClipPlayer = new AudioClipPlayer("foo.txt", mock(AudioClipPlayerListener.class));
    audioClipPlayer.goTo(0L);

    // Act
    AudioClipPlayer actualPlayResult = audioClipPlayer.play(1);

    // Assert
    assertSame(audioClipPlayer, actualPlayResult);
    assertEquals(1, actualPlayResult.loop_count);
  }

  @Test
  void testPlay20() throws MalformedURLException {
    // Arrange
    AudioClipPlayer audioClipPlayer = new AudioClipPlayer(
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL(), null);

    // Act
    AudioClipPlayer actualPlayResult = audioClipPlayer.play(1);

    // Assert
    assertSame(audioClipPlayer, actualPlayResult);
    assertEquals(1, actualPlayResult.loop_count);
  }

  @Test
  void testPlay21() {
    // Arrange
    AudioClipPlayer audioClipPlayer = new AudioClipPlayer(
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), null);

    // Act
    AudioClipPlayer actualPlayResult = audioClipPlayer.play(1);

    // Assert
    assertSame(audioClipPlayer, actualPlayResult);
    assertEquals(1, actualPlayResult.loop_count);
  }

  @Test
  void testPlay22() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayInputStream stream = new ByteArrayInputStream("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));
    AudioClipPlayer audioClipPlayer = new AudioClipPlayer(
        new AudioInputStream(stream, new AudioFormat(10.0f, 3, 1, true, true), 3L),
        mock(AudioClipPlayerListener.class));

    // Act
    AudioClipPlayer actualPlayResult = audioClipPlayer.play(1);

    // Assert
    assertSame(audioClipPlayer, actualPlayResult);
    assertEquals(1, actualPlayResult.loop_count);
  }

  @Test
  void testPlay23() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayInputStream stream = new ByteArrayInputStream("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));
    AudioClipPlayer audioClipPlayer = new AudioClipPlayer(
        new AudioInputStream(stream, new AudioFormat(10.0f, 3, 1, true, true), 3L), null);

    // Act
    AudioClipPlayer actualPlayResult = audioClipPlayer.play(1);

    // Assert
    assertSame(audioClipPlayer, actualPlayResult);
    assertEquals(1, actualPlayResult.loop_count);
  }

  @Test
  void testPlay24() {
    // Arrange
    AudioClipPlayer audioClipPlayer = new AudioClipPlayer((AudioInputStream) null, null);

    // Act
    AudioClipPlayer actualPlayResult = audioClipPlayer.play(1);

    // Assert
    assertSame(audioClipPlayer, actualPlayResult);
    assertEquals(1, actualPlayResult.loop_count);
  }

  @Test
  void testPause() {
    // Arrange
    AudioClipPlayer audioClipPlayer = new AudioClipPlayer("foo.txt", mock(AudioClipPlayerListener.class));

    // Act and Assert
    assertSame(audioClipPlayer, audioClipPlayer.pause());
  }

  @Test
  void testPause2() {
    // Arrange
    AudioClipPlayer audioClipPlayer = new AudioClipPlayer((String) null, mock(AudioClipPlayerListener.class));

    // Act and Assert
    assertSame(audioClipPlayer, audioClipPlayer.pause());
  }

  @Test
  void testPause3() {
    // Arrange
    AudioClipPlayer audioClipPlayer = new AudioClipPlayer("foo.txt", null);

    // Act and Assert
    assertSame(audioClipPlayer, audioClipPlayer.pause());
  }

  @Test
  void testPause4() throws MalformedURLException {
    // Arrange
    AudioClipPlayer audioClipPlayer = new AudioClipPlayer(
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL(),
        mock(AudioClipPlayerListener.class));

    // Act and Assert
    assertSame(audioClipPlayer, audioClipPlayer.pause());
  }

  @Test
  void testPause5() {
    // Arrange
    AudioClipPlayer audioClipPlayer = new AudioClipPlayer(
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), mock(AudioClipPlayerListener.class));

    // Act and Assert
    assertSame(audioClipPlayer, audioClipPlayer.pause());
  }

  @Test
  void testPause6() {
    // Arrange
    AudioClipPlayer audioClipPlayer = new AudioClipPlayer("foo.txt", mock(AudioClipPlayerListener.class));
    audioClipPlayer.setLoopCount(3);

    // Act and Assert
    assertSame(audioClipPlayer, audioClipPlayer.pause());
  }

  @Test
  void testPause7() {
    // Arrange
    AudioClipPlayer audioClipPlayer = new AudioClipPlayer("foo.txt", mock(AudioClipPlayerListener.class));
    audioClipPlayer.goTo(0L);

    // Act and Assert
    assertSame(audioClipPlayer, audioClipPlayer.pause());
  }

  @Test
  void testPause8() throws MalformedURLException {
    // Arrange
    AudioClipPlayer audioClipPlayer = new AudioClipPlayer(
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL(), null);

    // Act and Assert
    assertSame(audioClipPlayer, audioClipPlayer.pause());
  }

  @Test
  void testPause9() {
    // Arrange
    AudioClipPlayer audioClipPlayer = new AudioClipPlayer(
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), null);

    // Act and Assert
    assertSame(audioClipPlayer, audioClipPlayer.pause());
  }

  @Test
  void testPause10() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayInputStream stream = new ByteArrayInputStream("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));
    AudioClipPlayer audioClipPlayer = new AudioClipPlayer(
        new AudioInputStream(stream, new AudioFormat(10.0f, 3, 1, true, true), 3L),
        mock(AudioClipPlayerListener.class));

    // Act and Assert
    assertSame(audioClipPlayer, audioClipPlayer.pause());
  }

  @Test
  void testPause11() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayInputStream stream = new ByteArrayInputStream("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));
    AudioClipPlayer audioClipPlayer = new AudioClipPlayer(
        new AudioInputStream(stream, new AudioFormat(10.0f, 3, 1, true, true), 3L), null);

    // Act and Assert
    assertSame(audioClipPlayer, audioClipPlayer.pause());
  }

  @Test
  void testPause12() {
    // Arrange
    AudioClipPlayer audioClipPlayer = new AudioClipPlayer((AudioInputStream) null, null);

    // Act and Assert
    assertSame(audioClipPlayer, audioClipPlayer.pause());
  }

  @Test
  void testStop() {
    // Arrange
    AudioClipPlayer audioClipPlayer = new AudioClipPlayer("foo.txt", mock(AudioClipPlayerListener.class));

    // Act and Assert
    assertSame(audioClipPlayer, audioClipPlayer.stop());
  }

  @Test
  void testStop2() {
    // Arrange
    AudioClipPlayer audioClipPlayer = new AudioClipPlayer((String) null, mock(AudioClipPlayerListener.class));

    // Act and Assert
    assertSame(audioClipPlayer, audioClipPlayer.stop());
  }

  @Test
  void testStop3() {
    // Arrange
    AudioClipPlayer audioClipPlayer = new AudioClipPlayer("foo.txt", null);

    // Act and Assert
    assertSame(audioClipPlayer, audioClipPlayer.stop());
  }

  @Test
  void testStop4() throws MalformedURLException {
    // Arrange
    AudioClipPlayer audioClipPlayer = new AudioClipPlayer(
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL(),
        mock(AudioClipPlayerListener.class));

    // Act and Assert
    assertSame(audioClipPlayer, audioClipPlayer.stop());
  }

  @Test
  void testStop5() {
    // Arrange
    AudioClipPlayer audioClipPlayer = new AudioClipPlayer(
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), mock(AudioClipPlayerListener.class));

    // Act and Assert
    assertSame(audioClipPlayer, audioClipPlayer.stop());
  }

  @Test
  void testStop6() {
    // Arrange
    AudioClipPlayer audioClipPlayer = new AudioClipPlayer("foo.txt", mock(AudioClipPlayerListener.class));
    audioClipPlayer.setLoopCount(3);

    // Act and Assert
    assertSame(audioClipPlayer, audioClipPlayer.stop());
  }

  @Test
  void testStop7() {
    // Arrange
    AudioClipPlayer audioClipPlayer = new AudioClipPlayer("foo.txt", mock(AudioClipPlayerListener.class));
    audioClipPlayer.goTo(0L);

    // Act and Assert
    assertSame(audioClipPlayer, audioClipPlayer.stop());
  }

  @Test
  void testStop8() throws MalformedURLException {
    // Arrange
    AudioClipPlayer audioClipPlayer = new AudioClipPlayer(
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL(), null);

    // Act and Assert
    assertSame(audioClipPlayer, audioClipPlayer.stop());
  }

  @Test
  void testStop9() {
    // Arrange
    AudioClipPlayer audioClipPlayer = new AudioClipPlayer(
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), null);

    // Act and Assert
    assertSame(audioClipPlayer, audioClipPlayer.stop());
  }

  @Test
  void testStop10() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayInputStream stream = new ByteArrayInputStream("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));
    AudioClipPlayer audioClipPlayer = new AudioClipPlayer(
        new AudioInputStream(stream, new AudioFormat(10.0f, 3, 1, true, true), 3L),
        mock(AudioClipPlayerListener.class));

    // Act and Assert
    assertSame(audioClipPlayer, audioClipPlayer.stop());
  }

  @Test
  void testStop11() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayInputStream stream = new ByteArrayInputStream("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));
    AudioClipPlayer audioClipPlayer = new AudioClipPlayer(
        new AudioInputStream(stream, new AudioFormat(10.0f, 3, 1, true, true), 3L), null);

    // Act and Assert
    assertSame(audioClipPlayer, audioClipPlayer.stop());
  }

  @Test
  void testStop12() {
    // Arrange
    AudioClipPlayer audioClipPlayer = new AudioClipPlayer((AudioInputStream) null, null);

    // Act and Assert
    assertSame(audioClipPlayer, audioClipPlayer.stop());
  }

  @Test
  void testGoTo() {
    // Arrange
    AudioClipPlayer audioClipPlayer = new AudioClipPlayer("foo.txt", mock(AudioClipPlayerListener.class));

    // Act and Assert
    assertSame(audioClipPlayer, audioClipPlayer.goTo(1L));
  }

  @Test
  void testGoTo2() {
    // Arrange
    AudioClipPlayer audioClipPlayer = new AudioClipPlayer((String) null, mock(AudioClipPlayerListener.class));

    // Act and Assert
    assertSame(audioClipPlayer, audioClipPlayer.goTo(1L));
  }

  @Test
  void testGoTo3() {
    // Arrange
    AudioClipPlayer audioClipPlayer = new AudioClipPlayer("foo.txt", null);

    // Act and Assert
    assertSame(audioClipPlayer, audioClipPlayer.goTo(1L));
  }

  @Test
  void testGoTo4() throws MalformedURLException {
    // Arrange
    AudioClipPlayer audioClipPlayer = new AudioClipPlayer(
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL(),
        mock(AudioClipPlayerListener.class));

    // Act and Assert
    assertSame(audioClipPlayer, audioClipPlayer.goTo(1L));
  }

  @Test
  void testGoTo5() {
    // Arrange
    AudioClipPlayer audioClipPlayer = new AudioClipPlayer(
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), mock(AudioClipPlayerListener.class));

    // Act and Assert
    assertSame(audioClipPlayer, audioClipPlayer.goTo(1L));
  }

  @Test
  void testGoTo6() {
    // Arrange
    AudioClipPlayer audioClipPlayer = new AudioClipPlayer("foo.txt", mock(AudioClipPlayerListener.class));
    audioClipPlayer.setLoopCount(3);

    // Act and Assert
    assertSame(audioClipPlayer, audioClipPlayer.goTo(1L));
  }

  @Test
  void testGoTo7() throws MalformedURLException {
    // Arrange
    AudioClipPlayer audioClipPlayer = new AudioClipPlayer(
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL(), null);

    // Act and Assert
    assertSame(audioClipPlayer, audioClipPlayer.goTo(1L));
  }

  @Test
  void testGoTo8() {
    // Arrange
    AudioClipPlayer audioClipPlayer = new AudioClipPlayer(
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), null);

    // Act and Assert
    assertSame(audioClipPlayer, audioClipPlayer.goTo(1L));
  }

  @Test
  void testGoTo9() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayInputStream stream = new ByteArrayInputStream("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));
    AudioClipPlayer audioClipPlayer = new AudioClipPlayer(
        new AudioInputStream(stream, new AudioFormat(10.0f, 3, 1, true, true), 3L),
        mock(AudioClipPlayerListener.class));

    // Act and Assert
    assertSame(audioClipPlayer, audioClipPlayer.goTo(1L));
  }

  @Test
  void testGoTo10() throws UnsupportedEncodingException {
    // Arrange
    ByteArrayInputStream stream = new ByteArrayInputStream("AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"));
    AudioClipPlayer audioClipPlayer = new AudioClipPlayer(
        new AudioInputStream(stream, new AudioFormat(10.0f, 3, 1, true, true), 3L), null);

    // Act and Assert
    assertSame(audioClipPlayer, audioClipPlayer.goTo(1L));
  }

  @Test
  void testGoTo11() {
    // Arrange
    AudioClipPlayer audioClipPlayer = new AudioClipPlayer((AudioInputStream) null, null);

    // Act and Assert
    assertSame(audioClipPlayer, audioClipPlayer.goTo(1L));
  }
}

