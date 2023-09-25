package com.marcRG.javaCommunity.services.impl;

import com.marcRG.javaCommunity.DTO.TaskDTO;
import com.marcRG.javaCommunity.exception.EntityNotFoundException;
import com.marcRG.javaCommunity.exception.ErrorCodes;
import com.marcRG.javaCommunity.exception.InvalidEntityException;
import com.marcRG.javaCommunity.models.Task;
import com.marcRG.javaCommunity.repository.TaskRepository;
import com.marcRG.javaCommunity.services.TaskServices;
import com.marcRG.javaCommunity.validators.TaskValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@EnableAutoConfiguration
@EnableWebMvc
public class TaskServiceImpl implements TaskServices {
    private TaskRepository taskRepository;
    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public TaskDTO findByDescription(String description) {
        if(!StringUtils.hasLength(description)){
            log.error("la description que vous passer en parametre est null");
            return null;
        }
        Optional<Task> taskDTO= taskRepository.findTaskByDescription(description);
        return taskDTO.map(TaskDTO::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException("tache non trouvé pour cette description", ErrorCodes.Task_Not_Found));
    }

    @Override
    public List<TaskDTO> findAll() {
        return taskRepository.findAll().stream()
                .map(TaskDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if(id==null){
            log.error("vous ne pouvez pas supprimé un objet si pouvez un id null");
        }
        taskRepository.deleteById(id);
    }

    @Override
    public TaskDTO save(TaskDTO taskDTO) {
        List<String> error= TaskValidator.validate(taskDTO);
        if(!error.isEmpty()){
            log.error("votre objet que vous passer");
            throw new InvalidEntityException("vous passer une tache mal formé dans lequel des informations manque",ErrorCodes.Task_Not_Valid,error);
        }
        return TaskDTO.fromEntity(
                taskRepository.save(TaskDTO.toEntity(taskDTO))
        );
    }

    @Override
    public TaskDTO findById(Integer id) {
        if(id==null){
            log.error("vous ne pouvez une tache si vous ne fournissez rien en parametre!");
        }
        Optional<Task> task=taskRepository.findById(id);
        return task.map(TaskDTO::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException("aucune tache ne correspond à cet id passer en parametre!",ErrorCodes.Task_Not_Found));
    }
}
