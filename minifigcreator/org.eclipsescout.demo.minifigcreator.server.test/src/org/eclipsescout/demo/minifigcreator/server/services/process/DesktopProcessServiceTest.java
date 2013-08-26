/**
 * 
 */
package org.eclipsescout.demo.minifigcreator.server.services.process;

import org.eclipse.scout.commons.exception.VetoException;
import org.eclipse.scout.rt.testing.server.runner.ScoutServerTestRunner;
import org.eclipse.scout.service.SERVICES;
import org.eclipsescout.demo.minifigcreator.shared.services.process.DesktopFormData;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Tests for {@link org.eclipsescout.demo.minifigcreator.server.services.process.DesktopProcessService}.
 * 
 * @author jbr
 */
@RunWith(ScoutServerTestRunner.class)
public class DesktopProcessServiceTest {

  /**
   * Test method for {@link DesktopProcessService#store(DesktopFormData)}.
   * The name is invalid. An error is expected from the Service.
   */
  @Test(expected = VetoException.class)
  public void testStoreInvalidName() throws Exception {
    DesktopFormData formData = new DesktopFormData();
    formData.getName().setValue("xxx");

    SERVICES.getService(DesktopProcessService.class).store(formData);
  }
}
