package com.SSE2020.WannaTry.service;

import com.SSE2020.WannaTry.model.Staff;
import com.SSE2020.WannaTry.model.Students;

public class CurrentStaffSingleton {




        private static com.SSE2020.WannaTry.service.CurrentStaffSingleton currentStaffSingleton = null;
        private Staff currentStaff;
        private CurrentStaffSingleton(){
            currentStaff = null;
        }
        public void setCurrentUser(Staff staff){
            currentStaff = staff;
        }
        public static com.SSE2020.WannaTry.service.CurrentStaffSingleton getInstance(){
            if(currentStaffSingleton == null){
                currentStaffSingleton = new CurrentStaffSingleton();
            }
            return currentStaffSingleton;
        }
        public Staff getCurrentUser(){
            return currentStaff;
        }


}
