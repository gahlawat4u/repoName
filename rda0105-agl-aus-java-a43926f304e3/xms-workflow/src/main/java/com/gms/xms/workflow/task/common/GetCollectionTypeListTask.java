package com.gms.xms.workflow.task.common;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.CollectionTypeDao;
import com.gms.xms.txndb.vo.webship.CollectionTypeVo;
import com.gms.xms.workflow.core.Task;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

public class GetCollectionTypeListTask implements Task {
    private static final Log log = LogFactory.getLog(GetCollectionTypeListTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        CollectionTypeDao collectionTypeDao = new CollectionTypeDao();
        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            List<CollectionTypeVo> billingTypeVoList = collectionTypeDao.getCollectionTypeList();
            context.put(Attributes.COLLECTION_TYPE_LIST_RESULT, GsonUtils.toGson(billingTypeVoList));
        } catch (Exception e) {
            log.error(e.getMessage());
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            return false;
        }
        return true;
    }
}
