package swingy.Model.Characters;

import swingy.Model.Items.Armor;
import swingy.Model.Items.Helm;
import swingy.Model.Items.Weapon;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/*
 * Created by mabanciu on 5/30/18.
 */
@Entity
public class Hero extends Characters{
	public static int heroNR;
	@Transient
	private Weapon weapon;
	@Transient
	private Armor armor;
	@Transient
	private Helm helm;
	@Transient
	private Classes classes;
	@NotNull
	private String name;
	@Min(1)
	private int level;
	@Min(0)
	private int experience;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Min(0)
	private int posX;
	@Min(0)
	private int posY;
	@Min(0)
	private int hitPoints;
	@Min(0)
	private int classIndex;
	@Min(0)
	private int rarityHelm;
	@Min(0)
	private int rarityArmor;
	@Min(0)
	private int rarityWeapon;

	public Hero() {
		super();
	}

	public Classes getClasses() {
		return classes;
	}

	public int getClassIndex() {
		return classIndex;
	}

	public void setClassIndex(int classIndex) {
		this.classIndex = classIndex;
		this.classes = classesList.get(classIndex);
	}

	public Armor getArmor() {
		return armor;
	}

	public void setHelm(int rarityHelm) {
		this.helm = this.helmList.get(rarityHelm);
	}

	public Helm getHelm() {
		return helm;
	}

	public void setArmor(int rarityArmor) {
		this.armor = this.armorList.get(rarityArmor);
	}

	public Weapon getWeapon() {
		return weapon;
	}

	public void setWeapon(int rarityWeapon) {
		this.weapon = this.weaponList.get(rarityWeapon);
	}

	public int getHitPoints() {
		return hitPoints;
	}

	public void setHitPoints(int hitPoints) {
		this.hitPoints = hitPoints;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY (int posY) {
		this.posY = posY;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getLevel() {
		return level;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public int getExperience() {
		return experience;
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void regenerateItems() {
		this.weapon = weaponList.get(this.rarityWeapon);
		this.armor = armorList.get(this.rarityArmor);
		this.helm = helmList.get(this.rarityHelm);
	}

	public void updateItemsRarity() {
		this.rarityWeapon = this.weapon.getRarity();
		this.rarityHelm = this.helm.getRarity();
		this.rarityArmor = this.armor.getRarity();
	}

	public void setId(int id) {
		this.id = id;
	}
}
