package PMPS.activity;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import PMPS.DBsetting;

/**
 * <p>activityテーブル、projectdetailテーブル、commentテーブルにアクセスしデータのやり取りをするクラス</p>
 *
 * @author boc
 *
 */
public class ActivityDAO implements Serializable{

	public ActivityDAO() {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
	}

	//年度を引数に活動履歴を検索（一覧を表で表示するときに使用）
	/**
	 *
	 * <p>activityテーブルから引数に渡した年度にあてはまる活動履歴をセレクトします。</p>
	 * @param year
	 * @return List&lt;ActivityBean>
	 */
	public static List<ActivityBean> select(int year) {

		String sql = "SELECT * FROM activity " +
				"where year = ?";

		List<ActivityBean> list = new ArrayList<>();

		try (Connection conn = DriverManager.getConnection(DBsetting.URL, DBsetting.USER, DBsetting.PASSWORD);
				PreparedStatement statement = conn.prepareStatement(sql);) {
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

	//活動履歴をすべて表示
	/**
	 * activityテーブル全ての活動履歴をセレクトします。
	 * @return List&lt;ActivityBean>
	 */
	public static List<ActivityBean> selectAll() {

		String sql = "SELECT * FROM activity ORDER BY year desc";

		List<ActivityBean> list = new ArrayList<>();

		try (Connection conn = DriverManager.getConnection(DBsetting.URL, DBsetting.USER, DBsetting.PASSWORD);
				Statement statement = conn.createStatement();
				ResultSet resultSet = statement.executeQuery(sql);) {

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

	//年度をリストに入れて表示
	/**
	 * <p>activityテーブルから年度のみをセレクトしたものをList&ltInteger>で返します。<p>
	 * @return List&ltInteger>
	 */
	public static List<Integer> selectYear() {
		String sql = "SELECT year FROM activity GROUP BY year ORDER BY year desc";

		List<Integer> list = new ArrayList<>();

		try (Connection conn = DriverManager.getConnection(DBsetting.URL, DBsetting.USER, DBsetting.PASSWORD);
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

	//活動の詳細を表示するときに使用(activityテーブルと結合している)
	/**
	 * <p>プロジェクトIDを引数にprojectdetailテーブルとactivityテーブルを結合しセレクトしたものを
	 * List&ltDetailBean>で返します。</p>
	 * @param ID
	 * @return List&ltDetailBean>
	 */
	public static List<DetailBean> selectProjectDetail(int ID) {
		String sql = "SELECT * FROM projectdetail "
				+ "INNER JOIN activity "
				+ "ON projectdetail.projectid = activity.projectid "
				+ "WHERE projectdetail.projectid = ?;";

		List<DetailBean> list = new ArrayList<>();

		try (Connection conn = DriverManager.getConnection(DBsetting.URL, DBsetting.USER, DBsetting.PASSWORD);
				PreparedStatement statement = conn.prepareStatement(sql);) {

			statement.setInt(1, ID);

			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				DetailBean bean = new DetailBean();

				int projectid = resultSet.getInt("projectid");
				String outline = resultSet.getString("outline");
				String achievement = resultSet.getString("achievement");
				String impression = resultSet.getString("impression");
				String projectname = resultSet.getString("projectname");
				String leader = resultSet.getString("leader");

				bean.setProjectid(projectid);
				bean.setOutline(outline);
				bean.setAchievement(achievement);
				bean.setImpression(impression);
				bean.setProjectname(projectname);
				bean.setLeader(leader);

				list.add(bean);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	//活動リストの追加
	/**
	 * <p>ActivityBeanを引数にactivityテーブルにデータをインサートします。</p>
	 * @param acb
	 */
	public static void insert(ActivityBean acb ) {
		String sql = "insert into activity(projectname,leader,term,outline,link,grade,year) values(?,?,?,?,?,?,?)";

		Calendar calendar = Calendar.getInstance();

		int year = calendar.get(Calendar.YEAR);
		//年度表示のため1月、2月、3月のときマイナス１をする。（0が1月、1が2月、2が3月）
		if(calendar.get(Calendar.MONTH) == 0 ||calendar.get(Calendar.MONTH) == 1 || calendar.get(Calendar.MONTH) == 2) {
			year = year - 1;
		}

		try (Connection conn = DriverManager.getConnection(DBsetting.URL, DBsetting.USER, DBsetting.PASSWORD);
				PreparedStatement statement = conn.prepareStatement(sql);) {
			statement.setString(1, acb.getProjectname());
			statement.setString(2, acb.getLeader());
			statement.setString(3, acb.getTerm());
			statement.setString(4, acb.getOutline());
			statement.setString(5, acb.getLink());
			statement.setInt(6, acb.getGrade());
			statement.setInt(7, year);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//活動の詳細を追加する
	/**
	 * <p>DetailBeanを引数にprojectdetailテーブルにデータをインサートします。</p>
	 * @param details
	 * @return int
	 */
	public int insertDetails(DetailBean details) {
		String sql = "INSERT INTO projectdetail(outline,achievement,impression) values(?,?,?)";

		int returning = 0;
		try (Connection conn = DriverManager.getConnection(DBsetting.URL, DBsetting.USER, DBsetting.PASSWORD);
				PreparedStatement statement = conn.prepareStatement(sql);) {

			statement.setString(1, details.getOutline());
			statement.setString(2, details.getAchievement());
			statement.setString(3, details.getImpression());
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return returning;
	}

	//詳細ページに書かれてるコメントをもってくる
	/**
	 * <p>プロジェクトIDを引数にcommentテーブルからセレクトしたものをList&ltCommentBean>で返します。</p>
	 * @param ID
	 * @return List&ltCommentBean>
	 */
	public static List<CommentBean> selectComment(int ID) {
		String sql = "SELECT * FROM comment "
				+ "WHERE projectid = ? ";

		List<CommentBean> list = new ArrayList<>();

		try (Connection conn = DriverManager.getConnection(DBsetting.URL, DBsetting.USER, DBsetting.PASSWORD);
				PreparedStatement statement = conn.prepareStatement(sql);) {

			statement.setInt(1, ID);

			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				CommentBean bean = new CommentBean();

				String commenttext = resultSet.getString("commenttext");
				String username = resultSet.getString("username");
				Date date = resultSet.getDate("date");

				bean.setUsername(username);
				bean.setCommenttext(commenttext);
				bean.setDate(date);

				list.add(bean);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	//活動コメントを追加する
	/**
	 * <p>commentテーブルにprojectid、comment、usernameをインサートします。</p>
	 * @param projectid
	 * @param comment
	 * @param username
	 * @return int
	 */
	public static int insertComment(int projectid, String comment, String username) {
		String sql = "INSERT INTO comment values(?,?,?,?)";

		int result = 0;

		Date date = new Date(Calendar.getInstance().getTimeInMillis());

		try (Connection conn = DriverManager.getConnection(DBsetting.URL, DBsetting.USER, DBsetting.PASSWORD);
				PreparedStatement statement = conn.prepareStatement(sql);) {
			statement.setInt(1, projectid);
			statement.setString(2, comment);
			statement.setString(3, username);
			statement.setDate(4, date);

			result = statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;

	}

}
