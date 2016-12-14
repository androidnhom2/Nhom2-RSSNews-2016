package vn.edu.tdc.fragment;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import vn.edu.tdc.adapter.NewsAdapter;
import vn.edu.tdc.datamodels.News;
import vn.edu.tdc.nhom2ltdd.R;
import vn.edu.tdc.rssnews.DetailActivity;
import vn.edu.tdc.rssnews.XMLDOMParser;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class HealContent extends Fragment {

	private ListView lvheal;
	private NewsAdapter adapter;
	private ArrayList<News> arrNews = new ArrayList<News>();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_heal_item_layout,
				container, false);

		lvheal = (ListView) view.findViewById(R.id.lvheal);

		lvheal.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Bundle bundle = new Bundle();
				bundle.putString("title", arrNews.get(arg2).getStrTitle());
				bundle.putString("image", arrNews.get(arg2).getStrImage());
				bundle.putString("date", arrNews.get(arg2).getStrDate());
				bundle.putString("link", arrNews.get(arg2).getStrLink());
				
				Intent intent = new Intent(getActivity(), DetailActivity.class);
				/*intent.putExtra("title", arrNews.get(arg2).getStrTitle());
				intent.putExtra("date", arrNews.get(arg2).getStrDate());
				intent.putExtra("image", arrNews.get(arg2).getStrImage());
				intent.putExtra("link", arrNews.get(arg2).getStrLink());*/
				
				intent.putExtra("news", bundle);
				startActivity(intent);
			}
		});

		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				new ReadData().execute("http://vnexpress.net/rss/suc-khoe.rss");
			}
		};

		runnable.run();
		return view;
	}

	class ReadData extends AsyncTask<String, Integer, String> {

		ProgressDialog dialog;

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			dialog = new ProgressDialog(getActivity());
			dialog.setMessage("Đang tải dữ liệu. Vui lòng chờ trong giây lát...");
			dialog.show();
		}
		
		@Override
		protected String doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			return readContentFromUrl(arg0[0]);
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			dialog.dismiss();
			Toast.makeText(getActivity(), "Đã tải xong. Mời xem kết quả!",
					Toast.LENGTH_LONG).show();
			XMLDOMParser parser = new XMLDOMParser();
			Document document = parser.getDocument(result);
			NodeList nodeList = document.getElementsByTagName("item");
			NodeList nodeListDescription = document
					.getElementsByTagName("description");
			String photo = "";
			String title = "";
			String link = "";
			String date = "";
			String description = "";
			for (int i = 0; i < nodeList.getLength(); i++) {
				String cdata = nodeListDescription.item(i + 1).getTextContent();
				Pattern pattern = Pattern
						.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
				Matcher matcher = pattern.matcher(cdata);
				if (matcher.find()) {
					photo = matcher.group(1);
				}
				Element element = (Element) nodeList.item(i);
				title = parser.getValue(element, "title");
				date = parser.getValue(element, "pubDate");
				description = parser.getValue(element, "description");
				link = parser.getValue(element, "link");

				arrNews.add(new News(title, photo, date, link, description));
			}

			adapter = new NewsAdapter(getActivity(),
					android.R.layout.simple_list_item_1, arrNews);

			lvheal.setAdapter(adapter);
			super.onPostExecute(result);
		}
	}

	private static String readContentFromUrl(String strUrl) {
		StringBuilder content = new StringBuilder();

		try {
			// create a url object
			URL url = new URL(strUrl);

			// create a urlconnection object
			URLConnection urlConnection = url.openConnection();

			// wrap the urlconnection in a bufferedreader
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(urlConnection.getInputStream()));

			String line;

			// read from the urlconnection via the bufferedreader
			while ((line = bufferedReader.readLine()) != null) {
				content.append(line + "\n");
			}
			bufferedReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return content.toString();
	}
}
