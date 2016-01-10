package dinamica.coder;

import static org.junit.Assert.*;

import java.util.Date;

import org.apache.commons.codec.binary.Hex;
import org.junit.Test;

/**
 * 证书操作验证类
 * 
 * @author <a href="mailto:zlex.dongliang@gmail.com">梁栋</a>
 * @version 1.0
 * @since 1.0
 */
public class CertificateCoderTest2 {
	private String certificatePath = "zlex.crt";
	private String keyStorePath = "zlex.keystore";
	private String keyStorePassword = "123456";
	private String aliasPassword = "123456";
	private String alias = "1";

	@Test
	public void test() throws Exception {
		System.err.println("公钥加密——私钥解密");
		String inputStr = "Ceritifcate";
		byte[] data = inputStr.getBytes();

		byte[] encrypt = CertificateCoder2.encryptByPublicKey(data,
				certificatePath);

		byte[] decrypt = CertificateCoder2.decryptByPrivateKey(encrypt,
				keyStorePath, alias, keyStorePassword, aliasPassword);
		String outputStr = new String(decrypt);

		System.err.println("加密前: " + inputStr + "\n\r" + "解密后: " + outputStr);

		// 验证数据一致
		assertArrayEquals(data, decrypt);

		// 验证证书有效
		assertTrue(CertificateCoder2.verifyCertificate(certificatePath));

	}

	@Test
	public void testSign() throws Exception {
		System.err.println("私钥加密——公钥解密");

		String inputStr = "sign";
		byte[] data = inputStr.getBytes();

		byte[] encodedData = CertificateCoder2.encryptByPrivateKey(data,
				keyStorePath, keyStorePassword, alias, aliasPassword);

		byte[] decodedData = CertificateCoder2.decryptByPublicKey(encodedData,
				certificatePath);

		String outputStr = new String(decodedData);
		System.err.println("加密前: " + inputStr + "\n\r" + "解密后: " + outputStr);
		assertEquals(inputStr, outputStr);

		System.err.println("私钥签名——公钥验证签名");
		// 产生签名
		byte[] sign = CertificateCoder2.sign(encodedData, keyStorePath, alias,
				keyStorePassword, aliasPassword);
		System.err.println("签名:\r" + Hex.encodeHexString(sign));

		// 验证签名
		boolean status = CertificateCoder2.verify(encodedData, sign,
				certificatePath);
		System.err.println("状态:\r" + status);
		assertTrue(status);
	}

	@Test
	public void testVerify() throws Exception {
		System.err.println("密钥库证书有效期验证");
		boolean status = CertificateCoder2.verifyCertificate(new Date(),
				keyStorePath, keyStorePassword, alias);
		System.err.println("证书状态:\r" + status);
		assertTrue(status);
	}
}

