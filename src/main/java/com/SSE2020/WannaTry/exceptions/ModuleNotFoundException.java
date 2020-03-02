package com.SSE2020.WannaTry.exceptions;

public class ModuleNotFoundException extends Exception{
    private Long module_id;
    public ModuleNotFoundException(String module_id){
        super(String.format("No module was found with id: %s", module_id));
    }
}
