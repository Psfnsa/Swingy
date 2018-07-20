package swingy.Model.Items;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/*
 * Created by mabanciu on 5/30/18.
 */
public class Helm {
	@Min(0)
	private int hitPoints;
	@NotNull
	private String name;
	@Min(0)
	private int rarity;

	public Helm(String name, int hitPoints, int rarity) {
		this.hitPoints = hitPoints;
		this.name = name;
		this.rarity = rarity;
	}

	public int getHitPoints() {
		return hitPoints;
	}

	public String getName() {
		return name;
	}

	public int getRarity() {
		return rarity;
	}
}
