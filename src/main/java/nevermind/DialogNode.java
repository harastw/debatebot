package nevermind;

import java.util.ArrayList;

public class DialogNode {
	String text;
	Boolean isLast;
	ArrayList<Answer> answers;
	
	DialogNode(String _text, Boolean _isLast) {
		text = _text;
		isLast = _isLast;
		answers = new ArrayList<>();
	}
	
	public ArrayList<Answer> GetAnswers() {
		return answers;
	}
	
	public Boolean IsLast() {
		return isLast;
	}
	
	public String GetText() {
		return text;
	}
	
	public void AddAnswer(Answer answer) {
		answers.add(answer);
	}
}