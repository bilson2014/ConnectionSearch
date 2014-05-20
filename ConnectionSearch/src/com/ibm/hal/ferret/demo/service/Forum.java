package com.ibm.hal.ferret.demo.service;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author bsbsun
 * 
 */
@XmlRootElement
public	class  Forum {

		public String id;
		public String title;
		public String link;
		public String content;
		public String lastupdated;
		
		
		public Forum(){
			
		}
		public Forum(String id, String title,String link,String content, String lastupdated){
			this.id = id;
			this.title =title;
			this.link=link;
			this.content=content;
			this.lastupdated = lastupdated;
		}
	}
