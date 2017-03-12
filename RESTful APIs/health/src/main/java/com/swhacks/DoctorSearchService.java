package com.swhacks;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.swhacks.bean.Data;
import com.swhacks.bean.DoctorDetails;
import com.swhacks.bean.DoctorInfo;
import com.swhacks.bean.DoctorSearch;
import com.swhacks.bean.Insurance;
import com.swhacks.bean.InsurancePlan;
import com.swhacks.bean.Practice;

@Service
public class DoctorSearchService {

	@Value("${doctorKey}")
	private String aKey;

	public String getDoctors(DoctorSearch search)
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

		DoctorDetails doctorDetails = mapper.readValue(response.getBody(), DoctorDetails.class);

		// Construct a map that contains the insurance id and their names as
		// values
		Map<String, String> insuranceDetails = new HashMap<>();
		for (Data data : doctorDetails.getData()) {
			for (Insurance insuranc : data.getInsurances()) {
				InsurancePlan ip = insuranc.getInsurancePlan();
				insuranceDetails.put(ip.getUid(), ip.getName());
			}
		}

		List<Practice> practices = new ArrayList<>();

		for (Data data : doctorDetails.getData()) {
			List<Practice> ps = data.getPractices();
			for (Practice p : ps) {
				List<String> insuranceNames = new ArrayList<>();
				for (String insuranceId : p.getInsuranceUids()) {
					insuranceNames.add(insuranceDetails.get(insuranceId));
				}
				p.setInsuranceData(insuranceNames);
				p.setInsuranceUids(null);
				practices.add(p);
			}
		}

		DoctorInfo doctorInfo = new DoctorInfo();
		doctorInfo.setPractices(practices);
		return mapper.writeValueAsString(doctorInfo);
	}
}
