package com.swhacks;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.swhacks.bean.Insurer;

@Service
public class InsuranceService {

	@Value("${doctorKey}")
	private String aKey;

	public ResponseEntity<Insurer[]> getAllInsurance() {
		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<Insurer[]> response = restTemplate
				.getForEntity("https://api.betterdoctor.com/2016-03-01/insurances?user_key=" + aKey,
				Insurer[].class);

		return response;
	}

}
