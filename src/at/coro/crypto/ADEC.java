package at.coro.crypto;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * Asymmetric De and EnCoder
 * 
 * @author Coronaxe
 *
 */
public class ADEC {

	public final String version = "0.15";

	private KeyPair keypair;
	private String ALGORITHM = "RSA";

	public ADEC(String algorithm) {
		this.ALGORITHM = algorithm;
	}

	public ADEC() {
	}

	public void generateAsymmetricKeyPair(int keysize) throws NoSuchAlgorithmException, NoSuchProviderException {
		KeyPairGenerator keyGen = KeyPairGenerator.getInstance(this.ALGORITHM);
		keyGen.initialize(keysize);
		this.keypair = keyGen.generateKeyPair();
	}

	public PrivateKey getPrivateKey() {
		return this.keypair.getPrivate();
	}

	public PublicKey getPublicKey() {
		return this.keypair.getPublic();
	}

	/**
	 * 
	 * @param algorithm
	 *            String Algorithm type
	 * 
	 * @deprecated This method has error potential and will be likely removed in
	 *             the next version!
	 */
	public void setAlgorithm(String algorithm) {
		this.ALGORITHM = algorithm;
	}

	public byte[] encryptData(Object data) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
			IllegalBlockSizeException, BadPaddingException {
		// get an RSA cipher object and print the provider
		final Cipher cipher = Cipher.getInstance(this.getPublicKey().getAlgorithm());
		// encrypt the plain text using the public key
		cipher.init(Cipher.ENCRYPT_MODE, this.keypair.getPublic());
		return cipher.doFinal(((String) data).getBytes());
	}

	public byte[] encryptData(Object data, PublicKey encryptionKey) throws NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		// get an RSA cipher object and print the provider
		final Cipher cipher = Cipher.getInstance(encryptionKey.getAlgorithm());
		// encrypt the plain text using the public key
		cipher.init(Cipher.ENCRYPT_MODE, encryptionKey);
		return cipher.doFinal(((String) data).getBytes());
	}

	public Object decryptData(byte[] data) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
			IllegalBlockSizeException, BadPaddingException {
		// get an RSA cipher object and print the provider
		final Cipher cipher = Cipher.getInstance(this.getPrivateKey().getAlgorithm());
		// decrypt the text using the private key
		cipher.init(Cipher.DECRYPT_MODE, this.keypair.getPrivate());
		return cipher.doFinal(data);
	}

	public Object decryptData(byte[] data, PrivateKey customPrivateKey) throws NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		// get an RSA cipher object and print the provider
		final Cipher cipher = Cipher.getInstance(customPrivateKey.getAlgorithm());
		// decrypt the text using the private key
		cipher.init(Cipher.DECRYPT_MODE, customPrivateKey);
		return cipher.doFinal(data);
	}
}