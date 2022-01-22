package com.fg.roundteatable.util;

import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 */

public class DateUtil {

    private final SimpleDateFormat format;

    public DateUtil(SimpleDateFormat format) {
        this.format = format;
    }


    public SimpleDateFormat getFormat() {
        return format;
    }

    //紧凑型日期格式，也就是纯数字类型yyyyMMdd
    public static final DateUtil COMPAT = new DateUtil(new SimpleDateFormat("yyyyMMdd"));

    //常用日期格式，yyyy-MM-dd
    public static final DateUtil COMMON = new DateUtil(new SimpleDateFormat("yyyy-MM-dd"));
    public static final DateUtil COMMON_FULL = new DateUtil(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

    //使用斜线分隔的，西方多采用，yyyy/MM/dd
    public static final DateUtil SLASH = new DateUtil(new SimpleDateFormat("yyyy/MM/dd"));

    //中文日期格式常用，yyyy年MM月dd日
    public static final DateUtil CHINESE = new DateUtil(new SimpleDateFormat("yyyy年MM月dd日"));
    public static final DateUtil CHINESE_FULL = new DateUtil(new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒"));

    private final static SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");
    private final static SimpleDateFormat sdfDays = new SimpleDateFormat("yyyyMMdd");
    private final static SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");
    private final static SimpleDateFormat sdfMonth = new SimpleDateFormat("yyyy-MM");
    private final static SimpleDateFormat sdfTimes = new SimpleDateFormat("yyyyMMddHHmmss");
    private final static SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private final static SimpleDateFormat sdfTimeMillis = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    private final static SimpleDateFormat sdfTimeMilli = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");


    private static int getDateField(Date date, int field) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(field);
    }

    /**
     * 日期获取字符串
     */
    public String getDateText(Date date) {
        return getFormat().format(date);
    }

    /**
     * 字符串获取日期
     *
     * @throws ParseException
     */
    public Date getTextDate(String text) throws ParseException {
        return getFormat().parse(text);
    }

    /**
     * 日期获取字符串
     */
    public static String getDateText(Date date, String format) {
        return new SimpleDateFormat(format).format(date);
    }

    /**
     * 字符串获取日期
     *
     * @throws ParseException
     */
    public static Date getTextDate(String dateText, String format) throws ParseException {
        return new SimpleDateFormat(format).parse(dateText);
    }

    /**
     * 根据日期，返回其星期数，周一为1，周日为7
     *
     * @param date
     * @return
     */
    public static int getWeekDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int w = calendar.get(Calendar.DAY_OF_WEEK);
        int ret;
        if (w == Calendar.SUNDAY)
            ret = 7;
        else
            ret = w - 1;
        return ret;
    }

    /**
     * 获取yyyyMMMddHHmmssSSS格式
     *
     * @return
     */
    public static String getTimeMillis() {
        return sdfTimeMillis.format(new Date());
    }

    /**
     * 获取yyyy-MMM-dd HH:mm:ss:SSS格式
     *
     * @return
     */
    public static String getTimeMilli() {
        return sdfTimeMilli.format(new Date());
    }

    /**
     * 获取YYYY格式
     *
     * @return
     */
    public static String getSdfTimes() {
        return sdfTimes.format(new Date());
    }

    /**
     * 获取YYYY格式
     *
     * @return
     */
    public static String getYear() {
        return sdfYear.format(new Date());
    }

    /**
     * 获取YYYY-MM-DD格式
     *
     * @return
     */
    public static String getDay() {
        return sdfDay.format(new Date());
    }

    /**
     * 获取YYYY-MM格式
     *
     * @return
     */
    public static String getMonth() {
        return sdfMonth.format(new Date());
    }

    /**
     * 获取YYYYMMDD格式
     *
     * @return
     */
    public static String getDays() {
        return sdfDays.format(new Date());
    }

    /**
     * 获取YYYY-MM-DD HH:mm:ss格式
     *
     * @return
     */
    public static String getTime() {
        return sdfTime.format(new Date());
    }

    /**
     * 获取指定格式的当前日期
     *
     * @return
     */
    public static String getFormatTime(String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(new Date());
    }

    /**
     * 将源日期转化为制定格式
     *
     * @param sourceTime   源时间
     * @param sourceFormat 源时间格式
     * @param targetFormat 转化后的时间格式
     * @return
     * @throws ParseException
     */
    public static String getFormatTime(String sourceTime, String sourceFormat,
                                       String targetFormat) throws ParseException {
        SimpleDateFormat sourceDateFormat = new SimpleDateFormat(sourceFormat);
        SimpleDateFormat targetDateFormat = new SimpleDateFormat(targetFormat);
        Date date = sourceDateFormat.parse(sourceTime);
        return targetDateFormat.format(date);
    }

    /**
     * @param s
     * @param e
     * @return boolean
     * @throws
     * @Title: compareDate
     * @Description: TODO(日期比较 ， 如果s > = e 返回true 否则返回false)
     * @author fh
     */
    public static boolean compareDate(String s, String e) {
        if (formatDate(s) == null || formatDate(e) == null) {
            return false;
        }
        return formatDate(s).getTime() >= formatDate(e).getTime();
    }

    /**
     * 格式化日期
     *
     * @return
     */
    public static Date formatDate(String date) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return fmt.parse(date);
        } catch (ParseException e) {
//            e.printStackTrace();
            return null;
        }
    }

    /**
     * 校验日期是否合法
     *
     * @return
     */
    public static boolean isValidDate(String s) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            fmt.parse(s);
            return true;
        } catch (Exception e) {
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            return false;
        }
    }

    /**
     * @param startTime
     * @param endTime
     * @return
     */
    public static int getDiffYear(String startTime, String endTime) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            //long aa=0;
            int years = (int) (((fmt.parse(endTime).getTime() - fmt.parse(startTime).getTime()) / (1000 * 60 * 60 * 24)) / 365);
            return years;
        } catch (Exception e) {
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            return 0;
        }
    }

    /**
     * <li>功能描述：时间相减得到天数
     *
     * @param beginDateStr
     * @param endDateStr
     * @return long
     * @author Administrator
     */
    public static long getDaySub(String beginDateStr, String endDateStr) {
        long day = 0;
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
        java.util.Date beginDate = null;
        java.util.Date endDate = null;

        try {
            beginDate = format.parse(beginDateStr);
            endDate = format.parse(endDateStr);
        } catch (ParseException e) {
//            e.printStackTrace();
        }
        day = (endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000);
        //System.out.println("相隔的天数="+day);

        return day;
    }

    /**
     * 计算2个日期之间相差的  以年、月、日为单位，各自计算结果是多少
     * 比如：2011-02-02 到  2017-03-02
     *
     * @param beginDateStr
     * @param endDateStr
     * @return
     * @throws ParseException
     */
    public static int getMonthDiffer(String beginDateStr, String endDateStr) throws ParseException {
        java.util.Date beginDate = null;
        java.util.Date endDate = null;
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM");
        beginDate = format.parse(beginDateStr);
        endDate = format.parse(endDateStr);

        int from = getDateField(beginDate, Calendar.YEAR) * 12 - getDateField(beginDate, Calendar.MONTH);
        int to = getDateField(endDate, Calendar.YEAR) * 12 - getDateField(endDate, Calendar.MONTH);

        return to - from;
    }

    /**
     * 获取两个时间的 年份 或 月份 或 星期 或 天数 差值
     * @param start 起始日期
     * @param end   结束日期
     * @param type  差值计算类型  Calendar.YEAR：年份 Calendar.MONTH：推算  Calendar.DATE：推算
     * @return
     */
    public static int getDifferBetweenDate(Date start, Date end, Integer type) {
        type = type == null ? Calendar.DATE : type;

        if (start.getTime() > end.getTime()) {
            Date temp = start;
            start = end;
            end = temp;
        }

        if (Calendar.DATE == type)
            return (int) ((end.getTime() - start.getTime()) / (1000 * 60 * 60 * 24));
        else if (Calendar.MONTH == type) {
            int from = getDateField(start, Calendar.YEAR) * 12 - getDateField(start, Calendar.MONTH);
            int to = getDateField(end, Calendar.YEAR) * 12 - getDateField(end, Calendar.MONTH);
            return to - from;
        }

        return getDateField(end, type) - getDateField(start, type);
    }

    /**
     * 获取两个时间的 年份 或 月份 或 星期 或 天数 差值
     * @param startStr 起始日期字符串（yyyy-MM-dd）格式
     * @param endStr   结束日期字符串（yyyy-MM-dd）格式
     * @param type     差值计算类型  Calendar.YEAR：年份 Calendar.MONTH：推算  Calendar.WEEK_OF_YEAR：星期  Calendar.DATE：推算
     * @return
     */
    public static int getDifferBetweenDate(String startStr, String endStr, Integer type) throws Exception {
        java.util.Date end = new Date();
        java.util.Date start = new Date();
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");

        if (StringUtils.isNotBlank(startStr))
            start = format.parse(startStr);

        if (StringUtils.isNotBlank(endStr))
            end = format.parse(endStr);

        return getDifferBetweenDate(start, end, type);
    }

    /**
     * 根据指定开始年月，获取指定日期 formatStr（自定义） 字符串
     *
     * @param startStr  计算开始日期时间字符串（yyyy-MM-dd）格式，该字段为空时则以当前时间开始计算
     * @param type      计算类型 Calendar.YEAR：年份推算  Calendar.MONTH：推算月份  Calendar.DATE：推算日期
     * @param nums      推算长度 大于0：往后推算  小于0：往前推算
     * @param formatStr 需要得到的结果字符串日期时间格式，该字段为空时返回yyyy-MM-dd格式字符串
     * @return 获取startStr开始nums长度后的formatStr格式的日期时间字符串，monthStr、formatStr为空时以当前时间开始返回yyyy-MM-dd格式的年月字符串
     * @throws Exception
     */
    public static String getAssignDate(String startStr, Integer type, Integer nums, String formatStr) throws Exception {
        Calendar rightNow = Calendar.getInstance();

        if (StringUtils.isNotBlank(startStr)) {
            java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
            java.util.Date beginDate = format.parse(startStr);
            rightNow.setTime(beginDate);
        }

        rightNow.add(type == null ? Calendar.MONTH : type, nums == null ? 0 : nums);
        //  rightNow.add(Calendar.YEAR,-1);//日期减1年
        //  rightNow.add(Calendar.MONTH, nums);//日期加3个月
        //  rightNow.add(Calendar.DAY_OF_YEAR,5);//日期加5天

        return getDateFormatStr(StringUtils.isBlank(formatStr) ? "yyyy-MM-dd" : formatStr, rightNow.getTime());
    }

    /**
     * 得到n天之后的日期 formatStr（自定义） 格式，默认（format为空）返回（yyyy-MM-dd）格式
     *
     * @param days 推算天数  大于0：往后推算  小于0：往前推算
     * @return formatStr格式的日期时间字符串，formatStr为空时返回yyyy-MM-dd格式
     */
    public static String getAfterDayDate(String days, String formatStr) {
        int daysInt = Integer.parseInt(days);

        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动

        return getDateFormatStr(StringUtils.isBlank(formatStr) ? "yyyy-MM-dd" : formatStr, canlendar.getTime());
    }

    /**
     * 得到n个月之后的日期 formatStr（自定义） 格式，默认（formatStr为空）返回（yyyy-MM）格式
     *
     * @param months 推算月份  大于0：往后推算  小于0：往前推算
     * @return formatStr格式的日期时间字符串，formatStr为空时返回yyyy-MM格式
     */
    public static String getAfterMonthDate(String months, String formatStr) {
        int monthsInt = Integer.parseInt(months);

        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.MONTH, monthsInt); // 日期减 如果不够减会将月变动

        return getDateFormatStr(StringUtils.isBlank(formatStr) ? "yyyy-MM" : formatStr, canlendar.getTime());
    }

    /**
     * 得到n天之后是周几
     *
     * @param days
     * @return
     */
    public static String getAfterDayWeek(String days) {
        int daysInt = Integer.parseInt(days);
        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动

        return getDateFormatStr("E", canlendar.getTime());
    }

    private static String getDateFormatStr(String formatStr, Date date) {
        SimpleDateFormat sdfd = new SimpleDateFormat(formatStr);
        return sdfd.format(date);
    }

    public static void main(String[] args) throws Exception {
        System.out.println(DateUtil.getDifferBetweenDate("2016-05-01", "2018-05-03", Calendar.YEAR));
        System.out.println(DateUtil.getDifferBetweenDate("2016-05-01", "2012-05-03", Calendar.MONTH));
        System.out.println(DateUtil.getMonthDiffer("2016-05-01", "2012-05-03"));
        System.out.println(DateUtil.getDifferBetweenDate("2016-05-01", "2018-05-03", Calendar.WEEK_OF_YEAR));
        System.out.println(DateUtil.getDifferBetweenDate("2016-05-01", "2018-05-03", Calendar.DATE));
    }

}
