
package com.libreriaWeb.libreria.entidades;


import com.sun.istack.NotNull;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Libro {

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  private String id;

  /*Para que no permita que el número sea nulo y que sea único*/
  @NotNull
  @Column(unique = true)
  private Long isbn;

  @Column(unique = true)
  private String titulo;
  private Integer anio;
  private Integer ejemplares;
  private Integer ejemplaresPrestados;
  private Integer ejemplaresRestantes;
  private Boolean alta;

  @OneToOne
  private Autor autor;
  @OneToOne
  private Editorial editorial;


  public String getId() {
    return id;
  }

  public Long getIsbn() {
    return isbn;
  }

  public String getTitulo() {
    return titulo;
  }

  public Integer getAnio() {
    return anio;
  }

  public Integer getEjemplares() {
    return ejemplares;
  }

  public Integer getEjemplaresPrestados() {
    return ejemplaresPrestados;
  }

  public Integer getEjemplaresRestantes() {
    return ejemplaresRestantes;
  }

  public Boolean getAlta() {
    return alta;
  }

  public Autor getAutor() {
    return autor;
  }

  public Editorial getEditorial() {
    return editorial;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setIsbn(Long isbn) {
    this.isbn = isbn;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public void setAnio(Integer anio) {
    this.anio = anio;
  }

  public void setEjemplares(Integer ejemplares) {
    this.ejemplares = ejemplares;
  }


  public void setEjemplaresPrestados(Integer ejemplaresPrestados) {
    this.ejemplaresPrestados = ejemplaresPrestados;
  }

  public void setEjemplaresRestantes(Integer ejemplaresRestantes) {
    this.ejemplaresRestantes = ejemplaresRestantes;
  }

  public void setAlta(Boolean alta) {
    this.alta = alta;
  }

  public void setAutor(Autor autor) {
    this.autor = autor;
  }

  public void setEditorial(Editorial editorial) {
    this.editorial = editorial;
  }


}
