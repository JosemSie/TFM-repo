/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "verificar", urlPatterns = {"/verificar"})
public class verificar extends HttpServlet {
    
    public static sessionListener sessionL;
    private SessionController sessionC;
    public verificar() throws IOException{
        sessionL = new sessionListener();
        sessionC = new SessionController();
        sessionC.createMap("E:\\NetBeansProjects\\TFM\\src\\java\\conf\\map");
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println(request.getRequestURI());
        response.setContentType("text/html;charset=UTF-8");
        String usuario = request.getParameter("usuario");
        String contraseña = request.getParameter("contr");
        if("root".equals(usuario) && "123".equals(contraseña)){
            Session misession = new Session(request.getSession().getId(),"/TFM/verificar") {
                @Override
                public boolean isEqual(Session p1) {
                    return this.getId() == p1.getId();
                }
            };
           // HttpSession misession = ;
            sessionL.sessionCreated(misession);
            request.getSession().setAttribute("usu", misession);
            request.getSession().setAttribute("sessionC", sessionC);
            request.getRequestDispatcher("view1").forward(request, response);
        }
        else{
            request.getRequestDispatcher("error").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
