package com.it;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import javax.management.Query;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

public class FileTestaa {
    public static void main(String[] args) {
        String[] nameArr = {"rowGuid","projectNo","applyerName","address","managers","legal","zipCode","contactMobile","email","pesticidesName"
                ,"fax","managerTel","pesticidesCategory","pesticidesModel"
                ,"totalActiveIngredient","hzmc","hxOp","dlOp","yxOp","clOp","hjOp","bqOp","csOp","zhOp"
                ,"hxDate","dlDate","yxDate","clDate","hjDate","bqDate","csDate","zhDate"
                ,"hxUsername","dlUsername","yxUsername","clUsername","hjUsername","bqUsername","csUsername","zhUsername"
        };
        //18 26 34
        System.out.println(nameArr[22]);
        System.out.println(nameArr[30]);
        System.out.println(nameArr[38]);
    }
/**
 StringBuilder dataSql = new StringBuilder("SELECT A.ROW_GUID,A.PROJECT_NO,B.APPLYER_NAME,B.ADDRESS,B.MANAGERS,B.LEGAL,B.ZIP_CODE" +
 ",B.CONTACT_MOBILE,A.EMAIL,B.PESTICIDES_NAME,B.FAX,B.MANAGER_TEL,B.PESTICIDES_CATEGORY" +
 ",B.PESTICIDES_MODEL,B.TOTAL_ACTIVE_INGREDIENT,DICT.HZMC" +
 ",HX.OPINION AS HX_OP,DL.OPINION AS DL_OP,YX.OPINION AS YX_OP" +
 ",CL.OPINION AS CL_OP,HJ.OPINION AS HJ_OP,BQ.OPINION AS BQ_OP" +
 ",CS.CONTENT AS CS_OP,ZH.CONTENT AS ZH_OP" +
 ",TO_CHAR(HX.CREATE_TIME,'YYYY-MM-DD') AS HX_DATE,TO_CHAR(DL.CREATE_TIME,'YYYY-MM-DD') AS DL_DATE" +
 ",TO_CHAR(YX.CREATE_TIME,'YYYY-MM-DD') AS YX_DATE,TO_CHAR(CL.CREATE_TIME,'YYYY-MM-DD') AS CL_DATE" +
 ",TO_CHAR(HJ.CREATE_TIME,'YYYY-MM-DD') AS HJ_DATE,TO_CHAR(BQ.CREATE_TIME,'YYYY-MM-DD') AS BQ_DATE,CS_DATE,ZH_DATE" +
 ",HX.USERNAME AS HX_USERNAME,DL.USERNAME AS DL_USERNAME,YX.USERNAME AS YX_USERNAME,CL.USERNAME AS CL_USERNAME" +
 ",HJ.USERNAME AS HJ_USERNAME,BQ.USERNAME AS BQ_USERNAME,CS.CREATORNAME AS CS_CREATOR,ZH.CREATORNAME AS ZH_CREATOR " +
 " FROM TB_AEA_ITEM_SUPER_BASE A " +
 " LEFT JOIN TB_AEA_ITEM_0178001 B ON A.ROW_GUID=B.ROW_GUID " +
 " LEFT JOIN TB_AEA_SM_ICAMA_DICT DICT ON B.TOTAL_ACTIVE_INGREDIENT_UNIT=DICT.BM AND DICT.BMLB='NBDWBM' " +
 " LEFT JOIN TB_AEA_ITEM_OPINION HX ON B.ROW_GUID=HX.P_GUID AND HX.CODE='质量' " +
 " LEFT JOIN TB_AEA_ITEM_OPINION DL ON B.ROW_GUID=DL.P_GUID AND DL.CODE='毒理' " +
 " LEFT JOIN TB_AEA_ITEM_OPINION YX ON B.ROW_GUID=YX.P_GUID AND YX.CODE='药效' " +
 " LEFT JOIN TB_AEA_ITEM_OPINION CL ON B.ROW_GUID=CL.P_GUID AND CL.CODE='残留' " +
 " LEFT JOIN TB_AEA_ITEM_OPINION HJ ON B.ROW_GUID=HJ.P_GUID AND HJ.CODE='环境' " +
 " LEFT JOIN TB_AEA_ITEM_OPINION BQ ON B.ROW_GUID=BQ.P_GUID AND BQ.CODE='标签' " +
 " LEFT JOIN ( " +
 " SELECT SP.INSTID,OP.CONTENT AS CONTENT,SP.CREATORNAME,SP.CS_DATE " +
 " FROM ( SELECT INSTID,MAX(STEPSN) AS STEPSN,CREATORNAME,TO_CHAR(MAX(FINISHDATE) +INTERVAL '8' HOUR,'YYYY-MM-DD') AS CS_DATE " +
 " FROM HD_WF_HIS_STEP WHERE STEPNAME='省所初审' GROUP BY INSTID,CREATORNAME ) SP, HD_FORM_OPINION OP " +
 " WHERE SP.INSTID=OP.INSTID AND SP.STEPSN=OP.STEPSN " +
 ") CS ON CS.INSTID=A.WORK_ID " +
 " LEFT JOIN ( " +
 " SELECT SP.INSTID,OP.CONTENT AS CONTENT,SP.CREATORNAME,SP.ZH_DATE " +
 " FROM ( SELECT INSTID,MAX(STEPSN) AS STEPSN,CREATORNAME,TO_CHAR(MAX(FINISHDATE) +INTERVAL '8' HOUR,'YYYY-MM-DD') AS ZH_DATE " +
 " FROM HD_WF_HIS_STEP WHERE STEPNAME='省所综合' GROUP BY INSTID,CREATORNAME ) SP, HD_FORM_OPINION OP " +
 " WHERE SP.INSTID=OP.INSTID AND SP.STEPSN=OP.STEPSN " +
 ") ZH ON ZH.INSTID=A.WORK_ID ");
 StringBuilder whereSql = new StringBuilder(" WHERE 1=1 ");
 dataSql.append(whereSql);

 System.out.println(dataSql.toString());
 */


}
