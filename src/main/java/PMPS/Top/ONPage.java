package PMPS.Top;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
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
import PMPS.LogIn.SignOutPage;
import PMPS.Schedule.Schedule;
import PMPS.Schedule.ScheduleDAO;
import PMPS.activity.YearListPage;

public class ONPage extends WebPage {
	private static final long serialVersionUID = 1L;

	public ONPage() {

		ListModel<STR> ScheduleModel = new ListModel<STR>() {
			/**
			 *
			 */
			private static final long serialVersionUID = -1190725715427855501L;

			@Override
			public List<STR> getObject() {
				ScheduleDAO sdao = new ScheduleDAO();
				Session session = getSession();
				UserAccount user = (UserAccount) session.getAttribute("user");
				List<STR> sList = new ArrayList<STR>();
				List<Schedule> todayList = new ArrayList<>();
				List<Schedule> tommolowList = new ArrayList<>();
				List<Schedule> tttList = new ArrayList<>();

				LocalDateTime ldt = LocalDateTime.now();
				todayList = sdao.select(user.getUserId(), ldt.getYear(), ldt.getMonthValue(), ldt.getDayOfMonth());

				Calendar c2day = Calendar.getInstance();
				c2day.set(ldt.getYear(), ldt.getMonthValue(), ldt.getDayOfMonth());
				c2day.add(Calendar.DAY_OF_MONTH, 1);
				tommolowList = sdao.select(user.getUserId(), c2day.get(Calendar.YEAR), c2day.get(Calendar.MONTH),
						c2day.get(Calendar.DAY_OF_MONTH));

				Calendar c3day = Calendar.getInstance();
				c3day.set(ldt.getYear(), ldt.getMonthValue(), ldt.getDayOfMonth());
				c3day.add(Calendar.DAY_OF_MONTH, 2);
				tttList = sdao.select(user.getUserId(), c3day.get(Calendar.YEAR), c3day.get(Calendar.MONTH),
						c3day.get(Calendar.DAY_OF_MONTH));

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

		Link<Void> homeLink = new Link<Void>("homeLink") {

			@Override
			public void onClick() {
				setResponsePage(new ONPage());
			}
		};
		add(homeLink);
	}

	private String getRow(Schedule s) {
		return "(" + s.getStartTime() + "~" + s.getEndTime() + ")" + s.getContent();
	}

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
