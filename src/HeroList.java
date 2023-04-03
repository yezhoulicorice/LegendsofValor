import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class HeroList {
    Hero a_hero;
    private ArrayList<Hero> allHeros;
    private final ArrayList<Hero> heroParty;

    private ArrayList<Integer> Nums=new ArrayList<Integer>();
    private int partynum=3;

    // read .txt to init the Heroes list
    public HeroList() throws Exception {
        allHeros=new ArrayList<Hero>();
        heroParty=new ArrayList<Hero>();
        ReadTxt r =new ReadTxt();
        initPaladins(r.readTxt("./src/Legends_Monsters_and_Heroes/Paladins.txt"));
        initWarriors(r.readTxt("./src/Legends_Monsters_and_Heroes/Warriors.txt"));
        initSorcerers(r.readTxt("./src/Legends_Monsters_and_Heroes/Sorcerers.txt"));
    }
    private void initWarriors(ArrayList<String[]> w) throws Exception {
        for (int i = 1; i < w.size(); i++) {
            a_hero=new Hero(w.get(i)[0],
                    Integer.parseInt(w.get(i)[1]),
                    Integer.parseInt(w.get(i)[2]),
                    Integer.parseInt(w.get(i)[3]),
                    Integer.parseInt(w.get(i)[4]),
                    Integer.parseInt(w.get(i)[5]),
                    Integer.parseInt(w.get(i)[6]),
                    HeroType.Warrior,
                    100*Integer.parseInt(w.get(i)[6]),
                    0,
                    Integer.parseInt(w.get(i)[1]),
                    new Inventory(new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),new ArrayList<>())
                    ,new Weapon("Hand",0,0,0,0)
                    ,new Armory("Body",0,0,0)
                    ,0
                    ,0
                    ,0
            );
            this.allHeros.add(a_hero);
        }

    }

    private void initPaladins(ArrayList<String[]> p) throws Exception {
        for (int i = 1; i < p.size(); i++) {
            a_hero = new Hero(p.get(i)[0],
                    Integer.parseInt(p.get(i)[1]),
                    Integer.parseInt(p.get(i)[2]),
                    Integer.parseInt(p.get(i)[3]),
                    Integer.parseInt(p.get(i)[4]),
                    Integer.parseInt(p.get(i)[5]),
                    Integer.parseInt(p.get(i)[6]),
                    HeroType.Paladin,
                    100 * Integer.parseInt(p.get(i)[6]),
                    0,
                    Integer.parseInt(p.get(i)[1]),
                    new Inventory(new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>())
                    ,new Weapon("Hand",0,0,0,0)
                    ,new Armory("Body",0,0,0)
                    , 0
                    , 0
                    , 0
            );
            this.allHeros.add(a_hero);
        }

    }
    private void initSorcerers(ArrayList<String[]> s) throws Exception {
        for (int i = 1; i < s.size(); i++) {
            a_hero=new Hero(s.get(i)[0],
                    Integer.parseInt(s.get(i)[1]),
                    Integer.parseInt(s.get(i)[2]),
                    Integer.parseInt(s.get(i)[3]),
                    Integer.parseInt(s.get(i)[4]),
                    Integer.parseInt(s.get(i)[5]),
                    Integer.parseInt(s.get(i)[6]),
                    HeroType.Sorcerer,
                    100*Integer.parseInt(s.get(i)[6]),
                    0,
                    Integer.parseInt(s.get(i)[1]),
                    new Inventory(new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),new ArrayList<>())
                    ,new Weapon("Hand",0,0,0,0)
                    ,new Armory("Body",0,0,0)
                    ,0
                    ,0
                    ,0
            );
            this.allHeros.add(a_hero);

        }
    }
    public ArrayList<Hero> getAllHeros() {
        return allHeros;
    }

    public ArrayList<Hero> getHeroParty() {
        return heroParty;
    }

    public void display(ArrayList<Hero> heroes){
        System.out.println("------------------------------------------------------------------------  HEROES  ------------------------------------------------------------------------");
        System.out.println("Number              Name                     mana            strength           agility             dexterity                gold              level");
        for (int i = 0; i < heroes.size(); i++) {
            System.out.printf("%d\t\t",i);
            System.out.printf("%20s",heroes.get(i).getName());
            System.out.printf("%20s",heroes.get(i).getMaxMP());
            System.out.printf("%20s",heroes.get(i).getStrength());
            System.out.printf("%20s",heroes.get(i).getAgility());
            System.out.printf("%20s",heroes.get(i).getDexterity());
            System.out.printf("%20s",heroes.get(i).getGold());
            System.out.printf("%20s",heroes.get(i).getLevel());
            System.out.println();
        }
    }
    public void getInput() {
        Scanner sc = new Scanner(System.in);
        display(allHeros);

        boolean loop = true;
        System.out.println("Ok, you select " + partynum + " heroes, please select them separately");
        for (int i = 0; i < partynum; i++) {
            System.out.println("Please select the hero (total 3) to join your party!");
            boolean valid=true;
            int number=0;
            boolean validInput = false;
            while (!validInput) {
                try {
                    System.out.print("Select your hero for H"+(heroParty.size()+1)+" :Enter a number: ");
                    number = sc.nextInt();
                    if (number > getAllHeros().size() || number < 0 || Nums.contains(number)) {
                        System.out.println("Please enter a valid/non-repeat number!");
                        sc.nextLine();
                    } else {
                        validInput = true;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a number.");
                    sc.nextLine(); // consume the invalid input
                }

            }
            heroParty.add(this.allHeros.get(number));
            Nums.add(number);
        }
        heroParty.get(0).setIcon("H1");
        heroParty.get(1).setIcon("H2");
        heroParty.get(2).setIcon("H3");//set for icon for print out
        System.out.println("Congrats! you have selected "+partynum+" heroes:");
                System.out.printf("%s, %s, %s\n", heroParty.get(0).getName(), heroParty.get(1).getName(), heroParty.get(2).getName());
                System.out.printf("\n\n\n");
            }

    public int getPartynum() {
        return partynum;
    }
}
