package census.com.census;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DatabaseHelper extends SQLiteOpenHelper{

    String DB_PATH = null;
    public static String DB_NAME = "locations";
    public static SQLiteDatabase dbLocations;
    private final Context context;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
        this.context = context;
        this.DB_PATH = "/data/data/"+context.getPackageName()+"/"+"databases/";
    }

    public void createDatabase() throws IOException{
        boolean dbExist = checkDatabase();

        if(dbExist){
        }
        else {
            this.getReadableDatabase();

            try{
                copyDatabase();
            }
            catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    private boolean checkDatabase(){
        SQLiteDatabase checkDB = null;

        try{
            String dbPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(dbPath,null,SQLiteDatabase.OPEN_READWRITE);
        }
        catch(SQLiteException e){

        }
        if(checkDB!=null){
            checkDB.close();
        }
        return checkDB != null? true :false;
    }

    private void copyDatabase() throws IOException {
        InputStream inputDb = context.getAssets().open(DB_NAME+".db");

        String outDbName = DB_PATH + DB_NAME;
        OutputStream outputDB = new FileOutputStream(outDbName);

        byte[] buffer = new byte[1024];
        int length;

        while ((length = inputDb.read(buffer))>0){
            outputDB.write(buffer, 0, length);
        }

        outputDB.flush();
        outputDB.close();
        inputDb.close();
    }


    public void openDataBase() throws SQLException {
        String myPath = DB_PATH + DB_NAME;
        dbLocations = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
    }

    @Override
    public synchronized void close(){
        if(dbLocations!=null)
            dbLocations.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
