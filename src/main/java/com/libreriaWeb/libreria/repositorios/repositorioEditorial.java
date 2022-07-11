
package com.libreriaWeb.libreria.repositorios;

import com.libreriaWeb.libreria.entidades.Editorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface repositorioEditorial extends JpaRepository<Editorial, String>{
    
    
}
