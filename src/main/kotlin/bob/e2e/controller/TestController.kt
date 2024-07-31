package org.example.bob.e2e.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.Resource
import org.springframework.http.ResponseEntity
import org.springframework.util.StreamUtils
import java.nio.charset.StandardCharsets
import kotlin.test.Test
import kotlin.test.assertEquals

@RestController
@RequestMapping("/keypad")
class KeypadController {

    @GetMapping
    fun generateKeypad(): ResponseEntity<String> {
        val resource: Resource = ClassPathResource("keypad/keypadLayout.txt")
        val inputStream = resource.inputStream
        val keypadLayout = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8)

        return ResponseEntity.ok(keypadLayout)
    }

    @Test
    fun testKeypadInput() {
        val keypad = Keypad()
        val inputs = listOf("1", "2", "3", "c", "4", "5", "6", "e")
        val expectedOutputs = listOf(
            "Current input: ",
            "Current input: 1",
            "Current input: 12",
            "Current input: 123",
            "Current input: ",
            "Current input: 4",
            "Current input: 45",
            "Current input: 456"
        )

        var outputIndex = 0

        for (input in inputs) {
            println("Pressing key: $input")
            when (input) {
                in "0".."9" -> keypad.start(input)
                "c" -> keypad.clear()
                "e" -> {
                    assertEquals(expectedOutputs[outputIndex], keypad.display())
                    println("Exiting test...")
                    return
                }
            }
            assertEquals(expectedOutputs[outputIndex], keypad.display())
            outputIndex++
        }
    }
}
