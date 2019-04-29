package com.gms.xms.persistence.config;

import org.apache.commons.lang.SystemUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Reader;
import java.util.Properties;

public class MyBatisSettings {
    private static Log log = LogFactory.getLog(MyBatisSettings.class);
    private static SqlSessionFactory factory;
    private static final String GMS_DB_CONFIG_LINUX = "/opt/config/au_mybatis.properties";
    private static final String GMS_DB_CONFIG_WINDOWS = "C:/gms/config/au_mybatis.properties";

    private MyBatisSettings() {
    }

    static {
        try {
            Reader configReader = Resources.getResourceAsReader("mybatis-config.xml");

            Properties configProps = new Properties();
            String propsPath = SystemUtils.IS_OS_WINDOWS ? GMS_DB_CONFIG_WINDOWS : GMS_DB_CONFIG_LINUX;
            File propsFile = new File(propsPath);
            if (propsFile.exists()) {
                InputStream propsInputStream = new FileInputStream(propsFile);
                configProps.load(propsInputStream);
            }

            if (!configProps.isEmpty()) {
                factory = new SqlSessionFactoryBuilder().build(configReader, configProps);
            } else {
                factory = new SqlSessionFactoryBuilder().build(configReader);
            }
        } catch (Exception e) {
            log.error(e);
            throw new RuntimeException(e.getMessage());
        }
    }

    public static SqlSessionFactory getSqlSessionFactory() {
        return factory;
    }
}
