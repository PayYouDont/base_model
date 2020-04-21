package com.payudon.base.sys.entity;

import com.payudon.base.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
@Table(name="tv_record")
@EqualsAndHashCode(callSuper = false)
public class TVRecord extends BaseEntity {
	private Integer serviceId;
	private Long inTime;
	private Long offTime;
	private Long recordInterval;
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "resolve_id",nullable=false)
	private TVResolver tvResolver;
}
