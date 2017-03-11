package com.swhacks;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.swhacks.bean.DoctorDetails;
import com.swhacks.bean.DoctorSearch;

@Service
public class DoctorSearchService {

	@Value("${doctorKey}")
	private String aKey;

	public ResponseEntity<String> getDoctors(DoctorSearch search)
			throws JsonParseException, JsonMappingException, IOException {
		RestTemplate restTemplate = new RestTemplate();
		search.setUserKey(aKey);
		ResponseEntity<String> response = restTemplate.getForEntity(
				"https://api.betterdoctor.com/2016-03-01/doctors?user_key=" + search.getUserKey() + "&query="
						+ search.getCondition() + "&limit=" + search.getLimit() + "&skip=" + search.getSkip()
						+ "&user_location=" + search.getLocation() + "&sort=distance-asc",
				String.class);
		
		
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
		DoctorDetails data = mapper.readValue(response.getBody(), DoctorDetails.class);
		
		return response;
	}
}
