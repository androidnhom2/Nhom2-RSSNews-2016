package vn.edu.tdc.adapter;

import java.util.ArrayList;


import vn.edu.tdc.datamodels.ItemSlideMenu;
import vn.edu.tdc.nhom2ltdd.R;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SlideMenuAdapter extends ArrayAdapter<ItemSlideMenu> {

	Activity context;
	ArrayList<ItemSlideMenu> arrItems;

	public SlideMenuAdapter(Activity context, ArrayList<ItemSlideMenu> arrItems) {
		super(context, -1, arrItems);
		this.context = context;
		this.arrItems = arrItems;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		if (convertView == null) {
			LayoutInflater inflater = context.getLayoutInflater();
			convertView = inflater.inflate(R.layout.slide_menu_item_layout, null);
		}
		ImageView imgIcon = (ImageView) convertView.findViewById(R.id.icon);
		TextView txtTitle = (TextView) convertView.findViewById(R.id.title);
		
		ItemSlideMenu items = getItem(position);
		
		if (items != null) {
			// Anh xa + Gan gia tri

			imgIcon.setImageResource(items.getIcon());			
			txtTitle.setText(items.getTitle());
		}
		

		return convertView;

	}

}
