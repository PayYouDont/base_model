package com.payudon.base.sys.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.payudon.base.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.util.StringUtils;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="tv_resolver")
@EqualsAndHashCode(callSuper = false)
public class TVResolver extends BaseEntity {
	private Long qrCreateTime; // current_time二维码创建时间戳
	private String terminalId;
	private Long baseTime; // 收视记录基准时间
    @OneToMany(targetEntity = TVRecord.class)
    @JoinColumn(name = "record_id",referencedColumnName = "id")
    @JsonIgnore
	private List<TVRecord> tvRecords;

	public boolean resolve(String prot) throws Exception{
		// 解析数据类型头
		if (StringUtils.isEmpty (prot) || prot.charAt(0) != '%') {
			throw new RuntimeException("protocol empty, or head tag isnt %");
		}
		String[] heads = prot.substring(1).split("\\*"); // 去掉%,并分割
		if (heads.length < 7 || !heads[0].equals("01") || !heads[2].equals("WR")) {
			throw new RuntimeException("protocol head illegal");
		}
		setQrCreateTime (Long.valueOf(heads[1].trim(), 16));
		setTerminalId(heads[5].trim());

		int datalen = Integer.parseInt(heads[4].trim(), 16);
		if (datalen <= 0) {
			throw new RuntimeException("protocol datalenm<=0");
		}

		// 解析数据体
		String data = prot.substring(datalen);
		if (StringUtils.isEmpty (data) || data.charAt(0) != '@') {
			throw new RuntimeException("protocol data empty, or data tag isnt @");
		}
		String[] recitems = data.substring(1).split("#"); // 去掉@,并分割
		if (recitems.length < 2) { // 至少base_time+一条节目记录
			throw new RuntimeException("protocol data illegal");
		}
		setBaseTime(Long.parseLong(recitems[0].trim(), 16));

		List<TVRecord> records = new ArrayList<>();
		Long cnt = getBaseTime();
		for (int i = 1; i != recitems.length; ++i) {
			String[] offs = recitems[i].split(",");
			if (offs.length != 3) {
				continue;
			}

			TVRecord record = new TVRecord();

			record.setServiceId(Integer.valueOf(offs[0].trim()));
			record.setRecordInterval (Long.valueOf(offs[2].trim()));

			cnt += Long.valueOf(offs[1].trim());
			record.setInTime(cnt);

			cnt += Long.valueOf(offs[2].trim());
			record.setOffTime(cnt);

			records.add(record);
		}
		setTvRecords (records);

		return true;
	}
}
