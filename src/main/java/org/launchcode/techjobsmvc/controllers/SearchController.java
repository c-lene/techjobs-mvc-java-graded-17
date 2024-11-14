package org.launchcode.techjobsmvc.controllers;

import org.launchcode.techjobsmvc.models.Job;
import org.launchcode.techjobsmvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

import static org.launchcode.techjobsmvc.controllers.ListController.columnChoices;


/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @GetMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }


    // TODO #3 - Create a handler to process a search request and render the updated search view.
    @PostMapping("results")
    public String displaySearchResults(Model model, @RequestParam String searchType, @RequestParam(required = false) String searchTerm) {

        ArrayList<Job> jobs;

        // Uses IF-Else statement to determine where to send the search information
        if (searchTerm.toLowerCase().equals("all") || searchTerm.isEmpty()) {

            // IF - user enters "all" or leaves searchTerm empty, findAll() method is called and stored in 'jobs' ArrayList
            jobs = JobData.findAll();

        } else {
            // ELSE - searchTerm is used in findByColumnAndValue() and stored in 'jobs' ArrayList
            jobs = JobData.findByColumnAndValue(searchType, searchTerm);
        }

        // Passing jobs & ListController.columnChoices into the View
        model.addAttribute("jobs",jobs);
        model.addAttribute("columns",columnChoices);

        return "search";
    }

}

