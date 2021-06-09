package tictactoe.util;

public enum Character {
    EMPTY(" "),
    X("X"),
    O("O");

    private final String character;

    Character(String character) {
        this.character = character;
    }

    public String getCharacter() {
        return character;
    }

    public Character getReverse() {
        return Character.valueOf(character) == X ? O : Character.valueOf(character) == O ? X : EMPTY;
    }
}
