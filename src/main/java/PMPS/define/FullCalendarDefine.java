package PMPS.define;

/**
 *
 * <p>フルカレンダーを使用するためにheaderとview、メソッド、コールバックの処理を書いたクラスです</p>
 * @author uesugimasashi
 *
 */
public class FullCalendarDefine {

	public static final String ID_ATTRIBUTE = "#"; // ID属性
	public static final String FULLCALENDAR_NAME = "fullcalendar"; // フルカレンダーにあてる名前

	/**
	 * headerの定義
	 */
	public static enum HEADER {

		TITLE("title"), PREV("prev"), NEXT("next"), TODAY("today");

		private String value;

		/**
		 * コンストラクタ
		 *
		 * @param {@link
		 * 			String} value
		 */
		private HEADER(String value) {
			this.value = value;
		}

		/**
		 * value getter
		 *
		 * @return {@link String} value
		 */
		public String getValue() {
			return value;
		}

		/**
		 * value setter
		 *
		 * @param {@link
		 * 			String} value
		 */
		public void setValue(String value) {
			this.value = value;
		}
	}

	/**
	 * viewの定義
	 */
	public static enum VIEW {

		MONTH("month"), BASICWEEK("basicWeek"), BASICDAY("basicDay"), AGENDAWEEK("agendaWeek"), AGENDADAY(
				"agendaDay"), LISTYEAR("listYear"), LISTMONTH("listMonth"), LISTWEEK("listWeek"), LISTDAY("listDay");

		private String viewName;

		/**
		 * コンストラクタ
		 *
		 * @param {@link
		 * 			String} viewName
		 */
		private VIEW(String viewName) {
			this.viewName = viewName;
		}

		/**
		 * viewName getter
		 *
		 * @return {@link String} viewName
		 */
		public String getViewName() {
			return viewName;
		}

		/**
		 * viewName setter
		 *
		 * @param {@link
		 * 			String} viewName
		 */
		public void setViewName(String viewName) {
			this.viewName = viewName;
		}
	}

	/**
	 * メソッドの定義
	 */
	public static enum METHOD {

		CHANGEVIEW("changeView"), RENDEREVENT("renderEvent");

		private String methodName;

		/**
		 * コンストラクタ
		 *
		 * @param {@link
		 * 			String} methodName
		 */
		private METHOD(String methodName) {
			this.methodName = methodName;
		}

		/**
		 * methodName getter
		 *
		 * @return {@link String} methodName
		 */
		public String getMethodName() {
			return methodName;
		}

		/**
		 * methodName setter
		 *
		 * @param {@link
		 * 			String} methodName
		 */
		public void setMethodName(String methodName) {
			this.methodName = methodName;
		}
	}

	/**
	 * callbackの定義
	 */
	public static enum CALLBACK {
		SELECT("select"), EVENTCLICK("eventClick"), EVENTDROP("eventDrop"), EVENTRESIZE("eventResize");

		private String callbackName;

		/**
		 * コンストラクタ
		 *
		 * @param {@link
		 * 			String} callbackName
		 */
		private CALLBACK(String callbackName) {
			this.callbackName = callbackName;
		}

		/**
		 * callbackName getter
		 *
		 * @return {@link String} callbackName
		 */
		public String getCallbackName() {
			return callbackName;
		}

		/**
		 * callbackName setter
		 *
		 * @param {@link
		 * 			String} callbackName
		 */
		public void setCallbackName(String callbackName) {
			this.callbackName = callbackName;
		}

	}

	/**
	 * FullcalendarPanelのidをenumとして定義する
	 */
	public static enum FULLCALENDAR_PANEL_ID {
		PLAN("planPanel"), ACHIEVEMENT("achievementPanel");

		private String value;

		/**
		 * コンストラクタ
		 *
		 * @param value
		 *            FullcalendarPanelのid
		 */
		private FULLCALENDAR_PANEL_ID(String value) {
			this.value = value;
		}

		/**
		 * FullcalendarPanelのidを取得するgetter
		 *
		 * @return value FullcalendarPanelのid
		 */
		public String getValue() {
			return value;
		}
	}

}
