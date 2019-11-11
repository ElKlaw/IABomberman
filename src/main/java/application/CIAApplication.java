package application;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import beans.Inscription;

@SpringBootApplication
@ComponentScan(basePackages="controller")
public class CIAApplication {
	private static final Logger LOG = LogManager.getLogger(CIAApplication.class);
	
	private static final String URL_REGISTER="https://dev-arena.app.norsys.fr/api/challenge/register";
	private static final String ENDPOINT = "https://ciadevarena.app.norsys.fr/api/combat/exec";
	
	public static void main(String[] args) {
		SpringApplication.run(CIAApplication.class, args);
		/*RestTemplate restTemplate = new RestTemplate();
		HttpHeaders httpHeader = new HttpHeaders();
		httpHeader.setContentType(MediaType.APPLICATION_JSON);
		String body = Inscription.toJson();
		LOG.info(Inscription.toJson());
		
		//Inscription
		HttpEntity<String> entity = new HttpEntity<>(body, httpHeader) ;
		
		ResponseEntity<String> response=restTemplate.exchange(URL_REGISTER, HttpMethod.POST, entity, String.class);
		if(response != null) {
			String responseBody = response.getBody();
			JSONObject json = new JSONObject(responseBody);	
			LOG.info("Inscription réussie :"+json.get("id"));
		}	
		
		//Inscription
		RestTemplate restTemplate2 = new RestTemplate();
		HttpHeaders httpHeader2 = new HttpHeaders();
		httpHeader.setContentType(MediaType.APPLICATION_JSON);
		String body2 ="{\"id\":\"5bae16582ab79c0001c9ac04\",\"startDate\":\"2018-09-28T11:54:00Z\",\"endDate\":null,\"level\":3,\"lines\":[{\"tiles\":[{\"type\":\"grass\"},{\"type\":\"grass\",\"player\":{\"competitor\":{\"id\":\"5bacf5062ab79c0001c7c98c\",\"name\":\"CIA\"},\"alive\":true,\"score\":0,\"sonarCoverage\":null,\"sonarBugs\":0,\"sonarVulnerabilities\":0,\"sonarCodeSmells\":0,\"ponderatedScore\":0.0}},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\",\"player\":{\"competitor\":{\"id\":\"5badf95c2ab79c0001c98cc9\",\"name\":\"PurplePanda\"},\"alive\":true,\"score\":0,\"sonarCoverage\":null,\"sonarBugs\":0,\"sonarVulnerabilities\":0,\"sonarCodeSmells\":0,\"ponderatedScore\":0.0}},{\"type\":\"grass\"},{\"type\":\"grass\",\"bomb\":{\"timer\":1,\"radius\":1,\"playerName\":\"PurplePanda\"}},{\"type\":\"grass\"}]},{\"tiles\":[{\"type\":\"grass\",\"bomb\":{\"timer\":1,\"radius\":1,\"playerName\":\"CIA\"}},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"}]},{\"tiles\":[{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"}]},{\"tiles\":[{\"type\":\"grass\",\"bonus\":2},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"}]},{\"tiles\":[{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"}]},{\"tiles\":[{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"wood\",\"bonus\":1},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"wood\"}]},{\"tiles\":[{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"wood\",\"bonus\":1},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"wood\"}]},{\"tiles\":[{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"wood\"}]},{\"tiles\":[{\"type\":\"grass\",\"player\":{\"competitor\":{\"id\":\"5bacf4dd2ab79c0001c7c989\",\"name\":\"Slimbot\"},\"alive\":true,\"score\":0,\"sonarCoverage\":null,\"sonarBugs\":0,\"sonarVulnerabilities\":0,\"sonarCodeSmells\":0,\"ponderatedScore\":0.0},\"bomb\":{\"timer\":1,\"radius\":1,\"playerName\":\"Slimbot\"}},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"grass\",\"bomb\":{\"timer\":1,\"radius\":1,\"playerName\":\"jmartin\"}}]},{\"tiles\":[{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"wood\"},{\"type\":\"grass\"},{\"type\":\"wood\"},{\"type\":\"grass\",\"player\":{\"competitor\":{\"id\":\"5bacf60f2ab79c0001c7c9a2\",\"name\":\"jmartin\"},\"alive\":true,\"score\":0,\"sonarCoverage\":null,\"sonarBugs\":0,\"sonarVulnerabilities\":0,\"sonarCodeSmells\":0,\"ponderatedScore\":0.0}},{\"type\":\"grass\"}]}],\"players\":[]}";
		HttpEntity<String> entity2 = new HttpEntity<>(body2, httpHeader2) ;
				
		ResponseEntity<String> response2=restTemplate2.exchange(ENDPOINT, HttpMethod.POST, entity2, String.class);
		if(response2 != null) {
			String responseBody2 = response2.getBody();
			JSONObject json2 = new JSONObject(responseBody2);	
			LOG.info("Reponse Appel test :"+json2);
		}*/
		
	}
}
