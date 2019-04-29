/**
 *
 */
package com.gms.xms.workflow.client;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.model.FranchiseInfoModel;
import com.gms.xms.model.UserModel;
import com.gms.xms.model.franchisepayable.FranchisePayablePeriodModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.txndb.vo.FranchiseInfoVo;
import com.gms.xms.txndb.vo.FranchisePayableFilter;
import com.gms.xms.txndb.vo.FranchisePayablePeriodVo;
import com.gms.xms.txndb.vo.UserVo;
import com.gms.xms.workflow.core.WorkFlowManager;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Posted from FranchisePayableClient.java
 * <p>
 * Author Toantq Date Mar 22, 2015 Time: 6:03:24 PM
 */
public class FranchisePayableClient extends WorkflowBaseClient {
    public FranchisePayableClient(Map<String, String> addInfo) {
        super(addInfo);
    }

    protected static final Log log = LogFactory.getLog(FranchisePayableClient.class);

    /**
     * return franchise information by workflow
     *
     * @param franchiseCode - string franchise code
     * @param ctxReturn     - return context in result (true: return, false : no return)
     * @return - result in map
     * @throws Exception - on error
     */
    public Map<String, Object> getFranchiseInfoByCode(String franchiseCode, boolean ctxReturn) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        ContextBase context = new ContextBase(this.getAddInfo());
        context.put(Attributes.FRANCHISE_CODE, franchiseCode);
        context.put(Attributes.WFP_NAME, "Wfl-GetFranchiseInfoByCode");
        context = WorkFlowManager.getInstance().process(context);

        if (ErrorCode.SUCCESS.equalsIgnoreCase(context.get(Attributes.ERROR_CODE))) {
            String franchise = context.get(Attributes.FRANCHISE_INFO_RESULT);
            FranchiseInfoVo franchiseInfoVo = GsonUtils.fromGson(franchise, FranchiseInfoVo.class);
            resultMap.put(Attributes.RESULT, ModelUtils.createModelFromVo(franchiseInfoVo, FranchiseInfoModel.class));
        }
        if (ctxReturn) {
            resultMap.put(Attributes.CONTEXT_RESULT, context);
        }
        return resultMap;
    }

    /**
     * return franchise information by franchise code
     *
     * @param franchiseCode - string franchise code
     * @return - map result
     * @throws Exception - on error
     */
    public Map<String, Object> getFranchiseInfoByCode(String franchiseCode) throws Exception {
        return this.getFranchiseInfoByCode(franchiseCode, false);
    }

    /**
     * return user information by workflow
     *
     * @param userId
     * @param ctxReturn - return context in result (true: return, false : no return)
     * @return - map result
     * @throws Exception - on error
     */
    public Map<String, Object> getUserById(String userId, boolean ctxReturn) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        ContextBase context = new ContextBase(this.getAddInfo());
        context.put(Attributes.USER_ID, userId);
        context.put(Attributes.WFP_NAME, "Wfl-GetUserById");
        context = WorkFlowManager.getInstance().process(context);

        if (ErrorCode.SUCCESS.equalsIgnoreCase(context.get(Attributes.ERROR_CODE))) {
            String user = context.get(Attributes.USER_INFO_RESULT);
            UserVo userVo = GsonUtils.fromGson(user, UserVo.class);
            resultMap.put(Attributes.RESULT, ModelUtils.createModelFromVo(userVo, UserModel.class));
        }

        if (ctxReturn) {
            resultMap.put(Attributes.CONTEXT_RESULT, context);
        }

        return resultMap;
    }

    /**
     * return user information by workflow
     *
     * @param userId
     * @return - map result
     * @throws Exception - on error
     */
    public Map<String, Object> getUserById(String userId) throws Exception {
        return this.getUserById(userId, false);
    }

    /**
     * return franchise informaion by user managed
     *
     * @param userId
     * @param ctxReturn - return context in result (true: return, false : no return)
     * @return - map result
     * @throws Exception
     */
    public Map<String, Object> getFranchiseListManagedByUser(String userId, boolean ctxReturn) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        ContextBase context = new ContextBase(this.getAddInfo());

        context.put(Attributes.USER_ID, userId);
        context.put(Attributes.WFP_NAME, "Wfl-GetFranchiseListManagedByUser");
        context = WorkFlowManager.getInstance().process(context);

        if (ErrorCode.SUCCESS.equalsIgnoreCase(context.get(Attributes.ERROR_CODE))) {
            String franchiseList = context.get(Attributes.FRANCHISE_LIST_RESULT);
            Type type = new TypeToken<List<FranchiseInfoVo>>() {
            }.getType();
            List<FranchiseInfoVo> franchiseListVo = GsonUtils.fromGson(franchiseList, type);
            resultMap.put(Attributes.RESULT, ModelUtils.createListModelFromVo(franchiseListVo, FranchiseInfoModel.class));
        }

        if (ctxReturn) {
            resultMap.put(Attributes.CONTEXT_RESULT, context);
        }

        return resultMap;
    }

    /**
     * return franchise information managed by user
     *
     * @param userId
     * @return - map result
     * @throws Exception - on error
     */
    public Map<String, Object> getFranchiseListManagedByUser(String userId) throws Exception {
        return this.getFranchiseListManagedByUser(userId, false);
    }

    /**
     * return managed franchise by franchise code
     *
     * @param franchiseCode
     * @param ctxReturn     - return context in result (true: return, false : no return)
     * @return - map result
     * @throws Exception - on error
     */
    public Map<String, Object> getParentFranchiseByCode(String franchiseCode, boolean ctxReturn) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        ContextBase context = new ContextBase(this.getAddInfo());
        context.put(Attributes.FRANCHISE_CODE, franchiseCode);
        context.put(Attributes.WFP_NAME, "Wfl-GetParentFranchiseByCode");
        context = WorkFlowManager.getInstance().process(context);

        if (ErrorCode.SUCCESS.equalsIgnoreCase(context.get(Attributes.ERROR_CODE))) {
            String franchise = context.get(Attributes.FRANCHISE_INFO_RESULT);
            FranchiseInfoVo franchiseVo = GsonUtils.fromGson(franchise, FranchiseInfoVo.class);
            resultMap.put(Attributes.RESULT, ModelUtils.createModelFromVo(franchiseVo, FranchiseInfoModel.class));
        }

        if (ctxReturn) {
            resultMap.put(Attributes.CONTEXT_RESULT, context);
        }

        return resultMap;
    }

    /**
     * return managed franchise information by franchise code
     *
     * @param franchiseCode
     * @return - map result
     * @throws Exception - on error
     */
    public Map<String, Object> getParentFranchiseByCode(String franchiseCode) throws Exception {
        return this.getParentFranchiseByCode(franchiseCode, false);
    }

    /**
     * return list of Unfrozen list
     *
     * @param ctxReturn - return context in result (true: return, false : no return)
     * @return - map result
     * @throws Exception - on error
     */
    public Map<String, Object> getUnFrozenPeriodList(boolean ctxReturn) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        ContextBase context = new ContextBase(this.getAddInfo());

        context.put(Attributes.WFP_NAME, "Wfl-GetUnfrozenPeriodList");
        context = WorkFlowManager.getInstance().process(context);

        if (ErrorCode.SUCCESS.equalsIgnoreCase(context.get(Attributes.ERROR_CODE))) {
            String franchiseList = context.get(Attributes.PERIOD_LIST_RESULT);
            Type type = new TypeToken<List<FranchisePayablePeriodVo>>() {
            }.getType();
            List<FranchisePayablePeriodVo> periodListVo = GsonUtils.fromGson(franchiseList, type);
            resultMap.put(Attributes.RESULT, ModelUtils.createListModelFromVo(periodListVo, FranchisePayablePeriodModel.class));
        }

        if (ctxReturn) {
            resultMap.put(Attributes.CONTEXT_RESULT, context);
        }

        return resultMap;
    }

    /**
     * return list of Unfrozen list
     *
     * @return - map result
     * @throws Exception - on error
     */
    public Map<String, Object> getUnFrozenPeriodList() throws Exception {
        return this.getUnFrozenPeriodList(false);
    }

    /**
     * return list of frozen list
     *
     * @param ctxReturn - return context in result (true: return, false : no return)
     * @return - map result
     * @throws Exception - on error
     */
    public Map<String, Object> getFrozenPeriodList(boolean ctxReturn) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        ContextBase context = new ContextBase(this.getAddInfo());

        context.put(Attributes.WFP_NAME, "Wfl-GetFrozenPeriodList");
        context = WorkFlowManager.getInstance().process(context);

        if (ErrorCode.SUCCESS.equalsIgnoreCase(context.get(Attributes.ERROR_CODE))) {
            String franchiseList = context.get(Attributes.PERIOD_LIST_RESULT);
            Type type = new TypeToken<List<FranchisePayablePeriodVo>>() {
            }.getType();
            List<FranchisePayablePeriodVo> periodListVo = GsonUtils.fromGson(franchiseList, type);
            resultMap.put(Attributes.RESULT, ModelUtils.createListModelFromVo(periodListVo, FranchisePayablePeriodModel.class));
        }

        if (ctxReturn) {
            resultMap.put(Attributes.CONTEXT_RESULT, context);
        }

        return resultMap;
    }

    /**
     * return list of Unfrozen list
     *
     * @return - map result
     * @throws Exception - on error
     */
    public Map<String, Object> getFrozenPeriodList() throws Exception {
        return this.getFrozenPeriodList(false);
    }

    /**
     * freeze franchise payable report
     *
     * @param filter
     * @param ctxReturn - return context in result (true: return, false : no return)
     * @return - map result
     * @throws Exception - on error
     */
    public Map<String, Object> freezePayableReport(FranchisePayableFilter filter, boolean ctxReturn) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        ContextBase context = new ContextBase(this.getAddInfo());

        context.put(Attributes.WFP_NAME, "Wfl-FreezeFranchisePayableReport");
        context.put(Attributes.FRANCHISE_PAYABLE_FILTER, GsonUtils.toGson(filter));
        context = WorkFlowManager.getInstance().process(context);

        if (ctxReturn) {
            resultMap.put(Attributes.CONTEXT_RESULT, context);
        }

        return resultMap;
    }

    /**
     * freeze franchise payable report
     *
     * @param filter
     * @return - map result
     * @throws Exception - on error
     */
    public Map<String, Object> freezePayableReport(FranchisePayableFilter filter) throws Exception {
        return this.freezePayableReport(filter, false);
    }
}
