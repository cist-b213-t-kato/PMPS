package PMPS.activity;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * 活動の詳細を入力するページ
 * @author boc
 *
 */
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

		TextArea<String> outlineField = new TextArea<>("outlineField", outlineModel);
		form.add(outlineField);
		TextArea<String> achievementField = new TextArea<>("achievementField", achievementModel);
		form.add(achievementField);
		TextArea<String> impressionField = new TextArea<>("impressionField", impressionModel);
		form.add(impressionField);
	}
}
