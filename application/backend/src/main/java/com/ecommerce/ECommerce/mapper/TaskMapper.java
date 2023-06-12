package com.ecommerce.ECommerce.mapper;

import com.ecommerce.ECommerce.dto.TaskDTO;
import com.ecommerce.ECommerce.domain.model.Task;
import com.ecommerce.ECommerce.dto.CreateTaskDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    Task toTask(TaskDTO taskDTO);
    Task toTask(CreateTaskDTO createTaskDTO);
    TaskDTO toTaskDTO(Task task);
}
