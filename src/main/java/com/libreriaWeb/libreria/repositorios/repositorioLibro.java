
package com.libreriaWeb.libreria.repositorios;

import com.libreriaWeb.libreria.entidades.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface repositorioLibro extends JpaRepository<Libro, String>{

    @Query("SELECT c FROM Libro c WHERE c.titulo = :titulo")
    public Libro buscarPorId(@Param("titulo")String titulo);
      
    
    
}
