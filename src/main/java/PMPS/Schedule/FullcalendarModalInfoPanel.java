package PMPS.Schedule;


import java.time.LocalDate;

import org.apache.wicket.Session;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import PMPS.UserAccount;
import PMPS.define.FullCalendarDefine.CALLBACK;


public class FullcalendarModalInfoPanel extends Panel {
	private static final long serialVersionUID = 6568469572592001217L;

	/**
	 * コンストラクタ
	 * @param id
	 * @param callback {@link CALLBACK} 処理の種別
	 * @param ecltBean
	 */
	public FullcalendarModalInfoPanel(String id,LocalDate selectedDate) {
		super(id);

		FeedbackPanel feedback = new FeedbackPanel("feedback") {
			private static final long serialVersionUID = 8653715384248157662L;

			@Override
			protected void onInitialize() {
				super.onInitialize();
				setOutputMarkupId(true);
			}
		};

		Form<Void> form = new Form<Void>("form");
		IModel<String> contentModel = new Model<>();
		TextField<String> contentTextField = new TextField<String>("content",contentModel){
			@Override
			protected void onInitialize() {
				super.onInitialize();
				setRequired(true);
			}
		};

		AjaxButton submitButton = new AjaxButton("submitButton"){

			/**
			 *
			 */
			private static final long serialVersionUID = 6377904879632431801L;
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				super.onSubmit(target, form);
				Schedule s = new Schedule();
				s.setContent(contentModel.getObject());
				s.setYear(selectedDate.getYear());
				s.setMonth(selectedDate.getMonthValue());
				s.setDay(selectedDate.getDayOfMonth());
				s.setStartTime("17:30:00");
				s.setEndTime("18:00:00");
				Session session = getSession();
				UserAccount u = (UserAccount)session.getAttribute("user");
				s.setUserId(u.getUserId());
				ScheduleDAO sdao = new ScheduleDAO();
				sdao.insertSchedule(s);
				ModalWindow.closeCurrent(target);
			}
		};
		form.add(contentTextField);
		form.add(submitButton);
		this.add(form);
		this.add(feedback);
	}
}


