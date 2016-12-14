package vn.edu.tdc.adapter;

import java.util.ArrayList;
import java.util.List;

import com.squareup.picasso.Picasso;

import vn.edu.tdc.datamodels.News;
import vn.edu.tdc.nhom2ltdd.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class NewsAdapter extends ArrayAdapter<News> {
	private Context context;
    private List<News> items;

	public NewsAdapter(Context context, int resource, ArrayList<News> items) {
		super(context, resource, items);
		this.context = context;
        this.items = items;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View view = convertView;
		if (view == null) {
			LayoutInflater inflater = LayoutInflater.from(getContext());
			view = inflater.inflate(R.layout.listview_item_layout, null);
		}
		News news = getItem(position);
		if (news != null) {
			// Anh xa + Gan gia tri
			ImageView imgItems = (ImageView) view.findViewById(R.id.imgItems);
			TextView txtTitle = (TextView) view.findViewById(R.id.txtTitle);
			TextView txtDate = (TextView) view.findViewById(R.id.txtDate);
			
			txtTitle.setText(news.getStrTitle());
			txtDate.setText(news.getStrDate());
			
			Picasso.with(getContext()).load(news.getStrImage()).into(imgItems);
			
		}
		return view;
	}

}