package com.ping23.encryption.pk3;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

public class PKGenerateKeys {

	private KeyPairGenerator keyGen;
	private KeyPair pair;
	private PrivateKey privateKey;
	private PublicKey publicKey;
	public final static String RESOURCES_DIR = "src/main/resources";

	public PKGenerateKeys(int keylength) throws NoSuchAlgorithmException {
		this.keyGen = KeyPairGenerator.getInstance("RSA");
		this.keyGen.initialize(keylength);
	}

	public void createKeys() {
		this.pair = this.keyGen.generateKeyPair();
		this.privateKey = pair.getPrivate();
		this.publicKey = pair.getPublic();
	}

	public PrivateKey getPrivateKey() {
		return this.privateKey;
	}

	public PublicKey getPublicKey() {
		return this.publicKey;
	}

	public static void writeToFile(String path, byte[] key) throws IOException {
		File f = new File(path);
		f.getParentFile().mkdirs();

		try (FileOutputStream fos = new FileOutputStream(f)) {
			fos.write(key);
			fos.flush();
			fos.close();
		}
	}

	public static void main(String[] args) {
		PKGenerateKeys gk;
		try {
			gk = new PKGenerateKeys(4096);
			gk.createKeys();
			gk.writeToFile(RESOURCES_DIR + "/KeyPair/publicKey", gk.getPublicKey().getEncoded());
			gk.writeToFile(RESOURCES_DIR + "/KeyPair/privateKey", gk.getPrivateKey().getEncoded());
		} catch (NoSuchAlgorithmException e) {
			System.err.println(e.getMessage());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}

	}

}
