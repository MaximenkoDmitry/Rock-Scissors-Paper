import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class HMAC {
	String key;

	public HMAC() {
		generateKey();
	}

	private void generateKey(){
		SecureRandom random = new SecureRandom();
		try {
			KeyGenerator keyGen = KeyGenerator.getInstance("AES");
			keyGen.init(random);
			SecretKey secretKey = keyGen.generateKey();
			this.key = new BigInteger(1, secretKey.getEncoded()).toString(16);
		} catch (NoSuchAlgorithmException ex) {
			System.out.print("");
		}
	}

	public String makeHmac(String move){
		String result = key + move;
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] encodedHash = digest.digest(result.getBytes(StandardCharsets.UTF_8));
			result = bytesToHex(encodedHash);
		} catch (NoSuchAlgorithmException ex) {
			System.out.print("");
		}
		return result;
	}

	private static String bytesToHex(byte[] hash) {
		StringBuilder hexString = new StringBuilder(2 * hash.length);
		for (int i = 0; i < hash.length; i++) {
			String hex = Integer.toHexString(0xff & hash[i]);
			if(hex.length() == 1) {
				hexString.append('0');
			}
			hexString.append(hex);
		}
		return hexString.toString();
	}

	public String getKey() {
		return key;
	}
}
