package br.gov.incra.sicop.list;

import java.util.List;

import br.gov.incra.sicop.R;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListViewConfigAdapter extends BaseAdapter
{
	Activity context;
	List<String> title;

	public ListViewConfigAdapter(Activity context, List<String> title) {
		super();
		this.context = context;
		this.title = title;
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
        TextView txtViewTitle;
	}

	public View getView(int position, View convertView, ViewGroup parent)
	{
		// TODO Auto-generated method stub
		ViewHolder holder;
		LayoutInflater inflater =  context.getLayoutInflater();

		if (convertView == null)
		{
			convertView = inflater.inflate(R.layout.listconfig, null);
			holder = new ViewHolder();
			holder.txtViewTitle = (TextView) convertView.findViewById(R.id.tv_listconfig);
			convertView.setTag(holder);
		}
		else
		{
			holder = (ViewHolder) convertView.getTag();
		}
		holder.txtViewTitle.setText(title.get(position).toString());
		
	return convertView;
	}

}

