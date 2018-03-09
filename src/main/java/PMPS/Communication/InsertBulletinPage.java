package PMPS.Communication;

import org.apache.wicket.Session;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.component.IRequestablePage;

import PMPS.UserAccount;
import PMPS.Top.ONPage;

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
				up.setUserId(receiverUserIdModel.getObject());

				BulletinDAO bdao = new BulletinDAO();
				bdao.insert(b);
				up.setBulletinId(bdao.selectBulletinId());
				UserPostDAO udao = new UserPostDAO();
				udao.insert(up);
				setResponsePage((IRequestablePage) new ONPage());
			}
		};
		add(form);

		TextField<String> subjectField = new TextField<>("subject",subjectModel);
		TextField<String> mainTextField = new TextField<>("mainText",mainTextModel);
		TextField<String> receiverUserIdField = new TextField<>("receiverUserId",receiverUserIdModel);

		form.add(subjectField);
		form.add(mainTextField);
		form.add(receiverUserIdField);
	}
}
