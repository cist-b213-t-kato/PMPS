package kagidai.pmps.Communication;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Session;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.util.ListModel;

import kagidai.pmps.UserAccount;
import kagidai.pmps.schedule.ONPage;

public class CommunicationReadPage extends WebPage{

	/**
	 *
	 */
	private static final long serialVersionUID = -2941749330815026666L;


	public CommunicationReadPage(){
		ListModel<Bulletin> ReadListModel = new ListModel<Bulletin>(){
			/**
			 *
			 */
			private static final long serialVersionUID = -3095666348732050576L;

			@Override
			public List<Bulletin> getObject(){
				UserPostDAO udao = new UserPostDAO();
				BulletinDAO bdao = new BulletinDAO();
				Session session = getSession();
				UserAccount u = (UserAccount)session.getAttribute("user");

				List<Bulletin> bList = new ArrayList<>();
				List<Integer> idList = udao.selectIsRead(u.getUserId(), true);
				for(int id : idList){
					bList.add(bdao.select(id));
				}
				return bList;
			}
		};

		ListView<Bulletin> ReadListView = new ListView<Bulletin>("Read",ReadListModel){
			/**
			 *
			 */
			private static final long serialVersionUID = 4934462054861497197L;

			@Override
			protected void populateItem(ListItem<Bulletin> item){
				item.add(new Label("bulletinId",item.getModelObject().getBulletinId()));
				Link<Void> BulletinPage = new Link<Void>("BulletinPage"){
					/**
					 *
					 */
					private static final long serialVersionUID = 4074545361472127475L;

					@Override
					public void onClick(){
						setResponsePage(new BulletinPage(item.getModelObject().getBulletinId(),true));
					}
				};
				item.add(BulletinPage);
				BulletinPage.add(new Label("subject",item.getModelObject().getSubject()));
			}
		};
		add(ReadListView);

		Link<Void> homelink = new Link<Void>("homelink"){
			/**
			 *
			 */
			private static final long serialVersionUID = 4029383751906684759L;

			public void onClick(){
				setResponsePage(new ONPage());
			}
		};
		add(homelink);

		Link<Void> NotReadPageLink = new Link<Void>("NotReadPageLink") {
			/**
			 *
			 */
			private static final long serialVersionUID = 7123042162371774643L;

			@Override
			public void onClick() {
				setResponsePage(new CommunicationNotReadPage());
			}
		};
		add(NotReadPageLink);

		Link<Void> InsertBulletinPageLink = new Link<Void>("InsertBulletinPageLink") {
			/**
			 *
			 */
			private static final long serialVersionUID = 7123042162371774643L;

			@Override
			public void onClick() {
				setResponsePage(new InsertBulletinPage());
			}
		};
		add(InsertBulletinPageLink);
	}
}
