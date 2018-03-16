package PMPS.activity;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;

import PMPS.LogIn.SignOutPage;
import PMPS.Top.ONPage;

public class InsertSuccesPage extends WebPage {
	public InsertSuccesPage() {
		Link<Void> ONPageLink= new Link<Void>("ONPageLink") {
			@Override
			public void onClick() {
				setResponsePage(new ONPage());
			}
		};
		add(ONPageLink);
		Link<Void> ActivitySmallLink = new Link<Void>("ActivitySmallLink") {

			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

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
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(new SignOutPage());
			}
		};
		add(SignOutLink);

		Link<Void> homeLink = new Link<Void>("homeLink") {

			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(new ONPage());
			}
		};
		add(homeLink);
	}
	
}
