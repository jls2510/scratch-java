package com.ping23.encryption.rsa_key_pair;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.security.Key;

import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemWriter;

public class PemFile {

	private PemObject pemObject;

	public PemFile(Key key, String description) {
		this.pemObject = new PemObject(description, key.getEncoded());
	}

	public void write(String filename) throws FileNotFoundException, IOException {

		try (PemWriter pemWriter = new PemWriter(new OutputStreamWriter(new FileOutputStream(filename)))) {
			pemWriter.writeObject(this.pemObject);
		}
	}
}
