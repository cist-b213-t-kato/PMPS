package PMPS.Schedule;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.Session;
import org.apache.wicket.ajax.AbstractDefaultAjaxBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;

import PMPS.UserAccount;
import PMPS.define.DateTimeDefine;
import PMPS.define.FullCalendarDefine.HEADER;
import PMPS.define.FullCalendarDefine.METHOD;
import PMPS.define.FullCalendarDefine.VIEW;
import net.arnx.jsonic.JSON;

/**
 *
 * <p>フルカレンダーの核となるクラスです</p>
 * @author uesugimasashi
 *
 */
public class FullcalendarPanel extends Panel {

	private static final long serialVersionUID = 6124217131174484518L;

	private String calendarName;
	private String eventListName;
	private List<Event> eventList;
	private Label initJSLabel;

	private LocalDate nowDate;
	private LocalDate dispDate;

	// FullCalendarの設定事項
	private String slotLabelFormat;
	private String timeFormat;
	private String slotDuration;
	private String defaultDate;
	private String defaultView;
	private String scrollTime;
	private boolean editable;
	private boolean selectable;
	private boolean navLinks;

	// header
	private String headerLeft;
	private String headerCenter;
	private String headerRight;

	// views
	private String viewMonthColumnFormat;
	private String viewWeekColumnFormat;
	private String viewDayColumnFormat;

	// 作成イベントカラー
	private String createEventBackgroundColor;
	private String createEventBorderColor;
	private String createEventTextColor;

	// モーダルウィンドウ
	private ModalWindow modalWindow;

	public FullcalendarPanel(String id) {
		super(id);


		this.calendarName = "fullcalendar";
		this.eventListName = "eventList";

		this.nowDate = LocalDate.now();
		this.dispDate = nowDate;

		// FullCalendarの設定事項
		this.slotLabelFormat = DateTimeDefine.TIME_FORMAT;
		this.timeFormat = DateTimeDefine.TIME_FORMAT;
		this.slotDuration = "00:15:00"; // 15分区切りの指定
		this.defaultDate = nowDate.toString();
		this.defaultView = VIEW.MONTH.getViewName();
		this.scrollTime = LocalTime.now().truncatedTo(ChronoUnit.HOURS).toString();
		this.editable = true;
		this.selectable = true;
		this.navLinks = true;

		// header
		this.headerLeft = HEADER.TODAY.getValue();
		this.headerCenter = HEADER.PREV.getValue() +" "+ HEADER.TITLE.getValue() +" "+ HEADER.NEXT.getValue();
		this.headerRight = VIEW.MONTH.getViewName();

		// views
		this.viewMonthColumnFormat = DateTimeDefine.DAYOFWEEK_FORMAT;
		this.viewWeekColumnFormat = DateTimeDefine.DATE_DAYOFWEEK_FORMAT;
		this.viewDayColumnFormat = DateTimeDefine.DAYOFWEEK_FORMAT;

		// 作成イベントカラー
		this.createEventBackgroundColor = "#f0f8ff";
		this.createEventBorderColor = "#b0c4de";
		this.createEventTextColor = "#b0c4de";

		Session session = getSession();

		IModel<String> initJSModel = new AbstractReadOnlyModel<String>() {
			private static final long serialVersionUID = 3211579631691093290L;

			@Override
			public String getObject() {
				StringBuilder js = new StringBuilder();

				List<Event> events = new ArrayList<>();

				 UserAccount ua = (UserAccount) session.getAttribute("user");

				 ScheduleDAO sdao = new ScheduleDAO();
				 events=sdao.selectMonthTask(ua.getUserId(), dispDate.getYear(),
				 dispDate.getMonthValue());

				eventList = events;

				js.append(eventListName +" = "+ encodeListToJson(eventList) +";");

				return js.toString();
			}
		};

		initJSLabel = new Label("initJS", initJSModel) {
			private static final long serialVersionUID = 6311718980304266149L;

			@Override
			protected void onInitialize() {
				super.onInitialize();
				setOutputMarkupId(true);
				setEscapeModelStrings(false);
			}
		};

		IModel<String> fullCalendarJSModel = new AbstractReadOnlyModel<String>() {
			private static final long serialVersionUID = -6391622539195518036L;

			@Override
			public String getObject() {
				String dateFormat = DateTimeDefine.DATE_HYPHEN_SEPARATE_FORMAT;
				String fullcalendarRenderEventMethod = METHOD.RENDEREVENT.getMethodName();

				StringBuilder javascript = new StringBuilder();

				javascript.append("$(document).ready(function() {");
				javascript.append("	$('#"+ calendarName +"').fullCalendar({");
				javascript.append("		header: {");
				javascript.append("            left: "+ addSingleQuoteString(headerLeft) +",");
				javascript.append("            center: "+ addSingleQuoteString(headerCenter) +",");
				javascript.append("            right: "+ addSingleQuoteString(headerRight));
				javascript.append("		},");
				javascript.append("		views: {");
				javascript.append("		    month: {");
				javascript.append("		    	columnFormat: "+ addSingleQuoteString(viewMonthColumnFormat));
				javascript.append("		    },");
				javascript.append("			agendaWeek: {");
				javascript.append("				columnFormat: "+ addSingleQuoteString(viewWeekColumnFormat));
				javascript.append("			},");
				javascript.append("			agendaDay: {");
				javascript.append("				columnFormat: "+ addSingleQuoteString(viewDayColumnFormat));
				javascript.append("			},");
				javascript.append("		},");
				javascript.append("		slotLabelFormat: "+ addSingleQuoteString(slotLabelFormat) +",");
				javascript.append("		timeFormat: "+ addSingleQuoteString(timeFormat) +",");
				javascript.append("		slotDuration: "+ addSingleQuoteString(slotDuration) +",");
				javascript.append("		defaultDate: "+ addSingleQuoteString(defaultDate) +",");
				javascript.append("		defaultView: "+ addSingleQuoteString(defaultView) +",");
				javascript.append("     scrollTime: "+ addSingleQuoteString(scrollTime) +",");
				javascript.append("     events: "+ eventListName +",");
				javascript.append("		editable: "+ editable +",");
				javascript.append("		selectable: "+ selectable +",");
				javascript.append("     navLinks: "+ navLinks +",");

//				 FullCalendar表示時の振る舞い
				javascript.append("		viewRender: function(view, element) {");
				javascript.append("			/**");
				javascript.append("			 * fullcalendar表示タイトル(年月日)を取得して");
				javascript.append("			 * javaのcallbackに引数をして渡す");
				javascript.append("			 */");
				javascript.append("			var changedDispDate = moment(view.intervalStart._d).format("+ addSingleQuoteString(dateFormat) +");");
				javascript.append("			viewRenderCallback(changedDispDate);"); // ViewRenderBehaviorの呼び出し
				javascript.append("		},");
//
//				// イベント生成時の振る舞い
				javascript.append("		select: function (start, end) {");
				javascript.append("			var planEvent = {};");
				javascript.append("			planEvent = {");
				javascript.append("				start: start,");
				javascript.append("				end: end,");
				javascript.append("				backgroundColor: "+ addSingleQuoteString(createEventBackgroundColor) +",");
				javascript.append("				borderColor: "+ addSingleQuoteString(createEventBorderColor) +",");
				javascript.append("				textColor: "+ addSingleQuoteString(createEventTextColor));
				javascript.append("			};");
				javascript.append("         insertEventCallback(start, end);"); // InsertBehaviorの呼び出し
				javascript.append("");
				javascript.append("			$("+ calendarName +").fullCalendar("+ addSingleQuoteString(fullcalendarRenderEventMethod) +", planEvent, true);");
//				javascript.append("		},");
//
//				// イベントがクリックされた時の振る舞い
//				javascript.append("		eventClick: function(event) {");
//				javascript.append("			eventClickCallback(event.id);"); // EventClickBehaviorの呼び出し
//				javascript.append("		},");
//
//				// イベントを移動させる時の振る舞い
//				javascript.append("		eventDrop: function(event) {");
//				javascript.append("			timeUpdateEventCallback(event.id, event.start, event.end);"); // TimeUpdateBehaviorの呼び出し
//				javascript.append("		},");
//
//				// イベントの大きさを変更する時の振る舞い
//				javascript.append("		eventResize: function(event) {");
//				javascript.append("			timeUpdateEventCallback(event.id, event.start, event.end);"); // TimeUpdateBehaviorの呼び出し
				javascript.append("		}");

				javascript.append("	});");
				javascript.append("});");

				return javascript.toString();
			}

		};

		Label fullCalendarJSLabel = new Label("fullCalendarJS", fullCalendarJSModel) {
			private static final long serialVersionUID = 2873339586356121235L;

			@Override
			protected void onInitialize() {
				super.onInitialize();
				setOutputMarkupId(true);
				setEscapeModelStrings(false);
			}
		};

		modalWindow = new ModalWindow("modal");
		modalWindow.setCssClassName(ModalWindow.CSS_CLASS_GRAY);
		modalWindow.setResizable(false);
		modalWindow.setWidthUnit("px");
		modalWindow.setUseInitialHeight(false);
		modalWindow.setWindowClosedCallback(new ModalWindow.WindowClosedCallback() {
			private static final long serialVersionUID = -5811745206386805609L;

			@Override
			public void onClose(AjaxRequestTarget target) {
				target.add(initJSLabel);
				target.appendJavaScript("rerenderEvents("+ addSingleQuoteString(calendarName) +", "+ eventListName +");");
			}
		});

		this.add(initJSLabel);
		this.add(fullCalendarJSLabel);
		this.add(modalWindow);

		this.add(new ViewRenderBehavior());
		this.add(new InsertBehavior());
		this.add(new rerenderEventBehavior());
	}

	/**
	 * 文字列をシングルクォーテーションで括って返す
	 *
	 * @param str
	 * @return str
	 */
	private String addSingleQuoteString(String str) {
		return "'" + str + "'";
	}

	/**
	 * {@link Event} 型のリストをJSON形式の文字列で返す
	 * @param event {@link Event} 型のリスト
	 * @return 成功・JSON形式の文字列 : 失敗・null
	 */
	private String encodeListToJson(List<Event> event) {
        return JSON.encode(event);
    }
	/**
	 * fullcalendarの予定・実績の書き込み時に振る舞うクラス
	 */
	private class InsertBehavior extends AbstractDefaultAjaxBehavior {
		private static final long serialVersionUID = 8083460158869341481L;

		@Override
		public void renderHead(Component component, IHeaderResponse response) {
			super.renderHead(component, response);

			StringBuilder js = new StringBuilder();
			js.append("function insertEventCallback(start, end) {");
			js.append(String.format("Wicket.Ajax.get({'u':'%s',", getCallbackUrl()));
			js.append("    'ep':{");
			js.append("      'start': moment(start).format(\"YYYY/MM/DD\"),");
//			js.append("      'start': moment(start).format(\"YYYY/MM/DD HH:mm:ss\"),");
			js.append("      'end': moment(end).format(\"YYYY/MM/DD HH:mm:ss\")");
			js.append("    }");
			js.append("  });");
			js.append("  return 1;");
			js.append("}");
			response.render(JavaScriptHeaderItem.forScript(js.toString(), getClass().getName()));
		}

		/**
		 * callback時に呼び出される
		 * @param target
		 */
		@Override
		protected void respond(AjaxRequestTarget target) {

			LocalDate selectedDate = LocalDate.parse(
					getRequest().getRequestParameters().getParameterValue("start").toString(),
					DateTimeFormatter.ofPattern(DateTimeDefine.STRING_TO_DATE_FORMAT));

			modalWindow.setContent(new FullcalendarModalInfoPanel(modalWindow.getContentId(),selectedDate));
			modalWindow.show(target);
		}
	}

	private class rerenderEventBehavior extends AbstractDefaultAjaxBehavior{
		/**
		 *
		 */
		private static final long serialVersionUID = -3275139050864399952L;

		@Override
		public void renderHead(Component component, IHeaderResponse response) {
			// TODO 自動生成されたメソッド・スタブ
			super.renderHead(component, response);

			StringBuilder js = new StringBuilder();
			js.append("function rerenderEvents(calendarName,eventListName) {");
			js.append(" $('#'+calendarName).fullCalendar('removeEventSources');");
			js.append("$('#'+calendarName).fullCalendar('addEventSource',eventListName);");
			js.append("}");
			response.render(JavaScriptHeaderItem.forScript(js.toString(), getClass().getName()));

		}
		@Override
		protected void respond(AjaxRequestTarget target) {
			// TODO 自動生成されたメソッド・スタブ

		}

	}

	private class ViewRenderBehavior extends AbstractDefaultAjaxBehavior {
		private static final long serialVersionUID = 9185393960563039022L;

		@Override
		public void renderHead(Component component, IHeaderResponse response) {
			super.renderHead(component, response);

			StringBuilder js = new StringBuilder();
			js.append("function viewRenderCallback(changedDispDate) {");
			js.append(String.format("Wicket.Ajax.get({'u':'%s',", getCallbackUrl()));
			js.append("    'ep':{");
			js.append("      'changedDispDate': changedDispDate");
			js.append("    }");
			js.append("  });");
			js.append("  return 1;");
			js.append("}");
			response.render(JavaScriptHeaderItem.forScript(js.toString(), getClass().getName()));
		}

		/**
		 * callback時に呼び出される
		 * @param target
		 */
		@Override
		protected void respond(AjaxRequestTarget target) {

			String changedDispDateStr = getRequest().getRequestParameters().getParameterValue("changedDispDate").toString();
			LocalDate changedDispDate = LocalDate.parse(changedDispDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

			// 表示日と取得日が一致しない場合, 表示日を更新する
			if (dispDate != null && !dispDate.equals(changedDispDate.toString())) {
				dispDate = changedDispDate;
			}

			// 表示日と現在日が一致しない場合, イベントを更新する
			if (!dispDate.toString().equals(nowDate.toString())) {
				target.add(initJSLabel);
				target.appendJavaScript("rerenderEvents("+ addSingleQuoteString(calendarName) +", "+ eventListName +");");
			}
		}
	}

}

