package model.service;

import java.util.ArrayList;
import model.dao.PostDAO;
import domain.Post;

public class PostService {
    private PostDAO dao;
    private static final PostService instance = new PostService();

    private PostService() {
        dao = new PostDAO();
    }

    public static PostService getInstance() {
        return instance;
    }

    public ArrayList<Post> listS() {
        return dao.list();
    }

  
    public boolean insertS(Post dto) {
        return dao.insert(dto);   // dto로 넘겨야 맞음
    }

   
   /* public boolean deleteS(long seq) {
        return dao.delete(seq);
    }

   
    public boolean updateS(Post dto) {
        return dao.update(dto);
    }*/
}
