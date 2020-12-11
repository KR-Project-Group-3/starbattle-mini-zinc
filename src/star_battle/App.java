package star_battle;

import star_battle.controller.Controller;
import star_battle.view.Frame;

public class App {

	public static void main(String[] args) {

		Controller controller = new Controller();
		Frame frame = new Frame(controller);
	}
}
