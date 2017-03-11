package com.swhacks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestClientException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.swhacks.bean.DoctorSearch;

@Controller
public class DoctorSearchController {

	@Autowired
	private DoctorSearchService doctorService;

	@GetMapping(value = "/getDoctorsAndInsurance")
	public ResponseEntity<String> getSymptomDetails(DoctorSearch doctorSearch)
			throws RestClientException, JsonProcessingException {
		doctorSearch.setLimit("10");
		doctorSearch.setSkip("0");
		return doctorService.getDoctors(doctorSearch);
	}
}
