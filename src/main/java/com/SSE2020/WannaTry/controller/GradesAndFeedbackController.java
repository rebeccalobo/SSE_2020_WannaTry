package com.SSE2020.WannaTry.controller;

import com.SSE2020.WannaTry.exceptions.StudentGradesNotFoundException;
import com.SSE2020.WannaTry.exceptions.StudentNotFoundException;
import com.SSE2020.WannaTry.model.Modules;
import com.SSE2020.WannaTry.model.Staff;
import com.SSE2020.WannaTry.model.StudentModuleGrades;
import com.SSE2020.WannaTry.service.BackendRepoService;
import com.SSE2020.WannaTry.service.CurrentStaffSingleton;
import com.SSE2020.WannaTry.service.CurrentUserSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;


@Controller
public class GradesAndFeedbackController {
    @Autowired
    BackendRepoService backendRepoService;

    @RequestMapping(value = "/StudentGradesAndFeedbackPage")
    public String goToGradesPage(Model model){
        model.addAttribute("current_user", CurrentUserSingleton.getInstance().getCurrentUser());
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        ArrayList<StudentModuleGrades> grades = backendRepoService.getSmgRepo().getGrades(CurrentUserSingleton.getInstance().getCurrentUser().getStudent_id(), date);
        model.addAttribute("grades",grades);
        model.addAttribute("current_user", CurrentUserSingleton.getInstance().getCurrentUser());
        return "StudentGradesAndFeedbackPage";
    }
        @RequestMapping(value="/submit_grade",method = RequestMethod.POST)
        public String submitGrade(
                @RequestParam("module")String m_id,
                @RequestParam("Percentage")String percentage,
                @RequestParam("Letter")String letter,
                @RequestParam("student")String id){
            System.out.println(m_id);
            System.out.println(id);
            System.out.println(percentage);
            System.out.println(letter);
            try {
                StudentModuleGrades studentModuleGrades = backendRepoService.getSmgRepo()
                        .findById(m_id+"/"+id).orElseThrow(()->new StudentGradesNotFoundException(m_id+"/"+id));
                studentModuleGrades.setLetter_grade(letter);
                studentModuleGrades.setPercentage(Double.parseDouble(percentage));
                studentModuleGrades.setModule_id(m_id);
                studentModuleGrades.setStudent_id(id);
                studentModuleGrades.setRelation_id(m_id+"/"+id);
                backendRepoService.getSmgRepo().save(studentModuleGrades);
            } catch (StudentGradesNotFoundException e) {
                StudentModuleGrades studentModuleGrades = new StudentModuleGrades( m_id, id, Double.valueOf(percentage), letter,m_id+"/"+id);
                backendRepoService.getSmgRepo().save(studentModuleGrades);
                e.printStackTrace();
            }

            return "redirect:/StaffModule";
    }


    @RequestMapping(value = "/StaffModule")
    public String getTerminated(Model model){
        Staff current_staff = CurrentStaffSingleton.getInstance().getCurrentUser();
        long millis=System.currentTimeMillis();
        java.sql.Date date=new java.sql.Date(millis);

        HashMap<String, ArrayList<String>> students_enroled_in_module = new HashMap<>();
        Staff staff = CurrentStaffSingleton.getInstance().getCurrentUser();
        ArrayList<Modules> modules = backendRepoService.getModuleRepo().getStaffModules(staff.getStaff_id());
        ArrayList<Modules> terminated = new ArrayList<>();
        for(Modules module : modules){
            if(module.getEnd_date().before(date)){
                terminated.add(module);
                students_enroled_in_module.put(module.getModule_name(),
                        backendRepoService.getModuleRepo().getStudentsInSpecificModuleStaff(module.getModule_id(),current_staff.getStaff_id()));
            }
        }

        //TODO use rebeccas method of retrieving grades
        model.addAttribute("hashmap",students_enroled_in_module);
        model.addAttribute("modules",terminated);
        model.addAttribute("current_staff", current_staff);
        return "Grades_and_feedback_input";
    }
}
