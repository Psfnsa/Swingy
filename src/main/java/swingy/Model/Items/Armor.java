package swingy.Model.Items;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/*
 * Created by mabanciu on 5/30/18.
 */
public class Armor {
	@Min(0)
	private int defense;
	@NotNull
	private String name;
	@Min(0)
	private int rarity;

	public Armor(String name, int defense, int rarity) {
		this.defense = defense;
		this.name = name;
		this.rarity = rarity;
	}

	public int getDefense() {
		return defense;
	}

	public String getName() {
		return name;
	}

	public int getRarity() {
		return rarity;
	}
}
