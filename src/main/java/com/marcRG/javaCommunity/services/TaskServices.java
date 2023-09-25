package com.marcRG.javaCommunity.services;

import com.marcRG.javaCommunity.DTO.TaskDTO;

import java.util.List;

public interface TaskServices {
    TaskDTO save(TaskDTO taskDTO);
    TaskDTO findById(Integer id);
    TaskDTO findByDescription(String description);
    List<TaskDTO> findAll();
    void delete(Integer id);
}
