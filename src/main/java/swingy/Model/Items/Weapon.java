package swingy.Model.Items;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/*
 * Created by mabanciu on 5/30/18.
 */
public class Weapon {
	@Min(0)
	private int damage;
	@NotNull
	private String name;
	@Min(0)
	private int rarity;

	public Weapon(String name, int damage, int rarity) {
		this.damage = damage;
		this.name = name;
		this.rarity = rarity;
	}
	public int getDamage() {
		return damage;
	}

	public int getRarity() {
		return rarity;
	}

	public String getName() {
		return name;
	}
}
