package com.dachen;

import org.apache.hadoop.hive.ql.exec.UDF;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class timestamp_getmonth extends UDF {

    public String evaluate(String time) throws ParseException {
        String output = "";
        SimpleDateFormat month = new SimpleDateFormat("yyyy-MM");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        if (time == null || time.length()<10) return output;
        if(time.contains("-")) time = String.valueOf(df.parse(time).getTime());
        Timestamp date = new Timestamp(Long.parseLong(time.substring(0, 10)) * 1000);
        output = month.format(date);
        return output;
    }

    public String evaluate(Long time)  {
        String output = "";
        if (time == null || time.toString().length()<10) return output;
        SimpleDateFormat month = new SimpleDateFormat("yyyy-MM");
        String timestamp = time.toString();
        Timestamp date = new Timestamp(Long.parseLong(timestamp.substring(0, 10)) * 1000);
        output = month.format(date);
        return output;
    }

    public static void main(String[] args) throws ParseException {
        String time = "2019-05-01";
        timestamp_getmonth pt = new timestamp_getmonth();
        System.out.println(pt.evaluate(time));
    }
}
