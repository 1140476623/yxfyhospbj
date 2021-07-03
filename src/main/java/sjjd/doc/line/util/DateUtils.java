package sjjd.doc.line.util;

import org.apache.http.ParseException;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class DateUtils {

    public static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    //判断日期是否为当天
    public static boolean isToday(String str, String formatStr) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            System.out.println("解析日期错误");
        }

        Calendar c1 = Calendar.getInstance();

        c1.setTime(date);

        int year1 = c1.get(Calendar.YEAR);

        int month1 = c1.get(Calendar.MONTH) + 1;

        int day1 = c1.get(Calendar.DAY_OF_MONTH);

        Calendar c2 = Calendar.getInstance();

        c2.setTime(new Date());

        int year2 = c2.get(Calendar.YEAR);

        int month2 = c2.get(Calendar.MONTH) + 1;

        int day2 = c2.get(Calendar.DAY_OF_MONTH);

        if (year1 == year2 && month1 == month2 && day1 == day2) {
            return true;
        }
        return false;

    }


    public static String getNowDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        return simpleDateFormat.format(new Date());
    }

    public static String getNowTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        return simpleDateFormat.format(new Date());
    }

    public static String getDateTime() {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date();
        date.setTime(date.getTime() + 3 * 60 * 1000);
        return df.format(date);
    }


    /**
     * 获取当月的 天数
     */
    public static int getCurrentMonthDay() {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }

    public static List<String> getDayByMonth(int yearParam, int monthParam) {
        List list = new ArrayList();
        Calendar aCalendar = Calendar.getInstance(Locale.CHINA);
        aCalendar.set(yearParam, monthParam, 1);
        int year = aCalendar.get(Calendar.YEAR);//年份
        int month = aCalendar.get(Calendar.MONTH);//月份
        int day = aCalendar.getActualMaximum(Calendar.DATE);
        for (int i = 1; i <= day; i++) {
            String aDate = null;
            if (month < 10 && i < 10) {
                aDate = String.valueOf(year) + "-0" + month + "-0" + i;
            }
            if (month < 10 && i >= 10) {
                aDate = String.valueOf(year) + "-0" + month + "-" + i;
            }
            if (month >= 10 && i < 10) {
                aDate = String.valueOf(year) + "-" + month + "-0" + i;
            }
            if (month >= 10 && i >= 10) {
                aDate = String.valueOf(year) + "-" + month + "-" + i;
            }

            list.add(aDate);
        }
        return list;
    }


    /**
     * 根据 年、月 获取对应的月份 的 天数
     */
    public static int getDaysByYearMonth(int year, int month) {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }

    /**
     * 根据日期 找到对应日期的 星期几
     *
     * @param date 比如传参：2018-07-13 将返回“周五”
     */
    public static String getDayOfWeekByDate(String date) {
        String dayOfweek = "-1";
        try {
            SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
            Date myDate = myFormatter.parse(date);
            SimpleDateFormat formatter = new SimpleDateFormat("E");
            String str = formatter.format(myDate);
            getDamaiDay(myDate);
            dayOfweek = getDamaiDay(myDate);
        } catch (Exception e) {
            System.out.println("错误!");
        }
        return dayOfweek;
    }


    private static String getDamaiDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        switch (calendar.get(Calendar.DAY_OF_WEEK)) {
            case Calendar.MONDAY:
                return "周一";
            case Calendar.TUESDAY:
                return "周二";
            case Calendar.WEDNESDAY:
                return "周三";
            case Calendar.THURSDAY:
                return "周四";
            case Calendar.FRIDAY:
                return "周五";
            case Calendar.SATURDAY:
                return "周六";
            case Calendar.SUNDAY:
                return "周日";
            default:
                return "";
        }
    }

    public static String getsimDate(String dateType) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateType);//设置日期格式
        Date now = new Date();
        String time = sdf.format(now);
        return time;
    }

    public static String getsimNow(String dateType, Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateType);//设置日期格式
        String time = sdf.format(date);
        return time;
    }

    public static long fromDateStringToLong(String inVal) {
        Date date = null;
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:SSS");
        try {
            date = inputFormat.parse(inVal);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date.getTime();
    }

    public static LocalDateTime getStrLocalDate(String type, String str) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(type);
        LocalDateTime ldt = LocalDateTime.parse(str, df);
        return ldt;
    }


    public static String getDatePoor(Date endDate, Date nowDate) {

        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        return hour + "时" + min + "分";
    }


    public static String getDatePoorSim(Long date) {

        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;

        // 计算差多少天
        long day = date / nd;
        // 计算差多少小时
        long hour = date % nd / nh;
        // 计算差多少分钟
        long min = date % nd % nh / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        return hour + "时" + min + "分";
    }

    private static final int FIRST_DAY = Calendar.MONDAY;

    public static List<String> printWeekdays() {
        List<String> stringList = new ArrayList<String>();
        Calendar calendar = Calendar.getInstance();
        setToFirstDay(calendar);
        for (int i = 0; i < 7; i++) {
            stringList.add(printDay(calendar));
            calendar.add(Calendar.DATE, 1);
        }
        return stringList;
    }

    private static void setToFirstDay(Calendar calendar) {
        while (calendar.get(Calendar.DAY_OF_WEEK) != FIRST_DAY) {
            calendar.add(Calendar.DATE, -1);
        }
    }

    private static String printDay(Calendar calendar) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(calendar.getTime());
    }

    //实现日期加几天的方法
    public static String addDay(String date, int num) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar cd = Calendar.getInstance();
            cd.setTime(sdf.parse(date));
            cd.add(Calendar.DATE, num);//增加一天
            return sdf.format(cd.getTime());

        } catch (Exception e) {
            return null;
        }
    }
}
