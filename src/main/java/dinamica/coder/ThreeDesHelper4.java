package dinamica.coder;

import java.io.UnsupportedEncodingException;
import java.security.Security;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
public class ThreeDesHelper4 {
	
	
	private static final String Algorithm = "DESede"; //定义 加密算法,可用 DES,DESede,Blowfish
	private final static String encoding = "utf-8";
	
	
	public static String encrypt(String key, String  data) throws UnsupportedEncodingException {
    	    byte edata[]=encryptMode(key.getBytes(),data.getBytes(encoding));
			return  new Base64().encodeAsString(edata);
	}
	
	public static String decrypt(String key, String  data) throws UnsupportedEncodingException {
	    byte ddata[]=decryptMode(key.getBytes(),new Base64().decodeBase64(data));
	    return new String(ddata, encoding);
	}
	
    //keybyte为加密密钥，长度为24字节
    //src为被加密的数据缓冲区（源）
    public static byte[] encryptMode(byte[] keybyte, byte[] src) {
       try {
            //生成密钥
            SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);
            //加密
            Cipher c1 = Cipher.getInstance(Algorithm);
            c1.init(Cipher.ENCRYPT_MODE, deskey);
            return c1.doFinal(src);
        } catch (java.security.NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        } catch (javax.crypto.NoSuchPaddingException e2) {
            e2.printStackTrace();
        } catch (java.lang.Exception e3) {
            e3.printStackTrace();
        }
        return null;
    }

    //http://blog.csdn.net/alonesword/article/details/11596057
    
    
    //keybyte为加密密钥，长度为24字节
    //src为加密后的缓冲区
    public static byte[] decryptMode(byte[] keybyte, byte[] src) {      
    try {
            //生成密钥
            SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);
            //解密
            Cipher c1 = Cipher.getInstance(Algorithm);
            c1.init(Cipher.DECRYPT_MODE, deskey);
            return c1.doFinal(src);
        } catch (java.security.NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        } catch (javax.crypto.NoSuchPaddingException e2) {
            e2.printStackTrace();
        } catch (java.lang.Exception e3) {
            e3.printStackTrace();
        }
        return null;
    }
    
    
    
    

    //转换成十六进制字符串
    public static String byte2hex(byte[] b) {
        String hs="";
        String stmp="";

        for (int n=0;n<b.length;n++) {
            stmp=(java.lang.Integer.toHexString(b[n] & 0XFF));
            if (stmp.length()==1) hs=hs+"0"+stmp;
            else hs=hs+stmp;
            if (n<b.length-1)  hs=hs+":";
        }
        return hs.toUpperCase();
    }
    
    
    public static void main(String[] args) throws UnsupportedEncodingException
    {
    	String miwen=ThreeDesHelper4.encrypt("Y4OTUwMzc0ODU3NjAxMjM0OO", "123456");
    	System.out.println(miwen);
    	String mingwen=ThreeDesHelper4.decrypt("Y4OTUwMzc0ODU3NjAxMjM0OO", miwen);
    	System.out.println(mingwen);
    }
    
}