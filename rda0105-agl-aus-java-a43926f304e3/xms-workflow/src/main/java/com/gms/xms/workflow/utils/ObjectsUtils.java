package com.gms.xms.workflow.utils;

import com.gms.xms.common.utils.JsonUtils;
import com.gms.xms.model.BaseModel;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * File Name: ObjectsUtils.java <br/>
 * Author: TANDT <br/>
 * Create Date: 01-12-2015 <br/>
 * Project Name: xms-workflow <br/>
 * package Name: com.gms.xms.workflow.utils <br/>
 * Class: ObjectsUtils
 */
public class ObjectsUtils {

    /**
     * Functions: filterModels <br/>
     * Date Time Create: 02-12-2015 - 14:43:50 <br/>
     * Descriptions: Function filterModels ....... <br/>
     *
     * @param ms : List models input
     * @param m  : Model filter input
     * @return List Models Result
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public static <M extends BaseModel> List<M> filterModels(List<M> ms, M m) throws Exception {

        // Model Filter
        Class<M> classM = (Class<M>) m.getClass();
        Field[] fields = classM.getDeclaredFields();

        List<M> listModelResult = new ArrayList<M>();

        // Read List Model Data with Model Filter
        for (int j = 0; j < ms.size(); j++) {
            // Model Data
            Class<M> classMN = (Class<M>) ms.get(j).getClass();
            Field[] fieldsN = classMN.getDeclaredFields();
            Integer feldsValid = 0;
            Integer propertiesFilter = 0;
            for (int i = 0; i < fields.length; i++) {
                fields[i].setAccessible(true);
                fieldsN[i].setAccessible(true);
                String value = JsonUtils.toJson(fields[i].get(m)).toString();
                String valueN = JsonUtils.toJson(fieldsN[i].get(ms.get(j))).toString();
                if (fields[i].get(m) != null) {
                    if (!fields[i].getName().equals("serialVersionUID")) {
                        propertiesFilter = propertiesFilter + 1;
                        if (fieldsN[i].getName().toString() == fields[i].getName().toString() && value.equals(valueN)) {
                            feldsValid = feldsValid + 1;
                        }
                    }
                }
            }
            if (feldsValid == propertiesFilter) {
                listModelResult.add(ms.get(j));
            }
        }
        return listModelResult;
    }
}