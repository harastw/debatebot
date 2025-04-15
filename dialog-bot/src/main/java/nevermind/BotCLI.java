package nevermind;

import java.util.ArrayList;
import java.util.Scanner;

public class BotCLI {
	public void start() {
		DialogNode mainNode = new DialogNode("Do you have a question?");
		
		DialogNode socialismCounter = new DialogNode("nazi is not marxoid");
		Answer argimentFromSocialism = new Answer("Nazi is socialist!", socialismCounter);
		mainNode.addAnswer(argimentFromSocialism);
		
		DialogNode leftistCounter = new DialogNode("why?");
		Answer argumentFromLeftist = new Answer("but leftist btw", leftistCounter);
		socialismCounter.addAnswer(argumentFromLeftist);
		
		DialogNode marketCounter = new DialogNode("check germany modern market regulation and think");
		Answer argumentFromMarket = new Answer("no free market btw", marketCounter);
		socialismCounter.addAnswer(argumentFromMarket);
		
		DialogNode node = mainNode;
		ArrayList<Answer> answers;
		while (true) {
			answers = node.getAnswers();
		    System.out.println(node.getText());
		    for (int i = 0; i < answers.size(); i++) {
		    	Answer localAnswer = answers.get(i);
		        System.out.println(localAnswer.text);
		    }
		    Scanner scanner = new Scanner(System.in);
		    int index = scanner.nextInt();
		    node = answers.get(index - 1).node;
		}
	}
}
