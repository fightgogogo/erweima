package com.gx.gaoxin20181112.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gx.gaoxin20181112.R;
import com.gx.gaoxin20181112.bean.Nine;
import com.gx.gaoxin20181112.bean.Two;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyTwoAdapter extends BaseAdapter {
    private Context context;
    private List<Two.DataBean> data;

    public MyTwoAdapter(Context context, List<Two.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if (convertView==null){
            viewHolder=new ViewHolder();
            convertView= View.inflate(context, R.layout.twoitem,null);
            viewHolder.imageView=convertView.findViewById(R.id.twoimageitem);
            viewHolder.textView=convertView.findViewById(R.id.twotextitem);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        Two.DataBean dataBean = data.get(position);
        viewHolder.textView.setText(dataBean.getName());
        Picasso.with(context).load(dataBean.getPic_url()).into(viewHolder.imageView);
        return convertView;
    }
    class ViewHolder{
        ImageView imageView;
        TextView textView;
    }
}
