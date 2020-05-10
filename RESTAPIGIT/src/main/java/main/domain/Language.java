package main.domain;

import java.util.List;

public class Language {
    private String language;
    private List<Repo> reposUsingLanguage;
    private int numberRepos;

    public Language() {
    }

    public Language(String language, List<Repo> reposUsingLanguage) {
        this.language = language;
        this.reposUsingLanguage = reposUsingLanguage;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public List<Repo> getReposUsingLanguage() {
        return reposUsingLanguage;
    }

    public void setReposUsingLanguage(List<Repo> reposUsingLanguage) {
        this.reposUsingLanguage = reposUsingLanguage;
    }

    public int getNumberRepos() {
        return numberRepos;
    }

    public void setNumberRepos(int numberRepos) {
        this.numberRepos = numberRepos;
    }
}
