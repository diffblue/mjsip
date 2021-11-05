package org.mjsip.sip.provider;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.mjsip.sip.header.CSeqHeader;
import org.mjsip.sip.message.SipMessage;

class MethodIdDiffblueTest {
  @Test
  void testConstructor() {
    // Arrange, Act and Assert
    assertEquals("Method", (new MethodId("Method")).toString());
    assertEquals("Method", (new MethodId(new MethodId("Method"))).toString());
    assertEquals("ANY", (new MethodId(new MethodId(new MethodId("ANY")))).toString());
  }

  @Test
  void testConstructor2() {
    // Arrange
    SipMessage sipMessage = mock(SipMessage.class);
    when(sipMessage.getCSeqHeader()).thenReturn(new CSeqHeader("42"));

    // Act and Assert
    assertEquals("", (new MethodId(sipMessage)).toString());
    verify(sipMessage).getCSeqHeader();
  }
}

