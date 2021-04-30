package com.gemography.challenge.model;

import java.util.List;

public class TrendingResponseDTO {
	
	private List<TrendingDTO> trendingDTO;
	
	private int totalCount;
	
	public TrendingResponseDTO(List<TrendingDTO> trendingDTO) {
		setTrendingDTO(trendingDTO);
	}

	public List<TrendingDTO> getTrendingDTO() {
		return trendingDTO;
	}

	public void setTrendingDTO(List<TrendingDTO> trendingDTO) {
		this.trendingDTO = trendingDTO;
		totalCount = this.trendingDTO.parallelStream()
				.mapToInt(TrendingDTO::getCount)
				.sum();
	}

	public int getTotalCount() {
		return totalCount;
	}
}
