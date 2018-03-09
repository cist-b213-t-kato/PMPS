package kagidai.pmps;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserAccountDAO {

	public UserAccountDAO(){
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
	}
	public UserAccount checkLogIn(String userId,String password){
		String sql = "select * from useraccount where userid = ? AND password = ?";

	try(Connection conn = DriverManager.getConnection(DBSetting.url,DBSetting.user,DBSetting.pass);
			PreparedStatement statement = conn.prepareStatement(sql);
			){
			statement.setString(1, userId);
			statement.setString(2, password);
			ResultSet resultSet = statement.executeQuery();

		    UserAccount user = new UserAccount();

			if ( resultSet.next() ) {

				String accountid = resultSet.getString("userid");
				String userName = resultSet.getString("username");
				int grade = resultSet.getInt("grade");
				user.setUserId(accountid);
				user.setUserName(userName);
				user.setGrade(grade);
				return user;
			}
			return null;
	}catch(SQLException e){
		e.printStackTrace();
	}
	return null;
	}

	public UserAccount selectUser(String userId){
		String sql = "select * from useraccount where userid = ?";

		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

	try(Connection conn = DriverManager.getConnection(DBSetting.url,DBSetting.user,DBSetting.pass);
			PreparedStatement statement = conn.prepareStatement(sql);
			){
			statement.setString(1, userId);
			ResultSet resultSet = statement.executeQuery();

		    UserAccount user = new UserAccount();

			if ( resultSet.next() ) {

				String accountid = resultSet.getString("userid");
				String userName = resultSet.getString("username");
				int grade = resultSet.getInt("grade");
				user.setUserId(accountid);
				user.setUserName(userName);
				user.setGrade(grade);
				return user;
			}
			return null;
	}catch(SQLException e){
		e.printStackTrace();
	}
	return null;
	}
}
