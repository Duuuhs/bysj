package com.sise.common;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DateUtils
{
	
	/**
	 * 返回星期几
	 * @param object Date对象或者字符串,yyyy-MM-dd
	 * @return 星期五
	 */
	public static String getWeek(){
		String[] cnWeek={"日","一","二","三","四","五","六"};
		return "星期"+cnWeek[new Date().getDay()];
	}
	
	/**
	 * 根据时间字符串返回Date对象
	 * @param dateStr,可以接受3种格式分别是:yyyy-MM-dd,yyyy-MM-dd HH:mm,yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static Date getDateByStr(String dateStr){
		SimpleDateFormat formatter = null;
		if(dateStr.length()==10) formatter=new SimpleDateFormat("yyyy-MM-dd");
		else if(dateStr.length()==16) formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		else if(dateStr.length()==19) formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		else
		{
			System.out.println("日期字符串格式错误!");
			return null;
		}
		try
		{
			return formatter.parse(dateStr);
		}
		catch (ParseException e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * @description:把日期如:2008-9-12 2008-8-1 转来2008-09-12  2008-08-01;
	 * @updateDate:Sep 12, 2008 7:51:22 PM
	 * @author:黄春生
	 * @param strDate
	 * @return
	 */
	public static String myFormatDate(String strDate){
			String[] dateArr = strDate.split("-");
			StringBuffer sb1 = new StringBuffer("0");
			StringBuffer sb2 = new StringBuffer("0");
			StringBuffer sb3 = new StringBuffer(dateArr[0]);
			if(dateArr[1].length()==1)
				dateArr[1] = sb1.append(dateArr[1]).toString();
			if(dateArr[2].length()==1)
				dateArr[2] = sb2.append(dateArr[2]).toString();
			strDate = sb3.append("-").append(dateArr[1]).append("-").append(dateArr[2]).toString();
		return strDate;
	}
	/**
	 * 返回日期的字符串
	 * @param date Date对象
	 * @param format 例如:yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String getStrByDate(Date date,String format)
	{
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.format(date);
	}
	/**
	 * 返回日期的字符串,年-月-日
	 * @param date
	 * @return yyyy-MM-dd
	 */
	public static String getStrYMDByDate(Date date)
	{
		return getStrByDate(date,"yyyy-MM-dd");
	}
	/**
	 * 返回日期的字符串,时:分:秒
	 * @param date
	 * @return HH:mm:ss
	 */
	public static String getStrHMSByDate(Date date)
	{
		return getStrByDate(date,"HH:mm:ss");
	}
	/**
	 * 返回日期的字符串,年-月-日 时:分:秒
	 * @param date
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String getStrYMDHMSByDate(Date date)
	{
		return getStrByDate(date,"yyyy-MM-dd HH:mm:ss");
	}
	/**
	 * 返回日期的字符串,年月日 时:分
	 * @param date
	 * @return yyyy-MM-dd HH:mm
	 */
	public static String getStrYMDHMByDate(Date date)
	{
		return getStrByDate(date,"yyyy-MM-dd HH:mm");
	}
	/**
	 * 对天数进行加减运算
	 * @param date 原来的时间
	 * @param days 正数为加,负数为减
	 * @return 返回运算后的时间
	 */
	public static Date addDay(Date date,Integer days)
	{
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, days);
		
		return calendar.getTime();
	}
	/**
	 * 对月数进行加减运算
	 * @param date 原来的时间
	 * @param days 正数为加,负数为减
	 * @return 返回运算后的时间
	 */
	public static Date addMonth(Date date,Integer months)
	{
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, months);
		return calendar.getTime();
	}
	
	
	/**返回当前时间
	 * @return
	 */
	public static String getTime(){
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
	} 

	
	/** 
	   * 获得当前时间 
	   * @param parrten 输出的时间格式 如:想返回月:parrten=MM,年就parrten=yyyy
	   * @return 返回时间 
	   */ 
	  public static String getTime(String parrten) 
	  { 
	    String timestr; 
	    if(parrten==null||parrten.equals("")) 
	    { 
	      parrten="yyyyMMddHHmmss"; 
	    } 
	    SimpleDateFormat sdf=new SimpleDateFormat(parrten);
	    Date cday=new Date();
	    timestr=sdf.format(cday); 
	    return timestr; 
	  }
	  
	  /**
	   * @param strDate 如：3/25/2008 12:24:38
	   * @return 2008-03-25
	   */
	  public static String formatDate(String strDate){
	  	String arrays[]=strDate.split("/");
	  	String date=arrays[2].substring(0,4)+"-"+arrays[0]+"-"+arrays[1];
	  	return date;
	  }
	  /**
	   * @param strDate 如：3/25/2008 12:24:38
	   * @return 2008-03-25 12:24:38
	   */
	  public static String getStrDate(String strDate){
	  	String arrays[]=strDate.split("/");
	  	String date=arrays[2].substring(0,4)+"-"+arrays[0]+"-"+arrays[1]+arrays[2].substring(4,arrays[2].length());
	  	return date;
	  }
	  
	  /**
	 * @description:取得日期,格式:年月日.
	 * @updateDate:Aug 28, 2008 2:27:55 PM
	 * @author:黄春生
	 * @return
	 */
	public static Date getDate(){
		  return getDateByStr(getTime("yyyy-MM-dd"));
	  }
	
	/**
	 * @description:取得日期,格式:年月日时分秒.
	 * @updateDate:Sep 3, 2008 3:05:40 PM
	 * @author:黄春生
	 * @return
	 */
	public static Date getDateTime(){
		  return getDateByStr(getTime("yyyy-MM-dd HH:mm:ss"));
	  }
	
	
	/**
	 * @description:计算两个日期间的天数 
	 * @updateDate:Sep 12, 2008 6:27:49 PM
	 * @author:黄春生
	 * @param fromDate 起始日期
	 * @param toDate 结束日期
	 * @return
	 * @throws ParseException
	 */
	public static int daysBetween(String fromDate, String toDate)throws ParseException { 
		int days = 0; 
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
		Date from = df.parse(fromDate); 
		Date to = df.parse(toDate); 
		days = (int) Math.abs((to.getTime() - from.getTime())/(24*60*60*1000)) + 1; 
		return days; 
	}
	
	/**
	 * 获取中文年月日
	 */
	public static String getChineseDate(Date d){
	   String strDate =getStrYMDByDate(d);
	   StringBuffer sb = new StringBuffer();
	   try{
		   String strDates[] = strDate.split("-");
		   sb.append(strDates[0]).append("年").append(strDates[1]).append("月").append(strDates[2]).append("日");
	   }catch(Exception e){
		   e.printStackTrace();
	   }
	   return sb.toString();
	}

	/**
	 * 通过2018-07-12获取20180712
	 * @author : 刘少华
	 * 2018-7-12 下午4:56:10
	 */
	public static String getNumberDateByStr(String date) {
		if (date == null) {
			return null;
		}
		StringBuilder dateSb = new StringBuilder();
		String[] split = date.split("-");
		for (String str : split) {
			dateSb.append(str);
		}
		
		return dateSb.toString();
	}
	
	/**检测日期是否合法.
	 * @param date
	 * @return
	 * @date:Jul 7, 2009
	 * @author:黄春生
	 * @version:1.0
	 */
	public static boolean chkDateFormat(String date) {
		boolean flag = false;
        try {
        	logger.info("------------DateUtil------------");
            if(date!=null&&!"".equals(date)&&date.indexOf("-")>0){
            	logger.info("------------DateUtil------------");
                String[] dates = date.split("-");
                int year = Integer.parseInt(dates[0]);
                int month = Integer.parseInt(dates[1]);
                int day = Integer.parseInt(dates[2]);
                logger.info("------------DateUtil------"+dates[0]+"--"+dates[1]+"----"+dates[2]);
                Calendar calendar = GregorianCalendar.getInstance();
                // 当 Calendar 处于 non-lenient 模式时，如果其日历字段中存在任何不一致性，它都会抛出一个异常。
                calendar.setLenient(false);
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DATE, day);
                // 如果日期错误,执行该语句,必定抛出异常.
                calendar.get(Calendar.YEAR);
                flag = true;
            }
        } catch (IllegalArgumentException e) {
        	flag=false;
        }
        return flag;
    }
	
	
	public static String addDateMinut(String day, int x)//返回的是字符串型的时间，输入的  //是String day, int x
	{   
	        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 24小时制  
	        //引号里面个格式也可以是 HH:mm:ss或者HH:mm等等，很随意的，不过在主函数调用时，要和输入的变
	        //量day格式一致
	        Date date = null;   
	        try {   
	            date = format.parse(day);   
	        } catch (Exception ex) {   
	            ex.printStackTrace();   
	        }   
	        if (date == null)   
	            return "";   
	        System.out.println("front:" + format.format(date)); //显示输入的日期  
	        Calendar cal = Calendar.getInstance();   
	        cal.setTime(date);   
	        cal.add(Calendar.MINUTE, x);// 24小时制   
	        date = cal.getTime();   
	        System.out.println("after:" + format.format(date));  //显示更新后的日期 
	        cal = null;   
	        return format.format(date);   
	  
	 } 
	
	public static Long addCurrentDateMinut(int x){ 
			Long l=0L;
			try{
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 24小时制  
		        Calendar cal = Calendar.getInstance();  
		        Date date = new Date();
		        cal.setTime(date);   
		        cal.add(Calendar.MINUTE, x);// 24小时制   
		        date = cal.getTime();   
		        System.out.println("after:" + format.format(date));  //显示更新后的日期 
		        l=date.getTime();
			}catch(Exception e){
				e.printStackTrace();
			}
	        return l;   
	  
	 } 
	
	/**
	 * 获取年份
	 * @author : 刘少华
	 * 2018-9-11 上午10:07:27
	 */
	public static String getYear() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy");
		String year = format.format(new Date());
		return year;
	}
	
	/**
	 * 获取天
	 * @author : 刘少华
	 * 2018-9-26 上午11:07:27
	 */
	public static String getDay() {
		SimpleDateFormat format = new SimpleDateFormat("dd");
		String day = format.format(new Date());
		return day;
	}

	/**
	 * 获得当前时间周的周一时间
	 * @return
	 */
	public static String getMonday(){
		Calendar c = Calendar.getInstance();
		// 默认时，每周第一天为星期日，需要更改一下
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		String monday = Converts.dateFormat(c.getTime());
		return monday;
	}


	
	/**
	 * 获取当前时间的日期
	 * @return eg:2018-03-09
	 */
	public static String getCurrentDay(){
		SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String currentDay = dateFormater.format(date);
		
		return currentDay;
	}
	
	/**
	 * 获取月份
	 * @author : 刘少华
	 * 2018-9-11 上午10:07:27
	 */
	public static String getMonth() {
		SimpleDateFormat format = new SimpleDateFormat("MM");
		String month = format.format(new Date());
		return month;
	}

	/**
	 * 获取某个日期前后多少天时间（前几天为负数，后几天为正数）
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public static String getOneDay(String str,int day) throws Exception{
		
		 Calendar c = Calendar.getInstance();
		 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		 Date date = df.parse(str);
		 c.setTime(date);
         c.add(Calendar.DATE, day);
         Date d = c.getTime();
         
		 return Converts.dateFormat(d);
	}

	/**
	 * 获取上一天的今天日期
	 * @author : 刘少华
	 * 2018-9-11 上午10:17:01
	 */
	public static String getLastYearToday() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.YEAR, -1);
		Date y = c.getTime();
		String year = format.format(y);
		return year;
	}


	/**
	 * 当月第一天
	 * 
	 * @return
	 */
	public static String getFirstDay(String dateStr) {
		StringBuffer str = new StringBuffer();
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date theDate = df.parse(dateStr);

			GregorianCalendar gcLast = (GregorianCalendar) Calendar
					.getInstance();
			gcLast.setTime(theDate);
			gcLast.set(Calendar.DAY_OF_MONTH, 1);
			String day_first = df.format(gcLast.getTime());
			str = new StringBuffer().append(day_first);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str.toString();

	}

	/**
	 * 当月最后一天
	 * 
	 * @return
	 */
	public static String getLastDay(String dateStr) {
		StringBuffer str = null;
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date date = df.parse(dateStr);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.MONTH, 1); // 加一个月
			calendar.set(Calendar.DATE, 1); // 设置为该月第一天
			calendar.add(Calendar.DATE, -1); // 再减一天即为上个月最后一天
			String day_last = df.format(calendar.getTime());
			str = new StringBuffer().append(day_last);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return str.toString();

	}

	/**
	 * @Title: isInDate
	 * @Description: 判断一个时间段（YYYY-MM-DD）是否在一个区间
	 * @param @param date
	 * @param @param strDateBegin
	 * @param @param strDateEnd
	 * @param @return 设定文件
	 * @return boolean 返回类型
	 * @throws
	 */
	/*public static boolean isInDate(Date date, String strDateBegin,String strDateEnd) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",Locale.ENGLISH);
		String strDate = sdf.format(date); // 2017-04-11
		// 截取当前时间年月日 转成整型
		int tempDate = Integer.parseInt(strDate.split("-")[0]+ strDate.split("-")[1] + strDate.split("-")[2]);
		// 截取开始时间年月日 转成整型
		int tempDateBegin = Integer.parseInt(strDateBegin.split("-")[0]+ strDateBegin.split("-")[1] + strDateBegin.split("-")[2]);
		// 截取结束时间年月日 转成整型
		int tempDateEnd = Integer.parseInt(strDateEnd.split("-")[0]+ strDateEnd.split("-")[1] + strDateEnd.split("-")[2]);

		if ((tempDate >= tempDateBegin && tempDate <= tempDateEnd)) {
			return true;
		} else {
			return false;
		}
	}*/

	/**
	 * 获取前一个月或前一周的时间
	 * @param str
	 * @return
	 */
	public static String getDay(String str) {
		String day = "";
		 Calendar c = Calendar.getInstance();
		 if("lately_month".equals(str)){//获前取一个月时间
			 c.setTime(new Date());
			 c.add(Calendar.MONTH, -1);
			 Date m = c.getTime();
			 day = Converts.dateFormat(m);
		 }
		if ("lately_week".equals(str)) {//获取前一周时间
			c.setTime(new Date());
	        c.add(Calendar.DATE, - 7);
	        Date d = c.getTime();
	        day = Converts.dateFormat(d);
	        
		}
		 return day;
	}



	/**
	 * 格式化路径
	 * @return
	 */
	/*public static String formatePath(String domain,String path){
		if(path == null || path.isEmpty()){
			return null;
		}
		if(isGfsPic(path)){	 //图片未上阿里云-兼容旧文件系统
			domain = Config.get("fs.domain");
		}
		if(path.charAt(0) == '/'){
			return domain + path;
		}
		return domain + '/' + path;
	}*/

	/**
	 * 判断图片是否是文件系统存储图片的地址
	 * @param path
	 * @return
	 */
	private static  boolean isGfsPic(String path){
		if(path.startsWith("/tea") || path.startsWith("tea") || path.startsWith("/stu") || path.startsWith("stu")){
			return true;
		}
		return false;
	}

	/**
	 * 发起URL请求
	 * @author : 刘少华
	 * 2018-3-14 上午10:52:44
	 */
	/*public static String httpRequest(String requestUrl, String requestMethod, String outputStr) {
		StringBuffer buffer = null;
		try {
			URL url = new URL(requestUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestMethod(requestMethod);
			conn.connect();
			// 往服务器端写内容 也就是发起http请求需要带的参数
			if (null != outputStr) {
				OutputStream os = conn.getOutputStream();
				os.write(outputStr.getBytes("utf-8"));
				os.close();
			}

			// 读取服务器端返回的内容
			InputStream is = conn.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "utf-8");
			BufferedReader br = new BufferedReader(isr);
			buffer = new StringBuffer();
			String line = null;
			while ((line = br.readLine()) != null) {
				buffer.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buffer.toString();
	}*/
	
	public static Log logger=(Log)LogFactory.getLog("FILE");
	public static void main(String args[]){
//		 	logger.info("------------DateUtil------------");
//		 	
//		 	logger.info("uuuuuu"+addCurrentDateMinut(5));
//		 	
//		 	logger.info(getChineseDate(new Date()));
//		 	logger.info("uuuuuu"+myFormatDate("2008-9-5"));
//		 	
//		 	try {
//				int i=dateDiff("2008-09-10","2008-12-15");
//				System.out.println("I:"+i);
//			} catch (ParseException e1) {
//				
//				e1.printStackTrace();
//			}
//		 	
//		 	logger.info("------------"+getTime("yyyy-MM-dd")+"------------");
//		 	
//		    logger.info("2今天是:"+DateUtils.getTime());
//		    logger.info("3今天是:"+DateUtils.getStrByDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
//		    logger.info("4今天是:"+DateUtils.getStrByDate(new Date(), "yyyy-MM-dd"));
//		    logger.info("5今天是:"+DateUtils.getStrByDate(new Date(), "HH:mm:ss"));
//		    logger.info("6今天是:"+DateUtils.getStrByDate(new Date(), "yyyy-MM-dd HH:mm"));
//		    logger.info("7今天是:"+DateUtils.getStrHMSByDate(new Date()));
//		    logger.info("8今天是:"+DateUtils.getStrYMDHMSByDate(new Date()));
//		    logger.info("9今天是:"+DateUtils.getStrByDate(DateUtils.addDay(new Date(),2), "yyyy-MM-dd HH:mm"));
//		    logger.info("10今天是:"+DateUtils.addMonth(new Date(),17));
//		    
//		    logger.info("10今天是:"+chkDateFormat("1-02-21"));
		   // logger.info("10今天是:"+DateUtil.getStrDate(strDate));
//		logger.info(getYear());
//		logger.info(getMonth());
		    logger.info(getDay());
		    
		    
	}


}

