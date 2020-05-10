package main.service;

import main.Connector.ResponseDto;
import main.domain.Language;
import main.domain.Repo;

import java.util.LinkedHashSet;

public interface RepoService {

    public ResponseDto findAll(String date);
    public Repo findOne(Long id, String date);
    public LinkedHashSet<Language> languagesRepos(String date);
}
