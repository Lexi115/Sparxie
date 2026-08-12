package io.lexi115.sparxie.game;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game")
public class TestController {

    TestFeign testFeign;

    public TestController(TestFeign testFeign) {
        this.testFeign = testFeign;
    }

    @GetMapping
    public String test() {
        var str = testFeign.test();
        return str.toUpperCase();
    }

}
