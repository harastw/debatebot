package com.sark.dialog.core;

import java.util.ArrayList;

public class DialogNode {
	protected final String text;
	protected final ArrayList<Answer> answers;
	
	public DialogNode(String text) {
		this.text = text;
		answers = new ArrayList<>();
	}
	
	public ArrayList<Answer> getAnswers() {
		return answers;
	}
	
	public String getText() {
		return text;
	}
	
	public void addAnswer(Answer answer) {
		answers.add(answer);
	}
}