package kagidai.pmps.Activity;


import java.util.List;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.util.ListModel;

public class YearListPage extends WebPage {


	private static final long serialVersionUID = 1L;

	public YearListPage() {

		ListModel<Integer> listModel = new ListModel<Integer>() {
			/**
			 *
			 */
			private static final long serialVersionUID = -1190725715427855501L;

			@Override
			public List<Integer> getObject() {
				return ActivityDAO.selectYear();
			}
		};

		ListView<Integer> YearListView = new ListView<Integer>("YearList", listModel) {
			/**
			 *
			 */
			private static final long serialVersionUID = -2960991639228239464L;

			@Override
			protected void populateItem(ListItem<Integer> item) {

				Link<Void> toActivityPage = new Link<Void>("toActivityPage") {
					/**
					 *
					 */
					private static final long serialVersionUID = 6930862864012672408L;

					@Override
					public void onClick() {

						setResponsePage(new ActivityPage(item.getModelObject()));
					}
				};
				item.add(toActivityPage);

				toActivityPage.add(new Label("year", item.getModelObject()));

			}
		};
		add(YearListView);


	}
}
