package org.mjsip.ua;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.UnsupportedEncodingException;
import java.util.Vector;
import org.junit.jupiter.api.Test;
import org.mjsip.sip.address.SipURI;
import org.mjsip.sip.header.StatusLine;
import org.mjsip.sip.message.SipMessage;
import org.mjsip.sip.provider.SipProvider;
import org.mjsip.ua.cli.MessageAgentCli;

class MessageAgentDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    UserAgentProfile user_profile = new UserAgentProfile();
    SipProvider sip_provider1 = new SipProvider("File");

    // Act
    MessageAgent actualMessageAgent = new MessageAgent(sip_provider, user_profile,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));

    // Assert
    SipProvider sipProvider = actualMessageAgent.sip_provider;
    assertEquals("5060/", sipProvider.toString());
    assertTrue(sipProvider.isRportSet());
    assertFalse(sipProvider.isForceRportSet());
    assertTrue(sipProvider.isAllInterfaces());
    assertFalse(sipProvider.hasTelGateway());
    assertFalse(sipProvider.hasSecureTransport());
    assertFalse(sipProvider.hasOutboundProxy());

    assertEquals(0, sipProvider.getTlsPort());
    assertNull(sipProvider.getTelGateway());
    assertEquals(5060, sipProvider.getPort());
    assertNull(sipProvider.getOutboundProxy());
    assertEquals(Integer.SIZE, sipProvider.getNMaxConnections());
    assertNull(sipProvider.getLogger());
    assertEquals("udp", sipProvider.getDefaultTransport());
    assertNull(sipProvider.getBindingIpAddress());
    UserAgentProfile userAgentProfile = actualMessageAgent.user_profile;
    assertEquals(4080, userAgentProfile.getMediaPort());

  }

  @Test
  void testConstructor2() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    UserAgentProfile user_profile = new UserAgentProfile();
    SipProvider sip_provider1 = new SipProvider("File");

    // Act
    MessageAgent actualMessageAgent = new MessageAgent(sip_provider, user_profile,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));

    // Assert
    assertTrue(actualMessageAgent.listener instanceof MessageAgentCli);
    assertNull(actualMessageAgent.logger);
    UserAgentProfile userAgentProfile = actualMessageAgent.user_profile;
    assertNull(userAgentProfile.user);

  }

  @Test
  void testConstructor3() {
    // Arrange
    SipProvider sip_provider = new SipProvider("42 Main St", 8080);

    UserAgentProfile user_profile = new UserAgentProfile();
    SipProvider sip_provider1 = new SipProvider("File");

    // Act
    MessageAgent actualMessageAgent = new MessageAgent(sip_provider, user_profile,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));

    // Assert
    assertTrue(actualMessageAgent.listener instanceof MessageAgentCli);
    assertNull(actualMessageAgent.logger);
    UserAgentProfile userAgentProfile = actualMessageAgent.user_profile;
    assertNull(userAgentProfile.user);
    assertEquals("<sip:42 Main St:8080>", userAgentProfile.toLines());
  }

  @Test
  void testSend() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    UserAgentProfile user_profile = new UserAgentProfile();
    SipProvider sip_provider1 = new SipProvider("File");
    MessageAgent messageAgent = new MessageAgent(sip_provider, user_profile,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));

    // Act
    messageAgent.send("Recipient", "Hello from the Dreaming Spires", "Not all who wander are lost");

    // Assert
    assertEquals(1, messageAgent.sip_provider.getListeners().size());
  }

  @Test
  void testSend2() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    UserAgentProfile user_profile = new UserAgentProfile();
    SipProvider sip_provider1 = new SipProvider("File");
    MessageAgent messageAgent = new MessageAgent(sip_provider, user_profile,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));

    // Act
    messageAgent.send("sips:", "Hello from the Dreaming Spires", "Not all who wander are lost");

    // Assert
    assertEquals(1, messageAgent.sip_provider.getListeners().size());
  }

  @Test
  void testSend3() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    UserAgentProfile user_profile = new UserAgentProfile();
    SipProvider sip_provider1 = new SipProvider("File");
    MessageAgent messageAgent = new MessageAgent(sip_provider, user_profile,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));

    // Act
    messageAgent.send("", "Hello from the Dreaming Spires", "Not all who wander are lost");

    // Assert
    assertEquals(1, messageAgent.sip_provider.getListeners().size());
  }

  @Test
  void testSend4() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    UserAgentProfile user_profile = new UserAgentProfile();
    SipProvider sip_provider1 = new SipProvider("File");
    MessageAgent messageAgent = new MessageAgent(sip_provider, user_profile,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));

    // Act
    messageAgent.send("Recipient", "Hello from the Dreaming Spires", "");

    // Assert
    assertEquals(1, messageAgent.sip_provider.getListeners().size());
  }

  @Test
  void testSend5() {
    // Arrange
    SipProvider sipProvider = new SipProvider("File");
    sipProvider.setOutboundProxy(new SipURI("Uri"));
    UserAgentProfile user_profile = new UserAgentProfile();
    SipProvider sip_provider = new SipProvider("File");
    MessageAgent messageAgent = new MessageAgent(sipProvider, user_profile,
        new MessageAgentCli(sip_provider, new UserAgentProfile()));

    // Act
    messageAgent.send("Recipient", "Hello from the Dreaming Spires", "Not all who wander are lost");

    // Assert
    assertEquals(1, messageAgent.sip_provider.getListeners().size());
  }

  @Test
  void testSend6() {
    // Arrange
    SipProvider sipProvider = new SipProvider("File");
    sipProvider.setOutboundProxy(new SipURI("localhost", 10));
    UserAgentProfile user_profile = new UserAgentProfile();
    SipProvider sip_provider = new SipProvider("File");
    MessageAgent messageAgent = new MessageAgent(sipProvider, user_profile,
        new MessageAgentCli(sip_provider, new UserAgentProfile()));

    // Act
    messageAgent.send("Recipient", "Hello from the Dreaming Spires", "Not all who wander are lost");

    // Assert
    assertEquals(1, messageAgent.sip_provider.getListeners().size());
  }

  @Test
  void testSend7() {
    // Arrange
    SipProvider sipProvider = new SipProvider("File");
    sipProvider.setOutboundProxy(new SipURI("janedoe", "localhost"));
    UserAgentProfile user_profile = new UserAgentProfile();
    SipProvider sip_provider = new SipProvider("File");
    MessageAgent messageAgent = new MessageAgent(sipProvider, user_profile,
        new MessageAgentCli(sip_provider, new UserAgentProfile()));

    // Act
    messageAgent.send("Recipient", "Hello from the Dreaming Spires", "Not all who wander are lost");

    // Assert
    assertEquals(1, messageAgent.sip_provider.getListeners().size());
  }

  @Test
  void testSend8() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addMaddr("42 Main St");

    SipProvider sipProvider = new SipProvider("File");
    sipProvider.setOutboundProxy(sipURI);
    UserAgentProfile user_profile = new UserAgentProfile();
    SipProvider sip_provider = new SipProvider("File");
    MessageAgent messageAgent = new MessageAgent(sipProvider, user_profile,
        new MessageAgentCli(sip_provider, new UserAgentProfile()));

    // Act
    messageAgent.send("Recipient", "Hello from the Dreaming Spires", "Not all who wander are lost");

    // Assert
    assertEquals(1, messageAgent.sip_provider.getListeners().size());
  }

  @Test
  void testSend9() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addParameter("application/text");

    SipProvider sipProvider = new SipProvider("File");
    sipProvider.setOutboundProxy(sipURI);
    UserAgentProfile user_profile = new UserAgentProfile();
    SipProvider sip_provider = new SipProvider("File");
    MessageAgent messageAgent = new MessageAgent(sipProvider, user_profile,
        new MessageAgentCli(sip_provider, new UserAgentProfile()));

    // Act
    messageAgent.send("Recipient", "Hello from the Dreaming Spires", "Not all who wander are lost");

    // Assert
    assertEquals(1, messageAgent.sip_provider.getListeners().size());
  }

  @Test
  void testSend10() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addTransport("alice.liddell@example.org");

    SipProvider sipProvider = new SipProvider("File");
    sipProvider.setOutboundProxy(sipURI);
    UserAgentProfile user_profile = new UserAgentProfile();
    SipProvider sip_provider = new SipProvider("File");
    MessageAgent messageAgent = new MessageAgent(sipProvider, user_profile,
        new MessageAgentCli(sip_provider, new UserAgentProfile()));

    // Act
    messageAgent.send("Recipient", "Hello from the Dreaming Spires", "Not all who wander are lost");

    // Assert
    assertEquals(1, messageAgent.sip_provider.getListeners().size());
  }

  @Test
  void testSend11() {
    // Arrange
    SipURI sipURI = new SipURI("localhost", 10);
    sipURI.addMaddr("42 Main St");

    SipProvider sipProvider = new SipProvider("File");
    sipProvider.setOutboundProxy(sipURI);
    UserAgentProfile user_profile = new UserAgentProfile();
    SipProvider sip_provider = new SipProvider("File");
    MessageAgent messageAgent = new MessageAgent(sipProvider, user_profile,
        new MessageAgentCli(sip_provider, new UserAgentProfile()));

    // Act
    messageAgent.send("Recipient", "Hello from the Dreaming Spires", "Not all who wander are lost");

    // Assert
    assertEquals(1, messageAgent.sip_provider.getListeners().size());
  }

  @Test
  void testSend12() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addMaddr("42 Main St");
    sipURI.addMaddr("42 Main St");

    SipProvider sipProvider = new SipProvider("File");
    sipProvider.setOutboundProxy(sipURI);
    UserAgentProfile user_profile = new UserAgentProfile();
    SipProvider sip_provider = new SipProvider("File");
    MessageAgent messageAgent = new MessageAgent(sipProvider, user_profile,
        new MessageAgentCli(sip_provider, new UserAgentProfile()));

    // Act
    messageAgent.send("Recipient", "Hello from the Dreaming Spires", "Not all who wander are lost");

    // Assert
    assertEquals(1, messageAgent.sip_provider.getListeners().size());
  }

  @Test
  void testSend13() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addParameter("application/text");
    sipURI.addMaddr("42 Main St");

    SipProvider sipProvider = new SipProvider("File");
    sipProvider.setOutboundProxy(sipURI);
    UserAgentProfile user_profile = new UserAgentProfile();
    SipProvider sip_provider = new SipProvider("File");
    MessageAgent messageAgent = new MessageAgent(sipProvider, user_profile,
        new MessageAgentCli(sip_provider, new UserAgentProfile()));

    // Act
    messageAgent.send("Recipient", "Hello from the Dreaming Spires", "Not all who wander are lost");

    // Assert
    assertEquals(1, messageAgent.sip_provider.getListeners().size());
  }

  @Test
  void testSend14() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addTtl(2);
    sipURI.addMaddr("42 Main St");

    SipProvider sipProvider = new SipProvider("File");
    sipProvider.setOutboundProxy(sipURI);
    UserAgentProfile user_profile = new UserAgentProfile();
    SipProvider sip_provider = new SipProvider("File");
    MessageAgent messageAgent = new MessageAgent(sipProvider, user_profile,
        new MessageAgentCli(sip_provider, new UserAgentProfile()));

    // Act
    messageAgent.send("Recipient", "Hello from the Dreaming Spires", "Not all who wander are lost");

    // Assert
    assertEquals(1, messageAgent.sip_provider.getListeners().size());
  }

  @Test
  void testSend15() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addMaddr("MESSAGE");

    SipProvider sipProvider = new SipProvider("File");
    sipProvider.setOutboundProxy(sipURI);
    UserAgentProfile user_profile = new UserAgentProfile();
    SipProvider sip_provider = new SipProvider("File");
    MessageAgent messageAgent = new MessageAgent(sipProvider, user_profile,
        new MessageAgentCli(sip_provider, new UserAgentProfile()));

    // Act
    messageAgent.send("Recipient", "Hello from the Dreaming Spires", "Not all who wander are lost");

    // Assert
    assertEquals(1, messageAgent.sip_provider.getListeners().size());
  }

  @Test
  void testSend16() {
    // Arrange
    SipURI sipURI = new SipURI("janedoe", "localhost", 10, true);
    sipURI.addMaddr("sips:");

    SipProvider sipProvider = new SipProvider("File");
    sipProvider.setOutboundProxy(sipURI);
    UserAgentProfile user_profile = new UserAgentProfile();
    SipProvider sip_provider = new SipProvider("File");
    MessageAgent messageAgent = new MessageAgent(sipProvider, user_profile,
        new MessageAgentCli(sip_provider, new UserAgentProfile()));

    // Act
    messageAgent.send("Recipient", "Hello from the Dreaming Spires", "Not all who wander are lost");

    // Assert
    assertEquals(1, messageAgent.sip_provider.getListeners().size());
  }

  @Test
  void testSend17() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addMaddr("");

    SipProvider sipProvider = new SipProvider("File");
    sipProvider.setOutboundProxy(sipURI);
    UserAgentProfile user_profile = new UserAgentProfile();
    SipProvider sip_provider = new SipProvider("File");
    MessageAgent messageAgent = new MessageAgent(sipProvider, user_profile,
        new MessageAgentCli(sip_provider, new UserAgentProfile()));

    // Act
    messageAgent.send("Recipient", "Hello from the Dreaming Spires", "Not all who wander are lost");

    // Assert
    assertEquals(1, messageAgent.sip_provider.getListeners().size());
  }

  @Test
  void testSend18() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addTransport("");

    SipProvider sipProvider = new SipProvider("File");
    sipProvider.setOutboundProxy(sipURI);
    UserAgentProfile user_profile = new UserAgentProfile();
    SipProvider sip_provider = new SipProvider("File");
    MessageAgent messageAgent = new MessageAgent(sipProvider, user_profile,
        new MessageAgentCli(sip_provider, new UserAgentProfile()));

    // Act
    messageAgent.send("Recipient", "Hello from the Dreaming Spires", "Not all who wander are lost");

    // Assert
    assertEquals(1, messageAgent.sip_provider.getListeners().size());
  }

  @Test
  void testSend19() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addParameter("", "42");

    SipProvider sipProvider = new SipProvider("File");
    sipProvider.setOutboundProxy(sipURI);
    UserAgentProfile user_profile = new UserAgentProfile();
    SipProvider sip_provider = new SipProvider("File");
    MessageAgent messageAgent = new MessageAgent(sipProvider, user_profile,
        new MessageAgentCli(sip_provider, new UserAgentProfile()));

    // Act
    messageAgent.send("Recipient", "Hello from the Dreaming Spires", "Not all who wander are lost");

    // Assert
    assertEquals(1, messageAgent.sip_provider.getListeners().size());
  }

  @Test
  void testSend20() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addMaddr("MESSAGE");
    sipURI.addMaddr("42 Main St");

    SipProvider sipProvider = new SipProvider("File");
    sipProvider.setOutboundProxy(sipURI);
    UserAgentProfile user_profile = new UserAgentProfile();
    SipProvider sip_provider = new SipProvider("File");
    MessageAgent messageAgent = new MessageAgent(sipProvider, user_profile,
        new MessageAgentCli(sip_provider, new UserAgentProfile()));

    // Act
    messageAgent.send("Recipient", "Hello from the Dreaming Spires", "Not all who wander are lost");

    // Assert
    assertEquals(1, messageAgent.sip_provider.getListeners().size());
  }

  @Test
  void testSend21() throws UnsupportedEncodingException {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    UserAgentProfile user_profile = new UserAgentProfile();
    SipProvider sip_provider1 = new SipProvider("File");
    MessageAgent messageAgent = new MessageAgent(sip_provider, user_profile,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));

    // Act
    messageAgent.send("Recipient", "Hello from the Dreaming Spires", "Not all who wander are lost",
        "AAAAAAAA".getBytes("UTF-8"));

    // Assert
    assertEquals(1, messageAgent.sip_provider.getListeners().size());
  }

  @Test
  void testSend22() throws UnsupportedEncodingException {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    UserAgentProfile user_profile = new UserAgentProfile();
    SipProvider sip_provider1 = new SipProvider("File");
    MessageAgent messageAgent = new MessageAgent(sip_provider, user_profile,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));

    // Act
    messageAgent.send("sips:", "Hello from the Dreaming Spires", "Not all who wander are lost",
        "AAAAAAAA".getBytes("UTF-8"));

    // Assert
    assertEquals(1, messageAgent.sip_provider.getListeners().size());
  }

  @Test
  void testSend23() throws UnsupportedEncodingException {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    UserAgentProfile user_profile = new UserAgentProfile();
    SipProvider sip_provider1 = new SipProvider("File");
    MessageAgent messageAgent = new MessageAgent(sip_provider, user_profile,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));

    // Act
    messageAgent.send("", "Hello from the Dreaming Spires", "Not all who wander are lost",
        "AAAAAAAA".getBytes("UTF-8"));

    // Assert
    assertEquals(1, messageAgent.sip_provider.getListeners().size());
  }

  @Test
  void testSend24() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    UserAgentProfile user_profile = new UserAgentProfile();
    SipProvider sip_provider1 = new SipProvider("File");
    MessageAgent messageAgent = new MessageAgent(sip_provider, user_profile,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));

    // Act
    messageAgent.send("Recipient", "Hello from the Dreaming Spires", "Not all who wander are lost", new byte[]{});

    // Assert
    assertEquals(1, messageAgent.sip_provider.getListeners().size());
  }

  @Test
  void testSend25() throws UnsupportedEncodingException {
    // Arrange
    SipProvider sipProvider = new SipProvider("File");
    sipProvider.setOutboundProxy(new SipURI("Uri"));
    UserAgentProfile user_profile = new UserAgentProfile();
    SipProvider sip_provider = new SipProvider("File");
    MessageAgent messageAgent = new MessageAgent(sipProvider, user_profile,
        new MessageAgentCli(sip_provider, new UserAgentProfile()));

    // Act
    messageAgent.send("Recipient", "Hello from the Dreaming Spires", "Not all who wander are lost",
        "AAAAAAAA".getBytes("UTF-8"));

    // Assert
    assertEquals(1, messageAgent.sip_provider.getListeners().size());
  }

  @Test
  void testReceive() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    UserAgentProfile user_profile = new UserAgentProfile();
    SipProvider sip_provider1 = new SipProvider("File");
    MessageAgent messageAgent = new MessageAgent(sip_provider, user_profile,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));

    // Act
    messageAgent.receive();

    // Assert
    assertEquals(1, messageAgent.sip_provider.getListeners().size());
  }

  @Test
  void testOnReceivedMessage() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    UserAgentProfile user_profile = new UserAgentProfile();
    SipProvider sip_provider1 = new SipProvider("File");
    MessageAgent messageAgent = new MessageAgent(sip_provider, user_profile,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));
    SipProvider sipProvider = new SipProvider("File");

    // Act
    messageAgent.onReceivedMessage(sipProvider, new SipMessage("Str"));

    // Assert that nothing has changed
    assertTrue(sipProvider.isRportSet());
    assertFalse(sipProvider.isForceRportSet());
    assertFalse(sipProvider.hasTelGateway());
    assertFalse(sipProvider.hasSecureTransport());
    assertFalse(sipProvider.hasOutboundProxy());

    assertEquals(5060, sipProvider.getPort());
    assertEquals(Integer.SIZE, sipProvider.getNMaxConnections());
    assertTrue(sipProvider.getListeners().isEmpty());
    assertEquals("udp", sipProvider.getDefaultTransport());
    assertTrue(messageAgent.listener instanceof MessageAgentCli);
  }

  @Test
  void testOnReceivedMessage2() throws UnsupportedEncodingException {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    UserAgentProfile user_profile = new UserAgentProfile();
    SipProvider sip_provider1 = new SipProvider("File");
    MessageAgent messageAgent = new MessageAgent(sip_provider, user_profile,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));
    SipProvider sipProvider = new SipProvider("File");
    StatusLine status_line = new StatusLine(1, "foo");

    Vector headers = new Vector(1);

    // Act
    messageAgent.onReceivedMessage(sipProvider,
        new SipMessage(status_line, headers, "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8")));

    // Assert that nothing has changed
    assertTrue(sipProvider.isRportSet());
    assertFalse(sipProvider.isForceRportSet());
    assertFalse(sipProvider.hasTelGateway());
    assertFalse(sipProvider.hasSecureTransport());
    assertFalse(sipProvider.hasOutboundProxy());

    assertEquals(5060, sipProvider.getPort());
    assertEquals(Integer.SIZE, sipProvider.getNMaxConnections());
    assertTrue(sipProvider.getListeners().isEmpty());
    assertEquals("udp", sipProvider.getDefaultTransport());
    assertTrue(messageAgent.listener instanceof MessageAgentCli);
  }
}

