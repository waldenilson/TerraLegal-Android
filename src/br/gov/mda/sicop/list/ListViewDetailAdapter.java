package br.com.csl.base.list;

import java.util.List;

import br.com.csl.alunouniasselvi.R;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ListViewDetailAdapter extends BaseAdapter
{
	Activity context;
	List<String> title;
	List<String> desc;
	List<String> value;
	

	public ListViewDetailAdapter(Activity context, List<String> title, List<String> desc, List<String> value) {
		super();
		this.context = context;
		this.title = title;
		this.desc = desc;
		this.value = value;
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
        TextView txtViewTitle, txtViewValue, txtViewDesc;
	}

	public View getView(int position, View convertView, ViewGroup parent)
	{
		// TODO Auto-generated method stub
		ViewHolder holder;
		LayoutInflater inflater =  context.getLayoutInflater();

		if (convertView == null)
		{
			convertView = inflater.inflate(R.layout.listdetail, null);
			holder = new ViewHolder();
			holder.txtViewTitle = (TextView) convertView.findViewById(R.id.tv_listdetail_title);
			holder.txtViewDesc = (TextView) convertView.findViewById(R.id.tv_listdetail_desc);
			holder.txtViewValue = (TextView) convertView.findViewById(R.id.tv_listdetail_value);
			convertView.setTag(holder);
		}
		else
		{
			holder = (ViewHolder) convertView.getTag();
		}
		holder.txtViewTitle.setText(title.get(position).toString());
		holder.txtViewDesc.setText(desc.get(position).toString());
		holder.txtViewValue.setText(value.get(position).toString());
	return convertView;
	}

}

