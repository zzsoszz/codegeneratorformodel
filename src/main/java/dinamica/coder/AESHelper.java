package dinamica.coder;
import java.io.InputStreamReader;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;

import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.*;  
import javax.crypto.spec.*;  
import org.apache.commons.codec.binary.Base64;


public class AESHelper {


	/**
	 * 
	 * @author wchun
	 * 
	 * AES128 算法，加密模式为ECB，填充模式为 pkcs7（实际就是pkcs5）
	 * 
	 *
	 */
		
		static final String algorithmStr="AES/ECB/PKCS5Padding";
		
		static private KeyGenerator keyGen;
		
		static private Cipher cipher;
		
		static boolean isInited=false;
		
		//初始化
		static private void init()
		{
			
			//初始化keyGen
			try {
				keyGen=KeyGenerator.getInstance("AES");
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			keyGen.init(128);
			
			//初始化cipher
			try {
				cipher=Cipher.getInstance(algorithmStr);
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchPaddingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			isInited=true;
		}
		
		public static byte[] GenKey()
		{
			if(!isInited)//如果没有初始化过,则初始化
			{
				init();
			}
			return keyGen.generateKey().getEncoded();
		}
		
		public static byte[] Encrypt(byte[] content,byte[] keyBytes)
		{
			byte[] encryptedText=null;
			
			if(!isInited)//为初始化
			{
				init();
			}
			
			Key key=new SecretKeySpec(keyBytes,"AES");
			
			try {
				cipher.init(Cipher.ENCRYPT_MODE, key);
			} catch (InvalidKeyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				encryptedText=cipher.doFinal(content);
			} catch (IllegalBlockSizeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BadPaddingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return encryptedText;
		}
		
		//解密为byte[]
		public static byte[] DecryptToBytes(byte[] content,byte[] keyBytes)
		{
			byte[] originBytes=null;
			if(!isInited)
			{
				init();
			}
			
			Key key=new SecretKeySpec(keyBytes,"AES");
			
			try {
				cipher.init(Cipher.DECRYPT_MODE, key);
			} catch (InvalidKeyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//解密
			try {
				originBytes=cipher.doFinal(content);
			} catch (IllegalBlockSizeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BadPaddingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return originBytes;
		}
		
		
		public static String EncryptToString(String content,String  keystr)
		{
			byte[] contentEnc = null;
			try {
				contentEnc = Encrypt(content.getBytes("utf-8"),keystr.getBytes());
				return new Base64().encodeToString(contentEnc);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			
			return null;
		}
		
		public static String DecryptToString(String content,String keystr)
		{
			byte[] contentbase64=new Base64().decode(content);
			try {
				return new String(DecryptToBytes(contentbase64,keystr.getBytes()),"utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return null;
		}
		
		
		public static void main(String args[])
		{
//			Header header=new Header();
//			header.setAccountID("0682b215-066e-4ff4-89b4-cc0f85fb95f8");
//			header.setReqTime(DateHelper.getDateString("yyyy-MM-dd HH:mm:ss.SSS"));
//			header.setVersion("20111128102912");
//			header.setServiceName("SubmitHotelOrder");
			try {
//				String key=WebServiceHelper.createDigitalSign(header);
//				//System.out.println(new String( Encrypt("11111111".getBytes(),key) ));
//				//System.out.println(new String(DecryptToBytes(Encrypt("rrd3343rrrrrr".getBytes(),key),key)));
//				
//				//System.out.println(new String(Encrypt("rrd3343rrrrrr".getBytes(),key)));
//				//System.out.println(new String(DecryptToBytes(Encrypt("rrd3343rrrrrr".getBytes(),key),key)));
//				
//				//System.out.println(Encrypt("aaaa","ccc"));;
				
				
				
//		        /*
//		         * 此处使用AES-128-ECB加密模式，key需要为16位。
//		         */
//		        String cKey = "1234567890123456";
//		        // 需要加密的字串
//		        String cSrc = "www.gowhere.so";
//		        //System.out.println(cSrc);
//		        // 加密
//		        String enString = AESHelper.Encrypt(cSrc, cKey);
//		        //System.out.println("加密后的字串是：" + enString);
//		        // 解密
//		        String DeString = AESHelper.Decrypt(enString, cKey);
//		        //System.out.println("解密后的字串是：" + DeString);
				
//				//System.out.println(key);
//				String val=EncryptToString("中国工商银行信用卡",key);
//				//System.out.println(val);
//				String str2=DecryptToString(val,key);
//				//System.out.println(str2);
//				
				
//				key="e555f767bc4e17b5eafdb5e7bf36452c";
//				//System.out.println(key);
//				String val=EncryptToString("中国工商银行信用卡",key);
//				//System.out.println("中国工商银行信用卡".getBytes().length);
//				//System.out.println(val);
//				String str2=DecryptToString(val,key);
//				//System.out.println(str2);
//				
//				String val="中国工商银行信用卡";
//				//String arg=IOUtils.toString(val.getBytes("utf-8"), "utf-8");
//				//System.out.println(val.getBytes("utf-8").length);
				
//				String val2=EncryptToString(IOUtils.toString("中国工商银行信用卡".getBytes("utf-8"), "utf-8"),"1e99f1b9304151bcebbd8ab9df6a6da0");
//				//System.out.println(val2);
				
				String val2=EncryptToString(new String("中国工商银行信用卡".getBytes("utf-8"),"utf-8"),"1e99f1b9304151bcebbd8ab9df6a6da0");
				//System.out.println(val2);
				//String cardtype3=AESHelper.DecryptToString("iyyu5cggmRCo7QCOczn5CnWF8jmt/kv6SsQjIw4UOvY=","8c9ac36a7961330d699d31329e1c8875");
				String cardtype3=AESHelper.DecryptToString("bHCNkVR5ckopRx8zi6zQG6qgmttkCNKy9ukeQzm0KG0=","1e99f1b9304151bcebbd8ab9df6a6da0");
				//System.out.println(cardtype3);
				
				
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		

		
		
	    // 加密
	    public static String Encrypt(String sSrc, String sKey) throws Exception {
	        if (sKey == null) {
	            System.out.print("Key为空null");
	            return null;
	        }
	        // 判断Key是否为16位
	        if (sKey.length() != 16) {
	            System.out.print("Key长度不是16位");
	            return null;
	        }
	        byte[] raw = sKey.getBytes("utf-8");
	        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
	        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");//"算法/模式/补码方式"
	        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
	        byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));
	        return new Base64().encodeToString(encrypted);//此处使用BASE64做转码功能，同时能起到2次加密的作用。
	    }

	    // 解密
	    public static String Decrypt(String sSrc, String sKey) throws Exception {
	        try {
	            // 判断Key是否正确
	            if (sKey == null) {
	                System.out.print("Key为空null");
	                return null;
	            }
	            // 判断Key是否为16位
	            if (sKey.length() != 16) {
	                System.out.print("Key长度不是16位");
	                return null;
	            }
	            byte[] raw = sKey.getBytes("utf-8");
	            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
	            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
	            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
	            byte[] encrypted1 = new Base64().decode(sSrc);//先用base64解密
	            try {
	                byte[] original = cipher.doFinal(encrypted1);
	                String originalString = new String(original,"utf-8");
	                return originalString;
	            } catch (Exception e) {
	                //System.out.println(e.toString());
	                return null;
	            }
	        } catch (Exception ex) {
	            //System.out.println(ex.toString());
	            return null;
	        }
	    }


}
