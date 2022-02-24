package com.notes.notes.controller;

import com.notes.notes.model.CURS.Curs;
import com.notes.notes.model.EXP.Exp;
import com.notes.notes.model.MP.MP;
import com.notes.notes.model.PLA.Pla;
import com.notes.notes.service.CURS.ICursService;
import com.notes.notes.service.EXP.IExpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@ComponentScan(basePackages = {"com.notes.notes.controller"})
@Controller
@RequestMapping(value = "/Exp", method = RequestMethod.GET)
public class ExpController {
    @Autowired
    private IExpService serviceExp;

    @GetMapping("/llistaExp")
    public String llistaExp(Model m, @ModelAttribute(name = "modal") String modalAttr, @ModelAttribute(name = "expSel") Exp expSel){

        m.addAttribute("llistaExp", serviceExp.getAllExp());
        m.addAttribute("modal",modalAttr);

        if(modalAttr.equals("")){
            m.addAttribute("expSel",new Exp());
        }else {
            m.addAttribute("expSel",expSel);
        }

        return "exp/llistaExp";
    }

    @GetMapping("/creaExp")
    public String creaExp(HttpServletRequest request,Model m, RedirectAttributes redirAttri, @ModelAttribute(name = "expSel") Exp expSel, @ModelAttribute(name = "modal") String modalAttr){

        Exp exp = new Exp();
        m.addAttribute("creaExp",exp);

        if (exp != null){
            redirAttri.addFlashAttribute("creaExp",exp);
            redirAttri.addFlashAttribute("modal","1");
        }


        return "exp/llistaExp";
    }
}
