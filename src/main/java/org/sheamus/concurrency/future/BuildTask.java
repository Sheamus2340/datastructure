package org.sheamus.concurrency.future;

import org.sheamus.concurrency.future.vo.BannerVO;
import org.sheamus.concurrency.future.vo.BenefitVO;
import org.sheamus.concurrency.future.vo.CouponVO;
import org.sheamus.concurrency.future.vo.NotificationVO;

import java.util.ArrayList;
import java.util.List;

public class BuildTask {
    public List<BannerVO> buildBanners(String userId, String lang) {
        // 模拟请求耗时0.5秒
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new ArrayList<BannerVO>();
    }

    public List<NotificationVO> buildNotifications(String userId, String lang) {
        // 模拟请求耗时0.5秒
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new ArrayList<NotificationVO>();
    }

    public List<BenefitVO> buildBenefits(String userId, String lang) {
        // 模拟请求耗时0.5秒
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new ArrayList<BenefitVO>();
    }

    public List<CouponVO> buildCoupons(String userId) {
        // 模拟请求耗时0.5秒
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new ArrayList<CouponVO>();
    }
}
