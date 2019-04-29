package com.gms.xms.persistence.service.admin;

import com.gms.xms.txndb.vo.admin.SaveWebshipHistoryVo;

import java.util.Map;

public interface ISaveWebshipHistoryService {
    public void saveWebshipHistory(Map<String, String> context, SaveWebshipHistoryVo saveWebshipHistoryVo) throws Exception;
}
