
package com.libreriaWeb.libreria.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Autor {

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  private String id;
  private String nombre;
  private Boolean alta;


  public String getId() {
    return id;
  }

  public String getNombre() {
    return nombre;
  }

  public Boolean getAlta() {
    return alta;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public void setAlta(Boolean alta) {
    this.alta = alta;
  }


}
