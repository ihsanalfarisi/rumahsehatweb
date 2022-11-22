package apap.tugasakhir.rumahsehat.restcontroller;

import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class HomeController {
    @GetMapping("")
    public ResponseEntity home() {
        return ResponseEntity.ok("Welcome to home");
    }
}
