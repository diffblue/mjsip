package org.mjsip.ua;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.mjsip.sip.address.NameAddress;
import org.mjsip.sip.address.SipURI;
import org.mjsip.sip.call.RegistrationClient;
import org.mjsip.sip.message.SipMessage;
import org.mjsip.sip.provider.SipProvider;
import org.mjsip.ua.cli.MessageAgentCli;
import org.zoolu.util.LogLevel;

class JukeboxDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");

    // Act
    Jukebox actualJukebox = new Jukebox(sip_provider, new UserAgentProfile(), 10);

    // Assert
    assertEquals(4080, actualJukebox.first_media_port);
    assertNull(actualJukebox.logger);
    assertEquals(4089, actualJukebox.last_media_port);
    assertEquals(1, actualJukebox.sip_provider.getListeners().size());
    UserAgentProfile userAgentProfile = actualJukebox.ua_profile;
    assertNull(userAgentProfile.user);

    assertFalse(userAgentProfile.ua_server);
    assertFalse(userAgentProfile.options_server);
    assertFalse(userAgentProfile.null_server);
    assertFalse(userAgentProfile.do_register);
  }

  @Test
  void testConstructor2() {
    // Arrange
    SipProvider sip_provider = new SipProvider("42 Main St", 8080);

    // Act
    Jukebox actualJukebox = new Jukebox(sip_provider, new UserAgentProfile(), 10);

    // Assert
    assertEquals(4080, actualJukebox.first_media_port);
    assertNull(actualJukebox.logger);
    assertEquals(4089, actualJukebox.last_media_port);
    assertEquals(1, actualJukebox.sip_provider.getListeners().size());
    UserAgentProfile userAgentProfile = actualJukebox.ua_profile;
    assertNull(userAgentProfile.user);
    assertEquals("<sip:42 Main St:8080>", userAgentProfile.toLines());
    assertFalse(userAgentProfile.ua_server);
    assertFalse(userAgentProfile.options_server);
    assertFalse(userAgentProfile.null_server);
    assertFalse(userAgentProfile.do_register);
  }

  @Test
  void testLog() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    Jukebox jukebox = new Jukebox(sip_provider, new UserAgentProfile(), 10);

    // Act
    jukebox.log("Str");

    // Assert that nothing has changed
    assertEquals(4080, jukebox.first_media_port);
    assertEquals(4089, jukebox.last_media_port);
  }

  @Test
  void testLog2() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    Jukebox jukebox = new Jukebox(sip_provider, new UserAgentProfile(), 10);
    LogLevel logLevel = new LogLevel("Name", 42);

    // Act
    jukebox.log(logLevel, "Str");

    // Assert that nothing has changed
    assertEquals("Name", logLevel.getName());
    assertEquals(42, logLevel.getValue());
    assertEquals(4080, jukebox.first_media_port);
    assertEquals(4089, jukebox.last_media_port);
  }

  @Test
  void testOnReceivedMessage() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    Jukebox jukebox = new Jukebox(sip_provider, new UserAgentProfile(), 10);
    SipProvider sipProvider = new SipProvider("File");

    // Act
    jukebox.onReceivedMessage(sipProvider, new SipMessage("Str"));

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
    assertEquals(4080, jukebox.first_media_port);
    assertEquals(4089, jukebox.last_media_port);
    assertFalse(jukebox.ua_profile.use_rat);
  }

  @Test
  void testOnReceivedMessage2() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    Jukebox jukebox = new Jukebox(sip_provider, new UserAgentProfile(), 10);
    SipProvider sipProvider = new SipProvider("File");

    // Act
    jukebox.onReceivedMessage(sipProvider, new SipMessage("onReceivedMessage()"));

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
    assertEquals(4080, jukebox.first_media_port);
    assertEquals(4089, jukebox.last_media_port);
    assertFalse(jukebox.ua_profile.use_rat);
  }

  @Test
  void testOnRegistrationFailure() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    Jukebox jukebox = new Jukebox(sip_provider, new UserAgentProfile(), 10);
    SipProvider sip_provider1 = new SipProvider("File");
    SipURI registrar = new SipURI("Uri");
    NameAddress to_naddr = new NameAddress("Str");
    SipProvider sip_provider2 = new SipProvider("File");
    RegistrationClient registrationClient = new RegistrationClient(sip_provider1, registrar, to_naddr,
        new MessageAgentCli(sip_provider2, new UserAgentProfile()));

    NameAddress nameAddress = new NameAddress("Str");

    // Act
    jukebox.onRegistrationFailure(registrationClient, nameAddress, new NameAddress("Str"), "Result");

    // Assert that nothing has changed
    assertEquals(nameAddress, registrationClient.getTargetAOR());
    assertFalse(registrationClient.isRegistering());
    assertEquals("<sip:Str>", nameAddress.toString());
    assertFalse(nameAddress.hasDisplayName());
    assertEquals(4080, jukebox.first_media_port);
    assertEquals(4089, jukebox.last_media_port);
    assertFalse(jukebox.ua_profile.use_rat);
  }

  @Test
  void testOnRegistrationSuccess() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    Jukebox jukebox = new Jukebox(sip_provider, new UserAgentProfile(), 10);
    SipProvider sip_provider1 = new SipProvider("File");
    SipURI registrar = new SipURI("Uri");
    NameAddress to_naddr = new NameAddress("Str");
    SipProvider sip_provider2 = new SipProvider("File");
    RegistrationClient registrationClient = new RegistrationClient(sip_provider1, registrar, to_naddr,
        new MessageAgentCli(sip_provider2, new UserAgentProfile()));

    NameAddress nameAddress = new NameAddress("Str");

    // Act
    jukebox.onRegistrationSuccess(registrationClient, nameAddress, new NameAddress("Str"), 1, "Result");

    // Assert that nothing has changed
    assertEquals(nameAddress, registrationClient.getTargetAOR());
    assertFalse(registrationClient.isRegistering());
    assertEquals("<sip:Str>", nameAddress.toString());
    assertFalse(nameAddress.hasDisplayName());
    assertEquals(4080, jukebox.first_media_port);
    assertEquals(4089, jukebox.last_media_port);
    assertFalse(jukebox.ua_profile.use_rat);
  }

  @Test
  void testPrintOut() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    Jukebox jukebox = new Jukebox(sip_provider, new UserAgentProfile(), 10);

    // Act
    jukebox.printOut("Str");

    // Assert that nothing has changed
    assertEquals(4080, jukebox.first_media_port);
    assertEquals(4089, jukebox.last_media_port);
  }
}

