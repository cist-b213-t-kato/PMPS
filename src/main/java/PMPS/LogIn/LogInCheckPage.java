package PMPS.LogIn;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;



/**
 * ログインに失敗すると表示されるページです
 * @author junpei
 *
 */
public class LogInCheckPage extends WebPage {
	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public LogInCheckPage() {
		Link<Void> homeLink = new Link<Void>("homeLink") {
			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {


				setResponsePage(new LogInPage());
			}
		};
		add(homeLink);
	}
}
