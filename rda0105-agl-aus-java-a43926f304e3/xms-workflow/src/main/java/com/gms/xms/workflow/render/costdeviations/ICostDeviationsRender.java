package com.gms.xms.workflow.render.costdeviations;

import com.gms.xms.filter.invoicing.CostDeviationFilter;

public interface ICostDeviationsRender {
    public void generateHtmlFile(CostDeviationFilter filter, String outputFilePath, String realPath) throws Exception;

    public void generateXlsFile(CostDeviationFilter filter, String outputFilePath) throws Exception;
}
