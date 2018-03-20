package PMPS.activity;

import java.util.List;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.util.ListModel;

import PMPS.Communication.CommunicationNotReadPage;
import PMPS.LogIn.SignOutPage;
import PMPS.Schedule.SchedulePage;
import PMPS.Top.ONPage;

/**
 * <p>活動履歴一覧を表示するページ</p>
 * @author boc
 *
 */

public class ActivityPage extends WebPage {
	private static final long serialVersionUID = 1L;

	public ActivityPage(int year) {

		ListModel<ActivityBean> listModel = new ListModel<ActivityBean>() {
			@Override
			public List<ActivityBean> getObject() {
				if (year == 0) {
					return ActivityDAO.selectAll();
				} else {
					return ActivityDAO.select(year);
				}
			}
		};


		ListView<ActivityBean> activityListView = new ListView<ActivityBean>("activityList", listModel) {
			@Override
			protected void populateItem(ListItem<ActivityBean> item) {

				Link<Void> toDetailPage = new Link<Void>("toDetailPage") {
					@Override
					public void onClick() {

						setResponsePage(new DetailPage(item.getModelObject().getProjectid()));
					}
				};

				toDetailPage.add(new Label("projectname", item.getModelObject().getProjectname()));
				item.add(toDetailPage);

				item.add(new Label("leader", item.getModelObject().getLeader()));
				item.add(new Label("term", item.getModelObject().getTerm()));
				item.add(new Label("outline", item.getModelObject().getOutline()));

				Label link = new Label("link", "リンク");
				link.add(new AttributeModifier("href", new Model<String>(item.getModelObject().getLink())));
				item.add(link);

				item.add(new Label("grade", item.getModelObject().getGrade()));

			}
		};

		add(activityListView);


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
