package cn.moexc.vcs.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class Utils {
    private static final SimpleDateFormat SDF_YMDHMS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String genUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static Date parseDate(String sdate) throws ParseException {
        return SDF_YMDHMS.parse(sdate);
    }

    public static String formatDate(Date date){
        return SDF_YMDHMS.format(date);
    }

}
