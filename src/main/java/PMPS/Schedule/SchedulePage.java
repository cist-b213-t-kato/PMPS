package PMPS.Schedule;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.WebPage;

public class SchedulePage extends WebPage{

	private static final long serialVersionUID = -3209326947790638454L;

	/**
	 * コンストラクタ
	 */
	public SchedulePage () {

		this.add(new FullcalendarPanel("fullcalendarPanel"));


    }

	/**
	 * fullcalendarを読み込むために必要なリソースをヘッダーにrenderする
	 */
	@Override
	public void renderHead(IHeaderResponse response) {
		super.renderHead(response);

		response.render(CssHeaderItem.forUrl("fullcalendar/css/fullcalendar.min.css"));
		response.render(CssHeaderItem.forUrl("fullcalendar/css/calStyle.css"));
		response.render(JavaScriptHeaderItem.forUrl("fullcalendar/lib/moment.min.js"));
		response.render(JavaScriptHeaderItem.forUrl("fullcalendar/js/fullcalendar.min.js"));
		response.render(JavaScriptHeaderItem.forUrl("fullcalendar/js/ja.js"));
		response.render(JavaScriptHeaderItem.forUrl("fullcalendar/js/fullcalendarMethod.js"));
	}
}