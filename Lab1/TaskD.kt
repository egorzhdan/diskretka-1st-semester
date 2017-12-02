import java.util.*
import kotlin.collections.ArrayList
import kotlin.system.exitProcess

/**
 * @author Egor Zhdan
 */
object Input {
    private var tokenizer = StringTokenizer("")

    fun nextInt(): Int = nextLong().toInt()

    fun nextLong(): Long {
        if (!tokenizer.hasMoreTokens()) tokenizer = StringTokenizer(readLine()!!)
        return tokenizer.nextToken().toLong()
    }
}

interface Value {
    val calculated: Long
}

data class Const(override val calculated: Long, val index: Int) : Value {
    override fun toString() = "$index"

    override fun hashCode() = toString().hashCode()

    override fun equals(other: Any?): Boolean {
        if (other !is Value) return false
        return calculated == other.calculated
    }
}

data class And(val op1: Value, val op2: Value) : Value {
    override val calculated = op1.calculated and op2.calculated

    override fun toString() = "($op1)&($op2)"

    override fun hashCode() = calculated.hashCode()

    override fun equals(other: Any?): Boolean {
        if (other !is Value) return false
        return calculated == other.calculated
    }
}

data class Or(val op1: Value, val op2: Value) : Value {
    override val calculated = op1.calculated or op2.calculated

    override fun toString() = "($op1)|($op2)"

    override fun hashCode() = calculated.hashCode()

    override fun equals(other: Any?): Boolean {
        if (other !is Value) return false
        return calculated == other.calculated
    }
}

data class Not(val op: Value) : Value {
    override val calculated = op.calculated.inv()

    override fun toString() = "(~$op)"

    override fun hashCode() = calculated.hashCode()

    override fun equals(other: Any?): Boolean {
        if (other !is Value) return false
        return calculated == other.calculated
    }
}

fun isMajor(mask: Long, subMask: Long): Boolean {
    for (i in 0 until 60) {
        if (((subMask shr i) and 1) > 0) {
            if (((mask shr i) and 1) == 0.toLong()) return false
        }
    }
    return true
}

fun main(args: Array<String>) {
    val n = Input.nextInt()
    val initial = LongArray(n, { Input.nextLong() })
    val result = Input.nextLong()

    val all = ArrayList<Value>(n * 2)
    initial.forEachIndexed { idx, it ->
        all.add(Const(it, idx + 1))
        all.add(Not(all.last()))
    }

    for (mask in 0 until (1 shl n * 2)) {
        var cur: Value? = null
        for (idx in 0 until n * 2) {
            if (((mask shr idx) and 1) > 0) {
                if (cur == null) {
                    cur = all[idx]
                } else {
                    cur = And(cur, all[idx])
                }
            }
        }

        if (cur != null) {
            all.add(cur)
        }
    }

    var res: Value? = null
    all.forEach {
        if (isMajor(result, it.calculated)) {
            if (res == null) res = it
            else res = Or(res!!, it)
        }
    }

    if (res != null && res!!.calculated == result) {
        println(res)
        exitProcess(0)
    }

    println("Impossible")
}
