package PMPS.LogIn;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;

public class SignOutPage extends WebPage{
	/**
	 *
	 */
	private static final long serialVersionUID = 3238440560638680904L;

	public SignOutPage() {
		Link<Void> LogInLink = new Link<Void>("LogInLink") {
			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				getSession().invalidate();
				setResponsePage(new LogInPage());
			}
		};
		add(LogInLink);
	}

}
