package com.gerardvh.jh7_gvanhalsema;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.gerardvh.jh7_gvanhalsema.model.Computer;

import java.util.List;

/**
 * Created by gerardvh on 4/2/15.
 */
public class ComputerAdapter extends ArrayAdapter {

    private Context context;
    private List<Computer> objects;

    public ComputerAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
        this.context = context;
        this.objects = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Computer computer = objects.get(position);

        LayoutInflater inflater =
                (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_computer, null);

        TextView tv = (TextView) view.findViewById(R.id.textView);
        tv.setText(computer.toString());

        return view;
    }
}
