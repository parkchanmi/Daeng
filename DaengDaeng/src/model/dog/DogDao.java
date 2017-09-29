package model.dog;

import java.sql.*;
import javax.sql.*;

import model.dto.DogDataBean;
import model.dto.MemberDataBean;

import javax.naming.*;
import java.util.*;
import model.dto.MemberDataBean;

public class DogDao {
	private static DogDao instance = new DogDao();

	public static DogDao getInstance() {
		return instance;
	}

	private DogDao() {
	}

	private Connection getConnection() throws Exception {
		String jdbcDriver = "jdbc:apache:commons:dbcp:pool";
		return DriverManager.getConnection(jdbcDriver);
	}
	
	//inputPro.jsp
		public void insertDog(DogDataBean dog,int memcode) throws Exception {
			Connection conn = null;
			PreparedStatement pstmt = null;
			
			try {
				conn = getConnection();
	//DriverManager.getConnection(jdbc:apache:commons:dbcp:pool);
				pstmt = conn.prepareStatement(
				"insert into Dog values (dog_sequence.nextval,?,?,?,?,?,?)");
				pstmt.setInt(1, memcode);
				pstmt.setInt(2, dog.getFile_code());
				pstmt.setString(3, dog.getDog_name());
				pstmt.setString(4, dog.getDog_gender());
				pstmt.setString(5, dog.getDog_age() ); 
				pstmt.setString(6, dog.getDog_kind());
					
				pstmt.executeUpdate();
				
			
			} catch(Exception ex) {
				ex.printStackTrace();
			} finally {
				if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
				if (conn != null) try { conn.close(); } catch(SQLException ex) {}
			}

		}
	
		public DogDataBean selectDog(int dog_code) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String result = null;
			DogDataBean dog = null; // 멤버테이블 저장Databean

			try {
				conn = getConnection();
				// String query = "select * from member where mem_type='관리자'";
				String query = "select * from dog where dog_code=?";
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, dog_code);// 쿼리완성
				rs = pstmt.executeQuery();// 쿼리실행

				if (rs.next()) {
					// System.out.println(rs.getString("mem_email"));
					dog = new DogDataBean();
					dog.setDog_code(rs.getInt(1));
					dog.setDog_memcode(rs.getInt(2));
					dog.setFile_code(rs.getInt(3));
					dog.setDog_name(rs.getString(4));
					dog.setDog_gender(rs.getString(5));
					dog.setDog_age(rs.getString(6));
					dog.setDog_kind(rs.getString(7));

				} else {
					System.out.println("결과없음");
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
			return dog;
		}

		public int updateDog(String dog_name, String dog_kind, String dog_gender, String dog_age, int file_code02,
				int dog_code02) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			DogDataBean dog = null;

			MemberDataBean member = null; // 멤버테이블 저장Databean
			int result = 0;
			try {
				conn = getConnection();
				// String query = "select * from member where mem_type='관리자'";
				String query = "update dog set file_code=?, dog_name=?, dog_gender=?,dog_age=?, dog_kind=? where dog_code=?";
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, file_code02);
				pstmt.setString(2, dog_name);
				pstmt.setString(3, dog_gender);
				pstmt.setString(4, dog_age);
				pstmt.setString(5, dog_kind);
				pstmt.setInt(6, dog_code02);

				result = pstmt.executeUpdate();// 쿼리실행
				/*
				 * conn.setAutoCommit(false); pstmt.executeUpdate();//쿼리실행
				 * conn.commit(); conn.setAutoCommit(true);
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

		public ArrayList<DogDataBean> getDogList(int dog_memcode) throws Exception {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			ResultSet rs2 = null;
			ArrayList<DogDataBean> doglist = new ArrayList<DogDataBean>();

			try {
				conn = getConnection();

				pstmt = conn.prepareStatement("select * from dog where dog_memcode=?");
				pstmt.setInt(1, dog_memcode);
				System.out.println(dog_memcode);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					DogDataBean dogL = new DogDataBean();
					dogL.setDog_code(rs.getInt(1));
					dogL.setDog_memcode(rs.getInt(2));
					dogL.setFile_code(rs.getInt(3));
					dogL.setDog_name(rs.getString(4));
					dogL.setDog_gender(rs.getString(5));
					dogL.setDog_age(rs.getString(6));
					dogL.setDog_kind(rs.getString(7));
					
					
					String sql="select file_path from file_info where file_code=?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, rs.getInt(3));
					rs2=pstmt.executeQuery();
					if(rs2.next()){
						dogL.setDog_filepath(rs2.getString(1));
					}else{
						dogL.setDog_filepath("http://localhost:8081/DaengDaeng/upload/noimage.gif");
					}
					
					

					doglist.add(dogL);
				}
				System.out.println(doglist.size());
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
			return doglist;
		}

		public DogDataBean getDog(int dogCode) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String result = null;
			DogDataBean dog = null;
			try {
				conn = getConnection();
				String query = "select * from dog where dog_code=?";
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, dogCode);
				rs = pstmt.executeQuery();// 쿼리실행

				if (rs.next()) {
					dog = new DogDataBean();
					dog.setDog_code(rs.getInt(1));
					dog.setDog_memcode(rs.getInt(2));
					dog.setFile_code(rs.getInt(3));
					dog.setDog_name(rs.getString(4));
					dog.setDog_gender(rs.getString(5));
					dog.setDog_age(rs.getString(6));
					dog.setDog_kind(rs.getString(7));
				} else {
					System.out.println("결과없음");
					result = "결과없음";
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
			return dog;
		}

		public int deleteDog(int dog_code02) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			DogDataBean dog = null;
			MemberDataBean member = null; // 멤버테이블 저장Databean
			int result = 0;
			try {
				conn = getConnection();
				// String query = "select * from member where mem_type='관리자'";
				String query = "delete from dog where dog_code=?";
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, dog_code02);

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

		public int addDog(DogDataBean dog) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			int result = 0;

			try {
				conn = getConnection();

	          
				String query = "insert into dog values (dog_sequence.nextval,?,?,?,?,?,?)";
				pstmt = conn.prepareStatement(query);
				
				pstmt.setInt(1, dog.getDog_memcode());
		        pstmt.setInt(2, dog.getFile_code());
		        pstmt.setString(3, dog.getDog_name());
		        pstmt.setString(4, dog.getDog_gender());
		        pstmt.setString(5, dog.getDog_age());
		        pstmt.setString(6, dog.getDog_kind());	          
				
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
		
		public DogDataBean selectDogMem(int dog_memcode) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String result = null;
			DogDataBean dog = null; // 멤버테이블 저장Databean

			try {
				conn = getConnection();
				// String query = "select * from member where mem_type='관리자'";
				String query = "select * from dog where dog_memcode=?";
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, dog_memcode);// 쿼리완성
				rs = pstmt.executeQuery();// 쿼리실행

				if (rs.next()) {
					// System.out.println(rs.getString("mem_email"));
					dog = new DogDataBean();
					dog.setDog_code(rs.getInt(1));
					dog.setDog_memcode(rs.getInt(2));
					dog.setFile_code(rs.getInt(3));
					dog.setDog_name(rs.getString(4));
					dog.setDog_gender(rs.getString(5));
					dog.setDog_age(rs.getString(6));
					dog.setDog_kind(rs.getString(7));

				} else {
					System.out.println("결과없음");
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
			return dog;
		}

	

}
