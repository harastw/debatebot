package com.sark.dialog.javafx;

import javafx.application.Application;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

import com.sark.dialog.core.DialogNode;
import com.sark.dialog.core.Answer;

public class ApplicationFX extends Application {
	private VBox vbox;
	private DialogNode node;
	private ArrayList<DialogNode> nodes;
	private SqliteDBManipulator manipulator;
	
	public ApplicationFX() {
		manipulator = new SqliteDBManipulator();
	}
	
	private void openEditWindow() {
	    Stage dialog = new Stage();
	    dialog.initModality(Modality.APPLICATION_MODAL);
	    HBox hlayout = new HBox();
	    AtomicBoolean fieldIsExist = new AtomicBoolean(false);
	    for (int i = 0; i < nodes.size(); i++) {
	    	int index = i;
	        Button nodeButton = new Button(nodes.get(i).getText());
	        nodeButton.setOnAction(event -> {
	            if (!fieldIsExist.get()) {
	                fieldIsExist.set(true);
	                TextField answerField = new TextField();
	                TextField nodeField = new TextField();
	                Button add = new Button("add");
	                add.setOnAction(event2 -> {
	                	int childId = manipulator.writeNewNodeToDb(nodeField.getText());
	                    manipulator.writeNewAnswerToDb(answerField.getText(), index + 1, childId);
	                });
	                hlayout.getChildren().addAll(answerField, nodeField, add);
	            }
	        });
	        hlayout.getChildren().add(nodeButton);
	    }
	    Scene scene = new Scene(hlayout, 600, 400);
	    dialog.setTitle("Editor");
	    dialog.setScene(scene);
	    dialog.showAndWait();
	}
	
	private void updateUI(DialogNode node) {
		HBox hbox = new HBox(50);

		ArrayList<Answer> answers;
		answers = node.getAnswers();
		for (int i = 0; i < answers.size(); i++) {
			Answer currentAnswer = answers.get(i);
            Button yourTake = new Button(currentAnswer.getText());
            yourTake.setOnAction(event -> {
            	disableOldButtons(hbox);
            	updateUI(currentAnswer.getNode()); 
            });
            hbox.getChildren().add(yourTake);
		}
		Button openButton = new Button("Открыть второе окно");
		openButton.setOnAction(e -> openEditWindow());
		Label botTake = new Label(node.getText());
		vbox.getChildren().add(botTake);
		vbox.getChildren().add(hbox);
	}
	
	private void disableOldButtons(HBox hbox) {
        for (int i = 0; i < hbox.getChildren().size(); i++) {
            if (hbox.getChildren().get(i) instanceof Button) {
                Button button = (Button) hbox.getChildren().get(i);
                button.setDisable(true);
            }
        }
    }
	
	@Override
    public void start(Stage stage) throws Exception {
		vbox = new VBox(10);
		
		manipulator.loadData();
		nodes = manipulator.getNodes();
		node = manipulator.getRoot();
		
		Button openButton = new Button("Edit db");
		openButton.setOnAction(e -> openEditWindow());
		vbox.getChildren().add(openButton);
		
		updateUI(node);  
		
		Scene scene = new Scene(vbox, Color.LIGHTSKYBLUE);
		Image icon = new Image("icon.jpg");
		stage.setHeight(1000);
		stage.setWidth(1000);
		stage.getIcons().add(icon);
		stage.setTitle("Sark Bot");
		stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}