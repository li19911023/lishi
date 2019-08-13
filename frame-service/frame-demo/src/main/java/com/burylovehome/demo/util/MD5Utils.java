package com.burylovehome.demo.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 
 * [使用md5,md6的算法进行加密] <br> 
 *  
 * @author [li.qiong]<br>
 * @version 1.0.0<br>
 * @CreateDate 2019年4月16日 <br>
 * @since v1.0.0<br>
 * @see com.burylovehome.demo.util <br>
 */
public class MD5Utils {
	

	/**
	 * 
	 * [使用md5的算法进行加密] <br> 
	 *  
	 * @author [li.qiong]<br>
	 * @param plainText
	 * @return <br>
	 */
	public static String md5(String plainText) {
		byte[] secretBytes = null;
		try {
			secretBytes = MessageDigest.getInstance("md5").digest(
					plainText.getBytes());
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("没有md5这个算法！");
		}
		String md5code = new BigInteger(1, secretBytes).toString(16);// 16进制数字
		// 如果生成数字未满32位，需要前面补0
		for (int i = 0; i < 32 - md5code.length(); i++) {
			md5code = "0" + md5code;
		}
		return md5code;
	}

	public static void main(String[] args) {
		System.out.println(md6("sewrfew12"));
	}
	
	/**
	 * 
	 * [使用md6的算法进行加密] <br> 
	 *  
	 * @author [li.qiong]<br>
	 * @param plainText
	 * @return <br>
	 */
	public static String md6(String plainText) {
		String strSub = plainText.substring(plainText.length()-5, plainText.length()-1);
		StringBuilder builder = new StringBuilder(plainText);
		String sale = "jsbyjz";
		String strfanxu = builder.reverse().toString();
		String resultPass = plainText + strSub + sale + strfanxu;
		String pass1 = resultPass.substring(0,resultPass.length()/2);
		String pass2 = resultPass.substring(resultPass.length()/2);
		String password = MD5Utils.md5(pass1).substring(7, 23) + MD5Utils.md5(pass2).substring(5, 21);
		return password;
	}

}