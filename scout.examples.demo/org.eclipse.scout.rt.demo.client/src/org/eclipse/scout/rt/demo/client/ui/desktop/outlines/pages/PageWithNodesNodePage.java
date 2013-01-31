package org.eclipse.scout.rt.demo.client.ui.desktop.outlines.pages;

import java.util.Collection;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithNodes;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import org.eclipse.scout.rt.shared.TEXTS;

public class PageWithNodesNodePage extends AbstractPageWithNodes {

  @Override
  protected String getConfiguredIconId() {
    return org.eclipse.scout.rt.shared.AbstractIcons.TreeNode;
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("PageWithNodes");
  }

  @Override
  protected void execCreateChildPages(Collection<IPage> pageList) throws ProcessingException {
    PageWithTableTablePage pageWithTableTablePage = new PageWithTableTablePage(TEXTS.get("PageWithTable") + " 1");
    pageList.add(pageWithTableTablePage);

    PageWithTableTablePage pageWithTableTablePage0 = new PageWithTableTablePage(TEXTS.get("PageWithTable") + " 2");
    pageList.add(pageWithTableTablePage0);

    PageWithTableTablePage pageWithTableTablePage1 = new PageWithTableTablePage(TEXTS.get("PageWithTable") + " 3");
    pageList.add(pageWithTableTablePage1);

    PageWithTableTablePage pageWithTableTablePage2 = new PageWithTableTablePage(TEXTS.get("PageWithTable") + " 4");
    pageList.add(pageWithTableTablePage2);

    PageWithTableTablePage pageWithTableTablePage3 = new PageWithTableTablePage(TEXTS.get("PageWithTable") + " 5");
    pageList.add(pageWithTableTablePage3);
  }
}