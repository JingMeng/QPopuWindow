package cn.andy.qpopuwindow;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Shuai.Li13 on 2018/3/16.
 */

public class RecyclerViewActivity extends AppCompatActivity{

    private RecyclerView mRecyclerView;
    private int rawX;
    private int rawY;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recylerview_list);

        initRecylerView();
    }

    private void initRecylerView() {
        mRecyclerView = findViewById(R.id.recylerview);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        GridLayoutManager manager = new GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(RecyclerViewActivity.this,DividerItemDecoration.VERTICAL));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(RecyclerViewActivity.this,DividerItemDecoration.HORIZONTAL));
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(new MyAdapter());
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        rawX= (int) ev.getRawX();
        rawY= (int) ev.getRawY();
        return super.dispatchTouchEvent(ev);
    }

    private class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

        private final List<String> datas;

        public MyAdapter() {
            datas = Datas.getDatas();
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(View.inflate(RecyclerViewActivity.this,android.R.layout.simple_list_item_1,null));
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            ((TextView)holder.itemView.findViewById(android.R.id.text1)).setText(datas.get(position));
            ((TextView)holder.itemView.findViewById(android.R.id.text1)).setTextSize(30);
            ((TextView)holder.itemView.findViewById(android.R.id.text1)).setPadding(20,20,20,20);
        }

        @Override
        public int getItemCount() {
            return datas.size();
        }
    }

    private class MyViewHolder extends RecyclerView.ViewHolder{
        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    QPopuWindow.getInstance(RecyclerViewActivity.this).builder
                            .bindView(v,MyViewHolder.this.getAdapterPosition())
                            .setPopupItemList(new String[]{"澶嶅埗","绮樿创","杞彂","鏇村...."})
                            .setPointers(rawX,rawY)
                            .setOnPopuListItemClickListener(new QPopuWindow.OnPopuListItemClickListener() {
                                @Override
                                public void onPopuListItemClick(View anchorView, int anchorViewPosition, int position) {
                                    Toast.makeText(RecyclerViewActivity.this,anchorViewPosition+"---->"+position,Toast.LENGTH_SHORT).show();
                                }
                            }).show();
                    return true;
                }
            });
        }
    }
}

