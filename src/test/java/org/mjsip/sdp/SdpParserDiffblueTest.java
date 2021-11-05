package org.mjsip.sdp;

import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

class SdpParserDiffblueTest {
  @Test
  void testParseSdpField() {
    // Arrange, Act and Assert
    assertNull((new SdpParser("foo")).parseSdpField());
    assertNull((new SdpParser("foo", 1)).parseSdpField());
    assertNull((new SdpParser("foo")).parseSdpField('A'));
    assertNull((new SdpParser("org.mjsip.sdp.AttributeField[]")).parseSdpField('A'));
    assertNull((new SdpParser("foo", 1)).parseSdpField('A'));
  }

  @Test
  void testParseSdpField2() {
    // Arrange
    SdpParser sdpParser = new SdpParser("foo");
    sdpParser.goToIgnoreCase("foo");

    // Act and Assert
    assertNull(sdpParser.parseSdpField());
  }

  @Test
  void testParseSdpField3() {
    // Arrange
    SdpParser sdpParser = new SdpParser("foo");
    sdpParser.skipN(0);

    // Act and Assert
    assertNull(sdpParser.parseSdpField());
  }

  @Test
  void testParseSdpField4() {
    // Arrange
    SdpParser sdpParser = new SdpParser("\n");
    sdpParser.skipN(-1);

    // Act and Assert
    assertNull(sdpParser.parseSdpField());
  }

  @Test
  void testParseSdpField5() {
    // Arrange
    SdpParser sdpParser = new SdpParser("foo");
    sdpParser.skipChars(new char[]{'\u0000', '\u0000', '\u0000', '\u0000'});

    // Act and Assert
    assertNull(sdpParser.parseSdpField());
  }

  @Test
  void testParseSdpField6() {
    // Arrange
    SdpParser sdpParser = new SdpParser("foo");
    sdpParser.goTo("foo");

    // Act and Assert
    assertNull(sdpParser.parseSdpField());
  }

  @Test
  void testParseSdpField7() {
    // Arrange
    SdpParser sdpParser = new SdpParser("org.mjsip.sdp.AttributeField[]");
    sdpParser.goToIgnoreCase("");

    // Act and Assert
    assertNull(sdpParser.parseSdpField());
  }

  @Test
  void testParseSdpField8() {
    // Arrange
    SdpParser sdpParser = new SdpParser("foo");
    sdpParser.goTo(new String[]{""});

    // Act and Assert
    assertNull(sdpParser.parseSdpField());
  }

  @Test
  void testParseSdpField9() {
    // Arrange
    SdpParser sdpParser = new SdpParser("foo");
    sdpParser.goToIgnoreCase("foo");

    // Act and Assert
    assertNull(sdpParser.parseSdpField('A'));
  }

  @Test
  void testParseSdpField10() {
    // Arrange
    SdpParser sdpParser = new SdpParser("foo");
    sdpParser.skipN(0);

    // Act and Assert
    assertNull(sdpParser.parseSdpField('A'));
  }

  @Test
  void testParseSdpField11() {
    // Arrange
    SdpParser sdpParser = new SdpParser("foo");
    sdpParser.goToSkippingQuoted('\u0000');

    // Act and Assert
    assertNull(sdpParser.parseSdpField('A'));
  }

  @Test
  void testParseSdpField12() {
    // Arrange
    SdpParser sdpParser = new SdpParser("foo");
    sdpParser.goTo('\u0000');

    // Act and Assert
    assertNull(sdpParser.parseSdpField('A'));
  }

  @Test
  void testParseSdpField13() {
    // Arrange
    SdpParser sdpParser = new SdpParser("foo");
    sdpParser.goTo(new String[]{"Ss"});

    // Act and Assert
    assertNull(sdpParser.parseSdpField('A'));
  }

  @Test
  void testParseSdpField14() {
    // Arrange
    SdpParser sdpParser = new SdpParser("foo");
    sdpParser.skipChars(new char[]{'\u0000', '\u0000', '\u0000', '\u0000'});

    // Act and Assert
    assertNull(sdpParser.parseSdpField('A'));
  }

  @Test
  void testParseSdpField15() {
    // Arrange
    SdpParser sdpParser = new SdpParser("foo");
    sdpParser.goTo("foo");

    // Act and Assert
    assertNull(sdpParser.parseSdpField('A'));
  }

  @Test
  void testParseSdpField16() {
    // Arrange
    SdpParser sdpParser = new SdpParser("foo");
    sdpParser.setPos(0);

    // Act and Assert
    assertNull(sdpParser.parseSdpField('A'));
  }

  @Test
  void testParseSdpField17() {
    // Arrange
    SdpParser sdpParser = new SdpParser("foo");
    sdpParser.goToIgnoreCase(new String[]{"Ss"});

    // Act and Assert
    assertNull(sdpParser.parseSdpField('A'));
  }

  @Test
  void testParseSdpField18() {
    // Arrange
    SdpParser sdpParser = new SdpParser("foo");
    sdpParser.goTo(new char[]{'\u0000', '\u0000', '\u0000', '\u0000'});

    // Act and Assert
    assertNull(sdpParser.parseSdpField('A'));
  }

  @Test
  void testParseSdpField19() {
    // Arrange
    SdpParser sdpParser = new SdpParser("org.mjsip.sdp.AttributeField[]");
    sdpParser.goToIgnoreCase("foo");

    // Act and Assert
    assertNull(sdpParser.parseSdpField('A'));
  }

  @Test
  void testParseSdpField20() {
    // Arrange
    SdpParser sdpParser = new SdpParser("org.mjsip.sdp.AttributeField[]");
    sdpParser.goTo("foo");

    // Act and Assert
    assertNull(sdpParser.parseSdpField('A'));
  }

  @Test
  void testParseSdpField21() {
    // Arrange
    SdpParser sdpParser = new SdpParser("org.mjsip.sdp.AttributeField[]");
    sdpParser.goToIgnoreCase(new String[]{"Ss"});

    // Act and Assert
    assertNull(sdpParser.parseSdpField('A'));
  }

  @Test
  void testParseSdpField22() {
    // Arrange
    SdpParser sdpParser = new SdpParser("foo");
    sdpParser.goTo(new String[]{""});

    // Act and Assert
    assertNull(sdpParser.parseSdpField('A'));
  }

  @Test
  void testParseSdpField23() {
    // Arrange
    SdpParser sdpParser = new SdpParser("");
    sdpParser.skipChars(new char[]{'\u0000', '\u0000', '\u0000', '\u0000'});

    // Act and Assert
    assertNull(sdpParser.parseSdpField('A'));
  }

  @Test
  void testParseSdpField24() {
    // Arrange
    SdpParser sdpParser = new SdpParser("foo");
    sdpParser.goToIgnoreCase(new String[]{""});

    // Act and Assert
    assertNull(sdpParser.parseSdpField('A'));
  }

  @Test
  void testParseSdpField25() {
    // Arrange
    SdpParser sdpParser = new SdpParser("foo");
    sdpParser.skipN(4);

    // Act and Assert
    assertNull(sdpParser.parseSdpField('A'));
  }

  @Test
  void testParseOriginField() {
    // Arrange, Act and Assert
    assertNull((new SdpParser("foo")).parseOriginField());
    assertNull((new SdpParser("foo", 1)).parseOriginField());
    assertNull((new SdpParser("org.mjsip.sdp.AttributeField[]")).parseOriginField());
  }

  @Test
  void testParseOriginField2() {
    // Arrange
    SdpParser sdpParser = new SdpParser("");
    sdpParser.goToIgnoreCase((String) null);

    // Act and Assert
    assertNull(sdpParser.parseOriginField());
  }

  @Test
  void testParseOriginField3() {
    // Arrange
    SdpParser sdpParser = new SdpParser("foo");
    sdpParser.goToIgnoreCase("foo");

    // Act and Assert
    assertNull(sdpParser.parseOriginField());
  }

  @Test
  void testParseOriginField4() {
    // Arrange
    SdpParser sdpParser = new SdpParser("foo");
    sdpParser.goToIgnoreCase("\"");

    // Act and Assert
    assertNull(sdpParser.parseOriginField());
  }

  @Test
  void testParseOriginField5() {
    // Arrange
    SdpParser sdpParser = new SdpParser("");
    sdpParser.skipN(1);
    sdpParser.goToIgnoreCase((String) null);

    // Act and Assert
    assertNull(sdpParser.parseOriginField());
  }

  @Test
  void testParseOriginField6() {
    // Arrange
    SdpParser sdpParser = new SdpParser("foo");
    sdpParser.goToSkippingQuoted('A');
    sdpParser.goToIgnoreCase((String) null);

    // Act and Assert
    assertNull(sdpParser.parseOriginField());
  }

  @Test
  void testParseOriginField7() {
    // Arrange
    SdpParser sdpParser = new SdpParser("foo");
    sdpParser.goTo('A');
    sdpParser.goToIgnoreCase((String) null);

    // Act and Assert
    assertNull(sdpParser.parseOriginField());
  }

  @Test
  void testParseOriginField8() {
    // Arrange
    SdpParser sdpParser = new SdpParser("foo");
    sdpParser.goTo(new String[]{"Ss"});
    sdpParser.goToIgnoreCase((String) null);

    // Act and Assert
    assertNull(sdpParser.parseOriginField());
  }

  @Test
  void testParseOriginField9() {
    // Arrange
    SdpParser sdpParser = new SdpParser("");
    sdpParser.skipChars("AAAA".toCharArray());
    sdpParser.goToIgnoreCase((String) null);

    // Act and Assert
    assertNull(sdpParser.parseOriginField());
  }

  @Test
  void testParseOriginField10() {
    // Arrange
    SdpParser sdpParser = new SdpParser("org.mjsip.sdp.AttributeField[]");
    sdpParser.goTo("foo");
    sdpParser.goToIgnoreCase((String) null);

    // Act and Assert
    assertNull(sdpParser.parseOriginField());
  }

  @Test
  void testParseOriginField11() {
    // Arrange
    SdpParser sdpParser = new SdpParser("foo");
    sdpParser.goTo("AAAA".toCharArray());
    sdpParser.goToIgnoreCase((String) null);

    // Act and Assert
    assertNull(sdpParser.parseOriginField());
  }

  @Test
  void testParseOriginField12() {
    // Arrange
    SdpParser sdpParser = new SdpParser("foo");
    sdpParser.goToIgnoreCase(new String[]{"Ss"});
    sdpParser.goTo((String[]) null);

    // Act and Assert
    assertNull(sdpParser.parseOriginField());
  }

  @Test
  void testParseOriginField13() {
    // Arrange
    SdpParser sdpParser = new SdpParser("org.mjsip.sdp.AttributeField[]");
    sdpParser.goToIgnoreCase("foo");

    // Act and Assert
    assertNull(sdpParser.parseOriginField());
  }

  @Test
  void testParseOriginField14() {
    // Arrange
    SdpParser sdpParser = new SdpParser("org.mjsip.sdp.AttributeField[]");
    sdpParser.goToIgnoreCase(new String[]{"Ss"});
    sdpParser.goTo((String[]) null);

    // Act and Assert
    assertNull(sdpParser.parseOriginField());
  }

  @Test
  void testParseMediaField() {
    // Arrange, Act and Assert
    assertNull((new SdpParser("foo")).parseMediaField());
    assertNull((new SdpParser("foo", 1)).parseMediaField());
    assertNull((new SdpParser("org.mjsip.sdp.AttributeField[]")).parseMediaField());
  }

  @Test
  void testParseMediaField2() {
    // Arrange
    SdpParser sdpParser = new SdpParser("");
    sdpParser.goToIgnoreCase((String) null);

    // Act and Assert
    assertNull(sdpParser.parseMediaField());
  }

  @Test
  void testParseMediaField3() {
    // Arrange
    SdpParser sdpParser = new SdpParser("foo");
    sdpParser.goToIgnoreCase("foo");

    // Act and Assert
    assertNull(sdpParser.parseMediaField());
  }

  @Test
  void testParseMediaField4() {
    // Arrange
    SdpParser sdpParser = new SdpParser("foo");
    sdpParser.goToIgnoreCase("\"");

    // Act and Assert
    assertNull(sdpParser.parseMediaField());
  }

  @Test
  void testParseMediaField5() {
    // Arrange
    SdpParser sdpParser = new SdpParser("");
    sdpParser.skipN(1);
    sdpParser.goToIgnoreCase((String) null);

    // Act and Assert
    assertNull(sdpParser.parseMediaField());
  }

  @Test
  void testParseMediaField6() {
    // Arrange
    SdpParser sdpParser = new SdpParser("foo");
    sdpParser.goToSkippingQuoted('A');
    sdpParser.goToIgnoreCase((String) null);

    // Act and Assert
    assertNull(sdpParser.parseMediaField());
  }

  @Test
  void testParseMediaField7() {
    // Arrange
    SdpParser sdpParser = new SdpParser("foo");
    sdpParser.goTo('A');
    sdpParser.goToIgnoreCase((String) null);

    // Act and Assert
    assertNull(sdpParser.parseMediaField());
  }

  @Test
  void testParseMediaField8() {
    // Arrange
    SdpParser sdpParser = new SdpParser("foo");
    sdpParser.goTo(new String[]{"Ss"});
    sdpParser.goToIgnoreCase((String) null);

    // Act and Assert
    assertNull(sdpParser.parseMediaField());
  }

  @Test
  void testParseMediaField9() {
    // Arrange
    SdpParser sdpParser = new SdpParser("");
    sdpParser.skipChars("AAAA".toCharArray());
    sdpParser.goToIgnoreCase((String) null);

    // Act and Assert
    assertNull(sdpParser.parseMediaField());
  }

  @Test
  void testParseMediaField10() {
    // Arrange
    SdpParser sdpParser = new SdpParser("org.mjsip.sdp.AttributeField[]");
    sdpParser.goTo("foo");
    sdpParser.goToIgnoreCase((String) null);

    // Act and Assert
    assertNull(sdpParser.parseMediaField());
  }

  @Test
  void testParseMediaField11() {
    // Arrange
    SdpParser sdpParser = new SdpParser("foo");
    sdpParser.goTo("AAAA".toCharArray());
    sdpParser.goToIgnoreCase((String) null);

    // Act and Assert
    assertNull(sdpParser.parseMediaField());
  }

  @Test
  void testParseMediaField12() {
    // Arrange
    SdpParser sdpParser = new SdpParser("foo");
    sdpParser.goToIgnoreCase(new String[]{"Ss"});
    sdpParser.goTo((String[]) null);

    // Act and Assert
    assertNull(sdpParser.parseMediaField());
  }

  @Test
  void testParseMediaField13() {
    // Arrange
    SdpParser sdpParser = new SdpParser("org.mjsip.sdp.AttributeField[]");
    sdpParser.goToIgnoreCase("foo");

    // Act and Assert
    assertNull(sdpParser.parseMediaField());
  }

  @Test
  void testParseMediaField14() {
    // Arrange
    SdpParser sdpParser = new SdpParser("org.mjsip.sdp.AttributeField[]");
    sdpParser.goToIgnoreCase(new String[]{"Ss"});
    sdpParser.goTo((String[]) null);

    // Act and Assert
    assertNull(sdpParser.parseMediaField());
  }

  @Test
  void testParseConnectionField() {
    // Arrange, Act and Assert
    assertNull((new SdpParser("foo")).parseConnectionField());
    assertNull((new SdpParser("foo", 1)).parseConnectionField());
    assertNull((new SdpParser("org.mjsip.sdp.AttributeField[]")).parseConnectionField());
  }

  @Test
  void testParseConnectionField2() {
    // Arrange
    SdpParser sdpParser = new SdpParser("");
    sdpParser.goToIgnoreCase((String) null);

    // Act and Assert
    assertNull(sdpParser.parseConnectionField());
  }

  @Test
  void testParseConnectionField3() {
    // Arrange
    SdpParser sdpParser = new SdpParser("foo");
    sdpParser.goToIgnoreCase("foo");

    // Act and Assert
    assertNull(sdpParser.parseConnectionField());
  }

  @Test
  void testParseConnectionField4() {
    // Arrange
    SdpParser sdpParser = new SdpParser("foo");
    sdpParser.goToIgnoreCase("\"");

    // Act and Assert
    assertNull(sdpParser.parseConnectionField());
  }

  @Test
  void testParseConnectionField5() {
    // Arrange
    SdpParser sdpParser = new SdpParser("");
    sdpParser.skipN(1);
    sdpParser.goToIgnoreCase((String) null);

    // Act and Assert
    assertNull(sdpParser.parseConnectionField());
  }

  @Test
  void testParseConnectionField6() {
    // Arrange
    SdpParser sdpParser = new SdpParser("foo");
    sdpParser.goToSkippingQuoted('A');
    sdpParser.goToIgnoreCase((String) null);

    // Act and Assert
    assertNull(sdpParser.parseConnectionField());
  }

  @Test
  void testParseConnectionField7() {
    // Arrange
    SdpParser sdpParser = new SdpParser("foo");
    sdpParser.goTo('A');
    sdpParser.goToIgnoreCase((String) null);

    // Act and Assert
    assertNull(sdpParser.parseConnectionField());
  }

  @Test
  void testParseConnectionField8() {
    // Arrange
    SdpParser sdpParser = new SdpParser("foo");
    sdpParser.goTo(new String[]{"Ss"});
    sdpParser.goToIgnoreCase((String) null);

    // Act and Assert
    assertNull(sdpParser.parseConnectionField());
  }

  @Test
  void testParseConnectionField9() {
    // Arrange
    SdpParser sdpParser = new SdpParser("");
    sdpParser.skipChars("AAAA".toCharArray());
    sdpParser.goToIgnoreCase((String) null);

    // Act and Assert
    assertNull(sdpParser.parseConnectionField());
  }

  @Test
  void testParseConnectionField10() {
    // Arrange
    SdpParser sdpParser = new SdpParser("org.mjsip.sdp.AttributeField[]");
    sdpParser.goTo("foo");
    sdpParser.goToIgnoreCase((String) null);

    // Act and Assert
    assertNull(sdpParser.parseConnectionField());
  }

  @Test
  void testParseConnectionField11() {
    // Arrange
    SdpParser sdpParser = new SdpParser("foo");
    sdpParser.goTo("AAAA".toCharArray());
    sdpParser.goToIgnoreCase((String) null);

    // Act and Assert
    assertNull(sdpParser.parseConnectionField());
  }

  @Test
  void testParseConnectionField12() {
    // Arrange
    SdpParser sdpParser = new SdpParser("foo");
    sdpParser.goToIgnoreCase(new String[]{"Ss"});
    sdpParser.goTo((String[]) null);

    // Act and Assert
    assertNull(sdpParser.parseConnectionField());
  }

  @Test
  void testParseConnectionField13() {
    // Arrange
    SdpParser sdpParser = new SdpParser("org.mjsip.sdp.AttributeField[]");
    sdpParser.goToIgnoreCase("foo");

    // Act and Assert
    assertNull(sdpParser.parseConnectionField());
  }

  @Test
  void testParseConnectionField14() {
    // Arrange
    SdpParser sdpParser = new SdpParser("org.mjsip.sdp.AttributeField[]");
    sdpParser.goToIgnoreCase(new String[]{"Ss"});
    sdpParser.goTo((String[]) null);

    // Act and Assert
    assertNull(sdpParser.parseConnectionField());
  }

  @Test
  void testParseSessionNameField() {
    // Arrange, Act and Assert
    assertNull((new SdpParser("foo")).parseSessionNameField());
    assertNull((new SdpParser("foo", 1)).parseSessionNameField());
    assertNull((new SdpParser("org.mjsip.sdp.AttributeField[]")).parseSessionNameField());
  }

  @Test
  void testParseSessionNameField2() {
    // Arrange
    SdpParser sdpParser = new SdpParser("");
    sdpParser.goToIgnoreCase((String) null);

    // Act and Assert
    assertNull(sdpParser.parseSessionNameField());
  }

  @Test
  void testParseSessionNameField3() {
    // Arrange
    SdpParser sdpParser = new SdpParser("foo");
    sdpParser.goToIgnoreCase("foo");

    // Act and Assert
    assertNull(sdpParser.parseSessionNameField());
  }

  @Test
  void testParseSessionNameField4() {
    // Arrange
    SdpParser sdpParser = new SdpParser("foo");
    sdpParser.goToIgnoreCase("\"");

    // Act and Assert
    assertNull(sdpParser.parseSessionNameField());
  }

  @Test
  void testParseSessionNameField5() {
    // Arrange
    SdpParser sdpParser = new SdpParser("");
    sdpParser.skipN(1);
    sdpParser.goToIgnoreCase((String) null);

    // Act and Assert
    assertNull(sdpParser.parseSessionNameField());
  }

  @Test
  void testParseSessionNameField6() {
    // Arrange
    SdpParser sdpParser = new SdpParser("foo");
    sdpParser.goToSkippingQuoted('A');
    sdpParser.goToIgnoreCase((String) null);

    // Act and Assert
    assertNull(sdpParser.parseSessionNameField());
  }

  @Test
  void testParseSessionNameField7() {
    // Arrange
    SdpParser sdpParser = new SdpParser("foo");
    sdpParser.goTo('A');
    sdpParser.goToIgnoreCase((String) null);

    // Act and Assert
    assertNull(sdpParser.parseSessionNameField());
  }

  @Test
  void testParseSessionNameField8() {
    // Arrange
    SdpParser sdpParser = new SdpParser("foo");
    sdpParser.goTo(new String[]{"Ss"});
    sdpParser.goToIgnoreCase((String) null);

    // Act and Assert
    assertNull(sdpParser.parseSessionNameField());
  }

  @Test
  void testParseSessionNameField9() {
    // Arrange
    SdpParser sdpParser = new SdpParser("");
    sdpParser.skipChars("AAAA".toCharArray());
    sdpParser.goToIgnoreCase((String) null);

    // Act and Assert
    assertNull(sdpParser.parseSessionNameField());
  }

  @Test
  void testParseSessionNameField10() {
    // Arrange
    SdpParser sdpParser = new SdpParser("org.mjsip.sdp.AttributeField[]");
    sdpParser.goTo("foo");
    sdpParser.goToIgnoreCase((String) null);

    // Act and Assert
    assertNull(sdpParser.parseSessionNameField());
  }

  @Test
  void testParseSessionNameField11() {
    // Arrange
    SdpParser sdpParser = new SdpParser("foo");
    sdpParser.goTo("AAAA".toCharArray());
    sdpParser.goToIgnoreCase((String) null);

    // Act and Assert
    assertNull(sdpParser.parseSessionNameField());
  }

  @Test
  void testParseSessionNameField12() {
    // Arrange
    SdpParser sdpParser = new SdpParser("foo");
    sdpParser.goToIgnoreCase(new String[]{"Ss"});
    sdpParser.goTo((String[]) null);

    // Act and Assert
    assertNull(sdpParser.parseSessionNameField());
  }

  @Test
  void testParseSessionNameField13() {
    // Arrange
    SdpParser sdpParser = new SdpParser("org.mjsip.sdp.AttributeField[]");
    sdpParser.goToIgnoreCase("foo");

    // Act and Assert
    assertNull(sdpParser.parseSessionNameField());
  }

  @Test
  void testParseSessionNameField14() {
    // Arrange
    SdpParser sdpParser = new SdpParser("org.mjsip.sdp.AttributeField[]");
    sdpParser.goToIgnoreCase(new String[]{"Ss"});
    sdpParser.goTo((String[]) null);

    // Act and Assert
    assertNull(sdpParser.parseSessionNameField());
  }

  @Test
  void testParseTimeField() {
    // Arrange, Act and Assert
    assertNull((new SdpParser("foo")).parseTimeField());
    assertNull((new SdpParser("foo", 1)).parseTimeField());
    assertNull((new SdpParser("org.mjsip.sdp.AttributeField[]")).parseTimeField());
  }

  @Test
  void testParseTimeField2() {
    // Arrange
    SdpParser sdpParser = new SdpParser("");
    sdpParser.goToIgnoreCase((String) null);

    // Act and Assert
    assertNull(sdpParser.parseTimeField());
  }

  @Test
  void testParseTimeField3() {
    // Arrange
    SdpParser sdpParser = new SdpParser("foo");
    sdpParser.goToIgnoreCase("foo");

    // Act and Assert
    assertNull(sdpParser.parseTimeField());
  }

  @Test
  void testParseTimeField4() {
    // Arrange
    SdpParser sdpParser = new SdpParser("foo");
    sdpParser.goToIgnoreCase("\"");

    // Act and Assert
    assertNull(sdpParser.parseTimeField());
  }

  @Test
  void testParseTimeField5() {
    // Arrange
    SdpParser sdpParser = new SdpParser("");
    sdpParser.skipN(1);
    sdpParser.goToIgnoreCase((String) null);

    // Act and Assert
    assertNull(sdpParser.parseTimeField());
  }

  @Test
  void testParseTimeField6() {
    // Arrange
    SdpParser sdpParser = new SdpParser("foo");
    sdpParser.goToSkippingQuoted('A');
    sdpParser.goToIgnoreCase((String) null);

    // Act and Assert
    assertNull(sdpParser.parseTimeField());
  }

  @Test
  void testParseTimeField7() {
    // Arrange
    SdpParser sdpParser = new SdpParser("foo");
    sdpParser.goTo('A');
    sdpParser.goToIgnoreCase((String) null);

    // Act and Assert
    assertNull(sdpParser.parseTimeField());
  }

  @Test
  void testParseTimeField8() {
    // Arrange
    SdpParser sdpParser = new SdpParser("foo");
    sdpParser.goTo(new String[]{"Ss"});
    sdpParser.goToIgnoreCase((String) null);

    // Act and Assert
    assertNull(sdpParser.parseTimeField());
  }

  @Test
  void testParseTimeField9() {
    // Arrange
    SdpParser sdpParser = new SdpParser("");
    sdpParser.skipChars("AAAA".toCharArray());
    sdpParser.goToIgnoreCase((String) null);

    // Act and Assert
    assertNull(sdpParser.parseTimeField());
  }

  @Test
  void testParseTimeField10() {
    // Arrange
    SdpParser sdpParser = new SdpParser("org.mjsip.sdp.AttributeField[]");
    sdpParser.goTo("foo");
    sdpParser.goToIgnoreCase((String) null);

    // Act and Assert
    assertNull(sdpParser.parseTimeField());
  }

  @Test
  void testParseTimeField11() {
    // Arrange
    SdpParser sdpParser = new SdpParser("foo");
    sdpParser.goTo("AAAA".toCharArray());
    sdpParser.goToIgnoreCase((String) null);

    // Act and Assert
    assertNull(sdpParser.parseTimeField());
  }

  @Test
  void testParseTimeField12() {
    // Arrange
    SdpParser sdpParser = new SdpParser("foo");
    sdpParser.goToIgnoreCase(new String[]{"Ss"});
    sdpParser.goTo((String[]) null);

    // Act and Assert
    assertNull(sdpParser.parseTimeField());
  }

  @Test
  void testParseTimeField13() {
    // Arrange
    SdpParser sdpParser = new SdpParser("org.mjsip.sdp.AttributeField[]");
    sdpParser.goToIgnoreCase("foo");

    // Act and Assert
    assertNull(sdpParser.parseTimeField());
  }

  @Test
  void testParseTimeField14() {
    // Arrange
    SdpParser sdpParser = new SdpParser("org.mjsip.sdp.AttributeField[]");
    sdpParser.goToIgnoreCase(new String[]{"Ss"});
    sdpParser.goTo((String[]) null);

    // Act and Assert
    assertNull(sdpParser.parseTimeField());
  }

  @Test
  void testParseKeyField() {
    // Arrange, Act and Assert
    assertNull((new SdpParser("foo")).parseKeyField());
    assertNull((new SdpParser("foo", 1)).parseKeyField());
    assertNull((new SdpParser("org.mjsip.sdp.AttributeField[]")).parseKeyField());
  }

  @Test
  void testParseKeyField2() {
    // Arrange
    SdpParser sdpParser = new SdpParser("");
    sdpParser.goToIgnoreCase((String) null);

    // Act and Assert
    assertNull(sdpParser.parseKeyField());
  }

  @Test
  void testParseKeyField3() {
    // Arrange
    SdpParser sdpParser = new SdpParser("foo");
    sdpParser.goToIgnoreCase("foo");

    // Act and Assert
    assertNull(sdpParser.parseKeyField());
  }

  @Test
  void testParseKeyField4() {
    // Arrange
    SdpParser sdpParser = new SdpParser("foo");
    sdpParser.goToIgnoreCase("\"");

    // Act and Assert
    assertNull(sdpParser.parseKeyField());
  }

  @Test
  void testParseKeyField5() {
    // Arrange
    SdpParser sdpParser = new SdpParser("");
    sdpParser.skipN(1);
    sdpParser.goToIgnoreCase((String) null);

    // Act and Assert
    assertNull(sdpParser.parseKeyField());
  }

  @Test
  void testParseKeyField6() {
    // Arrange
    SdpParser sdpParser = new SdpParser("foo");
    sdpParser.goToSkippingQuoted('A');
    sdpParser.goToIgnoreCase((String) null);

    // Act and Assert
    assertNull(sdpParser.parseKeyField());
  }

  @Test
  void testParseKeyField7() {
    // Arrange
    SdpParser sdpParser = new SdpParser("foo");
    sdpParser.goTo('A');
    sdpParser.goToIgnoreCase((String) null);

    // Act and Assert
    assertNull(sdpParser.parseKeyField());
  }

  @Test
  void testParseKeyField8() {
    // Arrange
    SdpParser sdpParser = new SdpParser("foo");
    sdpParser.goTo(new String[]{"Ss"});
    sdpParser.goToIgnoreCase((String) null);

    // Act and Assert
    assertNull(sdpParser.parseKeyField());
  }

  @Test
  void testParseKeyField9() {
    // Arrange
    SdpParser sdpParser = new SdpParser("");
    sdpParser.skipChars("AAAA".toCharArray());
    sdpParser.goToIgnoreCase((String) null);

    // Act and Assert
    assertNull(sdpParser.parseKeyField());
  }

  @Test
  void testParseKeyField10() {
    // Arrange
    SdpParser sdpParser = new SdpParser("org.mjsip.sdp.AttributeField[]");
    sdpParser.goTo("foo");
    sdpParser.goToIgnoreCase((String) null);

    // Act and Assert
    assertNull(sdpParser.parseKeyField());
  }

  @Test
  void testParseKeyField11() {
    // Arrange
    SdpParser sdpParser = new SdpParser("foo");
    sdpParser.goTo("AAAA".toCharArray());
    sdpParser.goToIgnoreCase((String) null);

    // Act and Assert
    assertNull(sdpParser.parseKeyField());
  }

  @Test
  void testParseKeyField12() {
    // Arrange
    SdpParser sdpParser = new SdpParser("foo");
    sdpParser.goToIgnoreCase(new String[]{"Ss"});
    sdpParser.goTo((String[]) null);

    // Act and Assert
    assertNull(sdpParser.parseKeyField());
  }

  @Test
  void testParseKeyField13() {
    // Arrange
    SdpParser sdpParser = new SdpParser("org.mjsip.sdp.AttributeField[]");
    sdpParser.goToIgnoreCase("foo");

    // Act and Assert
    assertNull(sdpParser.parseKeyField());
  }

  @Test
  void testParseKeyField14() {
    // Arrange
    SdpParser sdpParser = new SdpParser("org.mjsip.sdp.AttributeField[]");
    sdpParser.goToIgnoreCase(new String[]{"Ss"});
    sdpParser.goTo((String[]) null);

    // Act and Assert
    assertNull(sdpParser.parseKeyField());
  }

  @Test
  void testParseAttributeField() {
    // Arrange, Act and Assert
    assertNull((new SdpParser("foo")).parseAttributeField());
    assertNull((new SdpParser("foo", 1)).parseAttributeField());
    assertNull((new SdpParser("org.mjsip.sdp.AttributeField[]")).parseAttributeField());
  }

  @Test
  void testParseAttributeField2() {
    // Arrange
    SdpParser sdpParser = new SdpParser("");
    sdpParser.goToIgnoreCase((String) null);

    // Act and Assert
    assertNull(sdpParser.parseAttributeField());
  }

  @Test
  void testParseAttributeField3() {
    // Arrange
    SdpParser sdpParser = new SdpParser("foo");
    sdpParser.goToIgnoreCase("foo");

    // Act and Assert
    assertNull(sdpParser.parseAttributeField());
  }

  @Test
  void testParseAttributeField4() {
    // Arrange
    SdpParser sdpParser = new SdpParser("foo");
    sdpParser.goToIgnoreCase("\"");

    // Act and Assert
    assertNull(sdpParser.parseAttributeField());
  }

  @Test
  void testParseAttributeField5() {
    // Arrange
    SdpParser sdpParser = new SdpParser("");
    sdpParser.skipN(1);
    sdpParser.goToIgnoreCase((String) null);

    // Act and Assert
    assertNull(sdpParser.parseAttributeField());
  }

  @Test
  void testParseAttributeField6() {
    // Arrange
    SdpParser sdpParser = new SdpParser("foo");
    sdpParser.goToSkippingQuoted('A');
    sdpParser.goToIgnoreCase((String) null);

    // Act and Assert
    assertNull(sdpParser.parseAttributeField());
  }

  @Test
  void testParseAttributeField7() {
    // Arrange
    SdpParser sdpParser = new SdpParser("foo");
    sdpParser.goTo('A');
    sdpParser.goToIgnoreCase((String) null);

    // Act and Assert
    assertNull(sdpParser.parseAttributeField());
  }

  @Test
  void testParseAttributeField8() {
    // Arrange
    SdpParser sdpParser = new SdpParser("foo");
    sdpParser.goTo(new String[]{"Ss"});
    sdpParser.goToIgnoreCase((String) null);

    // Act and Assert
    assertNull(sdpParser.parseAttributeField());
  }

  @Test
  void testParseAttributeField9() {
    // Arrange
    SdpParser sdpParser = new SdpParser("");
    sdpParser.skipChars("AAAA".toCharArray());
    sdpParser.goToIgnoreCase((String) null);

    // Act and Assert
    assertNull(sdpParser.parseAttributeField());
  }

  @Test
  void testParseAttributeField10() {
    // Arrange
    SdpParser sdpParser = new SdpParser("org.mjsip.sdp.AttributeField[]");
    sdpParser.goTo("foo");
    sdpParser.goToIgnoreCase((String) null);

    // Act and Assert
    assertNull(sdpParser.parseAttributeField());
  }

  @Test
  void testParseAttributeField11() {
    // Arrange
    SdpParser sdpParser = new SdpParser("foo");
    sdpParser.goTo("AAAA".toCharArray());
    sdpParser.goToIgnoreCase((String) null);

    // Act and Assert
    assertNull(sdpParser.parseAttributeField());
  }

  @Test
  void testParseAttributeField12() {
    // Arrange
    SdpParser sdpParser = new SdpParser("foo");
    sdpParser.goToIgnoreCase(new String[]{"Ss"});
    sdpParser.goTo((String[]) null);

    // Act and Assert
    assertNull(sdpParser.parseAttributeField());
  }

  @Test
  void testParseAttributeField13() {
    // Arrange
    SdpParser sdpParser = new SdpParser("org.mjsip.sdp.AttributeField[]");
    sdpParser.goToIgnoreCase("foo");

    // Act and Assert
    assertNull(sdpParser.parseAttributeField());
  }

  @Test
  void testParseAttributeField14() {
    // Arrange
    SdpParser sdpParser = new SdpParser("org.mjsip.sdp.AttributeField[]");
    sdpParser.goToIgnoreCase(new String[]{"Ss"});
    sdpParser.goTo((String[]) null);

    // Act and Assert
    assertNull(sdpParser.parseAttributeField());
  }

  @Test
  void testParseMediaDescriptor() {
    // Arrange, Act and Assert
    assertNull((new SdpParser("foo")).parseMediaDescriptor());
    assertNull((new SdpParser("foo", 1)).parseMediaDescriptor());
    assertNull((new SdpParser("org.mjsip.sdp.AttributeField[]")).parseMediaDescriptor());
  }

  @Test
  void testParseMediaDescriptor2() {
    // Arrange
    SdpParser sdpParser = new SdpParser("");
    sdpParser.goToIgnoreCase((String) null);

    // Act and Assert
    assertNull(sdpParser.parseMediaDescriptor());
  }

  @Test
  void testParseMediaDescriptor3() {
    // Arrange
    SdpParser sdpParser = new SdpParser("foo");
    sdpParser.goToIgnoreCase("foo");

    // Act and Assert
    assertNull(sdpParser.parseMediaDescriptor());
  }

  @Test
  void testParseMediaDescriptor4() {
    // Arrange
    SdpParser sdpParser = new SdpParser("foo");
    sdpParser.goToIgnoreCase("\"");

    // Act and Assert
    assertNull(sdpParser.parseMediaDescriptor());
  }

  @Test
  void testParseMediaDescriptor5() {
    // Arrange
    SdpParser sdpParser = new SdpParser("");
    sdpParser.skipN(1);
    sdpParser.goToIgnoreCase((String) null);

    // Act and Assert
    assertNull(sdpParser.parseMediaDescriptor());
  }

  @Test
  void testParseMediaDescriptor6() {
    // Arrange
    SdpParser sdpParser = new SdpParser("foo");
    sdpParser.goToSkippingQuoted('A');
    sdpParser.goToIgnoreCase((String) null);

    // Act and Assert
    assertNull(sdpParser.parseMediaDescriptor());
  }

  @Test
  void testParseMediaDescriptor7() {
    // Arrange
    SdpParser sdpParser = new SdpParser("foo");
    sdpParser.goTo('A');
    sdpParser.goToIgnoreCase((String) null);

    // Act and Assert
    assertNull(sdpParser.parseMediaDescriptor());
  }

  @Test
  void testParseMediaDescriptor8() {
    // Arrange
    SdpParser sdpParser = new SdpParser("foo");
    sdpParser.goTo(new String[]{"Ss"});
    sdpParser.goToIgnoreCase((String) null);

    // Act and Assert
    assertNull(sdpParser.parseMediaDescriptor());
  }

  @Test
  void testParseMediaDescriptor9() {
    // Arrange
    SdpParser sdpParser = new SdpParser("");
    sdpParser.skipChars("AAAA".toCharArray());
    sdpParser.goToIgnoreCase((String) null);

    // Act and Assert
    assertNull(sdpParser.parseMediaDescriptor());
  }

  @Test
  void testParseMediaDescriptor10() {
    // Arrange
    SdpParser sdpParser = new SdpParser("org.mjsip.sdp.AttributeField[]");
    sdpParser.goTo("foo");
    sdpParser.goToIgnoreCase((String) null);

    // Act and Assert
    assertNull(sdpParser.parseMediaDescriptor());
  }

  @Test
  void testParseMediaDescriptor11() {
    // Arrange
    SdpParser sdpParser = new SdpParser("foo");
    sdpParser.goTo("AAAA".toCharArray());
    sdpParser.goToIgnoreCase((String) null);

    // Act and Assert
    assertNull(sdpParser.parseMediaDescriptor());
  }

  @Test
  void testParseMediaDescriptor12() {
    // Arrange
    SdpParser sdpParser = new SdpParser("foo");
    sdpParser.goToIgnoreCase(new String[]{"Ss"});
    sdpParser.goTo((String[]) null);

    // Act and Assert
    assertNull(sdpParser.parseMediaDescriptor());
  }

  @Test
  void testParseMediaDescriptor13() {
    // Arrange
    SdpParser sdpParser = new SdpParser("org.mjsip.sdp.AttributeField[]");
    sdpParser.goToIgnoreCase("foo");

    // Act and Assert
    assertNull(sdpParser.parseMediaDescriptor());
  }

  @Test
  void testParseMediaDescriptor14() {
    // Arrange
    SdpParser sdpParser = new SdpParser("org.mjsip.sdp.AttributeField[]");
    sdpParser.goToIgnoreCase(new String[]{"Ss"});
    sdpParser.goTo((String[]) null);

    // Act and Assert
    assertNull(sdpParser.parseMediaDescriptor());
  }
}

