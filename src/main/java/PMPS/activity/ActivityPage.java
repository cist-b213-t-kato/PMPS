package PMPS.activity;

import java.util.List;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.util.ListModel;

public class ActivityPage extends WebPage {
	private static final long serialVersionUID = 1L;

	public ActivityPage(int year) {

		ListModel<ActivityBean> listModel = new ListModel<ActivityBean>() {
			@Override
			public List<ActivityBean> getObject() {
				return ActivityDAO.select(year);
			}
		};

		ListView<ActivityBean> activityListView = new ListView<ActivityBean>("activityList", listModel) {
			@Override
			protected void populateItem(ListItem<ActivityBean> item) {

				Link<Void> toDetailPage = new Link<Void>("toDetailPage") {
					@Override
					public void onClick() {

						setResponsePage(new DetailPage(item.getModelObject().getProjectid()));
					}
				};

				toDetailPage.add(new Label("projectname", item.getModelObject().getProjectname()));
				item.add(toDetailPage);

				item.add(new Label("leader", item.getModelObject().getLeader()));
				item.add(new Label("term", item.getModelObject().getTerm()));
				item.add(new Label("outline", item.getModelObject().getOutline()));

				Label link = new Label("link", item.getModelObject().getLink());
				link.add(new AttributeModifier("href", new Model<String>(item.getModelObject().getLink())));
				item.add(link);

				item.add(new Label("grade", item.getModelObject().getGrade()));

			}
		};

		add(activityListView);

		Link<Void> InsertActivityLink = new Link<Void>("InsertActivityLink") {


			@Override
			public void onClick() {
				setResponsePage(new InsertActivityPage());
			}
		};
		add(InsertActivityLink);
	}
}
