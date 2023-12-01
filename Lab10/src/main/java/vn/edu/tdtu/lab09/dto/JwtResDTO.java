package vn.edu.tdtu.lab09.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResDTO {
    private String username;
    private String token;
    private String role;
}
