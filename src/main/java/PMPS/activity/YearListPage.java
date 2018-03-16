package PMPS.activity;

import java.util.List;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.util.ListModel;

/**
 * <p>活動履歴ページの最初、年度一覧を表示します</p>
 * @author boc
 *
 */
public class YearListPage extends WebPage {


	private static final long serialVersionUID = 1L;

	public YearListPage() {

		ListModel<Integer> listModel = new ListModel<Integer>() {
			@Override
			public List<Integer> getObject() {
				return ActivityDAO.selectYear();
			}
		};

		ListView<Integer> YearListView = new ListView<Integer>("YearList", listModel) {
			@Override
			protected void populateItem(ListItem<Integer> item) {

				Link<Void> toActivityPage = new Link<Void>("toActivityPage") {
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

		Link<Void> AllView = new Link<Void>("AllActivityLink") {
			@Override
			public void onClick() {
				setResponsePage(new ActivityPage(0));
			}
		};
		add(AllView);

		Link<Void> InsertActivityLink = new Link<Void>("InsertActivityLink") {


			@Override
			public void onClick() {
				setResponsePage(new InsertActivityPage());
			}
		};
		add(InsertActivityLink);


	}
}
