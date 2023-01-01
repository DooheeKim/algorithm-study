package part1.ch08;

public class WildCardRun {
    public static void main(String[] args){

        String wildCardString = "th*l?*o*r?ng*s";
        String str = "thelordoftherings";
        WildCard wildCard = new WildCard(wildCardString, str);
        System.out.println(wildCard.match());
        System.out.println(wildCard.matchMemoized());
    }
}
