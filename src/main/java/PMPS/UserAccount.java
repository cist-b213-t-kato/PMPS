package PMPS;

import java.io.Serializable;

/**
 *
 * <p>ユーザーの情報が詰まったクラスです<p>
 * @author uesugimasashi
 *
 */
public class UserAccount implements Serializable {
	private static final long serialVersionUID = 6590180145565679411L;
	private String userId;
	private String userName;
	private int grade;

	/**
	 * @return accountId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param accountId セットする accountId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName セットする userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return grade
	 */
	public int getGrade() {
		return grade;
	}

	/**
	 * @param grade セットする grade
	 */
	public void setGrade(int grade) {
		this.grade = grade;
	}
}
