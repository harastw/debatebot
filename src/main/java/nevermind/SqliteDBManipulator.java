package nevermind;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class SqliteDBManipulator {
	private ArrayList<DialogNodeSql> nodes;
	private ArrayList<AnswerSql> answers;
	SqliteDBManipulator() {
		answers = new ArrayList<AnswerSql>();
		nodes = new ArrayList<DialogNodeSql>();
	}
	public DialogNode getRoot() {
		return nodes.get(0);
	}
	public void loadData() {
	    String url = "jdbc:sqlite:mydatabase.db";
	    try {
	        Connection conn = DriverManager.getConnection(url);
	        Statement stmtDialog = conn.createStatement();
	        Statement stmtAnswer = conn.createStatement();
	        String sqlAnswers = "SELECT path, answer_text, dialog_id FROM Answer";
	        String sqlDialog = "SELECT path, text, id FROM DialogNode";
	        ResultSet rsAnswers = stmtAnswer.executeQuery(sqlAnswers);
	        ResultSet rsDialog = stmtDialog.executeQuery(sqlDialog);

	        while (rsDialog.next()) {
	            String dialogNodeText = rsDialog.getString("text");
	            String dialogPath = rsDialog.getString("path");
	            int dialogID = rsDialog.getInt("id");
	            nodes.add(new DialogNodeSql(dialogNodeText, dialogPath, dialogID));
	            System.out.println("Dialog:" + dialogNodeText);
	        }
	        while (rsAnswers.next()) {
	        	String answerPath = rsAnswers.getString("path");
	            String answerText = rsAnswers.getString("answer_text");
	            int answerDialogID = rsAnswers.getInt("dialog_id");
	            for (int i = 0; i < nodes.size(); i++)
	            	if (nodes.get(i).getId() == answerDialogID)
	            		answers.add(new AnswerSql(answerText, nodes.get(i), answerPath));
	            System.out.println("Answer:" + answerText);
	        }
	        conn.close();
	        for (int i = 0; i < nodes.size(); i++) {
				for (int j = 0; j < answers.size(); j++) {
					if (answers.get(j).getPath().equals(nodes.get(i).getPath()))
						nodes.get(i).addAnswer(new Answer(answers.get(j).getText(), answers.get(j).getNode()));
				}
			}
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }
	}
}
