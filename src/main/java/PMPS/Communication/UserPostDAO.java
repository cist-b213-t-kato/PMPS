package PMPS.Communication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import PMPS.DBsetting;

public class UserPostDAO {
	public UserPostDAO(){
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
	}

	public List<Integer> selectIsRead(String userId,boolean boo){
		String sql = "select * from userpost where read = ? and userid = ?";
		List<Integer> uList = new ArrayList<Integer>();

		try(Connection conn = DriverManager.getConnection(DBsetting.URL,DBsetting.USER,DBsetting.PASSWORD);
				PreparedStatement statement = conn.prepareStatement(sql);
				){
			statement.setBoolean(1,boo);
				statement.setString(2, userId);
				ResultSet resultSet = statement.executeQuery();

			    while(resultSet.next()){
					uList.add(resultSet.getInt("bulletinid"));
			    }
				return uList;
		}catch(SQLException e){
			e.printStackTrace();
		}
		return uList;
	}

	public void insert(UserPost u){
		String sql = "insert into userpost values(?,?,?)";
		try(Connection conn = DriverManager.getConnection(DBsetting.URL,DBsetting.USER,DBsetting.PASSWORD);
				PreparedStatement statement = conn.prepareStatement(sql);
				){
				statement.setInt(1,u.getBulletinId());
				statement.setString(2,u.getUserId());
				statement.setBoolean(3,false);
				statement.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	public void updateRead(boolean boo,int bId,String userId){
		String sql = "update userpost set read = ? where bulletinid = ? and userid = ?";
		try(Connection conn = DriverManager.getConnection(DBsetting.URL,DBsetting.USER,DBsetting.PASSWORD);
				PreparedStatement statement = conn.prepareStatement(sql);
				){
				statement.setBoolean(1, boo);
				statement.setInt(2,bId);
				statement.setString(3,userId);
				statement.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
}
