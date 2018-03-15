package PMPS.Schedule;


import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.apache.wicket.Session;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.form.DropDownChoice;
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

		List<String> hourList = Arrays.asList(new String[] {"0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23"});
		List<String> minutesList = Arrays.asList(new String[]{"0", "15", "30", "45"});

		DropDownChoice<String> starthourchoice = new DropDownChoice<String>("starthourchoice", new Model<String>(),hourList);
		DropDownChoice<String> startminuteschoice = new DropDownChoice<String>("startminuteschoice", new Model<String>(), minutesList);

		DropDownChoice<String> endhourchoice = new DropDownChoice<String>("endhourchoice", new Model<String>(),hourList);
        DropDownChoice<String> endminuteschoice = new DropDownChoice<String>("endminuteschoice", new Model<String>(), minutesList);

		AjaxButton submitButton = new AjaxButton("submitButton"){

			private static final long serialVersionUID = 6377904879632431801L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				super.onSubmit(target, form);
				Schedule s = new Schedule();
				s.setContent(contentModel.getObject());
				s.setYear(selectedDate.getYear());
				s.setMonth(selectedDate.getMonthValue());
				s.setDay(selectedDate.getDayOfMonth());
				s.setStartTime(starthourchoice.getModel().getObject()+":"+startminuteschoice.getModel().getObject()+":00");
				s.setEndTime(endhourchoice.getModel().getObject()+":"+endminuteschoice.getModel().getObject()+":00");
				Session session = getSession();
				UserAccount u = (UserAccount)session.getAttribute("user");
				s.setUserId(u.getUserId());
				ScheduleDAO sdao = new ScheduleDAO();
				sdao.insertSchedule(s);
				ModalWindow.closeCurrent(target);
			}
		};


		form.add(starthourchoice);
		form.add(startminuteschoice);
		form.add(endhourchoice);
		form.add(endminuteschoice);

		form.add(contentTextField);
		form.add(submitButton);
		this.add(form);
		this.add(feedback);
	}
}


