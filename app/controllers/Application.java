package controllers;

import java.util.ArrayList;
import java.util.List;

import play.Logger;
import play.libs.F.Function;
import play.libs.F.Promise;
import play.libs.ws.WS;
import play.libs.ws.WSRequestHolder;
import play.libs.ws.WSResponse;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.characterlist;
import views.html.index;

public class Application extends Controller {

	public static Result index() {
		return ok(index.render("Your new application is ready."));
	}

	public static Result sayHello() {
		return ok("Hello " + request().remoteAddress());
	}

	public static Result sayHelloTo(String name) {
		if (name.toLowerCase().matches("[a-z]+")) {
			String theName = name.toUpperCase();
			return ok("Hello " + theName);
		} else {
			return badRequest("Provide a proper name!");
		}
	}

	public static Result listCharacters(String text) {
		List<Character> characters = new ArrayList<Character>();
		for (char c : text.toLowerCase().toCharArray()) {
			if (!characters.contains(c)) {
				characters.add(c);
			}
		}
		return ok(characterlist.render(text, characters));
	}

	public static Promise<Result> longRunningAction() {
		Logger.info("Long running action entry");
		WSRequestHolder duckduck = WS.url("https://duckduckgo.com/");
		Promise<WSResponse> duckduckResponse = duckduck.get();
		Promise<Result> result = duckduckResponse.map(toResult);
		Logger.info("Long running action exit");
		return result;
	}

	private static Function<WSResponse, Result> toResult = new Function<WSResponse, Result>() {
		public Result apply(WSResponse response) {
			Logger.info("Inside the toResult function");
			return ok(response.getBody()).as("text/html");
		}
	};

}
