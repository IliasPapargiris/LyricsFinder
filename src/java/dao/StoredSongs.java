/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;


import helpers.DB_Conection;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.naming.NamingException;
import model.Song;

/**
 *
 * @author User
 */
public class StoredSongs{ 
    ArrayList<Song> List;
       String songtitle;
    String artist;
    String lyrics;
    Blob mp3;
    public ArrayList<Song> songlist() throws NamingException, SQLException{
    ResultSet rs;
    Statement st;
    Connection k=DB_Conection.getConnection();
    String p="SELECT * FROM music.song";
    st= k.createStatement();
     rs=st.executeQuery(p);
     Song dummie=new Song();
     rs.last();
     int i= rs.getRow();
     rs.beforeFirst();
     List=new ArrayList<Song>();
     while (rs.next()){
       Song dum = new Song();
     dum.setArtist(rs.getString("Title"));
     dum.setLyrics(rs.getString("Artist"));
     dum.setMp3(rs.getBlob("mp3"));
     List.add(dum);
     }
      rs.close();
      st.close();
      k.close();
     return List;
}

     public void insertSongDB(String songtitle,String artist, String lyrics,File filePath) throws NamingException, SQLException, FileNotFoundException{
     
     Connection  con=DB_Conection.getConnection();
     try {
          //
            Connection  conn=DB_Conection.getConnection();
            String sql = "INSERT INTO song(Title,Artist,mp3,lyrics) values (?,?,?,?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, songtitle);
            statement.setString(2, artist);
            InputStream inputStream = new FileInputStream(filePath);
            statement.setBlob(3, inputStream);
            statement.setString(4,lyrics);
             statement.executeUpdate();
           // int row = statement.executeUpdate();
           // if (row > 0) {
                System.out.println("A contact was inserted with photo image.");
            //}
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
}
 public void deletesong(String artist,String title) throws NamingException, SQLException{
    Statement st;
    Connection k=DB_Conection.getConnection();
    String p="DELETE FROM music.song where Title='"+title+"' and Artist='"+artist+"'";
    st= k.createStatement();
     st.executeUpdate(p);
      st.close();
      k.close();
}
}