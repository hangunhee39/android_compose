package kr.co.fastcampus.part4plus.restaurantapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import kr.co.fastcampus.part4plus.restaurantapp.libraries.storage_contract.IStorage
import kr.co.fastcampus.part4plus.restaurantapp.libraries.storage_contract.constants.StorageKeys
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    // 현재 무슨 태마인지 저장 (다중 테마)
    @Inject
    lateinit var storage: IStorage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        saveAppTheme()
    }

    //다중테마
    private fun saveAppTheme() {
        storage.save(
            StorageKeys.APP_THEME,
            BuildConfig.FLAVOR
        )
    }
}
