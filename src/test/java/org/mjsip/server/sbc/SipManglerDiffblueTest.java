package org.mjsip.server.sbc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.io.UnsupportedEncodingException;
import java.util.Vector;
import org.junit.jupiter.api.Test;
import org.mjsip.sdp.SdpMessage;
import org.mjsip.sdp.field.ConnectionField;
import org.mjsip.sip.address.GenericURI;
import org.mjsip.sip.address.NameAddress;
import org.mjsip.sip.header.ContactHeader;
import org.mjsip.sip.header.MultipleHeader;
import org.mjsip.sip.header.StatusLine;
import org.mjsip.sip.message.SipMessage;

class SipManglerDiffblueTest {
  @Test
  void testMangleContact() {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.addRoutes(new MultipleHeader("Contact"));

    // Act
    SipMessage actualMangleContactResult = SipMangler.mangleContact(sipMessage, "localhost", 8080);

    // Assert
    assertNull(actualMangleContactResult.getAcceptEncodingHeader());
    assertEquals(43, actualMangleContactResult.getLength());
  }

  @Test
  void testUnmangleContact() {
    // Arrange
    SipMessage sipMessage = mock(SipMessage.class);
    when(sipMessage.getContactHeader()).thenReturn(new ContactHeader());
    when(sipMessage.hasContactHeader()).thenReturn(true);

    // Act
    SipMangler.unmangleContact(sipMessage);

    // Assert
    verify(sipMessage).getContactHeader();
    verify(sipMessage).hasContactHeader();
  }

  @Test
  void testUnmangleContact2() {
    // Arrange
    SipMessage sipMessage = mock(SipMessage.class);
    when(sipMessage.getContactHeader()).thenReturn(new ContactHeader(new NameAddress("Str")));
    when(sipMessage.hasContactHeader()).thenReturn(true);

    // Act
    SipMangler.unmangleContact(sipMessage);

    // Assert
    verify(sipMessage).getContactHeader();
    verify(sipMessage).hasContactHeader();
  }

  @Test
  void testUnmangleContact3() {
    // Arrange
    SipMessage sipMessage = mock(SipMessage.class);
    when(sipMessage.getContactHeader()).thenReturn(new ContactHeader(new GenericURI("Uri")));
    when(sipMessage.hasContactHeader()).thenReturn(true);

    // Act
    SipMangler.unmangleContact(sipMessage);

    // Assert
    verify(sipMessage).getContactHeader();
    verify(sipMessage).hasContactHeader();
  }

  @Test
  void testUnmangleContact4() {
    // Arrange
    ContactHeader contactHeader = mock(ContactHeader.class);
    when(contactHeader.isStar()).thenReturn(true);
    SipMessage sipMessage = mock(SipMessage.class);
    when(sipMessage.getContactHeader()).thenReturn(contactHeader);
    when(sipMessage.hasContactHeader()).thenReturn(true);

    // Act
    SipMangler.unmangleContact(sipMessage);

    // Assert
    verify(sipMessage).getContactHeader();
    verify(sipMessage).hasContactHeader();
    verify(contactHeader).isStar();
  }

  @Test
  void testUnmangleContact5() {
    // Arrange
    SipMessage sipMessage = mock(SipMessage.class);
    when(sipMessage.getContactHeader())
        .thenReturn(new ContactHeader(new NameAddress("Display name", new GenericURI("Uri"))));
    when(sipMessage.hasContactHeader()).thenReturn(true);

    // Act
    SipMangler.unmangleContact(sipMessage);

    // Assert
    verify(sipMessage).getContactHeader();
    verify(sipMessage).hasContactHeader();
  }

  @Test
  void testUnmangleContact6() {
    // Arrange
    SipMessage sipMessage = mock(SipMessage.class);
    when(sipMessage.getContactHeader()).thenReturn(new ContactHeader(new GenericURI("sips:")));
    when(sipMessage.hasContactHeader()).thenReturn(true);

    // Act
    SipMangler.unmangleContact(sipMessage);

    // Assert
    verify(sipMessage).getContactHeader();
    verify(sipMessage).hasContactHeader();
  }

  @Test
  void testUnmangleContact7() {
    // Arrange
    SipMessage sipMessage = mock(SipMessage.class);
    when(sipMessage.getContactHeader()).thenReturn(new ContactHeader(new GenericURI(new GenericURI("Uri"))));
    when(sipMessage.hasContactHeader()).thenReturn(true);

    // Act
    SipMangler.unmangleContact(sipMessage);

    // Assert
    verify(sipMessage).getContactHeader();
    verify(sipMessage).hasContactHeader();
  }

  @Test
  void testUnmangleContact8() {
    // Arrange
    GenericURI genericURI = new GenericURI((String) null);
    genericURI.addParameter("Name");
    SipMessage sipMessage = mock(SipMessage.class);
    when(sipMessage.getContactHeader()).thenReturn(new ContactHeader(genericURI));
    when(sipMessage.hasContactHeader()).thenReturn(true);

    // Act
    SipMangler.unmangleContact(sipMessage);

    // Assert
    verify(sipMessage).getContactHeader();
    verify(sipMessage).hasContactHeader();
  }

  @Test
  void testUnmangleContact9() {
    // Arrange
    GenericURI genericURI = new GenericURI((String) null);
    genericURI.addParameter("Name", "42");
    SipMessage sipMessage = mock(SipMessage.class);
    when(sipMessage.getContactHeader()).thenReturn(new ContactHeader(genericURI));
    when(sipMessage.hasContactHeader()).thenReturn(true);

    // Act
    SipMangler.unmangleContact(sipMessage);

    // Assert
    verify(sipMessage).getContactHeader();
    verify(sipMessage).hasContactHeader();
  }

  @Test
  void testUnmangleContact10() {
    // Arrange
    SipMessage sipMessage = mock(SipMessage.class);
    when(sipMessage.getContactHeader()).thenReturn(new ContactHeader(new GenericURI("")));
    when(sipMessage.hasContactHeader()).thenReturn(true);

    // Act
    SipMangler.unmangleContact(sipMessage);

    // Assert
    verify(sipMessage).getContactHeader();
    verify(sipMessage).hasContactHeader();
  }

  @Test
  void testUnmangleContact11() {
    // Arrange
    GenericURI genericURI = new GenericURI((String) null);
    genericURI.addLr();
    genericURI.addParameter("Name");
    SipMessage sipMessage = mock(SipMessage.class);
    when(sipMessage.getContactHeader()).thenReturn(new ContactHeader(genericURI));
    when(sipMessage.hasContactHeader()).thenReturn(true);

    // Act
    SipMangler.unmangleContact(sipMessage);

    // Assert
    verify(sipMessage).getContactHeader();
    verify(sipMessage).hasContactHeader();
  }

  @Test
  void testUnmangleContact12() {
    // Arrange
    NameAddress nameAddress = new NameAddress("Str");
    nameAddress.setDisplayName("sip");
    SipMessage sipMessage = mock(SipMessage.class);
    when(sipMessage.getContactHeader()).thenReturn(new ContactHeader(nameAddress));
    when(sipMessage.hasContactHeader()).thenReturn(true);

    // Act
    SipMangler.unmangleContact(sipMessage);

    // Assert
    verify(sipMessage).getContactHeader();
    verify(sipMessage).hasContactHeader();
  }

  @Test
  void testUnmangleContact13() {
    // Arrange
    SipMessage sipMessage = mock(SipMessage.class);
    when(sipMessage.getContactHeader()).thenReturn(new ContactHeader(new NameAddress("sips:", new GenericURI("Uri"))));
    when(sipMessage.hasContactHeader()).thenReturn(true);

    // Act
    SipMangler.unmangleContact(sipMessage);

    // Assert
    verify(sipMessage).getContactHeader();
    verify(sipMessage).hasContactHeader();
  }

  @Test
  void testUnmangleContact14() {
    // Arrange
    GenericURI genericURI = new GenericURI((String) null);
    genericURI.addLr();
    genericURI.addParameter("tag");
    SipMessage sipMessage = mock(SipMessage.class);
    when(sipMessage.getContactHeader()).thenReturn(new ContactHeader(genericURI));
    when(sipMessage.hasContactHeader()).thenReturn(true);

    // Act
    SipMangler.unmangleContact(sipMessage);

    // Assert
    verify(sipMessage).getContactHeader();
    verify(sipMessage).hasContactHeader();
  }

  @Test
  void testMangleSdpConnection() {
    // Arrange and Act
    SdpMessage actualMangleSdpConnectionResult = SipMangler.mangleSdpConnection(new SdpMessage(), "42 Main St");

    // Assert
    assertEquals("v=0\r\no=- 0 0 IN IP4 127.0.0.1\r\ns=-\r\nc=IN IP4 42 Main St\r\nt=0 0\r\n",
        actualMangleSdpConnectionResult.toString());
    ConnectionField connection = actualMangleSdpConnectionResult.getConnection();
    assertEquals('c', connection.getType());
    assertEquals("IN IP4 42 Main St", connection.getValue());
  }

  @Test
  void testMangleSdpConnection2() {
    // Arrange
    SdpMessage sdpMessage = mock(SdpMessage.class);
    when(sdpMessage.setConnection((ConnectionField) any())).thenReturn(new SdpMessage());
    when(sdpMessage.getConnection()).thenReturn(new ConnectionField("Connection field"));

    // Act
    SipMangler.mangleSdpConnection(sdpMessage, "42 Main St");

    // Assert
    verify(sdpMessage, atLeast(1)).getConnection();
    verify(sdpMessage).setConnection((ConnectionField) any());
  }

  @Test
  void testMangleSdpConnection3() {
    // Arrange
    SdpMessage sdpMessage = mock(SdpMessage.class);
    when(sdpMessage.setConnection((ConnectionField) any())).thenReturn(new SdpMessage());
    when(sdpMessage.getConnection()).thenReturn(new ConnectionField("/"));

    // Act
    SipMangler.mangleSdpConnection(sdpMessage, "42 Main St");

    // Assert
    verify(sdpMessage, atLeast(1)).getConnection();
    verify(sdpMessage).setConnection((ConnectionField) any());
  }

  @Test
  void testMangleSdpConnection4() {
    // Arrange
    ConnectionField connectionField = mock(ConnectionField.class);
    when(connectionField.getNum()).thenReturn(10);
    when(connectionField.getTTL()).thenReturn(1);
    when(connectionField.getAddressType()).thenReturn("42 Main St");
    when(connectionField.getAddress()).thenReturn("42 Main St");
    SdpMessage sdpMessage = mock(SdpMessage.class);
    when(sdpMessage.setConnection((ConnectionField) any())).thenReturn(new SdpMessage());
    when(sdpMessage.getConnection()).thenReturn(connectionField);

    // Act
    SipMangler.mangleSdpConnection(sdpMessage, "42 Main St");

    // Assert
    verify(sdpMessage, atLeast(1)).getConnection();
    verify(sdpMessage).setConnection((ConnectionField) any());
    verify(connectionField).getAddress();
    verify(connectionField).getAddressType();
    verify(connectionField).getNum();
    verify(connectionField).getTTL();
  }

  @Test
  void testMangleBody() throws UnsupportedEncodingException {
    // Arrange
    StatusLine status_line = new StatusLine(1, "foo");

    Vector vector = new Vector(1);

    // Act
    SipMessage actualMangleBodyResult = SipMangler.mangleBody(
        new SipMessage(status_line, vector, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8")), "42 Main St",
        new String[]{"Media"}, new int[]{8080, 8080, 8080, 8080});

    // Assert
    assertNull(actualMangleBodyResult.getAcceptEncodingHeader());
    assertEquals(110, actualMangleBodyResult.getLength());
    assertEquals(vector, actualMangleBodyResult.getHeaders());
    assertEquals("application/sdp", actualMangleBodyResult.getBodyType());
    assertEquals(42, actualMangleBodyResult.getBody().length);
  }

  @Test
  void testMangleBody2() throws UnsupportedEncodingException {
    // Arrange
    SipMessage sipMessage = new SipMessage("Str");
    sipMessage.setBody("Not all who wander are lost", "Not all who wander are lost", "AAAAAAAA".getBytes("UTF-8"));

    // Act
    SipMessage actualMangleBodyResult = SipMangler.mangleBody(sipMessage, "42 Main St", new String[]{"Media"},
        new int[]{8080, 8080, 8080, 8080});

    // Assert
    assertNull(actualMangleBodyResult.getAcceptEncodingHeader());
    assertEquals(95, actualMangleBodyResult.getLength());
    assertEquals("application/sdp", actualMangleBodyResult.getBodyType());
    assertEquals(42, actualMangleBodyResult.getBody().length);
  }
}

