package nevermind;

import java.util.ArrayList;
import java.util.Scanner;

public class Bot {
	public void start() {
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
		while (!isCurrentNodeLast) {
			answers = node.GetAnswers();
		    System.out.println(node.GetText());
		    for (int i = 0; i < answers.size(); i++) {
		    	Answer localAnswer = answers.get(i);
		        System.out.println(localAnswer.text);
		    }
		    Scanner scanner = new Scanner(System.in);
		    int index = scanner.nextInt();
		    node = answers.get(index - 1).node;
		    isCurrentNodeLast = node.IsLast();
		}
	}
}
