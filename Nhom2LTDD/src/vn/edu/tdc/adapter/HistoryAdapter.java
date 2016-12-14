package vn.edu.tdc.adapter;

import java.util.ArrayList;
import java.util.List;

import com.squareup.picasso.Picasso;

import vn.edu.tdc.datamodels.News;
import vn.edu.tdc.nhom2ltdd.R;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class HistoryAdapter extends ArrayAdapter<News> {
	Activity context = null;
	int itemlayout;
	ArrayList<News> arrNews = null;

	public HistoryAdapter(Activity context, ArrayList<News> objects) {
		super(context, -1, objects);
		// TODO Auto-generated constructor stub
		this.context = context;
		//itemlayout = resource;
		arrNews = objects;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if(convertView == null){
			LayoutInflater inflater = context.getLayoutInflater();
			convertView = inflater.inflate(R.layout.listview_item_layout, null);
		}

		ImageView imgHis = (ImageView) convertView
				.findViewById(R.id.imgItems);
		TextView txtTitleHis = (TextView) convertView.findViewById(R.id.txtTitle);
		TextView txtDateHis = (TextView) convertView.findViewById(R.id.txtDate);

		News news = getItem(position);
		if (news != null) {
			Picasso.with(getContext()).load(news.getStrImage()).into(imgHis);
			txtTitleHis.setText(news.getStrTitle());
			txtDateHis.setText(news.getStrDate());
			
		}
		return convertView;
	}

}
