package PMPS.activity;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import PMPS.LogIn.SignOutPage;
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

		TextArea<String> outlineField = new TextArea<>("outlineField", outlineModel);
		form.add(outlineField);
		TextArea<String> achievementField = new TextArea<>("achievementField", achievementModel);
		form.add(achievementField);
		TextArea<String> impressionField = new TextArea<>("impressionField", impressionModel);
		form.add(impressionField);
	}
}
