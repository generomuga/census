package census.com.census;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DbUtils {

    public static void getDatabase(Context context){
        try{
            SQLiteDatabase dbLocation = context.openOrCreateDatabase("Locations",Context.MODE_PRIVATE, null);

            dbLocation.execSQL("CREATE TABLE IF NOT EXISTS locations (id INT,regions VARCHAR,province VARCHAR, municipality VARCHAR)");
            dbLocation.execSQL("INSERT INTO locations (id,regions,province,municipality) VALUES (1,'IV','Laguna', 'Calauan')");

            Cursor c = dbLocation.rawQuery("SELECT * from locations",null);

            int regionsIndex = c.getColumnIndex("regions");
            int provinceIndex = c.getColumnIndex("province");
            int municipality = c.getColumnIndex("municipality");

            c.moveToFirst();
            while (c!=null){
                Log.i("regions",c.getString(regionsIndex));
                c.moveToNext();
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
