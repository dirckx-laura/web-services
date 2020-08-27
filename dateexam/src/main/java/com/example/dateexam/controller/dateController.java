package com.example.dateexam.controller;


import com.example.dateexam.jpa.DateRepository;
import com.example.dateexam.jpa.IntervalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;


import java.time.Duration;
import java.time.LocalDate;

import java.time.Period;
import java.time.ZoneId;
import java.util.Date;


@Controller
public class dateController {

    @Autowired
    DateRepository dateRepository;

    @GetMapping("/")
    private String getIndex(){

        return "dateForm";
    }

    @GetMapping("/answer")
    private String getAnswer(){

        return "answer";
    }


    @PostMapping("/findInterval")
    public String findInterval(@RequestParam("dateToCheck")
                               @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateToCheck,
                                @RequestParam("startDate")
                                @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
                                @RequestParam("endDate")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate,
                                Model model){
        int result = startDate.compareTo(dateToCheck) * dateToCheck.compareTo(endDate);

        boolean inInterval = false;
        String show = "No";
        if(result > 0){
            inInterval = true;
            show = "Yes";
        }


        long daysBetween = Period.between(LocalDate.now(),dateToCheck.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate()).getDays();

        IntervalDate res = new IntervalDate(dateToCheck,true,daysBetween);

        dateRepository.save(res);


        model.addAttribute("answerVar", show);
        model.addAttribute("daysBetween", daysBetween);
        return "answer";
    }
}

