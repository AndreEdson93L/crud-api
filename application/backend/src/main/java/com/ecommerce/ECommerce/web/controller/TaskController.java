package com.ecommerce.ECommerce.web.controller;

import com.ecommerce.ECommerce.dto.TaskDTO;
import com.ecommerce.ECommerce.service.TaskService;
import com.ecommerce.ECommerce.dto.CreateTaskDTO;
import com.ecommerce.ECommerce.dto.UpdateTaskDTO;
import com.ecommerce.ECommerce.mapper.TaskMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/tasks")
public class TaskController {

    private final TaskService taskService;
    private final TaskMapper taskMapper;

    @Autowired
    public TaskController(TaskService taskService, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }

    @GetMapping
    @Operation(summary = "Get all tasks")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tasks retrieved successfully", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = TaskDTO.class))}),
            @ApiResponse(responseCode = "404", description = "No tasks found", content = @Content)
    })
    public ResponseEntity<List<TaskDTO>> getAllTasks() {
        List<TaskDTO> taskDTOs = taskService.getAllTasks();
        return new ResponseEntity<>(taskDTOs, HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Create a task")
    @ApiResponse(responseCode = "201", description = "Task created successfully", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = CreateTaskDTO.class))})
    public ResponseEntity<TaskDTO> createTask(@RequestBody CreateTaskDTO createTaskDTO) {
        TaskDTO createdTask = taskService.createTask(createTaskDTO);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    @GetMapping("/{task_id}")
    @Operation(summary = "Get a task by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task retrieved successfully", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = TaskDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Task not found", content = @Content)
    })
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable("task_id") Long id) {
        TaskDTO taskDTO = taskService.getTask(id);
        return new ResponseEntity<>(taskDTO, HttpStatus.OK);
    }

    @PutMapping("/{task_id}")
    @Operation(summary = "Update a task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task updated successfully", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = UpdateTaskDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Task not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)
    })
    public ResponseEntity<TaskDTO> updateTask(@PathVariable("task_id") Long id, @RequestBody UpdateTaskDTO updateTaskDTO) {
        TaskDTO updatedTask = taskService.updateTask(id, updateTaskDTO);
        return new ResponseEntity<>(updatedTask, HttpStatus.OK);
    }

    @DeleteMapping("/{task_id}")
    @Operation(summary = "Delete a task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Task deleted", content = @Content),
            @ApiResponse(responseCode = "404", description = "Task not found", content = @Content)
    })
    public ResponseEntity<Void> deleteTask(@PathVariable("task_id") Long id) {
        taskService.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
