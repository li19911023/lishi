
package com.frame.common.util.common;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * [base64工具包] <br> 
 *  
 * @author [li.qiong]<br>
 * @version 1.0.0<br>
 * @CreateDate 2019年7月1日 <br>
 * @since v1.0.0<br>
 * @see com.runka.common.util.common <br>
 */
public class Base64Utils {

	/**
	 * 
	 * [编码] <br> 
	 *  
	 * @author [li.qiong]<br>
	 * @param str
	 * @return
	 * @throws UnsupportedEncodingException <br>
	 */
	public static String encoding(String str) {
		if(StringUtils.isNotEmpty(str)) {
			str = str.replace("\r\n", "");
			Encoder encoder = Base64.getEncoder();
			byte[] textByte = null;
			try {
				textByte = str.getBytes("UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			String encodeToString = encoder.encodeToString(textByte);
			return encodeToString;
		}
		return null;
	}
	
	/**
	 * 
	 * [解码] <br> 
	 *  
	 * @author [li.qiong]<br>
	 * @param str
	 * @return
	 * @throws UnsupportedEncodingException <br>
	 */
	public static String decoding(String str) {
		if(StringUtils.isNotEmpty(str)) {
			 str = str.replace("\r\n", "");
			 Decoder decoder = Base64.getDecoder();
			 byte[] decode = decoder.decode(str);
			 try {
				str = new String(decode, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			 return str;
		}
		return null;
	}
	
}

