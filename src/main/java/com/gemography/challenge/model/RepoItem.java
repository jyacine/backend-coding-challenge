package com.gemography.challenge.model;

import java.io.Serializable;

public class RepoItem implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -387523637559579608L;

	private final static String LANG_UNDEFINED = "undefined";
	
	private String html_url;
	
	private String language;

	public String getHtml_url() {
		return html_url;
	}

	public void setHtml_url(String html_url) {
		this.html_url = html_url;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		if(language == null) {
			language = LANG_UNDEFINED;
		}
		this.language = language;
	}
}
