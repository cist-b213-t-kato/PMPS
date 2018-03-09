package PMPS.Communication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import PMPS.DBsetting;

public class BulletinDAO {
	public BulletinDAO(){
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
	}

	public Bulletin select(int bulletinId){
		String sql = "select * from bulletin where bulletinid = ?";
		Bulletin b = new Bulletin();

		try(Connection conn = DriverManager.getConnection(DBsetting.URL,DBsetting.USER,DBsetting.PASSWORD);
				PreparedStatement statement = conn.prepareStatement(sql);
				){
				statement.setInt(1, bulletinId);
				ResultSet resultSet = statement.executeQuery();
			    if(resultSet.next()){
			   		b.setBulletinId(bulletinId);
			   		b.setSubject(resultSet.getString("subject"));
			   		b.setMainText(resultSet.getString("maintext"));
			   		b.setUserId(resultSet.getString("userid"));
			   		return b;
			    }
		}catch(SQLException e){
			e.printStackTrace();
		}
		return b;
	}

	public void insert(Bulletin b){
		String sql = "insert into bulletin(subject,maintext,userid) values(?,?,?)";
		try(Connection conn = DriverManager.getConnection(DBsetting.URL,DBsetting.USER,DBsetting.PASSWORD);
				PreparedStatement statement = conn.prepareStatement(sql);
				){
				statement.setString(1, b.getSubject());
				statement.setString(2, b.getMainText());
				statement.setString(3, b.getUserId());
				statement.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	public int selectBulletinId(){
		String sql = "select max(bulletinid) AS maxid from bulletin";
		int tmp = 0;
		try(Connection conn = DriverManager.getConnection(DBsetting.URL,DBsetting.USER,DBsetting.PASSWORD);
				PreparedStatement statement = conn.prepareStatement(sql);
				){
				ResultSet resultSet = statement.executeQuery();
			    while(resultSet.next()){
			    	tmp=resultSet.getInt("maxid");
			    }
			    return tmp;
		}catch(SQLException e){
			e.printStackTrace();
		}
		return tmp;
	}
}
