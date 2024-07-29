package com.nucleus.excpetion;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Exception handler for all types of exceptions
    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception ex) {
        ModelAndView modelAndView = new ModelAndView("errorPage");
        modelAndView.addObject("errorMessage", ex.getMessage());
        return modelAndView;
    }

    // Handler for 404 - Resource Not Found errors
    @RequestMapping("/*")
    public ModelAndView handleNotFound() {
        ModelAndView modelAndView = new ModelAndView("errorPage");
        modelAndView.addObject("errorMessage", "404 - Resource Not Found");
        return modelAndView;
    }
}
