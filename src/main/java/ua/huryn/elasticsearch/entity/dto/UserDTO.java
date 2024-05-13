package ua.huryn.elasticsearch.entity.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
public class UserDTO  implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long userId;
    private String username;
    private String email;
    private String picture;
}
