package kagidai.pmps.Activity;

public class ActivityBean {
	private int projectid;
	private String projectname;
	private String leader;
	private String term;
	private String outline;
	private String link;
	private int grade;

	//	public ActivityBean(int projectid, String projectname, String leader, String term, String outline, String link, int grade) {
	//		this.setProjectid(projectid);
	//		this.setProjectname(projectname);
	//		this.setLeader(leader);
	//		this.setTerm(term);
	//		this.setOutline(outline);
	//		this.setLink(link);
	//		this.setGrade(grade);
	//
	//	}

	public int getProjectid() {
		return projectid;
	}

	public void setProjectid(int projectid) {
		this.projectid = projectid;
	}

	public String getProjectname() {
		return projectname;
	}

	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}

	public String getLeader() {
		return leader;
	}

	public void setLeader(String leader) {
		this.leader = leader;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public String getOutline() {
		return outline;
	}

	public void setOutline(String outline) {
		this.outline = outline;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}
}
