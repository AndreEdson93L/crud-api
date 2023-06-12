package com.ecommerce.ECommerce.service;

import com.ecommerce.ECommerce.dto.TaskDTO;
import com.ecommerce.ECommerce.dto.CreateTaskDTO;
import com.ecommerce.ECommerce.dto.UpdateTaskDTO;

import java.util.List;

public interface TaskService {
    TaskDTO createTask(CreateTaskDTO createTaskDTO);
    TaskDTO getTask(Long id);
    List<TaskDTO> getAllTasks();
    TaskDTO updateTask(Long id, UpdateTaskDTO updateTaskDTOu);
    void deleteTask(Long id);
}
