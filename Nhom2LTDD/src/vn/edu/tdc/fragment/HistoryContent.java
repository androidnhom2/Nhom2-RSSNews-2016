package vn.edu.tdc.fragment;

import java.util.ArrayList;

import vn.edu.tdc.adapter.HistoryAdapter;
import vn.edu.tdc.database.NewsAccess;
import vn.edu.tdc.datamodels.News;
import vn.edu.tdc.nhom2ltdd.R;
import vn.edu.tdc.rssnews.DetailActivity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class HistoryContent extends Fragment {

	private ListView lvHis;
	private HistoryAdapter adapter;
	private ArrayList<News> arrNews = new ArrayList<News>();
	NewsAccess dba;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_history_item_layout,
				container, false);
		
		dba = new NewsAccess(getActivity());
		lvHis = (ListView) view.findViewById(R.id.lvHistory);
		
		adapter = new HistoryAdapter(getActivity(), arrNews);
		lvHis.setAdapter(adapter);

		lvHis.setOnItemClickListener(new OnItemClickListener() {

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
		
		dba.readContentNews(arrNews);
		adapter.notifyDataSetChanged();

		return view;
	}
}
