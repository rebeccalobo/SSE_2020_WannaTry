package com.SSE2020.WannaTry.controller;

import com.SSE2020.WannaTry.model.*;
import com.SSE2020.WannaTry.service.BackendRepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;


@Controller
public class GradesController {
    @Autowired
    BackendRepoService backendRepoService;

    @RequestMapping(value = "/grades",method = RequestMethod.GET)
    public String goToGradesPage(Model model){
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDetails.getUser();
        for(Role r : user.getRoles()){
            if(r.getName().equals("ROLE_STAFF")){
               return isStaff(user,model);
            }
        }
       return isStudent(user,model);
        //check roles, if the user is staff goto input page -> if student display his grades
    }



        @RequestMapping(value="/submit_grade",method = RequestMethod.POST)
        public String submitGrade(Model model, @RequestParam("student")int student_id,@RequestParam("module")String module_id,@RequestParam("percentage") double percentage){
                Optional<Grades> optional = backendRepoService.getSmgRepo().getGradeBySandM(student_id,module_id);
                if(optional.isPresent()){
                    Grades newGrade = optional.get();

                    newGrade.setPercentage(percentage);


                    newGrade.setLetter_grade(getLetter(percentage));

                    backendRepoService.getSmgRepo().save(newGrade);
                }else{
                    Grades grade = new Grades(module_id,student_id,percentage,getLetter(percentage),"");
                    backendRepoService.getSmgRepo().save(grade);
                }
               return "redirect:/grades";
    }

    private String getLetter(double percentage){
        String lG = "";

        if(percentage>= 85.00){
            lG = "A";
        }
        else if(percentage >= 70.00 && percentage< 85.00){
            lG = "B";
        }
        else if(percentage >= 55.00 && percentage< 70.00){
            lG = "C";
        }
        else if(percentage >= 40.00 && percentage< 55.00){
            lG = "D";
        }else{
            lG="F";
        }
        return lG;
    }
//    @RequestMapping(value = "/StaffModule")
//    public String getTerminated(Model model){
//        Staff current_staff = CurrentStaffSingleton.getInstance().getCurrentUser();
//        long millis=System.currentTimeMillis();
//        java.sql.Date date=new java.sql.Date(millis);
//
//        HashMap<String, ArrayList<String>> students_enroled_in_module = new HashMap<>();
//        Staff staff = CurrentStaffSingleton.getInstance().getCurrentUser();
//        ArrayList<Modules> modules = backendRepoService.getModuleRepo().getStaffModules(staff.getStaff_id());
//        ArrayList<Modules> terminated = new ArrayList<>();
//        for(Modules module : modules){
//            if(module.getEnd_date().before(date)){
//                terminated.add(module);
//                students_enroled_in_module.put(module.getModule_name(),
//                        backendRepoService.getModuleRepo().getStudentsInSpecificModuleStaff(module.getModule_id(),current_staff.getStaff_id()));
//            }
//        }
//
//        //TODO use rebeccas method of retrieving grades
//        model.addAttribute("hashmap",students_enroled_in_module);
//        model.addAttribute("modules",terminated);
//        model.addAttribute("current_staff", current_staff);
//        return "Grades_and_feedback_input";
//    }

    public String isStudent(User user,Model model){
        model.addAttribute("current_user", user);
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);

        Collection<Grades> grades = backendRepoService.getSmgRepo().getStudentsGrades(user.getID(),date);
        model.addAttribute("grades",grades);
        model.addAttribute("current_user", user);
        model.addAttribute("isStaff",false);
        model.addAttribute("isStudent",true);
        model.addAttribute("flag",false);
        return "StudentGradesAndFeedbackPage";
    }










    public String isStaff(User user,Model model){

        long millis=System.currentTimeMillis();
        java.sql.Date date=new java.sql.Date(millis);

        HashMap<String, ArrayList<Integer>> students_enroled_in_module = new HashMap<>();


        ArrayList<Modules> modules = backendRepoService.getModuleRepo().getStaffModules(user.getID());
        ArrayList<Modules> terminated = new ArrayList<>();


        for(Modules module : modules){
            if(module.getEnd_date().before(date)){
                terminated.add(module);
                students_enroled_in_module.put(module.getModule_id(),
                        backendRepoService.getModuleRepo().getStudentsInSpecificModuleStaff(module.getModule_id(),user.getID()));
            }
        }

        model.addAttribute("hashmap",students_enroled_in_module);
        model.addAttribute("modules",terminated);
        model.addAttribute("current_staff", user);
        model.addAttribute("isStaff",true);
        model.addAttribute("isStudent",false);
        model.addAttribute("flag",false);
        return "Grades_and_feedback_input";
    }
}
