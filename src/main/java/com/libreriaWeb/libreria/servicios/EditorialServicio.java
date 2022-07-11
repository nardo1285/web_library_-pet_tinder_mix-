
package com.libreriaWeb.libreria.servicios;

import com.libreriaWeb.libreria.entidades.Editorial;
import com.libreriaWeb.libreria.errores.ErrorServicio;
import com.libreriaWeb.libreria.repositorios.repositorioEditorial;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class EditorialServicio {
    
    @Autowired
    private repositorioEditorial editorialRepositorio;
    
    /*Todo método que maneje la base de datos, debe tener @Transactional)*/
    @Transactional
    public Editorial ingresarEditorial(String nombre) throws ErrorServicio {

        validar(nombre);
        
        Editorial editorial = new Editorial();
        
        editorial.setNombre(nombre);
        editorial.setAlta(Boolean.TRUE);
        
        editorialRepositorio.save(editorial);
        
        return editorial;

    }

    @Transactional
    public Editorial ModificarEditorial(String id, String nombre) throws ErrorServicio {

        validar(nombre);
        if (id == null || id.isEmpty()) {
            throw new ErrorServicio("El ID no puede ser nulo o estar vacío.");
        }
        
        Optional<Editorial> respuesta = editorialRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Editorial editorial = respuesta.get();
            editorial.setNombre(nombre);
            editorial.setAlta(true);
            editorialRepositorio.save(editorial);

            return editorial;    
        } else {
            throw new ErrorServicio("El Editorial no existe.");

        }
            
    }

    @Transactional
    public void BajaEditorial(String id) throws ErrorServicio {

        Optional<Editorial> respuesta = editorialRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Editorial editorial = respuesta.get();
            editorialRepositorio.delete(editorial);

        } else {
            throw new ErrorServicio("El Editorial no existe");

        }

    }

    
    public void validar(String nombre) throws ErrorServicio {
        if (nombre == null || nombre.isEmpty()) {
            throw new ErrorServicio("El nombre de editorial no puede ser nulo o estar vacío");
        }

    }

}

        

