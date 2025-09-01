package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.service.PostService;

import java.io.IOException;
import java.util.ArrayList;

import domain.Post;


@WebServlet("/main/main.do")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PostService service = PostService.getInstance();
        ArrayList<Post> list = service.listS();
        System.out.println("MainController 실행됨, list size=" + list.size());
        request.setAttribute("list", list);
        
       
        RequestDispatcher rd = request.getRequestDispatcher("/main/main.jsp");
        rd.forward(request, response);
    }

}
