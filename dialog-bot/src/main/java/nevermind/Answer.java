package nevermind;

public class Answer {
	Answer(String text, DialogNode node) {
		this.text = text;
		this.node = node;
	}
	protected final String text;
	protected final DialogNode node;
	String getText() {
		return text;
	}
	DialogNode getNode() {
		return node;
	}
}
