package com.gemography.challenge.svc;

import java.util.Date;

import com.gemography.challenge.model.GithubReposDTO;

public interface ITrendingService {

	/**
	 * List trending repo
	 * @param date date
	 * @return List max size of 100 of trending repo
	 */
	GithubReposDTO getListRepo(Date date);

}
