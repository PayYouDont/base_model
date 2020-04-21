package com.payudon.base.common.entity;

import lombok.Data;

/**
 * @ClassName Page
 * @Description TODO
 * @Author pay
 * @DATE 2019/11/8 17:23
 **/
@Data
public class LayuiTabPage {
    private int pageIndex;
    private int pageSize;
    private String query;
}
