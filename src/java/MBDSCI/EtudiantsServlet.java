/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MBDSCI;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Wili
 */
public class EtudiantsServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * 
     */
    String cheminFichier="Etudiants.cvs";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        afficherlisteEtu(request, response);
        response.setContentType("text/html;charset=UTF-8");
       
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
        //afficherlisteEtu(request, response);
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
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("pnom");
        String email = request.getParameter("mail");
        
        
        try
        {
            FileWriter fw = new FileWriter(cheminFichier,true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            pw.println(nom+","+prenom+","+email);
            pw.flush();
            pw.close();
        }
        catch(Exception e)
        {
            
        }
        
        afficherlisteEtu(request, response);
        
        
        
        
        //processRequest(request, response);
    }
    
    private void afficherlisteEtu(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        PrintWriter out = response.getWriter();
        String line="";
        String chemi = this.cheminFichier;
        
        try
        {
             /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            
            out.println("<title>ListeEtudiantsServlet</title>"); 
            out.println("<link href='boostrap.css' rel='stylesheet'>");
            out.println("</head>");
            out.println("<body>");
            out.println("<a href='Formulaire_Etudiant.html'><h1 style='color:yellow' class='btn btn-danger'>ReTour au formulaire " + request.getContextPath() + "</h1> <a/> <br/>");
            out.println("<table class='table table-sm table-succes table-striped table-over'> ");
            out.println("<tr> <th>Nom<th> <th>PRENOM <th/> <th>Adresse mail<th/> </tr>");
            
            
            
            BufferedReader br = new BufferedReader(new FileReader(chemi));
            while ((line = br.readLine())!= null )
            {
                String valeurs[] = line.split(",");
                out.println(" <tr><td>"+valeurs[0]+"<td/><td>"+valeurs[1]+"<td/> <td>"+valeurs[2]+"<td/> <tr/></body>");
                
            }
                  
            out.println(" <table/> </body>");
            out.println("</html>");
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
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
