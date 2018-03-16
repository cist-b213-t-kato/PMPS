package PMPS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserAccountDAO {

	public UserAccountDAO() {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
	}

	public UserAccount checkLogIn(String userId, String password) {
		String sql = "select * from useraccount where userid = ? AND password = ?";

		try (Connection conn = DriverManager.getConnection(DBsetting.URL, DBsetting.USER, DBsetting.PASSWORD);
				PreparedStatement statement = conn.prepareStatement(sql);) {
			statement.setString(1, userId);
			statement.setString(2, password);
			ResultSet resultSet = statement.executeQuery();

			UserAccount user = new UserAccount();

			if (resultSet.next()) {

				String accountid = resultSet.getString("userid");
				String userName = resultSet.getString("username");
				int grade = resultSet.getInt("grade");
				user.setUserId(accountid);
				user.setUserName(userName);
				user.setGrade(grade);
				return user;
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<String> selectGrade(int grade){
		String sql = "select * from useraccount where grade = ?";
		List<String> idList = new ArrayList<String>();

		try(Connection conn = DriverManager.getConnection(DBsetting.URL,DBsetting.USER,DBsetting.PASSWORD);
				PreparedStatement statement = conn.prepareStatement(sql);
				){
				statement.setInt(1,grade);
				ResultSet resultSet = statement.executeQuery();
			    if(resultSet.next()){
			    	idList.add(resultSet.getString("userid"));
			    }
		}catch(SQLException e){
			e.printStackTrace();
		}
		return idList;
	}

	public UserAccount selectUser(String userId) {
		String sql = "select * from useraccount where userid = ?";

		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		try (Connection conn = DriverManager.getConnection(DBsetting.URL, DBsetting.USER, DBsetting.PASSWORD);
				PreparedStatement statement = conn.prepareStatement(sql);) {
			statement.setString(1, userId);
			ResultSet resultSet = statement.executeQuery();

			UserAccount user = new UserAccount();

			if (resultSet.next()) {

				String accountid = resultSet.getString("userid");
				String userName = resultSet.getString("username");
				int grade = resultSet.getInt("grade");
				user.setUserId(accountid);
				user.setUserName(userName);
				user.setGrade(grade);
				return user;
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String selectUserId(String name) {
		String sql = "select * from useraccount where username = ?";

		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		try (Connection conn = DriverManager.getConnection(DBsetting.URL, DBsetting.USER, DBsetting.PASSWORD);
				PreparedStatement statement = conn.prepareStatement(sql);) {
			statement.setString(1, name);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				return resultSet.getString("userid");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}

	public Boolean checkUserName(String name) {
		String sql = "select * from useraccount where username = ?";

		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		try (Connection conn = DriverManager.getConnection(DBsetting.URL, DBsetting.USER, DBsetting.PASSWORD);
				PreparedStatement statement = conn.prepareStatement(sql);) {
			statement.setString(1, name);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
