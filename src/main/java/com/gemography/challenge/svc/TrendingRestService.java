package com.gemography.challenge.svc;

import java.util.Date;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController("github-treding")
public class TrendingRestService {
	
	@Autowired
	ITrendingService serviceTrending;
	
	@GetMapping("/{lang}")
    public List<String> getListRepo(@PathVariable String lang,@PathParam(value = "date") Date date) {
		return serviceTrending.getListRepo(lang,date);
    }
	
	@GetMapping("/{lang}/size")
    public int getSizeRepo(@PathVariable String lang,@PathParam(value = "date") Date date) {
		List<String> listRepo = serviceTrending.getListRepo(lang,date);
		return listRepo != null? listRepo.size():0;
    }

}
