package g.oa.bean;

import java.text.ParseException;

import g.oa.util.DateUtil;

public class Record {
	private long id;
	private String title;
	private String type; 
	private String date;
	private String content;
	private String fromDate;
	private String toDate;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String getShowDate() {
		try {
			return DateUtil.transformToViewYYYYMMDDHHMMSSM(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "Record [id=" + id + ", title=" + title + ", type=" + type
				+ ", date=" + date + ", content=" + content + ", fromDate="
				+ fromDate + ", toDate=" + toDate + "]";
	}
}
