package fourword_shared.model;

import android.content.Context;
import android.content.res.Configuration;

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

    public static boolean isSmallScreen(Context context){
        int size = context.getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK;
        return size == Configuration.SCREENLAYOUT_SIZE_SMALL
                || size == Configuration.SCREENLAYOUT_SIZE_NORMAL;
    }
}
