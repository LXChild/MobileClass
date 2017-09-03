package com.chong.vo;

import lombok.*;

import java.util.List;

/**
 * 表格展示对象
 * Created by LXChild on 24/04/2017.
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TableVO<T> {

    private Long total;

    private List<T> rows;
}
