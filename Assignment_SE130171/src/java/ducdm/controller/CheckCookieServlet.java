/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ducdm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author MinhDuc
 */
@WebServlet(name = "CheckCookieServlet", urlPatterns = {"/CheckCookieServlet"})
public class CheckCookieServlet extends HttpServlet {

    private final String LOGIN_PAGE = "loginPage";
    private final String LOGIN_SERVLET = "login";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String url = LOGIN_PAGE;
        boolean isCookie = false;
        try {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                //Get last cookie in cookies
                int cookiePosition = cookies.length - 1;
                Cookie lastCookie = cookies[cookiePosition];
                if (lastCookie != null) {
                    while (lastCookie.getName().trim().equals("JSESSIONID")) {
                        cookiePosition--;
                        lastCookie = cookies[cookiePosition];
                    }//end if the cookie name is not JSESSIONID 

                    //get the cookie name
                    String cookieName = lastCookie.getName().trim();
                    request.setAttribute("COOKIE_NAME", cookieName.trim());
                    //get the cookie value
                    String cookieValue = lastCookie.getValue().trim();
                    request.setAttribute("COOKIE_VALUE", cookieValue.trim());
                    url = LOGIN_SERVLET;
                    isCookie = true;
                }//end if the last cookie is not null
            }//end if cookies is not null
        } finally {
            if (isCookie) {
                //get ServletContext
                ServletContext context = request.getServletContext();
                //get attribute in ServletContext
                Map<String, String> siteMap
                        = (Map<String, String>) context.getAttribute("SITE_MAP");
                //get value of label 
                url = siteMap.get(LOGIN_SERVLET);
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
            } //end if the value isCookie is true
            else {
                response.sendRedirect(url);
            }
            out.close();
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
