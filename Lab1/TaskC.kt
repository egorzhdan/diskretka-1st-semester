import java.util.*
import kotlin.system.exitProcess

/**
 * @author Egor Zhdan
 */
object Input {
    private var tokenizer = StringTokenizer("")

    fun nextToken(): String {
        if (!tokenizer.hasMoreTokens()) tokenizer = StringTokenizer(readLine()!!)
        return tokenizer.nextToken()
    }

    fun nextInt() = nextToken().toInt()
}


fun main(args: Array<String>) {
    val n = Input.nextInt()

    var notSave0 = false
    var notSave1 = false
    var notLinear = false
    var notM = false
    var notS = false
    for (i in 1..n) {
        val argc = Input.nextInt()
        val res = Input.nextToken()

        if (res.first() != '0') notSave0 = true
        if (res.last() != '1') notSave1 = true

        for (j in 0 until res.length) {
            if (res[j] == res[res.length - 1 - j]) {
                notS = true
            }
        }

        for (mask in 0 until res.length) {
            if (res[mask] == '0')
                for (bit in 0 until argc) {
                    if (((1 shl bit) and mask) > 0) {
                        val prevMask = mask xor (1 shl bit)
                        if (res[prevMask] == '1') {
                            notM = true
                        }
                    }
                }
        }

        val tr = Array(1 shl argc, { IntArray(1 shl argc) })
        for (row in 0 until tr.size) {
            tr[row][0] = res[row] - '0'
        }
        for (col in 1 until tr.size) {
            for (row in 0 until tr.size - col) {
                tr[row][col] = tr[row][col - 1] xor tr[row + 1][col - 1]
            }
        }

        for (col in 0 until tr.size) {
            if (tr[0][col] == 1) {
                if (Integer.bitCount(col) > 1) {
                    notLinear = true
                }
            }
        }
    }

    println(if (notSave0 && notSave1 && notM && notS && notLinear) "YES" else "NO")
}
