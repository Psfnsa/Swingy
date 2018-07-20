package swingy;

import swingy.Controller.ControlSwing;
import swingy.Model.Characters.Characters;
import swingy.View.*;

import java.util.Objects;

/*
 * Created by mabanciu on 5/28/18.
 */
public class Main {
	public static void main(String[] args) {
		ControlSwing theController = null;
		Console theView = new Console();
		if (args.length == 0 || Objects.equals(args[0], "gui")) {
			theController = new ControlSwing(1);
		} else if (Objects.equals(args[0], "console")) {
			theController = new ControlSwing(0);
		 }
		theController.loopMenuBase();
	}
}
