package dicode;

import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Encrypt {

	private static final String salt = "saltIsGoodForYou";
	private static final String key = "keyForDicodeRest";

	public static byte[] encrypt(byte[] inputTxt, String encoding) throws Exception {

	    Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
	    byte[] saltBytes = salt.getBytes(encoding);
	    byte[] keyBytes = key.getBytes(encoding);
	    SecretKeySpec skeySpec = new SecretKeySpec(keyBytes, "AES");
	    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");
	    //Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
	    IvParameterSpec iv = new IvParameterSpec( saltBytes );

	    cipher.init(Cipher.ENCRYPT_MODE, skeySpec,iv);
	    return cipher.doFinal(inputTxt);
	  }
	
}
