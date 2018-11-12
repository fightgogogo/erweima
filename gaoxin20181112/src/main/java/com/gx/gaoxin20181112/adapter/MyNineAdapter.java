package com.gx.gaoxin20181112.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.gx.gaoxin20181112.R;
import com.gx.gaoxin20181112.bean.Nine;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyNineAdapter extends BaseAdapter {
    private Context context;
    private List<Nine.DataBean> data;

    public MyNineAdapter(Context context, List<Nine.DataBean> data) {
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
            convertView= View.inflate(context, R.layout.itemnine,null);
            viewHolder.imageView=convertView.findViewById(R.id.itemnineimage);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        Nine.DataBean dataBean = data.get(position);
        Picasso.with(context).load(dataBean.getIcon()).into(viewHolder.imageView);
        return convertView;
    }
    class ViewHolder{
        ImageView imageView;
    }
}
