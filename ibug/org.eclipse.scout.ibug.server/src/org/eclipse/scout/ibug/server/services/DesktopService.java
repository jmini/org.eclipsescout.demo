package org.eclipse.scout.ibug.server.services;

import java.util.List;

import org.eclipse.scout.commons.StringUtility;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.commons.logger.IScoutLogger;
import org.eclipse.scout.commons.logger.ScoutLogManager;
import org.eclipse.scout.ibug.BugzillaHtmlFetcher;
import org.eclipse.scout.ibug.IBug;
import org.eclipse.scout.ibug.IBugFetcher;
import org.eclipse.scout.ibug.shared.services.DesktopFormData;
import org.eclipse.scout.ibug.shared.services.IDesktopService;
import org.eclipse.scout.service.AbstractService;

public class DesktopService extends AbstractService implements IDesktopService {

  private static final IScoutLogger LOG = ScoutLogManager.getLogger(DesktopService.class);

  private IBugFetcher m_bugFetcher = null;

  public DesktopService() {
    super();
    m_bugFetcher = new BugzillaHtmlFetcher();
  }

  public String getCriteria() {
    return m_bugFetcher.getQueryCriteria();
  }

  public int getMaxNumberOfBugs() {
    return m_bugFetcher.getMaxNumberOfBugs();
  }

  public String getAssignee() {
    return m_bugFetcher.getAssignee();
  }

  public void setCriteria(String criteria) {
    m_bugFetcher.setQueryCriteria(criteria);
  }

  public void setMaxNumberOfBugs(int bugs) {
    m_bugFetcher.setMaxNumberOfBugs(bugs);
  }

  @Override
  public DesktopFormData load(DesktopFormData formData) throws ProcessingException {

    verifySearchCriteria(formData, m_bugFetcher, "Scout");

    List<IBug> bugs = m_bugFetcher.fetchBugs();
    formData.getBugs().clearRows();

    for (IBug bug : bugs) {
      LOG.info("adding bug " + bug);

      formData.getBugs().addRow(new Object[]{
          bug.getId(),
          bug.getSummary(),
          bug.getChanged(),
          bug.getSeverety(),
          bug.getPriority(),
          bug.getTargetMilestone(),
          bug.getStatus(),
          bug.getResolution(),
          bug.getComponent(),
          bug.getAssignee(),
          bug.getSortValue()
      });
    }

    return formData;
  }

  private void verifySearchCriteria(DesktopFormData formData, IBugFetcher bugFetcher, String defaultProduct) {
    String assignee = formData.getAssignee().getValue();
    String product = formData.getProduct().getValue();

    if (StringUtility.isNullOrEmpty(assignee) && StringUtility.isNullOrEmpty(product)) {
      LOG.info("no assignee or product provided, setting product to '" + defaultProduct + "'");
      formData.getProduct().setValue(defaultProduct);
      product = defaultProduct;
    }

    bugFetcher.setAssignee(assignee);
    bugFetcher.setProduct(product);
  }
}