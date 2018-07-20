package swingy.Model.Characters;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/*
 * Created by mabanciu on 5/30/18.
 */
public class Classes {
	@NotNull
	private String name;
	@Min(0)
	private int damage;
	@Min(0)
	private int hitPoints;
	@Min(0)
	private int defense;

	Classes(String name, int damage, int hitPoints, int defense) {
		this.name = name;
		this.damage = damage;
		this.hitPoints = hitPoints;
		this.defense = defense;
	}

	public int getDamage() {
		return damage;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHitPoints() {
		return hitPoints;
	}

	public int getDefense() {
		return defense;
	}

	public String getName() {
		return name;
	}
}
