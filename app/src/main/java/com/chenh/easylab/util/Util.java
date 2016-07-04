package com.chenh.easylab.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by chenh on 2016/6/5.
 */
public class Util {
    /**
     * 将Date对象转化为字符串
     * @param date 日期对象
     * @return 将时间对象的date转化为对应的年月日格式的字符串
     */
    public static String getViewDate(Date date){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = dateFormat.format(date);
        return dateStr;
    }

    public static String getViewTime(Date date){
        DateFormat dateFormat = new SimpleDateFormat("hh:mm");
        String dateStr = dateFormat.format(date);
        return dateStr;
    }

    public static boolean aBeforeB(String a,String b){
        String[] A=a.split(":");
        int numA=Integer.parseInt(A[0])*60+Integer.parseInt(A[1]);

        String[] B=b.split(":");
        int numB=Integer.parseInt(B[0])*60+Integer.parseInt(B[1]);

        if (numA<numB)
            return true;
        return false;
    }
}
