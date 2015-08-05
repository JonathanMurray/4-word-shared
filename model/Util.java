package fourword_shared.model;

import java.util.Random;

/**
 * Created by jonathan on 2015-07-19.
 */
public class Util {
    public static char randomLetter(){
        char letter = 'A';
        int offset = new Random().nextInt('Z' - 'A');
        letter = (char)(letter + offset);
        return letter;
    }
}
