package com.gms.xms.workflow.service.webship.history;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.txndb.vo.EmailTemplateVo;
import com.gms.xms.txndb.vo.ShipmentNoteFilter;
import com.gms.xms.txndb.vo.ShipmentNoteVo;
import com.gms.xms.txndb.vo.webship.HistoryDetailScheduleInfoVo;
import com.gms.xms.txndb.vo.webship.history.HistoryFilter;
import com.gms.xms.txndb.vo.webship.history.HistoryVo;

import java.util.List;
import java.util.Map;

public interface IHistoryService {
    public List<HistoryVo> selectByFilter(HistoryFilter filter) throws Exception;

    public Integer selectCountByFilter(HistoryFilter filter) throws DaoException;

    public List<ShipmentNoteVo> selectNoteList(ShipmentNoteFilter filter) throws DaoException;

    public Integer insertShipmentNote(Map<String, String> context, ShipmentNoteVo shipmentNoteVo) throws DaoException;

    public String getAwbBarcode(Long shipmentId) throws DaoException;

    public EmailTemplateVo getEmailTemplateByName(String templateName) throws DaoException;

    HistoryDetailScheduleInfoVo selectHistoryDetailScheduleCollection(Long shipmentId) throws DaoException;
}
