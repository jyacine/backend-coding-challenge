package com.gemography.challenge.model;

import java.io.Serializable;
import java.util.List;

public class GithubReposDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8356635616529102923L;
	
	private int total_count;
	
	private boolean incomplete_results;
	
	private List<RepoItem> items;

	public int getTotal_count() {
		return total_count;
	}

	public void setTotal_count(int total_count) {
		this.total_count = total_count;
	}

	public boolean getIncomplete_results() {
		return incomplete_results;
	}

	public void setIncomplete_results(boolean incomplete_results) {
		this.incomplete_results = incomplete_results;
	}

	public List<RepoItem> getItems() {
		return items;
	}

	public void setItems(List<RepoItem> items) {
		this.items = items;
	}

}
