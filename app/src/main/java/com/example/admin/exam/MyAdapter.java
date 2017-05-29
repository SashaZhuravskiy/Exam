package com.example.admin.exam;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by User on 13.04.2017.
 */
public class MyAdapter extends BaseAdapter {


    class ViewHolder {
        TextView Param1View;
        TextView Param2View;
    }

    Context SlovoContext;
    Parameters[] Array;

    public MyAdapter(Context SlovoContext, Parameters[] Array){
        this.SlovoContext=SlovoContext;
        this.Array=Array;
    }
    @Override
    public int getCount() {
        return Array.length;
    }

    @Override
    public Object getItem(int position) {
        return  Array[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            LayoutInflater Slovoinflater = (LayoutInflater) SlovoContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = Slovoinflater.inflate(R.layout.single_row,null);

            holder = new ViewHolder();
            holder.Param1View = (TextView) convertView.findViewById(R.id.textView);
            holder.Param2View = (TextView) convertView.findViewById(R.id.textView2);

            convertView.setTag(holder);
        }
        else{
            holder=(ViewHolder) convertView.getTag();
        }
        Parameters CurSlovo = (Parameters) getItem(position);
        holder.Param1View.setText(CurSlovo.GetParam1());
        holder.Param2View.setText(CurSlovo.GetParam2());
        return convertView;
    }
}
