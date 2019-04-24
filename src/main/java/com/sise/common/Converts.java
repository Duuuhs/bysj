package com.sise.common;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Converts {

	
	public static final String EMPTY = "";
	public static final  SimpleDateFormat DATE_FORMAT;
	public static final  SimpleDateFormat DATETIME_FORMAT;
	public static final  String DATE_PATTERN = "^\\d{4}-\\d{2}-\\d{2}$";
	public static final  String DATETIME_PATTERN = "^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\{2}$";
	
	static{
		DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
		DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * 除去字符串最后一个 ,
	 */
	public static String sub(String s){
		return s.substring(0,s.length()-1);
	}
	
	public static String sub(String s,int beginIndex){
		return s.substring(beginIndex);
	}
	
	public static String sub(String s,int beginIndex,int endIndex){
		return s.substring(beginIndex, endIndex);
	}

	/**
	 * 集合除首尾 '[' ']' 
	 */
	public static String sub(Collection<?> col){
		return join(col,",");
	}
	
	// Integer 转换
	//------------------------------------------------------
	public static Integer I(Object o){
		return I(o, null);
	}
	
	public static Integer I(Object o,Integer defaultVal){
		if(o == null){
			return defaultVal;
		}
		if(o instanceof Number){
			return ((Number)o).intValue();
		}
		return Integer.valueOf((String)o);
	}

	// Long　转换
	//------------------------------------------------------
	public static Long L(Object o){
		return L(o, null);
	}
	
	public static Long L(Object o,Long defaultVal){
		if(o == null){
			return defaultVal;
		}
		if(o instanceof Number){
			return ((Number)o).longValue();
		}
		return Long.valueOf((String)o);
	}
	
	
	// Float　转换
	//------------------------------------------------------
	public static Float F(Object o){
		return F(o, null);
	}
	
	public static Float F(Object o,Float defaultVal){
		if(o == null){
			return defaultVal;
		}
		if(o instanceof Number){
			return ((Number)o).floatValue();
		}
		return Float.valueOf((String)o);
	}
	
	// Double 转换
	//------------------------------------------------------
	public static Double D(Object o){
		return D(o, null);
	}
	
	public static Double D(Object o,Double defaultVal){
		if(o == null){
			return defaultVal;
		}
		if(o instanceof Number){
			return ((Number)o).doubleValue();
		}
		return Double.valueOf((String)o);
	}

	// Character 转换 String 
	//------------------------------------------------------
	public static String C(Object o){
		return o!=null ? o.toString(): null ;
	}

	public static String C(Object o,String defaultVal){
		return o!=null ? o.toString(): defaultVal ;
	}
	
	public static String S(Object o){
		return o!=null ? o.toString(): null ;
	}

	public static String S(Object o,String defaultVal){
		return o!=null ? o.toString(): defaultVal ;
	}
	
	public static int parseInt(String s){
		return Integer.parseInt(s);
	}
	
	public static long parseLong(String s){
		return Long.parseLong(s);
	}
	
	public static float parseFloat(String s){
		return Float.parseFloat(s);
	}
	
	public static double parseDouble(String s){
		return  Double.parseDouble(s);
	}
	
	// String 转 Date  
	//------------------------------------------------------
	public static synchronized Date parsetDate(String s) throws ParseException{
		try {
			return DATE_FORMAT.parse(s);
		} catch (ParseException e) {
			throw e;
		}
	}
	
	public static synchronized Date parsetDateTime(String s) throws ParseException{
		try {
			return DATETIME_FORMAT.parse(s);
		} catch (ParseException e) {
			throw e;
		}
	}
	
	// Date 转 String
	//------------------------------------------------------
	public static String format(Date date,String pattern){
		return new SimpleDateFormat(pattern).format(date);
	}
	
	public static synchronized String dateFormat(Date date){
		return DATE_FORMAT.format(date);
	}
	
	public static synchronized String dateTimeFormat(Date date){
		return DATETIME_FORMAT.format(date);
	}
	
	
	// 集合,数组转换为字符串,自定设置分隔符
	//------------------------------------------------------
	
    /**
     * <p>Joins the elements of the provided array into a single String
     * containing the provided list of elements.</p>
     *
     * <p>No separator is added to the joined String.
     * Null objects or empty strings within the array are represented by
     * empty strings.</p>
     *
     * <pre>
     * StringUtils.join(null)            = null
     * StringUtils.join([])              = ""
     * StringUtils.join([null])          = ""
     * StringUtils.join(["a", "b", "c"]) = "abc"
     * StringUtils.join([null, "", "a"]) = "a"
     * </pre>
     *
     * @param array  the array of values to join together, may be null
     * @return the joined String, <code>null</code> if null array input
     * @since 2.0
     */
    public static String join(Object[] array) {
        return join(array, null);
    }

    /**
     * <p>Joins the elements of the provided array into a single String
     * containing the provided list of elements.</p>
     *
     * <p>No delimiter is added before or after the list.
     * Null objects or empty strings within the array are represented by
     * empty strings.</p>
     *
     * <pre>
     * StringUtils.join(null, *)               = null
     * StringUtils.join([], *)                 = ""
     * StringUtils.join([null], *)             = ""
     * StringUtils.join(["a", "b", "c"], ';')  = "a;b;c"
     * StringUtils.join(["a", "b", "c"], null) = "abc"
     * StringUtils.join([null, "", "a"], ';')  = ";;a"
     * </pre>
     *
     * @param array  the array of values to join together, may be null
     * @param separator  the separator character to use
     * @return the joined String, <code>null</code> if null array input
     * @since 2.0
     */
    public static String join(Object[] array, char separator) {
        if (array == null) {
            return null;
        }

        return join(array, separator, 0, array.length);
    }

    /**
     * <p>Joins the elements of the provided array into a single String
     * containing the provided list of elements.</p>
     *
     * <p>No delimiter is added before or after the list.
     * Null objects or empty strings within the array are represented by
     * empty strings.</p>
     *
     * <pre>
     * StringUtils.join(null, *)               = null
     * StringUtils.join([], *)                 = ""
     * StringUtils.join([null], *)             = ""
     * StringUtils.join(["a", "b", "c"], ';')  = "a;b;c"
     * StringUtils.join(["a", "b", "c"], null) = "abc"
     * StringUtils.join([null, "", "a"], ';')  = ";;a"
     * </pre>
     *
     * @param array  the array of values to join together, may be null
     * @param separator  the separator character to use
     * @param startIndex the first index to start joining from.  It is
     * an error to pass in an end index past the end of the array
     * @param endIndex the index to stop joining from (exclusive). It is
     * an error to pass in an end index past the end of the array
     * @return the joined String, <code>null</code> if null array input
     * @since 2.0
     */
    public static String join(Object[] array, char separator, int startIndex, int endIndex) {
        if (array == null) {
            return null;
        }
        int bufSize = (endIndex - startIndex);
        if (bufSize <= 0) {
            return EMPTY;
        }

        bufSize *= ((array[startIndex] == null ? 16 : array[startIndex].toString().length()) + 1);
        StringBuffer buf = new StringBuffer(bufSize);

        for (int i = startIndex; i < endIndex; i++) {
            if (i > startIndex) {
                buf.append(separator);
            }
            if (array[i] != null) {
                buf.append(array[i]);
            }
        }
        return buf.toString();
    }


    /**
     * <p>Joins the elements of the provided array into a single String
     * containing the provided list of elements.</p>
     *
     * <p>No delimiter is added before or after the list.
     * A <code>null</code> separator is the same as an empty String ("").
     * Null objects or empty strings within the array are represented by
     * empty strings.</p>
     *
     * <pre>
     * StringUtils.join(null, *)                = null
     * StringUtils.join([], *)                  = ""
     * StringUtils.join([null], *)              = ""
     * StringUtils.join(["a", "b", "c"], "--")  = "a--b--c"
     * StringUtils.join(["a", "b", "c"], null)  = "abc"
     * StringUtils.join(["a", "b", "c"], "")    = "abc"
     * StringUtils.join([null, "", "a"], ',')   = ",,a"
     * </pre>
     *
     * @param array  the array of values to join together, may be null
     * @param separator  the separator character to use, null treated as ""
     * @return the joined String, <code>null</code> if null array input
     */
    public static String join(Object[] array, String separator) {
        if (array == null) {
            return null;
        }
        return join(array, separator, 0, array.length);
    }

    /**
     * <p>Joins the elements of the provided array into a single String
     * containing the provided list of elements.</p>
     *
     * <p>No delimiter is added before or after the list.
     * A <code>null</code> separator is the same as an empty String ("").
     * Null objects or empty strings within the array are represented by
     * empty strings.</p>
     *
     * <pre>
     * StringUtils.join(null, *)                = null
     * StringUtils.join([], *)                  = ""
     * StringUtils.join([null], *)              = ""
     * StringUtils.join(["a", "b", "c"], "--")  = "a--b--c"
     * StringUtils.join(["a", "b", "c"], null)  = "abc"
     * StringUtils.join(["a", "b", "c"], "")    = "abc"
     * StringUtils.join([null, "", "a"], ',')   = ",,a"
     * </pre>
     *
     * @param array  the array of values to join together, may be null
     * @param separator  the separator character to use, null treated as ""
     * @param startIndex the first index to start joining from.  It is
     * an error to pass in an end index past the end of the array
     * @param endIndex the index to stop joining from (exclusive). It is
     * an error to pass in an end index past the end of the array
     * @return the joined String, <code>null</code> if null array input
     */
    public static String join(Object[] array, String separator, int startIndex, int endIndex) {
        if (array == null) {
            return null;
        }
        if (separator == null) {
            separator = EMPTY;
        }

        // endIndex - startIndex > 0:   Len = NofStrings *(len(firstString) + len(separator))
        //           (Assuming that all Strings are roughly equally long)
        int bufSize = (endIndex - startIndex);
        if (bufSize <= 0) {
            return EMPTY;
        }

        bufSize *= ((array[startIndex] == null ? 16 : array[startIndex].toString().length())
                        + separator.length());

        StringBuffer buf = new StringBuffer(bufSize);

        for (int i = startIndex; i < endIndex; i++) {
            if (i > startIndex) {
                buf.append(separator);
            }
            if (array[i] != null) {
                buf.append(array[i]);
            }
        }
        return buf.toString();
    }

    
    /**
     * <p>Joins the elements of the provided <code>Iterator</code> into
     * a single String containing the provided elements.</p>
     *
     * <p>No delimiter is added before or after the list. Null objects or empty
     * strings within the iteration are represented by empty strings.</p>
     *
     * <p>See the examples here: {@link #join(Object[],char)}. </p>
     *
     * @param iterator  the <code>Iterator</code> of values to join together, may be null
     * @param separator  the separator character to use
     * @return the joined String, <code>null</code> if null iterator input
     * @since 2.0
     */
    public static String join(Iterator iterator, char separator) {

        // handle null, zero and one elements before building a buffer
        if (iterator == null) {
            return null;
        }
        if (!iterator.hasNext()) {
            return EMPTY;
        }
        Object first = iterator.next();
        if (!iterator.hasNext()) {
            return first.toString();
        }

        // two or more elements
        StringBuffer buf = new StringBuffer(256); // Java default is 16, probably too small
        if (first != null) {
            buf.append(first);
        }

        while (iterator.hasNext()) {
            buf.append(separator);
            Object obj = iterator.next();
            if (obj != null) {
                buf.append(obj);
            }
        }

        return buf.toString();
    }

    /**
     * <p>Joins the elements of the provided <code>Iterator</code> into
     * a single String containing the provided elements.</p>
     *
     * <p>No delimiter is added before or after the list.
     * A <code>null</code> separator is the same as an empty String ("").</p>
     *
     * <p>See the examples here: {@link #join(Object[],String)}. </p>
     *
     * @param iterator  the <code>Iterator</code> of values to join together, may be null
     * @param separator  the separator character to use, null treated as ""
     * @return the joined String, <code>null</code> if null iterator input
     */
    public static String join(Iterator iterator, String separator) {
        // handle null, zero and one elements before building a buffer
        if (iterator == null) {
            return null;
        }
        if (!iterator.hasNext()) {
            return EMPTY;
        }
        Object first = iterator.next();
        if (!iterator.hasNext()) {
        	 return first.toString();
        }

        // two or more elements
        StringBuffer buf = new StringBuffer(256); // Java default is 16, probably too small
        if (first != null) {
            buf.append(first);
        }

        while (iterator.hasNext()) {
            if (separator != null) {
                buf.append(separator);
            }
            Object obj = iterator.next();
            if (obj != null) {
                buf.append(obj);
            }
        }
        return buf.toString();
    }

    /**
     * <p>Joins the elements of the provided <code>Collection</code> into
     * a single String containing the provided elements.</p>
     *
     * <p>No delimiter is added before or after the list. Null objects or empty
     * strings within the iteration are represented by empty strings.</p>
     *
     * <p>See the examples here: {@link #join(Object[],char)}. </p>
     *
     * @param collection  the <code>Collection</code> of values to join together, may be null
     * @param separator  the separator character to use
     * @return the joined String, <code>null</code> if null iterator input
     * @since 2.3
     */
    public static String join(Collection collection, char separator) {
        if (collection == null) {
            return null;
        }
        return join(collection.iterator(), separator);
    }

    /**
     * <p>Joins the elements of the provided <code>Collection</code> into
     * a single String containing the provided elements.</p>
     *
     * <p>No delimiter is added before or after the list.
     * A <code>null</code> separator is the same as an empty String ("").</p>
     *
     * <p>See the examples here: {@link #join(Object[],String)}. </p>
     *
     * @param collection  the <code>Collection</code> of values to join together, may be null
     * @param separator  the separator character to use, null treated as ""
     * @return the joined String, <code>null</code> if null iterator input
     * @since 2.3
     */
    public static String join(Collection collection, String separator) {
        if (collection == null) {
            return null;
        }
        return join(collection.iterator(), separator);
    }
    

	public static byte[] toByteArray(String str,String regex){
		String[] a = str.split(regex);
		byte[] b = new byte[a.length];
		for(int i=0;i<a.length;i++){
			b[i] = Byte.parseByte(a[i]);
		}
		return b;
	}
	
	
	public static int[] toIntArray(String str,String regex){
		String[] a = str.split(regex);
		int[] b = new int[a.length];
		for(int i=0;i<a.length;i++){
			b[i] = Integer.parseInt(a[i]);
		}
		return b;
	}
	
	public static long[] toLongArray(String str,String regex){
		String[] a = str.split(regex);
		long[] b = new long[a.length];
		for(int i=0;i<a.length;i++){
			b[i] = Long.parseLong(a[i]);
		}
		return b;
	}
	
	public static float[] toFloatArray(String str,String regex){
		String[] a = str.split(regex);
		float[] b = new float[a.length];
		for(int i=0;i<a.length;i++){
			b[i] = Float.parseFloat(a[i]);
		}
		return b;
	}
	
	public static double[] toDoubleArray(String str,String regex){
		String[] a = str.split(regex);
		double[] b = new double[a.length];
		for(int i=0;i<a.length;i++){
			b[i] = Double.parseDouble(a[i]);
		}
		return b;
	}
	
	
	public static Byte[] toByteObjectArray(String str,String regex){
		String[] a = str.split(regex);
		Byte[] b = new Byte[a.length];
		for(int i=0;i<a.length;i++){
			b[i] = Byte.parseByte(a[i]);
		}
		return b;
	}
	
	
	public static Integer[] toIntObjectArray(String str,String regex){
		String[] a = str.split(regex);
		Integer[] b = new Integer[a.length];
		for(int i=0;i<a.length;i++){
			b[i] = Integer.parseInt(a[i]);
		}
		return b;
	}
	
	public static Long[] toLongObjectArray(String str,String regex){
		String[] a = str.split(regex);
		Long[] b = new Long[a.length];
		for(int i=0;i<a.length;i++){
			b[i] = Long.parseLong(a[i]);
		}
		return b;
	}
	
	/*public static Long[] toLongObjectArray(String str,String regex){
		if(str==null||"".equals(str.trim())){
			return null;
		}
		String[] a = str.split(regex);
		Long[] b = new Long[a.length];
		int k = 0 ; 
		for(int i=0;i<a.length;i++){
			if(a[i]==null || "".equals(a[i].trim())){
				continue;
			}
			if(Assert.isNumber(a[i].trim())){
				b[k] = Long.parseLong(a[i].trim());
				k++ ; 
			}
		}
		//数组去null值
		Long[] c = new Long[k];
		for (int j = 0; j < k; j++) {
			c[j]=b[j];
		}
		return c;
	}*/
	
	public static Float[] toFloatObjectArray(String str,String regex){
		String[] a = str.split(regex);
		Float[] b = new Float[a.length];
		for(int i=0;i<a.length;i++){
			b[i] = Float.parseFloat(a[i]);
		}
		return b;
	}
	
	public static Double[] toDoubleObjectArray(String str,String regex){
		String[] a = str.split(regex);
		Double[] b = new Double[a.length];
		for(int i=0;i<a.length;i++){
			b[i] = Double.parseDouble(a[i]);
		}
		return b;
	}

	/**
	 * 将对象转为Map,key->value结构
	 */ 
	public static Map objToMap(Object obj){
		Map m = new HashMap();
		try{
			if(m!=null){
				Class c = obj.getClass();				//获得自定义类描述对象
				for(Field f : c.getDeclaredFields()){	//获取类所有已声明的属性
					PropertyDescriptor pd = new PropertyDescriptor(f.getName(),c);				//属性描述器 -->  可以获得操作属性的相关方法			
					m.put(f.getName(), pd.getReadMethod().invoke(obj));		//读取,参数1:读取值
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return m;
	}
	

}
