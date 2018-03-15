package PMPS.Schedule;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import PMPS.DBsetting;

public class ScheduleDAO {
	public ScheduleDAO() {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
	}

//	public static void main(String[] args){
//		ScheduleDAO sdao =new ScheduleDAO();
//		List<Schedule> eList=sdao.select("b2150220",2018,3,31);
//
//		for(Schedule e : eList){
//			System.out.println(e.getStartTime());
//		}
//	}

	public List<Schedule> select(String userId, int year, int month, int day) {
		String sql = "select * from schedule where to_date(starttime::text,'YYYY/MM/DD') = ? and userid = ?";
		List<Schedule> sList = new ArrayList<Schedule>();
		String monthstr = String.valueOf(month);
		String daystr = String.valueOf(day);

		String yearstr = String.valueOf(year);
		if (month < 10) {
			monthstr = "0" + monthstr;
		}
		if (day < 10) {
			daystr = "0" + daystr;
		}
		try (Connection conn = DriverManager.getConnection(DBsetting.URL, DBsetting.USER, DBsetting.PASSWORD);
				PreparedStatement statement = conn.prepareStatement(sql);) {
			Timestamp ts = new Timestamp(
					new SimpleDateFormat("yyyy/MM/dd").parse(yearstr + "/" + monthstr + "/" + daystr).getTime());
			statement.setTimestamp(1, ts);
			statement.setString(2, userId);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Schedule s = new Schedule();
				s.setScheduleId(resultSet.getInt("scheduleid"));
				s.setUserId(resultSet.getString("userid"));
				s.setContent(resultSet.getString("content"));
				s.setYear(year);
				s.setMonth(month);
				s.setDay(day);

				Timestamp tmps = resultSet.getTimestamp("starttime");
				s.setStartTime(formatTimestampToString(tmps).substring(11, 16));

				Timestamp tmpe = resultSet.getTimestamp("endtime");
				s.setEndTime(formatTimestampToString(tmpe).substring(11, 16));
				sList.add(s);

			}
			return sList;
		} catch (SQLException | ParseException e) {
			e.printStackTrace();
		}
		return sList;
	}


	public List<Event> selectMonthTask(String userId, int year, int month) {
		String sql = "select * from schedule where to_char(starttime,'YYYY/MM') = ? and userid = ?";
		String monthstr = String.valueOf(month);
		List<Event> eList = new ArrayList<Event>();

		String yearstr = String.valueOf(year);
		if (month < 10) {
			monthstr = "0" + monthstr;
		}

		try (Connection conn = DriverManager.getConnection(DBsetting.URL, DBsetting.USER, DBsetting.PASSWORD);
				PreparedStatement statement = conn.prepareStatement(sql);) {
			statement.setString(1, yearstr+"/"+monthstr);
			statement.setString(2, userId);
			ResultSet resultSet = statement.executeQuery();


			while (resultSet.next()) {
				Timestamp tmps = resultSet.getTimestamp("starttime");
				Timestamp tmpe = resultSet.getTimestamp("endtime");
				eList.add(new Event(resultSet.getInt("scheduleid"),resultSet.getString("content"),
						encodeToFullcalendarTime(formatTimestampToString(tmps)),encodeToFullcalendarTime(formatTimestampToString(tmpe))));

			}

			return eList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return eList;
	}

	private String formatTimestampToString(Timestamp timestamp) {
		return new SimpleDateFormat("yyy/MM/dd/HH:mm:ss").format(timestamp);
	}

	/*
	 * 時間の文字列をfullcalendarに対応するように変換して返す
	 */
	public String encodeToFullcalendarTime(String time) {
        StringBuilder sb = new StringBuilder(time);
        sb.setCharAt(4,'-');
        sb.setCharAt(7,'-');
        sb.setCharAt(10, 'T');
        return sb.toString();
	}

	public int insertSchedule(Schedule scedule) {
		String sql = "INSERT INTO schedule(content,starttime,endtime,userid) "
				+ "VALUES(?,?,?,?)";
		String monthstr = String.valueOf(scedule.getMonth());
		String daystr = String.valueOf(scedule.getDay());

		String yearstr = String.valueOf(scedule.getYear());
		if ( scedule.getMonth() < 10) {
			monthstr = "0" + monthstr;
		}
		if (scedule.getDay() < 10) {
			daystr = "0" + daystr;
		}
		Timestamp tss =null;
		try {
			tss = new Timestamp(
					new SimpleDateFormat("yyyy/MM/dd/HH:mm:SS").parse
					(yearstr + "/" +  monthstr+ "/" + daystr+"/"+scedule.getStartTime()).getTime());
		} catch (ParseException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}

		Timestamp tse =null;
		try {
			tse = new Timestamp(
					new SimpleDateFormat("yyyy/MM/dd/HH:mm:SS").parse
					(yearstr + "/" +  monthstr+ "/" + daystr+"/"+scedule.getEndTime()).getTime());
		} catch (ParseException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}

		int returning = 0;
		try (Connection conn = DriverManager.getConnection(DBsetting.URL, DBsetting.USER, DBsetting.PASSWORD);
				PreparedStatement statement = conn.prepareStatement(sql);) {


			statement.setString(1, scedule.getContent());
			statement.setTimestamp(2, tss);
			statement.setTimestamp(3, tse);
			statement.setString(4, scedule.getUserId());
			statement.executeUpdate();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return returning;
	}
}