package com.gms.xms.workflow.render.searchairbill;

import com.gms.xms.txndb.vo.invoicing.searchairbill.SearchAirbillFilter;

public interface ISearchAirbillRender {
    public void generateHTMLForPDF(SearchAirbillFilter filter, String outputFilePath) throws Exception;

    public void generateCSVFile(SearchAirbillFilter filter, Integer userLevel, String outputFilePath) throws Exception;
}
