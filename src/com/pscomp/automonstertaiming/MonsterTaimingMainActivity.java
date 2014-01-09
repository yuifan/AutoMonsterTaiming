package com.pscomp.automonstertaiming;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
//import android.view.View.OnClickListener;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.RadioButton;

public class MonsterTaimingMainActivity extends Activity {
    private static final String TAG = "MonsterTaimingMainActivity";
    private static final boolean DEBUG = true;

    /**
     * Items what wants to buy.
     */
    private CheckBox mCBItemShield, mCBItemSword, mCBItemFriendTime, mCBItemAutoSkill;

    /**
     * Whether summon friend.
     */
    private RadioButton mRBSummonFriend, mRBNoSummonFriend;

    /**
     * Play Time.
     */
    private EditText mETPlayTime;

    /**
     * Chest wants to open.
     */
    private RadioButton mRBLeftRewardChest, mRBCenterRewardChest, mRBRightRewardChest, mRBRandomRewardChest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monster_taiming_main);

        inflateLayout();
        setOnEventListener();
    }

    /**
     * Inflate Layout.
     */
    private void inflateLayout() {
        inflateItemCheckBox();
        inflateSummonFriendRadioButton();
        inflatePlayTimeEditText();
        inflateChestRadioButton();
    }

    /**
     * Inflate CheckBox about items what wants to buy.
     */
    private void inflateItemCheckBox() {
        mCBItemShield = (CheckBox) findViewById(R.id.cb_item_shield);
        mCBItemSword = (CheckBox) findViewById(R.id.cb_item_sword);
        mCBItemFriendTime = (CheckBox) findViewById(R.id.cb_item_friend_time);
        mCBItemAutoSkill = (CheckBox) findViewById(R.id.cb_item_auto_skill);
    }

    /**
     * Inflate RadioButton about whether summon friend.
     */
    private void inflateSummonFriendRadioButton() {
        mRBSummonFriend = (RadioButton) findViewById(R.id.rb_summon_friend);
        mRBNoSummonFriend = (RadioButton) findViewById(R.id.rb_no_summon_friend);
    }

    /**
     * Inflate EditText about your play time.
     */
    private void inflatePlayTimeEditText() {
        mETPlayTime = (EditText) findViewById(R.id.et_play_time);
    }

    /**
     * Inflate RadioButton about which reward chest open you want.
     */
    private void inflateChestRadioButton() {
        mRBLeftRewardChest = (RadioButton) findViewById(R.id.rb_left_reward_chest);
        mRBCenterRewardChest = (RadioButton) findViewById(R.id.rb_center_reward_chest);
        mRBRightRewardChest = (RadioButton) findViewById(R.id.rb_right_reward_chest);
        mRBRandomRewardChest = (RadioButton) findViewById(R.id.rb_random_reward_chest);
    }

    /**
     * Set event listener on views.
     */
    private void setOnEventListener() {
        //Show information dialog when checked SummonFriend RadioButton.
        mRBSummonFriend.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                try {
                    if (isChecked && buttonView.getId() == R.id.rb_summon_friend) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(MonsterTaimingMainActivity.this);
                        builder.setTitle(R.string.information_title_summon_friend);
                        builder.setMessage(R.string.information_message_summon_friend);
                        builder.setNegativeButton(android.R.string.ok, new OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //Nothing. Just close this dialog.
                            }
                        });
                        builder.create().show();
                    }
                } catch (Exception e) {
                    if (DEBUG) {
                        Log.e(TAG, "Occured an exception. Skip 'onCheckedChanged' call-back method.");
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.monster_taiming_main, menu);
        menu.add(getString(R.string.save_settings)).setOnMenuItemClickListener(new OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                // TODO Auto-generated method stub
                return false;
            }
        });
        menu.add(getString(R.string.load_settings)).setOnMenuItemClickListener(new OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                // TODO Auto-generated method stub
                return false;
            }
        });
        return true;
    }

}
