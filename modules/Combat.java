package old80house.modules;

import java.util.Random;
import java.util.Scanner;
import old80house.Monster;
import old80house.Player;
import old80house.items.ProtonGun;
import old80house.items.ScrewDriver;
import old80house.items.Syringe;

public class Combat {

    Scanner scan = new Scanner(System.in); // Burde genbruges fra det object vi allerede har lavet i controller? Refactor here
    Random r = new Random();
    ProtonGun pg = new ProtonGun();        // sæt items til static, eller send object med ind i combat, dumt at new her.
    ScrewDriver sd = new ScrewDriver();
    MediaPlayer mp;

    private boolean fightsOn = true;
    private boolean playerStarts;
    private String input;
    private Player p;
    private Monster m;

    private int monsterHealth;
    private int monsterDamage;
    private int monsterXp;
    private int syringeCount = 0;
    private int playerHealth;
    private int playerDamage;

    private boolean gun, screwdriver;

    // Constructor
    public Combat(Player x, Monster y, MediaPlayer mp) {
        this.p = x;
        this.m = y;
        this.mp = mp;
    }

    public void Fight() {
        playerHealth = p.getHealth();
        monsterHealth = m.getHp();

        if (p.getHealth() > 0) {

            playerStarts = r.nextBoolean(); // Randomize who starts to attack

            // The lucky one starts the fight
            if (playerStarts) {
                System.out.println("You are lucky and attacks first!");
                playerAttacks();
            } else {
                System.out.println("The monster attacks first!");

                monsterAttacks();
            }
            if (!playerStarts) {
                playerAttacks();
            }
            while (fightsOn && p.getHealth() > 0 && monsterHealth > 0) {

                // Check on who started, the opponent get its turn right after
                monsterAttacks();
                playerAttacks();
            }

            if (p.getHealth() > 0) {
                System.out.println("Fights over!\nYou gained " + m.getXp() + " XP and looted " + m.getGooey() + " gooey");
                p.setXp(m.getXp());
                p.lvlCalc();
                p.setGooey(m.getGooey());
            }
        }

        fightsOn = false;

    } // fight end

    public void playerAttacks() {
        if (p.getHealth() > 0) {

            System.out.println("\n**YOU ATTACK**");
            System.out.println("Monster health is: " + monsterHealth);
            System.out.println("Monster XP is " + monsterXp);
            System.out.println("Your health is: " + p.getHealth());

            if (p.lookForItem("Proton gun").equals("Proton gun")) {
                gun = true;
            }
            if (p.lookForItem("screwdriver").equals("screwdriver")) { //checking what weapons player has in player inventory
                screwdriver = true;
            }
            for (int i = 0; i < p.getSize(); i++) {
                if (p.lookForItem2(i).equals("syringe")) {
                    syringeCount++;
                }

            }
            System.out.println("\nWeapon of choice?\n" + "[1] Proton gun");
            if (screwdriver) {
                System.out.println("[2] Screwdriver");
            }
            
            
            if (syringeCount > 0) {
                System.out.println("\nMedic?");
                System.out.println("[3] to use a Syringe upon your self to get health!");
            }

            while (true) {
                input = scan.next();
                switch (input) {
                    case "1":
                        if (gun) {
                            playerDamage = pg.use();

                            System.out.println("You chose the Proton gun, that has damage: " + playerDamage);
                            monsterHealth -= playerDamage + p.getDamage();   //damage from gun + damage from player
                            return;
                        } else {
                            System.out.println("You do not have this weapon");
                        }

                    case "2":
                        if (screwdriver) {
                            playerDamage = pg.use();
                            System.out.println("You chose the screwdriver, that has damage: " + playerDamage);
                            monsterHealth -= playerDamage;
                            return;
                        } else {
                            System.out.println("You do not have this weapon");
                        }

                    case "3":
                        if (p.getInv("syringe").equals("syringe")) {
                            Syringe syringe = new Syringe();
                            int temp = syringe.use();
                            if (temp > 0) {
                                mp.PlaySound(mp.soundMedic, false);
                            } else {
                                mp.PlaySound(mp.soundPain, false);
                            }
                            p.setHealth(p.getHealth() + temp);

                            System.out.println(syringe.useText());
                        } else {
                            System.out.println("You got no syringes!");
                        }

                    default:
                        if (input.equals("3")) {
                            System.out.println("Choose a weapon!");
                        } else {

                            System.out.println("Not a command here.");
                        }
                }
            }

        } else {
            System.out.println("You died!");
            fightsOn = false;

        }

    }

    public void monsterAttacks() {
        if (monsterHealth > 0) {
            monsterDamage = m.getDmg();
            monsterXp = m.getXp();

            System.out.println("\n**MONSTER ATTACKS**");
            System.out.println("Players health is: " + p.getHealth());
            System.out.println("Monsters attack damage is: " + monsterDamage);

            p.setHealth(p.getHealth() - monsterDamage);
        } else {
            System.out.println("Monster died");
            fightsOn = false;
        }
    }

} // class end
