/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;


public class Getlyrics {
        String lyrics = "lyrics not found";
        public String checker(String songdata){
          String checked= songdata.replaceAll(" ","%20");
            return checked;
        }
          public String json(String artistas,String songname) throws JSONException, MalformedURLException, IOException{ 
      String aux = "";
       try {
       String Artist=checker(artistas);
       String Title=checker(songname);
       URL url = new URL(" https://api.lyrics.ovh/v1/"+Artist+"/"+Title);        
       HttpURLConnection conn;
       conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");
        if(conn.getResponseCode()!=200){
         throw new RuntimeException("Failed:HTTP error code:" +conn.getResponseCode());}
      
      aux="Error";
      BufferedReader br= new BufferedReader(new InputStreamReader(conn.getInputStream()));
      StringBuilder builder = new StringBuilder();
      

while ((aux = br.readLine()) != null) {
    builder.append(aux +"\n");
}
//System.out.println(builder);

   aux = builder.toString();
 
//     Scanner scan = new Scanner(url.openStream());
 //   String str = new String();
  //  while (scan.hasNext())
    //    str += scan.nextLine();
    //scan.close();
    
    JSONObject obj = new JSONObject(aux);
    lyrics=obj.getString("lyrics");
     
    
    
}  catch (IOException ex) {
            Logger.getLogger(Getlyrics.class.getName()).log(Level.SEVERE, null, ex);    
}
            
        return lyrics; 
      } 
    
}


