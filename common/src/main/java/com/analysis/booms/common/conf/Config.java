package com.analysis.booms.common.conf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ResourceBundle;

/**
 * @author ：kequan
 * @date ：Created in 2020/5/27 11:37
 * @description：配置类
 * @modified By：
 */
public class Config {
    private static final Logger logger = LoggerFactory.getLogger(Config.class);
    public static String ENV_ACTIVE;
    public static String DORIS_DRIVER;
    public static String DORIS_FENODES;
    public static String DORIS_URL;
    public static String DORIS_USER;
    public static String DORIS_PASSWORD;
    public static String HDFS_URL;
    public static String BROKER_NAME;
    public static String PARTITION_MIN_TIME;
    static {
        //指定要读取的配置文件
        ResourceBundle bundle = ResourceBundle.getBundle("common");
        //获取配置文件里面内容
        ENV_ACTIVE = bundle.getString("env.active").trim();
        DORIS_DRIVER = bundle.getString("doris.driver").trim();
        DORIS_FENODES = bundle.getString("doris.fenodes").trim();
        DORIS_URL = bundle.getString("doris.url").trim();
        DORIS_USER = bundle.getString("doris.user").trim();
        DORIS_PASSWORD = bundle.getString("doris.password").trim();
        HDFS_URL = bundle.getString("hdfs.url").trim();
        BROKER_NAME = bundle.getString("broker.name").trim();
        PARTITION_MIN_TIME=bundle.getString("partition.min.time").trim();
        logger.info("----------  start  ------------- ");
        logger.info("----------  运行环境 : {}------------- ", ENV_ACTIVE);
    }
}
