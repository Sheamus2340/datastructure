package org.sheamus.concurrency.future;

import org.sheamus.concurrency.future.vo.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;

public class CompletableFutureCompose {

    ThreadPoolExecutor customAsyncTaskExecutor = null;

    private void init() {
        CustomThreadPool customThreadPool = new CustomThreadPool();
        customAsyncTaskExecutor = customThreadPool.createCustomThreadPool();
    }

    public void thenRun() {
        System.out.println("------- thenRun start -------");
        if (customAsyncTaskExecutor == null) {
            init();
        }

        CompletableFuture.runAsync(() -> {
            System.out.println("thread name: " + Thread.currentThread().getName() + " first step...");
        }, customAsyncTaskExecutor).thenRun(() -> {
            System.out.println("thread name: " + Thread.currentThread().getName() + " second step...");
        }).thenRunAsync(() -> {
            System.out.println("thread name: " + Thread.currentThread().getName() + " third step...");
        });
        System.out.println("------- thenRun end -------");
    }

    public void thenApply() {
        System.out.println("------- thenApply start -------");
        if (customAsyncTaskExecutor == null) {
            init();
        }

        CompletableFuture.supplyAsync(() -> {
            System.out.println("thread name:" + Thread.currentThread().getName() + " first step...");
            return "hello";
        }, customAsyncTaskExecutor).thenApply((result1) -> {
            String targetResult = result1 + " austin";
            System.out.println("first step result: " + result1);
            System.out.println("thread name: " + Thread.currentThread().getName() + " second step..., targetResult: " + targetResult);
            return targetResult;
        });
        System.out.println("------- thenApply end -------");
    }

    public void thenAccept() {
        System.out.println("------- thenAccept start -------");
        if (customAsyncTaskExecutor == null) {
            init();
        }

        CompletableFuture.supplyAsync(() -> {
            System.out.println("thread name: " + Thread.currentThread().getName() + " first step...");
            return "hello";
        }, customAsyncTaskExecutor).thenAccept((result1) -> {
            String targetResult = result1 + " austin";
            System.out.println("first step result: " + result1);
            System.out.println("thread name: " + Thread.currentThread().getName() + " second step..., targetResult: " + targetResult);
        });
        System.out.println("------- thenAccept end -------");
    }

    public void thenCombine() {
        System.out.println("------- thenCombine start -------");
        if (customAsyncTaskExecutor == null) {
            init();
        }

        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("thread name: " + Thread.currentThread().getName() + " 执行future1开始...");
            return "Hello";
        }, customAsyncTaskExecutor);
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("thread name: " + Thread.currentThread().getName() + " 执行future2开始...");
            return "World";
        }, customAsyncTaskExecutor);
        future1.thenCombine(future2, (result1, result2) -> {
            String result = result1 + " " + result2;
            System.out.println("thread name: " + Thread.currentThread().getName() + " 获取到future1、future2聚合结果：" + result);
            return result;
        }).thenAccept(result -> System.out.println(result));

        System.out.println("------- thenCombine end -------");
    }

    public void thenCompose() {
        System.out.println("------- thenCompose start -------");
        if (customAsyncTaskExecutor == null) {
            init();
        }

        CompletableFuture.supplyAsync(() -> {
            // 第一个Future实例结果
            System.out.println("thread name: " + Thread.currentThread().getName() + " 执行future1开始...");
            return "Hello";
        }, customAsyncTaskExecutor).thenCompose(result1 -> CompletableFuture.supplyAsync(() -> {
            // 将上一个Future实例结果传到这里
            System.out.println("thread name: " + Thread.currentThread().getName() + " 执行future2开始..., 第一个实例结果：" + result1);
            return result1 + " World";
        })).thenCompose(result12 -> CompletableFuture.supplyAsync(() -> {
            // 将第一个和第二个实例结果传到这里
            System.out.println("thread name: " + Thread.currentThread().getName() + " 执行future3开始..., 第一第二个实现聚合结果：" + result12);
            String targetResult = result12 + ", I am austin!";
            System.out.println("最终输出结果：" + targetResult);
            return targetResult;
        }));
        System.out.println("------- thenCompose end -------");
    }

    public static CompletableFuture handle(int a, int b) {
        return CompletableFuture.supplyAsync(() -> a / b)
                .handle((result, ex) -> {
                    if (null != ex) {
                        System.out.println("handle error: " + ex.getMessage());
                        return 0;
                    } else {
                        return result;
                    }
                });
    }

    public static CompletableFuture whenComplete(int a, int b) {
        return CompletableFuture.supplyAsync(() -> a / b)
                .whenComplete((result, ex) -> {
                    if (null != ex) {
                        System.out.println("whenComplete error: " + ex.getMessage());
                    }
                });
    }

    public HomeVO homeIndex(String userId, String lang) {

        if (customAsyncTaskExecutor == null) {
            init();
        }

        BuildTask buildTask = new BuildTask();
        // 获取Banner轮播图信息
        CompletableFuture<List<BannerVO>> future1 = CompletableFuture.supplyAsync(() -> buildTask.buildBanners(userId, lang), customAsyncTaskExecutor);
        // 获取用户message通知信息
        CompletableFuture<List<NotificationVO>> future2 = CompletableFuture.supplyAsync(() -> buildTask.buildNotifications(userId, lang), customAsyncTaskExecutor);
        // 获取用户权益信息
        CompletableFuture<List<BenefitVO>> future3 = CompletableFuture.supplyAsync(() -> buildTask.buildBenefits(userId, lang), customAsyncTaskExecutor);
        // 获取优惠券信息
        CompletableFuture<List<CouponVO>> future4 = CompletableFuture.supplyAsync(() -> buildTask.buildCoupons(userId), customAsyncTaskExecutor);

        CompletableFuture<Void> allOfFuture = CompletableFuture.allOf(future1, future2, future3, future4);
        HomeVO finalHomeVO = new HomeVO();
        CompletableFuture<HomeVO> resultFuture = allOfFuture.thenApply(v -> {
            try {
                finalHomeVO.setBanners(future1.get());
                finalHomeVO.setNotifications(future2.get());
                finalHomeVO.setBenefits(future3.get());
                finalHomeVO.setCoupons(future4.get());
                return finalHomeVO;
            } catch (Exception e) {
                System.out.println("[Error] assemble homeVO data error: {}" + e);
                throw new RuntimeException(e);
            }
        });
        HomeVO homeVO = resultFuture.join();
        return homeVO;
    }

    public static void main(String[] args) {
        CompletableFutureCompose completableFutureCompose = new CompletableFutureCompose();
        completableFutureCompose.thenRun();
        completableFutureCompose.thenApply();
        completableFutureCompose.thenAccept();
        completableFutureCompose.thenCombine();
        completableFutureCompose.thenCompose();

        try {
            System.out.println("success: " + handle(10, 5).get());
            System.out.println("fail: " + handle(10, 0).get());
        } catch (Exception e) {
            System.out.println("catch exception= " + e.getMessage());
        }

        System.out.println("------------------------------------------------------------------");

        try {
            System.out.println("success: " + whenComplete(10, 5).get());
            System.out.println("fail: " + whenComplete(10, 0).get());
        } catch (Exception e) {
            System.out.println("catch exception=" + e.getMessage());
        }

        completableFutureCompose.homeIndex("1", "10");
    }

}
