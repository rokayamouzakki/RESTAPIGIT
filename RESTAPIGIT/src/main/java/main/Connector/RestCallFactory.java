package main.Connector;

import main.domain.Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class RestCallFactory {

    private RestTemplate restTemplate;

    public static final String endpoint = "https://api.github.com/search/repositories";


    public RestCallFactory() {
        this.restTemplate = new RestTemplate();
    }

    //Consume the api
    public ResponseDto getRepos(String date){
        ResponseEntity<ResponseDto> rateResponse =
                restTemplate.getForEntity(endpoint+"?q=created:>"+date+"&sort=stars&order=desc", ResponseDto.class); // Calling the endpoint
        return rateResponse.getBody();
    }
}
