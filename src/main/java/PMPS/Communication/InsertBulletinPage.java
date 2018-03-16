package PMPS.Communication;

import java.util.List;

import org.apache.wicket.Session;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.component.IRequestablePage;

import PMPS.UserAccount;
import PMPS.UserAccountDAO;
import PMPS.LogIn.SignOutPage;
import PMPS.Schedule.SchedulePage;
import PMPS.Top.ONPage;
import PMPS.activity.YearListPage;

public class InsertBulletinPage extends WebPage{

	/**
	 *
	 */
	private static final long serialVersionUID = 2017176516978406916L;

	private IModel<String> subjectModel;
	private IModel<String> mainTextModel;
	private IModel<String> receiverUserIdModel;

	public InsertBulletinPage(){
		subjectModel = Model.of("");
		mainTextModel = Model.of("");
		receiverUserIdModel = Model.of("");

		Form<Void> form = new Form<Void>("form"){
			/**
			 *
			 */
			private static final long serialVersionUID = 3997292296855295076L;

			@Override
			protected void onSubmit(){
				super.onSubmit();
				Bulletin b = new Bulletin();
				Session session = getSession();
				b.setSubject(subjectModel.getObject());
				b.setMainText(mainTextModel.getObject());
				UserAccount u = (UserAccount)session.getAttribute("user");
				b.setUserId(u.getUserId());
				UserPost up = new UserPost();

				BulletinDAO bdao = new BulletinDAO();
				UserAccountDAO uadao = new UserAccountDAO();
				UserPostDAO udao = new UserPostDAO();
				bdao.insert(b);
				up.setBulletinId(bdao.selectBulletinId());
				if(receiverUserIdModel.getObject().equals("2年") || receiverUserIdModel.getObject().equals("２年")){
					List<String> list = uadao.selectGrade(2);
					for(String id : list){
						up.setUserId(id);
						udao.insert(up);
					}
				}else if(receiverUserIdModel.getObject().equals("3年") || receiverUserIdModel.getObject().equals("３年")){
					List<String> list = uadao.selectGrade(3);
					for(String id : list){
						up.setUserId(id);
						udao.insert(up);
					}
				}else{
					up.setUserId(receiverUserIdModel.getObject());
					udao.insert(up);
				}
				setResponsePage((IRequestablePage) new InsertSuccesPage());
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

		TextField<String> subjectField = new TextField<>("subject",subjectModel);
		TextField<String> receiverUserIdField = new TextField<>("receiverUserId",receiverUserIdModel);
		TextArea<String> mainTextField = new TextArea<>("mainText",mainTextModel);
		form.add(subjectField);
		form.add(receiverUserIdField);
		form.add(mainTextField);
	}
}
