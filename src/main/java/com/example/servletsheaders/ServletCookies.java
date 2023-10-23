package com.example.servletsheaders;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ServletCookies", value = "/ServletCookies")
public class ServletCookies extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Suponemos que el usuario visita por primera vez
        boolean newUser = true;

        //Obtenemos el arreglo de cookies
        Cookie[] cookies = request.getCookies();


        //Buscamos si ya hay una cookie creada con anterioridad llamada visitanteRecurrente
        if (cookies != null){
            for (Cookie c:
                 cookies) {
                if (c.getName().equals("visitanteRecurrente") && c.getValue().equals("si")){
                    newUser = false;
                    break;
                }
            }
        }

        String mensaje = null;

        if (newUser){
            Cookie visitanteCookie = new Cookie("visitanteRecurrente", "si");
            response.addCookie(visitanteCookie);
            mensaje = "Gracias por visitar nuestro sitio por primera vez";
        }else {
            mensaje = "Gracias por visitar NUEVAMENTE nuestro sitio";
        }

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("Mensaje: " + mensaje);
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
