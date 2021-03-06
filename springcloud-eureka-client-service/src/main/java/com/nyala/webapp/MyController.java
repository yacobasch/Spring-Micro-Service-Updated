package com.nyala.webapp;

import java.net.URI;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MyController {
	
    private final Random random = new Random();
    
	@Autowired DiscoveryClient client;
	
	@Value("${words:}") String[] words;
	
	@GetMapping("/")
	public String getWord() {
		return words[random.nextInt(words.length)];
	}
	
	@GetMapping("/sentence")
	public @ResponseBody String getSentence() {
	  return String.format("%s %s %s %s %s.",
		  getWord("CLIENT-SUBJECT"),
		  getWord("CLIENT-VERB"),
		  getWord("CLIENT-ARTICLE"),
		  getWord("CLIENT-ADJECTIVE"),
		  getWord("CLIENT-NOUN") );
	}

	public String getWord(String service) {
        List<ServiceInstance> list = client.getInstances(service);
        if (list != null && list.size() > 0 ) {
      	URI uri = list.get(0).getUri();
	      	if (uri !=null ) {
	      		return (new RestTemplate()).getForObject(uri,String.class);
	      	}
        }
        return null;
	}
}

