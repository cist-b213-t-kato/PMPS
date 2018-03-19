package PMPS.define;

/**
 * <p>TimestampをLocalDateTimeのフォーマットに合わせるためのクラスです</p>
 * <p>インスタンス生成せず、直接変数を呼んでください</p>
 * @author uesugimasashi
 *
 */
public class DateTimeDefine {

	/**
	 * このクラスのインスタンス生成を許可しない
	 */
	private DateTimeDefine() {
		throw new AssertionError();
	}

	public static final String TIME_FORMAT = "HH:mm"; // 時分
	public static final String DAYOFWEEK_FORMAT = "dddd"; // 曜日
	public static final String DATE_DAYOFWEEK_FORMAT = "M/DD（dd）"; // 日付と曜日
	public static final String DATE_HYPHEN_SEPARATE_FORMAT = "YYYY-MM-DD"; // ハイフン区切りの年月日
	public static final String STRING_TO_DATE_FORMAT = "uuuu/MM/dd";
	public static final String DATE_FORMAT = "yyyy/MM/dd HH:mm:ss"; // 年月日 日付
}
