package com.notes.notes.controller;

import com.notes.notes.model.MP.MP;
import com.notes.notes.model.PLA.Pla;
import com.notes.notes.service.MP.IMPService;
import com.notes.notes.service.PLA.IPlaService;
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
@RequestMapping(value = "/Pla", method = RequestMethod.GET)
public class PlaController {

    @Autowired
    private IPlaService servicePla;

    // - Llistar els Pla

    @GetMapping("/llistaPla")
    public String llistaPla(Model m, @ModelAttribute(name = "modal") String modalAttr, @ModelAttribute(name = "plaSel") Pla plaSel){
        m.addAttribute("llistaPla", servicePla.getAllPla());
        m.addAttribute("modal",modalAttr);

        if(modalAttr.equals("")){
            m.addAttribute("plaSel",new Pla());
        }else {
            m.addAttribute("plaSel",plaSel);
        }

        return "/pla/llistaPla";
    }

    // - Crear els Pla

    @GetMapping("/creaPla")
    public String creaPla(Model m, RedirectAttributes redirAttri, @ModelAttribute(name = "plaSel") Pla plaSel, @ModelAttribute(name = "modal") String modalAttr){
        Pla pla = new Pla();
        m.addAttribute("creaPla",pla);

        if (pla != null){
            redirAttri.addFlashAttribute("creaPla",pla);
            redirAttri.addFlashAttribute("modal","1");
        }

        return "pla/creaPla";
    }

    @PostMapping("/guardaPla")
    public String guardaPla(@ModelAttribute(name = "creaPla") @Valid Pla pla, BindingResult bindingResult, RedirectAttributes redirAttri){

        if (bindingResult.hasErrors()) {
            return "pla/creaPla";
        }else if(servicePla.addPla(pla)){
            redirAttri.addFlashAttribute("msg","Pla afegit correctament");
        }

        return "redirect:/Pla/llistaPla";
    }

    // - Editar el Pla

    @GetMapping("/editaPlaView")
    public String editaPlaForm(HttpServletRequest request, Model m, @ModelAttribute(name = "plaSel") Pla pla, @ModelAttribute(name = "modal") String modalAttr){

        String numPla = request.getParameter("idPla");

        if (numPla != null) {
            Pla plaSeleccionat = servicePla.getPlaByidPla(Integer.parseInt(numPla));
            m.addAttribute("editaPla",plaSeleccionat);
            m.addAttribute("modal",false);
        }else {
            m.addAttribute("editaPla",pla);
            m.addAttribute("modal",modalAttr);
        }

        return "pla/editaPla";
    }

    @PostMapping("/editaPreparaPla")
    public String editaPreparaPla(@ModelAttribute Pla pla, Model m, RedirectAttributes redirAttrs){

        redirAttrs.addFlashAttribute("plaSel",pla);
        redirAttrs.addFlashAttribute("modal","1");

        return "redirect:/Pla/editaPlaView";
    }

    @PostMapping("/editaPla")
    public String editaPla(Model m, @ModelAttribute(name = "editaPla") @Valid Pla pla, BindingResult bindingResult, RedirectAttributes redirAttri){

        if (bindingResult.hasErrors()) {
            return "pla/editaPla";
        }else if(servicePla.editaPla(pla)){
            redirAttri.addFlashAttribute("msg","Pla editat correctament");
        }

        return "redirect:/Pla/llistaPla";
    }


    // - Eliminar el Pla

    @PostMapping("/removePla")
    public String removePla(HttpServletRequest request, RedirectAttributes redirAttri, Pla pla){

        String idPla = request.getParameter("pla");

        System.out.println("Pla: " + pla);

        if(servicePla.removePla(pla)){
            redirAttri.addFlashAttribute("msg", "Pla eliminat correctament");
        }

        return "redirect:/Pla/llistaPla";
    }

    @GetMapping("/removeView")
    public String removePlaView(Model m, @RequestParam(name = "idPla") String idPla, RedirectAttributes redirAttrs){

        Pla pla = servicePla.getPlaByidPla(Integer.parseInt(idPla));

        redirAttrs.addFlashAttribute("plaSel",pla);
        redirAttrs.addFlashAttribute("modal","1");

        return "redirect:/Pla/llistaPla";
    }
}
