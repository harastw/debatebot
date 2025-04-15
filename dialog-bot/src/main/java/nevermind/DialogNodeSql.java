package nevermind;

public class DialogNodeSql extends DialogNode {
	private int id;
	DialogNodeSql(int id, String text) {
		super(text);
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
}
