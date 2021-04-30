/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.progiv.p4.clase20.datos.entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Sebastian
 */
@Entity
@Table(name = "Curso")
public class Curso implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;
            
    @Column(name = "Codigo del curso")
    private String codigo;
    
    @Column(name = "NCurso")
    private String nombreDeCurso;
    
    @Column(name = "NProfe")
    private String profesor;
    
    @Column(name = "Facultad")
    private String facultad;
    
    @Column(name = "Creditos")
    private String cred;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCred() {
        return cred;
    }

    public void setCred(String cred) {
        this.cred = cred;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombreDeCurso() {
        return nombreDeCurso;
    }

    public void setNombreDeCurso(String nombreDeCurso) {
        this.nombreDeCurso = nombreDeCurso;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }


    public String getFacultad() {
        return facultad;
    }

    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }

    @Override
    public String toString() {
        return "Curso{" + "codigo=" + codigo 
                + ", nombreDeCurso=" + nombreDeCurso 
                + ", profesor=" + profesor 
                + ", facultad=" + facultad 
                + ", cred=" + cred + '}';
    }
    
}
