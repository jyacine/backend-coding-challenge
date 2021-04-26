package com.gemography.challenge.svc;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TrendingService implements ITrendingService {

	@Override
	public List<String> getListRepo(String lang, Date date) {
		return null;
	}

}
