/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.progiv.p4.clase20.logica;

import co.edu.utp.isc.progiv.p4.clase20.datos.dao.CursoDao;
import co.edu.utp.isc.progiv.p4.clase20.datos.entidades.Curso;
import co.edu.utp.isc.progiv.p4.clase20.excepciones.BaseDatosException;
import co.edu.utp.isc.progiv.p4.clase20.excepciones.NoEncontradoException;
import java.util.List;

/**
 *
 * @author Sebastian
 */
public class CursoFacade {
    
    private final CursoDao cursoDao;

    public CursoFacade() {
        cursoDao = CursoDao.getInstance();
    }
    
    public List<Curso> listarCursos() throws BaseDatosException, NoEncontradoException {
        var listado = cursoDao.listar();

        if (listado == null || listado.isEmpty()) {
            throw new NoEncontradoException("No existen cursos en la base de datos");
        }

        return listado;
    }
    
    public Curso consultarCurso(Long id) throws NoEncontradoException {
        try {
            return cursoDao.consultar(id);
        } catch (BaseDatosException ex) {
            throw new NoEncontradoException("No existe el curso con id " 
                                            + id);
        }
    }
    
    public Curso guardarCurso(String codigo, String nombreDeCurso, String profe,
                                   String facultad, String creditos) throws BaseDatosException {
        return cursoDao.guardar(codigo,nombreDeCurso,profe,facultad,creditos);
    }
    
    public void eliminarCurso(Long id) throws NoEncontradoException {
        try {
            cursoDao.eliminar(id);
            System.out.println("Curso eliminado exitosamente");
        } catch (BaseDatosException ex) {
            throw new NoEncontradoException("No existe el curso con id " 
                                            + id);
        }
    }
    
    public void modificarCurso(Long id, String codigo, String nombreDeCurso, String profe,
                               String facultad, String creditos) 
                                    throws NoEncontradoException {
        try {
            cursoDao.modificar(id,codigo,nombreDeCurso,profe,facultad,creditos);
            System.out.println("Curso modificado con exito");
        } catch (BaseDatosException ex) {
            throw new NoEncontradoException("No existe el curso con id "
                                            + id);
        }
    }
}
