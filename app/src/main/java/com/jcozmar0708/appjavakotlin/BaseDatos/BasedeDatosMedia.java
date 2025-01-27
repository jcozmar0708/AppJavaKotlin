package com.jcozmar0708.appjavakotlin.BaseDatos;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.jcozmar0708.appjavakotlin.Miscelanea.Alumno;

import java.util.ArrayList;
import java.util.List;

public class BasedeDatosMedia extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "clase";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME_MEDIAS = "medias";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NOMBRE = "nombre";
    private static final String COLUMN_MATES = "matematicas";
    private static final String COLUMN_LENGUA = "lengua";
    private static final String COLUMN_INFORMATICA = "informatica";
    private static final String COLUMN_INGLES = "ingles";
    private static final String COLUMN_MEDIA = "media";

    private static final String CREATE_TABLE_MEDIAS =
            "CREATE TABLE " + TABLE_NAME_MEDIAS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY, " +
                    COLUMN_NOMBRE + " TEXT NOT NULL," +
                    COLUMN_MATES + " DECIMAL NOT NULL, " +
                    COLUMN_LENGUA + " DECIMAL NOT NULL, " +
                    COLUMN_INFORMATICA + " DECIMAL NOT NULL," +
                    COLUMN_INGLES + " DECIMAL NOT NULL," +
                    COLUMN_MEDIA + " DECIMAL NOT NULL" +
                    ");";

    public BasedeDatosMedia(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_MEDIAS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_MEDIAS);
        onCreate(db);
    }

    public long introducirAlumno(Alumno alumno) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(BasedeDatosMedia.COLUMN_ID, alumno.getId());
        contentValues.put(BasedeDatosMedia.COLUMN_NOMBRE, alumno.getNombre());
        contentValues.put(BasedeDatosMedia.COLUMN_MATES, alumno.getMates());
        contentValues.put(BasedeDatosMedia.COLUMN_LENGUA, alumno.getLengua());
        contentValues.put(BasedeDatosMedia.COLUMN_INFORMATICA, alumno.getInformatica());
        contentValues.put(BasedeDatosMedia.COLUMN_INGLES, alumno.getIngles());
        contentValues.put(BasedeDatosMedia.COLUMN_MEDIA, alumno.getMedia());

        return db.insert(BasedeDatosMedia.TABLE_NAME_MEDIAS, null, contentValues);
    }

    public boolean alumnoExiste(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME_MEDIAS + " WHERE " + COLUMN_ID + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{id});

        return cursor.moveToFirst();
    }

    public int borrarAlumno(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME_MEDIAS, COLUMN_ID + " = ?", new String[]{id});
    }

    public List<Alumno> obtenerAlumnos() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Alumno> alumnos = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME_MEDIAS;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("id"));
                @SuppressLint("Range") String nombre = cursor.getString(cursor.getColumnIndex("nombre"));
                @SuppressLint("Range") float matematicas = cursor.getFloat(cursor.getColumnIndex("matematicas"));
                @SuppressLint("Range") float lengua = cursor.getFloat(cursor.getColumnIndex("lengua"));
                @SuppressLint("Range") float informatica = cursor.getFloat(cursor.getColumnIndex("informatica"));
                @SuppressLint("Range") float ingles = cursor.getFloat(cursor.getColumnIndex("ingles"));
                @SuppressLint("Range") float media = cursor.getFloat(cursor.getColumnIndex("media"));

                Alumno alumno = new Alumno(id, nombre, matematicas, lengua, informatica, ingles, media);

                alumnos.add(alumno);

            } while (cursor.moveToNext());
        }

        cursor.close();

        return alumnos;
    }
}
