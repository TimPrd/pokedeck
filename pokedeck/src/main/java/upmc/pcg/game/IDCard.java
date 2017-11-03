package upmc.pcg.game;

/**
 * Created by timot on 29/10/2017.
 */
public class IDCard
{
    private static final int UNIQUE_ID = 0;
    private static int uid = UNIQUE_ID;

    public static int getUid()
    {
        return ++uid;
    }

}
