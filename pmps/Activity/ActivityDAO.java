package kagidai.pmps.Activity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import kagidai.pmps.DBSetting;

public class ActivityDAO {

	public ActivityDAO() {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
	}

//	public static void main(String[] args){
//		ActivityBean actbean= new ActivityBean();
//		actbean.setProjectname("やだチーム");
//		actbean.setLeader("やだ");
//		actbean.setTerm("秋学期");
//		actbean.setOutline("なんか頑張る");
//		actbean.setLink("http://yahoo.co.jp");
//		actbean.setGrade(2);
//		insert(actbean);
//	}

	public static void insert(ActivityBean acb){
		String sql = "insert into activity(projectname,leader,term,outline,link,grade,year) values(?,?,?,?,?,?,?)";
		Calendar calendar = Calendar.getInstance();
	    int year = calendar.get(Calendar.YEAR);
		try(Connection conn = DriverManager.getConnection(DBSetting.url,DBSetting.user,DBSetting.pass);
				PreparedStatement statement = conn.prepareStatement(sql);
				){
				statement.setString(1, acb.getProjectname());
				statement.setString(2, acb.getLeader());
				statement.setString(3, acb.getTerm());
				statement.setString(4, acb.getOutline());
				statement.setString(5, acb.getLink());
				statement.setInt(6, year);
				statement.setInt(7, acb.getGrade());
				statement.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	public static List<ActivityBean> select(int year) {

		String sql = "SELECT * FROM activity " +
						"where year = ?";

		List<ActivityBean> list = new ArrayList<>();

		try (Connection conn = DriverManager.getConnection(DBSetting.url, DBSetting.user, DBSetting.pass);
				PreparedStatement statement = conn.prepareStatement(sql);
				){
				statement.setInt(1, year);

				ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {

				ActivityBean bean = new ActivityBean();

				int projectid = resultSet.getInt("projectid");
				String projectname = resultSet.getString("projectname");
				String leader = resultSet.getString("leader");
				String term = resultSet.getString("term");
				String outline = resultSet.getString("outline");
				String link = resultSet.getString("link");
				int grade = resultSet.getInt("grade");

				bean.setProjectid(projectid);
				bean.setProjectname(projectname);
				bean.setLeader(leader);
				bean.setTerm(term);
				bean.setOutline(outline);
				bean.setLink(link);
				bean.setGrade(grade);

				list.add(bean);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	public static List<Integer> selectYear() {
		String sql = "SELECT year FROM activity GROUP BY year ORDER BY year desc";

		List<Integer> list = new ArrayList<>();

		try (Connection conn = DriverManager.getConnection(DBSetting.url, DBSetting.user, DBSetting.pass);
				Statement statement = conn.createStatement();
				ResultSet resultSet = statement.executeQuery(sql);) {

			while (resultSet.next()) {
				list.add(resultSet.getInt("year"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;

	}
}
