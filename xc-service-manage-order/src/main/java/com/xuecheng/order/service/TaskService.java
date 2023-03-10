package com.xuecheng.order.service;

import com.xuecheng.framework.domain.order.XcOrders;
import com.xuecheng.framework.domain.order.XcOrdersPay;
import com.xuecheng.framework.domain.task.XcTask;
import com.xuecheng.framework.domain.task.XcTaskHis;
import com.xuecheng.order.dao.XcOrderPayRepository;
import com.xuecheng.order.dao.XcOrderRepository;
import com.xuecheng.order.dao.XcTaskHisRepository;
import com.xuecheng.order.dao.XcTaskRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Administrator
 * @version 1.0
 **/
@Service
public class TaskService {

    @Autowired
    XcTaskRepository xcTaskRepository;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    XcTaskHisRepository xcTaskHisRepository;

    @Autowired
    XcOrderRepository xcOrderRepository;

    @Autowired
    XcOrderPayRepository xcOrderPayRepository;

    //查询前n条任务
    public List<XcTask> findXcTaskList(Date updateTime, int size) {
        //设置分页参数
        Pageable pageable = PageRequest.of(0, size);
        //查询前n条任务
        Page<XcTask> all = xcTaskRepository.findByUpdateTimeBefore(pageable, updateTime);
        return all.getContent();
    }

    //发布消息
    public void publish(XcTask xcTask, String ex, String routingKey) {
        Optional<XcTask> optional = xcTaskRepository.findById(xcTask.getId());
        if (optional.isPresent()) {
            rabbitTemplate.convertAndSend(ex, routingKey, xcTask);
            //更新任务时间
            XcTask one = optional.get();
            one.setUpdateTime(new Date());
            xcTaskRepository.save(one);
        }

    }

    //获取任务
    @Transactional
    public int getTask(String id, int version) {
        //通过乐观锁的方式来更新数据表，如果结果大于0说明取到任务
        return xcTaskRepository.updateTaskVersion(id, version);
    }

    //完成任务
    @Transactional
    public void finishTask(String taskId) {
        Optional<XcTask> optionalXcTask = xcTaskRepository.findById(taskId);
        if (optionalXcTask.isPresent()) {
            //当前任务
            XcTask xcTask = optionalXcTask.get();
            //历史任务
            XcTaskHis xcTaskHis = new XcTaskHis();
            BeanUtils.copyProperties(xcTask, xcTaskHis);
            xcTaskHisRepository.save(xcTaskHis);
            xcTaskRepository.delete(xcTask);
        }
    }

    @Transactional
    public void updateOrderStatus() {
        List<XcOrders> xcordersByEndTimeBefore = xcOrderRepository.findAllByEndTimeBeforeAndStatusEquals(new Date(), "401001");

        List<String> collect = xcordersByEndTimeBefore.stream().map(XcOrders::getOrderNumber).collect(Collectors.toList());
        List<XcOrdersPay> allByOrderNumberIn = xcOrderPayRepository.findAllByOrderNumberIn(collect);
        for (XcOrders x : xcordersByEndTimeBefore) {
            x.setStatus("401003");
        }
        xcOrderRepository.saveAll(xcordersByEndTimeBefore);
        for (XcOrdersPay p : allByOrderNumberIn) {
            p.setStatus("402003");
        }
        xcOrderPayRepository.saveAll(allByOrderNumberIn);

    }
}
