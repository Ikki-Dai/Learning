package com.example.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class EncryptUtil {
	
	private final static String KEY_DES = "DES";
	private final static String KEY_AES = "AES";
	
	public static final String HMAC_MD5    = "HmacMD5";
	public static final String HMAC_SHA1   = "HmacSHA1";
	public static final String HMAC_SHA224 = "HmacSHA224";
	public static final String HMAC_SHA256 = "HmacSHA256";
	public static final String HMAC_SHA384 = "HmacSHA384";
	public static final String HMAC_SHA512 = "HmacSHA512";

	private static String HMAC;
	
	public static String getHMAC() {
		return HMAC;
	}

	public static void setHMAC(String hMAC) {
		HMAC = hMAC;
	}

	/**
	 * length = 32 
	 */
	public static final String KEY_MD5 = "MD5";
	/**
	 * length = 40
	 */
	public static final String KEY_SHA1 = "SHA-1";
	/**
	 * length = 56
	 */
	public static final String KEY_SHA224 = "SHA-224";
	/**
	 * length = 64
	 */
	public static final String KEY_SHA256 = "SHA-256";
	/**
	 * length = 128
	 */
	public static final String KEY_SHA384 = "SHA-384";
	/**
	 * length = 256
	 */
	public static final String KEY_SHA512 = "SHA-512";
	
	
	private static MessageDigest digest;
	
	
	public static String getStrMD5(String message){
		return getSHA(message.getBytes(),KEY_MD5);
	}
	
	public static String getStrSHA(String message){
		return getSHA(message.getBytes(), KEY_SHA1);
	}
	
	public static String getStrSHA224(String message){
		return getSHA(message.getBytes(), KEY_SHA224);
	}
	
	public static String getStrSHA256(String message){
		return getSHA(message.getBytes(), KEY_SHA256);
	}
	
	public static String getStrSHA384(String message){
		return getSHA(message.getBytes(), KEY_SHA384);
	}
	
	public static String getStrSHA512(String message){
		return getSHA(message.getBytes(), KEY_SHA512);
	}
	
	public static byte[] decryptBASE64(String message) throws Exception{
		return (new BASE64Decoder()).decodeBuffer(message);
	}

	public static String encryptBASE64(byte[] key) throws Exception{
		return (new BASE64Encoder()).encodeBuffer(key);
	}
	
	@Deprecated
	public static String getFileSHA2(String fileName, String algorithm) throws IOException{
		BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(fileName));
		try {
			digest = MessageDigest.getInstance(algorithm);
			byte [] buffer = new byte[1024*128];
			int length;
			while((length = bufferedInputStream.read(buffer)) != -1){
				digest.update(buffer, 0, length);
			}
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return byteToHex(digest.digest());
	}
	/**
	 * recommend this method rather than 
	 * @param fileName
	 * @param algorithm
	 * @return
	 * @throws IOException
	 */
	public static String getFileSHA(String fileName, String algorithm) throws IOException{
		//BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(fileName));
		FileInputStream fileInputStream = new FileInputStream(fileName);
		try {
			digest = MessageDigest.getInstance(algorithm);
			DigestInputStream digestStream = new DigestInputStream(fileInputStream,digest);
			//int bufferSize = 256*1024;
			byte [] buffer = new byte[1024*128]; //best buffered Size
			while(digestStream.read(buffer)!=-1){
				digest = digestStream.getMessageDigest();
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return byteToHex(digest.digest());
	}
	
	
	
	private static String getSHA(byte [] bytes,String algorithm){
		try {
			digest = MessageDigest.getInstance(algorithm);
			digest.update(bytes);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return byteToHex(digest.digest());
				
	}
	
	public static String byteToHex(byte [] bytes){
		StringBuffer sb = new StringBuffer();
		for(byte b:bytes){
			String hex = Integer.toHexString(0xff & b); 
				sb = (hex.length() == 1) ? sb.append("0"+hex) : sb.append(hex);
		}
		return sb.toString();
	}
	/**
	 * 初始化密钥
	 * @param algorithm
	 * @return
	 * @throws Exception
	 */
	public static byte[] initHMACKey() throws Exception{
		KeyGenerator generator = KeyGenerator.getInstance(HMAC);
		return  generator.generateKey().getEncoded();
	}
	
	private static byte[] encryptHMAC(byte[] data, byte[] key) throws Exception{
		SecretKey secretKey;
		byte [] bytes = null;
		try {
			secretKey = new SecretKeySpec(key, HMAC);
			Mac mac = Mac.getInstance(secretKey.getAlgorithm());
			//初始化MAC
			mac.init(secretKey);			
			bytes = mac.doFinal();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bytes;
	}
 	
	public static String encryptHMACHex(String data , byte [] key)throws Exception{
		return byteToHex(encryptHMAC(data.getBytes(), key));
	}
	
}
