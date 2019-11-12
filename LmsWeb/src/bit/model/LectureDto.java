package bit.model;

import java.sql.Date;

public class LectureDto {
	private String code;			// 강의 코드
	private String name;			// human테이블 이름명
	private Date departday;			//
	private Date endday;			//
	private int listener;			//대상자
	private Date starttime;			//시작기간
	private Date endtime;			//종료기간
	private String room;			//강의실
	
	/* 황태연 추가 */
	private String lectureName;		// 강의명
	private String content;			// 강의내용
	private Date regday;			// 강의등록일자
	private int rownum;				// 강의NO(정렬용도)
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((departday == null) ? 0 : departday.hashCode());
		result = prime * result + ((endday == null) ? 0 : endday.hashCode());
		result = prime * result + ((endtime == null) ? 0 : endtime.hashCode());
		result = prime * result + listener;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((room == null) ? 0 : room.hashCode());
		result = prime * result + ((starttime == null) ? 0 : starttime.hashCode());
		return result;
	}
	@Override
	public String toString() {
		return "LectureDto [code=" + code + ", name=" + name + ", departday=" + departday + ", endday=" + endday
				+ ", listener=" + listener + ", starttime=" + starttime + ", endtime=" + endtime + ", room=" + room
				+ "]";
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LectureDto other = (LectureDto) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (departday == null) {
			if (other.departday != null)
				return false;
		} else if (!departday.equals(other.departday))
			return false;
		if (endday == null) {
			if (other.endday != null)
				return false;
		} else if (!endday.equals(other.endday))
			return false;
		if (endtime == null) {
			if (other.endtime != null)
				return false;
		} else if (!endtime.equals(other.endtime))
			return false;
		if (listener != other.listener)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (room == null) {
			if (other.room != null)
				return false;
		} else if (!room.equals(other.room))
			return false;
		if (starttime == null) {
			if (other.starttime != null)
				return false;
		} else if (!starttime.equals(other.starttime))
			return false;
		return true;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDepartday() {
		return departday;
	}
	public void setDepartday(Date departday) {
		this.departday = departday;
	}
	public Date getEndday() {
		return endday;
	}
	public void setEndday(Date endday) {
		this.endday = endday;
	}
	public int getListener() {
		return listener;
	}
	public void setListener(int listener) {
		this.listener = listener;
	}
	public Date getStarttime() {
		return starttime;
	}
	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}
	public Date getEndtime() {
		return endtime;
	}
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	
	
	public String getLectureName() {
		return lectureName;
	}
	public void setLectureName(String lectureName) {
		this.lectureName = lectureName;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getRegday() {
		return regday;
	}
	public void setRegday(Date regday) {
		this.regday = regday;
	}
	public int getRownum() {
		return rownum;
	}
	public void setRownum(int rownum) {
		this.rownum = rownum;
	}
}
