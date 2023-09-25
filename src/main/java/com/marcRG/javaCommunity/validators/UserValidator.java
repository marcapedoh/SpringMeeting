package com.marcRG.javaCommunity.validators;

import com.marcRG.javaCommunity.DTO.UserDTO;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class UserValidator {
    public static List<String> validate(UserDTO userDTO){
        List<String> errors= new ArrayList<>();
        if(userDTO==null){
            errors.add("vous pouvez pas enregistrer un objet qui n'a pas d'ID");
            errors.add("le champs username est obligatoire!");
            errors.add("tu es d'accord avec moi que tu ne peux pas passer un objet qui n'a pas de mot de passe il va se connecter avec quoi...?");
        }

        /*if(userDTO.getId()==null){
            errors.add("vous pouvez pas enregistrer un objet qui n'a pas d'ID");
        }*/
        if(!StringUtils.hasLength(userDTO.getUsername())){
            errors.add("le champs username est obligatoire!");
        }
        if(!StringUtils.hasLength(userDTO.getMotDePasse())){
            errors.add("tu es d'accord avec moi que tu ne peux pas passer un objet qui n'a pas de mot de passe il va se connecter avec quoi...?");
        }
        return errors;
    }
}
