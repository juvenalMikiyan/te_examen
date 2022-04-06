
package com.emergentes.controlador;

import com.emergentes.modelo.Producto;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author MIGUEL
 */
@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
       HttpSession ses = request.getSession();
       
       
       if(ses.getAttribute("listapro") == null)
        {
            ArrayList<Producto> listaux =new  ArrayList<Producto>();
            ses.setAttribute("listapro", listaux);
            
        }
       
       
       String op = request.getParameter("op");
       String opcion = (op != null) ? op :"view";
       
       
        Producto obj1 = new Producto();
        
        //creamos valores para recorrer el array
        int id, pos;
       
       //aqui creamos la varibale lista para recoorre la lista del vector
        ArrayList<Producto> lista =(ArrayList<Producto>)ses.getAttribute("listapro");
        
       
       
        switch(opcion)
        {   
             case "nuevo":
                request.setAttribute("miProducto", obj1);
                request.getRequestDispatcher("editar.jsp").forward(request, response);
                break;
                
             case "editar":
                 id= Integer.parseInt (request.getParameter("id"));
                 //averiguamos en que lugar se encuentra el elemetno la posicion para objtener el elemento
                 pos = buscarIndice(request,id);
                 obj1 = lista.get(pos);
                 request.setAttribute("miProducto", obj1);
                 request.getRequestDispatcher("editar.jsp").forward(request, response);
                 
                 break;
                 
             case "eliminar":
                 
                 //convertimos el id a intero, para ello se utiliza intger
                id= Integer.parseInt (request.getParameter("id"));
                 //averiguamos en que lugar se encuentra para objtener el elemento
                 pos = buscarIndice(request,id);
                 lista.remove(pos);//elimina la unave vez encontrada la posisico
                 ses.setAttribute("listapro", lista);
                 response.sendRedirect("index.jsp");//despues del eliminado vuelve a motrar ;a tabla
                 break;
            case "view":
                response.sendRedirect("index.jsp");
        }
       
       
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession ses = request.getSession();
        ArrayList<Producto> Lista=(ArrayList<Producto>) ses.getAttribute("listapro");
        
        //adiciiona,os un  objeto
        Producto obj1 = new Producto();
        
        obj1.setId(Integer.parseInt(request.getParameter("id")));
        obj1.setDescripcion(request.getParameter("descripcion"));
        obj1.setCantidad(Integer.parseInt(request.getParameter("cantidad")));
        obj1.setPrecio(Integer.parseInt(request.getParameter("precio")));
        obj1.setCategoria(request.getParameter("categoria"));
         //ULTIMA PARTE DEL CODIOG CON DE LA LISTA ES MAYUSCULA Y NO MINUSCULA COMO EN EL VIDEO minuto faltado 15 para que acabe
        //creamos id tempporal
        int idt = obj1.getId();
             
        if(idt ==0)
        {
            //nuevo
            //actualiozar el ultimo id
            int ultID;
            ultID=ultimoId(request);
            obj1.setId(ultID);
            Lista.add(obj1);
            
        }else
        {
            //edicio
            Lista.set(buscarIndice(request,idt),obj1);
            
        }
        ses.setAttribute("listapro",Lista);
        response.sendRedirect("index.jsp");
        
    }
      //metodo para buscar el indice en el arraylis
        private int buscarIndice(HttpServletRequest request, int id)
    {
        HttpSession ses = request.getSession();
        ArrayList<Producto> lista =(ArrayList<Producto>)ses.getAttribute("listapro");
        
        int  i =0;
        
        if(lista.size()>0)
        {
            while(i<lista.size())
            {
                if(lista.get(i).getId()==id)
                {
                    break;
                    
                }else
                {
                    i++;
                }
            }
        }
        return i;   
    }
       //metodo para el ultimo id  
        private int ultimoId(HttpServletRequest request)
    {
      HttpSession ses = request.getSession();
     ArrayList<Producto> lista =(ArrayList<Producto>)ses.getAttribute("listapro");
     
     int idaux =0;
     for(Producto item: lista)
     {
         idaux=item.getId();
     } 
     return idaux +1;
    }
}
