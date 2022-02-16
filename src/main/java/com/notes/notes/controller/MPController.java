package com.notes.notes.controller;

import com.notes.notes.model.MP.MP;
import com.notes.notes.model.PLA.Pla;
import com.notes.notes.model.UF.UF;
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
@RequestMapping("/mp")
public class MPController {

    @Autowired
    private IMPService serviceMP;
    @Autowired
    private IPlaService servicePla;

    // - Llistar els MP

    @GetMapping("/llistaMP")
    public String llistaMP(Model m, @ModelAttribute("idPla") String idPla ,@ModelAttribute("modal") String modalAttr, @ModelAttribute("mpSel") MP mpSel){

        m.addAttribute("pla", servicePla.getPlaByidPla(idPla));

        if (modalAttr.equalsIgnoreCase("1")){
            m.addAttribute("modal", modalAttr);
            m.addAttribute("mpSel", mpSel);
        }else {
            MP mp = new MP();
            m.addAttribute("mpSel", mp);
        }

        if (m.getAttribute("mp")==null){
            m.addAttribute("mp", new MP());
        }

        return "mp/llistaMP";
    }

    // - Crear els MP

    @GetMapping("/creaMP")
    public String creaMP(Model m, HttpServletRequest request, @ModelAttribute("idPla") String idPla){

        System.out.println("Aquest és l'id pla que en samu m'ha fet borrar abans: "+idPla);

        Pla pla = servicePla.getPlaByidPla(idPla);

        MP mp = new MP();
        m.addAttribute("pla", pla);
        m.addAttribute("mpSel", mp);
        System.out.println("Aquest és el pla que recupero: "+pla);
        return "mp/creaMP";
    }

    @PostMapping("/guardaMP")
    public String guardaMP(@ModelAttribute MP mp, BindingResult bindingResult, RedirectAttributes redirAttri, @RequestParam int idPla){

        if (bindingResult.hasErrors()) {
            return "mp/creaMP";
        }

        mp.setPla(servicePla.getPlaByidPla(String.valueOf(idPla)));

        if (serviceMP.addMP(mp)) {
            redirAttri.addFlashAttribute("msg", "MP afegida correctament");
            redirAttri.addFlashAttribute("idPla", mp.getPla().getIdPla());
        }

        return "redirect:/mp/llistaMP";
    }

    // - Editar els MP

    @GetMapping("/editaMPView")
    public String editaMPForm(HttpServletRequest request, Model m, @ModelAttribute(name = "mpSel") MP mp, @ModelAttribute(name = "modal") String modalAttr){

        String idMP = request.getParameter("idMP");

        if (idMP != null) {
            System.out.println("Entro al primer if");
            MP mp1 = serviceMP.getMPByidMP(idMP);
            System.out.println("Aquesta es la mp que recupero: "+mp1);
            m.addAttribute("mpSel", mp1);
            m.addAttribute("modal", false);
        }
        /*
        else {
            System.out.println("Entro al segon if");
            m.addAttribute("mpSel", mp);
            System.out.println("Segona mp que recupero: "+mp);
            m.addAttribute("modal", modalAttr);
        }
        */

        return "mp/editaMP";
    }

    @PostMapping("/editaPreparaMP")
    public String editaPreparaMP(@ModelAttribute MP mp, Model m, RedirectAttributes redirAttri, @RequestParam int idPla){

        Pla pla = servicePla.getPlaByidPla(String.valueOf(idPla));

        mp.setPla(pla);

        System.out.println("Aquesta és la mp per preparar el modal: "+ mp);

        redirAttri.addFlashAttribute("mpSel", mp);
        redirAttri.addFlashAttribute("modal", "1");

        return "redirect:/mp/editaMPView";
    }

    @PostMapping("/editaMP")
    public String editaMP(HttpServletRequest request,Model m, @ModelAttribute(name = "mpSel") @Valid MP mp, BindingResult bindingResult, RedirectAttributes redirAttri, @RequestParam int idPla){

        Pla pla = servicePla.getPlaByidPla(request.getParameter("idPla"));

        System.out.println("Id Pla: "+idPla);

        mp.setPla(pla);

        System.out.println("al guardar: "+mp);

        if (bindingResult.hasErrors()) {
            m.addAttribute("mpSel", mp);
            return "mp/editaMP";
        }

        if (serviceMP.editaMP(mp)) {
            m.addAttribute("mpSel", mp);
            redirAttri.addFlashAttribute("msg", "MP editada correctament");
            redirAttri.addFlashAttribute("idPla", mp.getPla().getIdPla());
        }

        return "redirect:/mp/llistaMP";
    }

    // - Eliminar els MP

    @PostMapping("/removeMP")
    public String removeMP(HttpServletRequest request, RedirectAttributes redirAttri, MP mp){
        String idMP = request.getParameter("idMP");
        MP mp1 = serviceMP.getMPByidMP(idMP);
        if (serviceMP.removeMP(mp1)) {
            redirAttri.addFlashAttribute("msg", "MP eliminada correctament");
            redirAttri.addFlashAttribute("idPla", mp1.getPla().getIdPla());
        }
        return "redirect:/mp/llistaMP";
    }

    @GetMapping("/removeView")
    public String removeMPView(HttpServletRequest request, Model m, @RequestParam(name = "idMP") String idMP, RedirectAttributes redirAttrs){

        MP mp1 = serviceMP.getMPByidMP(request.getParameter("idMP"));
        redirAttrs.addFlashAttribute("mpSel", mp1);
        redirAttrs.addFlashAttribute("idPla", mp1.getPla().getIdPla());
        System.out.println("MP recuperada del modal: " + mp1);
        redirAttrs.addFlashAttribute("modal", "1");
        System.out.println("Entra al modal");

        return "redirect:/mp/llistaMP";
    }
}