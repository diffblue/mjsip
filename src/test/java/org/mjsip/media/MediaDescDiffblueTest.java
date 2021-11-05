package org.mjsip.media;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Vector;
import org.junit.jupiter.api.Test;
import org.mjsip.sdp.AttributeField;
import org.mjsip.sdp.MediaDescriptor;
import org.mjsip.sdp.field.ConnectionField;
import org.mjsip.sdp.field.MediaField;

class MediaDescDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    MediaDesc actualMediaDesc = new MediaDesc("Media", 8080, "Transport",
        new MediaSpec[]{new MediaSpec("Type", 1, "Codec", 1, 1, 3)});

    // Assert
    assertEquals("Media", actualMediaDesc.getMedia());
    assertEquals("Transport", actualMediaDesc.getTransport());
    assertEquals(8080, actualMediaDesc.getPort());
    assertEquals(1, actualMediaDesc.getMediaSpecs().length);
  }

  @Test
  void testConstructor2() {
    // Arrange
    MediaDescriptor mediaDescriptor = mock(MediaDescriptor.class);
    when(mediaDescriptor.getAttributes((String) any()))
        .thenReturn(new AttributeField[]{new AttributeField("Attribute", "42")});
    when(mediaDescriptor.getMedia()).thenReturn(new MediaField("Media", 8080, 10, "Transport", "Formats"));

    // Act
    MediaDesc actualMediaDesc = new MediaDesc(mediaDescriptor);

    // Assert
    assertEquals("Media", actualMediaDesc.getMedia());
    assertEquals("Media 8080 Transport { Media 42 }", actualMediaDesc.toString());
    assertEquals("Transport", actualMediaDesc.getTransport());
    assertEquals(8080, actualMediaDesc.getPort());
    assertEquals(1, actualMediaDesc.getMediaSpecs().length);
    verify(mediaDescriptor).getAttributes((String) any());
    verify(mediaDescriptor).getMedia();
  }

  @Test
  void testConstructor3() {
    // Arrange
    AttributeField attributeField = mock(AttributeField.class);
    when(attributeField.getAttributeValue()).thenReturn("42");
    MediaDescriptor mediaDescriptor = mock(MediaDescriptor.class);
    when(mediaDescriptor.getAttributes((String) any())).thenReturn(new AttributeField[]{attributeField});
    when(mediaDescriptor.getMedia()).thenReturn(new MediaField("Media", 8080, 10, "Transport", "Formats"));

    // Act
    MediaDesc actualMediaDesc = new MediaDesc(mediaDescriptor);

    // Assert
    assertEquals("Media", actualMediaDesc.getMedia());
    assertEquals("Media 8080 Transport { Media 42 }", actualMediaDesc.toString());
    assertEquals("Transport", actualMediaDesc.getTransport());
    assertEquals(8080, actualMediaDesc.getPort());
    assertEquals(1, actualMediaDesc.getMediaSpecs().length);
    verify(mediaDescriptor).getAttributes((String) any());
    verify(mediaDescriptor).getMedia();
    verify(attributeField).getAttributeValue();
  }

  @Test
  void testConstructor4() {
    // Arrange
    AttributeField attributeField = mock(AttributeField.class);
    when(attributeField.getAttributeValue()).thenReturn("42");
    MediaDescriptor mediaDescriptor = mock(MediaDescriptor.class);
    when(mediaDescriptor.getAttributes((String) any())).thenReturn(new AttributeField[]{attributeField});
    when(mediaDescriptor.getMedia()).thenReturn(new MediaField("Media", 8080, 0, "Transport", "Formats"));

    // Act
    MediaDesc actualMediaDesc = new MediaDesc(mediaDescriptor);

    // Assert
    assertEquals("Media", actualMediaDesc.getMedia());
    assertEquals("Media 8080 Transport { Media 42 }", actualMediaDesc.toString());
    assertEquals("Transport", actualMediaDesc.getTransport());
    assertEquals(8080, actualMediaDesc.getPort());
    assertEquals(1, actualMediaDesc.getMediaSpecs().length);
    verify(mediaDescriptor).getAttributes((String) any());
    verify(mediaDescriptor).getMedia();
    verify(attributeField).getAttributeValue();
  }

  @Test
  void testConstructor5() {
    // Arrange
    MediaField mediaField = mock(MediaField.class);
    when(mediaField.getTransport()).thenReturn("Transport");
    when(mediaField.getPort()).thenReturn(8080);
    when(mediaField.getMedia()).thenReturn("Media");
    AttributeField attributeField = mock(AttributeField.class);
    when(attributeField.getAttributeValue()).thenReturn("42");
    MediaDescriptor mediaDescriptor = mock(MediaDescriptor.class);
    when(mediaDescriptor.getAttributes((String) any())).thenReturn(new AttributeField[]{attributeField});
    when(mediaDescriptor.getMedia()).thenReturn(mediaField);

    // Act
    MediaDesc actualMediaDesc = new MediaDesc(mediaDescriptor);

    // Assert
    assertEquals("Media", actualMediaDesc.getMedia());
    assertEquals("Media 8080 Transport { Media 42 }", actualMediaDesc.toString());
    assertEquals("Transport", actualMediaDesc.getTransport());
    assertEquals(8080, actualMediaDesc.getPort());
    assertEquals(1, actualMediaDesc.getMediaSpecs().length);
    verify(mediaDescriptor).getAttributes((String) any());
    verify(mediaDescriptor).getMedia();
    verify(attributeField).getAttributeValue();
    verify(mediaField).getMedia();
    verify(mediaField).getPort();
    verify(mediaField).getTransport();
  }

  @Test
  void testConstructor6() {
    // Arrange
    AttributeField attributeField = mock(AttributeField.class);
    when(attributeField.getAttributeValue()).thenReturn("42");
    MediaDescriptor mediaDescriptor = mock(MediaDescriptor.class);
    when(mediaDescriptor.getAttributes((String) any())).thenReturn(new AttributeField[]{attributeField});
    when(mediaDescriptor.getMedia()).thenReturn(new MediaField("Media", 8080, 10, "Transport", new Vector(1)));

    // Act
    MediaDesc actualMediaDesc = new MediaDesc(mediaDescriptor);

    // Assert
    assertEquals("Media", actualMediaDesc.getMedia());
    assertEquals("Media 8080 Transport { Media 42 }", actualMediaDesc.toString());
    assertEquals("Transport", actualMediaDesc.getTransport());
    assertEquals(8080, actualMediaDesc.getPort());
    assertEquals(1, actualMediaDesc.getMediaSpecs().length);
    verify(mediaDescriptor).getAttributes((String) any());
    verify(mediaDescriptor).getMedia();
    verify(attributeField).getAttributeValue();
  }

  @Test
  void testConstructor7() {
    // Arrange
    AttributeField attributeField = mock(AttributeField.class);
    when(attributeField.getAttributeValue()).thenReturn("42");
    MediaDescriptor mediaDescriptor = mock(MediaDescriptor.class);
    when(mediaDescriptor.getAttributes((String) any())).thenReturn(new AttributeField[]{attributeField});
    when(mediaDescriptor.getMedia()).thenReturn(new MediaField("Media", 8080, 0, "Transport", new Vector(1)));

    // Act
    MediaDesc actualMediaDesc = new MediaDesc(mediaDescriptor);

    // Assert
    assertEquals("Media", actualMediaDesc.getMedia());
    assertEquals("Media 8080 Transport { Media 42 }", actualMediaDesc.toString());
    assertEquals("Transport", actualMediaDesc.getTransport());
    assertEquals(8080, actualMediaDesc.getPort());
    assertEquals(1, actualMediaDesc.getMediaSpecs().length);
    verify(mediaDescriptor).getAttributes((String) any());
    verify(mediaDescriptor).getMedia();
    verify(attributeField).getAttributeValue();
  }

  @Test
  void testConstructor8() {
    // Arrange
    Vector vector = new Vector(1);
    vector.add("42");
    MediaField mediaField = new MediaField("Media", 8080, 10, "Transport", vector);

    AttributeField attributeField = mock(AttributeField.class);
    when(attributeField.getAttributeValue()).thenReturn("42");
    MediaDescriptor mediaDescriptor = mock(MediaDescriptor.class);
    when(mediaDescriptor.getAttributes((String) any())).thenReturn(new AttributeField[]{attributeField});
    when(mediaDescriptor.getMedia()).thenReturn(mediaField);

    // Act
    MediaDesc actualMediaDesc = new MediaDesc(mediaDescriptor);

    // Assert
    assertEquals("Media", actualMediaDesc.getMedia());
    assertEquals("Media 8080 Transport { Media 42 }", actualMediaDesc.toString());
    assertEquals("Transport", actualMediaDesc.getTransport());
    assertEquals(8080, actualMediaDesc.getPort());
    assertEquals(1, actualMediaDesc.getMediaSpecs().length);
    verify(mediaDescriptor).getAttributes((String) any());
    verify(mediaDescriptor).getMedia();
    verify(attributeField).getAttributeValue();
  }

  @Test
  void testToMediaDescriptor() {
    // Arrange and Act
    MediaDescriptor actualToMediaDescriptorResult = (new MediaDesc("Media", 8080, "Transport", new MediaSpec[]{}))
        .toMediaDescriptor();

    // Assert
    assertEquals(0, actualToMediaDescriptorResult.getAttributes().length);
    assertEquals("m=Media 8080 Transport\r\n", actualToMediaDescriptorResult.toString());
    assertNull(actualToMediaDescriptorResult.getConnection());
    MediaField media = actualToMediaDescriptorResult.getMedia();
    assertEquals("Media 8080 Transport", media.getValue());
    assertEquals('m', media.getType());
  }

  @Test
  void testToMediaDescriptor2() {
    // Arrange
    MediaField media = new MediaField("Media", 8080, 10, "Transport", "Formats");

    // Act
    MediaDescriptor actualToMediaDescriptorResult = (new MediaDesc(
        new MediaDescriptor(media, new ConnectionField("Connection field")))).toMediaDescriptor();

    // Assert
    assertEquals(0, actualToMediaDescriptorResult.getAttributes().length);
    assertEquals("m=Media 8080 Transport\r\n", actualToMediaDescriptorResult.toString());
    assertNull(actualToMediaDescriptorResult.getConnection());
    MediaField media1 = actualToMediaDescriptorResult.getMedia();
    assertEquals("Media 8080 Transport", media1.getValue());
    assertEquals('m', media1.getType());
  }

  @Test
  void testToMediaDescriptor3() {
    // Arrange
    MediaField media = new MediaField("Media", 8080, 10, "Transport", new Vector(1));

    // Act
    MediaDescriptor actualToMediaDescriptorResult = (new MediaDesc(
        new MediaDescriptor(media, new ConnectionField("Connection field")))).toMediaDescriptor();

    // Assert
    assertEquals(0, actualToMediaDescriptorResult.getAttributes().length);
    assertEquals("m=Media 8080 Transport\r\n", actualToMediaDescriptorResult.toString());
    assertNull(actualToMediaDescriptorResult.getConnection());
    MediaField media1 = actualToMediaDescriptorResult.getMedia();
    assertEquals("Media 8080 Transport", media1.getValue());
    assertEquals('m', media1.getType());
  }

  @Test
  void testToMediaDescriptor4() {
    // Arrange
    MediaField media = new MediaField("Media", 8080, 10, "Transport", "Formats");

    ConnectionField connection = new ConnectionField("Connection field");
    AttributeField attributeField = new AttributeField("Attribute");
    AttributeField attributeField1 = new AttributeField("Attribute");

    // Act
    MediaDescriptor actualToMediaDescriptorResult = (new MediaDesc(new MediaDescriptor(media, connection,
        new AttributeField[]{attributeField, attributeField1, new AttributeField("Attribute")}))).toMediaDescriptor();

    // Assert
    assertEquals(0, actualToMediaDescriptorResult.getAttributes().length);
    assertEquals("m=Media 8080 Transport\r\n", actualToMediaDescriptorResult.toString());
    assertNull(actualToMediaDescriptorResult.getConnection());
    MediaField media1 = actualToMediaDescriptorResult.getMedia();
    assertEquals("Media 8080 Transport", media1.getValue());
    assertEquals('m', media1.getType());
  }

  @Test
  void testToString() {
    // Arrange, Act and Assert
    assertEquals("Media 8080 Transport { }", (new MediaDesc("Media", 8080, "Transport", new MediaSpec[]{})).toString());
  }

  @Test
  void testToString2() {
    // Arrange
    MediaDesc mediaDesc = new MediaDesc("Media", 8080, "Transport", new MediaSpec[]{});
    mediaDesc.setPort(8080);

    // Act and Assert
    assertEquals("Media 8080 Transport { }", mediaDesc.toString());
  }

  @Test
  void testToString3() {
    // Arrange
    MediaDesc mediaDesc = new MediaDesc("Media", 8080, "Transport", null);
    mediaDesc.setPort(8080);

    // Act and Assert
    assertEquals("Media 8080 Transport", mediaDesc.toString());
  }

  @Test
  void testToString4() {
    // Arrange
    MediaSpec mediaSpec = new MediaSpec("Type", 1, "Codec", 1, 1, 3);

    // Act and Assert
    assertEquals("Media 8080 Transport { Type 1 Codec 1 3 1, Type 1 Codec 1 3 1 }", (new MediaDesc("Media", 8080,
        "Transport", new MediaSpec[]{mediaSpec, new MediaSpec("Type", 1, "Codec", 1, 1, 3)})).toString());
  }

  @Test
  void testToString5() {
    // Arrange
    MediaSpec mediaSpec = new MediaSpec("Type", 1, null, 1, 1, 3);

    // Act and Assert
    assertEquals("Media 8080 Transport { Type 1, Type 1 Codec 1 3 1 }", (new MediaDesc("Media", 8080, "Transport",
        new MediaSpec[]{mediaSpec, new MediaSpec("Type", 1, "Codec", 1, 1, 3)})).toString());
  }
}

