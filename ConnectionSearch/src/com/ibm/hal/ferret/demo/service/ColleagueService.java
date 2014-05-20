package com.ibm.hal.ferret.demo.service;
/**
 * 
 * @author james
 *
 */

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;

import com.ibm.hal.ferret.demo.util.*;

/**
 * @author bsbsun
 *
 */
@Path("/reportingChain")
public class ColleagueService {
	
	@Context HttpServletRequest request ;
	@Context HttpServletResponse response;
	
	//Return json object to the caller
	@GET
	@Produces( {MediaType.APPLICATION_JSON })
	public List<Colleague> getReportingChain() throws ClientProtocolException, IOException{
		String name = request.getParameter("PARAM_USERNAME");
		String passwd = request.getParameter("PARAM_PWD");
		HttpClient client = ConnectionUtil.setHttpClient(name, passwd);
		InputStream is = ConnectionUtil.getStream(client, ConnectionURI.LC_PROFILE_REPORT_CHAIN+name);
		String s = ConnectionUtil.inputStream2String(is);
		if(s!= null & s.length()>0){
			return ProfileStringUtil.getReportingChain(s, name, passwd);
		}else
			return null;
	}
		
}
