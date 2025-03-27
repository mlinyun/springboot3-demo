package com.mlinyun.springboot3demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FreeMarkerController {

    @GetMapping("/freemarker")
    public ModelAndView freeMarker(@RequestParam(value = "info", required = false, defaultValue = "FreeMarker") String info) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("info", info);
        modelAndView.setViewName("freemarker");
        return modelAndView;
    }

}
