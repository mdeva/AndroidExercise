package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.example.deverajan.androidexcercise.R;

import java.util.List;

import model.Listrow;

import static com.bumptech.glide.request.RequestOptions.centerInsideTransform;

/**
 * Created by deverajan on 12/2/18.
 */

public class ListAdapter extends  RecyclerView.Adapter<ListAdapter.MyViewHolder> {

    private List<Listrow> listrows;
    Context mContext;

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listrow, parent, false);

        return new MyViewHolder(itemView);
    }


    public ListAdapter(List<Listrow> listrows, Context context) {
        this.listrows = listrows;
        mContext = context;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Listrow listrow = listrows.get(position);
        holder.title.setText(listrow.getTitle());
        holder.description.setText(listrow.getDescription());
        Glide.with(mContext)
                .load(listrow.getImageHref())
                .apply(centerInsideTransform()
                        .placeholder(R.drawable.loading_picture)
                        .error(R.drawable.loading_picture)
                        .priority(Priority.HIGH))
                .into(holder.img);
    }

    @Override
    public int getItemCount() {
        return listrows.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
    public TextView title, description;
    public ImageView img;

    public MyViewHolder(View view) {
        super(view);
        title = (TextView) view.findViewById(R.id.title);
        description = (TextView) view.findViewById(R.id.description);
        img = (ImageView) view.findViewById(R.id.img);
    }
}

}


