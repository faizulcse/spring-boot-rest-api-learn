package com.aapon.springbootrestapilearn.restapi;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//@Controller
//public class RestApiController {
//    // localhost:8080/employees
//
//    @RequestMapping(value = "/employees", method = RequestMethod.GET)
//    @ResponseBody
//    public String getEmployees() {
//        return "Hello Rest API faizul";
//    }
//}

@RestController
public class RestApiController {
    // localhost:8080/employees

    @GetMapping("/employees")
    public String getEmployees() {
        return "Hello Rest API faizul";
    }
}
