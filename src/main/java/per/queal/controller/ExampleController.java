package per.queal.controller;

import org.camunda.bpm.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class ExampleController {

    @Autowired
    private RuntimeService runtimeService;

    @RequestMapping("/test")
    public void test() {

    }

}
