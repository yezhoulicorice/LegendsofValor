import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class LoVGamerOperation extends GameOperation {
    protected HeroList herolist;
    protected ArrayList<Hero> heroes;
    protected ArrayList<Monster> aliveMonsters;
    public int turn;
    private boolean quit = false;

    protected int level = Integer.MIN_VALUE;
    private boolean correct=true;


    public LoVGamerOperation(int GameType) throws Exception {
        super(GameType);
    }

    public void GameTurn() throws Exception {
        Random random = new Random();
        MonsterList monsterList = new MonsterList();
        content[][] board = this.map.realboard;
        board[7][1].setHero(heroes.get(0));
        board[7][4].setHero(heroes.get(1));
        board[7][7].setHero(heroes.get(2));//set Heroes to Map
        heroes.get(0).setCol(1);
        heroes.get(0).setRow(7);
        heroes.get(1).setCol(4);
        heroes.get(1).setRow(7);
        heroes.get(2).setCol(7);
        heroes.get(2).setRow(7);
        turn = 0;
        MonsterMove move=new MonsterMove();
        do {
            if (turn % 8 == 0) {
                for (int i = 0; i < 3; i++) {
                    if (level < heroes.get(i).getLevel()) {
                        level = heroes.get(i).getLevel();
                    }
                }
                ArrayList<Monster> monsters = getMonster(monsterList.getAllMonsters(), random, level);
                if (turn != 0) {
                    aliveMonsters.addAll(monsters);
                } else {
                    aliveMonsters = monsters;
                }
                monsters.get(0).setIcon("M1");
                monsters.get(1).setIcon("M2");
                monsters.get(2).setIcon("M3");
                board[0][0].setMonster(monsters.get(0));
                board[0][3].setMonster(monsters.get(1));
                board[0][6].setMonster(monsters.get(2));//set Monsters to Nexus
                monsters.get(0).setRow(0);
                monsters.get(0).setCol(0);
                monsters.get(1).setRow(0);
                monsters.get(1).setCol(3);
                monsters.get(2).setRow(0);
                monsters.get(2).setCol(6);
            }
            map.draw();
            //now we need the hero move first
            HeroMove(heroes.get(0));
            map.draw();
            HeroMove(heroes.get(1));
            map.draw();
            HeroMove(heroes.get(2));
            move.move(aliveMonsters,board);
            map.draw();
            break;


        } while (true);
    }


    @Override
    public void StartGame() throws Exception {
        System.out.println("Welcome to Legends_of_Valor!");
        herolist = new HeroList();
        herolist.getInput();
        heroes = herolist.getHeroParty();
        GameTurn();
    }

    public ArrayList<Monster> getMonster(ArrayList<Monster> allmonster, Random random, int level) {
        ArrayList<Integer> Nums = new ArrayList<>();
        ArrayList<Monster> monsters = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            do {
                int monster = random.nextInt(allmonster.size());
                if (!Nums.contains(monster) && allmonster.get(monster).getLevel() <= level) {
                    Nums.add(monster);
                    monsters.add(allmonster.get(monster));
                    break;
                }
            } while (true);
        }
        return monsters;
    }

    public void HeroMove(Hero hero) throws Exception {
        content[][] board = map.realboard;
        boolean valid = false;
        Scanner in = new Scanner(System.in);
        do {
            System.out.println(hero.getName() + ", you are at " + hero.geticon() + ", \n" +
                    "Please follow the instructions below\n" +
                    "• W/w: move up \n" +
                    "• A/a: move left\n" +
                    "• S/s: move down\n" +
                    "• D/d: move right\n" +
                    "• Q/q: quit game\n" +
                    "• I/i: show information\n" +
                    "• M/m: enter market (if you are at Nexus)\n" +
                    "• T/t: teleport to another hero\n" +
                    "• R/r: recall to the original Nexus\n" +
                    "• V/v: in attack mode (if there are monsters around you)");
            System.out.printf("input:");
            String input = in.nextLine();
            if (input.equalsIgnoreCase("A")) {
                if (hero.getCol() == 0) {
                    valid = false;
                    System.out.println("Cannot move outside of the map! Please reenter!");
                } else {
                    if (board[hero.getRow()][hero.getCol() - 1].cell == contentType.inaccessible) {
                        System.out.println("Sorry, this is a inaccessible area!");
                        in.nextLine();
                        break;
                    } else if (board[hero.getRow()][hero.getCol() - 1].haveHero) {
                        System.out.println("Sorry, there is already another hero!");
                        in.nextLine();
                        break;
                    }
                    board[hero.getRow()][hero.getCol()].setHero(null);
                    board[hero.getRow()][hero.getCol()].setHaveHero(false);
                    hero.setCol(hero.getCol() - 1);
                    board[hero.getRow()][hero.getCol()].setHero(hero);
                    valid = true;
                    break;
                }

            } else if (input.equalsIgnoreCase("S")) {
                if (hero.getRow() == 7) {
                    valid = false;
                    System.out.println("Cannot move outside of the map! Please reenter!");
                } else if (board[hero.getRow() + 1][hero.getCol()].cell == contentType.inaccessible) {
                    System.out.println("Sorry, this is a inaccessible area!");
                    in.nextLine();
                } else if (board[hero.getRow() + 1][hero.getCol()].haveHero) {
                    System.out.println("Sorry, there is already another hero!");
                    in.nextLine();
                } else {
                    board[hero.getRow()][hero.getCol()].setHero(null);
                    board[hero.getRow()][hero.getCol()].setHaveHero(false);
                    hero.setRow(hero.getRow() + 1);
                    board[hero.getRow()][hero.getCol()].setHero(hero);
                    break;
                }
            } else if (input.equalsIgnoreCase("D")) {
                if (hero.getCol() == 7) {
                    valid = false;
                    System.out.println("Cannot move outside of the map! Please reenter!");
                } else if (board[hero.getRow()][hero.getCol() + 1].cell == contentType.inaccessible) {
                    System.out.println("Sorry, this is a inaccessible area!");
                    in.nextLine();
                } else if (board[hero.getRow()][hero.getCol() + 1].haveHero) {
                    System.out.println("Sorry, there is already another hero!");
                    in.nextLine();
                } else {
                    board[hero.getRow()][hero.getCol()].setHero(null);
                    board[hero.getRow()][hero.getCol()].setHaveHero(false);
                    hero.setCol(hero.getCol() + 1);
                    board[hero.getRow()][hero.getCol()].setHero(hero);
                    break;

                }
            } else if (input.equalsIgnoreCase("W")) {
                if (hero.getRow() == 0) {
                    valid = false;
                    System.out.println("Cannot move outside of the map! Please reenter!");
                } else if (board[hero.getRow() - 1][hero.getCol()].cell == contentType.inaccessible) {
                    System.out.println("Sorry, this is a inaccessible area!");
                    in.nextLine();
                } else if (board[hero.getRow() - 1][hero.getCol()].haveHero) {
                    System.out.println("Sorry, there is already another hero!");
                    in.nextLine();
                } else if (board[hero.getRow()][hero.getCol()].isHaveMonster()) {
                    System.out.println("Sorry, there is monster around you! Please attack it first to move on");
                    in.nextLine();
                } else if (hero.getCol() != 0 && board[hero.getRow()][hero.getCol() - 1].isHaveMonster()) {
                    System.out.println("Sorry, there is monster around you! Please attack it first to move on");
                    in.nextLine();
                } else if (hero.getCol() != 7 && board[hero.getRow()][hero.getCol() + 1].isHaveMonster()) {
                    System.out.println("Sorry, there is monster around you! Please attack it first to move on");
                    in.nextLine();
                } else {
                    board[hero.getRow()][hero.getCol()].setHero(null);
                    board[hero.getRow()][hero.getCol()].setHaveHero(false);
                    hero.setRow(hero.getRow() - 1);
                    board[hero.getRow()][hero.getCol()].setHero(hero);
                    break;
                }
            } else if (input.equalsIgnoreCase("Q")) {
                quit = true;
                valid = true;
                break;
            } else if (input.equalsIgnoreCase("M")) {
                if (board[hero.getRow()][hero.getCol()].cell == contentType.Hero_Nexus) {
                    ViewMarket(hero);
                    in.nextLine();
                } else {
                    System.out.println("Sorry, but you are not on your naxus");
                    valid = false;
                }
            } else if (input.equalsIgnoreCase("I")) {
                ShowInfo(hero);
                in.nextLine();
            } else if (input.equalsIgnoreCase("T")) {
                Teleport(hero);
                break;
            } else if (input.equalsIgnoreCase("v")) {
                //Attack(hero);
                break;
            }else if(input.equalsIgnoreCase("r")){
                board[hero.getRow()][hero.getCol()].setHero(null);
                board[hero.getRow()][hero.getCol()].setHaveHero(false);
                if(hero.geticon().equals("H1")){
                    hero.setRow(7);
                    hero.setCol(1);
                    board[hero.getRow()][hero.getCol()].setHero(hero);
                }else if(hero.geticon().equals("H2")){
                    hero.setRow(7);
                    hero.setCol(4);
                    board[hero.getRow()][hero.getCol()].setHero(hero);
                }else{
                    hero.setRow(7);
                    hero.setCol(7);
                    board[hero.getRow()][hero.getCol()].setHero(hero);
                }
            }else {
                valid = false; //the situation that input isn't in the manual
                System.out.println("Sorry, your enter doesn't fit our operations");
            }
        } while (!valid);
    }

    public void ShowInfo(Hero hero) {
        Scanner in = new Scanner(System.in);
        hero.printHero();
        System.out.println("If you want to equip something, press e; if not, press anything else");
        System.out.printf("input:");
        String input = in.nextLine();
        if (input.equalsIgnoreCase("e")) {
            hero.equip();
        }
    }

    public void ViewMarket(Hero hero) throws Exception {
        Market market = new Market();
        Deal deal = new Deal();
        deal.getInput(hero, market);
    }

    public void Teleport(Hero hero) {
        ArrayList<Hero> copyHero = (ArrayList<Hero>) heroes.clone();
        copyHero.remove(hero);//here we clone one list that contains other
        Scanner in = new Scanner(System.in);
        Hero targetHero;
        do {
            correct=true;
            System.out.println("Which hero do you want to teleport to? a." + copyHero.get(0).geticon()
                    + ", b." + copyHero.get(1).geticon());
            String input = in.nextLine();
            if (input.equalsIgnoreCase("a")) {
                targetHero=copyHero.get(0);
                teleport_target(hero,targetHero);
                if(correct) {
                    break;
                }
            } else if (input.equalsIgnoreCase("b")) {
                targetHero=copyHero.get(1);
                teleport_target(hero,targetHero);
                if(correct) {
                    break;
                }
            } else {
                System.out.println("Sorry, wrong input, please select a/b.");
            }
        } while (true);
    }
    public void teleport_target(Hero hero, Hero hero1){
        content[][] board= map.realboard;
        Scanner in=new Scanner(System.in);
        do{
            System.out.println("Please enter a, s, d or q to teleport to the left, down, right of "+
                    hero1.geticon()+", or quit to reselect the target hero");
            String input=in.nextLine();
            if(input.equalsIgnoreCase("a")){
                if(hero1.getCol()!=0&&!board[hero1.getRow()][hero1.getCol()-1].haveHero){
                    board[hero.getRow()][hero.getCol()].setHero(null);
                    board[hero.getRow()][hero.getCol()].setHaveHero(false);
                    board[hero1.getRow()][hero1.getCol()-1].setHero(hero);
                    hero.setCol(hero1.getCol()-1);
                    hero.setRow(hero1.getRow());
                    break;
                }else{
                    System.out.println("Sorry, cannot move to left");
                }
            }else if(input.equalsIgnoreCase("d")){
                if(hero1.getCol()!=7&&!board[hero1.getRow()][hero1.getCol()+1].haveHero){
                    board[hero.getRow()][hero.getCol()].setHero(null);
                    board[hero.getRow()][hero.getCol()].setHaveHero(false);
                    board[hero1.getRow()][hero1.getCol()+1].setHero(hero);
                    hero.setCol(hero1.getCol()+1);
                    hero.setRow(hero1.getRow());
                    break;
                }else{
                    System.out.println("Sorry, cannot move to right");
                }
            }else if(input.equalsIgnoreCase("s")){
                if(hero1.getRow()!=0&&!board[hero1.getRow()-1][hero1.getCol()].haveHero){
                    board[hero.getRow()][hero.getCol()].setHero(null);
                    board[hero.getRow()][hero.getCol()].setHaveHero(false);
                    board[hero1.getRow()-1][hero1.getCol()].setHero(hero);
                    hero.setCol(hero1.getCol());
                    hero.setRow(hero1.getRow()-1);
                    break;
                }else{
                    System.out.println("Sorry, cannot move down");
                }
            }else if(input.equalsIgnoreCase("q")){
                System.out.println("Ok,let's reselect again");
                correct=false;
                break;
            }else{
                System.out.println("Sorry your enter is not available.");
            }
        }while (true);
    }
}
