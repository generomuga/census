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

    public ArrayList queryLocation(String query,String toQuery){
        ArrayList listResult = new ArrayList<>();

        try {
            SQLiteDatabase dbLocation = context.openOrCreateDatabase("locations", Context.MODE_PRIVATE, null);

            Cursor c = dbLocation.rawQuery(query,null);

            int toQueryIndex = c.getColumnIndex(toQuery);

            c.moveToFirst();
            while(c!=null){
                Log.i("region",c.getString(toQueryIndex));
                listResult.add(c.getString(toQueryIndex));
                c.moveToNext();
            }

            dbLocation.close();
        }
        catch (Exception e){
            Log.i("error",e.toString());
        }
        return listResult;
    }



}
