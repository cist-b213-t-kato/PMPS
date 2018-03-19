package kagidai.pmps.LogIn;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;

/**
 * ログアウトしたときに表示されるページです
 * @author junpei
 *
 */
public class SignOutPage extends WebPage{
	/**
	 *
	 */
	private static final long serialVersionUID = 3238440560638680904L;

	public SignOutPage() {
		Link<Void> homeLink = new Link<Void>("homeLink") {
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
		add(homeLink);
	}

}
