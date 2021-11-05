package org.mjsip.ua;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.mjsip.sip.address.GenericURI;
import org.mjsip.sip.address.NameAddress;
import org.mjsip.sip.address.SipURI;
import org.mjsip.sip.provider.SipProvider;

class UserAgentProfileDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    UserAgentProfile actualUserAgentProfile = new UserAgentProfile();

    // Assert
    assertEquals(4080, actualUserAgentProfile.getMediaPort());
    assertNull(actualUserAgentProfile.video_mcast_soaddr);
    assertFalse(actualUserAgentProfile.video);
    assertNull(actualUserAgentProfile.user);
    assertFalse(actualUserAgentProfile.use_vic);
    assertFalse(actualUserAgentProfile.use_rat);
    assertTrue(actualUserAgentProfile.use_jmf_video);
    assertFalse(actualUserAgentProfile.use_jmf_audio);
    assertTrue(actualUserAgentProfile.ua_server);
    assertNull(actualUserAgentProfile.ua_address);
    assertNull(actualUserAgentProfile.transfer_to);
    assertEquals(-1, actualUserAgentProfile.transfer_time);
    assertFalse(actualUserAgentProfile.symmetric_rtp);
    assertNull(actualUserAgentProfile.send_video_file);
    assertFalse(actualUserAgentProfile.send_tone);
    assertFalse(actualUserAgentProfile.send_only);
    assertNull(actualUserAgentProfile.send_file);
    assertNull(actualUserAgentProfile.registrar);
    assertEquals(20, actualUserAgentProfile.refuse_time);
    assertNull(actualUserAgentProfile.redirect_to);
    assertNull(actualUserAgentProfile.recv_video_file);
    assertFalse(actualUserAgentProfile.recv_only);
    assertNull(actualUserAgentProfile.recv_file);
    assertEquals(-1, actualUserAgentProfile.re_invite_time);
    assertEquals(-1, actualUserAgentProfile.re_call_time);
    assertEquals(-1, actualUserAgentProfile.re_call_count);
    assertEquals(20, actualUserAgentProfile.random_early_drop_rate);
    assertNull(actualUserAgentProfile.proxy);
    assertTrue(actualUserAgentProfile.options_server);
    assertTrue(actualUserAgentProfile.null_server);
    assertFalse(actualUserAgentProfile.no_system_audio);
    assertFalse(actualUserAgentProfile.no_prompt);
    assertFalse(actualUserAgentProfile.no_offer);
    assertEquals(1, actualUserAgentProfile.media_descs.length);
    assertNull(actualUserAgentProfile.media_addr);
    assertFalse(actualUserAgentProfile.loopback);
    assertEquals(0L, actualUserAgentProfile.keepalive_time);
    assertTrue(actualUserAgentProfile.javax_sound_sync);
    assertNull(actualUserAgentProfile.javax_sound_streamer);
    assertFalse(actualUserAgentProfile.javax_sound_direct_convertion);
    assertEquals(-1, actualUserAgentProfile.hangup_time);
    assertEquals(3600, actualUserAgentProfile.expires);
    assertFalse(actualUserAgentProfile.do_unregister_all);
    assertFalse(actualUserAgentProfile.do_unregister);
    assertFalse(actualUserAgentProfile.do_register);
    assertNull(actualUserAgentProfile.display_name);
    assertNull(actualUserAgentProfile.call_to);
    assertEquals("vic", actualUserAgentProfile.bin_vic);
    assertEquals("rat", actualUserAgentProfile.bin_rat);
    assertNull(actualUserAgentProfile.auth_user);
    assertNull(actualUserAgentProfile.auth_realm);
    assertNull(actualUserAgentProfile.auth_passwd);
    assertNull(actualUserAgentProfile.audio_mcast_soaddr);
    assertTrue(actualUserAgentProfile.audio);
    assertEquals(-1, actualUserAgentProfile.accept_time);
  }

  @Test
  void testConstructor2() {
    // Arrange and Act
    UserAgentProfile actualUserAgentProfile = new UserAgentProfile("File");

    // Assert
    assertEquals(4080, actualUserAgentProfile.getMediaPort());
    assertNull(actualUserAgentProfile.video_mcast_soaddr);
    assertFalse(actualUserAgentProfile.video);
    assertNull(actualUserAgentProfile.user);
    assertFalse(actualUserAgentProfile.use_vic);
    assertFalse(actualUserAgentProfile.use_rat);
    assertTrue(actualUserAgentProfile.use_jmf_video);
    assertFalse(actualUserAgentProfile.use_jmf_audio);
    assertTrue(actualUserAgentProfile.ua_server);
    assertNull(actualUserAgentProfile.ua_address);
    assertNull(actualUserAgentProfile.transfer_to);
    assertEquals(-1, actualUserAgentProfile.transfer_time);
    assertFalse(actualUserAgentProfile.symmetric_rtp);
    assertNull(actualUserAgentProfile.send_video_file);
    assertFalse(actualUserAgentProfile.send_tone);
    assertFalse(actualUserAgentProfile.send_only);
    assertNull(actualUserAgentProfile.send_file);
    assertNull(actualUserAgentProfile.registrar);
    assertEquals(20, actualUserAgentProfile.refuse_time);
    assertNull(actualUserAgentProfile.redirect_to);
    assertNull(actualUserAgentProfile.recv_video_file);
    assertFalse(actualUserAgentProfile.recv_only);
    assertNull(actualUserAgentProfile.recv_file);
    assertEquals(-1, actualUserAgentProfile.re_invite_time);
    assertEquals(-1, actualUserAgentProfile.re_call_time);
    assertEquals(-1, actualUserAgentProfile.re_call_count);
    assertEquals(20, actualUserAgentProfile.random_early_drop_rate);
    assertNull(actualUserAgentProfile.proxy);
    assertTrue(actualUserAgentProfile.options_server);
    assertTrue(actualUserAgentProfile.null_server);
    assertFalse(actualUserAgentProfile.no_system_audio);
    assertFalse(actualUserAgentProfile.no_prompt);
    assertFalse(actualUserAgentProfile.no_offer);
    assertEquals(1, actualUserAgentProfile.media_descs.length);
    assertNull(actualUserAgentProfile.media_addr);
    assertFalse(actualUserAgentProfile.loopback);
    assertEquals(0L, actualUserAgentProfile.keepalive_time);
    assertTrue(actualUserAgentProfile.javax_sound_sync);
    assertNull(actualUserAgentProfile.javax_sound_streamer);
    assertFalse(actualUserAgentProfile.javax_sound_direct_convertion);
    assertEquals(-1, actualUserAgentProfile.hangup_time);
    assertEquals(3600, actualUserAgentProfile.expires);
    assertFalse(actualUserAgentProfile.do_unregister_all);
    assertFalse(actualUserAgentProfile.do_unregister);
    assertFalse(actualUserAgentProfile.do_register);
    assertNull(actualUserAgentProfile.display_name);
    assertNull(actualUserAgentProfile.call_to);
    assertEquals("vic", actualUserAgentProfile.bin_vic);
    assertEquals("rat", actualUserAgentProfile.bin_rat);
    assertNull(actualUserAgentProfile.auth_user);
    assertNull(actualUserAgentProfile.auth_realm);
    assertNull(actualUserAgentProfile.auth_passwd);
    assertNull(actualUserAgentProfile.audio_mcast_soaddr);
    assertTrue(actualUserAgentProfile.audio);
    assertEquals(-1, actualUserAgentProfile.accept_time);
  }

  @Test
  void testConstructor3() {
    // Arrange and Act
    UserAgentProfile actualUserAgentProfile = new UserAgentProfile(null);

    // Assert
    assertEquals(4080, actualUserAgentProfile.getMediaPort());
    assertNull(actualUserAgentProfile.video_mcast_soaddr);
    assertFalse(actualUserAgentProfile.video);
    assertNull(actualUserAgentProfile.user);
    assertFalse(actualUserAgentProfile.use_vic);
    assertFalse(actualUserAgentProfile.use_rat);
    assertTrue(actualUserAgentProfile.use_jmf_video);
    assertFalse(actualUserAgentProfile.use_jmf_audio);
    assertTrue(actualUserAgentProfile.ua_server);
    assertNull(actualUserAgentProfile.ua_address);
    assertNull(actualUserAgentProfile.transfer_to);
    assertEquals(-1, actualUserAgentProfile.transfer_time);
    assertFalse(actualUserAgentProfile.symmetric_rtp);
    assertNull(actualUserAgentProfile.send_video_file);
    assertFalse(actualUserAgentProfile.send_tone);
    assertFalse(actualUserAgentProfile.send_only);
    assertNull(actualUserAgentProfile.send_file);
    assertNull(actualUserAgentProfile.registrar);
    assertEquals(20, actualUserAgentProfile.refuse_time);
    assertNull(actualUserAgentProfile.redirect_to);
    assertNull(actualUserAgentProfile.recv_video_file);
    assertFalse(actualUserAgentProfile.recv_only);
    assertNull(actualUserAgentProfile.recv_file);
    assertEquals(-1, actualUserAgentProfile.re_invite_time);
    assertEquals(-1, actualUserAgentProfile.re_call_time);
    assertEquals(-1, actualUserAgentProfile.re_call_count);
    assertEquals(20, actualUserAgentProfile.random_early_drop_rate);
    assertNull(actualUserAgentProfile.proxy);
    assertTrue(actualUserAgentProfile.options_server);
    assertTrue(actualUserAgentProfile.null_server);
    assertFalse(actualUserAgentProfile.no_system_audio);
    assertFalse(actualUserAgentProfile.no_prompt);
    assertFalse(actualUserAgentProfile.no_offer);
    assertEquals(1, actualUserAgentProfile.media_descs.length);
    assertNull(actualUserAgentProfile.media_addr);
    assertFalse(actualUserAgentProfile.loopback);
    assertEquals(0L, actualUserAgentProfile.keepalive_time);
    assertTrue(actualUserAgentProfile.javax_sound_sync);
    assertNull(actualUserAgentProfile.javax_sound_streamer);
    assertFalse(actualUserAgentProfile.javax_sound_direct_convertion);
    assertEquals(-1, actualUserAgentProfile.hangup_time);
    assertEquals(3600, actualUserAgentProfile.expires);
    assertFalse(actualUserAgentProfile.do_unregister_all);
    assertFalse(actualUserAgentProfile.do_unregister);
    assertFalse(actualUserAgentProfile.do_register);
    assertNull(actualUserAgentProfile.display_name);
    assertNull(actualUserAgentProfile.call_to);
    assertEquals("vic", actualUserAgentProfile.bin_vic);
    assertEquals("rat", actualUserAgentProfile.bin_rat);
    assertNull(actualUserAgentProfile.auth_user);
    assertNull(actualUserAgentProfile.auth_realm);
    assertNull(actualUserAgentProfile.auth_passwd);
    assertNull(actualUserAgentProfile.audio_mcast_soaddr);
    assertTrue(actualUserAgentProfile.audio);
    assertEquals(-1, actualUserAgentProfile.accept_time);
  }

  @Test
  void testGetUserURI() {
    // Arrange and Act
    NameAddress actualUserURI = (new UserAgentProfile()).getUserURI();

    // Assert
    assertEquals("<sip:null>", actualUserURI.toString());
    assertFalse(actualUserURI.hasDisplayName());
    GenericURI address = actualUserURI.getAddress();
    assertFalse(address.isTelURI());
    assertFalse(((SipURI) address).isSecure());
  }

  @Test
  void testGetUserURI2() {
    // Arrange and Act
    NameAddress actualUserURI = (new UserAgentProfile("File")).getUserURI();

    // Assert
    assertEquals("<sip:null>", actualUserURI.toString());
    assertFalse(actualUserURI.hasDisplayName());
    GenericURI address = actualUserURI.getAddress();
    assertFalse(address.isTelURI());
    assertFalse(((SipURI) address).isSecure());
  }

  @Test
  void testGetUserURI3() {
    // Arrange and Act
    NameAddress actualUserURI = (new UserAgentProfile(null)).getUserURI();

    // Assert
    assertEquals("<sip:null>", actualUserURI.toString());
    assertFalse(actualUserURI.hasDisplayName());
    GenericURI address = actualUserURI.getAddress();
    assertFalse(address.isTelURI());
    assertFalse(((SipURI) address).isSecure());
  }

  @Test
  void testGetUserURI4() {
    // Arrange
    UserAgentProfile userAgentProfile = new UserAgentProfile();
    userAgentProfile.ua_address = "42 Main St";

    // Act
    NameAddress actualUserURI = userAgentProfile.getUserURI();

    // Assert
    assertEquals("<sip:42 Main St>", actualUserURI.toString());
    assertFalse(actualUserURI.hasDisplayName());
    GenericURI address = actualUserURI.getAddress();
    assertFalse(address.isTelURI());
    assertFalse(((SipURI) address).isSecure());
  }

  @Test
  void testSetUserURI() {
    // Arrange
    UserAgentProfile userAgentProfile = new UserAgentProfile();

    // Act
    userAgentProfile.setUserURI(new NameAddress("Str"));

    // Assert
    assertNull(userAgentProfile.user);
    assertEquals("Str", userAgentProfile.registrar);
    assertEquals("Str", userAgentProfile.proxy);
    assertNull(userAgentProfile.display_name);
  }

  @Test
  void testSetUserURI2() {
    // Arrange
    UserAgentProfile userAgentProfile = new UserAgentProfile("File");

    // Act
    userAgentProfile.setUserURI(new NameAddress("Str"));

    // Assert
    assertNull(userAgentProfile.user);
    assertEquals("Str", userAgentProfile.registrar);
    assertEquals("Str", userAgentProfile.proxy);
    assertNull(userAgentProfile.display_name);
  }

  @Test
  void testSetUserURI3() {
    // Arrange
    UserAgentProfile userAgentProfile = new UserAgentProfile();

    // Act
    userAgentProfile.setUserURI(new NameAddress(""));

    // Assert
    assertNull(userAgentProfile.user);
    assertEquals("", userAgentProfile.registrar);
    assertEquals("", userAgentProfile.proxy);
    assertNull(userAgentProfile.display_name);
  }

  @Test
  void testSetUserURI4() {
    // Arrange
    UserAgentProfile userAgentProfile = new UserAgentProfile(null);

    // Act
    userAgentProfile.setUserURI(new NameAddress("Str"));

    // Assert
    assertNull(userAgentProfile.user);
    assertEquals("Str", userAgentProfile.registrar);
    assertEquals("Str", userAgentProfile.proxy);
    assertNull(userAgentProfile.display_name);
  }

  @Test
  void testSetMediaPort() {
    // Arrange
    UserAgentProfile userAgentProfile = new UserAgentProfile();

    // Act
    userAgentProfile.setMediaPort(8080);

    // Assert
    assertEquals(8080, userAgentProfile.getMediaPort());
  }

  @Test
  void testSetMediaPort2() {
    // Arrange
    UserAgentProfile userAgentProfile = new UserAgentProfile("File");

    // Act
    userAgentProfile.setMediaPort(8080);

    // Assert
    assertEquals(8080, userAgentProfile.getMediaPort());
  }

  @Test
  void testSetMediaPort3() {
    // Arrange
    UserAgentProfile userAgentProfile = new UserAgentProfile();

    // Act
    userAgentProfile.setMediaPort(0);

    // Assert
    assertEquals(0, userAgentProfile.getMediaPort());
  }

  @Test
  void testSetMediaPort4() {
    // Arrange
    UserAgentProfile userAgentProfile = new UserAgentProfile();

    // Act
    userAgentProfile.setMediaPort(8080, 1);

    // Assert
    assertEquals(8080, userAgentProfile.getMediaPort());
  }

  @Test
  void testSetMediaPort5() {
    // Arrange
    UserAgentProfile userAgentProfile = new UserAgentProfile("File");

    // Act
    userAgentProfile.setMediaPort(8080, 1);

    // Assert
    assertEquals(8080, userAgentProfile.getMediaPort());
  }

  @Test
  void testSetMediaPort6() {
    // Arrange
    UserAgentProfile userAgentProfile = new UserAgentProfile();

    // Act
    userAgentProfile.setMediaPort(0, 1);

    // Assert
    assertEquals(0, userAgentProfile.getMediaPort());
  }

  @Test
  void testGetMediaPort() {
    // Arrange, Act and Assert
    assertEquals(4080, (new UserAgentProfile()).getMediaPort());
    assertEquals(4080, (new UserAgentProfile("File")).getMediaPort());
    assertEquals(4080, (new UserAgentProfile(null)).getMediaPort());
  }

  @Test
  void testGetMediaPort2() {
    // Arrange
    UserAgentProfile userAgentProfile = new UserAgentProfile();
    userAgentProfile.ua_address = "42 Main St";

    // Act and Assert
    assertEquals(4080, userAgentProfile.getMediaPort());
  }

  @Test
  void testParseLine() {
    // Arrange
    UserAgentProfile userAgentProfile = new UserAgentProfile();

    // Act
    userAgentProfile.parseLine("display_name");

    // Assert
    assertEquals("", userAgentProfile.display_name);
  }

  @Test
  void testParseLine2() {
    // Arrange
    UserAgentProfile userAgentProfile = new UserAgentProfile();

    // Act
    userAgentProfile.parseLine("user");

    // Assert
    assertEquals("", userAgentProfile.user);
  }

  @Test
  void testParseLine3() {
    // Arrange
    UserAgentProfile userAgentProfile = new UserAgentProfile();

    // Act
    userAgentProfile.parseLine("proxy");

    // Assert
    assertEquals("", userAgentProfile.proxy);
  }

  @Test
  void testParseLine4() {
    // Arrange
    UserAgentProfile userAgentProfile = new UserAgentProfile();

    // Act
    userAgentProfile.parseLine("registrar");

    // Assert
    assertEquals("", userAgentProfile.registrar);
  }

  @Test
  void testParseLine5() {
    // Arrange
    UserAgentProfile userAgentProfile = new UserAgentProfile();

    // Act
    userAgentProfile.parseLine("auth_user");

    // Assert
    assertEquals("", userAgentProfile.auth_user);
  }

  @Test
  void testParseLine6() {
    // Arrange
    UserAgentProfile userAgentProfile = new UserAgentProfile();

    // Act
    userAgentProfile.parseLine("auth_realm");

    // Assert
    assertEquals("", userAgentProfile.auth_realm);
  }

  @Test
  void testParseLine7() {
    // Arrange
    UserAgentProfile userAgentProfile = new UserAgentProfile();

    // Act
    userAgentProfile.parseLine("auth_passwd");

    // Assert
    assertEquals("", userAgentProfile.auth_passwd);
  }

  @Test
  void testParseLine8() {
    // Arrange
    UserAgentProfile userAgentProfile = new UserAgentProfile("File");

    // Act
    userAgentProfile.parseLine("audio");

    // Assert
    assertFalse(userAgentProfile.audio);
  }

  @Test
  void testParseLine9() {
    // Arrange and Act
    (new UserAgentProfile("File")).parseLine("buddy_list_file");

    // Assert
    assertEquals("", UserAgentProfile.buddy_list_file);
  }

  @Test
  void testParseLine10() {
    // Arrange
    UserAgentProfile userAgentProfile = new UserAgentProfile("File");

    // Act
    userAgentProfile.parseLine("do_register");

    // Assert
    assertFalse(userAgentProfile.do_register);
  }

  @Test
  void testToLines() {
    // Arrange, Act and Assert
    assertEquals("<sip:null>", (new UserAgentProfile()).toLines());
    assertEquals("<sip:null>", (new UserAgentProfile("File")).toLines());
    assertEquals("<sip:null>", (new UserAgentProfile(null)).toLines());
  }

  @Test
  void testToLines2() {
    // Arrange
    UserAgentProfile userAgentProfile = new UserAgentProfile();
    userAgentProfile.ua_address = "42 Main St";

    // Act and Assert
    assertEquals("<sip:42 Main St>", userAgentProfile.toLines());
  }
}

