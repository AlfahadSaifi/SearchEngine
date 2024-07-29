package com.nucleus.controller.securitytest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/admin")
public class HelloWorldController {
    @Autowired
    Logger logger;
    @RequestMapping(path = "/hello")
    public String helloDisplay(){
        return "url_upload/uploadPDF";
    }
}
