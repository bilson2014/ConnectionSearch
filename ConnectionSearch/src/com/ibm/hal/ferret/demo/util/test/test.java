package com.ibm.hal.ferret.demo.util.test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * @author bsbsun
 *
 */
public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Client c = Client.create();  
	    String url = "http://localhost:8080/ConnectionSearch/ConnectionSearch/peerNews?PARAM_USERNAME=bsbsun@cn.ibm.com&PARAM_PWD=binsun015";  
//	    try {
//			url += URLEncoder.encode("����", "utf-8");
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}  
	    WebResource r = c.resource(url);  
	    ClientResponse response = r.get(ClientResponse.class);  
	    String entity = response.getEntity(String.class);  
 
            //WebResource r = c.resource(url);  
	    //String entity = r.get(String.class);
 
           //ע�Ͳ�������һ�ַ�ʽ
	    System.out.println(entity);  
	}

}
