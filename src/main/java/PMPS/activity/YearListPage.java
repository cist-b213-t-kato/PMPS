package PMPS.activity;

import java.util.List;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.util.ListModel;

/**
 * <p>活動履歴ページの最初、年度一覧を表示します</p>
 * @author boc
 *
 */
import PMPS.Communication.CommunicationNotReadPage;
import PMPS.LogIn.SignOutPage;
import PMPS.Schedule.SchedulePage;
import PMPS.Top.ONPage;

public class YearListPage extends WebPage {


	private static final long serialVersionUID = 1L;

	public YearListPage() {

		ListModel<Integer> listModel = new ListModel<Integer>() {
			@Override
			public List<Integer> getObject() {
				return ActivityDAO.selectYear();
			}
		};

		ListView<Integer> YearListView = new ListView<Integer>("YearList", listModel) {
			@Override
			protected void populateItem(ListItem<Integer> item) {

				Link<Void> toActivityPage = new Link<Void>("toActivityPage") {
					@Override
					public void onClick() {

						setResponsePage(new ActivityPage(item.getModelObject()));
					}
				};
				item.add(toActivityPage);

				toActivityPage.add(new Label("year", item.getModelObject()));

			}
		};
		add(YearListView);

		Link<Void> AllView = new Link<Void>("AllActivityLink") {
			@Override
			public void onClick() {
				setResponsePage(new ActivityPage(0));
			}
		};
		add(AllView);

		Link<Void> InsertActivityLink = new Link<Void>("InsertActivityLink") {


			@Override
			public void onClick() {
				setResponsePage(new InsertActivityPage());
			}
		};
		add(InsertActivityLink);

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
}
