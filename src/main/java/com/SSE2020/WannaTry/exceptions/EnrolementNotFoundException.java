package com.SSE2020.WannaTry.exceptions;

public class EnrolementNotFoundException extends Exception {
        private Long module_id;
        public EnrolementNotFoundException(String module_id){
            super(String.format("No enrolment was found with id: %s", module_id));
        }

}
