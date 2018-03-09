package PMPS.activity;

import java.util.List;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.util.ListModel;

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
				ActivityDAO.insertComment(ID, commentModel.getObject(), nameModel.getObject());
			}
		};
		add(form);

		TextField<String> nameField = new TextField<>("nameField", nameModel);
		form.add(nameField);
		TextField<String> bodyField = new TextField<>("bodyField", commentModel);
		form.add(bodyField);
	}

}
