package kagidai.pmps.schedule;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;

import kagidai.pmps.Activity.YearListPage;
import kagidai.pmps.Communication.CommunicationNotReadPage;
import kagidai.pmps.LogIn.SignOutPage;


public class ONPage extends WebPage{
	private static final long serialVersionUID = 1L;

	public ONPage() {

//		Link<Void> toSignOutPage = new Link<Void>("toSignOutPage") {
//			@Override
//			public void onClick() {
//				setResponsePage(new SignOutPage());
//			}
//
//		};
//		add(toSignOutPage);

		Link<Void> homeLink = new Link<Void>("homeLink") {
			/**
			 *
			 */
			private static final long serialVersionUID = 7123042162371774643L;

			@Override
			public void onClick() {
				setResponsePage(new StartPage());
			}
		};
		add(homeLink);

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
	}
}
