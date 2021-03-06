package com.cafe24.shoppingmall.controller.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;

import com.google.gson.Gson;

public class UserControllerTest extends TemplateTest{
	
	@Override
	public void setup() {
		super.setup();
		cartService.deleteAll();
		userService.deleteAll();
	}


	// 사용자 아이디 체크 Test Case 1. - 아이디 체크 결과 사용가능
	@Test
	public void checkIdTest1() throws Exception{
		checkId("chu1070y", status().isOk());
	}
	
	// 사용자 아이디 체크 Test Case 2. - 아이디 중복
	@Test
	public void checkIdTest2() throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", "chu1070y");
		map.put("pw", "12345678z!");
		map.put("name", "chuchu");
		map.put("email", "aaaaa@naver.com");
		map.put("tel_phone", "010-1234-1234");
		
		registerMember(map, status().isOk());
		checkId("chu1070y", status().isOk());
	}
	
	// 사용자 아이디 체크 Test Case 3. - 아이디 길이 4글자 미만
	@Test
	public void checkIdTest3() throws Exception{
		checkId("chu", status().isBadRequest());
	}
	
	// 사용자 아이디 체크 Test Case 4. - 아이디 길이 18글자 초과
	@Test
	public void checkIdTest4() throws Exception{
		checkId("chu1234567891234567", status().isBadRequest());
	}
	
	// 사용자 아이디 체크 Test Case 5. - 아이디 한글입력
	@Test
	public void checkIdTest5() throws Exception{
		checkId("chu추추", status().isBadRequest());
	}

	
	// 사용자 회원가입 Test Case 1. - 회원 정상 등록(성공)
	@Test
	public void registerMemberTest1() throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", "chu1070y");
		map.put("pw", "12345678z!");
		map.put("name", "chuchu");
		map.put("email", "aaaaa@naver.com");
		map.put("tel_phone", "010-1234-1234");
		
		registerMember(map, status().isOk());
	}

	// 사용자 회원가입 Test Case 2. - 아이디 길이 3미만
	@Test
	public void registerMemberTest2() throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", "chu");
		map.put("pw", "12345678z!");
		map.put("name", "chuchu");
		map.put("email", "aaaaa@naver.com");
		map.put("tel_phone", "010-1234-1234");
		
		registerMember(map, status().isBadRequest());
	}
	
	// 사용자 회원가입 Test Case 3. - 아이디 한글입력
	@Test
	public void registerMemberTest3() throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", "chu추추");
		map.put("pw", "12345678z!");
		map.put("name", "chuchu");
		map.put("email", "aaaaa@naver.com");
		map.put("tel_phone", "010-1234-1234");
		
		registerMember(map, status().isBadRequest());
	}
	
	// 사용자 회원가입 Test Case 4. - 비밀번호 특수문자 미포함
	@Test
	public void registerMemberTest4() throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", "chu1070y");
		map.put("pw", "12345678z");
		map.put("name", "chuchu");
		map.put("email", "aaaaa@naver.com");
		map.put("tel_phone", "010-1234-1234");
		
		registerMember(map, status().isBadRequest());
	}
	
	// 사용자 회원가입 Test Case 5. - 이름 길이 2미만
	@Test
	public void registerMemberTest5() throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", "chu1070y");
		map.put("pw", "12345678z!");
		map.put("name", "추");
		map.put("email", "aaaaa@naver.com");
		map.put("tel_phone", "010-1234-1234");
		
		registerMember(map, status().isBadRequest());
	}
	
	// 사용자 회원가입 Test Case 6. - 이메일 형식 오류
	@Test
	public void registerMemberTest6() throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", "chu1070y");
		map.put("pw", "12345678z!");
		map.put("name", "추연훈");
		map.put("email", "aaaaa@");
		map.put("tel_phone", "010-1234-1234");
		
		registerMember(map, status().isBadRequest());
	}
	
	// 사용자 회원가입 Test Case 7. - 휴대폰 번호 형식 오류
	@Test
	public void registerMemberTest7() throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", "chu1070y");
		map.put("pw", "12345678z!");
		map.put("name", "추연훈");
		map.put("email", "aaaaa@naver.com");
		map.put("tel_phone", "010-1234-124");
		
		registerMember(map, status().isBadRequest());
	}
	
	// 사용자 로그인 Test Case 1. - 정상 로그인(성공)
	@Test
	public void userLoginTest1() throws Exception{
		// 사전에 회원등록해놓긔
		registerMemberTest1();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", "chu1070y");
		map.put("pw", "12345678z!");
		
		userLogin(map, status().isOk());
	}
	
	// 사용자 로그인 Test Case 2. - 아이디 없음
	@Test
	public void userLoginTest2() throws Exception{
		// 사전에 회원등록해놓긔
		registerMemberTest1();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", "chu10");
		map.put("pw", "12345678z!");
		
		userLogin(map, status().isBadRequest());
	}
	
	// 사용자 로그인 Test Case 3. - 비밀번호 틀림
	@Test
	public void userLoginTest3() throws Exception{
		// 사전에 회원등록해놓긔
		registerMemberTest1();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", "chu1070y");
		map.put("pw", "123456zzz!");
		
		userLogin(map, status().isBadRequest());
	}
	
	// 회원정보 조회 Test Case 1. - 회원정보 조회(성공)
	@Test
	public void userInfoReadTest1() throws Exception{
		// 사전에 회원등록해놓긔
		registerMemberTest1();
		
		userInfoRead("chu1070y", status().isOk());
	}
	
	// 회원정보 조회 Test Case 2. - 해당 아이디 없음
	@Test
	public void userInfoReadTest2() throws Exception{
		// 사전에 회원등록해놓긔
		registerMemberTest1();
		
		userInfoRead("aaaa", status().isBadRequest());
	}
	
	// 회원정보 수정 Test Case 1. - 회원정보 수정(성공)
	@Test
	public void userInfoUpdateTest1() throws Exception{
		// 사전에 회원등록해놓긔
		registerMemberTest1();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", "chu1070y");
		map.put("pw", "12345678z!");
		map.put("name", "추추추");
		map.put("email", "chuchu@naver.com");
		map.put("tel_phone", "010-1234-1111");
		
		userInfoUpdate(map, status().isOk());
	}
	
	// 회원정보 수정 Test Case 2. - 비밀번호 문자 불포함, 이메일 형식 오류, 이름 길이 1글자
	@Test
	public void userInfoUpdateTest2() throws Exception{
		// 사전에 회원등록해놓긔
		registerMemberTest1();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", "chu1070y");
		map.put("pw", "12345678!");
		map.put("name", "추");
		map.put("email", "chuchu@");
		map.put("tel_phone", "010-1234-1111");
		
		userInfoUpdate(map, status().isBadRequest());
	}

	// 회원정보 삭제 Test Case 1. - 회원정보 삭제(성공)
	@Test
	public void userInfoDeleteTest1() throws Exception{
		// 사전에 회원등록해놓긔
		registerMemberTest1();
		
		userInfoDelete("chu1070y", status().isOk());
	}
	
	// 회원정보 삭제 Test Case 2. - 해당 회원 없음
	@Test
	public void userInfoDeleteTest2() throws Exception{
		// 사전에 회원등록해놓긔
		registerMemberTest1();
		
		userInfoDelete("chu1070", status().isOk());
	}
	
	// 회원정보 리스트 Test Case 1. - 회원정보 리스트(성공)
	@Test
	public void userInfoListTest1() throws Exception{
		// 사전에 회원등록해놓긔
		registerMemberTest1("chu1");
		registerMemberTest1("chu2");
		registerMemberTest1("chu3");
		registerMemberTest1("chu4");
		registerMemberTest1("chu5");
		
		userInfoList("1", status().isOk());
	}
	// 많은 유저 만들긔
	public void registerMemberTest1(String id) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("pw", "12345678z!");
		map.put("name", "chuchu");
		map.put("email", "aaaaa@naver.com");
		map.put("tel_phone", "010-1234-1234");
		
		registerMember(map, status().isOk());
	}
	
	
	/*
	 * 테스트케이스에 사용될 함수들..
	 */
	// 아이디 체크
	public void checkId(String id, ResultMatcher rm) throws Exception{
		mockMvc
		.perform(get("/api/user/checkId")
		.param("id", id))
		.andDo(print())
		.andExpect(rm);
	}
	
	// 회원정보 수정
	public void userInfoUpdate(Map<String, Object> map, ResultMatcher rm) throws Exception {
		ResultActions resultActions = mockMvc
				.perform(post("/api/user/update")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(map)));
		
		resultActions
			.andDo(print())
			.andExpect(rm);
	}
	
	// 회원정보 삭제
	public void userInfoDelete(String id, ResultMatcher rm) throws Exception {
		ResultActions resultActions = mockMvc
				.perform(post("/api/user/delete")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(id)));
		
		resultActions
			.andDo(print())
			.andExpect(rm);
	}
	
	// 회원정보 조회 - 1명
	public void userInfoRead(String id, ResultMatcher rm) throws Exception {
		ResultActions resultActions = mockMvc
		.perform(get("/api/user/read")
		.param("id", id));
		
		resultActions
		.andDo(print())
		.andExpect(rm);
	}
	
	// 회원정보 리스트
	public void userInfoList(String page, ResultMatcher rm) throws Exception {
		mockMvc
		.perform(get("/api/user/list")
		.param("page", page))
		.andDo(print())
		.andExpect(rm);
	}

	// 회원정보 페이지 for 시나리오용
	public void userPage() throws Exception{
		mockMvc
		.perform(get("/api/user/join"))
		.andDo(print())
		.andExpect(status().isOk())
		;
	}

}
