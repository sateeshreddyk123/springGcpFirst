package com.example.standalone.springbootgcp;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import org.springframework.http.MediaType;
import javax.net.ssl.SSLContext;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.HttpEntity;
import java.net.HttpURLConnection;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class AuthenticationController {

	@GetMapping("/getAccessToken")
	public String getAccessToken() {
		System.out.println("getAccessToken  start 6");
		String user = "353a21cf-ef69-4490-819b-a0d27bdaf7bd";
		String Pass = "y1/TOkyZbXG91Vdo/J+2eRxcF2GjNqPO1OqZshPLvk65WOzggk4shxhW6JKbOtfQbZXTK8DRVoZdnKtfBpnQ/TvZAyY0zj8Ct4q3F10ftdw=";
		StringBuffer sb = new StringBuffer();
		sb.append(user);
		sb.append(":");
		sb.append(Pass);

		byte[] plainCredsBytes = sb.toString().getBytes();
		byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
		String base64Creds = new String(base64CredsBytes);
		HttpHeaders headers = new HttpHeaders();
//		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
	//	headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Authorization", "Basic " + base64Creds);

		// Create Request Body

		MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<String, String>();
		requestBody.add("grant_type", "password");
		requestBody.add("scope", "api");
		requestBody.add("username", "eac1ecc4-e3e1-4fe4-9a0d-971d0928704d");
		requestBody.add("password","dxNheg9933q4uw4Zd0Q9e8/0ziX43xm+D5wSwunDou664ds1MH124h7N7WrwhexhBOhEmLfS2jvCg3jKHCxMpcfEVeEFOz8xdlTT8cqSVCk=");
		HttpEntity<MultiValueMap<String, String>> formEntity = new HttpEntity<MultiValueMap<String, String>>(requestBody, headers);
		String response = null;
		RestTemplate restTemplate =null;

		try {
			restTemplate = new RestTemplate(getHttpComponentsClientHttpRequestFactory());
			// restTemplate = new RestTemplate(clientHttpRequestFactory());
			response = restTemplate.postForObject("https://api.cloudcms.com/oauth/token", formEntity, String.class);
			System.out.println("response  :" + response.toString());

			// jsonResponse = new JSONObject(response);

			System.out.println("getAccessToken  end ");

		} catch (Exception e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}

		return response;

	}
	
	private HttpComponentsClientHttpRequestFactory getHttpComponentsClientHttpRequestFactory()throws NoSuchAlgorithmException, KeyManagementException {
		SSLContext context = SSLContext.getInstance("TLSv1.2");
		context.init(null, null, null);
		CloseableHttpClient httpClient = HttpClientBuilder.create().setSslcontext(context).build();
		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);

		return factory;

	}

}
