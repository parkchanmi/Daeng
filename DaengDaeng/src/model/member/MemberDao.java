package model.member;

import java.sql.*;
import javax.sql.*;
import javax.naming.*;
import java.util.*;
import model.dto.MemberDataBean;
import model.dto.MessageDataBean;

public class MemberDao {
	private static MemberDao instance = new MemberDao();

	public static MemberDao getInstance() {
		return instance;
	}

	private MemberDao() {
	}

	private Connection getConnection() throws Exception {
		String jdbcDriver = "jdbc:apache:commons:dbcp:pool";
		return DriverManager.getConnection(jdbcDriver);
	}
	public MemberDataBean selectMember(String email, String pw){//로그인 -id와 pw가 맞는 멤버 찾아오기
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result=null;
		MemberDataBean member=null; //멤버테이블 저장Databean
		try {
			conn = getConnection();
			//String query = "select * from member where mem_type='관리자'";
			String query = "select * from member where mem_email=? and mem_pw=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, email);
			pstmt.setString(2, pw);//쿼리완성
			rs = pstmt.executeQuery();//쿼리실행
			
			if (rs.next()){
				System.out.println(rs.getString("mem_email"));
				member=new MemberDataBean();
				member.setMem_code(rs.getInt(1));
				member.setMem_type(rs.getString(2));
				member.setMem_email(rs.getString(3));
				member.setMem_pw(rs.getString(4));
				member.setMem_name(rs.getString(5));
				member.setMem_gender(rs.getString(6));
				member.setMem_age(rs.getString(7));
				member.setMem_hp(rs.getString(8));
				member.setMem_visit(rs.getString(9));
				
				member.setMem_black(rs.getString(10));
				member.setMem_dog_ok(rs.getInt(11));
				member.setMem_loc(rs.getString(12));
				member.setMem_job(rs.getString(13));
				member.setMem_regdate(rs.getTimestamp(14));
				result=rs.getString("mem_email");
			}
			else{
				System.out.println("결과없음");
				result="결과없음";
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
		}
		return member;
	}
	public String getName(int memCode){//쪽지 -memCode에 해당하는 회원이름 불러오기
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result=null;
		try {
			conn = getConnection();
			String query = "select mem_name from member where mem_code=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memCode);
			rs = pstmt.executeQuery();//쿼리실행
			
			if (rs.next()){
				result=rs.getString(1);
			}
			else{
				System.out.println("결과없음");
				result="결과없음";
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
		}
		return result;
	}

	public int updateMember(String pw,  String hp, String loc,String memtype, int memcode){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		MemberDataBean member=null; //멤버테이블 저장Databean
		int result=0;
		try {
			conn = getConnection();
			//String query = "select * from member where mem_type='관리자'";
			String query = "update MEMBER set mem_pw=? ,mem_hp=? , mem_loc=?, mem_type=? where mem_code=?";
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, pw);
			pstmt.setString(2, hp);
			pstmt.setString(3, loc);
			pstmt.setString(4, memtype);
			pstmt.setInt(5, memcode);
	    	
			result = pstmt.executeUpdate();//쿼리실행
			/*conn.setAutoCommit(false);
	    	conn.commit();
	    	conn.setAutoCommit(true); 
	    	*/
	    
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
		}
		return result;
	}
	
	

	
	//회원리스트가져오기
	public ArrayList<MemberDataBean> get_MemberList(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<MemberDataBean> mList = new ArrayList<MemberDataBean>();
		int result=0;
		try {
			conn = getConnection();
			String query = "select * from member where mem_code!=0";//관리자제외 모든회원
			pstmt = conn.prepareStatement(query);
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				MemberDataBean member = new MemberDataBean();
				member.setMem_code(rs.getInt(1));
				member.setMem_type(rs.getString(2));
				member.setMem_email(rs.getString(3));
				member.setMem_pw(rs.getString(4));
				member.setMem_name(rs.getString(5));
				member.setMem_gender(rs.getString(6));
				member.setMem_age(rs.getString(7));
				member.setMem_hp(rs.getString(8));
				member.setMem_visit(rs.getString(9));
				
				member.setMem_black(rs.getString(10));
				member.setMem_dog_ok(rs.getInt(11));
				member.setMem_loc(rs.getString(12));
				member.setMem_job(rs.getString(13));
				member.setMem_regdate(rs.getTimestamp(14));
				//result=rs.getString("mem_email");
				mList.add(member);
			}
		}catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
		}
		return mList;
	}
	//코드에 해당하는 멤버(1)찾아오기
	public MemberDataBean getMember(int memCode){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result=null;
		MemberDataBean member =null;
		try {
			conn = getConnection();
			String query = "select * from member where mem_code=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memCode);
			rs = pstmt.executeQuery();//쿼리실행
			
			if (rs.next()){
				member = new MemberDataBean();
				member.setMem_code(rs.getInt(1));
				member.setMem_type(rs.getString(2));
				member.setMem_email(rs.getString(3));
				member.setMem_pw(rs.getString(4));
				member.setMem_name(rs.getString(5));
				member.setMem_gender(rs.getString(6));
				member.setMem_age(rs.getString(7));
				member.setMem_hp(rs.getString(8));
				member.setMem_visit(rs.getString(9));
				
				member.setMem_black(rs.getString(10));
				member.setMem_dog_ok(rs.getInt(11));
				member.setMem_loc(rs.getString(12));
				member.setMem_job(rs.getString(13));
				member.setMem_regdate(rs.getTimestamp(14));
			}
			else{
				System.out.println("결과없음");
				result="결과없음";
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
		}
		return member;
	}
	public int getMember(String memName){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result=-1;
		try {
			conn = getConnection();
			String query = "select * from member where mem_name=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memName);
			rs = pstmt.executeQuery();//쿼리실행
			
			if (rs.next()){
				result=rs.getInt("mem_code");//이름에 맞는회원이 있으면 mem_code반환
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
		}
		return result;
	}
	
	//회원정보수정하기
	public int updateMember(MemberDataBean member){
		return 0;
	}
	//회원정보삭제하기
	public int deleteMember(int memCode){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result=0;
		try {
			conn = getConnection();
			String query = "delete from member where mem_code=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memCode);
			result = pstmt.executeUpdate();//쿼리실행
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
		}
		return result;
	}
	
	//email찾기
		public String selectemail(String name, String hp){
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String result=null;
			MemberDataBean member=null; //멤버테이블 저장Databean
			try {
				conn = getConnection();
				//String query = "select * from member where mem_type='관리자'";
				String query = "select * from member where mem_name=? and mem_hp=?";
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, name);
				pstmt.setString(2, hp);//쿼리완성
				rs = pstmt.executeQuery();//쿼리실행
				
				if (rs.next()){
					
					System.out.println(rs.getString("mem_email"));
					result=rs.getString("mem_email");
				}
				else{
					System.out.println("결과없음");
					result="결과없음";
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				if (rs != null)
					try {
						rs.close();
					} catch (SQLException ex) {
					}
				if (pstmt != null)
					try {
						pstmt.close();
					} catch (SQLException ex) {
					}
				if (conn != null)
					try {
						conn.close();
					} catch (SQLException ex) {
					}
			}
			return result;
		}
		

		
		//inputPro.jsp
			public int insertMember(MemberDataBean member) throws Exception {
				Connection conn = null;
				PreparedStatement pstmt = null;
				int result=0;
				try {
					
					conn = getConnection();
		//DriverManager.getConnection(jdbc:apache:commons:dbcp:pool);
					pstmt = conn.prepareStatement(
							
					"insert into MEMBER values (insert_member.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?)");
					pstmt.setString(1, member.getMem_type());
					pstmt.setString(2, member.getMem_email());
					pstmt.setString(3, member.getMem_pw());
					pstmt.setString(4, member.getMem_name());
					pstmt.setString(5, member.getMem_gender()); 
					pstmt.setString(6, member.getMem_age());
					pstmt.setString(7, member.getMem_hp());
					pstmt.setString(8, member.getMem_visit());
			
					//System.out.println(member.getMem_regdate());
					pstmt.setString(9, member.getMem_black());
					pstmt.setInt(10, member.getMem_dog_ok());
					pstmt.setString(11, member.getMem_loc());
					pstmt.setString(12, member.getMem_job());
					
					pstmt.setTimestamp(13, member.getMem_regdate());
					result=pstmt.executeUpdate();
					
				
				} catch(Exception ex) {
					ex.printStackTrace();
				} finally {
					if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
					if (conn != null) try { conn.close(); } catch(SQLException ex) {}
				}
				return result;
			}
		
		//member_normal_pro.jsp
			public int userCheck(String mem_email, String mem_pw) throws Exception {
				Connection conn = null;
				PreparedStatement pstmt =null;
				ResultSet rs = null;
				String dbpasswd="";
				int x=-1;
				
				try {
					conn = getConnection();//겟 커넥션메서드로 커넥션풀에서 커넥션꺼내옴
					
					pstmt = conn.prepareStatement(
					"select passwd from MEMBER where mem_email = ?");
					pstmt.setString(1, mem_email);
					rs= pstmt.executeQuery();
					
					if(rs.next()){
						dbpasswd = rs.getString("mem_pw");
						if(dbpasswd.equals(mem_pw))
							x= 1; //인증성공
						else
							x= 0; //비밀번호 틀림
					}else
						x= -1;//해당 아이디 없음
				}catch(Exception ex) {
					ex.printStackTrace();
				}finally {
					if (rs != null) try { rs.close(); } catch(SQLException ex) {}
		            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
		            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
				}
				return x;
			}
		
		//confirmemail.jsp
			public int confirmmem_email(String mem_email) throws Exception {
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs= null;
				int x= -1;//경우의 수
				
				try {
					conn = getConnection();
					
					pstmt = conn.prepareStatement(
					"select mem_email from MEMBER where mem_email = ?");
					pstmt.setString(1, mem_email);
					rs= pstmt.executeQuery();
					
					if(rs.next())
						x= 1;//해당 이메일 있음
					else
						x= -1;//해당 이메일 없음
						
				} catch(Exception ex) {
					ex.printStackTrace();
				}finally {
					if (rs != null) try { rs.close(); } catch(SQLException ex) {}
		            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
		            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
				}
				return x;
			}
			
			//닉네임 중복여부
			public int confirmmem_name(String mem_name) throws Exception {
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs= null;
				int x= -1;//경우의 수
				
				try {
					conn = getConnection();
					
					pstmt = conn.prepareStatement(
					"select mem_email from MEMBER where mem_name = ?");
					pstmt.setString(1, mem_name);
					rs= pstmt.executeQuery();
					
					if(rs.next())
						x= 1;//해당 이메일 있음
					else
						x= -1;//해당 이메일 없음
						
				} catch(Exception ex) {
					ex.printStackTrace();
				}finally {
					if (rs != null) try { rs.close(); } catch(SQLException ex) {}
		            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
		            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
				}
				return x;
			}
			
			

			public int getMemcode(String mem_email)throws Exception  {
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null ;
				int result   = 0;
				
				
				MemberDataBean member=null;
				try{
					
				conn = getConnection();
				pstmt = conn.prepareStatement(
				"select mem_code from MEMBER where mem_email = ?");
				pstmt.setString(1, mem_email);
				rs= pstmt.executeQuery();
				
		
				if(rs.next()){
					member=new MemberDataBean();
					member.setMem_code(rs.getInt(1));
					result=rs.getInt("mem_code");
				}else{
					result=-1;
				}
				
			}catch(Exception ex) {
				ex.printStackTrace();
			}finally {
				if (rs != null) try { rs.close(); } catch(SQLException ex) {}
	            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
	            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
			}
			
			return  result;
		}
			
			//pw정보확인
			public int selectpw(String email, String name, String hp)throws Exception{
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				int result= -1;
				try {
					conn = getConnection();
					//String query = "select * from member where mem_type='관리자'";
					String query = "select * from member where mem_email=? and mem_name=? and mem_hp=?";
					pstmt = conn.prepareStatement(query);
					pstmt.setString(1, email);
					pstmt.setString(2, name);
					pstmt.setString(3, hp);
					rs = pstmt.executeQuery();//쿼리실행
					
					if (rs.next()){
					
						result=rs.getInt("mem_code");
					}
					else{
						result=-1;
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				} finally {
					if (rs != null)
						try {
							rs.close();
						} catch (SQLException ex) {
						}
					if (pstmt != null)
						try {
							pstmt.close();
						} catch (SQLException ex) {
						}
					if (conn != null)
						try {
							conn.close();
						} catch (SQLException ex) {
						}
				}
				return result;
			}
			
			
	       //새로운비밀번호
			public int newpw(String password, int memcode) throws Exception{
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				
				int result=-1;
				try {
					conn = getConnection();
					String query = "update MEMBER set mem_pw=? where mem_code=?";
					pstmt = conn.prepareStatement(query);
					pstmt.setString(1, password);
					pstmt.setInt(2, memcode);
					/*pstmt.setString(3, name);
					pstmt.setString(4, hp);*/
					
					result = pstmt.executeUpdate();
					
				}catch(Exception ex) {
					ex.printStackTrace();
				}finally {
					if (rs != null) try { rs.close(); } catch(SQLException ex) {}
		            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
		            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
				}
				return result;
			
				
			}

	

}
