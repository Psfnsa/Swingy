package swingy.View;

import swingy.Controller.ControlSwing;

import javax.validation.constraints.NotNull;
import java.util.Scanner;

/*
 * Created by mabanciu on 5/31/18.
 */
public class Console {
	private static Scanner sc = new Scanner(System.in);
	@NotNull
	private String line;
	private ControlSwing controller;

	public void mainMenu() {
		System.out.println("  Menu:  \n" +
				"1.Create Hero\n" +
				"2.Load Hero\n" +
				"3.Exit\n" +
				"4.Gui\n");
		line = sc.nextLine();
		switch (line.toLowerCase()) {
			case "1":
			case "create hero":
				controller.setIndex(1);
				nameSet();
				break;
			case "2":
			case "load hero":
				controller.setIndex(2);
				break;
			case"3":
			case"Exit":
				controller.setIndex(6);
				break;
			case"4":
			case"Gui":
				controller.setIndex(666);
				break;
			default:
				controller.setIndex(0);
				System.out.println("Please select an valid option.");
		}
	}

	public void loadHero(){
		controller.listHero();
		if ( controller.getId() != -1) {
			System.out.println("Please try option X or B. For Load press the number for the Hero you want\n" +
					"X.Delete\n" +
					"B.Back");
			line = sc.nextLine();
			switch (line.toLowerCase()) {
				case "x":
				case "delete":
					controller.setIndex(30);
					break;
				case "b":
				case "back":
					controller.setIndex(0);
					break;
				default:
					if (line.matches("\\d+") && Integer.parseInt(line) < controller.getHeroNr()) {
					controller.setHero(Integer.parseInt(line));
					controller.setIndex(40);
					} else {
						System.out.println("Incorrect input!");
					}
			}
		}
		else {
			System.out.println("We don't have any heroes saved at the moment\n\n" +
								"B.Back");
			line = sc.nextLine();
			switch (line.toLowerCase()) {
				case "b":
				case "back":
					controller.setIndex(0);
					break;
				default:
					controller.setIndex(0);
					System.out.println("Please try option 2. Thank you!");
			}
		}
	}

	public void chooseClass() {
		System.out.print("Choose your Class:\n" +
				" 1.Knight\n" +
				" 2.Barbarian\n" +
				" 3.Adventurer\n" +
				"4.Back\n");
		line = sc.nextLine();
		switch (line.toLowerCase()) {
			case "1":
			case "Knight":
				controller.setIndex(3);
				break;
			case "2":
			case "Barbarian":
				controller.setIndex(4);
				break;
			case "3":
			case "Adventurer":
				controller.setIndex(5);
				break;
			case "4":
			case "Back":
				controller.setIndex(-1);
				break;
			default:
				controller.setIndex(0);
				System.out.println("We don't have this type of class.");
		}
	}

	public void menuMap() {
		System.out.println("   1.North   \n" +
		"2.West  " + "3.East\n" +
		"   4.South  \n");
		line = sc.nextLine();
		switch (line.toLowerCase()) {
			case "1":
			case "w":
			case "north":
				controller.setIndex(7);
				break;
			case "2":
			case "a":
			case "west":
				controller.setIndex(8);
				break;
			case "3":
			case "d":
			case "east":
				controller.setIndex(9);
				break;
			case "4":
			case "s":
			case "south":
				controller.setIndex(10);
				break;
			case "menu":
				controller.setIndex(13);
				break;
			case "save":
				controller.setIndex(20);
				break;
			case "exit":
				controller.setIndex(0);
				break;
				default:
					controller.setIndex(-1);
					System.out.println("Try the shortcuts: 1, w, north / 2, a, west/ 3, d, east/ 4, s, south\nSave to save the hero\nMenu to go back to Main Menu\nExit to stop the game\n");
		}
	}

	private void nameSet() {
		System.out.print("Name your Hero: ");
		line = sc.nextLine();
		String name = line;
		controller.setName(name);
	}

	public void menuBattlePhase() {
		System.out.println("1.Fight (like a man, but be careful, if you die, you literally lose everything)\n" +
				"2.Run (and fight another day)\n");
		battlePhase();
	}

	private void battlePhase() {
		line = sc.nextLine();
		switch (line.toLowerCase()) {
			case "1":
			case "fight":
				controller.setIndex(11);
				break;
			case "2":
			case "run":
				controller.setIndex(12);
				break;
				default:
					System.out.println("So you wanna Run or Fight?");
					battlePhase();
		}
	}

	public void dropLootChoice() {
		line = sc.nextLine();
		switch (line.toLowerCase()) {
			case "1":
			case "yes":
			case "y":
				controller.setIndex(33);
				break;
			case "2":
			case "no":
			case "n":
				controller.setIndex(34);
				break;
				default:
					System.out.println("Try the option that are listed above. Thank you!");
		}
	}

	public void roundMenuBattle() {
		System.out.println("You want to see Next Round, or skip the entire Battle?Next/Skip");
		line = sc.nextLine();
		switch (line.toLowerCase()) {
			case "1":
			case "next":
			case "next round":
				controller.setIndex(35);
				break;
			case "2":
			case "skip":
				controller.setIndex(36);
				break;
			default:
				System.out.println("Try the option next or skip. Thank you!");
		}
	}

	public void setController(ControlSwing controller) {
		this.controller = controller;
	}
}