import java.util.Random;

public class Map {
    private int rownum;//the size will enter

    private int colnum;

    public static final String ANSI_RED="\u001B[31m";

    public static final String ANSI_RESET = "\u001B[0m";

    public static final String ANSI_YELLOW = "\u001B[33m";

    public static final String ANSI_GREEN="\u001b[32m";

    public static final String ANSI_BLUE="\u001b[34m";

    public content[][] realboard;//we set every cell a single one to easily reset them or change them

    public Map(int rownum, int colnum) {
        this.rownum = rownum;
        this.colnum = colnum;
        initGame();
    }

    public void initGame() {
        realboard = new content[rownum][colnum];//set a new char matrix
        for (int row = 0; row < rownum; row++) {
            for (int col = 0; col < colnum; col++) {
                realboard[row][col] = new content(row, col);//relate to public pawn--the cell inside it should
                //be not occupied, which means every cell's boolean set to false
            }
        }
        initalMap();
    }

    public void newGame() {
        for (int row = 0; row < rownum; row++) {
            for (int col = 0; col < colnum; col++) {
                realboard[row][col].newGame(); //reset the cell(see function in pawn)
            }
        }
        initalMap();
    }
    // then we need to print the board out//
    public void initalMap(){
        //set hero nexus
        for(int i=0;i<colnum;i++){
            realboard[rownum-1][i].cell=contentType.Hero_Nexus;
            realboard[rownum-1][i].haveItem=true;
        }
        //set monster nexus
        for(int i=0;i<colnum;i++){
            realboard[0][i].cell=contentType.Monster_Nexus;
            realboard[0][i].haveItem=true;
        }
        //set inaccessible area
        for(int i=0;i<rownum;i++){
            for(int j=2;j<colnum;j+=3){
                realboard[i][j].cell=contentType.inaccessible;
                realboard[i][j].haveItem=true;
            }
        }
        InitialRandom(realboard,contentType.Cave);
        InitialRandom(realboard,contentType.Bush);
        InitialRandom(realboard,contentType.Koulou);

    }
    public void InitialRandom(content[][] realboard, contentType type){
        Random random=new Random();
        for(int i=0;i<7;i++){ //36*0.2=7
            while(true){
                int randomNum = random.nextInt(rownum * colnum);
                if (!realboard[randomNum/rownum][randomNum%rownum].haveItem){
                    realboard[randomNum/rownum][randomNum%rownum].cell=type;
                    realboard[randomNum/rownum][randomNum%rownum].haveItem=true;
                    break;
                }
            }
        }
    }
    public void draw() {
        for (int i = 0; i < rownum; i++) {
            if (i <= rownum - 1) {
                int bbs = colnum;
                while (bbs > 1) {
                    System.out.print("+-+-");
                    bbs -= 1;
                }
                if (bbs == 1) {
                    System.out.println("+-+-+-+-+-+-+");
                }
            }
            for (int j = 0; j < colnum; j++) {
                if (j==0){
                    System.out.print("|");
                }
                realboard[i][j].draw();
                if (j < colnum - 1) {
                    System.out.print("|");
                } else if (j == colnum - 1) {
                    System.out.println("|");
                }
            }
            if(i==rownum-1){
                int bbss = colnum;
                while (bbss > 1) {
                    System.out.print("+-+-");
                    bbss -= 1;
                }
                if (bbss == 1) {
                    System.out.println("+-+-+-+-+-+-+");
                }
            }

        }
        System.out.println();
        System.out.println("Legend: \n" +
                "empty slot means you may randomly encounter a battle\n" +
                ANSI_YELLOW+"$"+ANSI_RESET+" means a market, it is safe and you can enter m/M to view market's items\n" +
                ANSI_RED+"X"+ANSI_RESET+" means the inaccessible area, you cannot walk through it");
        System.out.println();

    }

}
