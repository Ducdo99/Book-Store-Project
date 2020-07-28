/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ducdm.controller;

import ducdm.book.BookDAO;
import ducdm.book.BookDTO;
import ducdm.cart.CartCheckOutErrors;
import ducdm.cartdetail.CartDetailDAO;
import ducdm.cartdetail.CartDetailDTO;
import ducdm.cart.CartDAO;
import ducdm.cart.CartObject;
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
@WebServlet(name = "CheckOutServlet", urlPatterns = {"/CheckOutServlet"})
public class CheckOutServlet extends HttpServlet {

    private final String CONFIRM_PAGE = "confirmPage";
    private final String SHOW_PRODUCT_PAGE = "loadBooks";

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

        String url = CONFIRM_PAGE;

        boolean foundError = false;
        try {
            //1. Customer goes to cart's place
            HttpSession session = request.getSession(false);

            if (session != null) {
                //2. Customer takes cart 
                CartObject cart = (CartObject) session.getAttribute("CART");

                if (cart != null) {
                    //3. Get all book in items
                    Map<String, BookDTO> bookList = cart.getItems();

                    if (bookList != null) {
                        CartCheckOutErrors error = new CartCheckOutErrors();
                        String receiverName
                                = request.getParameter("txtReceiverName");
                        String receiverAddress
                                = request.getParameter("txtReceiverAddress");
                        if (receiverName.trim().length() < 2
                                || receiverName.trim().length() > 50) {
                            foundError = true;
                            error.setReceiverNameLengthErr(
                                    "Name is required input 2 - 50 characters");
                        }//end if receiver name out of range 2 - 50 characters
                        if (receiverAddress.trim().length() < 15
                                || receiverAddress.trim().length() > 250) {
                            foundError = true;
                            error.setReceiverAddressLengthErr(
                                    "Address is required input 15 - 250 characters");
                        }//end if receiver address out of range 15 - 250 characters
                        if (foundError) {
                            request.setAttribute("CHECKOUT_ERROR", error);
                            //get ServletContext
                            ServletContext context = request.getServletContext();
                            //get attribute in ServletContext
                            Map<String, String> siteMap
                                    = (Map<String, String>) context.getAttribute("SITE_MAP");
                            //get value of label 
                            url = siteMap.get(CONFIRM_PAGE);
                        } //end if foundError is true
                        else {
                            String senderName
                                    = (String) session.getAttribute("USERNAME");
                            if (senderName != null) {
                                CartDAO cartDAO = new CartDAO();
                                int cartID = 1; //default cart id will be started from 1
                                String id = cartDAO.getMaxCartID();

                                if (id != null) {
                                    int temp = Integer.parseInt(id.trim()); //convert String type to int type
                                    cartID = temp + 1;
                                }//end if cartID is existed
                                id = String.valueOf(cartID); //convert int type to String type
                                //Insert into Cart table
                                boolean result = cartDAO.insertIntoCart(id,
                                        senderName, receiverName,
                                        receiverAddress);

                                if (result) {
                                    BookDAO bookDAO = new BookDAO();
                                    CartDetailDAO cartDetailDAO = new CartDetailDAO();
                                    String bookID = null;
                                    String bookName = null;
                                    int quantity = 0;
                                    double price = 0;
                                    double total = 0;
                                    int bookQuantity = 0;
                                    String[] booksName
                                            = request.getParameterValues("txtBookName");
                                    for (String name : booksName) {
                                        BookDTO bookDTO = bookList.get(name);
                                        bookDAO.getBookID(bookDTO);
                                        bookID = bookDAO.getBookInfo().getBookID();
                                        bookName = bookDTO.getBookName();
                                        quantity = bookDTO.getQuantity();
                                        price = bookDTO.getPrice();
                                        total = quantity * price;
                                        CartDetailDTO dto = new CartDetailDTO(id,
                                                bookID, bookName, quantity,
                                                price, total);
                                        cartDetailDAO.insertCartDetail(dto);
                                        //Update book quantity
                                        bookDAO.updateQuantity(bookID, quantity);
                                        //get book quantity after update
                                        bookDAO.getBookQuantityAfterUpdate(bookID);
                                        bookQuantity = bookDAO.getBookInfo().getQuantity();
                                        if (bookQuantity <= 1) {
                                            bookDAO.updateStatus(bookID);
                                        }
                                    }
                                    session.removeAttribute("CART");
                                    url = SHOW_PRODUCT_PAGE;
                                }//end if insert into Cart table sucessed
                            }//end if account being signed in
                        }//end if foundError is false
                    }//end if book list is existed
                }//end if cart is existed
            }//end if session is exsited 
        } catch (SQLException ex) {
            log("CheckOutServlet_SQL: " + ex.getMessage());
        } catch (NamingException ex) {
            log("CheckOutServlet_NAMING: " + ex.getMessage());
        } finally {
            if (foundError) {
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
