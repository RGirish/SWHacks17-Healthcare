package com.swhacks;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class RestTempleSWHacks<T> {

	@Value("${appId}")
	private String aId;

	@Value("${appKey}")
	private String aKey;

	public HttpEntity<T> getEntity(T postObject) {

		List<String> appId = new ArrayList<>();
		List<String> appKey = new ArrayList<>();

		appId.add(aId);
		appKey.add(aKey);

		HttpHeaders headers = new HttpHeaders();

		headers.setContentType(MediaType.APPLICATION_JSON);

		headers.put("App-Id", appId);
		headers.put("App-Key", appKey);
		if (postObject != null) {
			HttpEntity<T> entity = new HttpEntity<T>(postObject, headers);
			return entity;
		}

		return new HttpEntity<T>(headers);

	}
}
