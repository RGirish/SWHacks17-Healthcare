package com.swhacks;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.swhacks.bean.Diagnosis;
import com.swhacks.bean.Evidence;
import com.swhacks.bean.SecondRequest;
import com.swhacks.bean.Symptom;

@Service
public class SymptomService {
	private static List<Symptom> symptoms = new ArrayList<>();

	@Autowired
	private RestTempleSWHacks<String> restTemplateForString;

	@Autowired
	private RestTempleSWHacks<String> restTemplateDiagnosis;

	@PostConstruct
	public void setUp() {

		RestTemplate restTemplate = new RestTemplate();
		ParameterizedTypeReference<List<Symptom>> responseType = new ParameterizedTypeReference<List<Symptom>>() {
		};
		ResponseEntity<List<Symptom>> response = restTemplate.exchange("https://api.infermedica.com/v2/symptoms",
				HttpMethod.GET, restTemplateForString.getEntity(null), responseType);

		symptoms = response.getBody();

	}

	public List<String> getSymptomsForWords(List<String> words) {
		List<String> sIds = new ArrayList<>();
		for (String sym : words) {
			for (Symptom symptom : symptoms) {
				if (sym.toLowerCase().contains(symptom.getName().toLowerCase())) {
					sIds.add(symptom.getId());
					break;
				}
			}
		}

		return sIds;
	}

	public Diagnosis getDiagnosis(List<String> symptomIds, String sex, String age)
			throws RestClientException, JsonProcessingException {

		SecondRequest sr = new SecondRequest();
		sr.setSex(sex);
		sr.setAge(age);

		List<Evidence> evidences = new ArrayList<>();
		for (String symptomId : symptomIds) {
			Evidence evidence = new Evidence();
			evidence.setChoiceId("present");
			evidence.setId(symptomId);
			evidences.add(evidence);
		}
		
		sr.setEvidences(evidences);

		RestTemplate restTemplate = new RestTemplate();
		ParameterizedTypeReference<Diagnosis> responseType = new ParameterizedTypeReference<Diagnosis>() {
		};

		ObjectMapper mapper = new ObjectMapper();

		ResponseEntity<Diagnosis> response = restTemplate.exchange("https://api.infermedica.com/v2/diagnosis",
				HttpMethod.POST, restTemplateDiagnosis.getEntity(mapper.writeValueAsString(sr)), responseType);
		return response.getBody();
	}
}
