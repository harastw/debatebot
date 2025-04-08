package nevermind;

public class AnswerSql extends Answer {
	private final String path;
	AnswerSql(String text, DialogNode node, String path) {
		super(text, node);
		this.path = path;
	}
	public String getPath() {
		return path;
	}
}