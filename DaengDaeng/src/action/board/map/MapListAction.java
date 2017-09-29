package action.board.map;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import model.dto.MapDataBean;
import model.map.MapDao;

public class MapListAction implements CommandAction {
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		String type= request.getParameter("type");
		System.out.println("파라미터 타입:"+type);
		if (type==null){
			type="카페";
		}
		System.out.println("파라미터 타입2:"+type);
		MapDao mdbPro = MapDao.getInstance();
		ArrayList<MapDataBean> articleList = mdbPro.getMap(type);
		request.setAttribute("maplist", articleList);
		return "/view/board/map/map_list.jsp";
	}

}
