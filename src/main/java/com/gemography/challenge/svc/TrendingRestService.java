package com.gemography.challenge.svc;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gemography.challenge.model.RepoItem;
import com.gemography.challenge.model.TrendingDTO;
import com.gemography.challenge.model.TrendingResponseDTO;

@RestController
@RequestMapping("github-trending")
public class TrendingRestService {
	
	@Autowired
	ITrendingService serviceTrending;
	
	
	@GetMapping("/all")
    public TrendingResponseDTO getListRepo(@PathParam("date") @DateTimeFormat(pattern = "dd-MM-yyyy") Date date) {
		List<RepoItem> reposItem = serviceTrending.getListRepo(date).getItems();
		return new TrendingResponseDTO(reposItem.stream().map(RepoItem::getLanguage)
				.distinct()
				.map(r1 -> {
					TrendingDTO trending = new TrendingDTO();
					trending.setLanguage(r1);
					reposItem.stream()
						.filter(r2 -> r1.equals(r2.getLanguage()))
						.forEach(r2 -> trending.addUrl(r2.getHtml_url()));
					return trending;
				})
				.collect(Collectors.toList()));
		
    }

}
