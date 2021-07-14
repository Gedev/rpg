package Utils;

public enum Colors {
    RESET("\033[0m"),

    BLACK("\033[0;30m"),
    RED("\033[0;31m"),
    GREEN("\033[0;32m"),
    YELLOW("\033[0;33m"),
    BLUE("\033[0;34m"),
    PURPLE("\033[0;35m"),
    CYAN("\033[0;36m"),
    BLACK_BACKGROUND_BRIGHT("\033[0;100m"),
    BLACK_BACKGROUND("\033[40m" ),
    RED_BACKGROUND("\033[41m" ),
    WHITE_BACKGROUND_BRIGHT("\033[0;107m");

    String codeASCII;

    public String getCodeASCII() {
        return codeASCII;
    }

    Colors(String codeASCII) {
        this.codeASCII = codeASCII;
    }
}
