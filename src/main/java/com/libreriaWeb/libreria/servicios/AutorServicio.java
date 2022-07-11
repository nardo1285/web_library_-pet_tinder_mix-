
package com.libreriaWeb.libreria.servicios;

import com.libreriaWeb.libreria.entidades.Autor;
import com.libreriaWeb.libreria.errores.ErrorServicio;
import com.libreriaWeb.libreria.repositorios.repositorioAutor;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
public class AutorServicio {
    
    @Autowired
    private repositorioAutor autorRepositorio;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
    public Autor ingresarAutor(String nombre) throws ErrorServicio {

        validar(nombre);
        
        Autor autor = new Autor();
        
        autor.setNombre(nombre);
        autor.setAlta(true);
        
        return autorRepositorio.save(autor);
    }

    
    @Transactional
    public Autor ModificarAutor(String id, String nombre) throws ErrorServicio {

        validar(nombre);
        if (id == null || id.isEmpty()) {
            throw new ErrorServicio("El ID no puede ser nulo o estar vacío.");
        }
        
        Optional<Autor> respuesta = autorRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Autor autor = respuesta.get();
            autor.setNombre(nombre);
            autor.setAlta(true);
            autorRepositorio.save(autor);

            return autor;    
        } else {
            throw new ErrorServicio("El Autor no existe.");

        }
            
    }

    @Transactional
    public Autor BajaAutor(String id) throws ErrorServicio {

        Optional<Autor> respuesta = autorRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Autor autor = respuesta.get();
            autor.setAlta(Boolean.FALSE);
            return autorRepositorio.save(autor);

        } else {
            throw new ErrorServicio("El Autor no existe");

        }

    }

    
    public void validar(String nombre) throws ErrorServicio {
        if (nombre == null || nombre.isEmpty()) {
            throw new ErrorServicio("El nombre de autor no puede ser nulo o estar vacío");
        }

    }

}
  

