package com.marcRG.javaCommunity.validators;

import com.marcRG.javaCommunity.DTO.TaskDTO;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class TaskValidator {
    public static List<String> validate(TaskDTO taskDTO){
        List<String> errors=new ArrayList<>();
        if(taskDTO==null){
            errors.add("vous ne pouvez une tache qui à un ID null");
            errors.add("la tache est null");
            errors.add("la description de la tache est obligatoire comment tu veux créer une tache sans dire à quoi tu vas consacrer du temps!");
        }
        /*if(taskDTO.getId()==null){
            errors.add("vous ne pouvez une tache qui à un ID null");
        }*/
        if(!StringUtils.hasLength(taskDTO.getDescription())){
            errors.add("la description de la tache est obligatoire comment tu veux créer une tache sans dire à quoi tu vas consacrer du temps!");
        }
        return errors;
    }
}
