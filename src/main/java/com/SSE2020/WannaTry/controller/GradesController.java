package com.SSE2020.WannaTry.controller;

import com.SSE2020.WannaTry.model.*;
import com.SSE2020.WannaTry.service.BackendRepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;


@Controller
public class GradesController {
    @Autowired
    BackendRepoService backendRepoService;

    @RequestMapping(value = "/grades")
    public String goToGradesPage(Model model){
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDetails.getUser();
        for(Role r : user.getRoles()){
            if(r.getName().equals("ROLL_STAFF")){
               return isStaff(user,model);
            }
        }
       return isStudent(user,model);
        //check roles, if the user is staff goto input page -> if student display his grades
    }
        @RequestMapping(value="/submit_grade",method = RequestMethod.POST)
        public void submitGrade(Model model, @ModelAttribute("grades") Grades grade){
                Optional<Grades> optional = backendRepoService.getSmgRepo().getGradeBySandM(grade.getStudent_id(),grade.getModule_id());
                if(optional.isPresent()){
                    Grades newGrade = optional.get();
                    newGrade.setLetter_grade(grade.getLetter_grade());
                    newGrade.setPercentage(grade.getPercentage());
                    newGrade.setModule_id(grade.getModule_id());
                    newGrade.setStudent_id(grade.getStudent_id());
                    backendRepoService.getSmgRepo().save(newGrade);
                }else{
                    backendRepoService.getSmgRepo().save(grade);
                }
            isStaff((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal(),model);
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
                students_enroled_in_module.put(module.getModule_name(),
                        backendRepoService.getModuleRepo().getStudentsInSpecificModuleStaff(module.getModule_id(),user.getID()));
            }
        }

        model.addAttribute("hashmap",students_enroled_in_module);
        model.addAttribute("modules",terminated);
        model.addAttribute("current_staff", user);
        return "Grades_and_feedback_input";
    }
}
