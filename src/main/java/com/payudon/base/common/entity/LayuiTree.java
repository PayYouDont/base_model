package com.payudon.base.common.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName BaseTree
 * @Description TODO
 * @Author pay
 * @DATE 2019/11/6 10:28
 **/
@Data
public class LayuiTree {
    //节点标题
    private String label;
    private Integer id;
    private List<LayuiTree> children = new ArrayList<> ();
    private String href;
    //节点是否初始展开，默认 false
    private boolean spread = false;
    private boolean checked = false;
    //节点是否为禁用状态。默认 false
    private boolean disabled = false;
}
