package org.mjsip.ua.cli;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Vector;
import org.junit.jupiter.api.Test;
import org.mjsip.sip.address.GenericURI;
import org.mjsip.sip.address.NameAddress;
import org.mjsip.sip.address.SipURI;
import org.mjsip.sip.call.RegistrationClient;
import org.mjsip.sip.provider.SipProvider;
import org.mjsip.ua.MessageAgent;
import org.mjsip.ua.UserAgentProfile;
import org.zoolu.util.Configure;

class MessageAgentCliDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange
    SipProvider sipProvider = new SipProvider("File");
    UserAgentProfile userAgentProfile = new UserAgentProfile();

    // Act
    MessageAgentCli actualMessageAgentCli = new MessageAgentCli(sipProvider, userAgentProfile);
    SipProvider sip_provider = new SipProvider("File");
    UserAgentProfile user_profile = new UserAgentProfile();
    SipProvider sip_provider1 = new SipProvider("File");
    MessageAgent ma = new MessageAgent(sip_provider, user_profile,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));

    NameAddress nameAddress = new NameAddress("Str");
    actualMessageAgentCli.onMaDeliveryFailure(ma, nameAddress, "Hello from the Dreaming Spires", "Result");
    SipProvider sip_provider2 = new SipProvider("File");
    UserAgentProfile user_profile1 = new UserAgentProfile();
    SipProvider sip_provider3 = new SipProvider("File");
    MessageAgent ma1 = new MessageAgent(sip_provider2, user_profile1,
        new MessageAgentCli(sip_provider3, new UserAgentProfile()));

    NameAddress nameAddress1 = new NameAddress("Str");
    actualMessageAgentCli.onMaDeliverySuccess(ma1, nameAddress1, "Hello from the Dreaming Spires", "Result");

    // Assert that nothing has changed
    assertEquals("5060/", sipProvider.toString());
    assertTrue(sipProvider.isRportSet());
    assertFalse(sipProvider.isForceRportSet());
    assertTrue(sipProvider.isAllInterfaces());
    assertFalse(sipProvider.hasTelGateway());
    assertFalse(sipProvider.hasSecureTransport());
    assertFalse(sipProvider.hasOutboundProxy());

    String[] transportProtocols = sipProvider.getTransportProtocols();
    assertEquals(0, transportProtocols.length);
    assertArrayEquals(new String[]{}, transportProtocols);
    assertEquals(0, sipProvider.getTlsPort());
    assertEquals(5060, sipProvider.getPort());
    assertEquals(Integer.SIZE, sipProvider.getNMaxConnections());
    assertEquals(1, sipProvider.getListeners().size());
    assertEquals("udp", sipProvider.getDefaultTransport());
    NameAddress contactAddress = sipProvider.getContactAddress();
    GenericURI address = contactAddress.getAddress();
    assertTrue(address instanceof SipURI);

    assertFalse(contactAddress.hasDisplayName());
    assertFalse(address.isTelURI());
    assertTrue(address.isSipURI());
    assertFalse(((SipURI) address).isSecure());
    assertFalse(((SipURI) address).hasUserName());
    assertFalse(((SipURI) address).hasTtl());
    assertTrue(((SipURI) address).hasTransport());
    assertFalse(((SipURI) address).hasPort());
    assertFalse(((SipURI) address).hasMaddr());
    assertFalse(address.hasLr());
    assertEquals(1, ((SipURI) address).getTtl());
    assertEquals("udp", ((SipURI) address).getTransport());

    assertEquals("sip", address.getScheme());
    assertEquals(-1, ((SipURI) address).getPort());
    Vector parameterNames = address.getParameterNames();
    assertEquals(1, parameterNames.size());
    assertEquals("transport", parameterNames.get(0));


    assertEquals(4080, userAgentProfile.getMediaPort());
    assertFalse(userAgentProfile.video);
    assertFalse(userAgentProfile.use_vic);
    assertFalse(userAgentProfile.use_rat);
    assertTrue(userAgentProfile.use_jmf_video);
    assertFalse(userAgentProfile.use_jmf_audio);
    assertTrue(userAgentProfile.ua_server);

    assertEquals(-1, userAgentProfile.transfer_time);
    assertFalse(userAgentProfile.symmetric_rtp);
    assertFalse(userAgentProfile.send_tone);
    assertFalse(userAgentProfile.send_only);
    assertEquals(20, userAgentProfile.refuse_time);
    assertFalse(userAgentProfile.recv_only);
    assertEquals(-1, userAgentProfile.re_invite_time);
    assertEquals(-1, userAgentProfile.re_call_time);
    assertEquals(-1, userAgentProfile.re_call_count);
    assertEquals(20, userAgentProfile.random_early_drop_rate);
    assertTrue(userAgentProfile.options_server);
    assertTrue(userAgentProfile.null_server);
    assertFalse(userAgentProfile.no_system_audio);
    assertFalse(userAgentProfile.no_prompt);
    assertFalse(userAgentProfile.no_offer);
    assertEquals(1, userAgentProfile.media_descs.length);
    assertFalse(userAgentProfile.loopback);
    assertEquals(0L, userAgentProfile.keepalive_time);
    assertTrue(userAgentProfile.javax_sound_sync);
    assertFalse(userAgentProfile.javax_sound_direct_convertion);
    assertEquals(-1, userAgentProfile.hangup_time);
    assertEquals(3600, userAgentProfile.expires);
    assertFalse(userAgentProfile.do_unregister_all);
    assertFalse(userAgentProfile.do_unregister);
    assertFalse(userAgentProfile.do_register);
    assertEquals("buddy.lst", UserAgentProfile.buddy_list_file);
    assertEquals("vic", userAgentProfile.bin_vic);
    assertEquals("rat", userAgentProfile.bin_rat);
    assertTrue(userAgentProfile.audio);
    assertEquals(-1, userAgentProfile.accept_time);
    assertEquals("NONE", Configure.NONE);
    assertTrue(userAgentProfile.getUserURI().getAddress() instanceof SipURI);
    assertEquals(nameAddress1, nameAddress);
    assertEquals(nameAddress, nameAddress1);
  }

  @Test
  void testConstructor2() {
    // Arrange
    SipProvider sipProvider = new SipProvider("File");
    UserAgentProfile userAgentProfile = new UserAgentProfile();

    // Act and Assert
    assertNull((new MessageAgentCli(sipProvider, userAgentProfile)).logger);
    assertEquals(1, sipProvider.getListeners().size());

  }

  @Test
  void testConstructor3() {
    // Arrange
    SipProvider sipProvider = new SipProvider("42 Main St", 8080);

    UserAgentProfile userAgentProfile = new UserAgentProfile();

    // Act and Assert
    assertNull((new MessageAgentCli(sipProvider, userAgentProfile)).logger);
    assertEquals(1, sipProvider.getListeners().size());
    assertEquals("42 Main St:8080", userAgentProfile.ua_address);
  }

  @Test
  void testGetRemoteUser() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");

    MessageAgentCli messageAgentCli = new MessageAgentCli(sip_provider, new UserAgentProfile());
    SipProvider sip_provider1 = new SipProvider("File");
    UserAgentProfile user_profile = new UserAgentProfile();
    SipProvider sip_provider2 = new SipProvider("File");
    MessageAgent ma = new MessageAgent(sip_provider1, user_profile,
        new MessageAgentCli(sip_provider2, new UserAgentProfile()));

    NameAddress sender = new NameAddress("Str");
    messageAgentCli.onMaReceivedMessage(ma, sender, new NameAddress("Str"), "Hello from the Dreaming Spires",
        "Not all who wander are lost", "Not all who wander are lost");

    // Act and Assert
    assertEquals("<sip:Str>", messageAgentCli.getRemoteUser());
  }

  @Test
  void testGetRemoteUser2() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");

    MessageAgentCli messageAgentCli = new MessageAgentCli(sip_provider, new UserAgentProfile());
    SipProvider sip_provider1 = new SipProvider("File");
    UserAgentProfile user_profile = new UserAgentProfile();
    SipProvider sip_provider2 = new SipProvider("File");
    MessageAgent ma = new MessageAgent(sip_provider1, user_profile,
        new MessageAgentCli(sip_provider2, new UserAgentProfile()));

    NameAddress sender = new NameAddress("Display name", new GenericURI("Uri"));

    messageAgentCli.onMaReceivedMessage(ma, sender, new NameAddress("Str"), "Hello from the Dreaming Spires",
        "Not all who wander are lost", "Not all who wander are lost");

    // Act and Assert
    assertEquals("\"Display name\" <Uri>", messageAgentCli.getRemoteUser());
  }

  @Test
  void testGetRemoteUser3() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");

    MessageAgentCli messageAgentCli = new MessageAgentCli(sip_provider, new UserAgentProfile());
    SipProvider sip_provider1 = new SipProvider("File");
    UserAgentProfile user_profile = new UserAgentProfile();
    SipProvider sip_provider2 = new SipProvider("File");
    MessageAgent ma = new MessageAgent(sip_provider1, user_profile,
        new MessageAgentCli(sip_provider2, new UserAgentProfile()));

    NameAddress sender = new NameAddress("Display name", null);

    messageAgentCli.onMaReceivedMessage(ma, sender, new NameAddress("Str"), "Hello from the Dreaming Spires",
        "Not all who wander are lost", "Not all who wander are lost");

    // Act and Assert
    assertEquals("\"Display name\" <null>", messageAgentCli.getRemoteUser());
  }

  @Test
  void testOnMaReceivedMessage() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    MessageAgentCli messageAgentCli = new MessageAgentCli(sip_provider, new UserAgentProfile());
    SipProvider sip_provider1 = new SipProvider("File");
    UserAgentProfile user_profile = new UserAgentProfile();
    SipProvider sip_provider2 = new SipProvider("File");
    MessageAgent ma = new MessageAgent(sip_provider1, user_profile,
        new MessageAgentCli(sip_provider2, new UserAgentProfile()));

    NameAddress sender = new NameAddress("Str");

    // Act
    messageAgentCli.onMaReceivedMessage(ma, sender, new NameAddress("Str"), "Hello from the Dreaming Spires",
        "Not all who wander are lost", "Not all who wander are lost");

    // Assert
    assertEquals("<sip:Str>", messageAgentCli.getRemoteUser());
  }

  @Test
  void testOnMaReceivedMessage2() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    MessageAgentCli messageAgentCli = new MessageAgentCli(sip_provider, new UserAgentProfile());
    SipProvider sip_provider1 = new SipProvider("File");
    UserAgentProfile user_profile = new UserAgentProfile();
    SipProvider sip_provider2 = new SipProvider("File");
    MessageAgent ma = new MessageAgent(sip_provider1, user_profile,
        new MessageAgentCli(sip_provider2, new UserAgentProfile()));

    // Act
    messageAgentCli.onMaReceivedMessage(ma, null, new NameAddress("Str"), "Hello from the Dreaming Spires",
        "Not all who wander are lost", "Not all who wander are lost");

    // Assert
    assertNull(messageAgentCli.remote_user);
  }

  @Test
  void testOnMaReceivedMessage3() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    MessageAgentCli messageAgentCli = new MessageAgentCli(sip_provider, new UserAgentProfile());
    SipProvider sip_provider1 = new SipProvider("File");
    UserAgentProfile user_profile = new UserAgentProfile();
    SipProvider sip_provider2 = new SipProvider("File");
    MessageAgent ma = new MessageAgent(sip_provider1, user_profile,
        new MessageAgentCli(sip_provider2, new UserAgentProfile()));

    NameAddress sender = new NameAddress("Display name", new GenericURI("Uri"));

    // Act
    messageAgentCli.onMaReceivedMessage(ma, sender, new NameAddress("Str"), "Hello from the Dreaming Spires",
        "Not all who wander are lost", "Not all who wander are lost");

    // Assert
    assertEquals("\"Display name\" <Uri>", messageAgentCli.getRemoteUser());
  }

  @Test
  void testOnMaReceivedMessage4() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    MessageAgentCli messageAgentCli = new MessageAgentCli(sip_provider, new UserAgentProfile());
    SipProvider sip_provider1 = new SipProvider("File");
    UserAgentProfile user_profile = new UserAgentProfile();
    SipProvider sip_provider2 = new SipProvider("File");
    MessageAgent ma = new MessageAgent(sip_provider1, user_profile,
        new MessageAgentCli(sip_provider2, new UserAgentProfile()));

    NameAddress sender = new NameAddress("Str");

    // Act
    messageAgentCli.onMaReceivedMessage(ma, sender, new NameAddress("Str"), null, "Not all who wander are lost",
        "Not all who wander are lost");

    // Assert
    assertEquals("<sip:Str>", messageAgentCli.getRemoteUser());
  }

  @Test
  void testOnMaReceivedMessage5() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    MessageAgentCli messageAgentCli = new MessageAgentCli(sip_provider, new UserAgentProfile());
    SipProvider sip_provider1 = new SipProvider("File");
    UserAgentProfile user_profile = new UserAgentProfile();
    SipProvider sip_provider2 = new SipProvider("File");
    MessageAgent ma = new MessageAgent(sip_provider1, user_profile,
        new MessageAgentCli(sip_provider2, new UserAgentProfile()));

    NameAddress nameAddress = new NameAddress("Str");
    nameAddress.setAddress(null);

    // Act
    messageAgentCli.onMaReceivedMessage(ma, nameAddress, new NameAddress("Str"), "Hello from the Dreaming Spires",
        "Not all who wander are lost", "Not all who wander are lost");

    // Assert
    assertEquals("<null>", messageAgentCli.getRemoteUser());
  }

  @Test
  void testOnMaReceivedMessage6() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    MessageAgentCli messageAgentCli = new MessageAgentCli(sip_provider, new UserAgentProfile());
    SipProvider sip_provider1 = new SipProvider("File");
    UserAgentProfile user_profile = new UserAgentProfile();
    SipProvider sip_provider2 = new SipProvider("File");
    MessageAgent ma = new MessageAgent(sip_provider1, user_profile,
        new MessageAgentCli(sip_provider2, new UserAgentProfile()));

    NameAddress sender = new NameAddress("Display name", null);

    // Act
    messageAgentCli.onMaReceivedMessage(ma, sender, new NameAddress("Str"), "Hello from the Dreaming Spires",
        "Not all who wander are lost", "Not all who wander are lost");

    // Assert
    assertEquals("\"Display name\" <null>", messageAgentCli.getRemoteUser());
  }

  @Test
  void testOnRegistrationSuccess() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    MessageAgentCli messageAgentCli = new MessageAgentCli(sip_provider, new UserAgentProfile());
    SipProvider sip_provider1 = new SipProvider("File");
    SipURI registrar = new SipURI("Uri");
    NameAddress to_naddr = new NameAddress("Str");
    SipProvider sip_provider2 = new SipProvider("File");
    RegistrationClient registrationClient = new RegistrationClient(sip_provider1, registrar, to_naddr,
        new MessageAgentCli(sip_provider2, new UserAgentProfile()));

    NameAddress nameAddress = new NameAddress("Str");

    // Act
    messageAgentCli.onRegistrationSuccess(registrationClient, nameAddress, new NameAddress("Str"), 1, "Result");

    // Assert that nothing has changed
    assertEquals(nameAddress, registrationClient.getTargetAOR());
    assertFalse(registrationClient.isRegistering());
    assertEquals("<sip:Str>", nameAddress.toString());
    assertFalse(nameAddress.hasDisplayName());
  }

  @Test
  void testOnRegistrationFailure() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    MessageAgentCli messageAgentCli = new MessageAgentCli(sip_provider, new UserAgentProfile());
    SipProvider sip_provider1 = new SipProvider("File");
    SipURI registrar = new SipURI("Uri");
    NameAddress to_naddr = new NameAddress("Str");
    SipProvider sip_provider2 = new SipProvider("File");
    RegistrationClient registrationClient = new RegistrationClient(sip_provider1, registrar, to_naddr,
        new MessageAgentCli(sip_provider2, new UserAgentProfile()));

    NameAddress nameAddress = new NameAddress("Str");

    // Act
    messageAgentCli.onRegistrationFailure(registrationClient, nameAddress, new NameAddress("Str"), "Result");

    // Assert that nothing has changed
    assertEquals(nameAddress, registrationClient.getTargetAOR());
    assertFalse(registrationClient.isRegistering());
    assertEquals("<sip:Str>", nameAddress.toString());
    assertFalse(nameAddress.hasDisplayName());
  }
}

