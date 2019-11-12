package bit.model;

import java.sql.Date;
import java.sql.Time;

public class BbsDto {
	private String code;
	private int num;
	private int subnum;
	private Date time;
	private String sub;
	private String hcode;
	private String id;
	private String content;
	private int cnt;
	
	private Time timeDetail;
	
	public Time getTimeDetail() {
		return timeDetail;
	}
	public void setTimeDetail(Time timeDetail) {
		this.timeDetail = timeDetail;
	}
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
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getSub() {
		return sub;
	}
	public void setSub(String sub) {
		this.sub = sub;
	}
	public String getHcode() {
		return hcode;
	}
	public void setHcode(String hcode) {
		this.hcode = hcode;
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
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
}
