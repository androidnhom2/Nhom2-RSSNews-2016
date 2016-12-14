package vn.edu.tdc.adapter;

import java.util.List;

import vn.edu.tdc.datamodels.ItemSlideMenu;
import vn.edu.tdc.nhom2ltdd.R;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SlideMenuAdapter extends BaseAdapter {

	Context context;
	List<ItemSlideMenu> itemSlide;

	public SlideMenuAdapter(Context context, List<ItemSlideMenu> itemSlide) {
		this.context = context;
		this.itemSlide = itemSlide;
	}

	private class ViewHolder {
		ImageView icon;
		TextView title;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		ViewHolder holder = null;

		LayoutInflater mInflater = (LayoutInflater) context
				.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.slide_menu_item_layout, null);
			holder = new ViewHolder();
			
			holder.icon = (ImageView) convertView.findViewById(R.id.icon);
			holder.title = (TextView) convertView.findViewById(R.id.title);

			ItemSlideMenu item_pos = itemSlide.get(position);
			// setting the image resource and title
			holder.icon.setImageResource(item_pos.getIcon());
			holder.title.setText(item_pos.getTitle());
			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		return convertView;

	}

	@Override
	public int getCount() {
		return itemSlide.size();
	}

	@Override
	public Object getItem(int position) {
		return itemSlide.get(position);
	}

	@Override
	public long getItemId(int position) {
		return itemSlide.indexOf(getItem(position));
	}

}
