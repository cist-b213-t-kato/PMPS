package PMPS.activity;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * 活動の詳細を入力するページ
 * @author boc
 *
 */
import PMPS.Communication.CommunicationNotReadPage;
import PMPS.LogIn.SignOutPage;
import PMPS.Schedule.SchedulePage;
import PMPS.Top.ONPage;

public class ActivityDetailsPage extends WebPage {
	IModel<String> outlineModel;
	IModel<String> achievementModel;
	IModel<String> impressionModel;

	public ActivityDetailsPage() {

		outlineModel = Model.of("");
		achievementModel = Model.of("");
		impressionModel = Model.of("");

		ActivityDAO dao = new ActivityDAO();

		Form<Void> form = new Form<Void>("form") {

			@Override
			protected void onSubmit() {
				super.onSubmit();
				DetailBean details = new DetailBean();
				details.setOutline(outlineModel.getObject());
				details.setAchievement(achievementModel.getObject());
				details.setImpression(impressionModel.getObject());

				dao.insertDetails(details);

				setResponsePage(new InsertSuccesPage());

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

		TextArea<String> outlineField = new TextArea<>("outlineField", outlineModel);
		form.add(outlineField);
		TextArea<String> achievementField = new TextArea<>("achievementField", achievementModel);
		form.add(achievementField);
		TextArea<String> impressionField = new TextArea<>("impressionField", impressionModel);
		form.add(impressionField);
	}
}
