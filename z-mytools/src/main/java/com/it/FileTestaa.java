package com.it;

public class FileTestaa {
    public static void main(String[] args) {
        String dataSql = "SELECT t1.ID, t1.ROW_GUID, t1.PAR_GUID, t1.YXCF_ID, t2.AICNAME, t2.AIENAME, t1.YXCF_CONTENT, " +
                "t1.YXCF_UNIT, t3.HZMC FROM TB_AEA_SM_ICAMA_PRODUCT_YXCF t1 LEFT JOIN TB_AEA_SM_TBL_ACTIVE t2 ON t1.yxcf_id = t2.AIID " +
                "LEFT JOIN TB_AEA_SM_ICAMA_DICT t3 ON t1.yxcf_unit = t3.BM AND t3.BMLB = 'NBDWBM' WHERE t1.PAR_GUID = :parGuid";
        System.out.println(dataSql);
    }


}
