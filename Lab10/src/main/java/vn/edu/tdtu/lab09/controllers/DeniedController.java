package vn.edu.tdtu.lab09.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.tdtu.lab09.dto.ResDTO;

@RestController
public class DeniedController {
    @RequestMapping("/403")
    public ResponseEntity<?> denied(){
        return ResponseEntity.badRequest().body(
            new ResDTO(
                "You do not have permission to access this content",
                false,
                null
            )
        );
    }
}
