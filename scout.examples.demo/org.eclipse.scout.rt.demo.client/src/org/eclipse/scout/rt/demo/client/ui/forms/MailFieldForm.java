package org.eclipse.scout.rt.demo.client.ui.forms;

import java.io.InputStream;

import javax.mail.internet.MimeMessage;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCloseButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractLinkButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.mailfield.AbstractMailField;
import org.eclipse.scout.rt.demo.client.Activator;
import org.eclipse.scout.rt.demo.client.ui.forms.MailFieldForm.MainBox.CloseButton;
import org.eclipse.scout.rt.demo.client.ui.forms.MailFieldForm.MainBox.HTMLButton;
import org.eclipse.scout.rt.demo.client.ui.forms.MailFieldForm.MainBox.HTMLWithAttachmentsButton;
import org.eclipse.scout.rt.demo.client.ui.forms.MailFieldForm.MainBox.HTMLWithInnerImagesButton;
import org.eclipse.scout.rt.demo.client.ui.forms.MailFieldForm.MainBox.MailField;
import org.eclipse.scout.rt.demo.client.ui.forms.MailFieldForm.MainBox.TextButton;
import org.eclipse.scout.rt.shared.TEXTS;

public class MailFieldForm extends AbstractForm implements IPageForm {

  public MailFieldForm() throws ProcessingException {
    super();
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("MailField");
  }

  @Override
  public void startPageForm() throws ProcessingException {
    startInternal(new PageFormHandler());
  }

  public CloseButton getCloseButton() {
    return getFieldByClass(CloseButton.class);
  }

  public HTMLButton getHTMLButton() {
    return getFieldByClass(HTMLButton.class);
  }

  public HTMLWithAttachmentsButton getHTMLWithAttachmentsButton() {
    return getFieldByClass(HTMLWithAttachmentsButton.class);
  }

  public HTMLWithInnerImagesButton getHTMLWithInnerImagesButton() {
    return getFieldByClass(HTMLWithInnerImagesButton.class);
  }

  public MailField getMailField() {
    return getFieldByClass(MailField.class);
  }

  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  public TextButton getTextButton() {
    return getFieldByClass(TextButton.class);
  }

  @Order(10.0)
  public class MainBox extends AbstractGroupBox {

    @Order(10.0)
    public class MailField extends AbstractMailField {

      @Override
      protected int getConfiguredGridH() {
        return 20;
      }

      @Override
      protected int getConfiguredGridW() {
        return 2;
      }

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("MailField");
      }

      @Override
      protected boolean getConfiguredLabelVisible() {
        return false;
      }
    }

    @Order(20.0)
    public class TextButton extends AbstractLinkButton {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("Text");
      }

      @Override
      protected void execClickAction() throws ProcessingException {
        loadFile("textMail.mail");
      }
    }

    @Order(30.0)
    public class HTMLButton extends AbstractLinkButton {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("HTML");
      }

      @Override
      protected void execClickAction() throws ProcessingException {
        loadFile("htmlMail.mail");
      }
    }

    @Order(40.0)
    public class HTMLWithInnerImagesButton extends AbstractLinkButton {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("HTMLWithInnerImages");
      }

      @Override
      protected void execClickAction() throws ProcessingException {
        loadFile("htmlMailWithInnerImages.mail");
      }
    }

    @Order(50.0)
    public class HTMLWithAttachmentsButton extends AbstractLinkButton {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("HTMLWithAttachments");
      }

      @Override
      protected void execClickAction() throws ProcessingException {
        loadFile("htmlMailWithAttachments.mail");
      }
    }

    @Order(60.0)
    public class CloseButton extends AbstractCloseButton {
    }
  }

  public void loadFile(String fileName) throws ProcessingException {
    try {
      InputStream inStream = FileLocator.openStream(Activator.getDefault().getBundle(), new Path("resources/mails/" + fileName), true);
      MimeMessage message = new MimeMessage(null, inStream);
      getMailField().setValue(message);
    }
    catch (Exception e) {
      throw new ProcessingException(null, e);
    }
  }

  public class PageFormHandler extends AbstractFormHandler {
  }
}