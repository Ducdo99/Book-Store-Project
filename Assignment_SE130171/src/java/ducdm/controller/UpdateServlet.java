/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ducdm.controller;

import ducdm.account.AccountDAO;
import ducdm.account.AccountUpdateErrors;
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

/**
 *
 * @author MinhDuc
 */
@WebServlet(name = "UpdateServlet", urlPatterns = {"/UpdateServlet"})
public class UpdateServlet extends HttpServlet {

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

        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String admin = request.getParameter("chkAdmin");
        String lastSearchValue = request.getParameter("txtLastSearchValue");
        boolean isAdmin = false;
        if (admin != null) {
            isAdmin = true;
        }//end if admin is not null

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
        AccountUpdateErrors updateError = new AccountUpdateErrors();

        try {
            if (password.trim().length() < 6 || password.trim().length() > 30) {
                error = true;
                updateError.setPasswordLengthErr("Password of the account "
                        + " must have 6 - 30 characters");
            }//end if the length of password doesn't have 8-30 characters 
            if (error) {
                request.setAttribute("UPDATEERRORS", updateError);
                request.setAttribute("USERNAME_UPDATE", username.trim());
            }//end if update occurs error 
            else {
                AccountDAO dao = new AccountDAO();
                boolean result = dao.updateAccount(username, password, isAdmin);

                if (result) {
                    url = searchServlet
                            + "?txtSearchValue=" + lastSearchValue
                            + "&btAction=Search";

                }//end if update successfully
            }
        } catch (SQLException ex) {
            log("UpdateServlet_SQL: " + ex.getMessage());
        } catch (NamingException ex) {
            log("UpdateServlet_NAMING: " + ex.getMessage());
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
