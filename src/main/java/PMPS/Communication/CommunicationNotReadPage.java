package PMPS.Communication;

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
import PMPS.LogIn.SignOutPage;
import PMPS.Schedule.SchedulePage;
import PMPS.Top.ONPage;
import PMPS.activity.YearListPage;

public class CommunicationNotReadPage extends WebPage{

	/**
	 *
	 */
	private static final long serialVersionUID = -1670068310642725121L;
	public CommunicationNotReadPage() {

		ListModel<Bulletin> NotReadlistModel = new ListModel<Bulletin>() {
			/**
			 *
			 */
			private static final long serialVersionUID = -1190725715427855501L;

			@Override
			public List<Bulletin> getObject() {
				UserPostDAO udao = new UserPostDAO();
				BulletinDAO bdao = new BulletinDAO();
				Session session = getSession();
				UserAccount user = (UserAccount)session.getAttribute("user");
				List<Bulletin> bList = new ArrayList<>();
				List<Integer> idList = udao.selectIsRead(user.getUserId(),false);
				for(int id : idList){
					bList.add(bdao.select(id));
				}
				return bList;
			}
		};

		ListView<Bulletin> NotReadListView = new ListView<Bulletin>("NotRead", NotReadlistModel) {
			/**
			 *
			 */
			private static final long serialVersionUID = -2960991639228239464L;

			@Override
			protected void populateItem(ListItem<Bulletin> item) {

				Bulletin b = item.getModelObject();

				item.add(new Label("bulletinId", b.getBulletinId()));

				Link<Void> BulletinPage = new Link<Void>("BulletinPage") {
					/**
					 *
					 */
					private static final long serialVersionUID = 6930862864012672408L;

					@Override
					public void onClick() {
						//System.out.println(item.getModelObject());
						setResponsePage(new BulletinPage(item.getModelObject().getBulletinId(),false));
					}
				};
				item.add(BulletinPage);

				BulletinPage.add(new Label("subject", item.getModelObject().getSubject()));
			}
		};
		add(NotReadListView);




		Link<Void> ReadPageLink = new Link<Void>("ReadPageLink") {
			/**
			 *
			 */
			private static final long serialVersionUID = 7123042162371774643L;

			@Override
			public void onClick() {
				setResponsePage(new CommunicationReadPage());
			}
		};
		add(ReadPageLink);

		Link<Void> InsertBulletinPageLink = new Link<Void>("InsertBulletinPageLink") {
			/**
			 *
			 */
			private static final long serialVersionUID = 7123042162371774643L;

			@Override
			public void onClick() {
				setResponsePage(new InsertBulletinPage());
			}
		};
		add(InsertBulletinPageLink);
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
