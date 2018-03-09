package kagidai.pmps.schedule;

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

import kagidai.pmps.DBSetting;

public class ScheduleDAO {
	public ScheduleDAO(){
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
	}

	public List<Schedule> select(String userId,int year,int month,int day){
		String sql = "select * from schedule where to_date(starttime::text,'YYYY/MM/DD') = ? and userid = ?";
		List<Schedule> sList = new ArrayList<Schedule>();
		String monthstr=String.valueOf(month);
		String daystr = String.valueOf(day);

		String yearstr = String.valueOf(year);
		if(month<10){
			monthstr="0"+monthstr;
		}
		if(day<10){
			daystr="0"+daystr;
			}
		try(Connection conn = DriverManager.getConnection(DBSetting.url,DBSetting.user,DBSetting.pass);
				PreparedStatement statement = conn.prepareStatement(sql);
				){
				Timestamp ts = new Timestamp(new SimpleDateFormat("yyyy/MM/dd").parse(yearstr+"/"+monthstr+"/"+daystr).getTime());
				statement.setTimestamp(1,ts);
				statement.setString(2,userId);
				ResultSet resultSet = statement.executeQuery();

			    while(resultSet.next()){
			    	Schedule s = new Schedule();
			    	s.setScheduleId(resultSet.getInt("scheduleid"));
			    	s.setUserId(resultSet.getString("userid"));
			    	s.setContent(resultSet.getString("content"));
			    	s.setYear(year);
			    	s.setMonth(month);
			    	s.setDay(day);

			    	Timestamp tmps = resultSet.getTimestamp("starttime");
			    	s.setStartTime(formatTimestampToString(tmps).substring(11,16));

			    	Timestamp tmpe = resultSet.getTimestamp("endtime");
			    	s.setEndTime(formatTimestampToString(tmpe).substring(11,16));
			    	sList.add(s);

			    }
				return sList;
		}catch(SQLException | ParseException e){
			e.printStackTrace();
		}
		return sList;
}
	private String formatTimestampToString(Timestamp timestamp) {
        return new SimpleDateFormat("yyy/MM/dd/HH:mm:ss").format(timestamp);
    }
}