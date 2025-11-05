package com.sark.dialog.core;

public class Answer {
	public Answer(String text, DialogNode node) {
		this.text = text;
		this.node = node;
	}
	protected final String text;
	protected final DialogNode node;
	public String getText() {
		return text;
	}
	public DialogNode getNode() {
		return node;
	}
}
