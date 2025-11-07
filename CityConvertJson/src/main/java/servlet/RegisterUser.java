package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.RegisterUserLogic;
import model.User;


@WebServlet("/RegisterUser")
public class RegisterUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) 
					throws ServletException, IOException {
		//フォワード先
		String forwardPath = null;
		//「action」の値をリクエストパラメータから取得
		String action = request.getParameter("action");
		
		if(action == null) {
			forwardPath = "WEB-INF/jsp/registerForm.jsp";
		}
		//登録実行
		else if(action.equals("done")) {
			HttpSession session = request.getSession();
			User registerUser = (User)session.getAttribute("registerUser");
			
			RegisterUserLogic logic = new RegisterUserLogic();
			logic.execute(registerUser);
			
			session.removeAttribute("registerUser");
			
			forwardPath = "WEB-INF/jsp/registerDone.jsp"; 
		}
		//フォワード先にフォワード（飛ばす）
		System.out.println("****" + forwardPath);
		RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) 
					throws ServletException, IOException {
		System.out.println("post来た");
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");

		
		User registerUser = new User(id, name, pass);
		
		HttpSession session = request.getSession();
		session.setAttribute("registerUser", registerUser);
		//フォワード先にフォワード（飛ばす）
		System.out.println("WEB-INF/jsp/registerConfirm.jsp");
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/registerConfirm.jsp");
		dispatcher.forward(request, response);
	}
}
