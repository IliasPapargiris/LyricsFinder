<%-- 
    Document   : newjsp
    Created on : Oct 30, 2018, 4:48:10 PM
    Author     : User
--%>
<%@page import="model.Song"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>All songs</title>
    </head>
    <body>
        <h1>Mp3s stored in data base</h1>
         <%ArrayList<Song> List=(ArrayList<Song>)request.getAttribute("storedSongs"); %>
        <form>
        <table style="width:100%">
            <%for (Song track:List){%>
            <tr>
                <th><%=track. getTitle()%> type="text" name="title"</th>
                <th><<%=track.getArtist()%> type="text" name="artist"/th>
                <th><%=track.getLyrics()%></th>
                <th><a  href="/delete?artist=${track.getTitle()}&title=${track.getArtist()}">Check all the uploaded songs!</a>>Delete</a></th> //url pattern neou servlet
             </tr>
             <%}%>
        </table>
        </form>
    </body>
</html>
