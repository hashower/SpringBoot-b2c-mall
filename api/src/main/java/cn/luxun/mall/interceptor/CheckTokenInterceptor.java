package cn.luxun.mall.interceptor;

import cn.luxun.mall.config.JwtUtil;
import cn.luxun.mall.vo.ResultVo;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class CheckTokenInterceptor implements HandlerInterceptor {


	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		String token = request.getHeader("token");

		if (token == null) {
			doResponse(response, ResultVo.fail(10001, "请登录"));
		}
		return true;
	}

	private void doResponse(HttpServletResponse response, ResultVo resultVO) throws IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String s = new ObjectMapper().writeValueAsString(resultVO);
		out.print(s);
		out.flush();
		out.close();
	}
}
