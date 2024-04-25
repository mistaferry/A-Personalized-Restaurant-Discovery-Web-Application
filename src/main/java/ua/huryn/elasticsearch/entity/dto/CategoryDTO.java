package ua.huryn.elasticsearch.entity.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
public class CategoryDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long categoryId;
    private String name;
}
