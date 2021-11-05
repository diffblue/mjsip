package org.mjsip.ua;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import java.io.StringWriter;
import org.junit.jupiter.api.Test;
import org.mjsip.media.FlowSpec;
import org.mjsip.media.MediaSpec;
import org.zoolu.util.LogWriter;
import org.zoolu.util.Logger;

class MediaAgentDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange
    UserAgentProfile ua_profile = new UserAgentProfile();

    // Act
    MediaAgent actualMediaAgent = new MediaAgent(ua_profile, new LogWriter(new StringWriter()));

    // Assert
    assertTrue(actualMediaAgent.logger instanceof LogWriter);
    assertTrue(actualMediaAgent.media_streamers.isEmpty());
    assertFalse(actualMediaAgent.ua_profile.video);
  }

  @Test
  void testStartMediaSession() {
    // Arrange
    UserAgentProfile ua_profile = new UserAgentProfile();
    MediaAgent mediaAgent = new MediaAgent(ua_profile, new LogWriter(new StringWriter()));
    MediaSpec media_spec = new MediaSpec("Type", 1, "Codec", 1, 1, 3);

    // Act and Assert
    assertFalse(
        mediaAgent.startMediaSession(new FlowSpec(media_spec, 8080, "42 Main St", 8080, new FlowSpec.Direction())));
  }

  @Test
  void testStartMediaSession2() {
    // Arrange
    MediaAgent mediaAgent = new MediaAgent(new UserAgentProfile(), null);
    MediaSpec media_spec = new MediaSpec("Type", 1, "Codec", 1, 1, 3);

    // Act and Assert
    assertFalse(
        mediaAgent.startMediaSession(new FlowSpec(media_spec, 8080, "42 Main St", 8080, new FlowSpec.Direction())));
  }

  @Test
  void testStartMediaSession3() {
    // Arrange
    UserAgentProfile ua_profile = new UserAgentProfile();
    MediaAgent mediaAgent = new MediaAgent(ua_profile, new LogWriter(new StringWriter()));
    MediaSpec media_spec = new MediaSpec("video", 1, "Codec", 1, 1, 3);

    // Act and Assert
    assertFalse(
        mediaAgent.startMediaSession(new FlowSpec(media_spec, 8080, "42 Main St", 8080, new FlowSpec.Direction())));
  }

  @Test
  void testStopMediaSession() {
    // Arrange
    Logger logger = mock(Logger.class);
    doNothing().when(logger).log((org.zoolu.util.LogLevel) any(), (String) any());

    // Act
    (new MediaAgent(new UserAgentProfile(), logger)).stopMediaSession("Media");

    // Assert
    verify(logger, atLeast(1)).log((org.zoolu.util.LogLevel) any(), (String) any());
  }
}

