package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

    // TODO #1 - Create handler to process search request and display results


    @RequestMapping(value = "results")
    public String results(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {
        model.addAttribute("columns", ListController.columnChoices);

        //if you want to search through all of the columnChoices by a certain search term
        if (searchType.equals("all")) {

            //initialize result variable
            ArrayList<HashMap<String, String>> jobs = JobData.findByValue(searchTerm);

            //create variable for result size to use in template
            String result = jobs.size() + " Result(s)";
            model.addAttribute("jobs", jobs);
            model.addAttribute("searchTerm", searchTerm);
            model.addAttribute("result", result);

        }

        //searches for given search term through collumn of choice (searchType)
        else {
            ArrayList<HashMap<String, String>> jobs = JobData.findByColumnAndValue(searchType, searchTerm);
            String result = jobs.size() + " Result(s)";
            model.addAttribute("jobs", jobs);
            model.addAttribute("searchTerm", searchTerm);
            model.addAttribute("result", result);

        }

        //renders the search.html template
        return "search";

    }
}



