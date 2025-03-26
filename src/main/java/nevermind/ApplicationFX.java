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
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class ApplicationFX extends Application {

	@Override
    public void start(Stage stage) throws Exception {
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
		
		DialogNode node = mainNode;
		Boolean isCurrentNodeLast = node.isLast;
		ArrayList<Answer> answers;
		
		HBox hbox = new HBox();
		VBox vbox = new VBox();
        hbox.setSpacing(100);
		for (int i = 0; i < mainNode.GetAnswers().size(); i++) {
            String labelText = mainNode.GetAnswers().get(i).text;
            Button yourTake = new Button(labelText);
            String iString = Integer.toString(i);
            yourTake.setOnAction(event -> System.out.println("Кнопка" + iString + " нажата!"));
            hbox.getChildren().add(yourTake);
		}
		Label botTake = new Label(mainNode.GetText());
		vbox.getChildren().add(botTake);
		vbox.getChildren().add(hbox);
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