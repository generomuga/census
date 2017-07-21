package census.com.census;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;


import java.io.IOException;
import java.util.ArrayList;

public class DbUtils {

    private String dbName;
    private Context context;
    private ArrayList<String> stringList;

    public DbUtils(Context context) {
        this.context = context;
        this.dbName = "locations";
    }

    public void createDbConnection(){
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        try {
            databaseHelper.createDatabase();
        }
        catch (IOException e) {
            throw new Error("Unable to create database");
        }

        try{
            databaseHelper.openDataBase();
        }
        catch (SQLException sqle){
            throw sqle;
        }
    }


    public void execQuery(String s, String query){
        try {
            SQLiteDatabase dbLocation = context.openOrCreateDatabase(dbName, Context.MODE_PRIVATE, null);

            /*Cursor c = dbLocation.rawQuery("SELECT distinct(region) FROM locations", null);

            int regionsIndex = c.getColumnIndex("region");
            //int provinceIndex = c.getColumnIndex("province");
            //int municipality = c.getColumnIndex("municipality");

            c.moveToFirst();
            while (c != null) {
                Log.i("region", c.getString(regionsIndex));
                //Toast.makeText(this,c.getString(regionsIndex),Toast.LENGTH_SHORT).show();
                c.moveToNext();*
            }
            */


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getStringList(String query,String toQuery) {
        try {
            stringList.clear();

            SQLiteDatabase dbLocation = context.openOrCreateDatabase(dbName, Context.MODE_PRIVATE, null);

            Cursor c = dbLocation.rawQuery(query,null);

            Toast.makeText(context,"Get string",Toast.LENGTH_SHORT).show();

            int toQueryIndex = c.getColumnIndex(toQuery);
            Toast.makeText(context,Integer.toString(toQueryIndex),Toast.LENGTH_SHORT).show();

            c.moveToFirst();
            while(c!=null){
                Log.i(toQuery,c.getString(toQueryIndex));
                stringList.add(c.getString(toQueryIndex));
                c.moveToNext();
            }

            //dbLocation.close();
        }
        catch (Exception e){
            Log.i("error",e.toString());
        }
        return stringList;
    }
}
