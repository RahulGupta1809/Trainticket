package com.cglia.search;
import java.io.*;

@SuppressWarnings("serial")
public class SearchBean  implements Serializable {
	
	private String trainname,startingstation,endingstation,duration,starttime,endtime;
private String trainnumber;
	
	public SearchBean()
	{
	}

	public String getTrainname() {
		return trainname;
	}

	public void setTrainname(String trainname) {
		this.trainname = trainname;
	}

	public String getStartingstation() {
		return startingstation;
	}

	public void setStartingstation(String startingstation) {
		this.startingstation = startingstation;
	}

	public String getEndingstation() {
		return endingstation;
	}

	public void setEndingstation(String endingstation) {
		this.endingstation = endingstation;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getTrainnumber() {
		return trainnumber;
	}

	public void setTrainnumber(String trainnumber) {
		this.trainnumber = trainnumber;
	}


}

	
