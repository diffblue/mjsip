package org.mjsip.sip.call;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import java.io.UnsupportedEncodingException;
import org.junit.jupiter.api.Test;
import org.mjsip.sip.address.NameAddress;
import org.mjsip.sip.address.SipURI;
import org.mjsip.sip.provider.SipProvider;
import org.mjsip.ua.UserAgentProfile;
import org.mjsip.ua.cli.MessageAgentCli;
import org.zoolu.util.LogLevel;
import org.zoolu.util.Timer;
import org.zoolu.util.TimerListener;

class RegistrationClientDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    SipURI registrar = new SipURI("Uri");
    NameAddress to_naddr = new NameAddress("Str");
    SipProvider sip_provider1 = new SipProvider("File");

    // Act
    RegistrationClient actualRegistrationClient = new RegistrationClient(sip_provider, registrar, to_naddr,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));

    // Assert
    NameAddress expectedTargetAOR = actualRegistrationClient.to_naddr;
    assertSame(expectedTargetAOR, actualRegistrationClient.getTargetAOR());
    assertFalse(actualRegistrationClient.isRegistering());
    NameAddress nameAddress = actualRegistrationClient.contact_naddr;
    assertNull(nameAddress.getDisplayName());
    assertFalse(nameAddress.hasDisplayName());

    SipURI sipURI = actualRegistrationClient.registrar_uri;
    assertFalse(sipURI.hasUserName());
    assertFalse(sipURI.hasTtl());
    assertFalse(sipURI.hasTransport());
    assertFalse(sipURI.hasPort());
    assertFalse(sipURI.hasMaddr());
    assertFalse(sipURI.hasLr());
    assertNull(sipURI.getUserName());
    assertEquals(1, sipURI.getTtl());
    assertNull(sipURI.getTransport());
    assertEquals("Uri", sipURI.getSpecificPart());
    assertEquals("sip", sipURI.getScheme());
    assertEquals(-1, sipURI.getPort());
    assertNull(sipURI.getMaddr());
    assertEquals("Uri", sipURI.getHost());
    assertFalse(sipURI.isTelURI());
    assertTrue(sipURI.isSipURI());
    assertEquals("sip:Uri", sipURI.toString());
    assertFalse(sipURI.isSecure());
    SipProvider sipProvider = actualRegistrationClient.sip_provider;
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
    assertEquals(nameAddress, sipProvider.getContactAddress());
    assertNull(sipProvider.getBindingIpAddress());
    assertEquals("5060/", sipProvider.toString());
  }

  @Test
  void testConstructor2() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    SipURI registrar = new SipURI("Uri");
    NameAddress to_naddr = new NameAddress("Str");
    SipProvider sip_provider1 = new SipProvider("File");

    // Act
    RegistrationClient actualRegistrationClient = new RegistrationClient(sip_provider, registrar, to_naddr, "janedoe",
        "Realm", "iloveyou", new MessageAgentCli(sip_provider1, new UserAgentProfile()));

    // Assert
    NameAddress expectedTargetAOR = actualRegistrationClient.to_naddr;
    NameAddress targetAOR = actualRegistrationClient.getTargetAOR();
    assertSame(expectedTargetAOR, targetAOR);
    assertEquals("janedoe", actualRegistrationClient.username);
    assertFalse(actualRegistrationClient.isRegistering());
    assertEquals(0, actualRegistrationClient.attempts);
    assertTrue(actualRegistrationClient.listener instanceof MessageAgentCli);
    assertEquals(3600, actualRegistrationClient.renew_time);
    assertNull(actualRegistrationClient.logger);
    assertFalse(actualRegistrationClient.loop);
    assertNull(actualRegistrationClient.next_nonce);
    assertEquals(3600, actualRegistrationClient.expire_time);
    assertEquals("iloveyou", actualRegistrationClient.passwd);
    assertNull(actualRegistrationClient.qop);
    assertSame(targetAOR, actualRegistrationClient.from_naddr);
    assertEquals("Realm", actualRegistrationClient.realm);
    NameAddress nameAddress = actualRegistrationClient.contact_naddr;
    assertNull(nameAddress.getDisplayName());

  }

  @Test
  void testConstructor3() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    SipURI registrar = new SipURI("Uri");
    NameAddress to_naddr = new NameAddress("Str");
    NameAddress from_naddr = new NameAddress("Str");
    NameAddress contact_naddr = new NameAddress("Str");
    SipProvider sip_provider1 = new SipProvider("File");

    // Act
    RegistrationClient actualRegistrationClient = new RegistrationClient(sip_provider, registrar, to_naddr, from_naddr,
        contact_naddr, "janedoe", "Realm", "iloveyou", new MessageAgentCli(sip_provider1, new UserAgentProfile()));

    // Assert
    NameAddress expectedTargetAOR = actualRegistrationClient.contact_naddr;
    NameAddress targetAOR = actualRegistrationClient.getTargetAOR();
    assertEquals(expectedTargetAOR, targetAOR);
    assertEquals("janedoe", actualRegistrationClient.username);
    assertFalse(actualRegistrationClient.isRegistering());
    assertEquals(0, actualRegistrationClient.attempts);
    assertTrue(actualRegistrationClient.listener instanceof MessageAgentCli);
    assertEquals(3600, actualRegistrationClient.renew_time);
    assertNull(actualRegistrationClient.logger);
    assertEquals(actualRegistrationClient.from_naddr, actualRegistrationClient.contact_naddr);
    assertFalse(actualRegistrationClient.loop);
    assertNull(actualRegistrationClient.next_nonce);
    assertEquals(3600, actualRegistrationClient.expire_time);
    assertEquals("iloveyou", actualRegistrationClient.passwd);
    assertNull(actualRegistrationClient.qop);
    assertEquals(targetAOR, actualRegistrationClient.from_naddr);
    assertEquals("Realm", actualRegistrationClient.realm);
  }

  @Test
  void testConstructor4() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    SipURI registrar = new SipURI("Uri");
    NameAddress to_naddr = new NameAddress("Str");
    NameAddress from_naddr = new NameAddress("Str");
    NameAddress contact_naddr = new NameAddress("Str");
    SipProvider sip_provider1 = new SipProvider("File");

    // Act
    RegistrationClient actualRegistrationClient = new RegistrationClient(sip_provider, registrar, to_naddr, from_naddr,
        contact_naddr, new MessageAgentCli(sip_provider1, new UserAgentProfile()));

    // Assert
    NameAddress expectedTargetAOR = actualRegistrationClient.contact_naddr;
    NameAddress targetAOR = actualRegistrationClient.getTargetAOR();
    assertEquals(expectedTargetAOR, targetAOR);
    assertNull(actualRegistrationClient.username);
    assertFalse(actualRegistrationClient.isRegistering());
    assertEquals(0, actualRegistrationClient.attempts);
    assertTrue(actualRegistrationClient.listener instanceof MessageAgentCli);
    assertEquals(3600, actualRegistrationClient.renew_time);
    assertNull(actualRegistrationClient.logger);
    assertEquals(actualRegistrationClient.from_naddr, actualRegistrationClient.contact_naddr);
    assertFalse(actualRegistrationClient.loop);
    assertNull(actualRegistrationClient.next_nonce);
    assertEquals(3600, actualRegistrationClient.expire_time);
    assertNull(actualRegistrationClient.passwd);
    assertNull(actualRegistrationClient.qop);
    assertEquals(targetAOR, actualRegistrationClient.from_naddr);
    assertNull(actualRegistrationClient.realm);
  }

  @Test
  void testConstructor5() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    SipURI registrar = new SipURI("Uri");
    NameAddress to_naddr = new NameAddress("Str");
    SipProvider sip_provider1 = new SipProvider("File");

    // Act
    RegistrationClient actualRegistrationClient = new RegistrationClient(sip_provider, registrar, to_naddr,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));

    // Assert
    NameAddress expectedTargetAOR = actualRegistrationClient.to_naddr;
    NameAddress targetAOR = actualRegistrationClient.getTargetAOR();
    assertSame(expectedTargetAOR, targetAOR);
    assertNull(actualRegistrationClient.username);
    assertFalse(actualRegistrationClient.isRegistering());
    assertEquals(0, actualRegistrationClient.attempts);
    assertTrue(actualRegistrationClient.listener instanceof MessageAgentCli);
    assertEquals(3600, actualRegistrationClient.renew_time);
    assertNull(actualRegistrationClient.logger);
    assertFalse(actualRegistrationClient.loop);
    assertNull(actualRegistrationClient.next_nonce);
    assertEquals(3600, actualRegistrationClient.expire_time);
    assertNull(actualRegistrationClient.passwd);
    assertNull(actualRegistrationClient.qop);
    assertSame(targetAOR, actualRegistrationClient.from_naddr);
    assertNull(actualRegistrationClient.realm);
    NameAddress nameAddress = actualRegistrationClient.contact_naddr;
    assertNull(nameAddress.getDisplayName());

  }

  @Test
  void testRegister() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    SipURI registrar = new SipURI("Uri");
    NameAddress to_naddr = new NameAddress("Str");
    SipProvider sip_provider1 = new SipProvider("File");
    RegistrationClient registrationClient = new RegistrationClient(sip_provider, registrar, to_naddr,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));

    // Act
    registrationClient.register();

    // Assert
    assertEquals(0, registrationClient.attempts);
    assertEquals(3600, registrationClient.expire_time);
  }

  @Test
  void testRegister2() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    SipURI registrar = new SipURI("42");
    NameAddress to_naddr = new NameAddress("Str");
    SipProvider sip_provider1 = new SipProvider("File");
    RegistrationClient registrationClient = new RegistrationClient(sip_provider, registrar, to_naddr,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));

    // Act
    registrationClient.register();

    // Assert
    assertEquals(0, registrationClient.attempts);
    assertEquals(3600, registrationClient.expire_time);
  }

  @Test
  void testRegister3() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    NameAddress to_naddr = new NameAddress("Str");
    SipProvider sip_provider1 = new SipProvider("File");
    RegistrationClient registrationClient = new RegistrationClient(sip_provider, null, to_naddr,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));

    // Act
    registrationClient.register();

    // Assert
    assertEquals(0, registrationClient.attempts);
    assertEquals(3600, registrationClient.expire_time);
    assertEquals(1, registrationClient.sip_provider.getListeners().size());
  }

  @Test
  void testRegister4() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    SipURI registrar = new SipURI("localhost", 10);

    NameAddress to_naddr = new NameAddress("Str");
    SipProvider sip_provider1 = new SipProvider("File");
    RegistrationClient registrationClient = new RegistrationClient(sip_provider, registrar, to_naddr,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));

    // Act
    registrationClient.register();

    // Assert
    assertEquals(0, registrationClient.attempts);
    assertEquals(3600, registrationClient.expire_time);
  }

  @Test
  void testRegister5() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    SipURI registrar = new SipURI("janedoe", "localhost");

    NameAddress to_naddr = new NameAddress("Str");
    SipProvider sip_provider1 = new SipProvider("File");
    RegistrationClient registrationClient = new RegistrationClient(sip_provider, registrar, to_naddr,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));

    // Act
    registrationClient.register();

    // Assert
    assertEquals(0, registrationClient.attempts);
    assertEquals(3600, registrationClient.expire_time);
  }

  @Test
  void testRegister6() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addMaddr("42 Main St");
    SipProvider sip_provider = new SipProvider("File");
    NameAddress to_naddr = new NameAddress("Str");
    SipProvider sip_provider1 = new SipProvider("File");
    RegistrationClient registrationClient = new RegistrationClient(sip_provider, sipURI, to_naddr,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));

    // Act
    registrationClient.register();

    // Assert
    assertEquals(0, registrationClient.attempts);
    assertEquals(3600, registrationClient.expire_time);
  }

  @Test
  void testRegister7() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addParameter("REGISTER");
    SipProvider sip_provider = new SipProvider("File");
    NameAddress to_naddr = new NameAddress("Str");
    SipProvider sip_provider1 = new SipProvider("File");
    RegistrationClient registrationClient = new RegistrationClient(sip_provider, sipURI, to_naddr,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));

    // Act
    registrationClient.register();

    // Assert
    assertEquals(0, registrationClient.attempts);
    assertEquals(3600, registrationClient.expire_time);
  }

  @Test
  void testRegister8() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addTransport("alice.liddell@example.org");
    SipProvider sip_provider = new SipProvider("File");
    NameAddress to_naddr = new NameAddress("Str");
    SipProvider sip_provider1 = new SipProvider("File");
    RegistrationClient registrationClient = new RegistrationClient(sip_provider, sipURI, to_naddr,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));

    // Act
    registrationClient.register();

    // Assert
    assertEquals(0, registrationClient.attempts);
    assertEquals(3600, registrationClient.expire_time);
  }

  @Test
  void testRegister9() {
    // Arrange
    SipURI sipURI = new SipURI("localhost", 10);
    sipURI.addMaddr("42 Main St");
    SipProvider sip_provider = new SipProvider("File");
    NameAddress to_naddr = new NameAddress("Str");
    SipProvider sip_provider1 = new SipProvider("File");
    RegistrationClient registrationClient = new RegistrationClient(sip_provider, sipURI, to_naddr,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));

    // Act
    registrationClient.register();

    // Assert
    assertEquals(0, registrationClient.attempts);
    assertEquals(3600, registrationClient.expire_time);
  }

  @Test
  void testRegister10() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addMaddr("42 Main St");
    sipURI.addMaddr("42 Main St");
    SipProvider sip_provider = new SipProvider("File");
    NameAddress to_naddr = new NameAddress("Str");
    SipProvider sip_provider1 = new SipProvider("File");
    RegistrationClient registrationClient = new RegistrationClient(sip_provider, sipURI, to_naddr,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));

    // Act
    registrationClient.register();

    // Assert
    assertEquals(0, registrationClient.attempts);
    assertEquals(3600, registrationClient.expire_time);
  }

  @Test
  void testRegister11() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addParameter("REGISTER");
    sipURI.addMaddr("42 Main St");
    SipProvider sip_provider = new SipProvider("File");
    NameAddress to_naddr = new NameAddress("Str");
    SipProvider sip_provider1 = new SipProvider("File");
    RegistrationClient registrationClient = new RegistrationClient(sip_provider, sipURI, to_naddr,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));

    // Act
    registrationClient.register();

    // Assert
    assertEquals(0, registrationClient.attempts);
    assertEquals(3600, registrationClient.expire_time);
  }

  @Test
  void testRegister12() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addTtl(2);
    sipURI.addMaddr("42 Main St");
    SipProvider sip_provider = new SipProvider("File");
    NameAddress to_naddr = new NameAddress("Str");
    SipProvider sip_provider1 = new SipProvider("File");
    RegistrationClient registrationClient = new RegistrationClient(sip_provider, sipURI, to_naddr,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));

    // Act
    registrationClient.register();

    // Assert
    assertEquals(0, registrationClient.attempts);
    assertEquals(3600, registrationClient.expire_time);
  }

  @Test
  void testRegister13() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addMaddr("Content-Length");
    SipProvider sip_provider = new SipProvider("File");
    NameAddress to_naddr = new NameAddress("Str");
    SipProvider sip_provider1 = new SipProvider("File");
    RegistrationClient registrationClient = new RegistrationClient(sip_provider, sipURI, to_naddr,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));

    // Act
    registrationClient.register();

    // Assert
    assertEquals(0, registrationClient.attempts);
    assertEquals(3600, registrationClient.expire_time);
  }

  @Test
  void testRegister14() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    SipURI registrar = new SipURI("Uri");
    NameAddress to_naddr = new NameAddress("Str");
    SipProvider sip_provider1 = new SipProvider("File");
    RegistrationClient registrationClient = new RegistrationClient(sip_provider, registrar, to_naddr,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));

    // Act
    registrationClient.register(1);

    // Assert
    assertEquals(0, registrationClient.attempts);
    assertEquals(1, registrationClient.expire_time);
  }

  @Test
  void testRegister15() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    SipURI registrar = new SipURI("42");
    NameAddress to_naddr = new NameAddress("Str");
    SipProvider sip_provider1 = new SipProvider("File");
    RegistrationClient registrationClient = new RegistrationClient(sip_provider, registrar, to_naddr,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));

    // Act
    registrationClient.register(1);

    // Assert
    assertEquals(0, registrationClient.attempts);
    assertEquals(1, registrationClient.expire_time);
  }

  @Test
  void testRegister16() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    NameAddress to_naddr = new NameAddress("Str");
    SipProvider sip_provider1 = new SipProvider("File");
    RegistrationClient registrationClient = new RegistrationClient(sip_provider, null, to_naddr,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));

    // Act
    registrationClient.register(1);

    // Assert
    assertEquals(0, registrationClient.attempts);
    assertEquals(1, registrationClient.expire_time);
    assertEquals(1, registrationClient.sip_provider.getListeners().size());
  }

  @Test
  void testRegister17() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    SipURI registrar = new SipURI("localhost", 10);

    NameAddress to_naddr = new NameAddress("Str");
    SipProvider sip_provider1 = new SipProvider("File");
    RegistrationClient registrationClient = new RegistrationClient(sip_provider, registrar, to_naddr,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));

    // Act
    registrationClient.register(1);

    // Assert
    assertEquals(0, registrationClient.attempts);
    assertEquals(1, registrationClient.expire_time);
  }

  @Test
  void testRegister18() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    SipURI registrar = new SipURI("janedoe", "localhost");

    NameAddress to_naddr = new NameAddress("Str");
    SipProvider sip_provider1 = new SipProvider("File");
    RegistrationClient registrationClient = new RegistrationClient(sip_provider, registrar, to_naddr,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));

    // Act
    registrationClient.register(1);

    // Assert
    assertEquals(0, registrationClient.attempts);
    assertEquals(1, registrationClient.expire_time);
  }

  @Test
  void testRegister19() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addMaddr("42 Main St");
    SipProvider sip_provider = new SipProvider("File");
    NameAddress to_naddr = new NameAddress("Str");
    SipProvider sip_provider1 = new SipProvider("File");
    RegistrationClient registrationClient = new RegistrationClient(sip_provider, sipURI, to_naddr,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));

    // Act
    registrationClient.register(1);

    // Assert
    assertEquals(0, registrationClient.attempts);
    assertEquals(1, registrationClient.expire_time);
  }

  @Test
  void testRegister20() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addParameter("REGISTER");
    SipProvider sip_provider = new SipProvider("File");
    NameAddress to_naddr = new NameAddress("Str");
    SipProvider sip_provider1 = new SipProvider("File");
    RegistrationClient registrationClient = new RegistrationClient(sip_provider, sipURI, to_naddr,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));

    // Act
    registrationClient.register(1);

    // Assert
    assertEquals(0, registrationClient.attempts);
    assertEquals(1, registrationClient.expire_time);
  }

  @Test
  void testRegister21() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addTransport("alice.liddell@example.org");
    SipProvider sip_provider = new SipProvider("File");
    NameAddress to_naddr = new NameAddress("Str");
    SipProvider sip_provider1 = new SipProvider("File");
    RegistrationClient registrationClient = new RegistrationClient(sip_provider, sipURI, to_naddr,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));

    // Act
    registrationClient.register(1);

    // Assert
    assertEquals(0, registrationClient.attempts);
    assertEquals(1, registrationClient.expire_time);
  }

  @Test
  void testRegister22() {
    // Arrange
    SipURI sipURI = new SipURI("localhost", 10);
    sipURI.addMaddr("42 Main St");
    SipProvider sip_provider = new SipProvider("File");
    NameAddress to_naddr = new NameAddress("Str");
    SipProvider sip_provider1 = new SipProvider("File");
    RegistrationClient registrationClient = new RegistrationClient(sip_provider, sipURI, to_naddr,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));

    // Act
    registrationClient.register(1);

    // Assert
    assertEquals(0, registrationClient.attempts);
    assertEquals(1, registrationClient.expire_time);
  }

  @Test
  void testRegister23() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addMaddr("42 Main St");
    sipURI.addMaddr("42 Main St");
    SipProvider sip_provider = new SipProvider("File");
    NameAddress to_naddr = new NameAddress("Str");
    SipProvider sip_provider1 = new SipProvider("File");
    RegistrationClient registrationClient = new RegistrationClient(sip_provider, sipURI, to_naddr,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));

    // Act
    registrationClient.register(1);

    // Assert
    assertEquals(0, registrationClient.attempts);
    assertEquals(1, registrationClient.expire_time);
  }

  @Test
  void testRegister24() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addParameter("REGISTER");
    sipURI.addMaddr("42 Main St");
    SipProvider sip_provider = new SipProvider("File");
    NameAddress to_naddr = new NameAddress("Str");
    SipProvider sip_provider1 = new SipProvider("File");
    RegistrationClient registrationClient = new RegistrationClient(sip_provider, sipURI, to_naddr,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));

    // Act
    registrationClient.register(1);

    // Assert
    assertEquals(0, registrationClient.attempts);
    assertEquals(1, registrationClient.expire_time);
  }

  @Test
  void testRegister25() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addTtl(2);
    sipURI.addMaddr("42 Main St");
    SipProvider sip_provider = new SipProvider("File");
    NameAddress to_naddr = new NameAddress("Str");
    SipProvider sip_provider1 = new SipProvider("File");
    RegistrationClient registrationClient = new RegistrationClient(sip_provider, sipURI, to_naddr,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));

    // Act
    registrationClient.register(1);

    // Assert
    assertEquals(0, registrationClient.attempts);
    assertEquals(1, registrationClient.expire_time);
  }

  @Test
  void testRegister26() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addMaddr("Content-Length");
    SipProvider sip_provider = new SipProvider("File");
    NameAddress to_naddr = new NameAddress("Str");
    SipProvider sip_provider1 = new SipProvider("File");
    RegistrationClient registrationClient = new RegistrationClient(sip_provider, sipURI, to_naddr,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));

    // Act
    registrationClient.register(1);

    // Assert
    assertEquals(0, registrationClient.attempts);
    assertEquals(1, registrationClient.expire_time);
  }

  @Test
  void testRegister27() throws UnsupportedEncodingException {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    SipURI registrar = new SipURI("Uri");
    NameAddress to_naddr = new NameAddress("Str");
    SipProvider sip_provider1 = new SipProvider("File");
    RegistrationClient registrationClient = new RegistrationClient(sip_provider, registrar, to_naddr,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));

    // Act
    registrationClient.register(1, "Not all who wander are lost", "AAAAAAAA".getBytes("UTF-8"));

    // Assert
    assertEquals(0, registrationClient.attempts);
    assertEquals(1, registrationClient.expire_time);
  }

  @Test
  void testRegister28() throws UnsupportedEncodingException {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    SipURI registrar = new SipURI("42");
    NameAddress to_naddr = new NameAddress("Str");
    SipProvider sip_provider1 = new SipProvider("File");
    RegistrationClient registrationClient = new RegistrationClient(sip_provider, registrar, to_naddr,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));

    // Act
    registrationClient.register(1, "Not all who wander are lost", "AAAAAAAA".getBytes("UTF-8"));

    // Assert
    assertEquals(0, registrationClient.attempts);
    assertEquals(1, registrationClient.expire_time);
  }

  @Test
  void testRegister29() throws UnsupportedEncodingException {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    NameAddress to_naddr = new NameAddress("Str");
    SipProvider sip_provider1 = new SipProvider("File");
    RegistrationClient registrationClient = new RegistrationClient(sip_provider, null, to_naddr,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));

    // Act
    registrationClient.register(1, "Not all who wander are lost", "AAAAAAAA".getBytes("UTF-8"));

    // Assert
    assertEquals(0, registrationClient.attempts);
    assertEquals(1, registrationClient.expire_time);
    assertEquals(1, registrationClient.sip_provider.getListeners().size());
  }

  @Test
  void testRegister30() throws UnsupportedEncodingException {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    SipURI registrar = new SipURI("localhost", 10);

    NameAddress to_naddr = new NameAddress("Str");
    SipProvider sip_provider1 = new SipProvider("File");
    RegistrationClient registrationClient = new RegistrationClient(sip_provider, registrar, to_naddr,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));

    // Act
    registrationClient.register(1, "Not all who wander are lost", "AAAAAAAA".getBytes("UTF-8"));

    // Assert
    assertEquals(0, registrationClient.attempts);
    assertEquals(1, registrationClient.expire_time);
  }

  @Test
  void testRegister31() throws UnsupportedEncodingException {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    SipURI registrar = new SipURI("janedoe", "localhost");

    NameAddress to_naddr = new NameAddress("Str");
    SipProvider sip_provider1 = new SipProvider("File");
    RegistrationClient registrationClient = new RegistrationClient(sip_provider, registrar, to_naddr,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));

    // Act
    registrationClient.register(1, "Not all who wander are lost", "AAAAAAAA".getBytes("UTF-8"));

    // Assert
    assertEquals(0, registrationClient.attempts);
    assertEquals(1, registrationClient.expire_time);
  }

  @Test
  void testRegister32() throws UnsupportedEncodingException {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addMaddr("42 Main St");
    SipProvider sip_provider = new SipProvider("File");
    NameAddress to_naddr = new NameAddress("Str");
    SipProvider sip_provider1 = new SipProvider("File");
    RegistrationClient registrationClient = new RegistrationClient(sip_provider, sipURI, to_naddr,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));

    // Act
    registrationClient.register(1, "Not all who wander are lost", "AAAAAAAA".getBytes("UTF-8"));

    // Assert
    assertEquals(0, registrationClient.attempts);
    assertEquals(1, registrationClient.expire_time);
  }

  @Test
  void testRegister33() throws UnsupportedEncodingException {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addParameter("REGISTER");
    SipProvider sip_provider = new SipProvider("File");
    NameAddress to_naddr = new NameAddress("Str");
    SipProvider sip_provider1 = new SipProvider("File");
    RegistrationClient registrationClient = new RegistrationClient(sip_provider, sipURI, to_naddr,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));

    // Act
    registrationClient.register(1, "Not all who wander are lost", "AAAAAAAA".getBytes("UTF-8"));

    // Assert
    assertEquals(0, registrationClient.attempts);
    assertEquals(1, registrationClient.expire_time);
  }

  @Test
  void testRegister34() throws UnsupportedEncodingException {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addTransport("alice.liddell@example.org");
    SipProvider sip_provider = new SipProvider("File");
    NameAddress to_naddr = new NameAddress("Str");
    SipProvider sip_provider1 = new SipProvider("File");
    RegistrationClient registrationClient = new RegistrationClient(sip_provider, sipURI, to_naddr,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));

    // Act
    registrationClient.register(1, "Not all who wander are lost", "AAAAAAAA".getBytes("UTF-8"));

    // Assert
    assertEquals(0, registrationClient.attempts);
    assertEquals(1, registrationClient.expire_time);
  }

  @Test
  void testRegister35() throws UnsupportedEncodingException {
    // Arrange
    SipURI sipURI = new SipURI("localhost", 10);
    sipURI.addMaddr("42 Main St");
    SipProvider sip_provider = new SipProvider("File");
    NameAddress to_naddr = new NameAddress("Str");
    SipProvider sip_provider1 = new SipProvider("File");
    RegistrationClient registrationClient = new RegistrationClient(sip_provider, sipURI, to_naddr,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));

    // Act
    registrationClient.register(1, "Not all who wander are lost", "AAAAAAAA".getBytes("UTF-8"));

    // Assert
    assertEquals(0, registrationClient.attempts);
    assertEquals(1, registrationClient.expire_time);
  }

  @Test
  void testRegister36() throws UnsupportedEncodingException {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addMaddr("42 Main St");
    sipURI.addMaddr("42 Main St");
    SipProvider sip_provider = new SipProvider("File");
    NameAddress to_naddr = new NameAddress("Str");
    SipProvider sip_provider1 = new SipProvider("File");
    RegistrationClient registrationClient = new RegistrationClient(sip_provider, sipURI, to_naddr,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));

    // Act
    registrationClient.register(1, "Not all who wander are lost", "AAAAAAAA".getBytes("UTF-8"));

    // Assert
    assertEquals(0, registrationClient.attempts);
    assertEquals(1, registrationClient.expire_time);
  }

  @Test
  void testRegister37() throws UnsupportedEncodingException {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addParameter("REGISTER");
    sipURI.addMaddr("42 Main St");
    SipProvider sip_provider = new SipProvider("File");
    NameAddress to_naddr = new NameAddress("Str");
    SipProvider sip_provider1 = new SipProvider("File");
    RegistrationClient registrationClient = new RegistrationClient(sip_provider, sipURI, to_naddr,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));

    // Act
    registrationClient.register(1, "Not all who wander are lost", "AAAAAAAA".getBytes("UTF-8"));

    // Assert
    assertEquals(0, registrationClient.attempts);
    assertEquals(1, registrationClient.expire_time);
  }

  @Test
  void testRegister38() throws UnsupportedEncodingException {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addTtl(2);
    sipURI.addMaddr("42 Main St");
    SipProvider sip_provider = new SipProvider("File");
    NameAddress to_naddr = new NameAddress("Str");
    SipProvider sip_provider1 = new SipProvider("File");
    RegistrationClient registrationClient = new RegistrationClient(sip_provider, sipURI, to_naddr,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));

    // Act
    registrationClient.register(1, "Not all who wander are lost", "AAAAAAAA".getBytes("UTF-8"));

    // Assert
    assertEquals(0, registrationClient.attempts);
    assertEquals(1, registrationClient.expire_time);
  }

  @Test
  void testRegister39() throws UnsupportedEncodingException {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addMaddr("Content-Length");
    SipProvider sip_provider = new SipProvider("File");
    NameAddress to_naddr = new NameAddress("Str");
    SipProvider sip_provider1 = new SipProvider("File");
    RegistrationClient registrationClient = new RegistrationClient(sip_provider, sipURI, to_naddr,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));

    // Act
    registrationClient.register(1, "Not all who wander are lost", "AAAAAAAA".getBytes("UTF-8"));

    // Assert
    assertEquals(0, registrationClient.attempts);
    assertEquals(1, registrationClient.expire_time);
  }

  @Test
  void testUnregisterall() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    SipURI registrar = new SipURI("Uri");
    NameAddress to_naddr = new NameAddress("Str");
    SipProvider sip_provider1 = new SipProvider("File");
    RegistrationClient registrationClient = new RegistrationClient(sip_provider, registrar, to_naddr,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));

    // Act
    registrationClient.unregisterall();

    // Assert
    assertEquals(0, registrationClient.attempts);
  }

  @Test
  void testUnregisterall2() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    SipURI registrar = new SipURI("42");
    NameAddress to_naddr = new NameAddress("Str");
    SipProvider sip_provider1 = new SipProvider("File");
    RegistrationClient registrationClient = new RegistrationClient(sip_provider, registrar, to_naddr,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));

    // Act
    registrationClient.unregisterall();

    // Assert
    assertEquals(0, registrationClient.attempts);
  }

  @Test
  void testUnregisterall3() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    NameAddress to_naddr = new NameAddress("Str");
    SipProvider sip_provider1 = new SipProvider("File");
    RegistrationClient registrationClient = new RegistrationClient(sip_provider, null, to_naddr,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));

    // Act
    registrationClient.unregisterall();

    // Assert
    assertEquals(0, registrationClient.attempts);
    assertEquals(1, registrationClient.sip_provider.getListeners().size());
  }

  @Test
  void testUnregisterall4() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    SipURI registrar = new SipURI("localhost", 10);

    NameAddress to_naddr = new NameAddress("Str");
    SipProvider sip_provider1 = new SipProvider("File");
    RegistrationClient registrationClient = new RegistrationClient(sip_provider, registrar, to_naddr,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));

    // Act
    registrationClient.unregisterall();

    // Assert
    assertEquals(0, registrationClient.attempts);
  }

  @Test
  void testUnregisterall5() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    SipURI registrar = new SipURI("janedoe", "localhost");

    NameAddress to_naddr = new NameAddress("Str");
    SipProvider sip_provider1 = new SipProvider("File");
    RegistrationClient registrationClient = new RegistrationClient(sip_provider, registrar, to_naddr,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));

    // Act
    registrationClient.unregisterall();

    // Assert
    assertEquals(0, registrationClient.attempts);
  }

  @Test
  void testUnregisterall6() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addMaddr("42 Main St");
    SipProvider sip_provider = new SipProvider("File");
    NameAddress to_naddr = new NameAddress("Str");
    SipProvider sip_provider1 = new SipProvider("File");
    RegistrationClient registrationClient = new RegistrationClient(sip_provider, sipURI, to_naddr,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));

    // Act
    registrationClient.unregisterall();

    // Assert
    assertEquals(0, registrationClient.attempts);
  }

  @Test
  void testUnregisterall7() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addParameter("REGISTER");
    SipProvider sip_provider = new SipProvider("File");
    NameAddress to_naddr = new NameAddress("Str");
    SipProvider sip_provider1 = new SipProvider("File");
    RegistrationClient registrationClient = new RegistrationClient(sip_provider, sipURI, to_naddr,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));

    // Act
    registrationClient.unregisterall();

    // Assert
    assertEquals(0, registrationClient.attempts);
  }

  @Test
  void testUnregisterall8() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addTransport("alice.liddell@example.org");
    SipProvider sip_provider = new SipProvider("File");
    NameAddress to_naddr = new NameAddress("Str");
    SipProvider sip_provider1 = new SipProvider("File");
    RegistrationClient registrationClient = new RegistrationClient(sip_provider, sipURI, to_naddr,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));

    // Act
    registrationClient.unregisterall();

    // Assert
    assertEquals(0, registrationClient.attempts);
  }

  @Test
  void testUnregisterall9() {
    // Arrange
    SipURI sipURI = new SipURI("localhost", 10);
    sipURI.addMaddr("42 Main St");
    SipProvider sip_provider = new SipProvider("File");
    NameAddress to_naddr = new NameAddress("Str");
    SipProvider sip_provider1 = new SipProvider("File");
    RegistrationClient registrationClient = new RegistrationClient(sip_provider, sipURI, to_naddr,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));

    // Act
    registrationClient.unregisterall();

    // Assert
    assertEquals(0, registrationClient.attempts);
  }

  @Test
  void testUnregisterall10() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addMaddr("42 Main St");
    sipURI.addMaddr("42 Main St");
    SipProvider sip_provider = new SipProvider("File");
    NameAddress to_naddr = new NameAddress("Str");
    SipProvider sip_provider1 = new SipProvider("File");
    RegistrationClient registrationClient = new RegistrationClient(sip_provider, sipURI, to_naddr,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));

    // Act
    registrationClient.unregisterall();

    // Assert
    assertEquals(0, registrationClient.attempts);
  }

  @Test
  void testUnregisterall11() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addParameter("REGISTER");
    sipURI.addMaddr("42 Main St");
    SipProvider sip_provider = new SipProvider("File");
    NameAddress to_naddr = new NameAddress("Str");
    SipProvider sip_provider1 = new SipProvider("File");
    RegistrationClient registrationClient = new RegistrationClient(sip_provider, sipURI, to_naddr,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));

    // Act
    registrationClient.unregisterall();

    // Assert
    assertEquals(0, registrationClient.attempts);
  }

  @Test
  void testUnregisterall12() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addTtl(2);
    sipURI.addMaddr("42 Main St");
    SipProvider sip_provider = new SipProvider("File");
    NameAddress to_naddr = new NameAddress("Str");
    SipProvider sip_provider1 = new SipProvider("File");
    RegistrationClient registrationClient = new RegistrationClient(sip_provider, sipURI, to_naddr,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));

    // Act
    registrationClient.unregisterall();

    // Assert
    assertEquals(0, registrationClient.attempts);
  }

  @Test
  void testUnregisterall13() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addMaddr("Content-Length");
    SipProvider sip_provider = new SipProvider("File");
    NameAddress to_naddr = new NameAddress("Str");
    SipProvider sip_provider1 = new SipProvider("File");
    RegistrationClient registrationClient = new RegistrationClient(sip_provider, sipURI, to_naddr,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));

    // Act
    registrationClient.unregisterall();

    // Assert
    assertEquals(0, registrationClient.attempts);
  }

  @Test
  void testUnregister() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    SipURI registrar = new SipURI("Uri");
    NameAddress to_naddr = new NameAddress("Str");
    SipProvider sip_provider1 = new SipProvider("File");
    RegistrationClient registrationClient = new RegistrationClient(sip_provider, registrar, to_naddr,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));

    // Act
    registrationClient.unregister();

    // Assert
    assertEquals(0, registrationClient.attempts);
  }

  @Test
  void testUnregister2() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    SipURI registrar = new SipURI("42");
    NameAddress to_naddr = new NameAddress("Str");
    SipProvider sip_provider1 = new SipProvider("File");
    RegistrationClient registrationClient = new RegistrationClient(sip_provider, registrar, to_naddr,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));

    // Act
    registrationClient.unregister();

    // Assert
    assertEquals(0, registrationClient.attempts);
  }

  @Test
  void testUnregister3() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    NameAddress to_naddr = new NameAddress("Str");
    SipProvider sip_provider1 = new SipProvider("File");
    RegistrationClient registrationClient = new RegistrationClient(sip_provider, null, to_naddr,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));

    // Act
    registrationClient.unregister();

    // Assert
    assertEquals(0, registrationClient.attempts);
    assertEquals(1, registrationClient.sip_provider.getListeners().size());
  }

  @Test
  void testUnregister4() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    SipURI registrar = new SipURI("localhost", 10);

    NameAddress to_naddr = new NameAddress("Str");
    SipProvider sip_provider1 = new SipProvider("File");
    RegistrationClient registrationClient = new RegistrationClient(sip_provider, registrar, to_naddr,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));

    // Act
    registrationClient.unregister();

    // Assert
    assertEquals(0, registrationClient.attempts);
  }

  @Test
  void testUnregister5() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    SipURI registrar = new SipURI("janedoe", "localhost");

    NameAddress to_naddr = new NameAddress("Str");
    SipProvider sip_provider1 = new SipProvider("File");
    RegistrationClient registrationClient = new RegistrationClient(sip_provider, registrar, to_naddr,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));

    // Act
    registrationClient.unregister();

    // Assert
    assertEquals(0, registrationClient.attempts);
  }

  @Test
  void testUnregister6() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addMaddr("42 Main St");
    SipProvider sip_provider = new SipProvider("File");
    NameAddress to_naddr = new NameAddress("Str");
    SipProvider sip_provider1 = new SipProvider("File");
    RegistrationClient registrationClient = new RegistrationClient(sip_provider, sipURI, to_naddr,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));

    // Act
    registrationClient.unregister();

    // Assert
    assertEquals(0, registrationClient.attempts);
  }

  @Test
  void testUnregister7() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addParameter("REGISTER");
    SipProvider sip_provider = new SipProvider("File");
    NameAddress to_naddr = new NameAddress("Str");
    SipProvider sip_provider1 = new SipProvider("File");
    RegistrationClient registrationClient = new RegistrationClient(sip_provider, sipURI, to_naddr,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));

    // Act
    registrationClient.unregister();

    // Assert
    assertEquals(0, registrationClient.attempts);
  }

  @Test
  void testUnregister8() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addTransport("alice.liddell@example.org");
    SipProvider sip_provider = new SipProvider("File");
    NameAddress to_naddr = new NameAddress("Str");
    SipProvider sip_provider1 = new SipProvider("File");
    RegistrationClient registrationClient = new RegistrationClient(sip_provider, sipURI, to_naddr,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));

    // Act
    registrationClient.unregister();

    // Assert
    assertEquals(0, registrationClient.attempts);
  }

  @Test
  void testUnregister9() {
    // Arrange
    SipURI sipURI = new SipURI("localhost", 10);
    sipURI.addMaddr("42 Main St");
    SipProvider sip_provider = new SipProvider("File");
    NameAddress to_naddr = new NameAddress("Str");
    SipProvider sip_provider1 = new SipProvider("File");
    RegistrationClient registrationClient = new RegistrationClient(sip_provider, sipURI, to_naddr,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));

    // Act
    registrationClient.unregister();

    // Assert
    assertEquals(0, registrationClient.attempts);
  }

  @Test
  void testUnregister10() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addMaddr("42 Main St");
    sipURI.addMaddr("42 Main St");
    SipProvider sip_provider = new SipProvider("File");
    NameAddress to_naddr = new NameAddress("Str");
    SipProvider sip_provider1 = new SipProvider("File");
    RegistrationClient registrationClient = new RegistrationClient(sip_provider, sipURI, to_naddr,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));

    // Act
    registrationClient.unregister();

    // Assert
    assertEquals(0, registrationClient.attempts);
  }

  @Test
  void testUnregister11() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addParameter("REGISTER");
    sipURI.addMaddr("42 Main St");
    SipProvider sip_provider = new SipProvider("File");
    NameAddress to_naddr = new NameAddress("Str");
    SipProvider sip_provider1 = new SipProvider("File");
    RegistrationClient registrationClient = new RegistrationClient(sip_provider, sipURI, to_naddr,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));

    // Act
    registrationClient.unregister();

    // Assert
    assertEquals(0, registrationClient.attempts);
  }

  @Test
  void testUnregister12() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addTtl(2);
    sipURI.addMaddr("42 Main St");
    SipProvider sip_provider = new SipProvider("File");
    NameAddress to_naddr = new NameAddress("Str");
    SipProvider sip_provider1 = new SipProvider("File");
    RegistrationClient registrationClient = new RegistrationClient(sip_provider, sipURI, to_naddr,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));

    // Act
    registrationClient.unregister();

    // Assert
    assertEquals(0, registrationClient.attempts);
  }

  @Test
  void testUnregister13() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addMaddr("Content-Length");
    SipProvider sip_provider = new SipProvider("File");
    NameAddress to_naddr = new NameAddress("Str");
    SipProvider sip_provider1 = new SipProvider("File");
    RegistrationClient registrationClient = new RegistrationClient(sip_provider, sipURI, to_naddr,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));

    // Act
    registrationClient.unregister();

    // Assert
    assertEquals(0, registrationClient.attempts);
  }

  @Test
  void testLoopRegister() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    SipURI registrar = new SipURI("Uri");
    NameAddress to_naddr = new NameAddress("Str");
    SipProvider sip_provider1 = new SipProvider("File");
    RegistrationClient registrationClient = new RegistrationClient(sip_provider, registrar, to_naddr,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));

    // Act
    registrationClient.loopRegister(1, 1);

    // Assert
    assertEquals(0, registrationClient.attempts);
    assertNull(registrationClient.registration_to);
    assertEquals(1, registrationClient.renew_time);
    assertTrue(registrationClient.loop);
    assertNull(registrationClient.attempt_to);
    assertEquals(1, registrationClient.expire_time);
  }

  @Test
  void testLoopRegister2() {
    // Arrange
    SipProvider sip_provider = new SipProvider("REGISTER");
    SipURI registrar = new SipURI("Uri");
    NameAddress to_naddr = new NameAddress("Str");
    SipProvider sip_provider1 = new SipProvider("File");
    RegistrationClient registrationClient = new RegistrationClient(sip_provider, registrar, to_naddr,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));

    // Act
    registrationClient.loopRegister(1, 1);

    // Assert
    assertEquals(0, registrationClient.attempts);
    assertNull(registrationClient.registration_to);
    assertEquals(1, registrationClient.renew_time);
    assertTrue(registrationClient.loop);
    assertNull(registrationClient.attempt_to);
    assertEquals(1, registrationClient.expire_time);
  }

  @Test
  void testLoopRegister3() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    SipURI registrar = new SipURI("42");
    NameAddress to_naddr = new NameAddress("Str");
    SipProvider sip_provider1 = new SipProvider("File");
    RegistrationClient registrationClient = new RegistrationClient(sip_provider, registrar, to_naddr,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));

    // Act
    registrationClient.loopRegister(1, 1);

    // Assert
    assertEquals(0, registrationClient.attempts);
    assertNull(registrationClient.registration_to);
    assertEquals(1, registrationClient.renew_time);
    assertTrue(registrationClient.loop);
    assertNull(registrationClient.attempt_to);
    assertEquals(1, registrationClient.expire_time);
  }

  @Test
  void testLoopRegister4() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    NameAddress to_naddr = new NameAddress("Str");
    SipProvider sip_provider1 = new SipProvider("File");
    RegistrationClient registrationClient = new RegistrationClient(sip_provider, null, to_naddr,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));

    // Act
    registrationClient.loopRegister(1, 1);

    // Assert
    assertEquals(0, registrationClient.attempts);
    assertNull(registrationClient.registration_to);
    assertEquals(1, registrationClient.renew_time);
    assertTrue(registrationClient.loop);
    assertNull(registrationClient.attempt_to);
    assertEquals(1, registrationClient.expire_time);
    assertEquals(1, registrationClient.sip_provider.getListeners().size());
  }

  @Test
  void testLoopRegister5() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    SipURI registrar = new SipURI("localhost", 10);

    NameAddress to_naddr = new NameAddress("Str");
    SipProvider sip_provider1 = new SipProvider("File");
    RegistrationClient registrationClient = new RegistrationClient(sip_provider, registrar, to_naddr,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));

    // Act
    registrationClient.loopRegister(1, 1);

    // Assert
    assertEquals(0, registrationClient.attempts);
    assertNull(registrationClient.registration_to);
    assertEquals(1, registrationClient.renew_time);
    assertTrue(registrationClient.loop);
    assertNull(registrationClient.attempt_to);
    assertEquals(1, registrationClient.expire_time);
  }

  @Test
  void testLoopRegister6() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    SipURI registrar = new SipURI("janedoe", "localhost");

    NameAddress to_naddr = new NameAddress("Str");
    SipProvider sip_provider1 = new SipProvider("File");
    RegistrationClient registrationClient = new RegistrationClient(sip_provider, registrar, to_naddr,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));

    // Act
    registrationClient.loopRegister(1, 1);

    // Assert
    assertEquals(0, registrationClient.attempts);
    assertNull(registrationClient.registration_to);
    assertEquals(1, registrationClient.renew_time);
    assertTrue(registrationClient.loop);
    assertNull(registrationClient.attempt_to);
    assertEquals(1, registrationClient.expire_time);
  }

  @Test
  void testLoopRegister7() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addMaddr("42 Main St");
    SipProvider sip_provider = new SipProvider("File");
    NameAddress to_naddr = new NameAddress("Str");
    SipProvider sip_provider1 = new SipProvider("File");
    RegistrationClient registrationClient = new RegistrationClient(sip_provider, sipURI, to_naddr,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));

    // Act
    registrationClient.loopRegister(1, 1);

    // Assert
    assertEquals(0, registrationClient.attempts);
    assertNull(registrationClient.registration_to);
    assertEquals(1, registrationClient.renew_time);
    assertTrue(registrationClient.loop);
    assertNull(registrationClient.attempt_to);
    assertEquals(1, registrationClient.expire_time);
  }

  @Test
  void testLoopRegister8() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addParameter("REGISTER");
    SipProvider sip_provider = new SipProvider("File");
    NameAddress to_naddr = new NameAddress("Str");
    SipProvider sip_provider1 = new SipProvider("File");
    RegistrationClient registrationClient = new RegistrationClient(sip_provider, sipURI, to_naddr,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));

    // Act
    registrationClient.loopRegister(1, 1);

    // Assert
    assertEquals(0, registrationClient.attempts);
    assertNull(registrationClient.registration_to);
    assertEquals(1, registrationClient.renew_time);
    assertTrue(registrationClient.loop);
    assertNull(registrationClient.attempt_to);
    assertEquals(1, registrationClient.expire_time);
  }

  @Test
  void testLoopRegister9() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addTransport("alice.liddell@example.org");
    SipProvider sip_provider = new SipProvider("File");
    NameAddress to_naddr = new NameAddress("Str");
    SipProvider sip_provider1 = new SipProvider("File");
    RegistrationClient registrationClient = new RegistrationClient(sip_provider, sipURI, to_naddr,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));

    // Act
    registrationClient.loopRegister(1, 1);

    // Assert
    assertEquals(0, registrationClient.attempts);
    assertNull(registrationClient.registration_to);
    assertEquals(1, registrationClient.renew_time);
    assertTrue(registrationClient.loop);
    assertNull(registrationClient.attempt_to);
    assertEquals(1, registrationClient.expire_time);
  }

  @Test
  void testLoopRegister10() {
    // Arrange
    SipURI sipURI = new SipURI("localhost", 10);
    sipURI.addMaddr("42 Main St");
    SipProvider sip_provider = new SipProvider("File");
    NameAddress to_naddr = new NameAddress("Str");
    SipProvider sip_provider1 = new SipProvider("File");
    RegistrationClient registrationClient = new RegistrationClient(sip_provider, sipURI, to_naddr,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));

    // Act
    registrationClient.loopRegister(1, 1);

    // Assert
    assertEquals(0, registrationClient.attempts);
    assertNull(registrationClient.registration_to);
    assertEquals(1, registrationClient.renew_time);
    assertTrue(registrationClient.loop);
    assertNull(registrationClient.attempt_to);
    assertEquals(1, registrationClient.expire_time);
  }

  @Test
  void testLoopRegister11() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addMaddr("42 Main St");
    sipURI.addMaddr("42 Main St");
    SipProvider sip_provider = new SipProvider("File");
    NameAddress to_naddr = new NameAddress("Str");
    SipProvider sip_provider1 = new SipProvider("File");
    RegistrationClient registrationClient = new RegistrationClient(sip_provider, sipURI, to_naddr,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));

    // Act
    registrationClient.loopRegister(1, 1);

    // Assert
    assertEquals(0, registrationClient.attempts);
    assertNull(registrationClient.registration_to);
    assertEquals(1, registrationClient.renew_time);
    assertTrue(registrationClient.loop);
    assertNull(registrationClient.attempt_to);
    assertEquals(1, registrationClient.expire_time);
  }

  @Test
  void testLoopRegister12() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addParameter("REGISTER");
    sipURI.addMaddr("42 Main St");
    SipProvider sip_provider = new SipProvider("File");
    NameAddress to_naddr = new NameAddress("Str");
    SipProvider sip_provider1 = new SipProvider("File");
    RegistrationClient registrationClient = new RegistrationClient(sip_provider, sipURI, to_naddr,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));

    // Act
    registrationClient.loopRegister(1, 1);

    // Assert
    assertEquals(0, registrationClient.attempts);
    assertNull(registrationClient.registration_to);
    assertEquals(1, registrationClient.renew_time);
    assertTrue(registrationClient.loop);
    assertNull(registrationClient.attempt_to);
    assertEquals(1, registrationClient.expire_time);
  }

  @Test
  void testLoopRegister13() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addTtl(2);
    sipURI.addMaddr("42 Main St");
    SipProvider sip_provider = new SipProvider("File");
    NameAddress to_naddr = new NameAddress("Str");
    SipProvider sip_provider1 = new SipProvider("File");
    RegistrationClient registrationClient = new RegistrationClient(sip_provider, sipURI, to_naddr,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));

    // Act
    registrationClient.loopRegister(1, 1);

    // Assert
    assertEquals(0, registrationClient.attempts);
    assertNull(registrationClient.registration_to);
    assertEquals(1, registrationClient.renew_time);
    assertTrue(registrationClient.loop);
    assertNull(registrationClient.attempt_to);
    assertEquals(1, registrationClient.expire_time);
  }

  @Test
  void testLoopRegister14() {
    // Arrange
    SipURI sipURI = new SipURI("Uri");
    sipURI.addMaddr("Content-Length");
    SipProvider sip_provider = new SipProvider("File");
    NameAddress to_naddr = new NameAddress("Str");
    SipProvider sip_provider1 = new SipProvider("File");
    RegistrationClient registrationClient = new RegistrationClient(sip_provider, sipURI, to_naddr,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));

    // Act
    registrationClient.loopRegister(1, 1);

    // Assert
    assertEquals(0, registrationClient.attempts);
    assertNull(registrationClient.registration_to);
    assertEquals(1, registrationClient.renew_time);
    assertTrue(registrationClient.loop);
    assertNull(registrationClient.attempt_to);
    assertEquals(1, registrationClient.expire_time);
  }

  @Test
  void testHalt() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    SipURI registrar = new SipURI("Uri");
    NameAddress to_naddr = new NameAddress("Str");
    SipProvider sip_provider1 = new SipProvider("File");
    RegistrationClient registrationClient = new RegistrationClient(sip_provider, registrar, to_naddr,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));

    // Act
    registrationClient.halt();

    // Assert that nothing has changed
    NameAddress expectedTargetAOR = registrationClient.to_naddr;
    NameAddress targetAOR = registrationClient.getTargetAOR();
    assertSame(expectedTargetAOR, targetAOR);
    assertFalse(registrationClient.isRegistering());
    assertEquals(0, registrationClient.attempts);
    assertTrue(registrationClient.listener instanceof MessageAgentCli);
    assertEquals(3600, registrationClient.renew_time);
    assertFalse(registrationClient.loop);
    assertEquals(3600, registrationClient.expire_time);
    assertSame(targetAOR, registrationClient.from_naddr);
  }

  @Test
  void testOnTimeout() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    SipURI registrar = new SipURI("Uri");
    NameAddress to_naddr = new NameAddress("Str");
    SipProvider sip_provider1 = new SipProvider("File");
    RegistrationClient registrationClient = new RegistrationClient(sip_provider, registrar, to_naddr,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));
    Timer timer = new Timer(10L, mock(TimerListener.class));

    // Act
    registrationClient.onTimeout(timer);

    // Assert that nothing has changed
    assertFalse(timer.isRunning());
    assertEquals(10L, timer.getTime());
    NameAddress expectedTargetAOR = registrationClient.to_naddr;
    NameAddress targetAOR = registrationClient.getTargetAOR();
    assertSame(expectedTargetAOR, targetAOR);
    assertFalse(registrationClient.isRegistering());
    assertEquals(0, registrationClient.attempts);
    assertTrue(registrationClient.listener instanceof MessageAgentCli);
    assertEquals(3600, registrationClient.renew_time);
    assertFalse(registrationClient.loop);
    assertEquals(3600, registrationClient.expire_time);
    assertSame(targetAOR, registrationClient.from_naddr);
  }

  @Test
  void testRun() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    SipURI registrar = new SipURI("Uri");
    NameAddress to_naddr = new NameAddress("Str");
    SipProvider sip_provider1 = new SipProvider("File");
    RegistrationClient registrationClient = new RegistrationClient(sip_provider, registrar, to_naddr,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));

    // Act
    registrationClient.run();

    // Assert
    assertFalse(registrationClient.isRegistering());
  }

  @Test
  void testLog() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    SipURI registrar = new SipURI("Uri");
    NameAddress to_naddr = new NameAddress("Str");
    SipProvider sip_provider1 = new SipProvider("File");
    RegistrationClient registrationClient = new RegistrationClient(sip_provider, registrar, to_naddr,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));
    LogLevel logLevel = new LogLevel("Name", 42);

    // Act
    registrationClient.log(logLevel, new Exception("An error occurred"));

    // Assert that nothing has changed
    assertEquals("Name", logLevel.getName());
    assertEquals(42, logLevel.getValue());
    NameAddress expectedTargetAOR = registrationClient.to_naddr;
    NameAddress targetAOR = registrationClient.getTargetAOR();
    assertSame(expectedTargetAOR, targetAOR);
    assertFalse(registrationClient.isRegistering());
    assertEquals(0, registrationClient.attempts);
    assertTrue(registrationClient.listener instanceof MessageAgentCli);
    assertEquals(3600, registrationClient.renew_time);
    assertFalse(registrationClient.loop);
    assertEquals(3600, registrationClient.expire_time);
    assertSame(targetAOR, registrationClient.from_naddr);
  }

  @Test
  void testLog2() {
    // Arrange
    SipProvider sip_provider = new SipProvider("File");
    SipURI registrar = new SipURI("Uri");
    NameAddress to_naddr = new NameAddress("Str");
    SipProvider sip_provider1 = new SipProvider("File");
    RegistrationClient registrationClient = new RegistrationClient(sip_provider, registrar, to_naddr,
        new MessageAgentCli(sip_provider1, new UserAgentProfile()));
    LogLevel logLevel = new LogLevel("Name", 42);

    // Act
    registrationClient.log(logLevel, "Str");

    // Assert that nothing has changed
    assertEquals("Name", logLevel.getName());
    assertEquals(42, logLevel.getValue());
    NameAddress expectedTargetAOR = registrationClient.to_naddr;
    NameAddress targetAOR = registrationClient.getTargetAOR();
    assertSame(expectedTargetAOR, targetAOR);
    assertFalse(registrationClient.isRegistering());
    assertEquals(0, registrationClient.attempts);
    assertTrue(registrationClient.listener instanceof MessageAgentCli);
    assertEquals(3600, registrationClient.renew_time);
    assertFalse(registrationClient.loop);
    assertEquals(3600, registrationClient.expire_time);
    assertSame(targetAOR, registrationClient.from_naddr);
  }
}

