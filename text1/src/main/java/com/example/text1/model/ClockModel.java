package com.example.text1.model;

import com.example.text1.bean.DbBean;
import com.example.text1.contract.ClockContract;
import com.example.text1.util.DbUtil;

import java.util.List;

/**
 * Created by dell on 2019/4/26.
 */

public class ClockModel implements ClockContract.Model {
    @Override
    public void query(ClockContract.CallBack back) {
        List<DbBean> query = DbUtil.getmUtil().query();
        if (back != null) {
            if (query != null) {
                back.onQueryFial("查询成功!!!");
                back.onQuerySuccess(query);
            }else {
                back.onQueryFial("查询失败!!!");
            }
        }
    }

    @Override
    public void delete(DbBean bean, ClockContract.CallBack back) {
        if (back != null) {
            boolean delete = DbUtil.getmUtil().delete(bean);
            if (delete) {
                back.onDelete("删除成功!!!");
            }else {
                back.onDelete("删除失败!!!");
            }
        }
    }

    @Override
    public void update(DbBean bean, ClockContract.CallBack back) {
        if (back != null) {
            boolean upaate = DbUtil.getmUtil().upaate(bean);
            if (upaate) {
                back.onUpdate("修改成功!!!");
            }else {
                back.onUpdate("修改失败!!!");
            }
        }
    }
}
