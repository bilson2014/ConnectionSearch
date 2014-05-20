package com.ibm.hal.ferret.demo.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.horrabin.horrorss.RssFeed;
import org.horrabin.horrorss.RssItemBean;
import org.horrabin.horrorss.RssParser;

import com.ibm.hal.ferret.demo.service.*;
/**
 * @author bsbsun
 * Real time operate forms related resource with the person accessed
 *
 */
public class ForumStringUtil {

	/**
	 * 
	 * @param s
	 * @param name
	 * @param passwd
	 * @return Return topic lists that are the most visited and its latest reply
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static List<Forum> getPeerNews(String s, String name, String passwd) throws ClientProtocolException, IOException{
		List<Forum> lf = new ArrayList<Forum>();
		
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
		             Forum f = new Forum();
		             f.title = item.getTitle();
		             f.link = item.getLink();
		             f.content = item.getDescription();
					String id = f.link.substring(f.link.length()-36);
					f.id = id;
//					f.content = GetTopicReply(id, name, passwd );
		             lf.add(f);
		             System.out.println("Title: " + item.getTitle());
		             System.out.println("Link : " + item.getLink());
		             System.out.println("Desc.: " + item.getDescription()); 
		             System.out.println("Data.: " + item.getPubDate()); 
		             System.out.println("ID.: " + id); 
		             
		             
		        }
		        
		}catch(Exception e){
		        // Something to do if an exception occurs
		}
		return lf;
		
		/**
		try{
			int a = s.indexOf("<entry");
			s = s.substring(a);
			s = s.replace("</feed>", "");
			String[] entries = s.split("</entry>");
			
			for(int i=0;i <entries.length ; i++){
				String entry = entries[i];
				Forum f = new Forum();
				int t1 = entry.indexOf("<title type=\"text\">");
				int t2 = entry.indexOf("</title>");
				String title = entry.substring(t1 + 19, t2);
				f.title = title;
				System.out.println(title);
				
				int l = entry.indexOf("https://w3-connections.ibm.com/forums/html/topic?id=");
				String link = entry.substring(l, l + "https://w3-connections.ibm.com/forums/html/topic?id=".length() +36);
				f.link = link;
				System.out.println(link);
				
//				int c1 = entry.indexOf("<content type=\"html\">");
//				int c2 = entry.indexOf("</content>");
				
				String id = link.substring(link.length()-36);
				f.id = id;
//				String content = GetTopicReply(id, name, passwd );
				f.content = "abc";
				
//				int u1 = entry.indexOf("<updated>");
//				System.out.println("u1=" + u1);
//				int u2 = entry.indexOf("</updated>");
//				System.out.println("u2=" + u2);
//				String updated = entry.substring(u1 + 9, u2);
//				System.out.println("---------");
//				System.out.println(updated);
				f.lastupdated = "2013Äê11ÔÂ5ÈÕ 19:07";
				lf.add(f);
			}
		}catch(StringIndexOutOfBoundsException e){
			e.printStackTrace();
		}finally{
			return lf;
		}
		**/
		
	}
	
	/**
	 * 
	 * @param id
	 * @param name
	 * @param passwd
	 * @return Return the latest reply of a given topic
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static String GetTopicReply(String id, String name, String passwd) throws ClientProtocolException, IOException {
		HttpClient client = ConnectionUtil.setHttpClient(name, passwd);
		String content = null;
		InputStream is = ConnectionUtil.getStream(client, ConnectionURI.LC_TOPIC_REPLY_LATEST + id);
		String s = ConnectionUtil.inputStream2String(is);
		RssParser rss = new RssParser();
		try{
			if(s!=null & s.length()>0){
				RssFeed feed = rss.loadString(s);
	        
		        // Gets and iterate the items of the feed 
		        List<RssItemBean> items = feed.getItems();
		             RssItemBean item = items.get(0); 
		             System.out.println("Desc.: " + item.getDescription()); 
		             System.out.println("Data.: " + item.getPubDate()); 
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return content;
		
	}
		
}
