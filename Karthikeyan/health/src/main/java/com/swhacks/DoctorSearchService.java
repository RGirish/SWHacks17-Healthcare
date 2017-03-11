package com.swhacks;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.swhacks.bean.DoctorSearch;

@Service
public class DoctorSearchService {

	@Value("${doctorKey}")
	private String aKey;

	public ResponseEntity<String> getDoctors(DoctorSearch search) {
		RestTemplate restTemplate = new RestTemplate();
		search.setUserKey(aKey);
		return restTemplate.getForEntity(
				"https://api.betterdoctor.com/2016-03-01/doctors?user_key=" + search.getUserKey() + "&query="
						+ search.getQuery() + "&limit=" + search.getLimit() + "&skip=" + search.getSkip(),
				String.class);
	}
}
