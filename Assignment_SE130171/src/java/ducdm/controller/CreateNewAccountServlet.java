/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ducdm.controller;

import ducdm.account.AccountCreateNewErrors;
import ducdm.account.AccountDAO;
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
@WebServlet(name = "CreateNewAccountServlet", urlPatterns = {"/CreateNewAccountServlet"})
public class CreateNewAccountServlet extends HttpServlet {

    private final String CREATE_NEW_ERROR_PAGE = "registerErrorPage";
    private final String LOGIN_PAGE = "loginPage";

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
        String confirmPassword = request.getParameter("txtConfirmPassword");
        String fullName = request.getParameter("txtFullName");

        String url = CREATE_NEW_ERROR_PAGE;

        boolean foundErr = false;
        AccountCreateNewErrors createNewError = new AccountCreateNewErrors();
        try {
            if (username != null && password != null && fullName != null) {
                AccountDAO dao = new AccountDAO();
                if (username.trim().length() < 6 || username.trim().length() > 20) {
                    foundErr = true;
                    createNewError.setUsernameLengthErr(
                            "Username is required input 6 - 20 characters");
                }
                if (password.trim().length() < 6 || password.trim().length() > 30) {
                    foundErr = true;
                    createNewError.setPasswordLengthErr(
                            "Password is required input 6 - 30 characters");
                } else if (!confirmPassword.trim().equalsIgnoreCase(password.trim())) {
                    foundErr = true;
                    createNewError.setConfirmNotMatchedErr(
                            "Confirm must match Password!!");
                }
                if (fullName.trim().length() < 2 || fullName.trim().length() > 50) {
                    foundErr = true;
                    createNewError.setFullnameLengthErr(
                            "Full name is required input 2 - 50 characters");
                }

                int isExisted = dao.getStatusAccount(username.trim());
                if (isExisted == 0) {
                    foundErr = true;
                    createNewError.setUsernamBeDisable("This username has been used");
                }//end if account is existed, but be disable

                if (foundErr) {
                    request.setAttribute("CREATE_ERRORS", createNewError);
                    //get ServletContext
                    ServletContext context = request.getServletContext();
                    //get attribute in ServletContext
                    Map<String, String> siteMap
                            = (Map<String, String>) context.getAttribute("SITE_MAP");
                    //get value of label 
                    String registsAccountErrrorPage = siteMap.get(url);
                    url = registsAccountErrrorPage;
                } else {
                    boolean result
                            = dao.createNewAccount(username,
                                    password, fullName, false);
                    if (result) {
                        url = LOGIN_PAGE;
                    }
                }
            }
        } catch (SQLException ex) {
            String msg = ex.getMessage();
            log("CreateNewAccountServlet_SQL: " + msg);
            if (msg.contains("duplicate")) {
                foundErr = true;
                createNewError.setUsernamIsExisted(
                        username + " is existed!!");
                request.setAttribute("CREATE_ERRORS", createNewError);
            }
        } catch (NamingException ex) {
            log("CreateNewAccountServlet_NAMING: " + ex.getMessage());
        } finally {
            if (foundErr) {
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
