package PMPS.Top;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public class StartPage extends WebPage {
	private static final long serialVersionUID = 1L;

	public StartPage() {
		String message = "村祭り";
		IModel<String> labelModel = Model.of(message);
		Label label1 = new Label("label1", labelModel);
		add(label1);

		Link<Void> toONPage = new Link<Void>("toONPage") {
			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(new ONPage());
			}

		};
		add(toONPage);
	}
}
