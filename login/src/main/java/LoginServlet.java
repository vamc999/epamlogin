

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
//@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    ArrayList<String> menuname=null;
    ArrayList<String> menupagepath=null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 menuname =  new ArrayList<String>();
	     menupagepath = new ArrayList<String>();
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String email=request.getParameter("email");  
        String password=request.getParameter("password");  
     
        try {
        	String driver = "com.mysql.cj.jdbc.Driver";
        	String connectionString = "jdbc:mysql://localhost:3306/rd_admin";
        	Class.forName(driver);  
            Connection con=DriverManager.getConnection(connectionString,"root","root");
    
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from userinfo where email = '"+email+"' and password ='"+password+"'"); 
              if(rs.next()) {
            	int id = rs.getInt(3);
            	ResultSet rs_menu = stmt.executeQuery("select * from menu where menuid in (select menuid from rolewithmenu where roleid="+id+")");
            	while(rs_menu.next()) {
            		menuname.add(rs_menu.getString(2));
            		menupagepath.add(rs_menu.getString(3));
            	}
            	request.setAttribute("menuname",menuname);
            	request.setAttribute("menupagepath",menupagepath);
            	//response.sendRedirect("home.jsp");
            	request.getRequestDispatcher("home.jsp").include(request, response);
            }
        }catch(Exception e){System.out.println(e);}
        
     //   out.print("Welcome2");
        
//        if(password.equals("admin")){  
//        out.print("Welcome, "+name);  
//        HttpSession session=request.getSession();  
//        session.setAttribute("name",name);  
//        }  
//        else{  
//            out.print("Sorry, username or password error!");  
//            request.getRequestDispatcher("login.jsp").include(request, response);  
//        }  
//        out.close();  
		doGet(request, response);
	}

}
