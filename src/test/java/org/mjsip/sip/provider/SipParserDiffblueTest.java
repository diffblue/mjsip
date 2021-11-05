package org.mjsip.sip.provider;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Vector;
import org.junit.jupiter.api.Test;
import org.mjsip.sip.address.GenericURI;
import org.mjsip.sip.address.NameAddress;
import org.mjsip.sip.address.SipURI;
import org.mjsip.sip.header.Header;
import org.mjsip.sip.header.RequestLine;
import org.zoolu.util.Parser;

class SipParserDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    SipParser actualSipParser = new SipParser(new StringBuffer(3), 0);

    // Assert
    assertEquals("", actualSipParser.getWholeString());
    assertEquals(0, actualSipParser.getPos());
  }

  @Test
  void testIsMark() {
    // Arrange, Act and Assert
    assertFalse(SipParser.isMark('A'));
    assertTrue(SipParser.isMark('|'));
  }

  @Test
  void testIsUnreserved() {
    // Arrange, Act and Assert
    assertTrue(SipParser.isUnreserved('A'));
    assertFalse(SipParser.isUnreserved('\u0000'));
    assertTrue(SipParser.isUnreserved('a'));
    assertFalse(SipParser.isUnreserved(':'));
    assertTrue(SipParser.isUnreserved('1'));
    assertTrue(SipParser.isUnreserved('|'));
  }

  @Test
  void testIsSeparator() {
    // Arrange, Act and Assert
    assertFalse(SipParser.isSeparator('A'));
    assertTrue(SipParser.isSeparator('"'));
  }

  @Test
  void testIndexOfSeparator() {
    // Arrange, Act and Assert
    assertEquals(3, (new SipParser("foo")).indexOfSeparator());
    assertEquals(0, (new SipParser("<tel:")).indexOfSeparator());
    assertEquals(0, (new SipParser("<tel:", 0)).indexOfSeparator());
  }

  @Test
  void testIndexOfEOH() {
    // Arrange, Act and Assert
    assertEquals(3, (new SipParser("foo")).indexOfEOH());
    assertEquals(5, (new SipParser("<tel:", 0)).indexOfEOH());
  }

  @Test
  void testIndexOfNextHeader() {
    // Arrange, Act and Assert
    assertEquals(3, (new SipParser("foo")).indexOfNextHeader());
    assertEquals(5, (new SipParser("<tel:", 0)).indexOfNextHeader());
  }

  @Test
  void testIndexOfHeader() {
    // Arrange, Act and Assert
    assertEquals(3, (new SipParser("foo")).indexOfHeader("Hname"));
    assertEquals(3, (new SipParser("GMT")).indexOfHeader("Hname"));
    assertEquals(0, (new SipParser("foo")).indexOfHeader(""));
    assertEquals(5, (new SipParser("<tel:", 0)).indexOfHeader("Hname"));
  }

  @Test
  void testGoToNextHeader() {
    // Arrange
    SipParser sipParser = new SipParser("foo");

    // Act
    SipParser actualGoToNextHeaderResult = sipParser.goToNextHeader();

    // Assert
    assertSame(sipParser, actualGoToNextHeaderResult);
    assertEquals(3, actualGoToNextHeaderResult.getPos());
  }

  @Test
  void testGoToNextHeader2() {
    // Arrange
    SipParser sipParser = new SipParser("<tel:", 0);

    // Act
    SipParser actualGoToNextHeaderResult = sipParser.goToNextHeader();

    // Assert
    assertSame(sipParser, actualGoToNextHeaderResult);
    assertEquals(5, actualGoToNextHeaderResult.getPos());
  }

  @Test
  void testGoToEndOfLastHeader() {
    // Arrange
    SipParser sipParser = new SipParser("foo");

    // Act
    SipParser actualGoToEndOfLastHeaderResult = sipParser.goToEndOfLastHeader();

    // Assert
    assertSame(sipParser, actualGoToEndOfLastHeaderResult);
    assertEquals(3, actualGoToEndOfLastHeaderResult.getPos());
  }

  @Test
  void testGoToEndOfLastHeader2() {
    // Arrange
    SipParser sipParser = new SipParser("foo", 1);

    // Act
    SipParser actualGoToEndOfLastHeaderResult = sipParser.goToEndOfLastHeader();

    // Assert
    assertSame(sipParser, actualGoToEndOfLastHeaderResult);
    assertEquals(3, actualGoToEndOfLastHeaderResult.getPos());
  }

  @Test
  void testGoToBody() {
    // Arrange
    SipParser sipParser = new SipParser("foo");

    // Act
    SipParser actualGoToBodyResult = sipParser.goToBody();

    // Assert
    assertSame(sipParser, actualGoToBodyResult);
    assertEquals(3, actualGoToBodyResult.getPos());
  }

  @Test
  void testGoToBody2() {
    // Arrange
    SipParser sipParser = new SipParser("\r\n\r\n");

    // Act
    SipParser actualGoToBodyResult = sipParser.goToBody();

    // Assert
    assertSame(sipParser, actualGoToBodyResult);
    assertEquals(4, actualGoToBodyResult.getPos());
  }

  @Test
  void testGoToBody3() {
    // Arrange
    SipParser sipParser = new SipParser("\r\n");

    // Act
    SipParser actualGoToBodyResult = sipParser.goToBody();

    // Assert
    assertSame(sipParser, actualGoToBodyResult);
    assertEquals(2, actualGoToBodyResult.getPos());
  }

  @Test
  void testGoToBody4() {
    // Arrange
    SipParser sipParser = new SipParser("foo", 1);

    // Act
    SipParser actualGoToBodyResult = sipParser.goToBody();

    // Assert
    assertSame(sipParser, actualGoToBodyResult);
    assertEquals(3, actualGoToBodyResult.getPos());
  }

  @Test
  void testGoToBody5() {
    // Arrange
    SipParser sipParser = new SipParser("\n\n", 1);

    // Act
    SipParser actualGoToBodyResult = sipParser.goToBody();

    // Assert
    assertSame(sipParser, actualGoToBodyResult);
    assertEquals(2, actualGoToBodyResult.getPos());
  }

  @Test
  void testGetHeader() {
    // Arrange
    SipParser sipParser = new SipParser("foo");

    // Act and Assert
    assertNull(sipParser.getHeader());
    assertEquals(3, sipParser.getPos());
  }

  @Test
  void testGetHeader2() {
    // Arrange
    SipParser sipParser = new SipParser("<tel:");

    // Act
    Header actualHeader = sipParser.getHeader();

    // Assert
    assertEquals("<tel", actualHeader.getName());
    assertEquals("", actualHeader.getValue());
    assertEquals(5, sipParser.getPos());
  }

  @Test
  void testGetHeader3() {
    // Arrange, Act and Assert
    assertNull((new SipParser("")).getHeader());
  }

  @Test
  void testGetHeader4() {
    // Arrange
    SipParser sipParser = new SipParser("<tel:", 0);

    // Act
    Header actualHeader = sipParser.getHeader();

    // Assert
    assertEquals("<tel", actualHeader.getName());
    assertEquals("", actualHeader.getValue());
    assertEquals(5, sipParser.getPos());
  }

  @Test
  void testGetHeader5() {
    // Arrange, Act and Assert
    assertNull((new SipParser("foo")).getHeader("Hname"));
  }

  @Test
  void testGetHeader6() {
    // Arrange, Act and Assert
    assertNull((new SipParser("GMT")).getHeader("Hname"));
  }

  @Test
  void testGetHeader7() {
    // Arrange
    SipParser sipParser = new SipParser("foo");

    // Act
    Header actualHeader = sipParser.getHeader("");

    // Assert
    assertEquals("", actualHeader.getName());
    assertEquals("foo", actualHeader.getValue());
    assertEquals(3, sipParser.getPos());
  }

  @Test
  void testGetHeader8() {
    // Arrange, Act and Assert
    assertNull((new SipParser("<tel:", 0)).getHeader("Hname"));
  }

  @Test
  void testGetRequestLine() {
    // Arrange
    SipParser sipParser = new SipParser("foo");

    // Act
    RequestLine actualRequestLine = sipParser.getRequestLine();

    // Assert
    assertNull(actualRequestLine.getAddress());
    assertEquals("foo", actualRequestLine.getMethod());
    assertEquals(3, sipParser.getPos());
  }

  @Test
  void testGetRequestLine2() {
    // Arrange
    SipParser sipParser = new SipParser("");

    // Act
    RequestLine actualRequestLine = sipParser.getRequestLine();

    // Assert
    assertNull(actualRequestLine.getAddress());
    assertEquals("", actualRequestLine.getMethod());
    assertEquals(0, sipParser.getPos());
  }

  @Test
  void testGetRequestLine3() {
    // Arrange
    SipParser sipParser = new SipParser("", 0);

    // Act
    RequestLine actualRequestLine = sipParser.getRequestLine();

    // Assert
    assertNull(actualRequestLine.getAddress());
    assertEquals("", actualRequestLine.getMethod());
    assertEquals(0, sipParser.getPos());
  }

  @Test
  void testGetRequestLine4() {
    // Arrange
    SipParser sipParser = new SipParser("foo", 2);

    // Act
    RequestLine actualRequestLine = sipParser.getRequestLine();

    // Assert
    assertNull(actualRequestLine.getAddress());
    assertEquals("o", actualRequestLine.getMethod());
    assertEquals(3, sipParser.getPos());
  }

  @Test
  void testGetStatusLine() {
    // Arrange
    SipParser sipParser = new SipParser("<tel:");

    // Act and Assert
    assertNull(sipParser.getStatusLine());
    assertEquals(5, sipParser.getPos());
  }

  @Test
  void testGetStatusLine2() {
    // Arrange
    SipParser sipParser = new SipParser("<tel:", 1);

    // Act and Assert
    assertNull(sipParser.getStatusLine());
    assertEquals(5, sipParser.getPos());
  }

  @Test
  void testGetURI() {
    // Arrange
    SipParser sipParser = new SipParser("foo");

    // Act and Assert
    assertNull(sipParser.getURI());
    assertEquals(3, sipParser.getPos());
  }

  @Test
  void testGetURI2() {
    // Arrange
    SipParser sipParser = new SipParser("<tel:");

    // Act and Assert
    assertEquals("tel:", sipParser.getURI().toString());
    assertEquals(5, sipParser.getPos());
  }

  @Test
  void testGetNameAddress() {
    // Arrange
    SipParser sipParser = new SipParser("foo");

    // Act
    NameAddress actualNameAddress = sipParser.getNameAddress();

    // Assert
    assertEquals("<sip:foo>", actualNameAddress.toString());
    assertFalse(actualNameAddress.hasDisplayName());
    GenericURI address = actualNameAddress.getAddress();
    assertFalse(address.isTelURI());
    assertFalse(((SipURI) address).isSecure());
    assertEquals(3, sipParser.getPos());
  }

  @Test
  void testGetNameAddress2() {
    // Arrange
    SipParser sipParser = new SipParser("sips:");

    // Act
    NameAddress actualNameAddress = sipParser.getNameAddress();

    // Assert
    assertEquals("<sips:>", actualNameAddress.toString());
    assertNull(actualNameAddress.getDisplayName());
    assertTrue(actualNameAddress.getAddress().isSipURI());
    assertEquals(5, sipParser.getPos());
  }

  @Test
  void testGetNameAddress3() {
    // Arrange
    SipParser sipParser = new SipParser("<tel:");

    // Act
    NameAddress actualNameAddress = sipParser.getNameAddress();

    // Assert
    assertEquals("<tel:>", actualNameAddress.toString());
    assertNull(actualNameAddress.getDisplayName());
    assertFalse(actualNameAddress.getAddress().isSipURI());
    assertEquals(5, sipParser.getPos());
  }

  @Test
  void testGetNameAddress4() {
    // Arrange
    SipParser sipParser = new SipParser("");

    // Act
    NameAddress actualNameAddress = sipParser.getNameAddress();

    // Assert
    assertEquals("<sip:>", actualNameAddress.toString());
    assertFalse(actualNameAddress.hasDisplayName());
    GenericURI address = actualNameAddress.getAddress();
    assertFalse(address.isTelURI());
    assertFalse(((SipURI) address).isSecure());
    assertEquals(0, sipParser.getPos());
  }

  @Test
  void testGetNameAddress5() {
    // Arrange
    SipParser sipParser = new SipParser("", 0);

    // Act
    NameAddress actualNameAddress = sipParser.getNameAddress();

    // Assert
    assertEquals("<sip:>", actualNameAddress.toString());
    assertFalse(actualNameAddress.hasDisplayName());
    GenericURI address = actualNameAddress.getAddress();
    assertFalse(address.isTelURI());
    assertFalse(((SipURI) address).isSecure());
    assertEquals(0, sipParser.getPos());
  }

  @Test
  void testGetDate() {
    // Arrange
    SipParser sipParser = new SipParser("foo");

    // Act and Assert
    assertNull(sipParser.getDate());
    assertNull(sipParser.getHeader());
  }

  @Test
  void testGetDate2() {
    // Arrange
    SipParser sipParser = new SipParser("", 1);

    // Act and Assert
    assertNull(sipParser.getDate());
    assertNull(sipParser.getHeader());
  }

  @Test
  void testGetDate3() {
    // Arrange
    SipParser sipParser = new SipParser("", -1);

    // Act and Assert
    assertNull(sipParser.getDate());
    assertNull(sipParser.getHeader());
  }

  @Test
  void testGetDate4() {
    // Arrange
    StringBuffer stringBuffer = new StringBuffer("Str");
    stringBuffer.appendCodePoint(1);
    SipParser sipParser = new SipParser(stringBuffer);

    // Act and Assert
    assertNull(sipParser.getDate());
    assertNull(sipParser.getHeader());
  }

  @Test
  void testGetDate5() {
    // Arrange
    StringBuffer stringBuffer = new StringBuffer("Str");
    stringBuffer.insert(2, new char[]{'\u0000', '1', '\u0000', '\u0000'});
    stringBuffer.appendCodePoint(1);
    SipParser sipParser = new SipParser(stringBuffer);

    // Act and Assert
    assertNull(sipParser.getDate());
    assertNull(sipParser.getHeader());
  }

  @Test
  void testGetParameter() {
    // Arrange
    SipParser sipParser = new SipParser("foo");

    // Act and Assert
    assertNull(sipParser.getParameter("Name"));
    assertEquals(3, sipParser.getPos());
  }

  @Test
  void testGetParameter2() {
    // Arrange
    SipParser sipParser = new SipParser("foo", 1);

    // Act and Assert
    assertNull(sipParser.getParameter("Name"));
    assertEquals(3, sipParser.getPos());
  }

  @Test
  void testGetParameter3() {
    // Arrange
    SipParser sipParser = new SipParser("<tel:", 0);

    // Act and Assert
    assertNull(sipParser.getParameter("Name"));
    assertEquals(5, sipParser.getPos());
  }

  @Test
  void testGetParameterNames() {
    // Arrange
    SipParser sipParser = new SipParser("foo");

    // Act
    Vector actualParameterNames = sipParser.getParameterNames();

    // Assert
    assertEquals(1, actualParameterNames.size());
    assertEquals("foo", actualParameterNames.get(0));
    assertEquals(3, sipParser.getPos());
  }

  @Test
  void testGetParameterNames2() {
    // Arrange
    SipParser sipParser = new SipParser("<tel:", 0);

    // Act
    Vector actualParameterNames = sipParser.getParameterNames();

    // Assert
    assertEquals(1, actualParameterNames.size());
    assertEquals("<tel:", actualParameterNames.get(0));
    assertEquals(5, sipParser.getPos());
  }

  @Test
  void testHasParameter() {
    // Arrange
    SipParser sipParser = new SipParser("foo");

    // Act and Assert
    assertFalse(sipParser.hasParameter("Name"));
    assertEquals(3, sipParser.getPos());
  }

  @Test
  void testHasParameter2() {
    // Arrange
    SipParser sipParser = new SipParser("Name");

    // Act and Assert
    assertTrue(sipParser.hasParameter("Name"));
    assertEquals(4, sipParser.getPos());
  }

  @Test
  void testHasParameter3() {
    // Arrange
    SipParser sipParser = new SipParser("<tel:", 0);

    // Act and Assert
    assertFalse(sipParser.hasParameter("Name"));
    assertEquals(5, sipParser.getPos());
  }

  @Test
  void testIndexOfCommaHeaderSeparator() {
    // Arrange, Act and Assert
    assertEquals(-1, (new SipParser("foo")).indexOfCommaHeaderSeparator());
    assertEquals(-1, (new SipParser("<tel:", 0)).indexOfCommaHeaderSeparator());
    assertEquals(-1, (new SipParser(new Parser("\""))).indexOfCommaHeaderSeparator());
  }

  @Test
  void testGoToCommaHeaderSeparator() {
    // Arrange
    SipParser sipParser = new SipParser("foo");

    // Act
    SipParser actualGoToCommaHeaderSeparatorResult = sipParser.goToCommaHeaderSeparator();

    // Assert
    assertSame(sipParser, actualGoToCommaHeaderSeparatorResult);
    assertEquals(3, actualGoToCommaHeaderSeparatorResult.getPos());
  }

  @Test
  void testGoToCommaHeaderSeparator2() {
    // Arrange
    SipParser sipParser = new SipParser("<tel:", 0);

    // Act
    SipParser actualGoToCommaHeaderSeparatorResult = sipParser.goToCommaHeaderSeparator();

    // Assert
    assertSame(sipParser, actualGoToCommaHeaderSeparatorResult);
    assertEquals(5, actualGoToCommaHeaderSeparatorResult.getPos());
  }

  @Test
  void testGoToCommaHeaderSeparator3() {
    // Arrange
    SipParser sipParser = new SipParser(new Parser("\""));

    // Act
    SipParser actualGoToCommaHeaderSeparatorResult = sipParser.goToCommaHeaderSeparator();

    // Assert
    assertSame(sipParser, actualGoToCommaHeaderSeparatorResult);
    assertEquals(1, actualGoToCommaHeaderSeparatorResult.getPos());
  }
}

