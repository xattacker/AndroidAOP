package zj.it.bhne.androidaop

import android.os.Bundle

import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.ToastUtils
import com.xattacker.android.singleClick.SingleClick
import com.xattacker.android.singleClick.setSingleClick
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() ,View.OnClickListener
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        btnClick.setOnClickListener(this)
        initView()
    }

    private fun initView() {
        btnKuoZhan.setSingleClick(2000) {
            ToastUtils.showShort("222")
            Log.e("aaa","wwww")
        }
    }

    @SingleClick
    override fun onClick(v: View?) {
        if (v != null) {
            when(v.id){
                R.id.btnClick->{
                    ToastUtils.showShort("哈哈哈")
                    Log.e("ee","wwww")
                }
            }
        }
    }
}
