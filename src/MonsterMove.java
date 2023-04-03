import java.util.ArrayList;

public class MonsterMove {
    public MonsterMove(){
        //
    }
    public void move(ArrayList<Monster> monsters, content[][]board){
        for(Monster monster:monsters){
            if(board[monster.getRow()][monster.getCol()].haveHero==true){
                //Attack(monster,board[monster.getRow()][monster.getCol()].getHero());
            }else{
                board[monster.getRow()][monster.getCol()].setMonster(null);
                board[monster.getRow()][monster.getCol()].setHaveMonster(false);
                board[monster.getRow()+1][monster.getCol()].setMonster(monster);
                monster.setRow(monster.getRow()+1);
            }
        }

    }
    //public void Attack(Monster monster, Hero hero){}
}
