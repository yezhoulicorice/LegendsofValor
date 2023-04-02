import java.util.ArrayList;

public class LoVGamerOperation extends GameOperation {
    protected HeroList herolist;
    protected ArrayList<Hero> heroes;

    public LoVGamerOperation(int GameType) throws Exception {
        super(GameType);
    }


    @Override
    public void StartGame() throws Exception {
        content[][] board = this.map.realboard;
        System.out.println("Welcome to Legends_of_Valor!");
        herolist=new HeroList();
        herolist.getInput();
        heroes=herolist.getHeroParty();
        map.draw();
    }
}
