package edu.sungshin.edusungshinourmoving

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import edu.sungshin.edusungshinourmoving.chatlist.ChatListFragment
import edu.sungshin.edusungshinourmoving.home.HomeFragment
import edu.sungshin.edusungshinourmoving.mypage.MyPageFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sungshin.edusungshinourmoving.R;
class BottomTabNavigation : AppCompatActivity() {
    var lastTimeBackPressed : Long = 0
    override fun onBackPressed() {
        if(System.currentTimeMillis() - lastTimeBackPressed >= 1500){
            lastTimeBackPressed = System.currentTimeMillis()
            Toast.makeText(this,"'뒤로' 버튼을 한번 더 누르시면 종료됩니다.",Toast.LENGTH_LONG).show() }
        else {
            ActivityCompat.finishAffinity(this)
            System.runFinalization()
            System.exit(0)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bottomtab)

        // 아래 3번에서 구현한 Fragment들을 가져옴
        val homeFragment = HomeFragment()
        val chatListFragment = ChatListFragment()
        val myPageFragment = MyPageFragment()

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        // fragment 초기값
        replaceFragment(homeFragment)

        // setOnNavigationItemSelectedListener는 네비게이션바의 탭들이 선택되었을 때 호출되어 선택된 탭의 id가 내려온다.
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> replaceFragment(homeFragment)
                R.id.chatting -> replaceFragment(chatListFragment)
               R.id.mypage -> replaceFragment(myPageFragment)
            }
            return@setOnNavigationItemSelectedListener true
        }
    }

    // FrameLayout에 선택된 Fragment를 attach하는 메소드
    private fun replaceFragment(fragment: Fragment) {

        supportFragmentManager.beginTransaction()
            .apply {
                replace(R.id.fragmentContainer, fragment)
                commit()
            }
    }
}