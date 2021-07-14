package Utils;

public enum GameReplies {

    // Tableau des répliques des collisions contre le mur
    wallHitReply_1("Vous vous heurtez au mur."),
    wallHitReply_2("Vous vous heurtez à nouveau au mur."),
    wallHitReply_3("Peut-être devriez-vous changer de lunettes.."),
    wallHitReply_4("Qu'est-ce que vous ne comprenez pas dans \"Vous vous heurtez contre le mur ?\""),
    wallHitReply_5("Mais... vous recommencez ? Vous êtes marteau ma parole !"),
    wallHitReply_6("Ok, ça suffit ! Vous allez mourir la tête écrasée contre le mur 😑"),
    wallHitReply_7("Après... votre tête a l'air assez solide."),
    wallHitReply_8("");

    private final String replies;

    public String getReplies() {
        return replies;
    }

    GameReplies(String replies) {
        this.replies = replies;
    }
}
