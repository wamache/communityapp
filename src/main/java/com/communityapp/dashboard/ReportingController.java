package com.communityapp.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class ReportingController {


    @Autowired
    private ReportingService reportingService;

    @GetMapping("/reporting")
    public String reporting() {
        String report = reportingService.generateReport();
        return report;
    }

}

