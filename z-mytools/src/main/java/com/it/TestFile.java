package com.it;

import java.io.*;

public class TestFile {
    public static void main(String[] args) throws Exception {
        String file = TestFile.class.getClassLoader().getResource("resoure.txt").getFile();
        Reader reader = new FileReader(new File(file));
        BufferedReader bufferedReader = new BufferedReader(reader);
        while (bufferedReader.ready()){
            String trim = bufferedReader.readLine().trim();
            System.out.println(trim);
             outStre( getInputStream("F:\\学习资料\\知识点\\个人笔记-write\\Netty.assets\\"+trim),trim);
            System.out.println("x");
        }
    }


    public static InputStream getInputStream(String path) throws Exception {
        System.out.println(path);
        File file = new File(path);
        InputStream fileInputStream = new FileInputStream(file);
        return fileInputStream;
    }


    public static void  outStre(InputStream in,String path) throws Exception {
        FileOutputStream out = new FileOutputStream("E:\\note\\javase\\images\\"+path);
        int len = -1;
        byte[] b = new byte[1024];
        while ((len = in.read(b)) != -1) {
            out.write(b, 0, len);
        }
        out.close();
        in.close();
    }
}
