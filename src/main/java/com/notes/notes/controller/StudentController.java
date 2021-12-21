package com.notes.notes.controller;

import com.notes.notes.model.STUDENT.Student;
import com.notes.notes.service.STUDENT.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDate;
import java.time.Period;

@ComponentScan(basePackages = {"com.notes.notes.controller"})
@Controller
@RequestMapping(value = "/Student", method = RequestMethod.GET)
public class StudentController {

    @Autowired
    private IStudentService serviceStudent;

    // - Llistar els MP

    @GetMapping("/llistaStudent")
    public String llistaStudent(Model m, @ModelAttribute(name = "modal") String modalAttr, @ModelAttribute(name = "studentSel") Student studentSel) {
        m.addAttribute("llistaStudent", serviceStudent.getAllStudent());
        m.addAttribute("modal", modalAttr);

        if (modalAttr.equals("")) {
            m.addAttribute("studentSel", new Student());
        } else {
            m.addAttribute("studentSel", studentSel);
        }

        return "student/llistaStudent";
    }

    // - Crear els MP

    @GetMapping("/creaStudent")
    public String creaStudent(Model m, RedirectAttributes redirAttri, @ModelAttribute(name = "studentSel") Student studentSel, @ModelAttribute(name = "modal") String modalAttr) {
        Student student = new Student();
        m.addAttribute("creaStudent", student);

        if (student != null) {
            redirAttri.addFlashAttribute("creaStudent", student);
            redirAttri.addFlashAttribute("modal", "1");
        }

        return "student/creaStudent";
    }

    @PostMapping("/guardaStudent")
    public String guardaStudent(@ModelAttribute(name = "creaStudent") @Valid Student student, BindingResult bindingResult, RedirectAttributes redirAttri) {

        boolean esMajor = checkIfIsOlder(student.getDataNaix());

        if (!esMajor){
            bindingResult.addError(new FieldError("student","dataNaix","Registre no permès per menors d'edat"));
        }

        if (bindingResult.hasErrors()) {
            return "student/creaStudent";
        }else
            if (serviceStudent.addStudent(student)) {
            redirAttri.addFlashAttribute("msg", "Student afegit correctament");
        }

        return "redirect:/Student/llistaStudent";
    }

    // - Editar els MP

    @GetMapping("/editaStudentView")
    public String editaStudentForm(HttpServletRequest request, Model m, @ModelAttribute(name = "studentSel") Student student, @ModelAttribute(name = "modal") String modalAttr) {

        String dni = request.getParameter("idStudent");

        if (dni != null) {
            Student studentSeleccionat = serviceStudent.getStudentByidStudent(dni);
            m.addAttribute("editaStudent", studentSeleccionat);
            m.addAttribute("modal", false);
        } else {
            m.addAttribute("editaStudent", student);
            m.addAttribute("modal", modalAttr);
        }

        return "student/editaStudent";
    }

    @PostMapping("/editaPreparaStudent")
    public String editaPreparaStudent(@ModelAttribute Student student, Model m, RedirectAttributes redirAttrs) {

        redirAttrs.addFlashAttribute("studentSel", student);
        redirAttrs.addFlashAttribute("modal", "1");

        return "redirect:/Student/editaStudentView";
    }

    @PostMapping("/editaStudent")
    public String editaStudent(Model m, @ModelAttribute(name = "editaStudent") @Valid Student student, BindingResult bindingResult, RedirectAttributes redirAttri) {

        boolean esMajor = checkIfIsOlder(student.getDataNaix());

        if (!esMajor){
            bindingResult.addError(new FieldError("student","dataNaix","Registre no permès per menors d'edat"));
        }

        if (bindingResult.hasErrors()) {
            return "student/editaStudent";
        }else if (serviceStudent.editaStudent(student)) {
            redirAttri.addFlashAttribute("msg", "Student editat correctament");
        }

        return "redirect:/Student/llistaStudent";
    }


    // - Eliminar els MP

    @PostMapping("/removeStudent")
    public String removeStudent(HttpServletRequest request, RedirectAttributes redirAttri, Student student) {

        String dni = request.getParameter("student");

        if (serviceStudent.removeStudent(student)) {
            redirAttri.addFlashAttribute("msg", "Student eliminat correctament");
        }

        return "redirect:/Student/llistaStudent";
    }

    @GetMapping("/removeView")
    public String removeStudentView(Model m, @RequestParam(name = "idStudent") String idStudent, RedirectAttributes redirAttrs) {

        Student student = serviceStudent.getStudentByidStudent(idStudent);

        redirAttrs.addFlashAttribute("studentSel", student);
        redirAttrs.addFlashAttribute("modal", "1");

        return "redirect:/Student/llistaStudent";
    }

    public boolean checkIfIsOlder(LocalDate ld){

        Period p = Period.between(ld,LocalDate.now());

        int anys = p.getYears();


        if (anys>17){
            return true;
        }else {
            return false;
        }
    }
}
