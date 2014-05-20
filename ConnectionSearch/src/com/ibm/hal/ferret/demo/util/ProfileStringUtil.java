package com.ibm.hal.ferret.demo.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.horrabin.horrorss.RssFeed;
import org.horrabin.horrorss.RssItemBean;
import org.horrabin.horrorss.RssParser;

import com.ibm.hal.ferret.demo.service.*;
/**
 * @author bsbsun
 * Real time operate profile related resource with the person accessed
 *
 */
public class ProfileStringUtil {

	/**
	 * 
	 * @param s
	 * @param name
	 * @param passwd
	 * @return Return profile lists that are the most visited and its latest reply
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static List<Colleague> getReportingChain(String s, String name, String passwd) throws ClientProtocolException, IOException{
		List<Colleague> lf = new ArrayList<Colleague>();
		
		RssParser rss = new RssParser();

		try{
		        RssFeed feed = rss.loadString(s);
		        
		        // Gets the channel information of the feed and 
		        // display its title
//		        RssChannelBean channel = feed.getChannel();
//		        System.out.println("Feed Title: " + channel.getTitle());
		        
		        // Gets the image of the feed and display the image URL
//		        RssImageBean image = feed.getImage();
//		        System.out.println("Feed Image: " + image.getUrl());
		        
		        // Gets and iterate the items of the feed 
		        List<RssItemBean> items = feed.getItems();
		        for (int i=0; i<items.size(); i++){
		             RssItemBean item = items.get(i); 
		             Colleague f = new Colleague();
		             f.userID = item.getTitle();
		             String link = item.getLink();
		             String id = link.substring(link.length()-36);
		             f.colleagueID = id;
		             System.out.println("Title: " + item.getTitle());
		             System.out.println("Link : " + item.getLink());
		             
		             lf.add(f);
		        }
		        
		}catch(Exception e){
		        // Something to do if an exception occurs
		}
		return lf;
		
	}
		
}
