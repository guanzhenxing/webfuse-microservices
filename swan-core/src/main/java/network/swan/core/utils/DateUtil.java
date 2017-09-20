package network.swan.core.utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    /**
     * 获得当天的开始时间
     *
     * @return
     */
    public static Date getBeginTimeOfDay() {
        Calendar currentDate = Calendar.getInstance();
        currentDate.set(Calendar.HOUR_OF_DAY, 0);
        currentDate.set(Calendar.MINUTE, 0);
        currentDate.set(Calendar.SECOND, 0);
        Date time = currentDate.getTime();
        return time;
    }

    /**
     * 获得当天的结束时间
     *
     * @return
     */
    public static Date getEndTimeOfDay() {
        Calendar currentDate = Calendar.getInstance();
        currentDate.set(Calendar.HOUR_OF_DAY, 23);
        currentDate.set(Calendar.MINUTE, 59);
        currentDate.set(Calendar.SECOND, 59);
        Date time = currentDate.getTime();
        return time;
    }

    /**
     * 获得当周的开始时间
     *
     * @return
     */
    public static Date getBeginTimeOfWeek() {
        Calendar currentDate = Calendar.getInstance();
        currentDate.setFirstDayOfWeek(Calendar.MONDAY);
        currentDate.set(Calendar.HOUR_OF_DAY, 0);
        currentDate.set(Calendar.MINUTE, 0);
        currentDate.set(Calendar.SECOND, 0);
        currentDate.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        Date time = currentDate.getTime();
        return time;
    }

    /**
     * 获得当周的结束时间
     * @return
     */
    public static Date getEndTimeOfWeek() {
        Calendar currentDate = Calendar.getInstance();
        currentDate.setFirstDayOfWeek(Calendar.MONDAY);
        currentDate.set(Calendar.HOUR_OF_DAY, 23);
        currentDate.set(Calendar.MINUTE, 59);
        currentDate.set(Calendar.SECOND, 59);
        currentDate.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        Date time = currentDate.getTime();
        return time;
    }

}
