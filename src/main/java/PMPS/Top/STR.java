package PMPS.Top;

/**
 *
 * <p>今後の活動予定で表示するために作成したクラスです</p>
 * <p>tttはtodayの2日後の日です</p>
 * @author uesugimasashi
 *
 */
public class STR {
	private String today;
	private String tommolow;
	private String ttt;
	/**
	 * @return ttt
	 */
	public String getTtt() {
		return ttt;
	}

	/**
	 * @param ttt セットする ttt
	 */
	public void setTtt(String ttt) {
		this.ttt = ttt;
	}

	public void setToday(String str){
		this.today=str;
	}

	public void setTommolow(String str){
		this.tommolow=str;
	}
	public String getToday(){
		return today;
	}
	public String getTommolow(){
		return tommolow;
	}
}
