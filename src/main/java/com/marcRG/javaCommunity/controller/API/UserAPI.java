package com.marcRG.javaCommunity.controller.API;

import com.marcRG.javaCommunity.DTO.UserDTO;
import com.marcRG.javaCommunity.auth.AuthenticationRequest;
import com.marcRG.javaCommunity.auth.AuthenticationResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.marcRG.javaCommunity.constants.Utils.APP_ROOT;

@Api(APP_ROOT+"/user")
public interface UserAPI {
    @PostMapping(value = APP_ROOT+"/auth/registerUser", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "enregistrer un utilisateur", notes=" cette methode permet de sauvegarder ou de modifier un utilisateur", response = UserDTO.class)
    @ApiResponses(value={
            @ApiResponse(code = 200, message = "utilisateur enregistrer avec succès ou modifier avec succès!")
    })
    AuthenticationResponse save(@RequestBody UserDTO userDTO);
    @GetMapping(value = APP_ROOT+"/user/findByEmail/{email}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "recherche par mail", notes = "cette methode permet de rechercher un utilsateur par son email",response = UserDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "utilsateur trouvé dans la base de donnée"),
            @ApiResponse(code = 404, message = "aucun utilisateur ne correspond à cette email")
    })
    UserDTO findByEmail(@PathVariable("email") String email);
    @GetMapping(value = APP_ROOT+"/user/findById/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "recherche par id", notes = "cette methode permet de rechercher un utilsateur par son id",response = UserDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "utilsateur trouvé dans la base de donnée"),
            @ApiResponse(code = 404, message = "aucun utilisateur ne correspond à cette id")
    })
    UserDTO findById(@PathVariable("id") Integer id);
    @GetMapping(value = APP_ROOT+"/user/all",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "afficher la liste des utilisateurs" ,notes = "cette methode permet d'afficher la liste de tous les utilisateurs de la base de donnée!",responseContainer = "List<UserDTO>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "liste des utilisateurs")
    })
    List<UserDTO> findAll();
    @GetMapping(value = APP_ROOT+"/user/findByUsername/{Username}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "recherche par mail", notes = "cette methode permet de rechercher un utilsateur par son email",response = UserDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "utilsateur trouvé dans la base de donnée"),
            @ApiResponse(code = 404, message = "aucun utilisateur ne correspond à cette email")
    })
    UserDTO findByUsername(@PathVariable("Username") String Username);

    @PostMapping(value = APP_ROOT+"/auth/authenticate",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "authentifier un utilisateur", notes=" cette methode permet d'authentifier un utilisateur", response = AuthenticationResponse.class)
    @ApiResponses(value={
            @ApiResponse(code = 200, message = "utilisateur authentifier avec succès!")
    })
    AuthenticationResponse authenticate(@RequestBody AuthenticationRequest request);
    @DeleteMapping(value = APP_ROOT+"/user/delete/{id}")
    @ApiOperation(value = "supprimer user", notes = "cette methode permet de supprimer un utilisateur",response = UserDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "utilisateur supprimer avec succès")
    })
    void delete(Integer id);
}
