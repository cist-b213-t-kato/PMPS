package PMPS.Communication;

import org.apache.wicket.Session;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;

import PMPS.UserAccount;
import PMPS.Top.ONPage;

public class BulletinPage extends WebPage {

	/**
	 *
	 */
	private static final long serialVersionUID = -7311243684350033803L;

	/**
	 *
	 */
	public BulletinPage(int bulletinId,Boolean boo) {
				BulletinDAO bdao = new BulletinDAO();
				Bulletin b = bdao.select(bulletinId);
				UserPostDAO pdao = new UserPostDAO();

				Session session = getSession();
				UserAccount u = (UserAccount) session.getAttribute("user");

				if(u!=null){
					Label mainTextLabel = new Label("mainText",b.getMainText());
					add(mainTextLabel);
					pdao.updateRead(true, bulletinId, u.getUserId());
				}

				Link<Void> NotReadLink = new Link<Void>("NotReadLink") {
					/**
					 *
					 */
					private static final long serialVersionUID = -1098459838625198314L;

					/**
					 *
					 */

					@Override
					public void onClick() {
						pdao.updateRead(false, bulletinId, u.getUserId());
						if(boo){
							setResponsePage(new CommunicationReadPage());
						}else{
						setResponsePage(new CommunicationNotReadPage());
						}
					}
				};
				add(NotReadLink);

				Link<Void> homeLink = new Link<Void>("homeLink") {
					/**
					 *
					 */
					private static final long serialVersionUID = 7123042162371774643L;

					@Override
					public void onClick() {
						setResponsePage(new ONPage());
					}
				};
				add(homeLink);
		}
}
