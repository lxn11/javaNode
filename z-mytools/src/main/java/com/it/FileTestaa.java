package com.it;

import java.io.File;

public class FileTestaa {
    public static void main(String[] args) {
        String fileName = "aaaaaaaaaaaa.pdf";
        int i = fileName.lastIndexOf(".");
        if (i==-1){
            System.out.println(fileName+".pdf");
        }else{
            System.out.println( fileName.substring(i,fileName.length()));
        }


    }
}
