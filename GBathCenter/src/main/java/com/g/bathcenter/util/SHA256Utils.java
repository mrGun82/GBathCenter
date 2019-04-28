package com.g.bathcenter.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.SecureRandom;

public class SHA256Utils {
	public static final String KEY_SHA = "SHA-256";   
	public static final String ra="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
 
	/**
	 * 字符串sha加密
	 * @param s 要加密的字符串
	 * @return 加密后的字符串
	 */
	public static String SHA(String s)
	{
	    BigInteger sha =null;
	    byte[] bys = s.getBytes();   
	    try {
	         MessageDigest messageDigest = MessageDigest.getInstance(KEY_SHA);  
	         messageDigest.update(bys);
	         sha = new BigInteger(messageDigest.digest());
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	    return sha.toString(32);
	}
	
	
	/**
	 * 生成随机盐
	 * @return 随机生成的盐
	 */
	public static String SALT() {
		SecureRandom random=new SecureRandom();
		int length=random.nextInt(5)+12;//盐的长度，这里是8-12可自行调整
        char[] text = new char[length];
        for (int i = 0; i < length; i++) {
            text[i] = ra.charAt(random.nextInt(ra.length()));
        }
        return new String(text);
    }
	
	/**
	 * 测试方法
	 * @param args
	 */
	public static void main(String[] args) {
		String salt=SALT();
		String sha = SHA("admin"+salt);
		System.out.println(salt);
		System.out.println(sha);
		
	}
}
