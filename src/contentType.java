public enum contentType {
    Monster_Nexus("MN"),Hero_Nexus("HN"),Plain(" "),Cave("C"),Bush("B"),Koulou("K"),inaccessible("X");

    private String icon;

    contentType(String icon){
        this.icon=icon;
    }
    public String geticon(){
        return icon;
    }


}
