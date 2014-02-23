package com.gmail.volodymyrdotsenko.qr.web;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gmail.volodymyrdotsenko.qr.domain.UserData;
import com.gmail.volodymyrdotsenko.qr.domain.UserToken;

import flexjson.JSONSerializer;

@Controller
@RequestMapping("/qr")
public class QrController {

	private static final Logger logger = Logger.getLogger(QrController.class);

	@RequestMapping("/is_login/{token}")
	@ResponseBody
	public ResponseEntity<String> is_login(@PathVariable String token) {

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "text/html; charset=utf-8");

		class Confirm {
			private boolean confirm;

			public Confirm(boolean confirm) {
				this.confirm = confirm;
			}

			public boolean isConfirm() {
				return confirm;
			}

			public void setConfirm(boolean confirm) {
				this.confirm = confirm;
			}
		}

		Confirm json = new Confirm(false);

		try {
			UserToken uToken = UserToken.findUserToken(token);

			if (uToken != null)
				json.setConfirm(true);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return new ResponseEntity<String>(toJsonArray(json), headers,
				HttpStatus.OK);
	}

	@RequestMapping("/put/{userName}/{token}")
	@ResponseBody
	public ResponseEntity<String> putToken(@PathVariable String userName,
			@PathVariable String token) {

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "text/html; charset=utf-8");

		class Result {
			private boolean result;

			public Result(boolean result) {
				this.result = result;
			}

			public boolean isResult() {
				return result;
			}

			public void setResult(boolean result) {
				this.result = result;
			}
		}

		Result json = new Result(false);

		try {
			UserData user = UserData.findUserData(userName);

			UserToken uToken = new UserToken();
			uToken.setUser(user);
			uToken.setToken(token);
			uToken.persist();

			json.setResult(true);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return new ResponseEntity<String>(toJsonArray(json), headers,
				HttpStatus.OK);
	}

	@RequestMapping(value = "/getmessage")
	public @ResponseBody
	ResponseEntity<String> getMessage() {
		logger.info("Accessing protected resource");

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=utf-8");

		return new ResponseEntity<String>(toJsonArray(
				new Message(100, "Congratulations!",
						"You have accessed a Basic Auth protected resource.")),
				headers, HttpStatus.OK);
	}
	
//	@RequestMapping(value = "/getmessage", method = RequestMethod.GET)
//	public @ResponseBody String getMessage(HttpServletResponse resp) {
//		logger.info("Accessing protected resource");
//		//resp.setContentType("application/json; charset=utf-8");
//		return "You have accessed a Basic Auth protected resource.";
//	}	

	public static String toJsonArray(Object json) {
		return new JSONSerializer().exclude("*.class").deepSerialize(json);
	}
}