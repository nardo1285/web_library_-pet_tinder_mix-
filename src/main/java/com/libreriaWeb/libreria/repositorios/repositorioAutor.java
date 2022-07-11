
package com.libreriaWeb.libreria.repositorios;

import com.libreriaWeb.libreria.entidades.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface repositorioAutor extends JpaRepository<Autor, String>{
    
    
}
