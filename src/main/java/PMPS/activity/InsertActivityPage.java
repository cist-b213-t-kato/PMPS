package PMPS.activity;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import PMPS.LogIn.SignOutPage;
import PMPS.Top.ONPage;

public class InsertActivityPage extends WebPage {

	/**
	*
	*/
	private static final long serialVersionUID = -1992205448531748600L;
	private IModel<String> projectNameModel;
	private IModel<String> leaderModel;
	private IModel<String> termModel;
	private IModel<String> outlineModel;
	private IModel<String> urlModel;
	private IModel<String> gradeModel;

	/**
	 * コンストラクタ.
	 */
	public InsertActivityPage() {
		projectNameModel = Model.of("");
		leaderModel = Model.of("");
		termModel = Model.of("");
		outlineModel = Model.of("");
		urlModel = Model.of("");
		gradeModel = Model.of("");

		// Formタグ用の Form コンポーネント
		Form<Void> form = new Form<Void>("form") {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit() {
				// submit ボタンがクリックされた時の処理
				super.onSubmit();
				System.out.println("projectName : " + projectNameModel.getObject());
				System.out.println("leaderModel  : " + leaderModel.getObject());
				System.out.println("termModel  : " + termModel.getObject());
				System.out.println("outlineModel  : " + outlineModel.getObject());
				System.out.println("urlModel  : " + urlModel.getObject());
				System.out.println("gradeModel  : " + gradeModel.getObject());
				//	ActivityDAO dao = new ActivityDAO();
				ActivityBean bean = new ActivityBean();
				bean.setProjectname(projectNameModel.getObject());
				bean.setLeader(leaderModel.getObject());
				bean.setTerm(termModel.getObject());
				bean.setOutline(outlineModel.getObject());
				bean.setLink(urlModel.getObject());
				bean.setGrade(Integer.parseInt(gradeModel.getObject()));
				ActivityDAO.insert(bean);
				setResponsePage(new ActivityDetailsPage());
			}
		};
		add(form);

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

		// name を入力する input type="text" 用のコンポーネント
		TextField<String> projectNameField = new TextField<>("projectName", projectNameModel);
		TextField<String> leaderField = new TextField<>("leader", leaderModel);
		TextField<String> termField = new TextField<>("term", termModel);
		TextField<String> outlineField = new TextField<>("outline", outlineModel);
		TextField<String> urlField = new TextField<>("url", urlModel);
		TextField<String> gradeField = new TextField<>("grade", gradeModel);

		form.add(projectNameField);
		form.add(leaderField);
		form.add(termField);
		form.add(outlineField);
		form.add(urlField);
		form.add(gradeField);

	}
}
