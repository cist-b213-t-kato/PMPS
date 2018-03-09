package kagidai.pmps.Communication;

import java.io.Serializable;

public class Bulletin implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = 1480035454127221318L;
	private int bulletinId;
	private String subject;
	private String mainText;
	private String userId;
	/**
	 * @return bulletinId
	 */
	public int getBulletinId() {
		return bulletinId;
	}
	/**
	 * @param bulletinId セットする bulletinId
	 */
	public void setBulletinId(int bulletinId) {
		this.bulletinId = bulletinId;
	}
	/**
	 * @return subject
	 */
	public String getSubject() {
		return subject;
	}
	/**
	 * @param subject セットする subject
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	/**
	 * @return mainText
	 */
	public String getMainText() {
		return mainText;
	}
	/**
	 * @param mainText セットする mainText
	 */
	public void setMainText(String mainText) {
		this.mainText = mainText;
	}
	/**
	 * @return userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId セットする userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}



}
