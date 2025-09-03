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
import domain.User;

import java.sql.Date;


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
        int page = 1;
        int pageSize = 10;
        int pageBlock = 5;

        String strPage = request.getParameter("page");
        if (strPage != null) page = Integer.parseInt(strPage);

        String rowsParam = request.getParameter("rows");
        if (rowsParam != null && !rowsParam.isBlank()) {
            pageSize = Integer.parseInt(rowsParam);
        }

        String sort = request.getParameter("sort");
        if (sort == null || sort.isBlank()) sort = "latest";

        String type = request.getParameter("type");
        String keyword = request.getParameter("keyword");

        int categoryNum = 1;
        String catParam = request.getParameter("category_num");
        if (catParam != null) {
            categoryNum = Integer.parseInt(catParam);
        }

        PostService service = PostService.getInstance();
        int totalCount = service.getTotalPostsByCategory(categoryNum);

        PagingUtil paging = new PagingUtil(totalCount, page, pageSize, pageBlock);

        List<Post> list;
        if (keyword != null && !keyword.isBlank()) {
            list = service.searchWithPagingS(paging.getStartRow(), pageSize, sort, type, keyword, categoryNum);
        } else {
            list = service.listPagingS(paging.getStartRow(), pageSize, sort, categoryNum);
        }

        request.setAttribute("list", list);
        request.setAttribute("paging", paging);
        request.setAttribute("sort", sort);
        request.setAttribute("type", type);
        request.setAttribute("keyword", keyword);
        request.setAttribute("category_num", categoryNum);

        RequestDispatcher rd = request.getRequestDispatcher("/post/post.jsp");
        rd.forward(request, response);
    }


    private void input(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
    	HttpSession session = request.getSession(false);
    	if (session == null) {
		    response.sendRedirect(request.getContextPath() + "/login/login.jsp?error=unauthorized");
		    return;
		}
    	User loginUser = (User) session.getAttribute("loginOkUser");
    	
    	if (loginUser == null) {
    	    response.sendRedirect(request.getContextPath() + "/login/login.jsp?error=unauthorized");
    	    return;
    	}

    	String email = loginUser.getEmail(); // null이 아님 보장
    	
    	//권한 체크 
    	if(!checkAuth(request,response,"write", email)) return;
    	
        RequestDispatcher rd = request.getRequestDispatcher("/post/input.jsp");
        rd.forward(request, response);
    }

    
    private void insert(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
    	HttpSession session = request.getSession(false);
    	if (session == null) {
		    response.sendRedirect(request.getContextPath() + "/login/login.jsp?error=unauthorized");
		    return;
		}
    	User loginUser = (User) session.getAttribute("loginOkUser");
    	
    	if (loginUser == null) {
    	    response.sendRedirect(request.getContextPath() + "/login/login.jsp?error=unauthorized");
    	    return;
    	}

    	String email = loginUser.getEmail(); // null이 아님 보장
    	
    	//권한 체크 
    	if(!checkAuth(request,response,"write", email)) return;
        
    	String post_subject = request.getParameter("post_subject");
        String post_content = request.getParameter("post_content");
        int post_view = 0; 
  
        int category_num = Integer.parseInt(request.getParameter("category_num"));
        String nickname = request.getParameter("nickname");
        int likes = 0;

        Post dto = new Post(-1, post_subject, post_content, null, 0, category_num, email, nickname, 0);

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
        Post post = service.get(seq);
        
        //권한 체크
        if(!checkAuth(request,response, "delete", post.getEmail())) return;
        
        boolean flag = service.deleteS(seq);
        
        request.setAttribute("flag", flag);
        request.setAttribute("kind", "delete");
        
        String view = "/post/msg.jsp";
        RequestDispatcher rd = request.getRequestDispatcher(view);
        rd.forward(request, response);
    }
    
    
    private void update(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	HttpSession session = request.getSession(false);
    	if (session == null) {
		    response.sendRedirect(request.getContextPath() + "/login/login.jsp?error=unauthorized");
		    return;
		}
 	    User loginUser = (User) session.getAttribute("loginOkUser");
 	   if (loginUser == null) {
	   	    response.sendRedirect(request.getContextPath() + "/login/login.jsp?error=unauthorized");
	   	    return;
	   	}	
 	    String role = (String) session.getAttribute("role");
    	
 	    int post_num = Integer.parseInt(request.getParameter("post_num"));
    	PostService service = PostService.getInstance();
	    Post post = service.get(post_num);
	      
	    //권한 체크
	    if(!checkAuth(request,response, "update", post.getEmail())) return;
      
        String post_subject = request.getParameter("post_subject");
        String post_content = request.getParameter("post_content");
        int category_num = Integer.parseInt(request.getParameter("category_num"));
        
        Post dto = new Post(post_num, post_subject, post_content, null, 0, category_num, null, null, 0);

        boolean flag = PostService.getInstance().updateS(dto);
        
        request.setAttribute("flag", flag);
        request.setAttribute("kind", "update");
        request.getRequestDispatcher("/post/msg.jsp")
        .forward(request, response);
    }
    
    
    private void content(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
    	HttpSession session = request.getSession(false);
    	if (session == null) {
		    response.sendRedirect(request.getContextPath() + "/login/login.jsp?error=unauthorized");
		    return;
		}
 	    User loginUser = (User) session.getAttribute("loginOkUser");
 	   if (loginUser == null) {
	   	    response.sendRedirect(request.getContextPath() + "/login/login.jsp?error=unauthorized");
	   	    return;
	   	}	
 	    String role = (String) session.getAttribute("role");
 	    
        int post_num = Integer.parseInt(request.getParameter("seq"));
        PostService service = PostService.getInstance();
        CommentService commentService = CommentService.getInstance();
        service.hit(post_num);               
        Post dto = service.get(post_num);    

        List<PostFile> fileList = FileService.getInstance().findFilesByPost(post_num);

        request.setAttribute("dto", dto);
        request.setAttribute("post_num", post_num);
        request.setAttribute("fileList", fileList);

        RequestDispatcher rd = request.getRequestDispatcher("/post/content.jsp");
        rd.forward(request, response);
    }

    
    private void edit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	HttpSession session = request.getSession(false);
    	if (session == null) {
		    response.sendRedirect(request.getContextPath() + "/login/login.jsp?error=unauthorized");
		    return;
		}
 	    User loginUser = (User) session.getAttribute("loginOkUser");
 	   if (loginUser == null) {
	   	    response.sendRedirect(request.getContextPath() + "/login/login.jsp?error=unauthorized");
	   	    return;
	   	}	
 	    String role = (String) session.getAttribute("role");
 	    
        int post_num = Integer.parseInt(request.getParameter("seq"));
        Post dto = PostService.getInstance().get(post_num);
	      
	    //권한 체크
	    if(!checkAuth(request,response, "update", dto.getEmail())) return;
        request.setAttribute("dto", dto);
        request.getRequestDispatcher("/post/postUpdate.jsp").forward(request, response);
    }
    
    private boolean checkAuth(HttpServletRequest request, HttpServletResponse response,
            String action, String writerEmail) throws IOException, ServletException {

		HttpSession session = request.getSession(false);
		if (session == null) {
		    response.sendRedirect(request.getContextPath() + "/login/login.jsp?error=unauthorized");
		    return false;
		}
		String role = (session != null) ? (String) session.getAttribute("role") : "guest"; 
		User loginUser = (User)session.getAttribute("loginOkUser");
		if (loginUser == null) {
		    response.sendRedirect(request.getContextPath() + "/login/login.jsp?error=unauthorized");
		    return false;
		}
		String email = (loginUser != null) ? loginUser.getEmail() : null;
		
		// 관리자는 모든 권한 허용
		if ("admin".equals(role)) return true;
		
		// 비회원/탈퇴회원은 작성/수정/삭제 불가
		if ("guest".equals(role)) {
		response.sendRedirect(request.getContextPath() + "/login/login.jsp?error=unauthorized");
		return false;
		}
		
		// 일반회원
		if ("user".equals(role)) {
			switch(action) {
				case "write":
				  return true; // 글 작성 가능
				case "update":
				case "delete":
				  // 본인 글만 가능
				  if (email != null && email.equals(writerEmail)) {
				      return true;
				  } else {
				      response.sendRedirect(request.getContextPath() + "/post.do?m=list&error=unauthorized");
				      return false;
				  }
				default:
				  return false;
			}
		}
		return false;
	}
}
