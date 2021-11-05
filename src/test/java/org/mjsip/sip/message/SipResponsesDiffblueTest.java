package org.mjsip.sip.message;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

class SipResponsesDiffblueTest {
  @Test
  void testReasonOf() {
    // Arrange, Act and Assert
    assertNull(SipResponses.reasonOf(1));
    assertEquals("Trying", SipResponses.reasonOf(100));
    assertEquals("Ringing", SipResponses.reasonOf(180));
    assertEquals("Call Is Being Forwarded", SipResponses.reasonOf(181));
    assertEquals("Queued", SipResponses.reasonOf(182));
    assertEquals("Session Progress", SipResponses.reasonOf(183));
    assertEquals("Early Dialog Terminated", SipResponses.reasonOf(199));
    assertEquals("OK", SipResponses.reasonOf(200));
    assertEquals("Accepted (Deprecated)", SipResponses.reasonOf(202));
    assertEquals("No Notification", SipResponses.reasonOf(204));
    assertEquals("Multiple Choices", SipResponses.reasonOf(300));
    assertEquals("Moved Permanently", SipResponses.reasonOf(301));
    assertEquals("Moved Temporarily", SipResponses.reasonOf(302));
    assertEquals("Use Proxy", SipResponses.reasonOf(305));
    assertEquals("Alternative Service", SipResponses.reasonOf(380));
    assertEquals("Bad Request", SipResponses.reasonOf(400));
    assertEquals("Unauthorized", SipResponses.reasonOf(401));
  }
}

