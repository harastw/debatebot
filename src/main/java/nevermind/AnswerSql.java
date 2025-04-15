package nevermind;

public class AnswerSql extends Answer {
	private int parentDialogId;
	AnswerSql(String text, DialogNode node, int parentDialogId) {
		super(text, node);
		this.parentDialogId = parentDialogId;
	}
	public int getParentDialogId() {
		return parentDialogId;
	}
}
