package com.marcRG.javaCommunity.controller.API;

import com.marcRG.javaCommunity.DTO.TaskDTO;
import com.marcRG.javaCommunity.DTO.UserDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.marcRG.javaCommunity.constants.Utils.APP_ROOT;
@Api(value = APP_ROOT+"/task")
public interface TaskAPI {
    @PostMapping(value = APP_ROOT+"/task/create", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "enregistrer une tache", notes=" cette methode permet de sauvegarder ou de modifier une tache", response = TaskDTO.class)
    @ApiResponses(value={
            @ApiResponse(code = 200, message = "tache enregistrer avec succès ou modifier avec succès!")
    })
    TaskDTO save(@RequestBody TaskDTO taskDTO);
    @GetMapping(value = APP_ROOT+"/task/findById/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "recherche par id", notes = "cette methode permet de rechercher un tache par son id",response = TaskDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "tache trouvé dans la base de donnée"),
            @ApiResponse(code = 404, message = "aucune tache ne correspond à cette id")
    })
    TaskDTO findById(@PathVariable("id") Integer id);
    @GetMapping(value = APP_ROOT+"/task/findByDescription/{description}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "recherche par description", notes = "cette methode permet de rechercher une tache par sa description",response = TaskDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "tache trouvé dans la base de donnée"),
            @ApiResponse(code = 404, message = "aucune tache ne correspond à cette description")
    })
    TaskDTO findByDescription(@PathVariable("description") String description);
    @GetMapping(value = APP_ROOT+"/task/all",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "afficher la liste des taches" ,notes = "cette methode permet d'afficher la liste de tous les taches de la base de donnée!",responseContainer = "List<TaskDTO>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "liste des tachse")
    })
    List<TaskDTO> findAll();
    @DeleteMapping(value = APP_ROOT+"/task/delete/{id}")
    @ApiOperation(value = "supprimer task", notes = "cette methode permet de supprimer une tache",response = TaskDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "tache supprimer avec succès")
    })
    void delete(Integer id);
}
