package com.sark.dialog.javafx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.sark.dialog.core.DialogNode;

public class SqliteDBManipulator {
	private ArrayList<DialogNodeSql> nodes;
	private ArrayList<AnswerSql> answers;
	
	SqliteDBManipulator() {
		nodes = new ArrayList<DialogNodeSql>();
		answers = new ArrayList<AnswerSql>();
	}
	
	public ArrayList<DialogNode> getNodes() {
	    return new ArrayList<>(nodes);
	}
	
	public DialogNode getRoot() {
		return nodes.get(0);
	}
	
	public int writeNewNodeToDb(String text) {
		String url = "jdbc:sqlite:mydatabase.db";
		String sql = "INSERT INTO DialogNode(text) VALUES(?)";

		try (Connection conn = DriverManager.getConnection(url);
				PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
				pstmt.setString(1, text);
				pstmt.executeUpdate();
				ResultSet rs = pstmt.getGeneratedKeys();
		        if (rs.next()) {
		            return rs.getInt(1);
		        }
		    } catch (SQLException e) {
		        System.out.println("Error inserting answer: " + e.getMessage());
		    }
		return -1;
	}
	
	public void writeNewAnswerToDb(String text, int parentDialogId, int childDialogId) {
		String url = "jdbc:sqlite:mydatabase.db";
		String sql = "INSERT INTO Answer(text, parent_dialog_id, child_dialog_id) VALUES(?, ?, ?)";

		try (Connection conn = DriverManager.getConnection(url);
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

				pstmt.setString(1, text);
		        pstmt.setInt(2, parentDialogId);
		        pstmt.setInt(3, childDialogId);

		        int rowsInserted = pstmt.executeUpdate();
		        if (rowsInserted > 0) {
		            System.out.println("New answer inserted successfully.");
		        } else {
		            System.out.println("Insert failed.");
		        }

		    } catch (SQLException e) {
		        System.out.println("Error inserting answer: " + e.getMessage());
		    }
	}
	
	public void loadData() {
	    String url = "jdbc:sqlite:mydatabase.db";
	    try {
	        Connection conn = DriverManager.getConnection(url);
	        Statement stmtDialog = conn.createStatement();
	        String sqlDialog = "SELECT id, text FROM DialogNode";
	        ResultSet rsDialog = stmtDialog.executeQuery(sqlDialog);
	        while (rsDialog.next()) {
	        	int id = rsDialog.getInt("id");
	            String text = rsDialog.getString("text");
	            DialogNodeSql node = new DialogNodeSql(id, text);
	            nodes.add(node);
	            System.out.println("Dialog:" + text);
	        }
	        Statement stmtAnswer = conn.createStatement();
	        String sqlAnswers = "SELECT text, parent_dialog_id, child_dialog_id FROM Answer";
	        ResultSet rsAnswers = stmtAnswer.executeQuery(sqlAnswers);
	        while (rsAnswers.next()) {
	            String text = rsAnswers.getString("text");
	            int parent_dialog_id = rsAnswers.getInt("parent_dialog_id");
	            int child_dialog_id = rsAnswers.getInt("child_dialog_id");
	            for (int i = 0; i < nodes.size(); i++) {
	            	if (nodes.get(i).getId() == child_dialog_id) {
	            		answers.add(new AnswerSql(text, nodes.get(i), parent_dialog_id));
	            	}
	            }
	            System.out.println("Answer:" + text);
	        }
	        for (int i = 0; i < nodes.size(); i++) {
				for (int j = 0; j < answers.size(); j++) {
					if (answers.get(j).getParentDialogId() == nodes.get(i).getId()) {
						nodes.get(i).addAnswer(answers.get(j));
					}
				}
			}
	        conn.close();
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }
	}
}
