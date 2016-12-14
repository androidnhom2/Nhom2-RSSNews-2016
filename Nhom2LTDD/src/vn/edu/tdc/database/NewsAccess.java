package vn.edu.tdc.database;

import java.util.ArrayList;

import vn.edu.tdc.datamodels.News;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class NewsAccess {
	static final String TAB_NAME = "News";
	static final String COL_ID = "Id";
	static final String COL_TITLE = "Title";
	static final String COL_IMG = "Image";
	static final String COL_PUBDATE = "PubDate";
	static final String COL_LINK = "Link";
	Context context;
	private DatabaseHelper dbHelper;

	public static String getCreateTableSQL() {
		String sql = "CREATE TABLE " + TAB_NAME + "(" + COL_ID
				+ " integer primary key autoincrement, " + COL_TITLE
				+ " text, " + COL_PUBDATE + " text, " + COL_LINK + " text, "
				+ COL_IMG + " text)";
		return sql;
	}

	public NewsAccess(Context context) {
		dbHelper = new DatabaseHelper(context);
		this.context = context;
	}

	private ContentValues MakeNewsContentValues(News news) {
		ContentValues values = new ContentValues();
		values.put(COL_TITLE, news.getStrTitle());
		values.put(COL_PUBDATE, news.getStrDate());
		values.put(COL_LINK, news.getStrLink());
		values.put(COL_IMG, news.getStrImage());
		return values;
	}

	long insertNews(News news) {
		ContentValues values = MakeNewsContentValues(news);
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		long ok = db.insert(TAB_NAME, null, values);

		return ok;
	}
	
	public void writeNews(News news) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		boolean ok = true;
		
		long position = insertNews(news);
		if (position == -1)
			ok = false;

		if (ok) {
			Toast.makeText(context, "Bài báo " + news.getStrTitle() +
					" đã được lưu vào Database",
					Toast.LENGTH_LONG).show();
		} else {
			Toast.makeText(
					context,
					"Lỗi!. Bài báo " + news.getStrTitle() + " này đã có trong Database.",
					Toast.LENGTH_LONG).show();
		}

		db.close();
	}
	
	/*
	 * public ArrayList<News> getAllNews(){ ArrayList<News> arrNews = new
	 * ArrayList<News>(); SQLiteDatabase db = dbHelper.getReadableDatabase();
	 * String sql = "SELECT * FROM " + TAB_NAME; Cursor cursor =
	 * db.rawQuery(sql, null); int indexId = cursor.getColumnIndex(COL_ID); int
	 * indexTitle = cursor.getColumnIndex(COL_TITLE); int indexPubDate =
	 * cursor.getColumnIndex(COL_PUBDATE); int indexLink =
	 * cursor.getColumnIndex(COL_LINK);
	 * 
	 * if(cursor.moveToFirst()){ while(!cursor.isAfterLast()){ News news = new
	 * News(); news.setId(cursor.getLong(indexId));
	 * news.setStrTitle(cursor.getString(indexTitle));
	 * news.setStrDate(cursor.getString(indexPubDate));
	 * news.setStrLink(cursor.getString(indexLink)); arrNews.add(news);
	 * cursor.moveToNext(); } } cursor.close(); db.close(); return arrNews; }
	 */

	public void readContentNews(ArrayList<News> arrNews) {
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		String query = "Select * from " + TAB_NAME;
		Cursor cur = db.rawQuery(query, null);
		if (cur.moveToFirst()) {
			do {
				News news = new News();
				news.setId(cur.getInt(cur.getColumnIndex(COL_ID)));
				news.setStrTitle(cur.getString(cur.getColumnIndex(COL_TITLE)));
				news.setStrDate(cur.getString(cur.getColumnIndex(COL_PUBDATE)));
				news.setStrImage(cur.getString(cur.getColumnIndex(COL_IMG)));
				news.setStrLink(cur.getString(cur.getColumnIndex(COL_LINK)));
				arrNews.add(news);
			} while (cur.moveToNext());
		}

		db.close();
	}
}
