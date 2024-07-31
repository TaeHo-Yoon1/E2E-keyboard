package bob.e2e.controller

fun main() {
    val keypad = Keypad()
    keypad.start()
}

class Keypad {
    private val input = StringBuilder()

    fun start() {
        while (true) {
            println("Current input: $input")
            println("Press a key (0-9) or 'c' to clear, 'e' to exit:")

            when (val key = readLine() ?: "") {
                in "0".."9" -> {
                    input.append(key)
                }
                "c" -> {
                    input.clear()
                }
                "e" -> {
                    println("Exiting...")
                    return
                }
                else -> {
                    println("Invalid input")
                }
            }
        }
    }
}
