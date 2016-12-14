package vn.edu.tdc.database;

import java.util.ArrayList;

import vn.edu.tdc.datamodels.News;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DatabaseHelper extends SQLiteOpenHelper {
	static int DB_VERSION = 1;
	static String DB_NAME = "RSSNews";

	public DatabaseHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String sql = NewsAccess.getCreateTableSQL();
		db.execSQL(sql);
	}
	/*public void writeArticles(News news) {
		
		writeArticle(news);
		this.close();
	}
	
	public void readArticles(ArrayList<News> arrNews) {
		createOrOpenDatabase();
		String query = "Select * from news";
		Cursor cur = db.rawQuery(query, null);
		if (cur.moveToFirst()) {
			do {
				News news = new News();
				
				news.setStrTitle(cur.getString(cur
						.getColumnIndex("title")));
				news.setStrImage(cur.getString(cur
						.getColumnIndex("image")));
				news.setStrDate(cur.getString(cur
						.getColumnIndex("date")));
				news.setStrLink(cur.getString(cur
						.getColumnIndex("link")));
				arrNews.add(news);
			} while (cur.moveToNext());
		}

		this.close();
	}*/

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		//db.execSQL("DROP TABLE IF EXISTS " + "articles");
		 
        // Create tables again
        //onCreate(db);

	}

}
