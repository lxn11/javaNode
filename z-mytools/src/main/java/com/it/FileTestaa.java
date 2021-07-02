package com.it;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.File;
import java.util.*;

public class FileTestaa {
    public static void main(String[] args) {
        Map<String, Object> pushMap = new HashMap<>(2);
        pushMap.put("loginName", "loginName");
        Map<String, String> informationMap = new HashMap<>(8);
        informationMap.put("applyId","applyId" );
        informationMap.put("companyName","companyName" );
        informationMap.put("creditCode", "creditCode");
        informationMap.put("legalRepresentative", "legalRepresentative");
        informationMap.put("contact", "contact");
        informationMap.put("contactMobile", "contactMobile");
        informationMap.put("contactFax","contactFax" );
        informationMap.put("postalCode","postalCode" );
        informationMap.put("address", "address");
        informationMap.put("province", "province");
        informationMap.put("city", "city");
        informationMap.put("county", "county");
        informationMap.put("remark", "remark");
        informationMap.put("fileNos","fileNos" );
        pushMap.put("informationAddData", informationMap);

        String jsonString = JSONObject.toJSONString(pushMap);
        System.out.println(jsonString);
    }
}
