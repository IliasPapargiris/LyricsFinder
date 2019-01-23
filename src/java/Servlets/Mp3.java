/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import helpers.Getlyrics;
import dao.StoredSongs;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import static javax.rmi.CORBA.Util.copyObject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.Song;
import org.farng.mp3.MP3File;
import org.farng.mp3.TagException;
import static org.farng.mp3.TagUtility.copyObject;
import org.farng.mp3.AbstractMP3FileItem;
import org.farng.mp3.AbstractMP3Tag;
import org.farng.mp3.filename.FilenameTag;
import org.farng.mp3.id3.ID3v1;
import org.farng.mp3.id3.ID3v2_2;
import org.json.JSONException;

 
/**
 *
 * @author User
 */
//THES GIA FORMES DO GET GIA TO NA DEIXEIS SELLIDA DO POST
@WebServlet(name = "Mp3", urlPatterns = {"/Mp3"})
@MultipartConfig(
                 fileSizeThreshold=1024*1024*100, 	 
                 maxFileSize=1024*1024*200,      	
                 maxRequestSize=1024*1024*300)   	
public class Mp3 extends HttpServlet {
    static String artist;
    static String album;
    static String songname;
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
            throws ServletException, IOException, TagException, NamingException, SQLException, JSONException {
        String filename = request.getParameter("filename");
       
       Part song = request.getPart("file");
        song.getSubmittedFileName();
        String path="C:\\songs\\"+filename; 
        File savedsong = new File(path);
        song.write(path);
        MP3File k = null;
        try {
            k = new MP3File(savedsong);
        } catch (TagException ex) {
            Logger.getLogger(Mp3.class.getName()).log(Level.SEVERE, null, ex);
        } 
           ID3v1 tag1 = (ID3v1) k.getID3v1Tag();
          ID3v2_2 tag = (ID3v2_2) k.getID3v2Tag();
           album=tag1.getAlbum();
           artist=tag1.getArtist();
           songname=tag1.getSongTitle();
           System.out.println(album); 
            System.out.println(artist);
             System.out.println(songname);
             Getlyrics finder=new Getlyrics();
             String lyrics = null;
        try {
            lyrics = finder.json(artist, songname);
        } catch (JSONException ex) {
            Logger.getLogger(Mp3.class.getName()).log(Level.SEVERE, null, ex);
        }
            
             System.out.println(lyrics);
             StoredSongs ins=new StoredSongs();
        try {
            ins.insertSongDB(songname,artist,lyrics,savedsong);
        } catch (NamingException | SQLException ex) {
            Logger.getLogger(Mp3.class.getName()).log(Level.SEVERE, null, ex);
        } 
         RequestDispatcher Rd = request.getRequestDispatcher("index.jsp");
        Rd.forward(request,response); 
    }
                  
                 
      // boolean a1=(k1==null);
     //  String singer=songTag.getLeadArtist();
      // String title=songTag.getSongTitle();
        
        
     //  FilenameTag name= k.getFilenameTag();
     // String album= name.getAlbumTitle();
       // System.out.println(album);
    

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
            throws ServletException, IOException, FileNotFoundException, MalformedURLException {

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
