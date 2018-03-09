package PMPS.Schedule;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public class InsertSchedulePage extends WebPage {

	IModel<String> startTimeModel;
	IModel<String> endTimeModel;
	IModel<String> contentModel;

	public InsertSchedulePage() {
		startTimeModel = Model.of("");
		endTimeModel = Model.of("");
		contentModel = Model.of("");

		// Formタグ用の Form コンポーネント
		Form<Void> form = new Form<Void>("form") {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit() {
				// submit ボタンがクリックされた時の処理
				super.onSubmit();
//				ScheduleDAO dao = new ScheduleDAO();
				Schedule bean = new Schedule();
				bean.setStartTime(startTimeModel.getObject());
				bean.setEndTime(endTimeModel.getObject());
				bean.setContent(contentModel.getObject());
				//				bean.setProjectname(projectNameModel.getObject());
				//				bean.setLeader(leaderModel.getObject());
				//				bean.setTerm(termModel.getObject());
				//				bean.setOutline(outlineModel.getObject());
				//				bean.setLink(urlModel.getObject());
				//				bean.setGrade(Integer.parseInt(gradeModel.getObject()));
				//				ActivityDAO.insert(bean);
				//				setResponsePage( new ActivityDetailsPage());
			}
		};
		add(form);


		// name を入力する input type="text" 用のコンポーネント
		TextField<String> startTime = new TextField<>("startTime", startTimeModel);
		TextField<String> endTime = new TextField<>("endTime", endTimeModel);
		TextField<String> content = new TextField<>("content", contentModel);

		form.add(startTime);
		form.add(endTime);
		form.add(content);

	}

}
