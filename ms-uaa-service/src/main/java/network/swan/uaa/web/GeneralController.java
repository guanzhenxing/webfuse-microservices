package network.swan.uaa.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GeneralController {


    @GetMapping("/")
    public String home() {
        return "Hello World";
    }
}
