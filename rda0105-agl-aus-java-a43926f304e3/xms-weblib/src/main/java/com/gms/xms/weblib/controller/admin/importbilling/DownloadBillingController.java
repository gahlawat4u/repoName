package com.gms.xms.weblib.controller.admin.importbilling;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.model.admin.imports.BillingResultModel;
import com.gms.xms.weblib.controller.AdminJsonBaseController;
import com.gms.xms.workflow.core2.WorkFlowManager2;
import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Posted from Jun 2, 2016 2:09:41 PM
 * <p>
 * Author huynd
 */
public class DownloadBillingController extends AdminJsonBaseController {

    private static final long serialVersionUID = 1L;

    private List<BillingResultModel> results;
    private String beginDownload;
    private String endDownload;

    public String saveDownload() {
        try {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            this.setBeginDownload(dateFormat.format(new Date()));
            Map<String, String> addInfoMap = this.getAddInfoMap();
            // Because this action maybe to used when the user not log
            // so get user name to detect the user logged or not?
            String userName = addInfoMap.get(Attributes.ADD_INFO_EXT_USERNAME);
            if (StringUtils.isBlank(userName)) {
                // Put user info using for system logging.
                addInfoMap.put(Attributes.ADD_INFO_EXT_USERNAME, "apache user run at backend");
                addInfoMap.put(Attributes.ADD_INFO_EXT_IS_ADMIN, AppConstants.YES_FLAG);
            }
            ContextBase2 context = new ContextBase2(addInfoMap);
            String filePath = request.getSession().getServletContext().getRealPath("") + "/download_billing_files/";
            context.put(Attributes.DOWNLOAD_FILE_PATH, filePath);
            context.put(Attributes.WFP_NAME, "Wfl-DownloadBillingDHL");
            context = WorkFlowManager2.getInstance().process(context);
            List<BillingResultModel> results = context.get(Attributes.BILLING_RESULTS);
            this.setResults(results);
            this.setEndDownload(dateFormat.format(new Date()));
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public List<BillingResultModel> getResults() {
        return results;
    }

    public void setResults(List<BillingResultModel> results) {
        this.results = results;
    }

    public String getBeginDownload() {
        return beginDownload;
    }

    public void setBeginDownload(String beginDownload) {
        this.beginDownload = beginDownload;
    }

    public String getEndDownload() {
        return endDownload;
    }

    public void setEndDownload(String endDownload) {
        this.endDownload = endDownload;
    }
}
