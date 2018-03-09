package PMPS.Communication;

import java.io.Serializable;

public class UserPost implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = -6228779236167248376L;
	private int bulletinId;
	private String userId;
	private boolean read;
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
	/**
	 * @return read
	 */
	public boolean isRead() {
		return read;
	}
	/**
	 * @param read セットする read
	 */
	public void setRead(boolean read) {
		this.read = read;
	}


}
