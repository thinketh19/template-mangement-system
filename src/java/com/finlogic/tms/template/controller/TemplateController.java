
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.finlogic.tms.template.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author njuser
 */
@Controller
@RequestMapping(value="template.fin")
public class TemplateController {
    
    @RequestMapping(params="cmdAction=getTemplate")
    public ModelAndView loadTemplate(HttpServletRequest request , HttpServletResponse response)
    {
        ModelAndView modelAndView = new ModelAndView("Template/template");
        return modelAndView;
    }
}