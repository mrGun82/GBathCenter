package g.oa.obj;

public class Record {
	private final String title;
	private final String content;
	private final String date;
	private final String type;
	
	public static class Builder {
		private String title;
		private String content;
		private String date;
		private String type;
		public Builder() {
		}
		
		public Builder title(String title) {
			this.title = title;
			return this;
		}
		public Builder content(String content) {
			this.content = content;
			return this;
		}
		public Builder date(String date) {
			this.date = date;
			return this;
		}
		public Builder type(String type) {
			this.type = type;
			return this;
		}
		
		public Record build() {
			return new Record(this);
		}
	}
	
	private Record(Builder builder) {
		this.title = builder.title;
		this.content = builder.content;
		this.date = builder.date;
		this.type = builder.type;
	}
	
	public String getTitle() {
		return title;
	}
	public String getContent() {
		return content;
	}
	public String getDate() {
		return date;
	}
	public String getType() {
		return type;
	}
	
	
}
