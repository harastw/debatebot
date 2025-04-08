package nevermind;

public class DialogNodeSql extends DialogNode {
	private final String path;
	private final int id;
	DialogNodeSql(String text, String path, int id) {
		super(text);
		this.path = path;
		this.id = id;
	}
	public String getPath() {
		return path;
	}
	public int getId() {
		return id;
	}
}
