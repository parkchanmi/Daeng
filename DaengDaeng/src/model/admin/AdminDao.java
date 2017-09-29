package model.admin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.dto.AdGrantDataBean;
import model.dto.BoardDataBean;
import model.dto.DogDataBean;
import model.dto.LostBoardDataBean;
import model.dto.MemberDataBean;
import model.dto.ReviewDataBean;
import model.member.MemberDao;

public class AdminDao {

	private static AdminDao instance = new AdminDao();
	MemberDao mdo = MemberDao.getInstance();
	private String sql;
	public static AdminDao getInstance() {
		return instance;
	}

	private AdminDao() {
	}

	private Connection getConnection() throws Exception {
		String jdbcDriver = "jdbc:apache:commons:dbcp:pool";
		return DriverManager.getConnection(jdbcDriver);
	}
	
	//회원구분통계
	//유입경로통계
	//연령+성별 통계
	//소모임카테고리 통계
	//카테고리별 멤버수 통계
	//방문통계
	
	//회원관리 -리스트,1개,삭제 -MemberDao
	
	public ArrayList<Integer> get_MsgReceiver(String loc){
		System.out.println(loc);
		ArrayList<Integer> receiver = new ArrayList<Integer>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from member where mem_loc like '%"+loc+"%'");
			rs = pstmt.executeQuery();
			while(rs.next()){//기존권한이 있던 멤버 - update
				receiver.add(rs.getInt("mem_code"));
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
		
		return receiver;
	}
	
	
	
	
	//관리자권한
	public int grantMember(int memcode,int[] gList){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result=0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from ad_grant where ad_memcode=?");
			pstmt.setInt(1, memcode);
			rs = pstmt.executeQuery();
			if(rs.next()){//기존권한이 있던 멤버 - update
				String query = "update ad_grant set ad_mem_ok=?,ad_board_ok=?,ad_lost_ok=?,ad_club_ok=? where ad_memcode=?";
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, gList[0]);
				pstmt.setInt(2, gList[1]);
				pstmt.setInt(3, gList[2]);
				pstmt.setInt(4, gList[3]);
				pstmt.setInt(5, memcode);
				result = pstmt.executeUpdate();//쿼리실행
			}
			else{//권한을 처음 받는 멤버 - insert
				String query = "insert into ad_grant values(ad_grant_sequence.nextval,?,?,?,?,?)";
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, memcode);
				pstmt.setInt(2, gList[0]);
				pstmt.setInt(3, gList[1]);
				pstmt.setInt(4, gList[2]);
				pstmt.setInt(5, gList[3]);
				result = pstmt.executeUpdate();//쿼리실행
			}
			
			if (result==1){
				System.out.println("권한부여완료");
				//커밋
			}
			else{
				System.out.println("권한부여실패");
				//롤백
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
	
	//등록상태,회원유형
	public int updateMember(int memCode,String memType,String memBlack){
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result=0;
		try {
			conn = getConnection();
			String query = "update member set mem_type=?, mem_black=? where mem_code=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memType);
			pstmt.setString(2, memBlack);
			pstmt.setInt(3, memCode);
			result = pstmt.executeUpdate();//쿼리실행
			
			if (result==1){
				//커밋
			}
			else{
				//롤백
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
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
    //관리자제외회원 표시 - 회원정보수정
	public ArrayList<AdGrantDataBean> get_AdminList(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<AdGrantDataBean> aList = new ArrayList<AdGrantDataBean>();
		int result=0;
		try {
			conn = getConnection();
			String query = "select * from ad_grant where ad_memcode!=0";//관리자제외 모든회원
			pstmt = conn.prepareStatement(query);
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				AdGrantDataBean admin = new AdGrantDataBean();
				admin.setAd_memcode(rs.getInt(1));
				admin.setAd_mem_ok(rs.getInt(2));
				admin.setAd_board_ok(rs.getInt(3));
				admin.setAd_lost_ok(rs.getInt(4));
				admin.setAd_club_ok(rs.getInt(5));
				
				//result=rs.getString("mem_email");
				aList.add(admin);
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
		return aList;
	}
	
	//멤코드로 멤버 정보 받아오기
		public MemberDataBean selectMember(String memcode){
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String result=null;
			MemberDataBean member=null; //멤버테이블 저장Databean
			try {
				conn = getConnection();
				//String query = "select * from member where mem_type='관리자'";
				String query = "select * from member where mem_code=? ";
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, memcode);
				
				rs = pstmt.executeQuery();//쿼리실행
				
				if (rs.next()){
					System.out.println(rs.getString("mem_code"));
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
					
					
					result=rs.getString("mem_code");
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
	
		//회원정보수정 업데이트
		public int updateMember(int memcode2 ,String black , String type, String job) throws Exception {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			//MemberDataBean member=null; //멤버테이블 저장Databean
			int result=0;
			try {
				conn = getConnection();
				//String query = "select * from member where mem_type='관리자'";
				String query = "update MEMBER set mem_black=?, mem_type=?, mem_job=? where mem_code=?";
				pstmt = conn.prepareStatement(query);

			    pstmt.setString(1, black);
			    pstmt.setString(2, type);
			    pstmt.setString(3, job);
			    pstmt.setInt(4, memcode2);
			    
			   result = pstmt.executeUpdate();//쿼리실행
				/*conn.setAutoCommit(false);
		    	conn.commit();
		    	conn.setAutoCommit(true); 
		    	
		    	*/
			   System.out.println("mem_code"+memcode2);
				System.out.println("mem_black"+black);
		    
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
		
		//게시판리스트가져오기 - 관리자 게시판 리스트 
		public ArrayList<BoardDataBean> get_BoardList(String boardtype){
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			ArrayList<BoardDataBean> bList = new ArrayList<BoardDataBean>();
			int result=0;
			String query="";
			
			try {
				conn = getConnection();
				if(boardtype.equals("전체")||boardtype==null){
					query = "select * from board";
					pstmt = conn.prepareStatement(query);
					rs=pstmt.executeQuery();
				}else{
					query = "select * from board where board_type=?";
					pstmt = conn.prepareStatement(query);
					pstmt.setString(1, boardtype);
					rs=pstmt.executeQuery();
				}
				
				while(rs.next()){
					BoardDataBean bo = new BoardDataBean();
					bo.setBoard_num(rs.getInt(1));
					bo.setBoard_writer(rs.getInt(2));
					bo.setBoard_filecode(rs.getInt(3));
					bo.setBoard_title(rs.getString(4));
					bo.setBoard_date(rs.getTimestamp(5));
					bo.setBoard_type(rs.getString(6));
					bo.setBoard_rdcount(rs.getInt(7));
					bo.setBoard_contents(rs.getString(8));
					bo.setBoard_ref(rs.getInt(9));
					bo.setBoard_step(rs.getInt(10));
					bo.setBoard_level(rs.getInt(11));
					
				
					//result=rs.getString("mem_email");
					bList.add(bo);
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
			return bList;
		}
		
		
		//리뷰리스트가져오기
				public ArrayList<ReviewDataBean> get_ReviewList(String reviewtype){
					Connection conn = null;
					PreparedStatement pstmt = null;
					ResultSet rs = null;
					ArrayList<ReviewDataBean> rList = new ArrayList<ReviewDataBean>();
					int result=0;
					String query="";
					try {
						conn = getConnection();
						if(reviewtype.equals("전체")||reviewtype==null){
							query = "select * from review";
							pstmt = conn.prepareStatement(query);
							rs=pstmt.executeQuery();
						}else{
							query = "select * from review where rev_typecode=?";
							pstmt = conn.prepareStatement(query);
							pstmt.setString(1, reviewtype);
							rs=pstmt.executeQuery();
						}
						MemberDao mdb = MemberDao.getInstance();
						while(rs.next()){
							ReviewDataBean re = new ReviewDataBean();
							re.setRev_num(rs.getInt(1));
							re.setRev_memcode(rs.getInt(2));
							re.setRev_typecode(rs.getString(3));
							re.setRev_typenum(rs.getInt(4));
							re.setRev_contents(rs.getString(5));
							re.setRev_date(rs.getTimestamp(6));
							re.setRev_writer_name(mdb.getName(rs.getInt(2)));
							
						
							//result=rs.getString("mem_email");
							rList.add(re);
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
					return rList;
				}

				//권한리스트가져오기
				public ArrayList<AdGrantDataBean> get_GrantList(){
					Connection conn = null;
					PreparedStatement pstmt = null;
					ResultSet rs = null;
					ArrayList<AdGrantDataBean> gList = new ArrayList<AdGrantDataBean>();
					int result=0;
					try {
						conn = getConnection();
						String query = "select * from review ";//관리자제외 모든회원
						pstmt = conn.prepareStatement(query);
						rs=pstmt.executeQuery();
						
						while(rs.next()){
							AdGrantDataBean ad = new AdGrantDataBean();
							ad .setAd_memcode(rs.getInt(1));
							ad .setAd_mem_ok(rs.getInt(2));
							ad .setAd_board_ok(rs.getInt(3));
							ad .setAd_lost_ok(rs.getInt(4));
							ad .setAd_club_ok(rs.getInt(5));
							
							
						
							//result=rs.getString("mem_email");
							gList.add(ad);
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
					return gList;
				}

				public int deleteBoard(int board_num02) {
					Connection conn = null;
					PreparedStatement pstmt = null;
					ResultSet rs = null;
					DogDataBean dog = null;
					BoardDataBean bo = null; // 멤버테이블 저장Databean
					int result = 0;
					try {
						conn = getConnection();
						// String query = "select * from member where mem_type='관리자'";
						String query = "delete from board where board_num=?";
						pstmt = conn.prepareStatement(query);
						pstmt.setInt(1, board_num02);

						// System.out.println("dog_code="+dog_code02);

						result = pstmt.executeUpdate();// 쿼리실행

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
				//멤코드로 멤버 정보 받아오기
				public AdGrantDataBean selectAdmin(int admemcode) {
					//멤코드로 멤버 정보 받아오기
					
						Connection conn = null;
						PreparedStatement pstmt = null;
						ResultSet rs = null;
						String result=null;
						AdGrantDataBean admin =null; //멤버테이블 저장Databean
						try {
							conn = getConnection();
							//String query = "select * from member where mem_type='관리자'";
							String query = "select * from ad_grant where ad_memcode=?";
							pstmt = conn.prepareStatement(query);
							pstmt.setInt(1, admemcode);
							
							rs = pstmt.executeQuery();//쿼리실행
							
							if (rs.next()){
								admin=new AdGrantDataBean();
								admin.setAd_memcode(rs.getInt(1));
								admin.setAd_mem_ok(rs.getInt(2));
								admin.setAd_board_ok(rs.getInt(3));
								admin.setAd_lost_ok(rs.getInt(4));
								admin.setAd_club_ok(rs.getInt(5));
								System.out.println("권한"+rs.getInt(1));
								
								
							}
							else{
								//System.out.println("결과없음");
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
						return admin;
					}
          //리뷰삭제
				public int deleteReview(int rev_num02) {
					Connection conn = null;
					PreparedStatement pstmt = null;
					ResultSet rs = null;
					
					ReviewDataBean re = null; // 멤버테이블 저장Databean
					int result = 0;
					try {
						conn = getConnection();
						// String query = "select * from member where mem_type='관리자'";
						String query = "delete from review where rev_num=?";
						pstmt = conn.prepareStatement(query);
						pstmt.setInt(1, rev_num02);

						// System.out.println("dog_code="+dog_code02);

						result = pstmt.executeUpdate();// 쿼리실행

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
 //로스트보드 리스트 가져오기
				public ArrayList<LostBoardDataBean> get_LostBdardList(String losttype) {
					Connection conn = null;
					PreparedStatement pstmt = null;
					ResultSet rs = null;
					ArrayList<LostBoardDataBean> lbList = new ArrayList<LostBoardDataBean>();
					int result=0;
					String query="";
					System.out.println(losttype);
					try {
						conn = getConnection();
						if(losttype.equals("전체")||losttype==null){
							query = "select * from lost_board";
							pstmt = conn.prepareStatement(query);
							rs=pstmt.executeQuery();
						}else{
							query = "select * from lost_board where lost_type=?";
							pstmt = conn.prepareStatement(query);
							pstmt.setString(1, losttype);
							rs=pstmt.executeQuery();
						}
						MemberDao mdb = MemberDao.getInstance();
						while(rs.next()){
							LostBoardDataBean lost = new LostBoardDataBean();
							
							lost.setLost_code(rs.getInt(1));
							lost.setLost_writer(rs.getInt(2));
							lost.setLost_filecode(rs.getInt(3));
							lost.setLost_type(rs.getString (4));
							lost.setLost_loc1(rs.getString(5));
							lost.setLost_loc2(rs.getString (6));
							lost.setLost_date(rs.getTimestamp(7));
							lost.setLost_contents(rs.getString(8));
							lost.setLost_writer_name(mdb.getName(rs.getInt(2)));
						
							
							
						
							//result=rs.getString("mem_email");
							lbList.add(lost);
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
					return lbList;
				}
//일반회원카운트
				public int getCountnormal() {
						Connection conn = null;
						PreparedStatement pstmt = null;
						int cnt=0;
						try {
							conn = getConnection();
							String query = "select count(*) from member where mem_type = '일반'";
							pstmt = conn.prepareStatement(query);
							ResultSet rs = pstmt.executeQuery();//쿼리실행
							
							if (rs.next()){
								cnt = rs.getInt("count(*)");
							}
							
						} catch (Exception ex) {
							ex.printStackTrace();
						} finally {
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
						return cnt;
					}
//견주회원 카운트
				public int getCountdogowner() {
					Connection conn = null;
					PreparedStatement pstmt = null;
					int cnt=0;
					try {
						conn = getConnection();
						String query = "select count(*) from member where mem_type = '견주'";
						pstmt = conn.prepareStatement(query);
						ResultSet rs = pstmt.executeQuery();//쿼리실행
						
						if (rs.next()){
							cnt = rs.getInt("count(*)");
						}
						
					} catch (Exception ex) {
						ex.printStackTrace();
					} finally {
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
					return cnt;
				}
//전문가회원카운트
				public int getCountexpert() {
					Connection conn = null;
					PreparedStatement pstmt = null;
					int cnt=0;
					try {
						conn = getConnection();
						String query = "select count(*) from member where mem_type = '전문가'";
						pstmt = conn.prepareStatement(query);
						ResultSet rs = pstmt.executeQuery();//쿼리실행
						
						if (rs.next()){
							cnt = rs.getInt("count(*)");
						}
						
					} catch (Exception ex) {
						ex.printStackTrace();
					} finally {
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
					return cnt;
				}

				public int getCountsearch() {
					Connection conn = null;
					PreparedStatement pstmt = null;
					int cnt=0;
					try {
						conn = getConnection();
						String query = "select count(*) from member where mem_visit = '검색'";
						pstmt = conn.prepareStatement(query);
						ResultSet rs = pstmt.executeQuery();//쿼리실행
						
						if (rs.next()){
							cnt = rs.getInt("count(*)");
						}
						
					} catch (Exception ex) {
						ex.printStackTrace();
					} finally {
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
					return cnt;
				}

				public int getCountfriend() {
					Connection conn = null;
					PreparedStatement pstmt = null;
					int cnt=0;
					try {
						conn = getConnection();
						String query = "select count(*) from member where mem_visit = '지인추천'";
						pstmt = conn.prepareStatement(query);
						ResultSet rs = pstmt.executeQuery();//쿼리실행
						
						if (rs.next()){
							cnt = rs.getInt("count(*)");
						}
						
					} catch (Exception ex) {
						ex.printStackTrace();
					} finally {
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
					return cnt;
				}

				public int getCountad() {
					Connection conn = null;
					PreparedStatement pstmt = null;
					int cnt=0;
					try {
						conn = getConnection();
						String query = "select count(*) from member where mem_visit = '광고'";
						pstmt = conn.prepareStatement(query);
						ResultSet rs = pstmt.executeQuery();//쿼리실행
						
						if (rs.next()){
							cnt = rs.getInt("count(*)");
						}
						
					} catch (Exception ex) {
						ex.printStackTrace();
					} finally {
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
					return cnt;
				}

				public int getCountsns() {
					Connection conn = null;
					PreparedStatement pstmt = null;
					int cnt=0;
					try {
						conn = getConnection();
						String query = "select count(*) from member where mem_visit = 'SNS'";
						pstmt = conn.prepareStatement(query);
						ResultSet rs = pstmt.executeQuery();//쿼리실행
						
						if (rs.next()){
							cnt = rs.getInt("count(*)");
						}
						
					} catch (Exception ex) {
						ex.printStackTrace();
					} finally {
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
					return cnt;
				}

				public int getCountetc() {
					Connection conn = null;
					PreparedStatement pstmt = null;
					
					int cnt=0;
					try {
						conn = getConnection();
						String query = "select count(*) from member where mem_visit = '기타'";
						pstmt = conn.prepareStatement(query);
						ResultSet rs = pstmt.executeQuery();//쿼리실행
						
						if (rs.next()){
							cnt = rs.getInt("count(*)");
						}
						
					} catch (Exception ex) {
						ex.printStackTrace();
					} finally {
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
					return cnt;
				}

				public int getUserinfo(String memgender, String memage) {
		               Connection conn = null;
		               PreparedStatement pstmt = null;
		               ResultSet rs = null;
		               int  cnt=0;
		               try {System.out.println("성별:"+memgender);
		               System.out.println("나이:"+ memage);
		                  conn = getConnection();
		                  
		                  String query = "select count(*) from member  where mem_gender= ? and mem_age=? "; 
		                  pstmt = conn.prepareStatement(query);
		                  pstmt.setString(1, memgender);
		                  pstmt.setString(2, memage);
		               
		                  rs=pstmt.executeQuery();
		                  if (rs.next()){
		                     cnt = rs.getInt(1);
		                  }
		            
		               } catch (Exception ex) {
		                  ex.printStackTrace();
		               } finally {
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
		               return cnt;
		            }
              // 멤버리스트에서 셀렉트박스 
				public ArrayList<MemberDataBean> getSelectboxMember(String memtype, String memblack) {
					Connection conn = null;
					PreparedStatement pstmt = null;
					ResultSet rs = null;
					ArrayList<MemberDataBean> mList = new ArrayList<MemberDataBean>();
					String sql = "";
					if(memtype==null) memtype="전체";
					if(memblack==null) memblack="전체";
					try {
						
						conn = getConnection();
						if (memtype.equals("전체")&& memblack.equals("전체")){
							sql = "select * from member";
							pstmt = conn.prepareStatement(sql);
							rs = pstmt.executeQuery();//쿼리실행
							System.out.println("1");
						}else if(memtype.equals("전체") && !memblack.equals("전체")){
							sql = "select * from member where mem_black=?";
							pstmt = conn.prepareStatement(sql);
							pstmt.setString(1, memblack);
							rs = pstmt.executeQuery();//쿼리실행
							System.out.println("2");
							
						}else if(!memtype.equals("전체") && memblack.equals("전체")){
							sql = "select * from member where mem_type=?";
							pstmt = conn.prepareStatement(sql);
							pstmt.setString(1, memtype);
							rs = pstmt.executeQuery();//쿼리실행
							System.out.println("3");
						}
						else{   
							sql = "select * from member where mem_type=? and mem_black=?";
							pstmt = conn.prepareStatement(sql);
							pstmt.setString(1, memtype);
							pstmt.setString(2, memblack);
							rs = pstmt.executeQuery();//쿼리실행
							System.out.println("4");
						} 
						
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
							System.out.println(member.getMem_name());
							mList.add(member);
						}
			        
					} catch (Exception ex) {
						ex.printStackTrace();
					} finally {
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
				
				
               //게시판 타입선택으로 게시판정보받아옴
				public BoardDataBean getSelectboardtype(String boardtype) {
					Connection conn = null;
					PreparedStatement pstmt = null;
					ResultSet rs = null;
					String result=null;
					BoardDataBean board=null; //멤버테이블 저장Databean
					try {
						conn = getConnection();
						//String query = "select * from member where mem_type='관리자'";
						String query = "select * from Board where board_type=? ";
						pstmt = conn.prepareStatement(query);
						pstmt.setString(1, boardtype);
						
						rs = pstmt.executeQuery();//쿼리실행
						
						while (rs.next()){
							//System.out.println(rs.getString("board_type"));
							BoardDataBean bo = new BoardDataBean();
							bo.setBoard_num(rs.getInt(1));
							bo.setBoard_writer(rs.getInt(2));
							bo.setBoard_filecode(rs.getInt(3));
							bo.setBoard_title(rs.getString(4));
							bo.setBoard_date(rs.getTimestamp(5));
							bo.setBoard_type(rs.getString(6));
							bo.setBoard_rdcount(rs.getInt(7));
							bo.setBoard_contents(rs.getString(8));
							bo.setBoard_ref(rs.getInt(9));
							bo.setBoard_step(rs.getInt(10));
							bo.setBoard_level(rs.getInt(11));
							
							
						//	result=rs.getString("board_type");
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
					return board;
				}		
  				
				
   
}
