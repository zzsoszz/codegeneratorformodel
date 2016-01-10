package dinamica.coder;

import java.security.Key;


import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import org.apache.commons.codec.binary.Base64;
/*
 * DES：算法DES要求密钥长度为64位密钥, 有效密钥56位。64bits=8*8*1，即8个ascii字符。
   DESede：算法DESede要求的密钥位数为192位，即192bits=64*3=8*8*3，即24个ascii字符。
   Blowfish：算法Blowfish要求密钥长度为8--448字位，即8--448(bits)。即：1个到56个ascii字符
 */
public class ThreeDesHelper2 {

		// 密钥
		 static String default_secretKey = "liuyunqiang@lx100$#365#$";
		// 向量
		 static String default_iv = "01234567";
		
		// 加解密统一使用的编码方式
		private final static String encoding = "utf-8";

		/**
		 * 3DES加密
		 * 
		 * @param plainText 普通文本
		 * @return
		 * @throws Exception 
		 */
		public static String encode(String plainText,String secretKey,String iv) throws Exception {
			Key deskey = null;
			DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes("UTF-8"));
			SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
			deskey = keyfactory.generateSecret(spec);
			Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
			IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
			cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
			byte[] encryptData = cipher.doFinal(plainText.getBytes(encoding));
			return  new Base64().encodeAsString(encryptData);
		}

		public static String encodebyDES(String plainText,String secretKey,String iv) throws Exception {
			Key deskey = null;
			DESKeySpec spec = new DESKeySpec(secretKey.getBytes("UTF-8"));
			SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("DES");
			deskey = keyfactory.generateSecret(spec);
			Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			//Cipher cipher = Cipher.getInstance("DES");
			IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
			cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
			byte[] encryptData = cipher.doFinal(plainText.getBytes(encoding));
			return  new Base64().encodeAsString(encryptData);
		}
		
		/*
		 * 去掉base64
		 */
		public static byte[] encode(byte[] plainText,String key,String iv) throws Exception {
			Key deskey = null;
			DESedeKeySpec spec = new DESedeKeySpec(key.getBytes());
			SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
			deskey = keyfactory.generateSecret(spec);
			Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
			IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
			cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
			byte[] encryptData = cipher.doFinal(plainText);
			return  encryptData;
		}
		
		
		
		
		/**
		 * 3DES解密
		 * 
		 * @param encryptText 加密文本
		 * @return
		 * @throws Exception
		 */
		public static String decode(String encryptText,String secretKey,String iv) throws Exception {
			Key deskey = null;
			DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
			SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
			deskey = keyfactory.generateSecret(spec);
			Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
			IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
			cipher.init(Cipher.DECRYPT_MODE, deskey, ips);
			byte[] decryptData = cipher.doFinal(new Base64().decodeBase64(encryptText));
			return new String(decryptData, encoding);
		}
		
		public static String decodeByDES(String encryptText,String secretKey,String iv) throws Exception {
			Key deskey = null;
			DESKeySpec spec = new DESKeySpec(secretKey.getBytes());
			SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("DES");
			deskey = keyfactory.generateSecret(spec);
			Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
			cipher.init(Cipher.DECRYPT_MODE, deskey, ips);
			byte[] decryptData = cipher.doFinal(new Base64().decodeBase64(encryptText));
			return new String(decryptData, encoding);
		}
		
		public static String decode(String encryptText) throws Exception {
			return decode(encryptText,default_secretKey,default_iv);
		}
		
		public static String encode(String plainText) throws Exception {
			return encode(plainText,default_secretKey,default_iv);
		}
		
		
		/*
		 * 去掉base64
		 */
		public static byte[] decode(byte[] encryptText,String secretKey,String iv) throws Exception {
			Key deskey = null;
			DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
			SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
			deskey = keyfactory.generateSecret(spec);
			Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
			IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
			cipher.init(Cipher.DECRYPT_MODE, deskey, ips);
			//byte[] decryptData = cipher.doFinal(new Base64().decodeBase64(encryptText));
			byte[] decryptData = cipher.doFinal(encryptText);
			return decryptData;
		}
		
		
		
		
		public static void main(String[] args) {
			try {
//				String a=ThreeDesHelper2.encode("491172625");
//				//System.out.println(a);
//				String b=ThreeDesHelper2.decode(a);
//				//System.out.println(b);
				
//				
//				byte[] a=ThreeDesHelper2.encode("491172625".getBytes("utf-8"),secretKey,iv);
//				String base64=new Base64().encodeAsString(a);
//				//System.out.println(base64);
//				byte[] b=ThreeDesHelper2.decode(new Base64().decodeBase64(base64),secretKey,iv);
//				//System.out.println(new String(b,"utf-8"));
//				
				//System.out.println(ThreeDesHelper2.decode("iJ1cPyunD6eP/LVBW2BOHg=="));
				
//				// 密钥
//				 String secretKey = "liuyunqiang@lx100$#365#$";
//				// 向量
//				 String iv = "01234567";
//				 
//				 String miwen=ThreeDesHelper2.encode("1111", secretKey, iv);
//				 String mingwen=ThreeDesHelper2.decode(miwen, secretKey, iv);
//				 System.out.println(miwen);
//				 System.out.println(mingwen);
//				 
				 
				
//				// 密钥
//				 String secretKey = ElongConfig.appKey.substring(ElongConfig.appKey.length()-8) ;
//				 // 向量
//				 String iv = ElongConfig.appKey.substring(ElongConfig.appKey.length()-8);
//				 
				
//				
//				 String secretKey = "12345678";
//				 // 向量
//				 String iv ="12345678";
//				 
//				 String miwen=ThreeDesHelper2.encodebyDES("12345#6789012345", secretKey, iv);
//				 String mingwen=ThreeDesHelper2.decodeByDES(miwen, secretKey, iv);
//				 System.out.println(miwen);
//				 System.out.println(mingwen);
//				 
				 
				
//				String miwen= Tool.encryptDES("12345#6789012345", "12345678");
//				String mingwen=Tool.decryptDES(miwen, "12345678");
//				System.out.println(miwen);
//				System.out.println(mingwen);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
}


//
//http://www.blogjava.net/amigoxie/archive/2014/07/06/415503.html
//
//常用加密算法的Java实现总结(二)
//
//——对称加密算法DES、3DES和AES
//
//日期：2014/7/6
//
//文：阿蜜果
//1、对称加密算法
//1.1 定义
//
//对称加密算法是应用较早的加密算法，技术成熟。在对称加密算法中，数据发信方将明文（原始数据）和加密密钥（mi yue）一起经过特殊加密算法处理后，使其变成复杂的加密密文发送出去。收信方收到密文后，若想解读原文，则需要使用加密用过的密钥及相同算法的逆算法对密文进行解密，才能使其恢复成可读明文。在对称加密算法中，使用的密钥只有一个，发收信双方都使用这个密钥对数据进行加密和解密，这就要求解密方事先必须知道加密密钥。
//1.2 优缺点
//
//         优点：算法公开、计算量小、加密速度快、加密效率高。
//
//         缺点：
//
//（1）交易双方都使用同样钥匙，安全性得不到保证。
//
//（2）每对用户每次使用对称加密算法时，都需要使用其他人不知道的惟一钥匙，这会使得发收信双方所拥有的钥匙数量呈几何级数增长，密钥管理成为用户的负担。对称加密算法在分布式网络系统上使用较为困难，主要是因为密钥管理困难，使用成本较高。
//1.3 常用对称加密算法
//
//基于“对称密钥”的加密算法主要有DES、3DES（TripleDES）、AES、RC2、RC4、RC5和Blowfish等。本文只介绍最常用的对称加密算法DES、3DES（TripleDES）和AES。
//2、DES
//2.1 概述
//
//DES算法全称为Data Encryption Standard，即数据加密算法，它是IBM公司于1975年研究成功并公开发表的。DES算法的入口参数有三个：Key、Data、Mode。其中Key为8个字节共64位，是DES算法的工作密钥；Data也为8个字节64位，是要被加密或被解密的数据；Mode为DES的工作方式,有两种：加密或解密。
//2.2 算法原理
//
//DES算法把64位的明文输入块变为64位的密文输出块，它所使用的密钥也是64位，其算法主要分为两步：
//
//（1）初始置换
//
//其功能是把输入的64位数据块按位重新组合,并把输出分为L0、R0两部分，每部分各长32位，其置换规则为将输入的第58位换到第一位，第50位换到第2位……依此类推,最后一位是原来的第7位。L0、R0则是换位输出后的两部分，L0是输出的左32位，R0是右32位，例：设置换前的输入值为D1D2D3……D64，则经过初始置换后的结果为:L0=D58D50……D8；R0=D57D49……D7。
//
//（2）逆置换
//
//经过16次迭代运算后，得到L16、R16,将此作为输入，进行逆置换，逆置换正好是初始置换的逆运算，由此即得到密文输出。
//2.3 五种分组模式
//2.3.1 EBC模式
//
//优点：
//
//1.简单；
//
//2.有利于并行计算；
//
//3.误差不会被传送；
//
//缺点：
//
//1.不能隐藏明文的模式；
//
//2.可能对明文进行主动攻击。
//2.3.2 CBC模式
//
//         CBC模式又称为密码分组链接模式，示意图如下：
//
//
//优点：
//
//1.不容易主动攻击,安全性好于ECB,适合传输长度长的报文,是SSL、IPSec的标准。
//
//缺点：
//
//1、不利于并行计算；
//
//2、误差传递；
//
//3、需要初始化向量IV。
//2.3.3 CFB模式
//
//         CFB模式又称为密码发反馈模式，示意图如下图所示：
//
//
//优点：
//
//1、隐藏了明文模式；
//
//2、分组密码转化为流模式；
//
//3、可以及时加密传送小于分组的数据。
//
//缺点:
//
//1、不利于并行计算；
//
//2、误差传送：一个明文单元损坏影响多个单元；
//
//3、唯一的IV。
//2.3.4 OFB模式
//
//         OFB模式又称输出反馈模式，示意图所下图所示：
//
//
//优点：
//
//1、隐藏了明文模式；
//
//2、分组密码转化为流模式；
//
//3、可以及时加密传送小于分组的数据。
//
//缺点：
//
//1、不利于并行计算；
//
//2、对明文的主动攻击是可能的；
//
//3、误差传送：一个明文单元损坏影响多个单元。
//2.3.5 CTR模式
//
//计数模式（CTR模式）加密是对一系列输入数据块(称为计数)进行加密，产生一系列的输出块，输出块与明文异或得到密文。对于最后的数据块，可能是长u位的局部数据块，这u位就将用于异或操作，而剩下的b-u位将被丢弃（b表示块的长度）。CTR解密类似。这一系列的计数必须互不相同的。假定计数表示为T1, T2, …, Tn。CTR模式可定义如下：
//
//CTR加密公式如下：
//
//Cj = Pj XOR Ek(Tj)
//
//C*n = P*n XOR MSBu(Ek(Tn)) j = 1，2… n-1;
//
//CTR解密公式如下：
//
//Pj = Cj XOR Ek(Tj)
//
//P*n = C*n XOR MSBu(Ek(Tn)) j = 1，2 … n-1;
//
//AES CTR模式的结构如图5所示。 
//
//图5 AES CTR的模式结构
//
//Fig 5 Structure of AES CTR Mode
//
//加密方式：密码算法产生一个16 字节的伪随机码块流，伪随机码块与输入的明文进行异或运算后产生密文输出。密文与同样的伪随机码进行异或运算后可以重产生明文。
//
//
//CTR 模式被广泛用于 ATM 网络安全和 IPSec应用中，相对于其它模式而言，CRT模式具有如下特点：
//
//■硬件效率：允许同时处理多块明文 / 密文。
//
//■ 软件效率：允许并行计算，可以很好地利用 CPU 流水等并行技术。
//
//■ 预处理：算法和加密盒的输出不依靠明文和密文的输入，因此如果有足够的保证安全的存储器，加密算法将仅仅是一系列异或运算，这将极大地提高吞吐量。
//
//■ 随机访问：第 i 块密文的解密不依赖于第 i-1 块密文，提供很高的随机访问能力
//
//■ 可证明的安全性：能够证明 CTR 至少和其他模式一样安全（CBC, CFB, OFB, ...）
//
//■ 简单性：与其它模式不同，CTR模式仅要求实现加密算法，但不要求实现解密算法。对于 AES 等加/解密本质上不同的算法来说，这种简化是巨大的。
//
//■ 无填充，可以高效地作为流式加密使用。
//2.4 常用的填充方式
//
//         在Java进行DES、3DES和AES三种对称加密算法时，常采用的是NoPadding（不填充）、Zeros填充（0填充）、PKCS5Padding填充。
//2.4.1 ZerosPadding
//
//全部填充为0的字节，结果如下：
//
//       F1 F2 F3 F4 F5 F6 F7 F8   //第一块
//
//      F9 00 00 00 00 00 00 00 //第二块
//2.4.2 PKCS5Padding
//
//每个填充的字节都记录了填充的总字节数，结果如下：
//
// F1 F2 F3 F4 F5 F6 F7 F8   //第一块
//
// F9 07 07 07 07 07 07 07 //第二块
//2.5 Java中的DES实现
//
//         DES加密算法（ECB、无填充）的Java实现如下所示：
//package amigo.endecrypt;
//
//import java.security.InvalidKeyException;
//import java.security.Key;
//import java.security.NoSuchAlgorithmException;
//import java.security.SecureRandom;
//import java.security.spec.InvalidKeySpecException;
//
//import javax.crypto.Cipher;
//import javax.crypto.SecretKey;
//import javax.crypto.SecretKeyFactory;
//import javax.crypto.spec.DESKeySpec;
//
//import org.apache.commons.codec.binary.Base64;
//
//public class DESUtil {
//    //算法名称 
//    public static final String KEY_ALGORITHM = "DES";
//    //算法名称/加密模式/填充方式 
//    //DES共有四种工作模式-->>ECB：电子密码本模式、CBC：加密分组链接模式、CFB：加密反馈模式、OFB：输出反馈模式
//    public static final String CIPHER_ALGORITHM = "DES/ECB/NoPadding";
//
//    /**
//     *   
//     * 生成密钥key对象
//     * @param KeyStr 密钥字符串 
//     * @return 密钥对象 
//     * @throws InvalidKeyException   
//     * @throws NoSuchAlgorithmException   
//     * @throws InvalidKeySpecException   
//     * @throws Exception 
//     */
//    private static SecretKey keyGenerator(String keyStr) throws Exception {
//        byte input[] = HexString2Bytes(keyStr);
//        DESKeySpec desKey = new DESKeySpec(input);
//        //创建一个密匙工厂，然后用它把DESKeySpec转换成
//        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
//        SecretKey securekey = keyFactory.generateSecret(desKey);
//        return securekey;
//    }
//
//    private static int parse(char c) {
//        if (c >= 'a') return (c - 'a' + 10) & 0x0f;
//        if (c >= 'A') return (c - 'A' + 10) & 0x0f;
//        return (c - '0') & 0x0f;
//    }
//
//    // 从十六进制字符串到字节数组转换 
//    public static byte[] HexString2Bytes(String hexstr) {
//        byte[] b = new byte[hexstr.length() / 2];
//        int j = 0;
//        for (int i = 0; i < b.length; i++) {
//            char c0 = hexstr.charAt(j++);
//            char c1 = hexstr.charAt(j++);
//            b[i] = (byte) ((parse(c0) << 4) | parse(c1));
//        }
//        return b;
//    }
//
//    /** 
//     * 加密数据
//     * @param data 待加密数据
//     * @param key 密钥
//     * @return 加密后的数据 
//     */
//    public static String encrypt(String data, String key) throws Exception {
//        Key deskey = keyGenerator(key);
//        // 实例化Cipher对象，它用于完成实际的加密操作
//        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
//        SecureRandom random = new SecureRandom();
//        // 初始化Cipher对象，设置为加密模式
//        cipher.init(Cipher.ENCRYPT_MODE, deskey, random);
//        byte[] results = cipher.doFinal(data.getBytes());
//        // 该部分是为了与加解密在线测试网站（http://tripledes.online-domain-tools.com/）的十六进制结果进行核对
//        for (int i = 0; i < results.length; i++) {
//            System.out.print(results[i] + " ");
//        }
//        System.out.println();
//        // 执行加密操作。加密后的结果通常都会用Base64编码进行传输 
//        return Base64.encodeBase64String(results);
//    }
//
//    /** 
//     * 解密数据 
//     * @param data 待解密数据 
//     * @param key 密钥 
//     * @return 解密后的数据 
//     */
//    public static String decrypt(String data, String key) throws Exception {
//        Key deskey = keyGenerator(key);
//        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
//        //初始化Cipher对象，设置为解密模式
//        cipher.init(Cipher.DECRYPT_MODE, deskey);
//        // 执行解密操作
//        return new String(cipher.doFinal(Base64.decodeBase64(data)));
//    }
//
//    public static void main(String[] args) throws Exception {
//        String source = "amigoxie";
//        System.out.println("原文: " + source);
//        String key = "A1B2C3D4E5F60708";
//        String encryptData = encrypt(source, key);
//        System.out.println("加密后: " + encryptData);
//        String decryptData = decrypt(encryptData, key);
//        System.out.println("解密后: " + decryptData);
//    }
//}
//
//    测试结果：
//原文: amigoxie
//97 -15 32 -117 -57 -42 -90 75 
//加密后: YfEgi8fWpks=
//解密后: amigoxie
//
//    为了核对测试结果是否正确，需要将结果与 “加密解密在线测试网站”（http://tripledes.online-domain-tools.com/）进行核对，在该网站的测试结果如下：
//
//
//         左侧下方显示的加密结果“61 f1 20 8b c7 d6 a6 4b”是返回的16进制结果。与我们打印出的十进制“97 -15 32 -117 -57 -42 -90 75”是相对应的。
//
//需要注意的是这个网站采用的填充方式是NoPadding，如果我们程序中采用PKCS5Padding或PKCS7Padding填充方式，这些填充方式在不足位时会进行填充，所以会跟我们在该测试网站看到的后面部分不一致。
//
//另外Java的byte的范围是-128-127，而不是0～255，因此超过十六进制7f（对应127）的数在Java中会转换为负数。
//
//【说明】DESUtil类中引入的org.bouncycastle.jce.provider.BouncyCastleProvider类在commons-codec-1.6.jar包中。
//3、3DES
//3.1 概述
//
//3DES（或称为Triple DES）是三重数据加密算法（TDEA，Triple Data Encryption Algorithm）块密码的通称。它相当于是对每个数据块应用三次DES加密算法。由于计算机运算能力的增强，原版DES密码的密钥长度变得容易被暴力破解；3DES即是设计用来提供一种相对简单的方法，即通过增加DES的密钥长度来避免类似的攻击，而不是设计一种全新的块密码算法。
//3.2 算法原理
//
//使用3条56位的密钥对 数据进行三次加密。3DES（即Triple DES）是DES向AES过渡的加密算法（1999年，NIST将3-DES指定为过渡的加密标准）。
//
//其具体实现如下：设Ek()和Dk()代表DES算法的加密和解密过程，K代表DES算法使用的密钥，P代表明文，C代表密文，这样：
//
//3DES加密过程为：C=Ek3(Dk2(Ek1(P)))
//
//3DES解密过程为：P=Dk1(EK2(Dk3(C)))
//3.3 Java中的3DES实现
//
//         3DES的在Java的实现与DES类似，如下代码为3DES加密算法、CBC模式、NoPadding填充方式的加密解密结果，参考代码如下所示：
//package amigo.endecrypt;
//
//import java.security.InvalidKeyException;
//import java.security.Key;
//import java.security.NoSuchAlgorithmException;
//import java.security.Security;
//import java.security.spec.InvalidKeySpecException;
//
//import javax.crypto.Cipher;
//import javax.crypto.SecretKeyFactory;
//import javax.crypto.spec.DESedeKeySpec;
//import javax.crypto.spec.IvParameterSpec;
//
//import org.bouncycastle.jce.provider.BouncyCastleProvider;
//
//public class ThreeDESUtil {
//    // 算法名称 
//    public static final String KEY_ALGORITHM = "desede";
//    // 算法名称/加密模式/填充方式 
//    public static final String CIPHER_ALGORITHM = "desede/CBC/NoPadding";
//
//    /** 
//     * CBC加密 
//     * @param key 密钥 
//     * @param keyiv IV 
//     * @param data 明文 
//     * @return Base64编码的密文 
//     * @throws Exception 
//     */
//    public static byte[] des3EncodeCBC(byte[] key, byte[] keyiv, byte[] data) throws Exception {
//        Security.addProvider(new BouncyCastleProvider()); 
//        Key deskey = keyGenerator(new String(key));
//        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
//        IvParameterSpec ips = new IvParameterSpec(keyiv);
//        cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
//        byte[] bOut = cipher.doFinal(data);
//        for (int k = 0; k < bOut.length; k++) {
//            System.out.print(bOut[k] + " ");
//        }
//        System.out.println("");
//        return bOut;
//    }
//
//    /** 
//     *   
//     * 生成密钥key对象 
//     * @param KeyStr 密钥字符串 
//     * @return 密钥对象 
//     * @throws InvalidKeyException   
//     * @throws NoSuchAlgorithmException   
//     * @throws InvalidKeySpecException   
//     * @throws Exception 
//     */
//    private static Key keyGenerator(String keyStr) throws Exception {
//        byte input[] = HexString2Bytes(keyStr);
//        DESedeKeySpec KeySpec = new DESedeKeySpec(input);
//        SecretKeyFactory KeyFactory = SecretKeyFactory.getInstance(KEY_ALGORITHM);
//        return ((Key) (KeyFactory.generateSecret(((java.security.spec.KeySpec) (KeySpec)))));
//    }
//
//    private static int parse(char c) {
//        if (c >= 'a') return (c - 'a' + 10) & 0x0f;
//        if (c >= 'A') return (c - 'A' + 10) & 0x0f;
//        return (c - '0') & 0x0f;
//    }
// 
//    // 从十六进制字符串到字节数组转换 
//    public static byte[] HexString2Bytes(String hexstr) {
//        byte[] b = new byte[hexstr.length() / 2];
//        int j = 0;
//        for (int i = 0; i < b.length; i++) {
//            char c0 = hexstr.charAt(j++);
//            char c1 = hexstr.charAt(j++);
//            b[i] = (byte) ((parse(c0) << 4) | parse(c1));
//        }
//        return b;
//    }
//
//    /** 
//     * CBC解密 
//     * @param key 密钥 
//     * @param keyiv IV 
//     * @param data Base64编码的密文 
//     * @return 明文 
//     * @throws Exception 
//     */
//    public static byte[] des3DecodeCBC(byte[] key, byte[] keyiv, byte[] data) throws Exception {
//        Key deskey = keyGenerator(new String(key));
//        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
//        IvParameterSpec ips = new IvParameterSpec(keyiv);
//        cipher.init(Cipher.DECRYPT_MODE, deskey, ips);
//        byte[] bOut = cipher.doFinal(data);
//        return bOut;
//    }
//
//    public static void main(String[] args) throws Exception {
//        byte[] key = "6C4E60E55552386C759569836DC0F83869836DC0F838C0F7".getBytes();
//        byte[] keyiv = { 1, 2, 3, 4, 5, 6, 7, 8 };
//        byte[] data = "amigoxie".getBytes("UTF-8");
//        System.out.println("data.length=" + data.length);
//        System.out.println("CBC加密解密");
//        byte[] str5 = des3EncodeCBC(key, keyiv, data);
//        System.out.println(new sun.misc.BASE64Encoder().encode(str5));
//
//        byte[] str6 = des3DecodeCBC(key, keyiv, str5);
//        System.out.println(new String(str6, "UTF-8"));
//    }
//}
//
//     测试结果如下所示：
//data.length=8
//CBC加密解密
//-32 6 108 42 24 -112 -66 -34 
//4AZsKhiQvt4=
//amigoxie
//
//     加密解密在线测试网站的3DES可选择CBC模式，无填充方式选项，采用NoPadding填充方式，加密结果如下所示：
//
//
//       ThreeDESUtil的测试代码中打印出的加密后的byte数组为：“-32 6 108 42 24 -112 -66 -34”，正是在线测试网站返回的十六进制“e0   06 6c 2a 18 90 be de”在Java中的十进制表示（Java中byte范围为：-128～127，所以超过127的数会被转换成负数）。
//
//【说明】ThreeDESUtil类中引入的org.bouncycastle.jce.provider.BouncyCastleProvider类在bcprov-jdk16-1.46.jar包中。
//4、AES加密
//
//         待写。
//5、参考文档
//
//         《对称加密算法_百度百科》：http://baike.baidu.com/view/7591.htm?fr=aladdin
//
//         《DES_百度百科》：http://baike.baidu.com/view/7510.htm?fr=aladdin
//
//《加密解密在线测试网站》：http://tripledes.online-domain-tools.com/
//
//《分组对称加密模式:ECB/CBC/CFB/OFB/CTR》：
//
//http://blog.sina.com.cn/s/blog_78efec1501015zfn.html
//
//《密码学 数据块填充模式》：http://laokaddk.blog.51cto.com/368606/461279/
//
//《3DES_百度百科》：
//
//http://baike.baidu.com/link?url=JIAkaazhQoWw1EattGwiAoC2SZjxmkjx-9UTlZGbyTLsDDNizQnElntZSooKckj_
//
// 

 







