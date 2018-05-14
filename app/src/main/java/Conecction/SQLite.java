package Conecction;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by P&D on 7/05/2018.
 */

public class SQLite extends SQLiteOpenHelper {
    private static String nameBD="bdlogeo";
    private static int versionBD=1;

    private String TABLE_USUARIO="CREATE TABLE usuario( id_usuario INTEGER PRIMARY KEY AUTOINCREMENT, " +
            " login TEXT , " +
            " clave TEXT , " +
            " estado TEXT)";
    public SQLite(Context context) {
        super(context, nameBD, null, versionBD);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_USUARIO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS usuario");
        this.onCreate(db);
    }
}
