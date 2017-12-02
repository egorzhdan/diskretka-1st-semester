import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashSet
import kotlin.system.exitProcess

/**
 * @author Egor Zhdan
 */
object Input {
    private var tokenizer = StringTokenizer("")

    fun nextInt(): Int {
        if (!tokenizer.hasMoreTokens()) tokenizer = StringTokenizer(readLine()!!)
        return tokenizer.nextToken().toInt()
    }
}


fun main(args: Array<String>) {
    val n = Input.nextInt()
    val k = Input.nextInt()
    val coeff = Array(k, {
        Array(n, {
            val c = Input.nextInt()
            when (c) {
                -1 -> null
                0 -> false
                1 -> true
                else -> error("")
            }
        })
    })
    val values = Array<Boolean?>(n, { null })

    while (true) {
        var hasLonely = false
        for (dis in 0 until k) {
            val undef = (0 until n).filter { values[it] == null && coeff[dis][it] != null }
                    .mapTo(LinkedList<Pair<Int, Boolean>>()) { it to coeff[dis][it]!! } // {index, needed}

            if (undef.size == 1) {
                hasLonely = true
                val it = undef[0]
                values[it.first] = it.second

                coeff[dis].fill(null)
                (0 until k).filter { other -> dis != other && coeff[other][it.first] == it.second }
                        .forEach { coeff[it].fill(null) }
            }
        }

        var hasFalse = false
        for (dis in 0 until k) {
            var is0 = true
            var allNulls = true
            for (i in 0 until n) {
                if (coeff[dis][i] != null) {
                    allNulls = false

                    if (values[i] == null) {
                        is0 = false
                    }
                    if (coeff[dis][i] == values[i]) {
                        is0 = false
                    }
                }
            }
            if (is0 && !allNulls) {
                hasFalse = true
            }
        }
        if (hasFalse) {
            println("YES")
            exitProcess(0)
        }

        if (!hasLonely) {
            println("NO")
            exitProcess(0)
        }
    }
}
