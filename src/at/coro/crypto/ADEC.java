package at.coro.crypto;

import java.security.InvalidKeyException;
import java.security.KeyException;
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

	public final String version = "0.1";

	private KeyPair keypair;
	private PublicKey publicKey;
	private PrivateKey privateKey;
	private String ALGORITHM = "RSA";

	public ADEC(String algorithm, int keysize) throws KeyException,
			NoSuchAlgorithmException, NoSuchProviderException {
		this.ALGORITHM = algorithm;
		generateAsymmetricKeyPair(keysize);
	}

	public ADEC(int keysize) throws KeyException, NoSuchAlgorithmException,
			NoSuchProviderException {
		generateAsymmetricKeyPair(keysize);
	}

	public ADEC() throws KeyException, NoSuchAlgorithmException,
			NoSuchProviderException {
		generateAsymmetricKeyPair(512);
	}

	private void generateAsymmetricKeyPair(int keysize)
			throws NoSuchAlgorithmException, NoSuchProviderException {
		KeyPairGenerator keyGen = KeyPairGenerator.getInstance(this.ALGORITHM);
		keyGen.initialize(keysize);
		this.keypair = keyGen.generateKeyPair();
		this.publicKey = this.keypair.getPublic();
		this.privateKey = this.keypair.getPrivate();
	}

	public PrivateKey privateKey() {
		return this.privateKey;
	}

	public PublicKey publicKey() {
		return this.publicKey;
	}

	public void setAlgorithm(String algorithm) {
		this.ALGORITHM = algorithm;
	}

	/**
	 * Encrypt the plain text using public key.
	 * 
	 * @param text
	 *            : original plain text
	 * @param key
	 *            :The public key
	 * @return Encrypted text
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws java.lang.Exception
	 */
	public byte[] encryptString(String text) throws NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidKeyException,
			IllegalBlockSizeException, BadPaddingException {
		byte[] cipherText = null;
		// get an RSA cipher object and print the provider
		final Cipher cipher = Cipher.getInstance(this.ALGORITHM);
		// encrypt the plain text using the public key
		cipher.init(Cipher.ENCRYPT_MODE, this.publicKey);
		cipherText = cipher.doFinal(text.getBytes());
		return cipherText;
	}

	public byte[] encryptString(PublicKey encryptionKey, String text)
			throws InvalidKeyException, IllegalBlockSizeException,
			BadPaddingException, NoSuchAlgorithmException,
			NoSuchPaddingException {
		byte[] cipherText = null;
		// get an RSA cipher object and print the provider
		final Cipher cipher = Cipher.getInstance(encryptionKey.getAlgorithm());
		// encrypt the plain text using the public key
		cipher.init(Cipher.ENCRYPT_MODE, encryptionKey);
		cipherText = cipher.doFinal(text.getBytes());
		return cipherText;
	}

	/**
	 * Decrypt text using private key.
	 * 
	 * @param text
	 *            :encrypted text
	 * @param key
	 *            :The private key
	 * @return plain text
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws java.lang.Exception
	 */
	public String decryptString(byte[] text) throws NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidKeyException,
			IllegalBlockSizeException, BadPaddingException {
		byte[] dectyptedText = null;

		// get an RSA cipher object and print the provider
		final Cipher cipher = Cipher.getInstance(this.ALGORITHM);

		// decrypt the text using the private key
		cipher.init(Cipher.DECRYPT_MODE, this.privateKey);
		dectyptedText = cipher.doFinal(text);

		return new String(dectyptedText);
	}

}
