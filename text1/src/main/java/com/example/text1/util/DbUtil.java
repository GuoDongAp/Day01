package com.example.text1.util;

import com.example.text1.bean.DbBean;
import com.example.text1.dao.DaoMaster;
import com.example.text1.dao.DbBeanDao;

import java.util.List;

/**
 * Created by dell on 2019/4/26.
 */
//郭栋
public class DbUtil {
    private static DbUtil mUtil;
    private final DbBeanDao mDao;

    private DbUtil() {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(App.getApp(), "db.db");
        mDao = new DaoMaster(devOpenHelper.getWritableDatabase()).newSession().getDbBeanDao();
    }

    public static DbUtil getmUtil() {
        if (mUtil == null) {
            synchronized (DbUtil.class){
                if (mUtil == null) {
                    mUtil = new DbUtil();
                }
            }
        }
        return mUtil;
    }

    private boolean has(DbBean bean){
        DbBean unique = mDao.queryBuilder().where(DbBeanDao.Properties.Url.eq(bean.getUrl())).unique();
        if (unique != null) {
            return true;
        }
        return false;
    }

    public long insert(DbBean bean){
        if(has(bean)){
            return -1;
        }else {
            return mDao.insertOrReplace(bean);
        }
    }

    public boolean delete(DbBean bean){
        if (has(bean)) {
            mDao.delete(bean);
            return true;
        }
        return false;
    }

    public boolean upaate(DbBean bean){
        if (has(bean)) {
            mDao.update(bean);
            return true;
        }
        return false;
    }

    public List<DbBean> query(){
        return mDao.queryBuilder().list();
    }
}
