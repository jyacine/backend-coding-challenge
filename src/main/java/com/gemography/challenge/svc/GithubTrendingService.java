package com.gemography.challenge.svc;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.gemography.challenge.model.GithubReposDTO;

@Service
public class GithubTrendingService implements ITrendingService {

	
	@Autowired
	RestTemplate restTemplate;
	
	@Value("${coding.challenge.github.nb_items}")
	private int nb_items; 
	
	private static final String githubUrl = "https://api.github.com/search/repositories"
			+ " ?q=created:>{date}&sort=stars&order=desc";
	
	
	@Override
	public GithubReposDTO getListRepo(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");		
		Map<String, String> uriParams = new HashMap<String, String>();
		uriParams.put("date", sdf.format(date));
		GithubReposDTO response = restTemplate.getForObject(githubUrl+"&per_page="+String.valueOf(nb_items), GithubReposDTO.class,uriParams); 
		return response;
	}

}
