package lms.login.model;

public class LoginDto {
		private String loginId;
		private String loginPw;
		
		/* Ȳ�¿� �߰� */
		private String name;		// session ����� �̸�
		private String chk;			// �α��ΰ��� ��ȸ value üũ��
		
		public String getLoginId() {
			return loginId;
		}
		public void setLoginId(String loginId) {
			this.loginId = loginId;
		}
		public String getLoginPw() {
			return loginPw;
		}
		public void setLoginPw(String loginPw) {
			this.loginPw = loginPw;
		}
		public String getChk() {
			return chk;
		}
		public void setChk(String chk) {
			this.chk = chk;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
}
