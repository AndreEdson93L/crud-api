package com.ecommerce.ECommerce.domain.repository;

import com.ecommerce.ECommerce.domain.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
