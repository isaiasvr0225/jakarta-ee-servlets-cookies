package com.example.servletsheaders;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ServletContadorVisitas", value = "/ServletContadorVisitas")
public class ServletContadorVisitas extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Declaramos la variable contador
        int contador = 0;

        //Verificamos si existe la cookie contadorVisitas
        Cookie[] cookies = request.getCookies();

        if (cookies != null){
            for (Cookie c:
                 cookies) {
                if (c.getName().equals("contadorVisitas")){
                    contador = Integer.parseInt(c.getValue());
                }
            }
        }

        //Incrementamos la variable contador
        contador++;

        //Agregamos la respuesta y la cookie al navegador
        Cookie c = new Cookie("contadorVisitas", Integer.toString(contador));

        //La cookie se almacenará por 1 hora (3600 seg)
        c.setMaxAge(3600);
        response.addCookie(c);

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("Contador de visitas de cada cliente: " + contador);
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
