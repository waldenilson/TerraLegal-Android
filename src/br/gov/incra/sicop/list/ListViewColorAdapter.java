package br.gov.incra.sicop.list;

import java.util.List;

import br.gov.incra.sicop.R;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ListViewColorAdapter extends BaseAdapter
{
	Activity context;
	List<String> title;
	List<String> desc;
	List<Integer> color;
	

	public ListViewColorAdapter(Activity context, List<String> title, List<String> desc, List<Integer> color) {
		super();
		this.context = context;
		this.title = title;
		this.desc = desc;
		this.color = color;
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return title.size();
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	private class ViewHolder {
        TextView txtViewTitle, txtViewDesc;
        LinearLayout llView;
	}

	public View getView(int position, View convertView, ViewGroup parent)
	{
		// TODO Auto-generated method stub
		ViewHolder holder;
		LayoutInflater inflater =  context.getLayoutInflater();

		if (convertView == null)
		{
			convertView = inflater.inflate(R.layout.listcolor, null);
			holder = new ViewHolder();
			holder.txtViewTitle = (TextView) convertView.findViewById(R.id.tv_listcolor_title);
			holder.txtViewDesc = (TextView) convertView.findViewById(R.id.tv_listcolor_desc);
			holder.llView = (LinearLayout) convertView.findViewById(R.id.ll_listcolor);
			convertView.setTag(holder);
		}
		else
		{
			holder = (ViewHolder) convertView.getTag();
		}
		holder.txtViewTitle.setText(title.get(position).toString());
		holder.txtViewDesc.setText(desc.get(position).toString());
		holder.llView.setBackgroundColor( color.get(position) );
	return convertView;
	}

}

