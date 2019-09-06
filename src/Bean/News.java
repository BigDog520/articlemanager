package Bean;

public class News {
	private int news_id;
	private int user_id;
	private String title;
	private String type;
	private String content;
	private String post_time;
	
	public int getNews_id() {
		return news_id;
	}
	public void setNews_id(int news_id) {
		this.news_id = news_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPost_time() {
		return post_time;
	}
	public void setPost_time(String post_time) {
		this.post_time = post_time;
	}
}
