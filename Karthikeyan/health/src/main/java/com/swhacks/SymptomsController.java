package com.swhacks;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestClientException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.swhacks.bean.Diagnosis;

@Controller
public class SymptomsController {

	@Autowired
	private SymptomService symptomService;

	@GetMapping(value = "/getDiagnosis")
	public ResponseEntity<Diagnosis> getSymptomDetails(@RequestParam("symptoms") List<String> symptoms)
			throws RestClientException, JsonProcessingException {
		List<String> symptomIds = symptomService.getSymptomsForWords(symptoms);
		Diagnosis dia = symptomService.getDiagnosis(symptomIds);
		return new ResponseEntity<Diagnosis>(dia, HttpStatus.OK);

	}

}
