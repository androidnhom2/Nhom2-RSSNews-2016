package vn.edu.tdc.rssnews;

import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import vn.edu.tdc.database.DatabaseHelper;
import vn.edu.tdc.database.NewsAccess;
import vn.edu.tdc.datamodels.News;
import vn.edu.tdc.nhom2ltdd.R;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;

public class DetailActivity extends Activity {

	private ImageView imgBack;
	private WebView webView;
	private CheckBox chkSave;
	NewsAccess dba;
	String link;
	String title;
	String image;
	String date;
	String detail = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail_layout);
		dba = new NewsAccess(DetailActivity.this);
		webView = (WebView) findViewById(R.id.webView);
		imgBack = (ImageView) findViewById(R.id.imgBack);

		final Intent intent = getIntent();
		final Bundle bundle = intent.getBundleExtra("news");
		link = bundle.getString("link");
		title = bundle.getString("title");
		image = bundle.getString("image");
		date = bundle.getString("date");

		/*
		 * News news = new News(bundle.getString("title"),
		 * bundle.getString("link"), bundle.getString("image"),
		 * bundle.getString("date"));
		 */

		WebSettings webSettings = webView.getSettings();
		webSettings.setSupportZoom(true);

		imgBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				onBackPressed();
			}
		});

		chkSave = (CheckBox) findViewById(R.id.chkhistory);

		chkSave.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				// TODO Auto-generated method stub
				News news;
				if (chkSave.isChecked()) {
					news = new News(title, image, date, link);
					dba.writeNews(news);
					Toast.makeText(DetailActivity.this,
							"Bài báo " + title + " đã lưu trong Database",
							Toast.LENGTH_LONG).show();
				}
			}
		});

		new GetData().execute();
	}

	public class GetData extends AsyncTask<Void, Void, Void> {

		ProgressDialog dialog;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog = new ProgressDialog(DetailActivity.this);
			dialog.setMessage("Đang xử lý. Vui lòng chờ trong giây lát...");
			dialog.show();
		}

		@Override
		protected Void doInBackground(Void... params) {

			try {
				Document doc = Jsoup.connect(link).get();

				Elements pubDate = doc
						.select("div.block_timer_share div.block_timer");
				Elements title = doc.select("div.title_news h1");
				Elements description = doc.select("h3.short_intro");

				Elements content = doc.select("div.fck_detail");

				detail += "<font size=\" 1.2em \" style = \" color: #005500 \"><em>"
						+ pubDate.text() + "</em></font>";
				detail += "<h2 style = \" color: red \">" + title.text()
						+ "</h2>";
				detail += "<h3>" + description.text() + "</h3>";

				detail += "<p>" + content.text() + "</p>";

			} catch (Exception e) {
				e.printStackTrace();
			}

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			dialog.dismiss();
			Toast.makeText(DetailActivity.this, "Hoàn thành việc đọc nội dung chi tiết.",
					Toast.LENGTH_LONG).show();
			webView.loadDataWithBaseURL("", detail, "text/html", "UTF-8", "");
		}

	}
}
