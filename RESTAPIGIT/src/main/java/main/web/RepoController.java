package main.web;

import main.domain.Language;
import main.service.RepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

@Controller
@RequestMapping(value = "/api/repos")
public class RepoController {

    @Autowired
    private RepoService repoService;

    //this ws is used to get list of languages and nbr of repos using this language and the repos
    @RequestMapping(value = "/languages", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Object languagesRepos(@RequestBody String date) {

        //response of this ws
        Map<String, Object> response = new HashMap<String, Object>();

        //lis of languages, each object Language contient the language, the ropos, the nbr
        LinkedHashSet<Language> languages = new LinkedHashSet<>();

        try {
            //Calling the Service
            languages = repoService.languagesRepos(date);
            //Generating the Response
            response.put("languages", languages);
            response.put("success", "true"); //true: to enable control for the frontend
        }catch (Exception e){
            response.put("ErrorMsg", e.toString());
            response.put("success", "false");
        }
        return response;
    }
}
