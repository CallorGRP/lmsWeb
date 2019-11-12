package bit.model;

import java.sql.Date;
import java.sql.Time;

public class SubboardDto {
	private String code;
	private int num;
	private int subnum;
	private int idx;
	private Date time;
	private Time timeDetail;
	private String hCode;
	private String id;
	private String content;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getSubnum() {
		return subnum;
	}
	public void setSubnum(int subnum) {
		this.subnum = subnum;
	}
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public Time getTimeDetail() {
		return timeDetail;
	}
	public void setTimeDetail(Time timeDetail) {
		this.timeDetail = timeDetail;
	}
	public String gethCode() {
		return hCode;
	}
	public void sethCode(String hCode) {
		this.hCode = hCode;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
