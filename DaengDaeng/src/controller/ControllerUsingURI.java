package controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;

public class ControllerUsingURI extends HttpServlet {

	  private Map commandMap = new HashMap();

	    public void init(ServletConfig config) throws ServletException {
	        String props = config.getInitParameter("configFile");//명령어 파일명 : /WEB-INF/commandHandlerURI.properties
	        Properties pr = new Properties();
	        FileInputStream f = null;
	        try {
	        	String configFilePath = config.getServletContext().getRealPath(props);//D:\pcm\workspace\DaengDaeng\WebContent\WEB-INF\commandHandlerURI.properties
	            f = new FileInputStream(configFilePath); 
	            pr.load(f);//프로퍼티객체["/member/join/member_choiceForm.do"]//["action.member.join.ChoiceFormAction"]
	        } catch (IOException e) {
	            throw new ServletException(e);
	        } finally {
	            if (f != null) try { f.close(); } catch(IOException ex) {}
	        }
	        Iterator keyIter = pr.keySet().iterator();
	        while( keyIter.hasNext() ) {
	            String command = (String)keyIter.next();///member/join/member_choiceForm.do
	            String className = pr.getProperty(command);//action.member.join.ChoiceFormAction
	            try {
	                Class commandClass = Class.forName(className);//action.member.join.ChoiceFormAction
	                Object commandInstance = commandClass.newInstance();//new ChoiceFormAction();
	                commandMap.put(command, commandInstance);//["/member/join/member_choiceForm.do"]//[new ChoiceFormAction()]
	            } catch (ClassNotFoundException e) {
	                throw new ServletException(e);
	            } catch (InstantiationException e) {
	                throw new ServletException(e);
	            } catch (IllegalAccessException e) {
	                throw new ServletException(e);
	            }
	        }
	    }

	    public void doGet(//get방식의 서비스 메소드
	        HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	        requestPro(request, response);
	    }

	    protected void doPost(//post방식의 서비스 메소드
	        HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	        requestPro(request, response);
	    }

	    //시용자의 요청을 분석해서 해당 작업을 처리
	    private void requestPro(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
		String view = null;
	    CommandAction com=null;
		try {
	            String command = request.getRequestURI();//[/DaengDaeng/member/join/member_choiceForm.do]
	            if (command.indexOf(request.getContextPath()) == 0) {
	               command = command.substring(request.getContextPath().length());//[/member/join/member_choiceForm.do]
	            }
	            com = (CommandAction)commandMap.get(command);//[CommandAction com= new ChoiceFormAction()]
	            if(com==null){
	            	//명령어오류
	            }
	            view = com.requestPro(request, response);///view/member/login/member_loginForm.jsp
	        } catch(Throwable e) {
	            throw new ServletException(e);
	        }  
	        RequestDispatcher dispatcher =request.getRequestDispatcher(view);
	        dispatcher.forward(request, response);//페이지이동
	    }
}
