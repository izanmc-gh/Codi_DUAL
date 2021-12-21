package com.notes.notes.controller;

import com.notes.notes.model.MP.MP;
import com.notes.notes.service.MP.IMPService;
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
@RequestMapping(value = "/MP", method = RequestMethod.GET)
public class MPController {

    @Autowired
    private IMPService serviceMP;

    // - Llistar els MP

    @GetMapping("/llistaMP")
    public String llistaMP(Model m, @ModelAttribute(name = "modal") String modalAttr, @ModelAttribute(name = "mpSel") MP mpSel){
        m.addAttribute("llistaMP", serviceMP.getAllMP());
        m.addAttribute("modal",modalAttr);

        if(modalAttr.equals("")){
            m.addAttribute("mpSel",new MP());
        }else {
            m.addAttribute("mpSel",mpSel);
        }

        return "/mp/llistaMP";
    }

    // - Crear els MP

    @GetMapping("/creaMP")
    public String creaMP(Model m, RedirectAttributes redirAttri, @ModelAttribute(name = "mpSel") MP mpSel, @ModelAttribute(name = "modal") String modalAttr){
        MP mp = new MP();
        m.addAttribute("creaMP",mp);

        if (mp != null){
            redirAttri.addFlashAttribute("creaMP",mp);
            redirAttri.addFlashAttribute("modal","1");
        }

        return "mp/creaMP";
    }

    @PostMapping("/guardaMP")
    public String guardaMP(@ModelAttribute(name = "creaMP") @Valid MP mp, BindingResult bindingResult, RedirectAttributes redirAttri){

        if (bindingResult.hasErrors()) {
            return "mp/creaMP";
        }else if(serviceMP.addMP(mp)){
            redirAttri.addFlashAttribute("msg","MP afegit correctament");
        }

        return "redirect:/MP/llistaMP";
    }

    // - Editar els MP

    @GetMapping("/editaMPView")
    public String editaMPForm(HttpServletRequest request, Model m, @ModelAttribute(name = "mpSel") MP mp, @ModelAttribute(name = "modal") String modalAttr){

        String numMP = request.getParameter("idMP");

        if (numMP != null) {
            MP mpSeleccionat = serviceMP.getMPByidMP(numMP);
            m.addAttribute("editaMP",mpSeleccionat);
            m.addAttribute("modal",false);
        }else {
            m.addAttribute("editaMP",mp);
            m.addAttribute("modal",modalAttr);
        }

        return "mp/editaMP";
    }

    @PostMapping("/editaPreparaMP")
    public String editaPreparaMP(@ModelAttribute MP mp, Model m, RedirectAttributes redirAttrs){

        redirAttrs.addFlashAttribute("mpSel",mp);
        redirAttrs.addFlashAttribute("modal","1");

        return "redirect:/MP/editaMPView";
    }

    @PostMapping("/editaMP")
    public String editaMP(Model m, @ModelAttribute(name = "editaMP") @Valid MP mp, BindingResult bindingResult, RedirectAttributes redirAttri){

        if (bindingResult.hasErrors()) {
            return "mp/editaMP";
        }else if(serviceMP.editaMP(mp)){
            redirAttri.addFlashAttribute("msg","MP editat correctament");
        }

        return "redirect:/MP/llistaMP";
    }


    // - Eliminar els MP

    @PostMapping("/removeMP")
    public String removeMP(HttpServletRequest request, RedirectAttributes redirAttri, MP mp){

        String numMP = request.getParameter("mp");

        if(serviceMP.removeMP(mp)){
            redirAttri.addFlashAttribute("msg", "MP eliminat correctament");
        }

        return "redirect:/MP/llistaMP";
    }

    @GetMapping("/removeView")
    public String removeMPView(Model m, @RequestParam(name = "idMP") String idMP, RedirectAttributes redirAttrs){

        MP mp = serviceMP.getMPByidMP(idMP);

        redirAttrs.addFlashAttribute("mpSel",mp);
        redirAttrs.addFlashAttribute("modal","1");

        return "redirect:/MP/llistaMP";
    }
}