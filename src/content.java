public class content {
    contentType cell; //market, enemy, or unknown
    int row;
    int col;
    public static final String ANSI_RED="\u001B[41m";

    public static final String ANSI_RESET = "\u001B[0m";

    public static final String ANSI_YELLOW = "\u001B[43m";

    public static final String ANSI_GREEN="\u001b[42m";

    public static final String ANSI_BLUE="\u001b[44m";
    public static final String ANSI_WHITE="\u001B[47m";
    public static final String ANSI_VIO="\u001B[45m";

    boolean haveItem;

    private String icon;

    public boolean haveHero=false;
    public Hero hero=null;

    public boolean haveMonster=false;
    public Monster monster=null;

    private contentType curtype;

    public content(int row, int col){
        this.row=row;
        this.col=col;
        this.haveItem=false;
        this.cell=contentType.Plain; //to easier to set content randomly
    }

    public void newGame() {
        this.haveItem = false;
        this.cell=contentType.Plain;
    }//here we start a new game//

    public void draw() {
        curtype = this.cell;
        if (!this.haveHero && !this.haveMonster) {
            if (curtype == contentType.Cave) {//cave should be yellow
                System.out.printf(ANSI_YELLOW+"%s"+ANSI_RESET,"    ");
            }else if (curtype == contentType.Bush) {//should be green
                System.out.printf(ANSI_GREEN+"%s"+ANSI_RESET,"    ");
            }else if(curtype == contentType.Koulou){//should be blue
                System.out.printf(ANSI_BLUE+"%s"+ANSI_RESET,"    ");
            }else if(curtype==contentType.inaccessible){
                System.out.printf(ANSI_RED+"%s"+ANSI_RESET,"    ");
            }else if(curtype==contentType.Monster_Nexus||curtype==contentType.Hero_Nexus){
                System.out.printf(ANSI_VIO+"%s"+ANSI_RESET,"    ");
            }else{
                System.out.printf(ANSI_WHITE+"%s"+ANSI_RESET,"    ");
            }
        }else if(this.haveHero&&!this.haveMonster){
            if (curtype == contentType.Cave) {//cave should be yellow
                System.out.printf(ANSI_YELLOW+"%s"+ANSI_RESET,this.getHero().geticon()+"  ");
            }else if (curtype == contentType.Bush) {//should be green
                System.out.printf(ANSI_GREEN+"%s"+ANSI_RESET,this.getHero().geticon()+"  ");
            }else if(curtype == contentType.Koulou){//should be blue
                System.out.printf(ANSI_BLUE+"%s"+ANSI_RESET,this.getHero().geticon()+"  ");
            }else if(curtype==contentType.inaccessible){
                System.out.printf(ANSI_RED+"%s"+ANSI_RESET,this.getHero().geticon()+"  ");
            }else if(curtype==contentType.Monster_Nexus||curtype==contentType.Hero_Nexus){
                System.out.printf(ANSI_VIO+"%s"+ANSI_RESET,this.getHero().geticon()+"  ");
            }else{
                System.out.printf(ANSI_WHITE+"%s"+ANSI_RESET,this.getHero().geticon()+"  ");
            }
        }else if(!this.haveHero&&this.haveMonster){
            if (curtype == contentType.Cave) {//cave should be yellow
                System.out.printf(ANSI_YELLOW+"%s"+ANSI_RESET,this.getMonster().geticon()+"  ");
            }else if (curtype == contentType.Bush) {//should be green
                System.out.printf(ANSI_GREEN+"%s"+ANSI_RESET,this.getMonster().geticon()+"  ");
            }else if(curtype == contentType.Koulou){//should be blue
                System.out.printf(ANSI_BLUE+"%s"+ANSI_RESET,this.getMonster().geticon()+"  ");
            }else if(curtype==contentType.inaccessible){
                System.out.printf(ANSI_RED+"%s"+ANSI_RESET,this.getMonster().geticon()+"  ");
            }else if(curtype==contentType.Monster_Nexus||curtype==contentType.Hero_Nexus){
                System.out.printf(ANSI_VIO+"%s"+ANSI_RESET,this.getMonster().geticon()+"  ");
            }else{
                System.out.printf(ANSI_WHITE+"%s"+ANSI_RESET,this.getMonster().geticon()+"  ");
            }
        }else{
            if (curtype == contentType.Cave) {//cave should be yellow
                System.out.printf(ANSI_YELLOW+"%s"+ANSI_RESET,this.getHero().geticon()+this.getMonster().geticon());
            }else if (curtype == contentType.Bush) {//should be green
                System.out.printf(ANSI_GREEN+"%s"+ANSI_RESET,this.getHero().geticon()+this.getMonster().geticon());
            }else if(curtype == contentType.Koulou){//should be blue
                System.out.printf(ANSI_BLUE+"%s"+ANSI_RESET,this.getHero().geticon()+this.getMonster().geticon());
            }else if(curtype==contentType.inaccessible){
                System.out.printf(ANSI_RED+"%s"+ANSI_RESET,this.getHero().geticon()+this.getMonster().geticon());
            }else if(curtype==contentType.Monster_Nexus||curtype==contentType.Hero_Nexus){
                System.out.printf(ANSI_VIO+"%s"+ANSI_RESET,this.getHero().geticon()+this.getMonster().geticon());
            }else{
                System.out.printf(ANSI_WHITE+"%s"+ANSI_RESET,this.getHero().geticon()+this.getMonster().geticon());
            }
        }
    }

    public int getRow(){
        return row;
    }
    public int getCol(){
        return col;
    }

    public void setType(contentType type){
        this.cell=type;
    }

    public boolean isHaveHero() {
        return haveHero;
    }

    public boolean isHaveMonster() {
        return haveMonster;
    }

    public Hero getHero() {
        return hero;
    }

    public Monster getMonster() {
        return monster;
    }

    public void setHaveHero(boolean haveHero) {
        this.haveHero = haveHero;
    }

    public void setHaveMonster(boolean haveMonster) {
        this.haveMonster = haveMonster;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
        this.haveMonster=true;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
        this.haveHero=true;
    }

}
