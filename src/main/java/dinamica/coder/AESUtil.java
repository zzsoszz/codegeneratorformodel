package dinamica.coder;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;

public class AESUtil {

	private static final String DECRYPT_MODE="AES/CBC/NoPadding";	
	
	private static SecretKeySpec aesKeySpc;
	
	private static IvParameterSpec ivSpec;
	
	public AESUtil(String key){
	    aesKeySpc = new SecretKeySpec(key.getBytes(), "AES");
		ivSpec = new IvParameterSpec(key.getBytes());
	}
	

	/**
	 * 
	* @Title: decryptAES
	* @Description: AES解密
	* @author： psk
	* @date:2013-1-8 下午2:28:12
	* @param input
	* @return    
	* @return String    返回类型
	* @throws
	 */
	public String decryptAES(String input){
		try {
	        Cipher cipher = Cipher.getInstance(DECRYPT_MODE);
	        cipher.init(Cipher.DECRYPT_MODE, aesKeySpc, ivSpec);
	        byte[] result = cipher.doFinal(Hex.decodeHex(input.toCharArray()));
	        return new String(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	* @Title: encryptAES
	* @Description: AES加密
	* @author： psk
	* @date:2013-1-8 下午2:28:44
	* @param input
	* @return    
	* @return String    返回类型
	* @throws
	 */
	public String encryptAES(String input){
		try {
	        Cipher cipher = Cipher.getInstance(DECRYPT_MODE);
	        cipher.init(Cipher.ENCRYPT_MODE, aesKeySpc, ivSpec);
	        //不满16位用0补齐
	        byte[] temp = input.getBytes("utf-8");
	        int len = temp.length;
	        if (len % 16 != 0) {
	            len = len + (16 - len%16);
	        }
	        byte[] target = new byte[len];
	        System.arraycopy(temp, 0, target,0,temp.length);
	        
	        byte[] outText = cipher.doFinal(target);
	        return Hex.encodeHexString(outText);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
