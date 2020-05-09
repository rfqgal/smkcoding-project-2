package com.example.smkcodingproject2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_my_friends.*

class MyFriendsFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_my_friends, container, false)
    }

    override fun onViewCreated(view: View, @Nullable savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    lateinit var friendList: ArrayList<MyFriends>

    private fun friendListSimulation() {
        friendList = ArrayList()
        friendList.add(MyFriends("Balyun", "Laki-laki", "balyun@gmail.com",
            "081234567890", "Malang"))
        friendList.add(MyFriends("Gilis", "Laki-laki", "gilis@gmail.com",
            "081234567891", "Malang"))
    }

    private fun showFriends() {
        rv_friends_list.layoutManager = LinearLayoutManager(activity)
        rv_friends_list.adapter = MyFriendsAdapter(activity!!, friendList)
    }

    private fun initView() {
        friendListSimulation()
        showFriends()
    }
}