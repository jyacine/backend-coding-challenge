package com.gemography.challenge.svc;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gemography.challenge.model.RepoItem;
import com.gemography.challenge.model.TrendingDTO;
import com.gemography.challenge.model.TrendingResponseDTO;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
	    info = @Info(
	        title = "Github trending repositories",
	        description = "Fetching the most starred repos created in the last 30 days"
	    )
	)
@RestController
@RequestMapping("github-trending")
public class TrendingRestService {
	
	@Autowired
	ITrendingService serviceTrending;
	
	@GetMapping("/all")
    public TrendingResponseDTO getListRepo(@Parameter(allowEmptyValue = false,
    											example = "2021-04-20", description = "date format YYYY-MM-DD")
    								@PathParam("date") @DateTimeFormat(iso = ISO.DATE) Date date) {
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
