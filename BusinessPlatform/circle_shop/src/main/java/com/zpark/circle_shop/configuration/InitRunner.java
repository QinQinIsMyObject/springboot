package com.zpark.circle_shop.configuration;

import com.zpark.circle_shop.service.GoodsRecordService;
import com.zpark.circle_shop.service.UserRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(-1)
public class InitRunner implements CommandLineRunner {

    @Autowired
    private GoodsRecordService goodsRecordService;

    @Autowired
    private UserRecordService userRecordService;

    @Override
    public void run(String... args) throws Exception {
        //开启异步线程定时持久化商品的记录
        new Thread(() -> {
            //死循环，一直执行，定期持久化
            while (true) {
                try {
                    //间隔时间为10分钟
                    Thread.sleep(1000 * 10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //记录浏览量
                goodsRecordService.viewRecord();
                //记录交易量
                goodsRecordService.dealRecord();
                //记录用户访问量
                userRecordService.recordVisit();

                System.out.println("-----完成定期持久化记录操作！-----");
            }
        }).start();
    }

//    @Autowired
//    private RedisTemplate<String, Object> redisTemplate;
//
//    @Autowired
//    private GoodsRecordMapper goodsRecordMapper;
//
//    @Override
//    public void run(String... args) throws Exception {
//        //开启异步线程定时持久化商品的记录
//        new Thread(() -> {
//            //死循环，一直执行，定期持久化
//            while(true){
//                try {
//                    //间隔时间为10分钟
//                    Thread.sleep(1000  * 10);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//                //获取当前的时间
//                LocalDateTime dateTime = LocalDateTime.now();
//                int hour = dateTime.getHour();
//                int minute = dateTime.getMinute();
//                //当天的临界时间 23:50 - 23:59
//
//                //获取redis中用户近期的浏览商品清单
//                List<Object> viewList = redisTemplate.opsForList().range("goodsViewList", 0, -1);
//                if (viewList != null){
//                    //遍历集合，准备进行持久化
//                    for (Object o : viewList) {
//                        Integer gId = (Integer) o;
//                        //获取商品的浏览记录
//                        Integer views = (Integer)redisTemplate.opsForValue().get("goodsViews::" + gId);
//                        //查询数据库的信息
//                        GoodsRecord goodsRecord = goodsRecordMapper.selectRecord(gId);
//                        if (goodsRecord == null){
//                            //封装记录
//                            goodsRecord = new GoodsRecord().setGId(gId).setViews(views).setDeals(0);
//                            //新增记录
//                            goodsRecordMapper.insertRecord(goodsRecord);
//                        }else{
//                            if (goodsRecord.getViews() < views){
//                                //更新记录
//                                goodsRecordMapper.updateRecord(goodsRecord.setViews(views));
//                            }
//                        }
//                        //删除第一天的记录，准备第二天的记录
//                        if(hour >= 23 && minute>=50){
//                            redisTemplate.delete("goodsViews::" + gId);
//                        }
//                    }
//                    //删除第一天的记录，准备第二天的记录
//                    if(hour >= 23 && minute>=50){
//                        redisTemplate.delete("goodsViewList");
//                    }
//                }
//
//
//                //准备持久化交易量
//                List<Object> dealList = redisTemplate.opsForList().range("goodsDealList", 0, -1);
//                if (dealList != null){
//                    //遍历集合，准备进行持久化
//                    for (Object o : dealList) {
//                        Integer gId = (Integer) o;
//                        //获取商品的交易记录
//                        Integer deals = (Integer)redisTemplate.opsForValue().get("goodsDeals::" + gId);
//                        //查询数据库的信息
//                        GoodsRecord goodsRecord = goodsRecordMapper.selectRecord(gId);
//                        if (goodsRecord == null){
//                            //封装记录
//                            goodsRecord = new GoodsRecord().setGId(gId).setDeals(deals).setViews(0);
//                            //新增记录
//                            goodsRecordMapper.insertRecord(goodsRecord);
//                        }else{
//                            if (goodsRecord.getDeals() < deals){
//                                //更新记录
//                                goodsRecordMapper.updateRecord(goodsRecord.setDeals(deals));
//                            }
//                        }
//                        //删除第一天的记录，准备第二天的记录
//                        if(hour >= 23 && minute>=50){
//                            redisTemplate.delete("goodsDeals::" + gId);
//                        }
//                    }
//                    //删除第一天的记录，准备第二天的记录
//                    if(hour >= 23 && minute>=50){
//                        redisTemplate.delete("goodsDealList");
//                    }
//                }
//
//
//                System.out.println("-----完成定期持久化记录操作！-----");
//            }
//        }).start();
//    }

}
