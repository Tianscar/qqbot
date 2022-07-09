package com.lrk.qqbot;

import java.io.File;
import java.sql.*;
import java.util.logging.Logger;

public class SQLData {

    public static final String DB_NAME = "data.db";

    private static final Logger logger = Logger.getLogger(SQLData.class.getSimpleName());
    private final Statement sqldata;
    private final Connection sql;

    public SQLData() throws Exception {
        Class.forName("org.sqlite.JDBC");
        sql = DriverManager.getConnection("jdbc:sqlite:" + Data.home.getAbsolutePath() + File.separator + DB_NAME);
        File database = new File(Data.home.getAbsolutePath() + File.separator + DB_NAME);
        if (database.length() == 0) {
            sqldata = sql.createStatement();
            sqldata.execute("CREATE TABLE UserReg(ID LONG PRIMARY KEY NOT NULL,REG_TIME DATE NOT NULL,ABLED TEXT NOT NULL,PermissionGroup TEXT NOT NULL);");
            sqldata.execute("CREATE TABLE UserData(ID LONG PRIMARY KEY NOT NULL);");
            logger.info("Preparing SQL...");
        } else {
            sqldata = sql.createStatement();
        }
    }
    //更新用户数据
    public void update(long qq,String TableName,String TargetKey,String TargetValue) throws SQLException {
        sqldata.executeUpdate("UPDATE "+TableName+" SET "+TargetKey+"= '"+TargetValue+"' WHERE ID="+qq+";");
    }
    //获取用户数据
    public String get(long qq, String tableName, String targetKey) throws SQLException {
        String data = null;
        ResultSet result = sqldata.executeQuery("SELECT " + targetKey + " FROM " + tableName + " WHERE ID=" + qq + ";");
        while(result.next()){
            data=result.getString(targetKey);
        }
        return data;
    }
    public void dispose() {
        try {
            sqldata.close();
            sql.close();
        }
        catch (SQLException e) {
            for (Throwable ex : e) {
                logger.info(ex.getMessage());
            }
        }
    }

}
