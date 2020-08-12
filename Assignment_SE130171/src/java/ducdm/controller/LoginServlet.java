/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ducdm.controller;

import ducdm.account.AccountDAO;
import ducdm.account.AccountDTO;
import ducdm.account.AccountSignInErrors;
import ducdm.cart.CartObject;
import ducdm.util.Utils;
import ducdm.util.VerifyRecaptchaUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author MinhDuc
 */
public class LoginServlet extends HttpServlet {

    private final String SIGN_IN_ERROR_PAGE = "loginErrorPage";
    private final String SEARCH_PAGE = "searchPage";
    private final String CONFIRM_PAGE = "confirmPage";
    private final String LOAD_DATA_SERVLET = "loadBooks";

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
        String rememberAccount = request.getParameter("addCookie");
        String url = SIGN_IN_ERROR_PAGE;
        boolean isVerified = false;
        boolean foundErr = false;
        String hashPassword = null;
        try {
            AccountSignInErrors signInErr = new AccountSignInErrors();
            //get the cookie name
            String cookieName = (String) request.getAttribute("COOKIE_NAME");

            //Check cookie 
            if (cookieName == null) {
                if (username.trim().length() < 6 || username.trim().length() > 20) {
                    foundErr = true;
                    signInErr.setIncorrectUsernameOrPasswordErr(
                            "Incorrect Username or Password!!");
                }//end if username out range of 6-20 characters

                if (password.trim().length() < 8 || password.trim().length() > 30) {
                    foundErr = true;
                    signInErr.setIncorrectUsernameOrPasswordErr(
                            "Incorrect Username or Password!!");
                }//end if passowrd out range of 6-20 characters

                String reCaptchaResponse = request.getParameter("g-recaptcha-response");
                isVerified = VerifyRecaptchaUtil.isVerified(reCaptchaResponse);
                if (isVerified == false) {
                    foundErr = true;
                    signInErr.setDoNotClickOnReCaptchaErr(
                            "Please verify that you are not a robot!!");
                }//end if user does not verify
            }//end if cookie is null 

            if (foundErr) {
                //set error into attribute
                request.setAttribute("SIGN_IN_ERRORS", signInErr);
            } else {
                AccountDAO dao = new AccountDAO();
                
                //Check cookie 
                if (cookieName == null) {
                    hashPassword = Utils.encryptBySHA256(password.trim());
                }//end if cookie is null

                if (cookieName != null) {
                    //get the cookie value
                    String cookieValue
                            = (String) request.getAttribute("COOKIE_VALUE");
                    if (cookieValue != null) {
                        username = cookieName.trim();
                        hashPassword = cookieValue.trim();
                    }//end if the cookie value is not null
                }//end if the cookie name is not null

                boolean result
                        = dao.checkLogin(username.trim(), hashPassword.trim());
                if (result) {
                    AccountDTO accountInfo = dao.getAccountInfo();
                    String userNameAccount = accountInfo.getUsername(); // get user name of account
                    String lastname = accountInfo.getLastname(); //get last name of account
                    boolean role = accountInfo.isIsAdmin(); //get role of account

                    HttpSession session = request.getSession();
                    session.setAttribute("USERNAME", userNameAccount);
                    session.setAttribute("LASTNAME", lastname);
                    session.setAttribute("ROLE", role);

                    //Check cookie 
                    if (cookieName == null) {
                        //Add cookie
                        if (rememberAccount != null) {
                            if (rememberAccount.trim().equals("true")) {
                                //Create cookie
                                Cookie cookie = new Cookie(
                                        username.trim(), hashPassword.trim());
                                //Set the max time is existed for cookie that is 3 min
                                cookie.setMaxAge(60 * 3);
                                //Return the cooke to client side 
                                response.addCookie(cookie);
                            }//end if the value of rememberAccount param is true
                        }//end if rememeberAccount param is existed
                    }//end if cookie is null
                    if (role) {
                        url = SEARCH_PAGE;
                    }//end if account being signed in which is admin
                    else if (role == false) {
                        url = LOAD_DATA_SERVLET;
                    }//end if account being signed in which is not admin
                    CartObject cartObject
                            = (CartObject) session.getAttribute("CART");
                    if (cartObject != null) {
                        url = CONFIRM_PAGE;
                    }//end if cart being existed
                }//end if account being existed
                else {
                    foundErr = true;
                    signInErr.setIncorrectUsernameOrPasswordErr(
                            "Incorrect Username or Password!!");
                    request.setAttribute("SIGN_IN_ERRORS", signInErr);
                }
            }//end if user click on reCaptcha
        } catch (SQLException ex) {
            log("LoginServlet_SQL: " + ex.getMessage());
        } catch (NamingException ex) {
            log("LoginServlet_NAMING: " + ex.getMessage());
        } catch (NoSuchAlgorithmException ex) {
            log("LoginServlet_NOSUCHAlGORITHM: " + ex.getMessage());
        } catch (UnsupportedEncodingException ex) {
            log("LoginServlet_UNSUPPORTEDENCODING: " + ex.getMessage());
        } catch (IOException ex) {
            log("LoginServlet_IO: " + ex.getMessage());
        } finally {
            if (foundErr) {
                //get ServletContext
                ServletContext context = request.getServletContext();
                //get attribute in ServletContext
                Map<String, String> siteMap
                        = (Map<String, String>) context.getAttribute("SITE_MAP");
                //get value of label 
                String signInErrrorPage = siteMap.get(url);
                url = signInErrrorPage;

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
//        try {
//            String username = request.getParameter("txtUsername");
//            String password = request.getParameter("txtPassword");
//            String reCaptchaResponse = request.getParameter("g-recaptcha-response");
//            System.out.println("Value of 'g-recaptcha-response' params \t: " + reCaptchaResponse);
//            boolean isVerified = VerifyRecaptchaUtil.isVerified(reCaptchaResponse);
//
//            //get servlet config init params
//            if
//        } finally {
//
//        }

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
