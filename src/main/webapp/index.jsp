<%-- 
    Document   : index
    Created on : 5 abr. 2022, 15:21:46
    Author     : MIGUEL
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.emergentes.modelo.Producto"%>
<%
    ArrayList<Producto> lista= (ArrayList<Producto>) session.getAttribute("listapro");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table border ="1" cellspacing="0" cellpading="10">
            <tr>
                <td>
                      <h3> PRIMER PARCIAL TEM-742</h3>
                      <h3> Nombre: MIGUEL MAMANI YANARICO</h3>
                      <h3> Carnet:13449218</h3>
                  </td>
                </tr>

        </table>
        
<center><h1>Productos</h1></center>
        
        <a href="MainController?op=nuevo"> Nuevo Producto</a>
        
        <table border =" 1">
            <tr>
                <th> Id</th>
                <th> Descripcion</th>
                <th> Cantidad</th>
                <th> Precio</th>
                <th> Categoria</th>
                 <th> </th>
                  <th> </th>
            </tr>
            <%-- 
            en aqui realizamos el recorrido de la lista 
            --%>
            <%
               if(lista!= null)
               {
               for (Producto item: lista)  
               {
            %>
          
            <tr>
                <td><%= item.getId() %></td>
                <td><%= item.getDescripcion() %></td>
                <td><%= item.getCantidad() %></td>
                <td><%= item.getPrecio() %></td>
                <td><%= item.getCategoria() %></td>
                <td> <a href="MainController?op=editar&id=<%= item.getId()%>">Modificar</a></td>
                <td> <a href="MainController?op=eliminar&id=<%= item.getId()%>"
                        onclick="return confirm('Esta seguro de eliminar el registro?');">Eliminar</a></td>
                    
                
            </tr>
            
             <%
                }
              }
            %>
            
        </table>
        
    </body>
</html>
