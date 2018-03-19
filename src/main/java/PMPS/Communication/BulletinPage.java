package PMPS.Communication;

import org.apache.wicket.Session;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;

import PMPS.UserAccount;
import PMPS.UserAccountDAO;
import PMPS.LogIn.SignOutPage;
import PMPS.Schedule.SchedulePage;
import PMPS.Top.ONPage;
import PMPS.activity.YearListPage;

public class BulletinPage extends WebPage {

	/**
	 *
	 */
	private static final long serialVersionUID = -7311243684350033803L;

	/**
	 *
	 */
	public BulletinPage(int bulletinId, Boolean boo) {
		BulletinDAO bdao = new BulletinDAO();
		Bulletin b = bdao.select(bulletinId);
		UserPostDAO pdao = new UserPostDAO();

		Session session = getSession();
		UserAccount u = (UserAccount) session.getAttribute("user");

		if (u != null) {
			UserAccountDAO udao = new UserAccountDAO();
			UserAccount ud = (UserAccount) udao.selectUser(b.getUserId());
			Label senderNameTextLabel = new Label("senderName", ud.getUserName());
			Label mainTextLabel = new Label("mainText", b.getMainText());
			add(mainTextLabel);
			add(senderNameTextLabel);
			pdao.updateRead(true, bulletinId, u.getUserId());
		}

		Link<Void> NotReadLink = new Link<Void>("NotReadLink") {
			/**
			 *
			 */
			private static final long serialVersionUID = -1098459838625198314L;

			/**
			 *
			 */

			@Override
			public void onClick() {
				pdao.updateRead(false, bulletinId, u.getUserId());
				if (boo) {
					setResponsePage(new CommunicationReadPage());
				} else {
					setResponsePage(new CommunicationNotReadPage());
				}
			}
		};
		add(NotReadLink);
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
