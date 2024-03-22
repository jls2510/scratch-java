package com.ping23.scratch.http;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

public class HttpURLConnectionProvider {

    /**
     * getHttpURLConnection
     *
     * @param endpoint
     * @return
     * @throws IOException
     */
    public static HttpURLConnection getHttpURLConnection(final String endpoint) throws IOException {

        HttpURLConnection connection = null;

        final URL url = new URL(endpoint);

        if (endpoint.contains("https")) {
            connection = (HttpsURLConnection) url.openConnection();

            // configure connection to trust specific self-signed certificates
            if (endpoint.contains("capella")) {
                final SSLContext sslContext = HttpURLConnectionProvider
                        .getTrustStoreSSLContext("src/main/resources/ssl_cert/capella.cert");
                ((HttpsURLConnection) connection).setSSLSocketFactory(sslContext.getSocketFactory());
            }

        } else {
            connection = (HttpURLConnection) url.openConnection();

        }

        return connection;
    }

    /**
     * getTrustStoreSSLContext
     *
     * @param certificateFilename
     * @return the SSLContext that "trusts" the given certificate
     *
     *         NOTE: the following line will need to be added to consuming code in
     *         order to configure the HttpURLConnection instance:
     *         connection.setSSLSocketFactory(sslContext.getSocketFactory());
     */
    private static SSLContext getTrustStoreSSLContext(final String certificateFilename) {
        final File crtFile = new File(certificateFilename);
        Certificate certificate;
        SSLContext sslContext = null;

        try {
            certificate = CertificateFactory.getInstance("X.509").generateCertificate(new FileInputStream(crtFile));
            // Or if the crt-file is packaged into a jar file:
            // CertificateFactory.getInstance("X.509").generateCertificate(this.class.getClassLoader().getResourceAsStream("server.crt"));

            final KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null, null);
            keyStore.setCertificateEntry("server", certificate);

            final TrustManagerFactory trustManagerFactory = TrustManagerFactory
                    .getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);

            sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustManagerFactory.getTrustManagers(), null);

        } catch (CertificateException | IOException | KeyStoreException | NoSuchAlgorithmException
                | KeyManagementException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return sslContext;

    }

}
