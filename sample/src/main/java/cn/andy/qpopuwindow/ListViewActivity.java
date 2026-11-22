package cn.andy.qpopuwindow;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by Shuai.Li13 on 2018/3/16.
 */

public class ListViewActivity extends AppCompatActivity{

    private int rawX;
    private int rawY;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
       final ListView listView= findViewById(R.id.listview);

       listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Datas.getDatas()));
       listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
           @Override
           public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

               QPopuWindow.getInstance(ListViewActivity.this).builder
                       .bindView(view,position)
                       .setPopupItemList(new String[]{"澶嶅埗","绮樿创","杞彂","鏇村...."})
                       .setPointers(rawX,rawY)
                       .setOnPopuListItemClickListener(new QPopuWindow.OnPopuListItemClickListener() {
                           @Override
                           public void onPopuListItemClick(View anchorView, int anchorViewPosition, int position) {
                               Toast.makeText(ListViewActivity.this,anchorViewPosition+"---->"+position,Toast.LENGTH_SHORT).show();
                           }
                       }).show();
               return true;
           }
       });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        rawX= (int) ev.getRawX();
        rawY= (int) ev.getRawY();
        return super.dispatchTouchEvent(ev);
    }

}

