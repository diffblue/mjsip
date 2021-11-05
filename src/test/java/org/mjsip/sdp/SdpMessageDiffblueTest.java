package org.mjsip.sdp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import java.util.Vector;
import org.junit.jupiter.api.Test;
import org.mjsip.sdp.field.ConnectionField;
import org.mjsip.sdp.field.KeyField;
import org.mjsip.sdp.field.OriginField;
import org.mjsip.sdp.field.SessionNameField;
import org.mjsip.sdp.field.TimeField;

class SdpMessageDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    SdpMessage actualSdpMessage = new SdpMessage();
    actualSdpMessage.setConnection(new ConnectionField("Connection field"));
    actualSdpMessage.setKey(new KeyField("Key field"));
    actualSdpMessage.setOrigin(new OriginField("Origin"));
    SdpMessage actualSetSessionNameResult = actualSdpMessage.setSessionName(new SessionNameField("Session name"));

    // Assert
    Vector expectedAttributes = actualSetSessionNameResult.av;
    Vector attributes = actualSdpMessage.getAttributes();
    assertSame(expectedAttributes, attributes);
    Vector vector = actualSetSessionNameResult.media;
    assertEquals(vector, attributes);
    ConnectionField expectedConnection = actualSetSessionNameResult.c;
    assertSame(expectedConnection, actualSdpMessage.getConnection());
    KeyField expectedKey = actualSetSessionNameResult.k;
    assertSame(expectedKey, actualSdpMessage.getKey());
    Vector mediaDescriptors = actualSdpMessage.getMediaDescriptors();
    assertSame(vector, mediaDescriptors);
    assertEquals(attributes, mediaDescriptors);
    OriginField expectedOrigin = actualSetSessionNameResult.o;
    assertSame(expectedOrigin, actualSdpMessage.getOrigin());
    SessionNameField expectedSessionName = actualSetSessionNameResult.s;
    assertSame(expectedSessionName, actualSdpMessage.getSessionName());
    SdpField sdpField = actualSdpMessage.v;
    assertEquals('v', sdpField.getType());
    assertEquals("0", sdpField.getValue());
    assertEquals("v=0\r\n", sdpField.toString());
  }

  @Test
  void testConstructor2() {
    // Arrange and Act
    SdpMessage actualSdpMessage = new SdpMessage();

    // Assert
    Vector expectedAttributes = actualSdpMessage.media;
    Vector attributes = actualSdpMessage.getAttributes();
    assertEquals(expectedAttributes, attributes);
    assertEquals(attributes, actualSdpMessage.getMediaDescriptors());
    assertEquals("v=0\r\no=- 0 0 IN IP4 127.0.0.1\r\ns=-\r\nc=IN IP4 127.0.0.1\r\nt=0 0\r\n",
        actualSdpMessage.toString());
    assertNull(actualSdpMessage.bv);
    assertEquals(1, actualSdpMessage.tv.length);
    SessionNameField sessionName = actualSdpMessage.getSessionName();
    assertEquals('s', sessionName.getType());
    TimeField time = actualSdpMessage.getTime();
    assertEquals("0 0", time.getValue());
    assertEquals('t', time.getType());
    assertEquals("-", sessionName.getSession());
    SdpField sdpField = actualSdpMessage.v;
    assertEquals('v', sdpField.getType());
    OriginField origin = actualSdpMessage.getOrigin();
    assertEquals("- 0 0 IN IP4 127.0.0.1", origin.getValue());
    ConnectionField connection = actualSdpMessage.getConnection();
    assertEquals('c', connection.getType());
    assertEquals("IN IP4 127.0.0.1", connection.getValue());
    assertEquals("0", sdpField.getValue());
    assertEquals('o', origin.getType());
  }

  @Test
  void testConstructor3() {
    // Arrange and Act
    SdpMessage actualSdpMessage = new SdpMessage("Owner", "42 Main St");

    // Assert
    Vector expectedAttributes = actualSdpMessage.media;
    Vector attributes = actualSdpMessage.getAttributes();
    assertEquals(expectedAttributes, attributes);
    assertEquals(attributes, actualSdpMessage.getMediaDescriptors());
    assertEquals("v=0\r\no=Owner 0 0 IN IP4 42 Main St\r\ns=-\r\nc=IN IP4 42 Main St\r\nt=0 0\r\n",
        actualSdpMessage.toString());
    assertNull(actualSdpMessage.bv);
    assertEquals(1, actualSdpMessage.tv.length);
    SessionNameField sessionName = actualSdpMessage.getSessionName();
    assertEquals('s', sessionName.getType());
    TimeField time = actualSdpMessage.getTime();
    assertEquals("0 0", time.getValue());
    assertEquals('t', time.getType());
    assertEquals("-", sessionName.getSession());
    SdpField sdpField = actualSdpMessage.v;
    assertEquals('v', sdpField.getType());
    OriginField origin = actualSdpMessage.getOrigin();
    assertEquals("Owner 0 0 IN IP4 42 Main St", origin.getValue());
    ConnectionField connection = actualSdpMessage.getConnection();
    assertEquals('c', connection.getType());
    assertEquals("IN IP4 42 Main St", connection.getValue());
    assertEquals("0", sdpField.getValue());
    assertEquals('o', origin.getType());
  }

  @Test
  void testConstructor4() {
    // Arrange and Act
    SdpMessage actualSdpMessage = new SdpMessage(null, "42 Main St");

    // Assert
    Vector expectedAttributes = actualSdpMessage.media;
    Vector attributes = actualSdpMessage.getAttributes();
    assertEquals(expectedAttributes, attributes);
    assertEquals(attributes, actualSdpMessage.getMediaDescriptors());
    assertEquals("v=0\r\no=- 0 0 IN IP4 42 Main St\r\ns=-\r\nc=IN IP4 42 Main St\r\nt=0 0\r\n",
        actualSdpMessage.toString());
    assertNull(actualSdpMessage.bv);
    assertEquals(1, actualSdpMessage.tv.length);
    SessionNameField sessionName = actualSdpMessage.getSessionName();
    assertEquals('s', sessionName.getType());
    TimeField time = actualSdpMessage.getTime();
    assertEquals("0 0", time.getValue());
    assertEquals('t', time.getType());
    assertEquals("-", sessionName.getSession());
    SdpField sdpField = actualSdpMessage.v;
    assertEquals('v', sdpField.getType());
    OriginField origin = actualSdpMessage.getOrigin();
    assertEquals("- 0 0 IN IP4 42 Main St", origin.getValue());
    ConnectionField connection = actualSdpMessage.getConnection();
    assertEquals('c', connection.getType());
    assertEquals("IN IP4 42 Main St", connection.getValue());
    assertEquals("0", sdpField.getValue());
    assertEquals('o', origin.getType());
  }

  @Test
  void testConstructor5() {
    // Arrange and Act
    SdpMessage actualSdpMessage = new SdpMessage("", "42 Main St");

    // Assert
    Vector expectedAttributes = actualSdpMessage.media;
    Vector attributes = actualSdpMessage.getAttributes();
    assertEquals(expectedAttributes, attributes);
    assertEquals(attributes, actualSdpMessage.getMediaDescriptors());
    assertEquals("v=0\r\no=- 0 0 IN IP4 42 Main St\r\ns=-\r\nc=IN IP4 42 Main St\r\nt=0 0\r\n",
        actualSdpMessage.toString());
    assertNull(actualSdpMessage.bv);
    assertEquals(1, actualSdpMessage.tv.length);
    SessionNameField sessionName = actualSdpMessage.getSessionName();
    assertEquals('s', sessionName.getType());
    TimeField time = actualSdpMessage.getTime();
    assertEquals("0 0", time.getValue());
    assertEquals('t', time.getType());
    assertEquals("-", sessionName.getSession());
    SdpField sdpField = actualSdpMessage.v;
    assertEquals('v', sdpField.getType());
    OriginField origin = actualSdpMessage.getOrigin();
    assertEquals("- 0 0 IN IP4 42 Main St", origin.getValue());
    ConnectionField connection = actualSdpMessage.getConnection();
    assertEquals('c', connection.getType());
    assertEquals("IN IP4 42 Main St", connection.getValue());
    assertEquals("0", sdpField.getValue());
    assertEquals('o', origin.getType());
  }

  @Test
  void testConstructor6() {
    // Arrange and Act
    SdpMessage actualSdpMessage = new SdpMessage("Owner", null);

    // Assert
    Vector expectedAttributes = actualSdpMessage.media;
    Vector attributes = actualSdpMessage.getAttributes();
    assertEquals(expectedAttributes, attributes);
    assertEquals(attributes, actualSdpMessage.getMediaDescriptors());
    assertEquals("v=0\r\no=Owner 0 0 IN IP4 127.0.0.1\r\ns=-\r\nc=IN IP4 127.0.0.1\r\nt=0 0\r\n",
        actualSdpMessage.toString());
    assertNull(actualSdpMessage.bv);
    assertEquals(1, actualSdpMessage.tv.length);
    SessionNameField sessionName = actualSdpMessage.getSessionName();
    assertEquals('s', sessionName.getType());
    TimeField time = actualSdpMessage.getTime();
    assertEquals("0 0", time.getValue());
    assertEquals('t', time.getType());
    assertEquals("-", sessionName.getSession());
    SdpField sdpField = actualSdpMessage.v;
    assertEquals('v', sdpField.getType());
    OriginField origin = actualSdpMessage.getOrigin();
    assertEquals("Owner 0 0 IN IP4 127.0.0.1", origin.getValue());
    ConnectionField connection = actualSdpMessage.getConnection();
    assertEquals('c', connection.getType());
    assertEquals("IN IP4 127.0.0.1", connection.getValue());
    assertEquals("0", sdpField.getValue());
    assertEquals('o', origin.getType());
  }

  @Test
  void testConstructor7() {
    // Arrange and Act
    SdpMessage actualSdpMessage = new SdpMessage("Origin", "Session", "Connection", "Time");

    // Assert
    Vector expectedAttributes = actualSdpMessage.media;
    Vector attributes = actualSdpMessage.getAttributes();
    assertEquals(expectedAttributes, attributes);
    assertEquals(attributes, actualSdpMessage.getMediaDescriptors());
    assertEquals("v=0\r\no=Origin\r\ns=Session\r\nc=Connection\r\nt=Time\r\n", actualSdpMessage.toString());
    assertNull(actualSdpMessage.bv);
    assertEquals(1, actualSdpMessage.tv.length);
    SessionNameField sessionName = actualSdpMessage.getSessionName();
    assertEquals('s', sessionName.getType());
    TimeField time = actualSdpMessage.getTime();
    assertEquals("Time", time.getValue());
    assertEquals('t', time.getType());
    assertEquals("Session", sessionName.getSession());
    SdpField sdpField = actualSdpMessage.v;
    assertEquals('v', sdpField.getType());
    OriginField origin = actualSdpMessage.getOrigin();
    assertEquals("Origin", origin.getValue());
    ConnectionField connection = actualSdpMessage.getConnection();
    assertEquals('c', connection.getType());
    assertEquals("Connection", connection.getValue());
    assertEquals("0", sdpField.getValue());
    assertEquals('o', origin.getType());
  }

  @Test
  void testConstructor8() {
    // Arrange
    OriginField origin = new OriginField("Origin");
    SessionNameField session = new SessionNameField("Session name");
    ConnectionField connection = new ConnectionField("Connection field");

    // Act
    SdpMessage actualSdpMessage = new SdpMessage(origin, session, connection, new TimeField("Time field"));

    // Assert
    Vector expectedAttributes = actualSdpMessage.media;
    Vector attributes = actualSdpMessage.getAttributes();
    assertEquals(expectedAttributes, attributes);
    assertEquals(attributes, actualSdpMessage.getMediaDescriptors());
    assertEquals("v=0\r\no=Origin\r\ns=Session name\r\nc=Connection field\r\nt=Time field\r\n",
        actualSdpMessage.toString());
    assertNull(actualSdpMessage.bv);
    assertEquals(1, actualSdpMessage.tv.length);
    SdpField sdpField = actualSdpMessage.v;
    assertEquals('v', sdpField.getType());
    assertEquals("0", sdpField.getValue());
  }

  @Test
  void testConstructor9() {
    // Arrange
    OriginField origin = new OriginField("0");
    SessionNameField session = new SessionNameField("Session name");
    ConnectionField connection = new ConnectionField("Connection field");

    // Act
    SdpMessage actualSdpMessage = new SdpMessage(origin, session, connection, new TimeField("Time field"));

    // Assert
    Vector expectedAttributes = actualSdpMessage.media;
    Vector attributes = actualSdpMessage.getAttributes();
    assertEquals(expectedAttributes, attributes);
    assertEquals(attributes, actualSdpMessage.getMediaDescriptors());
    assertEquals("v=0\r\no=0\r\ns=Session name\r\nc=Connection field\r\nt=Time field\r\n", actualSdpMessage.toString());
    assertNull(actualSdpMessage.bv);
    assertEquals(1, actualSdpMessage.tv.length);
    SdpField sdpField = actualSdpMessage.v;
    assertEquals('v', sdpField.getType());
    assertEquals("0", sdpField.getValue());
  }

  @Test
  void testConstructor10() {
    // Arrange
    OriginField origin = new OriginField("janedoe", "42 Main St", "42 Main St");

    SessionNameField session = new SessionNameField("Session name");
    ConnectionField connection = new ConnectionField("Connection field");

    // Act
    SdpMessage actualSdpMessage = new SdpMessage(origin, session, connection, new TimeField("Time field"));

    // Assert
    Vector expectedAttributes = actualSdpMessage.media;
    Vector attributes = actualSdpMessage.getAttributes();
    assertEquals(expectedAttributes, attributes);
    assertEquals(attributes, actualSdpMessage.getMediaDescriptors());
    assertEquals(
        "v=0\r\no=janedoe 0 0 IN 42 Main St 42 Main St\r\ns=Session name\r\nc=Connection field\r\nt=Time field\r\n",
        actualSdpMessage.toString());
    assertNull(actualSdpMessage.bv);
    assertEquals(1, actualSdpMessage.tv.length);
    SdpField sdpField = actualSdpMessage.v;
    assertEquals('v', sdpField.getType());
    assertEquals("0", sdpField.getValue());
  }

  @Test
  void testConstructor11() {
    // Arrange
    OriginField origin = new OriginField("janedoe", "Sess id", "1.0.2", "42 Main St", "42 Main St");

    SessionNameField session = new SessionNameField("Session name");
    ConnectionField connection = new ConnectionField("Connection field");

    // Act
    SdpMessage actualSdpMessage = new SdpMessage(origin, session, connection, new TimeField("Time field"));

    // Assert
    Vector expectedAttributes = actualSdpMessage.media;
    Vector attributes = actualSdpMessage.getAttributes();
    assertEquals(expectedAttributes, attributes);
    assertEquals(attributes, actualSdpMessage.getMediaDescriptors());
    assertEquals("v=0\r\n" + "o=janedoe Sess id 1.0.2 IN 42 Main St 42 Main St\r\n" + "s=Session name\r\n"
        + "c=Connection field\r\n" + "t=Time field\r\n", actualSdpMessage.toString());
    assertNull(actualSdpMessage.bv);
    assertEquals(1, actualSdpMessage.tv.length);
    SdpField sdpField = actualSdpMessage.v;
    assertEquals('v', sdpField.getType());
    assertEquals("0", sdpField.getValue());
  }

  @Test
  void testConstructor12() {
    // Arrange
    OriginField origin = new OriginField(new SdpField('A', "42"));
    SessionNameField session = new SessionNameField("Session name");
    ConnectionField connection = new ConnectionField("Connection field");

    // Act
    SdpMessage actualSdpMessage = new SdpMessage(origin, session, connection, new TimeField("Time field"));

    // Assert
    Vector expectedAttributes = actualSdpMessage.media;
    Vector attributes = actualSdpMessage.getAttributes();
    assertEquals(expectedAttributes, attributes);
    assertEquals(attributes, actualSdpMessage.getMediaDescriptors());
    assertEquals("v=0\r\nA=42\r\ns=Session name\r\nc=Connection field\r\nt=Time field\r\n",
        actualSdpMessage.toString());
    assertNull(actualSdpMessage.bv);
    assertEquals(1, actualSdpMessage.tv.length);
    SdpField sdpField = actualSdpMessage.v;
    assertEquals('v', sdpField.getType());
    assertEquals("0", sdpField.getValue());
  }

  @Test
  void testConstructor13() {
    // Arrange
    SessionNameField session = new SessionNameField("Session name");
    ConnectionField connection = new ConnectionField("Connection field");

    // Act
    SdpMessage actualSdpMessage = new SdpMessage((OriginField) null, session, connection, new TimeField("Time field"));

    // Assert
    Vector expectedAttributes = actualSdpMessage.media;
    Vector attributes = actualSdpMessage.getAttributes();
    assertEquals(expectedAttributes, attributes);
    assertEquals(attributes, actualSdpMessage.getMediaDescriptors());
    assertEquals("v=0\r\ns=Session name\r\nc=Connection field\r\nt=Time field\r\n", actualSdpMessage.toString());
    assertNull(actualSdpMessage.bv);
    assertNull(actualSdpMessage.getOrigin());
    assertEquals(1, actualSdpMessage.tv.length);
    SdpField sdpField = actualSdpMessage.v;
    assertEquals("0", sdpField.getValue());
    assertEquals('v', sdpField.getType());
  }

  @Test
  void testConstructor14() {
    // Arrange
    OriginField origin = new OriginField("Origin");
    SessionNameField session = new SessionNameField();
    ConnectionField connection = new ConnectionField("Connection field");

    // Act
    SdpMessage actualSdpMessage = new SdpMessage(origin, session, connection, new TimeField("Time field"));

    // Assert
    Vector expectedAttributes = actualSdpMessage.media;
    Vector attributes = actualSdpMessage.getAttributes();
    assertEquals(expectedAttributes, attributes);
    assertEquals(attributes, actualSdpMessage.getMediaDescriptors());
    assertEquals("v=0\r\no=Origin\r\ns=-\r\nc=Connection field\r\nt=Time field\r\n", actualSdpMessage.toString());
    assertNull(actualSdpMessage.bv);
    assertEquals(1, actualSdpMessage.tv.length);
    SdpField sdpField = actualSdpMessage.v;
    assertEquals('v', sdpField.getType());
    assertEquals("0", sdpField.getValue());
  }

  @Test
  void testConstructor15() {
    // Arrange
    OriginField origin = new OriginField("Origin");
    SessionNameField session = new SessionNameField(new SdpField('A', "42"));
    ConnectionField connection = new ConnectionField("Connection field");

    // Act
    SdpMessage actualSdpMessage = new SdpMessage(origin, session, connection, new TimeField("Time field"));

    // Assert
    Vector expectedAttributes = actualSdpMessage.media;
    Vector attributes = actualSdpMessage.getAttributes();
    assertEquals(expectedAttributes, attributes);
    assertEquals(attributes, actualSdpMessage.getMediaDescriptors());
    assertEquals("v=0\r\no=Origin\r\nA=42\r\nc=Connection field\r\nt=Time field\r\n", actualSdpMessage.toString());
    assertNull(actualSdpMessage.bv);
    assertEquals(1, actualSdpMessage.tv.length);
    SdpField sdpField = actualSdpMessage.v;
    assertEquals('v', sdpField.getType());
    assertEquals("0", sdpField.getValue());
  }

  @Test
  void testConstructor16() {
    // Arrange
    OriginField origin = new OriginField("Origin");
    SessionNameField session = new SessionNameField("Session name");
    ConnectionField connection = new ConnectionField("42 Main St", "42 Main St");

    // Act
    SdpMessage actualSdpMessage = new SdpMessage(origin, session, connection, new TimeField("Time field"));

    // Assert
    Vector expectedAttributes = actualSdpMessage.media;
    Vector attributes = actualSdpMessage.getAttributes();
    assertEquals(expectedAttributes, attributes);
    assertEquals(attributes, actualSdpMessage.getMediaDescriptors());
    assertEquals("v=0\r\no=Origin\r\ns=Session name\r\nc=IN 42 Main St 42 Main St\r\nt=Time field\r\n",
        actualSdpMessage.toString());
    assertNull(actualSdpMessage.bv);
    assertEquals(1, actualSdpMessage.tv.length);
    SdpField sdpField = actualSdpMessage.v;
    assertEquals('v', sdpField.getType());
    assertEquals("0", sdpField.getValue());
  }

  @Test
  void testConstructor17() {
    // Arrange
    OriginField origin = new OriginField("Origin");
    SessionNameField session = new SessionNameField("Session name");
    ConnectionField connection = new ConnectionField("42 Main St", "42 Main St", 1, 10);

    // Act
    SdpMessage actualSdpMessage = new SdpMessage(origin, session, connection, new TimeField("Time field"));

    // Assert
    Vector expectedAttributes = actualSdpMessage.media;
    Vector attributes = actualSdpMessage.getAttributes();
    assertEquals(expectedAttributes, attributes);
    assertEquals(attributes, actualSdpMessage.getMediaDescriptors());
    assertEquals("v=0\r\no=Origin\r\ns=Session name\r\nc=IN 42 Main St 42 Main St/1/10\r\nt=Time field\r\n",
        actualSdpMessage.toString());
    assertNull(actualSdpMessage.bv);
    assertEquals(1, actualSdpMessage.tv.length);
    SdpField sdpField = actualSdpMessage.v;
    assertEquals('v', sdpField.getType());
    assertEquals("0", sdpField.getValue());
  }

  @Test
  void testConstructor18() {
    // Arrange
    OriginField origin = new OriginField("Origin");
    SessionNameField session = new SessionNameField("Session name");
    ConnectionField connection = new ConnectionField("Connection field");

    // Act
    SdpMessage actualSdpMessage = new SdpMessage(origin, session, connection, new TimeField("Start", "Stop"));

    // Assert
    Vector expectedAttributes = actualSdpMessage.media;
    Vector attributes = actualSdpMessage.getAttributes();
    assertEquals(expectedAttributes, attributes);
    assertEquals(attributes, actualSdpMessage.getMediaDescriptors());
    assertEquals("v=0\r\no=Origin\r\ns=Session name\r\nc=Connection field\r\nt=Start Stop\r\n",
        actualSdpMessage.toString());
    assertNull(actualSdpMessage.bv);
    assertEquals(1, actualSdpMessage.tv.length);
    SdpField sdpField = actualSdpMessage.v;
    assertEquals('v', sdpField.getType());
    assertEquals("0", sdpField.getValue());
  }

  @Test
  void testConstructor19() {
    // Arrange
    OriginField origin = new OriginField("Origin");
    SessionNameField session = new SessionNameField("Session name");
    ConnectionField connection = new ConnectionField("Connection field");

    // Act
    SdpMessage actualSdpMessage = new SdpMessage(origin, session, connection, new TimeField());

    // Assert
    Vector expectedAttributes = actualSdpMessage.media;
    Vector attributes = actualSdpMessage.getAttributes();
    assertEquals(expectedAttributes, attributes);
    assertEquals(attributes, actualSdpMessage.getMediaDescriptors());
    assertEquals("v=0\r\no=Origin\r\ns=Session name\r\nc=Connection field\r\nt=0 0\r\n", actualSdpMessage.toString());
    assertNull(actualSdpMessage.bv);
    assertEquals(1, actualSdpMessage.tv.length);
    SdpField sdpField = actualSdpMessage.v;
    assertEquals('v', sdpField.getType());
    assertEquals("0", sdpField.getValue());
  }

  @Test
  void testConstructor20() {
    // Arrange
    OriginField origin = new OriginField("", "42 Main St", "42 Main St");

    SessionNameField session = new SessionNameField("Session name");
    ConnectionField connection = new ConnectionField("Connection field");

    // Act
    SdpMessage actualSdpMessage = new SdpMessage(origin, session, connection, new TimeField("Time field"));

    // Assert
    Vector expectedAttributes = actualSdpMessage.media;
    Vector attributes = actualSdpMessage.getAttributes();
    assertEquals(expectedAttributes, attributes);
    assertEquals(attributes, actualSdpMessage.getMediaDescriptors());
    assertEquals("v=0\r\no=- 0 0 IN 42 Main St 42 Main St\r\ns=Session name\r\nc=Connection field\r\nt=Time field\r\n",
        actualSdpMessage.toString());
    assertNull(actualSdpMessage.bv);
    assertEquals(1, actualSdpMessage.tv.length);
    SdpField sdpField = actualSdpMessage.v;
    assertEquals('v', sdpField.getType());
    assertEquals("0", sdpField.getValue());
  }

  @Test
  void testConstructor21() {
    // Arrange
    OriginField origin = new OriginField("janedoe", "", "42 Main St");

    SessionNameField session = new SessionNameField("Session name");
    ConnectionField connection = new ConnectionField("Connection field");

    // Act
    SdpMessage actualSdpMessage = new SdpMessage(origin, session, connection, new TimeField("Time field"));

    // Assert
    Vector expectedAttributes = actualSdpMessage.media;
    Vector attributes = actualSdpMessage.getAttributes();
    assertEquals(expectedAttributes, attributes);
    assertEquals(attributes, actualSdpMessage.getMediaDescriptors());
    assertEquals("v=0\r\no=janedoe 0 0 IN IP4 42 Main St\r\ns=Session name\r\nc=Connection field\r\nt=Time field\r\n",
        actualSdpMessage.toString());
    assertNull(actualSdpMessage.bv);
    assertEquals(1, actualSdpMessage.tv.length);
    SdpField sdpField = actualSdpMessage.v;
    assertEquals('v', sdpField.getType());
    assertEquals("0", sdpField.getValue());
  }

  @Test
  void testConstructor22() {
    // Arrange
    OriginField origin = new OriginField("janedoe", "", "1.0.2", "42 Main St", "42 Main St");

    SessionNameField session = new SessionNameField("Session name");
    ConnectionField connection = new ConnectionField("Connection field");

    // Act
    SdpMessage actualSdpMessage = new SdpMessage(origin, session, connection, new TimeField("Time field"));

    // Assert
    Vector expectedAttributes = actualSdpMessage.media;
    Vector attributes = actualSdpMessage.getAttributes();
    assertEquals(expectedAttributes, attributes);
    assertEquals(attributes, actualSdpMessage.getMediaDescriptors());
    assertEquals(
        "v=0\r\no=janedoe 0 1.0.2 IN 42 Main St 42 Main St\r\ns=Session name\r\nc=Connection field\r\nt=Time field\r\n",
        actualSdpMessage.toString());
    assertNull(actualSdpMessage.bv);
    assertEquals(1, actualSdpMessage.tv.length);
    SdpField sdpField = actualSdpMessage.v;
    assertEquals('v', sdpField.getType());
    assertEquals("0", sdpField.getValue());
  }

  @Test
  void testConstructor23() {
    // Arrange
    OriginField origin = new OriginField("janedoe", "Sess id", "", "42 Main St", "42 Main St");

    SessionNameField session = new SessionNameField("Session name");
    ConnectionField connection = new ConnectionField("Connection field");

    // Act
    SdpMessage actualSdpMessage = new SdpMessage(origin, session, connection, new TimeField("Time field"));

    // Assert
    Vector expectedAttributes = actualSdpMessage.media;
    Vector attributes = actualSdpMessage.getAttributes();
    assertEquals(expectedAttributes, attributes);
    assertEquals(attributes, actualSdpMessage.getMediaDescriptors());
    assertEquals("v=0\r\n" + "o=janedoe Sess id 0 IN 42 Main St 42 Main St\r\n" + "s=Session name\r\n"
        + "c=Connection field\r\n" + "t=Time field\r\n", actualSdpMessage.toString());
    assertNull(actualSdpMessage.bv);
    assertEquals(1, actualSdpMessage.tv.length);
    SdpField sdpField = actualSdpMessage.v;
    assertEquals('v', sdpField.getType());
    assertEquals("0", sdpField.getValue());
  }

  @Test
  void testConstructor24() {
    // Arrange
    SessionNameField session = new SessionNameField();
    ConnectionField connection = new ConnectionField("Connection field");

    // Act
    SdpMessage actualSdpMessage = new SdpMessage((OriginField) null, session, connection, new TimeField("Time field"));

    // Assert
    Vector expectedAttributes = actualSdpMessage.media;
    Vector attributes = actualSdpMessage.getAttributes();
    assertEquals(expectedAttributes, attributes);
    assertEquals(attributes, actualSdpMessage.getMediaDescriptors());
    assertEquals("v=0\r\ns=-\r\nc=Connection field\r\nt=Time field\r\n", actualSdpMessage.toString());
    assertNull(actualSdpMessage.bv);
    assertNull(actualSdpMessage.getOrigin());
    assertEquals(1, actualSdpMessage.tv.length);
    SdpField sdpField = actualSdpMessage.v;
    assertEquals("0", sdpField.getValue());
    assertEquals('v', sdpField.getType());
  }

  @Test
  void testConstructor25() {
    // Arrange
    ConnectionField connection = new ConnectionField("Connection field");

    // Act
    SdpMessage actualSdpMessage = new SdpMessage((OriginField) null, null, connection, new TimeField("Time field"));

    // Assert
    Vector expectedAttributes = actualSdpMessage.media;
    Vector attributes = actualSdpMessage.getAttributes();
    assertEquals(expectedAttributes, attributes);
    assertEquals(attributes, actualSdpMessage.getMediaDescriptors());
    assertEquals("v=0\r\nc=Connection field\r\nt=Time field\r\n", actualSdpMessage.toString());
    assertNull(actualSdpMessage.bv);
    assertNull(actualSdpMessage.getOrigin());
    assertNull(actualSdpMessage.getSessionName());
    assertEquals(1, actualSdpMessage.tv.length);
    SdpField sdpField = actualSdpMessage.v;
    assertEquals("0", sdpField.getValue());
    assertEquals('v', sdpField.getType());
  }

  @Test
  void testConstructor26() {
    // Arrange
    OriginField origin = new OriginField("Origin");
    SessionNameField session = new SessionNameField("Session name");
    ConnectionField connection = new ConnectionField("", "42 Main St");

    // Act
    SdpMessage actualSdpMessage = new SdpMessage(origin, session, connection, new TimeField("Time field"));

    // Assert
    Vector expectedAttributes = actualSdpMessage.media;
    Vector attributes = actualSdpMessage.getAttributes();
    assertEquals(expectedAttributes, attributes);
    assertEquals(attributes, actualSdpMessage.getMediaDescriptors());
    assertEquals("v=0\r\no=Origin\r\ns=Session name\r\nc=IN IP4 42 Main St\r\nt=Time field\r\n",
        actualSdpMessage.toString());
    assertNull(actualSdpMessage.bv);
    assertEquals(1, actualSdpMessage.tv.length);
    SdpField sdpField = actualSdpMessage.v;
    assertEquals('v', sdpField.getType());
    assertEquals("0", sdpField.getValue());
  }

  @Test
  void testConstructor27() {
    // Arrange
    ConnectionField connection = new ConnectionField("42 Main St", "42 Main St");

    // Act
    SdpMessage actualSdpMessage = new SdpMessage((OriginField) null, null, connection, new TimeField("Time field"));

    // Assert
    Vector expectedAttributes = actualSdpMessage.media;
    Vector attributes = actualSdpMessage.getAttributes();
    assertEquals(expectedAttributes, attributes);
    assertEquals(attributes, actualSdpMessage.getMediaDescriptors());
    assertEquals("v=0\r\nc=IN 42 Main St 42 Main St\r\nt=Time field\r\n", actualSdpMessage.toString());
    assertNull(actualSdpMessage.bv);
    assertNull(actualSdpMessage.getOrigin());
    assertNull(actualSdpMessage.getSessionName());
    assertEquals(1, actualSdpMessage.tv.length);
    SdpField sdpField = actualSdpMessage.v;
    assertEquals("0", sdpField.getValue());
    assertEquals('v', sdpField.getType());
  }

  @Test
  void testConstructor28() {
    // Arrange
    ConnectionField connection = new ConnectionField("42 Main St", "42 Main St", 1, 10);

    // Act
    SdpMessage actualSdpMessage = new SdpMessage((OriginField) null, null, connection, new TimeField("Time field"));

    // Assert
    Vector expectedAttributes = actualSdpMessage.media;
    Vector attributes = actualSdpMessage.getAttributes();
    assertEquals(expectedAttributes, attributes);
    assertEquals(attributes, actualSdpMessage.getMediaDescriptors());
    assertEquals("v=0\r\nc=IN 42 Main St 42 Main St/1/10\r\nt=Time field\r\n", actualSdpMessage.toString());
    assertNull(actualSdpMessage.bv);
    assertNull(actualSdpMessage.getOrigin());
    assertNull(actualSdpMessage.getSessionName());
    assertEquals(1, actualSdpMessage.tv.length);
    SdpField sdpField = actualSdpMessage.v;
    assertEquals("0", sdpField.getValue());
    assertEquals('v', sdpField.getType());
  }
}

