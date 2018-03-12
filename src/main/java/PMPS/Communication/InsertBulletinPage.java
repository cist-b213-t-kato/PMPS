package PMPS.Communication;

import java.util.List;

import org.apache.wicket.Session;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.component.IRequestablePage;

import PMPS.UserAccount;
import PMPS.UserAccountDAO;
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
