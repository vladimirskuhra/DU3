import java.awt.Color;

public class Farby {
    public static Color fromString(String name) {
        switch (name.toLowerCase()) {
            case "red":    return Color.RED;
            case "green":  return Color.GREEN;
            case "blue":   return Color.BLUE;
            case "brown":  return new Color(139,69,19);
            case "black":  return Color.BLACK;
            default:       return Color.BLACK; // fallback
        }
    }
}