package utils;

public enum Colors {
    RESET("\033[0m"),

    BLACK("\033[0;30m"),   // BLACK
    RED("\033[0;31m"),     // RED
    GREEN("\033[0;32m"),   // GREEN
    YELLOW("\033[0;33m"),  // YELLOW
    BLUE("\033[0;34m"),    // BLUE
    PURPLE("\033[0;35m"),  // PURPLE
    CYAN("\033[0;36m"),    // CYAN
    BLACK_BACKGROUND_BRIGHT("\033[0;100m"),// BLACK
    BLACK_BACKGROUND("\033[40m" ), // BLACK
    RED_BACKGROUND("\033[41m" ),   // RED
    WHITE_BACKGROUND_BRIGHT("\033[0;107m");   // WHITE

    String codeASCII;

    Colors(String codeASCII) {
        this.codeASCII = codeASCII;
    }
}
