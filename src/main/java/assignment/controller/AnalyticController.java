package assignment.controller;

import assignment.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;

@Controller
public class AnalyticController {
    @Autowired
    TransactionService transactionService;

    @GetMapping("/analytic/quarter")
    public String analyticByQuarter(Model model){
        model.addAttribute("data", transactionService.getRevenueByQuarterInYear(2024));
        return "analytic/quarter";
    }

    @GetMapping("/analytic/month")
    public String analyticByMonth(Model model){
        model.addAttribute("data", transactionService.getRevenueByMonthInYear(2024));
        return "analytic/month";
    }


    @GetMapping("/analytic/course")
    public String analyticByCourse(Model model){
        model.addAttribute("data", transactionService.getRevenueByCourse());
        return "analytic/course";
    }
}
