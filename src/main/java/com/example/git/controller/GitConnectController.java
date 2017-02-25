package com.example.git.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.git.model.GistDetail;
import com.example.git.model.GistModel;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class GitConnectController {

	private static final String CLIENT_ID = "dd027905d5db92122d02"; 
	private static final String CLIENT_SECRET = "9ee777c653a284410284194042cf3c30d2813d79";
	private static String token = "";

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String indexDisplay() {
		return "index";
	}

	@RequestMapping(value = "/showGist", method = RequestMethod.GET)
	public String showGist(ModelMap model, @RequestParam String code) {
		try {
			getAuthenticationData(code);
			GistDetail detail = getData();
			model.addAttribute("gistDetail", detail);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return "showGist";
	}

	private void getAuthenticationData(String accessCode) throws IOException {
		try {
			String jsonString = "{ \"client_id\": \"" + CLIENT_ID + "\","
					+ "\"client_secret\": \"" + CLIENT_SECRET + "\",\"code\": \"" + accessCode + "\"}";

			URL url = new URL("https://github.com/login/oauth/access_token");
			HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
			httpCon.setRequestMethod("POST");
			httpCon.setRequestProperty("Content-Type", "application/json");
			httpCon.setDoOutput(true);

			OutputStream out = httpCon.getOutputStream();
			out.write(jsonString.getBytes());
			out.flush();

			BufferedReader br = new BufferedReader(new InputStreamReader(httpCon.getInputStream()));
			String responseString = "";
			String outputString = "";
			while ((responseString = br.readLine()) != null) {
				outputString = outputString + responseString;
			}

			if (outputString != null && !outputString.isEmpty()) {
				String ar = outputString.split("&")[0];
				token = ar.split("access_token=")[1];
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/show", method = RequestMethod.POST)
	public String show(ModelMap model, String fileName, String description) {
		try {
			String inputBody = "{ \"description\": \"" + description + "\",  \"public\": true,"
					+ "\"files\": {   \"" + fileName + "\": {"
					+ "\"content\":\"" + "content" + " \"  } }}";

			URL url = new URL("https://api.github.com/gists");
			HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
			httpCon.setRequestMethod("POST");
			httpCon.setRequestProperty("Content-Type", "application/json");
			httpCon.setRequestProperty("Authorization", "Token " +  token);
			httpCon.setDoOutput(true);

			OutputStream out = httpCon.getOutputStream();
			out.write(inputBody.getBytes());
			out.flush();

			BufferedReader br = new BufferedReader(new InputStreamReader(httpCon.getInputStream()));
			String responseString = "";
			String outputString = "";
			while ((responseString = br.readLine()) != null) {
				outputString = outputString + responseString;
			}
			GistDetail det = getData();
			model.addAttribute("gistDetail", det);

		} catch (Exception ex)     {
			ex.printStackTrace();
		}
		return "showGist";
	}

	@SuppressWarnings("unchecked")
	private GistDetail getData() {
		GistDetail detail = new GistDetail();
		try {
			URL url = new URL("https://api.github.com/gists");
			HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
			httpCon.setRequestMethod("GET");
			httpCon.setRequestProperty("Content-Type", "application/json");
			httpCon.setRequestProperty("Authorization", "Token " +  token);
			httpCon.setDoOutput(true);

			BufferedReader br = new BufferedReader(new InputStreamReader(httpCon.getInputStream()));
			String responseString = "";
			String outputString = "";
			while ((responseString = br.readLine()) != null) {
				outputString = outputString + responseString;
			}

			ObjectMapper mapper = new ObjectMapper();
			List<Map<String, Object>> map = mapper.readValue(outputString,  new TypeReference<List<Map<String,Object>>>() {});
			for (Map<String, Object> m : map) {
				GistModel model = new GistModel();
				model.setDescription(m.get("description") != null ? m.get("description").toString() : "");
				Map<String, Object> child = (Map<String, Object>) m.get("files");
				for (Map.Entry<String, Object> rentry : child.entrySet()) {
					model.setFileName(rentry.getKey());
				}
				detail.getList().add(model);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return detail;
	}
}
