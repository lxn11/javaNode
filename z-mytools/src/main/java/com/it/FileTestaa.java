package com.it;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class FileTestaa {
    public static void main(String[] args) {
        StringBuilder sql = new StringBuilder("SELECT COUNT(1) AS COUNTS," +
                "(SELECT INTERFACE_NAME FROM INTERFACE_DETAILS where id =INTERFACE_DETAILS_ID) AS INTERFACENMAE  " +
                "FROM INTERFACE_STATISCS where DEL_FLAG = '0' ");
        sql.append(" GROUP BY INTERFACE_DETAILS_ID ;");
        System.out.println(sql.toString());
    }
}
