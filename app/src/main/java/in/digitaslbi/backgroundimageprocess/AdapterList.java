package in.digitaslbi.backgroundimageprocess;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Lokesh on 31-10-2015.
 */
public class AdapterList extends BaseAdapter {

    Context con;
    List<Item> item;

    public AdapterList(Context con, List<Item> item) {
        this.con = con;
        this.item = item;
    }

    @Override
    public int getCount() {
        return item.size();
    }

    @Override
    public Object getItem(int position) {
        return item.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = LayoutInflater.from(con).inflate(R.layout.item_listview, parent, false);

        ImageView imageView = (ImageView) view.findViewById(R.id.iv_test);
        TextView textView = (TextView) view.findViewById(R.id.tv_test);

        textView.setText(item.get(position).getName());

        /*Loading Images*/
        Glide.with(con)
                .load("https://upload.wikimedia.org/wikipedia/commons/thumb/5/50/Male_gorilla_in_SF_zoo.jpg/215px-Male_gorilla_in_SF_zoo.jpg")
                //.load(item.get(position).getUrl())
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .crossFade()
                .override(200,(200))

                .into(imageView);

        return view;
    }
}
