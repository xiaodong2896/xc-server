package com.xuecheng.framework.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;


@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class QueryResult<T> {
    //数据列表
    private List<T> list;
    //数据总数
    private long total;
}
