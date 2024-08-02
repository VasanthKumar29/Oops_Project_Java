package AssestManagement;

import java.util.*;

public class Installation {
  private Software software;
  private Date installationDate;

  public Installation(Software software, Date installationDate) {
    this.software = software;
    this.installationDate = installationDate;
  }

  public Software getSoftware() {
    return software;
  }

  public Date getInstallationDate() {
    return installationDate;
  }

}
