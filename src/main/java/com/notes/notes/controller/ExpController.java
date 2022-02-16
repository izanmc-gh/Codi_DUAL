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
    public String creaExp(Model m, RedirectAttributes redirAttri, @ModelAttribute(name = "expSel") Exp expSel, @ModelAttribute(name = "modal") String modalAttr){
        Exp exp = new Exp();
        m.addAttribute("creaExp",exp);

        if (exp != null){
            redirAttri.addFlashAttribute("creaExp",exp);
            redirAttri.addFlashAttribute("modal","1");
        }

        return "exp/creaExp";
    }

    @PostMapping("/guardaExp")
    public String guardaExp(@ModelAttribute(name = "creaExp") @Valid Exp exp, BindingResult bindingResult, RedirectAttributes redirAttri){

        if (bindingResult.hasErrors()) {
            return "exp/creaExp";
        }else if(serviceExp.addExp(exp)){
            redirAttri.addFlashAttribute("msg","Expedient afegit correctament");
        }

        return "redirect:/exp/llistaExp";
    }

    // - Editar l'expedient

    @GetMapping("/editaExpView")
    public String editaExpForm(HttpServletRequest request, Model m, @ModelAttribute(name = "expSel") Exp exp, @ModelAttribute(name = "modal") String modalAttr){

        String idExp = request.getParameter("idExp");

        if (idExp != null) {
            Exp expSeleccionat = serviceExp.getExpByidExp(idExp);
            m.addAttribute("editaExp",expSeleccionat);
            m.addAttribute("modal",false);
        }else {
            m.addAttribute("editaExp",exp);
            m.addAttribute("modal",modalAttr);
        }

        return "exp/editaExp";
    }

    @PostMapping("/editaPreparaExp")
    public String editaPreparaExp(@ModelAttribute Exp exp, Model m, RedirectAttributes redirAttrs){

        redirAttrs.addFlashAttribute("expSel",exp);
        redirAttrs.addFlashAttribute("modal","1");

        return "redirect:/Exp/editaExpView";
    }

    @PostMapping("/editaExp")
    public String editaExp(Model m, @ModelAttribute(name = "editaExp") @Valid Exp exp, BindingResult bindingResult, RedirectAttributes redirAttri){

        if (bindingResult.hasErrors()) {
            return "exp/editaExp";
        }else if(serviceExp.editaExp(exp)){
            redirAttri.addFlashAttribute("msg","Expedient editat correctament");
        }

        return "redirect:/Exp/llistaExp";
    }

    // - Eliminar l'expedient

    @PostMapping("/removeExp")
    public String removeExp(HttpServletRequest request, RedirectAttributes redirAttri, Exp exp){

        String idExp = request.getParameter("exp");

        System.out.println("Exp: " + exp);

        if(serviceExp.removeExp(exp)){
            redirAttri.addFlashAttribute("msg", "Expedient eliminat correctament");
        }

        return "redirect:/Exp/llistaExp";
    }

    @GetMapping("/removeView")
    public String removeExpView(Model m, @RequestParam(name = "idExp") String idExp, RedirectAttributes redirAttrs){

        Exp exp = serviceExp.getExpByidExp(idExp);

        redirAttrs.addFlashAttribute("expSel",exp);
        redirAttrs.addFlashAttribute("modal","1");

        return "redirect:/Exp/llistaExp";
    }
}
