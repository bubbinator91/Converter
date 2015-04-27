package com.bubbinator91.converter.util;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bubbinator91.converter.R;

import java.util.ArrayList;

/**
 * Created by Christopher on 4/26/2015.
 */
public class NavigationDrawerListAdapter extends BaseAdapter {
	private Context mContext;
	private ArrayList<NavigationDrawerItem> mNavigationDrawerItems;

	public NavigationDrawerListAdapter(Context context, ArrayList<NavigationDrawerItem> navigationDrawerItems) {
		mContext = context;
		mNavigationDrawerItems = navigationDrawerItems;
	}

	@Override
	public int getCount() {
		return mNavigationDrawerItems.size();
	}

	@Override
	public Object getItem(int position) {
		return mNavigationDrawerItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		if (view == null) {
			view = ((LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE))
						   .inflate(R.layout.util_drawer_list_item, null);
		}

		ImageView icon = ((ImageView) view.findViewById(R.id.drawer_list_row_icon));
		TextView text = ((TextView) view.findViewById(R.id.drawer_list_row_text));

		icon.setImageResource(mNavigationDrawerItems.get(position).getIcon());
		text.setText(mNavigationDrawerItems.get(position).getTitle());

		return view;
	}
}
