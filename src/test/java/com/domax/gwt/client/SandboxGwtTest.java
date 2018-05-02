package com.domax.gwt.client;

import com.google.gwt.junit.client.GWTTestCase;

public class SandboxGwtTest extends GWTTestCase {

  @Override
  public String getModuleName() {
    return "com.domax.gwt.GXTSample";
  }

  public void testSandbox() {
    assertTrue(true);
  }
}
