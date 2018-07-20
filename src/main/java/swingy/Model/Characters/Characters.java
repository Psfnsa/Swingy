package swingy.Model.Characters;

import swingy.Model.Items.Armor;
import swingy.Model.Items.Helm;
import swingy.Model.Items.Weapon;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/*
 * Created by mabanciu on 5/30/18.
 */
public class Characters {
	@NotNull
	List<Armor> armorList = new ArrayList<>();
	@NotNull
	List<Weapon> weaponList = new ArrayList<>();
	@NotNull
	List<Helm> helmList = new ArrayList<>();
	@NotNull
	List<Classes> classesList = new ArrayList<>();
	@NotNull
	private List<Foes> foesList = new ArrayList<>();

	public Characters() {
		generateClasses();
		generateWeapon();
		generateHelm();
		generateArmor();
		generateFoes();
	}

	private void generateArmor() {
		this.armorList.add(new Armor("Cloth Armor", 0, 0));
		this.armorList.add(new Armor("Leather Armor", 1, 1));
		this.armorList.add(new Armor("Copper Armor", 3, 2));
		this.armorList.add(new Armor("Bronze Armor", 5, 3));
		this.armorList.add(new Armor("Silver Armor", 7, 4));
		this.armorList.add(new Armor("Iron Armor", 9, 5));
		this.armorList.add(new Armor("Steel Armor", 11, 6));
		this.armorList.add(new Armor("Diamond Armor", 14, 7));
	}

	private void generateWeapon() {
		this.weaponList.add(new Weapon("Cloth Sword", 1, 0));
		this.weaponList.add(new Weapon("Leather Sword", 2, 1));
		this.weaponList.add(new Weapon("Copper Sword", 4, 2));
		this.weaponList.add(new Weapon("Bronze Sword", 6, 3));
		this.weaponList.add(new Weapon("Silver Sword", 8, 4));
		this.weaponList.add(new Weapon("Iron Sword", 10, 5));
		this.weaponList.add(new Weapon("Steel Sword", 12, 6));
		this.weaponList.add(new Weapon("Diamond Sword", 15, 7));
	}

	private void generateHelm() {
		this.helmList.add(new Helm("Cloth Helm", 2, 0));
		this.helmList.add(new Helm("Leather Helm", 5, 1));
		this.helmList.add(new Helm("Copper Helm", 8, 2));
		this.helmList.add(new Helm("Bronze Helm", 11, 3));
		this.helmList.add(new Helm("Silver Helm", 14, 4));
		this.helmList.add(new Helm("Iron Helm", 17, 5));
		this.helmList.add(new Helm("Steel Helm", 20, 6));
		this.helmList.add(new Helm("Diamond Helm", 25, 7));
	}

	private void generateClasses() {
		this.classesList.add(new Classes("Knight", 6, 45, 16));
		this.classesList.add(new Classes("Barbarian", 16, 31, 6));
		this.classesList.add(new Classes("Adventurer", 11, 40, 11));
	}

	private void generateFoes() {
		this.foesList.add(new Foes("Blue Jelly", 2, 152));
//		this.foesList.add(new Foes("Orange Jelly", 40, 143));
//		this.foesList.add(new Foes("Snake", 70, 210));
////		this.foesList.add(new Foes("Evil BIG Soldier With a BIG Sword", 122, 410));
//		this.foesList.add(new Foes("Evil Dad", 40, 80));
//		this.foesList.add(new Foes("Annonying Salesman", 1, 700));
//		this.foesList.add(new Foes("Princess", 40, 232));
//		this.foesList.add(new Foes("Image like you, but with different colors", 150, 240));
//		this.foesList.add(new Foes("Drop loot", 40, 340));
//		this.foesList.add(new Foes("FIRE", 100, 10));
//		this.foesList.add(new Foes("Stone", 2, 1200));
	}

	public Foes getFoe(int index) {
		return this.foesList.get(index);
	}

	public int getFoeNr() {
		return this.foesList.size();
	}

	public Classes getClasses() {
		Hero hero = new Hero();
		return this.classesList.get(hero.getClassIndex());
	}

	public List<Armor> getArmorList() {
		return armorList;
	}

	public List<Weapon> getWeaponList() {
		return weaponList;
	}

	public List<Helm> getHelmList() {
		return helmList;
	}
}