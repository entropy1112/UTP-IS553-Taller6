/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.progiv.p4.clase20.datos.dao;

import co.edu.utp.isc.progiv.p4.clase20.datos.entidades.Curso;
import co.edu.utp.isc.progiv.p4.clase20.datos.entidades.Estudiante;
import co.edu.utp.isc.progiv.p4.clase20.excepciones.BaseDatosException;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author Sebastian
 */
public class CursoDao {
    
    private static CursoDao instancia;

    public static CursoDao getInstance() {
        if(instancia == null){
            instancia = new CursoDao();
        }
        return instancia;
    }
    
    private final EntityManagerFactory emf;
    
    public CursoDao() {
        emf = Persistence.createEntityManagerFactory("clase20-pu");
    }
    
    public Curso guardar(String codigo, String nombreDeCurso, String profe,
                         String facultad, String creditos) throws BaseDatosException {
        
        var em = emf.createEntityManager();
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
            et.begin();
            
            Curso curso = new Curso();
            curso.setCodigo(codigo);
            curso.setNombreDeCurso(nombreDeCurso);
            curso.setProfesor(profe);
            curso.setFacultad(facultad);
            curso.setCred(creditos);
            
            em.persist(curso);
            
            et.commit();
            
            return curso;
        } catch (Exception e) {
            if (et != null) {
                et.rollback();
            }
            throw new BaseDatosException(e.getMessage());
        } finally {
            em.close();
        }
        
    }
    
    public List<Curso> listar() throws BaseDatosException { 
        
        var em = emf.createEntityManager();
        var query = em.createQuery("select e from Curso e", Curso.class);
        
        try {
           return query.getResultList();
        } catch (Exception e) {
            throw new BaseDatosException(e.getMessage());
        } finally {
            em.close();
        }
    }
    
    public Curso consultar(Long id) throws BaseDatosException {
        
        var em = emf.createEntityManager();
        Curso curso = null;
        try {
            var query = em.createQuery("select e from Curso e where e.id = :id", Curso.class);
            query.setParameter("id", id);
            
            curso = query.getSingleResult();
        } catch (Exception e) {
            throw new BaseDatosException(e.getMessage());
        } finally {
            em.close();
        }
        return curso;
    }
    
    public void eliminar (Long id) throws BaseDatosException {
        var em = emf.createEntityManager();
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
            et.begin();

            Curso curso = em.find(Curso.class, id);
            em.remove(curso);
            et.commit();
        } catch (Exception e) {
            throw new BaseDatosException(e.getMessage());
        } finally {
            em.close();
        }
         
    }
    
    public void modificar (Long id, String codigo, String nombreDeCurso, String profe,
                           String facultad, String creditos) throws BaseDatosException {
        var em = emf.createEntityManager();
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
            et.begin();
            Curso curso = em.find(Curso.class,id);
            
            if(!"".equals(codigo)){
                curso.setCodigo(codigo);
            }
            if(!"".equals(nombreDeCurso)){
                curso.setNombreDeCurso(nombreDeCurso);
            }
            if(!"".equals(profe)){
                curso.setProfesor(profe);
            }
            if(!"".equals(facultad)){
                curso.setFacultad(facultad);
            }
            if(!"".equals(creditos)){
                curso.setCred(creditos);
            }
            
            em.merge(curso);
            et.commit();
        } catch (Exception e) {
            throw new BaseDatosException(e.getMessage());
        } finally {
            em.close();
        }
    }
    
}
