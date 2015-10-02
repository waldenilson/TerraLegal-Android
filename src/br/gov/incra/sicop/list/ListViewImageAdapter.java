package br.gov.incra.sicop.list;

import java.util.List;

import br.gov.incra.sicop.R;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListViewImageAdapter extends BaseAdapter
{
	Activity context;
	List<String> title;
	List<String> desc;
	List<Drawable> image;

	public ListViewImageAdapter(Activity context, List<String> title, List<String> desc, List<Drawable> image) {
		super();
		this.context = context;
		this.title = title;
		this.desc = desc;
		this.image = image;
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
        ImageView img;
	}

	public View getView(int position, View convertView, ViewGroup parent)
	{
		// TODO Auto-generated method stub
		ViewHolder holder;
		LayoutInflater inflater =  context.getLayoutInflater();

		if (convertView == null)
		{
			convertView = inflater.inflate(R.layout.listimage, null);
			holder = new ViewHolder();
			holder.txtViewTitle = (TextView) convertView.findViewById(R.id.tv_listimage);
			holder.txtViewDesc = (TextView) convertView.findViewById(R.id.tv_listimagedesc);
			holder.img = (ImageView) convertView.findViewById(R.id.iv_listimage);
			convertView.setTag(holder);
		}
		else
		{
			holder = (ViewHolder) convertView.getTag();
		}
		holder.txtViewTitle.setText(title.get(position).toString());
		holder.txtViewDesc.setText(desc.get(position).toString());
		holder.img.setImageDrawable(image.get(position));
		
	return convertView;
	}

}

