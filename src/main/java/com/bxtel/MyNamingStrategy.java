package com.bxtel;

import org.hibernate.cfg.ImprovedNamingStrategy;
import org.hibernate.cfg.NamingStrategy;

/*
 * hibernate 表命名策略 
 * http://blog.csdn.net/xiaofengxiaoling/article/details/9092253
 */
public class MyNamingStrategy extends ImprovedNamingStrategy implements NamingStrategy {
//    @Override
//    public String columnName(String columnName) {
//        return addUnderscores(columnName).toUpperCase();
//    }
//    @Override
//    public String tableName(String tableName) {
//        return "qy_"+addUnderscores(tableName).toUpperCase();
//    }
	
	private String currentTablePrefix="qy_";
	
    @Override
    public String classToTableName(String className) {
        return currentTablePrefix+ super.classToTableName(className);
    }
}
