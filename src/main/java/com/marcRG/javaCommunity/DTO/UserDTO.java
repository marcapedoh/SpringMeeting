package com.marcRG.javaCommunity.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.marcRG.javaCommunity.models.Task;
import com.marcRG.javaCommunity.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    private Integer id;
    private String nom;
    private String prenom;
    private String email;
    @JsonIgnore
    private List<Task> taskList;
    private String username;
    private String motDePasse;

    public static UserDTO fromEntity(User user){
        if(user==null){
            return null;
        }
        return UserDTO.builder()
                .id(user.getId())
                .nom(user.getNom())
                .prenom(user.getPrenom())
                .username(user.getUsername())
                .email(user.getEmail())
                .motDePasse(user.getMotDePasse())
                .build();
    }

    public static User toEntity(UserDTO userDTO){
        if(userDTO==null){
            return null;
        }
        User user=new User();
        user.setId(userDTO.getId());
        user.setNom(userDTO.getNom());
        user.setPrenom(userDTO.getPrenom());
        user.setEmail(userDTO.getEmail());
        user.setMotDePasse(userDTO.getMotDePasse());
        user.setUsername(userDTO.getUsername());
        return user;
    }
}
