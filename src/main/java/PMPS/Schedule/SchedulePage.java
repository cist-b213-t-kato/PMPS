package PMPS.Schedule;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;

import PMPS.Communication.CommunicationNotReadPage;
import PMPS.LogIn.SignOutPage;
import PMPS.Top.ONPage;
import PMPS.activity.YearListPage;

/**
 *
 * <p>SchedulePageを読み込むクラスです</p>
 * @author uesugimasashi
 *
 */
public class SchedulePage extends WebPage{

	private static final long serialVersionUID = -3209326947790638454L;

	/**
	 * コンストラクタ
	 */
	public SchedulePage () {

		this.add(new FullcalendarPanel("fullcalendarPanel"));
//		↓ここからメニューバー+ロゴ
		Link<Void> homeLink = new Link<Void>("homeLink") {

			@Override
			public void onClick() {
				setResponsePage(new ONPage());
			}
		};
		add(homeLink);

		Link<Void> NotReadSmallLink = new Link<Void>("NotReadSmallLink") {
			/**
			 *
			 */
			private static final long serialVersionUID = -5294390084905468090L;

			@Override
			public void onClick() {
				setResponsePage(new CommunicationNotReadPage());
			}
		};

		add(NotReadSmallLink);



		Link<Void> ActivitySmallLink = new Link<Void>("ActivitySmallLink") {
			/**
			 *
			 */
			private static final long serialVersionUID = -5294390084905468090L;

			@Override
			public void onClick() {
				setResponsePage(new YearListPage());
			}
		};

		add(ActivitySmallLink);


		Link<Void> ScheduleSmallLink = new Link<Void>("ScheduleSmallLink") {
			/**
			 *
			 */
			private static final long serialVersionUID = -5294390084905468090L;

			@Override
			public void onClick() {
				setResponsePage(new SchedulePage());
			}
		};

		add(ScheduleSmallLink);

		Link<Void> SignOutLink = new Link<Void>("SignOutLink") {
			/**
			 *
			 */
			private static final long serialVersionUID = -5294390084905468090L;

			@Override
			public void onClick() {
				setResponsePage(new SignOutPage());
			}
		};
		add(SignOutLink);
		//ここまでメニューバー+ロゴ

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