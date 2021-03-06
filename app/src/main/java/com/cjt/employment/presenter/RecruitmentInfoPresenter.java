package com.cjt.employment.presenter;

import android.util.Log;
import android.view.MenuItem;

import com.cjt.employment.bean.Recruit;
import com.cjt.employment.bean.RecruitmentInfo;
import com.cjt.employment.bean.UpdateResult;
import com.cjt.employment.model.Imodel.RecruitModel;
import com.cjt.employment.model.Imodel.RecruitmentInfoModel;
import com.cjt.employment.model.RecruitModelImp;
import com.cjt.employment.model.RecruitmentInfoModelImp;
import com.cjt.employment.ui.activity.RecruitmentInfoActivity;
import com.cjt.employment.ui.view.HomeView;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * 作者: 陈嘉桐 on 2016/8/22
 * 邮箱: 445263848@qq.com.
 */
public class RecruitmentInfoPresenter extends BasePresenter<RecruitmentInfoActivity> {
    private RecruitmentInfoModel mRecruitmentInfoModel;

    public RecruitmentInfoPresenter() {
        mRecruitmentInfoModel = RecruitmentInfoModelImp.getInstance();
    }

    public void getRecruitInfoById(String action, int id) {
        if (mRecruitmentInfoModel != null) {
            getView().showProgressBar();
            mRecruitmentInfoModel.getRecruitInfoById(action, id)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<RecruitmentInfo>() {
                        @Override
                        public void call(RecruitmentInfo recruitmentInfo) {
//                            Log.i("CJT", RecruitmentInfo.toString());
                            getView().updateRecruitmentInfo(recruitmentInfo.getData());
                            getView().hideProgressBar();
//                            mHomeView.updata(shopList.getVendors());
                        }
                    }, new Action1<Throwable>() {
                        @Override
                        public void call(Throwable throwable) {
                            Log.i("RxJava", "又是在这里出现了问题呀----->" + throwable.toString());
                        }
                    });
        } else {
            Log.i("CJT", "model is null");
        }
    }

    public void pushVitage(String action, String id, int recruitId, int companyId) {
        if (mRecruitmentInfoModel != null) {
            getView().showProgressBar();
            mRecruitmentInfoModel.pushVitage(action, id, recruitId, companyId)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<UpdateResult>() {
                        @Override
                        public void call(UpdateResult updateResult) {
                            if (updateResult.getResult().equals("success")) {
                                getView().sendVitageSuccess();
                            } else {
                                getView().sendVitageFail();
                            }
                            getView().hideProgressBar();
                        }
                    }, new Action1<Throwable>() {
                        @Override
                        public void call(Throwable throwable) {
                            Log.i("RxJava", "又是在这里出现了问题呀----->" + throwable.toString());
                        }
                    });
        } else {
            Log.i("CJT", "model is null");
        }
    }

    public void addCollection(String action, String id, String recruitId, final MenuItem item) {
        if (mRecruitmentInfoModel != null) {
            getView().showProgressBar();
            mRecruitmentInfoModel.addCollection(action, id, recruitId)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<UpdateResult>() {
                        @Override
                        public void call(UpdateResult updateResult) {
                            if (updateResult.getResult().equals("success")) {
                                getView().addCollectionSuccess(item);
                            } else {
                                getView().addCollectionFail();
                            }
                            getView().hideProgressBar();
                        }
                    }, new Action1<Throwable>() {
                        @Override
                        public void call(Throwable throwable) {
                            Log.i("RxJava", "又是在这里出现了问题呀----->" + throwable.toString());
                        }
                    });
        } else {
            Log.i("CJT", "model is null");
        }
    }

    public void isCollection(String action, String id, String recruitId) {
        if (mRecruitmentInfoModel != null) {
            getView().showProgressBar();
            mRecruitmentInfoModel.isCollection(action, id, recruitId)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<UpdateResult>() {
                        @Override
                        public void call(UpdateResult updateResult) {
                            if (updateResult.getResult().equals("success")) {
                                getView().collection();
                            } else {
                                getView().unCollection();
                            }
                            getView().hideProgressBar();
                        }
                    }, new Action1<Throwable>() {
                        @Override
                        public void call(Throwable throwable) {
                            Log.i("RxJava", "又是在这里出现了问题呀----->" + throwable.toString());
                        }
                    });
        } else {
            Log.i("CJT", "model is null");
        }
    }

    public void deleteCollection(String action, String id, String recruitid, final MenuItem item) {
        if (mRecruitmentInfoModel != null) {
            getView().showProgressBar();
            mRecruitmentInfoModel.deleteCollection(action, id, recruitid)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<UpdateResult>() {
                        @Override
                        public void call(UpdateResult updateResult) {
                            if (updateResult.getResult().equals("success")) {
                                getView().deleteCollectionSuccess(item);
                            } else {
                                getView().deleteCollectionFail();
                            }
//                            getView().getCollectionSuccess(collectionBean.getData());
                            getView().hideProgressBar();
                        }
                    }, new Action1<Throwable>() {
                        @Override
                        public void call(Throwable throwable) {
                            Log.i("RxJava", "又是在这里出现了问题呀----->" + throwable.toString());
                        }
                    });
        } else {
            Log.i("CJT", "model is null");
        }
    }
}
