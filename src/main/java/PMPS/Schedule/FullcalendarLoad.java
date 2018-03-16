package PMPS.Schedule;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AbstractDefaultAjaxBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;

public class FullcalendarLoad extends AbstractDefaultAjaxBehavior{

	private static final long serialVersionUID = 762063241319668086L;

	public FullcalendarLoad() {

	}

	@Override
	public void renderHead(Component component, IHeaderResponse response) {
		super.renderHead(component, response);

		response.render(CssHeaderItem.forUrl("fullcalendar/css/fullcalendar.min.css"));
		response.render(JavaScriptHeaderItem.forUrl("fullcalendar/lib/moment.min.js"));
		response.render(JavaScriptHeaderItem.forUrl("fullcalendar/lib/jquery.min.js"));
		response.render(JavaScriptHeaderItem.forUrl("fullcalendar/js/fullcalendar.min.js"));
		response.render(JavaScriptHeaderItem.forUrl("fullcalendar/js/ja.js"));
		response.render(JavaScriptHeaderItem.forUrl("fullcalendar/js/viewCalendar.js"));
		response.render(JavaScriptHeaderItem.forUrl("fullcalendar/js/editCalendar.js"));
	}

	@Override
	protected void respond(AjaxRequestTarget target) {

	}

}
