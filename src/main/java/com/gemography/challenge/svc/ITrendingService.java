package com.gemography.challenge.svc;

import java.util.Date;
import java.util.List;

public interface ITrendingService {

	/**
	 * List trending repo
	 * @param lang code langage
	 * @param date date
	 * @return List max size of 100 of trending repo
	 */
	List<String> getListRepo(String lang, Date date);

}
