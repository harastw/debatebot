package com.sark.dialog.javafx;

import com.sark.dialog.core.Answer;
import com.sark.dialog.core.DialogNode;

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
