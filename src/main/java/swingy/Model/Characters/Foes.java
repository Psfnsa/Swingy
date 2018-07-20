package swingy.Model.Characters;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/*
 * Created by mabanciu on 5/30/18.
 */
public class Foes {
	@Min(0)
	private int damage;
	@Min(0)
	private int hitPoints;
	@NotNull
	private String name;


	Foes(String name, int damage, int hitPoints) {
		setName(name);
		setDamage(damage);
		setHitPoints(hitPoints);
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	private void setDamage(int damage) {
		this.damage = damage;
	}

	public int getDamage() {
		return damage;
	}

	public void setHitPoints(int hitPoints) {
		this.hitPoints = hitPoints;
	}

	public int getHitPoints() {
		return hitPoints;
	}

}
