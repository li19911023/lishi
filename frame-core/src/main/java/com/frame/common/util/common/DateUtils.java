
package com.frame.common.util.common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * [日期工具类] <br> 
 *  
 * @author [li.qiong]<br>
 * @version 1.0.0<br>
 * @CreateDate 2019年6月26日 <br>
 * @since v1.0.0<br>
 * @see com.runka.common.util.common <br>
 */
public class DateUtils {

	/**
	 * 
	 * [获取当前的时间的字符串类型] <br> 
	 *  
	 * @author [li.qiong]<br>
	 * @return <br>
	 */
	public static String getCurrentStringDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String createTime = sdf.format(new Date());
		return createTime;
	}
	
	/**
	 * 
	 * [获取当前的时间的字符串类型] <br> 
	 *  
	 * @author [li.qiong]<br>
	 * @return <br>
	 */
	public static String getCurrentStringYear() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String year = sdf.format(new Date());
		return year;
	}
	
	/**
	 * 
	 * [获取当前的时间的int类型] <br> 
	 *  
	 * @author [li.qiong]<br>
	 * @return <br>
	 */
	public static Integer getCurrentIntegerYear() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String year = sdf.format(new Date());
		if(StringUtils.isNoneEmpty(year)) {
			return Integer.parseInt(year);
		}
		return null;
	}
	
	/**
	 * 
	 * [获取当前时间日期类型] <br> 
	 *  
	 * @author [li.qiong]<br>
	 * @return <br>
	 */
    public static Date getCurrentDate() {
        return new Date();
    }
    
   /**
    * 
    * [获取当前时间节点] <br> 
    *  
    * @author [li.qiong]<br>
    * @return <br>
    */
    public static long getCurrentTime() {
        return System.currentTimeMillis();
    }
    
    /**
     * 
     * [这是获取当前时间的前一天] <br> 
     *  
     * @author [li.qiong]<br>
     * @return <br>
     * @throws ParseException 
     */
    public static Date getDate(Date beginDate) throws ParseException {
    	SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		Calendar date = Calendar.getInstance();
		date.setTime(beginDate);
		date.set(Calendar.DATE, date.get(Calendar.DATE)-1);
		Date endDate = dft.parse(dft.format(date.getTime()));
		return endDate;
    }
    
    /**
     * 
     * [今天开始的时间] <br> 
     *  
     * @author [li.qiong]<br>
     * @param beginDate
     * @return
     * @throws ParseException <br>
     */
    public static Date getStartDate()  {
    	Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return cal.getTime();
    }
    
    /**
     * 
     * [今天结束的时间] <br> 
     *  
     * @author [li.qiong]<br>
     */
    public static Date getEndDay() {
    	Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();
    }
    
    /**
     * 
     * [把String类型的时间转换成Date,时间年月日] <br> 
     *  
     * @author [li.qiong]<br>
     * @param date
     * @return <br>
     * @throws ParseException 
     */
    public static Date getDateByString(String date) throws ParseException {
    	if(StringUtils.isNotEmpty(date)) {
    		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    		Date date0 = df.parse(date); 
    		return date0;
    	} else {
    		//获取当前的时间戳
    		return new Date();
    	}
    }
    
    /**
     * 
     * [把String类型的时间转换成Date,时间年月日时分秒] <br> 
     *  
     * @author [li.qiong]<br>
     * @param date
     * @return <br>
     * @throws ParseException 
     */
    public static Date getDateByStringSe(String date) throws ParseException {
    	if(StringUtils.isNotEmpty(date)) {
    		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    		Date date0 = df.parse(date); 
    		return date0;
    	} else {
    		//获取当前的时间戳
    		return new Date();
    	}
    }
}

