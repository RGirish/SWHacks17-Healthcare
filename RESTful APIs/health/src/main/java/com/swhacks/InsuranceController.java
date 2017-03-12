package com.swhacks;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestClientException;

import com.swhacks.bean.Insurer;

@Controller
public class InsuranceController {
	@Autowired
	private InsuranceService iService;

	@GetMapping(value = "/getAllInsurers")
	public ResponseEntity<Insurer[]> getAllInsurers()
			throws RestClientException, JsonParseException, JsonMappingException, IOException {

		return iService.getAllInsurance();
	}

}
