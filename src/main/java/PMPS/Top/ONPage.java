package PMPS.Top;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Session;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.util.ListModel;

import PMPS.UserAccount;
import PMPS.Communication.CommunicationNotReadPage;
import PMPS.LogIn.LogInPage;
import PMPS.LogIn.SignOutPage;
import PMPS.Schedule.Schedule;
import PMPS.Schedule.ScheduleDAO;
import PMPS.Schedule.SchedulePage;
import PMPS.activity.YearListPage;

/**
 *
 * <p>PMPSのトップページを表示するクラスです</p>
 * @author uesugimasashi
 *
 */
public class ONPage extends WebPage {
	private static final long serialVersionUID = 1L;

	public ONPage() {

		Session session = getSession();
		UserAccount user = (UserAccount) session.getAttribute("user");

		if(user!=null){

		ListModel<STR> ScheduleModel = new ListModel<STR>() {
			/**
			 *
			 */
			private static final long serialVersionUID = -1190725715427855501L;

			@Override
			public List<STR> getObject() {
				ScheduleDAO sdao = new ScheduleDAO();
				List<STR> sList = new ArrayList<STR>();
				List<Schedule> todayList = new ArrayList<>();
				List<Schedule> tommolowList = new ArrayList<>();
				List<Schedule> tttList = new ArrayList<>();

				LocalDateTime ldt = LocalDateTime.now();
				LocalDateTime d2 = ldt.plusDays(1);
				LocalDateTime d3 = ldt.plusDays(2);
				todayList = sdao.select(user.getUserId(), ldt.getYear(), ldt.getMonthValue(), ldt.getDayOfMonth());
				tommolowList = sdao.select(user.getUserId(), d2.getYear(), d2.getMonthValue(), d2.getDayOfMonth());
				tttList = sdao.select(user.getUserId(), d3.getYear(), d3.getMonthValue(), d3.getDayOfMonth());

				todayList = swap(todayList);
				tommolowList = swap(tommolowList);
				tttList = swap(tttList);

				int tmp = Math.max(todayList.size(), Math.max(tommolowList.size(), tttList.size()));

				for (int i = 0; i < tmp; i++) {
					STR str = new STR();
					if (todayList.size() > i) {
						str.setToday(getRow(todayList.get(i)));
					}
					if (tommolowList.size() > i) {
						str.setTommolow(getRow(tommolowList.get(i)));
					}

					if (tttList.size() > i) {
						str.setTtt(getRow(tttList.get(i)));
					}
					sList.add(str);
				}

				return sList;
			}
		};

		Link<Void> NotReadLink = new Link<Void>("NotReadLink") {
			/**
			 *
			 */
			private static final long serialVersionUID = 8857405506843942073L;

			@Override
			public void onClick() {
				setResponsePage(new CommunicationNotReadPage());
			}
		};
		add(NotReadLink);

		Link<Void> ActivityLink = new Link<Void>("ActivityLink") {
			/**
			 *
			 */
			private static final long serialVersionUID = -5294390084905468090L;

			@Override
			public void onClick() {
				setResponsePage(new YearListPage());
			}
		};
		add(ActivityLink);

		Link<Void> ScheduleLink = new Link<Void>("ScheduleLink") {
			/**
			 *
			 */
			private static final long serialVersionUID = 8857405506843942073L;

			@Override
			public void onClick() {
				setResponsePage(new SchedulePage());
			}
		};
		add(ScheduleLink);

		//		↓ここからメニューバー+ロゴ
		Link<Void> homeLink = new Link<Void>("homeLink") {

			/**
			 *
			 */
			private static final long serialVersionUID = -3756497445338731145L;

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

		ListView<STR> scheduleView = new ListView<STR>("schedule", ScheduleModel) {
			/**
			 *
			 */
			private static final long serialVersionUID = -2960991639228239464L;

			@Override
			protected void populateItem(ListItem<STR> item) {
				item.add(new Label("content", item.getModelObject().getToday()));
				item.add(new Label("content2", item.getModelObject().getTommolow()));
				item.add(new Label("content3", item.getModelObject().getTtt()));
			}
		};
		add(scheduleView);
		}else{
			setResponsePage(new LogInPage());
		}

	}

	/**
	 *
	 * <p>Scheduleを渡すと 開始時間と終了時間、本文を (12:00~13:00)英語 のような文字列に加工してStringで返すメソッドです</p>
	 * @param s
	 * @return String
	 */
	private String getRow(Schedule s) {
		return "(" + s.getStartTime() + "~" + s.getEndTime() + ")" + s.getContent();
	}

	/**
	 *
	 * <p>List&lt;Schedule>を渡すと時系列順にListを変更して返すメソッドです</p>
	 * @param list
	 * @return List&lt;Schedule>
	 */
	private List<Schedule> swap(List<Schedule> list) {
		if (!list.isEmpty() || list.size() == 1) {
			for (int i = 1; i < list.size(); i++) {
				if (Integer.parseInt(list.get(i - 1).getStartTime().substring(0, 2)) > Integer
						.parseInt(list.get(i).getStartTime().substring(0, 2))) {
					Schedule tmp = list.get(i - 1);
					list.set(i - 1, list.get(i));
					list.set(i, tmp);
				}
			}
		}
		return list;

	}

}
