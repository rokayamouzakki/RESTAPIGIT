package main.test;

import main.Connector.ResponseDto;
import main.Connector.RestCallFactory;
import main.domain.Language;
import main.domain.Repo;
import main.service.RepoService;
import main.service.RepoServiceImpl;
import main.web.RepoController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@WebMvcTest(RepoController.class)
public class TestApplication {

    @MockBean
    private RepoService repoService;
    @MockBean
    private RestCallFactory restCallFactory;
    @Mock
    private RestTemplate restTemplate;

    @Autowired
    private MockMvc mvc;

    LinkedHashSet<Language> mockLanguages = new LinkedHashSet<>();

    @Test
    public void listWeb() throws Exception {

        Mockito.when(repoService.languagesRepos(Mockito.anyString())).thenReturn(mockLanguages);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/api/repos/languages"
        ).accept(MediaType.APPLICATION_JSON)
                .content("2020-01-12");

        MvcResult result = mvc.perform(requestBuilder).andReturn();

        System.out.println("***********************************------TEST------*****************************");
        System.out.println(result.getResponse().getStatus());
    }

    @Test
    public void listService() throws Exception {
        Repo repo = new Repo();
        repo.setLanguage("Java");
        repo.setName("testRepo");
        ResponseDto responseDto = new ResponseDto();
        responseDto.setTotal_count(3);
        List<Repo> repoList = new ArrayList<>();
        repoList.add(repo);
        responseDto.setItems(repoList);
        restCallFactory = Mockito.mock(RestCallFactory.class);
        RestTemplate restTemplate = Mockito.mock(RestTemplate.class);
        //repoService = Mockito.mock(RepoService.class);
        ResponseEntity<ResponseDto> responseEntity= Mockito.mock(ResponseEntity.class);
        Mockito
                .when(restTemplate.getForEntity(
                        Mockito.anyString(), Mockito.any()))
          .thenReturn(new ResponseEntity(responseDto, HttpStatus.OK));

        Mockito.when(restCallFactory.getRepos("2020-01-12")).thenReturn(responseDto);
        repoService = new RepoServiceImpl();
        ((RepoServiceImpl) repoService).setRestCallFactory(restCallFactory);
        mockLanguages = repoService.languagesRepos("2020-01-12");
        System.out.println("***********************************------TEST------*****************************");
        System.out.println(mockLanguages.toString());
    }

}
