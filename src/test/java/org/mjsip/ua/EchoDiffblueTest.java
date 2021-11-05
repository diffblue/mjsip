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

class EchoDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");

    // Act
    Echo actualEcho = new Echo(sip_provider, new UserAgentProfile(), 1, true);

    // Assert
    assertEquals(4080, actualEcho.first_media_port);
    assertEquals(4080, actualEcho.last_media_port);
    assertNull(actualEcho.logger);
    assertTrue(actualEcho.force_reverse_route);
    assertEquals(4080, actualEcho.media_port);
    UserAgentProfile userAgentProfile = actualEcho.ua_profile;
    assertFalse(userAgentProfile.use_jmf_audio);
    assertEquals(2, actualEcho.sip_provider.getListeners().size());
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
    Echo actualEcho = new Echo(sip_provider, new UserAgentProfile(), 1, true);

    // Assert
    assertEquals(4080, actualEcho.first_media_port);
    assertEquals(4080, actualEcho.last_media_port);
    assertNull(actualEcho.logger);
    assertTrue(actualEcho.force_reverse_route);
    assertEquals(4080, actualEcho.media_port);
    UserAgentProfile userAgentProfile = actualEcho.ua_profile;
    assertFalse(userAgentProfile.use_jmf_audio);
    assertEquals(2, actualEcho.sip_provider.getListeners().size());
    assertFalse(userAgentProfile.ua_server);
    assertFalse(userAgentProfile.options_server);
    assertFalse(userAgentProfile.null_server);
    assertFalse(userAgentProfile.do_register);
    assertEquals("<sip:42 Main St:8080>", userAgentProfile.toLines());
  }

  @Test
  void testOnReceivedMessage() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    Echo echo = new Echo(sip_provider, new UserAgentProfile(), 1, true);
    SipProvider sipProvider = new SipProvider("File");

    // Act
    echo.onReceivedMessage(sipProvider, new SipMessage("Str"));

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
    assertEquals(4080, echo.first_media_port);
    assertEquals(4080, echo.last_media_port);
    assertTrue(echo.force_reverse_route);
    assertEquals(4080, echo.media_port);
    assertFalse(echo.ua_profile.use_jmf_audio);
  }

  @Test
  void testOnReceivedMessage2() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    Echo echo = new Echo(sip_provider, new UserAgentProfile(), 1, true);
    SipProvider sipProvider = new SipProvider("File");

    // Act
    echo.onReceivedMessage(sipProvider, new SipMessage("onReceivedMessage()"));

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
    assertEquals(4080, echo.first_media_port);
    assertEquals(4080, echo.last_media_port);
    assertTrue(echo.force_reverse_route);
    assertEquals(4080, echo.media_port);
    assertFalse(echo.ua_profile.use_jmf_audio);
  }

  @Test
  void testLog() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    Echo echo = new Echo(sip_provider, new UserAgentProfile(), 1, true);

    // Act
    echo.log("Str");

    // Assert that nothing has changed
    assertEquals(4080, echo.first_media_port);
    assertEquals(4080, echo.last_media_port);
    assertTrue(echo.force_reverse_route);
    assertEquals(4080, echo.media_port);
    assertFalse(echo.ua_profile.use_jmf_audio);
  }

  @Test
  void testLog2() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    Echo echo = new Echo(sip_provider, new UserAgentProfile(), 1, true);
    LogLevel logLevel = new LogLevel("Name", 42);

    // Act
    echo.log(logLevel, "Str");

    // Assert that nothing has changed
    assertEquals("Name", logLevel.getName());
    assertEquals(42, logLevel.getValue());
    assertEquals(4080, echo.first_media_port);
    assertEquals(4080, echo.last_media_port);
    assertTrue(echo.force_reverse_route);
    assertEquals(4080, echo.media_port);
    assertFalse(echo.ua_profile.use_jmf_audio);
  }

  @Test
  void testOnRegistrationFailure() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    Echo echo = new Echo(sip_provider, new UserAgentProfile(), 1, true);
    SipProvider sip_provider1 = new SipProvider("File");
    SipURI registrar = new SipURI("Uri");
    NameAddress to_naddr = new NameAddress("Str");
    SipProvider sip_provider2 = new SipProvider("File");
    RegistrationClient registrationClient = new RegistrationClient(sip_provider1, registrar, to_naddr,
        new MessageAgentCli(sip_provider2, new UserAgentProfile()));

    NameAddress nameAddress = new NameAddress("Str");

    // Act
    echo.onRegistrationFailure(registrationClient, nameAddress, new NameAddress("Str"), "Result");

    // Assert that nothing has changed
    assertEquals(nameAddress, registrationClient.getTargetAOR());
    assertFalse(registrationClient.isRegistering());
    assertEquals("<sip:Str>", nameAddress.toString());
    assertFalse(nameAddress.hasDisplayName());
    assertEquals(4080, echo.first_media_port);
    assertEquals(4080, echo.last_media_port);
    assertTrue(echo.force_reverse_route);
    assertEquals(4080, echo.media_port);
    assertFalse(echo.ua_profile.use_jmf_audio);
  }

  @Test
  void testOnRegistrationSuccess() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    Echo echo = new Echo(sip_provider, new UserAgentProfile(), 1, true);
    SipProvider sip_provider1 = new SipProvider("File");
    SipURI registrar = new SipURI("Uri");
    NameAddress to_naddr = new NameAddress("Str");
    SipProvider sip_provider2 = new SipProvider("File");
    RegistrationClient registrationClient = new RegistrationClient(sip_provider1, registrar, to_naddr,
        new MessageAgentCli(sip_provider2, new UserAgentProfile()));

    NameAddress nameAddress = new NameAddress("Str");

    // Act
    echo.onRegistrationSuccess(registrationClient, nameAddress, new NameAddress("Str"), 1, "Result");

    // Assert that nothing has changed
    assertEquals(nameAddress, registrationClient.getTargetAOR());
    assertFalse(registrationClient.isRegistering());
    assertEquals("<sip:Str>", nameAddress.toString());
    assertFalse(nameAddress.hasDisplayName());
    assertEquals(4080, echo.first_media_port);
    assertEquals(4080, echo.last_media_port);
    assertTrue(echo.force_reverse_route);
    assertEquals(4080, echo.media_port);
    assertFalse(echo.ua_profile.use_jmf_audio);
  }

  @Test
  void testPrintOut() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    Echo echo = new Echo(sip_provider, new UserAgentProfile(), 1, true);

    // Act
    echo.printOut("Str");

    // Assert that nothing has changed
    assertEquals(4080, echo.first_media_port);
    assertEquals(4080, echo.last_media_port);
    assertTrue(echo.force_reverse_route);
    assertEquals(4080, echo.media_port);
    assertFalse(echo.ua_profile.use_jmf_audio);
  }
}

