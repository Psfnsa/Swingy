package swingy.Controller;

		import swingy.Model.Characters.Characters;
		import swingy.Model.Characters.Foes;
		import swingy.Model.Characters.Hero;
		import swingy.View.Console;
		import swingy.View.GuiView;

		import javax.persistence.EntityManager;
		import javax.persistence.EntityManagerFactory;
		import javax.persistence.Persistence;
		import javax.swing.*;
		import javax.validation.constraints.Min;
		import javax.validation.constraints.NotNull;
		import javax.validation.constraints.Null;
		import java.util.*;

/*
 * Created by mabanciu on 6/1/18.
 */
public class ControlSwing {
	private Console theView = new Console();
	private GuiView guiView = null;
	public Hero hero;
	public int[][] map;
	@Min(0)
	public int size;
	@Min(-1)
	private int id = -1;
	@NotNull
	private String name;
	public int[][] mapFights;
	@NotNull
	public LinkedHashMap<Integer, Hero> heroList;
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("main-persistence-unit");
	private EntityManager em = emf.createEntityManager();
	private Characters theModel = new Characters();
	@Min(0)
	private int itemId;
	@Min(0)
	private int index;
	@Min(0)
	private int randItem;
	@Min(-1)
	private int controllerId = -1;
	@Min(0)
	private int checkerMenu = 1;
	private int controlView;

	private void drawMap() {
		initMap();
		int startPosX;
		int startPosY;
		int endPosX;
		int endPosY;
		int offset = 5;

		startPosX = (hero.getPosX() - offset) > 0 ? hero.getPosX() - offset : 0;
		startPosY = (hero.getPosY() - offset) > 0 ? hero.getPosY() - offset : 0;

		endPosX = (hero.getPosX() + offset + 1) < size ? hero.getPosX() + offset + 1 : size - 1;
		endPosY = (hero.getPosY() + offset + 1) < size ? hero.getPosY() + offset + 1 : size - 1;

		endPosX = startPosX <= offset ? endPosX : endPosX + offset + 1 - startPosX;
		endPosY = startPosY <= offset ? endPosY : endPosY + offset + 1 - startPosY;

		for (int i = startPosX; i < endPosX; i++) {
			for (int n = startPosY; n < endPosY; n++) {
				if (map[i][n] == 3) {
					System.out.print("(ツ)");
				} else if (mapFights[i][n] == 5) {
					System.out.print("﹏X﹏");
				} else if (mapFights[i][n] == 6) {
					System.out.print("﹏T﹏");
				} else if (map[i][n] == 0) {
					System.out.print("﹏.﹏");
				} else if (map[i][n] == 1) {
					System.out.print("﹏.﹏");
				}
			}
			System.out.println();
		}
		System.out.println();

		System.out.println(hero.getPosX() + ", " + hero.getPosY());
	}


	public void initMap() {
		/*if (getIndex() == 101) {
			guiView.roundBox.setText("Your JOURNEY starts now! On a new Map Level : " + hero.getLevel() + "\n");
		} else if (getIndex() == 102) {
			guiView.roundBox.setText("Welcome Back Adventurer! On a Map Level : " + hero.getLevel() + "\n");
		} else {
			System.out.println("Your JOURNEY starts now! On a Map Level : " + hero.getLevel() + "\n");
		}*/
		size = (hero.getLevel() - 1) * 5 + 10 - (hero.getLevel() % 2);
		hero.setPosX(size / 2);
		hero.setPosY(size / 2);
		mapFights = new int[size][size];
		map = new int[size][size];
		Random random = new Random();
		for (int i = 0; i < size; i++) {
			for (int n = 0; n < size; n++) {
				int nr = random.nextInt(100);
				if (nr > 35) {
					map[i][n] = 1;
				} else
					map[i][n] = 0;
			}
		}
		map[hero.getPosX()][hero.getPosY()] = 3;
	}

	public void createHero(String name, Hero hero) {
		this.hero = hero;
		this.hero.setName(name);
		hero.setLevel(1);
	}

	public ControlSwing(int type) {
		if (type == 0) {
			setControllerId(0);
		} else if (type == 1) {
			setControllerId(-1);
		}
		theView.setController(this);
	}

	public void loopMenuBase() {
		while (true) {
			if (getControllerId() == 0) {
				setIndex(102);
				setControllerId(1);
				setCheckerMenu(1);
				if(guiView == null)
					guiView = new GuiView(false, this);
				else
					guiView.setVisible(false);
			} else if (getControllerId() == -1) {
				setIndex(101);
				setControllerId(1);
				setCheckerMenu(-1);
				if(guiView == null)
					guiView = new GuiView(true, this);
				else
					guiView.setVisible(true);
			}
			if (getIndex() == 102) {
				while (true) {
					if (getIndex() == 666) {
						setControlView(1);
						setControllerId(-1);
						setCheckerMenu(-1);
						break;
					}
					while (true) {
						theView.mainMenu();
						if (1 == getIndex()) {
							hero = new Hero();
							if (name.trim().length() < 2) {
								System.out.println("You can do better than that!! Name has to be at least 2 characters! Please.");
								continue;
							}
							createHero(name, hero);
							theView.chooseClass();
							if (getIndex() == 3) {
								hero.setClassIndex(0);
								hero.setWeapon(1);
								hero.setArmor(3);
								hero.setHelm(1);
							}
							if (getIndex() == 4) {
								hero.setClassIndex(1);
								hero.setWeapon(3);
								hero.setArmor(1);
								hero.setHelm(1);
							}
							if (getIndex() == 5) {
								hero.setClassIndex(2);
								hero.setWeapon(2);
								hero.setArmor(2);
								hero.setHelm(2);
							}
							while (true) {
								if (getIndex() >= 3 && getIndex() <= 5) {
									insertHero();
									break;
								} else if (getIndex() == -1) {
									break;
								} else {
									theView.chooseClass();
								}
							}
						} else if (getIndex() == 2) {
							while (true) {
								theView.loadHero();
								if (getIndex() == 30) {
									int size = heroList.size();
									if (size > 0) {
										obliviateHero(heroList.get(size - 1).getId());
									}
								}
								if (getIndex() == 40) {
									hero.regenerateItems();
									hero.setPosX(0);
									hero.setPosY(0);
									break;
								}
								if (getIndex() == 0)
									break;
							}
						} else if (getIndex() == 6) {
							System.out.println("Thank you for trying our game in the super very early alpha phase\n\n");
							System.exit(0);
						} else if (getIndex() == 666) {
							break;
						} else {
							System.out.println("I don't have that option!!!");
						}
						System.out.println();
						if (getIndex() > 0) {
							break;
						}
					}
					if (getIndex() == 666) {
						continue;
					}
					while (getCheckerMenu() == 1) {
						int fightNr = -1;
						int runNr = -1;
						while (getCheckerMenu() == 1) {
							drawMap();
							int oldPosX = hero.getPosX();
							int oldPosY = hero.getPosY();
							theView.menuMap();
							if (getIndex() == -1) {
								continue;
							} else if (getIndex() == 20) {
								updateHero();
							} else if (getIndex() == 13) {
								updateHero();
								break;
							} else if (getIndex() == 0) {
								System.exit(0);
							} else if (getIndex() == 7) {
								hero.setPosX(hero.getPosX() - 1);
							} else if (getIndex() == 8) {
								hero.setPosY(hero.getPosY() - 1);
							} else if (getIndex() == 9) {
								hero.setPosY(hero.getPosY() + 1);
							} else if (getIndex() == 10) {
								hero.setPosX(hero.getPosX() + 1);
							}
							if (hero.getPosY() == size || hero.getPosX() == size || hero.getPosY() < 0 || hero.getPosX() < 0) {
								map[oldPosX][oldPosY] = mapFights[oldPosX][oldPosY];
								if (fightNr == 1) {
									mapFights[oldPosX][oldPosY] = 5;
								}
								drawMap();
								System.out.println("\n\n             Victory\n\n");
								obliviateHero(this.hero.getId());
								System.exit(0);
							}
							map[oldPosX][oldPosY] = 0;
							if (fightNr == 1 && runNr == -1) {
								fightNr = -1;
								mapFights[oldPosX][oldPosY] = 5;
							}
							if (map[hero.getPosX()][hero.getPosY()] == 1 || map[hero.getPosX()][hero.getPosY()] == 6) {
								fightNr = 1;
								runNr = -1;
								Random r = new Random();
								Foes foe = theModel.getFoe(r.nextInt(theModel.getFoeNr()));
								System.out.println("You encounter an evil presence it looks like a " + foe.getName() + " with HP: " + foe.getHitPoints() +
										" and DMG: " + foe.getDamage() + "\n" + "You have 2 choices:");
								theView.menuBattlePhase();
								if (getIndex() == 11) {
									System.out.println("Hero name: " + hero.getName() + ", Class: " + hero.getClasses().getName() + ", HP: " + hero.getClasses().getHitPoints() + ", DMG: " + hero.getClasses().getDamage() + ", Defense: " + hero.getClasses().getDefense() + " Exp: " + hero.getExperience() + "\n" + "VS\n" + foe.getName() + " with HP: " + foe.getHitPoints() + " and DMG: " + foe.getDamage() + "\n");
									calculatorBattle(foe);
									if (getIndex() == 17) {
										break;
									}
								}
								if (getIndex() == 12) {
									int nr = r.nextInt(100);
									if (nr > 50) {
										System.out.println("You lucky bastard, you outrun the monster and escaped safely to your old position.");
										runNr = 1;
										mapFights[hero.getPosX()][hero.getPosY()] = 6;
										hero.setPosX(oldPosX);
										hero.setPosY(oldPosY);
										map[hero.getPosX()][hero.getPosY()] = 3;
										continue;
									} else {
										System.out.println("You weren't fast enough, so you got caught and now you are forced to fight for your life!");
										calculatorBattle(foe);
										if (getIndex() == 17) {
											break;
										}
									}
								}
							}
							if (mapFights[hero.getPosX()][hero.getPosY()] == 5) {
								fightNr = 1;
							}
							map[hero.getPosX()][hero.getPosY()] = 3;
						}
						//go back to the big while so that you can go back to Main Menu when you write menu in battlephase
						if (getIndex() == 13) {
							break;
						}
						if (getIndex() == 13) {
							setIndex(1);
							break;
						}
					}
				}
			} else {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void calculatorBattle(Foes foe) {
		setIndex(35);
		hero.setHitPoints((int) (hero.getClasses().getHitPoints() + (hero.getLevel() * 0.2)));
		int countBattle = 1;
		int oldFoeHitPoints = (int) (foe.getHitPoints() + (hero.getLevel() * 0.2));
		int defenseItems = (int) ((hero.getLevel() * 1.5) + hero.getHelm().getHitPoints() + hero.getArmor().getDefense());
		int totalDamage = hero.getLevel() + hero.getWeapon().getDamage() + hero.getClasses().getDamage() + hero.getLevel();
		while (hero.getHitPoints() > 0 && foe.getHitPoints() > 0) {
			if (countBattle > 1 && getIndex() != 36) {
				theView.roundMenuBattle();
			}
			int damage = 0;
			if (foe.getDamage() > defenseItems) {
				damage = foe.getDamage() + hero.getLevel() - defenseItems;
				defenseItems = 0;
			} else {
				defenseItems -= foe.getDamage() + hero.getLevel();
			}
			hero.setHitPoints((int) (hero.getHitPoints() + (hero.getLevel() * 0.2) - damage));
			foe.setHitPoints((int) (foe.getHitPoints() + (hero.getLevel() * 0.2) - totalDamage));
			if (getIndex() != 36) {
				System.out.println("You get hit this round" + countBattle + " for: " + foe.getDamage() + ", you have HP left: " + hero.getHitPoints() + ", Armor left: " + defenseItems);
				System.out.println("You hit this round" + countBattle + " for: " + totalDamage + ", the enemy has HP left: " + foe.getHitPoints());
			}
			countBattle++;
		}
		if (foe.getHitPoints() <= 0) {
			hero.setExperience((25 * (countBattle - 1)) + hero.getExperience());
			System.out.println("Your current Exp: " + hero.getExperience() + "/" + ((int) (hero.getLevel() * 1000 + Math.pow((hero.getLevel() - 1), 2) * 450)));
			foe.setHitPoints(oldFoeHitPoints);
			dropLoot();
			if (hero.getExperience() >= ((int) (hero.getLevel() * 1000 + Math.pow((hero.getLevel() - 1), 2) * 450))) {
				hero.setExperience(hero.getExperience() - ((int) (hero.getLevel() * 1000 + Math.pow((hero.getLevel() - 1), 2) * 450)));
				hero.setLevel(hero.getLevel() + 1);
				System.out.println("Congratulation you got a new level: " + hero.getLevel());
				setIndex(17);
			}
		} else if (hero.getHitPoints() < 0) {
			System.out.println("\n\n             You Lose  \n\n");
			obliviateHero(this.hero.getId());
			System.exit(0);
		}
	}

	public void calculatorBattleGui(Foes foe) {
		setIndex(35);
		hero.setHitPoints((int) (hero.getClasses().getHitPoints() + (hero.getLevel() * 0.2)));
		int countBattle = 1;
		int oldFoeHitPoints = (int) (foe.getHitPoints() + (hero.getLevel() * 0.2));
		int defenseItems = (int) ((hero.getLevel() * 1.5) + hero.getHelm().getHitPoints() + hero.getArmor().getDefense());
		int totalDamage = hero.getWeapon().getDamage() + hero.getClasses().getDamage() + hero.getLevel();
		while (hero.getHitPoints() > 0 && foe.getHitPoints() > 0) {
			int damage = 0;
			if (foe.getDamage() > defenseItems) {
				damage = foe.getDamage() + hero.getLevel() - defenseItems;
				defenseItems = 0;
			} else {
				defenseItems -= foe.getDamage() + hero.getLevel();
			}
			hero.setHitPoints((int) (hero.getHitPoints() + (hero.getLevel() * 0.2) - damage));
			foe.setHitPoints((int) (foe.getHitPoints() + (hero.getLevel() * 0.2) - totalDamage));
			if (getIndex() != 36 && countBattle > 1) {
				int reply = JOptionPane.showConfirmDialog(null, "You wanna see next Round? If you press NO, you will skip all rounds!", "Choose", JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {
					guiView.roundBox.setText(guiView.roundBox.getText() + "\n" + "You get hit this round" + countBattle + " for: " + foe.getDamage() + ", you have HP left: " + hero.getHitPoints() + ", Armor left: " + defenseItems);
					guiView.roundBox.setText(guiView.roundBox.getText() + "\n" + "You hit this round" + countBattle + " for: " + totalDamage + ", the enemy has HP left: " + foe.getHitPoints());
				} else if (reply == JOptionPane.NO_OPTION) {
					setIndex(36);
				}
			}
			countBattle++;
		}
		guiView.roundBox.setText(guiView.roundBox.getText() + "\n" + "You finish the fight!");
		if (foe.getHitPoints() <= 0) {
			hero.setExperience((25 * (countBattle - 1)) + hero.getExperience());
			guiView.roundBox.setText(guiView.roundBox.getText() + "\n" + "Your current Exp: " + hero.getExperience() + "/" + ((int) (hero.getLevel() * 1000 + Math.pow((hero.getLevel() - 1), 2) * 450)));
			foe.setHitPoints(oldFoeHitPoints);
			dropLootGui();
			if (hero.getExperience() >= ((int) (hero.getLevel() * 1000 + Math.pow((hero.getLevel() - 1), 2) * 450))) {
				hero.setExperience(hero.getExperience() - ((int) (hero.getLevel() * 1000 + Math.pow((hero.getLevel() - 1), 2) * 450)));
				hero.setLevel(hero.getLevel() + 1);
				guiView.roundBox.setText(guiView.roundBox.getText() + "\n" + "Congratulation you got a new level: " + hero.getLevel());
				initMap();
			}
		} else {
			guiView.roundBox.setText(guiView.roundBox.getText() + "\n" + "\n\n       You Lose  \n\n");
			obliviateHero(this.hero.getId());
			JOptionPane.showMessageDialog(null, "You just found Death. You Lose!");
			try {
				Thread.sleep(2000);
				System.exit(33);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void dropLootGui() {
		Random random = new Random();
		int nr = random.nextInt(100);
		randItem = random.nextInt(3);
		if (nr > 70) {
			guiView.roundBox.setText(guiView.roundBox.getText() + "\n" + "You've found an ");
			switch (randItem) {
				case 0: itemId = random.nextInt(theModel.getWeaponList().size()); break;
				case 1: itemId = random.nextInt(theModel.getArmorList().size()); break;
				case 2: itemId = random.nextInt(theModel.getHelmList().size()); break;
			}
			if (randItem == 0) {
				theModel.getWeaponList().get(itemId);
				guiView.roundBox.setText(guiView.roundBox.getText() + "\n" + theModel.getWeaponList().get(itemId).getName() + ".");
			} else if (randItem == 1) {
				theModel.getArmorList().get(itemId);
				guiView.roundBox.setText(guiView.roundBox.getText() + "\n" + theModel.getArmorList().get(itemId).getName() + ".");
			} else if (randItem == 2) {
				theModel.getHelmList().get(itemId);
				guiView.roundBox.setText(guiView.roundBox.getText() + "\n" + theModel.getHelmList().get(itemId).getName() + ".");
			}
			guiView.roundBox.setText(guiView.roundBox.getText() + "\n" + "Your current equipment is: " + hero.getWeapon().getName() + ", " + hero.getHelm().getName() + ", " + hero.getArmor().getName());
			int replay = JOptionPane.showConfirmDialog(null, "Do you pickup this item?", "Choose Item", JOptionPane.YES_NO_OPTION);
			if (replay == JOptionPane.YES_OPTION) {
				if (randItem == 0) {
					hero.setWeapon(itemId);
				} else if (randItem == 1) {
					hero.setArmor(itemId);
				} else if (randItem == 2) {
					hero.setHelm(itemId);
				}
				updateHero();
				guiView.roundBox.setText(guiView.roundBox.getText() + "\n" + "\n  Your new look\n\n"
						+ "Weapon: " + hero.getWeapon().getName() + ", Helm: " + hero.getHelm().getName() + ", Armor: " + hero.getArmor().getName());
			} else {
				guiView.roundBox.setText(guiView.roundBox.getText() + "\n" + "You've kept your item");
			}
		} else {
			guiView.roundBox.setText(guiView.roundBox.getText() + "\n" + "Better luck next time, you didn't find anything now! :(");
		}
	}

	private void dropLoot() {
		Random random = new Random();
		int nr = random.nextInt(100);
		randItem = random.nextInt(3);
		if (nr > 70) {
			System.out.print("You've found an ");
			switch (randItem) {
				case 0: itemId = random.nextInt(theModel.getWeaponList().size()); break;
				case 1: itemId = random.nextInt(theModel.getArmorList().size()); break;
				case 2: itemId = random.nextInt(theModel.getHelmList().size()); break;
			}
			if (randItem == 0) {
				theModel.getWeaponList().get(itemId);
				System.out.println(theModel.getWeaponList().get(itemId).getName() + ".");
			} else if (randItem == 1) {
				theModel.getArmorList().get(itemId);
				System.out.println(theModel.getArmorList().get(itemId).getName() + ".");
			} else if (randItem == 2) {
				theModel.getHelmList().get(itemId);
				System.out.println(theModel.getHelmList().get(itemId).getName() + ".");
			}
			System.out.println("Your current equipment is: " + hero.getWeapon().getName() + ", " + hero.getHelm().getName() + ", " + hero.getArmor().getName());
			System.out.println("Do you take this item, or keep your current item? Yes/No");
			/////tre sa faci alegerea playerului in the view.
			theView.dropLootChoice();
			if (getIndex() == 33) {
				if (randItem == 0) {
					hero.setWeapon(itemId);
				} else if (randItem == 1) {
					hero.setArmor(itemId);
				} else if (randItem == 2) {
					hero.setHelm(itemId);
				}
				updateHero();
				System.out.println("\n  Your new look\n\n"
						+ "Weapon: " + hero.getWeapon().getName() + ", Helm: " + hero.getHelm().getName() + ", Armor: " + hero.getArmor().getName());
			} else if (getIndex() == 34) {
				System.out.println("You've kept your items");
			}
		} else {
			System.out.println("Better luck next time, you didn't find anything now! :(");
		}
	}

	public void insertHero() {
		em.getTransaction().begin();
		em.persist(hero);//or this.here
		em.getTransaction().commit();
	}

	public void updateHero() {
		hero.updateItemsRarity();
		em.getTransaction().begin();
		em.getTransaction().commit();
	}

	private LinkedHashMap<Integer, Hero> loadHeroes() {
		LinkedHashMap<Integer, Hero> herolist = new LinkedHashMap<>();
		int i = 0;
		List<Hero> list = em.createQuery("Select e from Hero e", Hero.class).getResultList();
		for (Hero el : list) {
			herolist.put(i++, el);
		}
		Hero.heroNR = i;
		return herolist;
	}

	public void obliviateHero(int id) {
		Hero toDel = em.find(Hero.class, id);
		em.remove(toDel);
		em.getTransaction().begin();
		em.getTransaction().commit();
	}

	public void listHero() {
		heroList = loadHeroes();
		if (heroList.size() == 0) {
			setId(-1);
			return;
		}
		for (Map.Entry<Integer, Hero> entry : heroList.entrySet()) {
			Hero currentHero = entry.getValue();
			currentHero.setClassIndex(currentHero.getClassIndex());
			setId(currentHero.getId());
			System.out.println(entry.getKey() +  " ,  Hero Name: " + currentHero.getName() + ", Level: " + currentHero.getLevel() + ", Class: " + currentHero.getClasses().getName());
		}
	}

	public void listHeroGui() {
		heroList = loadHeroes();
		for (Map.Entry<Integer, Hero> entry : heroList.entrySet()) {
			Hero currentHero = entry.getValue();
			currentHero.setClassIndex(currentHero.getClassIndex());
			setId(currentHero.getId());
			guiView.loadHeroesText.setText(guiView.loadHeroesText.getText() + "\n" + entry.getKey() +  " ,  Hero Name: " + currentHero.getName() + ", Level: " + currentHero.getLevel() + ", Class: " + currentHero.getClasses().getName());
		}
	}

	public void setName(String name) {
		this.name = name;
	}

	private void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setHero(int index) {
		this.hero = this.heroList.get(index);
	}

	public int getHeroNr() {
		return heroList.size();
	}

	public void setIndex(int index) {
		this.index = index;
	}

	private int getIndex() {
		return index;
	}

	public void firstCreateHeroStats() {
		if (getIndex() == 3) {
			hero.setClassIndex(0);
			hero.setWeapon(1);
			hero.setArmor(3);
			hero.setHelm(1);
		}
		if (getIndex() == 4) {
			hero.setClassIndex(1);
			hero.setWeapon(3);
			hero.setArmor(1);
			hero.setHelm(1);
		}
		if (getIndex() == 5) {
			hero.setClassIndex(2);
			hero.setWeapon(2);
			hero.setArmor(2);
			hero.setHelm(2);
		}
	}

	public void setControllerId(int controllerId) {
		this.controllerId = controllerId;
	}

	public int getControllerId() {
		return controllerId;
	}

	private int getCheckerMenu() {
		return checkerMenu;
	}

	private void setCheckerMenu(int checkerMenu) {
		this.checkerMenu = checkerMenu;
	}

	public void setControlView(int controlView) {
		this.controlView = controlView;
	}

	public int getControlView() {
		return controlView;
	}
}
