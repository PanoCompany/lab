package assignment.controller;

import assignment.config.UserPrincipal;
import assignment.entity.Course;
import assignment.entity.CourseRevenue;
import assignment.entity.Transaction;
import assignment.entity.User;
import assignment.services.CourseService;
import assignment.services.FileUploadService;
import assignment.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class CourseController {
    @Autowired
    CourseService courseService;
    @Autowired
    TransactionService transactionService;
    @Autowired
    FileUploadService fileUploadService;

    @GetMapping("/manager/course")
    public String list(Model model) {
        model.addAttribute("courses", courseService.getAll());
        return "manager/course-list";
    }

    @GetMapping("/manager/course/add")
    public String form(Model model) {
        model.addAttribute("course", new Course());
        return "manager/course-add";
    }

    @PostMapping("/manager/course/add")
    public String create(Course course, @RequestParam("file") MultipartFile file, Principal principal) throws IOException {
        if (file.getOriginalFilename() != "") {
            String path = fileUploadService.upload(file);
            course.setImage(path);
        }

        course.setCreateAt(new Date());
        course.setUpdateAt(new Date());
        UserPrincipal userPrincipal = (UserPrincipal) ((Authentication) principal).getPrincipal();
        course.setCreator(userPrincipal.getUser());
        courseService.save(course);
        return "redirect:/manager/course";
    }

    @GetMapping("/manager/course/edit/{id}")
    public String edit(@PathVariable long id, Model model) {
        Course course = courseService.getById(id);

        model.addAttribute("course", course);
        return "manager/course-edit";
    }


    @GetMapping("/manager/course/delete/{id}")
    public String delete(@PathVariable long id) {
        courseService.deleteById(id);
        return "redirect:/manager/course";
    }

    @GetMapping("/course")
    public String getAllCourse(Model model, Principal principal) {
        UserPrincipal userPrincipal = (UserPrincipal) ((Authentication) principal).getPrincipal();
        User user = userPrincipal.getUser();

        List<Transaction> transactions = transactionService.findAllByBuyer(user);
        List<Course> courses = courseService.getAll();
        List<Course> coursesAvailable = new ArrayList<>();
        for (Course course : courses) {
            boolean isCourseAvailable = true;
            for (Transaction transaction : transactions) {
                if (transaction.getCourse().getId() == course.getId()) {
                    isCourseAvailable = false;
                    break;
                }
            }
            if (isCourseAvailable) {
                coursesAvailable.add(course);
            }
        }

        model.addAttribute("transactions", transactions);
        model.addAttribute("courses", coursesAvailable);

        return "course";
    }

    @GetMapping("/course/buy/{id}")
    public String buyCourse(@PathVariable long id, Principal principal, Model model){
        UserPrincipal userPrincipal = (UserPrincipal) ((Authentication) principal).getPrincipal();
        Course course = courseService.getById(id);

        Transaction transaction = new Transaction();
        transaction.setCourse(course);
        transaction.setPrice(course.getPrice());
        transaction.setCreateAt(new Date());
        transaction.setUpdateAt(new Date());
        transaction.setBuyer(userPrincipal.getUser());
        transaction.setStatus(1);

        transactionService.save(transaction);
        model.addAttribute("course", course);

        return "payment";
    }

}
