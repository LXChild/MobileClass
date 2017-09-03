package com.chong.utils;

import com.chong.dto.SortDTO;
import org.springframework.data.domain.Sort;

/**
 * 排序工具集
 * Created by LXChild on 16/04/2017.
 */
public class SortUtils {

    private SortUtils() {}

    public static Sort basicSort() {
        return basicSort("desc", "id");
    }

    public static Sort basicSort(String orderType, String orderField) {
        return new Sort(Sort.Direction.fromString(orderType), orderField);
    }

    public static Sort basicSort(SortDTO... dtos) {
        Sort result = null;
        for (SortDTO dto : dtos) {
            if (result == null) {
                result = new Sort(Sort.Direction.fromString(dto.getOrderType()), dto.getOrderField());
            } else {
                result = result.and(new Sort(Sort.Direction.fromString(dto.getOrderType()), dto.getOrderField()));
            }
        }
        return result;
    }
}
