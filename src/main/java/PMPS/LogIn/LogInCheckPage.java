package PMPS.LogIn;

import org.apache.wicket.Session;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;

import PMPS.UserAccount;

public class LogInCheckPage  extends WebPage {
		  private static final long serialVersionUID = 1L;


			/**
			 * コンストラクタ.
			 */
			public LogInCheckPage() {
					Session session = getSession();
//					Serializable s = session.getAttribute("user");
//					Object ob = (Object)s;
//					UserAccount user = (UserAccount)ob;
					UserAccount user = (UserAccount)session.getAttribute("user");;
					Label userIdLabel = new Label("userId", user.getUserId());

					add(userIdLabel);
		}
}
