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
    public String llistaUF(Model m, @ModelAttribute(name = "modal") String modalAttr,  @ModelAttribute(name = "ufSel") UF ufSel, @ModelAttribute("mp") MP mp, @ModelAttribute(name = "idMP") String idMP, @ModelAttribute(name = "uf1") String numUF) {

        MP mp1;

        if (ufSel.getIdUF()==0){
            mp1 = serviceMP.getMPByidMP(String.valueOf(mp.getIdMP()));
        }else {
            mp1 = ufSel.getMp();
        }

        m.addAttribute("mp", mp1);

        if (modalAttr != null){
            m.addAttribute("modal", modalAttr);
            m.addAttribute("ufSel", ufSel);
        }else {
            m.addAttribute("ufSel", new UF());
        }

        m.addAttribute("mp", serviceMP.getMPByidMP(idMP));

        return "uf/llistaUF";
    }

    // - Crea les UF

    @GetMapping("/creaUF")
    public String creaUF(Model m, @RequestParam(name = "idMP") String idMP, @ModelAttribute(name = "ufSel") UF ufSel, @ModelAttribute(name = "modal") String modalAttr) {

        MP mp = serviceMP.getMPByidMP(idMP);
        UF uf = new UF();
        uf.setMp(mp);
        System.out.println( "AQUESTA ES LA MP: " +mp);
        m.addAttribute("uf", uf);

        return "uf/creaUF";
    }

    @PostMapping("/guardaUF")
    public String guardaUF(@ModelAttribute(name = "uf") @Valid UF uf, BindingResult bindingResult, RedirectAttributes redirAttri) {

        System.out.println(uf);

        if (bindingResult.hasErrors()) {
            return "uf/creaUF";
        }else
        if (serviceUF.addUF(uf)) {
            redirAttri.addFlashAttribute("msg", "UF afegida correctament");
            redirAttri.addFlashAttribute("mp", uf.getMp().getIdMP());
        }

        return "redirect:/mp/llistaMP";
    }

    // - Editar les UF

    @GetMapping("/editaUFView")
    public String editaUFFrom(Model m, @ModelAttribute(name = "ufSel") UF uf, @ModelAttribute(name = "mp") MP mp, @ModelAttribute(name = "modal") String modalAttr, RedirectAttributes redirAttri, HttpServletRequest request) {

        String uf2 = request.getParameter("uf1");

        if (uf2 != null) {
            UF uf1 = serviceUF.getUFByidUF(Integer.parseInt(uf2));
            System.out.println("Aquesta es la uf que recupero: "+uf1);
            m.addAttribute("ufSel", uf1);
            m.addAttribute("modal", false);
        } else {
            m.addAttribute("ufSel", uf);
            System.out.println("Segona uf que recupero: "+uf);
            m.addAttribute("modal", modalAttr);
        }

        return "uf/editaUF";
    }

    @PostMapping("/editaPreparaUF")
    public String editaPreparaUF(@ModelAttribute UF uf, @ModelAttribute(name = "idMP") String idMP, Model m, RedirectAttributes redirAttrs) {

        //MP mp = serviceMP.getMPByidMP(idMP);
        redirAttrs.addFlashAttribute("ufSel", uf);
        redirAttrs.addFlashAttribute("mp", uf.getMp());

        System.out.println("UF recuperada del modal: " + uf);

        //redirAttrs.addFlashAttribute("mp", mp);
        redirAttrs.addFlashAttribute("modal", "1");

        return "redirect:/UF/editaUFView";
    }

    @PostMapping("/editaUF")
    public String editaUF(Model m, HttpServletRequest request, @ModelAttribute(name = "idMP") String idMP, @ModelAttribute(name = "ufSel") UF uf, BindingResult bindingResult, RedirectAttributes redirAttri) {

        uf.setMp(uf.getMp());

        System.out.println("al guardar: "+uf);

        if (bindingResult.hasErrors()) {
            return "uf/editaUF";
        }


        if (serviceUF.editaUF(uf)) {
            redirAttri.addFlashAttribute("msg", "UF editada correctament");
            redirAttri.addFlashAttribute("mp", uf.getMp().getIdMP());
        }

        return "redirect:/UF/llistaUF";
    }

    // - Elimina les UF

    @GetMapping("/removeUFView")
    public String removeUFView(HttpServletRequest request, RedirectAttributes redirAttrs) {

        String uf2 = request.getParameter("uf1");

        UF uf1 = serviceUF.getUFByidUF(Integer.parseInt(uf2));

        redirAttrs.addFlashAttribute("ufSel", uf1);
        redirAttrs.addFlashAttribute("idMP", uf1.getMp().getIdMP());

        System.out.println("UF recuperada del modal: " + uf1);

        redirAttrs.addFlashAttribute("modal", "1");
        System.out.println("Entra al modal");

        return "redirect:/UF/llistaUF";
    }

    @PostMapping("/removeUF")
    public String removeUF(RedirectAttributes redirAttri, HttpServletRequest request) {

        String idUF = request.getParameter("idUF");

        UF uf1 = serviceUF.getUFByidUF(Integer.parseInt(idUF));

        if (serviceUF.removeUF(uf1)) {
            redirAttri.addFlashAttribute("msg", "UF eliminada correctament");
            redirAttri.addFlashAttribute("idMP", uf1.getMp().getIdMP());
        }

        return "redirect:/UF/llistaUF";
    }
}
