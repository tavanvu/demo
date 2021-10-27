package com.codegym.aspect;

import com.codegym.exception.NotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionHelper {
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView notFound() {
        return new ModelAndView("product/demo/error-404");
    }
}
