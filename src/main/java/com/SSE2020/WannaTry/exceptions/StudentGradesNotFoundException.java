package com.SSE2020.WannaTry.exceptions;

public class StudentGradesNotFoundException extends Exception {

        private Long module_id;
        public StudentGradesNotFoundException(String module_id) {
            super(String.format("No module was found with id: %s", module_id));

        }

}
