package com.notes.notes.controller;

import com.notes.notes.model.CURS.Curs;
import com.notes.notes.model.MP.MP;
import com.notes.notes.service.CURS.ICursService;
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
@RequestMapping(value = "/Curs", method = RequestMethod.GET)
public class CursController {

    @Autowired
    private ICursService serviceCurs;

    @GetMapping("/llistaCurs")
    public String llistaCurs(Model m, @ModelAttribute(name = "modal") String modalAttr, @ModelAttribute(name = "cursSel") Curs cursSel){

        m.addAttribute("llistaCurs", serviceCurs.getAllCurs());
        m.addAttribute("modal",modalAttr);

        if(modalAttr.equals("")){
            m.addAttribute("cursSel",new Curs());
        }else {
            m.addAttribute("cursSel",cursSel);
        }

        return "curs/llistaCurs";
    }

    @GetMapping("/creaCurs")
    public String creaCurs(Model m, RedirectAttributes redirAttri, @ModelAttribute(name = "mpSel") MP mpSel, @ModelAttribute(name = "modal") String modalAttr){
        Curs curs = new Curs();
        m.addAttribute("creaCurs",curs);

        if (curs != null){
            redirAttri.addFlashAttribute("creaCurs",curs);
            redirAttri.addFlashAttribute("modal","1");
        }

        return "curs/creaCurs";
    }

    @PostMapping("/guardaCurs")
    public String guardaCurs(@ModelAttribute(name = "creaCurs") @Valid Curs curs, BindingResult bindingResult, RedirectAttributes redirAttri){

        if (bindingResult.hasErrors()) {
            return "curs/creaCurs";
        }else if(serviceCurs.addCurs(curs)){
            redirAttri.addFlashAttribute("msg","Curs afegit correctament");
        }

        return "redirect:/curs/llistaCurs";
    }
}
