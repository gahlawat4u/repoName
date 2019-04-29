package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.persistence.utils.XmsLogHelper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Posted from BaseDaoServiceService.java
 * <p>
 * Author Toantq Date Mar 22, 2015 Time: 5:17:54 PM
 */
public abstract class BaseDao<T> {
    public static final Log logger = LogFactory.getLog(BaseDao.class);

    private SqlSessionClient sqlSessionClient;

    public BaseDao() {
        this.sqlSessionClient = new SqlSessionClient();
    }

    public BaseDao(SqlSessionClient sqlSessionClient) {
        this.sqlSessionClient = sqlSessionClient;
    }

    @SuppressWarnings("unchecked")
    protected T select(Object obj, String sqlQuery) throws DaoException {
        try {
            T result = (T) sqlSessionClient.select(obj, sqlQuery);
            return result;
        } catch (SQLException e) {
            throw new DaoException("Error running SQL statement: " + sqlQuery + ". Error message is " + e.getMessage(), e);
        }
    }

    protected Object selectObject(Object obj, String sqlQuery) throws DaoException {
        try {
            Object result = sqlSessionClient.select(obj, sqlQuery);
            return result;
        } catch (SQLException e) {
            throw new DaoException("Error running SQL statement: " + sqlQuery + ". Error message is " + e.getMessage(), e);
        }
    }

    private Integer insert(Object obj, String sqlQuery) throws DaoException {
        try {
            return sqlSessionClient.insert(obj, sqlQuery);
        } catch (SQLException e) {
            throw new DaoException("Error running SQL statement: " + sqlQuery + ". Error message is " + e.getMessage(), e);
        }
    }

    protected Integer insert(Map<String, String> context, Object obj, String sqlQuery) throws DaoException {
        XmsLogHelper eventLogHelper = new XmsLogHelper(sqlSessionClient, context, obj, sqlQuery);
        eventLogHelper.logInsert();
        return insert(obj, sqlQuery);
    }

    private Integer update(Object obj, String sqlQuery) throws DaoException {
        try {
            int i = sqlSessionClient.update(obj, sqlQuery);
            return i;
        } catch (SQLException e) {
            throw new DaoException("Error running SQL statement: " + sqlQuery + ". Error message is " + e.getMessage(), e);
        }
    }

    protected Integer update(Map<String, String> context, Object obj, String sqlQuery) throws DaoException {
        XmsLogHelper eventLogHelper = new XmsLogHelper(sqlSessionClient, context, obj, sqlQuery);
        eventLogHelper.logUpdate();
        return update(obj, sqlQuery);
    }

    private Integer delete(Object obj, String sqlQuery) throws DaoException {
        try {
            int i = sqlSessionClient.delete(obj, sqlQuery);
            return i;
        } catch (SQLException e) {
            throw new DaoException("Error running SQL statement: " + sqlQuery + ". Error message is " + e.getMessage(), e);
        }
    }

    protected Integer delete(Map<String, String> context, Object obj, String sqlQuery) throws DaoException {
        XmsLogHelper eventLogHelper = new XmsLogHelper(sqlSessionClient, context, obj, sqlQuery);
        eventLogHelper.logDelete();
        return delete(obj, sqlQuery);
    }

    @SuppressWarnings("unchecked")
    protected <B extends T> List<B> selectList(Object obj, String sqlQuery) throws DaoException {
        try {
            return (List<B>) sqlSessionClient.selectList(obj, sqlQuery);
        } catch (SQLException e) {
            throw new DaoException("Error running SQL statement: " + sqlQuery + ". Error message is " + e.getMessage(), e);
        }
    }

    protected List<Object> selectListObject(Object obj, String sqlQuery) throws DaoException {
        try {
            return (List<Object>) sqlSessionClient.selectList(obj, sqlQuery);
        } catch (SQLException e) {
            throw new DaoException("Error running SQL statement: " + sqlQuery + ". Error message is " + e.getMessage(), e);
        }
    }

    protected List<Object> selectObjectList(Object obj, String sqlQuery) throws DaoException {
        try {
            return sqlSessionClient.selectList(obj, sqlQuery);
        } catch (SQLException e) {
            throw new DaoException("Error running SQL statement: " + sqlQuery + ". Error message is " + e.getMessage(), e);
        }
    }
}
