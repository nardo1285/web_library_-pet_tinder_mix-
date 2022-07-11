/*
LibroServicio
Esta clase tiene la responsabilidad de llevar adelante las funcionalidades necesarias
para administrar libros (consulta, creación, modificación y dar de baja).
*/
package com.libreriaWeb.libreria.servicios;

import com.libreriaWeb.libreria.entidades.Autor;
import com.libreriaWeb.libreria.entidades.Editorial;
import com.libreriaWeb.libreria.entidades.Libro;
import com.libreriaWeb.libreria.errores.ErrorServicio;
import com.libreriaWeb.libreria.repositorios.repositorioLibro;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
public class LibroServicio {
    
    /*Le decimos al Servidor de aplicaciones que inicialice la siguiente variable*/
    @Autowired
    private repositorioLibro repoLibro;
    @Autowired
    private AutorServicio serviAutor;
    @Autowired
    private EditorialServicio serviEditorial;
    
    /*Yo voy a recibir datos de un formulario, y eso lo transformamos en una entidad
    en nuestro sistema */
    /*Transactional tiene que ir en todo método que manipule la base de datos,
    si no lo colocamos, funciona igual, pero la parte operativa interna funciona mal.
    Hace un duplicado del objeto, hasta que todo salga bien y ahí lo guarda
    Ver en el video la parte de Transactional (min 13:18, video Spring Adri 2), 
    que sin try-catch ya hace el rollback en caso de la excepción*/
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
    public Libro ingresarLibro (String titulo, String autor,Long isbn, String editorial,
                                Integer anio, Integer ejemplares) throws ErrorServicio{
                                /*Integer ejemplaresPrestados, Integer ejemplaresRestantes,*/
        /*Llamamos al método para validar el ingreso de datos*/
        verificar(isbn, titulo, anio, ejemplares, autor, editorial);
        
        Libro libro = new Libro();
        libro.setIsbn(isbn);
        libro.setAnio(anio);
        libro.setEjemplares(ejemplares);
        //libro.setEjemplaresPrestados(ejemplaresPrestados);
        //libro.setEjemplaresRestantes(ejemplares-ejemplaresPrestados);
        libro.setAlta(true);
        libro.setAutor(serviAutor.ingresarAutor(autor));
        System.out.println(libro.getAutor().toString()+"--");
        libro.setEditorial(serviEditorial.ingresarEditorial(editorial));
        System.out.println(libro.getEditorial().toString()+"--");
        /*Con esto le decimos al repositorio que lo guarde en la base de datos
        El repositorio se encagará transformarlo en una o mas tablas de la base de datos*/
        return repoLibro.save(libro);
        
    }
    
    @Transactional
    public Libro modificarLibro(String id, String idAutor, String idEditorial, Long isbn, String titulo, Integer anio, 
                                Integer ejemplares, String autor, String editorial) throws ErrorServicio{
     
        /*Llamamos al método para validar el ingreso de datos*/
        verificar(isbn, titulo, anio, ejemplares, autor, editorial);
        
        /*Uso la clase Optional para verificar que con el ID me trae realmente un Libro*/
        /*Buscamos el libro por ID en nuestro repositorio*/
        Optional<Libro> respuesta = repoLibro.findById(id);
        if (respuesta.isPresent()) {
            Libro libro = respuesta.get();
            
            /*Una vez encontrado, seteamos los nuevos valores */
            libro.setIsbn(isbn);
            libro.setTitulo(titulo);
            libro.setAnio(anio);
            libro.setEjemplares(ejemplares);
            libro.setAutor(serviAutor.ModificarAutor(idAutor, autor));
            libro.setEditorial(serviEditorial.ModificarEditorial(idEditorial, editorial));

            return repoLibro.save(libro);
        
        }else{
            throw new ErrorServicio("No se encontró el libro buscado para modificar.");
        }
    }    
    
    /*El método de eliminar no es el más indicado, ya que hay veces que se desea
    persistir ese dato, y no perder del todo la info. El ejercicio pide la baja
    por lo que este método quedará comentado, por si se necesita*/
//    
//    @Transactional
//    public void eliminarLibro(String id) throws ErrorServicio{
//     
//        /*Llamamos al método para validar el ingreso de datos
//        verificar(isbn, titulo, anio, ejemplares, autor, editorial);*/
//        if (id.isEmpty()) {
//            throw new ErrorServicio("El ISBN no puede ser nulo");
//        }
//        /*Uso la clase Optional para verificar que con el ID me trae realmente un Libro*/
//        /*Buscamos el libro por ID en nuestro repositorio*/
//        Optional<Libro> respuesta = repoLibro.findById(id);
//        if (respuesta.isPresent()) {
//            Libro libro = respuesta.get();
//            
//            repoLibro.delete(libro);
//        
//        }else{
//            throw new ErrorServicio("No se encontró el libro buscado para eliminar.");
//        }
//    }

    /*Aquí va el método para consultar libro por nombre*/
    @Transactional
    public void consultarLibro()  {
        
    }
    
    
    /*Para arrojar las excepciones correspondientes, antes de persistir en la BD*/
    public void verificar(Long isbn, String titulo, Integer anio, Integer ejemplares, String autor, String editorial)throws ErrorServicio {
        
        if (isbn == null || isbn ==0) {
            throw new ErrorServicio("El ISBN no puede ser nulo");
        }
        if (anio == null || anio ==0) {
            throw new ErrorServicio("Debe colocar un año de edición");
        }
        if (titulo == null || titulo.isEmpty()) {
            throw new ErrorServicio("El Título no puede ser nulo");
        }
        if (ejemplares ==0 || ejemplares == null) {
            throw new ErrorServicio("Debe ingresar la cantidad de libros");
        }
        if (autor == null) {
            throw new ErrorServicio("El Autor no está cargado");
        }
        if (editorial == null) {
            throw new ErrorServicio("La Editorial no está cargada");
        }
        
    }

    

}
    
