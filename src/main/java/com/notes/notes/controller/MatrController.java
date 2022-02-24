package com.notes.notes.controller;

import com.notes.notes.model.EXP.Exp;
import com.notes.notes.model.MATR.Matr;
import com.notes.notes.service.MATR.IMatrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ComponentScan(basePackages = {"com.notes.notes.controller"})
@Controller
@RequestMapping(value = "/Matr", method = RequestMethod.GET)
public class MatrController {
    @Autowired
    private IMatrService serviceMatr;

    @GetMapping("/llistaMatr")
    public String llistaMatr(Model m, @ModelAttribute(name = "modal") String modalAttr, @ModelAttribute(name = "matrSel") Matr matrSel){

        m.addAttribute("llistaMatr", serviceMatr.getAllMatr());
        m.addAttribute("modal",modalAttr);

        if(modalAttr.equals("")){
            m.addAttribute("matrSel",new Matr());
        }else {
            m.addAttribute("matrSel",matrSel);
        }

        return "matr/llistaMatr";
    }

    @GetMapping("/creaMatr")
    public String creaMatr(Model m, RedirectAttributes redirAttri, @ModelAttribute(name = "matrSel") Matr matrSel, @ModelAttribute(name = "modal") String modalAttr){
        Matr matr = new Matr();
        m.addAttribute("creaMatr",matr);

        if (matr != null){
            redirAttri.addFlashAttribute("creaMatr",matr);
            redirAttri.addFlashAttribute("modal","1");
        }

        return "matr/llistaMatr";
    }



}
