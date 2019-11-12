package bit.model;

import java.sql.Date;

public class EstimateDto {
	private String code;
	private String hcode;
	private int id;
	private Date deadline;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@Override
	public String toString() {
		return "EstimateDto [code=" + code + ", hcode=" + hcode + ", id=" + id + ", deadline=" + deadline
				+ ", estimatecode=" + estimatecode + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((deadline == null) ? 0 : deadline.hashCode());
		result = prime * result + ((estimatecode == null) ? 0 : estimatecode.hashCode());
		result = prime * result + ((hcode == null) ? 0 : hcode.hashCode());
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EstimateDto other = (EstimateDto) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (deadline == null) {
			if (other.deadline != null)
				return false;
		} else if (!deadline.equals(other.deadline))
			return false;
		if (estimatecode == null) {
			if (other.estimatecode != null)
				return false;
		} else if (!estimatecode.equals(other.estimatecode))
			return false;
		if (hcode == null) {
			if (other.hcode != null)
				return false;
		} else if (!hcode.equals(other.hcode))
			return false;
		if (id != other.id)
			return false;
		return true;
	}
	public String getHcode() {
		return hcode;
	}
	public void setHcode(String hcode) {
		this.hcode = hcode;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDeadline() {
		return deadline;
	}
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
	public String getEstimatecode() {
		return estimatecode;
	}
	public void setEstimatecode(String estimatecode) {
		this.estimatecode = estimatecode;
	}
	private String estimatecode;
}
