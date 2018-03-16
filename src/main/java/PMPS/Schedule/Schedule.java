package PMPS.Schedule;

/**
 *
 * <p>予定の情報が詰まったクラスです</p>
 * @author uesugimasashi
 *
 */
public class Schedule {
	private int scheduleId;
	private int year;
	private int month;
	private int day;
	private String startTime;
	private String endTime;
	private String userId;
	private String content;
	/**
	 * @return scheduleId
	 */
	public int getScheduleId() {
		return scheduleId;
	}
	/**
	 * @param scheduleId セットする scheduleId
	 */
	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
	}
	/**
	 * @return year
	 */
	public int getYear() {
		return year;
	}
	/**
	 * @param year セットする year
	 */
	public void setYear(int year) {
		this.year = year;
	}
	/**
	 * @return month
	 */
	public int getMonth() {
		return month;
	}
	/**
	 * @param month セットする month
	 */
	public void setMonth(int month) {
		this.month = month;
	}
	/**
	 * @return day
	 */
	public int getDay() {
		return day;
	}
	/**
	 * @param day セットする day
	 */
	public void setDay(int day) {
		this.day = day;
	}
	/**
	 * @return startTime
	 */
	public String getStartTime() {
		return startTime;
	}
	/**
	 * @param startTime セットする startTime
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	/**
	 * @return endTime
	 */
	public String getEndTime() {
		return endTime;
	}
	/**
	 * @param endTime セットする endTime
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	/**
	 * @return userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId セットする userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * @return content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content セットする content
	 */
	public void setContent(String content) {
		this.content = content;
	}






}
