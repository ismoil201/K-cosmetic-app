package kr.dev.bureger_house.view.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kr.dev.bureger_house.network.ApiClient

class LoginFragmentVM(val savedStateHandle: SavedStateHandle) : ViewModel() {

    private  val userName =  savedStateHandle.get<String>("user_name")
    private  val tempTextLiveData : MutableLiveData<String> = MutableLiveData()

      val userNameLiveData: MutableLiveData<String?>  = MutableLiveData()

    init {

        userNameLiveData.value = userName
    }

    fun saveItemId(itemId: Int) {
        savedStateHandle.set("item_id", itemId)
    }

    fun getItemId(): Int? {
        return savedStateHandle.get("item_id")
    }





}