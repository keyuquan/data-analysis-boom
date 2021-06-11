package com.analysis.boom.common.conf;

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
    public static String DORIS_URL;
    public static String DORIS_LOAD_URL;
    public static String DORIS_USER;
    public static String DORIS_PASSWORD;

    //mysql config
    public static String USER_NAME;
    public static String PASS_WORD;
    public static String MYSQL_URL;

    // presto config
    public static String PRESTO_URL;
    public static String PRESTO_USER;
    public static String PRESTO_PASSWORD;

    // kafka config
    public static String KAFKA_SERVERS;
    public static String ZOOKEEPER_SERVERS;

    static {
        //指定要读取的配置文件
        ResourceBundle bundle = ResourceBundle.getBundle("common");
        //获取配置文件里面内容
        ENV_ACTIVE = bundle.getString("env.active").trim();
        DORIS_DRIVER = bundle.getString("doris.driver").trim();
        DORIS_URL = bundle.getString("doris.url").trim();
        DORIS_LOAD_URL= bundle.getString("doris.load.url").trim();
        DORIS_USER = bundle.getString("doris.user").trim();
        DORIS_PASSWORD = bundle.getString("doris.password").trim();

        USER_NAME = bundle.getString("mysql.username");
        PASS_WORD = bundle.getString("mysql.password");
        MYSQL_URL = bundle.getString("mysql.url");

        PRESTO_URL = bundle.getString("presto.url").trim();
        PRESTO_USER = bundle.getString("presto.user").trim();
        PRESTO_PASSWORD = bundle.getString("presto.password").trim();

        KAFKA_SERVERS = bundle.getString("kafka.bootstrap.servers").trim();
        ZOOKEEPER_SERVERS= bundle.getString("kafka.zookeeper.connect").trim();
        logger.info("----------  start  ------------- ");
        logger.info("----------  运行环境 : {}------------- ", ENV_ACTIVE);
    }
}
