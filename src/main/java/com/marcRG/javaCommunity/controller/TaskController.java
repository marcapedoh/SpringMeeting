package com.marcRG.javaCommunity.controller;

import com.marcRG.javaCommunity.DTO.TaskDTO;
import com.marcRG.javaCommunity.controller.API.TaskAPI;
import com.marcRG.javaCommunity.services.TaskServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

@CrossOrigin(origins = "*")
public class TaskController implements TaskAPI {
    private TaskServices taskServices;
    @Autowired
    public TaskController(TaskServices taskServices){
        this.taskServices=taskServices;
    }
    @Override
    public TaskDTO save(TaskDTO taskDTO) {
        return taskServices.save(taskDTO);
    }

    @Override
    public TaskDTO findById(Integer id) {
        return taskServices.findById(id);
    }

    @Override
    public TaskDTO findByDescription(String description) {
        return taskServices.findByDescription(description);
    }

    @Override
    public List<TaskDTO> findAll() {
        return taskServices.findAll();
    }

    @Override
    public void delete(Integer id) {
        taskServices.delete(id);
    }
}
