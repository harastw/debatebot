package com.sark.dialog.javafx;

import com.sark.dialog.core.DialogNode;

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
