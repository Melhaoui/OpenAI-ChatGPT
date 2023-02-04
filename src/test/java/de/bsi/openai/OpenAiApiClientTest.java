package de.bsi.openai;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpResponse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.util.ReflectionTestUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.bsi.openai.chatgpt.CompletionRequest;

public class OpenAiApiClientTest {
	
	@InjectMocks
	OpenAiApiClient openAiAPIClient;
	
	@Before
	public void setUp() throws Exception {
		openAiAPIClient = new OpenAiApiClient();
		ReflectionTestUtils.setField(openAiAPIClient, "openaiApiUrl", URI.create("https://api.openai.com/v1/completions"));
		ReflectionTestUtils.setField(openAiAPIClient, "openaiApiKey", "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");

	}

	@After
	public void tearDown() throws Exception {
		openAiAPIClient = null;
	}
	
	@Test
	public void testPostToOpenAiApi() throws InterruptedException {
		HttpResponse<String> result = null;
		try {
			String message = "Hello?";
			var completion = CompletionRequest.defaultWith(message);
			System.out.println("*****************");
			System.out.println(completion);
			ObjectMapper mapper = new ObjectMapper();
			var postBodyJson = mapper.writeValueAsString(completion);
			//var postBodyJson = jsonMapper.writeValueAsString(completion);
			System.out.println("+++++++++++++++++");
			System.out.println(postBodyJson);
			result = openAiAPIClient.postToOpenAiApi(postBodyJson);
			System.out.println("-------------------");
			//System.out.println(result);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(200, 200);
	}
	
}
