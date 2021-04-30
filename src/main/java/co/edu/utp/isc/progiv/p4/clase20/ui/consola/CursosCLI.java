/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.isc.progiv.p4.clase20.ui.consola;

import co.edu.utp.isc.progiv.p4.clase20.excepciones.BaseDatosException;
import co.edu.utp.isc.progiv.p4.clase20.excepciones.NoEncontradoException;
import co.edu.utp.isc.progiv.p4.clase20.logica.CursoFacade;
import co.edu.utp.isc.progiv.p4.clase20.ui.consola.utils.EntradaTecladoUtils;


/**
 *
 * @author Sebastian
 */
public class CursosCLI {
    
    private static CursoFacade facade ;
    
    static {
        facade = new CursoFacade();
    }
    
    public static void menuCursos() {
        String opcion;
        do {
            System.out.println("\n\n");
            System.out.println("========================================================");
            System.out.println(" Gestión de Cursos");
            System.out.println("--------------------------------------------------------");
            System.out.println("1. Listado de cursos");
            System.out.println("2. Consultar un curso por su CÓDIGO");
            System.out.println("3. Agregar un curso");
            System.out.println("4. Eliminar un curso por su CÓDIGO");
            System.out.println("5. Modificar un curso por su CÓDIGO");
            System.out.println("0. Salir");
            System.out.println("========================================================");
            opcion = EntradaTecladoUtils.obtenerOpcion("1,2,3,4,5,0".split(","));
            switch (opcion) {
                case "1":
                    listarCursos();
                    break;
                case "2":
                    consultarCurso();
                    break;
                case "3":
                    agregarCurso();
                    break;
                case "4":
                    eliminarCurso();
                    break;
                case "5":
                    modificarCurso();
                    break;
            }
        } while (!opcion.equals("0"));
        System.out.println("Hasta luego!");
    
    }
    
    private static void listarCursos() {
        System.out.println("\n\n");
        System.out.println("========================================================");
        System.out.println(" Listado de Cursos");
        System.out.println("========================================================");
        try {
            facade.listarCursos()
                    .forEach(curso -> {
                        System.out.println(curso.toString());
                    });
        } catch (BaseDatosException ex) {
            System.err.println("Error con base de datos: " + ex.getMessage());
        } catch (NoEncontradoException ex) {
            System.err.println(ex.getMessage());
        }

        EntradaTecladoUtils.presionaParaContinuar();
    }
    
    private static void consultarCurso() {
        System.out.println("\n\n");
        System.out.println("========================================================");
        System.out.println(" Consultar Curso");
        System.out.println("========================================================");
        String valor;
        do {
            valor = EntradaTecladoUtils.obtenerCadena("Ingrese el código del curso: ");

            if (valor == null
                    || valor.trim().isBlank()) {
                System.err.println("Debe ingresar una identificación válida");
                valor = null;
            }
        } while (valor == null);
        
        Long id = Long.valueOf(valor);
        try {
            System.out.println(facade.consultarCurso(id));
        } catch (NoEncontradoException ex) {
            System.err.println(ex.getMessage());
        }
        EntradaTecladoUtils.presionaParaContinuar();
    }
    
    private static void agregarCurso() {
        System.out.println("\n\n");
        System.out.println("========================================================");
        System.out.println(" Agregar un Curso");
        System.out.println("========================================================");
        String codigo = EntradaTecladoUtils.obtenerCadena("Ingresa por favor el código del curso:");
        String nombreDeCurso = EntradaTecladoUtils.obtenerCadena("Ingresa por favor el nombre del curso:");
        String profe = EntradaTecladoUtils.obtenerCadena("Ingresa por favor el nombre del profesor o profesora que dicta el curso:");
        String facultad = EntradaTecladoUtils.obtenerCadena("Ingresa por favor el nombre de la facultad a la que pertenece el curso:");
        String creditos = EntradaTecladoUtils.obtenerCadena("Ingresa por favor la cantidad de créditos del curso:");
        
        try {
            var curso = facade.guardarCurso(codigo,nombreDeCurso,profe,facultad,creditos);
            System.out.println("Se ha creado el curso:\n" + curso);
        } catch (BaseDatosException ex) {
            System.err.println("Error al guardar el curso: " + ex.getMessage());
        }
        EntradaTecladoUtils.presionaParaContinuar();
    }

    private static void eliminarCurso() {
        System.out.println("\n\n");
        System.out.println("========================================================");
        System.out.println(" Eliminar curso ");
        System.out.println("========================================================");
        String valor;
        do {
            valor = EntradaTecladoUtils.obtenerCadena("Ingrese el id del curso a eliminar: ");

            if (valor == null
                    || valor.trim().isBlank()) {
                System.err.println("Debe ingresar un código válido");
                valor = null;
            }
        } while (valor == null);
        
        Long id = Long.valueOf(valor);
        try {
            facade.eliminarCurso(id);
        } catch (NoEncontradoException ex) {
            System.err.println(ex.getMessage());
        }
        EntradaTecladoUtils.presionaParaContinuar();
    }
    
    private static void modificarCurso() {
        System.out.println("\n\n");
        System.out.println("========================================================");
        System.out.println(" Modificar curso ");
        System.out.println("========================================================");
        String valor;
        do {
            valor = EntradaTecladoUtils.obtenerCadena("Ingrese la el id del curso a eliminar: ");

            if (valor == null
                    || valor.trim().isBlank()) {
                System.err.println("Debe ingresar un código válido");
                valor = null;
            }
        } while (valor == null);
        
        System.out.println("========================================================");
        System.out.println(" Si no quiere hacer cambios, deje el campo vacio y presione ENTER ");
        System.out.println("========================================================");
        
        String codigo = EntradaTecladoUtils.obtenerCadena("Ingresa por favor el codigo del curso:");
        String nombreDeCurso = EntradaTecladoUtils.obtenerCadena("Ingresa por favor el nombre del curso:");
        String profe = EntradaTecladoUtils.obtenerCadena("Ingresa por favor el nombre del profesor o profesora que dicta el curso:");
        String facultad = EntradaTecladoUtils.obtenerCadena("Ingresa por favor el nombre de la facultad a la que pertenece el curso:");
        String creditos = EntradaTecladoUtils.obtenerCadena("Ingresa por favor la cantidad de créditos del curso:");
        Long id = Long.valueOf(valor);
        try {
            facade.modificarCurso(id,codigo,nombreDeCurso,profe,facultad,creditos);
        } catch (NoEncontradoException ex) {
            System.err.println(ex.getMessage());
        }
        EntradaTecladoUtils.presionaParaContinuar();
    }
}
