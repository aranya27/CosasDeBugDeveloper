package com.crud.bd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UsuariosSQLiteHelper extends SQLiteOpenHelper{
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "UsuariosDB";
	private static final String DATABASE_CREATE = 
			"create table usuario(" +
			"	id integer primary key autoincrement," +
			"	nombre text not null," +
			"	apaterno text not null," +
			"	amaterno text, " +
			"	email text," +
			"	fechaNacimiento date" +
			")";
	
	
	

	public UsuariosSQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);	
		
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase database, int arg1, int arg2) {
		database.execSQL("DROP TABLE IF EXISTS usuario");
		onCreate(database);
		
	}

}
