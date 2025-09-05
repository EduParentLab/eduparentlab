package model.service;

import java.util.List;
import model.dao.PlaylistDAO;
import domain.Playlist;

public class PlaylistService {

    private static PlaylistService instance = new PlaylistService();
    private PlaylistDAO dao;

    private PlaylistService() {
        dao = new PlaylistDAO();
    }

    public static PlaylistService getInstance() {
        return instance;
    }

    public List<Playlist> getAllPlaylists() {
        return dao.selectAll();
    }

    public List<Playlist> getPlaylistsByCategory(String category) {
        return dao.selectByCategory(category);
    }
}
