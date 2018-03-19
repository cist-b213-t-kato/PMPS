package kagidai.pmps.LogIn;

import org.apache.wicket.Session;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.component.IRequestablePage;

import kagidai.pmps.UserAccount;
import kagidai.pmps.UserAccountDAO;
import kagidai.pmps.schedule.ONPage;

/**
 * システムを起動したときに最初に表示されるページであり、
 * ログインも行うページ。
 * @author junpei
 *
 */
public class LogInPage extends WebPage {
	  private static final long serialVersionUID = 1L;

	// accountId の値を格納するModel
		private IModel<String> userIdModel;
	// password  の値を格納するModel
		private IModel<String> passwordModel;

		/**
		 * コンストラクタ.
		 */
		public LogInPage() {
			userIdModel = Model.of("");
			passwordModel = Model.of("");

			// Formタグ用の Form コンポーネント
			Form<Void> form = new Form<Void>("form") {
				private static final long serialVersionUID = 1L;

				@Override
				protected void onSubmit() {
					// submit ボタンがクリックされた時の処理
					super.onSubmit();
					System.out.println("userId : " + userIdModel.getObject());
					System.out.println("password  : " + passwordModel.getObject());
					UserAccountDAO dao = new UserAccountDAO();
					UserAccount user = dao.checkLogIn(userIdModel.getObject(), passwordModel.getObject());
					if(user != null){
						Session session = Session.get();
//						session.setAttribute("user",(Serializable) user);
						session.setAttribute("user", user);
						setResponsePage((IRequestablePage) new ONPage());
					}else {
						setResponsePage(new LogInCheckPage());
					}
				}
			};
			add(form);

			// name を入力する input type="text" 用のコンポーネント
			TextField<String> userIdField = new TextField<>("userId", userIdModel);
			TextField<String> passwordField = new TextField<>("password",passwordModel);
			form.add(userIdField);
			form.add(passwordField);
		}
	}