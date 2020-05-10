package main.service;

import main.Connector.ResponseDto;
import main.Connector.RestCallFactory;
import main.domain.Language;
import main.domain.Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class RepoServiceImpl implements RepoService {

    @Autowired
    RestCallFactory restCallFactory;


    //Return All Repos
    @Override
    public ResponseDto findAll(String date) {
        return restCallFactory.getRepos(date);
    }

    //Get a Repo by id
    @Override
    public Repo findOne(Long id, String date) {
        ResponseDto repos = restCallFactory.getRepos(date);
        Repo repo = new Repo();
        for (Repo r: repos.getItems()) {
            if(id.equals(r.getId())) repo = r;
        }
        return repo;
    }

    //Get list Languages
    @Override
    public LinkedHashSet<Language> languagesRepos(String date) {

        //Calling the connector to get all repos
        ResponseDto repos = restCallFactory.getRepos(date);
        Repo repo = new Repo();
        LinkedHashMap<String, List<Repo>> languagesUsingRepo = new LinkedHashMap();
        LinkedHashSet<Language> languages = new LinkedHashSet<>(); //Languages list
        //Fetching repos
        for (Repo r: repos.getItems()) {
            //Regrouping repos by language
            if(languagesUsingRepo.containsKey(r.getLanguage())){// if the language using in this repo exist in the list add the repo in list of repos using this language
                (languagesUsingRepo.get(r.getLanguage())).add(r);
            }else{// if the language does not exist create new language contains the curent repo
                List<Repo> repoList = new ArrayList<>();
                repoList.add(r);
                languagesUsingRepo.put(r.getLanguage(), repoList);
            }
        }
        //fetch languagesUsingRepo to regroup each language how much repos using it and the list of them
        Iterator i = languagesUsingRepo.keySet().iterator();
        for (Map.Entry<String, List<Repo>> m: languagesUsingRepo.entrySet()) {
            Language language = new Language();
            language.setLanguage(m.getKey());
            language.setReposUsingLanguage(m.getValue());
            language.setNumberRepos(m.getValue().size());
            languages.add(language);
        }
        return languages;
    }

    public RestCallFactory getRestCallFactory() {
        return restCallFactory;
    }

    public void setRestCallFactory(RestCallFactory restCallFactory) {
        this.restCallFactory = restCallFactory;
    }
}
