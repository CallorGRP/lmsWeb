package lms.login.model;

public class LoginDto {
		private String loginId;
		private String loginPw;
		
		/* 황태연 추가 */
		private String name;		// session 저장용 이름
		private String chk;			// 로그인계정 조회 value 체크값
		
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
