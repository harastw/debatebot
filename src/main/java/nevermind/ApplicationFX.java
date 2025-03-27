package nevermind;

import nevermind.DialogNode;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.util.ArrayList;

public class ApplicationFX extends Application {
	private VBox vbox;
	
	private void updateUI(DialogNode node) {
		HBox hbox = new HBox(50);

		ArrayList<Answer> answers;
		answers = node.GetAnswers();
		for (int i = 0; i < node.GetAnswers().size(); i++) {
			Answer currentAnswer = answers.get(i);
            Button yourTake = new Button(currentAnswer.text);
            yourTake.setOnAction(event -> {
            	disableOldButtons(hbox);
            	updateUI(currentAnswer.node); 
            });
            hbox.getChildren().add(yourTake);
		}
		Label botTake = new Label(node.GetText());
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
		
		DialogNode mainNode = new DialogNode("Do you have a question?", false);
		
		DialogNode socialismCounter = new DialogNode("nazi is not marxoid", false);
		Answer argimentFromSocialism = new Answer("Nazi is socialist!", socialismCounter);
		mainNode.AddAnswer(argimentFromSocialism);
		
		DialogNode leftistCounter = new DialogNode("why?", false);
		Answer argumentFromLeftist = new Answer("but leftist btw", leftistCounter);
		socialismCounter.AddAnswer(argumentFromLeftist);
		
		DialogNode marketCounter = new DialogNode("check germany modern market regulation and think", false);
		Answer argumentFromMarket = new Answer("no free market btw", marketCounter);
		socialismCounter.AddAnswer(argumentFromMarket);
		
		
        updateUI(mainNode);
        
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