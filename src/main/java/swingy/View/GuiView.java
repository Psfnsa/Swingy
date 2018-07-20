package swingy.View;

import swingy.Controller.ControlSwing;
import swingy.Model.Characters.Characters;
import swingy.Model.Characters.Foes;
import swingy.Model.Characters.Hero;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/*
 * Created by mabanciu on 7/7/18.
 */
public class GuiView {
	private JPanel container;
	private JPanel mainMenu;
	private JPanel loadHero;
	private JButton btnLoadHero;
	private JButton btnBack;
	private JButton btnBackNH;
	private JButton btnCreateHero;
	private JButton btnExit;
	private JPanel createHero;
	private JButton btnBackCH;
	private JTextField fieldName;
	private JComboBox<String> boxClassSelect;
	private JButton btnCreate;
	private JPanel battlePhase;
	private JButton btnLoad;
	public JTextPane roundBox;
	private JButton btnNorth;
	private JButton btnWest;
	private JButton btnEast;
	private JButton btnSouth;
	private JButton btnExitBattlePhase;
	private JButton btnSaveBattlePhase;
	private JButton btnMenuBattlePhase;
	public JTextPane loadHeroesText;
	private JTextField numberLoadHero;
	private JButton btnLoadDelete;
	private JButton btnConsoleMode;
	private int nrClass;
	private ControlSwing controller = null;
	private Random r = new Random();
	private int size;
	Hero hero = new Hero();
	private final JFrame window = new JFrame("Swingy") {};

	public GuiView(boolean visible, final ControlSwing controlSwing) {
		controller = controlSwing;
		Characters theModel = new Characters();
		final Foes foe = theModel.getFoe(r.nextInt(theModel.getFoeNr()));

		btnLoadHero.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showLoadHero();
			}
		});

		btnCreateHero.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showCreateHero();
			}
		});

		btnExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		window.setPreferredSize(new Dimension(1280, 720));
		window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		window.add(this.container);
		window.pack();

		window.setVisible(visible);
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				((CardLayout) container.getLayout()).show(container, "Main Menu");
			}
		});
		btnCreateHero.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				((CardLayout) container.getLayout()).show(container, "Create Hero");
			}
		});
		btnExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				((CardLayout) container.getLayout()).show(container, "Exit");
			}
		});
		btnBackCH.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				((CardLayout) container.getLayout()).show(container, "Main Menu");
			}
		});
		boxClassSelect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				nrClass = boxClassSelect.getSelectedIndex();
			}
		});
		boxClassSelect.addItem("Knight");
		boxClassSelect.addItem("Barbarian");
		boxClassSelect.addItem("Adventurer");

		btnCreate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = fieldName.getText();
				if (name.length() < 2) {
					JOptionPane.showMessageDialog(null, "You need at least 2 characters in the Name Field.");
					((CardLayout) container.getLayout()).show(container, "Create Hero");
				} else {
					roundBox.setText("");
					((CardLayout) container.getLayout()).show(container, "Battle Phase");
				}
				controller.createHero(name, hero);
				if (nrClass == 0) {
					controller.hero.setClassIndex(0);
					controller.hero.setWeapon(1);
					controller.hero.setArmor(3);
					controller.hero.setHelm(1);
				} else if (nrClass == 1) {
					controller.hero.setClassIndex(1);
					controller.hero.setWeapon(3);
					controller.hero.setArmor(1);
					controller.hero.setHelm(1);
				} else if (nrClass == 2) {
					controller.hero.setClassIndex(2);
					controller.hero.setWeapon(2);
					controller.hero.setArmor(2);
					controller.hero.setHelm(2);
				}
				controller.createHero(fieldName.getText(), controller.hero);
				controller.firstCreateHeroStats();
				controller.insertHero();
				controller.setIndex(101);
				controller.initMap();
				fieldName.setText("");
			}
		});
		btnExitBattlePhase.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnSaveBattlePhase.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.updateHero();
				roundBox.setText(roundBox.getText() + "\n" + "You've made an Save!");
			}
		});
		btnMenuBattlePhase.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.updateHero();
				((CardLayout) container.getLayout()).show(container, "Main Menu");
			}
		});
		btnNorth.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.hero.setPosX(controller.hero.getPosX() - 1);//north
				roundBox.setText(roundBox.getText() + "\n" + "You move 1 box to North!");
				findFight(foe);
			}
		});
		btnWest.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.hero.setPosY(controller.hero.getPosY() - 1);//west
				roundBox.setText(roundBox.getText() + "\n" + "You move 1 box to the West!");
				findFight(foe);
			}
		});
		btnEast.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.hero.setPosY(controller.hero.getPosY() + 1);//east
				roundBox.setText(roundBox.getText() + "\n" + "You move 1 box to the East!");
				findFight(foe);
			}
		});
		btnSouth.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.hero.setPosX(controller.hero.getPosX() + 1);//south
				roundBox.setText(roundBox.getText() + "\n" + "You move 1 box to South!");
				findFight(foe);
			}
		});
		btnLoad.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String nr = numberLoadHero.getText();
				if (nr.matches("\\d+") && Integer.parseInt(nr) < controller.getHeroNr()) {
					controller.setHero(Integer.parseInt(nr));
					controller.listHeroGui();
					controller.hero.regenerateItems();
					controller.hero.setPosX(0);
					controller.hero.setPosY(0);
					controller.initMap();
					controller.setIndex(102);
					loadHeroesText.setText("");
					roundBox.setText("");
					((CardLayout)container.getLayout()).show(container, "Battle Phase");
				} else {
					JOptionPane.showMessageDialog(null, "Incorrect Input.");
					((CardLayout) container.getLayout()).show(container, "Load Hero");
				}
				numberLoadHero.setText("");
			}
		});
		btnLoadDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				size = controller.heroList.size();
				if (size > 0) {
					controller.obliviateHero(controller.heroList.get(size - 1).getId());
				} else {
					JOptionPane.showMessageDialog(null, "We don't have Heroes in Database");
					((CardLayout) container.getLayout()).show(container, "Load Hero");
				}
				loadHeroesText.setText("");
				controller.listHeroGui();
				((CardLayout)container.getLayout()).show(container, "Load Hero");
			}
		});
		btnConsoleMode.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.setControllerId(0);
			}
		});
	}

	private void showLoadHero() {
		loadHeroesText.setText("");
		controller.listHeroGui();
		((CardLayout)this.container.getLayout()).show(this.container, "Load Hero");
	}

	private void showCreateHero() {
		((CardLayout)this.container.getLayout()).show(this.container, "Create Hero");
	}

	public void setController(ControlSwing controller) {
		this.controller = controller;
	}

	private void findFight(Foes foe) {
		if (controller.hero.getPosY() == controller.size || controller.hero.getPosX() == controller.size || controller.hero.getPosY() < 0 || controller.hero.getPosX() < 0) {
			JOptionPane.showMessageDialog(null, "You just found the exit. You've Won!");
			controller.obliviateHero(controller.hero.getId());
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			((CardLayout) this.container.getLayout()).show(this.container, "Main Menu");
		} else if (controller.map[controller.hero.getPosX()][controller.hero.getPosY()] == 1 || controller.map[controller.hero.getPosX()][controller.hero.getPosY()] == 6) {
			roundBox.setText(roundBox.getText() + "\n" + "You've encountered an evil presence on your way.");
			roundBox.setText(roundBox.getText() + "\n" + "You need to choose if you wanna fight or try to run!");
			int reply = JOptionPane.showConfirmDialog(null, "Will you FIGHT?", "Choose", JOptionPane.YES_NO_OPTION);
			if (reply == JOptionPane.YES_OPTION) {
				roundBox.setText(roundBox.getText() + "\n" + "You choose to fight!");
				controller.calculatorBattleGui(foe);
			} else {
				roundBox.setText(roundBox.getText() + "\n" + "You try to run!");
				int oldPosX = controller.hero.getPosX();
				int oldPosY = controller.hero.getPosY();
				int nr = r.nextInt(100);
				if (nr > 50) {
					controller.mapFights[controller.hero.getPosX()][controller.hero.getPosY()] = 6;
					controller.hero.setPosX(oldPosX);
					controller.hero.setPosY(oldPosY);
					controller.map[controller.hero.getPosX()][controller.hero.getPosY()] = 3;
					roundBox.setText(roundBox.getText() + "\n" + "You've escaped safely. THIS TIME!");
				} else {
					roundBox.setText(roundBox.getText() + "\n" + "You didn't escape. The monster attacked you and you are forced to fight for your life!");
					controller.calculatorBattleGui(foe);
				}
			}
		}
	}

	public void setVisible(boolean state) {
		window.setVisible(state);
	}

}
