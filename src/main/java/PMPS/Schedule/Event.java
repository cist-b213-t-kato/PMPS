package PMPS.Schedule;

import java.io.Serializable;
import java.util.List;

import net.arnx.jsonic.JSON;

/**
 *
 * <p>Scheduleをフルカレンダーで扱えるようにしたクラスです</p>
 * @author uesugimasashi
 *
 */
public class Event implements Serializable{

	private static final long serialVersionUID = -7567829977327707446L;

	private int id;
	private String title;
	private String start;
	private String end;

	public Event(int id,String title,String start,String end){
		this.id=id;
		this.title=title;
		this.start=start;
		this.end=end;
	}
	public Event (){

	}

	/**
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id セットする id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title セットする title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return start
	 */
	public String getStart() {
		return start;
	}

	/**
	 * @param start セットする start
	 */
	public void setStart(String start) {
		this.start = start;
	}

	/**
	 * @return end
	 */
	public String getEnd() {
		return end;
	}

	/**
	 * @param end セットする end
	 */
	public void setEnd(String end) {
		this.end = end;
	}

	/**
	 * @return serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * <p>オブジェクトをJSON形式で出力するメソッドです</p>
	 * @param event
	 * @return String
	 */
	public String EncodeObjToJson(Event event) {
        String jsonStr = JSON.encode(event);
        return jsonStr;
    }

	/**
	 *
	 * <p>リストをJSON形式で出力するメソッドです</p>
	 * @param event
	 * @return String
	 */
	public String EncodeListToJson(List<Event> event) {
        String jsonStr = JSON.encode(event);
        return jsonStr;
    }

	/**
	 * <p>時間の文字列をfullcalendarに対応するように変換して返すメソッドです</p>
	 * @param time
	 * @return String
	 */
	public String encodeToFullcalendarTime(String time) {
        StringBuilder sb = new StringBuilder(time);
        sb.setCharAt(4,'-');
        sb.setCharAt(7,'-');
        sb.setCharAt(10, 'T');
        return sb.toString();
	}
}
