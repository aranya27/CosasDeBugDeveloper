package com.crud.bd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PersonasSQLiteHelper extends SQLiteOpenHelper{
	private static final int DATABASE_VERSION = 2;
	private static final String DATABASE_NAME = "PersonasDB";
	private static final String DATABASE_CREATE = 
			"create table persona(" +
			"	id integer primary key autoincrement," +
			"	nombre text not null," +
			"	apaterno text not null," +
			"	amaterno text, " +
			"	email text," +
			"	fechaNacimiento date," +
			"	sexo text" +
			")";
	
	
	

	public PersonasSQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);	
		
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase database, int arg1, int arg2) {
		database.execSQL("DROP TABLE IF EXISTS persona");
		onCreate(database);
		
	}

}
