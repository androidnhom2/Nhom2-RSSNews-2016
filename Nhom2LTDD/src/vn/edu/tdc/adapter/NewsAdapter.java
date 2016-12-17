package vn.edu.tdc.adapter;

import java.util.ArrayList;

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

public class NewsAdapter extends ArrayAdapter<News> {

	Activity context = null;
	ArrayList<News> arrNews = null;

	public NewsAdapter(Activity context, ArrayList<News> arrNews) {
		super(context, -1, arrNews);
		this.context = context;
		this.arrNews = arrNews;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if (convertView == null) {
			LayoutInflater inflater = context.getLayoutInflater();
			convertView = inflater.inflate(R.layout.listview_item_layout, null);
		}
		ImageView imgItems = (ImageView) convertView.findViewById(R.id.imgItems);
		TextView txtTitle = (TextView) convertView.findViewById(R.id.txtTitle);
		TextView txtDate = (TextView) convertView.findViewById(R.id.txtDate);
		News news = getItem(position);
		if (news != null) {
			// Anh xa + Gan gia tri

			txtTitle.setText(news.getStrTitle());
			txtDate.setText(news.getStrDate());

			Picasso.with(getContext()).load(news.getStrImage()).into(imgItems);

		}
		return convertView;
	}

}