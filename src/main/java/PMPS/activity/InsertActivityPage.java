package PMPS.activity;

import java.util.Arrays;
import java.util.List;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * <p>活動履歴を追加するページ</p>
 * @author boc
 *
 */
import PMPS.Communication.CommunicationNotReadPage;
import PMPS.LogIn.SignOutPage;
import PMPS.Schedule.SchedulePage;
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
	private IModel<Integer> gradeModel;

	/**
	 * コンストラクタ.
	 */
	public InsertActivityPage() {
		projectNameModel = Model.of("");
		leaderModel = Model.of("");
		termModel = Model.of("");
		outlineModel = Model.of("");
		urlModel = Model.of("");
		gradeModel = Model.of();

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
				bean.setGrade(gradeModel.getObject());
//				Session activitySession = Session.get();
//				activitySession.setAttribute("activitySession", bean);
				ActivityDAO.insert(bean);
				setResponsePage(new ActivityDetailsPage());
			}

		};
		add(form);

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

		FeedbackPanel feedbackpanel = new FeedbackPanel("feedback");
		add(feedbackpanel);//空白があった場合警告がでてくるパネル

		// name を入力する input type="text" 用のコンポーネント
		TextField<String> projectNameField = new TextField<>("projectName", projectNameModel);
		projectNameField.setLabel(Model.of("プロジェクト名"));
		projectNameField.setRequired(true);//空白を禁止する

		TextField<String> leaderField = new TextField<>("leader", leaderModel);
		leaderField.setLabel(Model.of("担当者"));
		leaderField.setRequired(true);

		List<String> termList = Arrays.asList(new String[]{"春学期", "秋学期"});
		DropDownChoice<String> termChoice = new DropDownChoice<String>("term", termModel,termList);
//		TextField<String> termField = new TextField<>("term", termModel);
		termChoice.setLabel(Model.of("期間"));
		termChoice.setRequired(true);

		TextField<String> outlineField = new TextField<>("outline", outlineModel);
		outlineField.setLabel(Model.of("概要"));
		outlineField.setRequired(true);

		TextField<String> urlField = new TextField<>("url", urlModel);
		urlField.setLabel(Model.of("GoogleDriveへのURL"));
		urlField.setRequired(true);

		List<Integer> gradeList = Arrays.asList(new Integer[]{2, 3});
		DropDownChoice<Integer> gradeChoice = new DropDownChoice<Integer>("grade",gradeModel,gradeList);
//		TextField<String> gradeField = new TextField<>("grade", gradeModel);
		gradeChoice.setLabel(Model.of("学年"));
		gradeChoice.setRequired(true);

		form.add(projectNameField);
		form.add(leaderField);
		form.add(termChoice);
		form.add(outlineField);
		form.add(urlField);
		form.add(gradeChoice);

	}
}
