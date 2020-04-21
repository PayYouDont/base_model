package com.payudon.base.common.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName RedisVo
 * @Description TODO
 * @Author pay
 * @DATE 2020/4/20 15:11
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RedisVo {
    @NotBlank
    private String key;

    @NotBlank
    private String value;
}
