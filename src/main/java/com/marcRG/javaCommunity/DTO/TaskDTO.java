package com.marcRG.javaCommunity.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.marcRG.javaCommunity.models.Task;
import com.marcRG.javaCommunity.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskDTO {
    private Integer id;
    private String description;
    private Instant dateTask;
    @JsonIgnore
    private User user;

    public static Task toEntity(TaskDTO taskDTO){
        if(taskDTO==null){
            return null;
        }
        Task task= new Task();
        task.setId(taskDTO.getId());
        task.setDescription(taskDTO.getDescription());
        task.setDateTask(taskDTO.getDateTask());
        return task;
    }

    public static TaskDTO fromEntity(Task task){
        if(task==null){
            return null;
        }
        return TaskDTO.builder()
                .id(task.getId())
                .description(task.getDescription())
                .dateTask(task.getDateTask())
                .build();
    }
}
