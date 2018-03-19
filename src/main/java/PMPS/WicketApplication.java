package PMPS;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;

import PMPS.LogIn.LogInPage;

/**
 * Application object for your web application.
 * If you want to run this application without deploying, run the Start class.
 *
 * @see PMPS.Start#main(String[])
 */
public class WicketApplication extends WebApplication
{
	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<? extends WebPage> getHomePage()
	{
		return LogInPage.class;
	}

	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init()
	{
		super.init();

		mountPage("/home",LogInPage.class);

		// add your configuration here
	}
}
