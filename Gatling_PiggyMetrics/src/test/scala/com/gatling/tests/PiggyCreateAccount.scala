package com.gatling.tests

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class PiggyCreateAccount extends Simulation {

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

	val headers_1 = Map(
		"Content-Type" -> "application/json",
		"Origin" -> "http://localhost",
		"X-Requested-With" -> "XMLHttpRequest")

	val headers_2 = Map(
		"Content-Type" -> "application/x-www-form-urlencoded; charset=UTF-8",
		"Origin" -> "http://localhost",
		"X-Requested-With" -> "XMLHttpRequest")

	val headers_3 = Map(
		"X-Requested-With" -> "XMLHttpRequest",
		"authorization" -> "Bearer 5ca1b53f-40df-4f68-8831-16ddd31b7959")



	val scn = scenario("PiggyCreateAccount")
		.exec(http("request_0")
			.get("/")
			.headers(headers_0))
		.pause(1)
		.exec(http("request_1")
			.post("/accounts/")
			.headers(headers_1)
			.body(RawFileBody("com/gatling/tests/piggycreateaccount/0001_request.json"))
			.resources(http("request_2")
			.post("/uaa/oauth/token")
			.headers(headers_2)
			.formParam("scope", "ui")
			.formParam("username", "piggycreateaccount")
			.formParam("password", "piggycreateaccount")
			.formParam("grant_type", "password"),
	http("request_3")
			.get("/accounts/current")
			.headers(headers_3)))
		.pause(1)
		.exec(http("request_4")
			.get("/")
			.headers(headers_0))

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}
