package com.gatling.tests

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class PiggyUseAccount extends Simulation {

	val bearer_token = "Bearer" + "7bbdaefe-9e5c-413a-80dd-87c383691a3c"
	//must change this every time Piggy Metrics is restarted, should fix to not need this eventually

	val httpProtocol = http
		.baseUrl("http://localhost")
		.inferHtmlResources(BlackList(""".*\.js""", """.*\.css""", """.*\.gif""", """.*\.jpeg""", """.*\.jpg""", """.*\.ico""", """.*\.woff""", """.*\.woff2""", """.*\.(t|o)tf""", """.*\.png""", """.*detectportal\.firefox\.com.*"""), WhiteList())
		.acceptHeader("*/*")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("en-US,en;q=0.5")
		.userAgentHeader("Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:109.0) Gecko/20100101 Firefox/118.0")

	val headers_0 = Map(
		"Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8",
		"If-Modified-Since" -> "Wed, 06 Nov 2024 18:57:30 GMT",
		"Upgrade-Insecure-Requests" -> "1")

	/**val headers_1 = Map(
		"Content-Type" -> "application/x-www-form-urlencoded; charset=UTF-8",
		"Origin" -> "http://localhost",
		"X-Requested-With" -> "XMLHttpRequest")*/

	val headers_2 = Map(
		"X-Requested-With" -> "XMLHttpRequest",
		"authorization" -> bearer_token)

	val headers_3 = Map(
		"Content-Type" -> "application/json",
		"Origin" -> "http://localhost",
		"X-Requested-With" -> "XMLHttpRequest",
		"authorization" -> bearer_token)



	val scn = scenario("PiggyUseAccount")
		// starting reate account
		.exec(http("homepage")
			.get("/")
			.headers(headers_0))
		.pause(1)
		/*.exec(http("send oauth")
			.post("/uaa/oauth/token")
			.headers(headers_1)
			.formParam("scope", "ui")
			.formParam("username", "piggycreateaccount")
			.formParam("password", "piggycreateaccount")
			.formParam("grant_type", "password")
			.resources(http("request_2")
			.get("/accounts/current")
			.headers(headers_2)))
		.pause(1)*/
		.exec(http("add_income")
			.put("/accounts/current")
			.headers(headers_3)
			.body(RawFileBody("com/gatling/tests/piggyuseaccount/0003_request.json")))
		.pause(1)
		.exec(http("add_expense")
			.put("/accounts/current")
			.headers(headers_3)
			.body(RawFileBody("com/gatling/tests/piggyuseaccount/0004_request.json")))
		.pause(1)
		.exec(http("add_note")
			.put("/accounts/current")
			.headers(headers_3)
			.body(RawFileBody("com/gatling/tests/piggyuseaccount/0005_request.json")))
		.pause(1)
		.exec(http("add_interest")
			.put("/accounts/current")
			.headers(headers_3)
			.body(RawFileBody("com/gatling/tests/piggyuseaccount/0006_request.json")))
		.pause(1)
		.exec(http("back_to_homepage")
			.get("/")
			.headers(headers_0))

	//Run the test simulation with the scenarios
	setUp(scn.inject(rampUsers(10000).during(30))).protocols(httpProtocol)
}
