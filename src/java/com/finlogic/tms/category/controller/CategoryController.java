/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.finlogic.tms.category.controller;

import com.finlogic.tms.category.bean.CategoryFormBean;
import com.finlogic.tms.category.service.CategoryService;
import com.finlogic.util.CommonMember;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author njuser
 */
@Controller
@RequestMapping(value = "category.fin")
public class CategoryController {
    
    @Autowired
    CategoryService categoryService;
    
    @RequestMapping(params = "cmdAction=loadCategory", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView loadCategory(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView("Category/category");
        
        return modelAndView;
    }
    
    @RequestMapping(params = "cmdAction=loadAddCategory", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView loadAddCategory(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView("Category/addCategory");
        try {
            CommonMember.appendLogFile("Master 1" );
            modelAndView.addObject("tmptypelist", categoryService.getTemplateType());
            modelAndView.addObject("Default", "0");
            
        } catch (Exception ex) {
            CommonMember.errorHandler(ex);
        }
        return modelAndView;
    }
    @RequestMapping(params = "cmdAction=loadEditCategory", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView loadEditCategory(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView("Category/editCategory");
        try {
            String action = request.getParameter("Action");
            modelAndView.addObject("tmptypelist", categoryService.getTemplateType());
            modelAndView.addObject("action", action);
        } catch (Exception ex) {
            CommonMember.errorHandler(ex);
        }
        return modelAndView;
    }
    @RequestMapping(params = "cmdAction=loadDeleteCategory", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView loadDeleteCategory(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView("Category/editCategory");
        try {
            String action = request.getParameter("Action");
            modelAndView.addObject("tmptypelist", categoryService.getTemplateType());
            modelAndView.addObject("action", action);
            
        } catch (Exception ex) {
            CommonMember.errorHandler(ex);
        }
        return modelAndView;
    }
    @RequestMapping(params = "cmdAction=loadViewCategory", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView loadViewCategory(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView("Category/editCategory");
        try {
            String action = request.getParameter("Action");
            modelAndView.addObject("tmptypelist", categoryService.getTemplateType());
            modelAndView.addObject("action", action);
            
        } catch (Exception ex) {
            CommonMember.errorHandler(ex);
        }
        return modelAndView;
    }
    
    @RequestMapping(params = "cmdAction=insertCategory", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView insertCategory(HttpServletRequest request,
            HttpServletResponse response,
            CategoryFormBean categoryFormBean) {
        ModelAndView modelAndView = new ModelAndView("Category/categoryajax");
        try {
            
            CommonMember.appendLogFile("Master 1" + categoryFormBean.toString());
            int result = categoryService.insertCategoryDetail(categoryFormBean);
            modelAndView.addObject("Action", "insertCategory");
            modelAndView.addObject("Status", result);
            CommonMember.appendLogFile("@CategoryController :: InsertCategory :: result :: " + result);
            
        } catch (Exception e) {
            CommonMember.errorHandler(e);
        }
        
        return modelAndView;
    }
    
    
    @RequestMapping(params="cmdAction=showCategory" , method = {RequestMethod.GET , RequestMethod.POST})
    public ModelAndView getAllCategoryDetail(HttpServletRequest request , HttpServletResponse response , CategoryFormBean categoryFormBean)
    {
        ModelAndView modelAndView = new ModelAndView("Category/categoryajax");
        try {
            String crudAction = request.getParameter("action");
            String filterType = request.getParameter("filterValue");
            categoryFormBean.setCmbFilterType(filterType);
            List CategoryList = categoryService.getAllCategoryDetail(categoryFormBean);
            modelAndView.addObject("Action", "viewCategory");
            modelAndView.addObject("CategoryList", CategoryList);
            modelAndView.addObject("crudAction", crudAction);
            CommonMember.appendLogFile("@CategoryController :: getAllCategoryDetail :: CategoryList :: " + CategoryList + " :: filterType :: " + filterType);
            
        } catch (Exception ex) {
            CommonMember.errorHandler(ex);
        }
        return modelAndView;
    }
    
    @RequestMapping(params = "cmdAction=getCategoryData", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView getCategoryData(HttpServletRequest request,
            HttpServletResponse response,
            CategoryFormBean categoryFormBean)
    {
        ModelAndView modelAndView = new ModelAndView("Category/addCategory");
        try {
            String categoryId = request.getParameter("CategoryID");
            String crudAction = request.getParameter("crudAction");
            CommonMember.appendLogFile(categoryId);
            List categoryList = categoryService.getCategoryData(categoryId);
            modelAndView.addObject("task", crudAction);
            modelAndView.addObject("categoryID", categoryId);
           if (categoryList.size() > 0) {
                Map m = (Map) categoryList.get(0);
                modelAndView.addObject("hdnTemplateType", m.get("TEMPLATE_TYPE_NAME"));
                modelAndView.addObject("hdnTemplateCategory", m.get("CATEGORY_NAME"));
                modelAndView.addObject("TMPID", m.get("TEMPLATE_TYPE_ID"));
                //modelAndView.addObject("Type", m.get("TEMPLATE_TYPE_NAME"));
                modelAndView.addObject("tmptypelist", categoryService.getTemplateType());
                modelAndView.addObject("Category", m.get("CATEGORY_NAME"));
                modelAndView.addObject("IsActive", m.get("ISACTIVE"));
            }  
            CommonMember.appendLogFile("@CategoryController :: getCategoryData :: categoryList :: " + categoryList);
            
        } catch (Exception ex) {
            CommonMember.errorHandler(ex);
        }
        return modelAndView;
        
    }
    
    @RequestMapping(params = "cmdAction=editCategory", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView editCategory(HttpServletRequest request,
            HttpServletResponse response,
            CategoryFormBean categoryFormBean) {
        ModelAndView modelAndView = new ModelAndView("Category/categoryajax");
        try {
            int result = categoryService.editCategoryDetail(categoryFormBean);
            modelAndView.addObject("Action", "editCategory");
            modelAndView.addObject("Status", result);
            CommonMember.appendLogFile("@CategoryController :: EditCategory :: result :: " + result);
            
        } catch (Exception e) {
            CommonMember.errorHandler(e);
        }
        
        return modelAndView;
    }
    
    @RequestMapping(params = "cmdAction=deleteCategory", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView deleteCategory(HttpServletRequest request,
            HttpServletResponse response) 
    {
        ModelAndView modelAndView = new ModelAndView("Category/categoryajax");
        try {
            String CategoryID = request.getParameter("categoryId");
            CommonMember.appendLogFile("@CategoryController :: deleteCategory :: CategoryID :: " + CategoryID);
            int result = categoryService.deleteCategoryDetail(CategoryID);
            modelAndView.addObject("Action", "deleteCategory");
            modelAndView.addObject("Status", result);
            CommonMember.appendLogFile("@CategoryController :: EditCategory :: result :: " + result);
            
        } catch (Exception e) {
            CommonMember.errorHandler(e);
        }
        
        return modelAndView;
    }
    
    
}