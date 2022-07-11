
package com.libreriaWeb.libreria.controladores;

import com.libreriaWeb.libreria.entidades.Autor;
import com.libreriaWeb.libreria.entidades.Editorial;
import com.libreriaWeb.libreria.errores.ErrorServicio;
import com.libreriaWeb.libreria.servicios.AutorServicio;
import com.libreriaWeb.libreria.servicios.LibroServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
/*Ahora se va a indicar cual es la URL que va a "escuchar a este controlador"
Cada vez que pongan '/' este método se va a activar*/
@RequestMapping("/")
public class PortalControlador {
    
    @Autowired
    private LibroServicio libroServi;
    @Autowired
    private AutorServicio autorServi;

    /*Método de http que va a responder a '/', es decir, cuando entre a la / del servidor
    va a ejecutar todo lo que esté dentro de este método y va a devolver la vista que quiero 
    que renderice, en este caso es 'index' */
    /*Adaptaremos lo traído del Tinder de mascotas a la librería, según necesidad*/
    @GetMapping("/")
    public String index(){
        return "index.html";
    }
    
    @GetMapping("/inicio")
    public String inicio(){
        return "inicio.html";
    }

    @GetMapping("/registrar")
    public String registro(){
        return "registro.html";
    }
    
    //La notación @RequestParam hace obligatorio que ese valor llegue para ejecutar el método
    //La notación @ModelMap nos servirá para arrojar un mensaje de que la carga de datos
    //ha sido ingresada con éxito
    
    @PostMapping("/registrar")
    public String guardar(ModelMap modelo, @RequestParam String titulo, @RequestParam String autor, 
           Long isbn, @RequestParam String editorial, Integer anio, Integer ejemplares){
        
        System.out.println("Título: " +titulo);
        System.out.println("Autor: " +autor);
        System.out.println("ISBN: " +isbn);
        //System.out.println("Editorial: " +editorial);
//        System.out.println("Año: " +anio);
//        System.out.println("Ejemplares: " +ejemplares);
//        
        try{
            System.out.println("ingresó al try");
            libroServi.ingresarLibro(titulo, autor, isbn, editorial, anio, ejemplares);
            modelo.put("exito", "Ingreso exitoso!");
            return "registro.html";
            
        }catch(ErrorServicio e){
            System.out.println("ingresó al catch");
            System.out.println(e);
            modelo.put("error", e);
            
            return "registro.html";
        }
    
            
            
        
    }
            
            
    @GetMapping("/login")
    public String login(){
        return "login.html";
    }

    @GetMapping("/exito")
    public String exito(){
        return "exito.html";
    }
    @GetMapping("/listado")
    public String listado(){
        return "listado.html";
    }

}

/*

*/