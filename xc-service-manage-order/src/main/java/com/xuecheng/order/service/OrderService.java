package com.xuecheng.order.service;

import com.xuecheng.framework.domain.order.XcOrders;
import com.xuecheng.framework.domain.order.XcOrdersDetail;
import com.xuecheng.framework.domain.order.XcOrdersPay;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.QueryResult;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.order.client.LearningCourseClient;
import com.xuecheng.order.dao.XcOrderDetailRepository;
import com.xuecheng.order.dao.XcOrderPayRepository;
import com.xuecheng.order.dao.XcOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author User
 * @date 2019/12/7
 * @description
 */
@Service
public class OrderService {

    @Autowired
    XcOrderRepository xcOrderRepository;

    @Autowired
    XcOrderDetailRepository xcOrderDetailRepository;

    @Autowired
    XcOrderPayRepository xcOrderPayRepository;

    @Autowired
    LearningCourseClient learningCourseClient;

    public ResponseResult saveOrder(XcOrders xcOrders) {

        XcOrders xcOrders1 = new XcOrders();
        xcOrders1.setStatus("401001");
        Example<XcOrders> xcOrdersExample = Example.of(xcOrders1);
        List<XcOrders> all = xcOrderRepository.findAll(xcOrdersExample);
        if (!CollectionUtils.isEmpty(all)) {
            return ResponseResult.FAIL("您有未完成的订单，请完成后进行创建订单");

        }
        xcOrderRepository.save(xcOrders);
        return ResponseResult.SUCCESS(xcOrders);
    }

    public ResponseResult batchSaveXcOrderDetail(List<XcOrdersDetail> xcOrdersDetails) {
        List<XcOrdersDetail> xcOrdersDetails1 = xcOrderDetailRepository.saveAll(xcOrdersDetails);
        if (xcOrdersDetails1.size() == xcOrdersDetails.size()) {
            return new ResponseResult(CommonCode.SUCCESS);
        }
        return new ResponseResult(CommonCode.FAIL);
    }

    public QueryResult<XcOrders> list(int page, int size, XcOrders xcOrders) {
        ExampleMatcher exampleMatcher = ExampleMatcher.matching();
        Example<XcOrders> example = Example.of(xcOrders, exampleMatcher);

        Pageable pageable = PageRequest.of(page - 1, size);
        Page<XcOrders> all = xcOrderRepository.findAll(example, pageable);
        QueryResult<XcOrders> queryResult = new QueryResult<>();

        List<XcOrders> xcOrdersList = new ArrayList<>();
        for (XcOrders x : all.getContent()) {
            XcOrdersDetail xcOrdersDetail = new XcOrdersDetail();
            xcOrdersDetail.setOrderNumber(x.getOrderNumber());
            Example<XcOrdersDetail> xcOrdersDetailExample = Example.of(xcOrdersDetail);
            List<XcOrdersDetail> xcOrdersDetails = xcOrderDetailRepository.findAll(xcOrdersDetailExample);
            x.setXcOrdersDetails(xcOrdersDetails);
            xcOrdersList.add(x);
        }

        queryResult.setTotal(all.getTotalElements());
        queryResult.setList(xcOrdersList);
        return queryResult;
    }

    public XcOrders get(String orderNum) {
        Optional<XcOrders> optional = xcOrderRepository.findById(orderNum);
        return optional.orElse(null);
    }

    @Transactional
    public void updateOrderPayStatus(String orderNum, String payNum) {
        XcOrdersPay xcOrdersPay = new XcOrdersPay();
        xcOrdersPay.setOrderNumber(orderNum);
        Example<XcOrdersPay> example = Example.of(xcOrdersPay);
        Optional<XcOrdersPay> one = xcOrderPayRepository.findOne(example);

        XcOrders xcOrders = new XcOrders();
        xcOrders.setOrderNumber(orderNum);
        Example<XcOrders> example1 = Example.of(xcOrders);
        Optional<XcOrders> one1 = xcOrderRepository.findOne(example1);
        if (one1.isPresent()) {
            XcOrders xcOrders1 = one1.get();
            xcOrders1.setStatus("401002");
            xcOrderRepository.save(xcOrders1);
        }
        String courseId = "";
        if (one.isPresent()) {
            XcOrdersPay xcOrdersPay1 = one.get();
            xcOrdersPay1.setPayNumber(payNum);
            xcOrdersPay1.setStatus("402002");
            courseId = xcOrdersPay1.getId();
            xcOrderPayRepository.save(xcOrdersPay1);
        }

        //添加课程
        if (!courseId.equals("")) {
            learningCourseClient.addopencourse(courseId);
        }

    }
}
