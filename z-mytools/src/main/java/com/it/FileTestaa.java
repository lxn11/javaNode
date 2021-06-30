package com.it;

import com.alibaba.fastjson.JSON;

import java.io.File;
import java.util.*;

public class FileTestaa {
    public static void main(String[] args) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("status",100);
        System.out.println((int) map.get("status"));
        System.out.println((String) map.get("status"));
        System.out.println( map.get("status")instanceof Integer);
        System.out.println( map.get("status") instanceof  String);
    }
}
