package org.zoolu.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Vector;
import org.junit.jupiter.api.Test;

class ParserDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange and Act
    Parser actualParser = new Parser("foo");
    actualParser.setPos(1);

    // Assert
    assertEquals("foo", actualParser.getWholeString());
  }

  @Test
  void testConstructor2() {
    // Arrange, Act and Assert
    assertThrows(RuntimeException.class, () -> new Parser((String) null));
  }

  @Test
  void testConstructor3() {
    // Arrange, Act and Assert
    assertThrows(RuntimeException.class, () -> new Parser((String) null, 1));

  }

  @Test
  void testConstructor4() {
    // Arrange, Act and Assert
    assertThrows(RuntimeException.class, () -> new Parser((StringBuffer) null));
  }

  @Test
  void testConstructor5() {
    // Arrange, Act and Assert
    assertThrows(RuntimeException.class, () -> new Parser((StringBuffer) null, 1));

  }

  @Test
  void testGetRemainingString() {
    // Arrange, Act and Assert
    assertEquals("foo", (new Parser("foo")).getRemainingString());
    assertEquals("", (new Parser("", 0)).getRemainingString());
  }

  @Test
  void testLength() {
    // Arrange, Act and Assert
    assertEquals(3, (new Parser("foo")).length());
  }

  @Test
  void testHasMore() {
    // Arrange, Act and Assert
    assertTrue((new Parser("foo")).hasMore());
    assertFalse((new Parser("")).hasMore());
  }

  @Test
  void testCharAt() {
    // Arrange, Act and Assert
    assertEquals('o', (new Parser("foo")).charAt(1));
    assertEquals('o', (new Parser("foo", 1)).charAt(1));
  }

  @Test
  void testNextChar() {
    // Arrange, Act and Assert
    assertEquals('f', (new Parser("foo")).nextChar());
    assertEquals('o', (new Parser("foo", 1)).nextChar());
  }

  @Test
  void testGoTo() {
    // Arrange
    Parser parser = new Parser("foo");

    // Act
    Parser actualGoToResult = parser.goTo('A');

    // Assert
    assertSame(parser, actualGoToResult);
    assertFalse(actualGoToResult.hasMore());
  }

  @Test
  void testGoTo2() {
    // Arrange
    Parser parser = new Parser("\"");

    // Act
    Parser actualGoToResult = parser.goTo("foo");

    // Assert
    assertSame(parser, actualGoToResult);
    assertFalse(actualGoToResult.hasMore());
  }

  @Test
  void testGoTo3() {
    // Arrange
    Parser parser = new Parser("foo");

    // Act
    Parser actualGoToResult = parser.goTo("AAAA".toCharArray());

    // Assert
    assertSame(parser, actualGoToResult);
    assertFalse(actualGoToResult.hasMore());
  }

  @Test
  void testGoTo4() {
    // Arrange
    Parser parser = new Parser("foo", -1);

    // Act
    Parser actualGoToResult = parser.goTo(new char[]{});

    // Assert
    assertSame(parser, actualGoToResult);
    assertFalse(actualGoToResult.hasMore());
  }

  @Test
  void testGoTo5() {
    // Arrange
    Parser parser = new Parser("foo");

    // Act
    Parser actualGoToResult = parser.goTo(new String[]{"Ss"});

    // Assert
    assertSame(parser, actualGoToResult);
    assertFalse(actualGoToResult.hasMore());
  }

  @Test
  void testGoToIgnoreCase() {
    // Arrange
    Parser parser = new Parser("\"");

    // Act
    Parser actualGoToIgnoreCaseResult = parser.goToIgnoreCase("foo");

    // Assert
    assertSame(parser, actualGoToIgnoreCaseResult);
    assertFalse(actualGoToIgnoreCaseResult.hasMore());
  }

  @Test
  void testGoToIgnoreCase2() {
    // Arrange
    Parser parser = new Parser(new StringBuffer("Str"));

    // Act
    Parser actualGoToIgnoreCaseResult = parser.goToIgnoreCase("foo");

    // Assert
    assertSame(parser, actualGoToIgnoreCaseResult);
    assertFalse(actualGoToIgnoreCaseResult.hasMore());
  }

  @Test
  void testGoToIgnoreCase3() {
    // Arrange
    Parser parser = new Parser("foo");
    parser.goToSkippingQuoted('\u0000');

    // Act
    Parser actualGoToIgnoreCaseResult = parser.goToIgnoreCase((String) null);

    // Assert
    assertSame(parser, actualGoToIgnoreCaseResult);
    assertFalse(actualGoToIgnoreCaseResult.hasMore());
  }

  @Test
  void testGoToIgnoreCase4() {
    // Arrange
    Parser parser = new Parser("foo");

    // Act
    Parser actualGoToIgnoreCaseResult = parser.goToIgnoreCase(new String[]{"Ss"});

    // Assert
    assertSame(parser, actualGoToIgnoreCaseResult);
    assertFalse(actualGoToIgnoreCaseResult.hasMore());
  }

  @Test
  void testGoToIgnoreCase5() {
    // Arrange
    Parser parser = new Parser("\"");

    // Act
    Parser actualGoToIgnoreCaseResult = parser.goToIgnoreCase(new String[]{"Ss"});

    // Assert
    assertSame(parser, actualGoToIgnoreCaseResult);
    assertFalse(actualGoToIgnoreCaseResult.hasMore());
  }

  @Test
  void testGoToIgnoreCase6() {
    // Arrange
    Parser parser = new Parser(new StringBuffer("Str"));

    // Act
    Parser actualGoToIgnoreCaseResult = parser.goToIgnoreCase(new String[]{"Ss"});

    // Assert
    assertSame(parser, actualGoToIgnoreCaseResult);
    assertFalse(actualGoToIgnoreCaseResult.hasMore());
  }

  @Test
  void testGoToIgnoreCase7() {
    // Arrange
    Parser parser = new Parser("foo");

    // Act
    Parser actualGoToIgnoreCaseResult = parser.goToIgnoreCase(new String[]{});

    // Assert
    assertSame(parser, actualGoToIgnoreCaseResult);
    assertFalse(actualGoToIgnoreCaseResult.hasMore());
  }

  @Test
  void testGoToNextLine() {
    // Arrange
    Parser parser = new Parser("foo");

    // Act
    Parser actualGoToNextLineResult = parser.goToNextLine();

    // Assert
    assertSame(parser, actualGoToNextLineResult);
    assertFalse(actualGoToNextLineResult.hasMore());
  }

  @Test
  void testGoToNextLine2() {
    // Arrange
    Parser parser = new Parser("\"", 0);

    // Act
    Parser actualGoToNextLineResult = parser.goToNextLine();

    // Assert
    assertSame(parser, actualGoToNextLineResult);
    assertFalse(actualGoToNextLineResult.hasMore());
  }

  @Test
  void testIsAnyOf() {
    // Arrange, Act and Assert
    assertTrue(Parser.isAnyOf("AAAA".toCharArray(), 'A'));
    assertTrue(Parser.isAnyOf(new char[]{'\u0000', 'A', 'A', 'A'}, 'A'));
    assertFalse(Parser.isAnyOf(new char[]{}, 'A'));
  }

  @Test
  void testIsUpAlpha() {
    // Arrange, Act and Assert
    assertTrue(Parser.isUpAlpha('A'));
    assertFalse(Parser.isUpAlpha('\u0000'));
    assertFalse(Parser.isUpAlpha('a'));
  }

  @Test
  void testIsLowAlpha() {
    // Arrange, Act and Assert
    assertFalse(Parser.isLowAlpha('A'));
    assertTrue(Parser.isLowAlpha('a'));
    assertFalse(Parser.isLowAlpha('~'));
  }

  @Test
  void testIsAlpha() {
    // Arrange, Act and Assert
    assertTrue(Parser.isAlpha('A'));
    assertFalse(Parser.isAlpha('\u0000'));
    assertTrue(Parser.isAlpha('a'));
    assertFalse(Parser.isAlpha('~'));
  }

  @Test
  void testIsAlphanum() {
    // Arrange, Act and Assert
    assertTrue(Parser.isAlphanum('A'));
    assertFalse(Parser.isAlphanum('\u0000'));
    assertTrue(Parser.isAlphanum('a'));
    assertTrue(Parser.isAlphanum('0'));
    assertFalse(Parser.isAlphanum('?'));
    assertFalse(Parser.isAlphanum('~'));
  }

  @Test
  void testIsDigit() {
    // Arrange, Act and Assert
    assertFalse(Parser.isDigit('A'));
    assertFalse(Parser.isDigit('\u0000'));
    assertTrue(Parser.isDigit('0'));
  }

  @Test
  void testIsChar() {
    // Arrange, Act and Assert
    assertTrue(Parser.isChar('A'));
    assertFalse(Parser.isChar('\u0000'));
  }

  @Test
  void testIsCR() {
    // Arrange, Act and Assert
    assertFalse(Parser.isCR('A'));
    assertTrue(Parser.isCR('\r'));
  }

  @Test
  void testIsLF() {
    // Arrange, Act and Assert
    assertFalse(Parser.isLF('A'));
    assertTrue(Parser.isLF('\n'));
  }

  @Test
  void testIsCRLF() {
    // Arrange, Act and Assert
    assertFalse(Parser.isCRLF('A'));
    assertTrue(Parser.isCRLF('\r'));
  }

  @Test
  void testIsHT() {
    // Arrange, Act and Assert
    assertFalse(Parser.isHT('A'));
    assertTrue(Parser.isHT('\t'));
  }

  @Test
  void testIsSP() {
    // Arrange, Act and Assert
    assertFalse(Parser.isSP('A'));
    assertTrue(Parser.isSP(' '));
  }

  @Test
  void testIsWSP() {
    // Arrange, Act and Assert
    assertFalse(Parser.isWSP('A'));
    assertTrue(Parser.isWSP(' '));
  }

  @Test
  void testIsWSPCRLF() {
    // Arrange, Act and Assert
    assertFalse(Parser.isWSPCRLF('A'));
    assertTrue(Parser.isWSPCRLF(' '));
  }

  @Test
  void testCompareIgnoreCase() {
    // Arrange, Act and Assert
    assertEquals(0, Parser.compareIgnoreCase('A', 'A'));
    assertEquals(-97, Parser.compareIgnoreCase('\u0000', 'A'));
    assertEquals(0, Parser.compareIgnoreCase('a', 'A'));
    assertEquals(97, Parser.compareIgnoreCase('A', '\u0000'));
  }

  @Test
  void testIndexOf() {
    // Arrange, Act and Assert
    assertEquals(-1, (new Parser("foo")).indexOf('A'));
    assertEquals(0, (new Parser("foo")).indexOf("foo"));
    assertEquals(-1, (new Parser("foo", -1)).indexOf(new char[]{}));
    assertEquals(-1, (new Parser("foo")).indexOf(new String[]{"Ss"}));
    assertEquals(0, (new Parser("foo")).indexOf(new String[]{""}));
  }

  @Test
  void testIndexOf2() {
    // Arrange
    Parser parser = new Parser("foo");

    // Act and Assert
    assertEquals(-1, parser.indexOf("AAAA".toCharArray()));
  }

  @Test
  void testIndexOf3() {
    // Arrange
    Parser parser = new Parser("foo");

    // Act and Assert
    assertEquals(0, parser.indexOf("fAAA".toCharArray()));
  }

  @Test
  void testIndexOfIgnoreCase() {
    // Arrange, Act and Assert
    assertEquals(0, (new Parser("foo")).indexOfIgnoreCase("foo"));
    assertEquals(-1, (new Parser("\"")).indexOfIgnoreCase("foo"));
    assertEquals(-1, (new Parser(new StringBuffer("Str"))).indexOfIgnoreCase("foo"));
    assertEquals(-1, (new Parser("foo")).indexOfIgnoreCase(new String[]{"Ss"}));
    assertEquals(-1, (new Parser("\"")).indexOfIgnoreCase(new String[]{"Ss"}));
    assertEquals(-1, (new Parser(new StringBuffer("Str"))).indexOfIgnoreCase(new String[]{"Ss"}));
    assertEquals(-1, (new Parser("foo")).indexOfIgnoreCase(new String[]{}));
    assertEquals(0, (new Parser("foo")).indexOfIgnoreCase(new String[]{""}));
  }

  @Test
  void testIndexOfNextLine() {
    // Arrange, Act and Assert
    assertEquals(-1, (new Parser("foo")).indexOfNextLine());
  }

  @Test
  void testStartsWith() {
    // Arrange, Act and Assert
    assertTrue((new Parser("foo")).startsWith("foo"));
    assertFalse((new Parser("\"")).startsWith("foo"));
    assertFalse((new Parser("foo")).startsWith(new String[]{"Ss"}));
    assertTrue((new Parser("foo")).startsWith(new String[]{""}));
  }

  @Test
  void testStartsWithIgnoreCase() {
    // Arrange, Act and Assert
    assertTrue((new Parser("foo")).startsWithIgnoreCase("foo"));
    assertFalse((new Parser("\"")).startsWithIgnoreCase("foo"));
    assertTrue((new Parser("")).startsWithIgnoreCase("foo"));
    assertFalse((new Parser(new StringBuffer("Str"))).startsWithIgnoreCase("foo"));
    assertFalse((new Parser("foo")).startsWithIgnoreCase(new String[]{"Ss"}));
    assertFalse((new Parser("\"")).startsWithIgnoreCase(new String[]{"Ss"}));
    assertTrue((new Parser("")).startsWithIgnoreCase(new String[]{"Ss"}));
    assertFalse((new Parser(new StringBuffer("Str"))).startsWithIgnoreCase(new String[]{"Ss"}));
    assertTrue((new Parser("foo")).startsWithIgnoreCase(new String[]{""}));
  }

  @Test
  void testSkipChar() {
    // Arrange
    Parser parser = new Parser("");

    // Act and Assert
    assertSame(parser, parser.skipChar());
  }

  @Test
  void testSkipN() {
    // Arrange
    Parser parser = new Parser("");

    // Act
    Parser actualSkipNResult = parser.skipN(1);

    // Assert
    assertSame(parser, actualSkipNResult);
    assertFalse(actualSkipNResult.hasMore());
  }

  @Test
  void testSkipWSP() {
    // Arrange
    Parser parser = new Parser("foo");

    // Act and Assert
    assertSame(parser, parser.skipWSP());
  }

  @Test
  void testSkipWSP2() {
    // Arrange
    Parser parser = new Parser("");

    // Act and Assert
    assertSame(parser, parser.skipWSP());
  }

  @Test
  void testSkipWSP3() {
    // Arrange
    Parser parser = new Parser("\"", 0);

    // Act and Assert
    assertSame(parser, parser.skipWSP());
  }

  @Test
  void testSkipCRLF() {
    // Arrange
    Parser parser = new Parser("foo");

    // Act and Assert
    assertSame(parser, parser.skipCRLF());
  }

  @Test
  void testSkipCRLF2() {
    // Arrange
    Parser parser = new Parser("");

    // Act and Assert
    assertSame(parser, parser.skipCRLF());
  }

  @Test
  void testSkipCRLF3() {
    // Arrange
    Parser parser = new Parser("\"", 0);

    // Act and Assert
    assertSame(parser, parser.skipCRLF());
  }

  @Test
  void testSkipWSPCRLF() {
    // Arrange
    Parser parser = new Parser("foo");

    // Act and Assert
    assertSame(parser, parser.skipWSPCRLF());
  }

  @Test
  void testSkipWSPCRLF2() {
    // Arrange
    Parser parser = new Parser("");

    // Act and Assert
    assertSame(parser, parser.skipWSPCRLF());
  }

  @Test
  void testSkipWSPCRLF3() {
    // Arrange
    Parser parser = new Parser("\"", 0);

    // Act and Assert
    assertSame(parser, parser.skipWSPCRLF());
  }

  @Test
  void testSkipChars() {
    // Arrange
    Parser parser = new Parser("foo");

    // Act and Assert
    assertSame(parser, parser.skipChars("AAAA".toCharArray()));
  }

  @Test
  void testSkipChars2() {
    // Arrange
    Parser parser = new Parser("");

    // Act and Assert
    assertSame(parser, parser.skipChars("AAAA".toCharArray()));
  }

  @Test
  void testSkipChars3() {
    // Arrange
    Parser parser = new Parser("\"", 0);

    // Act and Assert
    assertSame(parser, parser.skipChars("AAAA".toCharArray()));
  }

  @Test
  void testSkipString() {
    // Arrange
    Parser parser = new Parser("foo");

    // Act
    Parser actualSkipStringResult = parser.skipString();

    // Assert
    assertSame(parser, actualSkipStringResult);
    assertFalse(actualSkipStringResult.hasMore());
  }

  @Test
  void testSkipString2() {
    // Arrange
    Parser parser = new Parser("");

    // Act
    Parser actualSkipStringResult = parser.skipString();

    // Assert
    assertSame(parser, actualSkipStringResult);
    assertFalse(actualSkipStringResult.hasMore());
  }

  @Test
  void testSkipString3() {
    // Arrange
    Parser parser = new Parser("", 0);

    // Act
    Parser actualSkipStringResult = parser.skipString();

    // Assert
    assertSame(parser, actualSkipStringResult);
    assertFalse(actualSkipStringResult.hasMore());
  }

  @Test
  void testSkipString4() {
    // Arrange
    Parser parser = new Parser("foo", 2);

    // Act
    Parser actualSkipStringResult = parser.skipString();

    // Assert
    assertSame(parser, actualSkipStringResult);
    assertFalse(actualSkipStringResult.hasMore());
  }

  @Test
  void testGetString() {
    // Arrange
    Parser parser = new Parser("foo");

    // Act and Assert
    assertEquals("foo", parser.getString());
    assertFalse(parser.hasMore());
  }

  @Test
  void testGetString2() {
    // Arrange
    Parser parser = new Parser("");

    // Act and Assert
    assertEquals("", parser.getString());
    assertFalse(parser.hasMore());
  }

  @Test
  void testGetString3() {
    // Arrange
    Parser parser = new Parser("", 0);

    // Act and Assert
    assertEquals("", parser.getString());
    assertFalse(parser.hasMore());
  }

  @Test
  void testGetString4() {
    // Arrange
    Parser parser = new Parser("foo", 2);

    // Act and Assert
    assertEquals("o", parser.getString());
    assertFalse(parser.hasMore());
  }

  @Test
  void testGetString5() {
    // Arrange
    Parser parser = new Parser("foo");

    // Act and Assert
    assertEquals("foo", parser.getString(3));
    assertFalse(parser.hasMore());
  }

  @Test
  void testGetString6() {
    // Arrange
    Parser parser = new Parser(new StringBuffer("Str"));

    // Act and Assert
    assertEquals("Str", parser.getString(3));
    assertFalse(parser.hasMore());
  }

  @Test
  void testGetWord() {
    // Arrange
    Parser parser = new Parser("foo");

    // Act and Assert
    assertEquals("foo", parser.getWord("AAAA".toCharArray()));
    assertFalse(parser.hasMore());
  }

  @Test
  void testGetWord2() {
    // Arrange
    Parser parser = new Parser("");

    // Act and Assert
    assertEquals("", parser.getWord("AAAA".toCharArray()));
    assertFalse(parser.hasMore());
  }

  @Test
  void testGetWord3() {
    // Arrange
    Parser parser = new Parser("foo");

    // Act and Assert
    assertEquals("oo", parser.getWord("fAAA".toCharArray()));
    assertFalse(parser.hasMore());
  }

  @Test
  void testGetWord4() {
    // Arrange
    Parser parser = new Parser("", 0);

    // Act and Assert
    assertEquals("", parser.getWord("AAAA".toCharArray()));
    assertFalse(parser.hasMore());
  }

  @Test
  void testGetWord5() {
    // Arrange
    Parser parser = new Parser("foo", 2);

    // Act and Assert
    assertEquals("o", parser.getWord("AAAA".toCharArray()));
    assertFalse(parser.hasMore());
  }

  @Test
  void testGetInt() {
    // Arrange
    Parser parser = new Parser("42");

    // Act and Assert
    assertEquals(42, parser.getInt());
    assertFalse(parser.hasMore());
  }

  @Test
  void testGetInt2() {
    // Arrange
    Parser parser = new Parser("42", 1);

    // Act and Assert
    assertEquals(2, parser.getInt());
    assertFalse(parser.hasMore());
  }

  @Test
  void testGetInt3() {
    // Arrange
    Parser parser = new Parser(new StringBuffer("42"));

    // Act and Assert
    assertEquals(42, parser.getInt());
    assertFalse(parser.hasMore());
  }

  @Test
  void testGetLong() {
    // Arrange
    Parser parser = new Parser("42");

    // Act and Assert
    assertEquals(42L, parser.getLong());
    assertFalse(parser.hasMore());
  }

  @Test
  void testGetLong2() {
    // Arrange
    Parser parser = new Parser("42", 1);

    // Act and Assert
    assertEquals(2L, parser.getLong());
    assertFalse(parser.hasMore());
  }

  @Test
  void testGetLong3() {
    // Arrange
    Parser parser = new Parser(new StringBuffer("42"));

    // Act and Assert
    assertEquals(42L, parser.getLong());
    assertFalse(parser.hasMore());
  }

  @Test
  void testGetDouble() {
    // Arrange
    Parser parser = new Parser("42");

    // Act and Assert
    assertEquals(42.0, parser.getDouble());
    assertFalse(parser.hasMore());
  }

  @Test
  void testGetDouble2() {
    // Arrange
    Parser parser = new Parser("42", 1);

    // Act and Assert
    assertEquals(2.0, parser.getDouble());
    assertFalse(parser.hasMore());
  }

  @Test
  void testGetDouble3() {
    // Arrange
    Parser parser = new Parser(new StringBuffer("42"));

    // Act and Assert
    assertEquals(42.0, parser.getDouble());
    assertFalse(parser.hasMore());
  }

  @Test
  void testGetLine() {
    // Arrange
    Parser parser = new Parser("foo");

    // Act and Assert
    assertEquals("foo", parser.getLine());
    assertFalse(parser.hasMore());
  }

  @Test
  void testGetLine2() {
    // Arrange
    Parser parser = new Parser("", 0);

    // Act and Assert
    assertEquals("", parser.getLine());
    assertFalse(parser.hasMore());
  }

  @Test
  void testGetLine3() {
    // Arrange
    Parser parser = new Parser("foo", 2);

    // Act and Assert
    assertEquals("o", parser.getLine());
    assertFalse(parser.hasMore());
  }

  @Test
  void testGetIntVector() {
    // Arrange
    Parser parser = new Parser("42");

    // Act
    Vector actualIntVector = parser.getIntVector();

    // Assert
    assertEquals(1, actualIntVector.size());
    assertEquals(42, actualIntVector.get(0));
    assertFalse(parser.hasMore());
  }

  @Test
  void testGetIntVector2() {
    // Arrange
    Parser parser = new Parser("42");

    // Act
    Vector actualIntVector = parser.getIntVector("AAAA".toCharArray());

    // Assert
    assertEquals(1, actualIntVector.size());
    assertEquals(42, actualIntVector.get(0));
    assertFalse(parser.hasMore());
  }

  @Test
  void testGetIntArray() {
    // Arrange
    Parser parser = new Parser("42");

    // Act
    int[] actualIntArray = parser.getIntArray();

    // Assert
    assertEquals(1, actualIntArray.length);
    assertEquals(42, actualIntArray[0]);
    assertFalse(parser.hasMore());
  }

  @Test
  void testGetIntArray2() {
    // Arrange
    Parser parser = new Parser("42");

    // Act
    int[] actualIntArray = parser.getIntArray("AAAA".toCharArray());

    // Assert
    assertEquals(1, actualIntArray.length);
    assertEquals(42, actualIntArray[0]);
    assertFalse(parser.hasMore());
  }

  @Test
  void testGetLongVector() {
    // Arrange
    Parser parser = new Parser("42");

    // Act
    Vector actualLongVector = parser.getLongVector();

    // Assert
    assertEquals(1, actualLongVector.size());
    assertEquals(42L, actualLongVector.get(0));
    assertFalse(parser.hasMore());
  }

  @Test
  void testGetLongVector2() {
    // Arrange
    Parser parser = new Parser("42");

    // Act
    Vector actualLongVector = parser.getLongVector("AAAA".toCharArray());

    // Assert
    assertEquals(1, actualLongVector.size());
    assertEquals(42L, actualLongVector.get(0));
    assertFalse(parser.hasMore());
  }

  @Test
  void testGetLongArray() {
    // Arrange
    Parser parser = new Parser("42");

    // Act
    long[] actualLongArray = parser.getLongArray();

    // Assert
    assertEquals(1, actualLongArray.length);
    assertEquals(42L, actualLongArray[0]);
    assertFalse(parser.hasMore());
  }

  @Test
  void testGetLongArray2() {
    // Arrange
    Parser parser = new Parser("42");

    // Act
    long[] actualLongArray = parser.getLongArray("AAAA".toCharArray());

    // Assert
    assertEquals(1, actualLongArray.length);
    assertEquals(42L, actualLongArray[0]);
    assertFalse(parser.hasMore());
  }

  @Test
  void testGetDoubleVector() {
    // Arrange
    Parser parser = new Parser("42");

    // Act
    Vector actualDoubleVector = parser.getDoubleVector();

    // Assert
    assertEquals(1, actualDoubleVector.size());
    assertEquals(42.0, actualDoubleVector.get(0));
    assertFalse(parser.hasMore());
  }

  @Test
  void testGetDoubleVector2() {
    // Arrange
    Parser parser = new Parser("42");

    // Act
    Vector actualDoubleVector = parser.getDoubleVector("AAAA".toCharArray());

    // Assert
    assertEquals(1, actualDoubleVector.size());
    assertEquals(42.0, actualDoubleVector.get(0));
    assertFalse(parser.hasMore());
  }

  @Test
  void testGetDoubleArray() {
    // Arrange
    Parser parser = new Parser("42");

    // Act
    double[] actualDoubleArray = parser.getDoubleArray();

    // Assert
    assertEquals(1, actualDoubleArray.length);
    assertEquals(42.0, actualDoubleArray[0]);
    assertFalse(parser.hasMore());
  }

  @Test
  void testGetDoubleArray2() {
    // Arrange
    Parser parser = new Parser("42");

    // Act
    double[] actualDoubleArray = parser.getDoubleArray("AAAA".toCharArray());

    // Assert
    assertEquals(1, actualDoubleArray.length);
    assertEquals(42.0, actualDoubleArray[0]);
    assertFalse(parser.hasMore());
  }

  @Test
  void testGetStringVector() {
    // Arrange
    Parser parser = new Parser("foo");

    // Act
    Vector actualStringVector = parser.getStringVector();

    // Assert
    assertEquals(1, actualStringVector.size());
    assertEquals("foo", actualStringVector.get(0));
    assertFalse(parser.hasMore());
  }

  @Test
  void testGetStringVector2() {
    // Arrange
    StringBuffer stringBuffer = new StringBuffer("Str");
    stringBuffer.appendCodePoint(1);
    Parser parser = new Parser(stringBuffer);

    // Act
    Vector actualStringVector = parser.getStringVector();

    // Assert
    assertEquals(2, actualStringVector.size());
    assertEquals("Str", actualStringVector.get(0));
    assertEquals("", actualStringVector.get(1));
    assertFalse(parser.hasMore());
  }

  @Test
  void testGetWordVector() {
    // Arrange
    Parser parser = new Parser("foo");

    // Act
    Vector actualWordVector = parser.getWordVector("AAAA".toCharArray());

    // Assert
    assertEquals(1, actualWordVector.size());
    assertEquals("foo", actualWordVector.get(0));
    assertFalse(parser.hasMore());
  }

  @Test
  void testGetWordVector2() {
    // Arrange
    Parser parser = new Parser("foo");

    // Act
    Vector actualWordVector = parser.getWordVector("fAAA".toCharArray());

    // Assert
    assertEquals(1, actualWordVector.size());
    assertEquals("oo", actualWordVector.get(0));
    assertFalse(parser.hasMore());
  }

  @Test
  void testGetWordVector3() {
    // Arrange
    Parser parser = new Parser("foo");

    // Act
    Vector actualWordVector = parser.getWordVector(null);

    // Assert
    assertEquals(1, actualWordVector.size());
    assertEquals("foo", actualWordVector.get(0));
    assertFalse(parser.hasMore());
  }

  @Test
  void testGetWordVector4() {
    // Arrange
    Parser parser = new Parser("foo");

    // Act
    Vector actualWordVector = parser.getWordVector("foAA".toCharArray());

    // Assert
    assertEquals(1, actualWordVector.size());
    assertEquals("", actualWordVector.get(0));
    assertFalse(parser.hasMore());
  }

  @Test
  void testGetWordVector5() {
    // Arrange
    Parser parser = new Parser("foo");

    // Act
    Vector actualWordVector = parser.getWordVector(new char[]{'\u0000', 'o', 'A', 'A'});

    // Assert
    assertEquals(2, actualWordVector.size());
    assertEquals("f", actualWordVector.get(0));
    assertEquals("", actualWordVector.get(1));
    assertFalse(parser.hasMore());
  }

  @Test
  void testGetStringArray() {
    // Arrange
    Parser parser = new Parser("foo");

    // Act
    String[] actualStringArray = parser.getStringArray();

    // Assert
    assertEquals(1, actualStringArray.length);
    assertEquals("foo", actualStringArray[0]);
    assertFalse(parser.hasMore());
  }

  @Test
  void testGetStringArray2() {
    // Arrange
    StringBuffer stringBuffer = new StringBuffer("Str");
    stringBuffer.appendCodePoint(1);
    Parser parser = new Parser(stringBuffer);

    // Act
    String[] actualStringArray = parser.getStringArray();

    // Assert
    assertEquals(2, actualStringArray.length);
    assertEquals("Str", actualStringArray[0]);
    assertEquals("", actualStringArray[1]);
    assertFalse(parser.hasMore());
  }

  @Test
  void testGetWordArray() {
    // Arrange
    Parser parser = new Parser("foo");

    // Act
    String[] actualWordArray = parser.getWordArray("AAAA".toCharArray());

    // Assert
    assertEquals(1, actualWordArray.length);
    assertEquals("foo", actualWordArray[0]);
    assertFalse(parser.hasMore());
  }

  @Test
  void testGetWordArray2() {
    // Arrange
    Parser parser = new Parser("foo");

    // Act
    String[] actualWordArray = parser.getWordArray("fAAA".toCharArray());

    // Assert
    assertEquals(1, actualWordArray.length);
    assertEquals("oo", actualWordArray[0]);
    assertFalse(parser.hasMore());
  }

  @Test
  void testGetWordArray3() {
    // Arrange
    Parser parser = new Parser("foo");

    // Act
    String[] actualWordArray = parser.getWordArray(null);

    // Assert
    assertEquals(1, actualWordArray.length);
    assertEquals("foo", actualWordArray[0]);
    assertFalse(parser.hasMore());
  }

  @Test
  void testGetWordArray4() {
    // Arrange
    Parser parser = new Parser("foo");

    // Act
    String[] actualWordArray = parser.getWordArray("foAA".toCharArray());

    // Assert
    assertEquals(1, actualWordArray.length);
    assertEquals("", actualWordArray[0]);
    assertFalse(parser.hasMore());
  }

  @Test
  void testGetWordArray5() {
    // Arrange
    Parser parser = new Parser("foo");

    // Act
    String[] actualWordArray = parser.getWordArray(new char[]{'\u0000', 'o', 'A', 'A'});

    // Assert
    assertEquals(2, actualWordArray.length);
    assertEquals("f", actualWordArray[0]);
    assertEquals("", actualWordArray[1]);
    assertFalse(parser.hasMore());
  }

  @Test
  void testGetWordSkippingQuoted() {
    // Arrange
    Parser parser = new Parser("foo");

    // Act and Assert
    assertEquals("foo", parser.getWordSkippingQuoted("AAAA".toCharArray()));
    assertFalse(parser.hasMore());
  }

  @Test
  void testGetWordSkippingQuoted2() {
    // Arrange
    Parser parser = new Parser("\"");

    // Act and Assert
    assertEquals("\"", parser.getWordSkippingQuoted("AAAA".toCharArray()));
    assertFalse(parser.hasMore());
  }

  @Test
  void testGetWordSkippingQuoted3() {
    // Arrange
    Parser parser = new Parser("");

    // Act and Assert
    assertEquals("", parser.getWordSkippingQuoted("AAAA".toCharArray()));
    assertFalse(parser.hasMore());
  }

  @Test
  void testGetWordSkippingQuoted4() {
    // Arrange
    Parser parser = new Parser("foo");

    // Act and Assert
    assertEquals("oo", parser.getWordSkippingQuoted("fAAA".toCharArray()));
    assertFalse(parser.hasMore());
  }

  @Test
  void testGetWordSkippingQuoted5() {
    // Arrange
    Parser parser = new Parser("", 0);

    // Act and Assert
    assertEquals("", parser.getWordSkippingQuoted("AAAA".toCharArray()));
    assertFalse(parser.hasMore());
  }

  @Test
  void testGetWordSkippingQuoted6() {
    // Arrange
    Parser parser = new Parser("foo", 2);

    // Act and Assert
    assertEquals("o", parser.getWordSkippingQuoted("AAAA".toCharArray()));
    assertFalse(parser.hasMore());
  }

  @Test
  void testGetStringUnquoted() {
    // Arrange
    Parser parser = new Parser("foo");

    // Act and Assert
    assertEquals("foo", parser.getStringUnquoted());
    assertFalse(parser.hasMore());
  }

  @Test
  void testGetStringUnquoted2() {
    // Arrange
    Parser parser = new Parser("\"");

    // Act and Assert
    assertEquals("\"", parser.getStringUnquoted());
    assertFalse(parser.hasMore());
  }

  @Test
  void testGetStringUnquoted3() {
    // Arrange, Act and Assert
    assertEquals("", (new Parser("")).getStringUnquoted());
  }

  @Test
  void testGetStringUnquoted4() {
    // Arrange, Act and Assert
    assertEquals("", (new Parser("", 0)).getStringUnquoted());
  }

  @Test
  void testGetStringUnquoted5() {
    // Arrange
    Parser parser = new Parser("foo", 2);

    // Act and Assert
    assertEquals("o", parser.getStringUnquoted());
    assertFalse(parser.hasMore());
  }

  @Test
  void testGoToSkippingQuoted() {
    // Arrange
    Parser parser = new Parser("foo");

    // Act
    Parser actualGoToSkippingQuotedResult = parser.goToSkippingQuoted('A');

    // Assert
    assertSame(parser, actualGoToSkippingQuotedResult);
    assertFalse(actualGoToSkippingQuotedResult.hasMore());
  }

  @Test
  void testGoToSkippingQuoted2() {
    // Arrange
    Parser parser = new Parser("\"");

    // Act
    Parser actualGoToSkippingQuotedResult = parser.goToSkippingQuoted('A');

    // Assert
    assertSame(parser, actualGoToSkippingQuotedResult);
    assertFalse(actualGoToSkippingQuotedResult.hasMore());
  }

  @Test
  void testGoToSkippingQuoted3() {
    // Arrange
    Parser parser = new Parser("foo");

    // Act and Assert
    assertSame(parser, parser.goToSkippingQuoted('f'));
  }

  @Test
  void testGoToSkippingQuoted4() {
    // Arrange
    Parser parser = new Parser("\"", 0);

    // Act
    Parser actualGoToSkippingQuotedResult = parser.goToSkippingQuoted('A');

    // Assert
    assertSame(parser, actualGoToSkippingQuotedResult);
    assertFalse(actualGoToSkippingQuotedResult.hasMore());
  }
}

