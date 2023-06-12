package com.ecommerce.ECommerce.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateTaskDTO {
    private String title;
    private String description;
    private boolean completed;
}
