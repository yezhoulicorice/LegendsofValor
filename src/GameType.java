public enum GameType {
    LoV(1,"Legends_of_Valor");

    private String GameName;

    private int gameType;

    GameType(int gameType,String GameName){
        this.GameName=GameName;
        this.gameType=gameType;
    }

    public int getGameType() {
        return gameType;
    }
}





