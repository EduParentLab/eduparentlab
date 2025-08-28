package controller;

import jakarta.servlet.*;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import model.service.FileService;
import model.service.CommentService;
import model.service.PostService;
import util.PagingUtil;
import domain.Comment;
import domain.Post;
import domain.PostFile;

@WebServlet("/post.do")
@MultipartConfig
public class PostController extends HttpServlet {

	private static final long serialVersionUID = 1L;
       

    public void service(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String m = request.getParameter("m");
        if (m == null) m = "list";

        switch(m) {
            case "input": input(request, response); break;
            case "insert": insert(request, response); break;
            case "edit" : edit(request, response); break;
            case "update" : update(request,response); break;
            case "delete": delete(request, response); break;
            case "content": content(request,response); break;
            case "view":content(request,response); break;
            default: list(request, response); break;
        }
    }

    private void list(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
    	int page = 1; //현재 페이지
    	int pageSize = 5; //한 페이지에 보여줄 글 수
    	int pageBlock = 5; //한 번에 보여줄 페이지 번호 개수
    	
    	String strPage = request.getParameter("page");
    	if(strPage != null) {
    		page = Integer.parseInt(strPage);
    	}
    	
        PostService service = PostService.getInstance();
        int totalCount = service.getTotalPosts();
        
        PagingUtil paging = new PagingUtil(totalCount, page, pageSize, pageBlock);
        
        
        List<Post> list = service.listPagingS(paging.getStartRow(), pageSize);
        request.setAttribute("list", list);
        request.setAttribute("paging", paging);

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
        int likes = 0;

        Post dto = new Post(-1, post_subject, post_content, null, post_view, category_num, email, likes);

        PostService service = PostService.getInstance();

     
        int postNum = service.insertInt(dto);

        boolean flag = false;

        if (postNum > 0) {
            FileService.getInstance().saveFiles(request, postNum);
            flag = true;
        }

        request.setAttribute("flag", flag);
        request.setAttribute("kind", "insert");

        RequestDispatcher rd = request.getRequestDispatcher("/post/msg.jsp");
        rd.forward(request, response);
    }


    
    
    private void delete(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
    	
    	String post_numStr = request.getParameter("seq");
        int seq = Integer.parseInt(post_numStr);
        
        
        PostService service = PostService.getInstance();
        
        boolean flag = service.deleteS(seq);
        
        request.setAttribute("flag", flag);
        request.setAttribute("kind", "del");
        
        String view = "/post/msg.jsp";
        RequestDispatcher rd = request.getRequestDispatcher(view);
        rd.forward(request, response);
    }
    
    
    private void update(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
        int post_num = Integer.parseInt(request.getParameter("post_num"));
        String post_subject = request.getParameter("post_subject");
        String post_content = request.getParameter("post_content");
        int category_num = Integer.parseInt(request.getParameter("category_num"));

 
        Post dto = new Post(post_num, post_subject, post_content, null, 0, category_num, null, 0);

        boolean flag = PostService.getInstance().updateS(dto);
        
        request.setAttribute("flag", flag);
        request.setAttribute("kind", "update");
        request.getRequestDispatcher("/post/msg.jsp")
        .forward(request, response);
    }
    
    
    private void content(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int post_num = Integer.parseInt(request.getParameter("seq"));
        PostService service = PostService.getInstance();
        CommentService commentService = CommentService.getInstance();
        service.hit(post_num);               
        Post dto = service.get(post_num);    

        List<PostFile> fileList = FileService.getInstance().findFilesByPost(post_num);

        request.setAttribute("dto", dto);

        request.setAttribute("fileList", fileList);

        RequestDispatcher rd = request.getRequestDispatcher("/post/content.jsp");
        rd.forward(request, response);
    }

    
    
    private void edit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int post_num = Integer.parseInt(request.getParameter("seq"));
        Post dto = PostService.getInstance().get(post_num);
        
        request.setAttribute("dto", dto);
        request.getRequestDispatcher("/post/postUpdate.jsp").forward(request, response);
    }
}
