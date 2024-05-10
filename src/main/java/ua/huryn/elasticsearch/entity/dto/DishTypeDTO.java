package ua.huryn.elasticsearch.entity.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
public class DishTypeDTO  implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long dishTypeId;
    private String name;
}
