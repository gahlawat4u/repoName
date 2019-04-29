package com.gms.xms.persistence.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;

import java.sql.SQLException;
import java.util.List;

/**
 * Posted from SqlSessionClient.java
 * <p>
 * Author Toantq Date Apr 4, 2015 Time: 1:53:19 PM
 */
public class SqlSessionClient {
    protected Log log = LogFactory.getLog(SqlSessionClient.class);
    private SqlSession session = null;
    private boolean isTransaction = false;

    private synchronized void openSession() {
        if (session == null) {
            session = MyBatisSettings.getSqlSessionFactory().openSession();
        }
    }

    /**
     * start batch transaction
     */
    public synchronized void startTransaction() {
        isTransaction = true;

    }

    /**
     * end batch transaction
     */
    public synchronized void endTransaction() {
        isTransaction = false;
        if (session != null) {
            session.commit();
            session.close();
            session = null;
        }
    }

    /**
     * rollback batch transaction
     */
    public synchronized void rollback() {
        isTransaction = false;
        if (session != null) {
            session.rollback();
            session.close();
            session = null;
        }
    }

    /**
     * Return a Object
     *
     * @param obj   - filter object
     * @param sqlId - sql query
     * @return a Object
     * @throws SQLException
     */
    public Object select(Object obj, String sqlId) throws SQLException {
        Object t = null;
        openSession();
        try {
            t = session.selectOne(sqlId, obj);
        } catch (Exception e) {
            throw new SQLException(e);
        } finally {
            if (!isTransaction) {
                session.commit();
                session.close();
                session = null;
            }
        }
        return t;
    }

    /**
     * Return a list Object
     *
     * @param obj   - filter object
     * @param sqlId - sql query
     * @return - list of object
     * @throws SQLException
     */
    public List<Object> selectList(Object obj, String sqlId) throws SQLException {
        List<Object> t = null;
        openSession();
        try {
            t = session.selectList(sqlId, obj);
        } catch (Exception e) {
            throw new SQLException(e);
        } finally {
            if (!isTransaction) {
                session.commit();
                session.close();
                session = null;
            }
        }
        return t;
    }

    /**
     * insert a object to database
     *
     * @param obj   - Object to insert
     * @param sqlId - sql id of insert
     * @return - value -1 :no insert,#-1 : inserted
     * @throws SQLException
     */
    public int insert(Object obj, String sqlId) throws SQLException {
        int result = -1;
        openSession();
        try {
            result = session.insert(sqlId, obj);
        } catch (Exception e) {
            throw new SQLException(e);
        } finally {
            if (!isTransaction) {
                session.commit();
                session.close();
                session = null;
            }
        }
        return result;
    }

    /**
     * Update by object
     *
     * @param obj   - filter object
     * @param sqlId - sql id of update
     * @return - int value :<=0:no record updated, >0: number of updated record.
     * @throws SQLException
     */
    public int update(Object obj, String sqlId) throws SQLException {
        int result = -1;
        openSession();
        try {
            result = session.update(sqlId, obj);
        } catch (Exception e) {
            throw new SQLException(e);
        } finally {
            if (!isTransaction) {
                session.commit();
                session.close();
                session = null;
            }
        }
        return result;
    }

    /**
     * delete record by object filter
     *
     * @param obj   - filter object
     * @param sqlId - sql id of delete query
     * @return - int value: number of deleted records
     * @throws SQLException
     */
    public int delete(Object obj, String sqlId) throws SQLException {
        int result = -1;
        openSession();
        try {
            result = session.update(sqlId, obj);
        } catch (Exception e) {
            throw new SQLException(e);
        } finally {
            if (!isTransaction) {
                session.commit();
                session.close();
                session = null;
            }
        }
        return result;
    }
}
