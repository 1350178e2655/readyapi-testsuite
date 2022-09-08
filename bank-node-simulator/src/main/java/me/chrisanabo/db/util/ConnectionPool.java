package me.chrisanabo.db.util;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.HikariConfig;



public class ConnectionPool {

    private static volatile ConnectionPool instance;

    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;

    private static String value;

    private ConnectionPool(String value) {
        this.value = value;
    }

    static {

        config.setPoolName(value);

        config.setMaximumPoolSize( Integer.valueOf( System.getenv("maximumPoolSize")) );
        config.setMinimumIdle( Integer.valueOf( System.getenv("minimumIdle")) );
        config.setJdbcUrl(System.getenv("conn_string"));

        config.setUsername( "database_username" );
        config.setPassword( "database_password" );

        config.addDataSourceProperty( "cachePrepStmts" , "true" );
        config.addDataSourceProperty( "prepStmtCacheSize" , "250" );
        config.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
        ds = new HikariDataSource( config );

    }




    public static ConnectionPool getInstance(String value) {

        String valuex = System.getProperty("read_from_minus_d");
        String log_dir = System.getenv("read_from_env_variable");
        String contextPath =  System.getenv("read_from_env_variable");

        ConnectionPool result = instance;
        if (result != null) {
            return result;
        }
        synchronized(ConnectionPool.class) {
            if (instance == null) {
                instance = new ConnectionPool(value);
            }
            return instance;
        }
    }

    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

}