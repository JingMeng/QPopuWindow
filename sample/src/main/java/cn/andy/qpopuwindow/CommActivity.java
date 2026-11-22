package cn.andy.qpopuwindow;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Shuai.Li13 on 2018/3/16.
 */

public class CommActivity extends AppCompatActivity implements View.OnLongClickListener {

    private int rawX;
    private int rawY;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_comm);

        TextView textView = findViewById(R.id.tv);
        textView.setOnLongClickListener(this);

    }

    @Override
    public boolean onLongClick(View v) {

        QPopuWindow.getInstance(this).builder
                .bindView(v,0)
                .setPopupItemList(new String[]{"澶嶅埗","绮樿创","杞彂","鏇村...."})
                .setPointers(rawX,rawY)
                .setOnPopuListItemClickListener(new QPopuWindow.OnPopuListItemClickListener() {
                    @Override
                    public void onPopuListItemClick(View anchorView, int anchorViewPosition, int position) {
                        Toast.makeText(CommActivity.this,anchorViewPosition+"---->"+position,Toast.LENGTH_SHORT).show();
                    }
                }).show();
        return true;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        rawX= (int) ev.getRawX();
        rawY= (int) ev.getRawY();
        return super.dispatchTouchEvent(ev);
    }
}

