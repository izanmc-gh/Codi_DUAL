package com.notes.notes.controller;

import com.notes.notes.model.MP.MP;
import com.notes.notes.model.UF.UF;
import com.notes.notes.service.MP.IMPService;
import com.notes.notes.service.UF.IUFService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@Controller
@RequestMapping(value = "/UF", method = RequestMethod.GET)
public class UFController {

    @Autowired
    private IUFService serviceUF;
    @Autowired
    private IMPService serviceMP;

    // - Llista les UF

    @GetMapping("/llistaUF")
    public String llistaUF(Model m, @ModelAttribute(name = "modal") String modalAttr, @ModelAttribute(name = "idMP") String idMP, @ModelAttribute(name = "uf1") String numUF) {

        UF uf = new UF();

        MP mp = serviceMP.getMPByidMP(idMP);

        m.addAttribute("oMP", mp);

        if (!numUF.isEmpty()) {
            uf = mp.getUFByNum(numUF);
        }

        m.addAttribute("uf", uf);

        m.addAttribute("modal", modalAttr);

        return "uf/llistaUF";
    }

    // - Crea les UF

    @GetMapping("/creaUF")
    public String creaUF(HttpServletRequest request, Model m, RedirectAttributes redirAttri, @RequestParam(name = "idMP") String idMP, @ModelAttribute(name = "ufSel") UF ufSel, @ModelAttribute(name = "modal") String modalAttr) {
        UF uf = new UF();
        MP mp = serviceMP.getMPByidMP(idMP);
        m.addAttribute("uf", uf);
        m.addAttribute("idMP", idMP);

        return "uf/creaUF";
    }

    @PostMapping("/guardaUF")
    public String guardaUF(@ModelAttribute(name = "uf") @Valid UF uf, BindingResult bindingResult, RedirectAttributes redirAttri, @RequestParam(name = "idMP") String idMP) {

        if (bindingResult.hasErrors()) {
            return "uf/creaUF";
        }else
        if (serviceMP.addUFaMP(idMP, uf)) {
            redirAttri.addFlashAttribute("msg", "UF afegida correctament");
        }

        return "redirect:/MP/llistaMP";
    }

    // - Editar les UF

    @GetMapping("/editaUFView")
    public String editaUFFrom(Model m, @ModelAttribute(name = "ufSel") UF uf, @ModelAttribute(name = "mp") MP mp, @ModelAttribute(name = "modal") String modalAttr, RedirectAttributes redirAttri, HttpServletRequest request) {

        String mp2 = request.getParameter("mp");
        String uf2 = request.getParameter("uf1");

        if (uf2 != null) {
            MP mp1 = serviceMP.getMPByidMP(mp2);
            UF uf1 = mp1.getUFByNum(uf2);
            m.addAttribute("editaUF", uf1);
            m.addAttribute("mp", mp1);
            m.addAttribute("modal", false);
        } else {
            m.addAttribute("editaUF", uf);
            m.addAttribute("mp", mp);
            m.addAttribute("modal", modalAttr);
        }

        return "uf/editaUF";
    }

    @PostMapping("/editaPreparaUF")
    public String editaPreparaUF(@ModelAttribute UF uf, @ModelAttribute(name = "idMP") String idMP, Model m, RedirectAttributes redirAttrs) {

        MP mp = serviceMP.getMPByidMP(idMP);
        redirAttrs.addFlashAttribute("ufSel", uf);
        redirAttrs.addFlashAttribute("mp", mp);
        redirAttrs.addFlashAttribute("modal", "1");

        return "redirect:/UF/editaUFView";
    }

    @PostMapping("/editaUF")
    public String editaUF(Model m, HttpServletRequest request, @ModelAttribute(name = "idMP") String idMP, @ModelAttribute(name = "editaUF") @Valid UF uf, BindingResult bindingResult, RedirectAttributes redirAttri) {

        MP mp = serviceMP.getMPByidMP(idMP);
        //redirAttri.addFlashAttribute("mp", numMP);

        if (bindingResult.hasErrors()) {
            m.addAttribute("oMP",mp);
            return "uf/editaUF";
        }else
        if (mp.editaUF(uf)) {
            redirAttri.addFlashAttribute("msg", "UF editada correctament");
        }

        return "redirect:/UF/llistaUF";
    }

    // - Elimina les UF

    @GetMapping("/removeUFView")
    public String removeUFView(Model m, RedirectAttributes redirAttrs, HttpServletRequest request) {

        String mp = request.getParameter("mp");

        String uf = request.getParameter("uf1");

        redirAttrs.addFlashAttribute("uf1", uf);
        redirAttrs.addFlashAttribute("mp", mp);
        redirAttrs.addFlashAttribute("modal", "1");
        System.out.println("Entra al modal");

        return "redirect:/UF/llistaUF";
    }

    @PostMapping("/removeUF")
    public String removeUF(Model m, RedirectAttributes redirAttri, HttpServletRequest request) {

        String mp = request.getParameter("mp");

        String uf = request.getParameter("uf1");


        MP mp1 = serviceMP.getMPByidMP(mp);


        UF uf1 = mp1.getUFByNum(uf);

        redirAttri.addFlashAttribute("mp", mp);

        if (mp1.removeUFtoMP(uf1)) {
            redirAttri.addFlashAttribute("msg", "UF eliminada correctament");
        }

        return "redirect:/UF/llistaUF";
    }
}
