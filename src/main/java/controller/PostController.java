package controller;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import model.service.PostService;
import domain.Post;

@WebServlet("/post.do")
public class PostController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void service(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String m = request.getParameter("m");
        if (m == null) m = "list";

        switch(m) {
            case "input": input(request, response); break;
            case "insert": insert(request, response); break;
            default: list(request, response); break;
        }
    }

    private void list(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        PostService service = PostService.getInstance();
        ArrayList<Post> list = service.listS();
        request.setAttribute("list", list);

        RequestDispatcher rd = request.getRequestDispatcher("/post/post.jsp");
        rd.forward(request, response);
    }

    private void input(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/post/input.jsp");
    }

    private void insert(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String post_subject = request.getParameter("post_subject");
        String post_content = request.getParameter("post_content");
        int post_view = 0; 
        String email = request.getParameter("email");
        int category_num = Integer.parseInt(request.getParameter("category_num"));

        Post dto = new Post(-1, post_subject, post_content, null, post_view, category_num, email);

        PostService service = PostService.getInstance();
        boolean flag = service.insertS(dto);

        request.setAttribute("flag", flag);
        request.setAttribute("kind", "insert");
        RequestDispatcher rd = request.getRequestDispatcher("/post/msg.jsp");
        rd.forward(request, response);
    }
}
