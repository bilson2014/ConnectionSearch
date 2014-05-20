package com.ibm.hal.ferret.demo.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * 
 * @author bsbsun
 * Utility function sets
 */
public class ConnectionUtil {
	
	/**
	 * 
	 * @param s
	 */
	public static void outPut(String s) {
		System.out.println("------------");
		System.out.println(s);
	}
	
	/**
	 * 
	 * @param name
	 * @param passwd
	 * @return HttpClient instance
	 */
	public static HttpClient setHttpClient(String name, String passwd){
		HttpClient httpclient = new DefaultHttpClient();

		((DefaultHttpClient) httpclient).getCredentialsProvider()
				.setCredentials(
						new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT),
						new UsernamePasswordCredentials(name, passwd));
		return httpclient;
	}
	
	/**
	 * 
	 * @param client
	 * @param url
	 * @return stream from the lotus connection for related request
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	
	public static InputStream getStream(HttpClient client, String url) throws ClientProtocolException, IOException {
		HttpGet httpGet = new HttpGet(url);

		HttpResponse response = null;
		HttpEntity entity = null;
		InputStream is = null;
		response = client.execute(httpGet);
		entity = response.getEntity();
		is = entity.getContent();
//		EntityUtils.consume(entity);
		
		return is;
	}

	/**
	 * 
	 * @param is
	 * @return String value
	 * @throws IOException
	 */
	public static String inputStream2String(InputStream is) throws IOException {
		String s = "";
		
		BufferedReader br=new BufferedReader(new InputStreamReader(is, "UTF-8"));   
		String line=null;   
		while((line=br.readLine())!=null){   
		    s = s + line;
		}   
		br.close();  
		
		return s;
	}
	
}
