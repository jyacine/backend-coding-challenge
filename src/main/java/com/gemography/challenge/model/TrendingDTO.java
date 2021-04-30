package com.gemography.challenge.model;

import java.util.ArrayList;
import java.util.List;

public class TrendingDTO {
	
	private String language;
	
	private List<String> urls;

	public int getCount() {
		return urls.size();
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public List<String> getUrls() {
		return urls;
	}

	public void addUrl(String url) {
		if(this.urls == null) 
			this.urls = new ArrayList<String>();
		this.urls.add(url);
	}

}
