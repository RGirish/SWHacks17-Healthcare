package com.swhacks;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestClientException;

import com.swhacks.bean.DoctorSearch;

@Controller
public class DoctorSearchController {

	@Autowired
	private DoctorSearchService doctorService;

	@GetMapping(value = "/getDoctorsAndInsurance")
	public ResponseEntity<String> getSymptomDetails(DoctorSearch doctorSearch)
			throws RestClientException, JsonParseException, JsonMappingException, IOException {
		doctorSearch.setLimit("2");
		doctorSearch.setSkip("0");
		return new ResponseEntity<String>(doctorService.getDoctors(doctorSearch), HttpStatus.OK);
	}
}
