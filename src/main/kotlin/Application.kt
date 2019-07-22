import io.javalin.Javalin

fun main(args: Array<String>) {
    val app = Javalin.create().start(7000)
    app.get("/accounts") { ctx -> ctx.result("Hello World") }
}