package servlet;	

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.SiteEV;
import model.SiteEVLogic;	


@WebServlet("/MinatoIndex")				
public class MinatoIndex extends HttpServlet {				
	private static final long serialVersionUID = 1L;			
	protected void doGet(HttpServletRequest request, 			
		HttpServletResponse response) 		
		throws ServletException, IOException {
		//アプリケーションスコープに保存されたサイト評価を取得		
		ServletContext application = this.getServletContext();		
		SiteEV siteEV = (SiteEV)application.getAttribute("siteEV");
		System.out.println("\nアプリケーションスコープに保存されたサイト評価を取得");
		//サイト評価の初期化（初回リクエスト時）		
		if(siteEV == null) {		
			siteEV = new SiteEV();
			System.out.println("サイト評価の初期化（初回リクエスト時）");
		}		
		//リクエストパラメータの取得		
		request.setCharacterEncoding("UTF-8");		
		String action = request.getParameter("action");
		System.out.println("リクエストパラメータの取得");
		//サイトの評価処理（初回以降実行）		
		SiteEVLogic siteEVLogic = new SiteEVLogic();
		System.out.println("サイトの評価処理（初回以降実行)");
		if(action != null && action.equals("like")) {		
			siteEVLogic.like(siteEV);	//よいねに1足す
			System.out.println("よいねに1足す");
		} else if(action != null && action.equals("dislike")) {		
			siteEVLogic.dislike(siteEV);	//よくないねに1足す
			System.out.println("よくないねに1足す");
		}		
		//アプリケーションスコープにサイト評価を保存		
		application.setAttribute("siteEV", siteEV);	
		System.out.println("アプリケーションスコープにサイト評価を保存");
		//フォワード(サーブレット（処理担当）からJSP（表示担当）へページを渡す)	
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/minatoIndex.jsp");		
		dispatcher.forward(request,  response);	
		System.out.println("フォワード(サーブレット（処理担当）からJSP（表示担当）へページを渡す)\n");
	}			
}				
