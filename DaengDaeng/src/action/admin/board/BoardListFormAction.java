package action.admin.board;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import javax.servlet.http.HttpSession;

import model.admin.AdminDao;

import model.dto.BoardDataBean;
import model.dto.DogDataBean;
import model.dto.MemberDataBean;
import model.member.MemberDao;
import action.CommandAction;



public class BoardListFormAction implements CommandAction{
    public String requestPro(HttpServletRequest request,
            HttpServletResponse response)throws Throwable {
        	
    	request.setCharacterEncoding("UTF-8");
        	
    	HttpSession session = request.getSession();
		MemberDataBean mdb = (MemberDataBean) session.getAttribute("memberDTO");
		
        	
        	AdminDao adPro = AdminDao.getInstance();// DB처리	
        	MemberDao dbPro = MemberDao.getInstance();
        	
        	String boardtype =  request.getParameter("board_type");
        	if(boardtype==null){
        		boardtype="전체";
        	}
        	//System.out.println(board);
        	ArrayList<BoardDataBean> bList = adPro.get_BoardList(boardtype);
        	if(bList.isEmpty())
        		bList=null;
        	
        	
        	//System.out.println(mList.size()); 	
        	request.setAttribute("board_list", bList);
        	
        	return "/view/admin/board/admin_boardlistForm.jsp";
            
            
        }
    }	