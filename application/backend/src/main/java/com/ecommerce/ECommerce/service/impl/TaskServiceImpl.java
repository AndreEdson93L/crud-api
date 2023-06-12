package com.ecommerce.ECommerce.service.impl;

import com.ecommerce.ECommerce.dto.TaskDTO;
import com.ecommerce.ECommerce.service.TaskService;
import com.ecommerce.ECommerce.domain.repository.TaskRepository;
import com.ecommerce.ECommerce.domain.model.Task;
import com.ecommerce.ECommerce.dto.CreateTaskDTO;
import com.ecommerce.ECommerce.dto.UpdateTaskDTO;
import com.ecommerce.ECommerce.mapper.TaskMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public TaskServiceImpl(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    @Override
    @Transactional
    public TaskDTO createTask(CreateTaskDTO createTaskDTO) {
        Task task = taskMapper.toTask(createTaskDTO);
        taskRepository.save(task);
        return taskMapper.toTaskDTO(task);
    }

    @Override
    public TaskDTO getTask(Long id) {
        return taskRepository.findById(id)
                .map(taskMapper::toTaskDTO)
                .orElseThrow(() -> new RuntimeException("Task not found"));
    }

    @Override
    public List<TaskDTO> getAllTasks() {
        return taskRepository.findAll().stream()
                .map(taskMapper::toTaskDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public TaskDTO updateTask(Long id, UpdateTaskDTO updateTaskDTO) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        task.setTitle(updateTaskDTO.getTitle());
        task.setDescription(updateTaskDTO.getDescription());
        task.setCompleted(updateTaskDTO.isCompleted());
        taskRepository.save(task);
        return taskMapper.toTaskDTO(task);
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
