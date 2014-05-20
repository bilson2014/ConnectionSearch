package com.ibm.hal.ferret.demo.service;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author bsbsun
 *
 */
@XmlRootElement
public class Colleague {
	public String userID;
	public String colleagueID;
	
	public Colleague(){
		
	}
	
	public Colleague(String userID, String colleagueID){
		this.userID = userID;
		this.colleagueID = colleagueID;
	}
}
