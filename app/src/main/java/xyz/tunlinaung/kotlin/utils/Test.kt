package xyz.tunlinaung.kotlin.utils

import kotlin.system.measureTimeMillis

    private val os: String by lazy {
        print("Computing...")
        "hello"
    }

    fun main(args: Array<String>) {


        val time = measureTimeMillis {
            println("Hello, world")
        }

        print("Finish time ${time} ms")

        var s = "test"
        s += "hello"

        for (i in 1..3) {
            println(os)
        }
    }
