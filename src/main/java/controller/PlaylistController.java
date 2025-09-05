package controller;

import java.io.IOException;
import java.util.List;

import domain.Playlist;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.service.PlaylistService;

@WebServlet("/playlist.do")
public class PlaylistController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String category = request.getParameter("category");

        PlaylistService service = PlaylistService.getInstance();
        List<Playlist> list;

        if (category != null && !category.isEmpty()) {
            list = service.getPlaylistsByCategory(category);
        } else {
            list = service.getAllPlaylists();
        }

        request.setAttribute("playlist", list);
        request.getRequestDispatcher("/playlist2/playlist.jsp").forward(request, response);
    }
}
