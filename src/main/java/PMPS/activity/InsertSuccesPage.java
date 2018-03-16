package PMPS.activity;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;

import PMPS.Top.ONPage;

/**
 * <p>インサート成功時に表示するページ</p>
 * @author boc
 *
 */

public class InsertSuccesPage extends WebPage {
	public InsertSuccesPage() {
		Link<Void> ONPageLink= new Link<Void>("ONPageLink") {
			@Override
			public void onClick() {
				setResponsePage(new ONPage());
			}
		};
		add(ONPageLink);

	}
}
