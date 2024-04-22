package assignment.controller;

import assignment.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
public class AnalyticController {
    @Autowired
    TransactionService transactionService;

    @GetMapping("/analytic/quarter")
    public String analyticByQuarter(Model model, @RequestParam(defaultValue = "2024") String year){
        model.addAttribute("data", transactionService.getRevenueByQuarterInYear(Integer.parseInt(year)));
        model.addAttribute("year", year);
        return "analytic/quarter";
    }

    @GetMapping("/analytic/month")
    public String analyticByMonth(Model model, @RequestParam(defaultValue = "2024") String year){
        model.addAttribute("data", transactionService.getRevenueByMonthInYear(Integer.parseInt(year)));
        model.addAttribute("year", year);
        return "analytic/month";
    }


    @GetMapping("/analytic/course")
    public String analyticByCourse(Model model){
        model.addAttribute("data", transactionService.getRevenueByCourse());
        return "analytic/course";
    }
}
