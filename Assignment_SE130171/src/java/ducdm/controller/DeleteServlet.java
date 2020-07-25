/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ducdm.controller;

import ducdm.account.AccountDAO;
import ducdm.account.AccountDTO;
import ducdm.account.AccountDeleteErrors;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author MinhDuc
 */
@WebServlet(name = "DeleteServlet", urlPatterns = {"/DeleteServlet"})
public class DeleteServlet extends HttpServlet {

    private final String SEARCH_SERVLET = "searchAccount";

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

        String username = request.getParameter("pk");
        String lastSearchValue = request.getParameter("lastSearchValue");

        AccountDAO dao = new AccountDAO();

        //get ServletContext
        ServletContext context = request.getServletContext();
        //get attribute in ServletContext
        Map<String, String> siteMap
                = (Map<String, String>) context.getAttribute("SITE_MAP");
        //get value of label 
        String searchServlet = siteMap.get(SEARCH_SERVLET);
        String url = searchServlet
                + "?txtSearchValue=" + lastSearchValue
                + "&btAction=Search";
        boolean error = false;
        AccountDeleteErrors deleteError = new AccountDeleteErrors();
        try {
            //get username being signed in
            HttpSession session = request.getSession(false);
            if (session != null) {
                String accountUsername
                        = (String) session.getAttribute("USERNAME");
                if (accountUsername.trim().equalsIgnoreCase(username.trim())) {
                    error = true;
                    deleteError.setAccountSignedInDeleteErr(
                            "This account being signed in!!");
                }//end if delete account being signed in
            }//end if this account being signed in
            
            if (error) {
                request.setAttribute("DELETEERRORS", deleteError);
                request.setAttribute("USERNAME_DELETE", username.trim());
            } // end if deleting occurs error 
            else {
                boolean result = dao.deleteAccount(username);
                if (result) {
                    url = searchServlet
                            + "?txtSearchValue=" + lastSearchValue
                            + "&btAction=Search";
                }//end if delete account is successed 
            }

        } catch (SQLException ex) {
            log("DeleteServlet_SQL: " + ex.getMessage());
        } catch (NamingException ex) {
            log("DeleteServlet_NAMING: " + ex.getMessage());
        } finally {
            if (error) {
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
            } else {
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
