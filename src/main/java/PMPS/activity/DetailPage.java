package PMPS.activity;

import java.util.List;

import org.apache.wicket.Session;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.util.ListModel;

import PMPS.UserAccount;
import PMPS.UserAccountDAO;
import PMPS.Communication.Bulletin;
import PMPS.Communication.BulletinDAO;
import PMPS.Communication.CommunicationNotReadPage;
import PMPS.Communication.UserPost;
import PMPS.Communication.UserPostDAO;
import PMPS.LogIn.SignOutPage;
import PMPS.Schedule.SchedulePage;
import PMPS.Top.ONPage;

public class DetailPage extends WebPage {
	private static final long serialVersionUID = 1L;
	IModel<String> commentModel;
	IModel<String> nameModel;
	List<CommentBean> commentList;

	public DetailPage(int ID) {

		commentModel = Model.of("");
		nameModel = Model.of("");
		commentList = ActivityDAO.selectComment(ID);

		ListModel<DetailBean> listModel = new ListModel<DetailBean>() {
			@Override
			public List<DetailBean> getObject() {
				return ActivityDAO.selectProjectDetail(ID);
			}
		};

		ListModel<CommentBean> commentlistModel = new ListModel<CommentBean>() {
			@Override
			public List<CommentBean> getObject() {
				return ActivityDAO.selectComment(ID);
			}
		};

		//詳細内容の表示
		ListView<DetailBean> detailListView = new ListView<DetailBean>("DetailList", listModel) {
			@Override
			protected void populateItem(ListItem<DetailBean> item) {

				item.add(new Label("projectname", item.getModelObject().getProjectname()));
				item.add(new Label("leader", item.getModelObject().getLeader()));

				item.add(new Label("outline", item.getModelObject().getOutline()));
				item.add(new Label("achievement", item.getModelObject().getAchievement()));
				item.add(new Label("impression", item.getModelObject().getImpression()));

			}

		};
		add(detailListView);

		//コメントの表示
		ListView<CommentBean> commentListView = new ListView<CommentBean>("CommentList", commentlistModel) {
			@Override
			protected void populateItem(ListItem<CommentBean> item) {

				item.add(new Label("commenttext", item.getModelObject().getCommenttext()));
				item.add(new Label("username", item.getModelObject().getUsername()));
				item.add(new Label("date", item.getModelObject().getDate()));

			}

		};
		add(commentListView);

		Form<Void> form = new Form<Void>("form") {
			@Override
			protected void onSubmit() {
				super.onSubmit();

				commentList.add(new CommentBean());
				Session session =  getSession();
				UserAccount u = (UserAccount)session.getAttribute("user");
				ActivityDAO.insertComment(ID, commentModel.getObject(), u.getUserName());
				UserAccountDAO uadao = new UserAccountDAO();
				if(uadao.checkUserName(nameModel.getObject())){
				BulletinDAO bdao = new BulletinDAO();
				Bulletin b = new Bulletin();
				b.setMainText(u.getUserName()+"さんからコメントがきました。"+commentModel.getObject());
				b.setSubject("[活動履歴]コメントがきました");
				b.setUserId(u.getUserId());
				bdao.insert(b);
				UserPostDAO updao = new UserPostDAO();
				UserPost up = new UserPost();
				up.setBulletinId(bdao.selectBulletinId());
				up.setUserId(uadao.selectUserId(nameModel.getObject()));
				updao.insert(up);
				}
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

		TextField<String> nameField = new TextField<>("nameField", nameModel);
		form.add(nameField);
		TextArea<String> bodyField = new TextArea<>("bodyField", commentModel);
		form.add(bodyField);
	}

}
