package ver1;

/**
 * This software takes what was written from data.txt and access the actual page
 * from York University. It returns all the information for a course and puts the 
 * result in a file coursepage.txt. This software accepts any public encryption key
 * without any validation in order to communicate with the web host.  
 * 
 * This software is a work in progress, as each a link to a course needs to be
 * manually updated into the code. Future releases will feature automated link updating.
 * 
 * Group Members: Chris Kerley, Roopdeep Samanta, Charley Hooper and Carlos Lau
 * Feb 21, 2013.
 * York University.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class urlReader {

    public static void main(String[] args) throws Exception {
        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }
            public void checkClientTrusted(X509Certificate[] certs, String authType) {
            }
            public void checkServerTrusted(X509Certificate[] certs, String authType) {
            }
        } };
        // Install the all-trusting trust manager
        final SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, new java.security.SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        // Create all-trusting host name verifier
        HostnameVerifier allHostsValid = new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };

        // Install the all-trusting host verifier
        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);

        URL url = new URL("https://w2prod.sis.yorku.ca/Apps/WebObjects/cdm.woa/25/wo/wElsutaEYENb8gUmh9DoCM/5.1.4.72.0");
        URLConnection con = url.openConnection();
        
        final Reader reader = new InputStreamReader(con.getInputStream());
        final BufferedReader br = new BufferedReader(reader);        
        
		File fileOutput = new File("coursepage.txt");
		PrintWriter out = new PrintWriter(new FileOutputStream(fileOutput));
        
        String line = "";
        while ((line = br.readLine()) != null) 
        {
           
        	System.out.println(line);
        	out.println(line);
        }        
        br.close();
        out.close();
    } // End of main 
} // End of the class //
