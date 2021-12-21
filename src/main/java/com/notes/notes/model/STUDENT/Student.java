package com.notes.notes.model.STUDENT;

import com.notes.notes.model.MATR.Matr;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Objects;

//MP -> UF -> STUDENT -> MATR -> CURS -> PLA

//https://spring.io/guides/gs/validating-form-input/

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int idStudent;
    @NotBlank(message = "El camp no es pot deixar en blanc")
    @Size(min = 8, message = "El dni ha de tenir mínim 8 caracters")
    @Pattern(regexp = "[0-9]{8}[A-Za-z]{1}", message = "El format del dni no és correcte")
    private String dni;
    @NotBlank(message = "El camp no es pot deixar en blanc")
    @Size(min = 2, max = 10, message = "El nom ha de tenir entre 2 i 10 caracters")
    private String nom;
    @NotBlank(message = "El camp no es pot deixar en blanc")
    @Size(min = 2, max = 10, message = "El cognom ha de tenir entre 2 i 10 caracters")
    private String cognom;
    @NotBlank(message = "El camp no es pot deixar en blanc")
    @Pattern(regexp = "^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$", message = "El format del correu no és correcte")
    private String mail;
    @NotNull(message = "El camp no es pot deixar en blanc")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past(message = "La data no pot ser superior a la d'avui")
    private LocalDate dataNaix;

    //private Matr matr;

    public Student() {
    }

    public Student(String dni, String nom, String cognom, LocalDate dataNaix, String mail) {
        this.dni = dni;
        this.nom = nom;
        this.cognom = cognom;
        this.dataNaix = dataNaix;
        this.mail = mail;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCognom() {
        return cognom;
    }

    public void setCognom(String cognom) {
        this.cognom = cognom;
    }

    public LocalDate getDataNaix() {
        return dataNaix;
    }

    public void setDataNaix(LocalDate dataNaix) {
        this.dataNaix = dataNaix;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(dni, student.dni) && Objects.equals(nom, student.nom) && Objects.equals(cognom, student.cognom) && Objects.equals(dataNaix, student.dataNaix) && Objects.equals(mail, student.mail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni, nom, cognom, dataNaix, mail);
    }

    public String getDataNaixamentString(){
        return this.dataNaix.getDayOfMonth()+"/"+this.dataNaix.getMonthValue()+"/"+this.dataNaix.getYear();
    }

    @Override
    public String toString() {
        return "Student{" +
                "idStudent=" + idStudent +
                ", dni='" + dni + '\'' +
                ", nom='" + nom + '\'' +
                ", cognom='" + cognom + '\'' +
                ", mail='" + mail + '\'' +
                ", dataNaix=" + dataNaix +
                '}';
    }
}
